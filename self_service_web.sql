/*
 Navicat Premium Data Transfer

 Source Server         : Self_service_bar
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : self_service_web

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 26/03/2019 14:08:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator_info
-- ----------------------------
DROP TABLE IF EXISTS `administrator_info`;
CREATE TABLE `administrator_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '管理员密码',
  `bar_id` bigint(20) DEFAULT NULL COMMENT '所属无人吧id',
  `token_create_at` datetime DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11002 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧内部管理员信息';

-- ----------------------------
-- Records of administrator_info
-- ----------------------------
BEGIN;
INSERT INTO `administrator_info` VALUES (11000, '123123', 10000, '2019-03-26 10:01:33', '2018-11-06 10:46:57', '张梦琪');
INSERT INTO `administrator_info` VALUES (11001, '12345', 10008, '2019-03-26 10:01:11', '2018-11-10 02:55:52', 'fw');
COMMIT;

-- ----------------------------
-- Table structure for bar_info
-- ----------------------------
DROP TABLE IF EXISTS `bar_info`;
CREATE TABLE `bar_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '无人吧id',
  `address` varchar(60) DEFAULT NULL COMMENT '无人吧地址',
  `longitude` decimal(15,10) DEFAULT NULL COMMENT '无人吧经度',
  `latitude` decimal(15,10) DEFAULT NULL COMMENT '无人吧纬度',
  `name` varchar(50) DEFAULT NULL COMMENT '无人吧名',
  `phone` varchar(20) DEFAULT NULL COMMENT '无人吧联系电话',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '无人吧创建时间',
  `poster_url` varchar(255) DEFAULT NULL COMMENT '无人吧封面图片url',
  `weekday_business_begin` time DEFAULT NULL COMMENT '无人吧工作日开始营业时间',
  `weekday_business_finish` time DEFAULT NULL COMMENT '无人吧工作日结束营业时间',
  `weekend_business_begin` time DEFAULT NULL COMMENT '无人吧周末开始营业时间',
  `weekend_business_finish` time DEFAULT NULL COMMENT '无人吧周末开始营业时间',
  `ip` varchar(100) DEFAULT NULL COMMENT '无人吧主控电脑ip地址',
  `district` varchar(10) DEFAULT NULL COMMENT '无人吧所属区',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8 COMMENT='无人吧信息表';

-- ----------------------------
-- Records of bar_info
-- ----------------------------
BEGIN;
INSERT INTO `bar_info` VALUES (10000, '北京市海淀区', 116.3646710000, 39.9673780000, '北邮店', '13521359010', '2018-10-13 23:11:11', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542005086609&di=ae57968a7ef2590d2b16a05a5ebeb283&imgtype=0&src=http%3A%2F%2Fimgqn.koudaitong.com%2Fupload_files%2F2015%2F01%2F31%2FFvsgsUkN_kqq1BnmLIfBElAOL9KD.jpg', '08:00:00', '22:00:00', '10:00:00', '22:00:00', '', '北京');
INSERT INTO `bar_info` VALUES (10001, '河北省石家庄市', 114.5279600000, 37.9848770000, '石家庄店', '13120325278', '2018-11-16 09:13:15', NULL, '09:00:00', '23:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '河北');
INSERT INTO `bar_info` VALUES (10002, '陕西省西安市', 108.9663710000, 34.3374760000, '西安店', '13209873212', '2018-11-16 09:13:15', NULL, '08:00:00', '22:00:00', '09:00:00', '22:00:00', 'http://87c88172.ngrok.io', '陕西');
INSERT INTO `bar_info` VALUES (10003, '湖北省武汉市', 114.3383820000, 30.5953590000, '武汉店', '13598320132', '2018-11-16 09:13:15', NULL, '08:00:00', '22:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '湖北');
INSERT INTO `bar_info` VALUES (10004, '湖南省长沙市', 112.9769820000, 28.2439010000, '长沙店', '13578290873', '2018-11-16 09:13:15', NULL, '08:00:00', '22:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '湖南');
INSERT INTO `bar_info` VALUES (10005, '浙江省杭州市', 120.1519270000, 30.2445680000, '杭州店', '13190747548', '2018-11-16 09:13:15', NULL, '08:00:00', '22:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '浙江');
INSERT INTO `bar_info` VALUES (10006, '上海市虹口区', 121.4851570000, 31.2684080000, '虹口店', '13209875693', '2018-11-16 09:13:15', NULL, '08:00:00', '22:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '上海');
INSERT INTO `bar_info` VALUES (10007, '四川省成都市', 104.0778640000, 30.5834210000, '成都店', '13579236767', '2018-11-16 09:13:15', NULL, '08:00:00', '23:00:00', '10:00:00', '21:00:00', 'http://87c88172.ngrok.io', '四川');
INSERT INTO `bar_info` VALUES (10008, '北京市海淀区学院南路', 116.3409570000, 39.9638080000, '中软店', '13521359010', '2019-01-22 08:27:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for entrance_info
-- ----------------------------
DROP TABLE IF EXISTS `entrance_info`;
CREATE TABLE `entrance_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '门禁id',
  `ip_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '门禁ip地址',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `bar_id` bigint(20) DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧内部门禁信息';

-- ----------------------------
-- Records of entrance_info
-- ----------------------------
BEGIN;
INSERT INTO `entrance_info` VALUES (12100, '196.168.2.1', '2018-05-06 14:47:17', 10000, '2018-05-08 01:21:38', 'HUAWEI');
COMMIT;

-- ----------------------------
-- Table structure for hardware_state
-- ----------------------------
DROP TABLE IF EXISTS `hardware_state`;
CREATE TABLE `hardware_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `type` tinyint(4) DEFAULT NULL COMMENT '设备类型 1-门禁，2-座位，3-灯光，4-摄像头',
  `state` tinyint(4) DEFAULT '0' COMMENT '设备状态 0-关闭，1-开启',
  `luminance` int(11) DEFAULT '0' COMMENT '灯的亮度',
  `color_temperature` int(11) DEFAULT '0' COMMENT '灯的色温',
  `availability` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧内部硬件状态（temp）';

-- ----------------------------
-- Records of hardware_state
-- ----------------------------
BEGIN;
INSERT INTO `hardware_state` VALUES (1, 11100, 3, 0, 81, 4437, 0);
INSERT INTO `hardware_state` VALUES (2, 11102, 3, 1, 66, 5713, 0);
INSERT INTO `hardware_state` VALUES (3, 11103, 3, 1, 58, 4840, 0);
INSERT INTO `hardware_state` VALUES (4, 11104, 3, 1, 15, 5751, 0);
INSERT INTO `hardware_state` VALUES (5, 11105, 3, 1, 16, 5788, 0);
INSERT INTO `hardware_state` VALUES (6, 11106, 3, 1, 35, 3000, 0);
INSERT INTO `hardware_state` VALUES (7, 11107, 3, 1, 36, 3000, 0);
INSERT INTO `hardware_state` VALUES (8, 11108, 3, 1, 70, 3000, 0);
INSERT INTO `hardware_state` VALUES (9, 11109, 3, 1, 27, 3000, 0);
INSERT INTO `hardware_state` VALUES (10, 11110, 3, 1, 26, 3859, 0);
INSERT INTO `hardware_state` VALUES (11, 11111, 3, 1, 37, 3000, 0);
INSERT INTO `hardware_state` VALUES (12, 11112, 3, 1, 35, 3000, 0);
INSERT INTO `hardware_state` VALUES (13, 11113, 3, 1, 59, 3000, 0);
INSERT INTO `hardware_state` VALUES (14, 11114, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (15, 11115, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (16, 11116, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (17, 11117, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (18, 11118, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (19, 11119, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (20, 11120, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (21, 11121, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (22, 11122, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (23, 11123, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (24, 11124, 3, 1, 71, 4520, 0);
INSERT INTO `hardware_state` VALUES (25, 11125, 3, 1, 70, 3000, 0);
INSERT INTO `hardware_state` VALUES (26, 11126, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (27, 11127, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (28, 11128, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (29, 11129, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (30, 11130, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (31, 11131, 3, 1, 66, 3000, 0);
INSERT INTO `hardware_state` VALUES (32, 11132, 3, 1, 61, 4123, 0);
INSERT INTO `hardware_state` VALUES (33, 11133, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (34, 11134, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (35, 11135, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (36, 11136, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (37, 11137, 3, 1, 25, 3000, 0);
INSERT INTO `hardware_state` VALUES (38, 11138, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (39, 11139, 3, 1, 64, 4315, 0);
INSERT INTO `hardware_state` VALUES (40, 11140, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (41, 11141, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (42, 11142, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (43, 11143, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (44, 11144, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (45, 11145, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (46, 11146, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (47, 11147, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (48, 11148, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (49, 11149, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (50, 11150, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (51, 11151, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (52, 11152, 3, 1, 46, 3000, 0);
INSERT INTO `hardware_state` VALUES (53, 11153, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (54, 11154, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (55, 11155, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (56, 11156, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (57, 11157, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (58, 11158, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (59, 11159, 3, 0, 51, 4338, 0);
INSERT INTO `hardware_state` VALUES (61, 12100, 1, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (62, 30111, 4, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (63, 30112, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (64, 30113, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (65, 30114, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (66, 30115, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (67, 30116, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (68, 30117, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (69, 30118, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (70, 30119, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (71, 30120, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (72, 30121, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (73, 30122, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (74, 11004, 2, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (75, 11005, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (76, 11006, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (77, 11007, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (78, 11008, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (79, 11009, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (80, 11010, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (81, 11011, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (82, 11012, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (83, 11003, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (84, 11013, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (85, 11014, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (86, 11015, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (87, 11016, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (88, 11017, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (89, 11018, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (90, 11019, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (91, 11020, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (92, 11021, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (93, 11022, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (94, 11023, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (95, 11024, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (96, 11025, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (97, 11026, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (98, 11027, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (99, 11028, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (100, 11029, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (101, 11030, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (102, 11031, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (103, 11032, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (104, 11033, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (105, 11034, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (106, 11035, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (107, 11036, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (108, 11037, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (109, 11038, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (110, 11039, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (111, 11040, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (112, 11041, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (113, 11042, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (114, 11043, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (115, 11044, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (116, 11045, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (117, 11046, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (118, 11047, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (119, 11048, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (120, 11049, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (121, 11050, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (122, 11051, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (123, 11052, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (124, 11053, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (125, 11054, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (126, 11055, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (127, 11056, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (128, 11057, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (129, 11058, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (130, 11059, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (131, 11060, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (132, 11061, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (133, 11160, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (134, 30123, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (135, 11062, 2, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (136, 11161, 3, 1, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (137, 30124, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (138, 30125, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (139, 30126, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (140, 30127, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (141, 30128, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (142, 30129, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (143, 30130, 4, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (144, 40001, 5, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (145, 40002, 5, 0, 0, 3000, 0);
INSERT INTO `hardware_state` VALUES (146, 30131, 4, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (147, 30132, 4, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (148, 11063, 2, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (149, 11064, 2, 1, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (150, 11065, 2, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (151, 11066, 2, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (152, 11067, 2, 0, 0, 0, 0);
INSERT INTO `hardware_state` VALUES (153, 11068, 2, 0, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for hardware_use_log
-- ----------------------------
DROP TABLE IF EXISTS `hardware_use_log`;
CREATE TABLE `hardware_use_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `type` tinyint(4) DEFAULT NULL COMMENT '设备类型 1-门禁，2-座位，3-灯光，4-摄像头',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `affair` tinyint(4) DEFAULT NULL COMMENT '操作事项 0-关闭，1-打开，2-创建，3-修改亮度，4-错误，5-维修',
  `comment` char(100) DEFAULT NULL COMMENT '操作事项备注',
  `user` char(20) DEFAULT NULL COMMENT '操作用户id/管理员',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10367 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hardware_use_log
-- ----------------------------
BEGIN;
INSERT INTO `hardware_use_log` VALUES (10000, 11100, 3, '2018-11-18 18:49:10', 3, '44', 'administer');
INSERT INTO `hardware_use_log` VALUES (10001, 30111, 4, '2018-11-18 18:56:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10002, 30111, 4, '2018-11-18 18:57:12', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10003, 30111, 4, '2018-11-18 19:32:10', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10004, 11004, 2, '2018-11-18 19:32:38', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10005, 30126, 4, '2018-11-18 19:34:41', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10006, 30113, 4, '2018-11-19 09:09:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10007, 30114, 4, '2018-11-19 09:10:08', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10008, 30111, 4, '2018-11-19 09:14:22', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10009, 30111, 4, '2018-11-19 09:20:27', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10010, 11100, 3, '2018-11-19 20:48:28', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10012, 12100, 1, '2018-11-19 20:51:53', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10013, 12100, 1, '2018-11-19 20:52:05', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10014, 30111, 4, '2018-11-20 12:23:19', 5, 'fix up a problem about flaws on camera', 'administer');
INSERT INTO `hardware_use_log` VALUES (10015, 30127, 4, '2018-11-20 13:26:12', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10016, 30128, 4, '2018-11-20 13:35:06', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10070, 11108, 3, '2018-11-22 14:42:32', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10018, 12100, 1, '2018-11-20 20:01:00', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10019, 11004, 2, '2018-11-20 20:18:58', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10020, 11006, 2, '2018-11-20 20:19:02', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10069, 11108, 3, '2018-11-22 14:41:39', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10068, 11109, 3, '2018-11-22 14:41:39', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10023, 30111, 4, '2018-11-20 20:47:05', 4, 'unknow error', 'administer');
INSERT INTO `hardware_use_log` VALUES (10024, 30129, 4, '2018-11-21 11:03:05', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10025, 11104, 3, '2018-11-22 08:42:26', 3, '54', 'administer');
INSERT INTO `hardware_use_log` VALUES (10073, 11104, 3, '2018-11-22 14:46:02', 6, '3561', 'administer');
INSERT INTO `hardware_use_log` VALUES (10072, 11102, 3, '2018-11-22 14:45:55', 6, '5713', 'administer');
INSERT INTO `hardware_use_log` VALUES (10028, 11102, 3, '2018-11-22 08:42:46', 3, '33', 'administer');
INSERT INTO `hardware_use_log` VALUES (10071, 11109, 3, '2018-11-22 14:42:32', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10030, 30111, 4, '2018-11-22 09:28:57', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10031, 30130, 4, '2018-11-22 09:34:43', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10032, 30130, 4, '2018-11-22 09:34:52', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10033, 30111, 4, '2018-11-22 10:13:45', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10034, 11124, 3, '2018-11-22 10:44:07', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10035, 11125, 3, '2018-11-22 10:44:07', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10036, 11124, 3, '2018-11-22 10:44:56', 3, '42', '10003');
INSERT INTO `hardware_use_log` VALUES (10037, 11124, 3, '2018-11-22 10:44:58', 3, '69', '10003');
INSERT INTO `hardware_use_log` VALUES (10038, 11124, 3, '2018-11-22 10:49:03', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10039, 11125, 3, '2018-11-22 10:49:03', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10040, 11125, 3, '2018-11-22 10:49:06', 3, '35', '10003');
INSERT INTO `hardware_use_log` VALUES (10041, 11125, 3, '2018-11-22 10:49:08', 3, '70', '10003');
INSERT INTO `hardware_use_log` VALUES (10042, 11125, 3, '2018-11-22 10:53:09', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10043, 11124, 3, '2018-11-22 10:53:09', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10044, 11124, 3, '2018-11-22 10:55:29', 3, '87', '10003');
INSERT INTO `hardware_use_log` VALUES (10045, 11124, 3, '2018-11-22 10:55:35', 3, '67', '10003');
INSERT INTO `hardware_use_log` VALUES (10046, 11124, 3, '2018-11-22 10:58:29', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10047, 11125, 3, '2018-11-22 10:58:29', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10048, 11124, 3, '2018-11-22 11:02:48', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10049, 11125, 3, '2018-11-22 11:02:48', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10067, 11110, 3, '2018-11-22 14:39:54', 0, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10066, 11110, 3, '2018-11-22 14:39:54', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10052, 11124, 3, '2018-11-22 11:08:15', 3, '50', '10003');
INSERT INTO `hardware_use_log` VALUES (10053, 11124, 3, '2018-11-22 11:10:56', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10054, 11125, 3, '2018-11-22 11:10:56', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10055, 11124, 3, '2018-11-22 11:12:50', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10056, 11125, 3, '2018-11-22 11:12:50', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10057, 11124, 3, '2018-11-22 11:16:34', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10058, 11125, 3, '2018-11-22 11:16:34', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10059, 40001, 5, '2018-11-22 11:22:19', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10060, 40001, 5, '2018-11-22 11:26:07', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10061, 40002, 5, '2018-11-22 11:26:41', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10062, 11109, 3, '2018-11-22 14:26:26', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10063, 11108, 3, '2018-11-22 14:27:39', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10064, 11108, 3, '2018-11-22 14:35:55', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10065, 11109, 3, '2018-11-22 14:35:55', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10074, 11108, 3, '2018-11-22 14:48:19', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10075, 11109, 3, '2018-11-22 14:48:19', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10076, 11109, 3, '2018-11-22 14:48:45', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10077, 11108, 3, '2018-11-22 14:48:45', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10078, 11103, 3, '2018-11-22 14:51:44', 6, '4840', 'administer');
INSERT INTO `hardware_use_log` VALUES (10079, 11108, 3, '2018-11-22 14:52:05', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10080, 11109, 3, '2018-11-22 14:52:05', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10081, 11105, 3, '2018-11-22 22:17:52', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10082, 11106, 3, '2018-11-22 22:17:52', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10083, 11105, 3, '2018-11-22 22:18:00', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10084, 11106, 3, '2018-11-22 22:18:00', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10085, 11105, 3, '2018-11-22 22:21:41', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10086, 11106, 3, '2018-11-22 22:21:41', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10087, 11105, 3, '2018-11-22 22:21:41', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10088, 11106, 3, '2018-11-22 22:21:41', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10089, 11113, 3, '2018-11-22 22:25:25', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10090, 11114, 3, '2018-11-22 22:25:25', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10091, 11113, 3, '2018-11-22 22:28:15', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10092, 11114, 3, '2018-11-22 22:28:15', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10093, 11113, 3, '2018-11-22 22:28:19', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10094, 11114, 3, '2018-11-22 22:28:20', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10095, 11113, 3, '2018-11-22 22:28:51', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10096, 11114, 3, '2018-11-22 22:28:51', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10097, 11113, 3, '2018-11-22 22:36:13', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10098, 11114, 3, '2018-11-22 22:36:13', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10099, 11105, 3, '2018-11-23 09:59:16', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10100, 11106, 3, '2018-11-23 09:59:16', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10101, 40001, 5, '2018-11-23 10:06:57', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10102, 11112, 3, '2018-11-23 10:48:07', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10103, 11113, 3, '2018-11-23 10:48:07', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10104, 11159, 3, '2018-11-23 10:59:31', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10105, 11100, 3, '2018-11-23 10:59:32', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10106, 11102, 3, '2018-11-23 10:59:32', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10107, 11159, 3, '2018-11-23 10:59:44', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10108, 11100, 3, '2018-11-23 10:59:44', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10109, 11102, 3, '2018-11-23 10:59:44', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10110, 11159, 3, '2018-11-23 11:01:14', 3, '59', '10003');
INSERT INTO `hardware_use_log` VALUES (10111, 11159, 3, '2018-11-23 11:01:43', 3, '39', '10003');
INSERT INTO `hardware_use_log` VALUES (10112, 11159, 3, '2018-11-23 11:01:59', 6, '5054', '10003');
INSERT INTO `hardware_use_log` VALUES (10113, 30111, 4, '2018-11-23 11:16:16', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10114, 30131, 4, '2018-11-23 11:23:46', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10115, 11100, 3, '2018-11-23 12:23:44', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10116, 30111, 4, '2018-11-23 12:31:28', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10117, 30111, 4, '2018-11-23 12:33:31', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10118, 40001, 5, '2018-11-23 12:36:59', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10119, 40001, 5, '2018-11-23 12:37:05', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10120, 12100, 1, '2018-11-23 12:37:13', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10121, 12100, 1, '2018-11-23 12:37:16', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10122, 11112, 3, '2018-11-25 11:01:42', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10123, 11112, 3, '2018-11-25 11:06:27', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10124, 11112, 3, '2018-11-25 11:06:28', 0, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10125, 11106, 3, '2018-11-25 11:20:11', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10126, 11131, 3, '2018-11-25 11:24:50', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10127, 11109, 3, '2018-11-25 11:26:46', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10128, 11109, 3, '2018-11-25 11:32:27', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10129, 11124, 3, '2018-11-25 11:37:31', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10130, 11125, 3, '2018-11-25 11:37:31', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10131, 11124, 3, '2018-11-25 11:37:56', 3, '71', '10003');
INSERT INTO `hardware_use_log` VALUES (10132, 11124, 3, '2018-11-25 11:37:59', 6, '4520', '10003');
INSERT INTO `hardware_use_log` VALUES (10133, 11124, 3, '2018-11-25 11:38:00', 0, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10134, 11124, 3, '2018-11-25 11:38:01', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10135, 11124, 3, '2018-11-25 11:38:01', 0, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10136, 12100, 1, '2018-11-25 12:10:22', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10137, 12100, 1, '2018-11-25 12:21:57', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10138, 12100, 1, '2018-11-25 12:21:59', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10139, 12100, 1, '2018-11-25 12:22:08', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10140, 11100, 3, '2018-11-25 13:02:43', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10141, 11100, 3, '2018-11-25 13:03:03', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10142, 11100, 3, '2018-11-25 13:03:20', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10143, 11100, 3, '2018-11-25 13:16:29', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10144, 11100, 3, '2018-11-25 13:16:35', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10145, 11100, 3, '2018-11-25 13:18:06', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10146, 11100, 3, '2018-11-25 13:20:34', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10147, 11100, 3, '2018-11-25 13:22:09', 6, '4661', 'administer');
INSERT INTO `hardware_use_log` VALUES (10148, 11100, 3, '2018-11-25 13:22:13', 3, '20', 'administer');
INSERT INTO `hardware_use_log` VALUES (10149, 11100, 3, '2018-11-25 13:22:17', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10150, 11106, 3, '2018-11-25 14:36:09', 3, '35', 'administer');
INSERT INTO `hardware_use_log` VALUES (10151, 11110, 3, '2018-11-25 14:36:14', 3, '46', 'administer');
INSERT INTO `hardware_use_log` VALUES (10152, 11110, 3, '2018-11-25 14:36:23', 3, '26', 'administer');
INSERT INTO `hardware_use_log` VALUES (10153, 11100, 3, '2018-11-25 14:37:01', 6, '3578', 'administer');
INSERT INTO `hardware_use_log` VALUES (10154, 11102, 3, '2018-11-25 15:50:44', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10155, 11107, 3, '2018-11-25 16:19:34', 1, '', '10003');
INSERT INTO `hardware_use_log` VALUES (10156, 11104, 3, '2018-11-25 17:53:14', 3, '15', 'administer');
INSERT INTO `hardware_use_log` VALUES (10157, 11104, 3, '2018-11-25 17:53:19', 6, '4511', 'administer');
INSERT INTO `hardware_use_log` VALUES (10158, 30132, 4, '2018-11-25 19:15:53', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10159, 11110, 3, '2018-11-26 09:42:07', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10160, 11111, 3, '2018-11-26 09:42:07', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10161, 11110, 3, '2018-11-26 09:42:28', 6, '3859', '10000');
INSERT INTO `hardware_use_log` VALUES (10162, 11111, 3, '2018-11-26 09:42:32', 3, '37', '10000');
INSERT INTO `hardware_use_log` VALUES (10163, 11139, 3, '2018-11-26 09:58:09', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10164, 11152, 3, '2018-11-26 09:58:09', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10165, 11139, 3, '2018-11-26 09:58:16', 3, '64', '10000');
INSERT INTO `hardware_use_log` VALUES (10166, 11152, 3, '2018-11-26 09:58:18', 3, '46', '10000');
INSERT INTO `hardware_use_log` VALUES (10167, 11139, 3, '2018-11-26 09:58:25', 6, '4315', '10000');
INSERT INTO `hardware_use_log` VALUES (10168, 11139, 3, '2018-11-26 09:58:32', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10169, 11139, 3, '2018-11-26 09:58:33', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10170, 11139, 3, '2018-11-26 09:58:34', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10171, 11139, 3, '2018-11-26 09:58:35', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10172, 11139, 3, '2018-11-26 09:58:36', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10173, 11139, 3, '2018-11-26 09:58:40', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10174, 11152, 3, '2018-11-26 10:09:31', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10175, 11139, 3, '2018-11-26 10:09:31', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10176, 11112, 3, '2018-11-26 10:11:43', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10177, 11113, 3, '2018-11-26 10:11:43', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10178, 11132, 3, '2018-11-26 16:56:07', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10179, 11133, 3, '2018-11-26 16:56:07', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10180, 11132, 3, '2018-11-26 16:56:16', 3, '61', '10000');
INSERT INTO `hardware_use_log` VALUES (10181, 11132, 3, '2018-11-26 16:56:21', 6, '4123', '10000');
INSERT INTO `hardware_use_log` VALUES (10182, 11133, 3, '2018-11-26 16:56:33', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10183, 11133, 3, '2018-11-26 16:56:33', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10184, 11100, 3, '2018-11-26 18:39:57', 3, '43', 'administer');
INSERT INTO `hardware_use_log` VALUES (10185, 11100, 3, '2018-11-26 18:40:01', 6, '4659', 'administer');
INSERT INTO `hardware_use_log` VALUES (10186, 11100, 3, '2018-11-27 00:01:55', 3, '28', 'administer');
INSERT INTO `hardware_use_log` VALUES (10187, 11100, 3, '2018-11-27 00:02:00', 6, '5204', 'administer');
INSERT INTO `hardware_use_log` VALUES (10188, 11100, 3, '2018-11-27 00:02:04', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10189, 11063, 2, '2018-11-27 08:32:48', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10190, 11064, 2, '2018-11-27 09:23:29', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10191, 11065, 2, '2018-11-27 09:24:20', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10192, 11064, 2, '2018-11-27 09:24:29', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10193, 11159, 3, '2018-11-27 10:39:54', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10194, 11102, 3, '2018-11-27 10:39:54', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10195, 11100, 3, '2018-11-27 10:39:54', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10196, 11159, 3, '2018-11-27 10:40:06', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10197, 11102, 3, '2018-11-27 10:40:06', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10198, 11100, 3, '2018-11-27 10:40:06', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10199, 11159, 3, '2018-11-27 10:40:09', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10200, 11102, 3, '2018-11-27 10:40:09', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10201, 11100, 3, '2018-11-27 10:40:09', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10202, 11159, 3, '2018-11-27 10:40:29', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10203, 11159, 3, '2018-11-27 10:40:30', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10204, 11159, 3, '2018-11-27 10:40:41', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10205, 11159, 3, '2018-11-27 10:40:42', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10206, 11159, 3, '2018-11-27 10:40:43', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10207, 11159, 3, '2018-11-27 10:40:44', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10208, 11159, 3, '2018-11-27 10:40:49', 3, '25', '10000');
INSERT INTO `hardware_use_log` VALUES (10209, 11159, 3, '2018-11-27 10:40:59', 6, '6500', '10000');
INSERT INTO `hardware_use_log` VALUES (10210, 30111, 4, '2018-11-27 10:54:08', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10211, 11066, 2, '2018-11-27 10:57:05', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10212, 11100, 3, '2018-11-27 10:57:32', 3, '65', 'administer');
INSERT INTO `hardware_use_log` VALUES (10213, 11100, 3, '2018-11-27 10:57:39', 6, '5663', 'administer');
INSERT INTO `hardware_use_log` VALUES (10214, 11159, 3, '2018-11-27 11:52:55', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10215, 11100, 3, '2018-11-27 11:52:55', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10216, 11102, 3, '2018-11-27 11:52:56', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10217, 11159, 3, '2018-11-27 11:52:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10218, 11100, 3, '2018-11-27 11:52:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10219, 11102, 3, '2018-11-27 11:52:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10220, 11159, 3, '2018-11-27 12:07:18', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10221, 11102, 3, '2018-11-27 12:07:18', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10222, 11100, 3, '2018-11-27 12:07:18', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10223, 11100, 3, '2018-11-27 21:15:14', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10224, 11159, 3, '2018-11-27 21:15:14', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10225, 11105, 3, '2018-11-28 11:12:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10226, 11106, 3, '2018-11-28 11:12:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10227, 11107, 3, '2018-11-28 11:12:57', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10228, 11105, 3, '2018-11-28 11:13:02', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10229, 11105, 3, '2018-11-28 11:13:03', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10230, 11106, 3, '2018-11-28 11:13:03', 0, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10231, 11106, 3, '2018-11-28 11:13:04', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10232, 11105, 3, '2018-11-28 11:13:08', 3, '16', '10000');
INSERT INTO `hardware_use_log` VALUES (10233, 11105, 3, '2018-11-28 11:13:11', 6, '5788', '10000');
INSERT INTO `hardware_use_log` VALUES (10234, 11105, 3, '2018-11-28 11:13:15', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10235, 11106, 3, '2018-11-28 11:13:15', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10236, 11107, 3, '2018-11-28 11:13:15', 1, '', '10000');
INSERT INTO `hardware_use_log` VALUES (10237, 11100, 3, '2018-12-13 18:20:08', 6, '5366', 'administer');
INSERT INTO `hardware_use_log` VALUES (10238, 1, 6, '2018-12-14 10:16:29', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10239, 11159, 3, '2018-12-14 10:23:36', 1, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10240, 11159, 3, '2018-12-15 16:19:12', 3, '63', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10241, 11159, 3, '2018-12-15 16:20:20', 3, '82', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10242, 11159, 3, '2018-12-15 16:23:48', 3, '59', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10243, 11159, 3, '2018-12-15 16:23:52', 3, '92', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10244, 11159, 3, '2018-12-15 16:23:55', 6, '4862', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10245, 11159, 3, '2018-12-15 16:25:59', 3, '54', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10246, 11159, 3, '2018-12-15 16:26:01', 1, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10247, 11159, 3, '2018-12-15 16:26:01', 0, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10248, 11159, 3, '2018-12-15 16:26:20', 6, '4429', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10249, 11159, 3, '2018-12-15 20:33:16', 3, '77', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10250, 11159, 3, '2018-12-15 20:33:26', 3, '60', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10251, 11159, 3, '2018-12-15 20:33:27', 1, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10252, 11159, 3, '2018-12-15 20:56:24', 3, '86', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10253, 11159, 3, '2018-12-15 21:11:32', 1, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10254, 11159, 3, '2018-12-15 21:11:43', 3, '51', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10255, 11159, 3, '2018-12-15 21:11:48', 6, '4338', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10256, 11159, 3, '2018-12-15 21:11:50', 0, '', 'pad:40001');
INSERT INTO `hardware_use_log` VALUES (10257, 11100, 3, '2019-01-02 07:45:40', 3, '81', 'administer');
INSERT INTO `hardware_use_log` VALUES (10258, 11100, 3, '2019-01-02 07:46:03', 6, '3672', 'administer');
INSERT INTO `hardware_use_log` VALUES (10259, 11102, 3, '2019-01-02 21:20:18', 3, '66', 'administer');
INSERT INTO `hardware_use_log` VALUES (10260, 11104, 3, '2019-01-02 21:35:15', 6, '5751', 'administer');
INSERT INTO `hardware_use_log` VALUES (10261, 12100, 1, '2019-01-03 01:21:26', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10262, 99999, 3, '2019-01-03 01:30:41', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10263, 99999, 3, '2019-01-03 01:30:41', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10264, 99999, 3, '2019-01-03 01:30:42', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10265, 99999, 3, '2019-01-03 01:30:42', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10266, 99999, 3, '2019-01-03 01:30:43', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10267, 99999, 3, '2019-01-03 01:30:43', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10268, 99999, 3, '2019-01-03 01:30:43', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10269, 99999, 3, '2019-01-03 01:30:43', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10270, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10271, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10272, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10273, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10274, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10275, 99999, 3, '2019-01-03 01:31:31', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10276, 99999, 3, '2019-01-03 01:31:32', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10277, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10278, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10279, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10280, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10281, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10282, 99999, 2, '2019-01-03 01:32:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10283, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10284, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10285, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10286, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10287, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10288, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10289, 99999, 3, '2019-01-03 01:44:25', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10290, 99999, 3, '2019-01-03 01:44:26', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10291, 99999, 1, '2019-01-03 01:44:39', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10292, 99999, 1, '2019-01-03 01:44:39', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10293, 99999, 1, '2019-01-03 01:44:39', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10294, 99999, 1, '2019-01-03 01:44:39', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10295, 99999, 1, '2019-01-03 01:44:39', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10296, 99999, 3, '2019-01-03 01:44:56', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10297, 99999, 3, '2019-01-03 01:44:56', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10298, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10299, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10300, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10301, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10302, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10303, 99999, 3, '2019-01-03 01:48:06', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10304, 99999, 3, '2019-01-03 01:48:07', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10305, 99999, 3, '2019-01-03 01:48:07', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10306, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10307, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10308, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10309, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10310, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10311, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10312, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10313, 99999, 2, '2019-01-03 01:48:12', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10314, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10315, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10316, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10317, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10318, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10319, 99999, 4, '2019-01-03 01:48:13', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10320, 99999, 4, '2019-01-03 01:48:14', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10321, 99999, 1, '2019-01-03 01:48:14', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10322, 99999, 1, '2019-01-03 01:48:14', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10323, 99999, 1, '2019-01-03 01:48:14', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10324, 99999, 1, '2019-01-03 01:48:14', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10325, 99999, 4, '2019-01-03 01:48:14', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10326, 99999, 1, '2019-01-03 01:48:15', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10327, 99999, 1, '2019-01-03 01:48:15', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10328, 99999, 1, '2019-01-03 01:48:16', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10329, 99999, 1, '2019-01-03 01:48:16', 0, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10330, 99999, 5, '2019-01-03 01:48:16', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10331, 99999, 5, '2019-01-03 01:48:16', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10332, 99999, 5, '2019-01-03 01:48:16', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10333, 99999, 5, '2019-01-03 01:48:16', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10334, 99999, 5, '2019-01-03 01:48:17', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10335, 99999, 5, '2019-01-03 01:48:17', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10336, 99999, 5, '2019-01-03 01:48:17', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10337, 99999, 5, '2019-01-03 01:48:17', 0, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10338, 99999, 3, '2019-01-03 03:38:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10339, 99999, 3, '2019-01-03 03:38:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10340, 99999, 3, '2019-01-03 03:38:00', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10341, 99999, 3, '2019-01-03 03:38:01', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10342, 99999, 3, '2019-01-03 03:38:01', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10343, 99999, 3, '2019-01-03 03:38:01', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10344, 99999, 3, '2019-01-03 03:38:01', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10345, 99999, 3, '2019-01-03 03:38:01', 1, '', 'admin');
INSERT INTO `hardware_use_log` VALUES (10346, 99999, 1, '2019-01-03 04:31:31', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10347, 99999, 1, '2019-01-03 04:31:31', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10348, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10349, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10350, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10351, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10352, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10353, 99999, 1, '2019-01-03 04:31:32', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10354, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10355, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10356, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10357, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10358, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10359, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10360, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10361, 99999, 1, '2019-01-03 04:31:51', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10362, 11067, 2, '2019-01-22 21:09:52', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10363, 11068, 2, '2019-01-22 21:10:35', 2, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10364, 30111, 4, '2019-01-23 15:00:49', 1, '', 'administer');
INSERT INTO `hardware_use_log` VALUES (10365, 11100, 3, '2019-01-23 15:00:55', 6, '4437', 'administer');
INSERT INTO `hardware_use_log` VALUES (10366, 11004, 2, '2019-03-26 10:02:35', 1, '', 'administer');
COMMIT;

-- ----------------------------
-- Table structure for light_info
-- ----------------------------
DROP TABLE IF EXISTS `light_info`;
CREATE TABLE `light_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '灯id',
  `ip_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '灯ip地址',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `seat_id` bigint(20) DEFAULT NULL COMMENT '所属座位id',
  `bar_id` bigint(20) DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `hardware_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11162 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧内部灯光信息';

-- ----------------------------
-- Records of light_info
-- ----------------------------
BEGIN;
INSERT INTO `light_info` VALUES (11100, '192.168.3.1', '2018-05-10 13:31:29', 11004, 10000, '2018-05-19 13:34:29', 'APPLE', 221118);
INSERT INTO `light_info` VALUES (11102, '192.168.3.2', '2018-05-10 13:31:29', 11005, 10000, '2018-05-19 13:34:29', 'APPLE', 221119);
INSERT INTO `light_info` VALUES (11103, '192.168.3.3', '2018-05-10 13:31:29', 11006, 10000, '2018-05-19 13:34:29', 'APPLE', 221120);
INSERT INTO `light_info` VALUES (11104, '192.168.3.4', '2018-05-10 13:31:29', 11007, 10000, '2018-05-19 13:34:29', 'APPLE', 221121);
INSERT INTO `light_info` VALUES (11105, '192.168.3.5', '2018-05-10 13:31:29', 11008, 10000, '2018-05-19 13:34:29', 'APPLE', 221122);
INSERT INTO `light_info` VALUES (11106, '192.168.3.6', '2018-05-10 13:31:29', 11009, 10000, '2018-05-19 13:34:29', 'APPLE', 221124);
INSERT INTO `light_info` VALUES (11107, '192.168.3.7', '2018-05-10 13:31:29', 11010, 10000, '2018-05-19 13:34:29', 'APPLE', 221125);
INSERT INTO `light_info` VALUES (11108, '192.168.3.8', '2018-05-10 13:31:29', 11011, 10000, '2018-05-19 13:34:29', 'APPLE', 221123);
INSERT INTO `light_info` VALUES (11109, '192.168.3.9', '2018-05-10 13:31:29', 11012, 10000, '2018-05-19 13:34:29', 'APPLE', 221126);
INSERT INTO `light_info` VALUES (11110, '192.168.3.10', '2018-05-10 13:31:29', 11013, 10000, '2018-05-19 13:34:29', 'APPLE', 221127);
INSERT INTO `light_info` VALUES (11111, '192.168.3.11', '2018-05-10 13:31:29', 11014, 10000, '2018-05-19 13:34:29', 'APPLE', 221128);
INSERT INTO `light_info` VALUES (11112, '192.168.3.12', '2018-05-10 13:31:29', 11015, 10000, '2018-05-19 13:34:29', 'APPLE', 221129);
INSERT INTO `light_info` VALUES (11113, '192.168.3.13', '2018-05-10 13:31:29', 11016, 10000, '2018-05-19 13:34:29', 'APPLE', 221130);
INSERT INTO `light_info` VALUES (11114, '192.168.3.14', '2018-05-10 13:31:29', 11017, 10000, '2018-05-19 13:34:29', 'APPLE', 221131);
INSERT INTO `light_info` VALUES (11115, '192.168.3.15', '2018-05-10 13:31:29', 11018, 10000, '2018-05-19 13:34:29', 'APPLE', 221132);
INSERT INTO `light_info` VALUES (11116, '192.168.3.16', '2018-05-10 13:31:29', 11019, 10000, '2018-05-19 13:34:29', 'APPLE', 221133);
INSERT INTO `light_info` VALUES (11117, '192.168.3.17', '2018-05-10 13:31:29', 11020, 10000, '2018-05-19 13:34:29', 'APPLE', 221134);
INSERT INTO `light_info` VALUES (11118, '192.168.3.18', '2018-05-10 13:31:29', 11021, 10000, '2018-05-19 13:34:29', 'APPLE', 221135);
INSERT INTO `light_info` VALUES (11119, '192.168.3.19', '2018-05-10 13:31:29', 11022, 10000, '2018-05-19 13:34:29', 'APPLE', 221136);
INSERT INTO `light_info` VALUES (11120, '192.168.3.20', '2018-05-10 13:31:29', 11023, 10000, '2018-05-19 13:34:29', 'APPLE', 221137);
INSERT INTO `light_info` VALUES (11121, '192.168.3.21', '2018-05-10 13:31:29', 11024, 10000, '2018-05-19 13:34:29', 'APPLE', 221138);
INSERT INTO `light_info` VALUES (11122, '192.168.3.22', '2018-05-10 13:31:29', 11025, 10000, '2018-05-19 13:34:29', 'APPLE', 221139);
INSERT INTO `light_info` VALUES (11123, '192.168.3.23', '2018-05-10 13:31:29', 11026, 10000, '2018-05-19 13:34:29', 'APPLE', 221140);
INSERT INTO `light_info` VALUES (11124, '192.168.3.24', '2018-05-10 13:31:29', 11027, 10000, '2018-05-19 13:34:29', 'APPLE', 221141);
INSERT INTO `light_info` VALUES (11125, '192.168.3.25', '2018-05-10 13:31:29', 11028, 10000, '2018-05-19 13:34:29', 'APPLE', 221142);
INSERT INTO `light_info` VALUES (11126, '192.168.3.26', '2018-05-10 13:31:29', 11029, 10000, '2018-05-19 13:34:29', 'APPLE', 221144);
INSERT INTO `light_info` VALUES (11127, '192.168.3.27', '2018-05-10 13:31:29', 11030, 10000, '2018-05-19 13:34:29', 'APPLE', 221143);
INSERT INTO `light_info` VALUES (11128, '192.168.3.28', '2018-05-10 13:31:29', 11031, 10000, '2018-05-19 13:34:29', 'APPLE', 221145);
INSERT INTO `light_info` VALUES (11129, '192.168.3.29', '2018-05-10 13:31:29', 11032, 10000, '2018-05-19 13:34:29', 'APPLE', 221146);
INSERT INTO `light_info` VALUES (11130, '192.168.3.30', '2018-05-10 13:31:29', 11033, 10000, '2018-05-19 13:34:29', 'APPLE', 221147);
INSERT INTO `light_info` VALUES (11131, '192.168.3.31', '2018-05-10 13:31:29', 11034, 10000, '2018-05-19 13:34:29', 'APPLE', 221148);
INSERT INTO `light_info` VALUES (11132, '192.168.3.32', '2018-05-10 13:31:29', 11035, 10000, '2018-05-19 13:34:29', 'APPLE', 221149);
INSERT INTO `light_info` VALUES (11133, '192.168.3.33', '2018-05-10 13:31:29', 11036, 10000, '2018-05-19 13:34:29', 'APPLE', 221150);
INSERT INTO `light_info` VALUES (11134, '192.168.3.34', '2018-05-10 13:31:29', 11037, 10000, '2018-05-19 13:34:29', 'APPLE', 221171);
INSERT INTO `light_info` VALUES (11135, '192.168.3.35', '2018-05-10 13:31:29', 11038, 10000, '2018-05-19 13:34:29', 'APPLE', 221172);
INSERT INTO `light_info` VALUES (11136, '192.168.3.36', '2018-05-10 13:31:29', 11039, 10000, '2018-05-19 13:34:29', 'APPLE', 221173);
INSERT INTO `light_info` VALUES (11137, '192.168.3.37', '2018-05-10 13:31:29', 11040, 10000, '2018-05-19 13:34:29', 'APPLE', 221174);
INSERT INTO `light_info` VALUES (11138, '192.168.3.38', '2018-05-10 13:31:29', 11041, 10000, '2018-05-19 13:34:29', 'APPLE', 221175);
INSERT INTO `light_info` VALUES (11139, '192.168.3.39', '2018-05-10 13:31:29', 11042, 10000, '2018-05-19 13:34:29', 'APPLE', 221176);
INSERT INTO `light_info` VALUES (11140, '192.168.3.40', '2018-05-10 13:31:29', 11043, 10000, '2018-05-19 13:34:29', 'APPLE', 221177);
INSERT INTO `light_info` VALUES (11141, '192.168.3.41', '2018-05-10 13:31:29', 11044, 10000, '2018-05-19 13:34:29', 'APPLE', 221178);
INSERT INTO `light_info` VALUES (11142, '192.168.3.42', '2018-05-10 13:31:29', 11045, 10000, '2018-05-19 13:34:29', 'APPLE', 221179);
INSERT INTO `light_info` VALUES (11143, '192.168.3.43', '2018-05-10 13:31:29', 11046, 10000, '2018-05-19 13:34:29', 'APPLE', 221151);
INSERT INTO `light_info` VALUES (11144, '192.168.3.44', '2018-05-10 13:31:29', 11047, 10000, '2018-05-19 13:34:29', 'APPLE', 221152);
INSERT INTO `light_info` VALUES (11145, '192.168.3.45', '2018-05-10 13:31:29', 11048, 10000, '2018-05-19 13:34:29', 'APPLE', 221153);
INSERT INTO `light_info` VALUES (11146, '192.168.3.46', '2018-05-10 13:31:29', 11049, 10000, '2018-05-19 13:34:29', 'APPLE', 221154);
INSERT INTO `light_info` VALUES (11147, '192.168.3.47', '2018-05-10 13:31:29', 11050, 10000, '2018-05-19 13:34:29', 'APPLE', 221155);
INSERT INTO `light_info` VALUES (11148, '192.168.3.48', '2018-05-10 13:31:29', 11051, 10000, '2018-05-19 13:34:29', 'APPLE', 221156);
INSERT INTO `light_info` VALUES (11149, '192.168.3.49', '2018-05-10 13:31:29', 11052, 10000, '2018-05-19 13:34:29', 'APPLE', 221157);
INSERT INTO `light_info` VALUES (11150, '192.168.3.50', '2018-05-10 13:31:29', 11053, 10000, '2018-05-19 13:34:29', 'APPLE', 221158);
INSERT INTO `light_info` VALUES (11151, '192.168.3.51', '2018-05-10 13:31:29', 11054, 10000, '2018-05-19 13:34:29', 'APPLE', 221159);
INSERT INTO `light_info` VALUES (11152, '192.168.3.52', '2018-05-10 13:31:29', 11055, 10000, '2018-05-19 13:34:29', 'APPLE', 221161);
INSERT INTO `light_info` VALUES (11153, '192.168.3.53', '2018-05-10 13:31:29', 11056, 10000, '2018-05-19 13:34:29', 'APPLE', 221162);
INSERT INTO `light_info` VALUES (11154, '192.168.3.54', '2018-05-10 13:31:29', 11057, 10000, '2018-05-19 13:34:29', 'APPLE', 221163);
INSERT INTO `light_info` VALUES (11155, '192.168.3.55', '2018-05-10 13:31:29', 11058, 10000, '2018-05-19 13:34:29', 'APPLE', 221164);
INSERT INTO `light_info` VALUES (11156, '192.168.3.56', '2018-05-10 13:31:29', 11059, 10000, '2018-05-19 13:34:29', 'APPLE', 221165);
INSERT INTO `light_info` VALUES (11157, '192.168.3.57', '2018-05-10 13:31:29', 11060, 10000, '2018-05-19 13:34:29', 'APPLE', 221166);
INSERT INTO `light_info` VALUES (11158, '192.168.3.58', '2018-05-10 13:31:29', 11061, 10000, '2018-05-19 13:34:29', 'APPLE', 221167);
INSERT INTO `light_info` VALUES (11159, '192.168.3.59', '2018-05-10 13:31:29', 11003, 10000, '2018-05-19 13:34:29', 'APPLE', 221168);
INSERT INTO `light_info` VALUES (11160, '192.168.3.60', '2018-05-10 13:31:29', 10023, 10000, '2018-05-19 13:34:29', 'APPLE', 221180);
INSERT INTO `light_info` VALUES (11161, '192.168.3.61', '2018-05-10 13:31:29', 10001, 10000, '2018-05-19 13:34:29', 'APPLE', 221181);
COMMIT;

-- ----------------------------
-- Table structure for monitor_info
-- ----------------------------
DROP TABLE IF EXISTS `monitor_info`;
CREATE TABLE `monitor_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ipAddress` char(20) DEFAULT NULL,
  `hardwareId` bigint(20) unsigned DEFAULT NULL,
  `location` char(20) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) DEFAULT NULL,
  `bar_id` bigint(20) DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30133 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of monitor_info
-- ----------------------------
BEGIN;
INSERT INTO `monitor_info` VALUES (30111, '192.168.1.1', 412321, '3B', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30112, '192.168.1.2', 412332, '4E', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30113, '192.168.1.3', 412333, '2A', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30114, '192.168.1.5', 412324, '1C', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30115, '192.168.1.6', 412326, '2D', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30116, '192.168.1.7', 412327, '1B', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30117, '192.168.1.8', 412328, '2C', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30118, '192.168.1.9', 412329, '3A', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30119, '192.168.1.10', 4123210, '4B', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30120, '192.168.1.11', 4123211, '4C', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30121, '192.168.1.12', 4123212, '1A', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30122, '192.168.1.13', 4123213, '3D', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30123, '192.168.1.14', 4123214, '4p', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30124, '192.168.1.15', 4123215, '4p', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30125, '192.168.1.16', 4123216, '4r', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30126, '192.168.1.17', 4123217, '4p2', '2018-05-08 19:14:29', '2018-05-13 10:34:29', 'Microsoft', 10000);
INSERT INTO `monitor_info` VALUES (30127, '192.168.1.18', 4123218, '4p', '2018-10-11 11:00:00', '2018-10-14 11:00:00', 'XIAOMI', 10000);
INSERT INTO `monitor_info` VALUES (30128, '192.168.1.19', 4123219, '4r', '2018-11-01 13:00:12', '2018-11-02 13:00:11', 'XIAOMI', 10000);
INSERT INTO `monitor_info` VALUES (30129, '192.168.1.20', 4123220, '3a', '2018-11-01 00:32:00', '2018-11-03 12:31:00', 'APPLE', 10000);
INSERT INTO `monitor_info` VALUES (30130, '192.168.1.21', 4123221, '3a', '2018-11-06 12:31:00', '2018-11-08 12:31:00', 'XIAOMI', 10000);
INSERT INTO `monitor_info` VALUES (30131, '192.168.1.22', 4123222, '4r', '2018-11-09 09:08:00', '2018-11-10 00:12:00', 'MM', 10000);
INSERT INTO `monitor_info` VALUES (30132, '192.168.1.23', 4123222, '4r', '2018-11-01 00:32:00', '2018-11-02 00:32:00', 'APPLE', 10000);
COMMIT;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单记录id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `bar_id` bigint(20) DEFAULT NULL COMMENT '无人吧id',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态:1-已提交,2-已付款,3-已结束,4-已取消',
  `order_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '订单秘钥',
  `seat_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '全部座位id拼接字符串',
  `scheduled_day` date DEFAULT NULL COMMENT '预定日期',
  `begin_hour` tinyint(4) DEFAULT NULL COMMENT '预定起始小时',
  `end_hour` tinyint(4) DEFAULT NULL COMMENT '预定结束小时',
  `admission` tinyint(4) DEFAULT '0' COMMENT '当前可入人数',
  `verify` tinyint(1) DEFAULT '0' COMMENT '是否通过门禁验证',
  `clean` tinyint(1) DEFAULT '0' COMMENT '是否收拾干净',
  `existing` tinyint(1) DEFAULT '0' COMMENT '当前该订单进入无人吧的人数',
  `leave_request` tinyint(1) DEFAULT '0' COMMENT '用户是否已经请求过结束订单',
  `renewal_end_hour` tinyint(4) DEFAULT NULL COMMENT '续时后的结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧订单信息';

-- ----------------------------
-- Records of order_info
-- ----------------------------
BEGIN;
INSERT INTO `order_info` VALUES (1, '1542093480360Hwlcw00', 10000, '10000', 2, 'Pn2Gc5nlz0yaXGOcehy1ZDnRc1cw6G', '11004+11005', '2018-11-13', 10, 22, 0, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (2, '1541148903029zgeu200', 10000, '10002', 2, 'MuUamUXh4qXYtCvDWHGMihEe8ZaayZ', '10003', '2018-11-02', 16, 18, 10, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (3, '1542247295018XLBwy00', 10000, '10002', 2, 'YglVwlkJRR5BPahRXRK3yiyip1FiZ1', '11034+11035', '2018-11-15', 10, 12, 12, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (4, '1542259273430Lw9vb00', 10000, '10002', 2, '2E6CJYUhxpFNUnIgp5eJJlcyz91l7A', '11012+11011+11015+11016', '2018-11-15', 13, 23, 4, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (5, '1542262683454tqWqb00', 10000, '10000', 2, 'Kb4HPcyEiXVfXVa9aF009snVWCYVDP', '11005', '2018-11-19', 10, 15, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (6, '1542290428494Cb4Tk00', 10000, '10002', 2, 'roi17sqU8KwMjh0VZUEZEpAlxVKDd5', '11040+11032+11033+11041', '2018-11-16', 9, 13, 4, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (7, '1540899290477NtHox00', 10000, '10002', 2, 'iSlqsLoDnOu1aQoMzqdZ6Uwp1ettqg', '10003', '2018-10-30', 13, 17, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (8, '1542332597563inPPk00', 10000, '10002', 2, 'fzmUVXxdR3JIDQLthpJoQCOg7nI3Ex', '11008+11009+11010', '2018-11-16', 10, 13, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (9, '1542333812366pPhcH00', 10000, '10002', 2, '12M2iVjwbM4JCI88VNYMGwRWcuN4dE', '11008+11009+11010', '2018-11-16', 10, 15, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (10, '15423344484332Bj3700', 10000, '10003', 2, 'beAZDU0Vi5DCLacWltVJZIRgo9BYmJ', '11017+11016+11013', '2018-11-17', 13, 15, 3, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (11, '1540898116261RP7mX00', 10000, '10002', 2, 'DLLPmlxpMTSiq8WAgSyJN3U8ftqjgK', '10006+10004', '2018-10-30', 19, 22, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (12, '1542726657809EAEWb00', 10000, '10002', 2, '9I76PxYY6yw18sPBRtBzLvp7hZfkw4', '11003', '2018-10-10', 1, 2, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (13, '1542728514341sWpQ600', 10000, '10003', 2, 'A5MTEE5yJ8ljA5YcY4fcqNokqy0m0Z', '11029', '2018-11-21', 0, 1, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (14, '1542730616265j9eyh00', 10000, '10003', 2, '9rpP3PN1gzI4T2VcfpfsS60iJ5pDUq', '11009', '2018-11-21', 0, 1, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (15, '1542763548015ij7eA00', 10000, '10003', 2, '12nKjehORPrfE7vU8emZTwxGxNTIb7', '11016', '2018-11-21', 9, 10, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (16, '1542763588570G5FM000', 10000, '10003', 2, 'MOCcIxGxm4BIkiuHiyvGh3xFBJaljb', '11025', '2018-11-21', 10, 11, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (17, '1542764080582wVTPN00', 10000, '10002', 2, 'qi9lWqsUPuC6OMTNwLGgJhYgYRhS0x', '11023', '2018-11-21', 10, 11, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (18, '1542772352560FmgEd00', 10000, '10002', 2, 'M8yVy5ji1iDZPQXwexsdWI6XF55fMx', '11003', '2018-05-15', 13, 14, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (19, '1540807076387Yu9qE00', 10000, '10002', 2, 'n5Hr6LuvarXk5tjQp23w0T9v1sofMU', '11002+8', '2018-10-29', 17, 22, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (20, '1542772505195hXnt900', 10000, '10003', 2, 'yyaSJFSYVwAIej4rZJofV1lXcrO27I', '11008+11010', '2019-05-15', 13, 14, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (21, '1542772544690lsGFo00', 10000, '10003', 2, 'HoehiTRyLmbGTh4UPNjKSzOyI2uiX7', '11003', '2019-05-15', 11, 12, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (22, '1542773034847hZhOw00', 10000, '10003', 2, 'gOVk3OsTWSp2KH0Elphmvpgk5jHZcq', '11005+11004+11010', '2018-11-21', 12, 13, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (23, '1542773094594MZ7jd00', 10000, '10003', 2, 'xskyvlyojJor7X0Ho1heGZ2fnT5RsF', '11025+11026', '2018-11-21', 13, 14, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (24, '1542773289615idlFH00', 10000, '10003', 2, 'ArD1FXhxoBLiOrs9AZhVQNNCJYdM4y', '11004+11005', '2018-12-15', 13, 14, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (25, '1542773465582qjB6X00', 10000, '10003', 2, 'uloJRsjedsq6OfweQ1SxmJDIicGp8L', '11015+11017+11018', '2018-11-21', 13, 14, 3, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (26, '1542773505703dl05c00', 10000, '10003', 2, 'ZvdpZF9UD2FmT1OjSzA9GSV9i2QtNr', '11015+11017+11018', '2018-11-21', 13, 14, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (27, '15427735586853Q9Q600', 10000, '10003', 2, 'axRx0nKhz9bT4DUU4s9VpqjskNuFi5', '11003+11004', '2018-11-21', 13, 14, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (28, '15427738942129MuLG00', 10000, '10003', 2, 'lstROU7xmvfURZqer38UEo9JYhS1jP', '11009+11010', '2018-11-15', 13, 14, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (29, '1542774038132emf0s00', 10000, '10003', 2, 'PPA8qEYZBsvJVS7zqvxBVsScvgmysu', '11023+11024+11025', '2018-11-21', 13, 14, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (30, '1542774554451jNlDO00', 10000, '10002', 2, 'gXSlSd5f4hSQWL0MdGACVfVjnMGXII', '11037+11045', '2018-11-12', 12, 23, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (31, '15427746142865cOWU00', 10000, '10002', 2, 'b6gVMSeYshBATWKsHwXUFbo8yCLtG1', '11034+11035', '2018-11-21', 13, 17, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (32, '15427790576044LUIi00', 10000, '10000', 2, 'o7gobEGHNg59PtJaVUQQIupJuEUUsk', '10000', '2018-11-21', 18, 20, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (33, '1542780654701Gg1oF00', 10000, '10003', 2, '2duC931FOyl1ILhSIZBoLSswstn8zV', '11009', '2018-11-21', 14, 16, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (34, '1542780725967qYC7100', 10000, '10003', 2, 'MBiui7icXqY6UnxPC5yKwWdGhwwGEv', '11017', '2018-11-21', 13, 15, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (35, '1542785607855IKSBd00', 10000, '10003', 2, 'p2zkTIN6fXqmAjp5e9nyF9dlTqOYGg', '11011+11012+11013+11014', '2018-11-21', 15, 17, 4, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (36, '15427969109046fcGp00', 10000, '10003', 2, 'bjiELqjdqH0sxVoVb3fYbsGSG8ucMq', '11004+11003', '2018-11-21', 18, 23, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (37, '1542850224882dR3IH00', 10000, '10003', 3, '5w6QlZ9FhFuWULwDEfRvOB2OUfmrkj', '11027+11028', '2018-11-22', 9, 23, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (38, '1542868020547FbYBU00', 10000, '10003', 3, 'rgrOjiOJ6Un9ukUlaa4P6qR1x6Es7U', '11011+11012+11013', '2018-11-22', 14, 23, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (39, '15428697762189zQV500', 10000, '10003', 2, '3O5gZacUBltEgWAMDdejUJjKk6iVlv', '11033+11034', '2018-11-22', 13, 16, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (40, '1542871587462wJxk900', 10000, '10000', 2, 'DLxJqSDfVfbhpf55SgnXYklqkOM30r', '11056+11057', '2018-11-22', 14, 19, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (41, '1542885543550wGqSY00', 10000, '10003', 2, 'zcSZC687L52Q3W9jHh9ZCZuEZfKATO', '11008+11009', '2018-11-22', 19, 23, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (42, '1542896633425glM8r00', 10000, '10003', 2, 'tPF44E2uuwaVWZhszLxlvAdjL31zAU', '11016+11017', '2018-11-22', 22, 23, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (43, '15429383723286w8lx00', 10000, '10000', 2, 'In5gefk7nisWlnYIIB3eLO3zbpk0OW', '11008+11009', '2018-11-23', 8, 13, 2, 1, 0, 0, 1, NULL);
INSERT INTO `order_info` VALUES (44, '1542938940754AHEON00', 10000, '10003', 2, 'DADQiJljcXB8VDlX7okoCpv6CT8ZD1', '11023', '2018-11-23', 10, 11, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (45, '1542939633689eHsTF00', 10000, '10003', 2, 'bCffq8nSvaJKTH5cTnPkBsC24kPMvh', '11015+11016', '2018-11-23', 10, 23, 2, 1, 0, 3, 0, NULL);
INSERT INTO `order_info` VALUES (46, '1542940056910todTM00', 10000, '10003', 2, 'edDGxTHonStFLjczY2XJIvLQQCp97A', '11003+11004+11005', '2018-11-23', 10, 21, 3, 1, 0, 1, 1, NULL);
INSERT INTO `order_info` VALUES (47, '1542941723879vxm5H00', 10000, '10003', 2, 'czUtY2hDSyO7oe6hHe5ya9JbFEYwsF', '11034+11035', '2018-11-23', 10, 14, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (48, '1543114631739fYY7O00', 10000, '10003', 2, 'wu2NJjWHEnujoorgUJaeRbilFRxksh', '11016', '2018-05-15', 13, 14, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (49, '1543114908734I4bU800', 10000, '10003', 3, 'AoOQr7NBniekerZKX05FFy0xVVJYDC', '11015', '2018-11-25', 11, 16, 1, 1, 0, 0, 1, NULL);
INSERT INTO `order_info` VALUES (50, '1543115317956crhXa00', 10000, '10000', 2, 'vdG83lFyQpBiJAaBlK45fFHCnGHCqM', '11017+11018', '2018-11-25', 8, 11, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (51, '1543115377033yPhAu00', 10000, '10000', 2, 'paXXcuqe7C3MjiJQfQNX0AK3tyKTLU', '11018+11017', '2018-11-25', 10, 21, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (52, '15431160377267bsG300', 10000, '10003', 3, 'An0HuxQCvR09idtcDNytAN06hHRMSJ', '11009', '2018-11-25', 11, 17, 1, 1, 0, 1, 1, NULL);
INSERT INTO `order_info` VALUES (53, '1543116303724ZAYiG00', 10000, '10003', 3, '45NwOYsl3YnjIF8qOJZlOb7dPjJgi5', '11034', '2018-11-25', 11, 15, 1, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (54, '15431164390454KRWG00', 10000, '10003', 3, 'SCnrJ69jyaJDnHp7vBKmWyOaI5vbGd', '11012', '2018-11-25', 11, 12, 1, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (55, '1543117083893VZemy00', 10000, '10003', 2, 'pFChkyXMpM3b0rzthrYximgaZRbarQ', '11027+11028', '2018-11-25', 11, 13, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (56, '1543132133107zkvLq00', 10000, '10003', 3, '1cLqvcKkROrfKWpR9IVjVYSZQFgpEW', '11005', '2018-11-25', 15, 23, 1, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (57, '1543132351080QpsE200', 10000, '10003', 2, 'ARWpjAevPD5Y1JfU2i068iDNqBXAS9', '11008', '2018-11-25', 15, 23, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (58, '1543132403138nxGwQ00', 10000, '10003', 3, 'BFdfbvcPIcaBTEaBqSMY59NuhDeiVX', '11010', '2018-11-25', 15, 17, 1, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (59, '1543196528289PpDim00', 10000, '10000', 3, 'pJDib8rYld7rjDWScUSjYYuZT4OLaf', '11013+11014', '2018-11-26', 8, 10, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (60, '1543197459001kseT200', 10000, '10000', 3, 'pyyffYilWHA89c7KwD4o4swyFpUJY7', '11042+11055', '2018-11-26', 9, 13, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (61, '15431974910943QjJD00', 10000, '10000', 3, 'I017eICwnQ7TgQYHi52WbeASvAsGh2', '11015+11016', '2018-11-26', 9, 12, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (62, '1543222536228F4xmK00', 10000, '10000', 3, 'Xdv1wW3NQuWQMQEn1IRJyOaARGB3DV', '11035+11036', '2018-11-26', 15, 23, 2, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (63, '1543238703622n8QrB00', 10000, '10000', 2, 'ZBQ5XoPtTSHklxUfTrScylexAr0KUD', '11016+11015', '2018-11-26', 13, 23, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (64, '1543244773256XwdLv00', 10000, '10000', 2, 'tRK4TdNCy801HcO3mXPnFlYas1lmEj', '11027+11028+11029+11013+11014', '2018-11-27', 4, 5, 5, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (65, '1543285345298VWS1S00', 10000, '10000', 3, '9MYsv9Q0MC7vh3UmBm1Bs42cewVeAd', '11003+11005+11004', '2018-11-27', 8, 13, 3, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (66, '1543290761023vUlra00', 10000, '10000', 2, 'vag25IgwHiyyp6n3rD3LmXcRulWV0m', '11003+11004+11005', '2018-11-27', 11, 13, 3, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (67, '1543324558830XmxPT00', 10000, '10000', 2, 'v9mmwxuEEDvjMpPd3UsZv7nrFkgzho', '11004+11003', '2018-11-27', 13, 22, 2, 1, 0, 0, 1, NULL);
INSERT INTO `order_info` VALUES (68, '1543374564504oRUSP00', 10000, '10000', 2, '0dKTc5IdiXGuc2S8BFXSrR6wOqvOVC', '11003+11004+11005', '2018-11-28', 8, 14, 3, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (69, '1543374721167Aud2x00', 10000, '10000', 3, 'OQka7B634KEen2hzbRU5Q7FyyXtUQ7', '11008+11009+11010', '2018-11-28', 9, 17, 3, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (70, '15438457342141u89K00', 10000, '10000', 3, 'a4PTVHDorjTJxAoWqW0WssIrvOaRXX', '11003+11004', '2018-12-03', 21, 23, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (71, '1543910524717gmXqI00', 10000, '10000', 3, 'WjCVI18ksmcV9aBb0KNDSJYyyWbxEL', '11003+11004', '2018-12-04', 14, 19, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (72, '154407932125699HRd00', 10000, '10003', 2, 'sLbaKk48woudEAhzevGDNyPvsTIB5o', '11004+11005', '2018-12-06', 15, 16, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (73, '1544089475406PkFag00', 10000, '10003', 2, 'Kb4rASPvWa5kSNYvG7QReAunlqVFjV', '11014+11013', '2018-12-06', 17, 23, 2, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (74, '1544089689865iqwUx00', 10000, '10003', 2, 't3JucAdUKr0ZifYes8ArzmjtY4XDoj', '11018', '2018-12-06', 18, 23, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (75, '1544089794966kL1vt00', 10000, '10003', 2, '8rFdP87h6x8R5GwEpXP8DFX6c5erPh', '11031', '2018-12-06', 18, 23, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (76, '1544762920443zWxvB00', 10000, '10002', 2, 'qCfd0DdyyrZufDqr9LdabS8JMmzEhX', '11009+11010+11008+11014', '2018-10-10', 1, 2, 4, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (77, '1544791970308gm2Rk00', 10000, '10003', 2, 'WffPS3h7iZ6OKxVdCOax2UoJr8zJmq', '11023', '2018-12-14', 20, 21, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (78, '1544803728066mcDIR00', 10000, '10003', 2, 'n1aHBC0PO5FJeIZvVcVT68HhM85joo', '11004', '2018-12-15', 0, 1, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (79, '1545126346383uGqTw00', 10000, '10003', 2, 'qQRlQQcN4bfIKH3FUkfSV4CBJKb99X', '11012', '2018-12-18', 17, 18, 1, 0, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (80, '1545138514045lKK8300', 10000, '10000', 3, '2lZVFUbzIkkuv2xhQz3RvVS1abx9eL', '10000', '2018-12-18', 20, 22, 1, 1, 0, 1, 0, NULL);
INSERT INTO `order_info` VALUES (81, '1545138966867DRhHz00', 10000, '10000', 2, 'KFvk4NCgX46FqEE6JcrdoRMgMObK8y', '11008+11009', '2018-12-18', 11, 23, 2, 1, 0, 0, 0, NULL);
INSERT INTO `order_info` VALUES (82, '1547296652122mJkTF00', 10000, '10006', 3, 'yKM4G3q4PDnBlnYDdL6NAcbrFlhmN5', '11004+11003', '2019-01-12', 20, 23, 2, 1, 0, 1, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_renewal_info
-- ----------------------------
DROP TABLE IF EXISTS `order_renewal_info`;
CREATE TABLE `order_renewal_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单续时记录id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `scheduled_day` date DEFAULT NULL COMMENT '预定日期',
  `end_hour` tinyint(4) DEFAULT NULL COMMENT '续时结束小时',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='无人吧订单续时记录';

-- ----------------------------
-- Table structure for pad_info
-- ----------------------------
DROP TABLE IF EXISTS `pad_info`;
CREATE TABLE `pad_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(20) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `seat_id` bigint(20) DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) DEFAULT NULL,
  `bar_id` bigint(20) DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40002 DEFAULT CHARSET=latin1 COMMENT='无人吧设备管理pad';

-- ----------------------------
-- Records of pad_info
-- ----------------------------
BEGIN;
INSERT INTO `pad_info` VALUES (40001, '10.108.122.240', '2018-12-14 10:46:23', 11003, '2018-12-03 00:32:00', 'APPLE', 10000);
COMMIT;

-- ----------------------------
-- Table structure for power_source_info
-- ----------------------------
DROP TABLE IF EXISTS `power_source_info`;
CREATE TABLE `power_source_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(20) DEFAULT NULL,
  `create_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hardware_id` bigint(20) DEFAULT NULL,
  `seat_id` bigint(20) DEFAULT NULL,
  `use_at` timestamp NOT NULL,
  `producer` char(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT 'pad电源/插座',
  `bar_id` bigint(20) DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40003 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of power_source_info
-- ----------------------------
BEGIN;
INSERT INTO `power_source_info` VALUES (40001, '192.168.4.1', '2018-11-22 11:20:23', 232210, 11004, '2018-11-07 13:00:10', 'XIAOMI', 1, 10000);
INSERT INTO `power_source_info` VALUES (40002, '192.168.4.2', '2018-11-01 00:12:00', 3213122, 11005, '2018-11-03 14:32:00', 'DAJIANG', 1, 10000);
COMMIT;

-- ----------------------------
-- Table structure for seat_info
-- ----------------------------
DROP TABLE IF EXISTS `seat_info`;
CREATE TABLE `seat_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `position_x` int(11) DEFAULT NULL,
  `position_y` int(11) DEFAULT NULL,
  `table_id` bigint(20) unsigned DEFAULT NULL,
  `ipAddress` char(20) DEFAULT NULL,
  `hardwareId` bigint(20) unsigned DEFAULT NULL,
  `location` char(20) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) DEFAULT NULL,
  `bar_id` bigint(20) DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11069 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of seat_info
-- ----------------------------
BEGIN;
INSERT INTO `seat_info` VALUES (11004, 2, 1, 10000, '192.168.0.29', 23242327, '1A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11005, 3, 1, 10000, '192.168.0.30', 23242328, '1A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11006, 0, 3, 10000, '192.168.0.31', 23242329, '1A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11007, 4, 3, 10000, '192.168.0.32', 23242330, '1A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11008, 1, 5, 10000, '192.168.0.33', 23242331, '1A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11009, 2, 5, 10000, '192.168.0.34', 23242332, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11010, 3, 5, 10000, '192.168.0.35', 23242333, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11011, 1, 8, 10001, '192.168.0.36', 23242334, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11012, 2, 8, 10001, '192.168.0.37', 23242335, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11003, 1, 1, 10000, '192.168.0.38', 23242336, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11013, 3, 8, 10001, '192.168.0.39', 23242337, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11014, 4, 8, 10001, '192.168.0.40', 23242338, '1B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11015, 1, 12, 10001, '192.168.0.41', 23242339, '1C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11016, 2, 12, 10001, '192.168.0.42', 23242340, '1C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11017, 3, 12, 10001, '192.168.0.43', 23242341, '1C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11018, 4, 12, 10001, '192.168.0.44', 23242342, '2A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11019, 2, 17, 10002, '192.168.0.45', 23242343, '2A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11020, 0, 19, 10002, '192.168.0.46', 23242344, '2A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11021, 4, 19, 10002, '192.168.0.47', 23242345, '2A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11022, 2, 21, 10002, '192.168.0.48', 23242346, '2A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11023, 7, 1, 10003, '192.168.0.49', 23242347, '2C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11024, 8, 1, 10003, '192.168.0.50', 23242348, '2C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11025, 9, 1, 10003, '192.168.0.51', 23242349, '2C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11026, 10, 1, 10003, '192.168.0.52', 23242350, '2C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11027, 7, 4, 10003, '192.168.0.53', 23242326, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11028, 8, 4, 10003, '192.168.0.54', 23242325, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11029, 9, 4, 10003, '192.168.0.55', 23242324, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11030, 10, 4, 10003, '192.168.0.56', 23242323, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11031, 8, 7, 10004, '192.168.0.57', 23242322, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11032, 6, 8, 10004, '192.168.0.28', 23242321, '3A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11033, 6, 10, 10004, '192.168.0.27', 23242320, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11034, 6, 12, 10004, '192.168.0.26', 23242319, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11035, 6, 14, 10004, '192.168.0.25', 23242318, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11036, 6, 16, 10004, '192.168.0.24', 23242317, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11037, 6, 18, 10004, '192.168.0.23', 23242316, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11038, 6, 20, 10004, '192.168.0.22', 23242315, '3B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11039, 6, 22, 10004, '192.168.0.21', 23242314, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11040, 10, 8, 10004, '192.168.0.20', 23242313, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11041, 10, 10, 10004, '192.168.0.19', 23242312, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11042, 10, 12, 10004, '192.168.0.18', 23242311, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11043, 10, 14, 10004, '192.168.0.17', 2324239, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11044, 10, 16, 10004, '192.168.0.16', 2324238, '3C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11045, 10, 18, 10004, '192.168.0.15', 2324237, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11046, 10, 20, 10004, '192.168.0.14', 2324236, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11047, 10, 22, 10004, '192.168.0.13', 2324235, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11048, 8, 23, 10004, '192.168.0.12', 2324233, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11049, 11, 2, 10005, '192.168.0.11', 2324232, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11050, 11, 4, 10005, '192.168.0.10', 2324231, '4A', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11051, 11, 6, 10005, '192.168.0.9', 232429, '4B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11052, 14, 2, 10005, '192.168.0.8', 232428, '4B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11053, 14, 4, 10005, '192.168.0.7', 232427, '4B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11054, 14, 6, 10005, '192.168.0.6', 232426, '4B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11055, 11, 12, 10006, '192.168.0.5', 232425, '4B', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11056, 14, 12, 10006, '192.168.0.4', 232424, '4C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11057, 13, 17, 10007, '192.168.0.3', 232423, '4C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
INSERT INTO `seat_info` VALUES (11058, 13, 21, 10007, '192.168.0.2', 4231232, '4C', '2018-05-04 21:34:29', '2018-05-18 12:34:29', 'GOOGLE', 10000);
COMMIT;

-- ----------------------------
-- Table structure for table_info
-- ----------------------------
DROP TABLE IF EXISTS `table_info`;
CREATE TABLE `table_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `left_up_x_coordinate` int(11) DEFAULT NULL,
  `right_down_y_coordinate` int(11) DEFAULT NULL,
  `left_up_y_coordinate` int(11) DEFAULT NULL,
  `right_down_x_coordinate` int(11) DEFAULT NULL,
  `location` char(20) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `use_at` timestamp NULL DEFAULT NULL,
  `producer` char(100) DEFAULT NULL,
  `bar_id` bigint(20) DEFAULT '10000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10011 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of table_info
-- ----------------------------
BEGIN;
INSERT INTO `table_info` VALUES (10000, 1, 4, 2, 3, '1A', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10001, 1, 11, 9, 4, '1B', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10002, 1, 20, 18, 3, '1C', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10003, 7, 3, 2, 10, '2A', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10004, 7, 22, 8, 9, '2C', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10005, 12, 7, 1, 13, '3A', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10006, 12, 13, 11, 13, '3B', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
INSERT INTO `table_info` VALUES (10007, 12, 20, 18, 14, '3C', '2018-05-09 17:33:29', '2018-05-14 13:12:29', 'TENCENT', 10000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
