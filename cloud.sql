/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-05-04 23:19:25
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
INSERT INTO `user` VALUES ('ee21b75f2e7e4e1fbeb73c2666a898f8', 'c1b50c69b9ff4611b6613363ae9b11e3', 'admin', '$2a$10$elowJGDnO/kppmsmC5PoLOeGvbjdDecl2QFS01xlzVa/uhZwbTxKS', '2020-04-26 00:00:00', '2020-05-04 22:22:37', '192.168.1.233');

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
  `icon` varchar(150) NOT NULL COMMENT '图标地址',
  `name` varchar(10) NOT NULL COMMENT '名称',
  `link` varchar(150) NOT NULL COMMENT '链接地址',
  `status` char(2) NOT NULL COMMENT '状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信功能菜单表';

-- ----------------------------
-- Records of wx_menus
-- ----------------------------
