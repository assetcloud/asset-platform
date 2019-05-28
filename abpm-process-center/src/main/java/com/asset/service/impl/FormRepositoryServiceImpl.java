package com.asset.service.impl;

import com.asset.dao.FormMapper;
import com.asset.entity.FormInRun;
import com.asset.entity.JsonForm;
import com.asset.service.FormRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormRepositoryServiceImpl implements FormRepositoryService {

    @Autowired
    FormMapper formMapper;

    @Override
    public int saveForm(String formJson,String modelID,String formName) {
        JsonForm jsonForm = new JsonForm(formJson,modelID,formName);
        int ret = formMapper.saveForm(jsonForm);
        return ret;
    }

    /**
     * 得到流程模型对应的第三方表单信息
     * @param modelID
     * @return 返回
     */
    @Override
    public JsonForm getForm(String modelID) {
        return formMapper.getForm(modelID);
    }


    /**
     * 执行的流程实例，先从数据库中获取当前执行的task,在flowable执行提交任务的时候并行执行保存相应第三方表单的任务
     * @param formname
     * @param procinstid
     * @param formjson
     * @return
     */
    @Override
    public int saveFormInst(String formname,
                            String procinstid,
                            String formjson,
                            String procdefid,
                            String modelid,
                            String taskid) {
        FormInRun formInRun = new FormInRun(taskid,procinstid,procdefid,modelid,formname,formjson);
        return formMapper.saveFormInst(formInRun);
    }

    /**
     * 得到流程实例中上一个执行的任务节点时填写的表单实例信息
     * @param taskID 上一个执行的任务节点的taskID
     * @return
     */
    @Override
    public JsonForm getFormInst(String taskID) {
        return formMapper.getFormInst(taskID);
    }
}
