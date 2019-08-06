/*
 Navicat Premium Data Transfer

 Source Server         : 10.1.18.178
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 10.1.18.178:3306
 Source Schema         : asset-idm

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 22/07/2019 13:55:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for as_application
-- ----------------------------
DROP TABLE IF EXISTS `as_application`;
CREATE TABLE `as_application`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `application_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块图标',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：1、可用；0、不可用',
  `is_published` int(1) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `disable_time` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `remove_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_application
-- ----------------------------
INSERT INTO `as_application` VALUES ('60b16393-8e70-11e9-b311-005056c00001', '测试应用', 'icon-cgfk', 1, 0, '2019-06-14 14:48:12', NULL, NULL);
INSERT INTO `as_application` VALUES ('6fc74e52-8e6e-11e9-b311-005056c00001', '采购管理', 'icon-cgfk', 1, 0, '2019-06-14 14:34:18', NULL, NULL);

-- ----------------------------
-- Table structure for as_menu
-- ----------------------------
DROP TABLE IF EXISTS `as_menu`;
CREATE TABLE `as_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级菜单',
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
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_menu
-- ----------------------------
INSERT INTO `as_menu` VALUES (1, 0, 'home', '主页', 'menu', '/home', 'iconfont iconicon_airplay', 1, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (2, 1, 'desk', '控制台', 'menu', '/home/desk', 'iconfont iconicon_sms', 1, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (3, 1, 'factory', '系统管理', 'menu', '/home/factory', 'iconfont iconicon_setting', 2, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (4, 3, 'application', '应用管理', 'menu', '/menu/app/menus', 'iconfont iconicon_principal', 1, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (5, 3, 'organization', '组织机构', 'menu', '/organ/rest/scene', 'iconfont iconicon_group', 2, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (6, 3, 'authority', '权限管理', 'menu', '/home/factory/authority', 'iconfont iconicon_addresslist', 3, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (7, 3, 'menu', '菜单管理', 'menu', '/system/menu', 'iconfont iconicon_subordinate', 4, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (8, 3, 'role', '角色管理', 'menu', '/role/roles', 'iconfont iconicon_boss', 5, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (9, 3, 'param', '参数管理', 'menu', '/system/param', 'iconfont iconicon_community_line', 6, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (10, 0, 'monitor', '系统监控', 'menu', '/monitor', 'iconfont icon-yanzhengma', 3, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (11, 4, 'app_add', '新增应用', 'add', '/home/factory/application/add', 'iconfont iconicon_study', 1, 1, 0, 2, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (12, 4, 'app_delete', '删除应用', 'delete', '/home/factory/application/remove', 'iconfont icon-canshu', 2, 1, 0, 2, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (13, 4, 'app_edit', '编辑应用', 'edit', '/home/factory/application/edit', 'iconfont iconicon_doc', 3, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (14, 13, 'log_usual', '通用日志', 'menu', '/monitor/log/usual', NULL, 1, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (15, 13, 'log_api', '接口日志', 'menu', '/monitor/log/api', NULL, 2, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (16, 13, 'log_error', '错误日志', 'menu', '/monitor/log/error', NULL, 3, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (17, 0, 'tool', '研发工具', 'menu', '/tool', 'iconfont icon-wxbgongju', 4, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (18, 17, 'code', '代码生成', 'menu', '/tool/code', 'iconfont iconicon_savememo', 1, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (19, 2, 'notice_add', '新增', 'add', '/desk/notice/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (20, 2, 'notice_edit', '修改', 'edit', '/desk/notice/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (21, 2, 'notice_delete', '删除', 'delete', '/api/blade-desk/notice/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (22, 2, 'notice_view', '查看', 'view', '/desk/notice/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (23, 4, 'app_rename', '重命名', 'reset-name', '/home/factory/application/reset-name', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (24, 4, 'user_edit', '修改', 'edit', '/system/user/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (25, 4, 'user_delete', '删除', 'delete', '/api/blade-user/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (26, 4, 'user_role', '角色配置', 'role', NULL, 'user-add', 4, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (27, 4, 'user_reset', '密码重置', 'reset-password', '/api/blade-user/reset-password', 'retweet', 5, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (28, 4, 'user_view', '查看', 'view', '/system/user/view', 'file-text', 6, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (29, 5, 'dept_add', '新增', 'add', '/system/dept/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (30, 5, 'dept_edit', '修改', 'edit', '/system/dept/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (31, 5, 'dept_delete', '删除', 'delete', '/api/blade-system/dept/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (32, 5, 'dept_view', '查看', 'view', '/system/dept/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (33, 6, 'dict_add', '新增', 'add', '/system/dict/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (34, 6, 'dict_edit', '修改', 'edit', '/system/dict/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (35, 6, 'dict_delete', '删除', 'delete', '/api/blade-system/dict/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (36, 6, 'dict_view', '查看', 'view', '/system/dict/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (37, 7, 'menu_add', '新增', 'add', '/system/menu/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (38, 7, 'menu_edit', '修改', 'edit', '/system/menu/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (39, 7, 'menu_delete', '删除', 'delete', '/api/blade-system/menu/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (40, 7, 'menu_view', '查看', 'view', '/system/menu/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (41, 8, 'role_add', '新增', 'add', '/system/role/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (42, 8, 'role_edit', '修改', 'edit', '/system/role/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (43, 8, 'role_delete', '删除', 'delete', '/api/blade-system/role/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (44, 8, 'role_view', '查看', 'view', '/system/role/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (45, 9, 'param_add', '新增', 'add', '/system/param/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (46, 9, 'param_edit', '修改', 'edit', '/system/param/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (47, 9, 'param_delete', '删除', 'delete', '/api/blade-system/param/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (48, 9, 'param_view', '查看', 'view', '/system/param/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (49, 14, 'log_usual_view', '查看', 'view', '/monitor/log/usual/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (50, 15, 'log_api_view', '查看', 'view', '/monitor/log/api/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (51, 16, 'log_error_view', '查看', 'view', '/monitor/log/error/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (52, 18, 'code_add', '新增', 'add', '/tool/code/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (53, 18, 'code_edit', '修改', 'edit', '/tool/code/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (54, 18, 'code_delete', '删除', 'delete', '/api/blade-system/code/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (55, 18, 'code_view', '查看', 'view', '/tool/code/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (56, 3, 'tenant', '租户管理', 'menu', '/system/tenant', 'iconfont icon-quanxian', 7, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (57, 56, 'tenant_add', '新增', 'add', '/system/tenant/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (58, 56, 'tenant_edit', '修改', 'edit', '/system/tenant/edit', 'form', 2, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (59, 56, 'tenant_delete', '删除', 'delete', '/api/blade-system/tenant/remove', 'delete', 3, 2, 3, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (60, 56, 'tenant_view', '查看', 'view', '/system/tenant/view', 'file-text', 4, 2, 2, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (61, 3, 'client', '应用管理', 'menu', '/system/client', 'iconfont iconicon_mobilephone', 8, 1, 0, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (62, 61, 'client_add', '新增', 'add', '/system/client/add', 'plus', 1, 2, 1, 1, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (63, 61, 'client_edit', '修改', 'edit', '/system/client/edit', 'form', 2, 2, 2, 2, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (64, 61, 'client_delete', '删除', 'delete', '/api/blade-system/client/remove', 'delete', 3, 2, 3, 3, NULL, 0, NULL);
INSERT INTO `as_menu` VALUES (65, 61, 'client_view', '查看', 'view', '/system/client/view', 'file-text', 4, 2, 2, 2, NULL, 0, NULL);

-- ----------------------------
-- Table structure for as_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `as_menu_role`;
CREATE TABLE `as_menu_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单编号',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 228 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `as_menu_role` VALUES (158, 61, 16);
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
INSERT INTO `as_menu_role` VALUES (223, 61, 17);
INSERT INTO `as_menu_role` VALUES (224, 62, 17);
INSERT INTO `as_menu_role` VALUES (225, 63, 17);
INSERT INTO `as_menu_role` VALUES (226, 64, 17);
INSERT INTO `as_menu_role` VALUES (227, 65, 17);

-- ----------------------------
-- Table structure for as_organ_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_organ_scene`;
CREATE TABLE `as_organ_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `principal_id` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '负责人id',
  `scene_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作场景id',
  `status` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_organ_scene
-- ----------------------------
INSERT INTO `as_organ_scene` VALUES (1, '1', 'top', '浙江省政府办公厅', NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (2, 'b1789bb8-9293-11e9-ae19-005056c00001', '1', '浙江省财政厅', NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (3, '6c7758a6-9353-11e9-8653-005056c00001', 'b1789bb8-9293-11e9-ae19-005056c00001', '浙江省财政厅资产处', NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (4, '00e86a20-9357-11e9-846c-005056c00001', '6c7758a6-9353-11e9-8653-005056c00001', '浙江省财政厅资产处人事部', NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (5, 'top', '', '顶级节点', NULL, '582d71e6-a77b-11e9-88fa-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (6, 'top', '', '顶级节点', NULL, '4075767c-a79b-11e9-a6df-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (7, 'top', '', '顶级节点', NULL, '28e48620-a79d-11e9-a4b2-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (8, 'top', '', '顶级节点', NULL, '82e33b75-aa0c-11e9-89ff-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (9, 'top', '', '顶级节点', NULL, '01ae7d34-aa0d-11e9-9d8b-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (10, 'top', '', '顶级节点', NULL, 'e65edc60-96ee-11e9-ac96-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (13, 'top', '', '顶级节点', NULL, '31cc5795-aaa2-11e9-aeaf-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (14, 'top', '', '顶级节点', NULL, 'fe358293-aaab-11e9-9589-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (15, 'top', '', '顶级节点', NULL, '0d1e3c54-aaad-11e9-89d6-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (16, 'top', '', '顶级节点', NULL, '81d27c21-aaad-11e9-afaa-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (17, 'top', '', '顶级节点', NULL, 'e516ee69-aaad-11e9-9ea9-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (18, 'top', '', '顶级节点', NULL, 'f1907b8a-aaae-11e9-a974-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (19, 'top', '', '顶级节点', NULL, '0bc633ef-aab2-11e9-80fb-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (20, 'top', '', '顶级节点', NULL, 'fa9ecdfe-aab3-11e9-93a9-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (21, 'top', '', '顶级节点', NULL, '63b9d993-aab4-11e9-95fb-005056c00001', 1);
INSERT INTO `as_organ_scene` VALUES (22, 'top', '', '顶级节点', NULL, 'daeb69f7-ac21-11e9-893f-005056c00001', 1);

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
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '插入时间',
  `is_deleted` int(2) NULL DEFAULT NULL,
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态 1：有效 0：无效',
  `sort` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `tenant_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_organ_tree
-- ----------------------------
INSERT INTO `as_organ_tree` VALUES ('09b3bbbc-9a6c-11e9-a169-005056c00001', '浙江省交通运输厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 16:24:57', NULL, NULL, NULL, NULL, '2019-06-29 20:47:22', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('0a6bf7cb1abd4807a0a62a204ff3f30f', '123', NULL, 'be6ef9d0-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:27:09', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('14861e7d-9a6c-11e9-a169-005056c00001', '浙江省商务厅', NULL, '507d123e-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:47:40', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('507d123e-9a6c-11e9-a169-005056c00001', '浙江省政府办公厅', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:49:21', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('83ae9b4677b84f10bf2f9e03575f88f5', '胡海杰', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 16:55:10', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b2ff459f-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处', NULL, 'ff6221bb-9a6b-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 20:52:06', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('b5b5fb74e13345ee866b0bd1fdb1ab19', '啥', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:05:56', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('b82839e240b748a78117c3523967bbfd', '测试节点2', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:03:24', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('be6ef9d0-9a6c-11e9-a169-005056c00001', '浙江省财政厅资产处人事处', NULL, 'b2ff459f-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:14:17', NULL, NULL, NULL, NULL, '2019-06-29 20:52:25', 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('cc762ffeab6546859439f6999c154e2f', '测试节点3', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('cf526e3128df4948b790ec8c4e79719b', '陈旭', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 16:54:41', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('d270ea1b736b42388b1569ded3741d10', '叶柄跃', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:05:05', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
INSERT INTO `as_organ_tree` VALUES ('e86bf5190ffc4f92932cfb0ad9869954', '测试节点1', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:03:23', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('eda002cccfbe46b0b4a0a5b2db05c202', '测试节点', NULL, 'top', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 17:02:51', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, NULL);
INSERT INTO `as_organ_tree` VALUES ('fd6befc4afa249fe8720f067da1e0420', '海', NULL, 'be6ef9d0-9a6c-11e9-a169-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-20 17:08:20', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL);
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
  `sort` int(2) NULL DEFAULT NULL,
  `category` int(2) NULL DEFAULT NULL,
  `remark` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表单分组id',
  `group_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表单分组名称',
  `scene_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场景id',
  `is_deleted` int(2) NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `remove_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `path`(`path`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1138 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_resource
-- ----------------------------
INSERT INTO `as_resource` VALUES (1080, 0, 'app', '测试应用', '应用图标', '6fc74e52-8e6e-11e9-b311-005056c00001', 0, 0, 1, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:33', NULL);
INSERT INTO `as_resource` VALUES (1081, 1080, 'form', '测试表单', '表单图标', '1619819', 0, 0, 2, NULL, '1', '测试分组', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1082, 1081, 'func', '新增', '', '1619819/list/add', 1, 1, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1083, 1081, 'func', '导入', '', '1619819/list/import', 1, 2, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1084, 1081, 'func', '导出', '', '1619819/list/export', 1, 3, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1085, 1081, 'func', '删除', '', '1619819/list/delete', 1, 4, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1086, 1081, 'func', '暂存', '', '1619819/form/add_temp', 2, 5, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1087, 1081, 'func', '提交', '', '1619819/form/save', 2, 6, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1088, 1081, 'func', '提交并添加', '', '1619819/form/save_add', 2, 7, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1089, 1081, 'func', '打印二维码', '', '1619819/list/printQR', 2, 8, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1090, 1081, 'func', '打印', '', '1619819/form/print', 3, 9, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1091, 1081, 'func', '删除', '', '1619819/form/delete', 3, 10, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 15:22:49', NULL);
INSERT INTO `as_resource` VALUES (1104, 1080, 'form', '测试表单1', '表单图标', '16198191221', 0, 0, 2, NULL, '1', '测试分组', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1105, 1104, 'func', '新增', '', '16198191221/list/add', 1, 1, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1106, 1104, 'func', '导入', '', '16198191221/list/import', 1, 2, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1107, 1104, 'func', '导出', '', '16198191221/list/export', 1, 3, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1108, 1104, 'func', '删除', '', '16198191221/list/delete', 1, 4, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1109, 1104, 'func', '暂存', '', '16198191221/form/add_temp', 2, 5, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1110, 1104, 'func', '提交', '', '16198191221/form/save', 2, 6, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1111, 1104, 'func', '提交并添加', '', '16198191221/form/save_add', 2, 7, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1112, 1104, 'func', '打印二维码', '', '16198191221/list/printQR', 2, 8, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1113, 1104, 'func', '打印', '', '16198191221/form/print', 3, 9, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1114, 1104, 'func', '删除', '', '16198191221/form/delete', 3, 10, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:22:36', NULL);
INSERT INTO `as_resource` VALUES (1115, 1080, 'form', '测试表单2', '表单图标', '1619819122112', 0, 0, 2, NULL, '1', '测试分组', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1116, 1115, 'func', '新增', '', '1619819122112/list/add', 1, 1, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1117, 1115, 'func', '导入', '', '1619819122112/list/import', 1, 2, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1118, 1115, 'func', '导出', '', '1619819122112/list/export', 1, 3, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1119, 1115, 'func', '删除', '', '1619819122112/list/delete', 1, 4, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1120, 1115, 'func', '暂存', '', '1619819122112/form/add_temp', 2, 5, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1121, 1115, 'func', '提交', '', '1619819122112/form/save', 2, 6, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1122, 1115, 'func', '提交并添加', '', '1619819122112/form/save_add', 2, 7, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1123, 1115, 'func', '打印二维码', '', '1619819122112/list/printQR', 2, 8, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1124, 1115, 'func', '打印', '', '1619819122112/form/print', 3, 9, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1125, 1115, 'func', '删除', '', '1619819122112/form/delete', 3, 10, 3, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:29:39', NULL);
INSERT INTO `as_resource` VALUES (1126, 1080, 'form', '测试表单3', '表单图标', '16198191221123', 0, 0, 2, NULL, '1', '测试分组', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1127, 1126, 'func', '新增', '', '16198191221123/list/add', 1, 1, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1128, 1126, 'func', '导入', '', '16198191221123/list/import', 1, 2, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1129, 1126, 'func', '导出', '', '16198191221123/list/export', 1, 3, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1130, 1126, 'func', '删除', '', '16198191221123/list/delete', 1, 4, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1131, 1126, 'func', '暂存', '', '16198191221123/form/add_temp', 2, 5, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1132, 1126, 'func', '提交', '', '16198191221123/form/save', 2, 6, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1133, 1126, 'func', '提交并添加', '', '16198191221123/form/save_add', 2, 7, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1134, 1126, 'func', '打印二维码', '', '16198191221123/list/printQR', 2, 8, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1135, 1126, 'func', '打印', '', '16198191221123/form/print', 3, 9, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1136, 1126, 'func', '删除', '', '16198191221123/form/delete', 3, 10, 3, NULL, '', '', NULL, 0, '2019-07-18 16:30:45', NULL);
INSERT INTO `as_resource` VALUES (1137, 0, 'app', '测试应用1', '应用图标', '60b16393-8e70-11e9-b311-005056c00001', 0, 0, 1, NULL, '', '', 'e65edc60-96ee-11e9-ac96-005056c00001', 0, '2019-07-18 16:46:38', NULL);

-- ----------------------------
-- Table structure for as_resource1
-- ----------------------------
DROP TABLE IF EXISTS `as_resource1`;
CREATE TABLE `as_resource1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keep_alive` tinyint(1) NULL DEFAULT NULL,
  `require_auth` tinyint(1) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  `resource_type` int(1) NULL DEFAULT NULL COMMENT '资源类型：1、菜单；2、应用；3、表单；4、报表',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  CONSTRAINT `as_resource1_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `as_resource1` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_resource1
-- ----------------------------
INSERT INTO `as_resource1` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `as_resource1` VALUES (2, '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource1` VALUES (3, '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource1` VALUES (4, '/', '/home', 'Home', '薪资管理', 'fa fa-money', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource1` VALUES (5, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource1` VALUES (6, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource1` VALUES (7, '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', NULL, NULL, 1, 2, 1, NULL);
INSERT INTO `as_resource1` VALUES (8, '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', NULL, NULL, 1, 2, 0, NULL);
INSERT INTO `as_resource1` VALUES (9, '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', NULL, NULL, 1, 3, 0, NULL);
INSERT INTO `as_resource1` VALUES (10, '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource1` VALUES (11, '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource1` VALUES (12, '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource1` VALUES (13, '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource1` VALUES (14, '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource1` VALUES (15, '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource1` VALUES (16, '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource1` VALUES (17, '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource1` VALUES (18, '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource1` VALUES (19, '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource1` VALUES (20, '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource1` VALUES (21, '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource1` VALUES (22, '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource1` VALUES (23, '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (24, '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (25, '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (26, '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (27, '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (28, '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource1` VALUES (29, '/', '/application', 'Home', '应用管理', 'fa fa-user-circle-o', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource1` VALUES (30, '/', '/organization', 'Home', '组织架构', 'fa fa-address-card-o', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource1` VALUES (31, '/', '/authority', 'Home', '权限管理', 'fa fa-money', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource1` VALUES (32, '/', '/companyInfo', 'Home', '企业信息', 'fa fa-bar-chart', NULL, 1, 1, 1, NULL);

-- ----------------------------
-- Table structure for as_resource_role
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_role`;
CREATE TABLE `as_resource_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1068 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_resource_role
-- ----------------------------
INSERT INTO `as_resource_role` VALUES (1011, 1080, 1);
INSERT INTO `as_resource_role` VALUES (1012, 1081, 1);
INSERT INTO `as_resource_role` VALUES (1013, 1082, 1);
INSERT INTO `as_resource_role` VALUES (1014, 1083, 1);
INSERT INTO `as_resource_role` VALUES (1015, 1084, 1);
INSERT INTO `as_resource_role` VALUES (1016, 1085, 1);
INSERT INTO `as_resource_role` VALUES (1017, 1086, 1);
INSERT INTO `as_resource_role` VALUES (1018, 1087, 1);
INSERT INTO `as_resource_role` VALUES (1019, 1088, 1);
INSERT INTO `as_resource_role` VALUES (1020, 1089, 1);
INSERT INTO `as_resource_role` VALUES (1021, 1090, 1);
INSERT INTO `as_resource_role` VALUES (1022, 1091, 1);
INSERT INTO `as_resource_role` VALUES (1023, 1092, 1);
INSERT INTO `as_resource_role` VALUES (1024, 1093, 1);
INSERT INTO `as_resource_role` VALUES (1025, 1094, 1);
INSERT INTO `as_resource_role` VALUES (1026, 1095, 1);
INSERT INTO `as_resource_role` VALUES (1027, 1096, 1);
INSERT INTO `as_resource_role` VALUES (1028, 1097, 1);
INSERT INTO `as_resource_role` VALUES (1029, 1098, 1);
INSERT INTO `as_resource_role` VALUES (1030, 1099, 1);
INSERT INTO `as_resource_role` VALUES (1031, 1100, 1);
INSERT INTO `as_resource_role` VALUES (1032, 1101, 1);
INSERT INTO `as_resource_role` VALUES (1033, 1102, 1);
INSERT INTO `as_resource_role` VALUES (1034, 1104, 1);
INSERT INTO `as_resource_role` VALUES (1035, 1105, 1);
INSERT INTO `as_resource_role` VALUES (1036, 1106, 1);
INSERT INTO `as_resource_role` VALUES (1037, 1107, 1);
INSERT INTO `as_resource_role` VALUES (1038, 1108, 1);
INSERT INTO `as_resource_role` VALUES (1039, 1109, 1);
INSERT INTO `as_resource_role` VALUES (1040, 1110, 1);
INSERT INTO `as_resource_role` VALUES (1041, 1111, 1);
INSERT INTO `as_resource_role` VALUES (1042, 1112, 1);
INSERT INTO `as_resource_role` VALUES (1043, 1113, 1);
INSERT INTO `as_resource_role` VALUES (1044, 1114, 1);
INSERT INTO `as_resource_role` VALUES (1045, 1115, 1);
INSERT INTO `as_resource_role` VALUES (1046, 1116, 1);
INSERT INTO `as_resource_role` VALUES (1047, 1117, 1);
INSERT INTO `as_resource_role` VALUES (1048, 1118, 1);
INSERT INTO `as_resource_role` VALUES (1049, 1119, 1);
INSERT INTO `as_resource_role` VALUES (1050, 1120, 1);
INSERT INTO `as_resource_role` VALUES (1051, 1121, 1);
INSERT INTO `as_resource_role` VALUES (1052, 1122, 1);
INSERT INTO `as_resource_role` VALUES (1053, 1123, 1);
INSERT INTO `as_resource_role` VALUES (1054, 1124, 1);
INSERT INTO `as_resource_role` VALUES (1055, 1125, 1);
INSERT INTO `as_resource_role` VALUES (1056, 1126, 1);
INSERT INTO `as_resource_role` VALUES (1057, 1127, 1);
INSERT INTO `as_resource_role` VALUES (1058, 1128, 1);
INSERT INTO `as_resource_role` VALUES (1059, 1129, 1);
INSERT INTO `as_resource_role` VALUES (1060, 1130, 1);
INSERT INTO `as_resource_role` VALUES (1061, 1131, 1);
INSERT INTO `as_resource_role` VALUES (1062, 1132, 1);
INSERT INTO `as_resource_role` VALUES (1063, 1133, 1);
INSERT INTO `as_resource_role` VALUES (1064, 1134, 1);
INSERT INTO `as_resource_role` VALUES (1065, 1135, 1);
INSERT INTO `as_resource_role` VALUES (1066, 1136, 1);
INSERT INTO `as_resource_role` VALUES (1067, 1137, 1);

-- ----------------------------
-- Table structure for as_resource_role1
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_role1`;
CREATE TABLE `as_resource_role1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `as_resource_role1_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `as_resource1` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `as_resource_role1_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 362 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_resource_role1
-- ----------------------------
INSERT INTO `as_resource_role1` VALUES (161, 7, 3);
INSERT INTO `as_resource_role1` VALUES (162, 7, 6);
INSERT INTO `as_resource_role1` VALUES (163, 9, 6);
INSERT INTO `as_resource_role1` VALUES (164, 10, 6);
INSERT INTO `as_resource_role1` VALUES (165, 11, 6);
INSERT INTO `as_resource_role1` VALUES (166, 12, 6);
INSERT INTO `as_resource_role1` VALUES (167, 13, 6);
INSERT INTO `as_resource_role1` VALUES (168, 14, 6);
INSERT INTO `as_resource_role1` VALUES (169, 15, 6);
INSERT INTO `as_resource_role1` VALUES (170, 16, 6);
INSERT INTO `as_resource_role1` VALUES (171, 17, 6);
INSERT INTO `as_resource_role1` VALUES (172, 18, 6);
INSERT INTO `as_resource_role1` VALUES (173, 19, 6);
INSERT INTO `as_resource_role1` VALUES (174, 20, 6);
INSERT INTO `as_resource_role1` VALUES (175, 21, 6);
INSERT INTO `as_resource_role1` VALUES (176, 22, 6);
INSERT INTO `as_resource_role1` VALUES (177, 23, 6);
INSERT INTO `as_resource_role1` VALUES (178, 25, 6);
INSERT INTO `as_resource_role1` VALUES (179, 26, 6);
INSERT INTO `as_resource_role1` VALUES (180, 27, 6);
INSERT INTO `as_resource_role1` VALUES (181, 28, 6);
INSERT INTO `as_resource_role1` VALUES (182, 24, 6);
INSERT INTO `as_resource_role1` VALUES (317, 29, 1);
INSERT INTO `as_resource_role1` VALUES (318, 30, 1);
INSERT INTO `as_resource_role1` VALUES (319, 31, 1);
INSERT INTO `as_resource_role1` VALUES (320, 32, 1);
INSERT INTO `as_resource_role1` VALUES (337, 29, 2);
INSERT INTO `as_resource_role1` VALUES (338, 30, 2);
INSERT INTO `as_resource_role1` VALUES (339, 31, 2);
INSERT INTO `as_resource_role1` VALUES (340, 32, 2);
INSERT INTO `as_resource_role1` VALUES (342, 7, 4);
INSERT INTO `as_resource_role1` VALUES (343, 10, 4);
INSERT INTO `as_resource_role1` VALUES (344, 11, 4);
INSERT INTO `as_resource_role1` VALUES (345, 12, 4);
INSERT INTO `as_resource_role1` VALUES (346, 13, 4);
INSERT INTO `as_resource_role1` VALUES (347, 14, 4);
INSERT INTO `as_resource_role1` VALUES (348, 15, 4);
INSERT INTO `as_resource_role1` VALUES (349, 16, 4);
INSERT INTO `as_resource_role1` VALUES (350, 17, 4);
INSERT INTO `as_resource_role1` VALUES (351, 18, 4);
INSERT INTO `as_resource_role1` VALUES (352, 19, 4);
INSERT INTO `as_resource_role1` VALUES (353, 20, 4);
INSERT INTO `as_resource_role1` VALUES (354, 21, 4);
INSERT INTO `as_resource_role1` VALUES (355, 22, 4);
INSERT INTO `as_resource_role1` VALUES (356, 23, 4);
INSERT INTO `as_resource_role1` VALUES (357, 24, 4);
INSERT INTO `as_resource_role1` VALUES (358, 25, 4);
INSERT INTO `as_resource_role1` VALUES (359, 26, 4);
INSERT INTO `as_resource_role1` VALUES (360, 27, 4);
INSERT INTO `as_resource_role1` VALUES (361, 28, 4);

-- ----------------------------
-- Table structure for as_resource_role2
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_role2`;
CREATE TABLE `as_resource_role2`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单编号',
  `role_id` int(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 967 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of as_resource_role2
-- ----------------------------
INSERT INTO `as_resource_role2` VALUES (583, 623, 1);
INSERT INTO `as_resource_role2` VALUES (584, 624, 1);
INSERT INTO `as_resource_role2` VALUES (585, 625, 1);
INSERT INTO `as_resource_role2` VALUES (586, 626, 1);
INSERT INTO `as_resource_role2` VALUES (587, 627, 1);
INSERT INTO `as_resource_role2` VALUES (588, 628, 1);
INSERT INTO `as_resource_role2` VALUES (589, 629, 1);
INSERT INTO `as_resource_role2` VALUES (590, 630, 1);
INSERT INTO `as_resource_role2` VALUES (591, 631, 1);
INSERT INTO `as_resource_role2` VALUES (592, 632, 1);
INSERT INTO `as_resource_role2` VALUES (593, 633, 1);
INSERT INTO `as_resource_role2` VALUES (594, 634, 1);
INSERT INTO `as_resource_role2` VALUES (595, 635, 1);
INSERT INTO `as_resource_role2` VALUES (596, 636, 1);
INSERT INTO `as_resource_role2` VALUES (597, 637, 1);
INSERT INTO `as_resource_role2` VALUES (598, 638, 1);
INSERT INTO `as_resource_role2` VALUES (599, 639, 1);
INSERT INTO `as_resource_role2` VALUES (600, 640, 1);
INSERT INTO `as_resource_role2` VALUES (601, 641, 1);
INSERT INTO `as_resource_role2` VALUES (602, 642, 1);
INSERT INTO `as_resource_role2` VALUES (603, 643, 1);
INSERT INTO `as_resource_role2` VALUES (604, 644, 1);
INSERT INTO `as_resource_role2` VALUES (605, 645, 1);
INSERT INTO `as_resource_role2` VALUES (606, 646, 1);
INSERT INTO `as_resource_role2` VALUES (607, 647, 1);
INSERT INTO `as_resource_role2` VALUES (608, 648, 1);
INSERT INTO `as_resource_role2` VALUES (609, 649, 1);
INSERT INTO `as_resource_role2` VALUES (610, 650, 1);
INSERT INTO `as_resource_role2` VALUES (611, 651, 1);
INSERT INTO `as_resource_role2` VALUES (612, 652, 1);
INSERT INTO `as_resource_role2` VALUES (613, 653, 1);
INSERT INTO `as_resource_role2` VALUES (614, 654, 1);
INSERT INTO `as_resource_role2` VALUES (615, 655, 1);
INSERT INTO `as_resource_role2` VALUES (616, 656, 1);
INSERT INTO `as_resource_role2` VALUES (617, 657, 1);
INSERT INTO `as_resource_role2` VALUES (618, 658, 1);
INSERT INTO `as_resource_role2` VALUES (619, 659, 1);
INSERT INTO `as_resource_role2` VALUES (620, 660, 1);
INSERT INTO `as_resource_role2` VALUES (621, 661, 1);
INSERT INTO `as_resource_role2` VALUES (622, 662, 1);
INSERT INTO `as_resource_role2` VALUES (623, 663, 1);
INSERT INTO `as_resource_role2` VALUES (624, 664, 1);
INSERT INTO `as_resource_role2` VALUES (625, 665, 1);
INSERT INTO `as_resource_role2` VALUES (626, 666, 1);
INSERT INTO `as_resource_role2` VALUES (627, 667, 1);
INSERT INTO `as_resource_role2` VALUES (628, 668, 1);
INSERT INTO `as_resource_role2` VALUES (629, 669, 1);
INSERT INTO `as_resource_role2` VALUES (630, 670, 1);
INSERT INTO `as_resource_role2` VALUES (631, 671, 1);
INSERT INTO `as_resource_role2` VALUES (632, 672, 1);
INSERT INTO `as_resource_role2` VALUES (633, 673, 1);
INSERT INTO `as_resource_role2` VALUES (634, 674, 1);
INSERT INTO `as_resource_role2` VALUES (635, 675, 1);
INSERT INTO `as_resource_role2` VALUES (636, 676, 1);
INSERT INTO `as_resource_role2` VALUES (637, 677, 1);
INSERT INTO `as_resource_role2` VALUES (638, 678, 1);
INSERT INTO `as_resource_role2` VALUES (639, 679, 1);
INSERT INTO `as_resource_role2` VALUES (640, 680, 1);
INSERT INTO `as_resource_role2` VALUES (641, 681, 1);
INSERT INTO `as_resource_role2` VALUES (642, 682, 1);
INSERT INTO `as_resource_role2` VALUES (643, 683, 1);
INSERT INTO `as_resource_role2` VALUES (644, 684, 1);
INSERT INTO `as_resource_role2` VALUES (645, 685, 1);
INSERT INTO `as_resource_role2` VALUES (646, 686, 1);
INSERT INTO `as_resource_role2` VALUES (647, 687, 1);
INSERT INTO `as_resource_role2` VALUES (648, 688, 1);
INSERT INTO `as_resource_role2` VALUES (649, 689, 1);
INSERT INTO `as_resource_role2` VALUES (650, 690, 1);
INSERT INTO `as_resource_role2` VALUES (651, 691, 1);
INSERT INTO `as_resource_role2` VALUES (652, 692, 1);
INSERT INTO `as_resource_role2` VALUES (653, 693, 1);
INSERT INTO `as_resource_role2` VALUES (654, 694, 1);
INSERT INTO `as_resource_role2` VALUES (655, 695, 1);
INSERT INTO `as_resource_role2` VALUES (656, 696, 1);
INSERT INTO `as_resource_role2` VALUES (657, 697, 1);
INSERT INTO `as_resource_role2` VALUES (658, 698, 1);
INSERT INTO `as_resource_role2` VALUES (659, 699, 1);
INSERT INTO `as_resource_role2` VALUES (660, 700, 1);
INSERT INTO `as_resource_role2` VALUES (661, 701, 1);
INSERT INTO `as_resource_role2` VALUES (662, 702, 1);
INSERT INTO `as_resource_role2` VALUES (663, 703, 1);
INSERT INTO `as_resource_role2` VALUES (664, 704, 1);
INSERT INTO `as_resource_role2` VALUES (665, 705, 1);
INSERT INTO `as_resource_role2` VALUES (666, 706, 1);
INSERT INTO `as_resource_role2` VALUES (667, 707, 1);
INSERT INTO `as_resource_role2` VALUES (668, 708, 1);
INSERT INTO `as_resource_role2` VALUES (669, 709, 1);
INSERT INTO `as_resource_role2` VALUES (670, 710, 1);
INSERT INTO `as_resource_role2` VALUES (671, 711, 1);
INSERT INTO `as_resource_role2` VALUES (672, 712, 1);
INSERT INTO `as_resource_role2` VALUES (673, 713, 1);
INSERT INTO `as_resource_role2` VALUES (674, 714, 1);
INSERT INTO `as_resource_role2` VALUES (675, 715, 1);
INSERT INTO `as_resource_role2` VALUES (676, 716, 1);
INSERT INTO `as_resource_role2` VALUES (677, 717, 1);
INSERT INTO `as_resource_role2` VALUES (678, 718, 1);
INSERT INTO `as_resource_role2` VALUES (679, 719, 1);
INSERT INTO `as_resource_role2` VALUES (680, 720, 1);
INSERT INTO `as_resource_role2` VALUES (681, 721, 1);
INSERT INTO `as_resource_role2` VALUES (682, 722, 1);
INSERT INTO `as_resource_role2` VALUES (683, 723, 1);
INSERT INTO `as_resource_role2` VALUES (684, 724, 1);
INSERT INTO `as_resource_role2` VALUES (685, 725, 1);
INSERT INTO `as_resource_role2` VALUES (686, 726, 1);
INSERT INTO `as_resource_role2` VALUES (687, 727, 1);
INSERT INTO `as_resource_role2` VALUES (688, 728, 1);
INSERT INTO `as_resource_role2` VALUES (689, 729, 1);
INSERT INTO `as_resource_role2` VALUES (690, 730, 1);
INSERT INTO `as_resource_role2` VALUES (691, 731, 1);
INSERT INTO `as_resource_role2` VALUES (692, 732, 1);
INSERT INTO `as_resource_role2` VALUES (693, 733, 1);
INSERT INTO `as_resource_role2` VALUES (694, 734, 1);
INSERT INTO `as_resource_role2` VALUES (695, 735, 1);
INSERT INTO `as_resource_role2` VALUES (696, 736, 1);
INSERT INTO `as_resource_role2` VALUES (697, 737, 1);
INSERT INTO `as_resource_role2` VALUES (698, 738, 1);
INSERT INTO `as_resource_role2` VALUES (699, 739, 1);
INSERT INTO `as_resource_role2` VALUES (700, 740, 1);
INSERT INTO `as_resource_role2` VALUES (701, 741, 1);
INSERT INTO `as_resource_role2` VALUES (702, 742, 1);
INSERT INTO `as_resource_role2` VALUES (703, 743, 1);
INSERT INTO `as_resource_role2` VALUES (704, 744, 1);
INSERT INTO `as_resource_role2` VALUES (705, 745, 1);
INSERT INTO `as_resource_role2` VALUES (706, 746, 1);
INSERT INTO `as_resource_role2` VALUES (707, 747, 1);
INSERT INTO `as_resource_role2` VALUES (708, 748, 1);
INSERT INTO `as_resource_role2` VALUES (709, 749, 1);
INSERT INTO `as_resource_role2` VALUES (710, 750, 1);
INSERT INTO `as_resource_role2` VALUES (711, 751, 1);
INSERT INTO `as_resource_role2` VALUES (712, 752, 1);
INSERT INTO `as_resource_role2` VALUES (713, 753, 1);
INSERT INTO `as_resource_role2` VALUES (714, 754, 1);
INSERT INTO `as_resource_role2` VALUES (715, 755, 1);
INSERT INTO `as_resource_role2` VALUES (716, 756, 1);
INSERT INTO `as_resource_role2` VALUES (717, 757, 1);
INSERT INTO `as_resource_role2` VALUES (718, 758, 1);
INSERT INTO `as_resource_role2` VALUES (719, 759, 1);
INSERT INTO `as_resource_role2` VALUES (720, 760, 1);
INSERT INTO `as_resource_role2` VALUES (721, 761, 1);
INSERT INTO `as_resource_role2` VALUES (722, 762, 1);
INSERT INTO `as_resource_role2` VALUES (723, 763, 1);
INSERT INTO `as_resource_role2` VALUES (724, 764, 1);
INSERT INTO `as_resource_role2` VALUES (725, 765, 1);
INSERT INTO `as_resource_role2` VALUES (726, 766, 1);
INSERT INTO `as_resource_role2` VALUES (727, 767, 1);
INSERT INTO `as_resource_role2` VALUES (728, 768, 1);
INSERT INTO `as_resource_role2` VALUES (729, 769, 1);
INSERT INTO `as_resource_role2` VALUES (730, 770, 1);
INSERT INTO `as_resource_role2` VALUES (731, 771, 1);
INSERT INTO `as_resource_role2` VALUES (732, 772, 1);
INSERT INTO `as_resource_role2` VALUES (733, 773, 1);
INSERT INTO `as_resource_role2` VALUES (734, 774, 1);
INSERT INTO `as_resource_role2` VALUES (735, 775, 1);
INSERT INTO `as_resource_role2` VALUES (736, 776, 1);
INSERT INTO `as_resource_role2` VALUES (737, 777, 1);
INSERT INTO `as_resource_role2` VALUES (738, 778, 1);
INSERT INTO `as_resource_role2` VALUES (739, 779, 1);
INSERT INTO `as_resource_role2` VALUES (740, 780, 1);
INSERT INTO `as_resource_role2` VALUES (741, 781, 1);
INSERT INTO `as_resource_role2` VALUES (742, 782, 1);
INSERT INTO `as_resource_role2` VALUES (743, 783, 1);
INSERT INTO `as_resource_role2` VALUES (744, 784, 1);
INSERT INTO `as_resource_role2` VALUES (745, 785, 1);
INSERT INTO `as_resource_role2` VALUES (746, 786, 1);
INSERT INTO `as_resource_role2` VALUES (747, 787, 1);
INSERT INTO `as_resource_role2` VALUES (748, 788, 1);
INSERT INTO `as_resource_role2` VALUES (749, 789, 1);
INSERT INTO `as_resource_role2` VALUES (750, 790, 1);
INSERT INTO `as_resource_role2` VALUES (751, 791, 1);
INSERT INTO `as_resource_role2` VALUES (752, 792, 1);
INSERT INTO `as_resource_role2` VALUES (753, 793, 1);
INSERT INTO `as_resource_role2` VALUES (754, 794, 1);
INSERT INTO `as_resource_role2` VALUES (755, 795, 1);
INSERT INTO `as_resource_role2` VALUES (756, 796, 1);
INSERT INTO `as_resource_role2` VALUES (757, 797, 1);
INSERT INTO `as_resource_role2` VALUES (758, 798, 1);
INSERT INTO `as_resource_role2` VALUES (759, 799, 1);
INSERT INTO `as_resource_role2` VALUES (760, 800, 1);
INSERT INTO `as_resource_role2` VALUES (761, 801, 1);
INSERT INTO `as_resource_role2` VALUES (762, 802, 1);
INSERT INTO `as_resource_role2` VALUES (763, 803, 1);
INSERT INTO `as_resource_role2` VALUES (764, 804, 1);
INSERT INTO `as_resource_role2` VALUES (765, 805, 1);
INSERT INTO `as_resource_role2` VALUES (766, 806, 1);
INSERT INTO `as_resource_role2` VALUES (767, 807, 1);
INSERT INTO `as_resource_role2` VALUES (768, 808, 1);
INSERT INTO `as_resource_role2` VALUES (769, 809, 1);
INSERT INTO `as_resource_role2` VALUES (770, 810, 1);
INSERT INTO `as_resource_role2` VALUES (771, 811, 1);
INSERT INTO `as_resource_role2` VALUES (772, 812, 1);
INSERT INTO `as_resource_role2` VALUES (773, 813, 1);
INSERT INTO `as_resource_role2` VALUES (774, 814, 1);
INSERT INTO `as_resource_role2` VALUES (775, 815, 1);
INSERT INTO `as_resource_role2` VALUES (776, 816, 1);
INSERT INTO `as_resource_role2` VALUES (777, 817, 1);
INSERT INTO `as_resource_role2` VALUES (778, 818, 1);
INSERT INTO `as_resource_role2` VALUES (779, 819, 1);
INSERT INTO `as_resource_role2` VALUES (780, 820, 1);
INSERT INTO `as_resource_role2` VALUES (781, 821, 1);
INSERT INTO `as_resource_role2` VALUES (782, 822, 1);
INSERT INTO `as_resource_role2` VALUES (783, 823, 1);
INSERT INTO `as_resource_role2` VALUES (784, 824, 1);
INSERT INTO `as_resource_role2` VALUES (785, 825, 1);
INSERT INTO `as_resource_role2` VALUES (786, 826, 1);
INSERT INTO `as_resource_role2` VALUES (787, 827, 1);
INSERT INTO `as_resource_role2` VALUES (788, 828, 1);
INSERT INTO `as_resource_role2` VALUES (789, 829, 1);
INSERT INTO `as_resource_role2` VALUES (790, 830, 1);
INSERT INTO `as_resource_role2` VALUES (791, 831, 1);
INSERT INTO `as_resource_role2` VALUES (792, 832, 1);
INSERT INTO `as_resource_role2` VALUES (793, 833, 1);
INSERT INTO `as_resource_role2` VALUES (794, 834, 1);
INSERT INTO `as_resource_role2` VALUES (795, 835, 1);
INSERT INTO `as_resource_role2` VALUES (796, 836, 1);
INSERT INTO `as_resource_role2` VALUES (797, 837, 1);
INSERT INTO `as_resource_role2` VALUES (798, 838, 1);
INSERT INTO `as_resource_role2` VALUES (799, 839, 1);
INSERT INTO `as_resource_role2` VALUES (800, 840, 1);
INSERT INTO `as_resource_role2` VALUES (801, 841, 1);
INSERT INTO `as_resource_role2` VALUES (802, 842, 1);
INSERT INTO `as_resource_role2` VALUES (803, 843, 1);
INSERT INTO `as_resource_role2` VALUES (804, 844, 1);
INSERT INTO `as_resource_role2` VALUES (805, 845, 1);
INSERT INTO `as_resource_role2` VALUES (806, 846, 1);
INSERT INTO `as_resource_role2` VALUES (807, 847, 1);
INSERT INTO `as_resource_role2` VALUES (808, 848, 1);
INSERT INTO `as_resource_role2` VALUES (809, 849, 1);
INSERT INTO `as_resource_role2` VALUES (810, 850, 1);
INSERT INTO `as_resource_role2` VALUES (811, 851, 1);
INSERT INTO `as_resource_role2` VALUES (812, 852, 1);
INSERT INTO `as_resource_role2` VALUES (813, 853, 1);
INSERT INTO `as_resource_role2` VALUES (814, 854, 1);
INSERT INTO `as_resource_role2` VALUES (815, 855, 1);
INSERT INTO `as_resource_role2` VALUES (816, 856, 1);
INSERT INTO `as_resource_role2` VALUES (817, 857, 1);
INSERT INTO `as_resource_role2` VALUES (818, 858, 1);
INSERT INTO `as_resource_role2` VALUES (819, 859, 1);
INSERT INTO `as_resource_role2` VALUES (820, 860, 1);
INSERT INTO `as_resource_role2` VALUES (821, 861, 1);
INSERT INTO `as_resource_role2` VALUES (822, 862, 1);
INSERT INTO `as_resource_role2` VALUES (823, 863, 1);
INSERT INTO `as_resource_role2` VALUES (824, 864, 1);
INSERT INTO `as_resource_role2` VALUES (825, 865, 1);
INSERT INTO `as_resource_role2` VALUES (826, 866, 1);
INSERT INTO `as_resource_role2` VALUES (827, 867, 1);
INSERT INTO `as_resource_role2` VALUES (828, 868, 1);
INSERT INTO `as_resource_role2` VALUES (829, 869, 1);
INSERT INTO `as_resource_role2` VALUES (830, 870, 1);
INSERT INTO `as_resource_role2` VALUES (831, 871, 1);
INSERT INTO `as_resource_role2` VALUES (832, 872, 1);
INSERT INTO `as_resource_role2` VALUES (833, 873, 1);
INSERT INTO `as_resource_role2` VALUES (834, 874, 1);
INSERT INTO `as_resource_role2` VALUES (835, 875, 1);
INSERT INTO `as_resource_role2` VALUES (836, 876, 1);
INSERT INTO `as_resource_role2` VALUES (837, 877, 1);
INSERT INTO `as_resource_role2` VALUES (838, 878, 1);
INSERT INTO `as_resource_role2` VALUES (839, 879, 1);
INSERT INTO `as_resource_role2` VALUES (840, 880, 1);
INSERT INTO `as_resource_role2` VALUES (841, 881, 1);
INSERT INTO `as_resource_role2` VALUES (842, 882, 1);
INSERT INTO `as_resource_role2` VALUES (843, 883, 1);
INSERT INTO `as_resource_role2` VALUES (844, 884, 1);
INSERT INTO `as_resource_role2` VALUES (845, 885, 1);
INSERT INTO `as_resource_role2` VALUES (846, 886, 1);
INSERT INTO `as_resource_role2` VALUES (847, 887, 1);
INSERT INTO `as_resource_role2` VALUES (848, 888, 1);
INSERT INTO `as_resource_role2` VALUES (849, 889, 1);
INSERT INTO `as_resource_role2` VALUES (850, 890, 1);
INSERT INTO `as_resource_role2` VALUES (851, 891, 1);
INSERT INTO `as_resource_role2` VALUES (852, 892, 1);
INSERT INTO `as_resource_role2` VALUES (853, 893, 1);
INSERT INTO `as_resource_role2` VALUES (854, 894, 1);
INSERT INTO `as_resource_role2` VALUES (855, 895, 1);
INSERT INTO `as_resource_role2` VALUES (856, 896, 1);
INSERT INTO `as_resource_role2` VALUES (857, 897, 1);
INSERT INTO `as_resource_role2` VALUES (858, 898, 1);
INSERT INTO `as_resource_role2` VALUES (859, 899, 1);
INSERT INTO `as_resource_role2` VALUES (860, 900, 1);
INSERT INTO `as_resource_role2` VALUES (861, 901, 1);
INSERT INTO `as_resource_role2` VALUES (862, 902, 1);
INSERT INTO `as_resource_role2` VALUES (863, 903, 1);
INSERT INTO `as_resource_role2` VALUES (864, 904, 1);
INSERT INTO `as_resource_role2` VALUES (865, 905, 1);
INSERT INTO `as_resource_role2` VALUES (866, 906, 1);
INSERT INTO `as_resource_role2` VALUES (867, 907, 1);
INSERT INTO `as_resource_role2` VALUES (868, 908, 1);
INSERT INTO `as_resource_role2` VALUES (869, 909, 1);
INSERT INTO `as_resource_role2` VALUES (870, 910, 1);
INSERT INTO `as_resource_role2` VALUES (871, 911, 1);
INSERT INTO `as_resource_role2` VALUES (872, 912, 1);
INSERT INTO `as_resource_role2` VALUES (873, 913, 1);
INSERT INTO `as_resource_role2` VALUES (874, 914, 1);
INSERT INTO `as_resource_role2` VALUES (875, 915, 1);
INSERT INTO `as_resource_role2` VALUES (876, 916, 1);
INSERT INTO `as_resource_role2` VALUES (877, 917, 1);
INSERT INTO `as_resource_role2` VALUES (878, 918, 1);
INSERT INTO `as_resource_role2` VALUES (879, 919, 1);
INSERT INTO `as_resource_role2` VALUES (880, 920, 1);
INSERT INTO `as_resource_role2` VALUES (881, 921, 1);
INSERT INTO `as_resource_role2` VALUES (882, 922, 1);
INSERT INTO `as_resource_role2` VALUES (883, 923, 1);
INSERT INTO `as_resource_role2` VALUES (884, 924, 1);
INSERT INTO `as_resource_role2` VALUES (885, 925, 1);
INSERT INTO `as_resource_role2` VALUES (886, 926, 1);
INSERT INTO `as_resource_role2` VALUES (887, 927, 1);
INSERT INTO `as_resource_role2` VALUES (888, 928, 1);
INSERT INTO `as_resource_role2` VALUES (889, 929, 1);
INSERT INTO `as_resource_role2` VALUES (890, 930, 1);
INSERT INTO `as_resource_role2` VALUES (891, 931, 1);
INSERT INTO `as_resource_role2` VALUES (892, 932, 1);
INSERT INTO `as_resource_role2` VALUES (893, 933, 1);
INSERT INTO `as_resource_role2` VALUES (894, 934, 1);
INSERT INTO `as_resource_role2` VALUES (895, 935, 1);
INSERT INTO `as_resource_role2` VALUES (896, 936, 1);
INSERT INTO `as_resource_role2` VALUES (897, 937, 1);
INSERT INTO `as_resource_role2` VALUES (898, 938, 1);
INSERT INTO `as_resource_role2` VALUES (899, 939, 1);
INSERT INTO `as_resource_role2` VALUES (900, 940, 1);
INSERT INTO `as_resource_role2` VALUES (901, 941, 1);
INSERT INTO `as_resource_role2` VALUES (902, 942, 1);
INSERT INTO `as_resource_role2` VALUES (903, 943, 1);
INSERT INTO `as_resource_role2` VALUES (904, 944, 1);
INSERT INTO `as_resource_role2` VALUES (905, 945, 1);
INSERT INTO `as_resource_role2` VALUES (906, 946, 1);
INSERT INTO `as_resource_role2` VALUES (907, 947, 1);
INSERT INTO `as_resource_role2` VALUES (908, 948, 1);
INSERT INTO `as_resource_role2` VALUES (909, 949, 1);
INSERT INTO `as_resource_role2` VALUES (910, 950, 1);
INSERT INTO `as_resource_role2` VALUES (911, 951, 1);
INSERT INTO `as_resource_role2` VALUES (912, 952, 1);
INSERT INTO `as_resource_role2` VALUES (913, 953, 1);
INSERT INTO `as_resource_role2` VALUES (914, 954, 1);
INSERT INTO `as_resource_role2` VALUES (915, 955, 1);
INSERT INTO `as_resource_role2` VALUES (916, 956, 1);
INSERT INTO `as_resource_role2` VALUES (917, 957, 1);
INSERT INTO `as_resource_role2` VALUES (918, 958, 1);
INSERT INTO `as_resource_role2` VALUES (919, 959, 1);
INSERT INTO `as_resource_role2` VALUES (920, 960, 1);
INSERT INTO `as_resource_role2` VALUES (921, 961, 1);
INSERT INTO `as_resource_role2` VALUES (922, 962, 1);
INSERT INTO `as_resource_role2` VALUES (923, 963, 1);
INSERT INTO `as_resource_role2` VALUES (924, 964, 1);
INSERT INTO `as_resource_role2` VALUES (925, 965, 1);
INSERT INTO `as_resource_role2` VALUES (926, 966, 1);
INSERT INTO `as_resource_role2` VALUES (927, 967, 1);
INSERT INTO `as_resource_role2` VALUES (928, 968, 1);
INSERT INTO `as_resource_role2` VALUES (929, 969, 1);
INSERT INTO `as_resource_role2` VALUES (930, 970, 1);
INSERT INTO `as_resource_role2` VALUES (931, 971, 1);
INSERT INTO `as_resource_role2` VALUES (932, 972, 1);
INSERT INTO `as_resource_role2` VALUES (933, 973, 1);
INSERT INTO `as_resource_role2` VALUES (934, 974, 1);
INSERT INTO `as_resource_role2` VALUES (935, 975, 1);
INSERT INTO `as_resource_role2` VALUES (936, 976, 1);
INSERT INTO `as_resource_role2` VALUES (937, 977, 1);
INSERT INTO `as_resource_role2` VALUES (938, 978, 1);
INSERT INTO `as_resource_role2` VALUES (939, 979, 1);
INSERT INTO `as_resource_role2` VALUES (940, 980, 1);
INSERT INTO `as_resource_role2` VALUES (941, 981, 1);
INSERT INTO `as_resource_role2` VALUES (942, 982, 1);
INSERT INTO `as_resource_role2` VALUES (943, 983, 1);
INSERT INTO `as_resource_role2` VALUES (944, 984, 1);
INSERT INTO `as_resource_role2` VALUES (945, 985, 1);
INSERT INTO `as_resource_role2` VALUES (946, 986, 1);
INSERT INTO `as_resource_role2` VALUES (947, 987, 1);
INSERT INTO `as_resource_role2` VALUES (948, 988, 1);
INSERT INTO `as_resource_role2` VALUES (949, 989, 1);
INSERT INTO `as_resource_role2` VALUES (950, 990, 1);
INSERT INTO `as_resource_role2` VALUES (951, 991, 1);
INSERT INTO `as_resource_role2` VALUES (952, 992, 1);
INSERT INTO `as_resource_role2` VALUES (953, 993, 1);
INSERT INTO `as_resource_role2` VALUES (954, 994, 1);
INSERT INTO `as_resource_role2` VALUES (955, 995, 1);
INSERT INTO `as_resource_role2` VALUES (956, 996, 1);
INSERT INTO `as_resource_role2` VALUES (957, 997, 1);
INSERT INTO `as_resource_role2` VALUES (958, 998, 1);
INSERT INTO `as_resource_role2` VALUES (959, 999, 1);
INSERT INTO `as_resource_role2` VALUES (960, 1000, 1);
INSERT INTO `as_resource_role2` VALUES (961, 1001, 1);
INSERT INTO `as_resource_role2` VALUES (962, 1002, 1);
INSERT INTO `as_resource_role2` VALUES (963, 1003, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_role
-- ----------------------------
INSERT INTO `as_role` VALUES (1, '超级管理员', 1, 'administrator', 0);
INSERT INTO `as_role` VALUES (2, '普通用户', 2, 'user', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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

-- ----------------------------
-- Table structure for as_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_scene`;
CREATE TABLE `as_scene`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `scene_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作场景名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(2) NULL DEFAULT 0,
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(2) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_scene
-- ----------------------------
INSERT INTO `as_scene` VALUES ('01ae7d34-aa0d-11e9-9d8b-005056c00001', '测试场景133', '', NULL, 0, '2019-07-19 18:07:25', 1);
INSERT INTO `as_scene` VALUES ('0bc633ef-aab2-11e9-80fb-005056c00001', 'test3的场景', '', NULL, 0, '2019-07-20 13:48:49', 1);
INSERT INTO `as_scene` VALUES ('0c370383-a6c9-11e9-9a6d-005056c00001', '测试场景4', '场景图片', '测试场景', 0, '2019-07-15 14:23:24', 1);
INSERT INTO `as_scene` VALUES ('0d1e3c54-aaad-11e9-89d6-005056c00001', '测试场景13312we', '', NULL, 0, '2019-07-20 13:13:04', 1);
INSERT INTO `as_scene` VALUES ('28e48620-a79d-11e9-a4b2-005056c00001', '测试场景3', '', NULL, 0, '2019-07-16 15:41:45', 1);
INSERT INTO `as_scene` VALUES ('31cc5795-aaa2-11e9-aeaf-005056c00001', '测试场景1150', '', NULL, 0, '2019-07-20 11:55:21', 1);
INSERT INTO `as_scene` VALUES ('4075767c-a79b-11e9-a6df-005056c00001', '测试场景2', '', NULL, 0, '2019-07-16 15:28:05', 1);
INSERT INTO `as_scene` VALUES ('582d71e6-a77b-11e9-88fa-005056c00001', '测试场景', NULL, NULL, 0, '2019-07-16 11:39:41', 1);
INSERT INTO `as_scene` VALUES ('5adb9ccf-96f0-11e9-867c-005056c00001', '工作场景6', NULL, NULL, 0, '2019-06-25 10:24:27', 1);
INSERT INTO `as_scene` VALUES ('5cb53ed0-96f0-11e9-867c-005056c00001', '工作场景2', NULL, NULL, 0, '2019-06-25 10:24:30', 1);
INSERT INTO `as_scene` VALUES ('5e43a701-96f0-11e9-867c-005056c00001', '工作场景3', NULL, NULL, 0, '2019-06-25 10:24:32', 1);
INSERT INTO `as_scene` VALUES ('5ffca1a2-96f0-11e9-867c-005056c00001', '工作场景4', NULL, NULL, 0, '2019-06-25 10:24:35', 1);
INSERT INTO `as_scene` VALUES ('6195b833-96f0-11e9-867c-005056c00001', '工作场景5', NULL, NULL, 0, '2019-06-25 10:24:38', 1);
INSERT INTO `as_scene` VALUES ('6393d224-96f0-11e9-867c-005056c00001', '工作场景6', NULL, NULL, 1, '2019-06-25 10:24:41', 0);
INSERT INTO `as_scene` VALUES ('63b9d993-aab4-11e9-95fb-005056c00001', 'test6的场景', '', NULL, 0, '2019-07-20 14:05:36', 1);
INSERT INTO `as_scene` VALUES ('6c42787ec2c534d6eb28d6424b87507a', '测试场景2011d', 'img', '这是测试场景', 0, '2019-07-12 11:22:48', 0);
INSERT INTO `as_scene` VALUES ('81d27c21-aaad-11e9-afaa-005056c00001', '测试场景115021', '', NULL, 0, '2019-07-20 13:16:20', 1);
INSERT INTO `as_scene` VALUES ('82e33b75-aa0c-11e9-89ff-005056c00001', '测试场景12', '', NULL, 0, '2019-07-19 18:03:52', 1);
INSERT INTO `as_scene` VALUES ('a1d7c397-a6c9-11e9-bb43-005056c00001', '测试场景1', '场景图片', '测试场景', 0, '2019-07-15 14:27:35', 1);
INSERT INTO `as_scene` VALUES ('daeb69f7-ac21-11e9-893f-005056c00001', 'test7的场景', '', NULL, 0, '2019-07-22 09:41:42', 1);
INSERT INTO `as_scene` VALUES ('e516ee69-aaad-11e9-9ea9-005056c00001', '测试', '', NULL, 0, '2019-07-20 13:19:06', 1);
INSERT INTO `as_scene` VALUES ('e65edc60-96ee-11e9-ac96-005056c00001', '工作场景1', NULL, NULL, 0, '2019-06-25 10:14:02', 1);
INSERT INTO `as_scene` VALUES ('f1907b8a-aaae-11e9-a974-005056c00001', '测试场景07201326', '', NULL, 0, '2019-07-20 13:26:37', 1);
INSERT INTO `as_scene` VALUES ('fa9ecdfe-aab3-11e9-93a9-005056c00001', 'test5的场景', '', NULL, 0, '2019-07-20 14:02:39', 1);
INSERT INTO `as_scene` VALUES ('fe358293-aaab-11e9-9589-005056c00001', '测试场景13312', '', NULL, 0, '2019-07-20 13:05:29', 1);

-- ----------------------------
-- Table structure for as_scene_member
-- ----------------------------
DROP TABLE IF EXISTS `as_scene_member`;
CREATE TABLE `as_scene_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) NOT NULL COMMENT 'organ_tree表的id',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人id',
  `principal` int(2) NOT NULL DEFAULT 0 COMMENT '是否是部门负责人',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人姓名',
  `is_deleted` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `add_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_scene_role
-- ----------------------------
DROP TABLE IF EXISTS `as_scene_role`;
CREATE TABLE `as_scene_role`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色代码',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称（英文），用于spring security安全校验',
  `role_name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称（中文）',
  `role_description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_scene_role
-- ----------------------------
INSERT INTO `as_scene_role` VALUES (1, 'ROLE_admin', '系统管理员', '这是总管理员的账号', 0, 1, NULL, NULL, '2019-05-27 20:57:41', NULL, NULL, '2019-05-27 20:57:46', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (2, 'ROLE_default', '所有用户', '默认角色', 1, 1, NULL, NULL, '2019-05-28 02:53:26', NULL, NULL, '2019-05-28 02:53:26', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (3, 'ROLE_test1', '业务人员', '测试角色', 0, 1, NULL, NULL, '2019-05-28 02:07:59', NULL, NULL, '2019-05-28 02:07:58', 1, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (4, 'ROLE_test2', '测试人员', '测试角色', 0, 1, NULL, NULL, '2019-05-28 02:08:24', NULL, NULL, '2019-05-28 02:08:23', 2, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (5, 'ROLE_test3', '调配人员', '测试角色', 0, 1, NULL, NULL, '2019-05-28 02:09:49', NULL, NULL, '2019-05-28 02:09:48', 2, 'e65edc60-96ee-11e9-ac96-005056c00001');
INSERT INTO `as_scene_role` VALUES (8, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 11:54:32', NULL, '31cc5795-aaa2-11e9-aeaf-005056c00001');
INSERT INTO `as_scene_role` VALUES (9, 'scene_admin', '组织管理员', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:05:29', NULL, 'fe358293-aaab-11e9-9589-005056c00001');
INSERT INTO `as_scene_role` VALUES (10, 'scene_default', '终端普通用户', NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:05:29', NULL, 'fe358293-aaab-11e9-9589-005056c00001');
INSERT INTO `as_scene_role` VALUES (11, 'scene_admin', '组织管理员', NULL, 0, 1, NULL, NULL, '2019-07-20 13:13:04', NULL, NULL, '2019-07-20 13:13:04', 9, '0d1e3c54-aaad-11e9-89d6-005056c00001');
INSERT INTO `as_scene_role` VALUES (12, 'scene_default', '终端普通用户', NULL, 1, 1, NULL, NULL, '2019-07-20 13:13:04', NULL, NULL, '2019-07-20 13:13:04', 9, '0d1e3c54-aaad-11e9-89d6-005056c00001');
INSERT INTO `as_scene_role` VALUES (13, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-20 13:16:20', NULL, NULL, '2019-07-20 13:16:20', 10, '81d27c21-aaad-11e9-afaa-005056c00001');
INSERT INTO `as_scene_role` VALUES (14, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-20 13:16:20', NULL, NULL, '2019-07-20 13:16:20', 10, '81d27c21-aaad-11e9-afaa-005056c00001');
INSERT INTO `as_scene_role` VALUES (15, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-20 13:19:06', NULL, NULL, '2019-07-20 13:19:06', 11, 'e516ee69-aaad-11e9-9ea9-005056c00001');
INSERT INTO `as_scene_role` VALUES (16, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-20 13:19:06', NULL, NULL, '2019-07-20 13:19:06', 11, 'e516ee69-aaad-11e9-9ea9-005056c00001');
INSERT INTO `as_scene_role` VALUES (17, 'scene_admin', '组织管理员', NULL, 0, 1, NULL, NULL, '2019-07-20 13:26:37', NULL, NULL, '2019-07-20 13:26:37', 12, 'f1907b8a-aaae-11e9-a974-005056c00001');
INSERT INTO `as_scene_role` VALUES (18, 'scene_default', '终端普通用户', NULL, 1, 1, NULL, NULL, '2019-07-20 13:26:37', NULL, NULL, '2019-07-20 13:26:37', 12, 'f1907b8a-aaae-11e9-a974-005056c00001');
INSERT INTO `as_scene_role` VALUES (19, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-20 13:48:49', NULL, NULL, '2019-07-20 13:48:49', 13, '0bc633ef-aab2-11e9-80fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (20, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-20 13:48:49', NULL, NULL, '2019-07-20 13:48:49', 13, '0bc633ef-aab2-11e9-80fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (21, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-20 14:02:39', NULL, NULL, '2019-07-20 14:02:39', 14, 'fa9ecdfe-aab3-11e9-93a9-005056c00001');
INSERT INTO `as_scene_role` VALUES (22, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-20 14:02:39', NULL, NULL, '2019-07-20 14:02:39', 14, 'fa9ecdfe-aab3-11e9-93a9-005056c00001');
INSERT INTO `as_scene_role` VALUES (23, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-20 14:05:36', NULL, NULL, '2019-07-20 14:05:36', 15, '63b9d993-aab4-11e9-95fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (24, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-20 14:05:36', NULL, NULL, '2019-07-20 14:05:36', 15, '63b9d993-aab4-11e9-95fb-005056c00001');
INSERT INTO `as_scene_role` VALUES (25, 'scene_admin', '组织管理员', NULL, 0, 0, NULL, NULL, '2019-07-22 09:41:42', NULL, NULL, '2019-07-22 09:41:42', 16, 'daeb69f7-ac21-11e9-893f-005056c00001');
INSERT INTO `as_scene_role` VALUES (26, 'scene_default', '终端普通用户', NULL, 1, 0, NULL, NULL, '2019-07-22 09:41:42', NULL, NULL, '2019-07-22 09:41:42', 16, 'daeb69f7-ac21-11e9-893f-005056c00001');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '用户的平台角色id',
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
  `admin` int(1) NOT NULL COMMENT '是否是超级管理员',
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_user
-- ----------------------------
INSERT INTO `as_user` VALUES ('0bb9b0ce-aab2-11e9-80fb-005056c00001', '', NULL, '测试账号', 'test3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$7p0ECJbdQBNYrAwUcPISAONdKqP7bJOsbiBIKenEF/KiJGydTlJ8G', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:48:49');
INSERT INTO `as_user` VALUES ('0c2aa772-a6c9-11e9-9a6d-005056c00001', '24993b54-a6c9-11e9-9a6d-005056c00001', NULL, '张三', 'admin2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$DAhhdj3CWNaxTW1O74YZj.Za9L2nGbnBI57DOGfCcHsKFxSILdCeO', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:23:23');
INSERT INTO `as_user` VALUES ('1', '1', 1, '系统管理员', 'admin', '系统管理员', 1, '2019-05-27 15:36:20', '123456@admin.com', '13112341234', 1, '330312123412341234', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 15:36:50');
INSERT INTO `as_user` VALUES ('4', NULL, NULL, 'hhj', 'hhj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$diZVrY./3ywZAehLq7VDkOqGmh7lCfAUYVp.6rX43bcQ.LwCOqclK', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:41:27');
INSERT INTO `as_user` VALUES ('5', NULL, NULL, 'yby', 'yby', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$mR.wbAklwKsKjeJ27NVb1OlQfH/4iPcIxq34x9ps0USPX1H3R.UGe', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:43:46');
INSERT INTO `as_user` VALUES ('6', NULL, NULL, 'pqq', 'pqq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UgIXCnxuYgwmxr72Ma94yeO3YZFfRFvmBfmQN6wPtXme4rCh9rf.O', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:45:24');
INSERT INTO `as_user` VALUES ('63a910b2-aab4-11e9-95fb-005056c00001', '', NULL, '测试账号4', 'test6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$ME8yc1rwfksB.LTamsbJy.6ckjNz2M8n1986O26WG4XIfa7CnrNpC', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 14:05:35');
INSERT INTO `as_user` VALUES ('7', NULL, NULL, 'test', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$/TV4/D0vaZ/DMHcnYMQgLe1sESduVs3WD671C807E8WW0q1WhO4Wa', NULL, NULL, 0, '浙江省杭州市江干区', NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-29 12:08:34');
INSERT INTO `as_user` VALUES ('977e0670-aab2-11e9-80fb-005056c00001', '', NULL, '测试账号4', 'test4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$otvMRCoZDyVl2kSecQvWfOcwO.nT27O6VnO7lpiQmyL8p99R0ois6', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:52:43');
INSERT INTO `as_user` VALUES ('a13c05a3-9bd7-11e9-a6d5-005056c00001', '3c5608ed-9be8-11e9-8731-005056c00001', NULL, '陈旭', 'chenxu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$4Mhl3A0UmxEVHXWdcxy3suTdKqb7Gof.f95kEVWgD7OuHF1A/WKOG', NULL, NULL, 1, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-01 16:10:04');
INSERT INTO `as_user` VALUES ('a1cb1966-a6c9-11e9-bb43-005056c00001', '', NULL, '张三', 'admin3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$9GlSBTTMPwTVD4bq5KNsZeByfsiCfNDkJy6ks/5SmUIgs4I/cQHDC', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:27:34');
INSERT INTO `as_user` VALUES ('b3b89840-a6c8-11e9-9a6d-005056c00001', 'f41975d1-a6c8-11e9-9a6d-005056c00001', NULL, '张三', 'admin1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$bMoKYhKghsavRSFj10dJZOR5kFy1Fp5HQXaDQuBRvFxTDwnyk8YWK', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-15 14:20:55');
INSERT INTO `as_user` VALUES ('dadf34f6-ac21-11e9-893f-005056c00001', '', 2, '测试账号7', 'test7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UBTqyrADA3QDz2IeCNcYC.PuvzrUhKcAN5CqC0jhId2mM/EYa3Ma6', NULL, NULL, 0, NULL, NULL, NULL, 2, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-22 09:41:42');
INSERT INTO `as_user` VALUES ('e50a6b48-aaad-11e9-9ea9-005056c00001', '', NULL, '测试账号', 'test1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$VH13GCSR6yGtK8xN5AVrte0JxYuFeYorwYUT1GDyYCrsEukhlu/GC', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:19:06');
INSERT INTO `as_user` VALUES ('fa8eef7d-aab3-11e9-93a9-005056c00001', '', NULL, '测试账号4', 'test5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$iVCleetTjjRBlqPsexyLiOL96NWUvi8UGOQc.tWW8yU6YDP1idpdq', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 14:02:39');
INSERT INTO `as_user` VALUES ('fd2e92bb-aaae-11e9-a974-005056c00001', '', NULL, '测试账号', 'test2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$vV2C11./QS75T.eZvVp26e0cu1dZdUGdlE8VcAahvVsAKln6GEtpC', NULL, NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-20 13:26:56');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_user_role
-- ----------------------------
INSERT INTO `as_user_role` VALUES (1, '1', NULL, 1, 1);
INSERT INTO `as_user_role` VALUES (2, 'dadf34f6-ac21-11e9-893f-005056c00001', '2019-07-22 09:45:20', 2, 1);

-- ----------------------------
-- Table structure for as_user_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_user_scene`;
CREATE TABLE `as_user_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '场景编号',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色编号',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1152212633756119052 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_user_scene
-- ----------------------------
INSERT INTO `as_user_scene` VALUES (1, 'e65edc60-96ee-11e9-ac96-005056c00001', '1', 1, 1);
INSERT INTO `as_user_scene` VALUES (2, '5cb53ed0-96f0-11e9-867c-005056c00001', '1', 1, 1);
INSERT INTO `as_user_scene` VALUES (13, 'e65edc60-96ee-11e9-ac96-005056c00001', 'b3b89840-a6c8-11e9-9a6d-005056c00001', 15, 1);
INSERT INTO `as_user_scene` VALUES (14, '0c370383-a6c9-11e9-9a6d-005056c00001', '0c2aa772-a6c9-11e9-9a6d-005056c00001', 16, 1);
INSERT INTO `as_user_scene` VALUES (15, 'a1d7c397-a6c9-11e9-bb43-005056c00001', 'a1cb1966-a6c9-11e9-bb43-005056c00001', 17, 1);
INSERT INTO `as_user_scene` VALUES (16, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 2, 1);
INSERT INTO `as_user_scene` VALUES (17, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 2, 1);
INSERT INTO `as_user_scene` VALUES (18, 'e65edc60-96ee-11e9-ac96-005056c00001', '21321321313', 2, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119043, '31cc5795-aaa2-11e9-aeaf-005056c00001', '31bfad64-aaa2-11e9-aeaf-005056c00001', 8, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119044, '81d27c21-aaad-11e9-afaa-005056c00001', '81c5f900-aaad-11e9-afaa-005056c00001', 13, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119045, 'e516ee69-aaad-11e9-9ea9-005056c00001', 'e50a6b48-aaad-11e9-9ea9-005056c00001', 15, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119046, 'f1907b8a-aaae-11e9-a974-005056c00001', 'fd2e92bb-aaae-11e9-a974-005056c00001', 18, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119047, '0bc633ef-aab2-11e9-80fb-005056c00001', '0bb9b0ce-aab2-11e9-80fb-005056c00001', 19, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119048, 'e65edc60-96ee-11e9-ac96-005056c00001', '977e0670-aab2-11e9-80fb-005056c00001', 2, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119049, 'fa9ecdfe-aab3-11e9-93a9-005056c00001', 'fa8eef7d-aab3-11e9-93a9-005056c00001', 21, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119050, '63b9d993-aab4-11e9-95fb-005056c00001', '63a910b2-aab4-11e9-95fb-005056c00001', 23, 1);
INSERT INTO `as_user_scene` VALUES (1152212633756119051, 'daeb69f7-ac21-11e9-893f-005056c00001', 'dadf34f6-ac21-11e9-893f-005056c00001', 25, 1);

SET FOREIGN_KEY_CHECKS = 1;
