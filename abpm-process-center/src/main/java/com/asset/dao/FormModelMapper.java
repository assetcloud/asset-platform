package com.asset.dao;

import com.asset.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormModelMapper {

    /**
     * 在数据库中创建表单模型
     * @return 返回1表示保存成功
     */

    int createFormModel(FormModelInfo formModelInfo);

    /**
     * 修改表单模型
     * @param formModelInfo
     * @return
     */
    int editFormModel(FormModelInfo formModelInfo);


    /**
     * 在流程模型表中绑定对应的表单模型
     * @return
     * @param bindInfo
     */
    int bindFormModel(FormBindInfo bindInfo);

    /**
     * 在表单模型表中绑定对应的流程模型
     * @param bindInfo
     */
    void bindFormModel2(FormBindInfo bindInfo);

    /**
     * 获取一个应用下所有表单模型信息
     * 这里根据传进来的表单模型ID一个一个取出来
     * @return
     */
    List<FormModelInfo> getFormModels(List<String> formModelIDs);

    /**
     * 返回对应的表单模型
     * @param formModelID
     * @return
     */
    FormModelInfo getFormModel(String formModelID);

    /**
     * 保存表单模型权限数据
     * @param formModelAuthority
     * @return
     */
    int saveFormModelAuthority(FormAuthorityInfo formModelAuthority);

    String getFormModelIdByProc(String procModelID);

    int saveFormDeployRelation(FormDeployRelationInfo relationInfo);


    /**
     * 返回与对应流程部署绑定的表单模型ID
     * @param procDeployID 在流程实例创建阶段得到的流程部署ID（这个阶段无法得到流程模型ID）
     * @return
     */
    String getFormModelIdByDeploy(String procDeployID);

    /**
     * 从asset_form_model表中找到model_json数据
     */
    String getModelJson(String formModelID);

    int setFormGroup(FormModelInfo info);

    /**
     * 获取与该表单模型绑定的流程模型ID
     * @param form_model_id
     * @return
     */
    String getProcModelID(String form_model_id);
}
