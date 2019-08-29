package com.asset.dao;

import com.asset.entity.FormModelDO;
import com.asset.javabean.FormModelBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormModelMapper {
    int insert(FormModelDO record);

    int insertSelective(FormModelDO record);

    /**
     * 返回对应的表单模型
     * @param formModelID
     * @return
     */
    FormModelDO getFormModel(String formModelID);

    /**
     * 修改表单模型
     * @param formModelDO
     * @return
     */
    int editFormModel(FormModelDO formModelDO);

    /**
     * 在表单模型表中绑定对应的流程模型
     * @param formModelId
     * @param procModelId
     * @return
     */
    int bindFormAndProcModel(@Param("formModelId") String formModelId,
                             @Param("procModelId") String procModelId);

    /**
     * 根据传进来的表单模型ID一个一个取出来
     * @param  formModelIDs
     * @return
     */
    List<FormModelDO> listAllFormModels(List<String> formModelIDs);

    /**
     * 根据传进来的表单模型ID一个一个取出来
     * @param  formModelIDs
     * @param groupId 传入的值为-1时表示不对分组进行限制，某一个具体值表示只筛选这个分组的表单模型
     * @param formStatus -1:全部 0:还没和流程模型绑定  1:和流程模型绑定  2:已删除
     */
    List<FormModelDO> listFormModels(@Param("formModelIDs")List<String> formModelIDs,
                                     @Param("formStatus") int formStatus,
                                     @Param("groupId") int groupId);


    /**
     * 给应用下的表单模型进行分组
     * @param groupInfo
     * @return
     */
    int setFormGroup(FormModelDO groupInfo);

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
    String getModelSheetStr(String formModelID);

    String checkFormContain(String formModelId);

    String getSceneId(String formModelId);

    String getFormModelName(String formModelId);

    List<FormModelDO> selectAll();

    List<FormModelDO> selectUnBindAll();

    List<FormModelDO> listFormModelsByModelId(@Param("formModelIDs")List<String> formModelIds);

    String getFormId(@Param("formName") String formName, @Param("procId") String procId);

    List<String> getFormModelIDs(String appId);

    String getFormModelId(String procModelId);

    String getBindFormSheet(String procModelId);

    String getSceneIdByProcModelId(String procModelId);

    FormModelDO selectById(String formModelId);

    void bindAuthority(String formModelId);

    void bindSeq(String formModelId);

    void bindNodes(String formModelId);
}
