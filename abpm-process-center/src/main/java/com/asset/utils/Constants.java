package com.asset.utils;

/**
 * @author lichao
 */
public interface Constants {

  /****/
  public static	String SESSION_USER = "admin-session-user";
  /****/
  public static	String NAMESPACE = "http://flowable.org/bpmn";
  /****/
  public static	String NAMESPACE_PREFIX = "flowable";

  public static Integer PageSize =10;

  //应用中分组状态
  public static final int GROUP_ENABLED = 0;
  public static final int GROUP_DISABLED = 1;

  public static final int FORM_MODEL_UNBIND = 0;   //还没和流程模型绑定
  public static final int FORM_MODEL_CREATED = 1;  //表单流程已经可以被发起
  public static final int FORM_MODEL_DELETED = 1;  //表单流程已被删除

  public static final int CODE_SUCCESS = 0;  //返回给前台的消息 成功
  public static final int CODE_FAILED = 1;  //返回给前台的消息 失败

}
