package org.springblade.common.constant;

public interface SystemConstant {

    /**
     * 业务资源的编号
     */
    String CODE_APP = "app";
    String CODE_FORM = "form";
    String CODE_FUNC = "func";

    /**
     * 应用和表单资源的默认显示级别
     */
    Integer DEFAULT_RES_LEVEL = 0;

    Integer DATA_AVAILABLE = 0;
    Integer DATA_DELETED = 1;

    String LIST_ADD = "/list/add";
    String LIST_IMPORT = "/list/import";
    String LIST_EXPORT = "/list/export";
    String LIST_DELETE = "/list/delete";
    String LIST_PRINTQR = "/list/printQR";

    String FORM_SAVE = "/form/save";
    String FORM_SAVE_AND_ADD = "/form/save_add";
    String FORM_ADD_TEMP = "/form/add_temp";
    String FORM_PRINT = "/form/print";
    String FORM_DELETE = "/form/delete";

    Integer SORT_LIST_ADD = 1;
    Integer SORT_LIST_IMPORT = 2;
    Integer SORT_LIST_EXPORT = 3;
    Integer SORT_LIST_DELETE = 4;

    Integer SORT_FORM_ADD_TEMP = 5;
    Integer SORT_FORM_SAVE = 6;
    Integer SORT_FORM_SAVE_AND_ADD = 7;
    Integer SORT_LIST_PRINTQR = 8;

    Integer SORT_FORM_PRINT = 9;
    Integer SORT_FORM_DELETE = 10;

    String ICON_LIST_ADD = "";
    String ICON_LIST_IMPORT = "";
    String ICON_LIST_EXPORT = "";
    String ICON_LIST_DELETE = "";

    String ICON_FORM_ADD_TEMP = "";
    String ICON_FORM_SAVE = "";
    String ICON_FORM_SAVE_AND_ADD = "";
    String ICON_LIST_PRINTQR = "";

    String ICON_FORM_PRINT = "";
    String ICON_FORM_DELETE = "";

    String NAME_LIST_ADD = "新增";
    String NAME_LIST_IMPORT = "导入";
    String NAME_LIST_EXPORT = "导出";
    String NAME_LIST_DELETE = "删除";

    String NAME_FORM_ADD_TEMP = "暂存";
    String NAME_FORM_SAVE = "提交";
    String NAME_FORM_SAVE_AND_ADD = "提交并添加";
    String NAME_LIST_PRINTQR = "打印二维码";

    String NAME_FORM_PRINT = "打印";
    String NAME_FORM_DELETE = "删除";

    Integer ADMIN_ROLE_ID = 1;
    Integer DEFAULT_ROLE_ID = 2;

    Long SCENE_ADMIN_ROLE = 1L;
    Long SCENE_DEFAULT_ROLE = 2L;

    Integer ROLE_TYPE_ADMIN = 1;
    Integer ROLE_TYPE_DEFAULT = 2;
    Integer ROLE_TYPE_NORMAL = 3;

    Integer RECORD_ALREADY_EXISTS = -2;
    Integer SYSTEM_ERROR = -1;

    /**
     * 正确变量
     */
    String DELETE_SUCCESS = "记录已删除";
    String ADD_SUCCESS = "保存成功";
    String UPDATE_SUCCESS = "修改成功";
    String GET_SUCCESS = "获取成功";

    /**
     * 错误变量
     */
    String DELETE_FAILURE = "删除失败";
    String ADD_FAILURE = "保存失败";
    String UPDATE_FAILURE = "修改失败";
    String GET_FAILURE = "获取失败";
    String SYSTEM_FAILURE = "系统错误";

    /**
     * 用户变量
     */
    String ADMIN_IDENTITY = "1";
    String REGISTER_SUCCESS = "注册成功";
    String REGISTER_FAILURE = "注册失败";
    String USER_ALREADY_EXISTS = "用户已存在";
    String SCENE_ALREADY_EXISTS = "该场景已存在，请更换名称";
    String SCENE_NOT_FOUND = "未搜索到相关场景";
    String NODE_NOT_FOUND = "未搜索到相关部门";
    String USERS_NOT_FOUND = "该角色下暂无成员";

    String ROLE_NOT_FOUND = "未搜索到相关角色";

    String SCENE_ADMIN = "scene_admin";
    String SCENE_ADMIN_CH = "组织管理员";
    String SCENE_DEFAULT = "scene_default";
    String SCENE_DEFAULT_CH = "终端默认角色";
    String SCENE_NORMAL = "scene_normal";

    String DEFAULT_GROUP_NAME = "默认分组";

    String DEFAULT_PASSWORD = "123456";

    /**
     * 分割符
     */
    public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * 顶级父节点id
     */
    Integer TOP_PARENT_ID = 0;

    Long RESOURCE_TOP_PARENT_ID = 0L;

    /**
     * 顶级父节点名称
     */
    String TOP_PARENT_NAME = "顶级";
}
