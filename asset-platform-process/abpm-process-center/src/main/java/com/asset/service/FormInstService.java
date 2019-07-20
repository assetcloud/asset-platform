package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.form.FormItem;
import com.asset.form.ItemRuleRequired;
import com.asset.form.FormJson;
import com.asset.dto.*;
import com.asset.utils.Constants;
import org.flowable.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 以下对流程实例的执行均只考虑非并行分支的情况
 */
@Service
public class FormInstService {
    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    ActTypeMapper actTypeMapper;
    @Autowired
    AsProcInstMapper procInstMapper;
    @Autowired
    FormAuthorityMapper formAuthorityMapper;
    @Autowired
    ProcInstService procInstService;


    Logger logger = LoggerFactory.getLogger(FormInstService.class);





    /**
     * 一个是节点类型，一个是页面显示的任务类型，看两者是不是匹配
     * @param curActType 节点类型：经办节点、审批节点、抄送节点
     * @param taskType 任务类型：待办、待阅
     * @return
     */
    public boolean match(int curActType, Integer taskType) {
        if(taskType== Constants.TASK_TO_DO)
        {
            if(curActType == Constants.AS_NODE_APPLY
                    || curActType== Constants.AS_NODE_APPROVE)
                return true;
            else
                return false;
        }
        if(taskType== Constants.TASK_TOBE_READ && curActType== Constants.AS_NODE_CC)
            return true;

        return false;
    }


    public List<FormInst> getTobeReadInsts(List<FormInst> formInsts) {
        List<AsTask> asTasks = flowableMapper.getActIDs(formInsts);
        return null;
    }



    /**
     * 获取流程实例中的上一个申请节点
     * 1、先获取当前流程实例执行的ACT_ID
     * 2、找到当初存在proc_model表中的功能性节点信息
     * @param procInstID
     * @return
     */
    public String getLastApplyNode(String procInstID) {
        String curActId = flowableMapper.getCurActId(procInstID);

        return "1";

    }

    /**
     * 从当前审批节点回滚到上一个申请节点
     * @param procInstID
     * @param rollbackActID 上一个申请节点的ActivityID
     */
    public void rollback(String procInstID,String rollbackActID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();

        runtimeService.createChangeActivityStateBuilder().processInstanceId(procInstID)
                .moveExecutionToActivityId(procInstService.getExecutionId(procInstID), rollbackActID).changeState();
    }

    //创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
    public FormInst createFormInst(FormJson formJson,
                                   String procInstId,
                                   String editor,
                                   String formModelId,
                                   String formInstValue,
                                   String taskId){
        String executionID = procInstService.getExecutionId(procInstId);
        String formInstJson = JSONObject.toJSONString(formJson);

        FormInst inst = new FormInst(
                formModelId,
                procInstId,
                executionID,
                taskId,
                editor,
                formInstValue,
                formInstJson
        );
        formInstMapper.insertSelective(inst);
        return inst;
    }

    //as_form_inst表中中包含这条表单实例信息，但是是属于还没完成的那种类型，需要填写表单信息后对这项进行更新
    public FormInst updateFormInst(FormInstRecBase recBase){
        FormInst inst = null;
        // 审批表单实例
        if (recBase instanceof FormInstRecApprove)
        {
            inst = new FormInst(
                    ((FormInstRecApprove) recBase).getForm_inst_id(),
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            formInstMapper.approveFormInst(inst);
        }
        // 经办节点
        if (recBase instanceof FormInstRecHandle)
        {
            String formInstJson = JSONObject.toJSONString(((FormInstRecHandle) recBase).getForm_inst_json());

            inst = new FormInst(
                    ((FormInstRecHandle) recBase).getForm_inst_id(),
                    formInstJson,
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            formInstMapper.handleFormInst(inst);
        }
        // 待阅
        if(recBase instanceof FormInstRecReadle)
        {
            inst = new FormInst(
                    ((FormInstRecReadle) recBase).getForm_inst_id(),
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            formInstMapper.readFormInst(inst);
        }
        return inst;
    }



    public TaskCount getCount(String userID,
                              int taskType) throws Exception {
        //map是TaskId和ActType的Map
        HashMap<String,Integer> map =new HashMap<String, Integer>();

        //1、先获取流转到该用户对应的procInstId\taskID\actId的集合
        List<AsTask> tasks = procInstService.getCurTasks(userID);
        if (tasks.size()==0)
            return new TaskCount(taskType,0);

        //2、对上面集合进行遍历，从数据库中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        for(int i =0;i<tasks.size();i++)
        {
            AsTask curAsTask = tasks.get(i);
            //2.1 由流程实例ID获取流程模型ID
            String procModelId = procInstService.getProcModelIdByProc(curAsTask.getProcInstId());
            //这里说明我们在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例，说明数据库中存在脏的流程实例数据
            if (procModelId.isEmpty()){
                logger.error("在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例!没有如下ProcInstID:{}", curAsTask.getProcInstId());
                throw new Exception("有运行中流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            }

            //2.2 根据流程模型ID、节点ID 获取对应的ActType，然后决定要不要把该taskInfos中的这一项给去掉
            Integer actType = procInstService.getActType(procModelId, curAsTask.getActId());
            if (actType == null)
            {
                logger.error("流程中间层生成的流程模型出错！procModelId为:{},没有如下ActID:{}",procModelId, curAsTask.getActId());
               throw new Exception("流程中间层生成的流程模型出错！");
            }
            //判断是不是符合当前页面要找的节点类型
            if(!match(actType,taskType))
            {
                tasks.remove(i);
                i--;
            }
            else {
                map.put(curAsTask.getTaskId(),actType);
            }
        }

        return new TaskCount(taskType,
                tasks.size());
    }



    public Integer getCurAuthority(String procModelId, String actId, String itemKey) {
        return formAuthorityMapper.getAuthority(procModelId,actId,itemKey);
    }

    /**
     *
     * @param items 一个表单模型中的表单项列表
     * @param j 表单项列表要修改第几个表单项
     * @param curAuthority 第j个表单项当前的权限应该是什么（在调用该方法前已经指定好了）
     */
    public void handleAuthority(List<FormItem> items, int j, Integer curAuthority) {
        switch (curAuthority){
            //必填，获取list中json对象的rules属性，进行修改,
            //diable:false;required:true+rule
            case Constants.AUTHORITY_REQUIRED:
                //对Options属性进行修改
                OptionsBase optionsBase = items.get(j).getOptions();
                optionsBase.setRequired(true);
                optionsBase.setDisabled(false);
                items.get(j).setOptions(optionsBase);

                //添加Rules
                List<JSONObject> rules = items.get(j).getRules();
                if(rules==null)
                {
                    //添加新的 必填 rule
                    addRequiredRule(items.get(j));
                }
                //还需要对rules里面的内容进行进一步的判断
                else{
                    for (int i = 0;i<rules.size();i++)
                    {
                        boolean isContain = rules.get(i).containsKey("required");
                        //表示当前包含"required"这个字段了,没必要添加新的 必填 rule
                        if(isContain)
                            return;
                    }
                    //上面都遍历了还没有找到"required"这个字段，说明的确是没有这个字段，现在
                    //要添加新的 必填 rule
                    addRequiredRule(items.get(j));
                }
                break;
            //不可见，就是这个表单项直接删除？先暂时不作
            case Constants.AUTHORITY_INVISIBLE:
                break;
            //可见，这个就是不处理
            //disable:false;required:false
            case Constants.AUTHORITY_ENABLE:
                OptionsBase optionsBase2 = items.get(j).getOptions();
                optionsBase2.setDisabled(false);
                optionsBase2.setRequired(false);
                items.get(j).setOptions(optionsBase2);
                break;
            //无法编辑，获取list中的json对象的options属性进行修改
            //disable:true;required:false
            case Constants.AUTHORITY_DISABLE:
                OptionsBase optionsBase1 = items.get(j).getOptions();
                optionsBase1.setDisabled(true);
                optionsBase1.setRequired(false);
                items.get(j).setOptions(optionsBase1);
                break;
        }
    }

    private void addRequiredRule(FormItem formItem) {
        List<JSONObject> rules = formItem.getRules();

        ItemRuleRequired required = new ItemRuleRequired("此项必填");
        JSONObject object = (JSONObject) JSON.toJSON(required);
        rules.add(object);
    }

    public String getProcModelIdByForm(String form_model_id) {
        return formModelMapper.getProcModelID(form_model_id);
    }

    public String getFormById(String form_model_id) {
        return formModelMapper.getModelJson(form_model_id);
    }

    /**
     * 根据formModelId找到对应的表单框架
     * @param formModelId
     * @return
     */
    public FormJson getFormJson(String formModelId) {
        String modelStr = formModelMapper.getModelJson(formModelId);
        return JSON.parseObject(modelStr,FormJson.class);
    }

    /**
     *
     * @param items
     */

    public void handleAuthoritys(List<FormItem> items) {

    }
}
