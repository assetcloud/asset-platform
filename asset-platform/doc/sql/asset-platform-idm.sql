/*
 Navicat Premium Data Transfer

 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : asset-platform-idm

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/10/2019 09:38:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for as_api_data_source
-- ----------------------------
DROP TABLE IF EXISTS `as_api_data_source`;
CREATE TABLE `as_api_data_source`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `api_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'api名称',
  `scene_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对应场景',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'api地址',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数集合',
  `api_method` int(2) NOT NULL COMMENT '访问方式：get和post',
  `api_type` int(2) NULL DEFAULT 1 COMMENT 'api类型',
  `api_header` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'api请求头',
  `api_cookie` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cookie',
  `content_type` int(2) NULL DEFAULT 1 COMMENT '请求的与实体对应的MIME信息',
  `add_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `is_deleted` int(2) NOT NULL DEFAULT 0 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_api_data_source
-- ----------------------------
INSERT INTO `as_api_data_source` VALUES ('23950dd694a7c34090301027630f1624', '测试接口1', '1', 'http://120.77.179.239:9000/form_model/models', 'app_id=8dde0934-e8a4-11e9-b8fa-0242ac120006&form_status=0&group_id=-1', 1, 1, '1', NULL, 2, '2019-10-08 14:47:35', 0);

-- ----------------------------
-- Table structure for as_dict
-- ----------------------------
DROP TABLE IF EXISTS `as_dict`;
CREATE TABLE `as_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父主键',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典码',
  `dict_key` int(2) NULL DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_dict
-- ----------------------------
INSERT INTO `as_dict` VALUES (3, 21, 'sex', 2, '女', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (4, 0, 'notice', -1, '通知类型', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (5, 4, 'notice', 1, '发布通知', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (6, 4, 'notice', 2, '批转通知', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (7, 4, 'notice', 3, '转发通知', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (8, 4, 'notice', 4, '指示通知', 4, NULL, 0);
INSERT INTO `as_dict` VALUES (9, 4, 'notice', 5, '任免通知', 5, NULL, 0);
INSERT INTO `as_dict` VALUES (10, 4, 'notice', 6, '事务通知', 6, NULL, 0);
INSERT INTO `as_dict` VALUES (11, 0, 'menu_category', -1, '菜单类型', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (12, 11, 'menu_category', 1, '菜单', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (13, 11, 'menu_category', 2, '按钮', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (14, 0, 'button_func', -1, '按钮功能', 4, NULL, 0);
INSERT INTO `as_dict` VALUES (15, 14, 'button_func', 1, '工具栏', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (16, 14, 'button_func', 2, '操作栏', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (17, 14, 'button_func', 3, '工具操作栏', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (18, 0, 'yes_no', -1, '是否', 5, NULL, 0);
INSERT INTO `as_dict` VALUES (19, 18, 'yes_no', 1, '否', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (20, 18, 'yes_no', 2, '是', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (21, 0, 'sex', -1, '性别', 1, '', 0);
INSERT INTO `as_dict` VALUES (25, 0, 'resource_category', -1, '业务资源类型', 6, '', 0);
INSERT INTO `as_dict` VALUES (26, 25, 'resource_category', 1, '应用', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (27, 25, 'resource_category', 2, '表单', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (28, 25, 'resource_category', 3, '操作', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (29, 0, 'scene_role_type', -1, '业务角色类型', 7, NULL, 0);
INSERT INTO `as_dict` VALUES (30, 29, 'scene_role_type', 1, '系统管理员', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (31, 29, 'scene_role_type', 2, '所有用户', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (32, 29, 'scene_role_type', 3, '自建角色', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (33, 0, 'api_method', -1, 'API访问方式', 8, NULL, 0);
INSERT INTO `as_dict` VALUES (34, 33, 'api_method', 1, 'GET', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (35, 33, 'api_method', 2, 'POST', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (36, 0, 'api_type', -1, 'api类型', 9, NULL, 0);
INSERT INTO `as_dict` VALUES (37, 36, 'api_type', 1, '无', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (38, 36, 'api_type', 2, '组织用户接口', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (39, 36, 'api_type', 3, '用户角色接口', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (40, 36, 'api_type', 4, '用户接口', 4, NULL, 0);
INSERT INTO `as_dict` VALUES (41, 0, 'api_content_type', -1, 'api请求参数类型', 10, NULL, 0);
INSERT INTO `as_dict` VALUES (42, 41, 'api_content_type', 1, 'application/x-www-form-unlencoded', 1, NULL, 0);
INSERT INTO `as_dict` VALUES (43, 41, 'api_content_type', 2, 'application/json', 2, NULL, 0);
INSERT INTO `as_dict` VALUES (44, 41, 'api_content_type', 3, 'multipart/form-data', 3, NULL, 0);
INSERT INTO `as_dict` VALUES (45, 41, 'api_content_type', 4, 'application/xml', 4, NULL, 0);

-- ----------------------------
-- Table structure for as_menu
-- ----------------------------
DROP TABLE IF EXISTS `as_menu`;
CREATE TABLE `as_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级菜单',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单别名',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单资源',
  `sort` int(2) NULL DEFAULT NULL COMMENT '排序',
  `category` int(2) NULL DEFAULT 1 COMMENT '菜单类型',
  `action` int(2) NULL DEFAULT 0 COMMENT '操作按钮类型',
  `is_open` int(2) NULL DEFAULT 1 COMMENT '是否打开新页面',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_menu
-- ----------------------------
INSERT INTO `as_menu` VALUES (1, 0, 'desk', '工作台', 'menu', '/workpanel', 'el-icon-data-board', 1, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (2, 71, 'todo', '待办事务', 'todo', '/toDo', '', 4, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (3, 0, 'system', '系统管理', 'menu', '/system', 'iconfont iconicon_setting', 5, 1, 0, 0, NULL, 1);
INSERT INTO `as_menu` VALUES (4, 4, '', '应用管理', '', '', '', 13, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (5, 3, 'dept', '部门管理', 'menu', '/system/dept', 'iconfont iconicon_group', 2, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (6, 3, 'dict', '字典管理', 'menu', '/system/dict', 'iconfont iconicon_addresslist', 3, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (7, 3, 'menu', '菜单管理', 'menu', '/system/menu', 'iconfont iconicon_subordinate', 4, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (8, 3, 'role', '角色管理', 'menu', '/system/role', 'iconfont iconicon_boss', 5, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (9, 3, 'param', '参数管理', 'menu', '/system/param', 'iconfont iconicon_community_line', 6, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (10, 0, 'monitor', '系统监控', 'menu', '/monitor', 'el-icon-view', 6, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (11, 10, 'doc', '接口文档', 'menu', '/interface', '', 7, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (12, 10, 'admin', '日志管理', 'menu', '/log', '', 2, 1, 0, 0, NULL, 1);
INSERT INTO `as_menu` VALUES (13, 10, '', '日志管理', '', '', '', 8, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (14, 13, 'log_usual', '通用日志', 'menu', '/monitor/log/usual', '', 9, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (15, 13, 'log_api', '接口日志', 'menu', '/monitor/log/api', '', 10, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (16, 13, 'log_error', '错误日志', 'menu', '/monitor/log/error', '', 11, 1, 0, 0, NULL, 0);
INSERT INTO `as_menu` VALUES (17, 0, 'shiwu', '事务管理', 'menu', '/thing', 'el-icon-menu', 2, 1, 0, 0, NULL, 1);
INSERT INTO `as_menu` VALUES (18, 17, 'code', '代码生成', 'menu', '/tool/code', 'iconfont iconicon_savememo', 1, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (19, 2, 'notice_add', '新增', 'add', '/desk/notice/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (20, 2, 'notice_edit', '修改', 'edit', '/desk/notice/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (21, 2, 'notice_delete', '删除', 'delete', '/api/blade-desk/notice/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (22, 2, 'notice_view', '查看', 'view', '/desk/notice/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (23, 4, 'user_add', '新增', 'add', '/system/user/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (24, 4, 'user_edit', '修改', 'edit', '/system/user/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (25, 4, 'user_delete', '删除', 'delete', '/api/blade-user/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (26, 4, 'user_role', '角色配置', 'role', NULL, 'user-add', 4, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (27, 4, 'user_reset', '密码重置', 'reset-password', '/api/blade-user/reset-password', 'retweet', 5, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (28, 4, 'user_view', '查看', 'view', '/system/user/view', 'file-text', 6, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (29, 5, 'dept_add', '新增', 'add', '/system/dept/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (30, 5, 'dept_edit', '修改', 'edit', '/system/dept/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (31, 5, 'dept_delete', '删除', 'delete', '/api/blade-system/dept/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (32, 5, 'dept_view', '查看', 'view', '/system/dept/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (33, 6, 'dict_add', '新增', 'add', '/system/dict/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (34, 6, 'dict_edit', '修改', 'edit', '/system/dict/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (35, 6, 'dict_delete', '删除', 'delete', '/api/blade-system/dict/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (36, 6, 'dict_view', '查看', 'view', '/system/dict/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (37, 7, 'menu_add', '新增', 'add', '/system/menu/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (38, 7, 'menu_edit', '修改', 'edit', '/system/menu/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (39, 7, 'menu_delete', '删除', 'delete', '/api/blade-system/menu/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (40, 7, 'menu_view', '查看', 'view', '/system/menu/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (41, 8, 'role_add', '新增', 'add', '/system/role/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (42, 8, 'role_edit', '修改', 'edit', '/system/role/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (43, 8, 'role_delete', '删除', 'delete', '/api/blade-system/role/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (44, 8, 'role_view', '查看', 'view', '/system/role/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (45, 9, 'param_add', '新增', 'add', '/system/param/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (46, 9, 'param_edit', '修改', 'edit', '/system/param/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (47, 9, 'param_delete', '删除', 'delete', '/api/blade-system/param/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (48, 9, 'param_view', '查看', 'view', '/system/param/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (49, 14, 'log_usual_view', '查看', 'view', '/monitor/log/usual/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (50, 15, 'log_api_view', '查看', 'view', '/monitor/log/api/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (51, 16, 'log_error_view', '查看', 'view', '/monitor/log/error/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (52, 18, 'code_add', '新增', 'add', '/tool/code/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (53, 18, 'code_edit', '修改', 'edit', '/tool/code/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (54, 18, 'code_delete', '删除', 'delete', '/api/blade-system/code/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (55, 18, 'code_view', '查看', 'view', '/tool/code/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (56, 3, 'tenant', '租户管理', 'menu', '/system/tenant', 'iconfont icon-quanxian', 7, 1, 0, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (57, 56, 'tenant_add', '新增', 'add', '/system/tenant/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (58, 56, 'tenant_edit', '修改', 'edit', '/system/tenant/edit', 'form', 2, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (59, 56, 'tenant_delete', '删除', 'delete', '/api/blade-system/tenant/remove', 'delete', 3, 2, 3, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (60, 56, 'tenant_view', '查看', 'view', '/system/tenant/view', 'file-text', 4, 2, 2, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (61, 3, 'client', '应用管理', 'menu', '/system/client', 'iconfont iconicon_mobilephone', 8, 1, 0, 0, NULL, 1);
INSERT INTO `as_menu` VALUES (62, 61, 'client_add', '新增', 'add', '/system/client/add', 'plus', 1, 2, 1, 1, NULL, 1);
INSERT INTO `as_menu` VALUES (63, 61, 'client_edit', '修改', 'edit', '/system/client/edit', 'form', 2, 2, 2, 2, NULL, 1);
INSERT INTO `as_menu` VALUES (64, 61, 'client_delete', '删除', 'delete', '/api/blade-system/client/remove', 'delete', 3, 2, 3, 3, NULL, 1);
INSERT INTO `as_menu` VALUES (65, 61, 'client_view', '查看', 'view', '/system/client/view', 'file-text', 4, 2, 2, 2, NULL, 1);
INSERT INTO `as_menu` VALUES (66, 1, '', 'test', '111', '', '', 0, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (67, 1, 'testxu', 'test', 'testxu', '/test', 'el-icon-edit', 2, 1, 0, 0, NULL, 1);
INSERT INTO `as_menu` VALUES (68, 1, '', 'test', '', '', '', 0, 0, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (69, 3, '', 'test2', '', '', '', 0, 0, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (70, 1, 'notice', '通知公告', 'notice', '/notice', '', 2, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (71, 0, 'done', '办结事务', 'done', '/done', '', 5, 0, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (72, 0, 'resource', '业务资源管理', 'resource', '/resource', 'el-icon-connection', 12, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (73, 72, '', '待办事务', '', '', '', 4, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (74, 72, '', '办结事务', '', '', '', 5, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (75, 0, 'system', '系统管理', 'system', '/system', 'el-icon-setting', 22, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (76, 76, 'todone', '待办事务', 'todone', '/todone', '', 4, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (77, 75, 'organization', '组织机构', 'organization', '/organization', '', 24, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (78, 0, 'office', '事务管理', 'office', '/officeManagement', 'el-icon-menu', 3, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (79, 78, 'todone', '待办事务', 'todone', '/todone', '', 4, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (80, 78, 'done', '办结事务', 'done', '/done', '', 5, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (81, 0, 'app', '应用管理', 'app', '/appManagement', '', 13, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (82, 72, 'app', '应用管理', 'app', '/appManagement', '', 13, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (83, 72, 'form', '表单管理', 'form', '/formManagement', '', 14, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (84, 72, 'flow', '流程管理', 'flow', '/flowManagement', '', 15, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (85, 84, 'model', '流程监控', 'model', '/formManagement/control', '', 19, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (86, 86, 'a', '测试菜单', 'a', 'a', 'el-icon-share', 1, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (87, 75, '1', '用户管理', '1', '/', '', 23, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (88, 75, 'x', '场景管理', 'x', '/scene', '', 25, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (89, 87, 'sw', 'test', 's', '/sd', '', 0, 1, 0, 0, '', 1);
INSERT INTO `as_menu` VALUES (90, 72, 'asdf', '业务角色管理', 'adsf', '/role', '', 21, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (91, 84, 'model', '模型管理', 'model', '/model', '', 16, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (92, 84, 'flow', '流程部署', 'flow', '/flow', '', 17, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (93, 84, 'example', '实例监控', 'example', '/example', '', 18, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (94, 72, 'form', '列表管理', 'form', '/form', '', 20, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (95, 75, 'role', '平台角色管理', 'role', '/plarformrole', '', 26, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (96, 75, 'dictionary', '字典管理', 'dictionary', '/dictionary', '', 27, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (97, 75, 'menu', '菜单管理', 'menu', 'menu', '', 28, 1, 0, 0, '', 0);
INSERT INTO `as_menu` VALUES (98, 0, '4', '4', '4', '4', '', 2, 1, 0, 0, '', 1);

-- ----------------------------
-- Table structure for as_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `as_menu_role`;
CREATE TABLE `as_menu_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单编号',
  `role_id` int(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 230 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_menu_role
-- ----------------------------
INSERT INTO `as_menu_role` VALUES (1, 1, 1);
INSERT INTO `as_menu_role` VALUES (2, 2, 1);
INSERT INTO `as_menu_role` VALUES (3, 3, 1);
INSERT INTO `as_menu_role` VALUES (4, 4, 1);
INSERT INTO `as_menu_role` VALUES (5, 5, 1);
INSERT INTO `as_menu_role` VALUES (6, 6, 1);
INSERT INTO `as_menu_role` VALUES (7, 7, 1);
INSERT INTO `as_menu_role` VALUES (8, 8, 1);
INSERT INTO `as_menu_role` VALUES (9, 9, 1);
INSERT INTO `as_menu_role` VALUES (10, 10, 1);
INSERT INTO `as_menu_role` VALUES (11, 11, 1);
INSERT INTO `as_menu_role` VALUES (12, 12, 1);
INSERT INTO `as_menu_role` VALUES (13, 13, 1);
INSERT INTO `as_menu_role` VALUES (14, 14, 1);
INSERT INTO `as_menu_role` VALUES (15, 15, 1);
INSERT INTO `as_menu_role` VALUES (16, 16, 1);
INSERT INTO `as_menu_role` VALUES (17, 17, 1);
INSERT INTO `as_menu_role` VALUES (18, 18, 1);
INSERT INTO `as_menu_role` VALUES (19, 19, 2);
INSERT INTO `as_menu_role` VALUES (85, 1, 11);
INSERT INTO `as_menu_role` VALUES (86, 3, 11);
INSERT INTO `as_menu_role` VALUES (87, 4, 11);
INSERT INTO `as_menu_role` VALUES (88, 5, 11);
INSERT INTO `as_menu_role` VALUES (89, 6, 11);
INSERT INTO `as_menu_role` VALUES (90, 1, 12);
INSERT INTO `as_menu_role` VALUES (91, 3, 12);
INSERT INTO `as_menu_role` VALUES (92, 4, 12);
INSERT INTO `as_menu_role` VALUES (93, 5, 12);
INSERT INTO `as_menu_role` VALUES (94, 6, 12);
INSERT INTO `as_menu_role` VALUES (97, 1, 15);
INSERT INTO `as_menu_role` VALUES (98, 1, 16);
INSERT INTO `as_menu_role` VALUES (99, 2, 16);
INSERT INTO `as_menu_role` VALUES (100, 3, 16);
INSERT INTO `as_menu_role` VALUES (101, 4, 16);
INSERT INTO `as_menu_role` VALUES (102, 5, 16);
INSERT INTO `as_menu_role` VALUES (103, 6, 16);
INSERT INTO `as_menu_role` VALUES (104, 7, 16);
INSERT INTO `as_menu_role` VALUES (105, 8, 16);
INSERT INTO `as_menu_role` VALUES (106, 9, 16);
INSERT INTO `as_menu_role` VALUES (107, 10, 16);
INSERT INTO `as_menu_role` VALUES (108, 11, 16);
INSERT INTO `as_menu_role` VALUES (109, 12, 16);
INSERT INTO `as_menu_role` VALUES (110, 13, 16);
INSERT INTO `as_menu_role` VALUES (111, 14, 16);
INSERT INTO `as_menu_role` VALUES (112, 15, 16);
INSERT INTO `as_menu_role` VALUES (113, 16, 16);
INSERT INTO `as_menu_role` VALUES (114, 17, 16);
INSERT INTO `as_menu_role` VALUES (115, 18, 16);
INSERT INTO `as_menu_role` VALUES (116, 19, 16);
INSERT INTO `as_menu_role` VALUES (117, 20, 16);
INSERT INTO `as_menu_role` VALUES (118, 21, 16);
INSERT INTO `as_menu_role` VALUES (119, 22, 16);
INSERT INTO `as_menu_role` VALUES (120, 23, 16);
INSERT INTO `as_menu_role` VALUES (121, 24, 16);
INSERT INTO `as_menu_role` VALUES (122, 25, 16);
INSERT INTO `as_menu_role` VALUES (123, 26, 16);
INSERT INTO `as_menu_role` VALUES (124, 27, 16);
INSERT INTO `as_menu_role` VALUES (125, 28, 16);
INSERT INTO `as_menu_role` VALUES (126, 29, 16);
INSERT INTO `as_menu_role` VALUES (127, 30, 16);
INSERT INTO `as_menu_role` VALUES (128, 31, 16);
INSERT INTO `as_menu_role` VALUES (129, 32, 16);
INSERT INTO `as_menu_role` VALUES (130, 33, 16);
INSERT INTO `as_menu_role` VALUES (131, 34, 16);
INSERT INTO `as_menu_role` VALUES (132, 35, 16);
INSERT INTO `as_menu_role` VALUES (133, 36, 16);
INSERT INTO `as_menu_role` VALUES (134, 37, 16);
INSERT INTO `as_menu_role` VALUES (135, 38, 16);
INSERT INTO `as_menu_role` VALUES (136, 39, 16);
INSERT INTO `as_menu_role` VALUES (137, 40, 16);
INSERT INTO `as_menu_role` VALUES (138, 41, 16);
INSERT INTO `as_menu_role` VALUES (139, 42, 16);
INSERT INTO `as_menu_role` VALUES (140, 43, 16);
INSERT INTO `as_menu_role` VALUES (141, 44, 16);
INSERT INTO `as_menu_role` VALUES (142, 45, 16);
INSERT INTO `as_menu_role` VALUES (143, 46, 16);
INSERT INTO `as_menu_role` VALUES (144, 47, 16);
INSERT INTO `as_menu_role` VALUES (145, 48, 16);
INSERT INTO `as_menu_role` VALUES (146, 49, 16);
INSERT INTO `as_menu_role` VALUES (147, 50, 16);
INSERT INTO `as_menu_role` VALUES (148, 51, 16);
INSERT INTO `as_menu_role` VALUES (149, 52, 16);
INSERT INTO `as_menu_role` VALUES (150, 53, 16);
INSERT INTO `as_menu_role` VALUES (151, 54, 16);
INSERT INTO `as_menu_role` VALUES (152, 55, 16);
INSERT INTO `as_menu_role` VALUES (153, 56, 16);
INSERT INTO `as_menu_role` VALUES (154, 57, 16);
INSERT INTO `as_menu_role` VALUES (155, 58, 16);
INSERT INTO `as_menu_role` VALUES (156, 59, 16);
INSERT INTO `as_menu_role` VALUES (157, 60, 16);
INSERT INTO `as_menu_role` VALUES (159, 62, 16);
INSERT INTO `as_menu_role` VALUES (160, 63, 16);
INSERT INTO `as_menu_role` VALUES (161, 64, 16);
INSERT INTO `as_menu_role` VALUES (162, 65, 16);
INSERT INTO `as_menu_role` VALUES (163, 1, 17);
INSERT INTO `as_menu_role` VALUES (164, 2, 17);
INSERT INTO `as_menu_role` VALUES (165, 3, 17);
INSERT INTO `as_menu_role` VALUES (166, 4, 17);
INSERT INTO `as_menu_role` VALUES (167, 5, 17);
INSERT INTO `as_menu_role` VALUES (168, 6, 17);
INSERT INTO `as_menu_role` VALUES (169, 7, 17);
INSERT INTO `as_menu_role` VALUES (170, 8, 17);
INSERT INTO `as_menu_role` VALUES (171, 9, 17);
INSERT INTO `as_menu_role` VALUES (172, 10, 17);
INSERT INTO `as_menu_role` VALUES (173, 11, 17);
INSERT INTO `as_menu_role` VALUES (174, 12, 17);
INSERT INTO `as_menu_role` VALUES (175, 13, 17);
INSERT INTO `as_menu_role` VALUES (176, 14, 17);
INSERT INTO `as_menu_role` VALUES (177, 15, 17);
INSERT INTO `as_menu_role` VALUES (178, 16, 17);
INSERT INTO `as_menu_role` VALUES (179, 17, 17);
INSERT INTO `as_menu_role` VALUES (180, 18, 17);
INSERT INTO `as_menu_role` VALUES (181, 19, 17);
INSERT INTO `as_menu_role` VALUES (182, 20, 17);
INSERT INTO `as_menu_role` VALUES (183, 21, 17);
INSERT INTO `as_menu_role` VALUES (184, 22, 17);
INSERT INTO `as_menu_role` VALUES (185, 23, 17);
INSERT INTO `as_menu_role` VALUES (186, 24, 17);
INSERT INTO `as_menu_role` VALUES (187, 25, 17);
INSERT INTO `as_menu_role` VALUES (188, 26, 17);
INSERT INTO `as_menu_role` VALUES (189, 27, 17);
INSERT INTO `as_menu_role` VALUES (190, 28, 17);
INSERT INTO `as_menu_role` VALUES (191, 29, 17);
INSERT INTO `as_menu_role` VALUES (192, 30, 17);
INSERT INTO `as_menu_role` VALUES (193, 31, 17);
INSERT INTO `as_menu_role` VALUES (194, 32, 17);
INSERT INTO `as_menu_role` VALUES (195, 33, 17);
INSERT INTO `as_menu_role` VALUES (196, 34, 17);
INSERT INTO `as_menu_role` VALUES (197, 35, 17);
INSERT INTO `as_menu_role` VALUES (198, 36, 17);
INSERT INTO `as_menu_role` VALUES (199, 37, 17);
INSERT INTO `as_menu_role` VALUES (200, 38, 17);
INSERT INTO `as_menu_role` VALUES (201, 39, 17);
INSERT INTO `as_menu_role` VALUES (202, 40, 17);
INSERT INTO `as_menu_role` VALUES (203, 41, 17);
INSERT INTO `as_menu_role` VALUES (204, 42, 17);
INSERT INTO `as_menu_role` VALUES (205, 43, 17);
INSERT INTO `as_menu_role` VALUES (206, 44, 17);
INSERT INTO `as_menu_role` VALUES (207, 45, 17);
INSERT INTO `as_menu_role` VALUES (208, 46, 17);
INSERT INTO `as_menu_role` VALUES (209, 47, 17);
INSERT INTO `as_menu_role` VALUES (210, 48, 17);
INSERT INTO `as_menu_role` VALUES (211, 49, 17);
INSERT INTO `as_menu_role` VALUES (212, 50, 17);
INSERT INTO `as_menu_role` VALUES (213, 51, 17);
INSERT INTO `as_menu_role` VALUES (214, 52, 17);
INSERT INTO `as_menu_role` VALUES (215, 53, 17);
INSERT INTO `as_menu_role` VALUES (216, 54, 17);
INSERT INTO `as_menu_role` VALUES (217, 55, 17);
INSERT INTO `as_menu_role` VALUES (218, 56, 17);
INSERT INTO `as_menu_role` VALUES (219, 57, 17);
INSERT INTO `as_menu_role` VALUES (220, 58, 17);
INSERT INTO `as_menu_role` VALUES (221, 59, 17);
INSERT INTO `as_menu_role` VALUES (222, 60, 17);
INSERT INTO `as_menu_role` VALUES (224, 62, 17);
INSERT INTO `as_menu_role` VALUES (225, 63, 17);
INSERT INTO `as_menu_role` VALUES (228, 64, 17);
INSERT INTO `as_menu_role` VALUES (229, 65, 17);

-- ----------------------------
-- Table structure for as_organ_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_organ_scene`;
CREATE TABLE `as_organ_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `scene_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作场景id',
  `status` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1864 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_organ_scene
-- ----------------------------
INSERT INTO `as_organ_scene` VALUES (1, '1', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (2, 'b1789bb8-9293-11e9-ae19-005056c00001', '1', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (3, '6c7758a6-9353-11e9-8653-005056c00001', 'b1789bb8-9293-11e9-ae19-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (4, '00e86a20-9357-11e9-846c-005056c00001', '6c7758a6-9353-11e9-8653-005056c00001', '浙江省财政厅资产处人事部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (5, 'top', '', '顶级节点', '582d71e6-a77b-11e9-88fa-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (6, 'top', '', '顶级节点', '4075767c-a79b-11e9-a6df-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (7, 'top', '', '顶级节点', '28e48620-a79d-11e9-a4b2-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (8, 'top', '', '顶级节点', '82e33b75-aa0c-11e9-89ff-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (9, 'top', '', '顶级节点', '01ae7d34-aa0d-11e9-9d8b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (10, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (13, 'top', '', '顶级节点', '31cc5795-aaa2-11e9-aeaf-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (14, 'top', '', '顶级节点', 'fe358293-aaab-11e9-9589-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (15, 'top', '', '顶级节点', '0d1e3c54-aaad-11e9-89d6-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (16, 'top', '', '顶级节点', '81d27c21-aaad-11e9-afaa-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (17, 'top', '', '顶级节点', 'e516ee69-aaad-11e9-9ea9-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (18, 'top', '', '顶级节点', 'f1907b8a-aaae-11e9-a974-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (19, 'top', '', '顶级节点', '0bc633ef-aab2-11e9-80fb-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (20, 'top', '', '顶级节点', 'fa9ecdfe-aab3-11e9-93a9-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (21, 'top', '', '顶级节点', '63b9d993-aab4-11e9-95fb-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (22, 'top', '', '顶级节点', 'daeb69f7-ac21-11e9-893f-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (23, 'top', '', '顶级节点', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (24, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (28, 'top', '', '顶级节点', 'f34a76a6-b1d3-11e9-a676-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (29, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', 'f34a76a6-b1d3-11e9-a676-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (30, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'f34a76a6-b1d3-11e9-a676-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (31, 'top', '', '顶级节点', '96f750e5-b1dd-11e9-80df-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (32, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', '96f750e5-b1dd-11e9-80df-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (33, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '96f750e5-b1dd-11e9-80df-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (34, 'top', '', '顶级节点', '5a9d7930-b278-11e9-bc73-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (35, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '5a9d7930-b278-11e9-bc73-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (36, 'top', '', '顶级节点', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (37, '0a6bf7cb1abd4807a0a62a204ff3f30f', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '123', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (38, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (39, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (40, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (41, 'fd6befc4afa249fe8720f067da1e0420', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '海', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (42, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (43, 'top', '', '顶级节点', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (44, 'top', '', '顶级节点', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (45, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (46, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (47, 'eda002cccfbe46b0b4a0a5b2db05c202', 'top', '测试节点', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (48, 'top', '', '顶级节点', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (49, 'top', '', '顶级节点', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (50, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (51, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (52, 'e86bf5190ffc4f92932cfb0ad9869954', 'top', '测试节点1', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (53, 'top', '', '顶级节点', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (54, 'top', '', '顶级节点', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (55, 'top', '', '顶级节点', 'aa796ec2-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (56, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (57, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (58, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (59, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (60, 'd270ea1b736b42388b1569ded3741d10', 'top', '叶柄跃', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (61, 'e86bf5190ffc4f92932cfb0ad9869954', 'top', '测试节点1', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (62, 'fd6befc4afa249fe8720f067da1e0420', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '海', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (63, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (64, 'top', '', '顶级节点', 'a735b571-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (65, 'top', '', '顶级节点', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (66, 'top', '', '顶级节点', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (67, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (68, 'top', '', '顶级节点', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (69, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (70, '0a6bf7cb1abd4807a0a62a204ff3f30f', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '123', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (71, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (72, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (73, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (74, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (75, 'fd6befc4afa249fe8720f067da1e0420', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '海', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (76, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (77, 'top', '', '顶级节点', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (78, 'eda002cccfbe46b0b4a0a5b2db05c202', 'top', '测试节点', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (79, 'top', '', '顶级节点', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (80, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (81, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (82, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (83, '0a6bf7cb1abd4807a0a62a204ff3f30f', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '123', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (84, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (85, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (86, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (87, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (88, 'top', '', '顶级节点', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (89, '074389607b39cfa677bb74eb9721450b', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (90, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (91, '4a407736f58fdd8a69b84c98431aa0c2', '743ccc5fb94314d08490c4662b16753a', '校宣传办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (92, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (93, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (94, '57bd3af15653a6f7bdf46debf036826b', '743ccc5fb94314d08490c4662b16753a', '机械与自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (95, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (96, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (97, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (98, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (99, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (123, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (124, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (125, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (126, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (127, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (128, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (129, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (130, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (131, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (132, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (133, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (134, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (135, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (136, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (137, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (138, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (139, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (140, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (141, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (142, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (143, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (145, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (147, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (148, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (149, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (150, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (151, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (152, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (153, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (154, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (190, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (191, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (192, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (193, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (194, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (195, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (196, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (197, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (198, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (199, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (200, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (201, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (202, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (203, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (204, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (205, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (206, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (207, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (208, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (209, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (210, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (211, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (212, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (213, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (214, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (215, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (216, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (217, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (218, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (219, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (220, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (221, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (222, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (224, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (225, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (226, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (227, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (228, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (229, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (230, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (231, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (232, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (233, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (234, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (235, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (236, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (237, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (238, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (239, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (240, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (241, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (242, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (243, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (244, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (245, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (246, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (247, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (248, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (249, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (250, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (251, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (252, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (253, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (254, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (255, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (256, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (257, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (258, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (259, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (260, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (261, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (262, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (263, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (264, 'top', '', '顶级节点', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (265, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (266, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (267, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (268, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (269, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (270, 'top', '', '顶级节点', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (271, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (272, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (273, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (274, 'top', '', '顶级节点', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (275, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (276, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (277, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (278, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (279, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (280, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (281, 'top', '', '顶级节点', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (282, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (283, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (284, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (285, 'top', '', '顶级节点', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (286, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (287, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (288, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (289, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (290, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (291, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (292, 'top', '', '顶级节点', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (293, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (294, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (295, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (296, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (297, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (298, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (299, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (300, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (301, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (302, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (303, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (304, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (305, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (306, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (307, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (308, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (309, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (310, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 0);
INSERT INTO `as_organ_scene` VALUES (311, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (312, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (313, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (314, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (315, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (316, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (317, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (318, 'top', '', '顶级节点', 'c0995055-b435-11e9-bebb-0e0b7ec70eea', 1);
INSERT INTO `as_organ_scene` VALUES (319, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (320, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (321, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (322, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (323, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (324, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (325, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (326, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (327, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (328, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (329, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (330, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (331, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (332, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (333, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (334, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (335, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (336, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (337, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (338, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (339, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (340, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (341, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (342, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (343, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (344, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (345, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (346, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (347, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (348, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (349, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (350, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (351, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (352, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (353, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (354, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (355, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (356, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (357, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (358, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (359, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (360, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (361, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (362, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (363, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (364, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (365, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (366, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (367, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (368, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (369, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (370, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (371, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (372, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (373, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (374, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (375, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (376, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (377, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (378, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (379, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (380, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (381, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (382, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (383, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (384, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (385, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (386, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (387, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (388, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (389, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (390, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (391, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (392, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (393, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (394, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (395, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (396, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (397, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (398, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (399, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (400, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (401, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (402, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (403, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (404, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (405, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (406, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (407, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (408, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (409, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (410, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (411, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (412, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (413, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (414, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (415, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (416, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (417, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (418, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (419, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (420, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (421, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (422, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (423, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (424, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (425, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (426, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (427, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (428, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (429, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (430, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (431, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (432, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (433, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (434, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (435, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (436, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (437, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (438, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (439, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (440, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (441, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (442, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (443, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (444, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (445, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (446, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (447, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (448, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (449, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (450, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (451, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (452, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (453, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (454, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (455, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (456, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (457, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (458, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (459, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (460, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (461, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (462, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (463, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (464, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (465, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (466, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (467, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (468, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (469, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (470, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (471, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (472, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (473, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (474, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (475, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (476, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (477, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (478, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (479, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (480, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (481, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (482, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (483, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (484, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (485, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (486, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (487, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (488, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (489, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (490, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (491, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (492, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (493, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (494, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (495, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (496, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (497, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (498, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (499, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (500, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (501, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (502, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (503, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (504, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (505, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (506, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (507, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (508, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (509, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (510, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (511, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (512, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (513, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (514, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (515, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (516, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (517, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (518, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (519, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (520, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (521, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (522, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (523, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (524, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (525, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (526, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (527, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (528, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (529, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (530, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (531, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (532, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (533, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (534, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (535, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (536, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (537, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (538, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (539, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (540, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (541, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (542, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (543, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (544, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (545, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (546, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (547, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (548, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (549, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (550, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (551, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (552, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (553, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (554, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (555, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (556, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (557, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (558, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (559, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (560, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (561, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (562, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (563, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (564, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (565, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (566, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (567, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (568, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (569, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (570, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (571, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (572, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (573, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (574, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (575, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (576, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (577, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (578, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (579, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (580, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (581, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (582, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (583, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (584, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (585, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (586, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (587, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (588, 'top', '', '顶级节点', 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (589, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (590, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (591, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (592, 'top', '', '顶级节点', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (593, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (594, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (595, 'top', '', '顶级节点', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (596, '14861e7d-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省商务厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (597, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (598, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (599, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (600, 'top', '', '顶级节点', '89371977-b1b4-11e9-8d9b-005056c00001', 0);
INSERT INTO `as_organ_scene` VALUES (601, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '89371977-b1b4-11e9-8d9b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (602, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '89371977-b1b4-11e9-8d9b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (603, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '89371977-b1b4-11e9-8d9b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (604, 'top', '', '顶级节点', '89371977-b1b4-11e9-8d9b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (605, 'top', '', '顶级节点', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (606, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (607, '5c510c1eb9545d9395dd13bfcb9784f9', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '测试', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (608, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (609, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (610, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (611, 'top', '', '顶级节点', 'c2ddb6e9-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (612, 'top', '', '顶级节点', 'e048b09a-d83f-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (613, 'top', '', '顶级节点', '0a10cbbb-d840-11e9-acac-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (614, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (615, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (616, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (617, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (618, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (619, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (620, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (621, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (622, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (623, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (624, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (625, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (626, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (627, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (628, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (629, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (630, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (631, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (632, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (633, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (634, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (635, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (636, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (637, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (638, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (639, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (640, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (641, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (642, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (643, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (644, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (645, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (646, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (647, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (648, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (649, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (650, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (651, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (652, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (653, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (654, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (655, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (656, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (657, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (658, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (659, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (660, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (661, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (662, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (663, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (664, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (665, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (666, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (667, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (668, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (669, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (670, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (671, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (672, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (673, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (674, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (675, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (676, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (677, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (678, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (679, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (680, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (681, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (682, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (683, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (684, 'top', '', '顶级节点', '0a10cbbb-d840-11e9-acac-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (685, 'top', '', '顶级节点', '44edf3bd-d84c-11e9-8ada-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (686, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '44edf3bd-d84c-11e9-8ada-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (687, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '44edf3bd-d84c-11e9-8ada-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (688, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '44edf3bd-d84c-11e9-8ada-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (689, 'top', '', '顶级节点', '44edf3bd-d84c-11e9-8ada-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (690, 'top', '', '顶级节点', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (691, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (692, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (693, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (694, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (695, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (696, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (697, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (698, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (699, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (700, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (701, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (702, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (703, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (704, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (705, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (706, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (707, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (708, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (709, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (710, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (711, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (712, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (713, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (714, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (715, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (716, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (717, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (718, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (719, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (720, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (721, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (722, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (723, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (724, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (725, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (726, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (727, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (728, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (729, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (730, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (731, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (732, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (733, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (734, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (735, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (736, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (737, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (738, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (739, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (740, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (741, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (742, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (743, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (744, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (745, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (746, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (747, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (748, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (749, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (750, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (751, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (752, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (753, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (754, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (755, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (756, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (757, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (758, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (759, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (760, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (761, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (762, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (763, 'top', '', '顶级节点', '919fd4a1-d9cb-11e9-b749-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (764, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (765, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (766, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (767, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (768, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (769, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (770, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (771, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (772, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (773, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (774, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (775, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (776, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (777, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (778, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (779, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (780, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (781, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (782, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (783, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (784, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (785, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (786, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (787, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (788, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (789, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (790, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (791, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (792, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (793, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (794, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (795, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (796, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (797, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (798, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (799, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (800, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (801, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (802, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (803, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (804, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (805, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (806, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (807, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (808, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (809, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (810, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (811, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (812, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (813, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (814, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (815, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (816, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (817, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (818, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (819, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (820, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (821, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (822, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (823, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (824, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (825, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (826, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (827, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (828, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (829, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (830, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (831, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (832, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (833, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (834, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (835, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (836, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (837, 'top', '', '顶级节点', '919fd4a1-d9cb-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (838, 'top', '', '顶级节点', 'ffaaa5f2-d9cc-11e9-b749-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (839, 'top', '', '顶级节点', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (840, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (841, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (842, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (843, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (844, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (845, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (846, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (847, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (848, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (849, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (850, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (851, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (852, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (853, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (854, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (855, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (856, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (857, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (858, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (859, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (860, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (861, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (862, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (863, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (864, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (865, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (866, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (867, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (868, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (869, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (870, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (871, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (872, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (873, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (874, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (875, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (876, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (877, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (878, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (879, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (880, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (881, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (882, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (883, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (884, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (885, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (886, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (887, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (888, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (889, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (890, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (891, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (892, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (893, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (894, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (895, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (896, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (897, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (898, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (899, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (900, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (901, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (902, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (903, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (904, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (905, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (906, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (907, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (908, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (909, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (910, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (911, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (912, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (913, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (914, 'top', '', '顶级节点', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (915, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (916, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (917, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (918, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (919, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (920, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (921, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (922, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (923, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (924, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (925, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (926, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (927, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (928, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (929, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (930, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (931, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (932, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (933, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (934, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (935, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (936, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (937, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (938, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (939, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (940, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (941, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (942, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (943, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (944, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (945, '5c510c1eb9545d9395dd13bfcb9784f9', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '测试', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (946, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (947, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (948, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (949, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (950, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (951, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (952, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (953, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (954, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (955, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (956, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (957, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (958, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (959, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (960, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (961, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (962, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (963, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (964, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (965, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (966, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (967, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (968, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (969, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (970, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (971, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (972, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (973, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (974, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (975, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (976, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (977, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (978, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (979, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (980, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (981, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (982, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (983, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (984, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (985, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (986, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (987, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (988, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (989, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (990, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (991, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (992, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (993, 'top', '', '顶级节点', '81cf08e2-d9ce-11e9-b832-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (994, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (995, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (996, '09b3bbbc-9a6c-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (997, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (998, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (999, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1000, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1001, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1002, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1003, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1004, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1005, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1006, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1007, '2185b032dfc752b058c79543f93f571f', '53dd6b7ec8be2d906c23cd90defafa18', '浙江大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1008, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1009, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1010, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1011, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1012, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1013, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1014, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1015, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1016, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1017, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1018, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1019, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1020, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1021, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1022, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1023, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1024, '5c510c1eb9545d9395dd13bfcb9784f9', 'be6ef9d0-9a6c-11e9-a169-005056c00001', '测试', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1025, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1026, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1027, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1028, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1029, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1030, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1031, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1032, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1033, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1034, '79d20426e9f6798ce6b315f58a24906e', 'd878ec6f942769e1a1cab9d0ef5e4e5c', '浙江财经大学计算机学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1035, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1036, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1037, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1038, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1039, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1040, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1041, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1042, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1043, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1044, 'a0188021b0da17763cbf54738c6f91c3', '53dd6b7ec8be2d906c23cd90defafa18', '中国美术学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1045, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1046, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1047, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1048, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1049, 'a5416a5aa7f270dd751e81e29d691627', 'd878ec6f942769e1a1cab9d0ef5e4e5c', '机械学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1050, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1051, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1052, 'aa3b90dc8794fa032512271d15cfe0f5', '53dd6b7ec8be2d906c23cd90defafa18', '浙江工业大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1053, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1054, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1055, 'b2ff459f-9a6c-11e9-a169-005056c00001', 'ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1056, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1057, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1058, 'be6ef9d0-9a6c-11e9-a169-005056c00001', 'b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1059, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1060, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1061, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1062, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1063, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1064, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1065, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1066, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1067, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1068, 'd878ec6f942769e1a1cab9d0ef5e4e5c', '53dd6b7ec8be2d906c23cd90defafa18', '浙江财经大学', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1069, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1070, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1071, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1072, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1073, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1074, 'ff6221bb-9a6b-11e9-a169-005056c00001', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省财政厅', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1075, 'top', '', '顶级节点', '81cf08e2-d9ce-11e9-b832-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1076, 'top', '', '顶级节点', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1077, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1078, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1079, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1080, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1081, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1082, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1083, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1084, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1085, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1086, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1087, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1088, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1089, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1090, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1091, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1092, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1093, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1094, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1095, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1096, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1097, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1098, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1099, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1100, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1101, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1102, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1103, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1104, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1105, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1106, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1107, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1108, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1109, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1110, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1111, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1112, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1113, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1114, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1115, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1116, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1117, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1118, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1119, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1120, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1121, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1122, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1123, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1124, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1125, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1126, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1127, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1128, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1129, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1130, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1131, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1132, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1133, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1134, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1135, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1136, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1137, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1138, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1139, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1140, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1141, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1142, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1143, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1144, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1145, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1146, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1147, 'top', '', '顶级节点', '96c1a722-dab5-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1148, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1149, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1150, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1151, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1152, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1153, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1154, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1155, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1156, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1157, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1158, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1159, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1160, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1161, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1162, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1163, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1164, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1165, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1166, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1167, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1168, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1169, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1170, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1171, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1172, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1173, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1174, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1175, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1176, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1177, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1178, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1179, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1180, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1181, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1182, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1183, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1184, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1185, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1186, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1187, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1188, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1189, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1190, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1191, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1192, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1193, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1194, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1195, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1196, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1197, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1198, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1199, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1200, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1201, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1202, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1203, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1204, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1205, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1206, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1207, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1208, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1209, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1210, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1211, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1212, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1213, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1214, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1215, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1216, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1217, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1218, 'top', '', '顶级节点', '96c1a722-dab5-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1219, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1220, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1221, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1222, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1223, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1224, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1225, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1226, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1227, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1228, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1229, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1230, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1231, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1232, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1233, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1234, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1235, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1236, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1237, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1238, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1239, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1240, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1241, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1242, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1243, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1244, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1245, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1246, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1247, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1248, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1249, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1250, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1251, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1252, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1253, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1254, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1255, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1256, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1257, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1258, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1259, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1260, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1261, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1262, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1263, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1264, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1265, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1266, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1267, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1268, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1269, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1270, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1271, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1272, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1273, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1274, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1275, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1276, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1277, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1278, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1279, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1280, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1281, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1282, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1283, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1284, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1285, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1286, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1287, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1288, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1289, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1290, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1291, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1292, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1293, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1294, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1295, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1296, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1297, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1298, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1299, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1300, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1301, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1302, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1303, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1304, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1305, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1306, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1307, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1308, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1309, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1310, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1311, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1312, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1313, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1314, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1315, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1316, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1317, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1318, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1319, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1320, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1321, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1322, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1323, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1324, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1325, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1326, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1327, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1328, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1329, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1330, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1331, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1332, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1333, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1334, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1335, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1336, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1337, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1338, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1339, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1340, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1341, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1342, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1343, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1344, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1345, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1346, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1347, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1348, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1349, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1350, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1351, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1352, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1353, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1354, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1355, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1356, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1357, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1358, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1359, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1360, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1361, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1362, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1363, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1364, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1365, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1366, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1367, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1368, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1369, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1370, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1371, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1372, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1373, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1374, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1375, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1376, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1377, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1378, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1379, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1380, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1381, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1382, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1383, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1384, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1385, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1386, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1387, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1388, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1389, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1390, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1391, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1392, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1393, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1394, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1395, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1396, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1397, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1398, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1399, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1400, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1401, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1402, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1403, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1404, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1405, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1406, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1407, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1408, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1409, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1410, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1411, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1412, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1413, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1414, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1415, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1416, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1417, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1418, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1419, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1420, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1421, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1422, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1423, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1424, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1425, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1426, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1427, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1428, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1429, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1430, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1431, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1432, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1433, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1434, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1435, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1436, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1437, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1438, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1439, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1440, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1441, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1442, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1443, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1444, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1445, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1446, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1447, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1448, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1449, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1450, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1451, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1452, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1453, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1454, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1455, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1456, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1457, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1458, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1459, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1460, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1461, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1462, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1463, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1464, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1465, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1466, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1467, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1468, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1469, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1470, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1471, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1472, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1473, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1474, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1475, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1476, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1477, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1478, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1479, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1480, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1481, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1482, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1483, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1484, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1485, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1486, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1487, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1488, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1489, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1490, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1491, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1492, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1493, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1494, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1495, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1496, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1497, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1498, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1499, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1500, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1501, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1502, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1503, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1504, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1505, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1506, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1507, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1508, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1509, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1510, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1511, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1512, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1513, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1514, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1515, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1516, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1517, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1518, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1519, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1520, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1521, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1522, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1523, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1524, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1525, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1526, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1527, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1528, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1529, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1530, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1531, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1532, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1533, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1534, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1535, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1536, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1537, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1538, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1539, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1540, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1541, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1542, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1543, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1544, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1545, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1546, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1547, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1548, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1549, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1550, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1551, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1552, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1553, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1554, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1555, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1556, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1557, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1558, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1559, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1560, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1561, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1562, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1563, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1564, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1565, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1566, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1567, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1568, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1569, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1570, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1571, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1572, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1573, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1574, 'top', '', '顶级节点', '8f91a633-daba-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1575, 'top', '', '顶级节点', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1576, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1577, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1578, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1579, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1580, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1581, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1582, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1583, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1584, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1585, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1586, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1587, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1588, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1589, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1590, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1591, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1592, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1593, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1594, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1595, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1596, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1597, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1598, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1599, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1600, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1601, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1602, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1603, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1604, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1605, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1606, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1607, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1608, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1609, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1610, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1611, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1612, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1613, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1614, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1615, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1616, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1617, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1618, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1619, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1620, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1621, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1622, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1623, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1624, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1625, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1626, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1627, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1628, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1629, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1630, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1631, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1632, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1633, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1634, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1635, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1636, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1637, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1638, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1639, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1640, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1641, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1642, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1643, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1644, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1645, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1646, 'top', '', '顶级节点', 'bb5e45a4-e025-11e9-8c14-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1647, 'top', '', '顶级节点', 'e88175ea-e029-11e9-a3b4-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1648, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1649, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1650, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1651, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1652, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1653, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1654, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1655, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1656, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1657, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1658, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1659, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1660, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1661, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1662, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1663, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1664, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1665, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1666, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1667, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1668, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1669, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1670, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1671, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1672, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1673, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1674, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1675, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1676, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1677, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1678, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1679, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1680, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1681, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1682, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1683, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1684, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1685, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1686, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1687, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1688, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1689, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1690, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1691, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1692, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1693, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1694, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1695, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1696, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1697, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1698, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1699, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1700, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1701, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1702, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1703, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1704, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1705, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1706, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1707, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1708, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1709, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1710, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1711, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1712, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1713, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1714, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1715, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1716, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1717, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1718, 'top', '', '顶级节点', 'e88175ea-e029-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1719, 'top', '', '顶级节点', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0);
INSERT INTO `as_organ_scene` VALUES (1720, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1721, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1722, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1723, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1724, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1725, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1726, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1727, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1728, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1729, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1730, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1731, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1732, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1733, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1734, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1735, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1736, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1737, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1738, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1739, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1740, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1741, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1742, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1743, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1744, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1745, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1746, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1747, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1748, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1749, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1750, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1751, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1752, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1753, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1754, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1755, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1756, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1757, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1758, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1759, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1760, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1761, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1762, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1763, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1764, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1765, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1766, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1767, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1768, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1769, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1770, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1771, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1772, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1773, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1774, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1775, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1776, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1777, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1778, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1779, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1780, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1781, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1782, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1783, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1784, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1785, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1786, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1787, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1788, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1789, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1790, 'top', '', '顶级节点', 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 1);
INSERT INTO `as_organ_scene` VALUES (1791, 'top', '', '顶级节点', '96a6b730-e11d-11e9-9b6c-0242ac120002', 0);
INSERT INTO `as_organ_scene` VALUES (1792, '087505eb0f4b83f0b134541d9df8cf46', '743ccc5fb94314d08490c4662b16753a', '学报编辑部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1793, '08cb2500e25c47e02fd35eeef9f1ee6e', '743ccc5fb94314d08490c4662b16753a', '合作发展处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1794, '0b782fe62b50ad6788556617379402b2', '743ccc5fb94314d08490c4662b16753a', '智慧城市研究中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1795, '0d84968806cce337b1590ca4020ffaf9', '743ccc5fb94314d08490c4662b16753a', '微电子研究中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1796, '0f3b7f36846b10db310a1e853a55f3f6', '743ccc5fb94314d08490c4662b16753a', '创新材料研究院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1797, '12eee5ea0dd0783305e67c864ed5d7f4', '743ccc5fb94314d08490c4662b16753a', '海洋工程研究中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1798, '1551fc5717e73df91fcac02f453103b3', '743ccc5fb94314d08490c4662b16753a', '组织部（统战部）', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1799, '16a15605776588e7e6493b5f6c4022d8', '743ccc5fb94314d08490c4662b16753a', '宣传部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1800, '18e86b16a71af2e44141ac7584c5fe82', '743ccc5fb94314d08490c4662b16753a', '学校办', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1801, '1cad1b8b5c5f2fea175465283217b7b3', '743ccc5fb94314d08490c4662b16753a', '高等教育研究中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1802, '201909032033', '8d91df9ebeb13adfc44c93e22095bc3e', '资产审批部门', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1803, '201909032036', '4be8010f4896fb7e0c5d9e8d30eef9b7', '资产审批部门', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1804, '21daeff5227d7f18b5a7a3f075c260d3', '743ccc5fb94314d08490c4662b16753a', '国有资产与实验室管理处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1805, '26427284b2eb4b395ae73974ac2be1b9', '743ccc5fb94314d08490c4662b16753a', '后勤服务与管理中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1806, '27ed80cb65fe1b10332bc41d7823423d', '743ccc5fb94314d08490c4662b16753a', '国际交流合作处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1807, '28b01e21274683fb264a2c015179509b', '743ccc5fb94314d08490c4662b16753a', '体育教学部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1808, '2a3e29a9b570251143749c5042b2ae88', '743ccc5fb94314d08490c4662b16753a', '经济学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1809, '323184664794bf157182b9b68582eeea', '743ccc5fb94314d08490c4662b16753a', '科技部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1810, '32e66c0f1535a2d227080508772610d5', '743ccc5fb94314d08490c4662b16753a', '文一教育发展有限公司', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1811, '3ca2dd6b19273bd3b30b64e7cc858741', '743ccc5fb94314d08490c4662b16753a', '外国语学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1812, '3e8ddc147ec62c9e0dbd2a9a0738625b', '743ccc5fb94314d08490c4662b16753a', '校级资产处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1813, '476e3015ddd08db98e6fa72ab1561143', '743ccc5fb94314d08490c4662b16753a', '教务处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1814, '4be8010f4896fb7e0c5d9e8d30eef9b7', '743ccc5fb94314d08490c4662b16753a', '自动化学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1815, '4cd073f87e096e9127c99ded489aa4be', '743ccc5fb94314d08490c4662b16753a', '机械工程学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1816, '507d123e-9a6c-11e9-a169-005056c00001', 'top', '浙江省政府办公厅', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1817, '51beaeb3ce18965ce09086e1900eb4ba', '743ccc5fb94314d08490c4662b16753a', '校医院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1818, '53dd6b7ec8be2d906c23cd90defafa18', '507d123e-9a6c-11e9-a169-005056c00001', '浙江省教育厅', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1819, '58455f6cfb61dbbb3c5a27a95fecb432', '743ccc5fb94314d08490c4662b16753a', '国际教育学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1820, '5ce55f150cf8f8d68efda5a33f35d3f7', '743ccc5fb94314d08490c4662b16753a', '马克思主义学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1821, '663320bd5d56d19a43852b4969395eed', '743ccc5fb94314d08490c4662b16753a', '信息工程学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1822, '679b9f4be010696e92ddc6750078094f', '743ccc5fb94314d08490c4662b16753a', '网络空间安全学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1823, '6ba70e1eba4e5de23885206d7d1b5ca9', '743ccc5fb94314d08490c4662b16753a', '校园建设与管理处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1824, '6c188f243e792f2b7bda3a65a7fd05b9', '743ccc5fb94314d08490c4662b16753a', '网络信息中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1825, '6f5a51b2a2f59ad10b9d4233003c4b8a', '743ccc5fb94314d08490c4662b16753a', '卓越学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1826, '715203e554cb01684abaece70788a523', '743ccc5fb94314d08490c4662b16753a', '采购中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1827, '743ccc5fb94314d08490c4662b16753a', '53dd6b7ec8be2d906c23cd90defafa18', '杭州电子科技大学', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1828, '74a1cdb079187a7d173cb51c021f9f92', '743ccc5fb94314d08490c4662b16753a', '招就处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1829, '7b34753bf1675fca71de7702502c3907', '743ccc5fb94314d08490c4662b16753a', '保卫处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1830, '7bba395f256e1321c9d6364ccdcff4c4', '743ccc5fb94314d08490c4662b16753a', '通信工程学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1831, '7d178ae7e4bfb88c8a1502e744bfdd12', '743ccc5fb94314d08490c4662b16753a', '图书馆', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1832, '7da418e984caf3673c52781aafceb613', '743ccc5fb94314d08490c4662b16753a', '学工部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1833, '7f23a751ea33cc838377b8a49a81e1b1', '743ccc5fb94314d08490c4662b16753a', '信息化与经济社会发展研究中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1834, '85e7fe399ff3a9d77ab7d4756b74589a', '743ccc5fb94314d08490c4662b16753a', '工会', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1835, '8d91df9ebeb13adfc44c93e22095bc3e', '743ccc5fb94314d08490c4662b16753a', '计算机学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1836, '95583369a2fd4131084f7f60cd82d383', '743ccc5fb94314d08490c4662b16753a', '人文与法学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1837, '99bd1e45bfbd26a1065406d210afadcf', '743ccc5fb94314d08490c4662b16753a', '理学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1838, 'a11c4f6997a726e49984d862145068a7', '743ccc5fb94314d08490c4662b16753a', '管理学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1839, 'a1eac28697013af6d118ef7f8b1c37d6', '743ccc5fb94314d08490c4662b16753a', '人事处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1840, 'a44ebe09c8843af20fdda62de11b7f27', '743ccc5fb94314d08490c4662b16753a', '纪监办', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1841, 'a5207cc57e46f7a6318f16b8683e2899', '743ccc5fb94314d08490c4662b16753a', '档案馆', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1842, 'a7c175060595641527e0779923470f6f', '743ccc5fb94314d08490c4662b16753a', '发规处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1843, 'a8380548b9180192df8d54aa84da09d2', '743ccc5fb94314d08490c4662b16753a', '军工处（国防科技学院）', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1844, 'ac7ea25f71edee7974f2bb6371b5f00e', '743ccc5fb94314d08490c4662b16753a', '生仪学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1845, 'ae44d847bbbe2d0c59bb6450bfd5a9fc', '743ccc5fb94314d08490c4662b16753a', '环材学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1846, 'b3346d5b9a9f2b80ce12a313134db712', '743ccc5fb94314d08490c4662b16753a', '电子信息学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1847, 'b8ba2706826fa30ef62785c35d24956e', '743ccc5fb94314d08490c4662b16753a', '高退休处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1848, 'bf3d4d5ced07580529b5e4588adcf543', '743ccc5fb94314d08490c4662b16753a', '创新创业学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1849, 'c20bf9433dcd3bd80dceff2c303279a7', '743ccc5fb94314d08490c4662b16753a', '资产经营有限公司', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1850, 'c46ef0c9e09a4a465a504b13a3d6620a', '743ccc5fb94314d08490c4662b16753a', '创新与发展研究院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1851, 'c7b50bfb213b1e2254c919171d3074da', '743ccc5fb94314d08490c4662b16753a', '会计学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1852, 'c7dcb3e5ea1c962daa28808135c6cb04', '743ccc5fb94314d08490c4662b16753a', '重点高校与学科建设办公室', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1853, 'cbe3cb1cf70c70ebcdf5d0009ba47a27', '743ccc5fb94314d08490c4662b16753a', '计财处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1854, 'ce8da74b3faebf9ef5c478ca37ee912a', '743ccc5fb94314d08490c4662b16753a', '审计处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1855, 'd6f5c6d5333dc35de5904c9feb9fc50a', '743ccc5fb94314d08490c4662b16753a', '教师教学发展中心', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1856, 'd71afd13ab2caeea901f4ca9fa881ace', '743ccc5fb94314d08490c4662b16753a', '继续教育学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1857, 'dbf52923838d131d525ac7738b1a407b', '743ccc5fb94314d08490c4662b16753a', '团委', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1858, 'df2cc7860093d68043a3a5f2ba04179e', '743ccc5fb94314d08490c4662b16753a', '数媒学院', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1859, 'f24e7c741364d01b41f15544c22a9f62', '743ccc5fb94314d08490c4662b16753a', '财务云', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1860, 'f66053c7fec66a098d36709c87f1021b', '743ccc5fb94314d08490c4662b16753a', '杭电资产处', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1861, 'f96d13c25fcafef06cc011aca957549c', '743ccc5fb94314d08490c4662b16753a', '研工部', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1862, 'top', '', '顶级节点', '96a6b730-e11d-11e9-9b6c-0242ac120002', 1);
INSERT INTO `as_organ_scene` VALUES (1863, 'top', '', '顶级节点', '59c2b212-e122-11e9-9b6c-0242ac120002', 1);

-- ----------------------------
-- Table structure for as_organ_tree
-- ----------------------------
DROP TABLE IF EXISTS `as_organ_tree`;
CREATE TABLE `as_organ_tree`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `unit_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `unit_name_en` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门英文名称',
  `parent_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '上级单位id',
  `organization_form` int(2) NULL DEFAULT NULL COMMENT '组织形式：\r\n10、公司制企业（11.国有独资企业 12.其他有限责任公司 13.上市股份有限公司 股票代码 14。非上市股份有限公司）20.公司制企业（21.非公司制独资企业 22.其他非公司制企业） 30.企业化管理事业单位 40。其他',
  `unit_type` int(3) NULL DEFAULT NULL COMMENT '单位分类\r\n1、主管部门 \r\n2、二级单位 \r\n3、基层单位 \r\n\r\n100、政府财政事务 \r\n200、安全管理事务 \r\n300、',
  `parent_node_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级单位名称',
  `charge_section_id` int(11) NULL DEFAULT NULL COMMENT '主管部门id',
  `charge_section_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管部门名称',
  `local_financial_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级财政代码',
  `local_financial_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级财政名称',
  `office_administration_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级机关事务管理局代码',
  `office_administration_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级机关事务管理局名称',
  `administration_division_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政区划代码',
  `administration_division_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政区划名称',
  `budget_code` int(64) NULL DEFAULT NULL COMMENT '财政预算代码：\r\n编码方法：采用层次码，用数字表示,代码结构为每3位一层。一级单位3位码,二级单位6位码,……,七级单位21位码。其中，预算单位本级由该预算单位的单位层次码加上001表示。中央一级预算单位统一的3位代码表见表5。 \r\n应用范围：中央使用,地方参照。 \r\n\r\n代码	名称 \r\n101	国务院办公厅 \r\n102	国家发展和改革委员会 \r\n105	教育部 \r\n106	科学技术部 \r\n107	国家国防科技工业局 \r\n108	国家民族事务委员会 \r\n109	国家体育总局 \r\n110	国家人口和计划生育委员会 \r\n111	公安部 \r\n112	国家安全部 \r\n113	司法部 \r\n114	外交部 \r\n115	监察部 \r\n117	人力资源和社会保障部 \r\n118	民政部 \r\n119	财政部 \r\n120	住房和城乡建设部 \r\n121	国土资源部 \r\n122	铁道部 \r\n123	交通运输部 \r\n124	工业和信息化部 \r\n125	农业部 \r\n126	水利部 \r\n127	全国社会保障基金理事会',
  `college_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高等院校代码\r\n院校代号即院校代码或学校代码，为全国各高校录取时为方便考生填报志愿而加注的由数字组成的代号串。院校代码就如同是学校的一个身份证号，方便查询学校信息，教育部为高校编排的代码有5位（此代码全国通用），各省教育考试院为高校编排代码有4位（此代码一般作填报高考志愿用，同一所高校在不同省份代码也不一样），由于高校办学情况每年都有变动，所以高校代码也有变化。 \r\n\r\n10001 北京大学 北京医科大学并入 ',
  `organization_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码\r\n组织机构代码按照强制性国家标准GB11714《全国组织机构代码编制规则》编制，由八位数字(或大写拉丁字母)本体代码和一位数字(或大写拉丁字母)校验码组成。组织机构代码证组织机构代码证书包括正本、副本和电子副本(IC卡)，代码登记部门在为组织机构赋码发证的同时，还要采集28项基础信息，并按照国家标准对这些信息进行编码，将这些信息存入代码数据库和代码证电子副本(IC卡)中，供代码应用部门使用。代码登记部门所采集的基础信息包括:机构名称、机构地址、机构类型、经济性质、行业分类、规模、法人代表等。',
  `unit_type_code` int(1) NULL DEFAULT NULL COMMENT '单位类型名称\r\n代码	名称	说明 \r\n1	行政	指依宪法和有关组织法的规定设置的，行使国家行政职权，负责对国家各项行政事务进行组织、管理、监督和指挥的国家机关。这里包括行政、立法、司法、军队、党务等按公务员法管理的单位。 \r\n2	事业	为了社会公益目的，从事教育、文化、卫生、科技等活动并以非盈利性为主的社会服务组织。 \r\n3	参公	参照公务员法管理的单位。 \r\n4、 企业 企业单位 \r\n9	其他	除上述行政、事业外的其他单位。',
  `unit_type_name` int(1) NULL DEFAULT NULL COMMENT '单位类型名称\r\n代码	名称	说明 \r\n1	行政	指依宪法和有关组织法的规定设置的，行使国家行政职权，负责对国家各项行政事务进行组织、管理、监督和指挥的国家机关。这里包括行政、立法、司法、军队、党务等按公务员法管理的单位。 \r\n2	事业	为了社会公益目的，从事教育、文化、卫生、科技等活动并以非盈利性为主的社会服务组织。 \r\n3	参公	参照公务员法管理的单位。 \r\n4、 企业 企业单位 \r\n9	其他	除上述行政、事业外的其他单位。',
  `unit_basic_property` int(2) NULL DEFAULT NULL COMMENT '单位基本性质',
  `social_credit_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `budget_unit_name` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算单位级次名称\r\n代码	名称 \r\n1	一级 \r\n2	二级 \r\n3	三级 \r\n4	四级 \r\n5	五级 \r\n6	六级 \r\n7	七级',
  `budget_unit_code` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算单位级次代码',
  `budget_management_level` int(1) NULL DEFAULT NULL COMMENT '预算管理级次\r\n1、中央级 \r\n2、省级 \r\n3、地（市）级 \r\n4、县级 \r\n5、乡镇级',
  `firm_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业名称\r\n采用国家标准GB/T 4754《国民经济行业分类》。 \r\n编码方法：采用层次码，第1位英文字母表示门类；第2、3位数字表示大类；第4位数字表示中类；第5位数字表示小类。 \r\n\r\n经营性国有资产、行政事业性国有资产、金融性国有资产和资源性国有资产的主体分类是否可以按照这个标准出。',
  `firm_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业代码\r\n代码	名称 \r\nA	农、林、牧、渔业 \r\nA01	农业 \r\nA011	种植业 \r\n…	…… \r\nA019	其他农业 \r\n…	…… \r\nB	采掘业 \r\n…	…… \r\nC	制造业 \r\n…	… \r\nC13	食品加工业 \r\nC131	粮食及饲料加工业 \r\nC1311	碾米业 ',
  `telephone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `fax_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真号码',
  `email_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `county` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县',
  `street_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道地址',
  `postal_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `fund_supply_mode_name` int(1) NULL DEFAULT NULL COMMENT '经费供给方式名称：\r\n代码	名称	说明 \r\n1	全额	指由财政供应全部经费的预算单位。 \r\n2	差额	指财政按单位收支差额供应经费的预算单位。 \r\n3	自收自支	不靠财政供应经费的预算单位。 \r\n9	其他	指财政按其他方式供应经费的预算单位。',
  `fund_supply_mode_code` int(1) NULL DEFAULT NULL COMMENT '经费供给方式代码：\r\n代码	名称	说明 \r\n1	全额	指由财政供应全部经费的预算单位。 \r\n2	差额	指财政按单位收支差额供应经费的预算单位。 \r\n3	自收自支	不靠财政供应经费的预算单位。 \r\n9	其他	指财政按其他方式供应经费的预算单位。',
  `administration_level_name` int(1) NULL DEFAULT NULL COMMENT '单位行政级别名称：\r\n代码	名称 \r\n1	正部（省）级 \r\n2	副部（省）级 \r\n3	正厅（地）级 \r\n4	副厅（地）级 \r\n5	正处（县）级 \r\n6	副处（县）级 \r\n7	正科级 \r\n8	副科级 \r\n9	股级',
  `administration_level_type` int(1) NULL DEFAULT NULL COMMENT '行政单位部门性质分类代码',
  `department_category_name` int(2) NULL DEFAULT NULL COMMENT '事业单位部门性质分类名称',
  `business_department_classification_code` int(2) NULL DEFAULT NULL COMMENT '事业单位部门性质分类代码：\r\n代码	名称 \r\n1	行政单位 \r\n11	共产党机关 \r\n12	政府机关 \r\n13	人大机关 \r\n14	政协机关 \r\n15	民主党派机关 \r\n16	群众团体 \r\n2	公检法司 \r\n21	公安 \r\n22	检察 \r\n23	法院 \r\n24	司法行政 \r\n25	监狱 \r\n26	劳教 \r\n29	其他 \r\n3	驻外机构 \r\n\r\n代码	名称 \r\n1	农、林、牧、渔业 \r\n2	交通运输、仓储和邮政业 \r\n3	科学研究、技术服务和地质勘查业 \r\n4	水利、环境和公共设施管理业 \r\n5	教育 \r\n6	卫生、社会保障和社会福利业 \r\n7	文化、体育和娱乐业 \r\n9	其他',
  `principal` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `corporate_representative` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `economic_type` int(2) NULL DEFAULT NULL COMMENT '经济类型：\r\n10、国有及国有控股（是否中外合资合作企业 11.是 12，否） \r\n20、厂办大集体（21 中央企厂办大集体 22、中央下放企业厂办大集体；23、地方企业厂办大集体） 30.其他城镇计提',
  `financial_affiliation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务隶属关系（行政隶属关系代码-部门标识代码）',
  `financial_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否财政单位',
  `vertical_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否垂管单位',
  `virtual_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否虚拟单位',
  `accounting_system` int(2) NULL DEFAULT NULL COMMENT '单位执行会计制度:\r\n10.行政单位会计制度 \r\n20.事业单位会计制度 \r\n21.科学事业单位会计制度 \r\n22.中小学校会计制度 \r\n23.高等学校会计制度 \r\n24.医院会计制度 \r\n25.基层医疗卫生机构会计制度 \r\n26.测绘事业单位会计制度 \r\n27.地质勘查单位会计制度 \r\n28.彩票机构会计制度 \r\n30.民间非营利组织会计制度 \r\n50.其他',
  `corporate_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业标志',
  `maintainer_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修商标志',
  `supplier_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商标志',
  `manufacture_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造商标志',
  `asset_disposal_agency_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产处置代理机构标志',
  `state_asset_management_company` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国有资产经营公司标志',
  `authorized_number` int(11) NULL DEFAULT NULL COMMENT '编制人数',
  `administration_authority` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政编制',
  `enable_time` datetime(0) NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `seal_time` datetime(0) NULL DEFAULT NULL COMMENT '封存时间',
  `unit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `asset_download_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产数据下载状态',
  `latitude_and_longitude` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GPS经纬度',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `is_deleted` int(2) NULL DEFAULT NULL,
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态 1：有效 0：无效',
  `sort` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `tenant_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_organ_tree
-- ----------------------------
INSERT INTO `as_organ_tree` VALUES ('074389607b39cfa677bb74eb9721450b', '通信工程学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:01:56', '2019-09-02 14:17:53', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('087505eb0f4b83f0b134541d9df8cf46', '学报编辑部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('08cb2500e25c47e02fd35eeef9f1ee6e', '合作发展处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('09b3bbbc-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 16:24:57', NULL, NULL, NULL, NULL, '2019-06-29 20:47:22', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('0a6bf7cb1abd4807a0a62a204ff3f30f', '123', NULL, 'be6ef9d0-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:27:09', '2019-08-04 15:35:35', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('0b782fe62b50ad6788556617379402b2', '智慧城市研究中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:22:59', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('0d84968806cce337b1590ca4020ffaf9', '微电子研究中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('0f3b7f36846b10db310a1e853a55f3f6', '创新材料研究院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('12eee5ea0dd0783305e67c864ed5d7f4', '海洋工程研究中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('14861e7d-9a6c-11e9-a169-005056c00001', '浙江省商务厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:47:40', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('1551fc5717e73df91fcac02f453103b3', '组织部（统战部）', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('16a15605776588e7e6493b5f6c4022d8', '宣传部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('18e86b16a71af2e44141ac7584c5fe82', '学校办', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('1cad1b8b5c5f2fea175465283217b7b3', '高等教育研究中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('201909032033', '资产审批部门', NULL, '8d91df9ebeb13adfc44c93e22095bc3e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:36:03', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('201909032036', '资产审批部门', NULL, '4be8010f4896fb7e0c5d9e8d30eef9b7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:36:52', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('2185b032dfc752b058c79543f93f571f', '浙江大学', NULL, '53dd6b7ec8be2d906c23cd90defafa18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:45', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('21daeff5227d7f18b5a7a3f075c260d3', '国有资产与实验室管理处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('26427284b2eb4b395ae73974ac2be1b9', '后勤服务与管理中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('27ed80cb65fe1b10332bc41d7823423d', '国际交流合作处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('28b01e21274683fb264a2c015179509b', '体育教学部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('2a3e29a9b570251143749c5042b2ae88', '经济学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('323184664794bf157182b9b68582eeea', '科技部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('32e66c0f1535a2d227080508772610d5', '文一教育发展有限公司', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('3466d47217a800236f24e3fa718e63b6', '', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-04 15:35:53', '2019-08-04 15:35:59', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('37cbc46d77eaf0594bdf3b70282d2cd7', '测试', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-15 16:33:02', '2019-09-02 14:17:55', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('3ca2dd6b19273bd3b30b64e7cc858741', '外国语学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('3e8ddc147ec62c9e0dbd2a9a0738625b', '校级资产处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-03 22:00:44', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('476e3015ddd08db98e6fa72ab1561143', '教务处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('4a407736f58fdd8a69b84c98431aa0c2', '校宣传办', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:01:56', '2019-09-02 14:17:56', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('4a5ac1ef535a3930c7abd7e2ad528473', '测试节点2', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-04 15:35:53', '2019-08-12 21:59:02', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('4be8010f4896fb7e0c5d9e8d30eef9b7', '自动化学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('4cd073f87e096e9127c99ded489aa4be', '机械工程学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('4efdcac469092b6d8ef609356e8e6ae2', '测试部门', NULL, '6ba70e1eba4e5de23885206d7d1b5ca9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-19 14:24:41', '2019-09-19 14:25:04', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('507d123e-9a6c-11e9-a169-005056c00001', '浙江省政府办公厅', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:49:21', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('51beaeb3ce18965ce09086e1900eb4ba', '校医院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('53dd6b7ec8be2d906c23cd90defafa18', '浙江省教育厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:16', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('57bd3af15653a6f7bdf46debf036826b', '机械与自动化学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:01:56', '2019-09-02 14:17:59', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('58455f6cfb61dbbb3c5a27a95fecb432', '国际教育学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('5c510c1eb9545d9395dd13bfcb9784f9', '测试', NULL, 'be6ef9d0-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-16 09:37:45', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('5ce55f150cf8f8d68efda5a33f35d3f7', '马克思主义学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('663320bd5d56d19a43852b4969395eed', '信息工程学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:22:59', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('679b9f4be010696e92ddc6750078094f', '网络空间安全学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('6ba70e1eba4e5de23885206d7d1b5ca9', '校园建设与管理处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('6c188f243e792f2b7bda3a65a7fd05b9', '网络信息中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('6f5a51b2a2f59ad10b9d4233003c4b8a', '卓越学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('715203e554cb01684abaece70788a523', '采购中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('743ccc5fb94314d08490c4662b16753a', '杭州电子科技大学', NULL, '53dd6b7ec8be2d906c23cd90defafa18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:45', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('74a1cdb079187a7d173cb51c021f9f92', '招就处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('79d20426e9f6798ce6b315f58a24906e', '浙江财经大学计算机学院', NULL, 'd878ec6f942769e1a1cab9d0ef5e4e5c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-18 13:19:48', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('7b34753bf1675fca71de7702502c3907', '保卫处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('7bba395f256e1321c9d6364ccdcff4c4', '通信工程学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('7d178ae7e4bfb88c8a1502e744bfdd12', '图书馆', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('7da418e984caf3673c52781aafceb613', '学工部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('7f23a751ea33cc838377b8a49a81e1b1', '信息化与经济社会发展研究中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('83ae9b4677b84f10bf2f9e03575f88f5', '胡海杰', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 16:55:10', '2019-08-04 15:35:06', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('85e7fe399ff3a9d77ab7d4756b74589a', '工会', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('8d91df9ebeb13adfc44c93e22095bc3e', '计算机学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:01:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('95583369a2fd4131084f7f60cd82d383', '人文与法学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('99bd1e45bfbd26a1065406d210afadcf', '理学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a0188021b0da17763cbf54738c6f91c3', '中国美术学院', NULL, '53dd6b7ec8be2d906c23cd90defafa18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:45', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a11c4f6997a726e49984d862145068a7', '管理学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a1eac28697013af6d118ef7f8b1c37d6', '人事处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a44ebe09c8843af20fdda62de11b7f27', '纪监办', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a5207cc57e46f7a6318f16b8683e2899', '档案馆', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a5416a5aa7f270dd751e81e29d691627', '机械学院', NULL, 'd878ec6f942769e1a1cab9d0ef5e4e5c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-18 13:19:48', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a7c175060595641527e0779923470f6f', '发规处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('a8380548b9180192df8d54aa84da09d2', '军工处（国防科技学院）', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('aa3b90dc8794fa032512271d15cfe0f5', '浙江工业大学', NULL, '53dd6b7ec8be2d906c23cd90defafa18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:45', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('ac7ea25f71edee7974f2bb6371b5f00e', '生仪学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('ae44d847bbbe2d0c59bb6450bfd5a9fc', '环材学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处', NULL, 'ff6221bb-9a6b-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:52:06', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('b3346d5b9a9f2b80ce12a313134db712', '电子信息学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:17', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b5b5fb74e13345ee866b0bd1fdb1ab19', '啥', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:05:56', '2019-08-04 15:35:00', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b7499fc039c720ed0e76ad56de234d03', '测试部门的子部门', NULL, '4efdcac469092b6d8ef609356e8e6ae2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-19 14:24:53', '2019-09-19 14:25:09', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b82839e240b748a78117c3523967bbfd', '测试节点2', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:03:24', '2019-08-04 15:35:08', NULL, NULL, NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('b8ba2706826fa30ef62785c35d24956e', '高退休处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('be6ef9d0-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', NULL, 'b2ff459f-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:14:17', NULL, NULL, NULL, NULL, '2019-06-29 20:52:25', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('bf3d4d5ced07580529b5e4588adcf543', '创新创业学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:22:59', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('c20bf9433dcd3bd80dceff2c303279a7', '资产经营有限公司', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:25:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('c46ef0c9e09a4a465a504b13a3d6620a', '创新与发展研究院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:22:59', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('c7b50bfb213b1e2254c919171d3074da', '会计学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:19:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('c7dcb3e5ea1c962daa28808135c6cb04', '重点高校与学科建设办公室', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('cb3e46447d630973df0dab943325234a', '11', NULL, 'f66053c7fec66a098d36709c87f1021b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-15 16:36:17', '2019-09-02 14:15:57', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('cbe3cb1cf70c70ebcdf5d0009ba47a27', '计财处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('cc762ffeab6546859439f6999c154e2f', '测试节点3', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 21:59:01', NULL, NULL, NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('ce8da74b3faebf9ef5c478ca37ee912a', '审计处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('cf526e3128df4948b790ec8c4e79719b', '陈旭', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 16:54:41', '2019-08-04 15:35:02', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('d270ea1b736b42388b1569ded3741d10', '叶柄跃', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:05:05', '2019-08-04 15:35:04', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('d6f5c6d5333dc35de5904c9feb9fc50a', '教师教学发展中心', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('d71afd13ab2caeea901f4ca9fa881ace', '继续教育学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:22:59', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('d878ec6f942769e1a1cab9d0ef5e4e5c', '浙江财经大学', NULL, '53dd6b7ec8be2d906c23cd90defafa18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-18 13:19:08', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('dbf52923838d131d525ac7738b1a407b', '团委', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('df2cc7860093d68043a3a5f2ba04179e', '数媒学院', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:21:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('e86bf5190ffc4f92932cfb0ad9869954', '测试节点1', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:03:23', '2019-08-12 21:58:59', NULL, NULL, NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('eda002cccfbe46b0b4a0a5b2db05c202', '测试节点', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:02:51', '2019-08-04 15:35:10', NULL, NULL, NULL, NULL, NULL, 1, 0, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('f24e7c741364d01b41f15544c22a9f62', '财务云', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('f4595475e57a49d8a79028f9723f161b', '222', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-15 16:38:05', '2019-09-02 14:16:00', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('f66053c7fec66a098d36709c87f1021b', '杭电资产处', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:01:56', '2019-09-02 14:18:00', NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('f96d13c25fcafef06cc011aca957549c', '研工部', NULL, '743ccc5fb94314d08490c4662b16753a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-09-02 14:30:58', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('fd6befc4afa249fe8720f067da1e0420', '海', NULL, 'be6ef9d0-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:08:20', '2019-08-04 15:35:31', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('ff6221bb-9a6b-11e9-a169-005056c00001', '浙江省财政厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 16:24:57', NULL, NULL, NULL, NULL, '2019-06-29 20:47:05', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('top', '顶级节点', 'TopNode', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);

-- ----------------------------
-- Table structure for as_resource
-- ----------------------------
DROP TABLE IF EXISTS `as_resource`;
CREATE TABLE `as_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(2) NULL DEFAULT NULL,
  `sort` int(2) NULL DEFAULT 0,
  `group_id` bigint(20) NULL DEFAULT 0,
  `category` int(2) NULL DEFAULT NULL,
  `remark` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scene_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场景id',
  `is_deleted` int(2) NULL DEFAULT 0,
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `remove_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2029 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_resource
-- ----------------------------
INSERT INTO `as_resource` VALUES (1524, 0, 'app', '演示用应用', 'icon-cgrk', '5dd0efe6-ce45-11e9-97ce-0242ac120004', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:21:34', NULL);
INSERT INTO `as_resource` VALUES (1525, 1524, 'form', '资产移交申请表单', '图标', 'a920b8e7-ce45-11e9-97ce-0242ac120004', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 12:23:40', NULL);
INSERT INTO `as_resource` VALUES (1526, 1525, 'func', '新增', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1527, 1525, 'func', '导入', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1528, 1525, 'func', '导出', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1529, 1525, 'func', '删除', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1530, 1525, 'func', '暂存', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1531, 1525, 'func', '提交', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1532, 1525, 'func', '提交并添加', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1533, 1525, 'func', '打印二维码', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1534, 1525, 'func', '打印', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1535, 1525, 'func', '删除', '', 'a920b8e7-ce45-11e9-97ce-0242ac120004/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 20:23:40', NULL);
INSERT INTO `as_resource` VALUES (1536, 1524, 'form', '测试流程', '图标', 'f353fbd0-ce55-11e9-ad65-0242ac120004', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 14:20:16', NULL);
INSERT INTO `as_resource` VALUES (1537, 1536, 'func', '新增', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1538, 1536, 'func', '导入', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1539, 1536, 'func', '导出', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1540, 1536, 'func', '删除', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1541, 1536, 'func', '暂存', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1542, 1536, 'func', '提交', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1543, 1536, 'func', '提交并添加', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1544, 1536, 'func', '打印二维码', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1545, 1536, 'func', '打印', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1546, 1536, 'func', '删除', '', 'f353fbd0-ce55-11e9-ad65-0242ac120004/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:20:16', NULL);
INSERT INTO `as_resource` VALUES (1547, 0, 'app', '演示应用', 'icon-cgfk', '38ba9891-ce57-11e9-ad65-0242ac120004', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:23', NULL);
INSERT INTO `as_resource` VALUES (1548, 1547, 'form', '资产移交表单', '图标', '45f56fd2-ce57-11e9-ad65-0242ac120004', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 14:29:44', NULL);
INSERT INTO `as_resource` VALUES (1549, 1548, 'func', '新增', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1550, 1548, 'func', '导入', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1551, 1548, 'func', '导出', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1552, 1548, 'func', '删除', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1553, 1548, 'func', '暂存', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1554, 1548, 'func', '提交', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1555, 1548, 'func', '提交并添加', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1556, 1548, 'func', '打印二维码', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1557, 1548, 'func', '打印', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1558, 1548, 'func', '删除', '', '45f56fd2-ce57-11e9-ad65-0242ac120004/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 22:29:45', NULL);
INSERT INTO `as_resource` VALUES (1559, 0, 'app', '演示应用2313', 'icon-cgfk', '71c3abfb-ce5d-11e9-9ac3-0242ac120004', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:13:55', NULL);
INSERT INTO `as_resource` VALUES (1560, 1559, 'form', '资产移交表单', '图标', '7ff5183c-ce5d-11e9-9ac3-0242ac120004', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 15:14:18', NULL);
INSERT INTO `as_resource` VALUES (1561, 1560, 'func', '新增', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1562, 1560, 'func', '导入', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1563, 1560, 'func', '导出', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1564, 1560, 'func', '删除', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1565, 1560, 'func', '暂存', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1566, 1560, 'func', '提交', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1567, 1560, 'func', '提交并添加', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1568, 1560, 'func', '打印二维码', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1569, 1560, 'func', '打印', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1570, 1560, 'func', '删除', '', '7ff5183c-ce5d-11e9-9ac3-0242ac120004/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-03 23:14:19', NULL);
INSERT INTO `as_resource` VALUES (1571, 0, 'app', '测试cx', '绑定客户', 'f94694dd-d211-11e9-9ac3-0242ac120004', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-08 16:23:45', NULL);
INSERT INTO `as_resource` VALUES (1591, 0, 'app', '测试YBY1', '订单结算', '0e507a35-d827-11e9-ae28-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:09:47', NULL);
INSERT INTO `as_resource` VALUES (1592, 1591, 'form', '资产移交申请表单', '图标', '22c3c496-d827-11e9-ae28-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 02:10:21', NULL);
INSERT INTO `as_resource` VALUES (1593, 1592, 'func', '新增', '', '22c3c496-d827-11e9-ae28-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1594, 1592, 'func', '导入', '', '22c3c496-d827-11e9-ae28-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1595, 1592, 'func', '导出', '', '22c3c496-d827-11e9-ae28-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1596, 1592, 'func', '删除', '', '22c3c496-d827-11e9-ae28-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1597, 1592, 'func', '暂存', '', '22c3c496-d827-11e9-ae28-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1598, 1592, 'func', '提交', '', '22c3c496-d827-11e9-ae28-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1599, 1592, 'func', '提交并添加', '', '22c3c496-d827-11e9-ae28-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1600, 1592, 'func', '打印二维码', '', '22c3c496-d827-11e9-ae28-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1601, 1592, 'func', '打印', '', '22c3c496-d827-11e9-ae28-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1602, 1592, 'func', '删除', '', '22c3c496-d827-11e9-ae28-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 10:10:21', NULL);
INSERT INTO `as_resource` VALUES (1603, 0, 'app', '190916YBY测试应用', '代理指南', '2b4d830e-d83f-11e9-a9e8-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:02:24', NULL);
INSERT INTO `as_resource` VALUES (1604, 1603, 'form', '资产移交申请表单', '图标', '660d7d6f-d83f-11e9-a9e8-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 05:04:01', NULL);
INSERT INTO `as_resource` VALUES (1605, 1604, 'func', '新增', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1606, 1604, 'func', '导入', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1607, 1604, 'func', '导出', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1608, 1604, 'func', '删除', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1609, 1604, 'func', '暂存', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1610, 1604, 'func', '提交', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1611, 1604, 'func', '提交并添加', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1612, 1604, 'func', '打印二维码', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1613, 1604, 'func', '打印', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1614, 1604, 'func', '删除', '', '660d7d6f-d83f-11e9-a9e8-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-16 13:04:02', NULL);
INSERT INTO `as_resource` VALUES (1654, 0, 'app', '测试', '绑定客户', 'b7e6b91f-d9c0-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:16', NULL);
INSERT INTO `as_resource` VALUES (1655, 1654, 'form', '请假', '图标', 'cc545e30-d9c0-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 03:02:49', NULL);
INSERT INTO `as_resource` VALUES (1656, 1655, 'func', '新增', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1657, 1655, 'func', '导入', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1658, 1655, 'func', '导出', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1659, 1655, 'func', '删除', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1660, 1655, 'func', '暂存', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1661, 1655, 'func', '提交', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1662, 1655, 'func', '提交并添加', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1663, 1655, 'func', '打印二维码', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1664, 1655, 'func', '打印', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1665, 1655, 'func', '删除', '', 'cc545e30-d9c0-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 11:02:50', NULL);
INSERT INTO `as_resource` VALUES (1669, 0, 'app', '测试应用1224', '订单结算', '31baeb09-d9cc-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 12:24:24', NULL);
INSERT INTO `as_resource` VALUES (1675, 0, 'app', '测试应用', '绑定客户', '49a337ea-d9cc-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, '919fd4a1-d9cb-11e9-b749-0242ac120003', 0, '2019-09-18 12:35:14', NULL);
INSERT INTO `as_resource` VALUES (1676, 0, 'app', '请假应用', '绑定客户', 'e3c863a6-d9cb-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:17', NULL);
INSERT INTO `as_resource` VALUES (1677, 1676, 'form', '请假表单', '图标', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 04:42:54', NULL);
INSERT INTO `as_resource` VALUES (1678, 1677, 'func', '新增', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1679, 1677, 'func', '导入', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1680, 1677, 'func', '导出', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1681, 1677, 'func', '删除', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1682, 1677, 'func', '暂存', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1683, 1677, 'func', '提交', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1684, 1677, 'func', '提交并添加', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1685, 1677, 'func', '打印二维码', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1686, 1677, 'func', '打印', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1687, 1677, 'func', '删除', '', 'c7908b6c-d9ce-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '81cf08e2-d9ce-11e9-b832-0242ac120003', 0, '2019-09-18 12:42:55', NULL);
INSERT INTO `as_resource` VALUES (1688, 0, 'app', 'Nfq', '绑定客户', 'e84453b4-da0e-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-18 20:21:57', NULL);
INSERT INTO `as_resource` VALUES (1689, 0, 'app', '我的应用', '订单管理', 'df191f95-da82-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 10:12:04', NULL);
INSERT INTO `as_resource` VALUES (1690, 0, 'app', '测试应用', '申请商城商户', 'da2a51b6-daab-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:05:25', NULL);
INSERT INTO `as_resource` VALUES (1691, 1690, 'form', '我的表单', '图标', '89e3b64b-daad-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 07:17:29', NULL);
INSERT INTO `as_resource` VALUES (1692, 1691, 'func', '新增', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1693, 1691, 'func', '导入', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1694, 1691, 'func', '导出', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1695, 1691, 'func', '删除', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1696, 1691, 'func', '暂存', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1697, 1691, 'func', '提交', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1698, 1691, 'func', '提交并添加', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1699, 1691, 'func', '打印二维码', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1700, 1691, 'func', '打印', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1701, 1691, 'func', '删除', '', '89e3b64b-daad-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-19 15:17:29', NULL);
INSERT INTO `as_resource` VALUES (1702, 0, 'app', '我的应用', '我的推广商', 'cc3323cc-dab5-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:16:37', NULL);
INSERT INTO `as_resource` VALUES (1703, 1702, 'form', '我的请假表单', '图标', 'e1e6683d-dab5-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 08:17:12', NULL);
INSERT INTO `as_resource` VALUES (1704, 1703, 'func', '新增', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1705, 1703, 'func', '导入', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1706, 1703, 'func', '导出', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1707, 1703, 'func', '删除', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1708, 1703, 'func', '暂存', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1709, 1703, 'func', '提交', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1710, 1703, 'func', '提交并添加', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1711, 1703, 'func', '打印二维码', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1712, 1703, 'func', '打印', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1713, 1703, 'func', '删除', '', 'e1e6683d-dab5-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:17:13', NULL);
INSERT INTO `as_resource` VALUES (1714, 1702, 'form', '我的测试表单', '图标', '86c4fb18-dab6-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 08:21:49', NULL);
INSERT INTO `as_resource` VALUES (1715, 1714, 'func', '新增', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1716, 1714, 'func', '导入', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1717, 1714, 'func', '导出', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1718, 1714, 'func', '删除', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1719, 1714, 'func', '暂存', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1720, 1714, 'func', '提交', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1721, 1714, 'func', '提交并添加', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1722, 1714, 'func', '打印二维码', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1723, 1714, 'func', '打印', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1724, 1714, 'func', '删除', '', '86c4fb18-dab6-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:21:49', NULL);
INSERT INTO `as_resource` VALUES (1725, 0, 'app', '我的测试应用', '收银管理', 'a16b8833-dab7-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:44', NULL);
INSERT INTO `as_resource` VALUES (1726, 1725, 'form', '我的测试表单', '图标', 'aabe01b4-dab7-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 08:29:59', NULL);
INSERT INTO `as_resource` VALUES (1727, 1726, 'func', '新增', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1728, 1726, 'func', '导入', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1729, 1726, 'func', '导出', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1730, 1726, 'func', '删除', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1731, 1726, 'func', '暂存', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1732, 1726, 'func', '提交', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1733, 1726, 'func', '提交并添加', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1734, 1726, 'func', '打印二维码', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1735, 1726, 'func', '打印', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1736, 1726, 'func', '删除', '', 'aabe01b4-dab7-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:29:59', NULL);
INSERT INTO `as_resource` VALUES (1737, 1725, 'form', '我的测试表单1', '图标', '014298c6-dab8-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 08:32:24', NULL);
INSERT INTO `as_resource` VALUES (1738, 1737, 'func', '新增', '', '014298c6-dab8-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1739, 1737, 'func', '导入', '', '014298c6-dab8-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1740, 1737, 'func', '导出', '', '014298c6-dab8-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1741, 1737, 'func', '删除', '', '014298c6-dab8-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1742, 1737, 'func', '暂存', '', '014298c6-dab8-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1743, 1737, 'func', '提交', '', '014298c6-dab8-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1744, 1737, 'func', '提交并添加', '', '014298c6-dab8-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1745, 1737, 'func', '打印二维码', '', '014298c6-dab8-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1746, 1737, 'func', '打印', '', '014298c6-dab8-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1747, 1737, 'func', '删除', '', '014298c6-dab8-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96c1a722-dab5-11e9-8c14-0242ac120003', 0, '2019-09-19 16:32:24', NULL);
INSERT INTO `as_resource` VALUES (1748, 0, 'app', '我的工作应用', '我的客户', 'cca0c08a-daba-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:52:25', NULL);
INSERT INTO `as_resource` VALUES (1749, 1748, 'form', '工作请假表单', '图标', 'e9afb78b-daba-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 08:53:13', NULL);
INSERT INTO `as_resource` VALUES (1750, 1749, 'func', '新增', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1751, 1749, 'func', '导入', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1752, 1749, 'func', '导出', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1753, 1749, 'func', '删除', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1754, 1749, 'func', '暂存', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1755, 1749, 'func', '提交', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1756, 1749, 'func', '提交并添加', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1757, 1749, 'func', '打印二维码', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1758, 1749, 'func', '打印', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1759, 1749, 'func', '删除', '', 'e9afb78b-daba-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '8f91a633-daba-11e9-8c14-0242ac120003', 0, '2019-09-19 16:53:13', NULL);
INSERT INTO `as_resource` VALUES (1760, 1688, 'form', 'sas', '图标', '095cb24d-dba4-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 12:41:59', NULL);
INSERT INTO `as_resource` VALUES (1761, 1760, 'func', '新增', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1762, 1760, 'func', '导入', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1763, 1760, 'func', '导出', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1764, 1760, 'func', '删除', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1765, 1760, 'func', '暂存', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1766, 1760, 'func', '提交', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1767, 1760, 'func', '提交并添加', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1768, 1760, 'func', '打印二维码', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1769, 1760, 'func', '打印', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1770, 1760, 'func', '删除', '', '095cb24d-dba4-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:41:59', NULL);
INSERT INTO `as_resource` VALUES (1771, 1654, 'form', 'dsd', '图标', 'a66a942e-dba5-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 12:53:32', NULL);
INSERT INTO `as_resource` VALUES (1772, 1771, 'func', '新增', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1773, 1771, 'func', '导入', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1774, 1771, 'func', '导出', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1775, 1771, 'func', '删除', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1776, 1771, 'func', '暂存', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1777, 1771, 'func', '提交', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1778, 1771, 'func', '提交并添加', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1779, 1771, 'func', '打印二维码', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1780, 1771, 'func', '打印', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1781, 1771, 'func', '删除', '', 'a66a942e-dba5-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-20 20:53:32', NULL);
INSERT INTO `as_resource` VALUES (1782, 0, 'app', 'Nfq01', '绑定客户', '18ce0114-dc32-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-21 13:38:54', NULL);
INSERT INTO `as_resource` VALUES (1783, 0, 'app', '测试应用1', '绑定客户', '49a337ea-d9cc-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:22:40', NULL);
INSERT INTO `as_resource` VALUES (1784, 0, 'app', '测试应用09261442', '绑定客户', 'd1397d4b-e028-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:42:33', NULL);
INSERT INTO `as_resource` VALUES (1785, 1784, 'form', '测试表单09261442', '图标', 'e4c43c6c-e028-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 06:43:05', NULL);
INSERT INTO `as_resource` VALUES (1786, 1785, 'func', '新增', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1787, 1785, 'func', '导入', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1788, 1785, 'func', '导出', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1789, 1785, 'func', '删除', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1790, 1785, 'func', '暂存', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1791, 1785, 'func', '提交', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1792, 1785, 'func', '提交并添加', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1793, 1785, 'func', '打印二维码', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1794, 1785, 'func', '打印', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1795, 1785, 'func', '删除', '', 'e4c43c6c-e028-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 0, '2019-09-26 14:43:06', NULL);
INSERT INTO `as_resource` VALUES (1796, 0, 'app', '测试应用09261452', '申请商城商户', '2e46f127-e02a-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:19', NULL);
INSERT INTO `as_resource` VALUES (1797, 1796, 'form', '请假表单09261452', '图标', '430f1248-e02a-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 06:52:53', NULL);
INSERT INTO `as_resource` VALUES (1798, 1797, 'func', '新增', '', '430f1248-e02a-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1799, 1797, 'func', '导入', '', '430f1248-e02a-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1800, 1797, 'func', '导出', '', '430f1248-e02a-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1801, 1797, 'func', '删除', '', '430f1248-e02a-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1802, 1797, 'func', '暂存', '', '430f1248-e02a-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1803, 1797, 'func', '提交', '', '430f1248-e02a-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1804, 1797, 'func', '提交并添加', '', '430f1248-e02a-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1805, 1797, 'func', '打印二维码', '', '430f1248-e02a-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1806, 1797, 'func', '打印', '', '430f1248-e02a-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1807, 1797, 'func', '删除', '', '430f1248-e02a-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e88175ea-e029-11e9-a3b4-0242ac120003', 0, '2019-09-26 14:52:54', NULL);
INSERT INTO `as_resource` VALUES (1808, 0, 'app', '测试应用09261537', '发票管理', '79f23805-e030-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:37:23', NULL);
INSERT INTO `as_resource` VALUES (1809, 1808, 'form', '测试09261544', '图标', '6c1e86b7-e031-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 07:44:08', NULL);
INSERT INTO `as_resource` VALUES (1810, 1809, 'func', '新增', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1811, 1809, 'func', '导入', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1812, 1809, 'func', '导出', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1813, 1809, 'func', '删除', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1814, 1809, 'func', '暂存', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1815, 1809, 'func', '提交', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1816, 1809, 'func', '提交并添加', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1817, 1809, 'func', '打印二维码', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1818, 1809, 'func', '打印', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1819, 1809, 'func', '删除', '', '6c1e86b7-e031-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-26 15:44:09', NULL);
INSERT INTO `as_resource` VALUES (1820, 0, 'app', '会签测试', '绑定客户', 'e0743f84-e049-11e9-9b1b-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:12', NULL);
INSERT INTO `as_resource` VALUES (1821, 1820, 'form', '会签测试', '图标', 'f20a5a95-e049-11e9-9b1b-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 10:39:41', NULL);
INSERT INTO `as_resource` VALUES (1822, 1821, 'func', '新增', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1823, 1821, 'func', '导入', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1824, 1821, 'func', '导出', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1825, 1821, 'func', '删除', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1826, 1821, 'func', '暂存', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1827, 1821, 'func', '提交', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1828, 1821, 'func', '提交并添加', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1829, 1821, 'func', '打印二维码', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1830, 1821, 'func', '打印', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1831, 1821, 'func', '删除', '', 'f20a5a95-e049-11e9-9b1b-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-26 18:39:41', NULL);
INSERT INTO `as_resource` VALUES (1832, 0, 'app', '请假', '绑定客户', 'd6b3e3ea-e0fa-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-27 15:45:56', NULL);
INSERT INTO `as_resource` VALUES (1833, 0, 'app', '0927流程统计', '绑定客户', 'b29589fb-e118-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-27 19:19:41', NULL);
INSERT INTO `as_resource` VALUES (1834, 0, 'app', '流程验证09272000', '绑定客户', '6e7f0fbe-e11e-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:00:44', NULL);
INSERT INTO `as_resource` VALUES (1835, 1834, 'form', '新成立实验室建制流程', '图标', '0e89fa6f-e11f-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 12:05:11', NULL);
INSERT INTO `as_resource` VALUES (1836, 1835, 'func', '新增', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1837, 1835, 'func', '导入', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1838, 1835, 'func', '导出', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1839, 1835, 'func', '删除', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1840, 1835, 'func', '暂存', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1841, 1835, 'func', '提交', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1842, 1835, 'func', '提交并添加', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1843, 1835, 'func', '打印二维码', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1844, 1835, 'func', '打印', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1845, 1835, 'func', '删除', '', '0e89fa6f-e11f-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:05:12', NULL);
INSERT INTO `as_resource` VALUES (1846, 1834, 'form', '123', '图标', '4cd5fa76-e122-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 12:28:24', NULL);
INSERT INTO `as_resource` VALUES (1847, 1846, 'func', '新增', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1848, 1846, 'func', '导入', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1849, 1846, 'func', '导出', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1850, 1846, 'func', '删除', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1851, 1846, 'func', '暂存', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1852, 1846, 'func', '提交', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1853, 1846, 'func', '提交并添加', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1854, 1846, 'func', '打印二维码', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1855, 1846, 'func', '打印', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1856, 1846, 'func', '删除', '', '4cd5fa76-e122-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 20:28:25', NULL);
INSERT INTO `as_resource` VALUES (1857, 0, 'app', '资产配置', '绑定客户', 'd90ee3dc-e122-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-27 20:32:20', NULL);
INSERT INTO `as_resource` VALUES (1858, 0, 'app', '资产出租', '代理指南', '91cce57d-e123-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', 0, '2019-09-27 20:37:30', NULL);
INSERT INTO `as_resource` VALUES (1859, 0, 'app', '随便', '绑定客户', '37d63554-e127-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-27 21:03:37', NULL);
INSERT INTO `as_resource` VALUES (1860, 0, 'app', '132', '绑定客户', 'bb4cb035-e127-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:07:18', NULL);
INSERT INTO `as_resource` VALUES (1861, 1834, 'form', '实验室建设项目立项流程', '图标', '961b090a-e12e-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 13:56:21', NULL);
INSERT INTO `as_resource` VALUES (1862, 1861, 'func', '新增', '', '961b090a-e12e-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1863, 1861, 'func', '导入', '', '961b090a-e12e-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1864, 1861, 'func', '导出', '', '961b090a-e12e-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1865, 1861, 'func', '删除', '', '961b090a-e12e-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1866, 1861, 'func', '暂存', '', '961b090a-e12e-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1867, 1861, 'func', '提交', '', '961b090a-e12e-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1868, 1861, 'func', '提交并添加', '', '961b090a-e12e-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1869, 1861, 'func', '打印二维码', '', '961b090a-e12e-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1870, 1861, 'func', '打印', '', '961b090a-e12e-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1871, 1861, 'func', '删除', '', '961b090a-e12e-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 21:56:22', NULL);
INSERT INTO `as_resource` VALUES (1872, 1834, 'form', '实验室危险化学品采购保管使用报废流程', '图标', '89202454-e132-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 14:24:37', NULL);
INSERT INTO `as_resource` VALUES (1873, 1872, 'func', '新增', '', '89202454-e132-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1874, 1872, 'func', '导入', '', '89202454-e132-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1875, 1872, 'func', '导出', '', '89202454-e132-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1876, 1872, 'func', '删除', '', '89202454-e132-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1877, 1872, 'func', '暂存', '', '89202454-e132-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1878, 1872, 'func', '提交', '', '89202454-e132-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1879, 1872, 'func', '提交并添加', '', '89202454-e132-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1880, 1872, 'func', '打印二维码', '', '89202454-e132-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1881, 1872, 'func', '打印', '', '89202454-e132-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1882, 1872, 'func', '删除', '', '89202454-e132-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-27 22:24:38', NULL);
INSERT INTO `as_resource` VALUES (1883, 1834, 'form', '采购流程', '图标', '468c7f49-e1df-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 11:01:09', NULL);
INSERT INTO `as_resource` VALUES (1884, 1883, 'func', '新增', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1885, 1883, 'func', '导入', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1886, 1883, 'func', '导出', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1887, 1883, 'func', '删除', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1888, 1883, 'func', '暂存', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1889, 1883, 'func', '提交', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1890, 1883, 'func', '提交并添加', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1891, 1883, 'func', '打印二维码', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1892, 1883, 'func', '打印', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1893, 1883, 'func', '删除', '', '468c7f49-e1df-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:01:09', NULL);
INSERT INTO `as_resource` VALUES (1894, 1834, 'form', '危险化学品申购、使用及报废流程', '图标', '8d369bc0-e1e4-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 11:38:55', NULL);
INSERT INTO `as_resource` VALUES (1895, 1894, 'func', '新增', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1896, 1894, 'func', '导入', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1897, 1894, 'func', '导出', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1898, 1894, 'func', '删除', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1899, 1894, 'func', '暂存', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1900, 1894, 'func', '提交', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1901, 1894, 'func', '提交并添加', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1902, 1894, 'func', '打印二维码', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1903, 1894, 'func', '打印', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1904, 1894, 'func', '删除', '', '8d369bc0-e1e4-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-28 19:38:55', NULL);
INSERT INTO `as_resource` VALUES (1905, 0, 'app', 'cyc', '绑定客户', '4e271c89-e1ef-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:55:54', NULL);
INSERT INTO `as_resource` VALUES (1906, 1905, 'form', '132312', '图标', '598bd43a-e1ef-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 12:56:13', NULL);
INSERT INTO `as_resource` VALUES (1907, 1906, 'func', '新增', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1908, 1906, 'func', '导入', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1909, 1906, 'func', '导出', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1910, 1906, 'func', '删除', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1911, 1906, 'func', '暂存', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1912, 1906, 'func', '提交', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1913, 1906, 'func', '提交并添加', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1914, 1906, 'func', '打印二维码', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1915, 1906, 'func', '打印', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1916, 1906, 'func', '删除', '', '598bd43a-e1ef-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-09-28 20:56:13', NULL);
INSERT INTO `as_resource` VALUES (1917, 1834, 'form', '房屋招租流程', '图标', '987f8956-e265-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 03:02:39', NULL);
INSERT INTO `as_resource` VALUES (1918, 1917, 'func', '新增', '', '987f8956-e265-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1919, 1917, 'func', '导入', '', '987f8956-e265-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1920, 1917, 'func', '导出', '', '987f8956-e265-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1921, 1917, 'func', '删除', '', '987f8956-e265-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1922, 1917, 'func', '暂存', '', '987f8956-e265-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1923, 1917, 'func', '提交', '', '987f8956-e265-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1924, 1917, 'func', '提交并添加', '', '987f8956-e265-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1925, 1917, 'func', '打印二维码', '', '987f8956-e265-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1926, 1917, 'func', '打印', '', '987f8956-e265-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1927, 1917, 'func', '删除', '', '987f8956-e265-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:02:39', NULL);
INSERT INTO `as_resource` VALUES (1928, 0, 'app', '1', '代理指南', '5bf2ac48-e267-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, '89371977-b1b4-11e9-8d9b-005056c00001', 0, '2019-09-29 11:15:17', NULL);
INSERT INTO `as_resource` VALUES (1929, 1834, 'form', '大型仪器验收流程', '图标', '156eb745-e2a1-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 10:08:29', NULL);
INSERT INTO `as_resource` VALUES (1930, 1929, 'func', '新增', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1931, 1929, 'func', '导入', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1932, 1929, 'func', '导出', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1933, 1929, 'func', '删除', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1934, 1929, 'func', '暂存', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1935, 1929, 'func', '提交', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1936, 1929, 'func', '提交并添加', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1937, 1929, 'func', '打印二维码', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1938, 1929, 'func', '打印', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1939, 1929, 'func', '删除', '', '156eb745-e2a1-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:08:29', NULL);
INSERT INTO `as_resource` VALUES (1940, 1834, 'form', '政采云网上超市验收入库流程', '图标', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 10:58:58', NULL);
INSERT INTO `as_resource` VALUES (1941, 1940, 'func', '新增', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1942, 1940, 'func', '导入', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1943, 1940, 'func', '导出', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1944, 1940, 'func', '删除', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1945, 1940, 'func', '暂存', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1946, 1940, 'func', '提交', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1947, 1940, 'func', '提交并添加', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1948, 1940, 'func', '打印二维码', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1949, 1940, 'func', '打印', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1950, 1940, 'func', '删除', '', '22ea3c4f-e2a8-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 18:58:58', NULL);
INSERT INTO `as_resource` VALUES (1951, 1834, 'form', '设备验收入库流程', '图标', '885a00d4-e2ad-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 11:37:35', NULL);
INSERT INTO `as_resource` VALUES (1952, 1951, 'func', '新增', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1953, 1951, 'func', '导入', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1954, 1951, 'func', '导出', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1955, 1951, 'func', '删除', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1956, 1951, 'func', '暂存', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1957, 1951, 'func', '提交', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1958, 1951, 'func', '提交并添加', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1959, 1951, 'func', '打印二维码', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1960, 1951, 'func', '打印', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1961, 1951, 'func', '删除', '', '885a00d4-e2ad-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 19:37:36', NULL);
INSERT INTO `as_resource` VALUES (1962, 1834, 'form', '维修流程', '图标', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 12:23:49', NULL);
INSERT INTO `as_resource` VALUES (1963, 1962, 'func', '新增', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1964, 1962, 'func', '导入', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1965, 1962, 'func', '导出', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1966, 1962, 'func', '删除', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1967, 1962, 'func', '暂存', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1968, 1962, 'func', '提交', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1969, 1962, 'func', '提交并添加', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1970, 1962, 'func', '打印二维码', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1971, 1962, 'func', '打印', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1972, 1962, 'func', '删除', '', 'fd4b88ce-e2b3-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-29 20:23:49', NULL);
INSERT INTO `as_resource` VALUES (1973, 1834, 'form', '调拨流程', '图标', 'ddf787eb-e326-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 02:06:08', NULL);
INSERT INTO `as_resource` VALUES (1974, 1973, 'func', '新增', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1975, 1973, 'func', '导入', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1976, 1973, 'func', '导出', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1977, 1973, 'func', '删除', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1978, 1973, 'func', '暂存', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1979, 1973, 'func', '提交', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1980, 1973, 'func', '提交并添加', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1981, 1973, 'func', '打印二维码', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1982, 1973, 'func', '打印', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1983, 1973, 'func', '删除', '', 'ddf787eb-e326-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:06:09', NULL);
INSERT INTO `as_resource` VALUES (1984, 1834, 'form', '报废流程', '图标', '670f5e50-e32b-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 02:38:36', NULL);
INSERT INTO `as_resource` VALUES (1985, 1984, 'func', '新增', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1986, 1984, 'func', '导入', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1987, 1984, 'func', '导出', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1988, 1984, 'func', '删除', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1989, 1984, 'func', '暂存', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1990, 1984, 'func', '提交', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1991, 1984, 'func', '提交并添加', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1992, 1984, 'func', '打印二维码', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1993, 1984, 'func', '打印', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1994, 1984, 'func', '删除', '', '670f5e50-e32b-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 10:38:37', NULL);
INSERT INTO `as_resource` VALUES (1995, 1834, 'form', '实验教学类项目申请入库流程', '图标', 'd17239ca-e330-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 03:17:22', NULL);
INSERT INTO `as_resource` VALUES (1996, 1995, 'func', '新增', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (1997, 1995, 'func', '导入', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (1998, 1995, 'func', '导出', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (1999, 1995, 'func', '删除', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2000, 1995, 'func', '暂存', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2001, 1995, 'func', '提交', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2002, 1995, 'func', '提交并添加', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2003, 1995, 'func', '打印二维码', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2004, 1995, 'func', '打印', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2005, 1995, 'func', '删除', '', 'd17239ca-e330-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 11:17:23', NULL);
INSERT INTO `as_resource` VALUES (2006, 1834, 'form', '采购流程1', '图标', '1c851ef8-e347-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 05:56:57', NULL);
INSERT INTO `as_resource` VALUES (2007, 2006, 'func', '新增', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2008, 2006, 'func', '导入', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2009, 2006, 'func', '导出', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2010, 2006, 'func', '删除', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2011, 2006, 'func', '暂存', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2012, 2006, 'func', '提交', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2013, 2006, 'func', '提交并添加', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2014, 2006, 'func', '打印二维码', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2015, 2006, 'func', '打印', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2016, 2006, 'func', '删除', '', '1c851ef8-e347-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, '96a6b730-e11d-11e9-9b6c-0242ac120002', 0, '2019-09-30 13:56:58', NULL);
INSERT INTO `as_resource` VALUES (2017, 0, 'app', '会签测试191007', '绑定客户', '8dde0934-e8a4-11e9-b8fa-0242ac120006', 0, 0, 0, 1, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:48:27', NULL);
INSERT INTO `as_resource` VALUES (2018, 2017, 'form', '会签测试191007', '图标', 'ad914535-e8a4-11e9-b8fa-0242ac120006', 0, 0, 0, 2, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 01:49:19', NULL);
INSERT INTO `as_resource` VALUES (2019, 2018, 'func', '新增', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/list/add', 1, 1, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2020, 2018, 'func', '导入', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/list/import', 1, 2, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2021, 2018, 'func', '导出', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/list/export', 1, 3, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2022, 2018, 'func', '删除', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/list/delete', 1, 4, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2023, 2018, 'func', '暂存', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/form/add_temp', 2, 5, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2024, 2018, 'func', '提交', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/form/save', 2, 6, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2025, 2018, 'func', '提交并添加', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/form/save_add', 2, 7, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2026, 2018, 'func', '打印二维码', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/list/printQR', 2, 8, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2027, 2018, 'func', '打印', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/form/print', 2, 9, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);
INSERT INTO `as_resource` VALUES (2028, 2018, 'func', '删除', '', 'ad914535-e8a4-11e9-b8fa-0242ac120006/form/delete', 2, 10, 0, 3, NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-10-07 09:49:20', NULL);

-- ----------------------------
-- Table structure for as_resource_group
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_group`;
CREATE TABLE `as_resource_group`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源分组名称',
  `is_deleted` int(2) NOT NULL DEFAULT 0 COMMENT '0-有效；1-已删除',
  `scene_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '所属场景id',
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `add_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加的时间戳',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_resource_group
-- ----------------------------
INSERT INTO `as_resource_group` VALUES (14, '测试分组', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1365, '2019-08-13 20:55:57');
INSERT INTO `as_resource_group` VALUES (15, '测试分组1', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1364, '2019-08-27 11:05:16');
INSERT INTO `as_resource_group` VALUES (16, '测试分组2', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1364, '2019-08-27 17:05:58');
INSERT INTO `as_resource_group` VALUES (17, '分组1', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1465, '2019-08-31 03:32:36');
INSERT INTO `as_resource_group` VALUES (18, '分组1', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1571, '2019-09-12 13:25:53');
INSERT INTO `as_resource_group` VALUES (19, 'dsa', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1524, '2019-09-20 12:41:44');
INSERT INTO `as_resource_group` VALUES (20, 'sas', 0, 'e65edc60-96ee-11e9-ac96-005056c00001', 1688, '2019-09-20 12:42:07');

-- ----------------------------
-- Table structure for as_resource_role
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_role`;
CREATE TABLE `as_resource_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4444 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_resource_role
-- ----------------------------
INSERT INTO `as_resource_role` VALUES (1569, 1574, 50);
INSERT INTO `as_resource_role` VALUES (2431, 1524, 1);
INSERT INTO `as_resource_role` VALUES (2432, 1525, 1);
INSERT INTO `as_resource_role` VALUES (2433, 1526, 1);
INSERT INTO `as_resource_role` VALUES (2434, 1527, 1);
INSERT INTO `as_resource_role` VALUES (2435, 1528, 1);
INSERT INTO `as_resource_role` VALUES (2436, 1529, 1);
INSERT INTO `as_resource_role` VALUES (2437, 1530, 1);
INSERT INTO `as_resource_role` VALUES (2438, 1531, 1);
INSERT INTO `as_resource_role` VALUES (2439, 1532, 1);
INSERT INTO `as_resource_role` VALUES (2440, 1533, 1);
INSERT INTO `as_resource_role` VALUES (2441, 1534, 1);
INSERT INTO `as_resource_role` VALUES (2442, 1535, 1);
INSERT INTO `as_resource_role` VALUES (2443, 1536, 1);
INSERT INTO `as_resource_role` VALUES (2444, 1537, 1);
INSERT INTO `as_resource_role` VALUES (2445, 1538, 1);
INSERT INTO `as_resource_role` VALUES (2446, 1539, 1);
INSERT INTO `as_resource_role` VALUES (2447, 1540, 1);
INSERT INTO `as_resource_role` VALUES (2448, 1541, 1);
INSERT INTO `as_resource_role` VALUES (2449, 1542, 1);
INSERT INTO `as_resource_role` VALUES (2450, 1543, 1);
INSERT INTO `as_resource_role` VALUES (2451, 1544, 1);
INSERT INTO `as_resource_role` VALUES (2452, 1545, 1);
INSERT INTO `as_resource_role` VALUES (2453, 1546, 1);
INSERT INTO `as_resource_role` VALUES (2454, 1547, 1);
INSERT INTO `as_resource_role` VALUES (2455, 1548, 1);
INSERT INTO `as_resource_role` VALUES (2456, 1549, 1);
INSERT INTO `as_resource_role` VALUES (2457, 1550, 1);
INSERT INTO `as_resource_role` VALUES (2458, 1551, 1);
INSERT INTO `as_resource_role` VALUES (2459, 1552, 1);
INSERT INTO `as_resource_role` VALUES (2460, 1553, 1);
INSERT INTO `as_resource_role` VALUES (2461, 1554, 1);
INSERT INTO `as_resource_role` VALUES (2462, 1555, 1);
INSERT INTO `as_resource_role` VALUES (2463, 1556, 1);
INSERT INTO `as_resource_role` VALUES (2464, 1557, 1);
INSERT INTO `as_resource_role` VALUES (2465, 1558, 1);
INSERT INTO `as_resource_role` VALUES (2466, 1559, 1);
INSERT INTO `as_resource_role` VALUES (2467, 1560, 1);
INSERT INTO `as_resource_role` VALUES (2468, 1561, 1);
INSERT INTO `as_resource_role` VALUES (2469, 1562, 1);
INSERT INTO `as_resource_role` VALUES (2470, 1563, 1);
INSERT INTO `as_resource_role` VALUES (2471, 1564, 1);
INSERT INTO `as_resource_role` VALUES (2472, 1565, 1);
INSERT INTO `as_resource_role` VALUES (2473, 1566, 1);
INSERT INTO `as_resource_role` VALUES (2474, 1567, 1);
INSERT INTO `as_resource_role` VALUES (2475, 1568, 1);
INSERT INTO `as_resource_role` VALUES (2476, 1569, 1);
INSERT INTO `as_resource_role` VALUES (2477, 1570, 1);
INSERT INTO `as_resource_role` VALUES (2478, 1591, 1);
INSERT INTO `as_resource_role` VALUES (2479, 1592, 1);
INSERT INTO `as_resource_role` VALUES (2480, 1593, 1);
INSERT INTO `as_resource_role` VALUES (2481, 1594, 1);
INSERT INTO `as_resource_role` VALUES (2482, 1595, 1);
INSERT INTO `as_resource_role` VALUES (2483, 1596, 1);
INSERT INTO `as_resource_role` VALUES (2484, 1597, 1);
INSERT INTO `as_resource_role` VALUES (2485, 1598, 1);
INSERT INTO `as_resource_role` VALUES (2486, 1599, 1);
INSERT INTO `as_resource_role` VALUES (2487, 1600, 1);
INSERT INTO `as_resource_role` VALUES (2488, 1601, 1);
INSERT INTO `as_resource_role` VALUES (2489, 1602, 1);
INSERT INTO `as_resource_role` VALUES (2490, 1603, 1);
INSERT INTO `as_resource_role` VALUES (2491, 1604, 1);
INSERT INTO `as_resource_role` VALUES (2492, 1605, 1);
INSERT INTO `as_resource_role` VALUES (2493, 1606, 1);
INSERT INTO `as_resource_role` VALUES (2494, 1607, 1);
INSERT INTO `as_resource_role` VALUES (2495, 1608, 1);
INSERT INTO `as_resource_role` VALUES (2496, 1609, 1);
INSERT INTO `as_resource_role` VALUES (2497, 1610, 1);
INSERT INTO `as_resource_role` VALUES (2498, 1611, 1);
INSERT INTO `as_resource_role` VALUES (2499, 1612, 1);
INSERT INTO `as_resource_role` VALUES (2500, 1613, 1);
INSERT INTO `as_resource_role` VALUES (2501, 1614, 1);
INSERT INTO `as_resource_role` VALUES (2502, 1615, 1);
INSERT INTO `as_resource_role` VALUES (2503, 1616, 1);
INSERT INTO `as_resource_role` VALUES (2504, 1617, 1);
INSERT INTO `as_resource_role` VALUES (2505, 1618, 1);
INSERT INTO `as_resource_role` VALUES (2506, 1619, 1);
INSERT INTO `as_resource_role` VALUES (2507, 1620, 1);
INSERT INTO `as_resource_role` VALUES (2508, 1621, 1);
INSERT INTO `as_resource_role` VALUES (2509, 1622, 1);
INSERT INTO `as_resource_role` VALUES (2510, 1623, 1);
INSERT INTO `as_resource_role` VALUES (2511, 1624, 1);
INSERT INTO `as_resource_role` VALUES (2512, 1625, 1);
INSERT INTO `as_resource_role` VALUES (2513, 1626, 1);
INSERT INTO `as_resource_role` VALUES (2514, 1630, 1);
INSERT INTO `as_resource_role` VALUES (2515, 1631, 1);
INSERT INTO `as_resource_role` VALUES (2516, 1632, 1);
INSERT INTO `as_resource_role` VALUES (2517, 1633, 1);
INSERT INTO `as_resource_role` VALUES (2518, 1634, 1);
INSERT INTO `as_resource_role` VALUES (2519, 1635, 1);
INSERT INTO `as_resource_role` VALUES (2520, 1636, 1);
INSERT INTO `as_resource_role` VALUES (2521, 1637, 1);
INSERT INTO `as_resource_role` VALUES (2522, 1638, 1);
INSERT INTO `as_resource_role` VALUES (2523, 1639, 1);
INSERT INTO `as_resource_role` VALUES (2524, 1640, 1);
INSERT INTO `as_resource_role` VALUES (2525, 1641, 1);
INSERT INTO `as_resource_role` VALUES (2526, 1642, 1);
INSERT INTO `as_resource_role` VALUES (2527, 1643, 1);
INSERT INTO `as_resource_role` VALUES (2528, 1644, 1);
INSERT INTO `as_resource_role` VALUES (2529, 1645, 1);
INSERT INTO `as_resource_role` VALUES (2530, 1646, 1);
INSERT INTO `as_resource_role` VALUES (2531, 1647, 1);
INSERT INTO `as_resource_role` VALUES (2532, 1648, 1);
INSERT INTO `as_resource_role` VALUES (2533, 1649, 1);
INSERT INTO `as_resource_role` VALUES (2534, 1650, 1);
INSERT INTO `as_resource_role` VALUES (2535, 1651, 1);
INSERT INTO `as_resource_role` VALUES (2536, 1652, 1);
INSERT INTO `as_resource_role` VALUES (2537, 1654, 1);
INSERT INTO `as_resource_role` VALUES (2538, 1655, 1);
INSERT INTO `as_resource_role` VALUES (2539, 1656, 1);
INSERT INTO `as_resource_role` VALUES (2540, 1657, 1);
INSERT INTO `as_resource_role` VALUES (2541, 1658, 1);
INSERT INTO `as_resource_role` VALUES (2542, 1659, 1);
INSERT INTO `as_resource_role` VALUES (2543, 1660, 1);
INSERT INTO `as_resource_role` VALUES (2544, 1661, 1);
INSERT INTO `as_resource_role` VALUES (2545, 1662, 1);
INSERT INTO `as_resource_role` VALUES (2546, 1663, 1);
INSERT INTO `as_resource_role` VALUES (2547, 1664, 1);
INSERT INTO `as_resource_role` VALUES (2548, 1665, 1);
INSERT INTO `as_resource_role` VALUES (2549, 1669, 1);
INSERT INTO `as_resource_role` VALUES (2550, 1675, 65);
INSERT INTO `as_resource_role` VALUES (2551, 1676, 69);
INSERT INTO `as_resource_role` VALUES (2552, 1677, 69);
INSERT INTO `as_resource_role` VALUES (2553, 1678, 69);
INSERT INTO `as_resource_role` VALUES (2554, 1679, 69);
INSERT INTO `as_resource_role` VALUES (2555, 1680, 69);
INSERT INTO `as_resource_role` VALUES (2556, 1681, 69);
INSERT INTO `as_resource_role` VALUES (2557, 1682, 69);
INSERT INTO `as_resource_role` VALUES (2558, 1683, 69);
INSERT INTO `as_resource_role` VALUES (2559, 1684, 69);
INSERT INTO `as_resource_role` VALUES (2560, 1685, 69);
INSERT INTO `as_resource_role` VALUES (2561, 1686, 69);
INSERT INTO `as_resource_role` VALUES (2562, 1687, 69);
INSERT INTO `as_resource_role` VALUES (2563, 1676, 70);
INSERT INTO `as_resource_role` VALUES (2564, 1677, 70);
INSERT INTO `as_resource_role` VALUES (2565, 1678, 70);
INSERT INTO `as_resource_role` VALUES (2566, 1679, 70);
INSERT INTO `as_resource_role` VALUES (2567, 1680, 70);
INSERT INTO `as_resource_role` VALUES (2568, 1681, 70);
INSERT INTO `as_resource_role` VALUES (2569, 1682, 70);
INSERT INTO `as_resource_role` VALUES (2570, 1683, 70);
INSERT INTO `as_resource_role` VALUES (2571, 1684, 70);
INSERT INTO `as_resource_role` VALUES (2572, 1685, 70);
INSERT INTO `as_resource_role` VALUES (2573, 1686, 70);
INSERT INTO `as_resource_role` VALUES (2574, 1687, 70);
INSERT INTO `as_resource_role` VALUES (2575, 1524, 3);
INSERT INTO `as_resource_role` VALUES (2576, 1525, 3);
INSERT INTO `as_resource_role` VALUES (2577, 1536, 3);
INSERT INTO `as_resource_role` VALUES (2578, 1547, 3);
INSERT INTO `as_resource_role` VALUES (2579, 1548, 3);
INSERT INTO `as_resource_role` VALUES (2580, 1559, 3);
INSERT INTO `as_resource_role` VALUES (2581, 1560, 3);
INSERT INTO `as_resource_role` VALUES (2582, 1591, 3);
INSERT INTO `as_resource_role` VALUES (2583, 1592, 3);
INSERT INTO `as_resource_role` VALUES (2584, 1603, 3);
INSERT INTO `as_resource_role` VALUES (2585, 1604, 3);
INSERT INTO `as_resource_role` VALUES (2586, 1654, 3);
INSERT INTO `as_resource_role` VALUES (2587, 1655, 3);
INSERT INTO `as_resource_role` VALUES (2588, 1658, 3);
INSERT INTO `as_resource_role` VALUES (2589, 1660, 3);
INSERT INTO `as_resource_role` VALUES (2590, 1661, 3);
INSERT INTO `as_resource_role` VALUES (2591, 1662, 3);
INSERT INTO `as_resource_role` VALUES (2592, 1664, 3);
INSERT INTO `as_resource_role` VALUES (2593, 1665, 3);
INSERT INTO `as_resource_role` VALUES (2594, 1688, 1);
INSERT INTO `as_resource_role` VALUES (2595, 1689, 1);
INSERT INTO `as_resource_role` VALUES (2596, 1524, 2);
INSERT INTO `as_resource_role` VALUES (2597, 1525, 2);
INSERT INTO `as_resource_role` VALUES (2598, 1526, 2);
INSERT INTO `as_resource_role` VALUES (2599, 1527, 2);
INSERT INTO `as_resource_role` VALUES (2600, 1528, 2);
INSERT INTO `as_resource_role` VALUES (2601, 1529, 2);
INSERT INTO `as_resource_role` VALUES (2602, 1530, 2);
INSERT INTO `as_resource_role` VALUES (2603, 1531, 2);
INSERT INTO `as_resource_role` VALUES (2604, 1532, 2);
INSERT INTO `as_resource_role` VALUES (2605, 1533, 2);
INSERT INTO `as_resource_role` VALUES (2606, 1534, 2);
INSERT INTO `as_resource_role` VALUES (2607, 1535, 2);
INSERT INTO `as_resource_role` VALUES (2608, 1536, 2);
INSERT INTO `as_resource_role` VALUES (2609, 1537, 2);
INSERT INTO `as_resource_role` VALUES (2610, 1538, 2);
INSERT INTO `as_resource_role` VALUES (2611, 1539, 2);
INSERT INTO `as_resource_role` VALUES (2612, 1540, 2);
INSERT INTO `as_resource_role` VALUES (2613, 1541, 2);
INSERT INTO `as_resource_role` VALUES (2614, 1542, 2);
INSERT INTO `as_resource_role` VALUES (2615, 1543, 2);
INSERT INTO `as_resource_role` VALUES (2616, 1544, 2);
INSERT INTO `as_resource_role` VALUES (2617, 1545, 2);
INSERT INTO `as_resource_role` VALUES (2618, 1546, 2);
INSERT INTO `as_resource_role` VALUES (2619, 1547, 2);
INSERT INTO `as_resource_role` VALUES (2620, 1548, 2);
INSERT INTO `as_resource_role` VALUES (2621, 1549, 2);
INSERT INTO `as_resource_role` VALUES (2622, 1550, 2);
INSERT INTO `as_resource_role` VALUES (2623, 1551, 2);
INSERT INTO `as_resource_role` VALUES (2624, 1552, 2);
INSERT INTO `as_resource_role` VALUES (2625, 1553, 2);
INSERT INTO `as_resource_role` VALUES (2626, 1554, 2);
INSERT INTO `as_resource_role` VALUES (2627, 1555, 2);
INSERT INTO `as_resource_role` VALUES (2628, 1556, 2);
INSERT INTO `as_resource_role` VALUES (2629, 1557, 2);
INSERT INTO `as_resource_role` VALUES (2630, 1558, 2);
INSERT INTO `as_resource_role` VALUES (2631, 1559, 2);
INSERT INTO `as_resource_role` VALUES (2632, 1560, 2);
INSERT INTO `as_resource_role` VALUES (2633, 1561, 2);
INSERT INTO `as_resource_role` VALUES (2634, 1562, 2);
INSERT INTO `as_resource_role` VALUES (2635, 1563, 2);
INSERT INTO `as_resource_role` VALUES (2636, 1564, 2);
INSERT INTO `as_resource_role` VALUES (2637, 1565, 2);
INSERT INTO `as_resource_role` VALUES (2638, 1566, 2);
INSERT INTO `as_resource_role` VALUES (2639, 1567, 2);
INSERT INTO `as_resource_role` VALUES (2640, 1568, 2);
INSERT INTO `as_resource_role` VALUES (2641, 1569, 2);
INSERT INTO `as_resource_role` VALUES (2642, 1570, 2);
INSERT INTO `as_resource_role` VALUES (2643, 1591, 2);
INSERT INTO `as_resource_role` VALUES (2644, 1592, 2);
INSERT INTO `as_resource_role` VALUES (2645, 1593, 2);
INSERT INTO `as_resource_role` VALUES (2646, 1594, 2);
INSERT INTO `as_resource_role` VALUES (2647, 1595, 2);
INSERT INTO `as_resource_role` VALUES (2648, 1596, 2);
INSERT INTO `as_resource_role` VALUES (2649, 1597, 2);
INSERT INTO `as_resource_role` VALUES (2650, 1598, 2);
INSERT INTO `as_resource_role` VALUES (2651, 1599, 2);
INSERT INTO `as_resource_role` VALUES (2652, 1600, 2);
INSERT INTO `as_resource_role` VALUES (2653, 1601, 2);
INSERT INTO `as_resource_role` VALUES (2654, 1602, 2);
INSERT INTO `as_resource_role` VALUES (2655, 1603, 2);
INSERT INTO `as_resource_role` VALUES (2656, 1604, 2);
INSERT INTO `as_resource_role` VALUES (2657, 1605, 2);
INSERT INTO `as_resource_role` VALUES (2658, 1606, 2);
INSERT INTO `as_resource_role` VALUES (2659, 1607, 2);
INSERT INTO `as_resource_role` VALUES (2660, 1608, 2);
INSERT INTO `as_resource_role` VALUES (2661, 1609, 2);
INSERT INTO `as_resource_role` VALUES (2662, 1610, 2);
INSERT INTO `as_resource_role` VALUES (2663, 1611, 2);
INSERT INTO `as_resource_role` VALUES (2664, 1612, 2);
INSERT INTO `as_resource_role` VALUES (2665, 1613, 2);
INSERT INTO `as_resource_role` VALUES (2666, 1614, 2);
INSERT INTO `as_resource_role` VALUES (2667, 1654, 2);
INSERT INTO `as_resource_role` VALUES (2668, 1655, 2);
INSERT INTO `as_resource_role` VALUES (2669, 1656, 2);
INSERT INTO `as_resource_role` VALUES (2670, 1657, 2);
INSERT INTO `as_resource_role` VALUES (2671, 1658, 2);
INSERT INTO `as_resource_role` VALUES (2672, 1659, 2);
INSERT INTO `as_resource_role` VALUES (2673, 1660, 2);
INSERT INTO `as_resource_role` VALUES (2674, 1661, 2);
INSERT INTO `as_resource_role` VALUES (2675, 1662, 2);
INSERT INTO `as_resource_role` VALUES (2676, 1663, 2);
INSERT INTO `as_resource_role` VALUES (2677, 1664, 2);
INSERT INTO `as_resource_role` VALUES (2678, 1665, 2);
INSERT INTO `as_resource_role` VALUES (2679, 1690, 1);
INSERT INTO `as_resource_role` VALUES (2680, 1691, 1);
INSERT INTO `as_resource_role` VALUES (2681, 1692, 1);
INSERT INTO `as_resource_role` VALUES (2682, 1693, 1);
INSERT INTO `as_resource_role` VALUES (2683, 1694, 1);
INSERT INTO `as_resource_role` VALUES (2684, 1695, 1);
INSERT INTO `as_resource_role` VALUES (2685, 1696, 1);
INSERT INTO `as_resource_role` VALUES (2686, 1697, 1);
INSERT INTO `as_resource_role` VALUES (2687, 1698, 1);
INSERT INTO `as_resource_role` VALUES (2688, 1699, 1);
INSERT INTO `as_resource_role` VALUES (2689, 1700, 1);
INSERT INTO `as_resource_role` VALUES (2690, 1701, 1);
INSERT INTO `as_resource_role` VALUES (2691, 1702, 71);
INSERT INTO `as_resource_role` VALUES (2692, 1703, 71);
INSERT INTO `as_resource_role` VALUES (2693, 1704, 71);
INSERT INTO `as_resource_role` VALUES (2694, 1705, 71);
INSERT INTO `as_resource_role` VALUES (2695, 1706, 71);
INSERT INTO `as_resource_role` VALUES (2696, 1707, 71);
INSERT INTO `as_resource_role` VALUES (2697, 1708, 71);
INSERT INTO `as_resource_role` VALUES (2698, 1709, 71);
INSERT INTO `as_resource_role` VALUES (2699, 1710, 71);
INSERT INTO `as_resource_role` VALUES (2700, 1711, 71);
INSERT INTO `as_resource_role` VALUES (2701, 1712, 71);
INSERT INTO `as_resource_role` VALUES (2702, 1713, 71);
INSERT INTO `as_resource_role` VALUES (2715, 1714, 71);
INSERT INTO `as_resource_role` VALUES (2716, 1715, 71);
INSERT INTO `as_resource_role` VALUES (2717, 1716, 71);
INSERT INTO `as_resource_role` VALUES (2718, 1717, 71);
INSERT INTO `as_resource_role` VALUES (2719, 1718, 71);
INSERT INTO `as_resource_role` VALUES (2720, 1719, 71);
INSERT INTO `as_resource_role` VALUES (2721, 1720, 71);
INSERT INTO `as_resource_role` VALUES (2722, 1721, 71);
INSERT INTO `as_resource_role` VALUES (2723, 1722, 71);
INSERT INTO `as_resource_role` VALUES (2724, 1723, 71);
INSERT INTO `as_resource_role` VALUES (2725, 1724, 71);
INSERT INTO `as_resource_role` VALUES (2749, 1725, 71);
INSERT INTO `as_resource_role` VALUES (2750, 1726, 71);
INSERT INTO `as_resource_role` VALUES (2751, 1727, 71);
INSERT INTO `as_resource_role` VALUES (2752, 1728, 71);
INSERT INTO `as_resource_role` VALUES (2753, 1729, 71);
INSERT INTO `as_resource_role` VALUES (2754, 1730, 71);
INSERT INTO `as_resource_role` VALUES (2755, 1731, 71);
INSERT INTO `as_resource_role` VALUES (2756, 1732, 71);
INSERT INTO `as_resource_role` VALUES (2757, 1733, 71);
INSERT INTO `as_resource_role` VALUES (2758, 1734, 71);
INSERT INTO `as_resource_role` VALUES (2759, 1735, 71);
INSERT INTO `as_resource_role` VALUES (2760, 1736, 71);
INSERT INTO `as_resource_role` VALUES (2796, 1737, 71);
INSERT INTO `as_resource_role` VALUES (2797, 1738, 71);
INSERT INTO `as_resource_role` VALUES (2798, 1739, 71);
INSERT INTO `as_resource_role` VALUES (2799, 1740, 71);
INSERT INTO `as_resource_role` VALUES (2800, 1741, 71);
INSERT INTO `as_resource_role` VALUES (2801, 1742, 71);
INSERT INTO `as_resource_role` VALUES (2802, 1743, 71);
INSERT INTO `as_resource_role` VALUES (2803, 1744, 71);
INSERT INTO `as_resource_role` VALUES (2804, 1745, 71);
INSERT INTO `as_resource_role` VALUES (2805, 1746, 71);
INSERT INTO `as_resource_role` VALUES (2806, 1747, 71);
INSERT INTO `as_resource_role` VALUES (2807, 1702, 72);
INSERT INTO `as_resource_role` VALUES (2808, 1703, 72);
INSERT INTO `as_resource_role` VALUES (2809, 1704, 72);
INSERT INTO `as_resource_role` VALUES (2810, 1705, 72);
INSERT INTO `as_resource_role` VALUES (2811, 1706, 72);
INSERT INTO `as_resource_role` VALUES (2812, 1707, 72);
INSERT INTO `as_resource_role` VALUES (2813, 1708, 72);
INSERT INTO `as_resource_role` VALUES (2814, 1709, 72);
INSERT INTO `as_resource_role` VALUES (2815, 1710, 72);
INSERT INTO `as_resource_role` VALUES (2816, 1711, 72);
INSERT INTO `as_resource_role` VALUES (2817, 1712, 72);
INSERT INTO `as_resource_role` VALUES (2818, 1713, 72);
INSERT INTO `as_resource_role` VALUES (2819, 1714, 72);
INSERT INTO `as_resource_role` VALUES (2820, 1715, 72);
INSERT INTO `as_resource_role` VALUES (2821, 1716, 72);
INSERT INTO `as_resource_role` VALUES (2822, 1717, 72);
INSERT INTO `as_resource_role` VALUES (2823, 1718, 72);
INSERT INTO `as_resource_role` VALUES (2824, 1719, 72);
INSERT INTO `as_resource_role` VALUES (2825, 1720, 72);
INSERT INTO `as_resource_role` VALUES (2826, 1721, 72);
INSERT INTO `as_resource_role` VALUES (2827, 1722, 72);
INSERT INTO `as_resource_role` VALUES (2828, 1723, 72);
INSERT INTO `as_resource_role` VALUES (2829, 1724, 72);
INSERT INTO `as_resource_role` VALUES (2830, 1725, 72);
INSERT INTO `as_resource_role` VALUES (2831, 1726, 72);
INSERT INTO `as_resource_role` VALUES (2832, 1727, 72);
INSERT INTO `as_resource_role` VALUES (2833, 1728, 72);
INSERT INTO `as_resource_role` VALUES (2834, 1729, 72);
INSERT INTO `as_resource_role` VALUES (2835, 1730, 72);
INSERT INTO `as_resource_role` VALUES (2836, 1731, 72);
INSERT INTO `as_resource_role` VALUES (2837, 1732, 72);
INSERT INTO `as_resource_role` VALUES (2838, 1733, 72);
INSERT INTO `as_resource_role` VALUES (2839, 1734, 72);
INSERT INTO `as_resource_role` VALUES (2840, 1735, 72);
INSERT INTO `as_resource_role` VALUES (2841, 1736, 72);
INSERT INTO `as_resource_role` VALUES (2842, 1737, 72);
INSERT INTO `as_resource_role` VALUES (2843, 1738, 72);
INSERT INTO `as_resource_role` VALUES (2844, 1739, 72);
INSERT INTO `as_resource_role` VALUES (2845, 1740, 72);
INSERT INTO `as_resource_role` VALUES (2846, 1741, 72);
INSERT INTO `as_resource_role` VALUES (2847, 1742, 72);
INSERT INTO `as_resource_role` VALUES (2848, 1743, 72);
INSERT INTO `as_resource_role` VALUES (2849, 1744, 72);
INSERT INTO `as_resource_role` VALUES (2850, 1745, 72);
INSERT INTO `as_resource_role` VALUES (2851, 1746, 72);
INSERT INTO `as_resource_role` VALUES (2852, 1747, 72);
INSERT INTO `as_resource_role` VALUES (2853, 1748, 73);
INSERT INTO `as_resource_role` VALUES (2854, 1749, 73);
INSERT INTO `as_resource_role` VALUES (2855, 1750, 73);
INSERT INTO `as_resource_role` VALUES (2856, 1751, 73);
INSERT INTO `as_resource_role` VALUES (2857, 1752, 73);
INSERT INTO `as_resource_role` VALUES (2858, 1753, 73);
INSERT INTO `as_resource_role` VALUES (2859, 1754, 73);
INSERT INTO `as_resource_role` VALUES (2860, 1755, 73);
INSERT INTO `as_resource_role` VALUES (2861, 1756, 73);
INSERT INTO `as_resource_role` VALUES (2862, 1757, 73);
INSERT INTO `as_resource_role` VALUES (2863, 1758, 73);
INSERT INTO `as_resource_role` VALUES (2864, 1759, 73);
INSERT INTO `as_resource_role` VALUES (2865, 1748, 74);
INSERT INTO `as_resource_role` VALUES (2866, 1749, 74);
INSERT INTO `as_resource_role` VALUES (2867, 1750, 74);
INSERT INTO `as_resource_role` VALUES (2868, 1751, 74);
INSERT INTO `as_resource_role` VALUES (2869, 1752, 74);
INSERT INTO `as_resource_role` VALUES (2870, 1753, 74);
INSERT INTO `as_resource_role` VALUES (2871, 1754, 74);
INSERT INTO `as_resource_role` VALUES (2872, 1755, 74);
INSERT INTO `as_resource_role` VALUES (2873, 1756, 74);
INSERT INTO `as_resource_role` VALUES (2874, 1757, 74);
INSERT INTO `as_resource_role` VALUES (2875, 1758, 74);
INSERT INTO `as_resource_role` VALUES (2876, 1759, 74);
INSERT INTO `as_resource_role` VALUES (2877, 1760, 1);
INSERT INTO `as_resource_role` VALUES (2878, 1761, 1);
INSERT INTO `as_resource_role` VALUES (2879, 1762, 1);
INSERT INTO `as_resource_role` VALUES (2880, 1763, 1);
INSERT INTO `as_resource_role` VALUES (2881, 1764, 1);
INSERT INTO `as_resource_role` VALUES (2882, 1765, 1);
INSERT INTO `as_resource_role` VALUES (2883, 1766, 1);
INSERT INTO `as_resource_role` VALUES (2884, 1767, 1);
INSERT INTO `as_resource_role` VALUES (2885, 1768, 1);
INSERT INTO `as_resource_role` VALUES (2886, 1769, 1);
INSERT INTO `as_resource_role` VALUES (2887, 1770, 1);
INSERT INTO `as_resource_role` VALUES (2888, 1771, 1);
INSERT INTO `as_resource_role` VALUES (2889, 1772, 1);
INSERT INTO `as_resource_role` VALUES (2890, 1773, 1);
INSERT INTO `as_resource_role` VALUES (2891, 1774, 1);
INSERT INTO `as_resource_role` VALUES (2892, 1775, 1);
INSERT INTO `as_resource_role` VALUES (2893, 1776, 1);
INSERT INTO `as_resource_role` VALUES (2894, 1777, 1);
INSERT INTO `as_resource_role` VALUES (2895, 1778, 1);
INSERT INTO `as_resource_role` VALUES (2896, 1779, 1);
INSERT INTO `as_resource_role` VALUES (2897, 1780, 1);
INSERT INTO `as_resource_role` VALUES (2898, 1781, 1);
INSERT INTO `as_resource_role` VALUES (2899, 1782, 1);
INSERT INTO `as_resource_role` VALUES (2900, 1783, 75);
INSERT INTO `as_resource_role` VALUES (2901, 1784, 75);
INSERT INTO `as_resource_role` VALUES (2902, 1785, 75);
INSERT INTO `as_resource_role` VALUES (2903, 1786, 75);
INSERT INTO `as_resource_role` VALUES (2904, 1787, 75);
INSERT INTO `as_resource_role` VALUES (2905, 1788, 75);
INSERT INTO `as_resource_role` VALUES (2906, 1789, 75);
INSERT INTO `as_resource_role` VALUES (2907, 1790, 75);
INSERT INTO `as_resource_role` VALUES (2908, 1791, 75);
INSERT INTO `as_resource_role` VALUES (2909, 1792, 75);
INSERT INTO `as_resource_role` VALUES (2910, 1793, 75);
INSERT INTO `as_resource_role` VALUES (2911, 1794, 75);
INSERT INTO `as_resource_role` VALUES (2912, 1795, 75);
INSERT INTO `as_resource_role` VALUES (2913, 1784, 76);
INSERT INTO `as_resource_role` VALUES (2914, 1785, 76);
INSERT INTO `as_resource_role` VALUES (2915, 1796, 77);
INSERT INTO `as_resource_role` VALUES (2916, 1797, 77);
INSERT INTO `as_resource_role` VALUES (2917, 1798, 77);
INSERT INTO `as_resource_role` VALUES (2918, 1799, 77);
INSERT INTO `as_resource_role` VALUES (2919, 1800, 77);
INSERT INTO `as_resource_role` VALUES (2920, 1801, 77);
INSERT INTO `as_resource_role` VALUES (2921, 1802, 77);
INSERT INTO `as_resource_role` VALUES (2922, 1803, 77);
INSERT INTO `as_resource_role` VALUES (2923, 1804, 77);
INSERT INTO `as_resource_role` VALUES (2924, 1805, 77);
INSERT INTO `as_resource_role` VALUES (2925, 1806, 77);
INSERT INTO `as_resource_role` VALUES (2926, 1807, 77);
INSERT INTO `as_resource_role` VALUES (2927, 1796, 78);
INSERT INTO `as_resource_role` VALUES (2928, 1797, 78);
INSERT INTO `as_resource_role` VALUES (2929, 1798, 78);
INSERT INTO `as_resource_role` VALUES (2930, 1799, 78);
INSERT INTO `as_resource_role` VALUES (2931, 1800, 78);
INSERT INTO `as_resource_role` VALUES (2932, 1801, 78);
INSERT INTO `as_resource_role` VALUES (2933, 1802, 78);
INSERT INTO `as_resource_role` VALUES (2934, 1803, 78);
INSERT INTO `as_resource_role` VALUES (2935, 1804, 78);
INSERT INTO `as_resource_role` VALUES (2936, 1805, 78);
INSERT INTO `as_resource_role` VALUES (2937, 1806, 78);
INSERT INTO `as_resource_role` VALUES (2938, 1807, 78);
INSERT INTO `as_resource_role` VALUES (2939, 1808, 79);
INSERT INTO `as_resource_role` VALUES (2940, 1809, 79);
INSERT INTO `as_resource_role` VALUES (2941, 1810, 79);
INSERT INTO `as_resource_role` VALUES (2942, 1811, 79);
INSERT INTO `as_resource_role` VALUES (2943, 1812, 79);
INSERT INTO `as_resource_role` VALUES (2944, 1813, 79);
INSERT INTO `as_resource_role` VALUES (2945, 1814, 79);
INSERT INTO `as_resource_role` VALUES (2946, 1815, 79);
INSERT INTO `as_resource_role` VALUES (2947, 1816, 79);
INSERT INTO `as_resource_role` VALUES (2948, 1817, 79);
INSERT INTO `as_resource_role` VALUES (2949, 1818, 79);
INSERT INTO `as_resource_role` VALUES (2950, 1819, 79);
INSERT INTO `as_resource_role` VALUES (2951, 1808, 80);
INSERT INTO `as_resource_role` VALUES (2952, 1809, 80);
INSERT INTO `as_resource_role` VALUES (2953, 1810, 80);
INSERT INTO `as_resource_role` VALUES (2954, 1811, 80);
INSERT INTO `as_resource_role` VALUES (2955, 1812, 80);
INSERT INTO `as_resource_role` VALUES (2956, 1813, 80);
INSERT INTO `as_resource_role` VALUES (2957, 1814, 80);
INSERT INTO `as_resource_role` VALUES (2958, 1815, 80);
INSERT INTO `as_resource_role` VALUES (2959, 1816, 80);
INSERT INTO `as_resource_role` VALUES (2960, 1817, 80);
INSERT INTO `as_resource_role` VALUES (2961, 1818, 80);
INSERT INTO `as_resource_role` VALUES (2962, 1819, 80);
INSERT INTO `as_resource_role` VALUES (2963, 1820, 1);
INSERT INTO `as_resource_role` VALUES (2964, 1821, 1);
INSERT INTO `as_resource_role` VALUES (2965, 1822, 1);
INSERT INTO `as_resource_role` VALUES (2966, 1823, 1);
INSERT INTO `as_resource_role` VALUES (2967, 1824, 1);
INSERT INTO `as_resource_role` VALUES (2968, 1825, 1);
INSERT INTO `as_resource_role` VALUES (2969, 1826, 1);
INSERT INTO `as_resource_role` VALUES (2970, 1827, 1);
INSERT INTO `as_resource_role` VALUES (2971, 1828, 1);
INSERT INTO `as_resource_role` VALUES (2972, 1829, 1);
INSERT INTO `as_resource_role` VALUES (2973, 1830, 1);
INSERT INTO `as_resource_role` VALUES (2974, 1831, 1);
INSERT INTO `as_resource_role` VALUES (2975, 1832, 1);
INSERT INTO `as_resource_role` VALUES (2976, 1833, 1);
INSERT INTO `as_resource_role` VALUES (3036, 1857, 79);
INSERT INTO `as_resource_role` VALUES (3037, 1858, 79);
INSERT INTO `as_resource_role` VALUES (3038, 1859, 1);
INSERT INTO `as_resource_role` VALUES (3185, 1905, 1);
INSERT INTO `as_resource_role` VALUES (3186, 1906, 1);
INSERT INTO `as_resource_role` VALUES (3187, 1907, 1);
INSERT INTO `as_resource_role` VALUES (3188, 1908, 1);
INSERT INTO `as_resource_role` VALUES (3189, 1909, 1);
INSERT INTO `as_resource_role` VALUES (3190, 1910, 1);
INSERT INTO `as_resource_role` VALUES (3191, 1911, 1);
INSERT INTO `as_resource_role` VALUES (3192, 1912, 1);
INSERT INTO `as_resource_role` VALUES (3193, 1913, 1);
INSERT INTO `as_resource_role` VALUES (3194, 1914, 1);
INSERT INTO `as_resource_role` VALUES (3195, 1915, 1);
INSERT INTO `as_resource_role` VALUES (3196, 1916, 1);
INSERT INTO `as_resource_role` VALUES (3342, 1928, 27);
INSERT INTO `as_resource_role` VALUES (3579, 1940, 81);
INSERT INTO `as_resource_role` VALUES (3580, 1941, 81);
INSERT INTO `as_resource_role` VALUES (3581, 1942, 81);
INSERT INTO `as_resource_role` VALUES (3582, 1943, 81);
INSERT INTO `as_resource_role` VALUES (3583, 1944, 81);
INSERT INTO `as_resource_role` VALUES (3584, 1945, 81);
INSERT INTO `as_resource_role` VALUES (3585, 1946, 81);
INSERT INTO `as_resource_role` VALUES (3586, 1947, 81);
INSERT INTO `as_resource_role` VALUES (3587, 1948, 81);
INSERT INTO `as_resource_role` VALUES (3588, 1949, 81);
INSERT INTO `as_resource_role` VALUES (3589, 1950, 81);
INSERT INTO `as_resource_role` VALUES (3690, 1951, 81);
INSERT INTO `as_resource_role` VALUES (3691, 1952, 81);
INSERT INTO `as_resource_role` VALUES (3692, 1953, 81);
INSERT INTO `as_resource_role` VALUES (3693, 1954, 81);
INSERT INTO `as_resource_role` VALUES (3694, 1955, 81);
INSERT INTO `as_resource_role` VALUES (3695, 1956, 81);
INSERT INTO `as_resource_role` VALUES (3696, 1957, 81);
INSERT INTO `as_resource_role` VALUES (3697, 1958, 81);
INSERT INTO `as_resource_role` VALUES (3698, 1959, 81);
INSERT INTO `as_resource_role` VALUES (3699, 1960, 81);
INSERT INTO `as_resource_role` VALUES (3700, 1961, 81);
INSERT INTO `as_resource_role` VALUES (3812, 1962, 81);
INSERT INTO `as_resource_role` VALUES (3813, 1963, 81);
INSERT INTO `as_resource_role` VALUES (3814, 1964, 81);
INSERT INTO `as_resource_role` VALUES (3815, 1965, 81);
INSERT INTO `as_resource_role` VALUES (3816, 1966, 81);
INSERT INTO `as_resource_role` VALUES (3817, 1967, 81);
INSERT INTO `as_resource_role` VALUES (3818, 1968, 81);
INSERT INTO `as_resource_role` VALUES (3819, 1969, 81);
INSERT INTO `as_resource_role` VALUES (3820, 1970, 81);
INSERT INTO `as_resource_role` VALUES (3821, 1971, 81);
INSERT INTO `as_resource_role` VALUES (3822, 1972, 81);
INSERT INTO `as_resource_role` VALUES (3945, 1973, 81);
INSERT INTO `as_resource_role` VALUES (3946, 1974, 81);
INSERT INTO `as_resource_role` VALUES (3947, 1975, 81);
INSERT INTO `as_resource_role` VALUES (3948, 1976, 81);
INSERT INTO `as_resource_role` VALUES (3949, 1977, 81);
INSERT INTO `as_resource_role` VALUES (3950, 1978, 81);
INSERT INTO `as_resource_role` VALUES (3951, 1979, 81);
INSERT INTO `as_resource_role` VALUES (3952, 1980, 81);
INSERT INTO `as_resource_role` VALUES (3953, 1981, 81);
INSERT INTO `as_resource_role` VALUES (3954, 1982, 81);
INSERT INTO `as_resource_role` VALUES (3955, 1983, 81);
INSERT INTO `as_resource_role` VALUES (4089, 1984, 81);
INSERT INTO `as_resource_role` VALUES (4090, 1985, 81);
INSERT INTO `as_resource_role` VALUES (4091, 1986, 81);
INSERT INTO `as_resource_role` VALUES (4092, 1987, 81);
INSERT INTO `as_resource_role` VALUES (4093, 1988, 81);
INSERT INTO `as_resource_role` VALUES (4094, 1989, 81);
INSERT INTO `as_resource_role` VALUES (4095, 1990, 81);
INSERT INTO `as_resource_role` VALUES (4096, 1991, 81);
INSERT INTO `as_resource_role` VALUES (4097, 1992, 81);
INSERT INTO `as_resource_role` VALUES (4098, 1993, 81);
INSERT INTO `as_resource_role` VALUES (4099, 1994, 81);
INSERT INTO `as_resource_role` VALUES (4244, 1995, 81);
INSERT INTO `as_resource_role` VALUES (4245, 1996, 81);
INSERT INTO `as_resource_role` VALUES (4246, 1997, 81);
INSERT INTO `as_resource_role` VALUES (4247, 1998, 81);
INSERT INTO `as_resource_role` VALUES (4248, 1999, 81);
INSERT INTO `as_resource_role` VALUES (4249, 2000, 81);
INSERT INTO `as_resource_role` VALUES (4250, 2001, 81);
INSERT INTO `as_resource_role` VALUES (4251, 2002, 81);
INSERT INTO `as_resource_role` VALUES (4252, 2003, 81);
INSERT INTO `as_resource_role` VALUES (4253, 2004, 81);
INSERT INTO `as_resource_role` VALUES (4254, 2005, 81);
INSERT INTO `as_resource_role` VALUES (4255, 2006, 81);
INSERT INTO `as_resource_role` VALUES (4256, 2007, 81);
INSERT INTO `as_resource_role` VALUES (4257, 2008, 81);
INSERT INTO `as_resource_role` VALUES (4258, 2009, 81);
INSERT INTO `as_resource_role` VALUES (4259, 2010, 81);
INSERT INTO `as_resource_role` VALUES (4260, 2011, 81);
INSERT INTO `as_resource_role` VALUES (4261, 2012, 81);
INSERT INTO `as_resource_role` VALUES (4262, 2013, 81);
INSERT INTO `as_resource_role` VALUES (4263, 2014, 81);
INSERT INTO `as_resource_role` VALUES (4264, 2015, 81);
INSERT INTO `as_resource_role` VALUES (4265, 2016, 81);
INSERT INTO `as_resource_role` VALUES (4266, 1834, 82);
INSERT INTO `as_resource_role` VALUES (4267, 1835, 82);
INSERT INTO `as_resource_role` VALUES (4268, 1836, 82);
INSERT INTO `as_resource_role` VALUES (4269, 1837, 82);
INSERT INTO `as_resource_role` VALUES (4270, 1838, 82);
INSERT INTO `as_resource_role` VALUES (4271, 1839, 82);
INSERT INTO `as_resource_role` VALUES (4272, 1840, 82);
INSERT INTO `as_resource_role` VALUES (4273, 1841, 82);
INSERT INTO `as_resource_role` VALUES (4274, 1842, 82);
INSERT INTO `as_resource_role` VALUES (4275, 1843, 82);
INSERT INTO `as_resource_role` VALUES (4276, 1844, 82);
INSERT INTO `as_resource_role` VALUES (4277, 1845, 82);
INSERT INTO `as_resource_role` VALUES (4278, 1846, 82);
INSERT INTO `as_resource_role` VALUES (4279, 1847, 82);
INSERT INTO `as_resource_role` VALUES (4280, 1848, 82);
INSERT INTO `as_resource_role` VALUES (4281, 1849, 82);
INSERT INTO `as_resource_role` VALUES (4282, 1850, 82);
INSERT INTO `as_resource_role` VALUES (4283, 1851, 82);
INSERT INTO `as_resource_role` VALUES (4284, 1852, 82);
INSERT INTO `as_resource_role` VALUES (4285, 1853, 82);
INSERT INTO `as_resource_role` VALUES (4286, 1854, 82);
INSERT INTO `as_resource_role` VALUES (4287, 1855, 82);
INSERT INTO `as_resource_role` VALUES (4288, 1856, 82);
INSERT INTO `as_resource_role` VALUES (4289, 1861, 82);
INSERT INTO `as_resource_role` VALUES (4290, 1862, 82);
INSERT INTO `as_resource_role` VALUES (4291, 1863, 82);
INSERT INTO `as_resource_role` VALUES (4292, 1864, 82);
INSERT INTO `as_resource_role` VALUES (4293, 1865, 82);
INSERT INTO `as_resource_role` VALUES (4294, 1866, 82);
INSERT INTO `as_resource_role` VALUES (4295, 1867, 82);
INSERT INTO `as_resource_role` VALUES (4296, 1868, 82);
INSERT INTO `as_resource_role` VALUES (4297, 1869, 82);
INSERT INTO `as_resource_role` VALUES (4298, 1870, 82);
INSERT INTO `as_resource_role` VALUES (4299, 1871, 82);
INSERT INTO `as_resource_role` VALUES (4300, 1872, 82);
INSERT INTO `as_resource_role` VALUES (4301, 1873, 82);
INSERT INTO `as_resource_role` VALUES (4302, 1874, 82);
INSERT INTO `as_resource_role` VALUES (4303, 1875, 82);
INSERT INTO `as_resource_role` VALUES (4304, 1876, 82);
INSERT INTO `as_resource_role` VALUES (4305, 1877, 82);
INSERT INTO `as_resource_role` VALUES (4306, 1878, 82);
INSERT INTO `as_resource_role` VALUES (4307, 1879, 82);
INSERT INTO `as_resource_role` VALUES (4308, 1880, 82);
INSERT INTO `as_resource_role` VALUES (4309, 1881, 82);
INSERT INTO `as_resource_role` VALUES (4310, 1882, 82);
INSERT INTO `as_resource_role` VALUES (4311, 1883, 82);
INSERT INTO `as_resource_role` VALUES (4312, 1884, 82);
INSERT INTO `as_resource_role` VALUES (4313, 1885, 82);
INSERT INTO `as_resource_role` VALUES (4314, 1886, 82);
INSERT INTO `as_resource_role` VALUES (4315, 1887, 82);
INSERT INTO `as_resource_role` VALUES (4316, 1888, 82);
INSERT INTO `as_resource_role` VALUES (4317, 1889, 82);
INSERT INTO `as_resource_role` VALUES (4318, 1890, 82);
INSERT INTO `as_resource_role` VALUES (4319, 1891, 82);
INSERT INTO `as_resource_role` VALUES (4320, 1892, 82);
INSERT INTO `as_resource_role` VALUES (4321, 1893, 82);
INSERT INTO `as_resource_role` VALUES (4322, 1894, 82);
INSERT INTO `as_resource_role` VALUES (4323, 1895, 82);
INSERT INTO `as_resource_role` VALUES (4324, 1896, 82);
INSERT INTO `as_resource_role` VALUES (4325, 1897, 82);
INSERT INTO `as_resource_role` VALUES (4326, 1898, 82);
INSERT INTO `as_resource_role` VALUES (4327, 1899, 82);
INSERT INTO `as_resource_role` VALUES (4328, 1900, 82);
INSERT INTO `as_resource_role` VALUES (4329, 1901, 82);
INSERT INTO `as_resource_role` VALUES (4330, 1902, 82);
INSERT INTO `as_resource_role` VALUES (4331, 1903, 82);
INSERT INTO `as_resource_role` VALUES (4332, 1904, 82);
INSERT INTO `as_resource_role` VALUES (4333, 1917, 82);
INSERT INTO `as_resource_role` VALUES (4334, 1918, 82);
INSERT INTO `as_resource_role` VALUES (4335, 1919, 82);
INSERT INTO `as_resource_role` VALUES (4336, 1920, 82);
INSERT INTO `as_resource_role` VALUES (4337, 1921, 82);
INSERT INTO `as_resource_role` VALUES (4338, 1922, 82);
INSERT INTO `as_resource_role` VALUES (4339, 1923, 82);
INSERT INTO `as_resource_role` VALUES (4340, 1924, 82);
INSERT INTO `as_resource_role` VALUES (4341, 1925, 82);
INSERT INTO `as_resource_role` VALUES (4342, 1926, 82);
INSERT INTO `as_resource_role` VALUES (4343, 1927, 82);
INSERT INTO `as_resource_role` VALUES (4344, 1929, 82);
INSERT INTO `as_resource_role` VALUES (4345, 1930, 82);
INSERT INTO `as_resource_role` VALUES (4346, 1931, 82);
INSERT INTO `as_resource_role` VALUES (4347, 1932, 82);
INSERT INTO `as_resource_role` VALUES (4348, 1933, 82);
INSERT INTO `as_resource_role` VALUES (4349, 1934, 82);
INSERT INTO `as_resource_role` VALUES (4350, 1935, 82);
INSERT INTO `as_resource_role` VALUES (4351, 1936, 82);
INSERT INTO `as_resource_role` VALUES (4352, 1937, 82);
INSERT INTO `as_resource_role` VALUES (4353, 1938, 82);
INSERT INTO `as_resource_role` VALUES (4354, 1939, 82);
INSERT INTO `as_resource_role` VALUES (4355, 1940, 82);
INSERT INTO `as_resource_role` VALUES (4356, 1941, 82);
INSERT INTO `as_resource_role` VALUES (4357, 1942, 82);
INSERT INTO `as_resource_role` VALUES (4358, 1943, 82);
INSERT INTO `as_resource_role` VALUES (4359, 1944, 82);
INSERT INTO `as_resource_role` VALUES (4360, 1945, 82);
INSERT INTO `as_resource_role` VALUES (4361, 1946, 82);
INSERT INTO `as_resource_role` VALUES (4362, 1947, 82);
INSERT INTO `as_resource_role` VALUES (4363, 1948, 82);
INSERT INTO `as_resource_role` VALUES (4364, 1949, 82);
INSERT INTO `as_resource_role` VALUES (4365, 1950, 82);
INSERT INTO `as_resource_role` VALUES (4366, 1951, 82);
INSERT INTO `as_resource_role` VALUES (4367, 1952, 82);
INSERT INTO `as_resource_role` VALUES (4368, 1953, 82);
INSERT INTO `as_resource_role` VALUES (4369, 1954, 82);
INSERT INTO `as_resource_role` VALUES (4370, 1955, 82);
INSERT INTO `as_resource_role` VALUES (4371, 1956, 82);
INSERT INTO `as_resource_role` VALUES (4372, 1957, 82);
INSERT INTO `as_resource_role` VALUES (4373, 1958, 82);
INSERT INTO `as_resource_role` VALUES (4374, 1959, 82);
INSERT INTO `as_resource_role` VALUES (4375, 1960, 82);
INSERT INTO `as_resource_role` VALUES (4376, 1961, 82);
INSERT INTO `as_resource_role` VALUES (4377, 1962, 82);
INSERT INTO `as_resource_role` VALUES (4378, 1963, 82);
INSERT INTO `as_resource_role` VALUES (4379, 1964, 82);
INSERT INTO `as_resource_role` VALUES (4380, 1965, 82);
INSERT INTO `as_resource_role` VALUES (4381, 1966, 82);
INSERT INTO `as_resource_role` VALUES (4382, 1967, 82);
INSERT INTO `as_resource_role` VALUES (4383, 1968, 82);
INSERT INTO `as_resource_role` VALUES (4384, 1969, 82);
INSERT INTO `as_resource_role` VALUES (4385, 1970, 82);
INSERT INTO `as_resource_role` VALUES (4386, 1971, 82);
INSERT INTO `as_resource_role` VALUES (4387, 1972, 82);
INSERT INTO `as_resource_role` VALUES (4388, 1973, 82);
INSERT INTO `as_resource_role` VALUES (4389, 1974, 82);
INSERT INTO `as_resource_role` VALUES (4390, 1975, 82);
INSERT INTO `as_resource_role` VALUES (4391, 1976, 82);
INSERT INTO `as_resource_role` VALUES (4392, 1977, 82);
INSERT INTO `as_resource_role` VALUES (4393, 1978, 82);
INSERT INTO `as_resource_role` VALUES (4394, 1979, 82);
INSERT INTO `as_resource_role` VALUES (4395, 1980, 82);
INSERT INTO `as_resource_role` VALUES (4396, 1981, 82);
INSERT INTO `as_resource_role` VALUES (4397, 1982, 82);
INSERT INTO `as_resource_role` VALUES (4398, 1983, 82);
INSERT INTO `as_resource_role` VALUES (4399, 1984, 82);
INSERT INTO `as_resource_role` VALUES (4400, 1985, 82);
INSERT INTO `as_resource_role` VALUES (4401, 1986, 82);
INSERT INTO `as_resource_role` VALUES (4402, 1987, 82);
INSERT INTO `as_resource_role` VALUES (4403, 1988, 82);
INSERT INTO `as_resource_role` VALUES (4404, 1989, 82);
INSERT INTO `as_resource_role` VALUES (4405, 1990, 82);
INSERT INTO `as_resource_role` VALUES (4406, 1991, 82);
INSERT INTO `as_resource_role` VALUES (4407, 1992, 82);
INSERT INTO `as_resource_role` VALUES (4408, 1993, 82);
INSERT INTO `as_resource_role` VALUES (4409, 1994, 82);
INSERT INTO `as_resource_role` VALUES (4410, 1995, 82);
INSERT INTO `as_resource_role` VALUES (4411, 1996, 82);
INSERT INTO `as_resource_role` VALUES (4412, 1997, 82);
INSERT INTO `as_resource_role` VALUES (4413, 1998, 82);
INSERT INTO `as_resource_role` VALUES (4414, 1999, 82);
INSERT INTO `as_resource_role` VALUES (4415, 2000, 82);
INSERT INTO `as_resource_role` VALUES (4416, 2001, 82);
INSERT INTO `as_resource_role` VALUES (4417, 2002, 82);
INSERT INTO `as_resource_role` VALUES (4418, 2003, 82);
INSERT INTO `as_resource_role` VALUES (4419, 2004, 82);
INSERT INTO `as_resource_role` VALUES (4420, 2005, 82);
INSERT INTO `as_resource_role` VALUES (4421, 2006, 82);
INSERT INTO `as_resource_role` VALUES (4422, 2007, 82);
INSERT INTO `as_resource_role` VALUES (4423, 2008, 82);
INSERT INTO `as_resource_role` VALUES (4424, 2009, 82);
INSERT INTO `as_resource_role` VALUES (4425, 2010, 82);
INSERT INTO `as_resource_role` VALUES (4426, 2011, 82);
INSERT INTO `as_resource_role` VALUES (4427, 2012, 82);
INSERT INTO `as_resource_role` VALUES (4428, 2013, 82);
INSERT INTO `as_resource_role` VALUES (4429, 2014, 82);
INSERT INTO `as_resource_role` VALUES (4430, 2015, 82);
INSERT INTO `as_resource_role` VALUES (4431, 2016, 82);
INSERT INTO `as_resource_role` VALUES (4432, 2017, 1);
INSERT INTO `as_resource_role` VALUES (4433, 2018, 1);
INSERT INTO `as_resource_role` VALUES (4434, 2019, 1);
INSERT INTO `as_resource_role` VALUES (4435, 2020, 1);
INSERT INTO `as_resource_role` VALUES (4436, 2021, 1);
INSERT INTO `as_resource_role` VALUES (4437, 2022, 1);
INSERT INTO `as_resource_role` VALUES (4438, 2023, 1);
INSERT INTO `as_resource_role` VALUES (4439, 2024, 1);
INSERT INTO `as_resource_role` VALUES (4440, 2025, 1);
INSERT INTO `as_resource_role` VALUES (4441, 2026, 1);
INSERT INTO `as_resource_role` VALUES (4442, 2027, 1);
INSERT INTO `as_resource_role` VALUES (4443, 2028, 1);

-- ----------------------------
-- Table structure for as_role
-- ----------------------------
DROP TABLE IF EXISTS `as_role`;
CREATE TABLE `as_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色别名',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_role
-- ----------------------------
INSERT INTO `as_role` VALUES (1, '超级管理员', 1, 'administrator', 0);
INSERT INTO `as_role` VALUES (2, '普通用户', 2, 'user', 0);
INSERT INTO `as_role` VALUES (3, '测试角色', 3, 'test', 0);
INSERT INTO `as_role` VALUES (4, '测试角色2', 4, 'test1', 0);

-- ----------------------------
-- Table structure for as_role_group
-- ----------------------------
DROP TABLE IF EXISTS `as_role_group`;
CREATE TABLE `as_role_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_deleted` tinyint(2) NOT NULL,
  `add_time` datetime(0) NOT NULL,
  `scene_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_role_group
-- ----------------------------
INSERT INTO `as_role_group` VALUES (1, '默认分组', 0, '2019-06-17 09:42:25', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (2, '系统角色', 0, '2019-06-17 09:44:34', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (3, '开发组', 1, '2019-06-17 10:20:02', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (4, '开发组', 1, '2019-06-17 10:33:18', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (5, '开发组', 0, '2019-06-17 10:34:41', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (8, '测试', 1, '2019-07-17 21:26:36', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (9, '默认分组', 0, '2019-07-20 13:13:04', '0d1e3c54-aaad-11e9-89d6-005056c00001');
INSERT INTO `as_role_group` VALUES (10, '默认分组', 0, '2019-07-20 13:16:20', '81d27c21-aaad-11e9-afaa-005056c00001');
INSERT INTO `as_role_group` VALUES (11, '默认分组', 0, '2019-07-20 13:19:06', 'e516ee69-aaad-11e9-9ea9-005056c00001');
INSERT INTO `as_role_group` VALUES (12, '默认分组', 0, '2019-07-20 13:26:37', 'f1907b8a-aaae-11e9-a974-005056c00001');
INSERT INTO `as_role_group` VALUES (13, '默认分组', 0, '2019-07-20 13:48:49', '0bc633ef-aab2-11e9-80fb-005056c00001');
INSERT INTO `as_role_group` VALUES (14, '默认分组', 0, '2019-07-20 14:02:39', 'fa9ecdfe-aab3-11e9-93a9-005056c00001');
INSERT INTO `as_role_group` VALUES (15, '默认分组', 0, '2019-07-20 14:05:36', '63b9d993-aab4-11e9-95fb-005056c00001');
INSERT INTO `as_role_group` VALUES (16, '默认分组', 0, '2019-07-22 09:41:42', 'daeb69f7-ac21-11e9-893f-005056c00001');
INSERT INTO `as_role_group` VALUES (17, '默认分组', 0, '2019-07-29 11:54:17', '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_role_group` VALUES (18, '开发小组', 0, '2019-07-29 14:35:25', '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_role_group` VALUES (20, '默认分组', 0, '2019-07-29 15:39:09', 'f34a76a6-b1d3-11e9-a676-005056c00001');
INSERT INTO `as_role_group` VALUES (21, '默认分组', 0, '2019-07-29 16:48:09', '96f750e5-b1dd-11e9-80df-005056c00001');
INSERT INTO `as_role_group` VALUES (22, '系统开发组', 1, '2019-07-30 09:53:15', '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_role_group` VALUES (23, '前端开发组', 1, '2019-07-30 09:53:28', '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_role_group` VALUES (24, '默认分组', 0, '2019-07-30 11:16:00', '5a9d7930-b278-11e9-bc73-005056c00001');
INSERT INTO `as_role_group` VALUES (25, '默认分组', 0, '2019-08-01 15:11:16', '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (26, '默认分组', 0, '2019-08-01 15:12:14', 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (27, '默认分组', 0, '2019-08-01 16:01:47', '9c4172d0-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (28, '默认分组', 0, '2019-08-01 16:02:06', 'a735b571-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (29, '默认分组', 0, '2019-08-01 16:02:11', 'aa796ec2-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (30, '默认分组', 0, '2019-08-01 16:24:09', 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (31, '默认分组', 0, '2019-08-01 16:24:13', 'be23bc74-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (32, '默认分组', 0, '2019-08-01 16:24:17', 'c0995055-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (33, '默认分组', 0, '2019-08-01 16:24:21', 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_role_group` VALUES (34, '业务角色', 0, '2019-09-03 10:22:51', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (35, '测试角色组', 0, '2019-09-09 20:59:45', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (36, '测试角色组2', 0, '2019-09-09 21:02:40', 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_role_group` VALUES (37, '默认分组', 0, '2019-09-16 13:06:38', 'c2ddb6e9-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_role_group` VALUES (38, '默认分组', 0, '2019-09-16 13:07:27', 'e048b09a-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_role_group` VALUES (39, '默认分组', 0, '2019-09-16 13:08:37', '0a10cbbb-d840-11e9-acac-0242ac120003');
INSERT INTO `as_role_group` VALUES (40, '默认分组', 0, '2019-09-16 14:36:10', '44edf3bd-d84c-11e9-8ada-0242ac120003');
INSERT INTO `as_role_group` VALUES (41, '默认分组', 0, '2019-09-18 12:19:56', '919fd4a1-d9cb-11e9-b749-0242ac120003');
INSERT INTO `as_role_group` VALUES (42, '默认分组', 0, '2019-09-18 12:30:10', 'ffaaa5f2-d9cc-11e9-b749-0242ac120003');
INSERT INTO `as_role_group` VALUES (43, '默认分组', 0, '2019-09-18 12:40:58', '81cf08e2-d9ce-11e9-b832-0242ac120003');
INSERT INTO `as_role_group` VALUES (44, '默认分组', 0, '2019-09-19 16:15:07', '96c1a722-dab5-11e9-8c14-0242ac120003');
INSERT INTO `as_role_group` VALUES (45, '默认分组', 0, '2019-09-19 16:50:42', '8f91a633-daba-11e9-8c14-0242ac120003');
INSERT INTO `as_role_group` VALUES (46, '默认分组', 0, '2019-09-26 14:20:27', 'bb5e45a4-e025-11e9-8c14-0242ac120003');
INSERT INTO `as_role_group` VALUES (47, '默认分组', 0, '2019-09-26 14:50:21', 'e88175ea-e029-11e9-a3b4-0242ac120003');
INSERT INTO `as_role_group` VALUES (48, '默认分组', 0, '2019-09-26 15:31:24', 'a48cb47b-e02f-11e9-a3b4-0242ac120003');
INSERT INTO `as_role_group` VALUES (49, '默认分组', 0, '2019-09-27 19:54:41', '96a6b730-e11d-11e9-9b6c-0242ac120002');
INSERT INTO `as_role_group` VALUES (50, '默认分组', 0, '2019-09-27 20:28:46', '59c2b212-e122-11e9-9b6c-0242ac120002');
INSERT INTO `as_role_group` VALUES (51, '测试', 0, '2019-09-28 15:03:06', '123');

-- ----------------------------
-- Table structure for as_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_scene`;
CREATE TABLE `as_scene`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `scene_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作场景名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0,
  `add_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_scene
-- ----------------------------
INSERT INTO `as_scene` VALUES ('01ae7d34-aa0d-11e9-9d8b-005056c00001', '测试场景133', '', NULL, 1, '2019-07-19 18:07:25');
INSERT INTO `as_scene` VALUES ('0a10cbbb-d840-11e9-acac-0242ac120003', 'aasa', '', NULL, 1, '2019-09-16 13:08:37');
INSERT INTO `as_scene` VALUES ('0bc633ef-aab2-11e9-80fb-005056c00001', 'test3的场景', '', NULL, 1, '2019-07-20 13:48:49');
INSERT INTO `as_scene` VALUES ('0c370383-a6c9-11e9-9a6d-005056c00001', '测试场景4', '场景图片', '测试场景', 1, '2019-07-15 14:23:24');
INSERT INTO `as_scene` VALUES ('0d1e3c54-aaad-11e9-89d6-005056c00001', '测试场景13312we', '', NULL, 1, '2019-07-20 13:13:04');
INSERT INTO `as_scene` VALUES ('28e48620-a79d-11e9-a4b2-005056c00001', '测试场景3', '', NULL, 1, '2019-07-16 15:41:45');
INSERT INTO `as_scene` VALUES ('31cc5795-aaa2-11e9-aeaf-005056c00001', '测试场景1150', '', NULL, 1, '2019-07-20 11:55:21');
INSERT INTO `as_scene` VALUES ('4075767c-a79b-11e9-a6df-005056c00001', '测试场景2', '', NULL, 1, '2019-07-16 15:28:05');
INSERT INTO `as_scene` VALUES ('44edf3bd-d84c-11e9-8ada-0242ac120003', '测试场景cx', '', NULL, 0, '2019-09-16 14:36:10');
INSERT INTO `as_scene` VALUES ('582d71e6-a77b-11e9-88fa-005056c00001', '测试场景', NULL, NULL, 1, '2019-07-16 11:39:41');
INSERT INTO `as_scene` VALUES ('59c2b212-e122-11e9-9b6c-0242ac120002', '资产管理云服务', '', NULL, 0, '2019-09-27 20:28:46');
INSERT INTO `as_scene` VALUES ('5a9d7930-b278-11e9-bc73-005056c00001', '场景07301114', '', NULL, 1, '2019-07-30 11:16:00');
INSERT INTO `as_scene` VALUES ('5adb9ccf-96f0-11e9-867c-005056c00001', '工作场景6', NULL, NULL, 1, '2019-06-25 10:24:27');
INSERT INTO `as_scene` VALUES ('5cb53ed0-96f0-11e9-867c-005056c00001', '工作场景2', NULL, NULL, 1, '2019-06-25 10:24:30');
INSERT INTO `as_scene` VALUES ('5e43a701-96f0-11e9-867c-005056c00001', '工作场景3', NULL, NULL, 1, '2019-06-25 10:24:32');
INSERT INTO `as_scene` VALUES ('5ffca1a2-96f0-11e9-867c-005056c00001', '工作场景4', NULL, NULL, 1, '2019-06-25 10:24:35');
INSERT INTO `as_scene` VALUES ('6195b833-96f0-11e9-867c-005056c00001', '工作场景5', NULL, NULL, 1, '2019-06-25 10:24:38');
INSERT INTO `as_scene` VALUES ('6393d224-96f0-11e9-867c-005056c00001', '工作场景6', NULL, NULL, 1, '2019-06-25 10:24:41');
INSERT INTO `as_scene` VALUES ('63b9d993-aab4-11e9-95fb-005056c00001', 'test6的场景', '', NULL, 1, '2019-07-20 14:05:36');
INSERT INTO `as_scene` VALUES ('81cf08e2-d9ce-11e9-b832-0242ac120003', '测试场景09181240', '', NULL, 0, '2019-09-18 12:40:58');
INSERT INTO `as_scene` VALUES ('81d27c21-aaad-11e9-afaa-005056c00001', '测试场景115021', '', NULL, 1, '2019-07-20 13:16:20');
INSERT INTO `as_scene` VALUES ('82e33b75-aa0c-11e9-89ff-005056c00001', '测试场景12', '', NULL, 1, '2019-07-19 18:03:52');
INSERT INTO `as_scene` VALUES ('89371977-b1b4-11e9-8d9b-005056c00001', '测试1', '', NULL, 0, '2019-07-29 11:54:17');
INSERT INTO `as_scene` VALUES ('8d4df33e-b42b-11e9-bebb-0e0b7ec70eea', '测试场景xu1', '', NULL, 1, '2019-08-01 15:11:16');
INSERT INTO `as_scene` VALUES ('8f91a633-daba-11e9-8c14-0242ac120003', '我的工作场景', '', NULL, 0, '2019-09-19 16:50:42');
INSERT INTO `as_scene` VALUES ('919fd4a1-d9cb-11e9-b749-0242ac120003', '测试场景0918', '', NULL, 0, '2019-09-18 12:19:56');
INSERT INTO `as_scene` VALUES ('96a6b730-e11d-11e9-9b6c-0242ac120002', '演示验证场景', '', NULL, 0, '2019-09-27 19:54:41');
INSERT INTO `as_scene` VALUES ('96c1a722-dab5-11e9-8c14-0242ac120003', '我的测试场景', '', NULL, 0, '2019-09-19 16:15:06');
INSERT INTO `as_scene` VALUES ('96f750e5-b1dd-11e9-80df-005056c00001', '场景291647', '', NULL, 1, '2019-07-29 16:48:09');
INSERT INTO `as_scene` VALUES ('9c4172d0-b432-11e9-bebb-0e0b7ec70eea', '新测试场景1', '', NULL, 0, '2019-08-01 16:01:47');
INSERT INTO `as_scene` VALUES ('a1d7c397-a6c9-11e9-bb43-005056c00001', '测试场景1', '场景图片', '测试场景', 1, '2019-07-15 14:27:35');
INSERT INTO `as_scene` VALUES ('a48cb47b-e02f-11e9-a3b4-0242ac120003', '杭州电子科技大学09261531', '', NULL, 0, '2019-09-26 15:31:24');
INSERT INTO `as_scene` VALUES ('a735b571-b432-11e9-bebb-0e0b7ec70eea', '新测试场景2', '', NULL, 1, '2019-08-01 16:02:06');
INSERT INTO `as_scene` VALUES ('aa796ec2-b432-11e9-bebb-0e0b7ec70eea', '新测试场景3', '', NULL, 1, '2019-08-01 16:02:11');
INSERT INTO `as_scene` VALUES ('afdf970f-b42b-11e9-bebb-0e0b7ec70eea', '测试场景xu2', '', NULL, 1, '2019-08-01 15:12:14');
INSERT INTO `as_scene` VALUES ('bb5e45a4-e025-11e9-8c14-0242ac120003', '杭州电子科技大学-demo', '', NULL, 0, '2019-09-26 14:20:27');
INSERT INTO `as_scene` VALUES ('bbcaff63-b435-11e9-bebb-0e0b7ec70eea', '新测试场景2', '', NULL, 0, '2019-08-01 16:24:09');
INSERT INTO `as_scene` VALUES ('be23bc74-b435-11e9-bebb-0e0b7ec70eea', '新测试场景3', '', NULL, 0, '2019-08-01 16:24:13');
INSERT INTO `as_scene` VALUES ('c0995055-b435-11e9-bebb-0e0b7ec70eea', '新测试场景4', '', NULL, 0, '2019-08-01 16:24:17');
INSERT INTO `as_scene` VALUES ('c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', '新测试场景5', '', NULL, 0, '2019-08-01 16:24:21');
INSERT INTO `as_scene` VALUES ('c2ddb6e9-d83f-11e9-acac-0242ac120003', 'aa', '', NULL, 1, '2019-09-16 13:06:38');
INSERT INTO `as_scene` VALUES ('daeb69f7-ac21-11e9-893f-005056c00001', 'test7的场景', '', NULL, 1, '2019-07-22 09:41:42');
INSERT INTO `as_scene` VALUES ('e048b09a-d83f-11e9-acac-0242ac120003', '测试场景cx', '', NULL, 1, '2019-09-16 13:07:27');
INSERT INTO `as_scene` VALUES ('e516ee69-aaad-11e9-9ea9-005056c00001', '测试', '', NULL, 1, '2019-07-20 13:19:06');
INSERT INTO `as_scene` VALUES ('e65edc60-96ee-11e9-ac96-005056c00001', '杭州电子科技大学', NULL, NULL, 0, '2019-06-25 10:14:02');
INSERT INTO `as_scene` VALUES ('e88175ea-e029-11e9-a3b4-0242ac120003', '我的测试场景0926', '', NULL, 0, '2019-09-26 14:50:21');
INSERT INTO `as_scene` VALUES ('f1907b8a-aaae-11e9-a974-005056c00001', '测试场景07201326', '', NULL, 1, '2019-07-20 13:26:37');
INSERT INTO `as_scene` VALUES ('f34a76a6-b1d3-11e9-a676-005056c00001', '场景291537', '', NULL, 1, '2019-07-29 15:39:09');
INSERT INTO `as_scene` VALUES ('fa9ecdfe-aab3-11e9-93a9-005056c00001', 'test5的场景', '', NULL, 1, '2019-07-20 14:02:39');
INSERT INTO `as_scene` VALUES ('fe358293-aaab-11e9-9589-005056c00001', '测试场景13312', '', NULL, 1, '2019-07-20 13:05:29');
INSERT INTO `as_scene` VALUES ('ffaaa5f2-d9cc-11e9-b749-0242ac120003', '123', '', NULL, 0, '2019-09-18 12:30:10');

-- ----------------------------
-- Table structure for as_scene_relation
-- ----------------------------
DROP TABLE IF EXISTS `as_scene_relation`;
CREATE TABLE `as_scene_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `rid` bigint(20) NOT NULL COMMENT '业务角色id',
  `is_deleted` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_scene_relation
-- ----------------------------
INSERT INTO `as_scene_relation` VALUES (1, 'f33e68b5-b1d3-11e9-a676-005056c00001', 31, 0);
INSERT INTO `as_scene_relation` VALUES (2, '1', 1, 0);
INSERT INTO `as_scene_relation` VALUES (3, '1', 1, 0);
INSERT INTO `as_scene_relation` VALUES (4, '96eb42f4-b1dd-11e9-80df-005056c00001', 33, 0);
INSERT INTO `as_scene_relation` VALUES (5, '21321321313', 2, 0);
INSERT INTO `as_scene_relation` VALUES (29, '4b4d7d72-b1b4-11e9-8d9b-005056c00001', 1, 1);
INSERT INTO `as_scene_relation` VALUES (30, '723a3e03-b1b4-11e9-8d9b-005056c00001', 1, 1);
INSERT INTO `as_scene_relation` VALUES (31, '0c2aa772-a6c9-11e9-9a6d-005056c00001', 36, 0);
INSERT INTO `as_scene_relation` VALUES (33, '5ec0cdb1-b28c-11e9-9cb3-005056c00001', 28, 0);
INSERT INTO `as_scene_relation` VALUES (36, 'f33e68b5-b1d3-11e9-a676-005056c00001', 37, 0);
INSERT INTO `as_scene_relation` VALUES (37, '0c419cd2-b28d-11e9-9cb3-005056c00001', 27, 0);
INSERT INTO `as_scene_relation` VALUES (38, '0c419cd2-b28d-11e9-9cb3-005056c00001', 28, 0);
INSERT INTO `as_scene_relation` VALUES (39, '0c419cd2-b28d-11e9-9cb3-005056c00001', 33, 0);
INSERT INTO `as_scene_relation` VALUES (40, '0c419cd2-b28d-11e9-9cb3-005056c00001', 34, 0);
INSERT INTO `as_scene_relation` VALUES (41, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 28, 0);
INSERT INTO `as_scene_relation` VALUES (42, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 37, 0);
INSERT INTO `as_scene_relation` VALUES (43, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 43, 0);
INSERT INTO `as_scene_relation` VALUES (44, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 49, 0);
INSERT INTO `as_scene_relation` VALUES (45, '7', 28, 0);
INSERT INTO `as_scene_relation` VALUES (46, '7', 43, 0);
INSERT INTO `as_scene_relation` VALUES (47, '7', 49, 0);
INSERT INTO `as_scene_relation` VALUES (48, '7', 55, 0);
INSERT INTO `as_scene_relation` VALUES (49, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 51, 0);
INSERT INTO `as_scene_relation` VALUES (50, '7', 51, 0);
INSERT INTO `as_scene_relation` VALUES (51, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 53, 0);
INSERT INTO `as_scene_relation` VALUES (52, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 55, 0);
INSERT INTO `as_scene_relation` VALUES (53, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 2, 0);
INSERT INTO `as_scene_relation` VALUES (54, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 1, 0);
INSERT INTO `as_scene_relation` VALUES (55, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 2, 0);
INSERT INTO `as_scene_relation` VALUES (56, '45daae24-bd0b-11e9-b0bf-9eb9361fdb0b', 2, 0);
INSERT INTO `as_scene_relation` VALUES (57, '6fa8bcb6-bd0b-11e9-b0bf-9eb9361fdb0b', 2, 0);
INSERT INTO `as_scene_relation` VALUES (58, '910f9c27-bd0b-11e9-b0bf-9eb9361fdb0b', 2, 0);
INSERT INTO `as_scene_relation` VALUES (59, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 1, 0);
INSERT INTO `as_scene_relation` VALUES (60, '201909032004', 2, 1);
INSERT INTO `as_scene_relation` VALUES (61, '201909032005', 2, 1);
INSERT INTO `as_scene_relation` VALUES (62, '201909032006', 2, 0);
INSERT INTO `as_scene_relation` VALUES (63, '201909032007', 2, 0);
INSERT INTO `as_scene_relation` VALUES (64, '201909032005', 55, 0);
INSERT INTO `as_scene_relation` VALUES (65, '201909032005', 55, 0);
INSERT INTO `as_scene_relation` VALUES (66, '201909032005', 28, 0);
INSERT INTO `as_scene_relation` VALUES (67, '201909032005', 51, 0);
INSERT INTO `as_scene_relation` VALUES (68, '201909032005', 2, 1);
INSERT INTO `as_scene_relation` VALUES (69, '201909032005', 1, 1);
INSERT INTO `as_scene_relation` VALUES (70, '201909032005', 2, 1);
INSERT INTO `as_scene_relation` VALUES (71, '201909032005', 1, 1);
INSERT INTO `as_scene_relation` VALUES (72, '201909032005', 2, 1);
INSERT INTO `as_scene_relation` VALUES (73, '201909032005', 3, 1);
INSERT INTO `as_scene_relation` VALUES (74, '201909032005', 2, 1);
INSERT INTO `as_scene_relation` VALUES (75, '201909032005', 3, 1);
INSERT INTO `as_scene_relation` VALUES (76, '201909032005', 3, 1);
INSERT INTO `as_scene_relation` VALUES (77, '201909032005', 1, 1);
INSERT INTO `as_scene_relation` VALUES (78, '201909032005', 3, 0);
INSERT INTO `as_scene_relation` VALUES (79, '201909032004', 2, 1);
INSERT INTO `as_scene_relation` VALUES (80, '201909032004', 1, 1);
INSERT INTO `as_scene_relation` VALUES (81, '201909032004', 2, 0);
INSERT INTO `as_scene_relation` VALUES (82, 'b7c4dd07-d551-11e9-8fc5-0242ac110005', 2, 0);
INSERT INTO `as_scene_relation` VALUES (83, 'c9fff538-d552-11e9-8fc5-0242ac110005', 2, 0);
INSERT INTO `as_scene_relation` VALUES (84, 'ff5cffc9-d552-11e9-8fc5-0242ac110005', 2, 0);
INSERT INTO `as_scene_relation` VALUES (85, 'c8df9984-d824-11e9-9107-0242ac120003', 2, 1);
INSERT INTO `as_scene_relation` VALUES (86, '201909032005', 62, 0);
INSERT INTO `as_scene_relation` VALUES (87, '201909032005', 62, 0);
INSERT INTO `as_scene_relation` VALUES (88, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 62, 0);
INSERT INTO `as_scene_relation` VALUES (89, '0c2aa772-a6c9-11e9-9a6d-005056c00001', 62, 0);
INSERT INTO `as_scene_relation` VALUES (90, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 28, 0);
INSERT INTO `as_scene_relation` VALUES (91, '0c2aa772-a6c9-11e9-9a6d-005056c00001', 28, 0);
INSERT INTO `as_scene_relation` VALUES (92, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 49, 0);
INSERT INTO `as_scene_relation` VALUES (93, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 62, 0);
INSERT INTO `as_scene_relation` VALUES (94, '0c2aa772-a6c9-11e9-9a6d-005056c00001', 62, 0);
INSERT INTO `as_scene_relation` VALUES (95, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 64, 0);
INSERT INTO `as_scene_relation` VALUES (96, 'c8df9984-d824-11e9-9107-0242ac120003', 2, 0);
INSERT INTO `as_scene_relation` VALUES (97, 'c8df9984-d824-11e9-9107-0242ac120003', 1, 0);
INSERT INTO `as_scene_relation` VALUES (98, '201909032004', 28, 0);
INSERT INTO `as_scene_relation` VALUES (99, '201909032004', 66, 0);
INSERT INTO `as_scene_relation` VALUES (100, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 66, 0);
INSERT INTO `as_scene_relation` VALUES (101, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 70, 1);
INSERT INTO `as_scene_relation` VALUES (102, '201909032004', 70, 0);
INSERT INTO `as_scene_relation` VALUES (103, '201909032005', 70, 0);
INSERT INTO `as_scene_relation` VALUES (104, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 70, 0);
INSERT INTO `as_scene_relation` VALUES (105, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 69, 0);
INSERT INTO `as_scene_relation` VALUES (106, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 70, 0);
INSERT INTO `as_scene_relation` VALUES (107, '7', 2, 0);
INSERT INTO `as_scene_relation` VALUES (108, '201909032004', 72, 0);
INSERT INTO `as_scene_relation` VALUES (109, '7', 72, 0);
INSERT INTO `as_scene_relation` VALUES (110, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 72, 0);
INSERT INTO `as_scene_relation` VALUES (111, '201909032004', 74, 0);
INSERT INTO `as_scene_relation` VALUES (112, '7', 74, 0);
INSERT INTO `as_scene_relation` VALUES (113, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 74, 0);
INSERT INTO `as_scene_relation` VALUES (114, '201909032004', 76, 0);
INSERT INTO `as_scene_relation` VALUES (115, '4', 76, 0);
INSERT INTO `as_scene_relation` VALUES (116, 'dadf34f6-ac21-11e9-893f-005056c00001', 76, 0);
INSERT INTO `as_scene_relation` VALUES (117, '7', 76, 0);
INSERT INTO `as_scene_relation` VALUES (118, '7', 78, 0);
INSERT INTO `as_scene_relation` VALUES (119, '201909032004', 78, 0);
INSERT INTO `as_scene_relation` VALUES (120, '201909032004', 80, 0);
INSERT INTO `as_scene_relation` VALUES (121, '7', 80, 0);
INSERT INTO `as_scene_relation` VALUES (122, 'b834cdb1-e11d-11e9-9b6c-0242ac120002', 82, 0);
INSERT INTO `as_scene_relation` VALUES (123, '0bb9b0ce-aab2-11e9-80fb-005056c00001', 80, 0);

-- ----------------------------
-- Table structure for as_scene_role
-- ----------------------------
DROP TABLE IF EXISTS `as_scene_role`;
CREATE TABLE `as_scene_role`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色代码',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称（英文），用于spring security安全校验',
  `role_name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称（中文）',
  `role_description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_type` int(2) NULL DEFAULT 3 COMMENT '角色类型',
  `role_default` tinyint(2) NULL DEFAULT 0 COMMENT '是否是默认角色（默认角色不允许编辑成员）',
  `status` tinyint(1) NOT NULL COMMENT '状态：\r\n1、新建 \r\n2、启用 \r\n3、停用',
  `applicable_unit_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限发布出去后，哪些单位可以使用。如适用于行政事业单位、主管部门、财政、国有企业等不同单位类型。',
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `enable_time` datetime(0) NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `group_id` bigint(20) NULL DEFAULT NULL,
  `scene_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场景id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_scene_role
-- ----------------------------
INSERT INTO `as_scene_role` VALUES (1, 'ROLE_admin', '系统管理员', '这是总管理员的账号', 1, 0, 1, NULL, NULL, '2019-05-27 20:57:41', NULL, NULL, '2019-05-27 20:57:46', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (2, 'ROLE_default', '所有用户', '默认角色', 2, 1, 1, NULL, NULL, '2019-05-28 02:53:26', NULL, NULL, '2019-05-28 02:53:26', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (3, 'ROLE_test1', '业务人员', '测试角色', 3, 0, 1, NULL, NULL, '2019-05-28 02:07:59', NULL, NULL, '2019-05-28 02:07:58', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (4, 'ROLE_test2', '测试人员', '测试角色', 3, 0, 1, NULL, NULL, '2019-05-28 02:08:24', NULL, NULL, '2019-05-28 02:08:23', 2, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (5, 'ROLE_test3', '调配人员', '测试角色', 3, 0, 1, NULL, NULL, '2019-05-28 02:09:49', NULL, NULL, '2019-05-28 02:09:48', 2, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (8, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 11:54:32', NULL, '31cc5795-aaa2-11e9-aeaf-005056c00001');
INSERT INTO `as_scene_role` VALUES (9, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:05:29', NULL, 'fe358293-aaab-11e9-9589-005056c00001');
INSERT INTO `as_scene_role` VALUES (10, 'scene_default', '终端普通用户', NULL, 2, 0, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:05:29', NULL, 'fe358293-aaab-11e9-9589-005056c00001');
INSERT INTO `as_scene_role` VALUES (11, 'scene_admin', '组织管理员', NULL, 1, 1, 1, NULL, NULL, '2019-07-20 13:13:04', NULL, NULL, '2019-07-20 13:13:04', 9, '0d1e3c54-aaad-11e9-89d6-005056c00001');
INSERT INTO `as_scene_role` VALUES (12, 'scene_default', '终端普通用户', NULL, 2, 0, 1, NULL, NULL, '2019-07-20 13:13:04', NULL, NULL, '2019-07-20 13:13:04', 9, '0d1e3c54-aaad-11e9-89d6-005056c00001');
INSERT INTO `as_scene_role` VALUES (13, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-20 13:16:20', NULL, NULL, '2019-07-20 13:16:20', 10, '81d27c21-aaad-11e9-afaa-005056c00001');
INSERT INTO `as_scene_role` VALUES (14, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-20 13:16:20', NULL, NULL, '2019-07-20 13:16:20', 10, '81d27c21-aaad-11e9-afaa-005056c00001');
INSERT INTO `as_scene_role` VALUES (15, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-20 13:19:06', NULL, NULL, '2019-07-20 13:19:06', 11, 'e516ee69-aaad-11e9-9ea9-005056c00001');
INSERT INTO `as_scene_role` VALUES (16, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-20 13:19:06', NULL, NULL, '2019-07-20 13:19:06', 11, 'e516ee69-aaad-11e9-9ea9-005056c00001');
INSERT INTO `as_scene_role` VALUES (17, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-07-20 13:26:37', NULL, NULL, '2019-07-20 13:26:37', 12, 'f1907b8a-aaae-11e9-a974-005056c00001');
INSERT INTO `as_scene_role` VALUES (18, 'scene_default', '终端普通用户', NULL, 2, 1, 1, NULL, NULL, '2019-07-20 13:26:37', NULL, NULL, '2019-07-20 13:26:37', 12, 'f1907b8a-aaae-11e9-a974-005056c00001');
INSERT INTO `as_scene_role` VALUES (19, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-20 13:48:49', NULL, NULL, '2019-07-20 13:48:49', 13, '0bc633ef-aab2-11e9-80fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (20, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-20 13:48:49', NULL, NULL, '2019-07-20 13:48:49', 13, '0bc633ef-aab2-11e9-80fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (21, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-20 14:02:39', NULL, NULL, '2019-07-20 14:02:39', 14, 'fa9ecdfe-aab3-11e9-93a9-005056c00001');
INSERT INTO `as_scene_role` VALUES (22, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-20 14:02:39', NULL, NULL, '2019-07-20 14:02:39', 14, 'fa9ecdfe-aab3-11e9-93a9-005056c00001');
INSERT INTO `as_scene_role` VALUES (23, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-20 14:05:36', NULL, NULL, '2019-07-20 14:05:36', 15, '63b9d993-aab4-11e9-95fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (24, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-20 14:05:36', NULL, NULL, '2019-07-20 14:05:36', 15, '63b9d993-aab4-11e9-95fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (25, 'scene_admin', '组织管理员', NULL, 1, 0, 0, NULL, NULL, '2019-07-22 09:41:42', NULL, NULL, '2019-07-22 09:41:42', 16, 'daeb69f7-ac21-11e9-893f-005056c00001');
INSERT INTO `as_scene_role` VALUES (26, 'scene_default', '终端普通用户', NULL, 2, 1, 0, NULL, NULL, '2019-07-22 09:41:42', NULL, NULL, '2019-07-22 09:41:42', 16, 'daeb69f7-ac21-11e9-893f-005056c00001');
INSERT INTO `as_scene_role` VALUES (27, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-07-29 11:54:17', NULL, NULL, '2019-07-29 11:54:17', 17, '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_scene_role` VALUES (28, 'scene_default', '终端普通用户', NULL, 2, 1, 1, NULL, NULL, '2019-07-29 11:54:17', NULL, NULL, '2019-07-29 11:54:17', 18, '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_scene_role` VALUES (31, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-07-29 15:39:09', NULL, NULL, '2019-07-29 15:39:09', 20, 'f34a76a6-b1d3-11e9-a676-005056c00001');
INSERT INTO `as_scene_role` VALUES (32, 'scene_default', '终端普通用户', NULL, 2, 1, 1, NULL, NULL, '2019-07-29 15:39:09', NULL, NULL, '2019-07-29 15:39:09', 20, 'f34a76a6-b1d3-11e9-a676-005056c00001');
INSERT INTO `as_scene_role` VALUES (33, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-07-29 16:48:09', NULL, NULL, '2019-07-29 16:48:09', 21, '96f750e5-b1dd-11e9-80df-005056c00001');
INSERT INTO `as_scene_role` VALUES (34, 'scene_default', '终端普通用户', NULL, 2, 1, 1, NULL, NULL, '2019-07-29 16:48:09', NULL, NULL, '2019-07-29 16:48:09', 21, '96f750e5-b1dd-11e9-80df-005056c00001');
INSERT INTO `as_scene_role` VALUES (35, 'scene_normal', '系统开发者', NULL, 3, 0, 1, NULL, NULL, '2019-07-30 09:49:27', NULL, NULL, '2019-07-30 09:49:27', 17, '89371977-b1b4-11e9-8d9b-005056c00001');
INSERT INTO `as_scene_role` VALUES (36, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-07-30 11:16:00', NULL, NULL, '2019-07-30 11:16:00', 24, '5a9d7930-b278-11e9-bc73-005056c00001');
INSERT INTO `as_scene_role` VALUES (37, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-07-30 11:16:00', NULL, NULL, '2019-07-30 11:16:00', 24, '5a9d7930-b278-11e9-bc73-005056c00001');
INSERT INTO `as_scene_role` VALUES (38, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 15:11:16', NULL, NULL, '2019-08-01 15:11:16', 25, '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (39, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 15:11:16', NULL, NULL, '2019-08-01 15:11:16', 25, '8d4df33e-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (40, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 15:12:14', NULL, NULL, '2019-08-01 15:12:14', 26, 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (41, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 15:12:14', NULL, NULL, '2019-08-01 15:12:14', 26, 'afdf970f-b42b-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (42, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:01:47', NULL, NULL, '2019-08-01 16:01:47', 27, '9c4172d0-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (43, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:01:47', NULL, NULL, '2019-08-01 16:01:47', 27, '9c4172d0-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (44, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:02:06', NULL, NULL, '2019-08-01 16:02:06', 28, 'a735b571-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (45, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:02:06', NULL, NULL, '2019-08-01 16:02:06', 28, 'a735b571-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (46, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:02:11', NULL, NULL, '2019-08-01 16:02:11', 29, 'aa796ec2-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (47, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:02:11', NULL, NULL, '2019-08-01 16:02:11', 29, 'aa796ec2-b432-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (48, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:24:09', NULL, NULL, '2019-08-01 16:24:09', 30, 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (49, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:24:09', NULL, NULL, '2019-08-01 16:24:09', 30, 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (50, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:24:13', NULL, NULL, '2019-08-01 16:24:13', 31, 'be23bc74-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (51, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:24:13', NULL, NULL, '2019-08-01 16:24:13', 31, 'be23bc74-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (52, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:24:17', NULL, NULL, '2019-08-01 16:24:17', 32, 'c0995055-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (53, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:24:17', NULL, NULL, '2019-08-01 16:24:17', 32, 'c0995055-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (54, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-08-01 16:24:21', NULL, NULL, '2019-08-01 16:24:21', 33, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (55, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-08-01 16:24:21', NULL, NULL, '2019-08-01 16:24:21', 33, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea');
INSERT INTO `as_scene_role` VALUES (56, 'scene_normal', '测试角色cx', NULL, 3, 0, 1, NULL, NULL, '2019-09-09 21:23:02', NULL, NULL, '2019-09-09 21:23:02', 2, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (57, 'scene_admin', '组织管理员', NULL, 3, 0, 1, NULL, NULL, '2019-09-16 13:06:38', NULL, NULL, '2019-09-16 13:06:38', 37, 'c2ddb6e9-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (58, 'scene_default', '终端默认角色', NULL, 3, 1, 1, NULL, NULL, '2019-09-16 13:06:38', NULL, NULL, '2019-09-16 13:06:38', 37, 'c2ddb6e9-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (59, 'scene_admin', '组织管理员', NULL, 3, 0, 1, NULL, NULL, '2019-09-16 13:07:27', NULL, NULL, '2019-09-16 13:07:27', 38, 'e048b09a-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (60, 'scene_default', '终端默认角色', NULL, 3, 1, 1, NULL, NULL, '2019-09-16 13:07:27', NULL, NULL, '2019-09-16 13:07:27', 38, 'e048b09a-d83f-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (61, 'scene_admin', '组织管理员', NULL, 3, 0, 1, NULL, NULL, '2019-09-16 13:08:37', NULL, NULL, '2019-09-16 13:08:37', 39, '0a10cbbb-d840-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (62, 'scene_default', '终端默认角色', NULL, 3, 1, 1, NULL, NULL, '2019-09-16 13:08:37', NULL, NULL, '2019-09-16 13:08:37', 39, '0a10cbbb-d840-11e9-acac-0242ac120003');
INSERT INTO `as_scene_role` VALUES (63, 'scene_admin', '组织管理员', NULL, 3, 0, 1, NULL, NULL, '2019-09-16 14:36:10', NULL, NULL, '2019-09-16 14:36:10', 40, '44edf3bd-d84c-11e9-8ada-0242ac120003');
INSERT INTO `as_scene_role` VALUES (64, 'scene_default', '终端默认角色', NULL, 3, 1, 1, NULL, NULL, '2019-09-16 14:36:10', NULL, NULL, '2019-09-16 14:36:10', 40, '44edf3bd-d84c-11e9-8ada-0242ac120003');
INSERT INTO `as_scene_role` VALUES (65, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-18 12:19:56', NULL, NULL, '2019-09-18 12:19:56', 41, '919fd4a1-d9cb-11e9-b749-0242ac120003');
INSERT INTO `as_scene_role` VALUES (66, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-18 12:19:56', NULL, NULL, '2019-09-18 12:19:56', 41, '919fd4a1-d9cb-11e9-b749-0242ac120003');
INSERT INTO `as_scene_role` VALUES (67, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-18 12:30:10', NULL, NULL, '2019-09-18 12:30:10', 42, 'ffaaa5f2-d9cc-11e9-b749-0242ac120003');
INSERT INTO `as_scene_role` VALUES (68, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-18 12:30:10', NULL, NULL, '2019-09-18 12:30:10', 42, 'ffaaa5f2-d9cc-11e9-b749-0242ac120003');
INSERT INTO `as_scene_role` VALUES (69, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-18 12:40:58', NULL, NULL, '2019-09-18 12:40:58', 43, '81cf08e2-d9ce-11e9-b832-0242ac120003');
INSERT INTO `as_scene_role` VALUES (70, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-18 12:40:58', NULL, NULL, '2019-09-18 12:40:58', 43, '81cf08e2-d9ce-11e9-b832-0242ac120003');
INSERT INTO `as_scene_role` VALUES (71, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-19 16:15:07', NULL, NULL, '2019-09-19 16:15:07', 44, '96c1a722-dab5-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (72, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-19 16:15:07', NULL, NULL, '2019-09-19 16:15:07', 44, '96c1a722-dab5-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (73, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-19 16:50:42', NULL, NULL, '2019-09-19 16:50:42', 45, '8f91a633-daba-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (74, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-19 16:50:42', NULL, NULL, '2019-09-19 16:50:42', 45, '8f91a633-daba-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (75, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-26 14:20:27', NULL, NULL, '2019-09-26 14:20:27', 46, 'bb5e45a4-e025-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (76, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-26 14:20:27', NULL, NULL, '2019-09-26 14:20:27', 46, 'bb5e45a4-e025-11e9-8c14-0242ac120003');
INSERT INTO `as_scene_role` VALUES (77, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-26 14:50:21', NULL, NULL, '2019-09-26 14:50:21', 47, 'e88175ea-e029-11e9-a3b4-0242ac120003');
INSERT INTO `as_scene_role` VALUES (78, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-26 14:50:21', NULL, NULL, '2019-09-26 14:50:21', 47, 'e88175ea-e029-11e9-a3b4-0242ac120003');
INSERT INTO `as_scene_role` VALUES (79, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-26 15:31:24', NULL, NULL, '2019-09-26 15:31:24', 48, 'a48cb47b-e02f-11e9-a3b4-0242ac120003');
INSERT INTO `as_scene_role` VALUES (80, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-26 15:31:24', NULL, NULL, '2019-09-26 15:31:24', 48, 'a48cb47b-e02f-11e9-a3b4-0242ac120003');
INSERT INTO `as_scene_role` VALUES (81, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-27 19:54:41', NULL, NULL, '2019-09-27 19:54:41', 49, '96a6b730-e11d-11e9-9b6c-0242ac120002');
INSERT INTO `as_scene_role` VALUES (82, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-27 19:54:41', NULL, NULL, '2019-09-27 19:54:41', 49, '96a6b730-e11d-11e9-9b6c-0242ac120002');
INSERT INTO `as_scene_role` VALUES (83, 'scene_admin', '组织管理员', NULL, 1, 0, 1, NULL, NULL, '2019-09-27 20:28:46', NULL, NULL, '2019-09-27 20:28:46', 50, '59c2b212-e122-11e9-9b6c-0242ac120002');
INSERT INTO `as_scene_role` VALUES (84, 'scene_default', '终端默认角色', NULL, 2, 1, 1, NULL, NULL, '2019-09-27 20:28:46', NULL, NULL, '2019-09-27 20:28:46', 50, '59c2b212-e122-11e9-9b6c-0242ac120002');

-- ----------------------------
-- Table structure for as_staff
-- ----------------------------
DROP TABLE IF EXISTS `as_staff`;
CREATE TABLE `as_staff`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代码',
  `graduate_time` datetime(0) NULL DEFAULT NULL COMMENT '毕业时间',
  `graduate_school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学校',
  `authorized_strength` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制情况',
  `staff_birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `party_group` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '党派',
  `fixed_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `overseas_relation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '海外关系',
  `native_place` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `emergency_contact` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急情况联系人',
  `contact_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `staff_nation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `staff_hiredate` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `remove_tag` int(1) NULL DEFAULT NULL COMMENT '删除标识',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `staff_major` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别',
  `staff_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_created_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已创建用户标识',
  `postal_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `staff_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片',
  `certificate_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `certificate_type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型\r\n1、身份证 \r\n2、护照 \r\n3、军官证',
  `staff_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `staff_duty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态\r\n1、在职 \r\n2、离职',
  `asset_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产配置状态：\r\n1、未配置 \r\n2、已配置',
  `staff_religion` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宗教信仰',
  `academic_credential` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `family_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_staff
-- ----------------------------
INSERT INTO `as_staff` VALUES ('24993b54-a6c9-11e9-9a6d-005056c00001', '0c2aa772-a6c9-11e9-9a6d-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '张三', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);
INSERT INTO `as_staff` VALUES ('26681999-a6c7-11e9-bd29-005056c00001', '11468dd8-a6c7-11e9-bd29-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '张三', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);
INSERT INTO `as_staff` VALUES ('3c5608ed-9be8-11e9-8731-005056c00001', 'a13c05a3-9bd7-11e9-a6d5-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '陈旭', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);
INSERT INTO `as_staff` VALUES ('f41975d1-a6c8-11e9-9a6d-005056c00001', 'b3b89840-a6c8-11e9-9a6d-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '张三', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);
INSERT INTO `as_staff` VALUES ('f4e1368a-8b2d-11e9-9815-005056c00001', '5fa7c0a0-8b29-11e9-aa21-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '测试官', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for as_user
-- ----------------------------
DROP TABLE IF EXISTS `as_user`;
CREATE TABLE `as_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户代码',
  `staff_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '员工代码',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '用户的平台角色id',
  `real_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号名称',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：\r\n1、男\r\n2、女',
  `user_birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号码',
  `certificate_type` int(1) NULL DEFAULT NULL COMMENT '证件类型',
  `certificate_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证件号码',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `authentication_method` int(1) NULL DEFAULT NULL COMMENT '认证方式：\r\n1、用户名+密码 \r\n2、ca认证',
  `account_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像',
  `admin` int(1) NOT NULL DEFAULT 0 COMMENT '是否是超级管理员',
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
  `user_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户照片',
  `user_remark` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '用户备注',
  `stage` int(2) NULL DEFAULT NULL COMMENT '用户状态：2：可用；1：审核中；0：不可用',
  `status` tinyint(1) NOT NULL COMMENT '状态 1：有效 0：无效',
  `user_type` int(1) NULL DEFAULT NULL COMMENT '用户类型：\r\n1、政府财政 \r\n2、主管部门 \r\n3、行政单位 \r\n4、事业单位 \r\n5、国有企业 \r\n9、普通用户',
  `disable_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `enable_time` datetime(0) NULL DEFAULT NULL COMMENT '启用时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次更新时间戳',
  `remove_time` datetime(0) NULL DEFAULT NULL COMMENT '注销时间',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_name`(`account_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_user
-- ----------------------------
INSERT INTO `as_user` VALUES ('0bb9b0ce-aab2-11e9-80fb-005056c00001', '', 2, '测试账号', 'test3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$7p0ECJbdQBNYrAwUcPISAONdKqP7bJOsbiBIKenEF/KiJGydTlJ8G', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:48:49');
INSERT INTO `as_user` VALUES ('0c2aa772-a6c9-11e9-9a6d-005056c00001', '24993b54-a6c9-11e9-9a6d-005056c00001', 2, '张三', 'admin2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$DAhhdj3CWNaxTW1O74YZj.Za9L2nGbnBI57DOGfCcHsKFxSILdCeO', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:23:23');
INSERT INTO `as_user` VALUES ('0c419cd2-b28d-11e9-9cb3-005056c00001', '', 2, '测试账号', 'test301344', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$FFk.vPZr.4Pd6kqKt8Lu8OaSN2jdCR/xt8hqMyG5wx.frTH8Ip53a', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-30 13:44:08');
INSERT INTO `as_user` VALUES ('1', '1', 1, '系统管理员', 'admin', '系统管理员', 1, '2019-05-27 15:36:20', '123456@admin.com', '13112341234', 1, '330312123412341234', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 15:36:50');
INSERT INTO `as_user` VALUES ('19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', '', 2, '海杰', 'huhaijie', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:11:39');
INSERT INTO `as_user` VALUES ('201909032004', '', 2, '李老师', 'li', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:05:14');
INSERT INTO `as_user` VALUES ('201909032005', '', 2, '孙老师', 'sun', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:06:12');
INSERT INTO `as_user` VALUES ('201909032006', '', 2, '田老师', 'tian', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:06:19');
INSERT INTO `as_user` VALUES ('201909032007', '', 2, '金伟刚', 'jin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-03 20:07:45');
INSERT INTO `as_user` VALUES ('4', NULL, 2, 'hhj', 'hhj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$diZVrY./3ywZAehLq7VDkOqGmh7lCfAUYVp.6rX43bcQ.LwCOqclK', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:41:27');
INSERT INTO `as_user` VALUES ('45daae24-bd0b-11e9-b0bf-9eb9361fdb0b', '', 2, '部门负责人', 'bmfzr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$4EblB.0mMoTR2IcMI0sC6Odjii/r2JNxYGjq/U2rFDJOORxsEUVfO', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:12:53');
INSERT INTO `as_user` VALUES ('4b4d7d72-b1b4-11e9-8d9b-005056c00001', '', 2, '测试账号', 'test291148', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$fD0.ElTqXQYnc2C1jv9l3ut8b7mRzYB2KgPJ4yYNHKObAjwcq40MW', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-29 11:52:33');
INSERT INTO `as_user` VALUES ('5', NULL, 2, '炳跃', 'yby', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$mR.wbAklwKsKjeJ27NVb1OlQfH/4iPcIxq34x9ps0USPX1H3R.UGe', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:43:46');
INSERT INTO `as_user` VALUES ('5ec0cdb1-b28c-11e9-9cb3-005056c00001', '', 2, '测试账号', 'test301338', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$V3CADs4n4RCXsY6ZoIxIY.QxWv/NncZaFhBA24Ny1q7gVGJ/MBLJC', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-30 13:39:17');
INSERT INTO `as_user` VALUES ('6', NULL, 2, 'pqq', 'pqq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UgIXCnxuYgwmxr72Ma94yeO3YZFfRFvmBfmQN6wPtXme4rCh9rf.O', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 2, 0, NULL, '2019-08-06 16:09:26', NULL, NULL, NULL, '2019-05-27 12:45:24');
INSERT INTO `as_user` VALUES ('62b516a4-b1c1-11e9-9b51-0e0b7ec70eea', '', 2, 'chenxu1', 'testxu1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$PgcS7G9x5a8aaqq1dPb2auyZZl5I7LQAzn0D8ljEIusPGE/TQvo2W', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-29 13:26:15');
INSERT INTO `as_user` VALUES ('63a910b2-aab4-11e9-95fb-005056c00001', '', 2, '测试账号4', 'test6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$ME8yc1rwfksB.LTamsbJy.6ckjNz2M8n1986O26WG4XIfa7CnrNpC', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 14:05:35');
INSERT INTO `as_user` VALUES ('6fa8bcb6-bd0b-11e9-b0bf-9eb9361fdb0b', '', 2, '宣传部门负责人', 'xcbmfzr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$kVdscTdTN8Z/Zq1vjSw...wYTYTaw0zbpe5xwG28hqfDczv5XgwhO', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:14:03');
INSERT INTO `as_user` VALUES ('7', NULL, 2, 'test', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, '浙江省杭州市江干区', NULL, NULL, 2, 1, NULL, '2019-08-06 16:07:47', NULL, NULL, NULL, '2019-05-29 12:08:34');
INSERT INTO `as_user` VALUES ('723a3e03-b1b4-11e9-8d9b-005056c00001', '', 2, '测试账号', 'test291153', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$8FiPqWne436ODjE9VsHvpeYDcsNHrLQWh1eQmIiDijjeCocBQsrlK', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-29 11:53:38');
INSERT INTO `as_user` VALUES ('88efd746-b1b4-11e9-8d9b-005056c00001', '', 2, '测试账号', 'test291154', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$9vU1svWjgbALTthGjithNONCHmerXNXpkoGbg.EuY/W2DF470Ge/C', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-29 11:54:16');
INSERT INTO `as_user` VALUES ('910f9c27-bd0b-11e9-b0bf-9eb9361fdb0b', '', 2, '杭电资产处负责人', 'hduzcc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UYCUBKOp03KoLO/gXyyWx.sTuE0SfN1eoTRPWZCKL/4xN14xgiBhC', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-12 22:14:59');
INSERT INTO `as_user` VALUES ('939f0bd7-b409-11e9-9442-0e0b7ec70eea', '', 2, '陈旭', 'chenxu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$gI9JzE51lond1ip8Vzgzu.CT69spY5wD9jwMmJ0tG4t3dOuiSmi0K', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-01 11:08:04');
INSERT INTO `as_user` VALUES ('96eb42f4-b1dd-11e9-80df-005056c00001', '', 2, '测试账号1310', 'test291647', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$1wDqBLeioI86aNB4h8PddufjzfUTY8betFIHzwAKylB9fjzMSyRKm', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-29 16:48:09');
INSERT INTO `as_user` VALUES ('977e0670-aab2-11e9-80fb-005056c00001', '', 2, '测试账号4', 'test4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$otvMRCoZDyVl2kSecQvWfOcwO.nT27O6VnO7lpiQmyL8p99R0ois6', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:52:43');
INSERT INTO `as_user` VALUES ('a13c05a3-9bd7-11e9-a6d5-005056c00001', '3c5608ed-9be8-11e9-8731-005056c00001', 2, '陈旭', 'chenxu1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$4Mhl3A0UmxEVHXWdcxy3suTdKqb7Gof.f95kEVWgD7OuHF1A/WKOG', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-01 16:10:04');
INSERT INTO `as_user` VALUES ('a1cb1966-a6c9-11e9-bb43-005056c00001', '', 2, '张三', 'admin3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$9GlSBTTMPwTVD4bq5KNsZeByfsiCfNDkJy6ks/5SmUIgs4I/cQHDC', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:27:34');
INSERT INTO `as_user` VALUES ('b3b89840-a6c8-11e9-9a6d-005056c00001', 'f41975d1-a6c8-11e9-9a6d-005056c00001', 2, '张三', 'admin1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$bMoKYhKghsavRSFj10dJZOR5kFy1Fp5HQXaDQuBRvFxTDwnyk8YWK', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:20:55');
INSERT INTO `as_user` VALUES ('b7c4dd07-d551-11e9-8fc5-0242ac110005', '', 2, '陈旭', 'chenxutest1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$K1UFSG3gJt06.r5uDOjqbOumUpQEEVkXnnbOfI3j1U9sfARlcrBX6', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-12 19:37:36');
INSERT INTO `as_user` VALUES ('b834cdb1-e11d-11e9-9b6c-0242ac120002', '', 2, '聂富强', 'nfq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$AD79sBw5vBVJRSnQ6kQLbu8Df0XgW7CBHASmVlNE.MhQoEAv4VFXS', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-27 19:55:37');
INSERT INTO `as_user` VALUES ('c8df9984-d824-11e9-9107-0242ac120003', '', 2, '测试人', 'test190916', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$pc4Q5uUxNpqKzzaz2DA4TO3Mt.jHqjoi1bwvL5f2nfXCh5sYtrkGq', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-09-16 09:53:31');
INSERT INTO `as_user` VALUES ('c9fff538-d552-11e9-8fc5-0242ac110005', '', NULL, '陈旭2', 'testchenxu2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$0n7qVWPeefwaVWZGyS.eB.cs/ddRybVkNS9u/t/9I86tVGjPfhW5m', NULL, NULL, 1, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-09-12 19:45:16');
INSERT INTO `as_user` VALUES ('dadf34f6-ac21-11e9-893f-005056c00001', '', 2, '测试账号7', 'test7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UBTqyrADA3QDz2IeCNcYC.PuvzrUhKcAN5CqC0jhId2mM/EYa3Ma6', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-22 09:41:42');
INSERT INTO `as_user` VALUES ('e50a6b48-aaad-11e9-9ea9-005056c00001', '', 2, '测试账号', 'test1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$VH13GCSR6yGtK8xN5AVrte0JxYuFeYorwYUT1GDyYCrsEukhlu/GC', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:19:06');
INSERT INTO `as_user` VALUES ('e9fa10e7-b1b4-11e9-be71-005056c00001', '', 2, '测试账号', 'test291155', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$enaRxsKpjGFYcnh7Sd5mw.czRTJ3tJOUwl9WrmOyTN6WW6V5Qbdo6', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-29 11:56:59');
INSERT INTO `as_user` VALUES ('f33e68b5-b1d3-11e9-a676-005056c00001', '', 2, '测试账号1310', 'test291537', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$hft.q2.hZeQHCm94WklENOyuN9VJcsVBj3Jaj9ln7diwOlDTDhMUi', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-29 15:39:09');
INSERT INTO `as_user` VALUES ('fa8eef7d-aab3-11e9-93a9-005056c00001', '', 2, '测试账号4', 'test5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$iVCleetTjjRBlqPsexyLiOL96NWUvi8UGOQc.tWW8yU6YDP1idpdq', NULL, NULL, 0, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 14:02:39');
INSERT INTO `as_user` VALUES ('ff5cffc9-d552-11e9-8fc5-0242ac110005', '', NULL, '陈旭', 'chenxutest2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$TcevgOi6Un1cPu24G9K7sejTG8hMLv8WxiiTLx./Qum0/Wlr8O2M.', NULL, NULL, 1, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-09-12 19:46:46');

-- ----------------------------
-- Table structure for as_user_role
-- ----------------------------
DROP TABLE IF EXISTS `as_user_role`;
CREATE TABLE `as_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_user_role
-- ----------------------------
INSERT INTO `as_user_role` VALUES (1, '1', NULL, 1, 1);
INSERT INTO `as_user_role` VALUES (2, 'dadf34f6-ac21-11e9-893f-005056c00001', '2019-07-22 09:45:20', 2, 1);
INSERT INTO `as_user_role` VALUES (3, '88efd746-b1b4-11e9-8d9b-005056c00001', '2019-07-29 11:56:40', 2, 1);
INSERT INTO `as_user_role` VALUES (4, 'f33e68b5-b1d3-11e9-a676-005056c00001', '2019-07-29 15:40:19', 2, 1);
INSERT INTO `as_user_role` VALUES (5, '96eb42f4-b1dd-11e9-80df-005056c00001', '2019-07-29 16:48:22', 2, 1);
INSERT INTO `as_user_role` VALUES (6, '0c2aa772-a6c9-11e9-9a6d-005056c00001', '2019-07-30 11:17:01', 2, 1);
INSERT INTO `as_user_role` VALUES (7, 'f33e68b5-b1d3-11e9-a676-005056c00001', '2019-07-30 11:19:56', 2, 1);
INSERT INTO `as_user_role` VALUES (8, '5ec0cdb1-b28c-11e9-9cb3-005056c00001', '2019-07-30 13:40:50', 2, 1);
INSERT INTO `as_user_role` VALUES (9, 'f33e68b5-b1d3-11e9-a676-005056c00001', '2019-07-30 13:50:22', 2, 1);
INSERT INTO `as_user_role` VALUES (10, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 11:08:04', 2, 1);
INSERT INTO `as_user_role` VALUES (11, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:16:47', 2, 1);
INSERT INTO `as_user_role` VALUES (12, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:20:51', 2, 1);
INSERT INTO `as_user_role` VALUES (13, '7', '2019-08-01 15:41:15', 2, 1);
INSERT INTO `as_user_role` VALUES (14, '7', '2019-08-01 15:42:09', 2, 1);
INSERT INTO `as_user_role` VALUES (15, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:42:41', 2, 1);
INSERT INTO `as_user_role` VALUES (16, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:49:23', 2, 1);
INSERT INTO `as_user_role` VALUES (17, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:56:54', 2, 1);
INSERT INTO `as_user_role` VALUES (18, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:56:50', 2, 1);
INSERT INTO `as_user_role` VALUES (19, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:57:19', 2, 1);
INSERT INTO `as_user_role` VALUES (20, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 15:58:40', 2, 1);
INSERT INTO `as_user_role` VALUES (21, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 16:02:47', 2, 1);
INSERT INTO `as_user_role` VALUES (22, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 16:12:02', 2, 1);
INSERT INTO `as_user_role` VALUES (23, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 16:21:36', 2, 1);
INSERT INTO `as_user_role` VALUES (24, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-01 16:26:05', 2, 1);
INSERT INTO `as_user_role` VALUES (25, '7', '2019-08-01 16:59:23', 2, 1);
INSERT INTO `as_user_role` VALUES (26, '7', '2019-08-02 10:34:49', 2, 1);
INSERT INTO `as_user_role` VALUES (27, '7', '2019-08-04 18:46:08', 2, 1);
INSERT INTO `as_user_role` VALUES (28, '7', '2019-08-04 18:46:25', 2, 1);
INSERT INTO `as_user_role` VALUES (29, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-04 18:46:47', 2, 1);
INSERT INTO `as_user_role` VALUES (30, '7', '2019-08-05 09:51:10', 2, 1);
INSERT INTO `as_user_role` VALUES (31, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-07 14:57:25', 2, 1);
INSERT INTO `as_user_role` VALUES (32, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-07 14:57:35', 2, 1);
INSERT INTO `as_user_role` VALUES (33, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-08-07 15:07:09', 2, 1);
INSERT INTO `as_user_role` VALUES (34, '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', '2019-08-12 22:11:39', 2, 1);
INSERT INTO `as_user_role` VALUES (35, '45daae24-bd0b-11e9-b0bf-9eb9361fdb0b', '2019-08-12 22:12:53', 2, 1);
INSERT INTO `as_user_role` VALUES (36, '6fa8bcb6-bd0b-11e9-b0bf-9eb9361fdb0b', '2019-08-12 22:14:03', 2, 1);
INSERT INTO `as_user_role` VALUES (37, '910f9c27-bd0b-11e9-b0bf-9eb9361fdb0b', '2019-08-12 22:14:59', 2, 1);
INSERT INTO `as_user_role` VALUES (42, '201909032005', '2019-09-09 10:24:37', 2, 1);
INSERT INTO `as_user_role` VALUES (43, '201909032005', '2019-09-09 10:25:42', 2, 1);
INSERT INTO `as_user_role` VALUES (44, '201909032005', '2019-09-09 10:50:23', 2, 1);
INSERT INTO `as_user_role` VALUES (45, 'b7c4dd07-d551-11e9-8fc5-0242ac110005', '2019-09-12 19:37:37', 2, 1);
INSERT INTO `as_user_role` VALUES (46, 'c8df9984-d824-11e9-9107-0242ac120003', '2019-09-16 09:53:31', 2, 1);
INSERT INTO `as_user_role` VALUES (47, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-09-16 14:36:39', 2, 1);
INSERT INTO `as_user_role` VALUES (48, '201909032004', '2019-09-18 12:12:32', 2, 1);
INSERT INTO `as_user_role` VALUES (49, '939f0bd7-b409-11e9-9442-0e0b7ec70eea', '2019-09-18 13:28:47', 2, 1);
INSERT INTO `as_user_role` VALUES (50, '7', '2019-09-19 10:45:40', 2, 1);
INSERT INTO `as_user_role` VALUES (51, 'b834cdb1-e11d-11e9-9b6c-0242ac120002', '2019-09-27 19:55:38', 2, 1);
INSERT INTO `as_user_role` VALUES (52, '0bb9b0ce-aab2-11e9-80fb-005056c00001', '2019-09-27 20:31:56', 2, 1);

-- ----------------------------
-- Table structure for as_user_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_user_scene`;
CREATE TABLE `as_user_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '场景编号',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `node_principal` int(2) NOT NULL DEFAULT 0,
  `node_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT '部门id',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1152212633756119127 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_user_scene
-- ----------------------------
INSERT INTO `as_user_scene` VALUES (1, 'e65edc60-96ee-11e9-ac96-005056c00001', '1', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (2, '5cb53ed0-96f0-11e9-867c-005056c00001', '1', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (13, 'e65edc60-96ee-11e9-ac96-005056c00001', 'b3b89840-a6c8-11e9-9a6d-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (14, '0c370383-a6c9-11e9-9a6d-005056c00001', '0c2aa772-a6c9-11e9-9a6d-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (15, 'a1d7c397-a6c9-11e9-bb43-005056c00001', 'a1cb1966-a6c9-11e9-bb43-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (16, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (17, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (18, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119043, '31cc5795-aaa2-11e9-aeaf-005056c00001', '31bfad64-aaa2-11e9-aeaf-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119044, '81d27c21-aaad-11e9-afaa-005056c00001', '81c5f900-aaad-11e9-afaa-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119045, 'e516ee69-aaad-11e9-9ea9-005056c00001', 'e50a6b48-aaad-11e9-9ea9-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119046, 'f1907b8a-aaae-11e9-a974-005056c00001', 'fd2e92bb-aaae-11e9-a974-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119047, '0bc633ef-aab2-11e9-80fb-005056c00001', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119048, 'e65edc60-96ee-11e9-ac96-005056c00001', '977e0670-aab2-11e9-80fb-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119049, 'fa9ecdfe-aab3-11e9-93a9-005056c00001', 'fa8eef7d-aab3-11e9-93a9-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119050, '63b9d993-aab4-11e9-95fb-005056c00001', '63a910b2-aab4-11e9-95fb-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119051, 'daeb69f7-ac21-11e9-893f-005056c00001', 'dadf34f6-ac21-11e9-893f-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119052, 'f1907b8a-aaae-11e9-a974-005056c00001', '4b4d7d72-b1b4-11e9-8d9b-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119053, 'f1907b8a-aaae-11e9-a974-005056c00001', '723a3e03-b1b4-11e9-8d9b-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119054, '89371977-b1b4-11e9-8d9b-005056c00001', '88efd746-b1b4-11e9-8d9b-005056c00001', 0, 'top', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119055, '89371977-b1b4-11e9-8d9b-005056c00001', 'e9fa10e7-b1b4-11e9-be71-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119056, '89371977-b1b4-11e9-8d9b-005056c00001', '62b516a4-b1c1-11e9-9b51-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119057, 'f34a76a6-b1d3-11e9-a676-005056c00001', 'f33e68b5-b1d3-11e9-a676-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119058, '96f750e5-b1dd-11e9-80df-005056c00001', '96eb42f4-b1dd-11e9-80df-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119059, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, '1', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119060, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, '1', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119061, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 0, '1', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119062, '5a9d7930-b278-11e9-bc73-005056c00001', '0c2aa772-a6c9-11e9-9a6d-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119063, '5a9d7930-b278-11e9-bc73-005056c00001', 'f33e68b5-b1d3-11e9-a676-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119064, '89371977-b1b4-11e9-8d9b-005056c00001', '5ec0cdb1-b28c-11e9-9cb3-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119065, '89371977-b1b4-11e9-8d9b-005056c00001', '0c419cd2-b28d-11e9-9cb3-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119066, '96f750e5-b1dd-11e9-80df-005056c00001', '0c419cd2-b28d-11e9-9cb3-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119067, '5a9d7930-b278-11e9-bc73-005056c00001', 'f33e68b5-b1d3-11e9-a676-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119068, '89371977-b1b4-11e9-8d9b-005056c00001', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119069, '5a9d7930-b278-11e9-bc73-005056c00001', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119070, '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119071, 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119072, '89371977-b1b4-11e9-8d9b-005056c00001', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119073, '9c4172d0-b432-11e9-bebb-0e0b7ec70eea', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119074, 'bbcaff63-b435-11e9-bebb-0e0b7ec70eea', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119075, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119076, 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, 'eda002cccfbe46b0b4a0a5b2db05c202', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119077, 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', '7', 0, 'eda002cccfbe46b0b4a0a5b2db05c202', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119078, 'c0995055-b435-11e9-bebb-0e0b7ec70eea', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119079, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119080, 'e65edc60-96ee-11e9-ac96-005056c00001', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '087505eb0f4b83f0b134541d9df8cf46', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119081, 'e65edc60-96ee-11e9-ac96-005056c00001', '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 0, '8d91df9ebeb13adfc44c93e22095bc3e', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119082, 'e65edc60-96ee-11e9-ac96-005056c00001', '45daae24-bd0b-11e9-b0bf-9eb9361fdb0b', 1, '8d91df9ebeb13adfc44c93e22095bc3e', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119083, 'e65edc60-96ee-11e9-ac96-005056c00001', '6fa8bcb6-bd0b-11e9-b0bf-9eb9361fdb0b', 0, '4a407736f58fdd8a69b84c98431aa0c2', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119084, 'e65edc60-96ee-11e9-ac96-005056c00001', '910f9c27-bd0b-11e9-b0bf-9eb9361fdb0b', 0, 'f66053c7fec66a098d36709c87f1021b', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119085, 'e65edc60-96ee-11e9-ac96-005056c00001', 'fd2e92bb-aaae-11e9-a974-005056c00001', 0, 'f66053c7fec66a098d36709c87f1021b', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119086, 'e65edc60-96ee-11e9-ac96-005056c00001', '201909032004', 1, '201909032033', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119087, 'e65edc60-96ee-11e9-ac96-005056c00001', '201909032005', 1, '201909032036', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119088, 'e65edc60-96ee-11e9-ac96-005056c00001', '201909032006', 1, 'f66053c7fec66a098d36709c87f1021b', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119089, 'e65edc60-96ee-11e9-ac96-005056c00001', '201909032007', 1, '8d91df9ebeb13adfc44c93e22095bc3e', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119090, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', '201909032005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119091, 'c2cc35e6-b435-11e9-bebb-0e0b7ec70eea', '201909032005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119092, '89371977-b1b4-11e9-8d9b-005056c00001', '201909032005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119093, 'be23bc74-b435-11e9-bebb-0e0b7ec70eea', '201909032005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119094, 'e65edc60-96ee-11e9-ac96-005056c00001', 'b7c4dd07-d551-11e9-8fc5-0242ac110005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119095, 'e65edc60-96ee-11e9-ac96-005056c00001', 'c9fff538-d552-11e9-8fc5-0242ac110005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119096, 'e65edc60-96ee-11e9-ac96-005056c00001', 'ff5cffc9-d552-11e9-8fc5-0242ac110005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119097, 'e65edc60-96ee-11e9-ac96-005056c00001', 'c8df9984-d824-11e9-9107-0242ac120003', 0, '201909032033', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119098, '89371977-b1b4-11e9-8d9b-005056c00001', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119099, '89371977-b1b4-11e9-8d9b-005056c00001', '0c2aa772-a6c9-11e9-9a6d-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119100, '0a10cbbb-d840-11e9-acac-0242ac120003', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119101, '0a10cbbb-d840-11e9-acac-0242ac120003', '0c2aa772-a6c9-11e9-9a6d-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 0);
INSERT INTO `as_user_scene` VALUES (1152212633756119102, '44edf3bd-d84c-11e9-8ada-0242ac120003', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119103, '89371977-b1b4-11e9-8d9b-005056c00001', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119104, '919fd4a1-d9cb-11e9-b749-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119105, '919fd4a1-d9cb-11e9-b749-0242ac120003', '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119106, '81cf08e2-d9ce-11e9-b832-0242ac120003', '19b87663-bd0b-11e9-b0bf-9eb9361fdb0b', 1, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119107, '81cf08e2-d9ce-11e9-b832-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119108, '81cf08e2-d9ce-11e9-b832-0242ac120003', '201909032005', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119109, '81cf08e2-d9ce-11e9-b832-0242ac120003', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119110, 'e65edc60-96ee-11e9-ac96-005056c00001', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119111, '96c1a722-dab5-11e9-8c14-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119112, '96c1a722-dab5-11e9-8c14-0242ac120003', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119113, '96c1a722-dab5-11e9-8c14-0242ac120003', '939f0bd7-b409-11e9-9442-0e0b7ec70eea', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119114, '8f91a633-daba-11e9-8c14-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119115, '8f91a633-daba-11e9-8c14-0242ac120003', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119116, '8f91a633-daba-11e9-8c14-0242ac120003', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119117, 'bb5e45a4-e025-11e9-8c14-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119118, 'bb5e45a4-e025-11e9-8c14-0242ac120003', '4', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119119, 'bb5e45a4-e025-11e9-8c14-0242ac120003', 'dadf34f6-ac21-11e9-893f-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119120, 'bb5e45a4-e025-11e9-8c14-0242ac120003', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119121, 'e88175ea-e029-11e9-a3b4-0242ac120003', '7', 0, '8d91df9ebeb13adfc44c93e22095bc3e', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119122, 'e88175ea-e029-11e9-a3b4-0242ac120003', '201909032004', 0, '8d91df9ebeb13adfc44c93e22095bc3e', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119123, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', '201909032004', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119124, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', '7', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119125, '96a6b730-e11d-11e9-9b6c-0242ac120002', 'b834cdb1-e11d-11e9-9b6c-0242ac120002', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119126, 'a48cb47b-e02f-11e9-a3b4-0242ac120003', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 0, '507d123e-9a6c-11e9-a169-005056c00001', 1);

-- ----------------------------
-- Table structure for blade_client
-- ----------------------------
DROP TABLE IF EXISTS `blade_client`;
CREATE TABLE `blade_client`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端id',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端密钥',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源集合',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NOT NULL COMMENT '令牌过期秒数',
  `refresh_token_validity` int(11) NOT NULL COMMENT '刷新令牌过期秒数',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加说明',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自动授权',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL COMMENT '状态',
  `is_deleted` int(2) NOT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_client
-- ----------------------------
INSERT INTO `blade_client` VALUES (1, 'sword', 'sword_secret', NULL, 'all', 'refresh_token,password,authorization_code', 'http://localhost:8888', NULL, 3600, 604800, NULL, NULL, 1, '2019-03-24 10:40:55', 1, '2019-03-24 10:40:59', 1, 0);
INSERT INTO `blade_client` VALUES (2, 'saber', 'saber_secret', NULL, 'all', 'refresh_token,password,authorization_code', 'http://localhost:8080', NULL, 3600, 604800, NULL, NULL, 1, '2019-03-24 10:42:29', 1, '2019-03-24 10:42:32', 1, 0);

-- ----------------------------
-- Table structure for blade_code
-- ----------------------------
DROP TABLE IF EXISTS `blade_code`;
CREATE TABLE `blade_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `code_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `table_prefix` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表前缀',
  `pk_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主键名',
  `package_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后端包名',
  `api_path` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后端路径',
  `web_path` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端路径',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_code
-- ----------------------------
INSERT INTO `blade_code` VALUES (1, 'blade-demo', '通知公告', 'blade_notice', 'blade_', 'id', 'org.springblade.desktop', 'D:\\Develop\\WorkSpace\\Git\\SpringBlade\\blade-ops\\blade-develop', 'D:\\Develop\\WorkSpace\\Git\\Sword', 0);

-- ----------------------------
-- Table structure for blade_dept
-- ----------------------------
DROP TABLE IF EXISTS `blade_dept`;
CREATE TABLE `blade_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父主键',
  `dept_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名',
  `full_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门全称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_dept
-- ----------------------------
INSERT INTO `blade_dept` VALUES (1, '000000', 0, '刀锋科技', '江苏刀锋科技有限公司', 1, NULL, 0);
INSERT INTO `blade_dept` VALUES (2, '000000', 1, '常州刀锋', '常州刀锋科技有限公司', 1, NULL, 0);
INSERT INTO `blade_dept` VALUES (3, '000000', 1, '苏州刀锋', '苏州刀锋科技有限公司', 1, NULL, 0);

-- ----------------------------
-- Table structure for blade_dict
-- ----------------------------
DROP TABLE IF EXISTS `blade_dict`;
CREATE TABLE `blade_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父主键',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典码',
  `dict_key` int(2) NULL DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_dict
-- ----------------------------
INSERT INTO `blade_dict` VALUES (1, 0, 'sex', -1, '性别', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (2, 1, 'sex', 1, '男', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (3, 1, 'sex', 2, '女', 2, NULL, 0);
INSERT INTO `blade_dict` VALUES (4, 0, 'notice', -1, '通知类型', 2, NULL, 0);
INSERT INTO `blade_dict` VALUES (5, 4, 'notice', 1, '发布通知', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (6, 4, 'notice', 2, '批转通知', 2, NULL, 0);
INSERT INTO `blade_dict` VALUES (7, 4, 'notice', 3, '转发通知', 3, NULL, 0);
INSERT INTO `blade_dict` VALUES (8, 4, 'notice', 4, '指示通知', 4, NULL, 0);
INSERT INTO `blade_dict` VALUES (9, 4, 'notice', 5, '任免通知', 5, NULL, 0);
INSERT INTO `blade_dict` VALUES (10, 4, 'notice', 6, '事务通知', 6, NULL, 0);
INSERT INTO `blade_dict` VALUES (11, 0, 'menu_category', -1, '菜单类型', 3, NULL, 0);
INSERT INTO `blade_dict` VALUES (12, 11, 'menu_category', 1, '菜单', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (13, 11, 'menu_category', 2, '按钮', 2, NULL, 0);
INSERT INTO `blade_dict` VALUES (14, 0, 'button_func', -1, '按钮功能', 4, NULL, 0);
INSERT INTO `blade_dict` VALUES (15, 14, 'button_func', 1, '工具栏', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (16, 14, 'button_func', 2, '操作栏', 2, NULL, 0);
INSERT INTO `blade_dict` VALUES (17, 14, 'button_func', 3, '工具操作栏', 3, NULL, 0);
INSERT INTO `blade_dict` VALUES (18, 0, 'yes_no', -1, '是否', 5, NULL, 0);
INSERT INTO `blade_dict` VALUES (19, 18, 'yes_no', 1, '否', 1, NULL, 0);
INSERT INTO `blade_dict` VALUES (20, 18, 'yes_no', 2, '是', 2, NULL, 0);

-- ----------------------------
-- Table structure for blade_log_api
-- ----------------------------
DROP TABLE IF EXISTS `blade_log_api`;
CREATE TABLE `blade_log_api`  (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器环境',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `remote_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
  `method_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法类',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作提交的数据',
  `time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blade_log_error
-- ----------------------------
DROP TABLE IF EXISTS `blade_log_error`;
CREATE TABLE `blade_log_error`  (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统环境',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `stack_trace` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '堆栈',
  `exception_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常名',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
  `line_number` int(11) NULL DEFAULT NULL COMMENT '错误行数',
  `method_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法类',
  `file_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作提交的数据',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blade_log_usual
-- ----------------------------
DROP TABLE IF EXISTS `blade_log_usual`;
CREATE TABLE `blade_log_usual`  (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统环境',
  `log_level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志级别',
  `log_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志业务id',
  `log_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '日志数据',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作提交的数据',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blade_menu
-- ----------------------------
DROP TABLE IF EXISTS `blade_menu`;
CREATE TABLE `blade_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级菜单',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单别名',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单资源',
  `sort` int(2) NULL DEFAULT NULL COMMENT '排序',
  `category` int(2) NULL DEFAULT NULL COMMENT '菜单类型',
  `action` int(2) NULL DEFAULT 0 COMMENT '操作按钮类型',
  `is_open` int(2) NULL DEFAULT 1 COMMENT '是否打开新页面',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_menu
-- ----------------------------
INSERT INTO `blade_menu` VALUES (1, 0, 'desk', '工作台', 'menu', '/desk', 'iconfont iconicon_airplay', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (2, 1, 'notice', '通知公告', 'menu', '/desk/notice', 'iconfont iconicon_sms', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (3, 0, 'system', '系统管理', 'menu', '/system', 'iconfont iconicon_setting', 2, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (4, 3, 'user', '用户管理', 'menu', '/system/user', 'iconfont iconicon_principal', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (5, 3, 'dept', '部门管理', 'menu', '/system/dept', 'iconfont iconicon_group', 2, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (6, 3, 'dict', '字典管理', 'menu', '/system/dict', 'iconfont iconicon_addresslist', 3, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (7, 3, 'menu', '菜单管理', 'menu', '/system/menu', 'iconfont iconicon_subordinate', 4, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (8, 3, 'role', '角色管理', 'menu', '/system/role', 'iconfont iconicon_boss', 5, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (9, 3, 'param', '参数管理', 'menu', '/system/param', 'iconfont iconicon_community_line', 6, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (10, 0, 'monitor', '系统监控', 'menu', '/monitor', 'iconfont icon-yanzhengma', 3, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (11, 10, 'doc', '接口文档', 'menu', 'http://localhost/doc.html', 'iconfont iconicon_study', 1, 1, 0, 2, NULL, 0);
INSERT INTO `blade_menu` VALUES (12, 10, 'admin', '服务治理', 'menu', 'http://localhost:7002', 'iconfont icon-canshu', 2, 1, 0, 2, NULL, 0);
INSERT INTO `blade_menu` VALUES (13, 10, 'log', '日志管理', 'menu', '/monitor/log', 'iconfont iconicon_doc', 3, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (14, 13, 'log_usual', '通用日志', 'menu', '/monitor/log/usual', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (15, 13, 'log_api', '接口日志', 'menu', '/monitor/log/api', NULL, 2, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (16, 13, 'log_error', '错误日志', 'menu', '/monitor/log/error', NULL, 3, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (17, 0, 'tool', '研发工具', 'menu', '/tool', 'iconfont icon-wxbgongju', 4, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (18, 17, 'code', '代码生成', 'menu', '/tool/code', 'iconfont iconicon_savememo', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (19, 2, 'notice_add', '新增', 'add', '/desk/notice/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (20, 2, 'notice_edit', '修改', 'edit', '/desk/notice/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (21, 2, 'notice_delete', '删除', 'delete', '/api/blade-desk/notice/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (22, 2, 'notice_view', '查看', 'view', '/desk/notice/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (23, 4, 'user_add', '新增', 'add', '/system/user/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (24, 4, 'user_edit', '修改', 'edit', '/system/user/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (25, 4, 'user_delete', '删除', 'delete', '/api/blade-user/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (26, 4, 'user_role', '角色配置', 'role', NULL, 'user-add', 4, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (27, 4, 'user_reset', '密码重置', 'reset-password', '/api/blade-user/reset-password', 'retweet', 5, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (28, 4, 'user_view', '查看', 'view', '/system/user/view', 'file-text', 6, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (29, 5, 'dept_add', '新增', 'add', '/system/dept/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (30, 5, 'dept_edit', '修改', 'edit', '/system/dept/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (31, 5, 'dept_delete', '删除', 'delete', '/api/blade-system/dept/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (32, 5, 'dept_view', '查看', 'view', '/system/dept/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (33, 6, 'dict_add', '新增', 'add', '/system/dict/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (34, 6, 'dict_edit', '修改', 'edit', '/system/dict/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (35, 6, 'dict_delete', '删除', 'delete', '/api/blade-system/dict/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (36, 6, 'dict_view', '查看', 'view', '/system/dict/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (37, 7, 'menu_add', '新增', 'add', '/system/menu/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (38, 7, 'menu_edit', '修改', 'edit', '/system/menu/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (39, 7, 'menu_delete', '删除', 'delete', '/api/blade-system/menu/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (40, 7, 'menu_view', '查看', 'view', '/system/menu/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (41, 8, 'role_add', '新增', 'add', '/system/role/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (42, 8, 'role_edit', '修改', 'edit', '/system/role/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (43, 8, 'role_delete', '删除', 'delete', '/api/blade-system/role/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (44, 8, 'role_view', '查看', 'view', '/system/role/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (45, 9, 'param_add', '新增', 'add', '/system/param/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (46, 9, 'param_edit', '修改', 'edit', '/system/param/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (47, 9, 'param_delete', '删除', 'delete', '/api/blade-system/param/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (48, 9, 'param_view', '查看', 'view', '/system/param/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (49, 14, 'log_usual_view', '查看', 'view', '/monitor/log/usual/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (50, 15, 'log_api_view', '查看', 'view', '/monitor/log/api/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (51, 16, 'log_error_view', '查看', 'view', '/monitor/log/error/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (52, 18, 'code_add', '新增', 'add', '/tool/code/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (53, 18, 'code_edit', '修改', 'edit', '/tool/code/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (54, 18, 'code_delete', '删除', 'delete', '/api/blade-system/code/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (55, 18, 'code_view', '查看', 'view', '/tool/code/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (56, 3, 'tenant', '租户管理', 'menu', '/system/tenant', 'iconfont icon-quanxian', 7, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (57, 56, 'tenant_add', '新增', 'add', '/system/tenant/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (58, 56, 'tenant_edit', '修改', 'edit', '/system/tenant/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (59, 56, 'tenant_delete', '删除', 'delete', '/api/blade-system/tenant/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (60, 56, 'tenant_view', '查看', 'view', '/system/tenant/view', 'file-text', 4, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (61, 3, 'client', '应用管理', 'menu', '/system/client', 'iconfont iconicon_mobilephone', 8, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (62, 61, 'client_add', '新增', 'add', '/system/client/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu` VALUES (63, 61, 'client_edit', '修改', 'edit', '/system/client/edit', 'form', 2, 2, 2, 2, NULL, 0);
INSERT INTO `blade_menu` VALUES (64, 61, 'client_delete', '删除', 'delete', '/api/blade-system/client/remove', 'delete', 3, 2, 3, 3, NULL, 0);
INSERT INTO `blade_menu` VALUES (65, 61, 'client_view', '查看', 'view', '/system/client/view', 'file-text', 4, 2, 2, 2, NULL, 0);

-- ----------------------------
-- Table structure for blade_notice
-- ----------------------------
DROP TABLE IF EXISTS `blade_notice`;
CREATE TABLE `blade_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `category` int(11) NULL DEFAULT NULL COMMENT '类型',
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_notice
-- ----------------------------
INSERT INTO `blade_notice` VALUES (23, '000000', '测试公告', 3, '2018-12-31 20:03:31', '222', 1, '2018-12-05 20:03:31', 1, '2018-12-28 11:10:51', 1, 0);
INSERT INTO `blade_notice` VALUES (24, '000000', '测试公告2', 1, '2018-12-05 20:03:31', '333', 1, '2018-12-28 10:32:26', 1, '2018-12-28 11:10:34', 1, 0);
INSERT INTO `blade_notice` VALUES (25, '000000', '测试公告3', 6, '2018-12-29 00:00:00', '11111', 1, '2018-12-28 11:03:44', 1, '2018-12-28 11:10:28', 1, 0);

-- ----------------------------
-- Table structure for blade_param
-- ----------------------------
DROP TABLE IF EXISTS `blade_param`;
CREATE TABLE `blade_param`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `param_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数键',
  `param_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_param
-- ----------------------------
INSERT INTO `blade_param` VALUES (1, '是否开启注册功能', 'account.registerUser', 'true', '开启注册', 1, '2018-12-28 12:19:01', 1, '2018-12-28 12:19:01', 1, 0);
INSERT INTO `blade_param` VALUES (2, '账号初始密码', 'account.initPassword', '123456', '初始密码', 1, '2018-12-28 12:19:01', 1, '2018-12-28 12:19:01', 1, 0);

-- ----------------------------
-- Table structure for blade_role
-- ----------------------------
DROP TABLE IF EXISTS `blade_role`;
CREATE TABLE `blade_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `role_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色别名',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_role
-- ----------------------------
INSERT INTO `blade_role` VALUES (1, '000000', 0, '超级管理员', 1, 'administrator', 0);
INSERT INTO `blade_role` VALUES (2, '000000', 0, '用户', 2, 'user', 0);

-- ----------------------------
-- Table structure for blade_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `blade_role_menu`;
CREATE TABLE `blade_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_role_menu
-- ----------------------------
INSERT INTO `blade_role_menu` VALUES (1, 1, 1);
INSERT INTO `blade_role_menu` VALUES (2, 2, 1);
INSERT INTO `blade_role_menu` VALUES (3, 3, 1);
INSERT INTO `blade_role_menu` VALUES (4, 4, 1);
INSERT INTO `blade_role_menu` VALUES (5, 5, 1);
INSERT INTO `blade_role_menu` VALUES (6, 6, 1);
INSERT INTO `blade_role_menu` VALUES (7, 7, 1);
INSERT INTO `blade_role_menu` VALUES (8, 8, 1);
INSERT INTO `blade_role_menu` VALUES (9, 9, 1);
INSERT INTO `blade_role_menu` VALUES (10, 10, 1);
INSERT INTO `blade_role_menu` VALUES (11, 11, 1);
INSERT INTO `blade_role_menu` VALUES (12, 12, 1);
INSERT INTO `blade_role_menu` VALUES (13, 13, 1);
INSERT INTO `blade_role_menu` VALUES (14, 14, 1);
INSERT INTO `blade_role_menu` VALUES (15, 15, 1);
INSERT INTO `blade_role_menu` VALUES (16, 16, 1);
INSERT INTO `blade_role_menu` VALUES (17, 17, 1);
INSERT INTO `blade_role_menu` VALUES (18, 18, 1);
INSERT INTO `blade_role_menu` VALUES (19, 19, 1);
INSERT INTO `blade_role_menu` VALUES (20, 20, 1);
INSERT INTO `blade_role_menu` VALUES (21, 21, 1);
INSERT INTO `blade_role_menu` VALUES (22, 22, 1);
INSERT INTO `blade_role_menu` VALUES (23, 23, 1);
INSERT INTO `blade_role_menu` VALUES (24, 24, 1);
INSERT INTO `blade_role_menu` VALUES (25, 25, 1);
INSERT INTO `blade_role_menu` VALUES (26, 26, 1);
INSERT INTO `blade_role_menu` VALUES (27, 27, 1);
INSERT INTO `blade_role_menu` VALUES (28, 28, 1);
INSERT INTO `blade_role_menu` VALUES (29, 29, 1);
INSERT INTO `blade_role_menu` VALUES (30, 30, 1);
INSERT INTO `blade_role_menu` VALUES (31, 31, 1);
INSERT INTO `blade_role_menu` VALUES (32, 32, 1);
INSERT INTO `blade_role_menu` VALUES (33, 33, 1);
INSERT INTO `blade_role_menu` VALUES (34, 34, 1);
INSERT INTO `blade_role_menu` VALUES (35, 35, 1);
INSERT INTO `blade_role_menu` VALUES (36, 36, 1);
INSERT INTO `blade_role_menu` VALUES (37, 37, 1);
INSERT INTO `blade_role_menu` VALUES (38, 38, 1);
INSERT INTO `blade_role_menu` VALUES (39, 39, 1);
INSERT INTO `blade_role_menu` VALUES (40, 40, 1);
INSERT INTO `blade_role_menu` VALUES (41, 41, 1);
INSERT INTO `blade_role_menu` VALUES (42, 42, 1);
INSERT INTO `blade_role_menu` VALUES (43, 43, 1);
INSERT INTO `blade_role_menu` VALUES (44, 44, 1);
INSERT INTO `blade_role_menu` VALUES (45, 45, 1);
INSERT INTO `blade_role_menu` VALUES (46, 46, 1);
INSERT INTO `blade_role_menu` VALUES (47, 47, 1);
INSERT INTO `blade_role_menu` VALUES (48, 48, 1);
INSERT INTO `blade_role_menu` VALUES (49, 49, 1);
INSERT INTO `blade_role_menu` VALUES (50, 50, 1);
INSERT INTO `blade_role_menu` VALUES (51, 51, 1);
INSERT INTO `blade_role_menu` VALUES (52, 52, 1);
INSERT INTO `blade_role_menu` VALUES (53, 53, 1);
INSERT INTO `blade_role_menu` VALUES (54, 54, 1);
INSERT INTO `blade_role_menu` VALUES (55, 55, 1);
INSERT INTO `blade_role_menu` VALUES (56, 56, 1);
INSERT INTO `blade_role_menu` VALUES (57, 57, 1);
INSERT INTO `blade_role_menu` VALUES (58, 58, 1);
INSERT INTO `blade_role_menu` VALUES (59, 59, 1);
INSERT INTO `blade_role_menu` VALUES (60, 60, 1);
INSERT INTO `blade_role_menu` VALUES (61, 61, 1);
INSERT INTO `blade_role_menu` VALUES (62, 62, 1);
INSERT INTO `blade_role_menu` VALUES (63, 63, 1);
INSERT INTO `blade_role_menu` VALUES (64, 64, 1);
INSERT INTO `blade_role_menu` VALUES (65, 65, 1);

-- ----------------------------
-- Table structure for blade_tenant
-- ----------------------------
DROP TABLE IF EXISTS `blade_tenant`;
CREATE TABLE `blade_tenant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编号',
  `tenant_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  `linkman` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_tenant
-- ----------------------------
INSERT INTO `blade_tenant` VALUES (1, '000000', '管理组', 'admin', '666666', '管理组', 1, '2019-01-01 00:00:39', 1, '2019-01-01 00:00:39', 1, 0);

-- ----------------------------
-- Table structure for blade_user
-- ----------------------------
DROP TABLE IF EXISTS `blade_user`;
CREATE TABLE `blade_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `account` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真名',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别',
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blade_user
-- ----------------------------
INSERT INTO `blade_user` VALUES (1, '000000', 'admin', '90b9aa7e25f80cf4f64e990b78a9fc5ebd6cecad', '管理员', '管理员', 'admin@bladex.vip', '22233322', '2018-08-08 00:00:00', 1, '1', '1', 1, '2018-08-08 00:00:00', 1, '2018-08-08 00:00:00', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
