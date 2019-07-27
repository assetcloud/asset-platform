package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.dao.AppFormBindMapper;
import com.asset.dao.FormModelMapper;
import com.asset.dto.FormModelEditDTO;
import com.asset.entity.AppFormBindDO;
import com.asset.entity.FormModelDO;
import com.asset.dto.FormModelCreateDTO;
import com.asset.exception.DatabaseException;
import com.asset.form.FormSheet;
import com.asset.javabean.FormModelBO;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FormModelService {

    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    AppFormBindMapper appFormBindMapper;
    @Autowired
    ApplicationService applicationService;


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
        ArrayList<FormModelDO> formModelDOs = (ArrayList<FormModelDO>) formModelMapper.listFormModels(formModelIds,formStatus,groupId);

        List<FormModelBO> boList = new ArrayList<>();
        for (int i = 0; i < formModelDOs.size(); i++) {
            FormModelBO bo = new FormModelBO(formModelDOs.get(i));
            boList.add(bo);
        }
        return boList;
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


}
