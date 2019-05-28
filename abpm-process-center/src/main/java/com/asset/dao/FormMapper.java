package com.asset.dao;

import com.asset.entity.FormInRun;
import com.asset.entity.JsonForm;
import org.springframework.stereotype.Repository;

@Repository
public interface FormMapper {

    /**
     * 在数据库中添加保存表单json数据的列
     * @return 返回1表示保存成功
     */
    int saveForm(JsonForm jsonForm);

    int createFormColumn();

    /**
     * 在数据库中添加保存表单json数据的列
     * @return 返回JsonForm对象
     */
    JsonForm getForm(String modelID);

    int saveFormInst(FormInRun formInRun);

    /**
     * 获取对应的taskID的表单实例
     * @param taskID
     * @return
     */
    JsonForm getFormInst(String taskID);
}
