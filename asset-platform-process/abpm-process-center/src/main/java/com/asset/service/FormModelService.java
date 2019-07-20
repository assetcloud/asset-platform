package com.asset.service;

import com.alibaba.fastjson.JSONObject;
import com.asset.dao.AppFormBindMapper;
import com.asset.dao.FormModelMapper;
import com.asset.entity.AppFormBind;
import com.asset.entity.FormModel;
import com.asset.dto.FormGroupRec;
import com.asset.dto.FormModelBindRec;
import com.asset.dto.FormModelCreateRec;
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



    public FormModel createFormModel(FormModelCreateRec rec) {
        String jsonString = JSONObject.toJSONString(rec.getModel_json());
        FormModel formModel = new FormModel(rec.getForm_name(),
                rec.getCreated_by(),
                rec.getIcon_cls(),
                Constants.FORM_MODEL_UNBIND,
                jsonString
                );
        formModel.setGroupId(-1);
        formModel.setProcModelId("null");

        String formModelID = formModel.getId();
        //表单模型表中填数据
        int ret = formModelMapper.insertSelective(formModel);
        //表单模型与App作绑定
        AppFormBind bind = new AppFormBind(rec.getOapp_id(),formModelID);
        appFormBindMapper.insert(bind);

        FormModel formModelRet = null;
        //ret为1，说明插入成功,返回这个插入的formModel的详细信息
        if(ret == 1)
        {
            formModelRet = formModelMapper.getFormModel(formModelID);
        }


        return formModelRet;
    }

//    /**
//     * 修改表单模型
//     * @param dto
//     * @return
//     */
//    public int editFormModel(FormModelEditRec dto) {
//
//    }

    /**
     * 把相应的表单模型与流程模型绑定
     */
    public int bindFormModel(FormModelBindRec rec) {
        FormModel formModel = new FormModel(rec.getForm_model_id(),rec.getProc_model_id());
        //在表单模型表中绑定流程模型ID
        int ret = formModelMapper.bindProcModel(formModel);
        //下面的是在流程模型中绑定表单模型，先不做，这个要做的话，需要对原来flowable的数据库表进行修改，这里暂时不修改了
        //        formModelMapper.bindFormModel2(bindInfo);
        return ret;
    }






    public int setFormGroup(FormGroupRec rec) {
        FormModel info = new FormModel(rec.getForm_model_id(),rec.getGroup_id());
        return formModelMapper.setFormGroup(info);
    }


    public String getProcModelID(String formModelID) {
        return formModelMapper.getProcModelID(formModelID);
    }

    /**
     * 返回流程模型
     * @param appId
     * @param userId
     * @param groupId 传入的值为-1时表示不对分组进行限制，某一个具体值表示只筛选这个分组的表单模型
     * @param formStatus
     * @return
     */

    public List<FormModel> getFormModels(String appId, String userId, int groupId, int formStatus) {
        ArrayList<FormModel> formModels = (ArrayList<FormModel>) applicationService.getFormModels(appId);
        for(int i = 0; i< formModels.size(); i++)
        {
            //筛选分组ID（存储时默认值是-1）
            if (groupId != -1) {
                if(formModels.get(i).getGroupId()!=groupId)
                {
                    formModels.remove(i);
                    i--;
                    //当前列表项已经被处理，继续处理下一条
                    continue;
                }
            }

            //筛选表单模型状态
            //-1代表获取全部，不筛选
            if (formStatus == -1)
                return formModels;
            if(formModels.get(i).getStatus()!=formStatus)
            {
                formModels.remove(i);
                i--;
            }

        }
        return formModels;
    }

}
