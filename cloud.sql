/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-05-20 17:35:46
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
INSERT INTO `user` VALUES ('ee21b75f2e7e4e1fbeb73c2666a898f8', 'c1b50c69b9ff4611b6613363ae9b11e3', 'admin', '$2a$10$elowJGDnO/kppmsmC5PoLOeGvbjdDecl2QFS01xlzVa/uhZwbTxKS', '2020-04-26 00:00:00', '2020-05-20 17:27:35', '192.168.1.233');

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
