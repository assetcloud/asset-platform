package com.asset.entity;

/**
 * 以json格式保存的第三方表单信息，与流程模型绑定
 * @author yby
 * @time 190523 1544
 * @version 1.0_190523 1544
 */
public class JsonForm {
    String formjson;
    String modelid;
    String formname;

    public JsonForm(String formjson, String modelid, String formname) {
        this.formjson = formjson;
        this.modelid = modelid;
        this.formname = formname;
    }

    public String getFormjson() {
        return formjson;
    }

    public void setFormjson(String formjson) {
        this.formjson = formjson;
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

    @Override
    public String toString() {
        return "JsonForm{" +
                "formjson='" + formjson + '\'' +
                ", modelid='" + modelid + '\'' +
                ", formname='" + formname + '\'' +
                '}';
    }
}
