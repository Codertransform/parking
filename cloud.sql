/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-03-22 23:05:52
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
  `carType` char(2) NOT NULL COMMENT '车辆类型',
  `status` char(2) NOT NULL COMMENT '状态',
  `buy_time` datetime NOT NULL COMMENT '购买时间',
  `maintenance` datetime DEFAULT NULL COMMENT '上次保养时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆信息表';

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES ('5cbd1e936bfd48f783e28859cde904d6', '陕C·7797W', '斯巴鲁123', '灰色', '0', '0', '2020-02-07 21:14:11', '2020-02-07 15:22:43');
INSERT INTO `cars` VALUES ('d8774db7b1914a9daefb3ff85301ccc7', '陕C·V5591', '静安寺独立开发', '白色', '0', '0', '2020-02-07 15:22:41', '2020-02-07 15:22:43');

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
  `startmiles` varchar(100) NOT NULL COMMENT '起始里程',
  `endmiles` varchar(100) NOT NULL COMMENT '截止里程',
  `position` varchar(200) DEFAULT NULL,
  `status` char(2) NOT NULL COMMENT '车辆状态',
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
  `hour` int(11) DEFAULT NULL COMMENT '小时费率',
  `halfday` int(11) NOT NULL COMMENT '半天费率',
  `allday` int(11) NOT NULL COMMENT '全天费率',
  `week` int(11) NOT NULL COMMENT '周费率',
  `month` int(11) NOT NULL COMMENT '月费率',
  `halfyear` int(11) NOT NULL COMMENT '半年费率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆类型';

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('c33bd280f6b141f6800a05419faada5b', '手动挡2.0排量（含1.8）以内', '60', '100', '200', '900', '3500', '20000');

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
