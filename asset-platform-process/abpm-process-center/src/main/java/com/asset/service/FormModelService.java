package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.dao.FormModelMapper;
import com.asset.dto.FormModelEditDTO;
import com.asset.entity.AsTempletFormModelDO;
import com.asset.entity.FormModelDO;
import com.asset.dto.FormModelCreateDTO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.form.FormSheet;
import com.asset.javabean.FormModelBO;
import com.asset.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FormModelService {

    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    FormInstService formInstService;

    /**
     * 创建表单模型数据
     * @param dto
     * @return
     */
    public FormModelBO createFormModel(FormModelCreateDTO dto) throws DatabaseException {
        //插入数据+绑定AppId
        FormModelDO formModelDO = insertFormModelDO(dto);
        //返回刚刚插入的这条数据
        FormModelBO bo = new FormModelBO();
        BeanUtils.copyProperties(formModelDO,bo);
        return bo;
    }


    /**
     * 插入一条新的表单模型数据，初始表单的组ID为-1，未绑定流程模型ID，所以值为null
     * @param dto
     * @return
     */
    public FormModelDO insertFormModelDO(FormModelCreateDTO dto) throws DatabaseException {
        String formSheetStr = JSONObject.toJSONString(dto.getForm_sheet());
        FormModelDO formModelDO = new FormModelDO.Builder()
                .formName(dto.getForm_name())
                .createdBy(dto.getCreated_by())
                .iconCls(dto.getIcon_cls())
                .modelSheet(formSheetStr)
                .appId(dto.getApp_id())
                .sceneId(dto.getScene_id())
                .groupId(-1)
                .createdTime(new Date())
                .version(1).build();
        int flag =  formModelMapper.insertSelective(formModelDO);
        if(flag == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
        return formModelDO;
    }

    /**
     * 把相应的表单模型与流程模型绑定
     */
    public void bindFormAndProcModel(String formModelId,String procModelId) throws DatabaseException {
        //在表单模型表中绑定流程模型ID
        int status = formModelMapper.bindFormAndProcModel(formModelId,procModelId);
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


//    /**
//     * 返回流程模型
//     * @param appId
//     * @param groupId 传入的值为-1时表示不对分组进行限制，某一个具体值表示只筛选这个分组的表单模型
//     * @param formStatus 0:还没和流程模型绑定  1:和流程模型绑定  2:已删除
//     * @return
//     */
//    public List<FormModelBO> getFormModelBOs(String appId, int groupId, int formStatus) {
//        List<String> formModelIds = getFormModels(appId);
//        if(formModelIds==null||formModelIds.size()==0)
//            return null;
//        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModels(formModelIds,formStatus,groupId);
//
//        List<FormModelBO> boList = new ArrayList<>();
//        for (int i = 0; i < formModelDOs.size(); i++) {
//            FormModelBO bo = new FormModelBO();
//            BeanUtils.copyProperties(formModelDOs.get(i),bo);
//            boList.add(bo);
//        }
//        return boList;
//    }

    /**
     * 返回所有表单模型，不对其状态进行筛选
     * @param appId
     * @param groupId
     * @return
     */
    public List<FormModelDO> listAllFormModelDO(String appId, int groupId){
        List<String> formModelIds = getFormModels(appId);
        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModels(formModelIds,-1,groupId);
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

//    /**
//     * 在系统初始化前插入注册审批的表单数据
//     * @param registerFormId
//     */
//    public void initRegisterFormModel(String registerFormId) throws DatabaseException {
//        FormModelDO formModelDO = new FormModelDO(registerFormId,
//                "注册表单",
//                Constants.USER_ADMIN,
//                1,
//                -1,
//                Constants.FORM_MODEL_BINDED,
//                Constants.REGISTER_PROC_ID,
//                Constants.REGISTER_FORM_SHEET);
//        int status =  formModelMapper.insertSelective(formModelDO);
//        if(status == Constants.DATABASE_FAILED)
//            throw new DatabaseException("插入数据失败！");
//    }

//    /**
//     * 在系统初始化前插入注册审批的表单数据
//     * @param sceneSelectFormId
//     */
//    public void initSceneSelectFormModel(String sceneSelectFormId) throws DatabaseException {
//        FormModelDO formModelDO = new FormModelDO(sceneSelectFormId,
//                "工作场景选择表单",
//                Constants.USER_ADMIN,
//                1,
//                -1,
//                Constants.FORM_MODEL_BINDED,
//                Constants.SCENE_SELECT_PROC_ID,
//                Constants.SCENE_SELECT_FORM_SHEET);
//        int status =  formModelMapper.insertSelective(formModelDO);
//        if(status == Constants.DATABASE_FAILED)
//            throw new DatabaseException("插入数据失败！");
//    }

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
        List<String> formModelIds = getFormModels(appId);
        if(formModelIds==null||formModelIds.size()==0)
            return null;

        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModelsByModelId(formModelIds);

        return formModelDOs;
    }

    public String getRegisterFormId(String formName,String procModelId) {
        return formModelMapper.getFormId(formName,procModelId);
    }

    /**
     * 获取一个 App应用下 所有表单模型Id数组
     * @return
     */
    public List<String> getFormModels(String appId) {
        return formModelMapper.getFormModelIDs(appId);
    }

    /**
     * 因为流程模型与表单模型是一对一的，所以可以由流程模型Id获取对应的表单模型Id
     * @param procModelId
     * @return
     */
    public String getFormModelId(String procModelId) {
        return formModelMapper.getFormModelId(procModelId);
    }

    public List<FormModelDO> getUnbindFormModels() {
        return formModelMapper.selectUnBindAll();
    }

    public String getBindFormSheet(String procModelId) {
        return formModelMapper.getBindFormSheet(procModelId);
    }

    public String getSceneIdByProcModelId(String procModelId) {
        return formModelMapper.getSceneIdByProcModelId(procModelId);
    }

    public String getSceneIdByTaskId(String taskId) {
        String formModelId = formInstService.getFormModelId(taskId);
        return getSceneId(formModelId);
    }

    /**
     * 导入表单模型资源
     * @param asTempletFormModelDO
     * @return
     */
    public void insertTempletResource(AsTempletFormModelDO asTempletFormModelDO,
                                        String sceneId,
                                        String userId,
                                        String bindProcModelId,
                                        String bindAppId) throws DatabaseException{
        FormModelDO formModelDO = new FormModelDO.Builder()
                .sceneId(sceneId)
                .createdTime(new Date())
                .createdBy(userId)
                .version(1)
                .groupId(Constants.GROUP_ALL)
                .isBinded(1)
                .isAddNodeInfo(1)
                .isAddSeqCondition(1)
                .isBindAuthority(1)
                .procModelId(bindProcModelId)
                .appId(bindAppId)
                .build();
        BeanUtils.copyProperties(asTempletFormModelDO,formModelDO,new String[]{"id"});
        int flag = formModelMapper.insertSelective(formModelDO);
        if(flag==Constants.DATABASE_FAILED)
            throw new DatabaseException("插入表单模型资源失败！");
    }

    public FormModelDO selectFormModelDO(String formModelId) {
        return formModelMapper.selectById(formModelId);
    }

    public void bindAuthority(String procModelId) {
        String formModelId = getFormModelId(procModelId);
        formModelMapper.bindAuthority(formModelId);
    }

    public void updateSeqCondition(String procModelId) {
        String formModelId = getFormModelId(procModelId);
        formModelMapper.bindSeq(formModelId);
    }

    public void bindNodes(String procModelId) {
        String formModelId = getFormModelId(procModelId);
        formModelMapper.bindNodes(formModelId);
    }
}
