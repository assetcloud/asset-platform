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

  //表单模型创建之后的状态
  public static final int FORM_MODEL_UNBIND = 0;   //还没和流程模型绑定
  public static final int FORM_MODEL_BINDED = 1;  //已绑定，表单流程已经可以被发起
  public static final int FORM_MODEL_DELETED = 2;  //表单流程已被删除

  //表单实例的状态
  public static final int FORM_INST_ENABLED = 0;   //默认就是0
  public static final int FORM_INST_DISABLED = 1;  //表单实例因为被回滚或者其他原因不可用

  //返回给前台的标示后台数据读写是否成功
  public static final int CODE_SUCCESS = 1;  //返回给前台的消息 成功
  public static final int CODE_FAILED = 0;  //返回给前台的消息 失败

  //待办页面中，审批节点的处理意见
  public static final int APPROVE_AGREE = 1;   //在审批节点中点击同意
  public static final int APPROVE_DISAGREE = 0;  //在审批节点中点击不同意

  //用户登录页面，点击不同的页面，显示不同类型的任务：待办、待阅、全部
  public static final int TASK_TO_DO = 0;        //待办任务（包含审批节点和经办节点）
  public static final int TASK_TOBE_READ = 1;     //待阅任务
  public static final int TASK_ALL =  2;           //全部任务

  //流程设计中，指示设计的节点的类型
  public static final int AS_NODE_START = 0;            //就是氚云中的开始节点
  public static final int AS_NODE_END = 999;            //结束节点
  public static final int AS_NODE_APPLY = 1;            //经办节点
  public static final int AS_NODE_APPROVE = 2;          //审批节点
  public static final int AS_NODE_CC = 3;               //抄送节点
  public static final int AS_NODE_PARALLEL_start = 4;   //并行节点开始
  public static final int AS_NODE_PARALLEL_end = 5;     //并行节点结束

  public static final int DATABASE_SUCCESS = 1;      //表示对数据库插入数据或者修改数据成功
  public static final int DATABASE_FAILED = 0;      //失败

  //代表int的初始值
  int EMPTY = -1;
}
