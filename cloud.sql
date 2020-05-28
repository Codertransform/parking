/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-05-27 18:56:57
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
INSERT INTO `cars` VALUES ('5cbd1e936bfd48f783e28859cde904d6', '陕C·7797W', '斯巴鲁', '灰色', '05a4a584e5c745978306e47d913834bf', '0', '2020-02-07 21:14:11', '2020-02-07 15:22:43');
INSERT INTO `cars` VALUES ('d8774db7b1914a9daefb3ff85301ccc7', '陕C·V5591', '静安寺独立开发', '白色', '05a4a584e5c745978306e47d913834bf', '0', '2020-02-07 15:22:41', '2020-02-07 15:22:43');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` varchar(64) NOT NULL,
  `device_id` varchar(15) NOT NULL COMMENT '设备编号',
  `car_id` varchar(64) DEFAULT NULL COMMENT '车辆id',
  `status` char(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('0f6ee3eda73a409eb7a7a223d272f079', '1715704370', '陕C·7797W', '1');

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
  `member_id` varchar(64) NOT NULL COMMENT '用户id',
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
INSERT INTO `lease` VALUES ('d490c0fd3f95431d8ad79e773edeae8b', 'YBTC2020051918025243474002', '5cbd1e936bfd48f783e28859cde904d6', '5f0952e82c0c4454890c90a513b47a90', 'b41d7348c9f74bd9a67fda4920a25e84', '05a4a584e5c745978306e47d913834bf', '200', null, null, null, '1', '2020-05-19 17:46:00', '2020-05-20 17:46:00');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养订单';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养结果';

-- ----------------------------
-- Records of maintain_result
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` varchar(64) NOT NULL,
  `username` varchar(10) NOT NULL COMMENT '姓名',
  `idCard` varchar(30) NOT NULL COMMENT '身份证号',
  `tel` varchar(20) NOT NULL COMMENT '电话',
  `unit_id` varchar(64) NOT NULL COMMENT '单位id',
  `openId` varchar(120) DEFAULT NULL COMMENT '微信openId',
  `status` char(2) NOT NULL COMMENT '状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('b41d7348c9f74bd9a67fda4920a25e84', '张三', '610302199209082056', '15769271840', '5f0952e82c0c4454890c90a513b47a90', 'ogFYn4z7DgLFGufrDdmHIzXSyArU', '-2', '');

-- ----------------------------
-- Table structure for member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `member_login_log`;
CREATE TABLE `member_login_log` (
  `id` varchar(64) NOT NULL,
  `member_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `open_id` varchar(64) NOT NULL COMMENT '微信openId',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_login_log
-- ----------------------------
INSERT INTO `member_login_log` VALUES ('3cb4a8b7e62d480c8791c367020851a7', 'b41d7348c9f74bd9a67fda4920a25e84', 'ogFYn4z7DgLFGufrDdmHIzXSyArU', '2020-05-19 17:42:27');

-- ----------------------------
-- Table structure for member_wxinfo
-- ----------------------------
DROP TABLE IF EXISTS `member_wxinfo`;
CREATE TABLE `member_wxinfo` (
  `id` varchar(64) NOT NULL,
  `member_id` varchar(64) DEFAULT NULL,
  `openId` varchar(64) NOT NULL,
  `session_key` varchar(120) NOT NULL,
  `nickName` varchar(20) NOT NULL,
  `gender` int(1) NOT NULL,
  `avatarUrl` varchar(350) NOT NULL,
  `country` varchar(10) NOT NULL,
  `province` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `language` varchar(10) DEFAULT NULL,
  `skey` varchar(64) NOT NULL COMMENT '自定义状态码',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_wxinfo
-- ----------------------------
INSERT INTO `member_wxinfo` VALUES ('5a7715dfdeb5473ebd70806ec350c292', 'b41d7348c9f74bd9a67fda4920a25e84', 'ogFYn4z7DgLFGufrDdmHIzXSyArU', 'Z0aTX7SUcP5Xl8LS/B0kPQ==', '王振宇', '1', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epyHSUjzhEH4k2comWaa166icjoWmrhjcIiamHkpIDRyyMBDSnXUac5sUUrFuTXSafDUNWHykzaQInw/132', 'China', 'Shaanxi', 'Baoji', 'zh_CN', 'a78567bcea644dcfbfb480bcc06bd6f7', '1');

-- ----------------------------
-- Table structure for origin_gps_data
-- ----------------------------
DROP TABLE IF EXISTS `origin_gps_data`;
CREATE TABLE `origin_gps_data` (
  `id` varchar(64) NOT NULL,
  `manu_name` char(2) NOT NULL COMMENT '制造商名称',
  `serial_number` varchar(10) NOT NULL COMMENT '车载机序列号',
  `data_type` char(2) NOT NULL COMMENT '数据类型',
  `time` varchar(10) NOT NULL COMMENT '时间',
  `valid` char(2) NOT NULL COMMENT '数据有效位',
  `latitude` varchar(10) NOT NULL COMMENT '纬度',
  `lat_flag` char(2) NOT NULL COMMENT '纬度标志',
  `longitude` varchar(10) NOT NULL COMMENT '经度',
  `lon_flag` char(2) NOT NULL COMMENT '经度标志',
  `speed` double(10,2) NOT NULL COMMENT '速度',
  `direction` varchar(255) NOT NULL COMMENT '方位角',
  `date` varchar(10) NOT NULL COMMENT '日期',
  `vehicle_status` varchar(10) NOT NULL COMMENT '车辆状态',
  `net_mcc` varchar(10) NOT NULL COMMENT '移动国家码',
  `net_mnc` varchar(10) NOT NULL COMMENT '移动网络码',
  `net_lac` varchar(10) NOT NULL COMMENT '基站区域码',
  `net_cellid` varchar(10) NOT NULL COMMENT '基站编码',
  `voltage` varchar(5) DEFAULT NULL COMMENT '电压',
  `GSM` varchar(5) DEFAULT NULL COMMENT 'GSM信号值',
  `satellites` varchar(5) DEFAULT NULL COMMENT 'GPS卫星数量',
  `voltage_unit` varchar(5) DEFAULT NULL COMMENT '电池电压单位',
  `iccid` varchar(20) DEFAULT NULL COMMENT 'iccid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of origin_gps_data
-- ----------------------------
INSERT INTO `origin_gps_data` VALUES ('00287f78eceb4633bfe495385556a05a', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('0066d81bd20c467e9472f3febdad3ea6', 'HQ', '1715704370', 'V1', '072701', 'A', '34.212228', 'N', '107.137586', 'E', '0.00', '88.00', '270520', 'FFFFFBFF', '460', '00', '37238', '28328', '12.60', '18', '11', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('00ffa8ca649d48fa83ba0cb9af5d4b73', 'HQ', '1715704370', 'V6', '070506', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('017456bfd6d44f2aa260f16b90f958b2', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('0391f87c88194c93a6e59bdd37cecb25', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.90', '30', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('03e9c7b47f8b4b82b58563c59dbb43c1', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '18', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('047bf2cea7db4f949ff211ca2d49676f', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '31', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('04d006186ce24f33aaf22e4b7fc42150', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('05d25abe3c324d518836ff11ea354485', 'HQ', '1715704370', 'V6', '070324', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('067cef2539cc473d8e189907c474e3f2', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('0758da88223d48ed8cc7dcc0cb6dbfd9', 'HQ', '1715704370', 'V6', '073912', 'A', '34.209198', 'N', '107.121381', 'E', '0.00', '178.00', '270520', 'FFFFFFFF', '460', '00', '37363', '26797', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('07eb405a9dff416f9e96b6f72e91a67e', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('0a47f773ed7a44748898a41111e3ba27', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('0c05576ecb954fb982f7edef2b2edee2', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('0cfdb39a7c9e45e3842ff05486778652', 'HQ', '1715704370', 'V6', '084434', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('0d40eced7c7f4b5bb9f3fa2413b5b024', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('0e161a69547a4bc19ee9378c294c92ec', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('0f2a6bae8956442e88ca66456878d7f4', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('0f6894e8ebfc4a70a1a99472a157d7ed', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('109f51729125460ca337df34d884aa1a', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('126b5b614f3d4903abb72fbf4f2d6f74', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('13f7c87db7fa463996b11234d60bdd51', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('14047604750f4d918edd9ea9c5757778', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('140c974cd6d94c678033efc351efb799', 'HQ', '1715704370', 'V1', '070328', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', '12.70', '25', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('142be67e11074052b24122c2ff2382d8', 'HQ', '1715704370', 'V6', '093356', 'A', '3421.1559', 'N', '10714.3690', 'E', '0.00', '0.00', '260520', 'FFFFFFFF', '460', '00', '37236', '4706', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('170f3a7eb04040e185184bf9a5f54868', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('177e34272bd14959b661b6bdb91e3825', 'HQ', '1715704370', 'V6', '025215', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('17b678b5fb084efda52b293f566ba82d', 'HQ', '1715704370', 'V6', '003753', 'V', '3421.2267', 'N', '10713.7426', 'E', '0.00', '0.00', '270520', 'FFFFFFFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('17d922970ca2469f98ec9e17cdad9b1f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('1c209583dc4e433fb7c330e60cc8a86e', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('1c388e6b2dd54138866d246452285b4d', 'HQ', '1715704370', 'V6', '062551', 'A', '34.212248', 'N', '107.137456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('1cf05ff3e22041119013bbaf41a28bc0', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('1df5c113d7f2477ea7b18d73f3f3e553', 'HQ', '1715704370', 'V1', '065014', 'A', '34.212248', 'N', '107.137456', 'E', '0.00', '0.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('1e311ff1345b42789490506a29c325b3', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('1ef92bba14a94dbd83cff1ea480e6ccc', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('21e68aad75fd41e6b0deba15d6b8ca35', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('22326acdcda4409ebc427b12f72798cb', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('229af77f028041fe9fc0532b69dfca01', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('244f95c6a64b46168dd59448e4e6c68c', 'HQ', '1715704370', 'V6', '072215', 'A', '34.212747', 'N', '107.137771', 'E', '15.00', '281.00', '270520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('249874579d334bdaa27fb197fe2f920f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('25ecad06a51443b7abb6a8450a71d12a', 'HQ', '1715704370', 'V6', '035221', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('286c63879b614af0a718b0219f8e0b4d', 'HQ', '1715704370', 'V6', '082126', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('29318965559e4505b66738dc9fe600a5', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '20', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('2b37b79cbb9f4731b9f68a47c39c1f4e', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('301ab53f532a4175b7acc0fd57d2717f', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('30bf58c9af9d4268866f59c7704200ea', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('3295c324405b4116a15ab718f31aae43', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('34615846e1ca4758a61b6f7a822ce195', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3479aef9b62a40fb8b343297fff7b843', 'HQ', '1715704370', 'V6', '020818', 'A', '3421.0936', 'N', '10716.5703', 'E', '21.00', '270.00', '270520', 'FFFFFFFF', '460', '00', '36919', '13854', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('36d0ff9cf42148c7808957e2006a6d3a', 'HQ', '1715704370', 'V6', '070651', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('37d1ec5f0ee24f99936ae9c710979c4f', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '18', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('38083d371d62497cb29fa1bf81ee73b4', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('3a1e788e69a24ace8fd689b4579d119c', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('3a277e99e3f1438da8fd92af170b5164', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('3b72e9846c984163be06291f3144e5d3', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3bd356fe974c4e7285495fd747c7c11d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3d6de22b382a4637804517e908f9100d', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('3f0ebb97c43c4905aa42c5d7963871fd', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3f1089d5e16a4d4ea9f0fc6c957f62a6', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('3fcf28e224634f6e9d16c62cc5093516', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('412029f6b7e545fd85422f3f12154f63', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('4132834331704b8c9a05609ced6a3210', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.90', '31', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('413539e43a8f4114acfd991d147c670d', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('4237d9ac225d4cd3817e9500c14674e4', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '20', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('432b25307f054b0b951b5877590b298b', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('438c7d84cab54671a195c1de92610e16', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('43cf67e1a8a34bb5899bcb5543bd6e1c', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('449ece493c4d4cfcb391f7011fee4607', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('44bcb9d3d9b44055a461b4ee6c550814', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('4543596c4ad84667a3b3b58cdac20e91', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('457a0469b29d45fa8a61fc99c0fb395f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('459093f4d7904cca888ea650c43cc8cd', 'HQ', '1715704370', 'V6', '005415', 'A', '3420.8557', 'N', '10714.0911', 'E', '0.00', '101.00', '270520', 'FFFFFFFF', '460', '00', '37236', '26827', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('45ab08b73fc4464c99adf975347091bd', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('45bd667c931442feaf6621567e6ea918', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('46aaefe9cac948f3addc6b03363d1766', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('4799ff9faadc43128d9e4dd11660ade0', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('48528f2a4e9542bf9422704406cd0637', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('4bf93ba7ac754cc59e3bdd032ad8c40b', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('4c25a294aec7448f9750f4fe8f0fd7d8', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('4c8617b13aad4dc4b3246e79c3f9ab94', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('4c88d407cc084e14b0bfd9af76991063', 'HQ', '1715704370', 'V6', '080357', 'A', '34.208695', 'N', '107.128868', 'E', '8.00', '142.00', '270520', 'FFFFFFFF', '460', '00', '37236', '48717', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('4ee1e610a99c4a48aad0af185671ce72', 'HQ', '1715704370', 'V6', '033610', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('4fd50cdca97a4418b14af19eedce92cf', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('508bec66612d4d36b7499e7a9671eeb1', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('51d9c8e8d1c94971b03c7e8b9764dfdb', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('51f0c4c4aed84a10bde73dec497fc2c6', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('5353aa6bc48a4e389542f3b6cea942aa', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('543534754faa4004893cf4ad8774daff', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('54568e27319544d2a8492b6f01e95bfa', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '20', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('55c35ac75a344b0bb4dc0f6e7a6eda00', 'HQ', '1715704370', 'V6', '071506', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('57d85fde34174d908a90d11eb14d6377', 'HQ', '1715704370', 'V6', '080541', 'A', '34.208807', 'N', '107.129868', 'E', '26.00', '101.00', '270520', 'FFFFFFFF', '460', '00', '37236', '48718', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('597de5fd03f648d0a2e8cf7f699b0e67', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('5a3aeafda1064edfba9fec7edab01964', 'HQ', '1715704370', 'V6', '020435', 'A', '3420.8008', 'N', '10718.6957', 'E', '50.00', '293.00', '270520', 'FFFFFFFF', '460', '00', '36919', '25181', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('5b079e282aab41f8a63b352c963e0ab7', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('5f051bbef65e410cb0ef36eddfc31d47', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('5f6d573a9ffb455c8d2cdbadec37800c', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('5ff0ba354e424e6a8d2f17667adcca18', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('625145901b8643109d15102e57eb42fd', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '31', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('62647f7669c947708ad2a3144b01691f', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '31', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('62d11c797f3a46a1b72e83e2a214a7e8', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('6663db2b089b49cc9bc664ead20455c1', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('6acffaf2a55c49879031235ec657a80c', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '29', '0', '3.85', null);
INSERT INTO `origin_gps_data` VALUES ('6c1a82321708415fa58ba7a24bdea425', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('6ec02baf901643f5a34f752bcba90092', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('6f28f1d5c2eb4ed8955649176e342b29', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('6fd195eaa112456284ba54b4f584ca3d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('7008f1028aa54945af6b4487bb274682', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('7153ef7993504706a8249a266322b9b3', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('7269dca549a74cba8a0ce6fefe365107', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('77c3bfee5b3c460db7978b75bc8e4341', 'HQ', '1715704370', 'V1', '070328', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', '12.70', '25', '0', '3.92', null);
INSERT INTO `origin_gps_data` VALUES ('7810285e8c2e44d7acae475a921693fe', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('7a12177cc8954ecba0401c7178b89e2a', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('7b85536933b247e4ae493b9606bcaff3', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('7bbf62112e2c41db8fb36473216c2fa1', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('7bf308e8c35240c6b065fde080a8beee', 'HQ', '1715704370', 'V6', '075731', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('7c2958486ce94437bd2755f96ac6edf7', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('7cfbf3fcd58a4b64ae02de5fb40e1ad3', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('7d685eda128c4b17887fdff5d0b9ad4f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('7e66d0ed62174e7cb30299b061257bc1', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '18', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('812a13166f724505b97de61d31f69040', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '18', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('81760ffd95054009b3df8499283271b1', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('8227b0e661a247769fabe0b54a656456', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('828252360717490994c3ecbc29a6ec12', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('82b112f244c54b7d8ccd79d22142a29b', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.92', null);
INSERT INTO `origin_gps_data` VALUES ('83ef98232268474abb79208d1b40d9d4', 'HQ', '1715704370', 'V6', '064729', 'V', '34.212248', 'N', '107.137456', 'E', '0.00', '0.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('8475eebad82d42e9a41bcf9413b99743', 'HQ', '1715704370', 'V1', '021843', 'A', '34.212248', 'N', '107.137456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('8532ff51d4134b29b254565efcda38fd', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '14', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('85e65f6dcd8f4631bb9100668d7662a6', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('8788abcd5e254c7aac527e937e150336', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('87c2dd9d22e1475589652ae303ecb34e', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('88fe85705cf24a02abfdfce3ea79f57d', 'HQ', '1715704370', 'V6', '081825', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('89013e4156e641f0b6eabc80b11b32a9', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('89098656ecaa42f2a1d65f0206cacf07', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('89bbc7a6ff784d8493f71b524c340a96', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('89ffdd9a3c0543df81c1982c8ea0bffd', 'HQ', '1715704370', 'V6', '065504', 'A', '34.212248', 'N', '107.137456', 'E', '0.00', '0.00', '270520', 'FFFFFFFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('8b23021ce7f141f0bdfad2362efa7465', 'HQ', '1715704370', 'V6', '070124', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('8ca5fbf6c9a1478082820853385d9bac', 'HQ', '1715704370', 'V6', '072443', 'A', '34.212228', 'N', '107.137586', 'E', '0.00', '88.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('8ca799401d7548e5a06feb55269c65a9', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('8d24997772dd49408078f9e840b63af1', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('8f463885e9674b6dab94f342349bea22', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('90cdb96d285746a3aa179e07db6ad50f', 'HQ', '1715704370', 'V6', '073954', 'A', '34.208939', 'N', '107.122005', 'E', '12.00', '77.00', '270520', 'FFFFFFFF', '460', '00', '37363', '26797', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('90db03ced6c2437f9c1b1bfde3e74339', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('91769a3f96b44d89b40f23688ab8913a', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('918ddb2bc8e74a4d832f861d539ba1aa', 'HQ', '1715704370', 'V6', '075927', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('92784d772a7a40ed8417a3a6a29c743b', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('968a2bc996464a9a9263dd1d68dd4b7e', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('972e8655a1d048aca151465f8de1afc4', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('974ec4ab0e054aea845fd85a69177b6c', 'HQ', '1715704370', 'V6', '033750', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('989b4b8031254ca5899955151db07f0d', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('99c161c160fb433a9f807ec65475fe80', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('9a9795dbfd35410e904c8414bfac6ac2', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('9b37710dfb804d7cb3182ca47f1a29db', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('9b64b663f8f8441e923f1fc7bebac017', 'HQ', '1715704370', 'V6', '015923', 'A', '3421.3643', 'N', '10720.1040', 'E', '4.00', '6.00', '270520', 'FFFFFFFF', '460', '00', '37247', '20732', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('a0bf06d4f5754c9badbfdfdd3df852c6', 'HQ', '1715704370', 'V6', '072341', 'A', '34.212228', 'N', '107.137586', 'E', '0.00', '88.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26827', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('a0c0b33b444a442186d5d1bd81303477', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '15', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('a1519e8d12aa471a88558bb8d5e4887f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('a36f29c66b9244f08e720be391dbc686', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('a615a4d3a31e4882bdb1ebf319a36860', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '29', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('a615f6bbb25b48e6954e31313dc8edbe', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('a6d504c66bbb454cbb1473093bb62ef8', 'HQ', '1715704370', 'V6', '005315', 'A', '3420.7080', 'N', '10714.0514', 'E', '0.00', '1.00', '270520', 'FFFFFFFF', '460', '00', '37236', '28281', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('ac9c680bb187426d959d27f061bb5c63', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('acaa30542f794993966963e0ae35c4b4', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('ad20902a53e64cbdbf8a17795c3b68d5', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '29', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('ad3f0f54dbaa45a6807dcc3d2a97fac8', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('ae5ed4df59924cf9b1550707e619a40c', 'HQ', '1715704370', 'V6', '080055', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('ae72f78e26e04370b3a746d958a9411f', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('aee12610f2594d9daabe4f63e3a31a84', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('af0143b2b79f41a8b1b102ad85cbf0c5', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('afea1b8a4152413bb02b6bfa1f44687f', 'HQ', '1715704370', 'V1', '074539', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', '12.80', '24', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('b0174a5023844e96b841a6132961fb42', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('b09abd7614e44c25bef590cbe5457989', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('b209515a6d294e7a8d97cfa7c3799b0c', 'HQ', '1715704370', 'V6', '030456', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('b381419fdb8247c1a3276c7771971897', 'HQ', '1715704370', 'V6', '080044', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('b47aa005013442ac9c768a0c8a66711f', 'HQ', '1715704370', 'V6', '025438', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('b519c900ef694af5a85fcd33fe58864d', 'HQ', '1715704370', 'V6', '081245', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('b6b529e90c6a4314973638be94de217f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '20', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('b8166dffebe644d6a4eba4ae07d75d96', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('b84bac6ab3c84b7a9bc758ebbf32f86d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('b8713b902dec4234a27c46de832172aa', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('b928ce8de2f14021b50ddeb1e37310d2', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('b99d11529e8948309e1be4ac640bcbb6', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('ba305f013ba24ac3817cb1ca50f28d59', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('bc18d0b680f744bd98cb584b0090a398', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('bc5a577a7db2493aa19945cdb01ddad3', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('bdc61cee647a43b2bf17a0ad19d4b3c0', 'HQ', '1715704370', 'V6', '072006', 'A', '34.211554', 'N', '107.143785', 'E', '8.00', '49.00', '270520', 'FFFFFFFF', '460', '00', '37236', '4706', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('be7caef76b4e4b53b20bf6be334fe56f', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('c149872132d94aa695639eaacb3ec327', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('c1c228171a0c452296fb3a0611a6e885', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('c31feb5129aa48999cddea6068547cb1', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '14', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('c4d5ec838a1d4832ab583df1b1ea5321', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('c6e22caf694a4c9184fe1bd972f4d0a7', 'HQ', '1715704370', 'V6', '031410', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('c711bd80f7f34a388b267cd534381f01', 'HQ', '1715704370', 'V6', '062509', 'A', '34.212248', 'N', '107.137456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('c960197986094a398fa2ceb148a2295b', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('cbeeaf4ec93d43478ac6d7b9b27130ec', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('cf15ed00fde2442da4f11b7c858ad87c', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('d0a7a11d5beb4d6dabd35c65d5bb1c1e', 'HQ', '1715704370', 'V6', '091321', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('d0a99a83ee744785937c39feb8219770', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('d35fd7b5640f462e9939ccd13c95591c', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('d493757aa62e4eaea2e5e26ac4ea1b3f', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('d4a449fad0fb483eb3df67050b998d00', 'HQ', '1715704370', 'V1', '074539', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', '12.80', '24', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('d4e5edc69fdc476497ffaa6875815255', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('d51bbe7c46ea4a4586a5482a26c08354', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('d62556dac4ea46ad81d5638b9c042601', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('d7545345ec5242139a7f09bc06ab688c', 'HQ', '1715704370', 'V6', '073229', 'A', '34.212228', 'N', '107.137586', 'E', '0.00', '88.00', '270520', 'FFFFFFFF', '460', '00', '37238', '28328', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('d8575d11416f436ea3daffdc0ea30f8d', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('db922c71ce664b09aefef000a2ac91b7', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('dd530e64412f4867aa5b31bc69ce2b51', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('de49d0a9046246578725e70d40589c5e', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('de4d78d5a7084bdfaea97e28c4ae93fc', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('de73c107b12344f1b476a3ff64fc9a3b', 'HQ', '1715704370', 'V1', '074539', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFBFF', '460', '00', '37236', '48717', '12.80', '24', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('deb220cbee8b4d259f06264f40fc47f4', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('e39f60978e4c46188ef00146a6218981', 'HQ', '1715704370', 'V6', '074223', 'A', '34.208722', 'N', '107.128192', 'E', '0.00', '130.00', '270520', 'FFFFFFFF', '460', '00', '37236', '48718', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('e40f41c7d1744466b18a9a1444624732', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('e43fd2b5296f49c282441a10d288a8e7', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('e4dcd5d595b24000b8ab95b4c2973117', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('eaa9f3bb06e349ce83c4c14b5548eebb', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('eafe27c1cec3463a915b2f158d9588ed', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('eb865539c39a493aaea8c113cde1d6fa', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('ebe8659f0dd54a9eae9d9b5cac58c95e', 'HQ', '1715704370', 'V6', '091533', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('ef8eabff94644e1299bd7f8c58e1c859', 'HQ', '1715704370', 'V6', '081445', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, '89860462041970365924');
INSERT INTO `origin_gps_data` VALUES ('f03dc0b383bd4cfcbfd498aebbb5f47b', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('f1e060811b334bf09e7d587fcc9b9997', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('f3147b081df74625a40b1ef78fa0d43a', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '19', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('f389d059158b44f0bda36170894e0ba2', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('f475a7d4f2d6448b8d29935f3ab8e7dc', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('f4ec215af48b4a31b7df08e5ac378fac', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('f63db91cedc34f669d3d5c47a9efc708', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('f7a62be5edd04814a8ce72907b81bbae', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '31', '0', '3.85', null);
INSERT INTO `origin_gps_data` VALUES ('fa9fe951b3da4c8f97a7c04ee655b47c', 'HQ', '1715704370', 'V6', '013828', 'A', '3421.2602', 'N', '10713.5160', 'E', '3.00', '98.00', '270520', 'FFFFFFFF', '460', '00', '37236', '32351', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('fb5a46b1285f4697bb5ffcda1d28b78e', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('fb7b9a54a7d844e4900395356ae88bf0', 'HQ', '1715704370', 'V1', '070328', 'A', '34.211437', 'N', '107.143599', 'E', '0.00', '249.00', '270520', 'FFFFFBFF', '460', '00', '37236', '4706', '12.70', '25', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('fcba8ad54a2b4fd78d3708851b6c507a', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('fcc9c4b40f8343aeb51381e95b31417e', 'HQ', '1715704370', 'V6', '025642', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('fd50fa7f5e334089bc4d44d7dab00446', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('fe21fc397b02431a93a3b05390a87161', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.80', '19', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('fe5ec2728cb44e2cabcd182493b901a4', 'HQ', '1715704370', 'V1', '005818', 'A', '3420.8318', 'N', '10714.1517', 'E', '0.00', '112.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26758', '12.80', '29', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('fed53d9acaf74bcf96084ad13d4a500a', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('ff394849762f42938419a88fdaac699e', 'HQ', '1715704370', 'V1', '021843', 'A', '3421.2248', 'N', '10713.7456', 'E', '0.00', '126.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('ff716d0b87ab483aa179d0fa030e96ae', 'HQ', '1715704370', 'V1', '081256', 'A', '34.212177', 'N', '107.137511', 'E', '0.00', '87.00', '270520', 'FFFFFBFF', '460', '00', '37236', '26829', '12.70', '14', '0', '3.91', null);

-- ----------------------------
-- Table structure for transform_gps_data
-- ----------------------------
DROP TABLE IF EXISTS `transform_gps_data`;
CREATE TABLE `transform_gps_data` (
  `id` varchar(64) NOT NULL,
  `origin_id` varchar(64) NOT NULL COMMENT '原始数据id',
  `latitude` varchar(20) NOT NULL COMMENT '纬度',
  `longitude` varchar(20) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transform_gps_data
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
INSERT INTO `type_info` VALUES ('5f88e673209342a6a6d886b84c87d4c8', '05a4a584e5c745978306e47d913834bf', 'halfday', '100');
INSERT INTO `type_info` VALUES ('672ac03ca3af4668a8dfb0ea823c9785', '05a4a584e5c745978306e47d913834bf', 'hour', '0');
INSERT INTO `type_info` VALUES ('cbe86dd6d8d2490993d7b0669dc84f4f', '05a4a584e5c745978306e47d913834bf', 'week', '900');

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) NOT NULL COMMENT '上级单位id',
  `name` varchar(200) NOT NULL COMMENT '单位名称',
  `tel` varchar(18) NOT NULL COMMENT '负责人手机',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请单位表';

-- ----------------------------
-- Records of unit
-- ----------------------------
INSERT INTO `unit` VALUES ('0d31e70bb4a9453796b9510959d0ca30', 'c7be3c4d047745b8840f9b6529cc6b86', '456', '13000000000', null, null);
INSERT INTO `unit` VALUES ('5f0952e82c0c4454890c90a513b47a90', 'db55c60805754b878baf5f35b5f6202c', '垃圾的酸辣粉', '13000000004', null, null);
INSERT INTO `unit` VALUES ('7b9ee245c3f048ac9601078d4038ce4b', '0', '高新管委会', '', null, null);
INSERT INTO `unit` VALUES ('8b9f02231e4742f1925976e787e5ef15', '0d31e70bb4a9453796b9510959d0ca30', '789', '13000000001', null, null);
INSERT INTO `unit` VALUES ('a1eeace1445f4870a346bf5db57e58a9', '7b9ee245c3f048ac9601078d4038ce4b', 'ASD发', '', null, null);
INSERT INTO `unit` VALUES ('c7be3c4d047745b8840f9b6529cc6b86', '0', '123', '15769271840', null, null);
INSERT INTO `unit` VALUES ('db55c60805754b878baf5f35b5f6202c', '7b9ee245c3f048ac9601078d4038ce4b', '145', '13000000003', null, null);
INSERT INTO `unit` VALUES ('ea344889ce8e4e638da892395aeba595', 'a1eeace1445f4870a346bf5db57e58a9', '124', '13000000002', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `regist_time` datetime NOT NULL COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(30) DEFAULT NULL COMMENT '登陆ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('ee21b75f2e7e4e1fbeb73c2666a898f8', 'c1b50c69b9ff4611b6613363ae9b11e3', 'admin', '$2a$10$elowJGDnO/kppmsmC5PoLOeGvbjdDecl2QFS01xlzVa/uhZwbTxKS', '2020-04-26 00:00:00', '2020-05-26 11:28:09', '192.168.1.233');

-- ----------------------------
-- Table structure for wx_banner
-- ----------------------------
DROP TABLE IF EXISTS `wx_banner`;
CREATE TABLE `wx_banner` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '图片名称',
  `picAddress` varchar(250) NOT NULL COMMENT '图片地址',
  `status` char(2) NOT NULL COMMENT '状态',
  `update_at` date DEFAULT NULL COMMENT '更新时间',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信小程序banner表';

-- ----------------------------
-- Records of wx_banner
-- ----------------------------
INSERT INTO `wx_banner` VALUES ('42601ff719dc4a54a3def79a4e2dc7b9', 'wxBanner', '/uploadFiles/banner/IMG2020041574056.png', '0', null, '');
INSERT INTO `wx_banner` VALUES ('5b3f6b6b2a9e4461a25b07d1584cde08', 'wxBanner2', '/uploadFiles/banner/IMG2020041647829.jpg', '0', null, '看见哈罗德是否合理');

-- ----------------------------
-- Table structure for wx_menus
-- ----------------------------
DROP TABLE IF EXISTS `wx_menus`;
CREATE TABLE `wx_menus` (
  `id` varchar(64) NOT NULL,
  `icon` varchar(150) DEFAULT NULL COMMENT '图标地址',
  `name` varchar(10) NOT NULL COMMENT '名称',
  `link` varchar(150) DEFAULT NULL COMMENT '链接地址',
  `status` char(2) NOT NULL COMMENT '状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信功能菜单表';

-- ----------------------------
-- Records of wx_menus
-- ----------------------------
INSERT INTO `wx_menus` VALUES ('3e769211d3fc4a0bb7f2705bcfc6217d', '/uploadFiles/wx/icon/IMG2020050696823.png', '超值套餐', '/pages/package/package', '0', '');
INSERT INTO `wx_menus` VALUES ('5b74accbeb1f4644b2437418b2950461', '/uploadFiles/wx/icon/IMG2020050697006.png', '领优惠券', '', '0', '');
INSERT INTO `wx_menus` VALUES ('647452fc7d15462c80d67b8887bf5a6c', '/uploadFiles/wx/icon/IMG2020050640762.png', '限时活动', null, '0', '');
INSERT INTO `wx_menus` VALUES ('ff513d6798f84c18a38550f7ec015d39', '/uploadFiles/wx/icon/IMG2020050652903.png', '签到积分', '', '0', '');
