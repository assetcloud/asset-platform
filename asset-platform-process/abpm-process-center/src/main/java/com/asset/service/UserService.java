package com.asset.service;

import com.alibaba.fastjson.JSONObject;
import com.asset.dao.FormInstMapper;
import com.asset.dao.AsProcInstMapper;
import com.asset.dao.UserMapper;
import com.asset.entity.FormInst;
import com.asset.entity.AsProcInst;
import com.asset.entity.User;
import com.asset.exception.ProcExeception;
import com.asset.form.FormItem;
import com.asset.form.FormJsonEntity;
import com.asset.dto.RegisterDTO;
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
    FormInstMapper formInstMapper;
    @Autowired
    AsProcInstMapper procInstMapper;
    @Autowired
    ProcInstService procInstService;

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
     * 根据上面创建的注册流程创建相应的注册表单实例，这里需要对 modelJson中的内容进行修改，设置disable为false
     * @param modelJson
     * @param processInstanceId
     * @param editor
     * @param registerFormModelID
     * @param taskID
     */
    public FormInst createRegisterFormInst(FormJsonEntity modelJson,
                                           String registerFormValue,
                                           String processInstanceId,
                                           String editor,
                                           String registerFormModelID,
                                           String taskID) {
        String executionID = procInstService.getExecutionId(processInstanceId);
        String formInstJson = JSONObject.toJSONString(modelJson);


        List<FormItem> items = modelJson.getList();
        //添加权限信息
        formInstService.handleAuthoritys(items);

        FormInst inst = new FormInst(
                registerFormModelID,
                processInstanceId,
                executionID,
                taskID,
                editor,
                registerFormValue,
                formInstJson
        );
        formInstMapper.insertSelective(inst);

        return inst;
    }


    public void insertProcInst(AsProcInst asProcInst) {
        procInstMapper.insert(asProcInst);
    }

    /**
     * 发起一个注册流程，注册流程表单、绑定的流程模型、绑定的权限数据、流程模型中节点类型都需要事先创建好！！
     * @param dto
     * @throws ProcExeception
     */
    public void createRegisterProc(RegisterDTO dto) throws ProcExeception {
        String registerProcModelID = "088c97ce-9bdd-11e9-b8c8-ba15de269d3a";
        ProcessInstance procInst = createRegisterTask(registerProcModelID);
        if (procInst==null)
        {
            throw new ProcExeception("无法创建流程实例，流程模型ID："+registerProcModelID);
        }
        String[] taskIDs = procInstService.getTaskIDs(procInst.getProcessInstanceId());

        //注册页面绑定的表单模型ID，需要在系统创建之后自动生成
        String registerFormModelID = "0af6e7db-98d5-11e9-993a-0215444863d4";

        createRegisterFormInst(dto.getModel_json(),
                dto.getResiter_value(),
                procInst.getProcessInstanceId(),
                dto.getEditor(),
                registerFormModelID,
                taskIDs[0]);

        //3、持久化流程实例
        AsProcInst asProcInst = new AsProcInst(
                procInst.getProcessInstanceId(),
                registerProcModelID,
                "",
                "",
                dto.getEditor());
        insertProcInst(asProcInst);


        procInstService.completeCurTask(taskIDs[0]);
        procInstService.saveUnCompleteTask(
                procInst.getProcessInstanceId(),
                        registerFormModelID,
                        dto.getResiter_value());
    }
}
