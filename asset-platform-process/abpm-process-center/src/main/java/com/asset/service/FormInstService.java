package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.form.FormItem;
import com.asset.form.ItemRuleRequired;
import com.asset.form.FormJson;
import com.asset.rec.*;
import com.asset.utils.Constants;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
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
    AsFormModelMapper asFormModelMapper;
    @Autowired
    AsFormInstMapper asFormInstMapper;
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    ActTypeMapper actTypeMapper;
    @Autowired
    AsProcInstMapper procInstMapper;
    @Autowired
    FormAuthorityMapper formAuthorityMapper;

    @Autowired
    protected ModelService modelService;

    Logger logger = LoggerFactory.getLogger(FormInstService.class);


    /**
     * 当前任务节点完成之后，会自动流转到下一个任务节点，数据库表中会生成这个没有被完成的任务节点的信息，
     * 这里是在as_proc_inst表中写入这个信息
     */
    public void saveUnCompleteTask( String procInstId,
                                    String formModelId,
                                    String formInstValue) {
        FormJson unCompleteFormJson = getFormJson(formModelId);
        //获取当前执行到的任务节点
        String[] taskIDs = getTaskIDs(procInstId);
        //如果下面还有任务节点要处理，就在as_proc_inst中新建这个表单实例字段（每一个TaskId对应一个新的表单实例）
        for (int i=0;i<taskIDs.length;i++){
            String a = asFormInstMapper.getTaskId(taskIDs[i]);
            if (!taskIDs[i].isEmpty()&&
                    a== null )  //当前要存的taskID不能是已经有的的，否则重复保存了
                createFormInst(unCompleteFormJson,
                        procInstId,
                        "",
                        formModelId,
                        formInstValue,
                        taskIDs[i]);
        }
    }


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


    public List<AsFormInst> getTobeReadInsts(List<AsFormInst> formInsts) {
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
                .moveExecutionToActivityId(getExecutionId(procInstID), rollbackActID).changeState();
    }

    //创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
    public AsFormInst createFormInst(FormJson formJson,
                                      String procInstId,
                                      String editor,
                                      String formModelId,
                                     String formInstValue,
                                     String taskId){
        String executionID = getExecutionId(procInstId);
        String formInstJson = JSONObject.toJSONString(formJson);

        AsFormInst inst = new AsFormInst(
                formModelId,
                procInstId,
                executionID,
                taskId,
                editor,
                formInstValue,
                formInstJson
        );
        asFormInstMapper.insertSelective(inst);
        return inst;
    }

    //as_form_inst表中中包含这条表单实例信息，但是是属于还没完成的那种类型，需要填写表单信息后对这项进行更新
    public AsFormInst updateFormInst(FormInstRecBase recBase){
        AsFormInst inst = null;
        // 审批表单实例
        if (recBase instanceof FormInstRecApprove)
        {
            inst = new AsFormInst(
                    ((FormInstRecApprove) recBase).getForm_inst_id(),
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            asFormInstMapper.approveFormInst(inst);
        }
        // 经办节点
        if (recBase instanceof FormInstRecHandle)
        {
            String formInstJson = JSONObject.toJSONString(((FormInstRecHandle) recBase).getForm_inst_json());

            inst = new AsFormInst(
                    ((FormInstRecHandle) recBase).getForm_inst_id(),
                    formInstJson,
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            asFormInstMapper.handleFormInst(inst);
        }
        // 待阅
        if(recBase instanceof FormInstRecReadle)
        {
            inst = new AsFormInst(
                    ((FormInstRecReadle) recBase).getForm_inst_id(),
                    recBase.getForm_inst_value(),
                    recBase.getEditor()
            );
            asFormInstMapper.readFormInst(inst);
        }
        return inst;
    }

    public ProcessInstance getProcInst(String procInstId){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        return runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
    }


    public void completeCurTask(String taskID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();

        taskService.complete(taskID);
    }

    public String[] getTaskIDs(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstID).list();
        String[] taskIDs = new String[tasks.size()];
        for (int i=0;i<tasks.size();i++)
            taskIDs[i]=tasks.get(i).getId();
        return taskIDs;
    }

    public String getExecutionId(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        ExecutionQuery executionQuery = runtimeService.createExecutionQuery().processInstanceId(procInstID);
        List<Execution> executions = executionQuery.list();

        Execution temp = null;
        for (int j = 0; j < executions.size(); j++) {
            temp = executions.get(j);
            if (temp.getActivityId() == null)
                continue;
            else
                break;
        }
        return temp.getId();
    }

    //直接由流程模型ID创建相应的流程实例
    public ProcessInstance createProcInstance(String procModelId,String defID,String deployID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();


        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);

        DeploymentBuilder builder = repositoryService.createDeployment();
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        String processXMLName = modelData.getName()+".bpmn20.xml";
        String depResourceName = modelData.getName()+"ResName";
        String depName = modelData.getName()+"DepName";
        String depKey = modelData.getName()+"DepKey";


        //部署
        Deployment dep = builder.addBpmnModel(depResourceName,bpmnModel).
                name(depName).
                key(depKey).
                addBytes(processXMLName,bpmnBytes).   //必须加这个，否则流程定义文件会为空
                deploy();
        //获取流程定义
        deployID = dep.getId();
        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        defID = def.getId();
        //创建流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(def.getId());
        return instance;
    }

    public void completeAll() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> lists = query.active().list();

        for(int j = 0 ; j<lists.size() ; j++) {
            ProcessInstance instance = lists.get(j);

            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                if (tasks.size() == 0)
                    break;

                //遍历，然后完成
                for (Task task : tasks) {
                    taskService.complete(task.getId());
                }
            }
        }
    }

    public TaskCount getCount(String userID,
                              int taskType) throws Exception {
        //map是TaskId和ActType的Map
        HashMap<String,Integer> map =new HashMap<String, Integer>();

        //1、先获取流转到该用户对应的procInstId\taskID\actId的集合
        List<AsTask> tasks = getCurTasks(userID);
        if (tasks.size()==0)
            return new TaskCount(taskType,0);

        //2、对上面集合进行遍历，从数据库中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        for(int i =0;i<tasks.size();i++)
        {
            AsTask curAsTask = tasks.get(i);
            //2.1 由流程实例ID获取流程模型ID
            String procModelId = getProcModelIdByProc(curAsTask.getProcInstId());
            //这里说明我们在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例，说明数据库中存在脏的流程实例数据
            if (procModelId.isEmpty()){
                logger.error("在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例!没有如下ProcInstID:{}", curAsTask.getProcInstId());
                throw new Exception("有运行中流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            }

            //2.2 根据流程模型ID、节点ID 获取对应的ActType，然后决定要不要把该taskInfos中的这一项给去掉
            Integer actType = getActType(procModelId, curAsTask.getActId());
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

    public List<AsTask> getCurTasks(String userID) {
        return flowableMapper.getTaskInfos(userID);
    }

    public String getProcModelIdByProc(String procInstId) {
        return procInstMapper.getProcModelId(procInstId);
    }

    public Integer getActType(String procModelId, String actId) {
        return actTypeMapper.getActType(procModelId,actId);
    }


    public String getActId(String taskId) {
        return flowableMapper.getActId(taskId);
    }

    public Integer getCurAuthority(String procModelId, String actId, String itemKey) {
        return formAuthorityMapper.getAuthority(procModelId,actId,itemKey);
    }

    /**
     *
     * @param items
     * @param j
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
        return asFormModelMapper.getProcModelID(form_model_id);
    }

    public String getFormById(String form_model_id) {
        return asFormModelMapper.getModelJson(form_model_id);
    }

    /**
     * 根据formModelId找到对应的表单框架
     * @param formModelId
     * @return
     */
    public FormJson getFormJson(String formModelId) {
        String modelStr = asFormModelMapper.getModelJson(formModelId);
        return JSON.parseObject(modelStr,FormJson.class);
    }

}
