package com.asset.service.impl;

import com.asset.dao.*;
import com.asset.entity.AsFormInst;
import com.asset.entity.AsFormProcInstBind;
import com.asset.rec.FormInstCommitRec;
import com.asset.service.FormInstService;
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
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormInstServiceImpl implements FormInstService {
    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    ProcInstMapper procInstMapper;
    @Autowired
    AsFormInstMapper asFormInstMapper;
    @Autowired
    AsFormProcInstBindMapper asFormProcInstBindMapper;

    @Autowired
    protected ModelService modelService;
    /**
     * 这边根据表单模型需要：
     * 1、创建流程实例
     * 2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
     * 3、流程实例与表单实例绑定，存入as_proc_relation表中
     * @param rec
     * @return
     */
    @Override
    public int commitFormInst(FormInstCommitRec rec) {
        //先获取与表单模型唯一绑定的流程模型ID
        String procModelID = formModelMapper.getProcModelID(rec.getForm_model_id());
        String defID = null;
        String deployID = null;
        //直接由流程模型后台创建流程实例
        ProcessInstance procInst = createProcInstance(procModelID,defID,deployID);

        //创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        String modelJson = formModelMapper.getModelJson(rec.getForm_model_id());
        String executionID = getExecutionId(procInst.getProcessInstanceId());
        String taskID = getTaskID(procInst.getProcessInstanceId());

        AsFormInst inst = new AsFormInst(
                rec.getForm_model_id(),
                procInst.getProcessInstanceId(),
                executionID,
                taskID,
                rec.getCreated_by(),
                modelJson
        );
        asFormInstMapper.insertSelective(inst);

        //流程实例与表单实例绑定，存入as_proc_relation表中
        AsFormProcInstBind asProcRelation =  new AsFormProcInstBind(
                rec.getForm_model_id(),
                procModelID,
                defID,
                deployID,
                procInst.getProcessInstanceId(),
                inst.getId(),
                rec.getCreated_by()
        );
        asFormProcInstBindMapper.insertSelective(asProcRelation);

        return Constants.CODE_SUCCESS;
    }


    /**
     * 用户登录系统之后，点击主页面的待办窗口中任一一项，返回对应的表单实例信息
     * 流程实例列表中 所有流转到当前执行人的流程实例，把它们取出来，并且找到对应的表单实例，把表单实例信息显示在界面上
     * 这里主要是两步，先取流程实例、再根据流程实例，获取表单实例
     * @param userID
     * @param form_status
     * @return
     */
    @Override
    public List<AsFormInst> getFormInsts(String userID, Integer form_status) {
        //先取流转到该用户的所有流程实例ID
        List<String> procInstIDs = asFormInstMapper.getProcInstIDs(userID);
        //返回与这些流程实例ID绑定的表单实例的信息
        List<AsFormInst> formInsts = asFormInstMapper.getFormInsts(procInstIDs);

        return formInsts;
    }

    private String getTaskID(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstID).list();
        return tasks.get(0).getId();
    }

    private String getExecutionId(String procInstID) {
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
    private ProcessInstance createProcInstance(String procModelId,String defID,String deployID) {
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
}
