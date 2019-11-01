/*
 Navicat Premium Data Transfer

 Source Server         : 10.1.18.178
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 10.1.18.178:3306
 Source Schema         : asset-platform-process

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 19/08/2019 20:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ACT_ADM_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ADM_DATABASECHANGELOG`;
CREATE TABLE `ACT_ADM_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ADM_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_ADM_DATABASECHANGELOG` VALUES ('1', 'flowable', 'META-INF/liquibase/flowable-admin-app-db-changelog.xml', '2019-08-19 19:58:13', 1, 'EXECUTED', '8:655e3bb142f7d051dfc2d641ee0eeebd', 'createTable tableName=ACT_ADM_SERVER_CONFIG', '', NULL, '3.6.2', NULL, NULL, '6215987303');

-- ----------------------------
-- Table structure for ACT_ADM_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ADM_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_ADM_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ADM_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_ADM_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_ADM_SERVER_CONFIG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ADM_SERVER_CONFIG`;
CREATE TABLE `ACT_ADM_SERVER_CONFIG`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SERVER_ADDRESS_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PORT_` int(11) NULL DEFAULT NULL,
  `CONTEXT_ROOT_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REST_ROOT_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `USER_NAME_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PASSWORD_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ENDPOINT_TYPE_` int(11) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ADM_SERVER_CONFIG
-- ----------------------------
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7cb4eac-c278-11e9-abcb-2e15a8856301', 'Flowable Process app', 'Flowable Process REST config', 'http://localhost', 8080, 'flowable-task', 'process-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 1, NULL);
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7cbeaed-c278-11e9-abcb-2e15a8856301', 'Flowable CMMN app', 'Flowable CMMN REST config', 'http://localhost', 8080, 'flowable-task', 'cmmn-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 5, NULL);
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7cc390e-c278-11e9-abcb-2e15a8856301', 'Flowable App app', 'Flowable App REST config', 'http://localhost', 8080, 'flowable-task', 'app-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 6, NULL);
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7ccae3f-c278-11e9-abcb-2e15a8856301', 'Flowable DMN app', 'Flowable DMN REST config', 'http://localhost', 8080, 'flowable-task', 'dmn-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 2, NULL);
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7ccfc60-c278-11e9-abcb-2e15a8856301', 'Flowable Form app', 'Flowable Form REST config', 'http://localhost', 8080, 'flowable-task', 'form-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 3, NULL);
INSERT INTO `ACT_ADM_SERVER_CONFIG` VALUES ('d7cd2371-c278-11e9-abcb-2e15a8856301', 'Flowable Content app', 'Flowable Content REST config', 'http://localhost', 8080, 'flowable-task', 'content-api', 'admin', 'wf088DItMLLPiQIoM5rajQ==', 4, NULL);

-- ----------------------------
-- Table structure for ACT_APP_APPDEF
-- ----------------------------
DROP TABLE IF EXISTS `ACT_APP_APPDEF`;
CREATE TABLE `ACT_APP_APPDEF`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_APP_DEF_DPLY`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_APP_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_APP_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_APP_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_APP_DATABASECHANGELOG`;
CREATE TABLE `ACT_APP_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_APP_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_APP_DATABASECHANGELOG` VALUES ('1', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2019-08-19 19:58:53', 1, 'EXECUTED', '8:496fc778bdf2ab13f2e1926d0e63e0a2', 'createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...', '', NULL, '3.6.2', NULL, NULL, '6216027159');
INSERT INTO `ACT_APP_DATABASECHANGELOG` VALUES ('2', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2019-08-19 19:58:53', 2, 'EXECUTED', '8:ccea9ebfb6c1f8367ca4dd473fcbb7db', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_APP_DEPLOYMENT', '', NULL, '3.6.2', NULL, NULL, '6216027159');

-- ----------------------------
-- Table structure for ACT_APP_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_APP_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_APP_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_APP_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_APP_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_APP_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_APP_DEPLOYMENT`;
CREATE TABLE `ACT_APP_DEPLOYMENT`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_APP_DEPLOYMENT_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_APP_DEPLOYMENT_RESOURCE`;
CREATE TABLE `ACT_APP_DEPLOYMENT_RESOURCE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_BYTES_` longblob NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_APP_RSRC_DPL`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_APP_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_APP_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_CASEDEF
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_CASEDEF`;
CREATE TABLE `ACT_CMMN_CASEDEF`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` bit(1) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `DGRM_RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `HAS_START_FORM_KEY_` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_CASE_DEF_DPLY`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_CASE_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_CMMN_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_DATABASECHANGELOG`;
CREATE TABLE `ACT_CMMN_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_CMMN_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('1', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-08-19 19:58:52', 1, 'EXECUTED', '8:8b4b922d90b05ff27483abefc9597aa6', 'createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...', '', NULL, '3.6.2', NULL, NULL, '6216025975');
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('2', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-08-19 19:58:52', 2, 'EXECUTED', '8:65e39b3d385706bb261cbeffe7533cbe', 'addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST', '', NULL, '3.6.2', NULL, NULL, '6216025975');
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('3', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-08-19 19:58:52', 3, 'EXECUTED', '8:c01f6e802b49436b4489040da3012359', 'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...', '', NULL, '3.6.2', NULL, NULL, '6216025975');
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('4', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-08-19 19:58:52', 4, 'EXECUTED', '8:e40d29cb79345b7fb5afd38a7f0ba8fc', 'createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST', '', NULL, '3.6.2', NULL, NULL, '6216025975');
INSERT INTO `ACT_CMMN_DATABASECHANGELOG` VALUES ('5', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2019-08-19 19:58:53', 5, 'EXECUTED', '8:70349de472f87368dcdec971a10311a0', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_CMMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_CASE_INST; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; modifyDataType columnName=T...', '', NULL, '3.6.2', NULL, NULL, '6216025975');

-- ----------------------------
-- Table structure for ACT_CMMN_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_CMMN_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_CMMN_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_CMMN_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_CMMN_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_DEPLOYMENT`;
CREATE TABLE `ACT_CMMN_DEPLOYMENT`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) NULL DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_DEPLOYMENT_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_DEPLOYMENT_RESOURCE`;
CREATE TABLE `ACT_CMMN_DEPLOYMENT_RESOURCE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_BYTES_` longblob NULL,
  `GENERATED_` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_CMMN_RSRC_DPL`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_CMMN_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_CMMN_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_HI_CASE_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_HI_CASE_INST`;
CREATE TABLE `ACT_CMMN_HI_CASE_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PARENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STATE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NULL DEFAULT NULL,
  `END_TIME_` datetime(3) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_HI_MIL_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_HI_MIL_INST`;
CREATE TABLE `ACT_CMMN_HI_MIL_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_STAMP_` datetime(3) NULL DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ELEMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_HI_PLAN_ITEM_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_HI_PLAN_ITEM_INST`;
CREATE TABLE `ACT_CMMN_HI_PLAN_ITEM_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STATE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_STAGE_` bit(1) NULL DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ITEM_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CREATED_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_AVAILABLE_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_ENABLED_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_DISABLED_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_STARTED_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_SUSPENDED_TIME_` datetime(3) NULL DEFAULT NULL,
  `COMPLETED_TIME_` datetime(3) NULL DEFAULT NULL,
  `OCCURRED_TIME_` datetime(3) NULL DEFAULT NULL,
  `TERMINATED_TIME_` datetime(3) NULL DEFAULT NULL,
  `EXIT_TIME_` datetime(3) NULL DEFAULT NULL,
  `ENDED_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_RU_CASE_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_RU_CASE_INST`;
CREATE TABLE `ACT_CMMN_RU_CASE_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `BUSINESS_KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PARENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STATE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `LOCK_TIME_` datetime(3) NULL DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_CASE_INST_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
  INDEX `ACT_IDX_CASE_INST_PARENT`(`PARENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_CASE_INST_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_RU_MIL_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_RU_MIL_INST`;
CREATE TABLE `ACT_CMMN_RU_MIL_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_STAMP_` datetime(3) NULL DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ELEMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_MIL_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
  INDEX `ACT_IDX_MIL_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_MIL_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MIL_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_RU_PLAN_ITEM_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_RU_PLAN_ITEM_INST`;
CREATE TABLE `ACT_CMMN_RU_PLAN_ITEM_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STAGE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_STAGE_` bit(1) NULL DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STATE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `ITEM_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ITEM_DEFINITION_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_COMPLETEABLE_` bit(1) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` bit(1) NULL DEFAULT NULL,
  `VAR_COUNT_` int(11) NULL DEFAULT NULL,
  `SENTRY_PART_INST_COUNT_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_PLAN_ITEM_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
  INDEX `ACT_IDX_PLAN_ITEM_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_PLAN_ITEM_STAGE_INST`(`STAGE_INST_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CMMN_RU_SENTRY_PART_INST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CMMN_RU_SENTRY_PART_INST`;
CREATE TABLE `ACT_CMMN_RU_SENTRY_PART_INST`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REV_` int(11) NOT NULL,
  `CASE_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PLAN_ITEM_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ON_PART_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IF_PART_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TIME_STAMP_` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_SENTRY_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
  INDEX `ACT_IDX_SENTRY_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_SENTRY_PLAN_ITEM`(`PLAN_ITEM_INST_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_SENTRY_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `ACT_CMMN_CASEDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SENTRY_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `ACT_CMMN_RU_CASE_INST` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SENTRY_PLAN_ITEM` FOREIGN KEY (`PLAN_ITEM_INST_ID_`) REFERENCES `ACT_CMMN_RU_PLAN_ITEM_INST` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CO_CONTENT_ITEM
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CO_CONTENT_ITEM`;
CREATE TABLE `ACT_CO_CONTENT_ITEM`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MIME_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TASK_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTENT_STORE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTENT_STORE_NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `FIELD_` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTENT_AVAILABLE_` bit(1) NULL DEFAULT b'0',
  `CREATED_` timestamp(6) NULL DEFAULT NULL,
  `CREATED_BY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LAST_MODIFIED_` timestamp(6) NULL DEFAULT NULL,
  `LAST_MODIFIED_BY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTENT_SIZE_` bigint(20) NULL DEFAULT 0,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `idx_contitem_taskid`(`TASK_ID_`) USING BTREE,
  INDEX `idx_contitem_procid`(`PROC_INST_ID_`) USING BTREE,
  INDEX `idx_contitem_scope`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_CO_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CO_DATABASECHANGELOG`;
CREATE TABLE `ACT_CO_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_CO_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_CO_DATABASECHANGELOG` VALUES ('1', 'activiti', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', '2019-08-19 19:58:51', 1, 'EXECUTED', '8:7644d7165cfe799200a2abdd3419e8b6', 'createTable tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_taskid, tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_procid, tableName=ACT_CO_CONTENT_ITEM', '', NULL, '3.6.2', NULL, NULL, '6216025750');
INSERT INTO `ACT_CO_DATABASECHANGELOG` VALUES ('2', 'flowable', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', '2019-08-19 19:58:51', 2, 'EXECUTED', '8:fe7b11ac7dbbf9c43006b23bbab60bab', 'addColumn tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_scope, tableName=ACT_CO_CONTENT_ITEM', '', NULL, '3.6.2', NULL, NULL, '6216025750');

-- ----------------------------
-- Table structure for ACT_CO_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_CO_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_CO_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_CO_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_CO_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_DE_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DE_DATABASECHANGELOG`;
CREATE TABLE `ACT_DE_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_DE_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_DE_DATABASECHANGELOG` VALUES ('1', 'flowable', 'META-INF/liquibase/flowable-modeler-app-db-changelog.xml', '2019-08-19 19:58:34', 1, 'EXECUTED', '8:e70d1d9d3899a734296b2514ccc71501', 'createTable tableName=ACT_DE_MODEL; createIndex indexName=idx_proc_mod_created, tableName=ACT_DE_MODEL; createTable tableName=ACT_DE_MODEL_HISTORY; createIndex indexName=idx_proc_mod_history_proc, tableName=ACT_DE_MODEL_HISTORY; createTable tableN...', '', NULL, '3.6.2', NULL, NULL, '6216008183');
INSERT INTO `ACT_DE_DATABASECHANGELOG` VALUES ('3', 'flowable', 'META-INF/liquibase/flowable-modeler-app-db-changelog.xml', '2019-08-19 19:58:34', 2, 'EXECUTED', '8:3a9143bef2e45f2316231cc1369138b6', 'addColumn tableName=ACT_DE_MODEL; addColumn tableName=ACT_DE_MODEL_HISTORY', '', NULL, '3.6.2', NULL, NULL, '6216008183');

-- ----------------------------
-- Table structure for ACT_DE_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DE_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_DE_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_DE_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_DE_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_DE_MODEL
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DE_MODEL`;
CREATE TABLE `ACT_DE_MODEL`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `model_key` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model_comment` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` datetime(6) NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `last_updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `model_editor_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `thumbnail` longblob NULL,
  `model_type` int(11) NULL DEFAULT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_proc_mod_created`(`created_by`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DE_MODEL_HISTORY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DE_MODEL_HISTORY`;
CREATE TABLE `ACT_DE_MODEL_HISTORY`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `model_key` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model_comment` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` datetime(6) NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_updated` datetime(6) NULL DEFAULT NULL,
  `last_updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `removal_date` datetime(6) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `model_editor_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `model_type` int(11) NULL DEFAULT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_proc_mod_history_proc`(`model_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DE_MODEL_RELATION
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DE_MODEL_RELATION`;
CREATE TABLE `ACT_DE_MODEL_RELATION`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `relation_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_relation_parent`(`parent_model_id`) USING BTREE,
  INDEX `fk_relation_child`(`model_id`) USING BTREE,
  CONSTRAINT `fk_relation_child` FOREIGN KEY (`model_id`) REFERENCES `ACT_DE_MODEL` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_relation_parent` FOREIGN KEY (`parent_model_id`) REFERENCES `ACT_DE_MODEL` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DMN_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_DATABASECHANGELOG`;
CREATE TABLE `ACT_DMN_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_DMN_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('1', 'activiti', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-08-19 19:58:51', 1, 'EXECUTED', '8:c8701f1c71018b55029f450b2e9a10a1', 'createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE', '', NULL, '3.6.2', NULL, NULL, '6216024907');
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('2', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-08-19 19:58:51', 2, 'EXECUTED', '8:47f94b27feb7df8a30d4e338c7bd5fb8', 'createTable tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.2', NULL, NULL, '6216024907');
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('3', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-08-19 19:58:51', 3, 'EXECUTED', '8:ac17eae89fbdccb6e08daf3c7797b579', 'addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.2', NULL, NULL, '6216024907');
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('4', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-08-19 19:58:51', 4, 'EXECUTED', '8:f73aabc4529e7292c2942073d1cff6f9', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE', '', NULL, '3.6.2', NULL, NULL, '6216024907');
INSERT INTO `ACT_DMN_DATABASECHANGELOG` VALUES ('5', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2019-08-19 19:58:51', 5, 'EXECUTED', '8:3e03528582dd4eeb4eb41f9b9539140d', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_DMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION; modifyDataType columnName=END_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '3.6.2', NULL, NULL, '6216024907');

-- ----------------------------
-- Table structure for ACT_DMN_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_DMN_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_DMN_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_DMN_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_DMN_DECISION_TABLE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_DECISION_TABLE`;
CREATE TABLE `ACT_DMN_DECISION_TABLE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `VERSION_` int(11) NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DMN_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_DEPLOYMENT`;
CREATE TABLE `ACT_DMN_DEPLOYMENT`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DMN_DEPLOYMENT_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_DEPLOYMENT_RESOURCE`;
CREATE TABLE `ACT_DMN_DEPLOYMENT_RESOURCE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_BYTES_` longblob NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_DMN_HI_DECISION_EXECUTION
-- ----------------------------
DROP TABLE IF EXISTS `ACT_DMN_HI_DECISION_EXECUTION`;
CREATE TABLE `ACT_DMN_HI_DECISION_EXECUTION`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DECISION_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NULL DEFAULT NULL,
  `END_TIME_` datetime(3) NULL DEFAULT NULL,
  `INSTANCE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ACTIVITY_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `FAILED_` bit(1) NULL DEFAULT b'0',
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EXECUTION_JSON_` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_EVT_LOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_EVT_LOG`;
CREATE TABLE `ACT_EVT_LOG`  (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DATA_` longblob NULL,
  `LOCK_OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`LOG_NR_`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_FO_DATABASECHANGELOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_DATABASECHANGELOG`;
CREATE TABLE `ACT_FO_DATABASECHANGELOG`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_FO_DATABASECHANGELOG
-- ----------------------------
INSERT INTO `ACT_FO_DATABASECHANGELOG` VALUES ('1', 'activiti', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-08-19 19:58:51', 1, 'EXECUTED', '8:033ebf9380889aed7c453927ecc3250d', 'createTable tableName=ACT_FO_FORM_DEPLOYMENT; createTable tableName=ACT_FO_FORM_RESOURCE; createTable tableName=ACT_FO_FORM_DEFINITION; createTable tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.2', NULL, NULL, '6216025202');
INSERT INTO `ACT_FO_DATABASECHANGELOG` VALUES ('2', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-08-19 19:58:51', 2, 'EXECUTED', '8:986365ceb40445ce3b27a8e6b40f159b', 'addColumn tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.2', NULL, NULL, '6216025202');
INSERT INTO `ACT_FO_DATABASECHANGELOG` VALUES ('3', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-08-19 19:58:51', 3, 'EXECUTED', '8:abf482518ceb09830ef674e52c06bf15', 'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_FO_FORM_DEFINITION', '', NULL, '3.6.2', NULL, NULL, '6216025202');
INSERT INTO `ACT_FO_DATABASECHANGELOG` VALUES ('4', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', '2019-08-19 19:58:51', 4, 'EXECUTED', '8:2087829f22a4b2298dbf530681c74854', 'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_FO_FORM_DEPLOYMENT; modifyDataType columnName=SUBMITTED_DATE_, tableName=ACT_FO_FORM_INSTANCE', '', NULL, '3.6.2', NULL, NULL, '6216025202');

-- ----------------------------
-- Table structure for ACT_FO_DATABASECHANGELOGLOCK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_DATABASECHANGELOGLOCK`;
CREATE TABLE `ACT_FO_DATABASECHANGELOGLOCK`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_FO_DATABASECHANGELOGLOCK
-- ----------------------------
INSERT INTO `ACT_FO_DATABASECHANGELOGLOCK` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_FO_FORM_DEFINITION
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_FORM_DEFINITION`;
CREATE TABLE `ACT_FO_FORM_DEFINITION`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `VERSION_` int(11) NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_FO_FORM_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_FORM_DEPLOYMENT`;
CREATE TABLE `ACT_FO_FORM_DEPLOYMENT`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_FO_FORM_INSTANCE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_FORM_INSTANCE`;
CREATE TABLE `ACT_FO_FORM_INSTANCE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FORM_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TASK_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SUBMITTED_DATE_` datetime(3) NULL DEFAULT NULL,
  `SUBMITTED_BY_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `FORM_VALUES_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_FO_FORM_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_FO_FORM_RESOURCE`;
CREATE TABLE `ACT_FO_FORM_RESOURCE`  (
  `ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESOURCE_BYTES_` longblob NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_GE_BYTEARRAY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;
CREATE TABLE `ACT_GE_BYTEARRAY`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `BYTES_` longblob NULL,
  `GENERATED_` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_FK_BYTEARR_DEPL`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_GE_BYTEARRAY
-- ----------------------------
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203ea-c278-11e9-8c70-2e15a8856301', 1, 'VacationRequest.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822203F3E0A0A3C646566696E6974696F6E732069643D22646566696E6974696F6E73220A202020202020202020202020207461726765744E616D6573706163653D22687474703A2F2F61637469766974692E6F72672F62706D6E323022200A20202020202020202020202020786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C220A20202020202020202020202020786D6C6E733A7873693D22687474703A2F2F7777772E77332E6F72672F323030312F584D4C536368656D612D696E7374616E6365220A20202020202020202020202020786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E223E0A20200A20203C70726F636573732069643D227661636174696F6E5265717565737422206E616D653D225661636174696F6E2072657175657374223E0A20200A202020203C73746172744576656E742069643D2272657175657374222061637469766974693A696E69746961746F723D22656D706C6F7965654E616D65223E0A2020202020203C657874656E73696F6E456C656D656E74733E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D226E756D6265724F664461797322206E616D653D224E756D626572206F6620646179732220747970653D226C6F6E67222076616C75653D2231222072657175697265643D2274727565222F3E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D2273746172744461746522206E616D653D22466972737420646179206F6620686F6C69646179202864642D4D4D2D797979292220646174655061747465726E3D2264642D4D4D2D797979792068683A6D6D2220747970653D2264617465222072657175697265643D227472756522202F3E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D227661636174696F6E4D6F7469766174696F6E22206E616D653D224D6F7469766174696F6E2220747970653D22737472696E6722202F3E0A2020202020203C2F657874656E73696F6E456C656D656E74733E0A202020203C2F73746172744576656E743E0A202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D227265717565737422207461726765745265663D2268616E646C655265717565737422202F3E0A202020200A202020203C757365725461736B2069643D2268616E646C655265717565737422206E616D653D2248616E646C65207661636174696F6E207265717565737422203E0A2020202020203C646F63756D656E746174696F6E3E0A2020202020202020247B656D706C6F7965654E616D657D20776F756C64206C696B6520746F2074616B6520247B6E756D6265724F66446179737D20646179287329206F66207661636174696F6E20284D6F7469766174696F6E3A20247B7661636174696F6E4D6F7469766174696F6E7D292E0A2020202020203C2F646F63756D656E746174696F6E3E200A2020202020203C657874656E73696F6E456C656D656E74733E0A2020202020202020203C61637469766974693A666F726D50726F70657274792069643D227661636174696F6E417070726F76656422206E616D653D22446F20796F7520617070726F76652074686973207661636174696F6E2220747970653D22656E756D222072657175697265643D2274727565223E0A202020202020202020203C61637469766974693A76616C75652069643D227472756522206E616D653D22417070726F766522202F3E0A202020202020202020203C61637469766974693A76616C75652069643D2266616C736522206E616D653D2252656A65637422202F3E0A20202020202020203C2F61637469766974693A666F726D50726F70657274793E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D226D616E616765724D6F7469766174696F6E22206E616D653D224D6F7469766174696F6E2220747970653D22737472696E6722202F3E0A2020202020203C2F657874656E73696F6E456C656D656E74733E0A2020202020203C706F74656E7469616C4F776E65723E0A20202020202020203C7265736F7572636541737369676E6D656E7445787072657373696F6E3E0A202020202020202020203C666F726D616C45787072657373696F6E3E6D616E6167656D656E743C2F666F726D616C45787072657373696F6E3E0A20202020202020203C2F7265736F7572636541737369676E6D656E7445787072657373696F6E3E0A2020202020203C2F706F74656E7469616C4F776E65723E2020202020202020200A202020203C2F757365725461736B3E0A202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D2268616E646C655265717565737422207461726765745265663D2272657175657374417070726F7665644465636973696F6E22202F3E0A202020200A202020203C6578636C7573697665476174657761792069643D2272657175657374417070726F7665644465636973696F6E22206E616D653D225265717565737420617070726F7665643F22202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77332220736F757263655265663D2272657175657374417070726F7665644465636973696F6E22207461726765745265663D2273656E64417070726F76616C4D61696C223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B7661636174696F6E417070726F766564203D3D202774727565277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A202020200A202020203C7461736B2069643D2273656E64417070726F76616C4D61696C22206E616D653D2253656E6420636F6E6669726D6174696F6E20652D6D61696C22202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77342220736F757263655265663D2273656E64417070726F76616C4D61696C22207461726765745265663D22746865456E643122202F3E0A202020203C656E644576656E742069643D22746865456E643122202F3E0A202020200A202020203C73657175656E6365466C6F772069643D22666C6F77352220736F757263655265663D2272657175657374417070726F7665644465636973696F6E22207461726765745265663D2261646A7573745661636174696F6E526571756573745461736B223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B7661636174696F6E417070726F766564203D3D202766616C7365277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A202020200A202020203C757365725461736B2069643D2261646A7573745661636174696F6E526571756573745461736B22206E616D653D2241646A757374207661636174696F6E2072657175657374223E0A2020202020203C646F63756D656E746174696F6E3E0A2020202020202020596F7572206D616E616765722068617320646973617070726F76656420796F7572207661636174696F6E207265717565737420666F7220247B6E756D6265724F66446179737D20646179732E0A2020202020202020526561736F6E3A20247B6D616E616765724D6F7469766174696F6E7D0A2020202020203C2F646F63756D656E746174696F6E3E0A2020202020203C657874656E73696F6E456C656D656E74733E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D226E756D6265724F664461797322206E616D653D224E756D626572206F662064617973222076616C75653D22247B6E756D6265724F66446179737D2220747970653D226C6F6E67222072657175697265643D2274727565222F3E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D2273746172744461746522206E616D653D22466972737420646179206F6620686F6C69646179202864642D4D4D2D79797929222076616C75653D22247B7374617274446174657D2220646174655061747465726E3D2264642D4D4D2D797979792068683A6D6D2220747970653D2264617465222072657175697265643D227472756522202F3E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D227661636174696F6E4D6F7469766174696F6E22206E616D653D224D6F7469766174696F6E222076616C75653D22247B7661636174696F6E4D6F7469766174696F6E7D2220747970653D22737472696E6722202F3E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D22726573656E645265717565737422206E616D653D22526573656E64207661636174696F6E207265717565737420746F206D616E616765723F2220747970653D22656E756D222072657175697265643D2274727565223E0A202020202020202020203C61637469766974693A76616C75652069643D227472756522206E616D653D2259657322202F3E0A202020202020202020203C61637469766974693A76616C75652069643D2266616C736522206E616D653D224E6F22202F3E0A20202020202020203C2F61637469766974693A666F726D50726F70657274793E0A2020202020203C2F657874656E73696F6E456C656D656E74733E0A2020202020203C68756D616E506572666F726D65723E0A20202020202020203C7265736F7572636541737369676E6D656E7445787072657373696F6E3E0A202020202020202020203C666F726D616C45787072657373696F6E3E247B656D706C6F7965654E616D657D3C2F666F726D616C45787072657373696F6E3E0A20202020202020203C2F7265736F7572636541737369676E6D656E7445787072657373696F6E3E0A2020202020203C2F68756D616E506572666F726D65723E20200A202020203C2F757365725461736B3E0A202020203C73657175656E6365466C6F772069643D22666C6F77362220736F757263655265663D2261646A7573745661636174696F6E526571756573745461736B22207461726765745265663D22726573656E64526571756573744465636973696F6E22202F3E0A202020200A202020203C6578636C7573697665476174657761792069643D22726573656E64526571756573744465636973696F6E22206E616D653D22526573656E6420726571756573743F22202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77372220736F757263655265663D22726573656E64526571756573744465636973696F6E22207461726765745265663D2268616E646C6552657175657374223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B726573656E6452657175657374203D3D202774727565277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A202020200A20202020203C73657175656E6365466C6F772069643D22666C6F77382220736F757263655265663D22726573656E64526571756573744465636973696F6E22207461726765745265663D22746865456E6432223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B726573656E6452657175657374203D3D202766616C7365277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A202020203C656E644576656E742069643D22746865456E643222202F3E0A2020202020200A20203C2F70726F636573733E0A20200A3C2F646566696E6974696F6E733E0A, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203eb-c278-11e9-8c70-2e15a8856301', 1, 'FixSystemFailureProcess.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C646566696E6974696F6E732069643D22646566696E6974696F6E7322200A2020786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C220A2020786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E220A20207461726765744E616D6573706163653D224578616D706C6573223E0A20200A20203C70726F636573732069643D2266697853797374656D4661696C75726522206E616D653D224669782073797374656D206661696C757265223E0A20200A202020203C73746172744576656E742069643D22746865537461727422202F3E0A202020200A202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22746865537461727422207461726765745265663D2273756250726F6365737322202F3E0A202020200A202020203C73756250726F636573732069643D2273756250726F63657373223E0A202020200A2020202020203C73746172744576656E742069643D2273756250726F63657373537461727422202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D2273756250726F63657373537461727422207461726765745265663D2273756250726F63657373466F726B22202F3E0A2020202020200A2020202020203C706172616C6C656C476174657761792069643D2273756250726F63657373466F726B22202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77332220736F757263655265663D2273756250726F63657373466F726B22207461726765745265663D227461736B3122202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77342220736F757263655265663D2273756250726F63657373466F726B22207461726765745265663D227461736B3222202F3E0A2020202020200A2020202020203C757365725461736B2069643D227461736B3122206E616D653D22496E766573746967617465206861726477617265222061637469766974693A63616E64696461746547726F7570733D22656E67696E656572696E6722202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77352220736F757263655265663D227461736B3122207461726765745265663D2273756250726F636573734A6F696E22202F3E0A2020202020200A2020202020203C757365725461736B2069643D227461736B3222206E616D653D22496E76657374696761746520736F667477617265222061637469766974693A63616E64696461746547726F7570733D22656E67696E656572696E6722202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77362220736F757263655265663D227461736B3222207461726765745265663D2273756250726F636573734A6F696E22202F3E0A2020202020200A2020202020203C706172616C6C656C476174657761792069643D2273756250726F636573734A6F696E22202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77372220736F757263655265663D2273756250726F636573734A6F696E22207461726765745265663D2273756250726F63657373456E6422202F3E0A2020202020203C656E644576656E742069643D2273756250726F63657373456E6422202F3E0A202020200A202020203C2F73756250726F636573733E0A202020200A202020203C212D2D2054696D6572206F6E2073756270726F63657373202D2D3E0A202020203C626F756E646172794576656E742069643D2274696D657222206174746163686564546F5265663D2273756250726F63657373223E0A2020202020203C74696D65724576656E74446566696E6974696F6E3E0A20202020202020203C74696D654475726174696F6E3E505434483C2F74696D654475726174696F6E3E0A2020202020203C2F74696D65724576656E74446566696E6974696F6E3E0A202020203C2F626F756E646172794576656E743E0A202020203C73657175656E6365466C6F772069643D22666C6F77382220736F757263655265663D2274696D657222207461726765745265663D227461736B416674657254696D657222202F3E0A202020203C757365725461736B2069643D227461736B416674657254696D657222206E616D653D2248616E64206F76657220746F204C6576656C203220737570706F7274222061637469766974693A63616E64696461746547726F7570733D226D616E6167656D656E7422202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77392220736F757263655265663D227461736B416674657254696D657222207461726765745265663D22746865456E643122202F3E0A202020203C656E644576656E742069643D22746865456E643122202F3E0A202020200A202020203C73657175656E6365466C6F772069643D22666C6F7731302220736F757263655265663D2273756250726F6365737322207461726765745265663D227461736B416674657253756250726F6365737322202F3E0A202020203C757365725461736B2069643D227461736B416674657253756250726F6365737322206E616D653D225772697465207265706F7274222061637469766974693A63616E64696461746547726F7570733D22656E67696E656572696E6722202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F7731312220736F757263655265663D227461736B416674657253756250726F6365737322207461726765745265663D22746865456E6422202F3E0A202020203C656E644576656E742069643D22746865456E6422202F3E0A202020200A20203C2F70726F636573733E0A0A3C2F646566696E6974696F6E733E, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203ec-c278-11e9-8c70-2e15a8856301', 1, 'VacationRequest.png', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x89504E470D0A1A0A0000000D49484452000002E2000001270806000000EC2E26A5000000206348524D00007A26000080840000FA00000080E8000075300000EA6000003A98000017709CBA513C0000000467414D410000B18E7CFB5193000000017352474200AECE1CE900000006624B474400FF00FF00FFA0BDA793000000097048597300000EC400000EC401952B0E1B000020004944415478DAED9D097814C5D686CF248184800A8AAC9145115051F11715BD20B8B1C84E005944822CB20A5C40454082C822888080806C89102010F6B0095EA3A01715041565B90A414008A0448C2140C8FC75BA6B9299C92C3D33DD3393E47B9FA79EE9A5AABAA7A7E7D4D7A74F551101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001A30E1120023309BCD111F7EF8C1A0AFBFFEF6C523478ED6FAE1871F23B2B2B27061FC4C6868A8B95AB54A7F54A850F69B73E7FE7CEFD75F4F7E86AB02006C1D809D0410E2A090B278F147FD962F4F987EE2446A64AF5E2F538306FFA2FAF51FA388880857CD998B75B38B6DEEF6BBFBF4769F5EE58D3A77F5332BEB2A1D3C7898F6ECF98E162D5A7BEDEFBFFF39FCFBEFE75F14BB0EE14E052010B6CE9D9D73B7CF1BDBA8D58EE8656F83619B76DBA9DAC92356763243D8C90BB09300421C143C162DFA68C398316FB579FDF5913468D0000A0B0B73739B79DAF8782BC0F512C17A8B69FF9EC39C392B68ECD80FAE5CBB76AD4B66E6B58DB86301D0D3D6696956CD1E08723DC5B8DE02DB28BB6CA410D7B60D7612F893505C02A067C33465CABB6D12135752870ED1141212AAA15132D9E5B15E3739786634697C9E3479F0ACE9CF3C81AA5FE5D147EFA7962D1B155BB1625B8BCCCCAC1FC5A663B87301F0D5D68538B065EE6C9EC9C97F5BCBB22FF6C19B7AF4F6D999743E7F7DCF0F76124088830207BFA21D33E6ADE1DC30F1AB59C7E2DA5922170D93C95061EA0FF1EBDFFADDD757AEDCADF4FCF30DC3E3E2363C9F9D7D638BD8741E773000BED83A4FEC9D1E02DC93FDFE14D87A9F43E05EDAC34E0208715060E0CE4AC3870FFFB457AF978BB17728BFB8B64DC9C95B68F4E8B1346FDE7C5ABB762D454696A4DAB56B7960947DF580EB6DE48D3EA6FE8D113732E1E1C5323FFBECDBFFCBC9C989C75D0C802FB64EAB0037E928C083F761BFB0003B0920C44141315643B76CD9D662F9F278198EE24818AB8D506CEC78DAB973179D3A758ABEFFFE7BBA7CF96FDABF7F1FFDF6DB297AFAE9A77410D75A047C303424A6801FFB89271E2A316BD6F26257AE5CFDAF583D8D3B19006F6C9D737BA7EDED9FB7025C6F0FB7DEE122C16E33B5D5F7C413756127018438086ECA96BDFDC3679F7DA642E3C68D9C3436EA677272326DD8B081B66EDDAA08F16BD7AED15F7FFD4567CF9E1542FC2455AC5891EAD4B98F02E3D50E26A1EC3FCE9C493BF6CD373F468AC59DB89301F0C6D639B677DAEC8ABF43438A967DD3CF4E9E879D048611824B007CE5C89123B578D82E77C4C7C72B5EF0ECECEC7CFB4E9F3E433D7AF4C4C5F4331D3A3C57597C34C69500C05B5BE7A9A88508869D04208F305C02E02B3C8185DA69C935E7CE9DA3DF7FFF9DCA972F4FB56BD7A6B4736914512282CC39666AD9AAA522D4817F79F4D13A15C5C7EDB81200786BEBCC1E8A6B332E24EC240010E2403F781639C71358581A28F55311E0B56AD38A152BE8CE3BEFCC973B4F887BD25019D5A8158DC63222221C7600009F6D5D7E7BA7CDAE186967CC7818809D04050084A600838C7FFECF98981E4A2376CB2DB7E42BB5EBD35DF4F0C3FFE7A62EBDCF2918AE5330FC460000DFFEC38E6C8CA3440ED6895CCFB0E9EB365F6C92D987B2C16E3361FF00843828128D525E6AD9B205DD57E75E7AFA99A729EB6A964DE9912347525CDC12D2E6C531EB242ECD067C77238E89060380E0B675AEC4B733314E1E2C0793FD803D0200421C14B8062A3DFD124D9E3C857EFAE9675ABC68314584DBBEDEBDE38E3BC4FE77E9D4A9D31E08EB82E88541230940E1B3759E0A724F44B859C3F1B5D801B39FED851EE7007B0620C401F0B9815ABA349EA2A33BD1DDB56A2B6129952A55CA57EABEFBEEA3AA55ABD30B9DBBD1E225712E1A1B4F8DBBD9C373363A4FA0EA0700E86FEB5C097277E5F410E4DED8076FEAF187C3C0EC87B200041FE87C007436ACA6DCE58C8C7FA87FFF8174E75D77D3BA0D1B292C2C8C2A578EA25EBD7A51EBD6ADE9FEFBEF57F21D3F7E9C8705A311AFBD4EDDBA77A7D16F8EA2397317D0A0817D5D886A6F3D2BBE86B70483A71EA21D80E0B075AED6BD715C68D9E7CE41A155BCFB1A06130C5E77A31E00603F81FF80471C1862E852534F52CB966DE8C5EE31F4DAEB6F28229C79E081072869FD7A2A5FA922EDDBBF9FF67EF33555AC548996AF58A98CAAC2F9DE9D3A8DF6EFFB8E5625AEF5A0A1D1EA3DD75BF06A291F0C1D330100FAFFB75C85A438CBEFACF3A6B7225C4F5B14ACDE72D83E507881471CE868F4F23C42CD9BB754BCE055AA54CD9793C3533A757AC1656D333FF8805AB56C419D5F684FFA8D2EE0AB17DC5763AF97C7DD97630300F411788E3CE0261FED865E1E72AD76CFDBFDDED81B7F84A320E40540880334500A3C557D68A8F7B797C583EE7B67263D3B070562D41684A100503004B95EA2D593753D475CF164BF2FE182C1D0B9143613040F084D013A1AB63CE3F6E083F7D3B7DF7C93BBBE7CD9324DB538CEE769C726AD42D91F9EF260EB2C8A31C40340695C82C26BEB5C8F8AE2AABCBBCE9E5AD6BDB18981F0861B5916B60C40880390AFB1A857EFFFE8C71F7FC8DD9A90B05C53E9FCF99C354466F22D56DC084FB9AF02D8ACF3B1D088059AC71E7BEC45F171E9C9279FEC81AB515805B9B3186F2D43176A11E4E4A320D77B4416B30FE5BDE9CBE38DFD0E564F3C0010E2C02F8D120921FE307DB7FF3B9DEBD622CE3D6D2CF410F09E362C7A187678BF839D060D1A74397CF870FCD0A143E9E0C1834B1E79E491185C15BF518DF2422F2B1869EBB4896F775E7377DBBD15E4BE0C91E86B784BA066F984F806050BC48803434479D9B2B7D2CF3FFF44CF376FAA6C399F9696BBEC0AEB7CC78FFF4ABE755EF2B51393B762DD1BB1ECAD904663118C3CF0C003D1DF7FFFFDB2152B5684B46CD9929A366D1AD2B56BD745B56BD7BE76E4C89115FE7E261069B2486345DA2352B6DCDE4CA471227514E97421FB094E88545DA45491668B5456A47622A5EB2FCAED31F9F810ADC52E9903B4EC8DE0D663F859D83F00210E800706516D884E9FFE9F411E0C2D8D92B722DD13816C84A03632261D1E72BFA8DE060DBAFCF0C30FCB2D225C51BCCD9AD1EAD5AB43DBB76FFFB110E3E44731BE50A47A7299C363EA883447AE6F16E90E91CE15F29F841F34768B5457A414FDC5B7C9477168F6C1CE044290FBDA5FC793EFE88DBD46580A28582034051820C4CD3E244775D86F73B4EE2E44C557A3AFE78C9EFE183211A3AD04028E0567119E9090902BC22D3CFBECB38A183F73E6CCC7515151EDFC744ABD456A25D241917A5A89702683F2876DD416A9B148A5ACB6F17294D5FEBA76652AC83C1152F45B774E8DB02B6BBDAFAE3C5694D5B66AB28C75F9C676656244AAE1E81948A40E76E76E5DEF39FD6D9DAFF6CE971095407BC57D15D7BEDA2223271C826D04FE23683CE266B33962E7CE9D3DB66EDDDAF5D8B163750F1F3E1C79E1C285B08C8C0C0A0F0F37972D5BF67AE5CA95CFDC7EFBED5FDDB87163C2F6EDDB8FE2E70B66316EC1E443592D5E20BD3D3B7AC599EB2DBA8DA81BE82DC239163C313131843DE08EE0EDEBD6AD0B6DDBB66D62646464C7CCCCCC8D069FD64529421DB1C74E189F9082BDB42CF3B814AF2C6EC7C9FC2C8439CC638348FD65B995221DE2670D29EEB96C7391F689545FA4A55665F961E08848DB480D91D94BAAD77EB048DB655D8922CDB47A908826D5933D42A45E321F87DA7491DBB91D5B2345FBD732BF3D2CF67F090E5BA7A7903462DC71BD847A41F386834204FFDFDB8AD448DA1D8BB3204BDA34B6735F8AB48A82202C2FE0429C05F8DB6FBFFDD1D34F3FDDE5D4A953618D1B37A69E3D7B529D3A75282A2A8A4A952A45428C9B2E5EBC58FCD0A143D53FFDF4D3EA9B376FEE5AB366CDDFFEFCF3CF77C4F645B8E7824984FBFA9A568F46C8A846C4E80E9D6603AE995E75035708BBD5EDBBEFBE7329C22DB0677CC3860DC5DAB469B35A88F1F6428C6F31F0D458AC6E96A2B89414CA16B8414AB55A7F88F262A82F911A431E27D72B4881DC5D0AEDC322CD9502DCD2F03D241BBA79527CDF2FF771FEB1B22C4911CDE7D14A8A717E18594F6A4CF702918693EAB9E77D1C4E335DB655E3E4C3011F931D31A3A510E7F3AC27CB674B41DED9EE3AA4C8733C4D7931F24162EBDC95F7547C1684F015A3874834FBA90C0832587C0F0C090979322727A7B883FD11D21E559379A749DBB058A4E5813AE9800AF1A4A4A49745A3F4615A5A5AF8E4C993156F51DE442E79B018E754AD5A35E2D7BD3366CC306DDFBEBDEAE8D1A3E78BFCC3CF9D3BD74A5F6F07D0BF31311964508D6874F49ABDD3AC9368F75450235EDC9FD4AC59B3EDFEFDFBE33926DC9D08B716E31B376E2C2EEC190BD0D6A47A798D609748F748E17C580A5916A3BBA5A0B516E22CA2D9FB5DDB413D2CDA93E572AAF428D5B312E21B6579CB723FABB2A9768D5C0729AE2D82789F14FAEC3D4F126986F4629D930DE676B9AF9414F95CAE34E579F35B906D27D42C07E7BF587EE747C8B010152DF64EEB7FCED7BE328116E4BEEE37D21BEE8DF8869D0C72AA994CA6F966B35919E94188708FFC2832BD42EA1B3BBF6BC980087171B1C2A64E9DFAC9A041839E1A3E7C38F1F05E8E04B82BB8C1132974CE9C39B5C78E1DFB8328FF2ABCE3C122C04D3E1832AD5E21231B1D4F1B063D66F634622650342246C39E7029C243ED63C2B588F1E4E4E462AD5AB55A9F9595D5D14AE8EA0D0BCF0DF293BDC81C52F21CA9E11C15AC04EF4E9112A48768A05579471E64575E655F3CCE1952B4B711E92AA9AF8ED3659D2CB0FB5809ED6C0FDAB1C9F28124437F016ED2E13FA757874D7F392BFC25C8F572DE407C17623A8786867E78E3C68D32B9C256E849B6AFD1D1D154BF7E7DAA50A102952D5B96D2D3D3E9DCB9733C9C2CAD5DBB96ED2F09DB6B29C67D4C0E48FBE857EFB8DF85388BF0B8B8B8AFE6CD9BF7C8FAF5EB958BE40B42CCF32805259A376FFE41993265C22F5DBA3417F765A084B8496703A6D7082ADE34227AC71DFA4BACC30BEE2F78B29EEFBEFB2EDE7A74144FE1C642D8C18876EDDAAD31408C4790AD77F80FCAEBC89825536D29C479E221F62AC75AEDB7705AD6154679A11F3CFACA41AB3CD61D24D953BECFC579F17764CFD57CABFC1CCBBE57AEAF1569993C5673B96DAF3CCF7A0E1AC9AFA5D02E2D457B8C836396259F43521CD93ABDFF4FBE087223EDA1BF04B99EE2DDC891544090D0D264322D17223CD422C0D9B13B70E0402582C29ED2A54B2B8947AEEADCB9B322CAA74F9F4E3367CEA4ECEC6C8B1D5B266D9CDF1CBB7E1F35853DE1E3C78F7FE4B3CF3EF359845BA85BB72EFDF7BFFF2D217E8429B241010113E3AE470530996E765846DDEEA89C2FD341F3F16E93F5DFE6209FB7A3AE78BA4D6BE363B6BA4E777B29EE3D693CD0C0F822C279B21E8E09F756845BE0B77B9B376F8E10B087BAA58EA7C9E11D3F4A91CA1D2987901A836DA131E57988596C779079574AD1DA86F2C23FB8815A2F853A7BCE53EC84782F599613C7780F76715EEF4A21CFC799203F875989FF1429A8391DB12AD75DD6BF5996D929B72F970F11FC5D39FCA69683631EA2FCA3BD1860EBD4FFD4F1E3A9D4A851732A5EFC36C5B639B37B9E8FA0927F9BC95486B48F28A5659FF3E5C4C48D54B56A3D71CC4AB9DB4DA6CA3E3A37BCCB6B32D5F44248437C17223A0B11BEC16C362B229CC5F581030768DAB4690E45B823D853CEF9B91CF74BB462A13FB5A45F3DE21C13CEE128EC09D77AA1B4C2F57DF2C927A59E78E289D957AE5CD96767C0815F44B88DE4D6E889F1555CFADB03A4D77087B6F92223EFA3CCCC43B9DBCCE6FF91BE61306858F44276CC8CD7D231532BEC1967312E44FDEAAB57AFB6277D62C6D98BCCFD67EACBC4611DD6F18FEC254E95CB7194E721E7F86DF650C750DE508217A5B86E20EBDD6577AC59B26E1E56F021CA1B8980C57A17BBBCECD9BE5F3E74B0C07F8EF2C7653EE5E0FBB0D8AE2EBF4B16E5C5A7F3320F07594F8AF75FE40387E51C22C8F1908606D83A757FCB961D69FCF837E9B3CF92292424C4CD7FD0DB500B3DEDA1363BD8BDFB604A4F3F22EC5589DCED66F319D27F86CFFCDB2223EB081BF9A3958D3CE6A1C30382BC10514D86A32822BC418306FC6651093FF10616E1BB77EFA68E1D3BD2AE5DB9A68DFBA3F8454BFA4D88F3E828A2019BC731E17A79C2F3B97FEAD6A549932685BFF6DA6B4BAF5FBFFE38EED56012E6DA1A964D9BB6D1C489EF8927D41FA87CF9DB69CA9458EAD6AD63AEE7272525995E7E7910A5A59DA7D9B3DFA59E3DBBD9D43962C45BB468D1328A8AAA44E3C68D74DAE064655DA53E7D86893FEF56AA59F32EDAB225812A562C6793A761C3363477EE647AE0817B72B77DFDF501718CB769E4C87EE23C3F10E779489EE728719E6D955C972EA58BE557C51F7A0F5DBF9E2DEEFD54F9DD7689327344999F4499B2A2CC6B225F1BF1BDEE92DFAF8614E0AA47DCBAA1B970E14FEADC7918EDDFFF133DFCF07DB46AD574BAFDF65BA547EA5E715DE2C575192DAECB1FE2BABC29AE4B3B37D71E0D8C3738EA98C9AF372B56AC982FEF8913276C1C0E1C9F58A64C997CF94E9D3AA58C102563C64BB468D162EDB56BD7A27512E3A932ED9262DA1A8E85DC26C53A8BE35576FB63E5676DBBBA9CB147266BD2292FE4C41A16CF492EEABAE8647BB6836358D867F7BDAD1BD4C54ECEC34BDBE63A342535F5A410078F09116E72B89FE352FBF41922ECCF66714FD510F667B5B8872AC8FF7E693B3B37D5C6CE8D1831D6CAC6BDEED4C6A976A89FB0439F0B3B745DD8930BCA9E0B172E0A5BD257D892EF852D7950D89205C296E4BD3134992A88E3AF17C71F2A8E7F411C7FA2387E67B15DBDC74B96BC4B8A6FF91862AA2CD64FCBE52851760D0D19328E0E1D3A4AD9D927C4B62AE27B7E4483078FA3BFFEBA4CCB97CFA47BEFBD9BDAB6ED4BFFFC9349B3668DA3D6AD9F51CAAB3672AE958D1C296D648D7C76913DE26673DE28C6AA8DFCB79D8D2C23F3B28D8C13DF698C9D8D84202F88984CA645969870F684FB22C22D70C8CA9A356B44BBDF50DCBBCA333E3FBCB367BCA1D1DFC76FA129EFBCF3CEE23FFEF8A338C7741BC9D0A1434B942B578EBD260851F1BBF0D63A6985A372EA7262E25A5ABA742E5DBB769E56AC584403078EB03184B3672FA02FBED842DF7DF739BDF1C6DB36E5274E7C9F6EBBAD8C103CC769EFDE1D74F2E469A7C78A8D9D4A0F3D743F65649CA0BE7D5FA437DF9C98CFE88E1AF52ABDFBEE1C9B6DD3A7CF57447862E226719EEF8BF33C21CE738E38CFD1B9A5478F9E46CF3FFFB4686C8F4911AE9298982CCA4C15658E8932B3449971F221F5179B4F478C1933939A34F997F86EDF2A9FE3C6CDB1D93F7BF672715D3E16D725495C97191E886E34305A614FF8D9B36793EC3B66F2EBCDB8B838C5905B080D0D158DFECB36E5FBF4E9C37322E4AE57AE5C59894F64116E418EA61259AC583116A97A86A93812B61CA36D192BDC15E7DC88E61D14DCA356F14808EFF9D3DE6DD8B04208CD6EB475EB270ECBC6C64E91F6E78CB03F31C2FED8DAB2D9B33F12FFE7ADD2CE8DCFDD3771E27461E36E15762055D8B84F848DFBCDC939B11D9A28ECD033C20E9DCE15E1BC6FCC98C9C286341675FC223E1B095B32359F2D983D7B9138FE0671FC9DE2F81349F540FF2EEDD41927F6435D9E3F7F196DDEBC4411E116860F7F8776EE5C4EFBF6250B913E5E3C4C4CA4A4A40F69C78E8F29266684031B7954D8C819C246C6CA63FE4F7E1E73FA7BE4D9C86F9CD8C8046923D758D94888EF02485BB3D9AC3CB9714C388B675F45B8B5185FB972A5F5E0210DFCA125FD22C4D91BFE9FFFFCA7130F5118111161F8F1962C59524634640371BF6A660CA9636AFAF08644BBE0668F4F5E2A23631C55121216D2BDF7D622D5235D5FF1A058D71B17F7A1103015A866CD3BE9FCF90B3675C7C7AF1486BB97B2A554A94861EC0738F516C5C72752F7EE1D95E5BE7DBB8B27EA6DF91E0A584CB3C7FBD429B5E16161FFC30F87A975EB26E23C672B5E1DA661C347C479FE9D5B6ED5AA4D3460C08BF95E492724CCB42A53CFAA0CB910CEEAF29A35DBC47753BD628307BF281AA964BBEB32495C97F2E2BA5415D7E54F2AE2612A3ADCCFB6F0643DDF7DF7DDC7CE4647E9D1A307DD7DF7DDB9EB376EDCA0FDFBF7F378E1CAFA9E3D7B8410D94957AF5E55D68B172FAE341EAFBEFA6ABEBAD8D3CEA3A90856E92CC61DA1A50323BF9A1DE9623FF7CDD9035B974793264F0911BE9ADE7A6B9210E45DE9D2A54B36E5E2E35708FBF382B2DCB76F0F617F926DFE9B717173A59DBBCBC6CEE5D938B3B07125858D1BE4D4C6AD5AB55ED8A197A51D325BD9924DA28E9ECAFAE0C1BD852D5997AF7C5CDC2C79FCEAE2F817353CC4E72D2F5B3693EEB8A392CD7616E6B56BDFA5D4979A7A9A264D1A99BB7EE9D25FB979131266081B5943DA7F8B5DD516BAB366CD76F1BDBADAD948B2B391E5C431ABD9D9482366FD0406FE07075A395EED63BB7D86EB1B316284F5A6578CBE207E094DE119334F9C3811A6573CA53B9A346952BC448912D5AF5FBF5E8D5CBF4A052A13E46726A9231A5866C2F362A82FF746CB6CFE33DF3693490DB33879F29468BC262B0D5366E6957C75B2C07676BCE3C74FD2CD3797222D3195E7CE9DA772E5EEB5F1603ACA3768504F9A3D7B094D9D3A86E6CE8D13EB31F23C4F8BF39C2ECE737BBEF3BC7C39C3615CA85A668628B34394C9D270BDF2F65DBA74597E7733454646581DD3FABAE8392C62A1B89FD3E5FD9CE0FDFDAC6DC64C4688747AF8E187C5EF7F59DE0797E995575E51E217BB74E92244C55FB9796FB9E5165AB46891D3615BD986F1D05A42F4AF12768C3B516E8799D2F5DE6085B789F23A7AEA6EEBF8EDDC9E3DDB68C992E54274BF221EAE56D9D99FBBEDEC8FB59D2BE95020AA36EE264DFFE7CB97FF76101AA386AC58EAB7B525EE8FAF45B8AAF7B4ED76555CE7E5B73824ECEDD3C99367A48DFCC4CA466ABBF6793692E3C9C3F37DAF52A54AE8DA8E019FFF83FF903AACAA27FFC12893C9F494D96C56EEB32143861872825C2F8FA422873664AFB8A15AD22F429CA7AD7736598F51346DDAF4DC9A356BB8017B0FF7BD66D88A759229438A9878524731C8F2CC7099BC38BC995AB478816262BAD0BC79EF291D82F2463B71EFB18888085706F2571B1FD7C7A952A5321D3DFA952853DCA597A777EFAE74CF3D8D68F8F0BE4A38CAD1A39F2B7B5AB4E821CEB3A338CF89F23CABE496E1D86D1E0A292C2CD4EEBBBD2CCA448B326FCB3277696C00CC54A6CCCD949191A93426FCA97696D22EE48B6803C3B1222FCA7451DECF89DAEE671521A2BB6811E14C8D1A3514033E6BD62C1B31FEDC73CFD11F7FFC919BEFD65B6FA5175E7881EAD5ABE7D6A120C4F88D162D5AAC14F75377326E9CF1A208ABD96E32F113D27AEDF786F6E9EDD9260D18D08B860E7DD3667B952A51C2967CABECD7E699B5B671371C3CE8E72FCB71DFAA1DB215C665CA94E6D9AA15B19D91F18F4DC74BCFEC8459C343BF279D45D9AEF6B2B291114E468F72BCECD846EA394422D0999276FF417E305EE1E63FD8D6324A0ADB63EBB03E3DE170437EF39994941B9167A896F44B68CAB163C7EA3EF3CC337EFD85A3A3A3B9116E847BDD6BD8B5CCD34473CC067B16D9ABE8C1EB2457B1E2CE63C853537F535EEB7283939CBC43D9C61D96B418FD6EDD3A504282FAC7397B368D060D7AC3415E75B95FBF1E1417B7CAC1F9DA2E172F1E462FBD142D1EECBA299F6AC349CAEBD5264D9E94E7B94B9EE705791E6D69DAB405F9CE3535F58C28D35096F95496B9A8EC2F59B28438E7F34E1B82CE9D5BD0DCB909CAF2AC591F0B21D7DC830603A3A9903A42488CBC9FF9875AEAEE7E7EE08107A2BFFFFEFB650909099A474779EBADB7E8E69BF386AA638F8AA883AE5CB96225CE2268C284099AEA1362BCC4860D1B8A8586867203D51266C9106EB1BA37FE94CE0717F786D9037B67A6952B93947870EB6DFDFAF514F627815CBFBDCB1F02A3DAB835CAF6B367CF59D938CA979FF34E9B36279FD8ECDCB9ADB0254BA52D59286C496B17C2D47B51AD2DBF6D1ED5AE36501C24C9C99FD9D855E736D2F2BDD846AE90DF6B99F85ECDDCD8436FC73F0706FD07BB5BFD079DD9E746561ACF680DE9F0B80556881F3E7C38924734F127F5EAD563EF6E5DDCDFBAC0EAB3ABF41AFD293FD9CA45686988B40B71332D58F03E356EDC9ACA96BD5B089843346C587F21881A92FB3171CD3465CA1825FEB154A96AD4A0414BF144FB9CD34660D4A8C174EAD4EFCA98B861615136E3E2DA1BDF01037AD09123BF289F79E739599C6727719E0F8AF3FC599C672F719ECAECBA141B3B9476EFFE56D45B43D45BDDAACC3BA24C5751A69E28734494E929CAB450F6AE5EFD01D5AAD5C469433161C2ABF4C9275F52E9D28F524ACA373469D250C250863E3D64C6C8FBF82CA96352DBDCCFEC093F71E244A2A73366B2F7913BFB5877DCE4D7A816787495B973E7DAEC77478B162D4A6ED9B2C50431EE17D88DFA92BC37F835C69AFCB6CEBDBDE337799C22222A527CFC2A4A4A5A6AB37FD4A8214ADF93AA551F14F74CB9DCF90EDC09F32953C64A1B5755DCA32D9CD838356F6CEC086187BE16F55712F597CFCD3361C2EBC296A4887BB0A6B0255F095BF2A61BD1AD55B46A992FC1757ED546761336F25161230F4B1BD94ADAC859C24636755A56B5917BC4F77ACCCA467A2AB23D99700818F81FB4D867F654ADB2FA0FD6B5D278869E849D6635544B9AFC71554B952A653E7BF62C7FFAED97FCF9E79FE9BEFBEEE38E4813715FBB659C0F658F8B74675EAF7C3D6E2BBDC6D53572164E3DC72177B7DFA84985D803F53B55AFAE68BBF1B89F157EA95CB9F29ABFFFFEFB75F6847B3B590F879E706F7E6B11CE71C08D1B37B61EA7D623B66DDBF6A7389FE23939391B29B8472929ACF7C6AF22DDA5AFADF354F41939F3A6B73632903376FA6712A1426A270BE27F3087A403F9C2850BBA8D96E208BBE166B3E4038221F825685B8D472BE5D75F7AF5EAD596EF370EF7BDA1DCE97DA3A24723E5E92B527F371C7AE4F5769BFBFC71719BF5308E85891A67CE9C19C5BDF17D993173EAD4A98A10B76941727268ECD8B15ED7D9BC79F35BEFBDF7DECB870E1DEA869F2920DC65BCADF3D521618420F7C65E068B207777DDB479C861278386DC280E23453863F7D6D2D0E1FEFC22C44B962C691662DCE44F31DEBE7D2BF4B41B0000200049444154FB8BE3C78FE72B098FB83E4FA83932D9DF33C75531EECAF8997C686CB4E60F446363FC6C72FACEE8993F7F4C4C2B1A3F5E89672F8A1E71B3839BF3F81D77DCB16EC99225FF6EDAB4A9D733678E1A354AE950C7C3185A608F380FE1DAA89177E1865BB66C49FBF9E79FB983130717C3231E94B6CE9DBDF345BC9B75D8EECFB786C124C8BD890BCF2326A66561B493C1FC1FBC2E3F8B39F86F2A62FCE2C58B867BC4ADC832F282F845888B8B75FDDCB973C57954017F111111F1A7BC78B1B8EF7DFA6364CBFB244426BE3B774931B05D5E63B37F1B9E400872B301F9FD199AE2F8FB56AB963B23646C11B99F73ACBC2A2647F733CF74F9C8238F7C1B1D1DBD92474BF1D433CE6386EFD8B1C346842B7FA4EC6C3A70E08032B678DBB66D3D16E1AD5BB72E919393C3D3C563F414FDEF8D6CAB36D18DAD33EBEC5CF0B4BC1E2325E925C8DDE73399EE24B3F9D7BCC714D35D7693970563988ABD9DAC5418ED64B0FD07AF4BFB1C6E25C0EDFF833F8AA408499ED1D84821CEF55B71B1C00BF18A152BFE76F0E0C11AFE14E2BB76EDE27FFE0DDCF31E9323FF10E156F74886FC1324CA3F45BAFE8D8FDEA2DCD3C6C73F5E9CC8C8FB2933F347F24F688A37DB0A25D6CA2944CBFDFCEDB7DFAEAE59B3E6B5AE5DBB26AD5EBD3A54AB679CC576B76EDDE8CF3FFFB4121EA6DC58F1F3E7CF13CF2ECCB1E25A3B6C6EDCB831AD7DFBF62CC2BB4184EB6EEBAE91FADAD9D2166AB075FEFCEFE8D55FC6482785EB7DD622DCF5F1FD15A6A277A80BF0812BF27F58D24A7CBBFA0F1EB408F17DFBF6E93E998F3542B392DD710BB6102F57AEDCE79F7EFA698D0E1D3AF8EDD75DB16205FF7B3EC77DEE31215284F39F81C702DC221B7FDDC6D6F5BD61739ED764AA4829296B69C890B174E8D051218C7EA3ACACABD4A7CF4865F64C9E9173CB968FC5C361B9DCBA468C98408B16ADA432656EA131635EA5DEBD5F138DC76FCAD8E066F349ABBAAB5A4D596F96F5BEA14C3EC133C46DD9B258D47BBBB277DDBA1DE21CDEA6B3672F289E519EA2D932262E7FE64DD51C4C9D380B2D26CFEF6765D8D50D428C77E8D4A95392D6D153DE7EFBEDDC31C4191EAAB056AD5A5C57EE1086FCC9B1E2B367CF765BDF575F7D75363A3A3A0222DC305B17A18FADF3D4DE79FBDFF3EE2DA13B1BE8AC2EB681EBD77F4483078F5366395EBE7C9632394FDBB67DE99F7F3269D6AC71D4BAF5B34ADE4D9B76D1C48973E8C0819FA87CF9B23465CAEBE2A1B4B5F480FF4AC113BEE24D39887183286125BEB5FC0759D3294272EDDAB514131363D88971FD76C72DD842FCCC993393C5D345CFECECEC103F4DEA93F5DFFFFEB736594D850A34C14F9E299437894FBAB1DE1C633C42F3E77F4C9B37C7E74EB31C1B3B9D1E7AE83E5AB66C96D8B78CDE7C730A2D5D3A5DD93771E26C6506BCF4F49F94C92DE6CF5FAED948C7C6CE94F5BE2FCA25887AA7897AA72AFB626246D28F3F6EA5AA552BE79661F16D32D594223C909D38713F6B85C578545454C7CE9D3B27AE5AB5AA982B31FECB2FBFD0071F7C603383E64D37DDA48C92C24361093BA86C636F3977E4ECD1A387CB21B86438CA2D10E105C1D6E9FDFF32FB98C7769F2B1BE84E9C0E1FFE0EEDDC99A04C94D6B469777AF0C17B2829699EB25EBF7E3B713FABCEC2C4C4CD8AFDE3593377EFFE865AB5EAA30871770F090543908320F90FF24C9C3358BB6EDFBE9D4E9F3E6DC8A43E1C96B279F366EB37A949467B040C67FFFEFDBF4644449CE00BE70F3EFCF0C3B5A2F1FA9D30BDBD567828071E4C9F0386DBC99BDDC386C96C5072779CFC0675D9B20F7245386F8B8F5F4DDDBBAB83F3F7EDDB55F10A59888F5F430307AAE383F34C732346F4D5EC658A8F5F2BEA6DA72CF7EDDB59F18C5BCEAB52A5F2B46A55B2658A5C0AFEE10E713F3B4318FBF5FFFCF3CFF32FBCF0C2359E76DE193D7BF6A4CCCCCCDC750E3DE131C3398E312121C166E428F69A737E0E657121C22DE1281B60A28A82ADF3C4EE9935DA45B35B1B685B9EF2D5B179F362AA5DFB4EE5AD1F4FB83369D2C8DCF54B97FECACD9F9030534E5D6FA6860D1F110FA47F53308403FA665F21C883EC3F785A8A76C576F22CC646C0F55EBD7AD522C2F718AD25FD36E77C5A5ADAA8D75E7B6D45B366CDC20CF68AA70F1932E45EF1F93EEE77CDBCA39F07C79FE3EA3ACEA34E2D6FB67ABABD40E5CA3D98BBCEA35758F61F3FFE1BDD7C7329AF1A0CB5DE7A76F5CAC7F60DF3A857AF513473661C2D5D3A999A357BD20B4F8C5E229C8A9208D7E97ECEC72E21B2DB74E9D265FDCA952B23EC3DE3CB972F57E62EB87EFD7AEE36F68277ECD85159E691529A366D2A04D07A6518430E4FE15EF973E6CC211E2AD11AC48417355BE78DE0F36C0415573690C34FF2D5A284E4A9FB55714D0ED66D3979F234BDF5D60CC521919999E5E6BC8261C4154F271D0241F21F9C2B92120F3573E64CE5CDA29EB1E2870E1DA269D3A6597BC317187D41FC26C4FFFAEBAF3511111163C585BB7FC48811861DA74D9B366BC593522D526763027EC71C807A5D1BD42A552AD3D1A39FCBE9E96D8D2C6FCBC9B9A10C35E7AA21C8C8C8CC67C4AB54A924EADDE5B05EF6187DF965226DDD9A423D7B8EA2B367BFA4C078C21DED47E3E225DB333232DA09712DF4F4FA084B074E7E8DD9BD7BF77C99172E5C68B3BE68D1229BB8437EAD3A6CD830E2BE3396D7ABEC099722FC05523B2C812267EB3C398636419E67038B3B11DDF6653D0FE768D1A217C5C444D3BC796F5364640921F06B68385F6F3CD246887084F21520D8839E225263F68AB3B363F7EEDDBA8CA0C2CE914E9D3ADD1058BC6A7BFCA125C3FC79F5D2D2D25A4F9C38F1E7060D1A94A85FBFBEEEF5EFDCB9739380E70C7F8AF286A302016F94F41EEACB5DE363BBBF5FBF17292E6EB5F269BFBF5BB7B69490B05E08A9F6422C9F573A1B59F27078C9B16327E8A69B2269E0C071F98ED3AF5F57516F92F2E9CCE057A952D166BD64C912CA71D44E9D7ABF4E8547C71F623C2B2BAB55BB76ED36AF59B346F18C57A850C166064D6770A88AAB7C569E7088F0022DC08D1A47DC7B87842B1BA84DF0BAB7B31CB6D2A44903C531919CFC1FD9E65FF083D0D65384C3595140E829D2B722953D72E408097BACBC6DF4458CB308EFD0A183F9F0E1C31611CE1D48FBF8434B86F9F9E2A58A46AC4FDBB66D3FDABB776F64B56AD574ABF8E79F7FFEB259B36675C5E248918EE03E0D44E364D2B9E1D1673CDD51A306D2E8D153A96AD5FA74E64C9A1CC5447DF5CA3DFB7BF4184EFDFB8F517AFACF9DFBB6481F2BE5B66D5B4A9D3B0F563A712E5C3859FCD177D818EA51A3FA897AA78B7A9FB4AAF77FCA3EEE94A90AF14A4A688AA5CCEAD533A956AD6674F9F23E9D45B837FB8197EC6231DEB163C7CDAB57AF2ED6AA55AB505F2B64111E1D1D5D5C86A3408417585B67A4D0F6DE36BAB281DAEA76FFF0BF60C13BD4B8B13AE9EBF0E12FD3B0613DE981075A6A14F5DE8A705F3B6BC246165052451A2CD232D6B13C7743C3860D954EF0DE84A970380A7BC2AD4438D3DF5F5AD214882B58AC58B1974B972E3D6BD3A64DA5F4F08CB3279C45B868C4E689D529B847FDDF3299CDA7FDD0F879B3DF73CF8FC9545D7CA1E33E361646341EC6887093A95EC06C4101E7D9E2C58B6F4C4A4A0AF7458CDB75CC444C7891B7757A0A733D4759326A6E067FDB51EF4438EC6450122312C7002A4E65EE7FC87D6E860F1FAEBCA97407871572C74C8E09170FA7D6BFED287F6AC9B0405CB9EBD7AF2FB970E14266A3468D16C4C6C6868E1C39B2A4971D38D3A3A3A337AC5BB7AE99BC7071B82F03D63E79F9CC177CD33D87871737A8B1D0434CEB2BC2814FECBA76ED5A74870E1DD66ED8B021BC79F3E61E8B7184A314465B6784BDD35A877176D17B01EE2FDBE9AB20878D2C80B0E6E39EC1EC842DCD31E3EFBDF79ED28993FBF0087DA80C11CBA29CC35638FC84C5374FD6236C2F6DDAB42927333333C4EA0FCBE12883FDAD25C30278015789466CEFD8B163178B2792FB162F5E7C738B162D4A682C9B357FFEFC0DAFBEFA6A0D21EAB94708C784231CA5C0375EDE94D37FC6CDACACC341E6BD81080F62B6B3186FD3A64D92A058EBD6AD8B6B2D888E99B077BE950FF4AC9B5AF29875C8EB6F1B0B0A18DC99722FA99E7165341516E43CD4ACABE16625D6437873C7CC3E81D0926101BE80A9376EDC78262D2DAD8368C8FA972851E2FE279F7CF242D7AE5D4B3DFCF0C3C5A3A2A22AF0D8BB7FFFFD77DAD9B367FF4A4949F9DFF2E5CB43BFFAEAAB5AA21C0F33C0C3D8F00C2CE89859E01B1E234579F04CF7ECFFB16FD1C0F8438C5FBF7EBD6D870E1D3608314E5AC4B85D380A44386C9D81A23CD082DC08BB6A948D05059454919E2375D6CD57446AEC81BE6501BE38905A322C482E629210D649191919515BB76EED20D2BFC4360EC8E2570E3C05F12D225D97FF984F490DA24FC5BD57501A2723461130EBB4CF5F825CAF5EFE10E141CA2E16E3D1D1D1EB8518BFDEA64D9B92CE32F2B4F59831B3300B71930E7518E190F0447C7B6A0783CD9901115E444992294A8A728B96AC20B5246BCA8B22F174B09FCBBC01D79261417611B917CC4C99409169B87C2DAB7F788A7F04792026AB00468AF1ECECEC8EEDDBB75FBB71E3C6CB2D5BB6BCD93E831C1D25023366C2DEE9578737C2DC9F31E3817466C03E16510A94960CC1EF05F46B7C8261AA672257533D3B9F1ADA593E57759117FB20C20B39DB85C86ED3B66D5BD3860D1B2E5BEFB0C484DFB871E3258870D83ADFED9E96A9EE3DB181668DDBB4D84F2DEB5AF23A5BF6251FEC2380100745A27132AA81F344987BD3F0686D743C6D44FC25CED1C80401BB84D86EDFA14307D3FAF5EB2F59443886282CCC42DC6851EFCD7968DDE7EB366FECA92F62DD17410EFB08828F305C02605C63E5EFFAFC317457410957014122C6D7DF7BEFBD977FFEF9E792428477810887BDF3AFEDD3C3066AB57D7ADA476F6D29EC23801007688882E458FE1C4125D804390816312EC4F7C643870EB1173C01221CB6CE7FC736AAC3A61182DC08D16DBD0EDB0820C441916A9C4C3AD6A5477E5FBDE4C1DA09098D4C01E117BB4F005BA7A3C00FD49B422304B93F6C2B0010E2A0C8345281AA5B0F515E5086E542230340E1B475DE1ECBACE3BE6018714ACFBC00408803344A7E3EA6AF0D4F4199F407043196F1C433702960EB82C729E189A8D6C316FA5374C3360208710002D0E819D959299826FD412353C0282B3F2FE25200E3C47E510D5181080710E20004C00006EB082ADE0A70ADE21C8D0C0045CBD6797B5C23FBCD18652F7D11E4B08D00421CA07172B2DDE46379A31A1D3D1B18BD1A10343400146C216ED2A91E7F38258C10DF10E00040888302D46819518F3F1B9D408CA8020028FCF64E6FDBE78D0DF4C4367A630FB5D83A887000210E40001B20A31A1D5FC4B79102DC7A1D8D0C00B075460AF3609CE407021C408803A043E360D2B12EBDCA04E2152C5EB716323A88545AA4451E9489909F59B87CB075C6897BBD479032C26642800300210E02D05005A26E7F4EF0E34D8383309402000BE8DD22D52375329EBD225513E99C48FF9282BC8EDCD7CE85D0AE203FCFE192C2D6F9E738FE12E5818A190700421C001BC2C38B9BB3B2B24C1111E141D81006C3043FC12BC0AF5CB97A527C54C65D9C8FB6226D11E93929C059781F9482BBA5CC932A52B448BD459A834B065B179C0F010565929FE015E0B09300421C043577DE19757EEFDEEFCB376EFC68001B1B234479E17FDDFAC927FF3D243E2EE02ECEC72AABE58376FB92AC964F535EF80980AD0B80DDD3C3FE79E298307A5BF079C06127819184E012005F295BB6F49E3D7BF64B03AA7772D630B8CBEBE97E7291DFD53677EB6637EB8165F1E28D97C5470AEE62AFE19093545C06D83AE3ED1E7950464B9D5AEC9D3B3B483E6C0B4E9B083B09FC0D3CE2C0674E9CF87DEA820589ADDF78A377B1B0B05027B94C5E7868B4122C13FC68C913540D4DEA962D5F3E283E3FC05D9C8F5891C6913A35FD4507E29B4354D8533E0C970AB64E9BBD33E2FF1F88F014ADB6CF173B1A54C04E024309C52500BE72F972C69952A522DB142B1656A97EFD070D38829ED33D07628480E06C685AB51AB6E6E8D193FC303E197771FEC657A48D22AD14E928A953D5CF15E973917E12A99948CB443AE4A69E185263CCE3099E73D83A43ED9ED672C138824AF0023B098C06A12940177EFFFDC28BB1B17333F7EE3D48817F4DEB6ABFB3F2EEB67B13B212BCAF5A5352BEDB989CBCBB15C1A3EB4A88A7C8D488D49151E2649A2FD278B9DD1D51F2F3342E296C9DF1E1297A84E8E9651F89DC87F00537B093C01FC0230EF4E2E2D5ABD7BED9BC39A54DC386FF17111555C1CB6ACC069683B7C7D2B83CF34CBFFBCD66F300B1BA07B7AE5B868AB499D430150B95481D5925DE4DD97F933AE2CA0C91D2712961EBF4B3799E96D7B3B3A627DB0BE6F083B09300421C14448EFFF3CF957DCB966D6E1B1616F6CFE38F3F5022242450135E183D6497FDF602D1D8A4F26BD6D1A3E73E2D1B9764DCB29AE0B1EA668BF4B748B78AD498D4F8F12F44DAA941C4B3109F05210E5BE71F916ED45C0B855F7CC34E824060C2250006503B343474568912E10F75EDFAFCE9F6ED9FADD8A8D1C315F41F7BD7C88E4AEAF66FBFFD895E7FFD039A346920D5AFFF40816B7078FC5B1E7A8B7BFD7387A39C9C9CDF497DCD7A08B7A947BC28D24091EACAF5E5220D26F733669E203546BC3A21461CB62E2002DD8898F1C225BE612701843828ACB068E1094F1A933A4321C65BF63FD9A48EEE9122D25A52678804FE03421CB60EC04E02E0140C5F088CE420E59F0CA520C18DE915ABF512E4DE030A0A273C9B665391BE17699148F5E5F6BD1AEE21C27D035B0700008EC0A8290038A7BEDD7A635C9222C93452872E63AFE7BFE4368EFBEEA1A1ACA527DF395C4600000010E20068A79FDD7A2F5C92227B1F3C22D262CAF36CF327C20F00000040880360104DECD61B8B540A97A5C8C1336B5AC6034F939F6D489DE807000000F01AC48803E0189E39B18CD57A0EA9B32BF2F6245C9E22C5489176933A290F77EA6A241FC89EC3A501000000210E80FEB4B45BB78C30D40642BCC8C1BFF711915E91027C3FA9336C66E0D200000080100740FFFFC5CB4E8438CFA6C8B1C11805A3E8F08648DB491D37DC1BFB9A8D4B080000C011881107203F3C5A4A0907DB5950B147B4192E5191E2165267D0FC8CD437255A3B6962C4140000002EC114F700E467AA48755C3CB8B2377C232E5391E15352A7B8BF41AA57FC6D52FB0BFC44AEC3536E25758AFB3F499DE21E00000070282C00002A1C4EE0AE131E87A794C6A52A52F0C3579C48AD488D19E77015BC19010000E0B3E80000E4D198544FA633AE4A11DE40A4645CAE2201FFDECF92DA59937FF7149186E1F707000000210E80BEBCE0667F31F9D90342ACC87096D45153D68AD45FA45F7049000000408803A03F9DDDECB784733D2BFF3F1811A3F0539DBCEB7069E9D4891176000000408803E00616D75A67CEE470850E22ADC2652B124C2335269CEF8F5D228DD520CE316A0A00000097A0B3260079D4F3307F6D5CB222010F5D584DA4B9224D24D5D3CDDBE0C8000000E013684800C8638A4CD6C48A344EA4F1721914BD87B38B2275B4DAB648A4A5A47AC8D14F00000080D7C0230E0000CE61EF77BA83EDA7491D4B1C0000008010070000033844EA90852F4A51CE6F11EBCBF53DB83C0000007C01A1290000E01CF6867358CA0C9196C96DDCF9B20FB91FC6109D3501000040880300800FA488F4904851D266B2B0D6322421862F040000E01284A6000080737888CA357299E3C25321AC0100004088030080F1F098E175282FCC04000000D00D84A6000080735880F3F085BB49ED9CC9DEF034B96F26391E510500000080100700001DF8547EDE42EA8CAA55E57AB60611CFA0B32600000008710000F09023E4FD444E96CE9A5771190100004088030080E7F06829BD49F5847328CA46524752010000007C029D350100C039D5443A2052232B51BE5EA4C9B8340000007C051E710000700ECFA0B95DA4EE56DBCA4A713E9DD48E9C0000008057C0230E0000CE497720B6799D67D574E7C808979F18771C0000008438000078489248F5456A4CAA279CA92BD221723F1A0A464D010000E01284A600008073FA4921FE99837D3CEB267BBBBB88B417970A00000084380000E847AC4CA565B2B69D5172F9082E1300000008710000300E9EC0E7B4D5FA2FB82400000020C40100C018D80BBE50A4B6D25E66881427D24842274C00000010E2000060183C5E783552E3C0F791DA0173A014E7DDDD944567CD22C2E0C1033A9C3C79AAFF6FBFFDF6F09123476FC9CAC2335A81114161A174F7DDD5FEBEF3CECA3F56AA547EFEC2856B96E1AA0008710000080E380E9CC70B4F92EBA9221D14E9B086B29629EEA1CA0A292D5BB67CFCF4E9DF566DDA947C47EFDEBD4CC3860DA1FAF51FA38888088D35985DACEBB1ACF73623EBD6E3B89E7F0F7E683A78F0C84D7BF67CF7447CFCC627EEBFFFEE4977DD55B5FB860DBB5270870308710000082C3C9DFD6322ADB2B39B880F2FE2B46FDFA6F79E3D5F2D1835EA8D9041830650589875736AD228BE4D76DB4C76CB967D261779CC2E8EE56EBFAB7CDED4ED4D3DE4637EDF8E1F11112E1E9E1E54D28811313467CE8AA80913E67F1A1DDD64C4DAB59FCCC09D0E20C401002070B0379BE3C3B9A3E63F72DB33A47AC67BCBED71B84C454F841F38F0FDFC8D1BD787B007DCBDF8262722DD9DE8762628DD094E57FBF512D8BEA0777DFA316850576ADCF89190162D06BCF7FCF30DAE6DDDBA672EEE78602498D00700009CC3310629A44EE65355A407A5F8E690956E22F5C0252A72D4FEFCF33D1FAE58B13CD4B108376948F679ED45BAC989707727EC3DDD6F32F0329902FC33797FFC3A756AD0962D1F867CFDF5A119A54B977C10B73C301278C40100C0398B64F20674D62C84DC7967F53583070F2AE65C84DB6E4B4E4EA6F8F88F293D3D9D6AD4A8413D7ABC4479653DF570EB158AE24B59A38EE14BA88BFEE7CE623C367640B119333E4E4C4FFFA736EE7C6014F08803008067F090865A46564067CDC247DDAB57AFD5E49870C722DC36BDF9E66821E6C6D3FEFDFB69DFBE7DF4C30F3F886D6FD29123471D942717DBC88B7C7A78C2F5CEA777593D70FE1B0C1AD4858A150BAB4EEAECBA0018023CE20000E09CCE227187AD0A76DB9371698A1E5151957ABDF24ADFE2B61D331D0972A275EBD6D192254B282D2D2D77CF575F7D45A1A1A1D4AF5F7F4A49F98CF277DC34DED30B3CA377EFF6C5DF7FFFE397CF9EBDB817570318013CE20000E09CD7451A2FD25322CD11A921A99D33DFC5A5297A98CDA6A60D1AFCCB81F8CEBF3E69D2241B116EE1C68D1BB47BF7EE007D03137E440F69D0E0FF282727E7395C096014F0880300807322A4F0E64F9EC8678FB49B43E432284208615DDD36BEDB7E041493755E8A8C8CA4F2E5CB2B9FD9D7B329B264A4B2FCD75F7F05EA51023FA287D4AD5B8B2E5C48AF822B0120C40100C0FFF0E43D75489D55B386482F8AD488D45154B4DAD76C5CC6C24176767698E3C97AAC45B8BA7CFDFA75251EBC77EFDE14116E5BA669B3A6E47A321F4F853304B6614FE211E1EC1147F40080100700800030CA4A480F166936A9DEF1EE1ACA62D49442497ED1ED480C376BD68C7EFDF5D77CA5BFDDF72D952C59D20FE2DBAC8370F7C7838039087E4F3CD880C081A73C0000704EAA48A7E53287A23C24D23DA47AC8DD018F78A116E3AE96CD1417B784AE645EA1A79E7A8AB2AEE60D9CD3A74F1F7AF6D9675CD4E1BADEC00B62A38E61D6795B308A7E009C3714000000544A89D4412EA7CBC44AEA9C14E535E47A2A2E555117E3F93DE319197FD38C99B3684BF256AA71570DFA68C14736A1297BF7EEA5FA8F3FAEC4890F1AD49FA2A2A2DC086E5FC5B71E9E7123857D613D3E00DA80471C00006CE1F8EF1E3271A7CC71224D1669A9DCC79D366370998AB20077BC9C9292422D5BB6A6FFFBBF4768C7CE5D74E6F73374DB6DB7D9943E9F765E99D8A7F153CFD00B2F74A3A54B3F96F5B8F37A7BE3412737225FAB80351B780D0BCA6F0D8031C0230E0000B6A4923A5CA13306E312418CDBC786EFDAF51F5AB86809AD5EB3964A962AA56C7B67E2241A307000B56EDD9A6ADE5D5311E6CB962DA3051F2DA4AAD5AAD1D6ED3B68CC9B6F52565626F5EFFF0AB90F79D122B8BD1192DE886FBDC346F410BF085B01050F78C40100C0960A3AD48159358B841857C5DB9E3D5FD2ECD91F8A3437578433751F7A88121357D3EDB797A383DF1FA462E1E194B4769D22C2199E1868CAD4A9F4CDB7DFD11A21E0BD17E1DE78C3CD5E7EE740085DB31FEA0300421C0000828179225593829CA7B6AE2DB787C9ED1691BDDE8568C788294540805BD6DF7863342D5CB4D846845B2879D34DD42E3A9A86FE7B3875EAF4029512EBF6CC9CF501CD9D3BCF61DDFA0A7277A2542FC1EBE9B18DF2AE0310FC20340500006C9925D2FF48F5661F9276F29014E1A5A430BF28D21108EDA22EC85542C3C21C8A70CD0D71589807E2D85B41AE578CB9B7751A25A6CD7ED8060084380000F88B14918AD96DFB91D4B8F18B528C9785088700773665FCF265CBE8C5EEEE879ACF9FCFDD243F7A8B706F04B95EC29874CE57543B9482820E42530000C03DF74B11CE6490DAA113F1DF455E889B1D0AB68484E59A6A729ECFECE238DE84AF7893D75D395FBDE1661DF2E925D821BA0184380000040B1C033ED46E1B7BC1DF90DB23708920C69D89707DEA250DA25BABB0F6765844234752211FF2F95B409BFD5E10142D109A020000B67007CD07ED84F97F498D09676FF867223524D7336696969FE9B89C8551849B6CD68FFFFA2B3DDFBC69EE96F3696936EBCEB0CEC7756817B35A04B9D983F2BE0872BDBDE1A453DDA4D3368F7956A4C572F9944863448A97EBDD447A4FA48A4E6EA660BEE1CF8A3442A404D80008710000300A8EFD9E20C5F76C91FE90A29C474149247566CD06A47AC7DF81102FCA62DC82894E9DFA9F4EF5792A28BD0951F13686DCD778736FAEAD3FEAD15D90B308EF23D20E919E9076C222C41788745701BDE9EB8AF42B84388438000018C945D958468B348C54EF16CFB4F29C95A86E21D22FB854455D849BF4106D1A05A0DE9D38BD15E17A0860BD63CCFD2EB4DD515EA45D72993DE23DACF6951429CD6AFDAA83136B4CEAC84D75A44663A7C04291DA89744CDA9FB3327F7B99973DECA15637A5AB3296632C91E7CA13942DB53A07F6D8F7964E87F156DBD3E4F90308710000309424525F215F203514A5A34807ADF6D721CCAE091C8A39938FE53D1590460C73E8EBB23FC4B511425B37C13E538A592D5C73B0AD9F48ADA4886762453A205277B96F92483DE5BE38523B929FB4ABC3551992F6EB4929AC775B09F1D1A4BE05E4377AA564EF2CB4200000181549444154D9824894486D456A44AA27BF82FC3EDCC1FE9CB4E75F8AB44A3E70041474D60400005BD8583717E91ED9C8EDB1DAC70DD402C2D085455C809BDDECD392DC9577754C7723A7044A84FBCBDB6DA460F7D95BCE616B7FC9E5A5520032EC7D3E6A97F7AA83F2DDAD4438C31EF56572F92352BDDC167E17A933E5EF40EEAA0C1323D21952BDE5E5ECCACD95CB190E1E28F8FC6F0FE23F278BEF9D2121211C42C3A1851D44AA61F51BF075AA26F34D93D799FBFCBC18C89386471C00001CE3486C7378CA4C0F6C6B362E636116E4CE307959CE17B16AD418E4DE2CFB22D2F514D7010B5FE1908E5852BDD23C03EF73D29EF4B7CBE7C8236E6F33D89B7BDE6AFD869DF0E498741ECD893DDEDB3594B1886C47DC29D26517DF6BA6AC37D83A985633994CF3CD66B3D2F3392727C793B28D657A455E43BF871D4288030080FE44C9CFD3B814855D809B741071660FF6F932028A3F05B99E639A07D21BEE93209F43795E6516C79F8B34DF8D10B7E737916A91E3790B7824A77F89F43CA9DEF78A1ACAB882F373A4843325FB2AD97AD08381CEA1A1A11FDEB871A34CAEB00D0BA3679F7D96A2A3A3A97EFDFA54A142052A5BB62CA5A7A7D3B973E7E8E0C183B476ED5A4A4E4EA6ACACDC4BD4403E38F1C3D2727F7E010871000000C023D16CD24BA8192C1A0329C8BDF96E7A0AF280896FEE24F9A65CE6BE26FBE5729A14C7D654D7501F0BF7183B01EF48AC7B5AC61109F29C9749513FDA6E3F87EB5D08A23F634B93C9B45C88F0508B001F3A74280D1C3890AA55AB962F73E9D2A59554BB766DEADCB9B322CAA74F9F4E3367CEA4EC6CE5454429F9DD39846591BFBE0462C4010000008FC5B227B1DFAECB67675FA73A751EA36BD7AE3AC9635BD6642A9DBB663295A1821143EEB9603799EE0AB020F70AEE5362E9DCCD232FF5F0F0C9C49EC922DD416A87CC6C273FE616B2ED8CE9AA8C2B38BE9D3BA767C8EF911CC47FC2CE42846F309BCD8A0867717DE0C0019A366D9A4311EE08F694737E2E57A74E1DFB8729BFC58D87C2A002E092C632F12BC5145C0EA011EEA9DF5636C81B71390A0DB1B1B16F38D8EC5BC8ECFCF98BE93FFFF9822222C2E9D1471F76ABD5C68F7F579CC8EBF2845E7791D7BD173C32B20A8D1E3D2C40223C6F3932F21E711E036DB6C5C6BEEAB178D757909BE5F5561CCBE335FE9CDF8BF4BE48A3A418FEC36ADF485263BAFFB1FC94F63FAD933AFF436A7CF6DB7679C6CBC4FB7EF1A08CB363729CC64A524759F940D669D9CF9D4DFBCB7D81A65A6868E8A69C9C9C485E69D0A001EDD8B143B300B7A75CB972D4B56B57DABF7F3F1D3F7EDCB2B909A9F1FD1721C40180100710E2403F78A6C35BA5C8C8F1B06CAC2A7CF5EBABC61DCB5E7CB12F6DDAB48A860C7983060FEE4326936DFD23468CA54E9D7AD2F2E5ABE9F6DBCBD29A351B7305B8C974AB587E4D2EDF26B75B3CCA65E53E33AD5B974C4D9A74A4D75E1B4FE3C6B1901F29F697535EC98F1F3F4D597724DC1B366C43F5EA3D48E5CB97CDDDFAF5D7DF51972E03A86CD95BE9A5975EA58103DFA48F3E4A50F23CF0C03D74E9D25F141DDD977AF61C4E6FBD355DD43D4C29B769D32722FFBF45FEB122FF0A99BFB6388FEAF23C6689BC43F21E6F4C35A41837D3850B7F52EBD6AF886B348176EEFC929E7FBE11952C1921F3D5A2A79E7A8C9E79264688F91954B1E2EDF4D043F7E826C83D14E2161C4DF8C51EEA75224D2F80FF1BFEBFF08FF163A04F44FC3F92C4FFE65E5E664F388B708E01F785888808717FB5A62D5BB6D0F9F34A3FD7E2D28E2F35FAFB20461C0000F4C7325C56062E45D031417EF2E8109B488D8BDDE3D96FE5CE3BAB7DD494952B9384A87C8EEAD4A94DAD5A35A365CB12A9478F2EB9FB274E7C8F6EBBAD0CA5A7A75246C63F347FFE5227E76076B93D266630FDF8E31754B56A54DE1E739A1035E5954F676275D4A857E9DD77E75042C2DCDC7DD3A7CFA79123FB5362E2465ABAF47DBAF7DE9AB47BF7D7E2FC63A85BB776420CBF2BBED353949CBC8442424272CB2526268BFCEF89FC3544FE6F45FE5E227F1B71FCE3E23CEE149FBF3ABD4E63C6CC100F12FFA24F3F8D17E7F391789898451F7E189BBB7FF6EC65F4C517CBE89F7FAE88878717C543403B37D73E2093FF2450C19D99B262909C475BB3D9FC8C2260C3C2C443E91A9F45B8058E1F5FB972A578887BC81233CE1D383944C5D0CE9B8811070000FDB1B40C7FE052042D37CB46769B48A9A47ABE9A51FE31991D08332D6112DA62C8274F7E9F860D1BA02C0F1BD65F88CC9936F9E3E357D1C081BDD5A7BB52913462C4400D62317F1C78A54A1568D5AAF5949575953C89137FFEF9A7E9C0814374EAD4EFCAFAC993A7E9871F0E53EBD6CF09713E4711E1AAE7FC51FAEBAFBF95E555AB36D18001DD6D44B8A242136629229C69D8F01199DFDDC38ABABC66CD36711DD4B0DDC1835FA4152B926DF6C7C54DA1CA95CB53CD9AD5E8FCF93F3C10D27E9F79B3A8C36FA4F86DA1B78EE0DC3F0077CCB48BEDF619AE6FC48811D69B5E31FA82C0230E0000C631CE6A99C71176369C9865C63767709CA22B8F2D0F938831CBBDE7365247998891D7996757E590A264E7D7D577B1B669D3367AECB187A597DA2C3EEF1002F5715AB76E33B56FDF52C973FC782ADD7C7329D21EFFED78FB860DF1D4ABD7509A3973012D5D3A8B9A357B9AB48EAA3268504F9A3D7B314D9D3A86E6CE8D13EB31CAF693274F29A127EBD76FA7CCCC2BB9F92F5FCEC827C2D5FCA745FEF745FE1D227F968607813C38DC851F4498C8C812F27879794B952AA149D03BBF5610E47EC2F2468AE3E43790EA6DD6FA462ACA64323D65369B156FF89021430C3941AE97475291431BB257BC9A7C5887100700800242B613311E089C3D003813FFAE1E0A78FB550FCBB87BC8F075BFDE94B212E5FC00B44BA478CAED23E2EBD4F679BCFDF634DABFFF202D59621BADF0EDB70772853877E0CCC9B961256CDD896F7599C358ACB7D5AE5D83BEFC3299B66EDD453D7B0EA1B3677FD024C279B977EFAE74CF3D8D68F8F0BE9498B8898E1EFD42D9DEA2450F8A89E944F3E64D12E238824CA62ACAF6DB6FBF5579B51F16166A534F8B162F8BFC1D44FE098A98E67014AD9EF932656E11DF295311DCFCC9E5B53F20F93ACA0A308092A40E95C8896722E530B115F27FE6CC61D1D6324A4AB366CD282A2ACA9013E3D1545AB66C49494949964D3C43E77B10E20000507058E4C0C6F2A803CEC21E787B0517F595A5BCB8734744B9B0E7AEEAAD5D08AEB53BA1CE0F45DE4EACC4D7BDB34C0A29297BA871E3061AC49F6B3EFFFC4B255C64DFBE3FF2D5F5FCF39D69D7AE147AF6D946D4AD5B074A4848A2EEDD3B09F19C461327BEEF543C737DC78EFD4A37DD548A060E7CDDA1C8AC52A5B2CD7AC992914ABD152B96772A488B172F462FBD144D4D9B76139F1DC4C34171657B6AEA696AD2A4A1B29E9CFCA9B22D2DEDA238E7B6346DDA021A356A804D5D6AFE06CAC34572F27F72F373A7CD92254B88F3382FCEA39CC3F3E8DCB905CD9DBB9C5E7FBD0FCD9A154F2FBCD0DC47410D411E44DC22527799F8554722397E23D5C8B2C093F51809D76F25C41B4188030040C18285E13B41722E159C3C003813FFAE1E0A9CD515EEA28CBB870C3DF65773730D6AE875316363A70A31FE2F9FEB79EBADC9F4FEFBEF90E3E1095FA77FFF7BAC22C4A74C194B3D7A0CA2FEFD4708C17ABB10A353455A9C2B1643434373EBD8B66D9510AC7D156FF8C2851C32B235379FC9A45E4216E24B97CECC2DB37AF54754AB5603BA7CF9984B413A60400F9A3C798E38C6B2DC3D0B164C160F259D94E5E1C3FBD0B061BDE981079AD0AFBF7E419D3A0DA4B163A7D38D1B37C86C3E21F34F14F9BBC8FCBD44FE9745FEE78518FF5A9CC76C711ECF89F338E8F07A4D983044D439449CC3027AE491FB69D52AFB0712B306C1EDEBAC9FC00FF0AB8E1899D853BE5DA438523DE5752D99EAD5AB67E849D4AD5BD766D5C86399F09B03E0BADD2535B460BC5C06000417EE843A3B9CACDF617FE6CBC1366D5A4EAD5A3535A009357B9C8763AE9F7CB2B5F8DCAFB13EB30FEB9EC5A4076E59DFBCA9A967A87A75254C683CFE6ABAE04BA81E0F37AAC4685DB87041B7D1521C919E9E4E65CA94B1AC66C907044380471C00004041861BC95437797EF1B63D2635469C83B8D93377254F846B15CFC608F38888282A5EBC38CD9B375543192304B81EF98C58D6376F5CDC663D0424D087DC8E12468A70868732B47BD8370C08710000004519565ED6AEED0C29BA13A5084FF744206BF3929BBD3C4DABA78FAC5356DBBD1D13BBB00A72FDC47B4C4C2B1A3F7E012FC223AE0F5A1E68AECBCF6276DB733DE2172F5E34DC236EF7B00F210E000000188089F2862CDC426A07B12CEDBA5D0F91ED4979B387FB8C14E4C128CE3D8915772FDEAB55CB9DC726167F154385F87529B4C3AD04B8FD1B299ED553E9EF71EEDC39438538D76F85A1D3DC4388030000288A70239F42794313A6EB2796F5424F51EEAB20D7CF5B1E19598732330F91B1A129EE45B66FD717E8C01529BE4B5A896F576FA40E5A84F8BE7DFB749FCCC79A83076D3A0D1F841007000000F4612CA931E5EC01F7E295B32B7166D2B12EDF84B7C954895252D6D2902163E9D0A1A3949DFD9B32AB669F3E239409786AD6BC93B66CF93877B8C0112326D0A2452B95F1BAC78C79957AF77E8DCCE6DF481D71A5AA583E997B1C93A99A584F55D678D2933E7DDE10757E22EAAC2EEA5C2CEB245AB76EBB38FEDB74F6EC05397ACA2FA26C0D797E35C4FAFFC8786FB95EA21E1840092BF1ADE58DD4E7A48EE94D6BD7AEA5989818C34E8CEBB73B2E8438000000A003060E2B69F6635DEE45F9FCF91FD3E6CDF174C71D95946DB1B1EFD1430FD5A165CB3E50F6BDF9E6645ABAF47D9A387136DD765B194A4FFF4919FA70FEFCE51A04AABA1E1B3B53D4799FA8F37D512E41D4F99EA8F35D655F4CCC48FAF1C76D54B56AA5BC52427C9B4C771700116E36E037055678F3468A67E29CC1DA75FBF6ED74FAF4694326F5E1B094CD9B375BC7A02519792142702F000000009E08647F2477C775BF6FD9B259428457CCDD161FBF86BA776FAFACF7EDDB4DF18C5BB60F1CD84359E669E4478CE8EB56805B888F5F2BEA6CA72CF7EDDB4599BEDE42A54AE569D5AACD8A27DEBDE8353B5886082F84F01B299EB8876FCC76525C6B0D0B3B2D45BB3273EBAC59B30C3941AEF7EAD5AB1611BE870C9CDE9E81471C000000F04888332603EBF6255FDEBEB0B0309B6DE7CE5DA072E5F2E626B14C0674FCF86F74F3CD25C9B33870759B5A673DBB3AD57D1B36CCA35EBD46D1CC9971B474E9146AD6EC49F2DD0BEE4A28438417007C7D233557A4677961E6CC99D4A3470F5D63C50F1D3A44D3A64DB3F6862F30FA82408803000000868966A38FA13D6E9C67D53C7A34459962DE3A0FAFE7E4E450484808B9F380676464DA6CAF52A592A87397559D791EEDDAB5EFA22FBF4CA4AD5B53A867CF37E8ECD92F7D14E19E8A7388F042087BD053446ACC5EF18E1D3BD2EEDDBB75194185872CECD4A9D30D81E56992BDE1AB8CFE42084D010000003C12C79E8494785387D6F014B38B7DF9CBF5EBF722C5C5ADC997AF5BB7B69490B05E593F7BF63C0D1AF4968D20E5109363C74E887D69F4D24BFFB6ABB3ABA873AD8BF361B15ED1E66C4A962CA11CC73E9FF7E2594F110E0A003D490E2978E4C8116AD7AE9D32AEB8AF22BC43870EE6C3870F5B44387720ED2352B6D15F061E71000000C023211EC871C4B5E4732C4C478D1A40A3474FA3AA551FA73367D2E44826A93465CAEBD4A3C770EADF7F0C952F5F96E6CE7D5BA48F734B6EDBB6943A771EAC74E45CB870924D1CF8A851FD449DD3459D4F5AD5F93F651F77CA548578255ABA7472EE79AC5E3D8B6AD56A4A972FEF77F17DCC41B00C82945491068BB48C75EC9E3D7BA861C386B466CD1AAFC254381C853DE156229CE9CF3ADF1F5FC684DF130097C4923A01C178C2840E001479156E369F0E80F0F7368FD9EB6D265375F1658F7B502E306392FB43849B4CF5A097829318911692742A739F88A14387D2F0E1C3A942850A6E0BF3E828DC319363C2C503A4F5EF3B4AA429FEFA12F08803000000BA0AE34079CCCD3A6C57B7858717F740B81B2DC8FD2DCE4101218ED431C7E789549A63C6DF7BEF3DA51367B366CD283A3A9AEAD5ABA788728E21E7F01316DF3C59CFC68D1B69D3A64D3999999921567F580E47192CEBF51B10E200000080DFC5BAAFE5BD1D675C9B473C2BEB30691935C5FBFD7A0A6D5F8437047801873B53EE25D533AE8CA6C2823C393959496EB0EE27C91D333926FC88BFBF008438000000E037916DD4F1CC5E94376BC8EB6B1E6F3DE47A7AC121C20B39A9223D47EAAC9BAF88D4D8037DCB027CB1483C8B5576204E1E421C000000D04D189B74A8C397B29E86AE6815F08112E410E140334932454951FE2F9138C09F03C623480D63E1E1550E923A6D7D12193C590F84380000001054425DEF3ABC11E6BE84AD7893C7ECC77DDE4C04040A19DCA37AA64C410F8438000000E057A16DE471F5EBB0A9AF00F74458FB2AB43D290300843800000050C085B849E7FA7C2DAB47884A41F1907B9B0F000871000000A0100B747FD46BEC082AFE17E07A0B72087000210E0000004070FBE5D84675D8D44390EB15CE022F388010070000008AB010371954AF9EE5FC15331EEC9D38018010070000000AA9280F86E30463CCB82F13FD782BBA21C001843800000000F11DB07308F4B0867A0A706F05390010E2000000003054ECFB22CCFD1DA2E28B0077551704388010070000002092037E5C6F42548C16E446AE4384030871000000A0880B71934EF5E829D0F512E5C128C821C0018438000000000C1386464F77AF87F886000700421C000000289062DB88E317C4497EBC590700421C00000028A242DC6450BD7A97351BB8DD08416EBF0D021C4088030000004037016DC4310A7ACC380438801007000000801DA1A1A1D7B3B2B28A45448417A007806098E4A7E00AF0ACAC6B1412129223C01F001842082E01000000E09EF0F0E2DFEFDDFBBD9F44B6BBA4B58CAB3C5AF619B5CD591E33059317FCE0C1A3E2772FF613EE7E00210E0000000490CCCC2BDBB76FDF9DA64D28FB92BC11E87A89724F043991FE823CB8F8E493BD17AF5CB9BA03773F80100700000002CBDA79F356856567677B29A6F516E8BEE47327BCC9C3ED5A44BAFD362D0F1F8165CE9CD5D7F977C7AD0F20C401000080C072F0F2E58C6F274DFAE894FBAC9E78BABD15EDDE1CD7682FB9966DC12DBE2D4C9AB4F8B78B17D37F168B7B71EB03087100000020F00C9B30615E893D7BF6A707263C85C8B710156F44B9BBED9E86AD043F3FFCF04BE6B8711F459ACDE6A1B8E5819184E21200E092C6327D2E520A2E0700459E8B3939E6032B576E6DDEA851BDECAA552B46FA3E96B8D90F65F59CE0C793ED056FF8C1EFBF3F76E9F1C7632E5FBB76BD8F58DD835B1E40880300210E00081E8E6767DFF8313E7E53C78B172FED6BD2E489AA212126030E6336A89C59E77D8567F6CB1123667ED5B3E7F8D2D7AF67F715ABC9B8D581D1987009007049AC48E3441A2F970100C0426D91668487177FA075EBC63F75EBD6E2F6A64D9FA81D11111E61EC61FD3BC14F6AEAEF1417B79962625A51B56A950A95F8CECABA9AF5C517077E5EB972C71F89899F54BF72E56AAAD83C4CA443B8BD0184380010E20080E0A7AE48D1A4BE3DAB2752042E49818187C03948EA1B4F1E1D051D33815FC1CC9A000000806F1C94A9B0124B7048006008183505000000000000087100000000000020C40100000000000010E200000000000040880300000000000020C4010000000000801007000000000000408803000000000000210E000000000000801007000000000000421C000000000000087100000000000000843800000000000010E200000000000000087100000000000020C40100000000000010E200000000000040880300000000000020C4010000000000801007000000000000421C00000000000000210E000000000000843800000000000000421C000000000000087100000000000000843800000000000010E200000000000000087100000000000020C4010000000000801007000000000000408803000000000000210E000000000000801007000000000000421C00000000000000210E000000000000843800000000000000421C000000000000087100000000000020C4010000000000007E200C9700004D8CC3250000C0FE010020C4014063040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000D0FF03E5E49926FA8FAE150000000049454E44AE426082, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203ed-c278-11e9-8c70-2e15a8856301', 1, 'Helpdesk.png', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x89504E470D0A1A0A0000000D49484452000001FB0000010408060000002F4F9D1E000000206348524D00007A26000080840000FA00000080E8000075300000EA6000003A98000017709CBA513C0000000467414D410000B18E7CFB5193000000017352474200AECE1CE900000006624B474400FF00FF00FFA0BDA793000000097048597300000EC400000EC401952B0E1B000020004944415478DAED9D07781455D7C7CF26014213D1D05103365E7A5510104293127A28D2114191BC802F200AA2A0225511E90A0A128AF4121005247E80740582101121428050449010422099EF9EBBB3C926D9BE339B4DF6FF7B9EF3ECECCC9D3B776766E77FCFBD77CE250200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002FC6805300ACA1284AE0DCB99F871F3870A8574CCCEFCF1E3F1E1D9894948413E3A5F8FBFB2BC1C1A5FF2E5932E8607CFC8DE97FFEF9D72E9C150000C41E5865D1A22F5E8F8858F6C9B973B105060C78851A34A84F75EB3E4F8181818E54136C7CB7B7ECCA762DF675343F2DF3D0367FAE881D3D1A437BF6FC420B17AE4DBE7DFBCEA94B97AEF6129B4EE08E0600620F4006162EFC62C3BBEFBED77EF4E851141EFE060504043870BB2876D67983C86B21AE7A0BBA76E5993D7B398D1BF7F9DDE4E4E49713139337E2CE06C077F1C7290099857EF2E429EDBFFD7605858575263F3F7F55E4EDD50B0D16D239B2ECCAF6CCEB1CADB33A9AAFDEF5698347CAF8DC73552834B4519EE5CBBF6B939898142D569DC61D0E00C41EF838DC742F3CFA112CF4DC649F5580ED993322EFA8506B217E5AA4376898BFDEA497A778F147A875EB86F9162FDED0FAC183942D62D555DCE90040EC818FC283F1468C18B173C08057F2B0476F5D988D1619B985C68E1D47F3E6CDA7B56BD751810205A842850A5644DC1DCF5B0B4FDA5BBC7CBD5B0C2CC3829F2F5F9EC45DBB0ED54C4D4D5D82BB1D00883DF05184200CDFB2E5BB3611114BD4A67B4B1EBDF1FBF8F11368FBF61D74E1C2053A76EC18FDFBEFBF74E4C8113A7FFE02356912E284276F705278BDD783F6AEBCB2F2C20BD5F3CF9C1991E7EEDD7BFBC4D738DCF10040EC810F1214546C6EB3664D4B366EDCC88208A77F464646D2860D1B68EBD6AD52EC939393E9D6AD5B74F9F26521F6E7A954A95254B972652F115E8C3F35E7E2C5ABA70F1E8C2E2016B7E36C00E05BF8E11400262626E6597EBDCE9E982E59B2447AF30F1E3CC892222E2E8EFAF6EDE761C1F55641F7BE728585352F233E1AE36E07C0F708C029000C07CC310ECAB38622052C3E3E9E2E5DBA44254A94907DF457E2AF5060FE405252150A6D1B2A2B0359F7D313C54BCFA8F795EBB9E72A97121FC570B70300B1073E0A0764C91A3047C9E4A12A46917FB6022D5FBE9CCA972F9F251FA3D86787D0290EAEF35D0203F3E13F0F808F82667C604724153323EAD7AFAFAC1414295224CB5E3B76EEA05AB56ADAC8C791753949BC152FCD0B000020F6C06191CF2ACAA1A16DA852E58AD4A469134ABA97314EFEA851A368F1E2AFECE4A5B7E0B9732C25875E2B000080D8038D04FFE6CD7F68D2A4C9F4DB6F2769D1C24514982F63B3FF638F3D26B64F91A3F49DA940E82FCA5AA4577290F0A222000080D803A7055FA1AFBF5E4C9D3B77A5A79FAD209BF04B972E9D65AF4A952AD1134F94A36EDD7BD2A2AF169363B1F15DD9EE6C85213BBD792D2A0E106F00807B60B00EB0202A86B4E58484041A3C389CCA3FF934ADDBB0514E8A53A64C591A306000B56BD78EAA54A922D39D3D7B965FDFA3916F8DA69EBD7BD3D831EFD0EC390B287CC86B1E107D47F7D55BA8B3C3034745000000CF1EB82C200AC5C6FE45A1A11DA857EF7EF4D6E8B7D366BFAB5AB52AAD59BF9E4A942E45878F1CA1FD070F5029E1E9472C5F2147EB73BA2953A7D191C3BFD0CA6FD73820C6EE88BE56DD005A36F5EB9D3F0000C0B3076E897CFAAB76AD5A854A6FFEF1C79FC892929BF2BB76ED6633B7CF3EFF9CDA86B6A1EEDD3A3928E28E4E59EB8A57AFF53A6FF3CE5139000040EC810BA251B97225F2F777FD1631B504D8167647055DCFC17DD9E1FD7BB27200D107C0D741333EC8240AE9C250AD5A153A74F060DAF788A54B1DCA256B3AC5EA315CF7F45DED12D04288F51A74971D6F1E000020F6C0A745BF76ED9A141D7D3C6DEDB265110EED6D399DE2A4776F4FD41D6D217067C09F5622AE85E78E8A000000620F34F5EC8DA250BB762DFAE5C82F3A8857663177C44B77D7BB7746383DD9848FFE7A0080FEA0CF1E581590A0A047E8E4C9DFA875AB97E49AAB57AEA42DDBC23CDDD9B37FDA107C67BD746B42AF97A7EFE8887B2DC2FEEA355810A20F0080D8039BC260A0B8B83F34C8CF1D81553448A395A7AF75D85FF4E5030020F6205BC4DEA08340280EAE57B261D9DDC17D8AC6E9F4AA1C000020F6DE20338A12B87DFBF6BE5BB76EED71FAF4E9EAA74E9D2A70EDDAB5008EE0962F5F3E252828E87E9932652E162B56ECE79494940FB76DDBF63B2E9F27BC7BCA540170D78374D5B3F7A4D07BC388FE9C100A38C753565807618D84551756525821613CC353BCB0A3C2F60A5B292C0EA70B40ECDD14F90F3EF8E08B264D9ABC7CE1C28580C68D1B53FFFEFDA972E5CA54B66C592A54A810876C355CBF7E3DEF891327CAEDDCB9B3DCE6CD9B7B3CF3CC33E76FDCB8F19158BF1097516BA137682C1A8E0891A322AFA5E8DB2BAB56A2AF65643D57F681E86782057E889F9FDF8BA9A9A9792D6CE7199E8255E3B4D38445095B242C02A70F40EC9D64CD9A35AF346BD66CEE952B57F24D9A34895AB66C9929108B11167CB6E0E0600A0D0DA5193366188467FFC4D8B163E78BF423E2E3E3DB8A64677039F5F4EEED79F98A8BF92A6E7CD763D9D996003D2B07EEE4034FDF02C1068361BE7030E4E85121F4CEECDB58359EECA13F9E370062EF98371F3075EAD41FC2C3C343468C1841C3870FB728F2B6E08A8130FFD9B3675718376EDC71B1FF5078F95A8BBCC14DC1509C58EF0D9EBED6FDF8AE8835E9900F1074F7F7F79F9B92925234EDE1279E39C2D9A0CE9D3B53DDBA75A964C99214141444376FDE24E140D0D1A34769EDDAB514191949494949A6DD1A08FB55D86078F900626F47E8172F5EFCF3BC79F3EAAC5FBF5EFEC9DC415418A8418306F95BB56AF579D1A245F3FDF3CF3F737059DD117B3D06E8B9235CD9D9BC9F1DA2AF678B81CF122A3CFA0821F4FE2691670763C89021B2B530330F3FFCB0B40A152A50F7EEDDA5F07FF2C927F4D9679FD183070F3809F7EB7398486EEE8783017204FE9E3E60810205764C9F3EBDFEAE5DBB64BFBC16708D3C2C2C2CCFA2458B5E4C4C4CBC20561DC7A5759AF1E3C7BF632789BD817AAE46A3D352F4B5AA0078EA7D7D4F5498D2D74D98305F7EF892472F847E957032E4B38E057CE7CE9DD4AB572F29E88EC05D882D5AB4A04E9D3AD1EEDDBBE9EAD5ABA64DED84FD89E70DC8097834821EF7D1CF98312364C58A15166BD4EEC0F9FDF0C30F85F2E7CF3F8BFFD3B8B4AE7ADF19A3E8D9DE6E2BBD33DB2DAD77E6BBB56557A3F4E9358A1F5EBD8709E6A67B93D03768D0408AB5AB4E06EFC7FB73D3BF19F3F0BC01107BF3C78EA204CE9A356B1EF7D1BBDB746F8DEAD5ABD3C71F7F9C2F4F9E3C5FE3D2BA2AF6CE88BBA3C26FAF22A1B828F2AE88B927845ECFD6013DC606E44E8447BFD0D447CF1E3D771B729FBC3B706BC0EAD5ABCD2B0CDCA4FF259E1D0062AFF2D1471F2DFAFBEFBFF3721FBB9E0C1F3E3C7FF1E2C5CB89C55EB8BC5A78F7B6045C2BAFDF5DD177C49376A4A2A085C7EE8A77EFAED7AED5A43FB98A0EC2C168CA0BDC47CF02EDAED09B0B3EB74E9A0D2A6E80E70D80D8AB5EFD8F3FFED8955FAF0B0C0CD4FD785F7DF55551E1DD0FF1B16BF92E19DF097663D0A53D51D7AA22E0A857EF094FDF9565AD07FC79E2353EC5D7EEE721660E8066E3834C707E23478E345FF51AE40478331E198DCF91F1CE9D3B17C0AFCB7982162D5AE4CD9F3F7FB9FBF7EF078BAFB13E722D3F543FEF0AFB41188FC4DA232CC135D1D7B3E5C0F5F5BB76EDA68A159FA512258A3920907A8CD6F7F4287F9FEDB337DDCF89C236095BE2C4FD5CD66030840827437ADFC3860DD3A5809C2F8FD0575FCB63EFDE979E3700629F150E816B2D608E5EBCF4D24BF1AB57AF0E138BD37DEC9AE617D65EB53BC2BE2363E4AF283286017542886D8FBE1F3870182524DC49BF99C4F55DBA743EBDF1C648EADFBF07D5A9534343E137AEEBD5EB0D9A33670A75E8D0521C67B438CECBE238D549BF57F4BC2544AFCFF6D91710D65DB57F854592F1B5375BF77307D3A03C7EEE70244E3DE0B78038C8D79A356B4CAB7CF1790372081E69C6E758F74D9B36F5E80FEBDCB933BF57D3C8C7AF6F41F501C4827F4BD86AB2D934EADC00BD0D1BB6D2534F95A3DEBDBB4AEBD9334CAEAF5FFF392A5E3CC881BC326E9F3DFB4B3A70E0B0957DB296D3789C47C9B941788A9DE5EC167AFB623D7BF672719E8EFB9AE8330F09EBA1DECFFF08FBC6CAFDDCC8EC39A0F773C6E27101F049CF9E27B5E191F29EA476EDDAEC1154C7254E23AF2AFC61AAC7FF93B059593D24E704A156AD6AD4BA75B30C8252B87021E1E5FBCBE54D9BB6C934D1D127C9CFCF8F5AB408A1BFFFBE41DBB6ED149FFFC86D2CDA870EFD4A5F7DB59CCE9C3947B1B1E7A95BB70E762B22850B17548F43EAB1BE97F95DBA142FC4F017E1F157A3E79FAF99B63D3272875CDFB2656371CC3A568596CBB56D5B945ABE2A226D2DB97EDDBA6D72B94409634566C386EF45FED5A954A9E2E2D8DB65DA53A7CED0C9937F88E5CAEA7EC63C376DDA29D65512DBFF14DBCFC8E5FAF56BA66DDFBDFBB03847A7A9489142D4BE7D532A5428BFD9EFDA25D25794DBCF9C392FCED37AF9191B7B519CA7975CF0FE73053CF8A7B76AB7856D27E3A8F828F3FFBD780EE85A884CCF353C6F806F8B3DCF5EC74D5E9EE4DEBD7BDCA9CBE1AEC6E3325BF4F85BABC6C466150683CB990F1E3C92E6CC994A65CA9492CBE5CB3F2104B104B569D35C1E2324A4BD10B456B2EFFDF4E93352787976C3E4E464BA7D3B418AACF59686F4CFC183478BE34C12C729A97E7F9BAA55AB48F7EFDFA7E79EAB410D1B76A413277EA4679E294FE1E163E9EAD5EBD4B1634BEAD36718CD98319EDAB56B6E51144342BA89F23517E57B5A94EF6C9AD8F7EB374208FE0255EC151A38F01D5AB2E413F1DB8A8963BF474F3DF584D8F628952C598CDE7DF753FAE69B69D4A14373B56CA6ED41627B90D83E436CE7EE8866347CF8C7A2B2132DCAD8938E1D8B11DB66D2E1C3ABE9D1471F56F7FD409CC3B2F238FC999C7C5F9CA73BE23CDD74C2935744E5E092E94B6EFB4F1416D64935262DE8BDDECF9D4CF907E1D1027C5AECF941CE51A83CC9AA55AB4CBFEF7D5C66BB04DB160A6B28F4CA2B4369C890B7E4B779F3A609016D69B6BF318F264D1AD28409A3D3D69F3C799A162CF884EAD5AB6D26B00D2838F871E1F93716DE6A7B72BE6FDA783CAE58CC9E3D512E4745FD2CC4F3A4F4FE172E5C4E89896764EB02F3CD376BCCC43E6325823DF3050B2689F2D5B02A9C96963B766C2E84BBBF5C2E56EC119A356BA92AF68AD9F6BEEAF6A2627B0455AEFC8C28EF32BA7CF9FFE43E2FBFDC5A1CFF4FFAFCF30871CED25F2869D2E479F5BB222A307F88F3F4829957EFD8B95ABC78B369456EFF4FA4754F6AF5BA9D353245E10BC4A304F8B4D8172C5850E1696A3D29F89D3A75BA3E61C204FE274EF4916BE9E8033C95B28ED5382BACBC7D81CFEAEDB368B76F6F147853D37D6691A951A34A86F573E74E11E2DE4136FF4F9EFCAEF0BC9F74B0A2612B588F9166CD1AA47D2F5AB408A5A6A608718CA17BF79285575D5D6DF54996DEB735919C3BF743E9DDB76E1D22CA375A94AF9C43650B0E2E9BB65CA9D2D33463C6D719D2070797C9B47DB1AC583CF4504129F4A6B4152A94A3DF7EFB2343EE356A54207747F1F7EB172A2A0C0B7871422EB99F53550BB0768F5FBF7E5D57C1E74973CC4882A4009F167BF167BB1F1F1F9FF7A9A79EF2D80F0B0C0CBCA1FEF9C643EC2985D2E741E087203FA176085B266C9B7A9E94AC13E1D817DB3C7902286FDE3C0EB502981834A8B7AC204C99328BEAD66D4D717147A9408140B23518CFB2C03B36E29ECB57A44861BA76ED9803A2A888F2F510E56B21CA374F94AFA328DF3E51BEFC59F6494949B59A1F77253CFE7829ABE7C1B8BDB47C83E1C183940C79DCBA755B9CD73C0EB6B0382EFAC1C1A54D8BE373F0FD9CAADECF79D47BD9D2FD1C2D4C3E6C78121B3DC59EF337E33A2405782B1E198D5FAA54A9F33C5DA427D9B163074F5071D487AF6DB2D9320B3DBF9FCCEF087511564EFDDC90D51B713624AEBDF5190587E710E76E1DEEBB66AF9E5FDD3B7F3ECE7833FAF9A9FDF58EC6CD27B21F6CC7E8ED172C5880962C594DF646EA1BCB7747F6BDB3576F2CDF45B98DF3F8F7DFDB32F5A449F3C47242863C7EFFFD6C5A5E2B564452DBB64D321C277D3B89ED5BC4F610D93CCF2D227BF7FE62740D939268F7EE23141ADAC8AA90FBF919C479BA45EEBFDA97634831BB4FFD54A1B7753FA7FDEF0F1F3EAC6BC1323DD77CF97903E0D913152F5EFCA79D3B773E151616E6B11FB67CF9727EBAFDE4C3D736AFD903710B19DF4F76F23D7B13CE0CD6B3FD6A1B37AB972B5747BE1B1F1B7B81C2C35FA10A159E94DBB9AF9E07DDEDD8F113AD5BF795039EBD7DAF5EDEE4C27BDEB4E92BEAD97328CD9F1F41850A1510DEDE2342706759AC8C942BD7408EE48F8D8D13E5EBA3968F68F4E8D7A8478FE1D2CB1F336630152DFA5086FDF7EE3D42F5EA7591797017C2E8D18332E4CD825EAF5E37B15D91FB8E1EFD2A0506E6A555AB6650AF5EA3A95AB567E9D8B1DFA973E7E6A2AC6DAC9EDB6EDD5A8AF3F491384FFBC4799A41B9388A9E7965D5DF89FB99FFF7F261C3F3D1F7EBD74FB78271FE998E0B805762F0C4416AD5AAF5E4B56BD74EFFF9E79F7E1E0AAC93E4EFEF7F493C74F9E5FE581FB996A627393769469131E25894FADDA1FD1525DE4345244A4CBC2B45387317008FC89735952C5D03AE4C839B55F4321ED77A85213131D1623A1E09CFDFD3CB67DC56A64C7D9A3367BCF0C843E49CE72CE2E6799729F3A2D8FE9ED8DE58DD9ECF42D992E47EECB9DBF3D88DE5E0F314E084C02B6430D4F6D8FF3E1BEF671E3C71CE58CF0BA073E7CEE91258879BF0838383957BF7EE99CE673942043DE0CB9EFD912347FE7CE69967CE6DDBB6ED498E38A53773E7CE5D2B84FE091FFBE38D537FEF1A7279A090E73C3F631F78D626F78C22AF757C786BC7CD5A6948EFA3CF98CEBAB89A5A11FC85F9D96865F0B73A90317DDC826DC14E3F4FAE07E6C9E5F7739C5A3168C615AB993367D2B469D3342F20E76B26F47B20F4C09BF1D8AC7757AE5C79E7ADB7DE7AC07F3E9DB9396CD8B08AE273818F5DCB8F8445905B23821D99D4C6D53C9D9D09CFD189716CADD373329D8CE92222A653BD7AD5ADEE13113155DD4EA4DF443A8E78F73E733FCF312D70FCFA13274E685A38CE4F5420CC4FACAF3D6F00C4DE32B76EDD5A7DE3C68D53FCC7D393F6EDDBAF15150A8E10B71297570F8176775E7B226DA7C07557D49D1176EBC21A1252570EEAB3B64F48C8F3EA766745DADDD0BD8AAFDEC41B54EF5E769B74E9D245BE86A7893771F32675EDDA354560EED5E3790320F666DE7DBB891327DEDDBF7FBF2EF96FDFBE7D938047360D2463F43CA0A9C86B554970C5A3B73735AEBBD3E23AEFCD5BDFE65C4541BFB8FC8AAFDFD01CE1482A7C4C4C0C75ECD8D16DC167A10F0B0B534E9D3A657A953501CF1B00B1CF4A6C5252D2C00E1D3A24C6C6C66A9AF1C99327F7B66CD992DB4947F17F1B97D655B1D7AB32E08AF0DBDBE66CF3BE2BDF9D19F9EF4D13F028B89D8D7DE8FF3509F19E3D7BA861C3862E37E9F37E2FBCF042CACE9D3BCD07380EC6F306E404FC3D7DC0070F1E44272727C72F5DBAB4A9F8E3E5D562942C7BF4F5EBD77F363535759EF83A1797D525C68F1FFF3F2FA954D84AA3E5FCEECE7CD76B0A5D3D96AD9FDF0913BE901F3E745FB3B25F10C62383FDD8B3FFF2CB2FE9F6EDDB54B56A5587C278F3A8FB891327529F3E7D94AB57AF9A3B48EFE0790320F63610A2FC6B6262E23921F82D0202021ED4AB572FAF2966B993DCECDCB9F3CA3163C6345214E55DF17D362EA93B62FFA603C90C6E08B856E2EE6E6520378BBEEDEBE08362CF70B01B8E3FCCD33306721C849F7FFE598EA63F74E8907CDD935FD1E3675081020564533DB73CEED8B1438AFC9021435277EDDA6510CF18D3CDCF4DF7AFE17903721201D978EC95E24FB67FDCB8718BC49FAED2A2458B1E6AD3A64D7E07F74D9A3F7FFE86A143873E75FFFE7D0E8B1942684AF322AF5C0F8F5E6B81F794E87BB23280A67B5BCF1B613C58E84B55F4E5C0BDC8C848697630F7447830DE403C6F00C4DE39625352529A5EB97225AC7DFBF683F3E7CF5FE5C5175FBCD6A3478F42B56AD5E226FE92DCCC76FBF6ED2B972F5FBE151515F547444484BFA8953F2BF6E3F67F7EBD865FCFC1E0986C17724F0ABFD64DFC5A89BC2B42AF55050042EFC8F346184F4318A67AE68D9D7806B2C82FC2F30640ECDD638D10EF3509090965B76EDD1A26ACBE58C7A1BEF81D5B8E345244D87DF589B6938C83626271F93C29C00637F777775FF4E143E8B57BDEA85656157ED3F3A6A4FABCE1E70E0FDBE7E6FF9FD4B478DE0088BD8670E4ABCF5403B9CEEB57344AE74C84386F69CED74BF421F478DE0090F3C41EE47841F7A4F8E7F43E7CAD2B00000060193F9C0290513C9C0DA4E3683E5A44D77326C88EBD7564639DBBEFE1BBF3EE3D84DE0BE9ABA3635446586F9C6200B1075E58017037D21E913E417688DC8FA3AF65585D57C4DC9910BAC043F05CC8813AE5FD1F619FE21403BD41333EF0026F518F57F23CD587EFC9A67E883C0000620F34135D830E796AB98FAFF7E1FB0C9B847D4EC601743C8BD06BEA3A86DF959F4EC637756285F52163A43CD37E4BD5EDBCCF1B648C74C7DF8B0AEB2EACBDB05785FD4CC6E87AA9C2BE165647583132061E72243A5E4B35DF82C266A8E5B557F6D6AA379F4C785F1F40EC41EEF7F0F50AB2E38CB7EF494FDF956D3EEDCD3716F68FB0BAC2DA0A1BA40A268BF13A615584FD25AC8BB08DC26A9AEDC7158027C9F84EFC2A328EBCAFA9E6B15115FC91C28E0B6B216C9BB0F7D40AC313C2A21D107B2EC702B51C89C24E0A5BAFE661ADEC2CFCCB8555127651AD8884E0990320F620178ABCD61EBDBB02EF8A17EF8C90C39B7783E1640C4F7B4D583E755D133206B9F94BFDBE56D80A610554D165DEA58CC16FDE523D69CE274AD80FEAFAF3943E76E996B04EC28284E575A06C4DD416818FD5EF1C7EBC86590B83A5B23752CB7E51FD7E1A971840EC810F5726BC75B21CBD441EDEBC15EE5B599F6CB69CAA7EFA5958E728E555CFFB4361FFE7C47EF1C2B6AACBFCF9AB9DB2E72344E003107BE07B9EBD168176F4EAC337505CDC1DDAB0218AB66EFD9176EEDC45F7EFDF274531A6E5C9536AD7AE4E9D3BBF44DDBB8750D9B2C534A80440E81DE0081963DC17523DE77AAAA79CE0469E0DC9D874FF2D195F8773E44DA59FD572449B79F3F6382BEC45B3B237C4E504107BE025C26BD0200F2D2B007A4F9663A077DE59400B17AEA022458A50484808F5EEDD97060F1E42CF3DF71C952851822E5EBC48E7CE9D939FFBF7EF17695EA7A4A4440A0D6D40F3E68D8137AF2F67C8D8DFCE62CB03DC2A0AEBE1669E7B854D2563DFFD35F518E3544FDF1A2CF0FDC818C29B43EB9650D3EFB0B1CF0132763BFCA11E671F2E27F004069C0260521B45F164F7A19EA1735D17FD9F7EFA8DC2C286506262228D1C395288FE3B1418E8D82BD693264D9253A2FAFB1B68F5EAA9D4A2453D173C7B9DFFF086DAB9E97FCFDE375F9C440DF3E3BEFA24D511E2EFC90EEE671A2FE068F70197FB01A1491FC0B307DEE3D97BCBB1F59C2CC7404387CEA43973160AEF3C94BEFDF65B29F23C0DEA279F7C425F7CF1059D3F7F9EEEDDBB279BF11F7EF8612A59B22455A952858A172F4EE3C78F971503B6EEDDBB53AB56E134685098F0F2DFF60A91CFA5A46A28F4A6FC92D4E5074EEEE76C1742122E1F80D8032F105C830E797A4AFC15A7F38C8CFC8D962C59490B172EA4FEFDFB4B919F3E7D3A2D58B0801E7AE821AA56AD1ABDFBEEBB42C45B51505090DC27262686B66CD9223DFA79F3E651FDFAF569D3A64DB472E54AEAD3A70F75EBD695AA567D8A060F0E83C80300B2157F9C02A0229CD3702F6E495034DE96BE7EE5CA033462C438B5EF3D8476EEDC49B56AD5A2FCF9F3D3D75F7F2D05FEEAD5ABB476ED5A1A356A148D1B374E36D7EFDBB74F0AFF471F7D445DBA74A1A54B974A0FBF75EBD6D4A85123EAD1A387F0EEFF27D21416A2FFB45788FC84095FC80FDCEE0040EC810F922F5FDEF7478EEC6F0808C8EE5B42AF403B969BF2F7ECF95D78E1C369DDBA7554B972659A356B16F5EAD58BBA76ED4A63C78EA5F6EDDB4B117FFEF9E7A95FBF7E347BF66C29F477EEDCA19A356BD28D1B37E8FDF7DFA7AD5BB7D2C68D1BE9F1C71FA7FFFEF7BF54BD7A75B9BD71631EDCF70655AA548E9E7EFAF16C3DB377EFDEFB6BE2C4AF7814F887B8E301F02D30110E90942F5FF6EAFEFDC73C20E4CE4CA2A3E5643996D6198498BF460D1B36A4BA75EBD2860D1B68F8F0E152E47BF7EE4DCD9A351395A07CB2F99EAD71E3C66983F5F893F7E1417C4F3DF5946C0968DEBC39FDE73FFF919503DEFFD8B16352F45BB468492FBF3C36DBAFF10F3FEC3B41C651E30000883DF04582821EDEB367CF11726D963BADA7B455C8FAA03AAD66C2237AE79D2F282929498A33F7D1BFF1C61BF4EAABAF4AF11F3C7830AD5FBF9EDE7AEB2DF9690E7BF2090909B2F93E222242EEFBCD37DFD0F6EDDBA557CF798E1933465606AE5FBF2EF28FA094945491E7A46CBDC68B166DFC978CD1E30000107BE08B9C3B7769EA8205DFDE67E1D256B0B5AC00382AFC8E89FEFCF94BA5675EA85021FAECB3CF643FFB9B6FBE29BD72AE00B058F3C87C1E78670EF7CBEFD8B183BEFFFE7B29E65F7EF9A50CB0C3DD009B376FA6810307CABE7F1EA5DFAE5D3BB9CF7BEFBD272A06DF65E7258EDDB2656F3532BEE30D0080D8035F242E2EFE606A6AEAF1D9B397D949E9EAFCF5CE540CB4F2E8AD7BF53B77464BEF9C5F95E30ACE9C3973E8C30F3F94E2CCAFD5F148FB9B376FCA656EC2CF0C0BFD4B2FBD249BFDCB962D2BD771FAA3478F4A91EFD8B1A3AC041C3870405608DE7A6B34DDBB974CCB96658FE0B76DFBE676717D2F89C5FDB8DB0180D8031FE6D2A56BBDC68F9F93B87FFF51F26C533E917BFDF88E087FC6F5DF7DB7477AEEDCF73E79F26439F29EC59FDFA3E711F73CF27EFEFCF9564B1B191929BD7E7378F0DEB265CB64537E9E3C79E42B7B8F3EFAA86C0960EAD6AD4DDBB71FF0F8758D8AFA656364E46E9E79ED4DDCE500F826188D0FCCB92EBCCF839B3747B56FD8B06660D9B225DDCC4ED1795FD783EC7CF9E5161A30602055AC58517C0EA01A356AD0952B57643840BDD5250000200049444154DC61C386C9D7E61A346860716FF6FAB999FFE38F3F263FBFF4FAF28B2FBE2847F2F328FCBB77EFD2A14387A865CB9674FAF469F96A5EB1628FD0EEDD3F89BC5B7A54E89B367DBD8AA2283C95EA1EDCE200C0B30780D971F5EA8DB0468DFAFD3B69D2C21BF6FBF0B5F6E2B56ACAB7DD877FF4E87139529E898B8BA3366DDA08618CB22AF099BD7A1671EEA7B706E7C3F971BED1D1D1725DE5CA55C5717FF7D4758C6DDBF6CD2F4342063D9F9A9A3A8C8B8D5B1B00DF0511F48045C14F4EBEFFFCB871B3667EFCF117357AF4681DD7A953B3528D1AD52A1918984FC7C3EA1D2F3F7DDB850B7132DC2DC34DF76DDBB6A5418306C9A67D7B707F3DB706D8822B12DC7F5FA142058A8F8F97EB82824AD2D5ABFFE876F6F83D7A7EBD8E47DDF3603C21F2E5C4EAE6C24EE0960600620F8025625252525E4A4848ACFEC5176B3A0B6B2CD63D4CC6093C72053C0A5FCABF1AEB9E5F99E3BE7B865FAF33F5B59BC36978243EDBCB2FBF9C252D7F4E989031401D0FDC331D4F54A24C93D1E8014FCDCA33A99D12F63961301E0000620F1CE428E5C2402C068341E13E7A9EAE9643DE7277050FD6FBE79F7F6CCE72C7DB4CF3D95B82C59E8D2B05850B17969F262E5FBE7C557C1427CC360900F030E8B3073E499E3C799413278CADDBDC9CCF4DEEB56BD796F1F135A92189FCB8299F03EDE4CD9B57AE13C7BB233EEEE3EC030020F600788042850ADDE1BE77E699679EA1356BD6C870B87BF61807AC73643C0EB4E30C9C7EF1E2C57299F3E1FC385E3EB71E30CB972FBF2E3EAEE3EC030020F6007880C2850BC7FCF8E38F7299E3D9F3D4B69D3B7796F3D6F3FBF3FCBE7C5858985379F23CF6FC7E3ECF9267CA8F8FC19FCC77DF7DF70861B01C0000620F806730180C03AF5CB992CA7DF56FBFFDB61C4477E9D2252A58B0A01C48C7616F4D91F11C85BB03783F8EA0979A9A2AC702DCBF7F5F0EE013245DBD7AB5B4F89C8EB30F0080D803E0016263638F162850E0DCB66DDBA4B8F37BF1AFBFFEBA6CCEFFE9A79FE8F0E1C3321D570278C21B6B70D33DBFA76F82C701FCF0C30F72CA5C9EF77EC8902172A4FFDCB973D72A8AC2330DFD80B30F0080D803E0218467FFCED0A1435378C43C4F6FCB23F177EEDC9936452D0FD6E3003A7BF7EECDB09FF92B79ECBDF3C437DC42C0426F8A89CFB3E0715C7C9E6887EB0CC3860DAB283E17E0AC030020F60078905BB76EAD165E7912BF2FCFDE3787B79D366D9A7C5D6EDEBC7932D0CED4A953A5809B63FE1E3D87C7E5487A7DFAF491F3D9CF9831837EFFFD77FAF4D34FE5BEEA6C78BF8ACA00FFD756E2AC0300B203C4C6073E4D6A6AEA5E21CE7D9F7EFA69034F4BCBB1F1FBF7EF2FDF919F3973A61C95CF83ECEEDCB923FBE1B95F7EE2C489B29F9F9BFA57AE5C295B03B8BF9F5B0178601F4FACC34DFFAFBDF61ACD9A35EB47E1E53716877A55D8EF38E30080EC00C13D80CF23BCEF998AA2FC372A2ACAC07DF72CDE3C22BF58B16272EADBC71E7B4C8EB2E758F72CF0ECF9B3C7CEEFD1F3EB753CDA9E9BF3478F1E4D3C068005BF43870EB465CB96C3221F0EC0CFCDF7E138D30000883D00D9889F9FDF32F1D15D08B41F4F72C303F3786E7BEEAFE7696ADF7BEF3D19379F059E859E059F3DFE152B56F0603F693C188FFBE879FBFCF9F3770E1E3CB891C873A7B09638C30000883D00DEC1FFF2E6CDFBFEF8F1E3FD478D1A55D024EADC7F7FFCF871E9D55FBC78510EE46338325E993265E8D5575FA5A143879A62EDDF149EFE8675EBD67511CBAB84BD82D30A0080D803E05D04FBFBFB2F2A58B060EDF0F0F0C0891327E67570BFA4D1A347FF3863C68CE2F7EFDFE780F80385C5E074020020F600782FD30D06030FAA2B5CBA74E95B1D3B767CD0A54B17EE9B2FB66AD52A6ADFBEFDF59B376FFE1B1111717DCB962DC5AE5CB9C2117858E44708FB5AD8039C420000C41E809C014F703F5A582D167E61DC56CFB345A60AE3E9EF38D6FD213246C6FB09A70B0000B10720E7335E18C7BF9DA02E030080D783A03A00000000C41E00000000107B0000000040EC0100000000B10700000000C41E00000000107B0000000040EC01000000883D0000000020F60000000080D80300000000620F00000000883D0000000020F60000000080D803000000107B0000000040EC0100000000B10700000000C41E00000000107B0000000040EC0100000000B10700000000C41E00000080D80300000000620F00000000883D0000000020F60000000080D80300000000620F00000000883D00000000B10700000000C41E00000000107B0000000078090138050000138AA204CE9DFB79F88103877AC5C4FCFEECF1E3D18149494938315E8ABFBFBF121C5CFAEF9225830EC6C7DF98FEE79F7FEDC259019630E01400E014E385BD2F6C82BA9C6B58B4E88BD72322967D72EE5C6C8101035EA1060DEA53DDBACF536060A023D5041BDFED2DBBB25D8B7D1DCD4FCB3CB4CD9F2B62478FC6D09E3DBFD0C2856B936FDFBE73EAD2A5ABBDC4A613F8AB02883D0010FB0C2C5CF8C58677DF7DAFFDE8D1A3283CFC0D0A080870E011A1D859E70D22AF85B8EA2DE8DA9567F6ECE5346EDCE7779393935F4E4C4CDE88BF2B30E18F530080533456ED276151B945E8274F9ED2FEDB6F5750585867F2F3F35745DE9E2F60B090CE916557B6675EE7A89FE268BE7AFB50068F94F1B9E7AA506868A33CCB977FD7263131295AAC3A8DBF2C6030400F001F869BEED9A35FBA74896CB2CF2AC0F6CC19917754A8B5103F2DD21B34CC5F6FD2CB53B9F253B46BD75785F3E7CFB794BFE22E07F0EC01F061CF9E07E38D183162E78001AFE4618FDEBA301B2D32720B8D1D3B8EE6CD9B4F6BD7AEA302050A50850A15AC88B83B9EB7169EB4B778F97AB71858A678F147285FBE3C89BB761DAA999A9ABA047F5B00B107C047C55E08C2F02D5BBE6B1311B1446DBAB7E4D11BBF8F1F3F81B66FDF41172E5CA063C78ED1BFFFFE4B478E1CA1F3E72F509326214E78F2062785D77B3D68EFCA2B2B2FBC503DFFCC991179EEDEBDB74F7C8DC35F17620F00F041B10F0A2A36B759B3A6251B376E644184D33F23232369C3860DB475EB5629F6C9C9C974EBD62DBA7CF9B210FBF354AA5429AA5C393B5A8B73BAA7AE3F172F5E3D7DF0607401B1B81D67C3B7419F3D003E4A4C4CCCB3FC7A9D3D315DB26489F4E61F3C789025455C5C1CF5EDDBCFC382EBAD82EE7DE50A0B6B5E46AD9C021F07417500F05138608E71509E35142960F1F1F174E9D2252A51A284ECA3BF127F8502F3079292AA5068DB505919C8BA9F9E285E7A46BDAF5CCF3D57B994F82886BB1D40EC01F05138204BD680394A260F55318AFCB31568F9F2E554BE7CF92CF918C53E3B844E71709DEF1218980FCF792041333E0080B2066C51D2D6F5EBD757560A8A14299265AF1D3B7750AD5A356DE4E3C8BA9C24DE8A97E60500C41E00E0B0C86715E5D0D03654A972456AD2B40925DDCB18277FD4A851B478F15776F2D25BF0DC39969243AF1500107B008046827FF3E63F3469D264FAEDB793B468E1220ACC97B1D9FFB1C71E13DBA7C851FACE5420F417652DD22B394878511100107B0080D382AFD0D75F2FA6CE9DBBD2D3CF56904DF8A54B97CEB257A54A95E88927CA51B7EE3D69D1578BC9B1D8F8AE6C77B6C2909DDEBC1615078837D0160CDC0000224FC64179C6E58484041A3C389CCA3FF934ADDBB0514E8A53A64C591A306000B56BD78EAA54A922D39D3D7B965FDFA3916F8DA69EBD7BD3D831EFD0EC390B287CC86B1E107D47F7D55BA8B3C303474500C0B30700B82C200AC5C6FE45A1A11DA857EF7EF4D6E8B7D366BFAB5AB52AAD59BF9E4A942E45878F1CA1FD070F5029E1E9472C5F2147EB73BA2953A7D191C3BFD0CA6FD73820C6EE88BE56DD005A36F5EB9D3F00F0EC01006E897CFAAB76AD5A854A6FFEF1C79FC892929BF2BB76ED6633B7CF3EFF9CDA86B6A1EEDD3A3928E28E4E59EB8A57AFF53A6FF3CE513900107B00800BA251B97225F2F777FDB1606A09B02DEC8E0ABA9E83FBB2C3FBF764E500A20F3282667C007C5EE8D385A15AB52A74E8E0C1B4EF114B973A944BD6748AD563B8EEE9BBDA25A08510EB35E82E3BDE3C00107B00804F8B7EEDDA35293AFA78DADA65CB221CDADB723AC549EFDE9EA83BDA42E0CE803FAD445C0BCF1D150100B1070068EAD91B45A176ED5AF4CB915F7410AFCC62EE8897EEAE77EF8C707AB2091FFDF5C0F3A0CF1E0090261641418FD0C993BF51EB562FC93557AF5C495BB68579BAB367FFB421F8CE7AE9D6845E2F4FDFD111F75A84FDD56BB020441F40EC01003685C14071717F68909F3B02AB6890462B4F5FEBB0BFE8CB07107B0040B688BD410781501C5CAF64C3B2BB83FB148DD3E955390000620F00B02914060D3D48573D7B4F0ABD378CE8CF09A180731465857510D64858756125851512C6B339C50B3B2A6CAFB095C2E220F600001F107A83C6A2E18810392AF25A8ABEBDB26A25FA5A46D673651F9F167D16F8217E7E7E2FA6A6A6E6B5B09D67730A568DD34E1316256C91B088DC7A5220F600003BE260704350DC157DBD3D7D67C45AAB6E00BD2A193EEFE9071B0C86F98AA2C891A242E89DD9B7B16A3CB1437F616720F600805C2CF2063705437162BD3778FA5AF7E3BB22D6A4433E3E47777F7FFFB929292945D3C42D20809A356B469D3B77A6BA75EB52C99225292828886EDEBC49F1F1F174F4E8515ABB762D4546465252529269B706C27E153638B779F9107B007C5EECF518A0E78E7019BF1F3AF42B952E5D92CA942929BFEFDAB5872A567C864A9428E661EFDE7EF977EDDA27CAF6B428DBA36E54146C1FFBD0A168713E8A89F3510215818C840A8F3E4208BDBF49E4870F1F4E43860CA1E0E0E02C891F7EF86169152A54A0EEDDBB4BE1FFE4934FE8B3CF3EA3070F1E7012EED7E79090DCDCBF30B79C2404D50100826FC3323270E07F85E8FC9261DFAFBF5E4A9F7E3ACBCABED6F2B3B5CEC8840953E8C71FFF2FED7BAF5EAFD1BE7D872CA47527488F23626B5F8C7BF57A5394ED880E226C7E3EE68AF37100DE7F268F5E08FD064551A4D0B380FFFAEBAF346DDA348B426F09F6F8393DEF57B97265F34D5FF2A585D8030072A1D0DBDEBE6143245DBC783143FA23478ED29E3DFB1DA82CD85BEFCC776BCBAE5600F41AC59F71FBECD94BE9C081A34E7BF510788B0473D3BD49E81B346840BB77EFCE2CD80EC3FBF1FEDCF46FC63CAE43E4869385667C007C5EEC5D69C6B7DE877EE6CC59216887E9D6AD7FA9468DAA54AF5E9DB46D9B367D47B56A55A74B972ECB3475EAD4A4E79FAF9596C7A64DDB2836F63CBDF8623DB21D5B5FA1C8C81F441EBF50CB964DA87EFD3A36C5363272BB9A36242DEDDF7FDFA06DDB7689CF7F4499AAA8EB8DE977EF3E48870E1DA5A2458B886D95A96AD5FFA8BFED9C14EB5BB76E8BDF5651FCB69A16CB77E6CC5F22DDAF225D829AAEBAC8EF387DF5D51AB92D36F62275EBD62A6DAFC8C82891FE98285F03518E9A66E76397481B27CE476D27AE856F88BEF0E8179AFAE8D9A35FBF7EBDEC9377076EDE5FBD7A35356CD8904E9C38C1AB0AA91E7E4378F600805CE6DDDBF2F46D79EAC6E531633EA03B77EE5040803F356FDE416DF6376E1B3CF87F3470E05099E6F2E52BE2A1DA9A4E9F3E23B70D1DFA36CD9831974A9408A2993317D09123C7AC7AE9E1E16FD337DFAC927DF87DFA0C11A2F8BD55A10F0F1F23D2AE56D30E55D32A14121246313167A858B1474419CEA6A51F3E7C3CF5E8114E050AE49723BA8F1D3B9596DF9831D3C46F4B547F5B6F29E096CE9331DD5D355D1F992E2121919293EFD3EDDB776405C34478F807A27C1B44F99E12E57B5B94EF47F57C4C14E763891C073073E652713E7ED3A8452057D04178F44DA5C71A102005DA5DA13717FC152B56984FD7CC83F6727C733E3C7B00722EEF0A63F72352D803D7859EC899D7EB7AF4189061DEFAA4A47B141A9A1E3F7FD5AAAFD3F6DBBA75BB14F33A756AA46D2F5FFE099A3D7BAA5C8E8ADA23C4F404152C989FE6CEFD4A54004E0AF17D5478BD1DA975EB6E168F7FF6EC5FB47061042526FE457E7E467F8585BF5DBB1659CA6D4CBB4CA43DABA65544DA3532EDC9937FD082055384D75DCBCC238F15C2BA880E1EDC2CCA5C2D8BA8AE5A35C7ECB7ED9295843A75AA6429E3AA55B3D2F6DBBA354AA48BA59E3DDB527070196AD1A2419A577FF6EC7951BED5A27C4745F90C6AF93652AD5A15C5F95829CE4794AC8C70FAD6AD5F7750D49D0DCE9323EFDF21A6051E8CE76AD3BD3538BF912347D2E4C9934DABF895BC1C3D3A1F620F40CEE543F5F38EB00DEAC3688FB004D745DF3ECB977F491D3AB431F34CDFA24B97E2D3F2387EFC379A336721FDF3CF4DD99FDFB973DB0CF9376BD638ED98DC4C9E9A9A22BDF8871E2A2C85DE94D624E499CB76E2C429BA772F5978BC95E4775E2E59B2B895B4316ADAAA66698BC97473E77E2CBCFB2E42449B8887FA3BF4CC33E565FA3C79022C0ABDF1B79D12BFED1BF1DB6E8932478BDFD6D2E2B93C7E3C46A48B50D39D10E95A583CD7274EFCA196AFBE59F982C43E27C5F9282885DEB48FB13260EF9A2939F1FEBD2D6CA3B0650EDEBF650D064388F0EC65A573D8B061BA148EF3E511FAEA6B79ECDD070B8B85D80300B28B82C27AAA764BD826D6643246054B724EE40D6E1443A198983FA879F38EF4FDF76BA87AF52AD4A9531F2BC7CCDA176F7CEDC97ED741DEBC79A9489187E8DAB59376BDDBBC79F3A869A3B36C1B34A827B56FDF82A64C994B75EBB6A3B8B883327D6AAA222B20A6960013DCE4DFBC792FF1DBBE11BFED3FE2B70DB678DC98983F45BABE22DDD76ABA376CFC162E5F6151BE9F33E4C57DF50F1EA4D8F0DA7395E817266333391BF76F6C16B6C2C6FDDBC13428AF65CB9654B66C595D0AC5A3F443434369CD9A35A65561C2A6E7D48704FAEC01C85D1411D65BD877C26E08E336F50ED62BF6B646CCDBEAC3B7BC2D3AFA24952A55520A3D7B44172F5E16A2759F6C0FB623D9CC9F987897F6EE35BE5AB663C74F74E0C0118B958366CD1A52C1820568C99255562A0FE6691BC82E82254B566748C7629E909020C707B0579F907087CE9FBF442D5A3494EB3EFCF0F3B4B4DC4DC1CBD1D1BF8BDF565C0A38AFBB7831DEF45E7686E346479F16E98AA9E9F81C5C51855B911588BFFFBE9996B659B317D4F26DC87066EAD4A924CE4792381FBFCAEF3B76EC13E7233AB78B3EC303EEFAA8F7EFDF56EEDF46A6050E98A32799F26F94931F0CF0EC01C8BDE417D64F35F6F8B7095B9CD5637225EEBA656169D2A4018D1DFB1155AC588F8A167D98EAD77F9E3EF8603A55AD5A896AD5AA6A559C59443FFDF4030A09E948850A15A456AD9AAAA3F4B35610B8E976D3A66FA867CFC1347FFE12993E28E8115AB162AE95B48B45DA7091F61B356D515ABA7426952BF7826CAE8F8DBD40E1E1FDA84285F272AF75EB1650D7AE6FD0FAF5DBA497DFB2E58B3475EA3BE2B7D513BF6D9AF86D2DC46F7B48FCB65AE2B7CD12BFAD42867236695257A4FB44A46B29BB298CE9668B74CF52B76EAD69F0E0F78578FF2C8E334B94CF4F946FAE28DF2851BE95A27C0564F956AC9826CEC75BE27CF493EB5AB56A28CE47151B9EBE337301E4180A98DDBF37D5FB7789B0B40120B56BD7D6B500D5AB57CFF035279F4C039E870038C57861EFABCB13B2B92CEFBBB1EF9FC29E54946B3A3C0E14E9A5F3687693671C1898CF21314A4E4E9623E0D3D3DBF656F9383CE29D9BC32DE76F9E36518A7F7A5A45DD9FD70564D987B70506E6CD3276C0F8DB02CD7E5B5EABC74B3F0749EA6F52E4887C26F331D99337FE9680B4759CD6783EF2DA1175CBA21F1B7B49546A42BDE15ED5FAFE955CBB764DB351F896E0D0BA458BA645E04D522BD0F0EC01F031DECFC1657FD29688BAED9215487F261A85CAB198F9E6426CBF7C4A9AE85ADB9E316DFE2CDB32EE9F711F6B799BAFB726F4198FA7A4097DFA6F54AC9425A368674D4B0E7AF2C6758B176FCE0DF7AA55F4147A865FC3332330279F2B883D00CEB138875536EEAB9F7932ADE717CBCBDB17787BDEBE5653AF7AEB043AAEECE36A043E7763F567DDDEAF5F284D98B020277BF6F7D49BD0D254B574FDFA75DD3D7B339220F600F80EB1646CCAF7E687250B3CCFEF99CF4CE4F9A9B5838CAF376D531F5C4AD6087A5A7AFB8A8BDB1D1B68F6C61B6F53FFFEDDA84E9DEA6E08B6A3DBDCA900787A829EF4E5E0E0D2A6C5F15EF85FB276FFFEAB8A7BA07A0F67BE7F7944BC6C99E2496CF4147BCEDFBC6E01B1070064377755812F6826F009AAB07FAB3E286F6A2FE8D927FC1CDEB678F120378452AF6973B5127A3DE2F67B2D3C80945FA7E3F0B40FD9B97F7B9AC4FEF0E1C39A07D43187A7C135FF0AB107006437F9CD1E90FC62F01632462673F23D7B13CE0CD6736F3637D73C7B850A172E2807B431C638F75159E2DCF3F78CEB8DA3B7D7ADFB4E2EF36B76C6097E7EA0E79FAF2EDF0A488FA7FFA38C6FDFB2652339A2DE3B859E9CD8DFAB29E2C4FDFB1319DF7997F3D1F7EBD74FB74271FE998E9B63C17BF600E47CD8E3E117B53B0A7B4C587FF581E9461FA3E284B9928FAD3496D6675E473478F03B72B21A639CFBAE6A9CFB4733C4B90F09E96621FEBD220462044547C7A4A51B38F06DFAF5D7DF283D9EFEFBF4CD37EBE41CF57DFA8CA04D9B763828D09E16FA5CF16EBDB3F72FA795010EB66DDB46717171BA148A9BF0376FDE6C7E32D7C0B307006407E3C83886C00D61F7B430B8D29C6F7F9D31CEFD2435CE7DFAF6F4F5351C2EA1315EFDB79498F89B59BCFAF5D4AE5D53D2AEA9DFD3FDFCB9EAFE65758F12D68C831ACD9C3953CE47AF359CEFBD7BF74C4D5C7B280787CA85D80390B3F9483BF1D532E486A2413A67449FE3DC4F945E7CEBD6213479F2DB32CE3D3377EE8799D607DBC8D398D78913BFABF1EA9F936BCDE3E95B2E871EA2AF65652157DEBF3C23919C789EE3D7F7EDDB57D3BE7B9EDE565420CC47AF2EC8E90F0B883D00C003E2A0E500BDACEB070D7A99DAB76F4E53A6CCA3BA753B505CDC7EF99EFBA0413DD4F8F7A6F5FB32BCFF6E222525356D393D5EFD21070459EB0A80D6AD02B9960DAA77DF98BDFB2E5DBAD0EEDDBB351999CFAFDB75EDDA3545E06FE6D5AFCCE9270C7DF600F8BCC8BBDA1FEFC8FE8EF4D1DB8AB96F6DBFF475C638F777D438F7A3D3E2DC73E439E3FA47CDD65F94FB706CFD7FFF4D90CB9326CD55978DF9356B565F8D57BF3693803ADA3F0FA1F710DCB72F5F878B8989A18E1D3BCAF7EEDD15FAB0B030E5D4A95326A1E71B6320B93C85343C7B0080D7887D76BD676F2F8D634DF92CF6E5CA35A03A75AA526C6C1C8587F79571EE79F219E3FA6AEAFA3E62BD3170E0E8D1AF518F1EC3A4973F66CC6019EBDE94B7315EFD02EAD9F37F347FFE0AB378F53334146508BD06C40AFBAFB0A57CD9F6ECD9430D1B36A4D5AB57BBD4A4CF4DF7ECD19B093DC3531BC6E4869385D8F800F8B0D22B4A9CA70EE5461AC7443F6BEC7B63BAF4F8F71943CF1A63D42B36E3EA5BDBD733FDF6DA08BDC1503BB73FEBFB09FBD2E4BCF2F51A3E7C388D1831424E536B0F1E75AF0EF253525252CCCFD33BC226E7969304CF1E009FF7EC5DF509149D8EEDDA7A4BB1EFD3D7676D5EB734F98DE53C1D6D9AF73EA1F71116937134FF3C610F731FFEF4E9D3E5C03D9EEF9EA7A9E5D9F158F8B94F9F9BEA59E03960CEC68D1B69D3A64DA9A2A2E86776A327A82D068B73D34982D8030034A810E8E1D1BBEBEDBB1B575F6B6187D0EB080FA0DBAF7AF872943E8B7E6464A4343B988F5DE3C178DC471F93DB4E10C41E0008B9171D4B8F8972F4147957845E8F91FB808C7DF8CDC9185DEF35618D9DD03816F945C22228170CC683D803009C1060839BFBBBBBAFD602EF29CF5E4B0F1E42EF026B542BAB0A7F7D613C70813BF079721D6EF2E761FB1C7EF127356D6C6E3F29107B00808E5EBFA2513A6722C4794B73BE5EA20FA177101E7DFA996A3E0FC41E0008BA171ECF13F3DE7B4AF4F5AC0000E01808AA0380CF8BBDB381741CCDC7D1BCB50DB2633B008EB575CE7C7724ADB565C5857D20F436E8ABA3D35A4658EF1C9837C41E00A0A5703B5B417067063C22D766C7B3B5CE918A813D51D642CC1D198807A1B7C22C32F6C3EBC17F847D9A03F386D80300B41076574D2FE1B7B5CE1101D7DBB37747F421F21AC171921FF5E513803E7B007C5EEC99EC98F5CE997D7CBD0F3F47D352D87461058571CCE1CFD5F51384F5529DCE3EC276ABEB9BA9E979AA411E39CF318EBF1656475DC7FBCDB5722C4BE9FEA77EE777F177089B62A34CAD558F3B996CBF6B6F6DFF4DEAF2676AE5E235759D337943EC01007A8BBE371C47D1709B27445FEB6DB9CA9B6791E5E961AB084B147652D87A61D7848D12562053FA52AA38B611B6CB6CFD7BC22E087B4258B40DB1B7948E0576BCB0EEC2FEB651265E5E2EAC92DA12F086B010277E131FB7B1B07F84D515D656D820F5F73CEA60DE107B00404E1679AD3D7A7705DE152FDE1921F7796FDE4413613C7FF0C7EA779E64A6862A802CC65B857D286C9FBAFD45616732093D734B5827613C876D5E1BC773249DB532B11EEE51C59839EDE4FE17D4EFC3C91872972B34F9D4758D1CCC1B620F00C8ED9509FD27CBD1C6D3D723FA5EAEEE9B8F57459DD4CF5FD5E586C25E2563F3FA4C4A1FB07637D3FEE5D5CA01570AFECFC6711C4D67AD4CEC953F70F33731F72DA4CF47D91C990F03F400F07931F6D4A03C7BC774249DB56D96D65BCACBD63A7B69DC19B94F647BC05FAEE56732365D9BBC78B6CBEA36EEBBE666F691AA474FAAF7CB5E722DB33C1AAAFB7F2BECB60DDDB295EE9EB0603B653AAB96A390597ECEFE266B389A373C7B0080273D6F83067968D902905326CB81379F096EDAEE276C2719C3D396503D6F9EB4E6B0B0E3C2AA93B10F9BE1666E7EFF7C9BB0BDC29E12F6B2B0A9EA3A6E1AE766FE716A3EE6ECB5916EB6B025C2169271F09CA532F1E0BDB5C2FE50F7DFE7E46FDA61E33C1C70306FDDC07CF600F8B0D22B8A27BB0EF50C9DEBAEE87B52E43D2BF45E329F3D7BD93C188F07B4A59AAD2F64619D297DA0BACDF49DFBE0935427D54F6D19B0741C6BE902D5E5543B65E2740FC87EB3BBB5FD6DE168DEF0EC0100D921C0D975EC9C36598EF788BC97C1629860617D828DF48999BE27A9CB0FEC1CC75ABA2407CB94E4E66FB24552765D00883D00107B930F98CD15084F4D81EB884043E441EE02620F00F0A02069353A5F8B3EFCEC98161700883D00C083E4CB9757494A4A320406E6CB01950CBD06E8E9E5E97B87C8DFBD7BEF2F324EBA027C1CBC7A07808F52BE7CD9ABFBF71FF380907B7BCC7C475EA773356E7EF6F2C30FFB4E9071C43880D803007C91A0A087F7ECD97384BCE73D7B67DFB5B725FC9E9E16D73B67A65BB468E3BFE2230A773B80D803E0A39C3B7769EA8205DFDE7FF0E081C682AD57A01D7783EC68312D6ECE107995D82D5BF6118E7668000001AC494441545623E3FBDD00620F00F045E2E2E20FA6A6A61E9F3D7B999D94AECE5FEF4CC5402B8F5E4BAFDED63AEF9F63BE6DDB37B78BEB7B898C016C00C41E00E0AB5CBA74ADD7F8F17312F7EF3F4A9E6DCA2772AF1FDF11E1F754485DEF232AEA978D9191BB79D6B537719703C61FA700009FE6FABD7BC907376F8E6ADFB061CDC0B2654BBA999DA2F3BEDE32EFBD770B7DD3A6AF5751148543D0EEC12D0E20F60000E6EC9D3B770F2F5DBAB9434040C09D7AF5AAE6F7F3D33ABAAAA2C33E5ABD8E97F3055E25B66DDB37578F1D3BA7892AF491B8B58109C4C6070098A8E0EFEF3F337FFE7C357AF4681DD7A953B3528D1AD52AA9EF7BF87AC7CBCFDD02CFEFD1F3EB753CEA9E07E3A97DF4DC747F02B73380D803006CC1B3907526E3FCDE3C8B4A204E89D7C2AF52F0808B28328EBAC7603C00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000B992FF078734F7F331A56FAA0000000049454E44AE426082, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203ee-c278-11e9-8c70-2e15a8856301', 1, 'reviewSalesLead.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C646566696E6974696F6E7320786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C220A2020786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E220A09786D6C6E733A62706D6E64693D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F444922200A09786D6C6E733A6F6D6764633D22687474703A2F2F7777772E6F6D672E6F72672F737065632F44442F32303130303532342F4443220A09786D6C6E733A6F6D6764693D22687474703A2F2F7777772E6F6D672E6F72672F737065632F44442F32303130303532342F444922200A09786D6C6E733A7873693D22687474703A2F2F7777772E77332E6F72672F323030312F584D4C536368656D612D696E7374616E636522200A097461726765744E616D6573706163653D224578616D706C6573220A097873693A736368656D614C6F636174696F6E3D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C20687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F322E302F32303130303530312F42504D4E32302E787364223E0A090A093C6572726F722069643D226E6F74456E6F756768496E666F4572726F7222206572726F72436F64653D226E6F745F656E6F7567685F696E666F22202F3E0A090A2020203C70726F636573732069643D2272657669657753616C65644C65616422206E616D653D225265766965772073616C6573206C656164223E0A2020200A2020202020203C73746172744576656E742069643D227468655374617274222061637469766974693A696E69746961746F723D22696E69746961746F7222202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22746865537461727422207461726765745265663D2270726F766964654E657753616C65734C656164222F3E0A2020202020200A2020202020203C757365725461736B2069643D2270726F766964654E657753616C65734C65616422206E616D653D2250726F76696465206E65772073616C6573206C656164222061637469766974693A61737369676E65653D22247B696E69746961746F727D223E0A20202020202020203C657874656E73696F6E456C656D656E74733E0A202020202020202020203C61637469766974693A666F726D50726F70657274792069643D22637573746F6D65724E616D6522206E616D653D22437573746F6D6572206E616D652220747970653D22737472696E67222072657175697265643D2274727565222F3E0A202020202020202020203C61637469766974693A666F726D50726F70657274792069643D22706F74656E7469616C50726F66697422206E616D653D22506F74656E7469616C2070726F6669742220747970653D226C6F6E6722202F3E0A202020202020202020203C61637469766974693A666F726D50726F70657274792069643D2264657461696C7322206E616D653D2244657461696C732220747970653D22737472696E67222F3E0A20202020202020203C2F657874656E73696F6E456C656D656E74733E0A2020202020203C2F757365725461736B3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D2270726F766964654E657753616C65734C65616422207461726765745265663D2272657669657753616C65734C65616453756250726F63657373222F3E0A2020202020200A2020202020203C73756250726F636573732069643D2272657669657753616C65734C65616453756250726F6365737322206E616D653D225265766965772073616C6573206C656164223E0A2020202020202020200A2020202020202020203C73746172744576656E742069643D2273756250726F63657373537461727422202F3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77332220736F757263655265663D2273756250726F63657373537461727422207461726765745265663D22666F726B222F3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77342220736F757263655265663D22666F726B22207461726765745265663D2272657669657750726F6669746162696C697479222F3E0A2020202020202020200A2020202020202020203C706172616C6C656C476174657761792069643D22666F726B22202F3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77352220736F757263655265663D22666F726B22207461726765745265663D22726576696577437573746F6D6572526174696E67222F3E0A2020202020202020200A2020202020202020203C757365725461736B2069643D22726576696577437573746F6D6572526174696E6722206E616D653D2252657669657720637573746F6D657220726174696E67222061637469766974693A63616E64696461746547726F7570733D226163636F756E74616E637922202F3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77362220736F757263655265663D22726576696577437573746F6D6572526174696E6722207461726765745265663D2273756250726F63657373456E6431222F3E0A2020202020202020200A2020202020202020203C656E644576656E742069643D2273756250726F63657373456E643122202F3E0A2020202020202020200A2020202020202020203C757365725461736B2069643D2272657669657750726F6669746162696C69747922206E616D653D225265766965772070726F6669746162696C697479222061637469766974693A63616E64696461746547726F7570733D226D616E6167656D656E74223E0A20202020202020202020203C646F63756D656E746174696F6E3E0A20202020202020202020202020247B696E69746961746F727D20686173207075626C69736865642061206E65772073616C6573206C6561643A20247B637573746F6D65724E616D657D2E2044657461696C733A20247B64657461696C737D0A20202020202020202020203C2F646F63756D656E746174696F6E3E200A2020202020202020202020203C657874656E73696F6E456C656D656E74733E0A09090920202020202020203C61637469766974693A666F726D50726F70657274792069643D226E6F74456E6F756768496E666F726D6174696F6E22206E616D653D22446F20796F752062656C69657665207468697320637573746F6D65722069732070726F66697461626C653F2220747970653D22656E756D222072657175697265643D2274727565223E0A090909202020202020202020203C61637469766974693A76616C75652069643D2266616C736522206E616D653D2259657322202F3E0A090909202020202020202020203C61637469766974693A76616C75652069643D227472756522206E616D653D224E6F20283D2072657175657374206D6F726520696E666F2922202F3E0A09090920202020202020203C2F61637469766974693A666F726D50726F70657274793E0A0909092020202020203C2F657874656E73696F6E456C656D656E74733E0A2020202020202020203C2F757365725461736B3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77372220736F757263655265663D2272657669657750726F6669746162696C69747922207461726765745265663D22656E6F756768496E666F726D6174696F6E436865636B222F3E0A2020202020202020200A2020202020202020203C6578636C7573697665476174657761792069643D22656E6F756768496E666F726D6174696F6E436865636B22206E616D653D22456E6F75676820696E666F726D6174696F6E3F22202F3E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77382220736F757263655265663D22656E6F756768496E666F726D6174696F6E436865636B22207461726765745265663D226E6F74456E6F756768496E666F726D6174696F6E456E64223E0A20202020202020202020203C636F6E646974696F6E45787072657373696F6E3E247B6E6F74456E6F756768496E666F726D6174696F6E203D3D202774727565277D3C2F636F6E646974696F6E45787072657373696F6E3E0A2020202020202020203C2F73657175656E6365466C6F773E0A2020202020202020203C73657175656E6365466C6F772069643D22666C6F77392220736F757263655265663D22656E6F756768496E666F726D6174696F6E436865636B22207461726765745265663D2273756250726F63657373456E6432223E0A20202020202020202020203C636F6E646974696F6E45787072657373696F6E3E247B6E6F74456E6F756768496E666F726D6174696F6E203D3D202766616C7365277D3C2F636F6E646974696F6E45787072657373696F6E3E0A2020202020202020203C2F73657175656E6365466C6F773E0A2020202020202020200A2020202020202020203C656E644576656E742069643D2273756250726F63657373456E643222202F3E0A2020202020202020203C656E644576656E742069643D226E6F74456E6F756768496E666F726D6174696F6E456E64223E0A2020202020202020202020203C6572726F724576656E74446566696E6974696F6E206572726F725265663D226E6F74456E6F756768496E666F4572726F7222202F3E0A2020202020202020203C2F656E644576656E743E0A2020202020202020200A2020202020203C2F73756250726F636573733E0A2020202020203C73657175656E6365466C6F772069643D22666C6F7731302220736F757263655265663D2272657669657753616C65734C65616453756250726F6365737322207461726765745265663D2273746F72654C656164496E43726D53797374656D222F3E0A2020202020200A2020202020203C626F756E646172794576656E74206174746163686564546F5265663D2272657669657753616C65734C65616453756250726F63657373222063616E63656C41637469766974793D2274727565222069643D2263617463684E6F74456E6F756768496E666F726D6174696F6E4572726F7222203E0A2020202020202020203C6572726F724576656E74446566696E6974696F6E206572726F725265663D226E6F74456E6F756768496E666F4572726F7222202F3E0A2020202020203C2F626F756E646172794576656E743E0A2020202020203C73657175656E6365466C6F772069643D22666C6F7731312220736F757263655265663D2263617463684E6F74456E6F756768496E666F726D6174696F6E4572726F7222207461726765745265663D2270726F766964654164646974696F6E616C44657461696C73222F3E0A2020202020200A2020202020203C757365725461736B2069643D2270726F766964654164646974696F6E616C44657461696C7322206E616D653D2250726F76696465206164646974696F6E616C2064657461696C73222061637469766974693A61737369676E65653D22247B696E69746961746F727D223E0A20202020202020203C646F63756D656E746174696F6E3E50726F76696465206164646974696F6E616C2064657461696C7320666F7220247B637573746F6D65724E616D657D2E3C2F646F63756D656E746174696F6E3E0A20202020202020203C657874656E73696F6E456C656D656E74733E0A202020202020202020203C61637469766974693A666F726D50726F70657274792069643D2264657461696C7322206E616D653D224164646974696F6E616C2064657461696C732220747970653D22737472696E67222072657175697265643D2274727565222F3E0A20202020202020203C2F657874656E73696F6E456C656D656E74733E0A2020202020203C2F757365725461736B3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F7731322220736F757263655265663D2270726F766964654164646974696F6E616C44657461696C7322207461726765745265663D2272657669657753616C65734C65616453756250726F63657373222F3E0A2020202020200A2020202020203C7461736B2069643D2273746F72654C656164496E43726D53797374656D22206E616D653D2253746F7265206C65616420696E2043524D2073797374656D22202F3E0A2020202020203C73657175656E6365466C6F772069643D22666C6F7731332220736F757263655265663D2273746F72654C656164496E43726D53797374656D22207461726765745265663D2270726F63657373456E64222F3E0A2020202020200A2020202020203C656E644576656E742069643D2270726F63657373456E6422202F3E0A2020202020200A2020203C2F70726F636573733E0A2020200A2020203C62706D6E64693A42504D4E4469616772616D2069643D227369642D36323861386432632D303030392D346461302D396332612D343132636637363031356138223E0A2020202020203C62706D6E64693A42504D4E506C616E652062706D6E456C656D656E743D2272657669657753616C65644C656164222069643D227369642D35636232663863332D333838392D346131322D386135622D623866393035353136393565223E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D227468655374617274222069643D2274686553746172745F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2233302E30222077696474683D2233302E302220783D2237352E302220793D223330302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2270726F766964654E657753616C65734C656164222069643D2270726F766964654E657753616C65734C6561645F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223136352E302220793D223237352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2272657669657753616C65734C65616453756250726F63657373222069643D2272657669657753616C65734C65616453756250726F636573735F67756922206973457870616E6465643D2274727565223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D223332302E30222077696474683D223534342E302220783D223331352E302220793D223136302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2273756250726F636573735374617274222069643D2273756250726F6365737353746172745F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2233302E30222077696474683D2233302E302220783D223336302E302220793D223330302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D22666F726B222069643D22666F726B5F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2234302E30222077696474683D2234302E302220783D223433352E302220793D223239352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D22726576696577437573746F6D6572526174696E67222069643D22726576696577437573746F6D6572526174696E675F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223531372E302220793D223231302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2273756250726F63657373456E6431222069643D2273756250726F63657373456E64315F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2232382E30222077696474683D2232382E302220783D223637302E302220793D223233362E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2272657669657750726F6669746162696C697479222069643D2272657669657750726F6669746162696C6974795F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223531372E302220793D223336302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D22656E6F756768496E666F726D6174696F6E436865636B222069643D22656E6F756768496E666F726D6174696F6E436865636B5F677569222069734D61726B657256697369626C653D2274727565223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2234302E30222077696474683D2234302E302220783D223636342E302220793D223338302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2273756250726F63657373456E6432222069643D2273756250726F63657373456E64325F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2232382E30222077696474683D2232382E302220783D223736352E302220793D223338362E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D226E6F74456E6F756768496E666F726D6174696F6E456E64222069643D226E6F74456E6F756768496E666F726D6174696F6E456E645F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2232382E30222077696474683D2232382E302220783D223736352E302220793D223334352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2270726F766964654164646974696F6E616C44657461696C73222069643D2270726F766964654164646974696F6E616C44657461696C735F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223636302E302220793D223532352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2263617463684E6F74456E6F756768496E666F726D6174696F6E4572726F72222069643D2263617463684E6F74456E6F756768496E666F726D6174696F6E4572726F725F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2233302E30222077696474683D2233302E302220783D223738332E383632303638393636303331312220793D223436352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2273746F72654C656164496E43726D53797374656D222069643D2273746F72654C656164496E43726D53797374656D5F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2238302E30222077696474683D223130302E302220783D223931302E302220793D223237352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E53686170652062706D6E456C656D656E743D2270726F63657373456E64222069643D2270726F63657373456E645F677569223E0A2020202020202020202020203C6F6D6764633A426F756E6473206865696768743D2232382E30222077696474683D2232382E302220783D22313035302E302220793D223330312E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E53686170653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F773132222069643D22666C6F7731325F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223636302E302220793D223536352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223538372E302220793D223536352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223538372E302220793D223438302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7739222069643D22666C6F77395F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223730342E302220793D223430302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223736352E302220793D223430302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7737222069643D22666C6F77375F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223631372E302220793D223430302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223636342E302220793D223430302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7738222069643D22666C6F77385F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223638342E302220793D223338302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223638342E352220793D223335392E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223736352E302220793D223335392E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7733222069643D22666C6F77335F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223339302E302220793D223331352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223433352E302220793D223331352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7734222069643D22666C6F77345F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D223333352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E352220793D223430302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223531372E302220793D223430302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7736222069643D22666C6F77365F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223631372E302220793D223235302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223637302E302220793D223235302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7731222069643D22666C6F77315F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223130352E302220793D223331352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223136352E302220793D223331352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F773130222069643D22666C6F7731305F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223835392E302220793D223331372E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223931302E302220793D223331352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F773131222069643D22666C6F7731315F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223739382E302220793D223439352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223739382E383632303638393636303331312220793D223536352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223736302E302220793D223536352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7732222069643D22666C6F77325F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223236352E302220793D223331352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223239302E302220793D223331352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223331352E302220793D223331362E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F773133222069643D22666C6F7731335F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D22313031302E302220793D223331352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D22313035302E302220793D223331352E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020202020203C62706D6E64693A42504D4E456467652062706D6E456C656D656E743D22666C6F7735222069643D22666C6F77355F677569223E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E302220793D223239352E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223435352E352220793D223235302E30222F3E0A2020202020202020202020203C6F6D6764693A776179706F696E7420783D223531372E302220793D223235302E30222F3E0A2020202020202020203C2F62706D6E64693A42504D4E456467653E0A2020202020203C2F62706D6E64693A42504D4E506C616E653E0A2020203C2F62706D6E64693A42504D4E4469616772616D3E0A3C2F646566696E6974696F6E733E0A, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203ef-c278-11e9-8c70-2e15a8856301', 1, 'FixSystemFailureProcess.png', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x89504E470D0A1A0A0000000D49484452000002D3000001A0080600000068AAEBC3000000206348524D00007A26000080840000FA00000080E8000075300000EA6000003A98000017709CBA513C0000000467414D410000B18E7CFB5193000000017352474200AECE1CE900000006624B474400FF00FF00FFA0BDA793000000097048597300000EC400000EC401952B0E1B000020004944415478DAEC9D077814E5F687CF9200A18B86AE94A818213443952010420F354850FAC5F8BF4044B9D204225D100B88414108103A4A09101104042FE8A56A2842448108095254403152B3FFEF7CD9DD6CDAEECCEE6CCDEF7D9E797677765A26B333EF9C39DFF988000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001C800EBBC0BDD1EBF57E1F7DF451F4C18307FB2527273F75FCF871BFDBB76F63C700008087E1E3E3A3AF5EBDFAEF152B563C74F9F2E577CF9E3DBB077B0500C8347020717171FF5EB972E57BE7CF9F2F3E64C8100A0909A1A64D9B929F9F1F760E000078181C08494A4AA2FDFBF7D3E2C58BEFFEF5D75FA72F5DBAD44F7C75127B0700C834D01871A24D98387162B7B163C752747434F9FAFA62A700008017111B1B4B313131FFDCBD7BF785F4F4F4CDD8230078263ED805EE29D2B366CDEAB66EDD3AEAD5AB17152A54083B050000BC8CC68D1B53787878E1D5AB577716327D428C3A83BD0200641AD809A7764C9C38F17516694EE9000000E0BD942F5F9E3A75EA5474D9B2659DEEDFBFFFB91875157B0500C834B0116E6CF8FAEBAFEF1E326448618E480300002818425DB468D1F43D7BF63C939191118F3D02806781FC013782AB76706343CE91060000507018356AD4C3A54B977E52BCC523490020D3C056B8FC1D57ED40634300002878F4EDDBF7B27889C09E0000320D6C84EB4873F93B000000058F5EBD7A55112FADB02700F02C10027523B84316343A0400808249E3C68D2B899772D813007816884CBB115CD01F1DB2000040C1C470FE47900B00C834000000000000906900000000000000641A000000000000C8340000000000006E031A3A0000F22529E93BDAB06123EDDDBB978E1C392A1BC9023B4FBABE3E54BF7E10B56AF51C4544F4A1A64D9FC54E010000C83400C09B484E3E4D23478EE4DAE73464C8BF68DAB42942FA9A58A836A3CFE3BDA571B64CE31DAF7C439294944CFBF71FA5A8A84154B1E2A33467CE3C0A0A0AC28107000090690080A7B36BD797D4BFFF401A3B76346DDD9A40BEBE852D4C6D14455D1EEF75396439AFEFF29AC6BBF1F32B2A6E4CEA89A12E8D1A359862635751DBB66D68D1A2C5141EDE050720000040A601009E2CD243870EA34D9BD693B20E847466526D49A89548B3D6829DDF72DC4BE0A3A35FA456AD1A51972EC3E5B6858787E3400400000F020D100100124EEDE088F48A15F139445A6761C829D53A0BC29DDFB43A2BA2AE747EA5CB2385D33B8FA0A02769EBD6791415F5129D3C7912072300007810884C0300249C23CDA91D798B7476121313293E7E39FDF6DB352A59B2A4CCABEEDEBD1B598E46DB1B11B6757EB5F3B926721D14F4044D98F0B2F83FBC463B77EEC2010900001E0222D3000059B5831B1B46470FCB47A4B3A2D193274F91227DF1E24559E1E3F8F113347DFA0C9A38F14D0FDF0BAE8F504747F7A6CB972FD181030770500200808780C834004096BFE3E872DE8D0DB3249323D2870E1DA29D3B77D2FDFBF7E5B85BB76ED1850B17E8B7DF7EA33A75822832F2792A880D0BB562E0C008F1FFD8A030671D000080AB41641A0020EB48878434CF53A0CD898F8FA763C78E9944DA9CD4D434218283F359830E3B5921212175E4FF030000806780C8340040A66B701DE92CF4790AF0E5CB97E9D2A54B54A142050A0C0CA42B97AF905F313FD267E829BC4BB894EDBC41945A29F5EB07505252127604000040A601009E02772492D9214B5E129D354E4AF45381B47AF56A0A0808C8B59C2C99863CDB0AD7A1CE2BF20F0000C03D419A0700201FF494BDF73E3D0D1A34504A77993265724DBD6BF72E0A0E7EC6C3FF5E00000000320D00B04B26F5790875E6101EDE996A07D5A2D036A174FBCEED6C738F1E3D9A962D8BCB67395AC8AADE49F341AA01000040A601001A0BF58D1BD769E6CC59F4C30FA7286E711CF915F5CB36E7638F3D26BE9F4D172FA65A9153BD857559DB164BF32B5D1E299C1E000000804C0300EC12EACCF74B97C65344446F7AF2A94099E251B972E55C73D5AE5D9BAA55AB41917DFA51DC92781B84556BB9D5AB1CEF4EFB1E000080A780068800003391D3657BCF35A4870E1D4E018F3F491B133693AFAF2F55A9F2280D193284BA76ED4A75EAD491539F3B774E76FA326ACC58EADBBF3F4D18FF06C5CE5F48D1C35FB622CC7AEC730000001E0D22D300801C72972978292929141EDE8DFAF51F4463C68E9322CDD4AD5B97D66FDA44152A57A223478FD2814307A952E5CAB472F51A59ED83A77B7BF63B74F4C8F7B476DD4685E2AC57F9EA6AE9559A32A2F4EF8054030080A782C83400C04CE8B222D31D3B86CB6874D5AAD5724DC9A91EBD7B475A5CDADC79F3A84B7867EA13D98372E760DB2B905ACBB75A39B626C190630000282820320D00C8218199221814549B7C7C6CBFDF3646B2AD8BB433A2D18810030000804C03009C24D2FC5AAF5E1D3A7CE890E9DB952B56285A4AFED32911692D8418000000804C03005C2CD50D1B3E43274E1C378D5DB56AA5A2B9734FA7B722D25A09B3ADF3AA2DDD077907000000990600E423D1461A360CA6EF8E7EA7F1F22DBD5722C1F644A5ED95645BEB5DDBFA0A0000C01340034400409E92E8EFFF309D3AF50375EAD85E7EBE7AE58AE9BD25CCA73B77EEAC1569B545A06D116500000000320D00708244679259D52335F5271BE7B7F6596DA3445BC5596D1418520E000000320D00B059A6751A48A4DECA385B44DA5929205A4BB956EB070000009906007888509BA3B34304D546A6D5C8B51692ED888685B6E65543AA010000320D00F00291D6692077B6F416A8955CAB99462B0106000000990600008BA2A85331AD12A9D652A4B5884EDB73D300E9060000C8340000E4D10051B9385B93586BE2AC954893C2EF6C15647BF62B4AE3010000641A00E0E532ED8C068896A45AEB28B53D52AB468C4983FD060000C01341A72D00801C32987350377D54D4083A7CF8BB7C96A177824867316CD838B12D49E4984E5E94DC2CA82DC507000000320D00F00291B626D8F94F97909048696997AC2C376717E34ADE5B16E9D8D82574F0E077D9BE6FDEBC11952FFF888265E42FB5B1B1CBC572931408B016A5F820D50000E06920CD0300E000A9CB12E12D5BBEA0E0E0FA74E9D2AF424A8F50A346CF509326C1F4E5975F51EDDA8154A54A25D33CEBD66DA2E79FEF46850A65A69B24267E29E6394A1D3A840A316E2CA7F9FDF7EBB47DFB6EF91A1C5C570A33479F972C59433FFF7C9E52522E52646417397FA95225C8D737EB34B76DDB57629A147AEEB9C6F4D75FB7A86AD52A54AD5A65F91D8F3F78F07BBA79F32F6AD0A016356BF68C58EE31B1DCCFC477BF88E5A68AE576362D2B31718F98FE98D8B616621B9EB149D4010000783E884C03007208B0D2540F65D30E1DFA1F99FA317EFC54FAF5D72BD4A245273A73E66721B63BE99D773E34CDF7F5D7DFD2DCB90B4D221D1D3D8E962F5F47B56AD5A40103860B29DF2EC7B76EDD8392937FA672E51E11CB392BC7DDBAF537DDBD7B4F0AF2EFBFFF61B6EE374C691E51516368C68C7962BE8769F1E235D4BBF730FAEF7F0F9AA61D3F7E36FDFDF73F42BE7DA86DDB0152A46FDD4A372CF76FB1DC1BA6BF2F3A7AB2D8B604B16D8F8B6D1B23B66DB70D372A906E0000F006109906C00B983E7D3A05050551787878B648AC7A99D6591140B5629E494040758A8D7D5BBEDFBB773F1D3B7692FEF5AF17A94D9B9EF4FEFBD3844017A24F3E89A7975EEA27A739772E4508EF0A4A4FBF6092EBE5CB3FA5AE5DDBD1A9536768E1C277A859B360D3F25BB77E96AA577F8CDAB56B4991915D736D33479D972EFD54C8FC1129E12FBCD055ACE3423681FDF4D3F9A6F7DBB6ED15A27E9EFAF6ED2696FBA8586E88586E27C3B65D10DBF699D8B6E366DB9620B62D14820C00009069008027121313235F8B172F2EA4AE2B0D1C3890424242A864C9923648B056642D2B2CACA5E97DD9B2652823E301D5AD5B9BAA54A94889893B640AC7D6AD3B282E6EAE9CEFE4C9D374E7CE5DAA50A1969C87DF57AC585EBEFFE8A359429E7B52A74E6D68D6AC8954B3668D3CB63DFBDF71F2E48F54BA744929D2C6EF58E0CDA73D7E3C99E6CF5F4ED7AFDFA4A3474F504444FB3CF7C7C993670CDBD6CC6CDBFC55EE134837000040A601006E477A7A3AAD5DBB560EA54B979691EAFEFDFB53AB56ADC8CFCF4FA548EB54AC5969D58FECE3060D7A81962C594DE7CEFD420306F416DB58547E53A448612A53A6345DBB763A9724BFFC723FEAD6AD3DBDFD762C356DDA895253BF1337117E16849597E72BD335CCBF630936929C7C96DAB6ED473B76C453FDFA4F53CF9E43F395E0CC6D2B25B6ED00041900000072A601F056FEFCF34F5ABD7A3575ECD891CA962D2B647500252424D0FDFBF75508B2D2C1D2BCF94B77DFBE11B231E18205CB0C291E99F384853D47254A14A7F8F875D9E6CDC8C890F9D1152AF8D3AC5913644EF3850BA99927B34285CCF2A5B30B7858580B2A56CC8FA64D9B2773A8274C784746AB8D691A274E2453A54AE5A548DFBE7D87D2D2AE98F6134F639E2F1D16D65C6C5B31B16D9B489B1AD5906E0000F064109906A00070FBF66D5AB162851C4A952A456DDBB6A5A8A8A81C116B47CA5CDEA5F138EDA25DBBD6949A7A49886CEDAC1393AF0F6DD9B25CC8F65021DAF154B26409F2F77F586CFF8754A346636AD4A8BEACDA111D3D8802039F90CBE25C696E70B86BD73EDAB87151B6B573647ADBB6651413F32E6DD8B08D468E7C898283EB88F145E4BCA1A1CD8460BF4BB56AB517371EA565758EA95363A96EDDA764058FA143DF14CBFD462C77BED8B64262DB16886D7B5D6CDB1AC3B63D446BD6BCA7429CADED2B0000009E820EBBC0ADD00BB0173C8C9494145AB66C994BB761CA942936CFFBF8E38FD3D9B367C5C1774D83D3825EE5777AAB9FD3D3FF918D2A59888D703A4BE6B8C2D9E6B97BF7AE419E0B5B14598E3A57A9D244C8F10221CEC1D9D6654C19E108B59F5F911CCBF5CDB16DB7A55C5B5B9F9AA8744A4A9AB86108A7499326B9F4981A34681055AF5E1D3F70675F94753A5C9B01F030109906C04E58A4ED915957C322AD5C881D25D6F97FCE9E0F9DF95DF1E2C5F294D12CA9CD2DAB3131EFC9EFAB56AD4C8B17AFA5060D6A6713E99CEB328AB4314F3AAFF5152F5E54C5DFAA2C1564D9B2AD76DF2069C5E4C993F103070000C83400CEC355D14425E2C539C53CE4CC990E0808A073E7CE59903C9D8DC29CC59E3DFB65BDE80A15CA5999D79264E7FD9E73A02B57AE202B83588A0A73393CCE8DE6868831312364B9BBFC25574D7499ACCCA74EB2070DEA22FE9F0BDDFA58020000009906C02122EDAA485E7E02C4F2ECE3E343F7EEDD938DF77878E8A187282C2C8CFAF6ED4B1D3A749039D38647CB7609B3A579FAF51B46F3E7CFA2EEDD3B285CBE5EB1604F993247F678D8BF7F4F8BF3D6AAF5841C6C136235D3DB96DE61A47AF5CC1E215D19158650030000641A80020BCB73E1C28565A343A34073BD6916E7C8C84829D22CD4DA48B31AA9569EDAA144A295BFB7655A4744A9D5A482000000804C03005CC283070FE4C002DDAB572FEADCB9B3AC37ADBECE34A3A41D9472013C7CF87B3A78F03B598DA34993674CF3730F853CFEE6CD3FA94183A06CBD1B6ED9B2838283EBCA140D2E53D7AEDD7362DC4E59CDE3B9E71A9B6D835E8E6FD4A8AE2C73C77CF6D9E71412D2D0F099BFDF25F645A8ECC5F0E0C124B1BEBFC4FA6A89F53530FD1DDC357870706DB1BE1F0DEB0B91DF2526EE15F31C13372521B2DA87ED020D710600006F0275A601F02238E2DCBD7B77DAB469135DBC7891962E5D2A85DABA485B1265B535A6F39A97E8934F56D0F8F133E9D75FAF528B16DDE9CC99B3A6EF78FCDF7FFF2D4BE2B56DDB47E6411BE7E572777DFA0CA5254BD68A79AFD088116FD29C398B64ADE90F3E58227B2B34B266CD66F1B7EF90EFCF9C3947BD7B0FA755AB36CBE5F07A5F7D75AA14E4F1E3DF15EB4B37AC6F8058DF31D332B80C5E9F3EAF89F5AD17F3645638898E9E26BB0CAF55EB711A30609C10EEAF54CAB12DA9200000003C0144A601F002A64D9B26CB98D92FCE8E424F0101D528367686FCB477EF3774ECD80FA6AEC03FFD748169CA6DDBBE9222DCA8513DD336858636A729535EA7B4B45F69C890D142728F50B9720F5364643875EA34D0B48E0E1D5AC93AD2C386F5A3C4C4DDB283951D3BBEA651A35EA2CD9B77528F1EED0CEB9B67B6BEBD627D29627D754CE342439B8AF58D90EF398ABD78F167949EFEBDA90BF2E5CB3753D7AEAD3590638834000040A601002E67E2C4899A08AF36E56DF31642EE85D0F87DD9B265642EB791E3C74FD1FCF9CBE8FAF59B74F4E8718A88E8986D395CC68E3F7314BA74E99252A48D640A6EE6B41D3A3C47515163E9FEFD07B475EB6E8A8B9B4541411D64059375EB1269D6AC3172DAE3C793C5FA561AD67752ACAF5DB66DE5D40FE3B69E3CF993EC7ABC4285CCEA1FFCBE62457F1B441AE20C00009069008097A377E0F2F2EE053139F9676ADBF605DAB16315D5AF5F8B7AF68CB2B03D7AB3D27EB9BFE3D48FA0A0A784386F95A21C105055D6926691FEF9E75FA849937A627D67C5FA068AF52D95DD87F7EC39CCC2F66676D452A64C29BA76ED5BB23D171A39D40000E0AD20671A0090437695E6445B9A474F7957E3C83DFEC489D3B281208B34F73A989676D920CCB9C59B533FB8D7C16FBE392CC7EEDAB59F0E1EFC3EDB741D3BB6A471E3DEA66EDDC2E4E7F6ED5B88CFB32922A283617D3F8AF5959322CD154FD2D2AEC848767E51E4B0B067A9448962141FBF295FE146941A00000A2E884C0300CC844E97CF78B5CB51FABD5EE6434F98309B6AD56A2DD33F9A376F4853A7CEA5BA75032938B84EB6695982DF7F7F22B56EDD874A962C21C4B9153569D220DB72DBB56B416FBDF591ACDAC170DEF4E8D1B3844CB737ACAF9958DFFB627D1D0CEB0B16EB8B15EB7B4AACAF56AEEDE4468A5BB6CCA7BE7DC7D08205EBC47A8B93BF7F595AB366B60A59B6B47F20D20000009906007891506BBFBCB4B423D93E27262E337D7EE49187E8CC99AF293DFD1F5377DE6FBD3586FCFC8A1AE63D986D8923460CA27FFFBBAFCCB9CE9C26BBC4B66CD998F47A63A510BD8C40EBF53F99A678E49132627D5FE658DF4853F7E16969FFCDB54C2E95979C9C28A3E22CD79CFA615D96D5D4BC0600000099060078B1486B11B1B62C9F46B16532C536FF086E912285297BCA88FA0E5E72AFCFBAF866CE636F0F89D6A6070000009906001420D1B6651EBDC2F1F675356E7D3A2D7B52B447AE01000040A601000550989D25D56A245AAD243B43A4D1E010000020D30080022AD33A0D055CAD58EBEDF86C8F603B52AE21D200000099060040B2ED9ECFD6A8B423251A220D000000320D0070AA343B4AACB5946AAD04DB1EB98648030000641A005080645A67C7BCB64EEF4CA97666941A220D00009069004001976B472C47CB540F4B72EB2C798648030000641A00007976C9F21D1195B6459CED95678834000040A601000558A6751A2CC39E691D9DEA618B60DBDB1011000000641A0050C0A4DA99CBD3AB98CF11916935920C9106000000990600385CA26D598F563D23DA22D11069000000906900804789B9333B71B157B06DC9BD0600000099060040809DB27C4775E262AB54231A0D000000320D00B04B767536CE678F5C3B4BAAB54A038148030000641A000034156535CBB037D5C31E89B645B091D601000000320D007088383B4AAC5DD5894B7EDF41A201000040A60100794AA3B3EA4C5B9BDED99DB828156C8834000000C83400403321D66A99EED8898BF9674834000000C83400200FFCFC8AD2EDDBB7E5ABEB85DD55A91E96A6759E48DFBEAD275F5F9C9A8117DEA6EBF57E1F7D342FFAE0C1C3FD92937F7CEAF8F1137E7CDE01F6E1E3E3A3AF5EBDF2EF152BFA1FBA7CF98F77CF9EFD650FF60A641A00E0641A36AC47070E1CA356AD1A3B4196ED91675BA5DAFD25DA4852D245AA5FBF3E0E4AE055C4C57DF2EFD0D056EF9D3F9F527CC8907FD1E0C183A869D326E206DE4FC1EF5CAF609C2DD378C7ABB821D1252525FBEFDF7FB4D3E2C51BC22A572E7FFAD2A5ABFDC4572771E441A601004EA255AB96244EC4E2B59103D7E26E553D2C7D765D4AC7FEFD27C4FFA1150E4AE0352C5EFC49C284096F761B3B763445470F235FDFC20A7EEBBA3CDEEBF2F86DE6FC4E47052D258B9F28366D5A4F0C7569D4A8C145626357D58B899977E8EEDDBB2FA4A7DFDD8C23D0F114C22E00004444BC4071711BE8FEFDFB860B51CE21BF8B9E9AC1DABC96BECF6BBC9271B67C76E585B808C5C77F26FE1F11382881D788F4AC596F77DBB4693DBDF6DAAB5644DA28C73AB3F794E37D5EDF595A96D26949C5F2948E770DD1D12FD2BE7D2B8A152F5E6285F8188EA310320D007002F5EB37A0C0C0A7293676950A71B68652B1562ACF6AA55AAD642BFDBB1C476CEC76AA58B122356DDA140725F07838B563E2C437BBAD58119FE398D659189488B0CE8A345B935EA5F32B5D9EA3E4DD7682829EA43D7B96942A56AC280B75108E46C83400C009CC99F321BDFDF6523A702089D4479DED8D665B9367A5024D56E6754F89664E9EFC9D66CC785FFC1FE6E060041E0F37365CB972D57B9CDAA144A413133FA7E79F8FA4D6ADDB50972EDD282161B302A9B5575A754E9ACF35721D14F4044D9D1A9DE1EBEB3B0F4724641A00E00402030369C58A55D4BFFF04D918D186CB27B967AA87DECA67D7C322DDA3C7705AB46891B8002288043C1FAEDAC18D0D39473AB7486797EAC993A7507CFC72BA78F1221D3972948E1F3F41D3A7CFA08913DFF4F0BDE0FA08F5A851831E2E5DBAC493E22D1E7741A60100CE202C2C8C3EFE781175E93282E6CE5D6D21875A8BBC6A6BD39206024DA42C72ED2A8A506CEC57D4B6ED4019910E0F477A23F00E0E1E3CD28FAB76E49D239D259989898974E8D0214A484810F31CA45BB76ED1850B17E8E8D1EF68E5CA95B46EDD676E23A69E4ADFBE9D2F8B1734C47020A8E60100C825D4FBF6EDA7912347D2071FACA42143222924A40E356DFAB44675A8B5EEC085487D7D69D7C175A4B9FC1D57EDE0C6869C23BD73E74E44A48157919C9CFCD4E0C103F3146873E2E3E3E9D8B163861BF7ECA4A6A6D1C081832932F2F97C841C1D2929A157AFB6553EFC70752BEC09C83400C08970CAC7175F7C21A42F89366CD84031319FD0912347081D2C6870D2F5F59575A4B9FC1DA775A0B121F046B84316AE239DFD8636B7505FBE7C992E5DBA44152A5490E79D2B97AF905F313FD267E829BC4BB8946DDB6FCA01D3B8715025F1520E7B02320D0070012C7DE840C47B99356B16BDF1C61B797E3765CA1439983369D2249A3C7932761CB04A668FAA7EF94874D63829D14F05D2EAD5AB29202020D772B2641AF26C2B86278AF03D07829C69000028C0374B6A78E28927B0D3801DE46E003C68D04029DD65CA94C935F5AEDDBB2838F8190FFF7B01641A000080D7D2A14307F2F7F75734ED430F3D44BD7AF5C24E0336C864FEE52FC3C33B53EDA05A14DA26946EDFC99E46367AF4685AB62C8EF2EF5049AFD1F6397A3E4835641A000080D7D2BD7B7745D3718E77E6637B00B411EA1B37AED3CC99B3E8871F4E51DCE238F22B9AFDF87AECB1C7C4F7B3E9E2C5542B72AAB7B02E6BDB62697EA5CB2385D303C834000000AF23323252D17443860CC1CE021A0875E6FBA54BE32922A2373DF954A04CF1A85CB972AEB96AD7AE4DD5AAD5A0C83EFD286E49BC0DC2AAB5DCAAAD32E44EFB1E381224A4030040012624244496E7E3AA0AF951B26449391D00EA454E97ED3DD7911E3A7438053CFE246D4CD82CABDB54A9F2A8BC59EBDAB52BD5A953474E7DEEDC392EAF47A3C68CA5BEFDFBD384F16F50ECFC85143DFC652BC2ACC73E074E079169000028C070EA06E74E5B8273A539671A00DBE42E53F0525252283CBC1BF5EB3F88C68C1D27459AA95BB72EADDFB4892A54AE44478E1EA503870E52A5CA9569E5EA35B2DA074FF7F6EC77E8E891EF69EDBA8D0AC559AFF2D5D5D2AB346544E9DF01A97626884C0300400167E0C081B46CD9B27CBFEFDCB9337612B05118B322D31D3B86CB6874D5AAD5724DC9A91EBD7B5B4E399A3B6F1E7509EF4C7D227B50DE9D32E9EDDC562DE55BAD1C5B9360C8B13B83C83400001470388523BFC833A778A09B73609FA4668A6050506DF2F1B13D86678C645B17696744A311210690690000006692D2AF5FBF3CBFE3141054F100F68A34BFD6AB57870E1F3A64FA76E58A158A9692FF744A445A0B210600320D0000C00AEDDBB7CF73BCD26A1F005893EA860D9FA113278E9BC6AE5AB552D1DCB9A7D35B1169AD84D9D679D596EE83BC43A6010000783C1C817EF4D147B38DE3D48FB0B030EC1C6097441B69D83098BE3BFA9DC6CBB7F45E8904DB1395B657926DAD776DEB2B7014688008000040A67AB0389B3744E4CFA8E201B4925E7FFF87E9D4A91FA853C7CCA72057AF5C31BDB784F974E7CE9DB522ADB608B42DA20C00641A0000400E7256F5E8DBB72F760AB05BA233C9ACEA919AFA938DF35BFBACB651A2ADE2AC360A0C29874C0300002830346DDA34DB676BF5A701B02EC33A0D24526F659C2D22EDAC1410ADA55CABF503AF9469BD5EEFB773E7CE81DBB66D7BF1CC9933F54F9F3E5DFCDAB56BBEDC5B52D1A245F5FEFEFEF7AA54A99256AE5CB96F1F3C78306DFBF6ED3FE2DF070000DAC1553B02030365CF73D5AB5747150FA091509BA3B34304D546A6D5C8B51692ED888685B6E6557BBC5473038EEE62682986FA62A828869262B82D06EEAE35490CDF8861AD18520BBC4CB3444F9D3AF593D0D0D0172E5EBCE8DBAA552B1A3C7830050505C9C6305CE35408B5EEB7DF7E2B72F2E4C91ABB77EFAEB175EBD6176BD6AC79E18F3FFE982EC62FC6C90A0000B461E8D0A1F4EAABAF527474347606D04004751AC89D2DBD056A25D76AA6D14A800B342CD0C30B152AF45C46464691BCEEF9C550DD30F0B4EF8861AF18E2C4B0D2551BED52995EBF7EFDBFC2C2C23EBA72E54AD1993367CA478A5945D9B360A1E6812325DC79C09C397374DBB76FAF3661C2840562FAD72F5FBEDC454CF6338E41E00A6AD4A891ED15004F65D7AE5D3469D2247AEDB5D768FAF4E9F4D4534FA1C316A08150E7854EC5B44AA45A4B91D6223A6DCF4D438194EEEA3A9D6E815EAF972D4D8548AB99B79561F83F310C76850FBA44A6C5CEF29D3D7BF697D1D1D1AD5F7FFD7579E2CE4BA2E0CE04AA00002000494441542DC1E22D069FD8D8D8C0989898E362FE1188520357C8071FBFC6E191471E817C008F3D969F7FFE795AB162853C86B9EE74FFFEFD69E9D2A538A68106226D4B845AAF609C5EE5775AA6806821C8F6EC57AF298DD7C7C7C7E7A3070F1E9435C9A9A1BA504444846CCB51B16245F2F7F7A71B376ED0E5CB9729292989366CD84089898974FBF66DE36C2162F85E0C43C9C9516AA7CB348BF4B265CBBEFDF8E38F1B6DDAB429578317B5F0A3C8909090621D3B769C57B66CD9A2D7AF5F9F8F1318807C0060FBB16C0C58AC59B326D77800D4499F331A205A926AADA3D4F648AD1A31260DF69B4710AED3E9560A91F6314A3407A6860F1F2EB31172C2A53A79E0B61D7DFAF49162FDDE7BEFD1DCB973E9FEFDFB3C09E7557397999C0EE2B400AB8FB3F75AF1E2C577BDFBEEBBCDF7ECD923F3A2B580EF587AF5EA55382E2EEEB9F4F4F48B62D4710F3DA8260B70FEF550F978E28927E899679EA1175E78411EDB356BD6C48E026ECFF6EDDB652F87790973404000D5AF5F5F96C8E3573EC681639932658A7CF1823F455CCEDEC8E72B9D4A717694542B1DA7467E95DC14E86DF85B6D2DC5673CAE16B8E371D54788F4A77ABD5EBA280BF2EEDDBBA95FBF7E8AEBDB730A70BB76EDA867CF9EB46FDF3EBA7AF5AAF1ABAE6238EB2C1F74AA4C738EF49B6FBEF9EA679F7DA699489BDFADB46DDBB6C8F2E5CB43C5DDC96631EA37C8B4B6A4A6A692D8BFF4CE3BEFD08409134CC3DB6FBF4DE24686BEFEFA6B394DD5AA55A974E9D2900FC807F08063996FFE3802DDA953A73CA7E163B861C386F298C74D22645ADDF56C9C4281562BD6B694CAB355AE958AAC96DD96938A7995ED2B3794E9EA3E3E3E5B3232328AF387909010DAB163479ED16825942F5F9E5E7CF1453A7AF4289D3B77CE38BA9D183639C3079D26D35CB563D8B061FB070D1AE4D3BB776F87AC8323D4254A94D07FF5D5574DC53F280E32AD0D090909F4CA2BAFD0071F7C40C58A15A36EDDBAC9C73053A74EA5B7DE7A4BBEE7FF29575F3976EC98146C4EE1E1C73575EBD6857C403E801BC24F57F818E563D95A3D69BE49E4639A8F7DDC2442A66D9369BB2D42C138ADA3D44AE558EB9AD5B646A52D1D57EE25D33A9D6EBDF0B45AFC9E23D22CD29C136D0F5CCAB36BD7AEF4F9E79F1B23D45C0D84CBEA2D75F4DFE3B49CE9E9D3A7C7FDFEFBEF451C5D6E49885DB177DF7DB7465A5A5A3F726199146F2025254596C9E268B3CA6A2B523A63626268E1C2853287D81B2EBE2C1F4691B6261FDC70829FC0702A8892E90170F6B16C4C53527A6C1A8FE91E3D7AC8576F3EA6F99CC741047EDAC60D9D382F93FB3CE08B35076DF886A279F3E63267938308408D009B744AE5F4B64AB49622ED0881D6A2D1A0C73540ECAED7EBDB4809154EC1E7137B45DA086729F035B7418306C61C6A6E94E8701F744A649AA3D2E216759590AB42B56AD572F8FA8282828AAC5BB7EE311744A7278AE161CA2CCB9261C3FC6E13995EBB76ADBCD872BA02772FCC778E850A15523C3FCBF3CB2FBF4CE9E9E9B26E385F803C394A6D2E1FF945A473628CE6716B64CEA546340FB8DBB1ACB651211FD38D1B37F6DAA72EC6A770A3478FA6AD5BB7D2A953A7E88F3FFEA0BB77EFCAEFF9E2CCD504B8539B9D3B77CAC0010BB7964FE1DC2832ADC1F56C2CD996DEA154B2B58C4CAB116D7BEB516BD97DB9D2E36AA196C795BDC7C6877C3AE137FFF9CF7F648EB49670CAC73FFFFC43FBF7EF378D220747A79D1299E69E0DCF9F3FEFEBAC4846BB76ED8A142B56ACC6BD7BF7AA8B8F294E3CF94C33BCDEE2F3B218568961BFE1B3C7C0A566DE78E30DD2B0DA8A8C6671F99A975E7AA940C90747F3783FA2220270078C694AF61C8B7C4C73E4C7DEE5B813C6A770BC7FD4B277EF5E3978D353B81CD7B37FC4F0851816D9763DD3322A6A6FCF885A8BB43DB9D4B68AB85AD9D6BBDBB1F1A84EA76BADD7EBE54D287710E50878B95CE1C350368FA3D30EF541A744A61F79E491F7AB57AF5E8D73599CC5F7DF7F7FF6D4A9531CE3FFD689279FC98657CED3E13005DF6E0D130387E3F93FCA5D5EDEB77C273FD9A5674F8E4873ED6FADABAD74EFDE9D860C19221FE5785284DA526343A598374A440E3570B5485BCAF7578AB15D803754AEE1731EDFEC1F3F9ED5E89F2FF25C2160ECD8B1B25D08B70F993D7BB66C1F3268D02019201042201B3A191E25D3850B17A44C73036C7BCE716E1499365E8C0A8BE169C3F56C84181A88E12F65D7B3313946298F524745BD2AF6651571FD282F3F6FDBB6933EF860A13876DB9A2471DCB8A954BB7620952A553297380E1B3686CA977F84AA54A9648344DB23D24AE5D87951E9CCE34AD3C8B43DC7C6003174E6377C1EFAF7BFFFED908397534FB90D173F5D3290E6481F748A4C3FFCF0C31FF4ECD9B3A833523C8C646464DC59BF7E7D31F1768D0B4E3EE670ADC37A8683ED75BE0E19CE28793D1E71A94C73748623A88EAAB6C25DC50F1C38504AA956F951900F00ACA3A6B1219F838CD156FECD5ABA49F4F44689FC148EF70BE7431B259A1F3BF3CD333F55E3F42C7E645CBC78F1CC93B99F9F3C77F16F981B5DF3933696EA43870EC91EDB381D849F44711E35CFEB25326D0E078A6A1BAE67A30DEF1FE47F3D1B6BF3CA57AEFC946EDCF8935AB67C567E7EF3CD99B47CF93A1A33E615F17FF2A13FFFFC4BECFFD728B7B0678AE68D1B37E9E9A79F12D79E52141B1B477A31FAD1472B91E31B256AF75D6CEC2A4E9315DB5DC146D9D63B5AA66D3936F83B2983FC049CCF1D0EF440D9B18B81BB8EF441A7A4799C3E7DBAB82377585E88933C9FFDEA937BC1723FC830DC645F13C332CAEC57FEB6AB372E2A2A4A46A5ED4DEDC80F3E06B8D207AF87EB41BABB7C286D6C687E0364E966088D1281AB8E65358D0D0D3267F578F6F4639A23D2DCC99231B2CCED42D40612F8A91B970AE520019F2F4E9E3C693A97B2786B9D0BEA661415431FC3C077235F89E1E3ECD733DB530CC2C25A8AFFC766D332FEFBDFFF51850AE5C4F1FC358587B7A52FBED845EDDAB596DF6DD9B29D8283EBD28913A765DB9E76ED5AC968B5AF6F213A7C3889962C59433FFF7C9E52522E889BA76EA6652626EEA48307BF13C76D6B6ADEBC519E22BA65CB9762D975C4B2930DCB6E6198779798F77B316F4B316F43D33C5BB6EC12D3D716DE73964E9DFA49BC0F12DF079B96B76FDF61B1AC1FA94C9952D4AD5B1B2A59B2B8D9BA76CB794F9C3823B6F717B1DD1BE46B4A4A9AD8EE0E0A849CAC48B75B1C1BF5CD3CCDA11B91C33B1DEA834E91E96BD7AEF9F249C799DCB973A71C653E6698ECA627A2326288340CCC59576E0C37BCE116EB8EAEB6C2CBE79AD42B57AE74DB0B0DE40378A3483B2AB7D91373A8F9291C3734348A34A76D7044D9D627662CE01C20E07DCDFB9CE11C6C9605967435DB653C9578D8A1C679165D0D03733EBBC4A96F84F8DC73CDC4F562ACF81FDDA30B17D2E889276A50E5CA15851C7F27657ADBB65DD4A9531BB98EA1434753404035AA54A90275EE1C66183786E6CF9F25A5959F18FCF5D72DFAFDF7EBA66D8A8E1E4F57AFFE463D7A74A4010346D09C3993A96BD776B9E473E8D037C4B2AB1A961D6A9877A298F777316F7B31EF7FC4BC3162DE30C3F413C5B65613E2EF2F6EB6FC69E2C4F768F9F277A97BF7B6F4DA6BD3C5F61F17F3F7A763C74E8BEFDEA7234736D2238F3C649877B258D763625DE5E4EBDDBBF7C476FF6DD86E6B529CBF64A7A45C72E57195F3D8C830BF1975243996EFD0C7E14E91697E84C6F92BCEE4D34F3F35FE7D933CE464F4B82B573E7FFE7C59FE8EA3298E8623392CA0F6CA345F786C2DF00EF900DE8E168D0D95C23785C69B444F38A63972FCDB6F99FD38B0ECDA23D24638958DF7418B162D64849AAF7B6A9FC271E52403933CFCF0AB91B7E8292730F009219965E9ABAFF649996CD224580A7342C236B9BC2FBFDC4373E74E334D1F1A1A22AE2BB9533E5AB76E2EAE138F51BB762D0D5169A273E75268F1E255949E7ECE50A54A2F8477BD41A673CB68686873B1ECFF18E6FD45CCBB56CC9B6CAA70B57CF946934C333D7AB413E23C58BE2F57EE61FAF0C3E5E286AB26C5C6AEA05F7FFD568C2B2B7E9B9DE9D4A99F69DEBCE562D9234CEB0B0D6D223EBF223F9F3C79466C7773B3A8B47571CE4BB2972DDBEA4EC795A92C98A3D33D73F4A2E850B9718A4C73472AE2C4A273A650F7ECD9F337216CBC276738F1205172A0DE35BC16C9319EBBEC0970C591CD35557FFEF967A745495926396A63AF0CD7A85143366CE447ACFC0AF90020FBB1ECCCA71FCE7AEA327DFA741909E6DF4C5E75EFADC14FE18CD16347D7B8E5D25C6A9EC271E34637CA9956723D638BE348A34FDED7B3FC244F5977E2A1A12DA44CFFFAEB15715C7595D1E1D75E9B40870F7F2FC5BA6CD932A6E91B34A8434A3B763979F247BA73E72E55A890D95094DF57AC582E5F396DD0A0B6695CD6BC0D0DF3DE31CC9B355FF5EA8F9A3ED7AEFD24CD99B354A67C942E5D528A74D60D4300FDF0C3CFD9D6D7A0412D2BE24C0AF65DF6D74183BA689D336DCFB19161146ABEA175A45073194B331C9A4AEB1499163BEBDEE5CB978B38B3818A9F9FDF1F869D37D90D4E3EFF180E9EA26612CDFF653EA373F9BCED866D75496575BEB8E4D7218BA360F95DBF7E3D8D1A35CAEE6DE7A14A952AB29EF5FFFDDFFFD9D4898237CB072858A8C9F777D431CDEBE7CA168EB849E4CEA08CE2CACBE72A399CA6A13458C34FE18C70750EAD1B5BF3F2F8BC366BD62CF9994BE6299569B3E0C2643738942C09D3038324E90CAF2AAF67CA2E759CC6F1D1474B65647AE1C277A94891C272FC8C1973C4B1F69C05D9B45C42AF48115F99FE71EDDA092BF3E65E366F43E6BCDF295AE7BD7BF7A96AD54AB2D1A431ADC838FDCD9B7F51E1C2BE8A6E00AC09B325E9AE5EBD92D6C7953DC706EF7429839C5AEA4899E6E59BE1D02EC50B39E31759A952A50BDC8B94932F289C839CE4C2131127DDFF6D785FCC20D23C6EBD189EA7CCC760FC9A402E6E7CC89D0EB469D3C6A9EB6CD9B2A55CAF56A4A5A5C988155F8C3A77EE2C05DB505FD223E4831B4271650100EC1569B53D1B3AE298E6F53BFA98E6A813477D3B76EC289F52F1CD34DF145BFADDF35338AE522223490EAE716B4C99E3E8B4592EB42773C7ECBD8FB2EB99DEAEA155AB6765034116583FBFA232AD82D339366FDE2E1B0DE62D9DFA5C9F0B15D20921FFC3F4392CAC059528519CE2E33FCD21A1FA7C9693B5BCB0B0E68679D7E7331FD18F3F9E33BD5FB3662B75E9122AB6BBA93CE6BEF92653C26FDFBE43FBF61D113784ADF295E2CCEDBEA140A42DDDB0E8DDEDD83079D99123471CBA5139BCD3A13EE8945064F9F2E5BFDEBD7BF713BD7AF572DAAF7EF5EAD57C047DEDC2134F4933A9E683EA733124921B54EDC8EB80E37C6967C2D11B47DC603D78F080B66DDB26072E69C5BD30727DEBFCD249DC493E90430DB41269571F43CE4E63E2C7C59C6FCC0347A8F95AD3AD5BB75CA9207C936D8C0EF2363AAA2B706EF8C4EBE6A76F8C164FE1DC80A2EAAF6779899C92C68899F355A95291AA557B9442429A98C6356AD480FEFBDF03D4AC5943B21C81CEFA2E32B2AB6C48B86BD73EDAB871B18C126FD9B294FAF67D85162C58218E9912E4EF5F5688EF7C0B12AA37DC84F1BC8BC4BCAF897957C96A1CFEFE0F8B79E79AA6FBE69B2362FBBE94A5D9381565ECD8FF9337039F7EFA01F5EBF73AD5AB1748C78E255344443BB19C2EF9FE1D91911DC5764F15DBFD3FB1DD732DEC5397771BAEE6D8602F9332C865EB38B5C9519895C52347FBA053643A2D2D6DA610A7C1E22456C849A904B7FFF7BFFF7113EAE12E3AB0F85692C31FF186D71BEE7C86E44721CEAEB6C25DF4F27AB9FA05AFDB18C5E1E3833F1B8F13BE309A3F06E2F74A1FE75EBD7A5546ABB9D3056E14C48F74CD2FAE05593E8077A126DF9F7F73E615682CC135942D3169D2A47C2BD8F04DE2FBEFBF2F8FE988880859A62AE723DD9C37B9E6E702865339723422B20A37FC338A35AF8FB783DB5570CD6CF3A761BC4D8E84976F94695EAF07CBB4C6D73375C277F6ECC16CF34E99324A0EE6CB494BFB3E97409B8FEBDBB7A7380EBB649B86CBDD2527EFA5F4F47FE43581533FF292D2B4B483B9B63D73DE5D39E6CDFAFE5FFF7A5EFC0E5BCB1B379668E3F8B0B06674FEFC57723E3FBF22A6C68F59EBDA9B6D397DFB7616DBDDCECA7EB4B7D745A71F1B1CA19EC3977B3E6FF1D32247DCD4B25F6CDDBA556F76F7B6DEE365FAE8D1A3676BD6AC795EECB8C79D21091F7DF4D1067147588D9CDB9538136358E77A72C308B4A58B8FB3ABAD6CD9B2459E68945ED4ED81A3037C31335E48B9F320BEB87234DEDDE40339D440F5D5ECC60D99EA60BC5974277EF9E5179976B16AD52A39381B8E58733D691E98C2850B9BBE73668D5B67A739BACFF5CC919152BD8AF1FA5CC26BA478713F0BF35A8E52E73FAF5E46B0B9CE755ECBC89ACFBA0067E689DB93DEA177B76323D520DE61EC001F7CF081ACF0A535BCDC3B77EE182FC8FB1DED834E6B7176E5CA9537C68C19B35A0882AF83A3D3375E7DF5556E0EFBBE0B4E3ED33DF18CC922ED6CA1E6AEE567CC98213B7111C78629CF917F5C7C47697C14CBDB652C6165BC381A7B2BB315EE5E94BB097647F960A1E6C75E2C47D7AF5F571D9503050F3E46BEF8E20B19996EDFBEBD5BDD8455AB564D469A394A5BA952A56CBF6526672E31FFF6CD739EF94621478B7CBBB877EF9EE9BD336BDCE6FCBB3D040DAE67E681414788B3658156FE59E977D6DFAF5CF91ED5AAF50469D735B95B44A0B53E36389F46D6129C3B77AE0C6E69D910984B530A41373FF8163A7A87384DA66FDEBCF99938A9C6881D57C7918FBBBA75EBB64188D853E2ED5A5C66959FF4F922E6CC6A2B0F3FFCB05CAF3DDDA75B8B02F3F7DC156B4EF847DBA74F1F7AF7DD77DD4E3E38F5841F4FB31C41A481528C69423D7AF4904F352CDD24F26FCED2EFCEFC7795D7EF47CDB1CC5D73F3766979D3CA8FC6EDD92E23CEAC71ABB431B477A277E2F2B4946ADB04BB75EBA6768A3439F83BB780533DF68AA11507CEF8692CD763D7E237C937DFBD7BF77E203096E4DBEF0C1FF475E6DEBB72E54AD71933669C0A090929E6882EAB77EEDCB945D059BCE566BEF7718955063F8EE4C790CE9469BE73747417F37CC16561E75C4996668EFA9AE7663569D2C4EDE4C3D58D2181E7C2C737773EE20E69428EACD9CE4F36CD23CC96E01B67DE2F5C3E8FCF3765CB96353DD972668D5B677486E55922ADB3611E35D35A936A2D045BCD7BAF8E52DB0AF76A7398EF6B939393E5B5D8DECE93F837D7AB572FFDE9D3A78D22CD3FF62867F8A0AF93775E8AB8438FEADEBDFB27070E1C28AE65EF75A74E9DFA465C3CD8CE468B21199756E57099BADDBB779333ABAD70FE32AF576B3855856FD48CF2CC17D3FCD28A0A8A7C808225D4AE6EC8CAE5AEB82C9EA3D6CF157BAC0507589E795FE4BC61E79B6BEEA08A71668D5B4747C1DD5BA675760AB392E9954AB53D9FDD4DA4D5FC1D6E498A18B8ABC715ECA25C42920B05F0F9CB96940F0ED07144DA4CA499A1CEF24167CB343FEE5A254E86451B376EFCC1962D5B4A6A11A1E688348B744646C6C7E2E34A5C52D5C11DA8BCF7DE7B324FD9591DB770892AF17FB37B393E3E3EB2B731AE2DCD11683E9ED444810A827C808227D4AEEAAE9E9FAE7084C991EBE506C5D92E62E29CC537C25C2B9F3B47B124AE2CD74699E6DF9DD61DB69863DEE8D0D14FE1DC5FA89DB93C2562E9ECC8B42D224D364CAFF7B48383D32FF882BD887FCA1CA1E6EB39B7677AFDF5D715B56BE09B56432346BD596A07F386337DD0D7157BEFDEBD7B4BAE5DBB96DEB265CB8593274FF6193D7A74091B25EE46444444C2C68D1B3B1876DC325C4AD5C3A90F9CE2C1D151675C78F982CB3F127B9F4CB004B338D89B5BECEDF2010A1EAE28B5689EA6C437E88E847FF37CF3CC11687E551AF9E5A761C67275CEAC71EB88A7709E2FD25A44AC95CAB22D52ED6E516AB2637AB786BD8D1B157030F4210EEA717B266E98C8E7316EBCCC9577D819F877CEA91C2CD07CB3BA79F366AE0C96919E9E5EC8EC80E2D48E579CED83BE2EDC816BEFDEBD7B202626264EDC55D48E8B8B2BDDB973E7620AE7BDBD60C182841123463C21C49C137D39471AA91D76307CF8707AE38D379CD2AD38AF478B9EC7B44C4BF176F900050FE34D223FF57054D7DE469C99A6C4A959393B63510AFFCE468E1C299FC239BAC6AD79EF8FCE4CA1F30ED1B6651E5B533DB4906835C2EC2CB9F62838427D803223D4B2CA07FF46F937A4A01755F39EBCB9B161942B7CD0D7C53B30E5C183076DAE5CB9D2AB5BB76E438B152B56E7B9E79EBBF6E28B2F960C0E0E2E224E72153907F6AFBFFEBAF2EBAFBFDEDCBB77EF4F2B57AEF4F9F6DB6F9F12F3F11990CBAB70181F8D0DED842F32FCA884EF061D596D25363656A66170350DC80772A48163E19B4463EF9A8E6A17C037858E5C7E5EE72A5B6171E648366FB3A36BDC1A2B788484849096ED83BC5F989D25D56A245AAD243B43A43DAEC1A1551F14435BCAEC1DF1FFC4D04A85A3B244C7B9D2077DDD6427AE1772BCFED6AD5B8F6EDBB6AD97189A8B715C519FCF469C4F53460CF70C47CB6ECA4C2A4FC1A5525B58209B356B264FFE8EA8B6C28F65B8B6F49E3D7B9C969B0DF900106AC73D7531DE147AD2B1CC4FE1F837C838AAC62D3FA636F27FFFF77F05F8E87374350F5BC45AAB4688CE90E70223D2D97CD0303C6A106BA30F5634F8207B21176EE746095F1BA675B90FBA9BD170CF38730D0370321C3D9933678EBC38B2F06A194DE1CE19383798A340818181900F883470226A1ADA722F9D4A8F6563C3594F3A9639B2CDD1E9BD7BF792236ADCF2EFDBD8E9140726DCF1299CFB4AB6BDF3D91A9576A44443A40B820FFAE047ED564CB6A713132DE0080D37EE193C78B08C4E6B914FC81169EED18F2334C3860DF3887F444040806CF4C01746DE27356BD6B4383D5F9C8D8335F9888F8FA74E9D3AE168076E794C2B399639B21B1919298F654F4C53E2BF8DBB374F4F4F97F5A6FFF7BFFFC95E598B172F6E9748B3981F3870407EE614C5AD5BB752F9F2E5552D67CA9429F2C53BAE67AFB950C8B56884A8548AB51669A5F3E8551C579F78CB71059906EE2FD30C9771E296B32C7EDCDB58E3C68DE5AB2D708E344BF4B469D3283A3A1AF2E1A1F201BC47A88D7598F9D5968E9AF8A6908F657EBAE2A937851C30A85AB5AAAC06C0A5F62E5CB820C5977FC36AE597E1D40E2ECF691469262E2E8E424343552FCBBB643A674373355D8B6B95EEE14CA97666945AAFF2B8824C43A621D34E87E5B177EFDE32ED836B50F34558CD8597E5912FD8A74F9F9625A2F83133E4630D22D2C0E5F031CCC7B2D2A72E79DD147A439A12FFED2CD49F7FFEB9146A8E502F5AB4881BBC53DDBA756564D91A5CB583DB8170DEB579272D3367CEB4F9291C22D3F68AB696A91E96E4D659F26CBF4843A621D3906917C2D19B010306C848CD871F7E2873297FFCF14753E72E458A14910377CF7BE9D225199559B870A16CE073F4E8518A8A8AA2F7DF7FDFA6480FE40300C71ED36AD2988CC7B231E7DA5B6E0AF977CD7F3BF700CBD53758AABFFDF65B598DE3F0E1C374F7EE5D79AEE327739C02C2A91CDCFE83F7054BF42BAFBC425F7DF595A9231916703E07DAF314CEBB23D3CE946AA502AD44AAED95687BE5D93E91864C3B1E5FEC02600DAE91CA03D764E50E0F38D7907B0FE3480C5F80B8D41D37DEE10B13774EC03D1B7A5B29285B1A259ACB07441AB81B6A1A25F2EFDD5B3B17E20682DC3E846FFE8D553E54D4B835C18D0D39B2EDEE0DAC9D8B51FC741A2CC39E691D9DEA618B60DBDB101140A68147C28D11B99B4F1E201F05573E80771DD3FCD4848F693EB6F3BAE933AFDAE1AD9D0BF1CD3F07013858C0916563A50FA5123D64C810D995B9BB96FC741FA976E6F2F42AE67344645A8D2443A421D300403E0A9C7C00EF818F61EE5530AF9B44634DF482725398F329DC37DF7C63F1291C4F5B703B64718544DBB21EAD7A46B445A221D29069004081978FA4A4EF68C3868D325277E4C85153AF6EC08E93AEAF8F90B1206AD5EA398A88E0148367DDE22631E75317F334A582F674A5A03F85F36C317766272EF60AB62DB9D700320D8097E0EDF2919C7C9A468E1C295E9369C8907FD1B4695384F435919139EB1729351D0DA899C63B5EF98624292999F6EFE746BA83A862C54769CE9C799AF6C267CF31DDB3674FD9F8F8FAF5EBF2290CD29480E305D851CB7754272EB64A35A2D1906900409EF211111141B56AD5A253A74EC9C6999E2E1FBB767D49FDFB0FA4B16347D3D6AD09E4EB5B58C1C54A97C77B5D1E17849CDFE90ADC45C3CFAFA8B831A92786BA346AD4608A8D5D456DDBB6A1458B168B63A78BCB8FE93265CAD0C58B17A972E5CA1069E000D9D5D9389F3D72ED2CA9D62A0D04220D9906A0800975972E5DA444732D6A6F10E9A14387D1A64DEB658503EBE8CC4EFE96845A89346B2DD83A0B1773F7B9584547BF48AD5A3512C7D170B96DAE3C86386ACEF9C20C97BB34E60903E058C1D67A19F6A67AD823D1B60836D23A3C9D42D80500D887B123175B3A74712738B58323D22B56C4E710699D8521A754EB2C08777ED3EAAC88BAD2F9952E8F144EEF3C82829EA4AD5BE75154D44BB2373D5761DE831FC3B9F200D827BD8E18ACADC3D2F7798DCF6F9CA56948C5777A52168D86487B2A884C0300249C23CDA91D798B7476B8FE6E7CFC72FAEDB76BB2A30ACEABEEDEBD1B598E46DB1B11B6757EB5F3B926721D14F4044D98F0B2F83FBC463B77EE72C931306FDEBC6C9FB94B6CD44807F6C9B4BD37AC7A0DA77776272E448846170C10990600C8AA1DDCD8303A7A583E229D158D9E3C798A1469CEABE50A1FC78F9FA0E9D367D0C4896F7AF85E707D843A3ABA375DBE7C295784D859ECD9B327DB678E4C730FA700D82FD55A44A1952CD3D234798D57324EC967B5DF11211A0D99060078115CFE8EA3CB793736CC924C8E481F3A7488121212E8E0C18352B42E5CB840478F7E472B57AEA475EB3E731B31F554060E8C10FF8F0D4E5F2FD747E7EEB24D17874285E8B7DF7E93E301504BD1A245F499A5349D91EA4156A67155AA075999CF39FCF3CF9D5FC4CB7D1C959069008003E108644848F33C05DA9CF8F8783A76EC589EBDC3A5A6A609111C9CCF1A20D74A0909A9E3925C65AE4C634E4646867CDDBC7933FE29403501018F5E3D70E098864BB4278FDA1EA9B626D9A4E0B3A5F91CCF975FFE8F1B6224E1A8741CC8990600C8740DAE239DFD42905B80B92738AEF250A142050A0C0CA42B97AF905F313FD267E829BC4BB894EDFC2F844009F5EB0750529273AF7B7C73B47AF5EA3CBFE3A710A8EA01D4E2EFFFD0FEFDFB8F4670A51AC7E16E553D2C7D76DD39302E6EF39FE2E5348E4AC83400C08164C9525E129D354E4AF4538152BC020202722D274BA6FEAD5A8D000020004944415421CFB6C275A8F38AFC3B12CED1CEAB774B4EF5E0541E4EF5E8DEBD3BFE394031E7CF5F9ABD70E1BAAEE3C6BD54987BFDCC8D4EE39B6E4776E062AF54BBF47C98F2F9E7DFD413AFF370543A0EA47900002C5C64B23F9A1C3468A0946EEED82327BB76EFA2E0E0673CFCEF2D98CC993327CFF148F500B6929A7AF990387E8E73A74496CF2F4A1A1B5A9A47690EB59AF1F98D53DB48D1F50D0CBB7419B953FC1F2EF13D338E4AC83400C0693299FF85273CBC33D50EAA45A16D42E9F69DEC91CCD1A347D3B2657194FF634DBD46DBE7E8F90A965473147CF7EEDD16A7E1540FF3C6890028E1D2A56BFD264F9E9F7EE0401239A6D121299856696EB59271E43112CDECDDFBDDE6C4C47DDCADEA481C8D906900801B08F58D1BD769E6CC59F4C30FA7286E711CF915CD9E43FBD8638F89EF67D3C58BA956E4546F615DD6B6C5D2FC4A97470AA72F187063C79B376FE6FB7DE1C285A548EFDFBF1F3F0FA096E49B376F75EBD6ED959BB6354674A70E5CF21A67AD51A26B45BA4D9B7FD7116FA3C470128722641A00E032A1CE7CBF74693C4544F4A6279F0A94291E952B57CE3557EDDAB5A95AB51A14D9A71FC52D89B74158B5965BBDCAF1EEB4EF9DC7B265CB2C7E7FEFDE3DF99A7FE352002CB2EBEAD53F7AB56C39E8CF993317FF91D91EC011916835A91EF6083491B2C8B5CB48E9D265E4A2D6AD5F6E929191F1AAF89C8843D0F1A0012200C0EC82A0CBF69E1B9F0D1D3A9C021E7F9236266C265F5F5FAA52E5511A32640875EDDA95EAD4A923A73E77EE9CECF465D498B1D4B77F7F9A30FE0D8A9DBF90A287BF6C4598F5D8E72E8653381419D1AE5D3225848F0100D40AF5DDBBF79AC4C47CF8C15B6F7DD2E0C5173BA5F6EC1956A965CBE08ADCE0D639BF23BD06E3DDB3D742AE23CDE5EFB86A07373614125D438C6E4B884843A60100AE943B1DA5A4A4D0A04143E88DF11329A4450BD31475EBD6A5F59B36D18E1DDBE9C8D1A374FFC17DAA57B71EAD5CBDC6D430F1EDD9EF50F4B061B476DD46EA13D943C1C547AFF2D5D5D2AB346544E9DFE19ABF8B05F9EFBFFF56342DA77AAC5FBF9EFAF4E9839F09B085E4070F1EB4BF752BBDFE279FAC8F10432B31EE2131A0E6A2FD5411C335CA2C7FC7553BD0D810320D00709D306645A63B760C97D1E8AA55ABE59A92A5B977EF488B4B9B3B6F1E7509EF6C9069BD02F1B4456EB5926FB5726C4D823D23E27EE4C8117536949C8C9F09B0972442072200320D00F06EA12629D54141B5C9C7C7F65344563A803591764634DABD23C4AE62DCB871723067F2E4C93465CA149A3469927C0F0000C032688008003013C92CC9AC57AF0E1D3E74C8F4EDCA152B142D25FFE99488B416420C00000040A601002E96EA860D9FA113278E9BC6AE5AB552D1DCB9A7D35B1169AD84D9D679D596EE83BC030000804C0300F29168230D1B06D37747BFD378F996DE2B91607BA2D2F64AB2ADF5AE6D7D050000E00920671A0090A724FAFB3F4CA74EFD409D3AB6979FAF5EB9627A6F09F3E9CE9D3B6B455A6D11685B4419000000804C03009C20D1996456F5484DFDC9C6F9AD7D56DB28D15671561B058694030000804C03006C96699D0612A9A6B303BD8DE31C9502A2B5946BB57E000000906900808708B5393A3B44506D645A8D5C6B21D98E6858686B5E35A41A000020D300002F10699D0672674B6F815AC9B59A69B4126000000090690000B0288A3A15D32A916A2D455A8BE8B43D370D906E0000804C0300401E0D10958BB33589B526CE5A893429FCCE5641B667BFA2341E000040A601005E2ED3CE68806849AAB58E52DB23B56AC49834D86F0000003C1174DA0200C82183390775D347458DA0C387BFCB67197A27887416C3868D13DB92448EE9E445C9CD82DA527C00000020D300002F10696B829DFF74090989949676C9CA72737631AEE4BD65918E8D5D42070F7E97EDFBE6CD1B51F9F28F285846FE521B1BBB5C2C374981006B518A0F520D00009E06D23C00000E90BA2C11DEB2E50B0A0EAE4F972EFD2AA4F408356AF40C3569124C5F7EF915D5AE1D4855AA5432CDB36EDD267AFEF96E54A85066BA4962E297629EA3D4A143A810E3C6729ADF7FBF4EDBB7EF96AFC1C175A53073F479C99235F4F3CFE72925E52245467691F3972A55827C7DB34E73DBB67D25A649A1E79E6B4C7FFD758BAA56AD42D5AA5596DFF1F88307BFA79B37FFA2060D6A51B366CF88E51E13CBFD4C7CF78B586EAA586E67D3B21213F788E98F896D6B21B6E1199B441D000080E783C834002087002B4DF55036EDD0A1FF91A91FE3C74FA55F7FBD422D5A74A233677E1662BB93DE79E743D37C5F7FFD2DCD9DBBD024D2D1D1E368F9F27554AB564D1A3060B890F2ED727CEBD63D2839F9672A57EE11B19CB372DCAD5B7FD3DDBBF7A420FFFEFB1F66EB7EC394E61115358666CC9827E67B98162F5E43BD7B0FA3FFFEF7A069DAF1E367D3DF7FFF23E4DB87DAB61D2045FAD6AD74C372FF16CBBD61FAFBA2A3278B6D4B10DBF6B8D8B63162DB76DB70A302E90600006F00916900BC80E9D3A753505010858787678BC4AA97699D1501542BE699040454A7D8D8B7E5FBBD7BF7D3B16327E95FFF7A91DAB4E949EFBF3F4D087421FAE493787AE9A57E729A73E75284F0AEA0F4F40B26B95EBEFC53EADAB51D9D3A7586162E7C879A350B362DBF75EB67A97AF5C7A85DBB961419D935D73673D479E9D24F85CC1F9112FEC20B5DC53A2E6413D84F3F9D6F7ABF6DDB5E21EAE7A96FDF6E62B98F8AE58688E576326CDB05B16D9F896D3B6EB66D0962DB4221C80000009906007822313131F2B578F1E242EABAD2C08103292424844A962C6983046B45D6B2C2C25A9ADE972D5B8632321E50DDBAB5A94A958A9498B843A6706CDDBA83E2E2E6CAF94E9E3C4D77EEDCA50A156AC979F87DC58AE5E5FB8F3E9A25E4B92775EAD48666CD9A48356BD6C863DBB3FF1D274FFE48A54B9794226DFC8E05DE7CDAE3C79369FEFCE574FDFA4D3A7AF4044544B4CF737F9C3C79C6B06DCDCCB6CD5FE53E8174030000641A00E076A4A7A7D3DAB56BE550BA746919A9EEDFBF3FB56AD58AFCFCFC548AB44EC59A9556FDC83E6ED0A01768C992D574EEDC2F3460406FB18D45E537458A14A632654AD3B56BA77349F2CB2FF7A36EDDDAD3DB6FC752D3A69D2835F53B7113E16741587979BE325DC3FC3B966023C9C967A96DDB7EB463473CD5AFFF34F5EC39345F09CEDCB65262DB0E409001000020671A006FE5CF3FFFA4D5AB5753C78E1DA96CD9B242560750424202DDBF7F5F85202B1D2CCD9BBF74F7ED1B211B132E58B0CC90E291394F58D87354A244718A8F5F976DDE8C8C0C991F5DA1823FCD9A3541E6345FB8909A79322B54C82C5F3ABB808785B5A062C5FC68DAB47932877AC2847764B4DA98A671E2443255AA545E8AF4EDDB77282DED8A693FF134E6F9D26161CDC5B61513DBB689B4A9510DE90600004F069169000A00B76FDFA6152B56C8A154A952D4B66D5B8A8A8ACA11B176A4CCE55D1A8FD32EDAB56B4DA9A99784C8D6CE3A31F9FAD0962DCB856C0F15A21D4F254B96207FFF87C5F67F48356A34A6468DEACBAA1DD1D1832830F009B92CCE95E60687BB76EDA38D1B17655B3B47A6B76D5B463131EFD2860DDB68E4C8972838B88E185F44CE1B1ADA4C08F6BB54AB567B71E3515A56E7983A3596EAD67D4A56F0183AF44DB1DC6FC472E78B6D2B24B66D81D8B6D7C5B6AD316CDB43B466CD7B2AC4D9DABE020000E029E8B00BDC0ABD007BC1C3983C79324D993245BE9F3469924BB6C1B87E5B78FCF1C7E9ECD9B3E2E0BBA6C16941AFF23BBDD5CFE9E9FFC846952CC446389D25735CE16CF3DCBD7BD720CF852D8A2C479DAB546922E4788110E7E06CEB32A68C7084DACFAF488EE5FAE6D8B6DB52AEADAD4F4D543A25254DDC3084BBFC58E2F5F3B10D9C7C51D6E9706D06C0C340641A0037915A57C122AD5C881D25D6F97FCE9E0F9DF95DF1E2C5F294D12CA9CD2DAB3131EFC9EFAB56AD4C8B17AFA5060D6A6713E99CEB328AB4314F3AAFF5152F5E54C5DFAA2C1564D9B2AD1E7B2C010000641A00A09A4183067984C4734E310F3973A6030202E8DCB97316244F67A3305B9B4E5DA4FAE8D16374E6CC39B1BDD5A84993FAF9CC937F5498CBE1716E3437448C891921CBDD599B47597499ACCCA74EB2070DEA22FE9F0B5D169976A7E31A000020D3001400AA57AFEEF2C7E1F9C934CBB38F8F0FDDBB774F36DEE3E1A1871EA2B0B030EADBB72F75E8D041E64C1B1E2DDB29CC4AE6B145AAF5320F7AC890D7E9D5575FA22B57AE99C9B4520126AA55EB0939D826C46AA6B72DBD23EB78CAEC11122916000000990600B80096E7C2850BCB46874681E67AD32CCE91919152A459A8B591665BA53AAFEFF2FFBC6AD546EADCB90DFDE73F51F2736CEC526AD4A89E90EA06947F7D69CB72AD3CEAAC55945A4D2A08000000C83400C0253C78F0400E2CD0BD7AF51212DA59D69B565F679A51D20E4A5DAAC7EFBF5FA7EDDBF7C83276C1C1F5A879F386A629F6ED3B48274E9C96759CB9963457CAE0F40EAE43CDA5EDD6ADDB42D5AA55A1254BD6C95E0DB9A247646417DAB2E54B29D75CDE8ED7F3D9679F53484823F1B99C5CEE962DBBC43E682D9673910E1E4CA29B37FF9439D3CD9A65A58BF034C1C14162FD9925F332D340F49498B847CC734CDC8CB490553EEC1768883300007813A8330D8017C111E7EEDDBBD3A64D9BE8E2C58BB474E95229D4D645DA9200ABAD319DDFBC99B46E1D41C9C93FC9B27867CE64357E7CEDB53769DCB819B28744CE6DAE53A78D146EAE25CD79CEDCC90A8B38D799E6EA1A7FFD754B7EE665AF59B345FCCDDBE57BEE06BC77EFE1B46A55825CEEAFBF5EA5575F9D22535EC68F7F87FEFE3BB31248DBB6FDE9F0E1E3A66DE5F2777DFABC26447DBD98E79A1C171D3D557615CEE92103068C15C2BDDB4639B62515040000802780C834005EC0B469D364EEB6FDE2EC28B2967DEAD4195AB87036356B166C126D8E3273EAC6AFBF7E2F24FB617AE1856E72BA79F3E268CA94D7A966CD002A55AA040D1BD65F2EA37AF547A95DBB16322ACD74E8D092366CF8427E9F98B84B76ACB263C7D7346A54146DDEFC25F5E8D14EAEE7D34F3F346DCFB66D7BA478376A54C7B46DA1A14DC5FA46C8EF398ABD78F167949E9E64E87A5C2FC47A3375ED1AAAA11C43A4010000320D00703913274ED44878751A2D277F3EFAE82D199DEED4A90DCD9A355E8AF2A9533F52E9D225A5481B971118F838FDF0C3190BB29AF599653A2A6AACAC54B275EB6E8A8B7B9B8282DACBCFEBD67D2ED6335A4E79FC7832CD9FBF82AE5FBF49478F9EA48888F6D996D9A0412DD3FB93277F92D1F00A159ACBCFFCBE62457F3B441AE20C00009069008097A377F8F25E7EB99FCC877EFBEDF9D4B46938A5A61E916917F7EF3FC836FDCD9B7F51E1C2BE56459AA950E11121CF4F0971DE2A453920E03159437ADDBA4419F5E6EA1FC9C967A96DDB01B463C732AA5F3F907AF61C6E51D4B98316CEDDBE76ED5B0B424C0EFE0E000080BB839C69008099CCA9C989B6348F3E4FF1CDC87840B76EDD12F2EB2FA3D29CFF7CE1422A85863E2BBB10FFE69BC3724AAE44C28D11C3C3DB505E115D4EBBC8CC97CE1ADFB1632B1A37EE6D21EA6DE5E7F6ED5BC8CF11111DE434DCB0901B28B24873EF8669699773D5DC36FF9BC2C29EA512258A517C7C8205E945941A00000A3A884C0300CC844E97CF78B5CBC91B2ED357A346335979832B7144470FA6C0C0CCDACF9F7EFA31F5EB3782EAD5AB45C78E9D1212DC91FAF6ED9EE7B22323C369E8D009B46BD77ED92538C3D537DE7A6BBE10F050830C37A7D1A36799523942439BD18409EF51AD5A1D6423478E5C4F9D1A4B75EB3E45C1C1B5736D3B7713BE65CB47621B46D382056BA964C9E2E4EF5F96D6AC79C70659B6B47F20D20000E0C9E8B00BDCCB6604D80BC0F927029D4E1C7CA90E12F4DCA4A7FF23533B389522E7F4FC9D9F5F5143A3BFFCE5932B7C30D99761BDA6747A7ABAA93B728E80F3BAACCD9B9E7E5BCA757EDD8ADB26D7F90BB74ED790702E28B8BF455C9B01F02C109906005814DFFCEFBB6DEF19B17871BF7C979329BA7AB216C1CD5FA22D8BB151A4F9BD6591D69B6D53519BC558FDF400000020D300800224DAB6CCA3B4BB71EB5D8D2B9568E582AB447C6D8F3A43A4010000320D0080303B59AAD548B45A49768648A3C121000040A601000554A6751A0AB85AB1D6DBF1D91EC176A45C43A4010000320D008064DB3D9FAD5169474A34441A000000641A00E054697694586B29D55A09B63D720D91060000C83400A000C9B4CE8E796D9DDE9952EDCC2835441A000020D30080022ED78E588E96A91E96E4D659F20C91060000C8340000F2EC92E53B222A6D8B38DB2BCF10690000804C03000AB04CEB3458863DD33A3AD5C316C1B6B72122000000C83400A08049B53397A757319F2322D36A2419220D000000320D0070B844DBB21EAD7A46B445A221D200000020D300008F92736776E262AF60DB927B0D000000320D0080FC3A65F98EEAC4C556A946341A000000641A006097ECEA6C9CCF1EB97696546B95060291060000C8340000682ACA6A96616FAA873D126D8B6023AD03000000641A00E01071769458BBAA1397FCBE8344030000804C0300F2944667D599B636BDB33B71512AD81069000000906900806642ACD532DDB11317F3CF9068000000906900401EF8F915A5DBB76FCB57D70BBBAB523D2C4DEB3C91BE7D5B4FBEBE3835030080A75008BB0000D0B0613D3A70E098C6B26C69B034AD9AF139C7E5378DD2E9C9CA7C8E2729E922D5AF5F1F0725000040A601009E42AB562D69FFFEA30A2458E9A046B0ADC93391FD52ADE6B36B24DAC8FEFD27C4FFA3150E4A0000804C03003C85888817282E6E03DDBF7F5F0339B6251A6D4BB4DA5152EDCADCE822141FFF99F87F44E0A0040000C83400C053A85FBF0105063E4DB1B1AB5488B335D444AEB54CF5D0DB28D9AE8B461B898DDD4E152B56A4A64D9BE2A0040000C83400C0939833E7437AFBEDA574E040127957AA87FB4B3473F2E4EF3463C6FBE2FF30070723000040A601009E46606020AD58B18AFAF79F60636344774DF5D05BF9EC7A58A47BF4184E8B162DA2A0A0201C8C0000009906007822616161F4F1C78BA84B97113477EE6A0B39D45AE4555B9B963410682265916B5751846263BFA2B66D07CA88747878380E420000F03050CC1400904BA8F7EDDB4F23478EA40F3E58494386445248481D6ADAF4698DEA506BDD810B91FAFAD2AE83EB4873F93BAEDAC18D0D39477AE7CE9D8848030000641A00E02D70CAC7175F7C21A42F89366CD84031319FD091234764C72EC0CE93AEAFAFAC23CDE5EF38AD038D0D010000320D00F05258FAD08108000000903FC89906000000000000320D000000000000641A000000000000C8340000000000009069000000000000402E50CD0300E07052535329212181B66DDB46BB77EFA67BF7EE915E9F59FB994BC5356CD890222222A84F9F3EF4E8A38F6287010000804C0300C01B6FBC418B172FA63265CA50EBD6ADA97FFFFE3474E8506ADCB83155A85081D2D2D2E8FCF9F3F2F5C08103721AAE65CD3D017EFCF1C7D88100000020D3008082C7D75F7F4DBD7AF5A2F4F4741A356A94946A3F3FBF5CD355A952450E4C6464A4EC527BE6CC993463C60C5ABD7A357DF6D967D4AE5D3BEC5000000090690040C160C48811347FFE7C195D5EB76E9D94E8FBF7EFD37BEFBD479F7CF2095DB87081EEDCB923D33C1E7AE821D99D769D3A75A87CF9F23479F26429DE3C70CA47C78E1DE9E5975F46941A000000641A00E0FD242626527C7CBC4CED183C78B094E877DF7D97162E5C48A54B97A67AF5EAD1C48913A524FBFBFBCB79929393E9F3CF3F97116996E6E6CD9BD3962D5B68EDDAB53460C00019B1AE5BB7AE4C0F0100000020D30000AF84E59723CAC78E1DA3EAD5ABCB86863D7BF6A4F6EDDBD3175F7C41B76EDDA20D1B36C8E834479B39379A1B1F7277E5AD5AB59222FEE79F7F52545494CCA7FEF6DB6FA953A74E74E2C409994B5DAA5429EAD7AF1F7634000000B7C207BBC07D285AB4E8A451A346E9583000F024F6EFDF2FA3C81B376EA4A0A020FAF0C30FA5F8F6EEDD9B264C9840DDBA75A3152B565093264D68D0A041141B1B2BF3A2FFFEFB6F7AE69967E88F3FFEA0499326C96A1F9B376FA6AA55ABD22BAFBC22459BBF67D9E6C68BB56BD7A6279F7C123B1C7825FFFCF3CF2FE2775152BC9D86BD0180E7803AD36E444040C055AE680080A7C1B2DCA2450B6ADAB4A92C81F7DA6BAF498966010E0B0BE31B4599DEC1038BB1B13122BFF23CDC48F189279EA0E0E0606ADBB62D3DFDF4D352BE797E8E74B3547343C4175E78013B1B782D5F7EF9E549F192843D0100641AD888BFBFFF7E8EF001E049706A07A76CB0FC728EF4B061C3E8A5975E9272CD79CE9B366DA23163C6C857733812CDA91FD3A74FA7952B57CA79972F5F4E3B77EE9451695EE6F8F1E3A56CFFF6DB6F72F90F1E3C40EE34F05AE2E2E2FE142F7BB12700804C031B397FFEFCEC850B17DE63A900C05358B060818C2C972C5992E6CE9D4B2D5BB6A4912347CAA8320B30CB3057F6E08685E670E58E5DBB76D18E1D3BA42C2F5AB448E650739AC8D6AD5B65EE34E74A73958FAE5DBBCA79DE7CF34D29DE007821299F7FFE793DF1BA01BB0200C834B091D4D4D443191919C7399F14004F801B19727499A3D37C13C825F1A64D9B463D7AF49022CC22CD70093C4EF1C8098B343750E4B410F39E0F59A839AD837B4564C93E78F0A0146E8E707359BD55AB5661E703AFA24B972E3BC5F9FF92788B5C3F0020D3C01E2E5DBAD46FF2E4C9E9C89D069E0057E96061E6DCE759B36651B162C5A45C73C342AECCC125F0B82BF1FCE00A1E1CB536E7F2E5CB2C1674F5EA55D2E974B2A4DE238F3C2223D90CAF8F534100F016F6EEDDBB59FC16BA88B723B13700F03C50CDC3FDF8EDCE9D3B87B66EDDDAAD458B167EE6D13A00DC0D8E1A0F1932846AD5AA255F1B34684057AE5C91553B38FD83E11BC3679F7D36D7BC5C5F9AD340DE7AEB2D2A5428EBBE9E6B54735DE9D9B367D3DDBB77E9F0E1C3D4A143073A73E60C3DFFFCF354AE5C39DAB76F1FBDF8E28BF80700AF10E9366DDAD4D1EBF5C3C447349A01C0034164DA3DD975F5EAD55E2D5BB6FC73E6CC997F20871AB82B494949B2D206C311E8CE9D3BB31C504848881CC7E5F1389F3A2F382ACD929CB31424A77C7003461ECFCBE1E5F172B9DE34C32920BC5E003C9C942E5DBA2C6ADDBA75938C8C8C57F927815D0280678282C66E2CD477EFDE6D121313F3C15B6FBDD5E0C5175F4CEDD9B3672521D8158D65C5007035172F5E94DD81339CCBCCE919DC218B3157DA129C2FCDD16C4BB0A8B338070606CAF40F867B4EE41410003C0DAE23CDE5EFB86A07373614125D438C6E2B8693D83B0040A68163487EF0E041FB5BB76ED5FFE4934F22C4D04A8C7B480CB069E03670150F46AFD7CB86865CD28E73A7192E7F67CC753687A7E14A1E3C186B479B4FCBAF53A64CC936CF8D1B374CEBE3F40FCEA706C0C3A822866B62382D867984C6860040A681D3482214F207EE899E73A4B9FB6F8E18734A123F39B97EFD3A597A82C2DFB17CE707CB340F2CDDDC8D38BF1AE1F519804D0300007039C8990600D87E37EEEB7BFFE4C9CC27D49CEEC129190D1B3624ADAAD11873B2B97A47912245E4B89F7EFA89A3D2684800000000320D00F06C8A162D7A93739F999A356BD2FAF5EB6577E15AF5E4C9CBE1E56DDEBC5946BF19AE6D5DA850A13FB0F701000040A601001ECD83070F8E7CF5D557F2FDD34F3F2DCBDA193B5A898F8F979DB770FD693570F7E25C9F7AD9B265B464C912B93C5E07BF32090909FF88F51EC3DE070000009906007834B76FDFFECFE5CB97EF71AEF4B871E36423C14B972E71C45A4A3497B4E332776A888E8EA6C8C8487AEFBDF7E8DEBD7B32179B5FB9812273EAD4A9C2E2E55DEC7D00000090690080A773AA70E1C23F6FDFBE5D56D9E0BAD0FFFEF7BF69D3A64D52ACB92634CB30BFE788737ECC9D3BD7D453225704E1BC6BEE3E9C9713131343C3870F97E3B9FA879F9F1F179CFE12BB1E000000641A00E0F15CB97225E695575EB9C7153712121264250FCE6BE6DE0DFBF7EF2F1B2372072DDF7CF34DB6F9CC4BE6B170474545C96A20DCA091D343385564F9F2E574F0E04153C72FD1D1D137FFFCF3CFF7B1D701000040A601005EC13FFFFCB3212D2DED6E9F3E7DF41C3DE6EEBFDF79E71D59CEEEE38F3F961DB970D7E02CC8E698D791FEFFF6EE042CAAAAFF03F86F580411B7DCD3FEA2A999B9E10A822908884AB8A0B946D8AB1946E65B968ABD2999DB6BB9E2BEA569B8818664EA6B8AE12E26981A9AB99B4B96B821C872FFE79C591864963BC30C0CFAFD3CCF7998B9F7DC3B87EB80DF399C7B0E5F2991AF78181A1A4AFEFEFE347BF66C3A7BF62CCD9A354B1CCBF7858585E5B1D7E1ABB5ACC7550700005B618F4B00004595979777E0DCB973610D1B3654F8F8F850BB76ED68E8D0A1628EE8B973E7D2DAB56BC54D848F1E3DE275C5347A53A64C11E3AC93939369FDFAF5A2379B8FB7E6BDD8EBD6AD1363AEF9D090112346881EEFC8C848BE584B287BB9B3B8E20000602BB0E801005884ABABEBC2C78F1FBF979898A8E063A779380E0A0AA26AD5AAD182050BE8A5975EA2D8D85862FB4580E63DD7BCC799CF23CDA7BFE3B375F0E11E63C78E253E069B07EA5EBD7AA9A7C7CB632FB12437377724AE340000204C03C033A96CD9B21B9E3C79D22F212141111818286E3C0C0E0E16E3A5AB54A9429F7FFE397978788800CD83340FD4BCC73A2626862E5DBA240ABFD9908F91E6FBB76DDBC603759E2449BB59E98A2B0C000008D300F04C6321788CB3B3F3E48913273A8F1E3D5A139AF9F8E993274F8A5EE9EBD7AF8B1B1539BEB261EDDAB5C5147AA3468D12B38270F3E7CFCFFBF8E38F7372737363F2F2F2C27065010000611A009E176E356BD68C9324A9D98811231CB46F363466D5AA557C9CF5A3CB972F9F4F4F4F1FC036A5E172020000C234003C8FBE7274747C2F2F2FCF852F37DEB3674F051FF6D1AC5933D103CD8781A4A5A589F9A3B76EDD9A9B9292C27F2765E6E6E68EE6B99A951C5C42000040980680E79D8742A118E7E4E4E4C982B52B0BCB4EACD8DBD9D9E54A92A470707048CFC9C939C41ECF6475F7E172010000C23400807E9358E1EB8347A91E030000944A58B4050000000000611A0000000000611A0000000000611A0000000000611A000000000010A6010000000010A6010000000010A6010000000010A6010000000010A6010000000000611A0000000000611A0000000000611A0000000000611A0000000000611A000000000010A6010000000010A601000000006C9E032E0100807C1F7C30B2EFE5CB57C3AF5CB9D23A2DED6CC5CCCC4C5C94D2F09F9D833D356CE8F6A07EFDDABF8BF640310000200049444154BEF8628DC5CB966DFA165705002C41814B00002560122B135989523DB6794141419ED7AE5D597FF76EFA4BC386FD4BE1EDED451E1EEDC9D9D959E6192403CF4D796CCEFEE23E8F64E5F39ABE8D7FE8494949A3FDFB7FA1D5ABBF278582AEBDFC72DDB7B66EDD9D881F47004098060084692BEAD3A7E7B0FDFB0F2E193F7E9C5D44C44872707090F96B5432B2CD5642B4A503B8ED07ECE8E8EF68F2E4C5791D3BB619131BBB6B367E2401C05CF6B8040050023AABCA3E56126D3D489F3891BA78F3E68DF67DFB86909D9D9D2A402B64F447E8AA67EA6373F61B6B9325B799FA1A8A126A5341EDDA35A36EDDBC153367AEF2F7F26AF9F7EFBF5F39861F4B00409806008469CB6A7CFBF69D84D8D84D0E7C4887EE506BAC94448856142968967EF2BEEFEAD55F207F7F4FC59429CBFC9D9CECE33333B36FE147130010A6010061DA42EAD7AFB7F7934F3EAEC57BA4E504E984841F68C284FFD0B265CBE9D0A1C354B56A55AA53A78E0542747186CEE2EF252EB980AD1081DAC5A5AC7D6AEA399FBB77EF47E34713004C85A9F10000746B9995F5A4111F23AD3F48E73F8E8C9C40932645D1F1E3C7293939994E9E3CC9B645525ADAD9620CD10A33F7D97AE8B5AE888881E4E8E8508F3DF4C0DB1E0010A601002CA04E9D17FFF5DE7BEF9651DE6C68284813C5C5C5D1CA952B4590BE78F122A5A7A7D3C18307E9E79F93E8BDF7C2C9F0900F7303E6B33084C376BE8761C3FA94A955ABEA3B78E70300C2340080054892A22B9FFE4E8EA953A7D2AD5B8587DBE6E6E6525252122E6629E0EDDD8AF2F2F2FC712500C05458B405004007168EEBE5DF7468B42EB9B8B8508D1A35C4D79CEC1C7229E7221EDFBB770F17B31468D9F215FAEBAFF4FFC3950000846900000BC8C9C971C85F9085CF51ACD07A4CAAE7CAEDD9D9D9627CF4B061C3C8D9A9E0222E5D03BBAAEAE99BA3590E49E6B6D2C676BE07676727DE338DBFD60280C9F08B03004056D87BFA71FE6220818181F4C71F7F143AFA58F2312A57AE1CC95B9CC51201532A15C1B5B4046C000084690000AB076A89BEF966253DCE784C3E3E3E949995A9A9317CF870F2F3EB22F35C25112AA562D866CB211DC11D008A0EC33C000064053145A1C70F1F3EA0D973E6D20F09DBA9C1CB0D68E992A50586791C3E7C983C3C3DC5B8E9888870AD39A7CD0DD5A6865AE939FEF70200281EE89906009015CA0A3E4E4C4CA4A0A0606AD5AA2DEDFCDF6EBAFEE775AA52A54A81A36FDFBA4D0D1A34A0CE3E5DA87FFFC1B46AD51A9941DA52A15A6ED0B474CFB3A901BFA47BC311BE01C07CE8990600301AB40ADE7CB87BF71E5AB67C256DDC144BE55C5DC5B62FA74CA591EF8FA4E0E0606AD4B09108D7DF7EFB2D2D59BA8CEABAB9D1F61D3BE9B3C848CACCCCA0F0F011C51CAAAD39C4A1B48DD346700600CB42CF340080AC00A60C61FBF71FA0F9F317B2B24013A4B996EEEEB461C346AA56AD3AA5A4A690A393136D8E8D13419AE38BBF4CFFEF7FE9E8B15F68130BE1A605694946FB2C11A04BCB78E967610C37003C2BD0330D006034B4E5F74C8F1B3781E2B67C5F2048AB952B5F9E7A8784183CE39CB9F3A8E71B3DA85FBFDE06425F517BAD4B228823100300C234000018097EF60E0E3A83B4EC5FBA0E0E3283B3A9A1DA942121960AAD96B821B2384235423A00580F86790000180C6192C1E0B5F6DB6F659DA9703D89E4CD636DE9506CCE986C4B8DD3B6DE871C84640040980600B0C930AD2FE02AAD5BB756D69974D7932C1CAA2D7923A3A5826C69DC0600201F86790000C80E8D0A2B9DD7D87339FB2C3D3B88A57BA5116E0100611A00E0390CD28A02CF2FFCF10775EFD655B3E5F6AD5B059EEBA35DEF8266E971396152B2F263B9FB2D15824B532F380000C23400800502B59A82AE5EFDDD42E733B5C757F9FCD8B15FE8C5176B50EDDA2F5A29549B12B0CD19736DA973234003806DC098690000836152D718E6C265F8F0912CE82617D8C6573C9C356B2EE91F1B2DE909CDFAC64D4B1415358DF6ECF999CCBB49B138C65F1725BC22F00240E9839E69000059A1FA6905C74F6FDDBA8D7AF408A4B66D5B6BB61D3F7E82FEFCF3067DF4D10716089172C36D7105694B85EAFCC7D1D16BD8F56B4EEDDBB790798CADAD0659A2EAB0D28B954EACB464A5262B7C0EC74C566EB292C2CA0156D6B3720D3FD30008D30000C518A215268432DD01EFFCF90B74E4C831BA77EF3EB9BB37274FCF769A7DF1F1DBA975EB9622781F3992CC02652B1628DB68CEC1F75FBA74855E7FBD83ACC09D9474887EFDF53455AC58817AF6EC4EAEAE2EB46BD75E7AEDB5C654BB764D4DED0D1BB650BF7EC1646767470909BBD86B1FA7C0405FF2F26AABD5B69DAC6D2DD8F9CE887A01019D0A7D8F7FFF7D9776ECD82BBEB66EDD8C1DDF86BDDECFF4CA2BF5A96EDDDA9A7A9B36FDC0DA13400F1E3C60F5F7B1FAE9AC7E5356BF351D3B964A2B576E62D7E932FB5EAF51FFFE3D34AF9390B097B52D85B5ED7556B795AA5D7BD8B14D58DDEB74E2C46FECDAB4A6E6CD5FA1EDDBF789E7FDFB77A3060D5E7AD60334A902F4FBECDFE6F5BCBCBC323AF63BB3E2A62ABCEE4C56125959C1CA5AFC8C03141D86790000C80AD5FA8ABC63232327D2A3478FC8C1C19EFCFDDF60E1F1B8667F78F8681A3E3C82D589A21B376E52C78E5DE9DCB9DFC5BE51A33EA5D9B317508D1AD568EEDC4574FC788A8E3098DF96D1A3C7D3B8715154B9722516807FA366CDBC5968FD8785CCDD3473E67CCD11FBF61DA039739688801C11318ED6ACD9484D9ABC42A1A1112240E7B76D1C0D18308205DDF5AC6DB7747E58F0F1E9476969E7A95AB52AACDD17C4B61D3B12D9F7334353F3D0A15F282A6A2E9529E3C0EA0F64F5FF60F55F60F52F8AF33C7C98414F9E64B3A0FD48846CB5888828D6B62DAC6D0D58DB3E616DFB496C0F0F9F486161E369E1C2EFE8DAB51BE4ED3D98468C98C83E20FC48172E5C65D77008B17029F30350A9E4A6502876B0AF5B58F1D313A4F5E9CC0A9FF83C899506F8F906281AF44C0300180CC2DA0CF7500F1AF48E08CB6A999959141414281E6FDCB846537FFBF69D2C449E173DD06AF5EBBB5174F457E271626212A5A69EA272E55C58585CCE42EC59163CAB52FFFEBDA97BF77E7ADBC77BBFA3A379FD3322D80E1CD887CE9C49A379F396D13BEF0CA22E5D7AD3AC5993598056D0D2A5DFD2B0614358F0BC44CB97AFA58C8CCB623B3FE79A359B28383840735E5F5F6F1684C73CF59AF9AF7DE6CCEFB464C974F2F46CADD93E6CD8006AD93290162D9A4A152A9463AFB19EB5A19FAAFE79567F0AABDF4A732E1F1F0F7273AB430101DE9A5E691E8A972FDFC8DA7652847E6ECD9AADAC6DBEE2B1BBFBABF4DFFF7E22EA4647AFA3860DEBD2983143292727971C1D9BB1E332C9D5B5ACCC005DAA42F5007B7BFB85B9B9B99535FF993B38909F9F1F85848490878707D5AC5993AA56AD4AE9E9E974F3E64D4A4949A1D8D8584A484860EFCB4CF561DEAC9CE09F4D08BDD40008D30000D609D30AD981EBBBEF5650AF5E419AE7111163C4D00D7EDCC993A768C1826574F76EBAE85D0E09E959E07C7E7E9D35AFC17B95F3F27245BD0A15CA8B60ACAEAB0E95BA7A88CF9C39AB555FA971E38674FAF46F2C0C7F4AB56BD762616A277979B5A76DDB76D28A15B369D7AE44CACA7A42356ABC26EAF3C7356B562F706E77F7A67A83347FBC70E1141686DF6441DF97A64F1F478D1AD5A3264D1AB20F0B2D68EDDA3816EA832926269E85BA63E288850B27B3FA83587D1F56FF13515F9753A7CEA9DAE6A9D5B6AA9AD7EFD0C1BDC07569D9B2B12A58DA99F0EF5BEA0429148AB52C48DBAB43F4E8D1A3E9FDF7DF671F46DC0A55AE54A992288D1B37A60103068860FDF5D75FD39C3973D8878E1C5E858FABE6BDD47C38C872FCCC03204C0300582150EB236F1197B4B4B3E4EFDF9376EEDCC2025F33EAD36708C9BBA14E52071E23F554BFD01DEC0BD5E763B41D1D1DC5E3B0B001B472E57774E1C2650A0D7D939C9D9DC4B00B3EB6FAAFBFCE183CB7A1D77EF7DDC1622CF48C190BC9C3A3275DBB76845C5CCAD2DB6F87D0D2A53162F846FFFE412CE8BBAAEA0F60F5FD58FDC5AC7E08AB7F80D5772E74DE32651C59DBCAB3B61D21F3566C943B4D5FA9318007694992EC951F941AD3A64D9BA869D3A6B24FC07BAC67CE9CC9FE6DDE661F7206B20F2CA7D4BB9691F26645F45003980863A601000C86314363A49FDEAEBB3EBF79AF56AD1A2C4837A5CCCCC774FDFA9FAAD06B78BA3C3E0C2423E3311D3870586CDBBD7B9FB849B060DDFCC7BEBE1D454FE58103CAF0C9879924251DA6A0207F516BF0E0BEB463C71E5ABC78350D1B3658D4F1F37B5D0C2759BD7A23993373071F97FCF0E143AA51A32A4D9F3E9E3D7E4457AEFC29F60D19D25B0C01993D7B397BBDFEAAFAB9627CB4B2FEA705EAF36126FC2646353FBF0EAC6D6559DBB69810A48B36A3880D73E3433BD441DADBDB9BFDDB269914A4B5F1E3F8F17C688896453CA3E3E71EC034E89906003018CC14328299A1FDCA903B61C217D4A4497B3184830FB3F8E28B19D4BC7913318B87BEF3D6AA559D66CDFA927C7C82C9D5B51C75EBE647EDDBB7D6FB5ACECE6568E3C6E52CC4BE472D5A3415E3AE4342DE10219AE337FC0504741637ECB56CA91CD6C1C3777CFC6A5667A408D9FC75AA56AD4C31318B64854F1E8EEBD5F312433A2E5DBA4A111161D4B8F1CBA20EEF9D1E3CB8A7988983CFF0C1B7E5E549AC7E4731051E9F8923222294D5AF2FCEC5C74A87877FCE3E341CA4B8B868D1D31E1FBF989DE363D6B618312B89B26D5F9BF86F52AA42B34E0A8562B97A8C34EF91DEB2658B18135D147CF807EFD9EED8B1A3BA879AFFE980F75077C4CF3E80093F9FB80400500226B132919528D5639B4CD29274B788BF460B8637DECBCC0326C77B8DF9300B3961EFC9932C1142F3EB1BEF69E5AFC5EB2BC758CB9B973A232343846B3EBCC2D4B9A6F51D3B78F087EC03404B1A352A8CB47BD279FB94F51D0A9C8F0F09E15F95E7C97F0D657DFB42DBCD9B335BF73685C2DD56FF5FE453DA6D517FF83971E284D93DD2BAF020EDEEEEAE3D44E82DC2700F00D930CC03009E659FA98288997F859333FD9DFC69F3D4419AE3BDC8FAEB17DC5EA64C19559096B38AA2A4792D5382B4BA27D99C20ADEFD8AB57FFA4D8D81F292CAC6FA120ABACEF50E87CFC1CF98159FBDA39EB08D224A36DA66EB3C5F721BDAF7EC06F36B46490E6F8F9C68C19A3BD69047E7500C887611E00F02C9BACFAFA8895ADA4EC6DDBCFCA43D343B569F84C1CE7CEFD21A6BC2B383443FFF9F7EEDD2FE67AE6734A9B16024D0D97D65835B1F0633E063A296993981ACF9C73ECDD7B985D8F97D9F5A86266682ECA4A8956791F66B092C0CA2A13DE8775140A858F2449A257FAC30F3FB44A03F979F90C1FAA69F3F894796EAC5CC2AF1000E3D0330D00CF039EE6F81D773F927229653EE9339F00DAD978C83575911612370AF6E9132A16393974E8A8C1F38D1C39868E1D3B21F60E19324255DF586FF5D3DB48CF3663F58DD5D5FD78F8F0B162C54263F5F96A8B6DDB36333B8C0F1932865D8F149DFBD7ADDB46FEFEFFA26AD5BC2828289C5252D24C0CC825327EDA85953755EF433E67628C8CF7612FF54D8781818154A74E1DAB348CCFF2111414A4BDA92F7E6D00C8839E690078DE5424E598505E1EB3B28195EF49D96398533870997A03220F7A1BA9478F00FAE8A37023C748E4E5D58EAA57AF42451B9A607A4F7574F42A71D360FBF6AD48FE2C1ECAE75BB7EE64DF9F8FB889505E38B6546F77FE363ECBC79A35D3A956ADAA346FDE5AF2F51D4AB76F27155834C7F4F6142B7EB3DF0055C9622596954D3ADE879DD40FF8822CD6C4CFBF79F366EDD7FD0ABF2E008C43CF34003CCFF820E63052DEDC758795F554A8A75032A9F0E11D7C55415E366C88A39C9C6CB132E1BA759B68E1C2154FF53C13952FEF2AFE7CAFEBF5E2E212E8D6ADBF34CFB76EDDAE5AD29B283E7E8798626FC78E9FC4C22BEA63131276D17FFE33830E1C38FAD4F9F2CF7FEC580AAD5C194331315B591BBFD7ECE1D3E82D5CB89AB5758B98EEAE709B7405D1C23DDB09097B581BBE666D382EB6EDDA95C4DA7AABC085DFB021416BB96FDEEEBDEC9839AA63C8E807865EBDBA8820CD8D1C3990EEDEBDCFBEAF532686669B991E8F0F881FA47A1FFEA3FAAA7E1FAAA77BA1366DDA58B5112D5BB62CF014BF1E00E441CF340094A48936D416DE63DD5F5584C3878F9187873AC0C89BE481CF9BCC67A4E037FFA9E74C8E8CFC92FCFC3A89D0ECEF1F427BF76EA5B66DC5CC11141E3E86162C9841B56B772F14F4C2C23E6081FA1BAA51E375B165F8F07FD3EAD5F35988F463C77D4AF5EBD715F357F7E8E127EA47448CA7DBB7EF50EFDEDD2934F4039A3D3B8A8283BB160A8BEA363E78F050D54689468F9E28866EF0A9ED5253CFD0679FCDA0E4E4ED54A54A251981337F7B44C444551BBAB2367CC4DAF019EDD97388B66F4FA439733E1375F6ED3BC21E7FA359363C22228A1DF30F3BC68F1DF3293B2692B5DB4776E84D4A4AA68A155DD9356D6A89D03CA984DF87E54979B3622FD573F5270E3114C39A9E3A7F55FC7A024098060004EA2219376E3225267E2F3784099D3A7952A3462F8B1EE79123878A6D7CEE67B5EDDBFF276E4C54876979814FF76BFBFA7A5354D427E2315FD570F9F27594917151338BC79A359B58280D28742E1F9F0EE4E6568702025E67813698CE9FBF44D1D1DFD08D1BC7C552E40307F6148BADCC9BB7929DFF2323DF7BFE3E651BD6B3369C11371F2ADBB0853EFF3C82BA74798B66CD8A14DB972E5D4FC386BD29F65FB870951DB3891D93AA592A9D1F53304CEBBF46B76EDD61E7FA9C1D33EDA965C44D09D5922DBF1F35DF5451E7953686CF3BADC519BF9A0010A601C0767D6343413D5BF5B5D07C6C5F7E399E8ABE9CB844274F9EA1050B56D2DDBBE974FC782A858404C90EA88642A1BB7B53CDF353A77EA3ACAC2754A386F2863FFEB8664D63B382289D39734E2CF5CD83B4BA2E5F78E5F4E97364CAD47AA74E9D53B5A16D8136346FDE58DC8CC8877F7879B5A66DDBF6D08A1553459DFC633A681D5355D635397FFE0AF5E9338AE6CE1D4F41419DC842C33BA24AE87D98A72A0E3AB68B407DE7CE1DAB06EAF4F474EDA799F8350580300D00B6EB1215CF9FD3271A08D03CA4386985689E2476B3B28E951DAC3CF6F66E673428EBDFAE2C6969BF93BF7F5FDAB973A3584EBC4F9F301DC7EB1B935C705F6E6EAEDE36F0B9A82B56AC407FFD75CA481B0B3FE737EDE5E4E416D877EFDE7D727474301040251D6D70646D28CFDA905CE898B0B03EB472E566D1131D1ADA4BB3004DFE3187C9941B126FDDFA9BBA747987D6AE9D4E1D3BB6A6A22DE252E0F1A4627C1F7239AAFF8BED54E5E9F7E1AFAC34E0156FDEBC69D530CDCFAFE50E7E4D01C8831B1001E079C167EE78A47AECA80AD2FC2E3B3E7D413F56EAA9BEF2F9A833750563F925FFD85F7F3D23C635F3E5BBF91CBED7AFDF103725EA0FB9F9E72957CE85EEDF7F20B64E9B36973D7E48FAA6B7F3F3F316335CAC5EBD51475B0A3FD71ED3EDEBDB4104EA0307942198B73329E92805057521E353E6E59FD3CFCF4BB479F5EAB842F5060F0EA61D3B7EA6C58BBF530DF1501FD341D5EE2DB28334F7F6DBE369E0C0EE260669533F145905FF1097A5F5DCC1C8FB503337607272B2551B96929252E0297E6500C8839E6900785EA8971F5407971F48390D59A679214BDE0D89BEBE1D69C28469D4A4893755AE5C494C85F7C5175F53F3E64DA875EBE6065E47A2B1632368D0A070B15A6064E487ECF88A7AC23189301C1FFF0D0BAD112CB0AE2157D77254B5EA0B1413B340E7F7D3BFFF1B141E1E49BB7727515CDC52DAB871110D19328A5AB468226E400C09E9C6CED553EFB51834E8C302D3D06DDEBC8002023AB2362C61C77DC4DAB04ED586CAAC0DB3A95AB517D87E6FBA76ED26FB60F1AAE67CCA762F62C78C61C7C4B0635C54C77C6DE0DF42A29D3B0F883263C60ACDD63B777E56DD3069EE947BC5C2EEA90F72C6DE87FB4835E7736C6C2C85858559AD61FCFC4FBD2E00C8A0C025008067983A31F13F9D27B2B25AF5355DCEB19274D3620DC9C878AC594E3C33334B33CCC158A8E3B36E289714779419F625F15A7CE610FDCB6F4B5AE7A602CB7AF363F952E7EA9B018D8750DDFB3232320CB441DF318F45B836774973D3EB163C4EA16863ADFF178BF23EE4ABB45C149F3BD8F5BC78F1A255166EE1433CDCDCDCA4ACAC2CF5F7CF7BC82FE157088071E899068067D97F548180F7009A714395E57A2F5D5C9C35E7E36155CE34734F075DC36D92B45EAB2CE95F1DD1F0B995ED342D38EBDAA7FEE060CAD2E5F9AF5DFC41DA86DF87D754C1DB2F272787E6CE9D4B3367CEB47803F979B582F47E046900F930661A009E655FB2B296CC9E9940FE12E2E69FDBD872E5FAC7631B1F1BADEFFB28CA733975F53D96CC38A6D407690BBC0F49335667CE9C3974EAD4298B368E9F8F0574ED0BB104BF3A0010A601008A21F816A5981AAEE56C2703DB250B856A7303B7DC739812824B4590B6047E3362227FC07BA7FBF5EB27A6C9B3043E1DDE9B6FBE99CB68F74AAFC7CF3C00C23400403184684B067163C79119019A64866F4B87EA7CD1D1DFD29123A926846A39A1DC52BDDAA50A5F014824E8B4B434EADDBB779103350FD27DFBF6957EFBED37F59DA4FCA6C8E1A49CAE0F0064B2C7250000D069D2A4497CF53F6BDEA72D15A90E9FDA6ECB961F2931F190E8B1FCBFFF7B516C8F8BFB915E78A192981983DBBA75A798CBB97CF972E2797CFCFFC4BEA347532921E127AD6325B66FB7D6BE3D05CEAB6E4B52D231FAE187443A77EE22D5AFFF92E686C1F8F89FC40C1E070FFE42172E5CA17FFEB94793272F14CB96DFBBF7809A366D68E1306CD9501D15B5547CB1D1F723BF59F12A299719B7BB72E50A6DDBB68D3A77EE4CD5AB5737F9647C6847606060EED1A347B53BD5FEC5CA1EFCE803204C030058304C1717C9E47A1E1EC162FA3B37B7DA74EBD65FE4EEFE9AD8DEBE7D30F9F8788AA0CB75ECD88FD575A7860DEB89E37D7D07D1DEBD87E8ECD93FC48C1E9F7C32955E7DF565B1EAA1AFEF60B6EF30DB7781ED7BC2F64D53EDAB2FCE357AF49762B9EF4E9DDAD1C99369F4FEFB93E8ADB77A8A9B077D7D43E9A79F0ED1F9F397A956ADEA54B6ACB308E47C5ABC4A952A50DBB64D0D7CCF251BA44B419816195815A8F9129A76BC677AD9B265ECC3CA036ADEBC39FB20E36AF4047CD68E2953A6506868A874FBF66DED20CD97FB5C881F7B00D361360F008022055C850502B239C74B74E6CCEFB464C934F2F46CA5E318C3433B7AF7EECA82F150F19887DDF9F35753AF5EFEAA7D01AA7D926ADF1AB1EFFCF94B62D8C68D1B07C5F681037BB0369CA779F3D6B0203A4A1CEBEBEBA17AAC7C2D37B73A1410E045FDFB7793196C4B26489722DF90F246C645AC54E27F39F8EAABAFC48D89818181141212426DDAB4A19A356B8AD512F9500E1EA0F9822CDF7FFF3DC5C7C7E7656464D869BD71F9D08E0F54E70500846900005B0CDC453D5E779D850BBF241F9FFED4BDBB0F4D9F3E8E1A35AA27E33875C8ADAD79FEDA6B0D69F6EC959A1ACA7DCA7ACA7DAB54E1FD3C55A8E0CA827465CD7EDE637DFAF4EF9AE7EEEEAF5A202C23481BC16F10E4EBAF2F63C58F6FE0A13A2121411423B47BA3F9CD867C8C741A7E8E01CC871B1001000C86586B1739AFA7BBCEBBEF0EA4CB970F8ADE5F0F8F5E62A1145D81313737CF6060CDCECE1F33FD74F8CCCECE66FB6A89677C41151EDAB4F7F3B1D08E8E0E323E0414750A3D4BCDFCF1CCB8C40AFF53025F7A7C379976D3200FD1FC4F0F3E08D20045879E6900008361DA108505CE61DEB179797962C5C01A35AAD0F4E963293A7A355DB9F2A718F75CAE9C0BDDBFFF401C3F6DDA22F6F861A1F3F131D1EA6D3131F1F4C61BBE4FED53D68D894950ED93C4100EBE0ADF8103C7C9CBAB9558C931292999264C784F6FA0E52B29F21B258D075F5303B2DCE3A567FD4DBA5955F8B2887CD9712F56F8528E3559E1ABE0F021217CDA8F14522E11BE99B0200B00C2340040E908DB963C8754284CD7ABE74D6DDBB6A04B97AE514444A8EA264189C68E1D4183067D2856218C8C0CA7CA952B140A9A070E2493A7E72E761E49EC1F3BF65D4D1D1E963D3DFB89D7A85CB9A26A9F72E5C68D1BE7D0902163A8458BC6949A9A462121013478F01B7ADBCCC74A878747D1EEDD87282E6EAE8502B2DC63A4E7E9CDC8574A9CA32A00508C14B8040000BA93A0245DB2E190AEACC37BA7796FB1727ABAFC7D7C960E2E7F7BFE396BD7EE400B16445150902FE5E464B390ECA4B5CF8BED9BC4F6F988211DCA7D85C36A4646A608D7BCE7D9D8B2E57C5690FCB694C4186A99FF212ADAE0FF450030197AA601004C0EB50A0B9EAB68C7F2DE675D6382F383ABA4F79C0E0E76AC38E97C0D3E3E9AEFD71750F95478C6166C513FD7DD96E2BA49110000611A00A09484ECE238AF64E6FEFCED6BD77E4D4D9A34D05977EDDAAFD8BE974D08B1457D6E4E58D6B70F211A0010A601009E83D05C1CC15A7FA8F6F169AFB77EFE3E49C6392D15A22DB10F411A0010A601006C384C2BAC745E4B1D635EB0363D305B33449B1BAA11A40100611A00A014056B5B781DC982FBCCDD56123DD3DACF11A20100611A000001BAC86DB056AF7449876A437511A40100611A00004C0E8545BF01D13A01BAB84235423400204C03003CA341B7245ED752BDD2B61EA211A40100611A00E0190AD30A0B9DC752E7B0648FB52502B3254336423400204C03003C4741DBDAE72CCA386A5B1FEE81200D0008D3000008CC3613AE4B6A660F846800409806004098D628E979A6E51E67CD31D4C531FC03211A0010A601009EF1606D2BAF632B337B20440300C234000068B1B7B7CFCECCCC747476762A2541DEDA37209A1B984B4788CECC7C42767676790CDEFC0060123B5C020080C29C9CCAA41E3E9C5A4C41D95891738CB9FB0D6D3356D7D4F3693F97C8967AA35352CEB27F73C7D378E70300C23400800564643CDEB16347D22D7961B728C59C906D4A702603FB8B1AB60D1DABEF38DB0AD16ABB761DBEF3F871D64EBCF30100611A00C03262172D5AEF909393636620B674C8B644AF7551B6173568DB6688568B8EDE98CDFFCDF1B6070053D9E3120000E874332BEB898F8383BD53A74E6D2B5AF7A5242B1C531263A8894AE34D8553A7AEB89290B0FF2C7F88B73D00980A3DD30000FAFD7BF2E44565F7EF3F9EFE7C0CF5B0D41010DBEE85D676F2E4F98C891397BA4892341A6F770030077AA60100F4BB9397279D8889D9DEAD53A7363975EBBEE852F4534AC570AC64E17DE64E8567DB5253CFDDF5F40CBBFFE449F670F6743FDEEE0080300D006079177272727F5DBD3ABEDF9D3BFF24070474A86B67A7B0C2CB48563AC69A8BB894CE10CD8D1933E7E0D0A15195B203F327700000010449444154B373DE654F13F03607007329700900006469CCCA6C27A732CD83833B9F1E3CB847B5AE5D3B3476767672B6EECB16C7222ECF7E80CECCCCCAFCF9E71367626276FEBD61C3AE7A8F1F675D629BFFCDCA29BCB50100611A00A0F8B464258495CEACB461C51997A454E0D3B2A4B09248CA593B0EE3920000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000058DFFF037F1AD8DFF2F5CEE20000000049454E44AE426082, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203f0-c278-11e9-8c70-2e15a8856301', 1, 'Helpdesk.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C646566696E6974696F6E732069643D22646566696E6974696F6E73220A09786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C22200A09786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E22200A097461726765744E616D6573706163653D224578616D706C6573223E0A0A093C70726F636573732069643D22657363616C6174696F6E4578616D706C6522206E616D653D2248656C706465736B2070726F63657373223E0A0A09093C73746172744576656E742069643D22746865537461727422202F3E0A09093C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22746865537461727422207461726765745265663D2266697273744C696E65537570706F727422202F3E0A0A09093C757365725461736B2069643D2266697273744C696E65537570706F727422206E616D653D224669727374206C696E6520737570706F7274222061637469766974693A61737369676E65653D226B65726D6974223E0A090920203C646F63756D656E746174696F6E3E4669782069737375652072616973656420627920637573746F6D65723C2F646F63756D656E746174696F6E3E0A09093C2F757365725461736B3E0A09093C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D2266697273744C696E65537570706F727422207461726765745265663D226E6F726D616C456E6422202F3E0A0A09093C656E644576656E742069643D226E6F726D616C456E6422202F3E0A0A09093C626F756E646172794576656E742069643D22657363616C6174696F6E54696D6572222063616E63656C41637469766974793D227472756522206174746163686564546F5265663D2266697273744C696E65537570706F7274223E0A0909093C74696D65724576656E74446566696E6974696F6E3E0A090909093C74696D654475726174696F6E3E5054354D3C2F74696D654475726174696F6E3E0A0909093C2F74696D65724576656E74446566696E6974696F6E3E0A09093C2F626F756E646172794576656E743E0A09093C73657175656E6365466C6F772069643D22666C6F77332220736F757263655265663D22657363616C6174696F6E54696D657222207461726765745265663D2268616E646C65457363616C6174696F6E22202F3E0A0A09093C757365725461736B2069643D2268616E646C65457363616C6174696F6E22206E616D653D2248616E646C6520657363616C61746564206973737565222061637469766974693A63616E64696461746547726F7570733D226D616E6167656D656E74223E0A090920203C646F63756D656E746174696F6E3E457363616C6174696F6E3A20697373756520776173206E6F7420666978656420696E2074696D65206279206669727374206C6576656C20737570706F72743C2F646F63756D656E746174696F6E3E0A09093C2F757365725461736B3E0A09093C73657175656E6365466C6F772069643D22666C6F77342220736F757263655265663D2268616E646C65457363616C6174696F6E22207461726765745265663D22657363616C61746564456E6422202F3E0A0A09093C656E644576656E742069643D22657363616C61746564456E6422202F3E0A0A093C2F70726F636573733E0A0A3C2F646566696E6974696F6E733E, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203f1-c278-11e9-8c70-2e15a8856301', 1, 'oneTaskProcess.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C646566696E6974696F6E730A2020786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C220A2020786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E220A20207461726765744E616D6573706163653D224578616D706C6573223E0A0A20203C70726F636573732069643D226F6E655461736B50726F6365737322206E616D653D2246616D6F7573204F6E65205461736B2050726F63657373223E0A20200A202020203C73746172744576656E742069643D227468655374617274222061637469766974693A696E69746961746F723D22696E69746961746F7222202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22746865537461727422207461726765745265663D227468655461736B22202F3E0A202020203C757365725461736B2069643D227468655461736B22206E616D653D226D79207461736B222061637469766974693A61737369676E65653D22247B696E69746961746F727D22202F3E202020200A202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D227468655461736B22207461726765745265663D22746865456E6422202F3E0A202020203C656E644576656E742069643D22746865456E6422202F3E0A202020200A20203C2F70726F636573733E0A0A3C2F646566696E6974696F6E733E0A, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f06203f2-c278-11e9-8c70-2e15a8856301', 1, 'createTimersProcess.bpmn20.xml', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C646566696E6974696F6E7320786D6C6E733D22687474703A2F2F7777772E6F6D672E6F72672F737065632F42504D4E2F32303130303532342F4D4F44454C2220786D6C6E733A61637469766974693D22687474703A2F2F61637469766974692E6F72672F62706D6E220A2020786D6C6E733A7873693D22687474703A2F2F7777772E77332E6F72672F323030312F584D4C536368656D612D696E7374616E6365220A20207461726765744E616D6573706163653D224578616D706C6573223E0A0A20203C70726F636573732069643D2263726561746554696D65727350726F6365737322206E616D653D224372656174652074696D6572732070726F63657373223E0A202020203C646F63756D656E746174696F6E3E546573742070726F6365737320746F206372656174652061206E756D626572206F662074696D6572732E3C2F646F63756D656E746174696F6E3E0A202020203C73746172744576656E742069643D227468655374617274223E0A2020202020203C657874656E73696F6E456C656D656E74733E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D227468726F77457863657074696F6E22206E616D653D225468726F7720657863657074696F6E207768656E20657865637574696E672074696D6572220A20202020202020202020747970653D22656E756D222072657175697265643D2274727565223E0A202020202020202020203C61637469766974693A76616C75652069643D227472756522206E616D653D225965732C20706C6561736522202F3E0A202020202020202020203C61637469766974693A76616C75652069643D2266616C736522206E616D653D224E6F207468616E6B7322202F3E0A20202020202020203C2F61637469766974693A666F726D50726F70657274793E0A20202020202020203C61637469766974693A666F726D50726F70657274792069643D226475726174696F6E22206E616D653D2254696D6572206475726174696F6E2220747970653D22656E756D222072657175697265643D2274727565223E0A202020202020202020203C61637469766974693A76616C75652069643D226C6F6E6722206E616D653D224F6E6520686F757222202F3E0A202020202020202020203C61637469766974693A76616C75652069643D2273686F727422206E616D653D223130207365636F6E647322202F3E0A20202020202020203C2F61637469766974693A666F726D50726F70657274793E0A2020202020203C2F657874656E73696F6E456C656D656E74733E0A202020203C2F73746172744576656E743E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77312220736F757263655265663D22746865537461727422207461726765745265663D226578636C7573697665477722202F3E0A0A202020203C6578636C7573697665476174657761792069643D226578636C7573697665477722206E616D653D224578636C75736976652054696D6572206475726174696F6E206761746577617922202F3E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77322220736F757263655265663D226578636C7573697665477722207461726765745265663D226C6F6E6754696D65725461736B223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B6475726174696F6E203D3D20276C6F6E67277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77332220736F757263655265663D226578636C7573697665477722207461726765745265663D2273686F727454696D65725461736B223E0A2020202020203C636F6E646974696F6E45787072657373696F6E207873693A747970653D2274466F726D616C45787072657373696F6E223E247B6475726174696F6E203D3D202773686F7274277D3C2F636F6E646974696F6E45787072657373696F6E3E0A202020203C2F73657175656E6365466C6F773E0A0A202020203C757365725461736B2069643D226C6F6E6754696D65725461736B22206E616D653D225461736B20776974682074696D6572206F6E206974223E0A2020202020203C646F63756D656E746174696F6E3E54686973207461736B2068617320612074696D6572206F6E2069743C2F646F63756D656E746174696F6E3E0A202020203C2F757365725461736B3E0A202020203C626F756E646172794576656E742069643D226C6F6E6754696D6572222063616E63656C41637469766974793D227472756522206174746163686564546F5265663D226C6F6E6754696D65725461736B223E0A2020202020203C74696D65724576656E74446566696E6974696F6E3E0A20202020202020203C74696D654475726174696F6E3E505431483C2F74696D654475726174696F6E3E0A2020202020203C2F74696D65724576656E74446566696E6974696F6E3E0A202020203C2F626F756E646172794576656E743E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77342220736F757263655265663D226C6F6E6754696D65725461736B22207461726765745265663D22746865456E6422202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77352220736F757263655265663D226C6F6E6754696D657222207461726765745265663D226C6F6E6754696D657245787069726522202F3E0A0A202020203C7363726970745461736B2069643D226C6F6E6754696D657245787069726522206E616D653D2245786563757465207363726970742220736372697074466F726D61743D2267726F6F7679223E0A2020202020203C7363726970743E0A20202020202020206966287468726F77457863657074696F6E203D3D2027747275652729207B0A202020202020202020207468726F77206E6577206A6176612E6C616E672E52756E74696D65457863657074696F6E2827466C6F7761626C6520456E67696E6520526F636B732127293B0A20202020202020207D0A2020202020203C2F7363726970743E0A202020203C2F7363726970745461736B3E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77362220736F757263655265663D226C6F6E6754696D657245787069726522207461726765745265663D22746865456E6422202F3E0A0A202020203C757365725461736B2069643D2273686F727454696D65725461736B22206E616D653D226D79207461736B223E0A20202020200A202020203C2F757365725461736B3E0A202020203C626F756E646172794576656E742069643D2273686F727454696D6572222063616E63656C41637469766974793D227472756522206174746163686564546F5265663D2273686F727454696D65725461736B223E0A2020202020203C74696D65724576656E74446566696E6974696F6E3E0A20202020202020203C74696D654475726174696F6E3E50543130533C2F74696D654475726174696F6E3E0A2020202020203C2F74696D65724576656E74446566696E6974696F6E3E0A202020203C2F626F756E646172794576656E743E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77372220736F757263655265663D2273686F727454696D65725461736B22207461726765745265663D22746865456E6422202F3E0A202020203C73657175656E6365466C6F772069643D22666C6F77382220736F757263655265663D2273686F727454696D657222207461726765745265663D2273686F727454696D657245787069726522202F3E0A202020200A202020203C7363726970745461736B2069643D2273686F727454696D657245787069726522206E616D653D2245786563757465207363726970742220736372697074466F726D61743D226A73223E0A2020202020203C7363726970743E0A20202020202020206966287468726F77457863657074696F6E203D3D2027747275652729207B0A202020202020202020207468726F77206E6577206A6176612E6C616E672E52756E74696D65457863657074696F6E2827466C6F7761626C6520456E67696E6520526F636B732127293B0A20202020202020207D0A2020202020203C2F7363726970743E0A202020203C2F7363726970745461736B3E0A0A202020203C73657175656E6365466C6F772069643D22666C6F77392220736F757263655265663D2273686F727454696D657245787069726522207461726765745265663D22746865456E6422202F3E0A0A202020203C656E644576656E742069643D22746865456E6422202F3E0A0A20203C2F70726F636573733E0A0A3C2F646566696E6974696F6E733E0A, 0);
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('f10066d3-c278-11e9-8c70-2e15a8856301', 1, 'reviewSalesLead.reviewSaledLead.png', 'f06203e9-c278-11e9-8c70-2e15a8856301', 0x89504E470D0A1A0A0000000D4948445200000440000002670806000000978626FB000057774944415478DAECDD0B9054E59D377EA2EB1AFFC672DD4ACA6435ABE6B55CE2BAAEA6FEAE5B7FA3E53F95BCC68ABBC6182F73E12220104463505F515731DE508C45A2C1784350094488CB1AD6C41B304210830A8A100964448101841082A81851CFDBBFB39E49F7CCF44CCFADE7F6F9543D35E7F4397DA6E9997E78CE779E4BBF7E0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000065B664C992FF6FD4A8516BBEF8C52FEECEED268AA2288AA2281D5D0E3EF8E0778F38E28829B9ED4F697D01005D127EE47C70DA69A725CF3FFF7CB26BD7AE0400A023EDDEBD3B79E595579233CF3CF3A3FDF6DB6FB510040028BB0B2EB8A036C20F008072F8DAD7BEB635D70419AF15060094550C7B899E1F0000E5B072E5CAB7724D90355A610040B919F60200944D0C87E9F73FF38200009437000100282701080020000100042000005D1580F4CB5BC66EAFBDF64AFEE11FFE2159B060417B1B3EDDAD21D667AE07000210004000D2C20D786D6D6DBAFDC52F7EB1B735C4042000200001000420FD0AF6A32748D8B163473278F0E064EFBDF74EF6D9679FE4ACB3CE4AB66CD992CC9C39333D6FE0C081E979714EECFFEC673F2BB85EB1E787CF7EF6B3C977BEF39D74FBBEFBEE4B9F3369D2A474FF820B2E48F6DB6FBF46AFF5B1C71E4BFEE99FFE297D7D71BD934F3E3959B76E5D7AECD9679F4DFEF11FFF313D16D79E366D5AA37F5F73AFA7B96B177BBF9ABB5EB1D71321D3B1C71E9B9E3F6CD83001080002100080720720AFBCF24ABAFDCFFFFCCFE9FE902143D2FD787CE5CA95E9F6A9A79E9AFCE10F7F48B70F3DF4D0F4BC2F7CE10BE9FEE6CD9B0BAE57ECF9E1CC33CF4CFEEEEFFE2EDDFEF297BF9C1EFBD77FFDD774FFE0830F4ECE38E38C46AF75FFFDF74FCFDBB06143B26CD9B274FB5FFEE55FD2631136C463D96B88731BFEFB9A7B3DCD5DBBD8FBD5DCF58ABD9EAF7CE52BE9FE9C3973D2D045000280000400A08C014856A277C20B2FBC901E8B9E0D0D8FC763217A4BC4FEAF7FFDEBF46BDCF0370C089A7BFE35D75C53D06B240B20B2FDAC7748BE2C28895E15DFF8C63792BBEFBE3BED851122B8B8F2CA2BD3D022FF3594FA7A9ABB76B100A4B9EB157B3DD9733EFCF0C3B40840001080000094310009DFFDEE77937DF7DD3779E289271ADDAC3774E18517A6C7A2A7467E6051EC66BFA1D9B367A7C7A2B7477C9D3A756AC1FEF4E9D31B3D27AE535353937CEF7BDF4BFEFEEFFF3E3D2F266D0D316F49ECFFEA57BF6A310069EAF53477ED960290A6AE57ECF5084000108000007471001237E471D37FC00107A44337AAABABD363F7DF7F7FFDF098934E3AA920C0881E13F1F591471E6974BDE69EBF71E3C6825E2721BFB744532BD1C4109938163D54B261381198E4070BD9F7692A0069EEF53477ED62EF5773D72BF67A62884CBF8F87C064EFA1000400010800401903901037E6B17FEEB9E7267FFCE31FD3792E62C2CEB8A18F9BF7082EC2AE5DBB923DF7DC333D37BE66C345F2AFD7DCF343F43689737FF2939FA4FB31EC247BFEEEDDBB1BBDD6786ECC1D12D78BE0250293986C347B6E7C8F86138B96FA7A9ABB76B1F7ABB9EB157B3D31B16ACCB112DF63F8F0E102100004200000E5084000000420008000040040000200084000000420008000040010800000084000000108008000040010800000084000000108002000010010800000021000000108002000010010800000021000000108002000010004200000021000400002002000010004200000021000400002000840000004200080000400400002000840000004200080000400108000000840000001080080000400108000000840000001080080000400108000000210000001080020000100108000000210000001080020000100042000000210004000020020000100042000000210004000020020000100042000800004004000020008400000042000800004004000020008400000010800800004BA8575EBD6258B172F4EE6CE9D9B3CF5D4534A99CBD34F3F9D2C58B02059B3668D5F46108000000840A0B3C28F850B17265BB66C49FEFCE73F2B5D54B66EDD9AD4D4D424AB57AFF64B0902100000010874B4E8F921FCE83E2148F4C201042000000210E86071C32D7CE83E2586C40002100000010874B0B8E1163C0840000108002000010148AEBCFDA74DC96BCF4F49563E7D5D5A623B1E135A0840000108002000815E1180ECDC5E97AC78E2EAE4E5C72E2B28F1581C135C0840000108002000811E1F80AC5FF98B46E1475636AC9C23B81080000210004000023D3F007975FE2D4503903826B81080000210004000023D3E0059F1D4B54503903826B8108000021000400002021045000202100000010874F70024567D291680C431C1850004108000000210E8F101C89A453F2E1A80C431C1850004108000000210E8F101C8B6BAE5C98A27C7351EFE927B2C8E092E0420800004001080408F0F40A2AC7DE1C14601483C26B4108000021000400002BD230079EFBD64CDB33F693CFC25F7581C135C0840000108002000811E1D80ECDC5E97AC5E7447D13940E2589C23BC1080000210004000023D2F0079EFBD64D39A79C92B8F5F5934FCC84A9C13E7EA0D2200010420008000047A4C00D252AF0FBD410420800004001080408F0F404AE9F5D15C6F1041860004042000000210E8F601485BC38FAC0832042020000100108040B70F4014010820000100042020005104208000040010808000441180800004004000020210450002021000000108084004207D4B6D6D6D3271E2C464D4A85149757575525151919C7BEEB969A9AAAA4A468E1C994C9830213D0F042000000210108008407A9419336624C3860D2B083C5A2A43870E4D264F9EECC38B00040040000202100148F7B66AD5AA64F8F0E125871E4D95F3CE3B2F59BE7CB90F3102100000010808400420DDCF942953D2612DF96146EC8F1F3F3E99376F5EB26EDDBAE4ADB7DE4ACF7DE79D7792BABABA64D1A245E91099010306143C2F7A8EE80D8200040040000202100148B73273E6CC46F37B4C9B362DD9B2654B49CFDFBE7D7B7A7EC300E5CE3BEFF46146000200084000018800A47BF4FCC80F3FC68C1993F6F6688B78DE65975D561082E8098200040010800002100148978A393FF27B6D8C1B37AE7E984B5BC5F0981B6FBCB16038CCB265CB7CA811800000021040002200E91AE79F7F7E41CF8FF6861FF921487E4F9098181504200080000410800840CA2E96BACD9FF3A3ADC35E8A89EBE5F72E31140601080020000104200290B21B366C587D381113987676C8A217080210004000027D48DC743EF9E4930210014897FE8ED5D6D6D64F7C1ABD34B66DDBD629AF255687C95F2237E61C010108002000813E20BB118C9BD4B973E776CB00E4E39B84B4ECB5D75EC911471C91BED6F65E4F00D27D7EC7264E9C587FDE8409133AF5F594F37B8100040010804037BB39CD4A4C42D9D45FEBBB430012DBF117FBD8EEDFBFBF1E20BDE8776CD4A851F5C76B6A6A3AF5F52C5AB4A8FE7B8D18314225800004001080405F0C40F2FF5A9F7F93DA5D02906C3F7A82C4F6D6AD5B93810307267BEFBD77B2CF3EFB24679E7966B261C38664FAF4E9E979D5D5D5E979714EECC7DC12F9D72BF6FC3876E08107A637C8B17DD75D77A5CFF9D18F7E94EE8F1C3932D96FBFFD04201DF43B163FA7ECF18E9EFCB4A1BABABAFAEF555959A912400002000840A02F0720F97FAD8F610BDD250059BA7469BA7DF4D147A7FB83070F4EF7E3F1975E7A29DD3EE59453928D1B37A6DB871C72487ADE61871D96EEAF5FBFBEE07AC59E1FC7CE38E38CE4739FFB5CBA7DC20927A4C78E3FFEF874FFA0830E4A4E3FFD74014807FD8EE5EF77D4D2B7C5C492B8F9DF0F042000800004DA7033D75B4B77990324CA31C71C932C5EBC383D163D371A1E8FC7E2D851471D95EECF9F3F3FFD7AE49147360A549A7BFE55575D55D06B64FFFDF72FD8CF7A877445E9CDBF6BE5FE1C83000400108040911BA7BE10E80C1A3428993973667AB31DBA4B0F90D1A34727FBEEBB6FF2D8638F150418BB76ED6AF4BC9857228E454F8DFCC0A2A900A4A9E7CF9A352B3D16BD3DE2EB7DF7DD57B0FFE0830FEA01D241BF63D90A307A80200001001080200029CBCD69C3E023D35D0290082A621598030E38201DCE127338C4B17BEEB9A77E78CC89279E581060C47C21F1F5E1871F6E74BDE69EFFC61B6F14F43A89C78E3BEEB8FAC7DAB3124D5F0E409AFA1D3307080210000001080290B2DC9CC65C183366CC68147C74B70024CAECD9B3D3FDB3CF3E3BD9BC7973FADA6302D3E8CD11F377447011E7EDD8B123D973CF3DD373E36B4C78DAF07ACD3D3F4AF4368973EFB8E38E747FD2A449F5CF8F9E0402908EF91DB30A0C02100000010802904E55ACC747770A40949E1D8094F23B3671E2C4FA5062C284099DFA7ACAF9BD4000020008401080F4300210014867AAADADAD0F25AAAAAA926DDBB675CAF7D9BE7D7BC1709B55AB56A9D4108000000210108008400420E5336CD8B0FA602256DAE90C310427FB1EE79D779E0A0D0108002000010188004400525EF9E144F402E9E8C950E37A71DDEC7B4C9E3C59858600040010808000A46B0290679E792639E9A4937A6C30D1F0F5F76B3081AB00A47943870EAD0F28C68C19D3614BE2C684B5975C724941EF8F96E6BD0101080020004100220029DB8A2F3DAD94EBF5F7D60024E6E4A8A8A8A80F2AC68D1BD7EE1024C28F1B6EB8A1FE9A71FD65CB96A9CC1080000002101080941E80C492B1279F7C72B2D75E7B25FDFBF74F162F5EDC641090BFFFE8A38F26471D7554FA9C587E367A4CC42498B10C6D765E766E4C86194B9566CBD48E1C39325DDE36FF9A5FFAD297D26BEDB7DF7EE90DF319679C919E7BE08107A67349C4B9B104EEC08103D3C7E35A679E7966B261C38682EBC4EB38EEB8E38A861AF9C7A3A7C791471E997EDFF83E53A74E6DF2F537B57DE9A597A6CBEB7EE6339F49EEB9E79EF458FCFBE3DA71BD58DEB7A520A5B7062061CA9429F56145D613A4ADC361E279F93D3F0C7D4100020008404000D2A600E4D4534F4D1BF1117CC4D7A38F3EBAC50064FFFDF74FB7D7AE5D9B2C59B224DDCE828586CF8BF023F6A74F9F9ECC9A352BDD8E1024FFDC471E7924BD56B61FE7AD59B326DD3EE08003D273070F1E9CEE2F5DBA3479E9A597D2ED534E39A5E03A73E7CE4D366EDC583400C93F1EE147BCF6F5EBD7A7C7E2DFD4D2BF3BDB9E3D7B76B262C58A82D717AF253B96FD3BFB6A0012EEBEFBEE82D022E6EE88302B567129459C17738AE4CFF911E5A69B6E52892100010004202000697D0092F57A68692848FEFE09279C906E476F870850264D9A94F6D068EA79D9F577EDDA9596D88EC75AFA1E0DF71BF6CE68EA3A71FDE686B5E41F8FC0E5F2CB2F4F839B7E457A7C140B40F28FEDB9E79E45FF9D7D390069AA274816844C983021A9A9A9497B7764C36362984B5D5D5DB268D1A2E4F6DB6F4F060D1A54F0BC18F612A10A0840000001080840CA1680C40D7E5CEFA28B2E4A3EFFF9CFA78F1F71C4114D3E2F86AB340C0662F8485B0390A6428E96C286A68EC7709F786CCE9C39ED0A409A7A7D0290BF883941F297C76D4B89094FCDF98100040040008200A45D0148360426FEF2DE2F6F084CCC6F11FB31D4E3B1C71E2BB8A13FFEF8E3EB87CDC49092D83EE8A083D263116EC47ECCF31141403674257F08CCA851A35A1D80545656A6DB31E7460C8389ED134F3CB1CD0148165864D7CA8E377CFDA50620A79F7E7AFD1098871F7E5800D2C0430F3DD4EA2024828FE8F561B5170420000002100420ED0E40622848041A319C2526368D7931E2F1981434E6C5881E1C175F7C71C10D7D4C9C1A1395C6B1785E0C23894945E3D8FDF7DF9FCE8B11434362CE879804356E7C23708812E147C349504B0940366FDE9C8629D964AA31E746BC8EB60620316C27AE3364C89082E30D5F7FA90148BC8FC71C734CFABCF8F7F6FB78889000A4504C167BDB6DB7A5BF070DE7F788902BE68C892132D1730404200000021004201D1680281D5362059B9B6FBE399DCB226EF2FBE5F5A6118080000400400082004400D22B4A0C7D89C0235BA637268ACD9614168080000400400082004400D2278B0004042000000210042002100108200001001080200011800840000108002000010188004400020840000001080210018822000104200080000401880044118000021000400082004400A208404000A20906407377A97FF5DA6BAFCD7AEEB9E73E9C3B776EDA0054CA5B9E7EFAE964C182057FC8956A0108021001882200010420DAE8DAE84027888A75E1C285C9962D5B3480BBB06CDDBA3579E6996776E42ADA6F0B4010800840140108D0B703106D746D74A01344AAAC62ED3615EC9F730DF0570520084004208A0004E8DB018836BA363AD009A24B9D8AAD5B35C0770B4010800840140108D0B703106D746D74A013688077BF06B80004018800441180007D3B00F17F84363AD08595EBDB7FDA94BCF6FC9464E5D3D7A525B6E33115A2CA55008200440022000104205D1780BCB7EB9D64EB1BCF27BF7FEE9EE48D977FAE4ED74607DA53B9EEDC5E97AC78E2EAE4E5C72E2B28F1581C5329AA5C0520084004200210400052DE36FAF63757276FBC34B3A09DFEBB853F54A76BA303EDA95CD7AFFC45A3F0232B1B56CE5129AA5C05200840042002104000528636FABB3BB72775AF3E9EFC76DEF8C276F92FFF4FF2DAF35393B777BCA94ED74607DA1380BC3AFF96A201481C5329AA5C05200840042002104000D2B96DF477DEDA52B45D1EBD40E258C3B2AAE6D664EB1B4BD4F3DAE840A901C88AA7AE2D1A80C43195A2CA55008200440022000104209DDB465FB5E0B6A26DF2E68A7941B4D1010188CA550082004400A208404000D263DAE8A5841DB150C1DA171EFC4B79715AB2F1774F25DB37FF4E5DAF8D0E94D2008F555F8A55B2714CA5A8721580200011800840000148D7072045DBEC4F69B36BA3032505206B16FDB868651AC7548A2A5701080290D69B3B77AE7A4F000208403A340059BDE88EA476C9FD05E5F5A53F4DB66D78495DAF8D0E9412806CAB5B9EAC78725CE3E12FB9C7E2984A51E52A004100D27A0B172E4CB66CD9A2EEEB0665F3E6CD6920050840BA731B7DF9AFAE6853EF8FE5BF1A9BBCB5ED0DF5BD363A506A17EC1843D8B0328DC754882A5701080290B6A9ADAD4D6A6A6A9237DFB4646177083F56AF5EEDC30802906EDD465FFBE2432D861DBF9D7753A39560A257C83B3BFFA0CED746074A0A40DE7B2F59F3EC4F1A0F7FC93D16C7548A2A5701080290B68720F3E6CD4B3FFB4AD714E10708407A4A1BFDAD6DEBD2DE1CA50420B1FCEDB6BA97B5AFB5D181D604203BB7D7A5A97173E30CE31C15A3CA5500820004000148E7B6D163F8F9CAA7AF2F61D8CB15C9F6CDAF6A5F6BA303250520EFBD976C5A332F79E5F12B5BAC60E39C38576F1095AB0004010800029032F5D256B4D181F657AE2DF5FAD01B44E52A0041000280004400A28D0EF4F8CAB5945E1FCDF5065149AA5C052008400010800840B4D1816E5FB9B635FCC88A4A52E52A00A154D75F7F7D1A80C4570010800840B4D10195ABCA550042AF73CB2DB7249FF8C42792638F3D36FD7ACD35D7785300108068A36BA3032A5795AB0084DE177E64A147F400D9638F3D8420000840B4D1B5D10195ABCA550042EF0C3F5A7A1C0004208A363AA07255B90A40E851A2A74773214776DC9C20000840B48DB5D1A1F00EEB93CB972F1FF1C0030F3C73F3CD37EFB8E8A28B760F1E3C389D50AFBABAFAA3EF7CE73B7FBEEAAAAB5EBBE5965BA6E5CA3F78C754AE2A5701487BAD5BB72E99316346DA5B2157E72479754E326AD4A8E4EAABAF4EEEBAEBAEE4B5D75ED3126D63B8A127080002106D746DF48E55555575F039E79C333AD76E9D952B6B726567B461736557AEACCD1D9B9DFB7A699CE72EB91B061F8F3CF2C883B946F4EE8B2FBE38B9FBEEBB93E79E7B2EA9ABAB4B76EDDA955680F175CB962DC98B2FBE983CF0C003C977BFFBDD8FBEF7BDEFBD3E7CF8F061DE4195ABCA5500D25A8F3FFE787AE3DE8A3A27B9E4924B922953A66891E6851AA5F6EC68EDF900200051B4D11BABA8A8F866AE3C952B7FFE38F028A5CCCF9D5FED6EB91BC8DD740CB9E1861BDEBBECB2CBD21B8D0F3EF8A0E44AF1A5975E4AC68E1DFBC18811235E3DEBACB30EF76EAA5C55AE029096D4D6D626B93A2769479D935C70C105C9F2E5CBFB7CF8D1DA1E1D42100004208A367ADB9C7BEEB9875654543CDE8AD0A3A9B2D07D73D7DD4DFDD5A38F3E3A6FE4C891C99C39735A7513D2D45F72870C19F2AEDE202A5795AB00A439BFFCE52F938EAA73860E1DDA277B83B477388BE130000840146DF4D639E79C73CEADACACFC637E98515555958C1F3F3E99376F5E3AA4FBADB7DE4A3F37EFBCF34EDAAB79D1A245C9C4891393010306340C4176EA0DD205E1474D4DCD920B2FBC3059BD7A75875490AFBFFE7ADCD8BC3B6CD8B00B04202A3595AB00A4A1050B16249D50E7A4F383F4152D4D789AD7584D9AFB9D29E53A00200051B4D1D3F0E3B48A8A8A0FF2838F69D3A6A543B54BB17DFBF6F4FC785E7E1092BBA6A924CA257A7EC48D48A93FB452C5F5860F1FDEA7132D95ABCA5500D258F4FCE8C43A27993C79729F093F5A1ABE524A0012F404014000A268A3B7DCF3233FFC1833664CDADBA32DE2793104BC4108A22748678B393FE2AFA61DF557D886E2AFB203070EDC9EFB61F61780282A570148CCF951863A2759B66C59AF6D80B666EE8E520390D65E1700018836BAD297DAE831E747FEB09771E3C6D50F7369AB181E73E38D37361C0ED327EF9BCB7507F5C9EBAEBBEECF31FEBE333DF6D863EF5655552D1680282A570148AECE493ABBCE891E263127486F0E3F4AEDA9D19A00440802800044D1466F5A6565E5D3F93D3FDA1B7EE487200D7A822C94547492D9B367FF34DEECF7DF7FBFD32BCC51A3466DEE8B5D7A54AE2A5701C85FC464A5E5AA73626598DE3614A62DC3545A1B8004738200200051B4D1FF2296BACD9FF3A3ADC35E8A89EBE5CF0962284C27F5FEC8357277C7B293E5F0F2CB2FFFB92FF60251B9AA5C05208537D665AC737A552F90B686126D0940DA1AB6002000D146577A6900F254164EC404A69D61C68C197A8174A6E5CB978F884908DBB3F4646B0D193264738C9DEA0DEF5F7C0872E5CB2A57956B57FC5EF5C4002492ED72D7391180AC5AB5AADBBC07C3860D4B9E7CF2C936871F6D1996D2D600243F04311C06809E10809C75D6599FCDDD6BFC47EEEBA7B4D1B5D13BAAED5D5555757036F169F4D2D8B66D5BA77CBE627598064BE41E2AB5E8400F3CF0C033F7DD775F592BCD891327BE94FB415EDA1BDEBFBCEE494F9D73CE39FFDA99956BFE0DCCDE7BEF9D1C7DF4D1C992254B3AB4D2C9AEDFDA632AD7AEF9BDEA890148A4DAE5AE73EEB8E38E64C28409DDE63DC87EBE1184CC9D3BB72C21447B0290ECFBEFB1C71E7A8200D0ED0390B8618CFF67736DA8EDB93236D79EFA7467B5D1A32D7EE28927A66DF37DF6D92739E1841392458B16A5C79E79E699E4A4934EEAF4766D47B7D18B5DAF1CF7025DD5462FA5ED9D7B7C74765E67B72B73F7CBF901C8A5528B0E74F3CD37EF78EEB9E7CA5A693EFBECB3AFE77E90737A530092577ED5547AD89101486CCF9F3F3FDD8E10A45CA18100A4FBFD5EF5C400246EA4CB5DE72C5EBC3819316244B70B40B272FEF9E737DB23A42386A1B4370009E60401A0270420D1F3A3C1FFB5BB72E5D6DC0DEC511DDD463FE28823D2F763F9F2E5C9D2A54BD3EDCF7FFEF3656D3B972B00E9CD6DF452DADEB9C76665C76B6A6A3AF53316215ADE6B9923B5E840175D74D1EE4D9B3695B5D2DCBC79F396DC0F727D2F0D40EAD3C3FC0F4D470720D9FE5E7BED55702C52E6E38E3B2E7D2CBA65C54D5FA4D1914AC792A33B76EC48A64F9F9E9E5B5D5D9D9E174B85C67E8C63CBFF1E3164E098638E499F3F64C89082635BB76E4D9F97A5DD679E7966B261C386A2AFF9D24B2F4DF6DD77DFE4339FF94C72CF3DF7B4788D030F3C307DEDB17DD75D77A5D7F8D18F7E94EEC7BF63BFFDF6EB6B014893BF573D3100C9D5394917D43949656565B70D40F27B84340C423A6A0E8E8E08403AF2F5002000E9A2B6D48CFC20A4BD6DF468C3C6FB11EDE6FCC7A37D9BFF7F6F736DF3626DF9D6B6B75B7A4EF44839F2C823D3FB87686B4F9D3AB5C5367FB1EFD35C1BBF9704208DDADEB9FD35D9E31D3DF96943757575F9AF63BDD4A2030D1E3C38D9B56B57592BCDF87E91C4F6E600243F3D8C6E549DD503242AABFC63D19D7EE3C68DE96351C1C6631178CC9A352BDD8E8A368EC7F621871C929E77D86187A5FBEBD7AF2FF81E279F7C72BA3D7BF6ECE4D1471F2D3816BF37B11D49F74B2FBD946E9F72CA29455F735C63C58A15E9F601071CD0E235CE38E38CE4739FFB5CBA1D5D09E3D8F1C71F9FEE1F74D041C9E9A79FDE27039086BF573D3100E9C23AA7DB0720F93D42E2B35C4A8F8BFCC65547979642107382000840FA7A1B3D8642C4FB1121C0A851A3D29E20C5FE7859AC6D5EAC2DDFDAF6764BCF89F02386EC646DFEFDF7DFBFC5367F4B0148536DFCDE1480E4FFBEE4CABBD97E472D7D5B4C2C899BDF83496AD1C13FECEE7803D0DB4A47CF01B2E79E7BA695D8C2850B0B8EC58D5EC3E4391E8B92CD1D12C78E3AEAA8743F0B52E25A0D2BB6A69EDFF058C379494AE9B512AFBDA56B5C75D55505BD52A282CEDFCF7A87B4B5F496DFAB9E1880A8734A2BDFFAD6B7D29FEFB1C71E5B4A23B4AC014888D715E7BCFEFAEBEE0400FA6000327CF8F0BDFEEDDFFEEDFF39EBACB3F68F3936AAABAB3F97DBFEFB73CE39E77FE5F6FB575555FD536EFB4BB9FFD38E8FBFA09F7DF6D9FF7F6EFF7FE7F6BF114B89E6B6CFCA6D57E6B607E5CAB0DCFE7772FB17E5B62F89793B72DB57E7CA75B9FDF1B9FD1FE4B67F942B77E6B6EFCD7D9D9A2B3FCD1D9B99DB9F9DDBFEEF5C793257E6E7CAAF73E53725FC5FFB61AE7CD0116DF418AE10C1438420F1DE4428D2543BB8B9B679736DF9D6B4B79B7BCEDAB56B93CB2FBF3CED61526A9BBFA500A4A9367E5F68A397BBED2AB5E84083060DFAA8DC7F8D7DF7DD7737F7811E20CFE72AE4D34E3EF9E4BFEAAC21302D1DCBBAE4E557665131C7B148A8633F7A52E4070AAD0D40F22BE95287ED94728D2C158FDE1EF13526CDCCDF7FF0C107FB6A0F9082DFAB9E1880E4EA9CA40BEA9C1ED10324DE9B993367A6BFA3A194555FF40001A08BFE1F7B3F57DEC9953FE5CAD65CD9982B6FE4CAEF73E5D55C7B6579EEEB8BB9F25C2CE5595151312FF7F589082B22B488F022428C08333E0E35EE8C9023C28E083D22FC8810E4E3494C2F8970244292084B223489F02442940853225489702542965C39A1B2B2F25F9A694BAD8EEBFCFBBFFFFB7ED19EEAC85560B29E150D438D52DAE64DB5E5DBD2DE6EEE39FDFBF74F8FCD9933A75302908E9837A41BF600A96F7BE7B677EA01D20B5C78E1857F2EF778FC8D1B37FEAE17CF01D2F006B55F5705205917B8FC6E76117CE4070C310630BE3EFCF0C38DAE13DDE5B2AE6DD9F9D9B1984F21B663AC5F36E153CC80DD9A00A4B96BBCF1C61BF5E766C37CF2D3EAE81ED8C70290267FAF7A6200327AF4E8B2CF0112DFAF3BCF01D230F8682A6C30070800DD450F1902B335FFFFDA581126C2935CFBF8931DD946CF4285185AF2C20B2FA4DB31316A1CCB7A84C43C1F112C34D7366FAA2DDF96F67673CFC9828EECF152DAFC7D380069D4F63607482F71F5D557AF29F78A0C4F3CF1C42F7BE12A308B23816EE206B5CB029098682926558CCA2E4A54B0D9444BF135BAA965DDD562C2A486D7A9ADAD4D5799899024AE937F2C26958C4A3C9BC4292ACE082D5A1380B4748DEC3F8D58C234F6274D9A54FFFC4845FB4800D2ECEF554F0C40AEBDF6DAB2AF021343BDBAE32A30F1FB1FCB0237157C34153AB4A7C7453FABC000D0B70290B5792BC05C9BFB3FF76F3AA38D1EF36C64CBE0469B3AE6AE8BD5E7E2D8FDF7DF9FCE8B118FC730EEE6DAE64DB5E5DBD2DE6EEE39D1968EC71A4E74DA5C9BBF0F062045DBDE5681E9256EBDF5D6FB627841398D1B37EEB1DEB29E71337F99EFF00044E953CBE096F47BD5130390A86FCA5DE7C458DCCE5EAFBD359AEBF1D15208D2D6F0A1BD01480C3BDB638F3D841F00F494002486E14C8CF949B4D1B5D13BA2ED9D3B3E3A0B253ABB5D3971E2C4FC00E452A94507FA8FFFF88FFF357AF4E80F3FF8E08372D599BB2A2B2B6B733FC843FBD2FBAC7255B976921ED7687AEDB5D7D2613065AC7392010306A44BBCF574EDE981D19E00240B5FC68E1DABD50F408F08408AF5F8D046D7466FABAAAAAA8373F7B0BB2394C86DA73D7A3AC3F6EDDB93EAEAEA8FF2029043FBD1B1C68C19F3FB175F7CB12C15E6134F3C312D2642EA6BEFB1CA55E52A00F98B5833BE5C75CE82050B92F3CE3BAFD7343ADBDA13A4AD0188F003809E188068A36BA377868A8A8AA7B2602286357586181E9D177EF4B9FBE6B2183A74E859975C72C9EE32FC45767B6565E5D2DC2F4EB5004451B9F6DD002426DDCDD53965E90512E1C7E4C9937B55C3337A82B476384A5B0210737E00200051B4D10B02906F66E144F402E9E8C950E37AB9FBE5FADE1F7DF1BEB96C468C18B13C9644EA4C31DF48A45825CC6B2000E9E4D2AF83262AEA29D71380743F310CA6B3EB9C986B240290D6CCB5D1934290D64C8CDADA00C452B70008404A2B311FC461871D964E22BADF7EFBA52BB1C4C4A2F96DDA7E1FAFBE182BC464AB19E61FCB264E8DAFF98F77F63DC133CF3C939C74D249029056C8DDCFCECF028A3163C674D892B8B1C8C325975CF2417EEF8FBE78DF5CCE1FE4A143860C7977F5EAD59D52512E5FBEFCD1DCF7D8545151D1BF2FBEBF0210018800A450CCC911B39077569DF3DBDFFE366D802C5BB6ACD736405B331CA63501486BC3150004207DB58D9EAD52F8B5AF7D2D5DEAF6D1471F4DF7635595866DDA68FBC4762C9DDB3000F9C10F7E501FA694330029D7F7E96501C8A1F9CB2C8F1B37AEDD2148841F37DE7863FEBC1F3BFBEA7D7359555757578D1831E29D2D5BB6746825B961C3865FE76E44DEE8CB5D783A3300898AF6A8A38E4A53E558F22A52DC2C758E54F7C8238F4C8F1D78E081C9D4A9531B5576B104EEC08103D365B1E2F9679E7966FCCC5ABC76B1CAB3B9EB157B3DF11FC231C71C939EDF70692E0148EF0C4042FCFC6379DA8EAE7362EDF45C7DD6EB86BEB4270429350069CBF01A0004207DB58D1EEDD7783F66CF9E5D52C090F504C93F1681C837BEF18DF4B1F81A6DE5626DE1626DF3587E37CE5FBE7C797A5E7C8DFDE38F3FBEE873A2ADDE306C69AE1D9F9DF7A52F7DA9BEB74BDCFC9F71C619E9F9D1B68F7931FA421BFD9C73CE39379B1035EB09D2D6E130F1BC063D3F0C7D29A7AAAAAA21C3870FDFD9517F958D9E1F117EE47E49C6F6E5F7B5330390FDF7DF3FAD8CD6AE5D9B2C59B224DD3EEEB8E3D2635181C663EBD7AF4F1F8F731B56C6B156786C2F5DBA345DCB3CB663CDF096AE5DAC726FEE7AC55ECFC9279F5CFF9F47969C0B407A7F0012EEBEFBEE2457E774584F90E8F911E1C74D37DDD4671AA2A58420A50420ED5D6A170001485F6BA3471010EFC78E1D3B5A0C40A26DDC54EF9051A346A50142F40288EBC57EB1B670B1B6F9F4E9D3D3EDABAEBA2A3DEF8A2BAE48F7EFB9E79E66DBF30DBF4F73EDF8ECDC471E7924BD56B63F6BD6AC64CD9A35E9F601071CD067DAE8E79E7BEEE0FC1024E604890028567129459C17139EE6CFF911A5AFDF377759A295BB81D8F15FFFF55F6FB76392C2EDB7DD76DB9418F612BF1C7DFD3DEDCC00244B7CA3C23CF5D453D3AE7891DEC6B1A89C2EBFFCF2B492CBAFE0F2B71BA6BF51E2B196AE5DAC726FEE7AC55E4FF69CE83A184500D27702903065CA9474A9DA5C9DD3AE8951A3C7470C7B8950A5AF69EFB015C35E001080B4BEECB9E79EE9FB114BA236178064257A8C64F37DE40708F1357A53647F102CD6162ED6368FF6F341071D941C72C821E97931274984113B77EE6CB63DDFF0FB34D78E6FAA374B73FB7DA18D1EF7CDB9B23D3FC08820248632D5D4D4A4BD3BB2E1311170452FE5458B1625B7DF7E7B3268D0A00FF39F17C35EDC3777F1D8A6DC8DC4DC1123466C5EBA74E9BBADA813773DF9E49333AAABAB9F8F895B8C5DEAFC00242ABCB8FE45175D947CFEF39F4F2B9E9860298E4597BAD88FC9265B0A40E23AADB9764B014853D72BF67A04207D3B0009310C2A7A827CE73BDF69F5BC1D0B172E4C874EC584A7BD79CE8F52438CD6F6E030E129000290B695E8CD51EA109898007EDF7DF74D1E7BECB1826371631C414A04181152C47EB1B670736DF32C4089F944E2EBC5175FDCE2738A05204DB5E30520C5EF9BF397C76D6371DFDC8D7EA0DF8E2064F0E0C15B6EBEF9E695BFFEF5AFDFA8ABABDB141F8AF0EEBBEF6EDEB871E3EF9E7EFAE9FFBEF6DA6B7F555555F55AFC0023BD326B6D79029018DBD7EFE3D9A3733F8B743B2AD0FC4A2CEB72D75400127F31CFBAC865E79D78E2892D5EBB5885D8DCF58ABD9EE85A97FDE791A5E00290BE1580641E7AE8A13408893023FE038F943CC69EE6D539C9A64D9B92050B16A4E97AF41C8973A3D7476F5CEDA5B55A3B8CC5B0170004206D6F53DE75D75DE9FB117377445B258687C4FEE1871FDEA88D1CC72378889E19D970F0EC58CCCB11DB312CBCB930A1B9B679B4972240C986BCAC58B1A2C5E74420930DE189D7D75C3B5E00D2F27DF3C741C8EED6041FEE9BBBA9AAAAAA83733FD08B733FA059B9B23657767DFC438BAFEB73654EAE5C1A099877ABBC01C81B6FBC914E3E14131545A517C34B62B2D16C66EA081D1A4E2C9ABFBD79F3E674BC5F3C3FCE8D3022AED9D2B58B0520CD5DAFD8EB89899822418FEF316CD83001481F0E4032F13B71DB6DB7A5E360A32B61FE7F16F19F734C9E1A0148F41CA16DA1C6830F3E989E3776EC586F1A0002903696080B62684BFE24A30D87B964E766C35BCE3EFBEC8263DFFFFEF7D3ED1B6FBCB1D930A1A5B6794C609A1FA4B4F49CFBEFBF3F0D64A2074ACC5FD15C3B5E00E2BE19BA4500A20840A0A196E6F4C8567B117E002000E9F96DDBE8BD11F37A643D49B200461B1D50B92A0210FA84623D410C7B014000D2BBDAE831EC257A6CC4109BA953A76AA3032A574500821044F8018000441B5D1B1D50B9AA5C0520F4EA10E4D8638F157E002000D146D7460754AE2A570108BD57CCF911137059EA1600018836BA363AA07255B90A40E8D522000100018836BA363AA07255B90A40108000800044D1460754AE2A57010802100004200210451B1D50B9AA5C0520084000108068A32B02101080A8D454AE02100420000840B4D115010808401495AB000401080002106D746D7440E5AAA85C0520084000108068A36BA3032A574500020210000420DAE8DAE880CA551180200001E83B6A6B6B9389132726A3468D4AAAABAB938A8A8AB43E8C525555958C1C3932993061427A1E0210451B1D50B9AA5C05200840007A9419336624C3860D2B083C5A2A43870E4D264F9EECCD138028DAE880CA55E52A00410002D0BDAD5AB52A193E7C78C9A14753E5BCF3CE4B962F5FEECD148028DAE880CA55E52A00410002D0FD4C9932251DD6921F66C4FEF8F1E39379F3E625EBD6AD4BDE7AEBADF4DC77DE7927A9ABAB4B162D5A940E9119306040C1F3A2E788DE200210451B1D50B9AA5C0520084000BA95993367369ADF63DAB469C9962D5B4A7AFEF6EDDBD3F31B062877DE79A7375700A268A3032A5795AB0004010840D78B9E1FF9E1C7983163D2DE1E6D11CFBBECB2CB0A42103D4104208A363AA07255B90A4010800074A998F323BFD7C6B871E3EA87B9B4550C8FB9F1C61B0B86C32C5BB6CC9B2D0051B4D10195ABCA5500820004A06B9C7FFEF9053D3FDA1B7EE48720F93D4162625404208A363A5082B973E7AAD4BA4FD995AB5C770B40108000F46CB1D46DFE9C1F2D0D7B79F4D1475B75FDB85E7EEF1243617A5F00A28DAE8D0E7482050B166C8A49B8546C5D5FEAEAEA7E96AB5C5F1580200001E8D9860D1B561F4EC404A6CDD9BA756B3A942586CCB43564D10BA4F70520DAE8DAE8402798376FDEE93535357F7AF3CD37DF55C1755DAA1C15EBD34F3FFD46AE7C5B00820004A0FB061B4F3EF964B3E7D4D6D6D64F7C1ABD34B66DDBD6ECF9F197FE6C98CC071F7CD0E26BF8E8A38FD2AFB13A4CFE12B9AD0D500420DAE84A9F6CA303B90FF4694F3DF5D4F3D1B52BC6B729652FF1BEBFDACB2A56AD64042040AFACAFA2441012C14553264E9C587FDE8409135ABC669C9F0526FFF99FFFD9E2F98B162D6AF3F7128068A32B7DBE8D0E800004010840E901485662A2D3863D42468D1A557FBCA6A6A6D9EB7DF8E187E9F095ECFCEAEAEA64D3A64D45CF7FF9E59793A953A7168421D973478C182100E9650108002000410002D02D0290FC1E215910122146F6784B939FAE5EBDBAE03AD113E4FAEBAF6FF2DC3FFEF18FC9F0E1C393E79E7BAEFEB1BABABAFAE75656560A40042000800004010840E70520F93D42F2F75B5AFAF6E73FFF79FDF097FCB260C18282F3A2A7C8B871E3D27377ECD851FF782C899BFF3C018800040010802000013AE8265F29BDB4E4EAABAF6E1480C4FED0A143939D3B77D69F377DFAF4F4D8A5975EDAECCF4B002200010004200840BAA5E81EBF78F1E274324593CD95BF3CFDF4D3E95FDAD7AC59E383E833D9AE7068D0A041C9CC9933D39523F2038DE67A8044EF8DA67A7F6421C85D77DD959EF7E28B2FD69F3765CA9446D710800840000001086EB6BA7DF8B170E1C264CB962D961AECC2B275EBD674A2CA988B0101486B0390FCE02353EA1C20BFF9CD6F0AE6EF88D2F0FAF1BB993F49EA92254B0AAE610E100108002000C1CD56B7173D3F841FDD270429B6A4A9CF244D052083070F4E66CC9851107C644A5D05E6DE7BEFAD0F3D9AEA09923D96FF357F584CB00A8C0004001080E066ABDB8B1B6EE143F72931240601484B9AEAF1D1D0C48913EB4389091326143D6FF4E8D1F5E7FDEC673F4B6EBBEDB66687C45C76D9656DFE5E0210000001086EB6BA4CDC700B1E04203E93BD4F6D6D6D7D28515555956CDBB6ADD1399B366DAA3FE78A2BAE483EF8E083F4B138BF5800F2C0030F145C63FBF6ED05C36D56AD5A2500118000000210DC6CF5DC00E4ED3F6D4A5E7B7E4AB2F2E9EBD212DBF198D04200E233D97D0D1B36AC3E9898366D5AA3E34F3CF144FD1C22117C646292D362BD405E78E185826BC4109CEC58CC13820004001080E066ABC706203BB7D7252B9EB83A79F9B1CB0A4A3C16C7041702109FC9EE293F9C885E1D0D2743BDF5D65BD363F3E7CF2F783CE6F88830A3A9A57163C5974C5C2FBFB7C8E4C993BDE90210004000829BAD9E1B80AC5FF98B46E1475636AC9C23B81080F84C766343870EAD0F28C68C1953BF246E0C7789495463FE8EA6FCE217BF68147E5C7EF9E5F5C72308B9E4924B0A7A7F343727890004004000829BAD6E1F80BC3AFF96A201481C135C08407C26BBAF989323BF27C7B871E3D21064E5CA95E94A316FBFFD7693CF7BFFFDF7930B2EB8A060F597871E7AA83EFCB8E1861B0AC29165CB9679B3052000800004375B3D3B0059F1D4B54503903826B81080F84C766F31A7477E6F8EE80972CF3DF7A4214873F297B78DB274E9D274D84B7ECF0F435F042000800004375B02104500E233D96DDC7DF7DD05A1456565653A316AACE252CC471F7D945C79E595053D401AAE1073D34D377973052000800004375BBD230089555F8A0520714C702100F199EC993D41B2C951274C9890D4D4D4A4BD3BB2394262984B5D5D5DF2F39FFFBC3E0069382748842A0840000001086EB67A4D00B266D18F8B0620714C702100F199EC39624E90FCE571DB5262C253737E0840000001086EB67A5D00B2AD6E79B2E2C9718D87BFE41E8B63820B0188CF64CF13C3595A1B8444F011BD3EACF6220001000420B8D9EA95014894B52F3CD8280089C7841602109FC99EADB6B636B9EDB6DBD215611ACEEF11F3848C1831221D22133D47108000000210DC6CF5EE00E4BDF79235CFFEA4F1F097DC63714C702100F199040108002000C1CD568F0E40766EAF4B562FBAA3E81C20712CCE115E08407C2641000200084070B3D5F30290F7DE4B36AD9997BCF2F89545C38FACC43971AEDE2002109F49108000000210DC6CF59800A4A55E1F7A8308407C2641000200084070B3D5E30390527A7D34D71B44902100F199440002002000C1CD56B70F40DA1A7E644590210069AFEBAFBF3EFD4CC6571080000008401080744A00A20840BAD22DB7DC927CE2139F488E3DF6D8F4EB35D75CA382420002002000410022001180F4BEF0230B3DA207C81E7BEC2104410002002000410022001180F4CEF0A3A5C7410002002000410022001180F428D1D3A3B990233B6E4E100420000002100420021001488F0E3F5A0A37F404E91CB5B5B5C9C489139351A34625D5D5D5494545455A1F46A9AAAA4A468E1C994C9830213D0F0108002000410022005104206D90851AA5F6EC68EDF9143763C68C64D8B0610581474B65E8D0A1C9E4C993BD79021000400082004400A208405A1B7EB4B6478710A47D56AD5A950C1F3EBCE4D0A3A972DE79E725CB972FF7660A4000000108021001882200E98CF0A3A39EDF574D9932251DD6921F66C4FEF8F1E39379F3E625EBD6AD4BDE7AEBADF4DC77DE7927A9ABAB4B162D5A940E9119306040C1F3A2E788DE20021000400082004400A208408A6869C2D3725FA7AF98397366A3F93DA64D9B966CD9B2A5A4E76FDFBE3D3DBF618072E79D777A7305200080000401880044118034155A74D4F0153D414AEFF9911F7E8C193326EDEDD116F1BCCB2EBBAC2004D1134400020008401080084014014883B0220B3F3EFCF0C37412CEF8DA1A0D9F674E90E6C59C1FF9BD36C68D1B573FCCA5AD6278CC8D37DE58301C66D9B265DE6C01080020004100220051FA7600D2B0A7468417471C71447AA3185F4B0D418A3D4F0852DCF9E79F5FD0F3A3BDE1477E0892DF1324264645000200084010800840943E1B8034354C257A707C7C93587208921F7E64255633C99813A4B158EA367FCE8FB60E7B2926AE97DFBBC4501801080020004100220051FA6400522C94682ACC682E0429F57C7382148AA0290B276202D3CE0E59F40211800000021004205D72E3F3E4934F0A40042065FB5D2A167E141B96526AA8D1DAB0A42F0C8729E567525B5B5B3FF169F4D2D8B66D5BA7BC96581D267F89DC987344002200010004200840CAFAEF8912374A73E7CEED960148FE0DED5E7BED95DED4C66B6DEFF504205DF3BBD49610A2A570A3B5E147FEF7DF638F3D7A6D4F90527E2613274EAC3F6FC284099DFA7ACAF9BD04200000021004204DDE20652526426CEA2FC6DD210089EDF8AB716CF7EFDF5F0F901EFABBD430FC28357C281672ECDEBDBB4DE147C31E28BD310429E567326AD4A8FAE33535359DFA7A162D5A54FFBD468C18210011800000021004205D778394FF17E3FC1BA5EE128064FBD11324B6B76EDD9A0C1C3830D97BEFBD937DF6D92739F3CC33930D1B3624D3A74F4FCFABAEAE4ECF8B73623FE637C8BF5EB1E7C7B1030F3C30BD498BEDBBEEBA2B7DCE8F7EF4A3747FE4C891C97EFBED270069C3EF525BC28FE64290BFFEEBBF6E73F8D1DED7D31B3EDFF119C91EEFE8C94F1BAAABABABFF5E959595021001080020004100D2F53748F97F318EAEF3DD250059BA7469BA7DF4D147A7FB83070F4EF7E3F1975E7A29DD3EE59453928D1B37A6DB871C72487ADE61871D96EEAF5FBFBEE07AC59E1FC7CE38E38CE4739FFB5CBA7DC20927A4C78E3FFEF874FFA0830E4A4E3FFD7401481B7E97DADBE3A2A910A43DE147C310A437CD0952CACF247FBFA396BE2D2696C4CDFF7E02100108002000A107DF50F4D6D25DE6008972CC31C7248B172F4E8F45CF8D86C7E3B13876D45147A5FBF3E7CF4FBF1E79E4918D0295E69E7FD5555715F41AD97FFFFD0BF6B3DE215D517AEAEFD1B7BEF5ADF4BD3BF6D863DBF5398C612F0D7B7EC47E3CDE1EF1BAFAEA67BC5C818400440002000840A05B053A83060D4A66CE9C99DE6C87EED20364F4E8D1C9BEFBEE9B3CF6D8630501C6AE5DBB1A3D2FE6368863D153233FB0682A0069EAF9B366CD4A8F456F8FF87ADF7DF715EC3FF8E0837A80B4E177A9A5555FF40029EFCF245B01460F100108002000813E158034BC59CD74970024828AB8C93DE08003D2E12C318F401CBBE79E7BEA87C79C78E289050146CC17125F1F7EF8E146D76BEEF96FBCF14641AF9378ECB8E38EAB7FAC3D2BD1F48500A4D8EF527ED8600E90AEFF999803440002000840A04F05203117C68C19339ABC59ED4E014894D9B367A7FB679F7D76B279F3E6F4B5C704A6D19B23E6EF88E022CEDBB16347B2E79E7BA6E7C6D798F0B4E1F59A7B7E94E86D12E7DE71C71DE9FEA44993EA9F1F7FCD1680B4FE77A9AD3D2EAC02D3393F13ABC0084000000108F409CDFD95BEBB04204ACF08404AFD5D6A4BCF8B62E1471672B474BC9818C6B4C71E7BF4CAF0A3D49FC9C48913EB438909132674EAEB29E7F7128000000840A04D04200290CED2520F8C52C38DD6862059F83276ECD83EFDD9AEADADAD0F25AAAAAA926DDBB675CAF7D9BE7D7BC1709B55AB56094004200080000404204ADF09409AEB09D2DA50A3D4F3851F85860D1B561F4CC42A479D2186E064DFE3BCF3CEF3A60B4000000108084094BE1780643D411A0E47891BF37EAD1CD6D25408327CF8F0927B9CF445F9E144F402E9E8C950E37A71DDEC7B4C9E3CD99B2E00010004202000694D89BF561F78E081E924A7279D7452C104A7CF3CF34CFA586B27592D76ACD876A9DF4700525A08923F316A7E98D19A894D8B3DAF372E75DB51860E1D5A1F508C1933A6C396C48DC9822FB9E49282DE1FAD9927460002002000010148AE644BDCC68A2FB1446EA9C146479E57EAF30520A569381C26C28BE8C1D1DAA56D1B3EAF61B842A19893A3A2A2A23EA818376E5CBB4390083F6EB8E186FA6BC6F5972D5BE6CD168000000210E81B0148161864CBCF1E7DF4D1E9CD57FEB1E85171DC71C7A58FC5A48CB16466B654EDC89123D3C023B6FBE50D73C87F7E53C7A2A7C691471E998626D16B64EAD4A905CF89E116F13D8E39E69846AFA7D876C3EF73C20927A45F972F5F9E9E175F63FFF8E38F1780B42304E9889E25BD79B5978E3265CA94FAB022EB09D2D6E130F1BCFC9E1F86BE084000000108F4D900E491471E491E7DF4D174FB2B5FF94AC1B1B973E7261B376E4C1F8BF0231E9B3E7D7A326BD6AC743B4290A67A5F140B2CA244F8B164C99264FDFAF5E9E3FBEFBF7FC179F15AB2D7930D69E957C21098FCED788DB17DD55557A5FB575C7145BA7FCF3DF70840BA2804E9E830A5B7BBFBEEBB0B428B98BB23869AC52A2EA588F3624E91FC393FA2DC74D34DDE5C010800200081BE1980C4909528FD3EEE49D1F058767ED6CBA2B9F34B0940D6AE5D9B5C7EF9E569CF92A6CE6BE9FAA56CC7F30F3AE8A0E490430E49F70F3BECB0E480030E4876EEDC29006963CF8DF60C5B31ECA5637A826441C8840913929A9A9AB47747363C2686B9D4D5D5258B162D4A6EBFFDF664D0A04105CF8B612F11AA2000010004202000C96DC7D09362F369C4B186E7EFBBEFBEAD0E40FAF7EF9FEECF9933A7C500A4A9D753CA7694983B21F67FF0831FA45F2FBEF8627380744008D2DA1E1C263C6D9F180696BF3C6E5B4A4C786ACE0F0108002000813E1F803CF8E083F561C437BEF18DA20148CC15D2AFC1109851A346B518804448D22F6F82D4AC27C9D2A54B9B0C30A29B7FF67A4E3DF5D492438F86DF67C3860DE93C2331C4261E5FB1628500A49D5A3B8CC5B0978EF3D0430FB53A0889E0237A7D58ED4500020008404000F2F13C1EB17C6D0C4989E129C502909804356EC022C08812E147840D2D0520F7DF7F7F3AFC24BE47841B93264D4A9F3F64C89026C38C986B24828B989475CD9A352507200DBF4F3C3670E0C0F4F8C9279F6C159832872011ACC57963C78EF5C1ED40B5B5B5C96DB7DD967EFE1ACEEF515959997E7E62884CF41C4100020008404000D245CBC696AB440F90AD5BB7A693A8C6BF71F1E2C502900ED4D29C1ED96A2FC20F042000000210E8F20024EBC9D11B039018F612FFB6238E38A27E995D0148797A8218F682000400400002DD2A005104201D1D82083F10800000084040002200E9D521C8B1C71E2BFC40000200200001018800A4F78A393FA20EB7D42D02100000010808400420BDDAEBAFBFEE4D400002002000010188000410800000084040002200010420008000041080084000010800200001018822000104200080000404208A000410800000021010802802101080000008404000A2084040000200200001018800041080000008404000220001042000005A61200011800002100040000202100188000410800000021010802802104000020008404000A2084000010800200001018822000101080080000404208A0004042000000210E84473E7CE153C084000010800200081DE6DE1C285C9962D5B840FDDA06CDEBC390DA4000108008000043A586D6D6D52535393BCF9E69B42886E107EAC5EBDDA2F25084000000420D05921C8BC79F3D2E1174AD714E1070840000004200080000400400002000840000001080080000400108000000840000001080080000400108000000840000001080020000100108000000210000001080020000100108000000210004000020020000100042000000210004000020020000100042000800044000200084000000108008000040010800000084000000108008000040010800000021000000108002000010010800000021000000108002000010004200000021000400002002000010004200000021000E8766A6B6B939A9A9AFAFDBABABAE4DC73CF4D060C18905C7EF9E5C98C193304200000021000E8995E7CF1C5E4BAEBAE4B468D1A954C9B36ADD1F15DBB76255BB66C49D6AD5B57FFD8EBAFBF9E3CF7DC73C9071F7C2000010010800040F777FBEDB7A761C69FFEF4A7E4A1871E4A7B7DF4EFDF3FF99BBFF99B340CF8D4A73E951C7AE8A1C9D7BFFEF564CA9429C9F6EDDB93D5AB5727575E796572F5D557A7618800040040000200DDCE3BEFBC53BFBD69D3A664F4E8D169D01121C7BDF7DE9BBCF2CA2BC9D6AD5BD3E31178BCFAEAABE910986F7FFBDBE97923478E4C9FF7F8E38F27E79F7F7EBA2D00010010800040B73167CE9C64DCB871E9F6C48913934F7FFAD3C9A5975E9AAC5DBB367D6CF6ECD9CD3E3FC28E383F7A888C1F3F3E79EBADB77AD5FB23000100042000D0C3C550970B2FBC309DD323428C18EA12BD3D32D1EB2386C0AC58B1A2C56BC5F38E3AEAA8B437C8EEDDBBD3C7F27B96084000000420005076117AC444A7319969841F5FFEF297EB87B964E6CE9D9B0620175F7C71F2FEFBEFB778CD3FFEF18FC957BFFAD5340489C952E379F993A50A4000000420005076B1B46D0C7B899E1F0DC38F10C7060E1C9886203367CE6CF17A8B162D4AE708899E20311CE6A9A79E4A2746EDC9ABC308400000010800F470317F47CCF9913FEC25F3E1871F2643860C49EEBCF3CEE4873FFC61525D5D9D0626C5BCFCF2CBC9D4A953D3EDB85ECC0912D78FD5619E7FFE7901080080000400CA2B86A544AF8C58ED2586BF342596B68D9E1F59AF8E0843BEFFFDEF271F7DF451A37363E8CBF0E1C3D339453263C78E4D87C2AC5CB93259BE7CB900040040000200E5535B5B9B2E551BA1452C61BB7EFDFA26CFFBF9CF7F9E545454243B76EC48F763384B0422F3E7CF2F382F7A8A4430927F6E88DE1F71FDA686D6084000005A084062423500A0ED62D9DBFBEEBB2F79E8A18792D34E3BADE879D75C734D72C51557D4EF47CF8F786CD8B0610541C7C30F3F9C0623975D7659A36B7CFBDBDF4EEEBDF7DE1EFB5EEDDEBD7BBB00040028BB2F7CE10B3B7BF2186200E80E6EBFFDF674A84A841353A64C69F29C58BEB6B2B232F9D9CF7E96FCFEF7BF4FE6CD9B973E1EBD45AAAAAA92499326A5FB31B4257A7E4400D2D4B566CC98917CFDEB5F4F87DCC4764FF3E28B2FBE906B82ACD10A0300CAAA7FFFFE779D7AEAA9BB355D01A0ED66CD9A95CEE971F8E1873739F969F8CD6F7E93861A2FBEF86272D145172523468C48DE7DF7DDF4588422716CE1C285E9BC1F31396AEC2F59B2A4D1755E7DF5D5E4D0430F4D97DC6DAA874837B7FBB39FFDECDC5C1364BC561800506E9FCA5979E289276E7DEEB9E7B6EFDE2D0B0180B68A555A8ACDCF11C356CE3BEFBCB4B748F4F08812435DC2FBEFBF9F5C7CF1C569E811E7FCF8C73F4E8FEFDCB9B3D17522688979406208EB8001037A46EAB17BF7F6E8F9F171F8F154AE7C52130C00E892102457AECD9557FBFDCF985C45519466CBDFFEEDDFA6376ADE0B45F99F72F0C107A737FAFD9A995C3C7A7D0C1C3830FDECC4D09518F212FBDBB66D4B8F47CF9138F6ECB3CF26D75F7F7D72F9E59717BD56F67D7AD8E73086BDDC2AFC0000007A8CDC4DD79CB8F13AF9E493FFCABB01FDFA0D1C3870502C815BAC0748ACDE129F9928575E7965BA5CEE1FFEF087B407C79D77DE597F5E0C8F89DE20F1F8030F3CD064F891DF03A4BABAFA43EF3E0000402738E79C73FE357713B73B6EE42A2A2ABEEE1D8134145CBB71E3C6778ACD01F2C4134FA4E1C7A04183D23024133D4162A8CBEBAFBF5EFFD8CA952BD3735F78E185260390FC39402EBAE8A2B7BCFB0000009DE09C73CE999DFD253B579EF78EC0FF7C2E162E5CF85CB155606EBDF5D6F433337FFEFC82C76312D498F4F4861B6E2898503542915835A629D92A302FBDF4523276ECD857BCFB0000001D2C7703F7FF66BD3FB292BBF13BCD3B83CFC6B997DE72CB2DF31E7AE8A1E4B4D34E2B082C62B84B4C6C3A71E2C426038DAC77C8B265CBD2FD71E3C625575C7145D1F93F2264890955A3D7C81D77DCF17FBCFB0000001D7F9337273FFCC87A81980B84BEAEB2B2F29851A346FD70E7CE9DBB637E8EF5EBD7170C59C91D4BDE7EFBED26038D0848C68C19935C7AE9A5698F905802378294A6C4F099B8FEC7F38CACCD159F3D0000808ED454EF8FAC5454547CD33B04E9D22CF78E1E3D3A0D33F287B434352F48BEE79F7F3EFD2CFDF0873F4CBF2E5DBAB4C9F3C68E1D9B8C1C3932DB1DE61D070000E860B99BB25F35157E7C5C167B87200D400EDEB871E37B9FFEF4A7EB438FC58B1727A5F8FEF7BF9F7E9E2A2B2BD39E200DC5F5629599E80572DD75D7BD7DFEF9E7FFA3771C0000A00335D7FBC35C20F017B9CFC1D8499326CD8CF93EFAF7EF9F0E5589212EA5A8ADAD4D273FBDEAAAAB9A5CFAF6A8A38E4AC68F1F9FCC9B372FE614F9ADA1670000001DACC8DC1F8DE602F14ED1D79D75D65987E73E0B9B7EF7BBDF2DBCF8E28B932F7FF9CBD97C1D25B9FDF6DB939FFEF4A78DC28FAF7EF5ABC9B061C3D2DE1F83060D7A27E61CF16E0300009441841EDE0568F2B3F1ED8A8A8ABA75EBD62D8F10247A82B4340748E6CD37DF4C56AC585130EC257A7E44F8B173E7CE984F64515555D510EF32000040F96EF2042050FCF331F8DA6BAF3D32499239311C26E6EE888951A3074729E2BC98F0349E77FDF5D7670F8FB7EA0B000040F96FF004205082CD9B375FB46EDDBA1DB13ACC273FF9C9E4B4D34E4BA64C9992F6EEC886C7C43097582E77C68C19E990B2083EB2212FBFFCE52FFF78CB2DB7DCEC9D040000E802021028F9B3F21F555555BF7DF4D147FF7BDBB66DBB22FCF8E637BF991C7EF8E169D0913B25F9D4A73E951C7AE8A1E95C1FF7DE7B6F1A7C2C5DBAF4ED0B2EB8E0F58A8A8A45E6FC000000E8BA9B3A010894A8A2A2A23AF79959982B1B5E7EF9E54B932499922BAFBEFFFEFB9BA30748EE6BB26EDDBA4DBB77EF7E29B77BC7FAF5EBAB2A2B2B7F134369ACF6020000D0850420D07A151515FD070F1EFCC9BCCFD1FABC9594D65455551DEC5D020000E8460420D031CE3AEBAC3D739FA70FBC13000000DD9000043AC6A9A79EBA77EEF3F49E77020000A01B128040C7183060C0BEB9CFD3DBDE09E0FFB677F721529C070087051B24151212B021044985908A15236DAD2541B1A5C1081113CCDE3877172B56A215120818524A4B42F347690BF923AD2D1192DA72D8842B1C46FA95BDF3EBB05A93CAB531289528F6F4E2472591B45549CB74DEC195BDF3F6DC98313BCE3D0FBCF8DECEDEEE66B837303F6666010028200104F2D1D1D17153BA9ECEDA13000000052480403E2A95CAADE97A3A634F00000014900002F978F4D1473F93AEA793F604000040010920908FCECECEDBD3F534644F00000014900002B9ADA5A9E9F8873D01000050CC833601047250A954A6A5EBE9B03D01000050400208E4A352A9DC95AEA743F604000040010920908F388EA7A7EBE9803D01000050400208E4238AA299E97A7ACB9E00000028200104F21145D13DE97A1AB0270000000A4800817CB4B7B77F315D4F6FDA13000000052480406E6B696E3AF6D813000000C53C681340209FB5746F3A76D913000000C53C68134020076D6D6DF3E338DE614F00000014900002F988E3F86BE97AEAB3270000000A4800817CC4717C7FBA9E5EB7270000000A4800817C4451B4285D4FBFB3270000000A4800817CC471BC388AA2D7EC09000080021240201F711C3F1445518F3D01000050400208E4B6961E4947B73D01000050CC83360104721045D1B2388E7F6D4F00000014900002F988A2A8238EE32E7B020000A0800410C8471CC7DF48D7D3467B020000A0800410C86D2D7D331D2FD913000000C53C681340209FB5F4583A5EB4270000008A79D02680400EE2385E1B45D17A7B020000A08004902B4B92E453870F1FEEDEB367CFFFFAFAFA926AB56A7CC2A3B7B737D9B973E73FD3D159E000F27814452F583100000002C87529C48FFEFEFEE4D4A953C9850B178C168DD3A74F273B76EC38DBDBDBFB4841D7D293E978DE8A0100001040AE4BE1CC0FF1A33011E442B55A3D50C4BF93288A9E8AE3F847560C00008000725D0A97BD880FC519D56AF5C38206906FC771FC032B0600004000B92E857B50080F850A204941D7D277D3F19C150300002080943A80FCEBFD7793C36FFC2279BBF7FBD908F3F09868316E02C8B3E978C68A01000010404A1B403E78EF78B2FF8FDF4BFEFADBA7868DF058D8265C8C8B00F25C380BC48A01000010404A1B4006DF7EEDB2F8511BC7DEDE225C8C830012EEFF11EE0362C50000000820A50D2007B6FDB0610009DB848B717106C88FD3B1CE8A01000010404A1B40F6579F6D1840C236E1625C0490E7D3F1A415030000208008204669034814452FC471FCB8150300002080943680846F7D691440C236E1625C9C01F2B3288ABE65C50000000820A50D208776FDB4610009DB848B7111405E4CC763560C0000800052DA0072E6F8DF92FDAF3F73F9E52FE963619B70312E02C84BE95869C50000000820A50D20611C79F357970590F09868316EBE06F79751142DB7620000000490F20690F3E793437FFAF9E597BFA48F856DC2C5B808205D511475583100000002482903C807EF1D4FFEBEEB270DEF0112B685E78817A5BF04E6957444560C0000800052AE0072FE7CF2EEA1ADC95B7FF84EC3F8511BE139E1B9CE0629F5D7E0FE268EE3A5560C00008000529A0072A5B33E9C0D322E03484F1CC70F5931000000024869024833677D8C7536889051CA4B60B64451F4A01503000020809426805C6DFCA80D21A39401E4F7711C3F60C50000000820A50920860032CA5A7A3D8EE3FBAD1800000001440031CAFC35B85BDBDADABE6AC50000000820028851E6334076A6639E1503000020800820469903C8AE74DC6BC50000000820028851E600F2E7F6F6F62F5B3100000002880062943980FC258AA22F5831000000028800629439800C4451748F1503000020800820469903C8FE388E3F6FC50000000820028851E6AFC13D58A9543E67C50000000820028851E633400E552A95BBAC1800000001440031CA1C408EA4E3B3560C00008000228018650E2083E9986AC50000000820028851E60032D4D9D979BB1503000020800820469903C8A94AA532C58A01000010400410E39A0790F46FFA4B5114F5B4602D9DA9542AB75A31000000028800F21146FAD697C6A449939259B366257BF7EEBD26EFF151B715318084F0918E2DE9F8B0157FD7E97B9EEDE8E8B8C98A01000010400490AB8C13DBB66DCBE62182B4E2FD8B1C4046868FDA68C15AFAF7E2C58B3F6DC5000000082002C8C70810617EC30D370CDB367FFEFC64CE9C39D96367CE9C4956AF5E9DDC78E38DD919236BD6AC49CE9E3D9B6CDAB4297B6E676767F6BCE5CB97673F7775750D7B8F83070F26B367CFCE7E7FE5CA95C3B69D3E7D3AFBBDF0BA61FBD2A54B9363C78EB534804451F49570A9CBC8F0D1C200727ED1A24593AC18000000014400F9986780844051BFADAFAF2F191A1ACA1E0BF1233C168247777777360F11246C0FF33BEFBC337BDEB469D3B29F07070787BDC782050BB2794F4F4FB279F3E661DB56AC5891CDF7EDDB970C0C0C64F3850B17B62C808C76C6470102C87F2B95CA442B060000A0A001C4B8F268F53D40264E9C98CC983123E9EFEF1FB6EDDCB973979E1FCECEA83D16C6848BF70E09DB66CE9C99FD5C0B29E1B5464696D17E7FE4B60923EE4BD2C20052C8E1FF280000005CB78A7209CC95B6854B5346068CC9932767DBD6AE5D9BFDBC64C992ECDF70B6C8D50490FAE0D2EA7B80B4B5B57D7DD9B265BBC508000000184701A476994AFD2530217C846DB59FC33D44C2BFAFBEFAEA65AF132E699970F11298DAF36BDBDADBDBB3F9860D1BB2CB60C27CDEBC7985B8096A1CC70F2C5BB6AC5F0001000080711040C24D5057AD5A959DAD1146881FE126A8615BF8375C4633E1E2E534E1A6A6235FE79D77DEC9BE65264492F03AF5DB4E9C38910596DA0D56432C397AF468A1BE06F7E21921FD02080000005C4701C4F86801A4E6E21921BBFDE5020000800052DA000200000008200208000000208008200000002080E47E13D3FAF98E1D3B92F9F3E737F51AD7F226AB57F3BC919FBD99D7BB9AFF3E0104000000AEC3007235B1A18801E46A3EBB0002000000050D20E14C87193366645F177BDB6DB7251B376ECC1E3F78F060327BF6ECECAB6257AE5C39ECE07EAC6DB579F87AD9DA7CC228674884AFB95DBD7AF5A5AFA25DB366CDA5AFB9AD3D6FDDBA75C9E4C993932953A6241B366C18F3F3368A0F637DD6F0F5B9CB972FCFDE3F6C5FBA746972ECD8B1513F7B33EF5B3FDFBC79733273E6CCECF9E1B5C3D924E1AB7905100000006841000907F57BF7EE4D060707B383F79B6FBE397B7CC18205D9CF3D3D3DD9C17CFDC1FD58DB268C714948FDCF217E84F9A64D9B92EEEEEE6C1E2248FDF3C2EBEFDFBF3F9BDF72CB2D637EDE460164ACCFBA62C58A6CBE6FDFBE646060209B2F5CB870D4D76BE67DEBE7617B981F397224FBBD309F33678E0002000000AD0820E100FDE9A79FCE0ECEEB0FE06B67419C3B772E1BCD6E6B36808CF61AE1B146BF3771E2C4313F6FA300D2CC7F47FD68F4199A79DFFAF97DF7DD97CDC319208B162D4AD6AF5F9F9D712280000000400B02C8F4E9D3B303F52D5BB67CA201245C1632F235C2E52E57FABD469FF7E30490F0F895EEE7D1CCFBD6CFC36B867DFDC4134F2453A74ECD1EBFFBEEBB0510000000684500A94580701948FD017CB81464C2C54B476A97A834B3AD7E1E824698877B7B8C8C0FB5CB4FEA2F8159BB766DD3678E8CFCBC8D02C8589FB5BDBD3D9B87FB8BD45E6FDEBC79A37EF666DEB77E3E77EEDC6CBE7BF7EE646868289BDF71C71D0208000000B42280844B33C2C1FDC81B84861B76CE9A352BBB8463D5AA554D6FAB9FBFFCF2CBD9BD3BC2E52B5D5D5D97DD0435FC6E78EF3042FC187913D4D10248A3CFDB28808CF5594F9C38918598DA8D58432C397AF4E8A89FBD99F7AD9F87D779F8E187B3D70EEF1D2E9D0937521540000000A00501C468ED104000000040001140000000000144000100000004100104000000041043000100000001C41040000000400031041000000010400C0104000000041001040000001040041000000040001140000000000144000100000001C41040000000400031041000000010400C010400000004104300010000000144000100000004100104000000104004100000004000114000000040003104100000002885BEBE3EE1A138E35CB55AFDD05F25000000E46CE7CE9DEF9E3A754A7C28C0387EFCF82BD56AF580BF4A000000C8D9D6AD5B976CDFBEFDFD93274FFE478468DD991F217EF4F6F61E4DC723FE2A010000E01A480FBA1FAC56AB6F84CB2FC23D288C4F7C84FD7E40FC000000000000000000000000000000000000000000000000000000000000000000000000000000C6A5FF03B588C93EF972C05D0000000049454E44AE426082, 1);

-- ----------------------------
-- Table structure for ACT_GE_PROPERTY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;
CREATE TABLE `ACT_GE_PROPERTY`  (
  `NAME_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`NAME_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_GE_PROPERTY
-- ----------------------------
INSERT INTO `ACT_GE_PROPERTY` VALUES ('cfg.execution-related-entities-count', 'true', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('cfg.task-related-entities-count', 'true', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('common.schema.version', '6.4.0.0', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('identitylink.schema.version', '6.4.0.0', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('job.schema.version', '6.4.0.0', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('next.dbid', '1', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('schema.history', 'create(6.3.2.0)', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('schema.version', '6.4.0.0', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('task.schema.version', '6.4.0.0', 1);
INSERT INTO `ACT_GE_PROPERTY` VALUES ('variable.schema.version', '6.4.0.0', 1);

-- ----------------------------
-- Table structure for ACT_HI_ACTINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_ACTINST`;
CREATE TABLE `ACT_HI_ACTINST`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT 1,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACT_NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACT_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) NULL DEFAULT NULL,
  `DURATION_` bigint(20) NULL DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_START`(`START_TIME_`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_END`(`END_TIME_`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_PROCINST`(`PROC_INST_ID_`, `ACT_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_ACT_INST_EXEC`(`EXECUTION_ID_`, `ACT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_ATTACHMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;
CREATE TABLE `ACT_HI_ATTACHMENT`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `URL_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CONTENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TIME_` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_COMMENT`;
CREATE TABLE `ACT_HI_COMMENT`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACTION_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `MESSAGE_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `FULL_MSG_` longblob NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_DETAIL`;
CREATE TABLE `ACT_HI_DETAIL`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DOUBLE_` double NULL DEFAULT NULL,
  `LONG_` bigint(20) NULL DEFAULT NULL,
  `TEXT_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TEXT2_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_PROC_INST`(`PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_ACT_INST`(`ACT_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_TIME`(`TIME_`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_NAME`(`NAME_`) USING BTREE,
  INDEX `ACT_IDX_HI_DETAIL_TASK_ID`(`TASK_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_IDENTITYLINK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;
CREATE TABLE `ACT_HI_IDENTITYLINK`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_USER`(`USER_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_TASK`(`TASK_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_IDENT_LNK_PROCINST`(`PROC_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_PROCINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_PROCINST`;
CREATE TABLE `ACT_HI_PROCINST`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT 1,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) NULL DEFAULT NULL,
  `DURATION_` bigint(20) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `START_ACT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `END_ACT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE INDEX `PROC_INST_ID_`(`PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_PRO_INST_END`(`END_TIME_`) USING BTREE,
  INDEX `ACT_IDX_HI_PRO_I_BUSKEY`(`BUSINESS_KEY_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_TASKINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_TASKINST`;
CREATE TABLE `ACT_HI_TASKINST`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT 1,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ASSIGNEE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) NULL DEFAULT NULL,
  `END_TIME_` datetime(3) NULL DEFAULT NULL,
  `DURATION_` bigint(20) NULL DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PRIORITY_` int(11) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) NULL DEFAULT NULL,
  `FORM_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `LAST_UPDATED_TIME_` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_TASK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_TASK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_TASK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_TASK_INST_PROCINST`(`PROC_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_HI_VARINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_VARINST`;
CREATE TABLE `ACT_HI_VARINST`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT 1,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DOUBLE_` double NULL DEFAULT NULL,
  `LONG_` bigint(20) NULL DEFAULT NULL,
  `TEXT_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TEXT2_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NULL DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_NAME_TYPE`(`NAME_`, `VAR_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_VAR_SCOPE_ID_TYPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_VAR_SUB_ID_TYPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_PROC_INST`(`PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_TASK_ID`(`TASK_ID_`) USING BTREE,
  INDEX `ACT_IDX_HI_PROCVAR_EXE`(`EXECUTION_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_BYTEARRAY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_BYTEARRAY`;
CREATE TABLE `ACT_ID_BYTEARRAY`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `BYTES_` longblob NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_GROUP`;
CREATE TABLE `ACT_ID_GROUP`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_INFO
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_INFO`;
CREATE TABLE `ACT_ID_INFO`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `USER_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PASSWORD_` longblob NULL,
  `PARENT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_MEMBERSHIP
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;
CREATE TABLE `ACT_ID_MEMBERSHIP`  (
  `USER_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`, `GROUP_ID_`) USING BTREE,
  INDEX `ACT_FK_MEMB_GROUP`(`GROUP_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_PRIV
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_PRIV`;
CREATE TABLE `ACT_ID_PRIV`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE INDEX `ACT_UNIQ_PRIV_NAME`(`NAME_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ID_PRIV
-- ----------------------------
INSERT INTO `ACT_ID_PRIV` VALUES ('dd4680ed-c278-11e9-973f-2e15a8856301', 'access-admin');
INSERT INTO `ACT_ID_PRIV` VALUES ('dd434c9b-c278-11e9-973f-2e15a8856301', 'access-idm');
INSERT INTO `ACT_ID_PRIV` VALUES ('dd4918ff-c278-11e9-973f-2e15a8856301', 'access-modeler');
INSERT INTO `ACT_ID_PRIV` VALUES ('dd4fa8b3-c278-11e9-973f-2e15a8856301', 'access-rest-api');
INSERT INTO `ACT_ID_PRIV` VALUES ('dd4c7461-c278-11e9-973f-2e15a8856301', 'access-task');

-- ----------------------------
-- Table structure for ACT_ID_PRIV_MAPPING
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_PRIV_MAPPING`;
CREATE TABLE `ACT_ID_PRIV_MAPPING`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PRIV_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `GROUP_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_FK_PRIV_MAPPING`(`PRIV_ID_`) USING BTREE,
  INDEX `ACT_IDX_PRIV_USER`(`USER_ID_`) USING BTREE,
  INDEX `ACT_IDX_PRIV_GROUP`(`GROUP_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_PRIV_MAPPING` FOREIGN KEY (`PRIV_ID_`) REFERENCES `ACT_ID_PRIV` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ID_PRIV_MAPPING
-- ----------------------------
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('dd45486c-c278-11e9-973f-2e15a8856301', 'dd434c9b-c278-11e9-973f-2e15a8856301', 'admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('dd482e9e-c278-11e9-973f-2e15a8856301', 'dd4680ed-c278-11e9-973f-2e15a8856301', 'admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('dd4b8a00-c278-11e9-973f-2e15a8856301', 'dd4918ff-c278-11e9-973f-2e15a8856301', 'admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('dd4dfb02-c278-11e9-973f-2e15a8856301', 'dd4c7461-c278-11e9-973f-2e15a8856301', 'admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('dd51a484-c278-11e9-973f-2e15a8856301', 'dd4fa8b3-c278-11e9-973f-2e15a8856301', 'admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('f059c687-c278-11e9-8c70-2e15a8856301', 'dd4fa8b3-c278-11e9-973f-2e15a8856301', 'rest-admin', NULL);
INSERT INTO `ACT_ID_PRIV_MAPPING` VALUES ('f05b7438-c278-11e9-8c70-2e15a8856301', 'dd4680ed-c278-11e9-973f-2e15a8856301', 'rest-admin', NULL);

-- ----------------------------
-- Table structure for ACT_ID_PROPERTY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_PROPERTY`;
CREATE TABLE `ACT_ID_PROPERTY`  (
  `NAME_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`NAME_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ID_PROPERTY
-- ----------------------------
INSERT INTO `ACT_ID_PROPERTY` VALUES ('schema.version', '6.4.0.0', 1);

-- ----------------------------
-- Table structure for ACT_ID_TOKEN
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_TOKEN`;
CREATE TABLE `ACT_ID_TOKEN`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TOKEN_VALUE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TOKEN_DATE_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `IP_ADDRESS_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `USER_AGENT_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TOKEN_DATA_` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_ID_USER
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_USER`;
CREATE TABLE `ACT_ID_USER`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `FIRST_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `LAST_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DISPLAY_NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EMAIL_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PWD_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PICTURE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_ID_USER
-- ----------------------------
INSERT INTO `ACT_ID_USER` VALUES ('admin', 1, 'Test', 'Administrator', NULL, 'admin@flowable.org', 'test', NULL, NULL);
INSERT INTO `ACT_ID_USER` VALUES ('rest-admin', 1, 'Rest', 'Admin', NULL, NULL, 'test', NULL, NULL);

-- ----------------------------
-- Table structure for ACT_PROCDEF_INFO
-- ----------------------------
DROP TABLE IF EXISTS `ACT_PROCDEF_INFO`;
CREATE TABLE `ACT_PROCDEF_INFO`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE INDEX `ACT_UNIQ_INFO_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
  INDEX `ACT_IDX_INFO_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
  INDEX `ACT_FK_INFO_JSON_BA`(`INFO_JSON_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RE_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;
CREATE TABLE `ACT_RE_DEPLOYMENT`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_RE_DEPLOYMENT
-- ----------------------------
INSERT INTO `ACT_RE_DEPLOYMENT` VALUES ('f06203e9-c278-11e9-8c70-2e15a8856301', 'Demo processes', NULL, NULL, '', '2019-08-19 20:00:29.633', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ACT_RE_MODEL
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_MODEL`;
CREATE TABLE `ACT_RE_MODEL`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) NULL DEFAULT NULL,
  `META_INFO_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_FK_MODEL_SOURCE`(`EDITOR_SOURCE_VALUE_ID_`) USING BTREE,
  INDEX `ACT_FK_MODEL_SOURCE_EXTRA`(`EDITOR_SOURCE_EXTRA_VALUE_ID_`) USING BTREE,
  INDEX `ACT_FK_MODEL_DEPLOYMENT`(`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RE_PROCDEF
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;
CREATE TABLE `ACT_RE_PROCDEF`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) NULL DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) NULL DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `ENGINE_VERSION_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DERIVED_VERSION_` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE INDEX `ACT_UNIQ_PROCDEF`(`KEY_`, `VERSION_`, `DERIVED_VERSION_`, `TENANT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ACT_RE_PROCDEF
-- ----------------------------
INSERT INTO `ACT_RE_PROCDEF` VALUES ('createTimersProcess:1:f102d7d9-c278-11e9-8c70-2e15a8856301', 1, 'Examples', 'Create timers process', 'createTimersProcess', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'createTimersProcess.bpmn20.xml', NULL, 'Test process to create a number of timers.', 0, 0, 1, '', NULL, NULL, NULL, 0);
INSERT INTO `ACT_RE_PROCDEF` VALUES ('escalationExample:1:f102d7d7-c278-11e9-8c70-2e15a8856301', 1, 'Examples', 'Helpdesk process', 'escalationExample', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'Helpdesk.bpmn20.xml', 'Helpdesk.png', NULL, 0, 0, 1, '', NULL, NULL, NULL, 0);
INSERT INTO `ACT_RE_PROCDEF` VALUES ('fixSystemFailure:1:f102d7d5-c278-11e9-8c70-2e15a8856301', 1, 'Examples', 'Fix system failure', 'fixSystemFailure', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'FixSystemFailureProcess.bpmn20.xml', 'FixSystemFailureProcess.png', NULL, 0, 0, 1, '', NULL, NULL, NULL, 0);
INSERT INTO `ACT_RE_PROCDEF` VALUES ('oneTaskProcess:1:f102d7d8-c278-11e9-8c70-2e15a8856301', 1, 'Examples', 'Famous One Task Process', 'oneTaskProcess', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'oneTaskProcess.bpmn20.xml', NULL, NULL, 0, 0, 1, '', NULL, NULL, NULL, 0);
INSERT INTO `ACT_RE_PROCDEF` VALUES ('reviewSaledLead:1:f102d7d6-c278-11e9-8c70-2e15a8856301', 1, 'Examples', 'Review sales lead', 'reviewSaledLead', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'reviewSalesLead.bpmn20.xml', 'reviewSalesLead.reviewSaledLead.png', NULL, 0, 1, 1, '', NULL, NULL, NULL, 0);
INSERT INTO `ACT_RE_PROCDEF` VALUES ('vacationRequest:1:f10289b4-c278-11e9-8c70-2e15a8856301', 1, 'http://activiti.org/bpmn20', 'Vacation request', 'vacationRequest', 1, 'f06203e9-c278-11e9-8c70-2e15a8856301', 'VacationRequest.bpmn20.xml', 'VacationRequest.png', NULL, 0, 0, 1, '', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for ACT_RU_DEADLETTER_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_DEADLETTER_JOB`;
CREATE TABLE `ACT_RU_DEADLETTER_JOB`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
  INDEX `ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
  INDEX `ACT_IDX_DJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_DJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_DJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_DEADLETTER_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
  INDEX `ACT_FK_DEADLETTER_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_EVENT_SUBSCR
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;
CREATE TABLE `ACT_RU_EVENT_SUBSCR`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CONFIGURATION_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_EVENT_SUBSCR_CONFIG_`(`CONFIGURATION_`) USING BTREE,
  INDEX `ACT_FK_EVENT_EXEC`(`EXECUTION_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_EXECUTION
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;
CREATE TABLE `ACT_RU_EXECUTION`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PARENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ACT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) NULL DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) NULL DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) NULL DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) NULL DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) NULL DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) NULL DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `START_ACT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `START_TIME_` datetime(3) NULL DEFAULT NULL,
  `START_USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) NULL DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) NULL DEFAULT NULL,
  `TASK_COUNT_` int(11) NULL DEFAULT NULL,
  `JOB_COUNT_` int(11) NULL DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) NULL DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) NULL DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) NULL DEFAULT NULL,
  `VAR_COUNT_` int(11) NULL DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) NULL DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_EXEC_BUSKEY`(`BUSINESS_KEY_`) USING BTREE,
  INDEX `ACT_IDC_EXEC_ROOT`(`ROOT_PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_FK_EXE_PROCINST`(`PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_FK_EXE_PARENT`(`PARENT_ID_`) USING BTREE,
  INDEX `ACT_FK_EXE_SUPER`(`SUPER_EXEC_`) USING BTREE,
  INDEX `ACT_FK_EXE_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_HISTORY_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_HISTORY_JOB`;
CREATE TABLE `ACT_RU_HISTORY_JOB`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RETRIES_` int(11) NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ADV_HANDLER_CFG_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_IDENTITYLINK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;
CREATE TABLE `ACT_RU_IDENTITYLINK`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `GROUP_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `USER_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_USER`(`USER_ID_`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_GROUP`(`GROUP_ID_`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_IDENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_ATHRZ_PROCEDEF`(`PROC_DEF_ID_`) USING BTREE,
  INDEX `ACT_FK_TSKASS_TASK`(`TASK_ID_`) USING BTREE,
  INDEX `ACT_FK_IDL_PROCINST`(`PROC_INST_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_JOB`;
CREATE TABLE `ACT_RU_JOB`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RETRIES_` int(11) NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
  INDEX `ACT_IDX_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
  INDEX `ACT_IDX_JOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_JOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_JOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
  INDEX `ACT_FK_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_SUSPENDED_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_SUSPENDED_JOB`;
CREATE TABLE `ACT_RU_SUSPENDED_JOB`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RETRIES_` int(11) NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
  INDEX `ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
  INDEX `ACT_IDX_SJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_SJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_SJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_SUSPENDED_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
  INDEX `ACT_FK_SUSPENDED_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_TASK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_TASK`;
CREATE TABLE `ACT_RU_TASK`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ASSIGNEE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DELEGATION_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PRIORITY_` int(11) NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) NULL DEFAULT NULL,
  `CATEGORY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `FORM_KEY_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) NULL DEFAULT NULL,
  `VAR_COUNT_` int(11) NULL DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) NULL DEFAULT NULL,
  `SUB_TASK_COUNT_` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_TASK_CREATE`(`CREATE_TIME_`) USING BTREE,
  INDEX `ACT_IDX_TASK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_TASK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_TASK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_TASK_EXE`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_TASK_PROCINST`(`PROC_INST_ID_`) USING BTREE,
  INDEX `ACT_FK_TASK_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_TIMER_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_TIMER_JOB`;
CREATE TABLE `ACT_RU_TIMER_JOB`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) NULL DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RETRIES_` int(11) NULL DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
  INDEX `ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
  INDEX `ACT_IDX_TJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_TJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_TJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_TIMER_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_TIMER_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
  INDEX `ACT_FK_TIMER_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `ACT_FK_TIMER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ACT_RU_VARIABLE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;
CREATE TABLE `ACT_RU_VARIABLE`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REV_` int(11) NULL DEFAULT NULL,
  `TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TASK_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DOUBLE_` double NULL DEFAULT NULL,
  `LONG_` bigint(20) NULL DEFAULT NULL,
  `TEXT_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TEXT2_` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  INDEX `ACT_IDX_RU_VAR_SCOPE_ID_TYPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_IDX_RU_VAR_SUB_ID_TYPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
  INDEX `ACT_FK_VAR_BYTEARRAY`(`BYTEARRAY_ID_`) USING BTREE,
  INDEX `ACT_IDX_VARIABLE_TASK_ID`(`TASK_ID_`) USING BTREE,
  INDEX `ACT_FK_VAR_EXE`(`EXECUTION_ID_`) USING BTREE,
  INDEX `ACT_FK_VAR_PROCINST`(`PROC_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_application
-- ----------------------------
DROP TABLE IF EXISTS `as_application`;
CREATE TABLE `as_application`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `application_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块图标',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：1、可用；0、不可用',
  `is_published` int(1) NULL DEFAULT NULL COMMENT '是否发布：1——发布，0——没发布',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `disable_time` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `remove_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_form_authority
-- ----------------------------
DROP TABLE IF EXISTS `as_form_authority`;
CREATE TABLE `as_form_authority`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proc_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `act_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `form_item_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authority` int(32) NOT NULL COMMENT '1不可见，2可见+不可编辑，3可见+可编辑，4必填',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_form_inst
-- ----------------------------
DROP TABLE IF EXISTS `as_form_inst`;
CREATE TABLE `as_form_inst`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `form_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `proc_inst_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `execution_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `task_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `executor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
  `execute_time` datetime(6) NULL DEFAULT NULL,
  `form_inst_sheet` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `form_inst_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单项中填的信息',
  `status` int(64) NULL DEFAULT 0 COMMENT ' 0;   //表示被创建，等待被执行\r\n 1;   //被执行了\r\n99;  //表单实例被丢弃（即没被执行了，可能是直接被回滚了）\r\n999;  //表单实例原来被正确执行了的，但是现在被回滚了',
  `node_type` int(64) NULL DEFAULT NULL COMMENT '用来标示当前任务节点类型：经办1、审批2、抄送3',
  `approve_result` int(64) NULL DEFAULT NULL COMMENT '1表示对当前审批节点同意，0表示不同意',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_form_model
-- ----------------------------
DROP TABLE IF EXISTS `as_form_model`;
CREATE TABLE `as_form_model`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `form_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_time` datetime(6) NOT NULL,
  `created_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_updated_time` datetime(6) NULL DEFAULT NULL,
  `last_updated_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  `model_sheet` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `group_id` int(11) NOT NULL COMMENT '该表单所属分组（负值代表不属于任一分组）',
  `icon_cls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单显示的vue图标',
  `proc_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定的流程模型ID',
  `scene_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前表单流程模型所属的工作场景',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单模型在哪个应用下，暂时先放着，不用',
  `is_binded` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '表单模型是否绑定流程模型，0否，1是',
  `is_bind_authority` int(1) NULL DEFAULT NULL COMMENT '表单项权限数据是否添加，0否，1是',
  `is_add_node_info` int(1) NULL DEFAULT NULL COMMENT '是否正确增加节点信息，0否，1是',
  `is_add_seq_condition` int(1) NULL DEFAULT NULL COMMENT '是否增加seqCondition，0否，1是',
  `is_deleted` int(1) NULL DEFAULT 0 COMMENT '是否被删除，0否，1是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `app_idFK`(`app_id`) USING BTREE,
  CONSTRAINT `as_form_model_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `as_application` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_group
-- ----------------------------
DROP TABLE IF EXISTS `as_group`;
CREATE TABLE `as_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `status` int(11) NULL DEFAULT NULL,
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_proc_inst
-- ----------------------------
DROP TABLE IF EXISTS `as_proc_inst`;
CREATE TABLE `as_proc_inst`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `proc_model_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `proc_def_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `proc_deploy_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commit_time` datetime(4) NOT NULL,
  `committer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `form_inst_all_value` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `status` int(64) NOT NULL COMMENT '流程实例状态 0：激活 1：被挂起 2：被删除 3：已完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_proc_model
-- ----------------------------
DROP TABLE IF EXISTS `as_proc_model`;
CREATE TABLE `as_proc_model`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `node_num` int(64) NULL DEFAULT NULL,
  `seq_condition` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '保存当前流程模型中sequenceFlow的条件语句',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_proc_node
-- ----------------------------
DROP TABLE IF EXISTS `as_proc_node`;
CREATE TABLE `as_proc_node`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `proc_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `node_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `node_type` int(64) NOT NULL COMMENT '1经办，2审批，3抄送',
  `candidate_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `candidate_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理组，这里应该设置成json数据，可以包含复杂数据，比如排除掉某个组里的某些人或者某个组里的某些小组（这个暂时不做，先留个接口在这里）',
  `limit_time` datetime(0) NULL DEFAULT NULL COMMENT '处理该节点事项的限时，目前暂时只做审批节点\r\n超过限时后，转交其他人处理或者直接流到下一节点\r\n\r\n',
  `overtime_strategy` int(64) NULL DEFAULT NULL COMMENT '节点如果超时之后的处理方式：\r\n0：交由系统管理员处理\r\n1：自动到下一节点\r\n',
  `sign_strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这是设置节点多人审批的策略，用json格式存储实现复杂规则',
  `todo_strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这是设置节点多人处理经办节点的策略，用json格式存储实现复杂规则',
  `if_joint_sign` int(64) NULL DEFAULT NULL COMMENT '判断是否是多人会签节点\r\n0：不是会签\r\n1：是会签，串行\r\n2：是会签，并行\r\n\r\n',
  `approve_type` int(64) NULL DEFAULT NULL COMMENT '在当前节点是审批节点的情况下，该属性表示在这个审批节点上点击“不同意”执行的策略：\r\n0表示——当前实例被结束（默认情况）\r\n1表示——回滚到上一个经办节点\r\n2表示——由审批人选择回滚到任意一个经办节点\r\n'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet
-- ----------------------------
DROP TABLE IF EXISTS `as_templet`;
CREATE TABLE `as_templet`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板Id',
  `templet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板的名称',
  `icon_cls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板的图标',
  `link_app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板下对应的应用Id',
  `link_form_model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板下对应的表单模型Id',
  `link_proc_model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板下对应的流程模型Id',
  `status` int(64) NOT NULL COMMENT '模板状态',
  `committer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布模板的人的Id',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '模板发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet_application
-- ----------------------------
DROP TABLE IF EXISTS `as_templet_application`;
CREATE TABLE `as_templet_application`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用模板Id',
  `application_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用模块名称',
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用模块图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet_de_model
-- ----------------------------
DROP TABLE IF EXISTS `as_templet_de_model`;
CREATE TABLE `as_templet_de_model`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程模型模板的Id',
  `name` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `model_key` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model_comment` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model_editor_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `thumbnail` longblob NULL,
  `model_type` int(11) NOT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `node_num` int(64) NOT NULL,
  `seq_condition` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板中所属的流程模型信息（除了flowable设计的内容之外还包含为了设计界面额外增加的字段——node_num和seq_condition）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet_form_authority
-- ----------------------------
DROP TABLE IF EXISTS `as_templet_form_authority`;
CREATE TABLE `as_templet_form_authority`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单项权限模板的Id',
  `templet_proc_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `act_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `form_item_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authority` int(32) NOT NULL COMMENT '1不可见，2可见+不可编辑，3可见+可编辑，4必填',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet_form_model
-- ----------------------------
DROP TABLE IF EXISTS `as_templet_form_model`;
CREATE TABLE `as_templet_form_model`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '表单模型模板Id',
  `form_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `model_sheet` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `icon_cls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单显示的vue图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for as_templet_proc_node
-- ----------------------------
DROP TABLE IF EXISTS `as_templet_proc_node`;
CREATE TABLE `as_templet_proc_node`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '流程节点模板Id',
  `templet_proc_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注意这里绑定的是流程模型资源的模板Id，资源导入之后还需要获取这个流程模型的Id',
  `node_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `node_type` int(64) NOT NULL COMMENT '1经办，2审批，3抄送',
  `candidate_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `candidate_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理组，这里应该设置成json数据，可以包含复杂数据，比如排除掉某个组里的某些人或者某个组里的某些小组（这个暂时不做，先留个接口在这里）',
  `limit_time` datetime(0) NULL DEFAULT NULL COMMENT '处理该节点事项的限时，目前暂时只做审批节点\r\n超过限时后，转交其他人处理或者直接流到下一节点\r\n\r\n',
  `overtime_strategy` int(64) NULL DEFAULT NULL COMMENT '节点如果超时之后的处理方式：\r\n0：交由系统管理员处理\r\n1：自动到下一节点\r\n',
  `sign_strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这是设置节点多人审批的策略，用json格式存储实现复杂规则',
  `todo_strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这是设置节点多人处理经办节点的策略，用json格式存储实现复杂规则',
  `if_joint_sign` int(64) NULL DEFAULT NULL COMMENT '判断是否是多人会签节点\r\n0：不是会签\r\n1：是会签，串行\r\n2：是会签，并行\r\n\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
