/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-05-26 18:51:50
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
  `speed` double(3,2) NOT NULL COMMENT '速度',
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
INSERT INTO `origin_gps_data` VALUES ('017456bfd6d44f2aa260f16b90f958b2', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('03e9c7b47f8b4b82b58563c59dbb43c1', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '18', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('04d006186ce24f33aaf22e4b7fc42150', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('0cfdb39a7c9e45e3842ff05486778652', 'HQ', '1715704370', 'V6', '084434', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('0d40eced7c7f4b5bb9f3fa2413b5b024', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('0e161a69547a4bc19ee9378c294c92ec', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('13f7c87db7fa463996b11234d60bdd51', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('14047604750f4d918edd9ea9c5757778', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('142be67e11074052b24122c2ff2382d8', 'HQ', '1715704370', 'V6', '093356', 'A', '3421.1559', 'N', '10714.3690', 'E', '0.00', '0.00', '260520', 'FFFFFFFF', '460', '00', '37236', '4706', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('21e68aad75fd41e6b0deba15d6b8ca35', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('301ab53f532a4175b7acc0fd57d2717f', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('3295c324405b4116a15ab718f31aae43', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('34615846e1ca4758a61b6f7a822ce195', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('38083d371d62497cb29fa1bf81ee73b4', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('3b72e9846c984163be06291f3144e5d3', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3bd356fe974c4e7285495fd747c7c11d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('3f1089d5e16a4d4ea9f0fc6c957f62a6', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('438c7d84cab54671a195c1de92610e16', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('4543596c4ad84667a3b3b58cdac20e91', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('45ab08b73fc4464c99adf975347091bd', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('508bec66612d4d36b7499e7a9671eeb1', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('51f0c4c4aed84a10bde73dec497fc2c6', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('597de5fd03f648d0a2e8cf7f699b0e67', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('6ec02baf901643f5a34f752bcba90092', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('6f28f1d5c2eb4ed8955649176e342b29', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('6fd195eaa112456284ba54b4f584ca3d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.90', null);
INSERT INTO `origin_gps_data` VALUES ('7153ef7993504706a8249a266322b9b3', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('7a12177cc8954ecba0401c7178b89e2a', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('7c2958486ce94437bd2755f96ac6edf7', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('812a13166f724505b97de61d31f69040', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '18', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('81760ffd95054009b3df8499283271b1', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('8788abcd5e254c7aac527e937e150336', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('87c2dd9d22e1475589652ae303ecb34e', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('92784d772a7a40ed8417a3a6a29c743b', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('989b4b8031254ca5899955151db07f0d', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('99c161c160fb433a9f807ec65475fe80', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('9b37710dfb804d7cb3182ca47f1a29db', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('ad3f0f54dbaa45a6807dcc3d2a97fac8', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.91', null);
INSERT INTO `origin_gps_data` VALUES ('ae5ed4df59924cf9b1550707e619a40c', 'HQ', '1715704370', 'V6', '080055', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('ae72f78e26e04370b3a746d958a9411f', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.88', null);
INSERT INTO `origin_gps_data` VALUES ('b84bac6ab3c84b7a9bc758ebbf32f86d', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('c149872132d94aa695639eaacb3ec327', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('d0a7a11d5beb4d6dabd35c65d5bb1c1e', 'HQ', '1715704370', 'V6', '091321', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('db922c71ce664b09aefef000a2ac91b7', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '15', '0', '3.86', null);
INSERT INTO `origin_gps_data` VALUES ('de4d78d5a7084bdfaea97e28c4ae93fc', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('deb220cbee8b4d259f06264f40fc47f4', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.80', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('eb865539c39a493aaea8c113cde1d6fa', 'HQ', '1715704370', 'V1', '094358', 'A', '3421.2267', 'N', '10713.7426', 'E', '0.00', '80.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.87', null);
INSERT INTO `origin_gps_data` VALUES ('ebe8659f0dd54a9eae9d9b5cac58c95e', 'HQ', '1715704370', 'V6', '091533', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', null, null, null, null, null);
INSERT INTO `origin_gps_data` VALUES ('fb5a46b1285f4697bb5ffcda1d28b78e', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('fcba8ad54a2b4fd78d3708851b6c507a', 'HQ', '1715704370', 'V1', '064604', 'A', '3421.2355', 'N', '10713.7093', 'E', '0.00', '0.00', '260520', 'FFFFFBFF', '460', '00', '37236', '26828', '12.70', '16', '0', '3.89', null);
INSERT INTO `origin_gps_data` VALUES ('fd50fa7f5e334089bc4d44d7dab00446', 'HQ', '1715704370', 'V1', '082139', 'A', '3421.1396', 'N', '10714.3541', 'E', '0.00', '248.00', '260520', 'FFFFFBFF', '460', '00', '36917', '47774', '12.70', '17', '0', '3.89', null);

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
