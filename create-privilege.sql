/*
Navicat MySQL Data Transfer

Source Server         : LocalMysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : privilege

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-07 10:45:24
*/

drop DATABASE IF EXISTS `privilege`
CREATE DATABASE `privilege`;

USE `privilege`;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单资源名称',
  `url` varchar(300) NOT NULL DEFAULT '' COMMENT '菜单资源URL',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '菜单资源简要描述',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级id',
  `sort_no` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `create_person` varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人',
  `create_date` datetime NOT NULL COMMENT '记录生成时间',
  `update_person` varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限资源表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('2', '权限管理', '/controller/permission/list.do', '', '0', '2', 'system', '2017-07-07 10:15:35', 'system', '2017-07-07 10:15:35');
INSERT INTO `permission` VALUES ('3', '角色管理', '/controller/role/list.do', '', '0', '3', 'system', '2017-07-07 10:15:35', 'system', '2017-07-07 10:15:35');
INSERT INTO `permission` VALUES ('4', '帐户管理', '/controller/user/list.do', '', '0', '4', 'system', '2017-07-07 10:15:35', 'system', '2017-07-07 10:15:35');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '角色描述',
  `create_person` varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人',
  `create_date` datetime NOT NULL COMMENT '记录生成时间',
  `update_person` varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '物流总监', '物流总监', 'root', '2013-10-23 01:08:24', 'root', '2013-10-23 01:08:43');
INSERT INTO `role` VALUES ('2', '客服总监', '客服总监', 'root', '2013-10-23 01:08:32', 'root', '2013-10-23 01:08:38');
INSERT INTO `role` VALUES ('3', '运营总监', '运营总监', 'root', '2013-10-21 01:05:31', 'root', '2013-10-21 01:05:34');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '资源ID',
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_resource_id` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限资源关系表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('2', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '登录用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '登录密码',
  `fullname` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别：1男0女',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否管理员：1是0否',
  `is_lock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定：1是0否',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：1是0否',
  `create_person` varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人',
  `create_date` datetime NOT NULL COMMENT '记录生成时间',
  `update_person` varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人',
  `update_date` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', '63a9f0ea7bb98050796b649e85481845', '管理员', '1', '1', '1', '0', 'system', '2013-10-21 01:04:49', 'system', '2013-10-21 01:04:49');
INSERT INTO `user` VALUES ('2', 'cl', '63a9f0ea7bb98050796b649e85481845', '商务人员', '1', '0', '2', '0', 'root', '2013-10-23 01:09:30', 'root', '2013-10-23 01:09:30');
INSERT INTO `user` VALUES ('3', 'test', '63a9f0ea7bb98050796b649e85481845', '测试', '1', '0', '3', '1', 'root', '2013-10-21 01:06:08', 'root', '2013-10-21 01:06:08');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('2', '1');
