package com.asset.service;

import com.alibaba.fastjson.JSONObject;
import com.asset.dao.AsFormInstMapper;
import com.asset.dao.AsProcInstMapper;
import com.asset.dao.UserMapper;
import com.asset.entity.AsFormInst;
import com.asset.entity.AsProcInst;
import com.asset.entity.User;
import com.asset.javabean.FormJson;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    protected ModelService modelService;
    @Autowired
    FormInstService formInstService;
    @Autowired
    AsFormInstMapper asFormInstMapper;
    @Autowired
    AsProcInstMapper procInstMapper;

    public PageGrids getUsers(Integer pageNum, Integer pageSize, String id, String displayName) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.getUsers(id, displayName);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 发起一个流程，专门用于新用户注册审批
     */
    public ProcessInstance createRegisterTask(String registerProcModelID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        //这边系统需要创建一个含有两个节点的流程模型，并创建相应的ActType列表
        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(registerProcModelID);
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

        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        //创建流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(def.getId());
        return instance;
    }

    /**
     * 根据上面创建的注册流程创建相应的注册表单实例
     * @param model_json
     * @param processInstanceId
     * @param editor
     * @param registerFormModelID
     * @param taskID
     */
    public AsFormInst createRegisterFormInst(FormJson model_json,
                                       String registerFormValue,
                                       String processInstanceId,
                                       String editor,
                                       String registerFormModelID,
                                       String taskID) {
        String executionID = formInstService.getExecutionId(processInstanceId);
        String formInstJson = JSONObject.toJSONString(model_json);

        AsFormInst inst = new AsFormInst(
                registerFormModelID,
                processInstanceId,
                executionID,
                taskID,
                editor,
                registerFormValue,
                formInstJson
        );
        asFormInstMapper.insertSelective(inst);

        return inst;
    }


    public void insertProcInst(AsProcInst asProcInst) {
        procInstMapper.insert(asProcInst);
    }
}
