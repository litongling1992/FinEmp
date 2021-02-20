/*
Navicat MySQL Data Transfer

Source Server         : WJ
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : finemp

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-08-31 20:19:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_emp`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `type` varchar(20) NOT NULL,
  `computer` varchar(40) NOT NULL,
  `mouse` varchar(40) NOT NULL,
  `other` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1', '李同灵', '笔记本', '惠普', '小米', '内存条：8G');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `roles` varchar(50) NOT NULL,
  `regstertime` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `realname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123', 'admin', '2020-08-30 12:22:59', 'admin');
INSERT INTO `t_user` VALUES ('2', 'li', '123', 'visitor', '2020-08-30 12:24:46', 'Lee');
INSERT INTO `t_user` VALUES ('3', 'tt', '123', 'visitor', '2020-08-30 06:50:26', '李同灵');
