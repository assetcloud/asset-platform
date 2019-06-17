package com.asset.service.impl;

import com.asset.dao.FormModelMapper;
import com.asset.dao.ModelDeployMapper;
import com.asset.dao.OAppFormBindMapper;
import com.asset.entity.*;
import com.asset.rec.*;
import com.asset.service.FormModelService;
import com.asset.utils.Constants;
import com.asset.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FormModelServiceImpl implements FormModelService {

    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    ModelDeployMapper modelDeployMapper;
    @Autowired
    OAppFormBindMapper oAppFormBindMapper;

    @Override
    public FormModelInfo createFormModel(FormModelCreateRec rec) {
        FormModelInfo formModelInfo = new FormModelInfo(rec.getForm_name(),
                rec.getCreated_by(),
                JsonUtils.recJsonArrayToString(rec.getModel_json()),
                rec.getIcon_cls(),
                Constants.FORM_MODEL_CREATED);
        formModelInfo.setGroup_id("-1");

        //表单模型表中填数据
        int ret = formModelMapper.createFormModel(formModelInfo);
        //表单模型与OApp作绑定
        OAppFormBind bind = new OAppFormBind(rec.getOapp_id(),rec.getForm_model_id());
        oAppFormBindMapper.insert(bind);

        FormModelInfo formModelRet = null;
        //ret为1，说明插入成功,返回这个插入的formModel的详细信息
        if(ret == 1)
        {
            formModelRet = formModelMapper.getFormModel(formModelInfo.getForm_model_id());
        }

        return formModelRet;
    }

    /**
     * 修改表单模型
     * @param rec
     * @return
     */
    public int editFormModel(FormModelEditRec rec) {
        FormModelInfo formModelInfo = new FormModelInfo(rec);

        return formModelMapper.editFormModel(formModelInfo);
    }

    /**
     * 把相应的表单模型与流程模型绑定
     */
    public int bindFormModel(FormModelBindRec rec) {
        FormBindInfo bindInfo = new FormBindInfo(rec.getForm_model_id(),rec.getProc_model_id());
        //需要在proc表中绑定表单，也需要在表单模型表中绑定流程模型ID
        int ret = formModelMapper.bindFormModel(bindInfo);
        formModelMapper.bindFormModel2(bindInfo);
        return ret;
    }

    /**
     * 获取一个 OApp应用下 所有表单模型数据
     * @return
     */
    @Override
    public List<FormModelInfo> getFormModels(String oappID) {
        //先从asset_oapp_form表中找到所有该OApp应用下 表单模型ID
        List<String> formModelIDs = oAppFormBindMapper.getFormModelIDs(oappID);

        return formModelMapper.getFormModels(formModelIDs);
    }

    /**
     * 保存表单模型权限信息
     * @param formModelAuthority
     * @return
     */
    @Override
    public int saveFormModelAuthority(FormAuthorityRec formModelAuthority) {
        FormAuthorityInfo authorityInfo = new FormAuthorityInfo(formModelAuthority);
        System.out.println(formModelAuthority.toString());
        return formModelMapper.saveFormModelAuthority(authorityInfo);
    }

    public int formBindDeploy(FormDeployBindRec formDeployBindRec) {
        String appDefKey = formDeployBindRec.getApp_def_key();
        String procModelID = formDeployBindRec.getProc_model_id();

        //去act_re_deployment表中取出所有与APP_DEF_KEY相同值的
        List<String> procDeployIDs = modelDeployMapper.getDeployID(appDefKey);
        String procDeployID = procDeployIDs.get(0);
        String formModelID = formModelMapper.getFormModelIdByProc(procModelID);

        FormDeployRelationInfo relationInfo = new FormDeployRelationInfo(procDeployID,formModelID);

        return formModelMapper.saveFormDeployRelation(relationInfo);
    }

    @Override
    public int setFormGroup(FormGroupRec rec) {
        FormModelInfo info = new FormModelInfo(rec.getForm_model_id(),rec.getGroup_id());
        return formModelMapper.setFormGroup(info);
    }

}
