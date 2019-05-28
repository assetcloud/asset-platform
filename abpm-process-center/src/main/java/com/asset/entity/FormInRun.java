package com.asset.entity;

/**
 * 用来保存于流程实例中每一个运行的task阶段做绑定的第三方表单信息
 * @author yby
 * @time 190524 1916
 * @version 1.0_190524 1916
 */
public class FormInRun {
    String taskid;
    String procinstid;
    String procdefid;
    String modelid;
    String formname;
    String formjson;

    public FormInRun(String taskid, String procinstid, String procdefid, String modelid, String formname, String formjson) {
        this.taskid = taskid;
        this.procinstid = procinstid;
        this.procdefid = procdefid;
        this.modelid = modelid;
        this.formname = formname;
        this.formjson = formjson;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getProcinstid() {
        return procinstid;
    }

    public void setProcinstid(String procinstid) {
        this.procinstid = procinstid;
    }

    public String getProcdefid() {
        return procdefid;
    }

    public void setProcdefid(String procdefid) {
        this.procdefid = procdefid;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getFormjson() {
        return formjson;
    }

    public void setFormjson(String formjson) {
        this.formjson = formjson;
    }
}
