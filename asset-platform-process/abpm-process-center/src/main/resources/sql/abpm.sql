/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 127.0.0.1:3306
 Source Schema         : fbpm1.0

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 28/02/2019 11:12:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for act_proc_re_identity
-- ----------------------------
DROP TABLE IF EXISTS `act_proc_re_identity`;
CREATE TABLE `act_proc_re_identity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `act_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `act_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `identity_type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '1、角色 2、用户组 3、组织 4、岗位 5、用户',
  `identity_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `identity_name` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `proc_def_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `proc_def_key` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_proc_re_node
-- ----------------------------
DROP TABLE IF EXISTS `act_proc_re_node`;
CREATE TABLE `act_proc_re_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proc_def_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义Id',
  `act_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '节点Id',
  `act_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '节点名称',
  `act_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '节点类型',
  `logic_child_id` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '逻辑子Id',
  `physics_child_id` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '物理子Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_multiinstance` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否多实例节点',
  `is_preempt_mode` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否抢占模式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_proc_ru_identity
-- ----------------------------
DROP TABLE IF EXISTS `act_proc_ru_identity`;
CREATE TABLE `act_proc_ru_identity` (
  `id` int(11) NOT NULL DEFAULT '0',
  `task_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `proc_def_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `proc_def_key` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `business_key` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `act_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `identity_type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `identity_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_proc_ru_status
-- ----------------------------
DROP TABLE IF EXISTS `act_proc_ru_status`;
CREATE TABLE `act_proc_ru_status` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `task_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '任务Id',
  `proc_inst_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例Id',
  `proc_def_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义Id',
  `act_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '节点Id',
  `proc_status` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '流程状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
