/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : login

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-07-07 12:07:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_gl`
-- ----------------------------
DROP TABLE IF EXISTS `t_gl`;
CREATE TABLE `t_gl` (
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gl
-- ----------------------------
INSERT INTO `t_gl` VALUES ('admin', '8');

-- ----------------------------
-- Table structure for `t_jieshu`
-- ----------------------------
DROP TABLE IF EXISTS `t_jieshu`;
CREATE TABLE `t_jieshu` (
  `xuehao` varchar(11) NOT NULL,
  `dep` varchar(20) NOT NULL,
  `let` varchar(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `jietime` datetime DEFAULT NULL,
  `huantime` datetime DEFAULT NULL,
  PRIMARY KEY (`xuehao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jieshu
-- ----------------------------
INSERT INTO `t_jieshu` VALUES ('1', '1', '1', '1', null, null);
INSERT INTO `t_jieshu` VALUES ('11', '11', '11', '11', null, null);
INSERT INTO `t_jieshu` VALUES ('34', '44', '4', '4', null, null);

-- ----------------------------
-- Table structure for `t_shu`
-- ----------------------------
DROP TABLE IF EXISTS `t_shu`;
CREATE TABLE `t_shu` (
  `name` varchar(20) NOT NULL,
  `bianhao` varchar(10) NOT NULL,
  `zuozhe` varchar(20) NOT NULL,
  `CBS` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shu
-- ----------------------------
INSERT INTO `t_shu` VALUES ('1', '1', '1', '1');
INSERT INTO `t_shu` VALUES ('2', '2', '2', '2');

-- ----------------------------
-- Table structure for `t_tj`
-- ----------------------------
DROP TABLE IF EXISTS `t_tj`;
CREATE TABLE `t_tj` (
  `name` int(11) NOT NULL,
  `bianhao` varchar(20) NOT NULL,
  `zuozhe` varchar(10) NOT NULL,
  `chubanshe` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tj
-- ----------------------------
INSERT INTO `t_tj` VALUES ('1111', '1111', '111', '111');

-- ----------------------------
-- Table structure for `t_tushu`
-- ----------------------------
DROP TABLE IF EXISTS `t_tushu`;
CREATE TABLE `t_tushu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `xinxi` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tushu
-- ----------------------------
INSERT INTO `t_tushu` VALUES ('12', '2', '22266');
INSERT INTO `t_tushu` VALUES ('13', '111', '1111');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sf` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', '0');
INSERT INTO `t_user` VALUES ('2', '2', '0');
INSERT INTO `t_user` VALUES ('45', '4', '0');
INSERT INTO `t_user` VALUES ('5', '22', '0');
INSERT INTO `t_user` VALUES ('8', '1', '0');
