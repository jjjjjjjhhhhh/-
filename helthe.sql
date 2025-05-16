/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.25 : Database - carbroom
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`carbroom` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `carbroom`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) NOT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) DEFAULT NULL,
  `type` int NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int DEFAULT NULL COMMENT '排序',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `statu` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`name`,`path`,`perms`,`component`,`type`,`icon`,`orderNum`,`created`,`updated`,`statu`) values 
(1,0,'系统管理','','sys:manage','',0,'el-icon-s-operation',1,'2021-01-15 18:58:18','2021-01-15 18:58:20',1),
(2,1,'用户管理','/sys/users','sys:user:list','sys/User',1,'el-icon-s-custom',1,'2021-01-15 19:03:45','2021-01-15 19:03:48',1),
(6,5,'数字字典','/sys/dicts','sys:dict:list','sys/Dict',1,'el-icon-s-order',1,'2021-01-15 19:07:18','2021-01-18 16:32:13',1),
(7,3,'添加角色','','sys:role:save','',2,'',1,'2021-01-15 23:02:25','2021-01-17 21:53:14',0),
(9,2,'添加用户',NULL,'sys:user:save',NULL,2,NULL,1,'2021-01-17 21:48:32',NULL,1),
(10,2,'修改用户',NULL,'sys:user:update',NULL,2,NULL,2,'2021-01-17 21:49:03','2021-01-17 21:53:04',1),
(11,2,'删除用户',NULL,'sys:user:delete',NULL,2,NULL,3,'2021-01-17 21:49:21',NULL,1),
(12,2,'分配角色',NULL,'sys:user:role',NULL,2,NULL,4,'2021-01-17 21:49:58',NULL,1),
(13,2,'重置密码',NULL,'sys:user:repass',NULL,2,NULL,5,'2021-01-17 21:50:36',NULL,1),
(14,3,'修改角色',NULL,'sys:role:update',NULL,2,NULL,2,'2021-01-17 21:51:14',NULL,1),
(15,3,'删除角色',NULL,'sys:role:delete',NULL,2,NULL,3,'2021-01-17 21:51:39',NULL,1),
(16,3,'分配权限',NULL,'sys:role:perm',NULL,2,NULL,5,'2021-01-17 21:52:02',NULL,1),
(17,4,'添加菜单',NULL,'sys:menu:save',NULL,2,NULL,1,'2021-01-17 21:53:53','2021-01-17 21:55:28',1),
(18,4,'修改菜单',NULL,'sys:menu:update',NULL,2,NULL,2,'2021-01-17 21:56:12',NULL,1),
(19,4,'删除菜单',NULL,'sys:menu:delete',NULL,2,NULL,3,'2021-01-17 21:56:36',NULL,1),
(33,0,'个人中心',NULL,'sys:center',NULL,0,'el-icon-s-operation',1,'2024-03-14 10:03:38',NULL,1),
(34,33,'个人信息','/sys/center','sys:center:list','center/center',2,'el-icon-s-operation',1,'2024-03-14 10:18:04',NULL,1),
(35,0,'健康监测与提示',NULL,'sys:helthe',NULL,0,'el-icon-s-operation',1,'2025-04-11 16:59:51','2025-04-11 16:59:53',1),
(52,35,'健康录入','/sys/jklr','sys:helthe:jklr','helthe/jklr',2,'el-icon-s-operation',1,'2025-04-11 17:03:31','2025-04-11 17:03:33',1),
(53,35,'健康检测','/sys/jkjc','sys:helthe:jkjc','helthe/jkjc',2,'el-icon-s-operation',1,'2025-04-11 17:05:06','2025-04-11 17:05:07',1),
(54,35,'健康提醒','/sys/jktx','sys:helthe:jktx','helthe/jktx',2,'el-icon-s-operation',1,'2025-04-11 17:05:54','2025-04-11 17:05:56',1),
(55,0,'健康计划与执行',NULL,'sys:plan',NULL,0,NULL,1,'2025-04-11 17:07:15','2025-04-11 17:07:18',1),
(56,55,'健康计划','/sys/jkjh','sys:plan:jkjh','plan/jkjh',2,NULL,1,'2025-04-11 17:08:13','2025-04-11 17:08:14',1),
(57,55,'健康计划执行情况','/sys/jkzx','sys:plan:jkzx','plan/jkzx',2,NULL,1,'2025-04-11 17:09:30','2025-04-11 17:09:31',1),
(58,0,'饮食管理',NULL,'sys:ysgl','',0,NULL,1,'2025-04-11 17:10:18','2025-04-11 17:10:20',1),
(60,58,'饮食','/sys/ysgl','sys:ysgl:ysgl','ysgl/ysgl',2,NULL,1,'2025-04-11 17:11:17','2025-04-11 17:11:18',1),
(61,0,'远程问诊',NULL,'sys:ycwz',NULL,0,NULL,1,'2025-04-11 17:16:22','2025-04-11 17:16:24',1),
(62,61,'在线问诊','/sys/zxwz','sys:ycwz:zxwz','ycwz/zxwz',2,NULL,1,'2025-04-11 17:17:23','2025-04-11 17:17:25',1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `statu` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`,`remark`,`created`,`updated`,`statu`) values 
(3,'普通用户','normal','只有基本查看功能','2021-01-04 10:09:14','2021-01-30 08:19:52',1),
(6,'超级管理员','admin','系统默认最高权限，不可以编辑和任意修改','2021-01-16 13:29:03','2021-01-17 15:50:45',1);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(60,7,1),
(61,7,2),
(62,6,9),
(63,6,10),
(64,6,11),
(65,6,12),
(66,6,13),
(67,6,3),
(68,6,7),
(69,6,14),
(70,6,15),
(71,6,16),
(72,6,4),
(73,6,17),
(74,6,18),
(75,6,19),
(76,6,5),
(77,6,6),
(96,3,1),
(97,3,2),
(98,3,3),
(99,3,4),
(100,3,5),
(101,3,6),
(102,4,20),
(103,4,21),
(104,4,22),
(105,4,23),
(106,4,24),
(107,6,25),
(108,5,26),
(109,5,27),
(110,5,28),
(111,5,29),
(112,5,30),
(113,6,31),
(114,4,33),
(115,4,34),
(116,4,35),
(117,4,40),
(118,4,41),
(119,4,42),
(120,4,43),
(121,4,44),
(122,4,45),
(123,4,46),
(124,4,47);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '888888',
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `statu` int NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`avatar`,`email`,`city`,`created`,`updated`,`last_login`,`statu`,`phone`) values 
(1,'admin1','$2a$10$egE9we/TbDtgoD2/hZpROuWwNdshYuWkD8z8nU9Z696O6yknf1aLy','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg','123@qq.com','大连','2023-05-01 22:13:53','2023-05-01 16:57:32','2022-11-30 08:38:37',1,'1734980'),
(3,'sysadmin','$2a$10$egE9we/TbDtgoD2/hZpROuWwNdshYuWkD8z8nU9Z696O6yknf1aLy','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg','zh@qq.com','大连','2023-05-17 20:20:17','2023-03-19 20:20:27','2023-03-19 20:20:30',1,'1734980'),
(15,'214','888888',NULL,'2414',NULL,'2023-05-14 16:57:41',NULL,NULL,1,'412'),
(19,'asfsa','888888',NULL,'fas',NULL,NULL,NULL,NULL,1,'sfas');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 
(4,1,4),
(14,2,5),
(15,3,7),
(21,1,3);

/*Table structure for table `sys_user_sta` */

DROP TABLE IF EXISTS `sys_user_sta`;

CREATE TABLE `sys_user_sta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_user_sta` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
