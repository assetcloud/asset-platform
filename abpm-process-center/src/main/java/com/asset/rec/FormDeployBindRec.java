package com.asset.rec;

/**
 * 用于接收前台发起表单模型与流程部署绑定时的Body参数
 * @author YBY
 */
public class FormDeployBindRec {
    String proc_model_id;
    String app_def_key;

    public FormDeployBindRec() {
    }

    public FormDeployBindRec(String proc_model_id, String app_def_key) {
        this.proc_model_id = proc_model_id;
        this.app_def_key = app_def_key;
    }

    public String getProc_model_id() {
        return proc_model_id;
    }

    public void setProc_model_id(String proc_model_id) {
        this.proc_model_id = proc_model_id;
    }

    public String getApp_def_key() {
        return app_def_key;
    }

    public void setApp_def_key(String app_def_key) {
        this.app_def_key = app_def_key;
    }

    @Override
    public String toString() {
        return "FormDeployBindRec{" +
                "proc_model_id='" + proc_model_id + '\'' +
                ", app_def_key='" + app_def_key + '\'' +
                '}';
    }
}




