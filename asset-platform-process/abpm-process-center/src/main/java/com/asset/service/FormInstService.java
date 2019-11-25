package com.asset.service;

import
        com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.converter.FormConverter;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.exception.*;
import com.asset.filter.DuplicateFilter;
import com.asset.filter.SceneFilter;
import com.asset.filter.UserIdFilter;
import com.asset.javabean.*;
import com.asset.javabean.form.ColumnItem;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.FormSheet;
import com.asset.dto.*;
import com.asset.service.impl.ActRuVariableService;
import com.asset.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.dom4j.DocumentException;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 以下对流程实例的执行均只考虑非并行分支的情况
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FormInstService implements IFormInstService {
    Logger logger = LoggerFactory.getLogger(FormInstService.class);

    @Autowired
    FlowableService flowableService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    ActRuVariableService actRuVariableService;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    AsFormInstMapper asFormInstMapper;

    /**
     * 业务入口
     * 选中某个表单模型项，显示该表单第一个节点的formSheet信息
     *
     * @param formModelId
     * @return
     */
    public JSONObject showNewFormSheet(String formModelId) throws InfoException {
        //获取对应流程模型ID以及流程模型中第一个节点ID
        String procModelId = formModelService.getProcModelID(formModelId);
        String firstNodeId = procNodeService.getFirstNodeId(procModelId);

        String formSheetStr = formModelService.getModelSheetStr(formModelId);
        FormSheet sheet = FormConverter.jsonToEntity(formSheetStr);

        List<FormItem> items = sheet.getList();
        for (int j = 0; j < items.size(); j++) {
            FormItem cur = items.get(j);
            //说明是普通的表单项元素，不是栅格元素
            if (!cur.getType().equals("grid")) {
                //获取当前Authority
                Integer curAuthority = authorityService.getCurAuthority(procModelId, firstNodeId, cur.getKey());
                //添加权限信息
                authorityService.handleItemAuthority(cur, curAuthority);
            } else {
                handleGridColumn(cur.getColumns(), procModelId, firstNodeId);
            }
        }


        formSheetStr = FormConverter.entityToJson(sheet);

        HashMap<String, String> map = new HashMap<>();
        map.put("form_json", formSheetStr);
        JSONObject object = JSON.parseObject(JSON.toJSONString(map));
        return object;
    }

    private void handleGridColumn(List<JSONObject> columns, String procModelId, String nodeId) {
        for (int m = 0; m < columns.size(); m++) {
            //这个object就是最外面一层grid中的元素列表
            JSONObject object = columns.get(m);
            //从这里由 object 变为 columnItem ，至此之后对columnItem的操作都不会再传导到object对象，需要手动进行传导
            ColumnItem columnItem = JSON.parseObject(JSON.toJSONString(object), ColumnItem.class);
            List<FormItem> list = columnItem.getList();
            for (int n = 0; list.size() != 0 && n < list.size(); n++) {
                FormItem cur = list.get(n);
                if (!cur.getType().equals("grid")) {
                    //获取当前Authority
                    Integer curAuthority = authorityService.getCurAuthority(procModelId, nodeId, cur.getKey());
                    //添加权限信息
                    authorityService.handleItemAuthority(cur, curAuthority);
                } else {
                    handleGridColumn(cur.getColumns(), procModelId, nodeId);
                }
            }

            for (Map.Entry<String, Object> set : object.entrySet()) {
                if (set.getKey().equals("list"))
                    set.setValue(list);
            }

        }


    }

    /**
     * 业务入口
     * 发起一个新的表单实例
     *
     * @param dto
     * @return
     */
    public String[] commitNewFormInst(FormInstRecCreate dto) throws DocumentException, FlowableException, DatabaseException, InterruptedException {
        String procInst = commitFormInst(dto);

        //生成一个或多个外链，当前待办的任务节点的执行人会受到这个URL，执行人点击这个URL就会跳转到相应的页面进行登录
        return generateUrls(procInst);
    }

    //发起实例
    public String commitFormInst(FormInstRecCreate dto) throws DocumentException, DatabaseException, InterruptedException {
        //1、先获取与表单模型唯一绑定的流程模型ID
        String procModelID = formModelService.getProcModelID(dto.getForm_model_id());
        //直接由流程模型后台创建流程实例
        HashMap<String, Object> map = procInstService.createProcInstance(procModelID);

        ProcessInstance procInst = (ProcessInstance) map.get("inst");
        String defID = (String) map.get("defID");
        String deployID = (String) map.get("deployID");

        //发起一个实例时，第一个节点可能是会签节点，所以会有多个taskId，需要执行完成一个，然后剩余的
        String[] taskIDs = ProcUtils.getTaskIDs(procInst.getProcessInstanceId());

        FormInstDO inst = createFormInst(dto.getForm_inst_sheet(),
                procInst.getProcessInstanceId(),
                dto.getEditor(),
                dto.getForm_model_id(),
                dto.getForm_inst_value(),
                taskIDs[0]);
        formInstMapper.insert(inst);

        String[] hasAddedNodeId = new String[taskIDs.length];
        int c = 0;
        hasAddedNodeId[c] = flowableService.getNodeId(taskIDs[0]);

        //2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        for (int i = 1; i < taskIDs.length; i++) {
            String curNodeId = flowableService.getNodeId(taskIDs[i]);
            if (!CommonUtils.isStringArrayContain(hasAddedNodeId, curNodeId)) {
                FormInstDO instUncomplete = createFormInst(dto.getForm_inst_sheet(),
                        procInst.getProcessInstanceId(),
                        null,
                        dto.getForm_model_id(),
                        dto.getForm_inst_value(),
                        taskIDs[i]);
                formInstMapper.insertSelective(instUncomplete);
                c++;
                hasAddedNodeId[c] = flowableService.getNodeId(taskIDs[i]);
            }
        }


        //3、数据库表新建流程实例条目,注意这里还需要存储一个叫form_inst_all_value的字段
        ProcInstDO asProcInstDO = new ProcInstDO.Builder()
                .procInstId(procInst.getProcessInstanceId())
                .procModelId(procModelID)
                .procDefId(defID)
                .procDeployId(deployID)
                .committer(dto.getEditor())
                .status(Constants.PROC_INST_ENABLE)
                .formInstAllValue(dto.getForm_inst_value()).build();

        procInstService.insertProcInst(asProcInstDO);

        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(taskIDs[0]);
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(procInst.getProcessInstanceId())
                .form_inst_value(dto.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);
        //至此，相当于把第一个任务节点要填的表单内容存进数据库了，而且绑定的流程实例也存进了数据库，当前流程应当流转到下个任务节点上了
        Thread.sleep(1000);
        ProcUtils.completeTask(taskIDs[0]);

        //在as_form_inst表中创建下一个还没被执行的任务节点的TASK条目
        procInstService.saveUnCompleteTask(
                procInst.getProcessInstanceId(),
                dto.getForm_model_id());

        return procInst.getProcessInstanceId();
    }

    /*
     * nfq:2019/10/11
     * 这个方法的目的就是把获取的真正的表单实例放到一个集合里
     * */
    @Override
    public List<CommitFormInstDO> listComFormInst(String userID,
                                                  Integer taskType,
                                                  String curSelectSceneId,
                                                  String sectionId) throws InfoException, ProcException, FormException {

        List<CommitFormInstDO> tasks = flowableService.listComFormInst1(userID, curSelectSceneId);
        // ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
        //formInstVOs.add(tasks);
        //return formInstVOs;
        return tasks;


    }

    /**
     * 业务入口
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     *
     * @param userID
     * @param taskType
     * @param curSelectSceneId 当前用户选择的工作场景Id
     * @param sectionId
     * @return
     * @throws InfoException
     * @throws ProcException
     * @throws FormException
     */
    public List<FormInstVO> listFormInst(String userID,
                                         Integer taskType,
                                         String curSelectSceneId,
                                         String sectionId) throws InfoException, ProcException, FormException {
        /*nfq1010:首先是获取流转用户的taskID  （是通过用户的ID来获取流转到该用户的task    这里是获取了多个 说明一个用户可能有多个task）？？？
         * */
        //1、先获取流转到该用户对应的FlowableTaskDO
        List<FlowableTaskDO> tasks = flowableService.listCurTasks(userID);
        if (tasks.size() == 0)
            throw new SizeNullException("表单实例为空");

        List<TaskBO> taskBOs = constructTaskBO(tasks);

        //2、对上面集合进行遍历，从as_proc_node中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        taskBOs = dressTaskByType(taskBOs, taskType);
        if (taskBOs.size() == 0)
            throw new SizeNullException("表单实例为空");

        //3、获取真正的表单实例表
        ArrayList<FormInstDO> formInstDOs = (ArrayList<FormInstDO>) getFormInsts(taskBOs);
        ArrayList<FormInstBO> formInstBOs = new ArrayList<>();

        //从数据库中找到的所有任务节点信息
        for (FormInstDO doo : formInstDOs) {
            String procModelId = procInstService.getProcModelId(doo.getProcInstId());
            String nodeId = flowableService.getNodeId(doo.getTaskId());
            ProcNodeDO nodeDO = procNodeService.getNodeDO(procModelId, nodeId);

            FormInstBO boo = new FormInstBO.Builder()
                    .curUserId(userID)
                    .curTaskType(taskType)
                    .procModelId(procModelId)
                    .nodeId(nodeId)
                    .ifJointSign(nodeDO.getIfJointSign())
                    .sceneId(formModelService.getSceneId(doo.getFormModelId())).build();
            BeanUtils.copyProperties(doo, boo);
            if (!StringUtils.isEmpty(nodeDO.getCandidateUser()))
                boo.setCandidateUser(nodeDO.getCandidateUser().split("\\|"));
            if (!StringUtils.isEmpty(nodeDO.getCandidateGroup()))
                boo.setCandidateGroup(nodeDO.getCandidateGroup().split("\\|"));

            boo.setCommitter(procInstService.getCommitter(doo.getTaskId()));

            formInstBOs.add(boo);
//            formInstBOs.add(new FormInstBO(doo,userID,taskType,nodeDO,
//                    formModelService.getSceneId(doo.getFormModelId())));
        }

        //4、对所有表单实例的表单Sheet进行表单项操作权限设置
        for (int i = 0; i < formInstBOs.size(); i++) {
            FormInstDO inst = formInstBOs.get(i);
            authorityService.handleFormSheetAuthority(inst);
        }

        UserIdFilter userIdFilter = new UserIdFilter();
        DuplicateFilter duplicateFilter = new DuplicateFilter();
        SceneFilter sceneFilter = new SceneFilter();
        //对不属于当前用户的表单任务进行筛选
        ArrayList<FormInstBO> filtrate1 = userIdFilter.filtrate(formInstBOs, sectionId);
        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
        ArrayList<FormInstBO> filtrate2 = duplicateFilter.filtrate(filtrate1);
        //过滤工作场景
        ArrayList<FormInstBO> filtrate3 = sceneFilter.filtrate(filtrate2, curSelectSceneId);


        ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
        for (int i = 0; i < filtrate3.size(); i++) {
            FormInstBO boo = filtrate3.get(i);
//            String committer = procInstService.getCommitter(filtrate3.get(i).getTaskId());
            String formInstValue = procInstService.getFormInstAllValue(boo.getProcInstId());
            Date commitTime = procInstService.getCommitTime(boo.getProcInstId());   // 这个指的是流程实例发起的时间
            FormInstVO voo = new FormInstVO.Builder()
                    .commitTime(commitTime.getTime())
                    .title(formModelService.getFormName(boo.getFormModelId()))   //title应该是对应的表单模型的名称
                    .build();
            BeanUtils.copyProperties(boo, voo);
            voo.setFormInstValue(formInstValue);

            //content那一项内容应该是 根据需求显示表单模板中的一些字段信息（这个字段信息的动态设置应该是放在as_form_model表中），如果没有对这个进行设置的话，就显示这个任务的类型
            //现在暂时写死
            String zichanyijiaoFormModelId = "660d7d6f-d83f-11e9-a9e8-0242ac120006";
            if (boo.getFormModelId().equals(zichanyijiaoFormModelId)) {
                JSONObject jsonObject = JSON.parseObject(formInstValue);
                String assetName = (String) jsonObject.get("input_1566366027958");
                String assetNum = (String) jsonObject.get("input_1566366061170");
//                String committerForContent = (String) jsonObject.get("input_1568450723142");
                String committerForContent = "金伟刚";
//                String committerSection = (String) jsonObject.get("input_1568450690311");
                String committerSection = (String) jsonObject.get("input_1568450690311");
                String taker = (String) jsonObject.get("input_1566958355545");
                String takerSection = (String) jsonObject.get("input_1566960206187");
                voo.setContent("资产名称：" + assetName + " 数量：" + assetNum + " 申请人：" + committerForContent + " 所在部门：" + committerSection + " 接收人：" + taker + " 所在部门：" + takerSection);
            } else {
                if (boo.getNodeType() == Constants.AS_NODE_APPLY)
                    voo.setContent("经办节点");
                else if (boo.getNodeType() == Constants.AS_NODE_APPROVE)
                    voo.setContent("审批节点");
                else if (boo.getNodeType() == Constants.AS_NODE_CC)
                    voo.setContent("抄送节点");
                else
                    voo.setContent("其他节点");
            }

//            FormInstVO voo = filtrate3.get(i).transToVO(procInstService.getCommitter(formInstDOs.get(i).getTaskId()));
            formInstVOs.add(voo);
        }

        return formInstVOs;
    }

    /**
     * 获取所有表单实例，不作筛选
     *
     * @return
     */
    public List<FormInstDO> listFormInst() {
        ArrayList<FormInstDO> formInstDOs = (ArrayList<FormInstDO>) formInstMapper.listFormInsts();
        return formInstDOs;
    }

    /**
     * 对当前用户以及当前拿到的任务节点进行匹配，看是不是当前用户可以执行的任务
     *
     * @param inst
     * @param userId
     * @return
     */
    private Boolean userMatch(FormInstDO inst, String userId) {
        String curNodeId = flowableService.getNodeId(inst.getTaskId());
        ProcNodeDO nodeDO = procNodeService.getNodeDO(procInstService.getProcModelId(inst.getProcInstId()),
                curNodeId);
        nodeDO.getCandidateUser();

        //默认匹配
        return true;
    }

    /**
     * 一个是节点类型，一个是页面显示的任务类型，看两者是不是匹配
     *
     * @param curActType 节点类型：经办节点、审批节点、抄送节点
     * @param taskType   任务类型：待办、待阅
     * @return
     */
    public boolean nodeTypeMatch(int curActType, Integer taskType) {
        if (taskType == Constants.TASK_TO_DO) {
            if (curActType == Constants.AS_NODE_APPLY
                    || curActType == Constants.AS_NODE_APPROVE)
                return true;
            else
                return false;
        }
        if (taskType == Constants.TASK_TOBE_READ && curActType == Constants.AS_NODE_CC)
            return true;

        return false;
    }


    private ArrayList<FormInstVO> transToVO(ArrayList<FormInstDO> formInstDOs) {
        ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
        for (int i = 0; i < formInstDOs.size(); i++) {
            formInstVOs.add(new FormInstVO(formInstDOs.get(i),
                    procInstService.getCommitter(formInstDOs.get(i).getTaskId())));
        }
        return formInstVOs;
    }

    /**
     * 业务入口
     * 对审批节点进行审批
     *
     * @param dto
     */
    public String[] approveNode(FormInstRecApprove dto) throws Exception {
        //找到当前传入的表单实例对应的流程实例的ID，注意和TaskID进行区分！！一次执行中，TaskId会一直变化，但是流程实例ID是不会变的
//        String procInstID = formInstMapper.getProcInstID(dto.getForm_inst_id());
        String procInstID = dto.getProc_inst_id();
        String procModelId = formModelService.getProcModelID(dto.getForm_model_id());

        //审批节点还可以对表单内容进行修改,这里的代码在updateFormInst方法里面写了
        //当前填写表单数据 对数据库进行更新
        updateFormInst(dto);

        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(dto.getTask_id());
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(dto.getProc_inst_id())
                .form_inst_value(dto.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);


        //当前审批节点同意申请，先把当前新加了 同意 这个信息的 新表单 放入数据库，并完成当前任务节点
        if (dto.getApprove_result() == Constants.APPROVE_AGREE) {
            //同意审批，as_form_inst表中输入approve_result,sheet和value、executor、execute_time值
            updateFormInst(dto);
            //这里审批节点之后就算存在分支结构，也不会是对这个审批意见进行处理，因为如果是对在之前任务节点上的表单项填写内容进行分支，那个时候就已经写入了runVariable数据，这里不需要再写
            //但是这里也可以写，如果以后需要对整个流程进行监控，就是比如在哪个节点干了什么事情这种
//            //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
//            String executionId = ProcUtils.getExecutionId(dto.getTask_id());
//            ActRuVariableBO boo = new ActRuVariableBO.Builder()
//                    .executionId(executionId)
//                    .procInstId(procInstID)
//                    .form_inst_value("approve").build();
//            actRuVariableService.saveRunVariable(boo);
            ProcUtils.completeTask(dto.getTask_id());
            procInstService.saveUnCompleteTask(
                    procInstID,
                    dto.getForm_model_id());
        } else if (dto.getApprove_result() == Constants.APPROVE_DISAGREE) {
            String[] strings = null;

            //注意，每个审批节点在流程模型设计阶段都会设置一个驳回属性：approveType，即当点击“不同意”按钮之后实例发生的变化
            //但是，现在流程模型设计阶段并没有对approve_type这个属性进行操作（还没做，后续需要你做）
            ProcNodeDO nodeDO = procNodeService.getNodeDO(formModelService.getProcModelID(dto.getForm_model_id()), flowableService.getNodeId(dto.getTask_id()));
            Integer approveType = nodeDO.getApprove_type();
            //一个审批节点默认的驳回属性是：结束整个流程，即CANCEL
            if (approveType == null)
                approveType = Constants.NODE_APPROVE_CANCEL;

            switch (approveType) {
                //当前审批节点的驳回属性设置为：直接结束整个流程
                case Constants.NODE_APPROVE_CANCEL:
                    ProcUtils.completeProcInstForRejected(dto.getTask_id());
                    break;
                //当前审批节点的驳回属性设置为：回滚到上一个经办节点处
                case Constants.NODE_APPROVE_ROLLBACK:
                    String lastApplyNode = "";

                    //先判断当前实例中是否包含并行分支，如果是包含并行分支的，需要根据当前任务节点的位置，选择正确的回滚点位置
                    if (dto.containParallel()) {
                        //完成所有的回滚操作
                        lastApplyNode = getRollbackPosInParallelProc(procInstID, procModelId);
                    }
                    //不包含并行分支，直接进行回滚
                    else {
                        //获取上一个经办节点
                        lastApplyNode = getLastApplyNodeActId(getProcInstId(dto.getTask_id()));
                        if (lastApplyNode.equals(""))
                            throw new ProcException("无法找到上一个经办节点，无法完成回滚，当前审批意见无法执行！");
                        //回滚
                        procInstService.rollback(procInstID, lastApplyNode, executionId);
                    }

                    //回滚之后，需获取当前任务到上一个经办节点之间的那些任务实例formInst，将其状态值修改为“已回滚”
                    List<HistoricActivityInstance> historicActs = ProcUtils.getHistoricActsDesc(procInstID);
                    for (int i = 0; i < historicActs.size(); i++) {
                        if (historicActs.get(i).getActivityId().equals(lastApplyNode))
                            break;

                        AsFormInstDO updateDO = new AsFormInstDO.Builder()
                                .status(Constants.FORM_INST_ROLLED)
                                .build();

                        UpdateWrapper<AsFormInstDO> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.lambda()
                                .eq(AsFormInstDO::getTaskId, historicActs.get(i).getTaskId());

                        int update = asFormInstMapper.update(updateDO, updateWrapper);
                        if (update == Constants.DATABASE_FAILED)
                            throw new DatabaseException("更新任务状态值失败！");
                    }


                    // 回滚后，该实例下会生成新的任务节点信息（在act_hi_actinst表中可以找到，相同inst_id且END_time为空的条目）
                    // 新的任务节点信息将其封装在as_form_inst表中，这里先不用saveRollbackTask()，saveUnCompleteTask()应该就可以满足要求了
//                        saveRollbackTask(procInstID, dto.getForm_model_id());
                    procInstService.saveUnCompleteTask(
                            procInstID,
                            dto.getForm_model_id());
                    String[] taskIDs = ProcUtils.getTaskIDs(procInstID);
                    strings = taskIDs;

                    break;

                //当前审批节点的驳回属性设置为：高级回滚，可以指定回滚到任意经办节点，这个功能暂时不做
                case Constants.NODE_APPROVE_ROLLBACK_PLUS:
                    throw new ProcException("暂时不支持该回滚类型！请检查node：" + nodeDO.getNodeId() + " 的属性");
            }
            return strings;
        }
        return generateUrls(procInstID);
    }


    /*
    在包含并行分支的流程执行序列中找到正确的回滚点位置，方案设计如下：
    从头开始遍历执行序列，遇到并行网关，如果出度有多个分支，那么代表是开始 发散了，需要构建一条新的executionId;
            如果出度只有一个出口，那么代表的是结束；
         遇到普通userTask，出度是一个的，加入之前创建的多条executionId表
         */
    public String getRollbackPosInParallelProc(String procInstID, String procModelId) throws Exception {
        //获取model
        ArrayList<FlowElement> flowElements = (ArrayList<FlowElement>) ProcUtils.getFlowElements(procModelId);
        //遍历model,标记并行网关
        HashMap<String, AsParallelNode> parallelNodes = signParallel(flowElements);

        //获取从头开始的执行序列
        List<HistoricActivityInstance> historicActsAsc = ProcUtils.getHistoricActsAsc(procInstID);
        AsExecution firstExecution = new AsExecution("first");
        int historicIndex = 0;
        HashMap<String, AsExecution> allExes = new HashMap<>();
        allExes.put(firstExecution.getExeId(), firstExecution);
        String curTaskId = ProcUtils.getTaskIDs(procInstID)[0];
        newConstructExecutions(firstExecution, historicActsAsc, historicIndex, parallelNodes, allExes, curTaskId);

        //接着遍历得到的allExes，注意这里先遍历包含curTaskId的exe(containExes变量),找到离当前审批节点最近的统一的经办节点，然后回滚到这个经办节点，
        // 接着看剩余的exe（不包含curTaskId的exe，noneExes变量）,是否包含这个回滚点，如果包含，那么也回滚
        ArrayList<AsExecution> containExes = new ArrayList<>();
        ArrayList<AsExecution> noneExes = new ArrayList<>();

        for(String key:allExes.keySet())
        {
            AsExecution curExe = allExes.get(key);

            //原来是从startevent开始排序的，需要逆序排列
            Collections.reverse(curExe.getExecutions());

            if(curExe.containTask(curTaskId))
            {
                containExes.add(curExe);
            }
            else {
                noneExes.add(curExe);
            }
        }

        AsExecution curExecution = containExes.get(0);
        AsTask curApproveTask = null;
        //这里的i表示现在找第几个i
        loop1:
        for(int i=1;i<curExecution.getExecutions().size()+1;i++)
        {
            curApproveTask = getApproveTask(procModelId, i, curExecution.getExecutions());
            //在剩余的执行序列中找当前找到的经办节点
            for(int f=1;f<containExes.size();f++)
            {
                //如果有执行流不包含，那么说明当前找到的经办节点不会，i值++，找下一个经办节点
                if(!containExes.get(f).containTask(curApproveTask.getTaskId()))
                    continue loop1;
            }
            //如果能到这一步，说明这个当前找到的经办节点在当前执行流中全都包含，所以
            break loop1;
        }

        //接着，要回滚了，containExes全部回滚到这个回滚点，noneExes看有没有这个回滚点，如果包含的话，也要回滚
        for(int i = 0;i<containExes.size();i++)
        {
            ProcUtils.rollback(ProcUtils.getExecutionId(curApproveTask.getTaskId()),curApproveTask.getActId(),procInstID);
        }

        for (int i = 0;i<noneExes.size();i++)
        {
            if(noneExes.get(i).containTask(curApproveTask.getTaskId()))
                ProcUtils.rollback(noneExes.get(i).getExecutions().get(0).getExecutionId(),curApproveTask.getActId(),procInstID);
        }

        return curApproveTask.getActId();




//        //获取当前执行序列，从后往前
//        List<HistoricActivityInstance> historicActsDesc = ProcUtils.getHistoricActsDesc(procInstID);
//        int i, count = 0;
//        HashMap<String, ProcExecution> runningExecutions = new HashMap<>();
//        HashMap<String, Boolean> isVisited = new HashMap<>();
//        HashMap<String, String[]> nodeContainsIn = new HashMap<>();
//
//
//        ProcExecution mainExecution = new ProcExecution("main");
//        String startEventExecutionId = historicActsAsc.get(0).getExecutionId();
//        mainExecution.setExecutionId(startEventExecutionId);
//        runningExecutions.put(startEventExecutionId, mainExecution);
//
//
//        //构建执行序列
//        constructExecutions(historicActsAsc, flowElements, runningExecutions, mainExecution, 0, procModelId, isVisited, nodeContainsIn);
//
//        //##对执行序列进行遍历，找到两个序列经过的相同的距离审批节点最近的经办节点，就是我们要找的回滚点
//        //获取当前节点执行流的ExecutionID
//        String curExecutionID = historicActsDesc.get(0).getExecutionId();
//        //获取当前执行流
//        ProcExecution curExecution = runningExecutions.get(curExecutionID);
//        //获取当中节点
//        ArrayList<ProcNode> curProcnodes = curExecution.getProcNodes();
        //从后往前遍历寻找回滚节点
//        for (i = curProcnodes.size() - 2; i >= 0; i--) {
//            //检查节点的类型
//            ProcNode procNode = curProcnodes.get(i);
//            if (procNode.getType() == Constants.AS_NODE_APPLY && count <= 0) {
//                //count用来记录经过的并行网关出口，来辅助寻找回滚节点，若找到配对的并行网关入口则减1，count<=0时代表是合适的回滚位置
//                return procNode.getId();
//
//            } else if (procNode.getType() == Constants.AS_NODE_PARALLEL_end) {
//                count++;
//            } else if (procNode.getType() == Constants.AS_NODE_PARALLEL_start) {
//                count--;
//            }
//        }

    }

    /**
     * 获取第index个经办节点
     * @param index
     * @return
     */
    public AsTask getApproveTask(String procModelId, int index,ArrayList<AsTask> executions){
        int i = 1;

        //遍历executions，找到第index个经办任务，i用来计数
        for(int f= 0;f<executions.size();f++)
        {
            if(procNodeService.getNodeType(procModelId,executions.get(f).getActId())==Constants.AS_NODE_APPLY)
            {
                if(i==index)
                    return executions.get(f);
                else
                    i++;
            }
        }

        return null;
    }

    /**
     * @param publicExecution  这个是当前方法的一个公共执行链，如果需要创建新的分支，需要从这个公共执行链复制内容,注意这里的String内容是taskId
     * @param historicActsAsc  一个不变的执行序列
     * @param curHistoricIndex 当前递归层次遍历到执行序列的哪一个元素了
     * @param parallelNodes    之前遍历model时得到的关于并行网关的信息
     * @param allExes          构造出的所有执行流列表
     */
    private void newConstructExecutions(AsExecution publicExecution,
                                        List<HistoricActivityInstance> historicActsAsc,
                                        int curHistoricIndex,
                                        HashMap<String, AsParallelNode> parallelNodes,
                                        HashMap<String, AsExecution> allExes,
                                        String curRunningTaskId) {
        for (; curHistoricIndex < historicActsAsc.size(); curHistoricIndex++) {
            HistoricActivityInstance curNode = historicActsAsc.get(curHistoricIndex);
            AsTask curTask = new AsTask(curNode.getTaskId(), curNode.getExecutionId(), curNode.getActivityId());
            if (curNode.getActivityType().equals("parallelGateway")) {
                AsParallelNode asParallelNode = parallelNodes.get(curNode.getActivityId());
                //判断是不是开始节点
                if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_start) {
                    //有几个出度，就要新创建几个exe，注意第一个创建的exe会把原来的publicExe在allExes中的位置顶替掉
                    int outNums = asParallelNode.getOutNums();

                    for (int i = 0; i < outNums; i++) {
                        AsExecution newExe = new AsExecution(publicExecution);
                        newExe.add(curTask);
                        //第一个创建的exe会把原来的publicExe在allExes中的位置顶替掉
                        if (i == 0) {
                            allExes.put(newExe.getExeId(), newExe);
                        }
                        //curHistoricIndex是基本类型int，所以是值传递，不用担心在下一层递归函数中被修改
                        newConstructExecutions(newExe, historicActsAsc, curHistoricIndex, parallelNodes, allExes, curRunningTaskId);
                    }
                } else if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_end) {
                    publicExecution.add(curTask);
                }
            }
            //遇到当前节点，return
            else if (curNode.getTaskId().equals(curRunningTaskId)) {
                publicExecution.add(curTask);
                return;
            }
            //遇到非并行网关节点，直接添加
            else {
                publicExecution.add(curTask);
            }
        }
    }

    /**
     * 标记并行网关
     *
     * @param flowElements
     */
    private HashMap<String, AsParallelNode> signParallel(ArrayList<FlowElement> flowElements) {
        //记录当前模型中出现的并行网关，然后对其中的并行网关进行标记
        HashMap<String, AsParallelNode> parallelNodes = new HashMap<>();
        Stack<String> stack = new Stack<>();

        int i = 0;
        //先从头遍历，构建多条执行序列
        for (; i < flowElements.size(); i++) {
            FlowElement flowElement = flowElements.get(i);
            if (flowElement instanceof ParallelGateway) {
                ParallelGateway gateway = (ParallelGateway) flowElement;
                List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
                List<SequenceFlow> incomingFlows = gateway.getIncomingFlows();

                //出度为1，那么是end
                if (outgoingFlows.size() == 1) {
                    String curPeerStartId = stack.pop();
                    parallelNodes.put(gateway.getId(), new AsParallelNode.Builder()
                            .id(gateway.getId())
                            .type(Constants.AS_NODE_PARALLEL_end)
                            .peerNodeId(curPeerStartId)
                            .outNums(1)
                            .build());
                    AsParallelNode curPeerStartNode = parallelNodes.get(curPeerStartId);
                    curPeerStartNode.setPeerNodeId(gateway.getId());
                } else {
                    stack.push(gateway.getId());
                    parallelNodes.put(gateway.getId(), new AsParallelNode(gateway.getId(), Constants.AS_NODE_PARALLEL_start, outgoingFlows.size()));
                }
            }
        }

        return parallelNodes;
    }

    /**
     * @param historicActsAsc
     * @param modelFlowMents
     * @param runnningExecutions
     * @param curExecution
     * @param i
     * @param procModelId
     * @param isVisited
     * @param nodeContainsIn
     * @throws Exception
     */
    public void constructExecutions(List<HistoricActivityInstance> historicActsAsc,
                                    ArrayList<FlowElement> modelFlowMents,
                                    HashMap<String, ProcExecution> runnningExecutions,
                                    ProcExecution curExecution,
                                    int i,
                                    String procModelId,
                                    HashMap<String, Boolean> isVisited,
                                    HashMap<String, String[]> nodeContainsIn) throws Exception {
        //先从头遍历，构建多条执行序列
        for (; i < modelFlowMents.size(); i++) {
            FlowElement flowElement = modelFlowMents.get(i);
            String curNodeExecutionId = getExecutionId(flowElement.getId(), historicActsAsc);
            //这边认为模型与实例是一一对应好的，不存在模型与实例元素不匹配的情况
            //当前模型中遍历的元素在执行序列中没有记录，可以认为当前元素没有被执行过，所以跳过
            //如果这个元素已经被访问过了，也不能够继续被访问
            if (curNodeExecutionId == null || isVisited.get(flowElement.getId())) {
                continue;
//                throw new ProcException("流程执行序列出错，实例:"+historicActsAsc.get(0).getProcessInstanceId()+"与对应模型中元素："+flowElement.getId()+" 无法匹配，当前审批任务无法完成回滚！");
            }


            //接着判断当前元素是不是属于当前的执行序列，如果不是，需要找到对应的执行序列，添加进去
            boolean isMatch = curExecution.match(curNodeExecutionId);
            //当前遍历的元素不在当前执行序列上，先去执行序列表中找是否有这么一条序列，如果有的话，切换到这条序列上；如果没有，创建新的序列，增加该元素
            if (!isMatch) {
                //获取正确的执行序列
                ProcExecution execution = runnningExecutions.get(curNodeExecutionId);
//                ProcExecution execution = getExecution(curNodeExecutionId, runnningExecutions);
                //找不到，说明该执行序列没有添加到执行序列表中，需要新建，元素不是在这个时候加，需要先判断类型
                if (execution == null) {
                    ProcExecution newExecution = new ProcExecution(curNodeExecutionId);
                    newExecution.setExecutionId(curNodeExecutionId);
                    runnningExecutions.put(curNodeExecutionId, newExecution);
                    curExecution = newExecution;
                } else {
                    curExecution = execution;
                }
            }

            //看是否需要新增一条executions
            if (flowElement instanceof StartEvent) {
                curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_START, curNodeExecutionId));
            } else if (flowElement instanceof UserTask) {
                Integer nodeTyep = procNodeService.getNodeType(procModelId, flowElement.getId());
                curExecution.add(new ProcNode(flowElement.getId(), nodeTyep, curNodeExecutionId));
            } else if (flowElement instanceof ParallelGateway) {
                ParallelGateway gateway = (ParallelGateway) flowElement;
                List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
                List<SequenceFlow> incomingFlows = gateway.getIncomingFlows();
                //出度为1，那么就是join，即end
                if (outgoingFlows.size() == 1)
                    curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_PARALLEL_end, curNodeExecutionId));
                else {
                    curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_PARALLEL_start, curNodeExecutionId));

                    //所有的出度，每一个出度建立一个execution,然后把原有的代替掉
                    for (int n = 0; n < outgoingFlows.size(); n++) {
                        SequenceFlow curSequence = outgoingFlows.get(n);
                        UserTask userTask = (UserTask) curSequence.getTargetFlowElement();
                        String matchExecutionId = getExecutionId(userTask.getId(), historicActsAsc);

                        if (runnningExecutions.get(matchExecutionId) == null) {
                            ProcExecution newExecution = new ProcExecution();
                            BeanUtils.copyProperties(newExecution, curExecution);
                            newExecution.setExecutionId(matchExecutionId);
                            runnningExecutions.put(matchExecutionId, newExecution);
                        }
                    }
                }
            }
        }
    }

//    public ArrayList<String> getLastNode(FlowElement flowElement) {
//        ArrayList<String> lastNodes = new ArrayList<>();
//        if (flowElement instanceof UserTask) {
//            List<SequenceFlow> incomingFlows = ((UserTask) flowElement).getIncomingFlows();
//            for (SequenceFlow sequenceFlow : incomingFlows) {
//                lastNodes.add(sequenceFlow.getSourceRef());
//            }
//
//        } else if (flowElement instanceof ParallelGateway) {
//
//        }
//    }

    private ProcExecution getExecution(String executionId, ArrayList<ProcExecution> executions) {
        ProcExecution procExecution = null;

        for (ProcExecution execution : executions) {
            if (execution.getExecutionId().equals(executionId)) {

                if (procExecution == null) {
                    procExecution = execution;
                } else {
                    if (execution.getProcNodes().size() > procExecution.getProcNodes().size())
                        procExecution = execution;
                }
            }
        }
        return procExecution;
    }

    /**
     * 根据nodeId获取对应的执行序列中的executionId
     *
     * @param nodeId
     * @param historicActsAsc
     * @return
     */
    private String getExecutionId(String nodeId, List<HistoricActivityInstance> historicActsAsc) {
        for (HistoricActivityInstance hi : historicActsAsc) {
            if (hi.getActivityId().equals(nodeId))
                return hi.getExecutionId();
        }

        return null;
    }


//    public void saveRollbackTask(String procInstId, String formModelId) {
//        FormSheet originalFormSheet = formModelService.getModelSheet(formModelId);
//        //获取当前执行到的任务节点
//        String[] taskIDs = ProcUtils.getTaskIDs(procInstId);
//        //如果下面还有任务节点要处理，就在as_proc_inst中新建这个表单实例字段（每一个TaskId对应一个新的表单实例）
//        for (int i = 0; i < taskIDs.length; i++) {
//            boolean isNotContain = formInstMapper.getTaskId(taskIDs[i]) == null ? true : false;
//            //当前要存的taskID不能是已经有的的，否则重复保存了
//            if (isNotContain) {
//                String formInstAllValue = getFormInstAllValue(procInstId);
//                FormInstDO formInst = formInstService.createFormInst(originalFormSheet,
//                        procInstId,
//                        null,
//                        formModelId,
//                        formInstAllValue,
//                        taskIDs[i]);
//                String procModelId = formModelService.getProcModelID(formModelId);
//                String nodeId = flowableService.getNodeId(taskIDs[i]);
//                //在存进去之前，就必须要求这个节点的节点类型是确定的
//                formInst.setNodeType(procModelService.getNodeType(procModelId, nodeId));
//                formInstService.saveUnCompleteFormInst(formInst);
//            }
//        }
//    }

    public String getProcInstId(String taskId) {
        return formInstMapper.getProcInstId(taskId);
    }

    /**
     * 这块逻辑太复杂了，先暂时不搞了
     * 回滚结束后，在form_inst表中把晚于这个节点执行时间且相同procInstId的项目的状态置为 已被回滚
     * 当前的处理逻辑是：
     * 1、先判断要回滚到的上一个经办节点位置是不是有两个出度（即是否是分支节点）
     * 2、如果不是，那么就是从curTaskId开始在数据库中遍历相同procInstId的条目直到lastApplyNodeId，把这些条目都置为 已回滚
     * 如果是，先把as_form_inst表中的指定proc_inst_id的条目都取出来，构造<task_id,act_id>的哈希表（需两次读取数据库表，先拿task_id，再由task_id拿act_id），然后
     * 获取经办节点的所有出度，遍历所有act_id，直到endNode，出现了的就把这一项的task_id取出来去as_form_inst中找到这个条目置为 已回滚
     */
    private void updateFormInstAfterRollback(String curTaskId, String lastApplyNodeId) {
        String procInstId = getProcInstId(curTaskId);
        String defId = procInstService.getDefId(procInstId);
        if (ProcUtils.containParral(defId, lastApplyNodeId)) {

        }
        //不包含分支结构
        else {
//            Date lastlastApplyNodeExecuteTime = getExecuteTime(lastApplyNodeId);
        }

    }

    private void getExecuteTime(String lastApplyNodeId) {
//        return formInstMapper.getFormInst(lastApplyNodeId);
    }


    /**
     * 业务入口
     * 对经办节点进行处理
     *
     * @param rec
     */
    public String[] applyNode(FormInstRecHandle rec) throws FlowableException {
        //当前填写表单数据 对数据库进行更新
        updateFormInst(rec);
        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(rec.getTask_id());
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(rec.getProc_inst_id())
                .form_inst_value(rec.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);
        //完成当前经办节点
        ProcUtils.completeTask(rec.getTask_id());
        procInstService.saveUnCompleteTask(
                rec.getProc_inst_id(),
                rec.getForm_model_id());
        return generateUrls(rec.getProc_inst_id());
    }

    /**
     * 业务入口
     * 对抄送节点进行处理
     *
     * @param rec
     */
    public String[] pendingNode(FormInstRecReadle rec) throws FlowableException {
        //保存当前抄送节点的已阅信息
        updateFormInst(rec);
        //完成当前抄送任务,抄送任务之后也不需要考虑对当前结果的处理意见进行一个保存
        //抄送任务结束之后，默认后面是没有任务节点了！！所以这里不需要再保存未完成任务节点
        ProcUtils.completeTask(rec.getTask_id());
        return generateUrls(rec.getProc_inst_id());
    }

    /**
     * 入口方法
     * 统计各个类型的表单实例数目分别是多少
     *
     * @param userID
     * @return
     */
    public List<TaskCount> getFormInstsCounts(String userID, String sceneId, String sectionId) throws Exception {
//        TaskCount toDoCount = getCount(userID,
//                Constants.TASK_TO_DO,
//                sceneId,
//                sectionId);
//        TaskCount toReadCount = getCount(userID,
//                Constants.TASK_TOBE_READ,
//                sceneId,
//                sectionId);
        TaskCount toDoCount;    //nfq:这个是统计待办的
        TaskCount toReadCount;   //nfq:这个是统计待阅的
        try {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, listFormInst(userID, Constants.TASK_TO_DO, sceneId, sectionId).size());
        } catch (SizeNullException e) {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, 0);
        }

        try {
            toReadCount = new TaskCount(Constants.TASK_TOBE_READ, listFormInst(userID, Constants.TASK_TOBE_READ, sceneId, sectionId).size());
        } catch (SizeNullException e) {
            toReadCount = new TaskCount(Constants.TASK_TOBE_READ, 0);
        }

        List<TaskCount> taskCounts = new ArrayList<>();
        taskCounts.add(toDoCount);
        taskCounts.add(toReadCount);
        return taskCounts;
    }

    /*nfq:2019/10/11
     * */
    public List<TaskCount> getcommitFormCounts(String userID, String sceneId, String sectionId) throws Exception {
        TaskCount comformCount; // 统计提交表单数量
        try {
            comformCount = new TaskCount(Constants.COMMIT_TASK, listComFormInst(userID, Constants.COMMIT_TASK, sceneId, sectionId).size());
        } catch (SizeNullException e) {
            comformCount = new TaskCount(Constants.COMMIT_TASK, 0);
        }
        List<TaskCount> taskCounts1 = new ArrayList<>();
        taskCounts1.add(comformCount);
        return taskCounts1;
    }

    /**
     * 入口方法
     * 点击外链之后,显示当前待执行节点的表单sheet
     *
     * @param taskId
     */
    public FormInstVO getShareLinkTask(String taskId,
                                       String userId,
                                       String sectionId) throws ProcException, FormException {
        FormInstDO formInstDO = getFormInst(taskId);

//        这边就是，生成的这个外链复制给别人，然后别人点击，通过用户验证之后就可以去执行了
//        if(!userService.validateFormInst(formInstDO.getTaskId(),"?????"))
//            return RespBean.error("当前任务节点不属于该用户！");

        formInstDO = authorityService.handleFormSheetAuthority(formInstDO);

        String procModelId = formModelService.getProcModelID(formInstDO.getFormModelId());
        if (procModelId.equals(Constants.REGISTER_PROC_ID) ||
                procModelId.equals(Constants.SCENE_SELECT_PROC_ID)) {
            formInstDO.setNodeType(Constants.AS_NODE_APPROVE);
        } else {
            Integer nodeType = formInstDO.getNodeType();
            if (nodeType == null) {
                logger.error("流程中间层生成的流程模型出错！procModelId为:{}", procModelId);
                throw new ProcException("流程中间层生成的流程模型出错！");
            }
        }
        FormInstBO boo = new FormInstBO();
        BeanUtils.copyProperties(formInstDO, boo);

        boo.setCurUserId(userId);
        boo.setCurTaskType(Constants.TASK_ALL);
        boo.setProcModelId(procInstService.getProcModelId(formInstDO.getProcInstId()));
        boo.setNodeId(flowableService.getNodeId(taskId));

        ProcNodeDO nodeDO = procNodeService.getNodeDO(procModelId, boo.getNodeId());
        if (!StringUtils.isEmpty(nodeDO.getCandidateUser()))
            boo.setCandidateUser(nodeDO.getCandidateUser().split("\\|"));
        if (!StringUtils.isEmpty(nodeDO.getCandidateGroup()))
            boo.setCandidateGroup(nodeDO.getCandidateGroup().split("\\|"));

        boo.setIfJointSign(nodeDO.getIfJointSign());

        boo.setSceneId(formModelService.getSceneId(formInstDO.getFormModelId()));
//        FormInstBO formInstBO = new FormInstBO(formInstDO,userId,Constants.TASK_ALL);

        UserIdFilter userFilter = new UserIdFilter();
        DuplicateFilter duplicateFilter = new DuplicateFilter();

        //对不属于当前用户的表单任务进行筛选
        FormInstBO formInstBO1 = userFilter.shareLinkFiltrate(boo, sectionId);
        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
        FormInstBO formInstBO2 = duplicateFilter.shareLinkFiltrate(formInstBO1);

        FormInstVO voo = formInstBO2.transToVO(procInstService.getCommitter(formInstBO2.getTaskId()));

        return voo;
    }

    /**
     * 这里的获取任务节点信息都没有经过筛选，不完整
     *
     * @param userID
     * @param taskType
     * @param sceneId
     * @param sectionId
     * @return
     * @throws InfoException
     * @throws ProcException
     */
    public TaskCount getCount(String userID,
                              int taskType,
                              String sceneId,
                              String sectionId) throws InfoException, ProcException {
        //1、先获取流转到该用户对应的FlowableTaskDO
        List<FlowableTaskDO> tasks = flowableService.listCurTasks(userID);
        if (tasks.size() == 0)
            return new TaskCount(taskType, 0);

        List<TaskBO> taskBOs = constructTaskBO(tasks);

        //2、对上面集合进行遍历，从as_proc_node中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        taskBOs = dressTaskByType(taskBOs, taskType);

//        //对用户、组织筛选
//        UserIdFilter userIdFilter = new UserIdFilter();
//        DuplicateFilter duplicateFilter = new DuplicateFilter();
//        SceneFilter sceneFilter = new SceneFilter();
//        //对不属于当前用户的表单任务进行筛选
//        ArrayList<FormInstBO> filtrate1 = userIdFilter.filtrate(formInstBOs,sectionId);
//        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
//        ArrayList<FormInstBO> filtrate2 = duplicateFilter.filtrate(filtrate1);
//        //过滤工作场景
//        ArrayList<FormInstBO> filtrate3 = sceneFilter.filtrate(filtrate2,sceneId);

        return new TaskCount(taskType, taskBOs.size());
    }


    /**
     * 需要根据修改的节点类型的不同更新as_form_inst表中的不同字段
     */
    public FormInstDO updateFormInst(FormInstRecBase recBase) {
        FormInstDO inst = null;
        //审批表单实例,更新approve_result、executor、execute_time值
        //sheet、value不用更新
        if (recBase instanceof FormInstRecApprove) {
            String formInstSheet = JSONObject.toJSONString(((FormInstRecApprove) recBase).getForm_inst_sheet());
            inst = new FormInstDO.Builder()
                    .id(((FormInstRecApprove) recBase).getForm_inst_id())
                    .approveResult(((FormInstRecApprove) recBase).getApprove_result())
                    .executor(recBase.getEditor())
                    .procInstId(((FormInstRecApprove) recBase).getProc_inst_id())
                    .formInstValue(((FormInstRecApprove) recBase).getForm_inst_value())
                    .formInstSheetStr(formInstSheet)
                    .build();
            formInstMapper.approveFormInst(inst);
            procInstService.updateFormValueForAll(((FormInstRecApprove) recBase).getForm_inst_value(), inst.getProcInstId());
        }
        // 经办节点,更新executor、execute_time、sheet、value值
        // 注意这里还需要对as_proc_inst表的eform_inst_all_value字段进行更新
        else if (recBase instanceof FormInstRecHandle) {
            String formInstSheet = JSONObject.toJSONString(((FormInstRecHandle) recBase).getForm_inst_sheet());
            inst = new FormInstDO(
                    ((FormInstRecHandle) recBase).getProc_inst_id(),
                    ((FormInstRecHandle) recBase).getForm_inst_id(),
                    formInstSheet,
                    ((FormInstRecHandle) recBase).getForm_inst_value(),
                    recBase.getEditor()
            );
            formInstMapper.handleFormInst(inst);
            procInstService.updateFormValueForAll(((FormInstRecHandle) recBase).getForm_inst_value(), inst.getProcInstId());
        }
        // 抄送节点，更新executor、execute_time值
        else if (recBase instanceof FormInstRecReadle) {
            inst = new FormInstDO(
                    ((FormInstRecReadle) recBase).getForm_inst_id(),
                    recBase.getEditor()
            );
            formInstMapper.readFormInst(inst);
        }
        return inst;
    }

    /**
     * 业务入口
     * 这里根据Task信息返回对应的表单实例信息，但是注意这里表单实例信息中的formValue值来自as_proc_inst表中
     * formSheet值来自as_form_model
     *
     * @param taskBOs
     * @return
     */
    private List<FormInstDO> getFormInsts(List<TaskBO> taskBOs) {
        List<FormInstDO> formInstsByTasks = formInstMapper.getFormInstsByTasks(taskBOs);
        for (int i = 0; i < formInstsByTasks.size(); i++) {
            FormInstDO formInstDO = formInstsByTasks.get(i);
            String originFormSheet = JSON.toJSONString(formModelService.getModelSheet(formInstDO.getFormModelId()));
            formInstDO.setFormInstSheetStr(originFormSheet);
        }
        return formInstsByTasks;
    }

    /**
     * 由flowable数据库取出来的TASK信息加上当前task的节点类型信息，然后返回
     *
     * @param tasks
     */
    private List<TaskBO> constructTaskBO(List<FlowableTaskDO> tasks) throws ProcException {
        List<TaskBO> taskBOs = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            FlowableTaskDO cur = tasks.get(i);
            String procModelId = procInstService.getProcModelId(cur.getProcInstId());
            //这里说明我们在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例，说明数据库中存在脏的流程实例数据
            if (StringUtils.isEmpty(procModelId)) {
                logger.error("在as_proc_inst表中找不到这个在act_hi_actinst表中存在的流程实例!没有如下ProcInstID:{}", cur.getProcInstId());
                throw new ProcException("有运行中流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            }

            Integer nodeType = null;

            if (procModelId.equals(Constants.REGISTER_PROC_ID) ||
                    procModelId.equals(Constants.SCENE_SELECT_PROC_ID)) {
                nodeType = Constants.AS_NODE_APPROVE;  // 节点设置为审批节点
            } else {
                nodeType = procNodeService.getNodeType(procModelId, cur.getActId());
                if (nodeType == null) {
                    logger.error("流程中间层生成的流程模型出错！procModelId为:{},没有如下NodeId:{}", procModelId, cur.getActId());
                    throw new ProcException("流程中间层生成的流程模型出错！");
                }
            }

            TaskBO taskBO = new TaskBO(cur, nodeType);
            taskBOs.add(taskBO);
        }
        return taskBOs;
    }

    /**
     * 对当前得到的Task进行筛选，看是否符合前台请求的任务类型（待办、待阅、全部）
     *
     * @param tasks
     * @param taskType
     * @return
     * @throws ProcException
     */
    private List<TaskBO> dressTaskByType(List<TaskBO> tasks, Integer taskType) throws ProcException {
        for (int i = 0; i < tasks.size(); i++) {
            TaskBO curTaskBO = tasks.get(i);
            //判断是不是符合当前页面要找的节点类型
            if (!nodeTypeMatch(curTaskBO.getNodeType(), taskType)) {
                tasks.remove(i);
                i--;
            }
        }
        return tasks;
    }

    /**
     * 独立功能模块
     * 创建表单实例
     *
     * @param formSheet
     * @param procInstId
     * @param editor
     * @param formModelId
     * @param formInstValue
     * @param taskId
     * @return 返回创建好的表单实例
     */
    public FormInstDO createFormInst(FormSheet formSheet,
                                     String procInstId,
                                     String editor,
                                     String formModelId,
                                     String formInstValue,
                                     String taskId) {
        String executionID = procInstService.getExecutionId(procInstId);
        String formInstJson = JSONObject.toJSONString(formSheet);
        FormInstDO inst = new FormInstDO.Builder()
                .formModelId(formModelId)
                .procInstId(procInstId)
                .executionId(executionID)
                .taskId(taskId)
                .executor(editor)
                .formInstValue(formInstValue)
                .formInstSheetStr(formInstJson).build();

        String procModelID = formModelService.getProcModelID(formModelId);

        if (procModelID.equals(Constants.REGISTER_PROC_ID) ||
                procModelID.equals(Constants.SCENE_SELECT_PROC_ID))
            inst.setNodeType(Constants.AS_NODE_APPLY);
        else {
            String firstNodeId = procNodeService.getFirstNodeId(procModelID);
            inst.setNodeType(procNodeService.getNodeType(procModelID, firstNodeId));
        }

        return inst;
    }


    /**
     * 获取流程实例中的上一个经办节点,注意这里不能获取并行网关中的经办节点！因为流程回滚不能回滚到并行网关中的经办节点处
     * 1、用comingflow那个操作获取上一个节点
     * 2、查看它的node——type是不是经办节点类型
     * 3、如果不是，回到1；如果是，返回该节点
     *
     * @param procInstId 当前待执行的Task所属的流程实例Id
     * @return
     */
    public String getLastApplyNodeActId(String procInstId) {
        List<HistoricActivityInstance> historicActs = ProcUtils.getHistoricActsDesc(procInstId);
        String procModelId = procInstService.getProcModelId(procInstId);
        for (HistoricActivityInstance instance : historicActs) {
            String actId = instance.getActivityId();
            if (procNodeService.getNodeType(procModelId, actId) == Constants.AS_NODE_APPLY) ;
            return actId;
        }
        return "";
    }


    public String[] generateUrls(String procInstId) {
        String[] curTaskIds = ProcUtils.getTaskIDs(procInstId);
        String[] urls = new String[curTaskIds.length];

        for (int i = 0; i < curTaskIds.length; i++) {
            urls[i] = Constants.REQUEST_URL_PREFIX + "/form_inst/share_link?task_id=" + curTaskIds[i];
        }
        return urls;
    }

    /**
     * 用户点击这个外链，自动跳转到相应的待办 或者 待阅 页面对任务节点进行处理
     *
     * @param taskId
     */
    public FormInstDO getFormInst(String taskId) {
        return formInstMapper.getFormInst(taskId);
    }

    public String getFormModelId(String taskId) {
        return formInstMapper.getFormModelIdByTaskId(taskId);
    }

    public void saveUnCompleteFormInst(FormInstDO formInst) {
        formInstMapper.saveUnCompleteFormInst(formInst);
    }


    public List<String> getAlreadyCompleteTask(String curUserId, String procInstId) throws FlowableException {
        return formInstMapper.getAlreadyCompleteTask(curUserId, procInstId);
    }

    /**
     * 获取指定实例Id的所有任务节点信息
     *
     * @param procInstId
     * @return
     */
    public List<AsFormInstDO> listFormInst(String procInstId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getProcInstId, procInstId);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs;
    }

    /**
     * 判断某一个实例所属的taskId是不是被正常执行了，还是这个实例被终止了，导致这个task_id在as_form_inst表中没有得到记录
     *
     * @return
     */
    public boolean isFormInstExecuted(String taskId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getTaskId, taskId);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 这里传入的task如果是被拒绝的，返回true；其他情况都是false，
     *
     * @param taskId
     * @return
     */
    public boolean isRejected(String taskId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getTaskId, taskId)
                .eq(AsFormInstDO::getApproveResult, Constants.APPROVE_DISAGREE);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 如果是一个会签任务的话，可能会有多个记录
     *
     * @param procInstId
     * @return
     */
    public List<AsFormInstDO> getExecutorId(String procInstId, String executor) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getProcInstId, procInstId)
                .eq(AsFormInstDO::getExecutor, executor);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs;
    }
}
