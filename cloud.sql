/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-03-23 18:43:45
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
  `carType` varchar(64) NOT NULL COMMENT '车辆类型',
  `status` char(2) NOT NULL COMMENT '状态',
  `buy_time` datetime NOT NULL COMMENT '购买时间',
  `maintenance` datetime DEFAULT NULL COMMENT '上次保养时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆信息表';

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES ('5cbd1e936bfd48f783e28859cde904d6', '陕C·7797W', '斯巴鲁123', '灰色', '05a4a584e5c745978306e47d913834bf', '0', '2020-02-07 21:14:11', '2020-02-07 15:22:43');
INSERT INTO `cars` VALUES ('d8774db7b1914a9daefb3ff85301ccc7', '陕C·V5591', '静安寺独立开发', '白色', '05a4a584e5c745978306e47d913834bf', '-1', '2020-02-07 15:22:41', '2020-02-07 15:22:43');

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of dict
-- ----------------------------

-- ----------------------------
-- Table structure for lease
-- ----------------------------
DROP TABLE IF EXISTS `lease`;
CREATE TABLE `lease` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) NOT NULL COMMENT '订单号',
  `carId` varchar(64) NOT NULL COMMENT '车辆id',
  `unit` varchar(50) NOT NULL COMMENT '单位名称',
  `type` varchar(64) NOT NULL COMMENT '租用方式',
  `amount` int(10) NOT NULL COMMENT '总金额',
  `startmiles` varchar(100) DEFAULT NULL COMMENT '起始里程',
  `endmiles` varchar(100) DEFAULT NULL COMMENT '截止里程',
  `position` varchar(200) DEFAULT NULL,
  `status` char(2) DEFAULT NULL COMMENT '车辆状态',
  `startdate` datetime NOT NULL COMMENT '开始时间',
  `enddate` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租车表';

-- ----------------------------
-- Records of lease
-- ----------------------------

-- ----------------------------
-- Table structure for maintain_order
-- ----------------------------
DROP TABLE IF EXISTS `maintain_order`;
CREATE TABLE `maintain_order` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) DEFAULT NULL,
  `carId` varchar(10) NOT NULL COMMENT '车牌号',
  `distance` double(10,0) NOT NULL COMMENT '行驶距离',
  `time` datetime NOT NULL COMMENT '保养时间',
  `manager` varchar(10) NOT NULL COMMENT '经办人 ',
  `status` char(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_order
-- ----------------------------
INSERT INTO `maintain_order` VALUES ('9647434e99d24fa1b9be01e896ea84d7', '2020022116092463549033', '陕C·V5591', '210000', '2020-02-21 15:44:22', '王振宇', '1');

-- ----------------------------
-- Table structure for maintain_result
-- ----------------------------
DROP TABLE IF EXISTS `maintain_result`;
CREATE TABLE `maintain_result` (
  `id` varchar(64) NOT NULL,
  `result` varchar(300) NOT NULL COMMENT '检测结果',
  `subject` varchar(50) NOT NULL COMMENT '保养项目',
  `car_img` varchar(150) NOT NULL COMMENT '车链照片',
  `people_img` varchar(150) NOT NULL COMMENT '人车合照',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_result
-- ----------------------------

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆类型';

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('05a4a584e5c745978306e47d913834bf', '手动挡2.0排量（含1.8）以内');

-- ----------------------------
-- Table structure for type_info
-- ----------------------------
DROP TABLE IF EXISTS `type_info`;
CREATE TABLE `type_info` (
  `id` varchar(64) NOT NULL,
  `type_id` varchar(64) NOT NULL COMMENT '类型id',
  `key` varchar(10) NOT NULL COMMENT '键名',
  `value` int(10) NOT NULL COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型明细';

-- ----------------------------
-- Records of type_info
-- ----------------------------
INSERT INTO `type_info` VALUES ('15b7730ca76449c79654d5eb2fd81004', '05a4a584e5c745978306e47d913834bf', 'month', '3500');
INSERT INTO `type_info` VALUES ('362bdb32996e4ab1bb6002f8268f3ca0', '05a4a584e5c745978306e47d913834bf', 'allday', '200');
INSERT INTO `type_info` VALUES ('4f194f5d7adb4ac98cab26bb6666b1c1', '05a4a584e5c745978306e47d913834bf', 'halfyear', '20000');
INSERT INTO `type_info` VALUES ('5540b0f50b494d17a76f74d2fef70bf7', 'c7954c25104448c387b4286ab9c38090', 'hour', '10');
INSERT INTO `type_info` VALUES ('5f88e673209342a6a6d886b84c87d4c8', '05a4a584e5c745978306e47d913834bf', 'halfday', '100');
INSERT INTO `type_info` VALUES ('672ac03ca3af4668a8dfb0ea823c9785', '05a4a584e5c745978306e47d913834bf', 'hour', '0');
INSERT INTO `type_info` VALUES ('95f26eb7b2a9464e8acd93f0cff0dc1d', 'c7954c25104448c387b4286ab9c38090', 'week', '900');
INSERT INTO `type_info` VALUES ('ab469c2edd32418a9d38f8aee997e948', 'c7954c25104448c387b4286ab9c38090', 'halfyear', '20000');
INSERT INTO `type_info` VALUES ('b37a7a855ff24eb7bf6f48276464655f', 'c7954c25104448c387b4286ab9c38090', 'halfday', '100');
INSERT INTO `type_info` VALUES ('cbe86dd6d8d2490993d7b0669dc84f4f', '05a4a584e5c745978306e47d913834bf', 'week', '900');
INSERT INTO `type_info` VALUES ('cdb0bb5e24cf43ad85101da98432d1ed', 'c7954c25104448c387b4286ab9c38090', 'allday', '200');
INSERT INTO `type_info` VALUES ('cee9a4299da34fc68eedc3ac64785c10', 'c7954c25104448c387b4286ab9c38090', 'month', '3500');

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) NOT NULL COMMENT '上级单位id',
  `name` varchar(200) NOT NULL COMMENT '单位名称',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请单位表';

-- ----------------------------
-- Records of unit
-- ----------------------------
