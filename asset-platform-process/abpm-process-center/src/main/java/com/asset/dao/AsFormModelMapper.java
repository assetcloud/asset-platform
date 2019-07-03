package com.asset.dao;

import com.asset.entity.AsFormModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsFormModelMapper {
    int insert(AsFormModel record);

    int insertSelective(AsFormModel record);

    /**
     * 返回对应的表单模型
     * @param formModelID
     * @return
     */
    AsFormModel getFormModel(String formModelID);

    /**
     * 修改表单模型
     * @param asFormModel
     * @return
     */
    int editFormModel(AsFormModel asFormModel);

    /**
     * 在表单模型表中绑定对应的流程模型
     * @param bindInfo
     */
    int bindProcModel(AsFormModel bindInfo);

    /**
     * 根据传进来的表单模型ID一个一个取出来
     * @param  formModelIDs
     * @return
     */
    List<AsFormModel> getFormModels(List<String> formModelIDs);

    /**
     * 给应用下的表单模型进行分组
     * @param groupInfo
     * @return
     */
    int setFormGroup(AsFormModel groupInfo);

    /**
     * 获取与该表单模型绑定的流程模型ID
     * @param formModelId
     * @return
     */
    String getProcModelID(String formModelId);

    /**
     * 从as_form_model表中找到model_json数据
     * @param formModelID
     * @return
     */
    String getModelJson(String formModelID);

}
