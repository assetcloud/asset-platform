package com.asset.utils;

/**
 * @author lichao
 */
public interface Constants {
    /**
     * ----------------------------------------------------------------李超定义的变量---------------------------------------------------------
     */
    public static String SESSION_USER = "admin-session-user";
    public static String NAMESPACE = "http://flowable.org/bpmn";
    public static String NAMESPACE_PREFIX = "flowable";
    public static Integer PageSize = 10;

    /**
     * ----------------------------------------------------------------模型：应用分组状态---------------------------------------------------------
     */
    //分组状态
    public static final int GROUP_ENABLED = 0;
    public static final int GROUP_DISABLED = 1;
    public static final int GROUP_ALL = -1;
    //应用状态
    public static final int AS_APPLICATION_UNABLE = 0;    //应用 可用
    public static final int AS_APPLICATION_ENABLE = 1;    //应用 不可用
    public static final int AS_APPLICATION_UNPUBLISHED = 0;    //应用 未发布
    public static final int AS_APPLICATION_PUBLISHED = 1;    //应用 已发布
    public static final int APP_PUBLISHED = 1;      //应用被发布出去
    public static final int APP_NOT_PUBLISHED = 0;      //应用没有被发布
    Integer APP_STATUS_ENABLE = 1;


    /**
     * ----------------------------------------------------------------模型：表单模型---------------------------------------------------------
     */
    //表单模型状态
    public static final int FORM_MODEL_ALL = -1;   //表示不对表单模型的状态进行筛选
    public static final int FORM_MODEL_CREATED = 0;   //表单模型刚创建
    public static final int FORM_MODEL_BINDED = 1;  //表单模型绑定流程模型，但此时流程模型信息还没写入数据库，还不能被发起
    public static final int FORM_MODEL_WITH_AUTHORITY = 2;  //表单模型对应的流程模型绑定了表单项权限
    public static final int FORM_MODEL_WITH_NODE = 3;  //表单模型对应的流程模型还往数据库中写了节点数据
    public static final int FORM_MODEL_DELETED = 4;  //表单流程已被删除
    //表单项权限
    public static final int AUTHORITY_INVISIBLE = 1;      //表单项 不可见
    public static final int AUTHORITY_DISABLE = 2;      //表单项 可见
    public static final int AUTHORITY_ENABLE = 3;      //表单项 可写
    public static final int AUTHORITY_REQUIRED = 4;      //表单项 必填


    /**
     * --------------------------------------------------------------------模型：流程模型-----------------------------------------------------------------
     */
    //节点类型
    public static final int AS_NODE_START = 0;            //就是氚云中的开始节点
    public static final int AS_NODE_END = 999;            //结束节点
    public static final int AS_NODE_APPLY = 1;            //经办节点
    public static final int AS_NODE_APPROVE = 2;          //审批节点
    public static final int AS_NODE_CC = 3;               //抄送节点
    public static final int AS_NODE_PARALLEL_start = 4;   //并行节点开始
    public static final int AS_NODE_PARALLEL_end = 5;     //并行节点结束
    //节点属性：是否包含会签功能
    public static final int AS_NODE_JOINT_DISABLE = 0;     //该节点不包含会签功能
    public static final int AS_NODE_JOINT_SERIAL = 1;     //该节点包含 串行会签功能
    public static final int AS_NODE_JOINT_PARRAL = 2;    //该节点包含 并行会签功能


    /**
     * --------------------------------------------------------------------实例-----------------------------------------------------------------
     */
    //表单实例状态
    public static final int FORM_INST_ENABLED = 0;   //表示被创建，等待被执行
    public static final int FORM_INST_EXECUTED = 1;   //被执行了
    public static final int FORM_INST_DELETED = 99;  //表单实例被丢弃（即没被执行了，可能是直接被回滚了）
    public static final int FORM_INST_ROLLED = 999;  //表单实例原来被正确执行了的，但是现在被回滚了
    //流程实例状态
    Integer PROC_INST_ENABLE = 0;  //激活状态
    Integer PROC_INST_SUSPENDED = 1;  //被挂起
    Integer PROC_INST_DELETED = 2;  //被删除
    Integer PROC_INST_FINISHED = 3;   // 已完成
    Integer PROC_INST_REJECTED = 4;   // 被拒绝


    /**
     * --------------------------------------------------------------------任务-----------------------------------------------------------------
     */
    //待办页面中，审批节点的处理意见
    public static final int APPROVE_AGREE = 1;   //在审批节点中点击同意
    public static final int APPROVE_DISAGREE = 0;  //在审批节点中点击不同意
    //用户登录页面，点击不同的页面，显示不同类型的任务：待办、待阅、全部
    public static final int TASK_TO_DO = 0;        //待办任务（包含审批节点和经办节点）
    public static final int TASK_TOBE_READ = 1;     //待阅任务
    public static final int TASK_ALL = 2;           //全部任务

    public static final int COMMIT_TASK = 3;    // 发起任务(nfq)


    /**
     * --------------------------------------------------------------------模板：模板状态-----------------------------------------------------------------
     */
    //模板状态
    public static final int TEMPLET_UNABLE = 0;           //模板 未发布
    public static final int TEMPLET_ENABLE = 1;           //模板 已发布
    public static final int TEMPLET_DELETED = 2;    //模板 被删除


    /**
     * --------------------------------------------------------------------返回前台数据信息-----------------------------------------------------------------
     */
    public static final int DATABASE_SUCCESS = 1;      //表示对数据库插入数据或者修改数据成功
    public static final int DATABASE_FAILED = 0;      //失败


    /**
     * --------------------------------------------------------------------预置流程表单常量-----------------------------------------------------------------
     */
    //注册审批
    String REGISTER_PROC_ID = "register";  //指的是流程模型ID
    String REGISTER_FORM_NAME = "注册审批流程";
    String REGISTER_PROC_NAME = "注册审批流程";
    String REGISTER_BPMN_NAME = "register";
    String REGISTER_PROC_NODE_1 = "task1";
    String REGISTER_PROC_NODE_2 = "task2";
    //场景选择
    String SCENE_SELECT_PROC_ID = "sceneSelect";
    String SCENE_SELECT_FORM_NAME = "工作场景选择流程";
    String SCENE_SELECT_PROC_NAME = "工作场景选择流程";
    String SCENE_SELECT_BPMN_NAME = "sceneSelect";
    String SCENE_SELECT_PROC_NODE_1 = "task1";
    String SCENE_SELECT_PROC_NODE_2 = "task2";
    //流程模型中常量
    String CANDIDATE_GROUP_CUR_SECTION = "CUR_SECTION";  //这一项表示当前申请人的所在部门
    String CANDIDATE_INITIATOR = "INTATOR";  //这一项表示发起人（也就是申请人）


    /**
     * --------------------------------------------------------------------与第三方协同状态码-----------------------------------------------------------------
     */
    //第三方通过业务中台发起的表单流程的运行情况
    public static final int COOP_PROC_FAILED = -1;   //运行出错
    public static final int COOP_PROC_INIT = 0;     //还没开始
    public static final int COOP_PROC_RUN = 1;      //运行中
    public static final int COOP_PROC_COMPLETED = 2;    //审批节点全部同意，运行结束
    public static final int COOP_PROC_COMPLETED_REJECTED = 3;    //实例因为中间审批节点拒绝,运行结束



    /**
     * --------------------------------------------------------------------其他-----------------------------------------------------------------
     */
    String REQUEST_URL_PREFIX = "localhost:9000";
    String USER_ADMIN = "admin";
    String APP_ADMIN = "系统自带应用";
    String DEFAULT_ICON_CLS = "默认图标";
    int EMPTY = -1;  //代表int的初始值

}
