package com.asset.service;

import com.asset.entity.Code;
import com.asset.entity.FormInRun;
import com.asset.entity.JsonForm;

/**
 * 处理第三方表单与Flowable的整合
 * @author yby
 * @time 190523 0945
 * @version 1.0_190523 0945
 */
public interface FormRepositoryService {

    /**
     * 保存第三方表单信息，与流程模型绑定
     * @param formJson
     * @param modelID
     * @param formName
     * @return 返回1表示成功存储，其他表示失败
     */
    int saveForm(String formJson,String modelID,String formName);

    /**
     * 获取与流程模型绑定的第三方表单信息
     * @param modelID
     * @return
     */
    JsonForm getForm(String modelID);

    int saveFormInst(String formname,
                     String procinstid,
                     String formjson,
                     String procdefid,
                     String modelid,
                     String taskid);


    /**
     * 得到流程实例中上一个执行的任务节点时填写的表单实例信息
     * @param taskID
     * @return
     */
    JsonForm getFormInst(String taskID);
}
