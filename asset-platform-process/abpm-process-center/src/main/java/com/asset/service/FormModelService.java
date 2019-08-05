package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.FlowableApplication;
import com.asset.dao.AppFormBindMapper;
import com.asset.dao.FormModelMapper;
import com.asset.dto.FormModelEditDTO;
import com.asset.entity.AppFormBindDO;
import com.asset.entity.FormModelDO;
import com.asset.dto.FormModelCreateDTO;
import com.asset.exception.DatabaseException;
import com.asset.exception.InfoException;
import com.asset.form.FormSheet;
import com.asset.javabean.FormModelBO;
import com.asset.utils.Constants;
import com.sun.org.apache.regexp.internal.RE;
import org.flowable.dmn.model.DecisionTableOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FormModelService {

    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    AppFormBindMapper appFormBindMapper;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    FormProcService formProcService;


    /**
     * 创建表单模型数据
     * @param dto
     * @return
     */
    public FormModelBO createFormModel(FormModelCreateDTO dto) throws DatabaseException {
        //插入数据
        FormModelDO formModelDO = insertFormModelDO(dto);
        //绑定AppId
        bindAppAndForm(formModelDO.getId(),dto.getApp_id());
        //返回刚刚插入的这条数据
        return new FormModelBO(formModelMapper.getFormModel(formModelDO.getId()));
    }

    /**
     * 绑定表单模型和APP
     * @param formModelID
     * @param appId
     * @throws DatabaseException
     */
    private void bindAppAndForm(String formModelID,String appId) throws DatabaseException {
        //表单模型与App作绑定
        AppFormBindDO record = new AppFormBindDO(appId,formModelID);
        int status =  appFormBindMapper.insert(record);
        if(status == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    /**
     * 插入一条新的表单模型数据，初始表单的组ID为-1，未绑定流程模型ID，所以值为null
     * @param dto
     * @return
     */
    public FormModelDO insertFormModelDO(FormModelCreateDTO dto) throws DatabaseException {
        String formSheetStr = JSONObject.toJSONString(dto.getForm_sheet());
        FormModelDO formModelDO = new FormModelDO(dto.getForm_name(),
                dto.getCreated_by(),
                dto.getIcon_cls(),
                Constants.FORM_MODEL_UNBIND,
                formSheetStr
        );
        formModelDO.setSceneId(dto.getScene_id());
        formModelDO.setGroupId(-1);
        formModelDO.setProcModelId("null");
        int status =  formModelMapper.insertSelective(formModelDO);
        if(status == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
        return formModelDO;
    }

    /**
     * 把相应的表单模型与流程模型绑定
     */
    public void bindFormAndProcModel(String formModelId,String procModelId) throws DatabaseException {
        //在表单模型表中绑定流程模型ID
        int status = formModelMapper.bindFormAndProcModel(formModelId,procModelId);
        formProcService.bindFormAndProc(formModelId,procModelId);
        if(status == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    /**
     * 对表单模型进行修改
     * @param dto
     * @throws DatabaseException
     */
    public void updateFormModel(FormModelEditDTO dto) throws DatabaseException {
        FormModelDO formModelDO = new FormModelDO(dto);
        int i= formModelMapper.editFormModel(formModelDO);
        if(i==Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }


    /**
     * 返回流程模型
     * @param appId
     * @param groupId 传入的值为-1时表示不对分组进行限制，某一个具体值表示只筛选这个分组的表单模型
     * @param formStatus 0:还没和流程模型绑定  1:和流程模型绑定  2:已删除
     * @return
     */
    public List<FormModelBO> getFormModels(String appId, int groupId, int formStatus) {
        List<String> formModelIds = applicationService.getFormModels(appId);
        if(formModelIds==null||formModelIds.size()==0)
            throw new InfoException("应用ID不存在，请检查");
        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModels(formModelIds,formStatus,groupId);

        List<FormModelBO> boList = new ArrayList<>();
        for (int i = 0; i < formModelDOs.size(); i++) {
            FormModelBO bo = new FormModelBO(formModelDOs.get(i));
            boList.add(bo);
        }
        return boList;
    }

    public List<FormModelDO> getFormModelDOs(String appId, int groupId, int formStatus){
        List<String> formModelIds = applicationService.getFormModels(appId);
        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModels(formModelIds,formStatus,groupId);
        return formModelDOs;
    }


    public List<FormModelDO> getFormModels(){
        return formModelMapper.selectAll();
    }

    public String getProcModelID(String formModelID) {
        return formModelMapper.getProcModelID(formModelID);
    }

    public String getModelSheetStr(String form_model_id) {
         return formModelMapper.getModelSheetStr(form_model_id);
    }

    public FormSheet getModelSheet(String form_model_id) {
        String modelStr = formModelMapper.getModelSheetStr(form_model_id);
        return JSON.parseObject(modelStr, FormSheet.class);
    }

    /**
     * 在系统初始化之后，检查数据库表as_form_model中是否包含注册审批这个表单
     * @return
     */
    public boolean checkRegisterFormContain(String formModelId, ApplicationContext context) {
        return formModelMapper.checkFormContain(formModelId) == null?false:true;
    }

    /**
     * 在系统初始化前插入注册审批的表单数据
     * @param registerFormId
     */
    public void initRegisterFormModel(String registerFormId) throws DatabaseException {
        FormModelDO formModelDO = new FormModelDO(registerFormId,
                "注册表单",
                Constants.USER_ADMIN,
                1,
                -1,
                Constants.FORM_MODEL_BINDED,
                Constants.REGISTER_PROC_ID,
                Constants.REGISTER_FORM_SHEET);
        int status =  formModelMapper.insertSelective(formModelDO);
        if(status == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    /**
     * 在系统初始化前插入注册审批的表单数据
     * @param sceneSelectFormId
     */
    public void initSceneSelectFormModel(String sceneSelectFormId) throws DatabaseException {
        FormModelDO formModelDO = new FormModelDO(sceneSelectFormId,
                "工作场景选择表单",
                Constants.USER_ADMIN,
                1,
                -1,
                Constants.FORM_MODEL_BINDED,
                Constants.SCENE_SELECT_PROC_ID,
                Constants.SCENE_SELECT_FORM_SHEET);
        int status =  formModelMapper.insertSelective(formModelDO);
        if(status == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    /**
     * 获取当前表单流程的工作场景ID
     * @param formModelId
     * @return
     */
    public String getSceneId(String formModelId) {
        return formModelMapper.getSceneId(formModelId);
    }


    public String getFormName(String formModelId) {
        return formModelMapper.getFormModelName(formModelId);
    }

    public List<FormModelDO> getAdminApplicationFormModel(String appId) {
        List<String> formModelIds = applicationService.getFormModels(appId);
        if(formModelIds==null||formModelIds.size()==0)
            throw new InfoException("应用ID不存在，请检查");

        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModelsByModelId(formModelIds);

        return formModelDOs;
    }
}
