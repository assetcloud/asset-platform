package org.springblade.system.user.entity;

public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

//    public static RespBean success(String msg){
//        return new RespBean(200, msg, null);
//    }
//
//    public static RespBean fail(String msg){
//        return new RespBean(500, msg, null);
//    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean paramError() {
        return new RespBean(500, "Wrong Parameters", null);
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public static RespBean data(Object obj){
        return new RespBean(200, "操作成功", obj);
    }

    public static RespBean status(boolean flag){
        return flag ? ok("操作成功") : error("操作失败");
    }

    public static RespBean error(Integer status, String msg, Object obj) {
        return new RespBean(status, msg, obj);
    }
}