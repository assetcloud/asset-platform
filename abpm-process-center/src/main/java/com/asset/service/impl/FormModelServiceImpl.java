package com.asset.service.impl;

import com.asset.dao.AsFormModelMapper;
import com.asset.dao.ModelDeployMapper;
import com.asset.dao.AppFormBindMapper;
import com.asset.entity.AsFormModel;
import com.asset.entity.AppFormBind;
import com.asset.rec.FormGroupRec;
import com.asset.rec.FormModelBindRec;
import com.asset.rec.FormModelCreateRec;
import com.asset.rec.FormModelEditRec;
import com.asset.service.FormModelService;
import com.asset.utils.Constants;
import com.asset.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FormModelServiceImpl implements FormModelService {

    @Autowired
    AsFormModelMapper asFormModelMapper;
    @Autowired
    ModelDeployMapper modelDeployMapper;
    @Autowired
    AppFormBindMapper appFormBindMapper;

    @Override
    public AsFormModel createFormModel(FormModelCreateRec rec) {
        AsFormModel asFormModel = new AsFormModel(rec.getForm_name(),
                rec.getCreated_by(),
                rec.getIcon_cls(),
                Constants.FORM_MODEL_CREATED,
                JsonUtils.recJsonArrayToString(rec.getModel_json())
                );
        asFormModel.setGroupId(-1);
        asFormModel.setProcModelId("null");

        String formModelID = asFormModel.getId();
        //表单模型表中填数据
        int ret = asFormModelMapper.insertSelective(asFormModel);
        //表单模型与App作绑定
        AppFormBind bind = new AppFormBind(rec.getOapp_id(),formModelID);
        appFormBindMapper.insert(bind);

        AsFormModel formModelRet = null;
        //ret为1，说明插入成功,返回这个插入的formModel的详细信息
        if(ret == 1)
        {
            formModelRet = asFormModelMapper.getFormModel(formModelID);
        }


        return formModelRet;
    }

    /**
     * 修改表单模型
     * @param rec
     * @return
     */
    public int editFormModel(FormModelEditRec rec) {
        AsFormModel asFormModel = new AsFormModel(rec);

        return asFormModelMapper.editFormModel(asFormModel);
    }

    /**
     * 把相应的表单模型与流程模型绑定
     */
    public int bindFormModel(FormModelBindRec rec) {
        AsFormModel asFormModel = new AsFormModel(rec.getForm_model_id(),rec.getProc_model_id());
        //在表单模型表中绑定流程模型ID
        int ret = asFormModelMapper.bindProcModel(asFormModel);
        //下面的是在流程模型中绑定表单模型，先不做
        //        asFormModelMapper.bindFormModel2(bindInfo);
        return ret;
    }





    @Override
    public int setFormGroup(FormGroupRec rec) {
        AsFormModel info = new AsFormModel(rec.getForm_model_id(),rec.getGroup_id());
        return asFormModelMapper.setFormGroup(info);
    }

    @Override
    public String getProcModelID(String formModelID) {
        return asFormModelMapper.getProcModelID(formModelID);
    }

}
