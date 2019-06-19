/*
 Navicat Premium Data Transfer

 Source Server         : 10.1.18.123
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 10.1.18.123:3306
 Source Schema         : asset-platform-idm

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 18/06/2019 15:44:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for as_app_template
-- ----------------------------
DROP TABLE IF EXISTS `as_app_template`;
CREATE TABLE `as_app_template`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用模板id',
  `application_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `publish_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_app_template
-- ----------------------------
INSERT INTO `as_app_template` VALUES ('917290e1-873d-11e9-88ac-005056c00001', '1', 1, '2019-06-05 10:56:51', NULL, 'admin');
INSERT INTO `as_app_template` VALUES ('eac01fa3-874a-11e9-8f24-005056c00001', '2', 1, '2019-06-05 12:32:25', NULL, 'admin');

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
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '失效时间',
  `remove_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(2) NULL DEFAULT NULL,
  `sort` int(2) NULL DEFAULT NULL,
  `category` int(2) NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` int(2) NULL DEFAULT NULL,
  `add_time` datetime NULL DEFAULT NULL,
  `remove_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_menu
-- ----------------------------
INSERT INTO `as_menu` VALUES (77, 0, 'app', '采购管理', '2', '6fc74e52-8e6e-11e9-b311-005056c00001', 0, 0, 1, NULL, 0, '2019-06-14 14:34:37', NULL);
INSERT INTO `as_menu` VALUES (78, 77, 'form', '测试表单', 'icon-cgfk', '1', 0, 0, 2, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (79, 78, 'func', '新增', '', '1/list/add', 1, 1, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (80, 78, 'func', '导入', '', '1/list/import', 1, 2, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (81, 78, 'func', '导出', '', '1/list/export', 1, 3, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (82, 78, 'func', '删除', '', '1/list/delete', 1, 4, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (83, 78, 'func', '暂存', '', '1/form/add_temp', 2, 5, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (84, 78, 'func', '提交', '', '1/form/save', 2, 6, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (85, 78, 'func', '提交并添加', '', '1/form/save_add', 2, 7, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (86, 78, 'func', '打印二维码', '', '1/list/printQR', 2, 8, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (87, 78, 'func', '打印', '', '1/form/print', 3, 9, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (88, 78, 'func', '删除', '', '1/form/delete', 3, 10, 3, NULL, 0, '2019-06-14 14:35:14', NULL);
INSERT INTO `as_menu` VALUES (89, 0, 'app', '测试应用', '2', '60b16393-8e70-11e9-b311-005056c00001', 0, 0, 1, NULL, 0, '2019-06-14 14:48:34', NULL);
INSERT INTO `as_menu` VALUES (90, 89, 'form', '测试表单2', 'icon-cgfk', '1', 0, 0, 2, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (91, 90, 'func', '新增', '', '11/list/add', 1, 1, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (92, 90, 'func', '导入', '', '11/list/import', 1, 2, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (93, 90, 'func', '导出', '', '11/list/export', 1, 3, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (94, 90, 'func', '删除', '', '11/list/delete', 1, 4, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (95, 90, 'func', '暂存', '', '11/form/add_temp', 2, 5, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (96, 90, 'func', '提交', '', '11/form/save', 2, 6, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (97, 90, 'func', '提交并添加', '', '11/form/save_add', 2, 7, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (98, 90, 'func', '打印二维码', '', '11/list/printQR', 2, 8, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (99, 90, 'func', '打印', '', '11/form/print', 3, 9, 3, NULL, 0, '2019-06-14 14:48:44', NULL);
INSERT INTO `as_menu` VALUES (100, 90, 'func', '删除', '', '11/form/delete', 3, 10, 3, NULL, 0, '2019-06-14 14:48:44', NULL);

-- ----------------------------
-- Table structure for as_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `as_menu_role`;
CREATE TABLE `as_menu_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of as_menu_role
-- ----------------------------
INSERT INTO `as_menu_role` VALUES (37, 77, 1);
INSERT INTO `as_menu_role` VALUES (38, 78, 1);
INSERT INTO `as_menu_role` VALUES (39, 79, 1);
INSERT INTO `as_menu_role` VALUES (40, 80, 1);
INSERT INTO `as_menu_role` VALUES (41, 81, 1);
INSERT INTO `as_menu_role` VALUES (42, 82, 1);
INSERT INTO `as_menu_role` VALUES (43, 83, 1);
INSERT INTO `as_menu_role` VALUES (44, 84, 1);
INSERT INTO `as_menu_role` VALUES (45, 85, 1);
INSERT INTO `as_menu_role` VALUES (46, 86, 1);
INSERT INTO `as_menu_role` VALUES (47, 87, 1);
INSERT INTO `as_menu_role` VALUES (48, 88, 1);
INSERT INTO `as_menu_role` VALUES (49, 89, 1);
INSERT INTO `as_menu_role` VALUES (50, 90, 1);
INSERT INTO `as_menu_role` VALUES (51, 91, 1);
INSERT INTO `as_menu_role` VALUES (52, 92, 1);
INSERT INTO `as_menu_role` VALUES (53, 93, 1);
INSERT INTO `as_menu_role` VALUES (54, 94, 1);
INSERT INTO `as_menu_role` VALUES (55, 95, 1);
INSERT INTO `as_menu_role` VALUES (56, 96, 1);
INSERT INTO `as_menu_role` VALUES (57, 97, 1);
INSERT INTO `as_menu_role` VALUES (58, 98, 1);
INSERT INTO `as_menu_role` VALUES (59, 99, 1);
INSERT INTO `as_menu_role` VALUES (60, 100, 1);

-- ----------------------------
-- Table structure for as_module
-- ----------------------------
DROP TABLE IF EXISTS `as_module`;
CREATE TABLE `as_module`  (
  `id` int(11) NOT NULL,
  `module_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块图标',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：1、可用；0、不可用',
  `application_id` int(11) NULL DEFAULT NULL COMMENT '应用id',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '失效时间',
  `remove_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_organ_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_organ_scene`;
CREATE TABLE `as_organ_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `scene_id` bigint(20) NOT NULL COMMENT '工作场景id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_organ_tree
-- ----------------------------
DROP TABLE IF EXISTS `as_organ_tree`;
CREATE TABLE `as_organ_tree`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `unit_name_en` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门英文名称',
  `parent_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级单位id',
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
  `is_financial_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否财政单位',
  `is_vertical_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否垂管单位',
  `is_virtual_unit` tinyint(1) NULL DEFAULT NULL COMMENT '是否虚拟单位',
  `accounting_system` int(2) NULL DEFAULT NULL COMMENT '单位执行会计制度:\r\n10.行政单位会计制度 \r\n20.事业单位会计制度 \r\n21.科学事业单位会计制度 \r\n22.中小学校会计制度 \r\n23.高等学校会计制度 \r\n24.医院会计制度 \r\n25.基层医疗卫生机构会计制度 \r\n26.测绘事业单位会计制度 \r\n27.地质勘查单位会计制度 \r\n28.彩票机构会计制度 \r\n30.民间非营利组织会计制度 \r\n50.其他',
  `corporate_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业标志',
  `maintainer_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修商标志',
  `supplier_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商标志',
  `manufacture_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造商标志',
  `asset_disposal_agency_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产处置代理机构标志',
  `state_asset_management_company` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国有资产经营公司标志',
  `authorized_number` int(11) NULL DEFAULT NULL COMMENT '编制人数',
  `administration_authority` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政编制',
  `enable_time` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `seal_time` datetime NULL DEFAULT NULL COMMENT '封存时间',
  `unit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `asset_download_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产数据下载状态',
  `latitude_and_longitude` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GPS经纬度',
  `created_time` datetime NULL DEFAULT NULL COMMENT '插入时间',
  `is_deleted` int(2) NULL DEFAULT NULL,
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1：有效 0：无效',
  `sort` int(2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '排序',
  `tenant_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_organ_tree
-- ----------------------------
INSERT INTO `as_organ_tree` VALUES (1, '浙江省政府办公厅', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-05 18:55:23', NULL, 1, NULL, NULL);
INSERT INTO `as_organ_tree` VALUES (2, '浙江省商务厅', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-05 18:58:07', NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for as_resource
-- ----------------------------
DROP TABLE IF EXISTS `as_resource`;
CREATE TABLE `as_resource`  (
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
  CONSTRAINT `as_resource_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `as_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_resource
-- ----------------------------
INSERT INTO `as_resource` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `as_resource` VALUES (2, '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource` VALUES (3, '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource` VALUES (4, '/', '/home', 'Home', '薪资管理', 'fa fa-money', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource` VALUES (5, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource` VALUES (6, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 1, 0, NULL);
INSERT INTO `as_resource` VALUES (7, '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', NULL, NULL, 1, 2, 1, NULL);
INSERT INTO `as_resource` VALUES (8, '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', NULL, NULL, 1, 2, 0, NULL);
INSERT INTO `as_resource` VALUES (9, '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', NULL, NULL, 1, 3, 0, NULL);
INSERT INTO `as_resource` VALUES (10, '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource` VALUES (11, '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource` VALUES (12, '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource` VALUES (13, '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', NULL, NULL, 1, 3, 1, NULL);
INSERT INTO `as_resource` VALUES (14, '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource` VALUES (15, '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource` VALUES (16, '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource` VALUES (17, '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource` VALUES (18, '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', NULL, NULL, 1, 4, 1, NULL);
INSERT INTO `as_resource` VALUES (19, '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource` VALUES (20, '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource` VALUES (21, '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource` VALUES (22, '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', NULL, NULL, 1, 5, 1, NULL);
INSERT INTO `as_resource` VALUES (23, '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (24, '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (25, '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (26, '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (27, '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (28, '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', NULL, NULL, 1, 6, 1, NULL);
INSERT INTO `as_resource` VALUES (29, '/', '/application', 'Home', '应用管理', 'fa fa-user-circle-o', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource` VALUES (30, '/', '/organization', 'Home', '组织架构', 'fa fa-address-card-o', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource` VALUES (31, '/', '/authority', 'Home', '权限管理', 'fa fa-money', NULL, 1, 1, 1, NULL);
INSERT INTO `as_resource` VALUES (32, '/', '/companyInfo', 'Home', '企业信息', 'fa fa-bar-chart', NULL, 1, 1, 1, NULL);

-- ----------------------------
-- Table structure for as_resource_role
-- ----------------------------
DROP TABLE IF EXISTS `as_resource_role`;
CREATE TABLE `as_resource_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `as_resource_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `as_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `as_resource_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 362 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_resource_role
-- ----------------------------
INSERT INTO `as_resource_role` VALUES (161, 7, 3);
INSERT INTO `as_resource_role` VALUES (162, 7, 6);
INSERT INTO `as_resource_role` VALUES (163, 9, 6);
INSERT INTO `as_resource_role` VALUES (164, 10, 6);
INSERT INTO `as_resource_role` VALUES (165, 11, 6);
INSERT INTO `as_resource_role` VALUES (166, 12, 6);
INSERT INTO `as_resource_role` VALUES (167, 13, 6);
INSERT INTO `as_resource_role` VALUES (168, 14, 6);
INSERT INTO `as_resource_role` VALUES (169, 15, 6);
INSERT INTO `as_resource_role` VALUES (170, 16, 6);
INSERT INTO `as_resource_role` VALUES (171, 17, 6);
INSERT INTO `as_resource_role` VALUES (172, 18, 6);
INSERT INTO `as_resource_role` VALUES (173, 19, 6);
INSERT INTO `as_resource_role` VALUES (174, 20, 6);
INSERT INTO `as_resource_role` VALUES (175, 21, 6);
INSERT INTO `as_resource_role` VALUES (176, 22, 6);
INSERT INTO `as_resource_role` VALUES (177, 23, 6);
INSERT INTO `as_resource_role` VALUES (178, 25, 6);
INSERT INTO `as_resource_role` VALUES (179, 26, 6);
INSERT INTO `as_resource_role` VALUES (180, 27, 6);
INSERT INTO `as_resource_role` VALUES (181, 28, 6);
INSERT INTO `as_resource_role` VALUES (182, 24, 6);
INSERT INTO `as_resource_role` VALUES (317, 29, 1);
INSERT INTO `as_resource_role` VALUES (318, 30, 1);
INSERT INTO `as_resource_role` VALUES (319, 31, 1);
INSERT INTO `as_resource_role` VALUES (320, 32, 1);
INSERT INTO `as_resource_role` VALUES (337, 29, 2);
INSERT INTO `as_resource_role` VALUES (338, 30, 2);
INSERT INTO `as_resource_role` VALUES (339, 31, 2);
INSERT INTO `as_resource_role` VALUES (340, 32, 2);
INSERT INTO `as_resource_role` VALUES (342, 7, 4);
INSERT INTO `as_resource_role` VALUES (343, 10, 4);
INSERT INTO `as_resource_role` VALUES (344, 11, 4);
INSERT INTO `as_resource_role` VALUES (345, 12, 4);
INSERT INTO `as_resource_role` VALUES (346, 13, 4);
INSERT INTO `as_resource_role` VALUES (347, 14, 4);
INSERT INTO `as_resource_role` VALUES (348, 15, 4);
INSERT INTO `as_resource_role` VALUES (349, 16, 4);
INSERT INTO `as_resource_role` VALUES (350, 17, 4);
INSERT INTO `as_resource_role` VALUES (351, 18, 4);
INSERT INTO `as_resource_role` VALUES (352, 19, 4);
INSERT INTO `as_resource_role` VALUES (353, 20, 4);
INSERT INTO `as_resource_role` VALUES (354, 21, 4);
INSERT INTO `as_resource_role` VALUES (355, 22, 4);
INSERT INTO `as_resource_role` VALUES (356, 23, 4);
INSERT INTO `as_resource_role` VALUES (357, 24, 4);
INSERT INTO `as_resource_role` VALUES (358, 25, 4);
INSERT INTO `as_resource_role` VALUES (359, 26, 4);
INSERT INTO `as_resource_role` VALUES (360, 27, 4);
INSERT INTO `as_resource_role` VALUES (361, 28, 4);

-- ----------------------------
-- Table structure for as_role
-- ----------------------------
DROP TABLE IF EXISTS `as_role`;
CREATE TABLE `as_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色代码',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称（英文），用于spring security安全校验',
  `role_name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称（中文）',
  `role_description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(1) NOT NULL COMMENT '状态：\r\n1、新建 \r\n2、启用 \r\n3、停用',
  `applicable_unit_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限发布出去后，哪些单位可以使用。如适用于行政事业单位、主管部门、财政、国有企业等不同单位类型。',
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `enable_time` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `created_time` datetime NOT NULL COMMENT '插入时间',
  `group_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_role
-- ----------------------------
INSERT INTO `as_role` VALUES (1, 'ROLE_admin', '系统管理员', '这是总管理员的账号', 1, NULL, NULL, '2019-05-27 20:57:41', NULL, NULL, '2019-05-27 20:57:46', 1);
INSERT INTO `as_role` VALUES (2, 'ROLE_default', '所有用户', '默认角色', 1, NULL, NULL, '2019-05-28 02:53:26', NULL, NULL, '2019-05-28 02:53:26', 1);
INSERT INTO `as_role` VALUES (3, 'ROLE_test1', '业务人员', '测试角色', 1, NULL, NULL, '2019-05-28 02:07:59', NULL, NULL, '2019-05-28 02:07:58', 1);
INSERT INTO `as_role` VALUES (4, 'ROLE_test2', '测试人员', '测试角色', 1, NULL, NULL, '2019-05-28 02:08:24', NULL, NULL, '2019-05-28 02:08:23', 2);
INSERT INTO `as_role` VALUES (5, 'ROLE_test3', '调配人员', '测试角色', 1, NULL, NULL, '2019-05-28 02:09:49', NULL, NULL, '2019-05-28 02:09:48', 2);

-- ----------------------------
-- Table structure for as_role_group
-- ----------------------------
DROP TABLE IF EXISTS `as_role_group`;
CREATE TABLE `as_role_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_deleted` tinyint(2) NULL DEFAULT NULL,
  `add_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_role_group
-- ----------------------------
INSERT INTO `as_role_group` VALUES (1, '默认分组', 0, '2019-06-17 09:42:25');
INSERT INTO `as_role_group` VALUES (2, '系统角色', 0, '2019-06-17 09:44:34');
INSERT INTO `as_role_group` VALUES (3, '开发组', 1, '2019-06-17 10:20:02');
INSERT INTO `as_role_group` VALUES (4, '开发组', 1, '2019-06-17 10:33:18');
INSERT INTO `as_role_group` VALUES (5, '开发组', 0, '2019-06-17 10:34:41');

-- ----------------------------
-- Table structure for as_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_scene`;
CREATE TABLE `as_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '工作场景名称',
  `is_deleted` tinyint(2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `add_time` datetime NULL DEFAULT NULL,
  `status` tinyint(2) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_staff
-- ----------------------------
DROP TABLE IF EXISTS `as_staff`;
CREATE TABLE `as_staff`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代码',
  `graduate_time` datetime NULL DEFAULT NULL COMMENT '毕业时间',
  `graduate_school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学校',
  `authorized_strength` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制情况',
  `staff_birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `party_group` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '党派',
  `fixed_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `overseas_relation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '海外关系',
  `native_place` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `emergency_contact` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急情况联系人',
  `contact_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `staff_nation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `staff_hiredate` datetime NULL DEFAULT NULL COMMENT '入职时间',
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
INSERT INTO `as_staff` VALUES ('f4e1368a-8b2d-11e9-9815-005056c00001', '5fa7c0a0-8b29-11e9-aa21-005056c00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '测试官', '1', NULL, NULL, NULL, 'null', NULL, NULL, '1', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for as_unit
-- ----------------------------
DROP TABLE IF EXISTS `as_unit`;
CREATE TABLE `as_unit`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `unit_name_en` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门英文名称',
  `parent_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级单位id',
  `organization_form` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织形式：\r\n10、公司制企业（11.国有独资企业 12.其他有限责任公司 13.上市股份有限公司 股票代码 14。非上市股份有限公司）20.公司制企业（21.非公司制独资企业 22.其他非公司制企业） 30.企业化管理事业单位 40。其他',
  `unit_type` tinyint(3) NULL DEFAULT NULL COMMENT '单位分类\r\n1、主管部门 \r\n2、二级单位 \r\n3、基层单位 \r\n\r\n100、政府财政事务 \r\n200、安全管理事务 \r\n300、',
  `parent_node_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级单位名称',
  `charge_section_id` bigint(20) NULL DEFAULT NULL COMMENT '主管部门id',
  `charge_section_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管部门名称',
  `local_financial_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级财政代码',
  `local_financial_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级财政名称',
  `office_administration_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级机关事务管理局代码',
  `office_administration_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本级机关事务管理局名称',
  `administration_division_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政区划代码',
  `administration_division_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政区划名称',
  `budget_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财政预算代码：\r\n编码方法：采用层次码，用数字表示,代码结构为每3位一层。一级单位3位码,二级单位6位码,……,七级单位21位码。其中，预算单位本级由该预算单位的单位层次码加上001表示。中央一级预算单位统一的3位代码表见表5。 \r\n应用范围：中央使用,地方参照。 \r\n\r\n代码	名称 \r\n101	国务院办公厅 \r\n102	国家发展和改革委员会 \r\n105	教育部 \r\n106	科学技术部 \r\n107	国家国防科技工业局 \r\n108	国家民族事务委员会 \r\n109	国家体育总局 \r\n110	国家人口和计划生育委员会 \r\n111	公安部 \r\n112	国家安全部 \r\n113	司法部 \r\n114	外交部 \r\n115	监察部 \r\n117	人力资源和社会保障部 \r\n118	民政部 \r\n119	财政部 \r\n120	住房和城乡建设部 \r\n121	国土资源部 \r\n122	铁道部 \r\n123	交通运输部 \r\n124	工业和信息化部 \r\n125	农业部 \r\n126	水利部 \r\n127	全国社会保障基金理事会',
  `college_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高等院校代码\r\n院校代号即院校代码或学校代码，为全国各高校录取时为方便考生填报志愿而加注的由数字组成的代号串。院校代码就如同是学校的一个身份证号，方便查询学校信息，教育部为高校编排的代码有5位（此代码全国通用），各省教育考试院为高校编排代码有4位（此代码一般作填报高考志愿用，同一所高校在不同省份代码也不一样），由于高校办学情况每年都有变动，所以高校代码也有变化。 \r\n\r\n10001 北京大学 北京医科大学并入 ',
  `organization_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码\r\n组织机构代码按照强制性国家标准GB11714《全国组织机构代码编制规则》编制，由八位数字(或大写拉丁字母)本体代码和一位数字(或大写拉丁字母)校验码组成。组织机构代码证组织机构代码证书包括正本、副本和电子副本(IC卡)，代码登记部门在为组织机构赋码发证的同时，还要采集28项基础信息，并按照国家标准对这些信息进行编码，将这些信息存入代码数据库和代码证电子副本(IC卡)中，供代码应用部门使用。代码登记部门所采集的基础信息包括:机构名称、机构地址、机构类型、经济性质、行业分类、规模、法人代表等。',
  `unit_type_code` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位类型名称\r\n代码	名称	说明 \r\n1	行政	指依宪法和有关组织法的规定设置的，行使国家行政职权，负责对国家各项行政事务进行组织、管理、监督和指挥的国家机关。这里包括行政、立法、司法、军队、党务等按公务员法管理的单位。 \r\n2	事业	为了社会公益目的，从事教育、文化、卫生、科技等活动并以非盈利性为主的社会服务组织。 \r\n3	参公	参照公务员法管理的单位。 \r\n4、 企业 企业单位 \r\n9	其他	除上述行政、事业外的其他单位。',
  `unit_type_name` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位类型名称\r\n代码	名称	说明 \r\n1	行政	指依宪法和有关组织法的规定设置的，行使国家行政职权，负责对国家各项行政事务进行组织、管理、监督和指挥的国家机关。这里包括行政、立法、司法、军队、党务等按公务员法管理的单位。 \r\n2	事业	为了社会公益目的，从事教育、文化、卫生、科技等活动并以非盈利性为主的社会服务组织。 \r\n3	参公	参照公务员法管理的单位。 \r\n4、 企业 企业单位 \r\n9	其他	除上述行政、事业外的其他单位。',
  `unit_basic_property` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位基本性质',
  `social_credit_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `budget_unit_name` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算单位级次名称\r\n代码	名称 \r\n1	一级 \r\n2	二级 \r\n3	三级 \r\n4	四级 \r\n5	五级 \r\n6	六级 \r\n7	七级',
  `budget_unit_code` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算单位级次代码',
  `budget_management_level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算管理级次\r\n1、中央级 \r\n2、省级 \r\n3、地（市）级 \r\n4、县级 \r\n5、乡镇级',
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
  `fund_supply_mode_name` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经费供给方式名称：\r\n代码	名称	说明 \r\n1	全额	指由财政供应全部经费的预算单位。 \r\n2	差额	指财政按单位收支差额供应经费的预算单位。 \r\n3	自收自支	不靠财政供应经费的预算单位。 \r\n9	其他	指财政按其他方式供应经费的预算单位。',
  `fund_supply_mode_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经费供给方式代码：\r\n代码	名称	说明 \r\n1	全额	指由财政供应全部经费的预算单位。 \r\n2	差额	指财政按单位收支差额供应经费的预算单位。 \r\n3	自收自支	不靠财政供应经费的预算单位。 \r\n9	其他	指财政按其他方式供应经费的预算单位。',
  `administration_level_name` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位行政级别名称：\r\n代码	名称 \r\n1	正部（省）级 \r\n2	副部（省）级 \r\n3	正厅（地）级 \r\n4	副厅（地）级 \r\n5	正处（县）级 \r\n6	副处（县）级 \r\n7	正科级 \r\n8	副科级 \r\n9	股级',
  `administration_level_type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政单位部门性质分类代码',
  `department_category_name` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事业单位部门性质分类名称',
  `business_department_classification_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事业单位部门性质分类代码：\r\n代码	名称 \r\n1	行政单位 \r\n11	共产党机关 \r\n12	政府机关 \r\n13	人大机关 \r\n14	政协机关 \r\n15	民主党派机关 \r\n16	群众团体 \r\n2	公检法司 \r\n21	公安 \r\n22	检察 \r\n23	法院 \r\n24	司法行政 \r\n25	监狱 \r\n26	劳教 \r\n29	其他 \r\n3	驻外机构 \r\n\r\n代码	名称 \r\n1	农、林、牧、渔业 \r\n2	交通运输、仓储和邮政业 \r\n3	科学研究、技术服务和地质勘查业 \r\n4	水利、环境和公共设施管理业 \r\n5	教育 \r\n6	卫生、社会保障和社会福利业 \r\n7	文化、体育和娱乐业 \r\n9	其他',
  `principal` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `corporate_representative` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `economic_type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经济类型：\r\n10、国有及国有控股（是否中外合资合作企业 11.是 12，否） \r\n20、厂办大集体（21 中央企厂办大集体 22、中央下放企业厂办大集体；23、地方企业厂办大集体） 30.其他城镇计提',
  `financial_affiliation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务隶属关系（行政隶属关系代码-部门标识代码）',
  `is_financial_unit` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否财政单位',
  `is_vertical_unit` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否垂管单位',
  `is_virtual_unit` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否虚拟单位',
  `accounting_system` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位执行会计制度:\r\n10.行政单位会计制度 \r\n20.事业单位会计制度 \r\n21.科学事业单位会计制度 \r\n22.中小学校会计制度 \r\n23.高等学校会计制度 \r\n24.医院会计制度 \r\n25.基层医疗卫生机构会计制度 \r\n26.测绘事业单位会计制度 \r\n27.地质勘查单位会计制度 \r\n28.彩票机构会计制度 \r\n30.民间非营利组织会计制度 \r\n50.其他',
  `corporate_tag` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业标志',
  `maintainer_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修商标志',
  `supplier_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商标志',
  `manufacture_mark` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造商标志',
  `asset_disposal_agency_tag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产处置代理机构标志',
  `state_asset_management_company` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国有资产经营公司标志',
  `authorized_number` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制人数',
  `administration_authority` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政编制',
  `enable_time` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `seal_time` datetime NULL DEFAULT NULL COMMENT '封存时间',
  `unit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `asset_download_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产数据下载状态',
  `latitude_and_longitude` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GPS经纬度',
  `created_time` datetime NULL DEFAULT NULL COMMENT '插入时间',
  `status` tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1：有效 0：无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_unit
-- ----------------------------
INSERT INTO `as_unit` VALUES (1, '浙江省政府办公厅', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-05 18:55:23', 1);
INSERT INTO `as_unit` VALUES (2, '浙江省商务厅', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-05 18:58:07', 1);

-- ----------------------------
-- Table structure for as_user
-- ----------------------------
DROP TABLE IF EXISTS `as_user`;
CREATE TABLE `as_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户代码',
  `staff_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '员工代码',
  `real_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '真实姓名',
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号名称',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：\r\n1、男\r\n2、女',
  `user_birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
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
  `status` tinyint(1) NOT NULL COMMENT '状态 1：有效 0：无效',
  `user_type` int(1) NULL DEFAULT NULL COMMENT '用户类型：\r\n1、政府财政 \r\n2、主管部门 \r\n3、行政单位 \r\n4、事业单位 \r\n5、国有企业 \r\n9、普通用户',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `enable_time` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '最后一次更新时间戳',
  `remove_time` datetime NULL DEFAULT NULL COMMENT '注销时间',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_name`(`account_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_user
-- ----------------------------
INSERT INTO `as_user` VALUES ('1', '1', '系统管理员', 'admin', '系统管理员', 1, '2019-05-27 15:36:20', '123456@admin.com', '13112341234', 1, '330312123412341234', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 15:36:50');
INSERT INTO `as_user` VALUES ('4', NULL, 'hhj', 'hhj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$diZVrY./3ywZAehLq7VDkOqGmh7lCfAUYVp.6rX43bcQ.LwCOqclK', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:41:27');
INSERT INTO `as_user` VALUES ('5', NULL, 'yby', 'yby', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$mR.wbAklwKsKjeJ27NVb1OlQfH/4iPcIxq34x9ps0USPX1H3R.UGe', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:43:46');
INSERT INTO `as_user` VALUES ('5fa7c0a0-8b29-11e9-aa21-005056c00001', 'f4e1368a-8b2d-11e9-9815-005056c00001', '测试官', 'test2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$dKqMmcARvdIFpSAR6S4VLOgoHlec8NQSXyIqSzqL.pyzcKxyggRJ2', NULL, NULL, 0, '浙江省杭州市江干区', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-06-10 10:42:22');
INSERT INTO `as_user` VALUES ('6', NULL, 'pqq', 'pqq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$UgIXCnxuYgwmxr72Ma94yeO3YZFfRFvmBfmQN6wPtXme4rCh9rf.O', NULL, NULL, 1, '浙江省杭州市江干区', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-27 12:45:24');
INSERT INTO `as_user` VALUES ('7', NULL, 'test', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$/TV4/D0vaZ/DMHcnYMQgLe1sESduVs3WD671C807E8WW0q1WhO4Wa', NULL, NULL, 0, '浙江省杭州市江干区', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-05-29 12:08:34');
INSERT INTO `as_user` VALUES ('e6268402-8b27-11e9-818e-005056c00001', NULL, '测试官', 'test1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$.k3mMbJcrElvZErubKXaMuFuwD8luOvryh8zi53AyHzusxk0P.Z26', NULL, NULL, 0, '浙江省杭州市江干区', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-06-10 10:31:49');

-- ----------------------------
-- Table structure for as_user_role
-- ----------------------------
DROP TABLE IF EXISTS `as_user_role`;
CREATE TABLE `as_user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL COMMENT '插入时间',
  `status` int(2) NOT NULL COMMENT '状态 1：有效 0：无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_user_role
-- ----------------------------
INSERT INTO `as_user_role` VALUES (1, '1', 1, '2019-05-15 11:13:39', 1);
INSERT INTO `as_user_role` VALUES (2, '4', 2, '2019-05-28 10:15:09', 1);
INSERT INTO `as_user_role` VALUES (35, '1', 2, '2019-06-18 15:01:34', 1);
INSERT INTO `as_user_role` VALUES (36, '1', 1, '2019-06-18 15:28:58', 1);
INSERT INTO `as_user_role` VALUES (37, '1', 1, '2019-06-18 15:28:58', 1);

-- ----------------------------
-- Table structure for as_user_scene
-- ----------------------------
DROP TABLE IF EXISTS `as_user_scene`;
CREATE TABLE `as_user_scene`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(20) NOT NULL,
  `user_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
