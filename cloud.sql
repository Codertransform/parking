/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-02-09 00:08:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cars
-- ----------------------------
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (
  `id` varchar(64) NOT NULL,
  `cardId` varchar(10) NOT NULL COMMENT '车牌号',
  `model` varchar(20) NOT NULL COMMENT '型号',
  `color` varchar(20) NOT NULL COMMENT '颜色',
  `carType` char(1) NOT NULL COMMENT '车辆类型',
  `status` char(1) NOT NULL COMMENT '状态',
  `buy_time` datetime NOT NULL COMMENT '购买时间',
  `maintenance` datetime DEFAULT NULL COMMENT '上次保养时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆信息表';

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES ('c880639e9cf0422c8eee49d69bc314d6', '陕C·V5591', '静安寺独立开发', '白色', '0', '0', '2020-02-07 15:22:41', '2020-02-07 15:22:43');
INSERT INTO `cars` VALUES ('e817fa1e3cee4f8496c7c2b937aaa7eb', '房东是个很', '看见啊大号封了你', '啊的风格啊', '0', '0', '2020-01-17 09:31:02', '2020-01-17 09:29:27');
INSERT INTO `cars` VALUES ('ff02a2cb0a6e48d4adb2974c63bd75ad', '陕C·7797W', '斯巴鲁123', '灰色', '0', '0', '2020-02-07 15:22:41', '2020-02-07 15:22:43');
