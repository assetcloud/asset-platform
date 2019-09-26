package cn.org.assetcloud.system.common;

import cn.org.assetcloud.system.entity.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SystemConstant {

    String CODE_APP = "app";
    String CODE_FORM = "form";
    String CODE_FUNC = "func";

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

    Long ADMIN_ROLE_ID = 1L;
    Long DEFAULT_ROLE_ID = 2L;

    Resource RESOURCE_LIST_ADD = new Resource(CODE_FUNC, NAME_LIST_ADD, ICON_LIST_ADD, LIST_ADD ,1 , SORT_LIST_ADD, 3);
    Resource RESOURCE_LIST_IMPORT = new Resource(CODE_FUNC, NAME_LIST_IMPORT, ICON_LIST_IMPORT, LIST_IMPORT ,1 , SORT_LIST_IMPORT, 3);
    Resource RESOURCE_LIST_EXPORT = new Resource(CODE_FUNC, NAME_LIST_EXPORT, ICON_LIST_EXPORT, LIST_EXPORT ,1 , SORT_LIST_EXPORT, 3);
    Resource RESOURCE_LIST_DELETE = new Resource(CODE_FUNC, NAME_LIST_DELETE, ICON_LIST_DELETE, LIST_DELETE ,1 , SORT_LIST_DELETE, 3);

    Resource RESOURCE_FORM_ADD_TEMP = new Resource(CODE_FUNC, NAME_FORM_ADD_TEMP, ICON_FORM_ADD_TEMP, FORM_ADD_TEMP ,2 , SORT_FORM_ADD_TEMP, 3);
    Resource RESOURCE_FORM_SAVE = new Resource(CODE_FUNC, NAME_FORM_SAVE, ICON_FORM_SAVE, FORM_SAVE ,2 , SORT_FORM_SAVE, 3);
    Resource RESOURCE_FORM_SAVE_AND_ADD = new Resource(CODE_FUNC, NAME_FORM_SAVE_AND_ADD, ICON_FORM_SAVE_AND_ADD, FORM_SAVE_AND_ADD ,2 , SORT_FORM_SAVE_AND_ADD, 3);
    Resource RESOURCE_LIST_PRINTQR = new Resource(CODE_FUNC, NAME_LIST_PRINTQR, ICON_LIST_PRINTQR, LIST_PRINTQR ,2 , SORT_LIST_PRINTQR, 3);

    Resource RESOURCE_FORM_PRINT = new Resource(CODE_FUNC, NAME_FORM_PRINT, ICON_FORM_PRINT, FORM_PRINT ,3 , SORT_FORM_PRINT, 3);
    Resource RESOURCE_FORM_DELETE = new Resource(CODE_FUNC, NAME_FORM_DELETE, ICON_FORM_DELETE, FORM_DELETE ,3 , SORT_FORM_DELETE, 3);

    Resource[] RESOURCES = {RESOURCE_LIST_ADD, RESOURCE_LIST_IMPORT, RESOURCE_LIST_EXPORT, RESOURCE_LIST_DELETE,
            RESOURCE_FORM_ADD_TEMP, RESOURCE_FORM_SAVE, RESOURCE_FORM_SAVE_AND_ADD, RESOURCE_LIST_PRINTQR,
            RESOURCE_FORM_PRINT, RESOURCE_FORM_DELETE};

    List<Resource> RESOURCE_LIST = new ArrayList<>(Arrays.asList(RESOURCES));

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

    Long SYSTEM_DEFAULT_USER = (long) 2;

    String DEFAULT_PASSWORD = "000000";

    /**
     * 分割符
     */
    public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");
}
