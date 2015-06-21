/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.0.96-community-nt : Database - phone_bao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`phone_bao` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `phone_bao`;

/*Table structure for table `dm_camera` */

DROP TABLE IF EXISTS `dm_camera`;

CREATE TABLE `dm_camera` (
  `camera_id` tinyint(3) unsigned NOT NULL auto_increment,
  `camera` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`camera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_camera` */

insert  into `dm_camera`(`camera_id`,`camera`,`prepared1`,`prepared2`,`prepared3`) values (1,'800万像素',NULL,NULL,NULL),(2,'500万像素',NULL,NULL,NULL),(3,'1200万像素',NULL,NULL,NULL),(4,'1300万像素',NULL,NULL,NULL),(5,'1600万像素',NULL,NULL,NULL),(6,'2100万像素',NULL,NULL,NULL),(7,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_city` */

DROP TABLE IF EXISTS `dm_city`;

CREATE TABLE `dm_city` (
  `city_id` tinyint(3) unsigned NOT NULL auto_increment,
  `city` varchar(20) NOT NULL,
  `province_id` tinyint(3) unsigned default NULL,
  `city_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`city_id`),
  KEY `dm_city_fk_province_id` (`province_id`),
  CONSTRAINT `dm_city_fk_province_id` FOREIGN KEY (`province_id`) REFERENCES `dm_province` (`province_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_city` */

insert  into `dm_city`(`city_id`,`city`,`province_id`,`city_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'武汉市',1,NULL,1,NULL,NULL,NULL),(2,'深圳市',2,NULL,1,NULL,NULL,NULL);

/*Table structure for table `dm_communication` */

DROP TABLE IF EXISTS `dm_communication`;

CREATE TABLE `dm_communication` (
  `communication_id` tinyint(3) unsigned NOT NULL auto_increment,
  `communication` varchar(30) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`communication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_communication` */

insert  into `dm_communication`(`communication_id`,`communication`,`prepared1`,`prepared2`,`prepared3`) values (1,'移动TD-LTE',NULL,NULL,NULL),(2,'联通TD-LTE',NULL,NULL,NULL),(3,'联通TD-LTE 移动TD-LTE',NULL,NULL,NULL),(4,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_complain_reason` */

DROP TABLE IF EXISTS `dm_complain_reason`;

CREATE TABLE `dm_complain_reason` (
  `complain_reason_id` tinyint(3) unsigned NOT NULL auto_increment,
  `complain_reason` varchar(50) NOT NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`complain_reason_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_complain_reason` */

insert  into `dm_complain_reason`(`complain_reason_id`,`complain_reason`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'出售假货',1,NULL,NULL,NULL),(2,'没有发货',1,NULL,NULL,NULL);

/*Table structure for table `dm_complain_result` */

DROP TABLE IF EXISTS `dm_complain_result`;

CREATE TABLE `dm_complain_result` (
  `complain_result_id` tinyint(3) unsigned NOT NULL auto_increment,
  `complain_result` varchar(50) NOT NULL,
  `camplain_result_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`complain_result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_complain_result` */

insert  into `dm_complain_result`(`complain_result_id`,`complain_result`,`camplain_result_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'投诉无效',NULL,1,NULL,NULL,NULL),(2,'投诉成功',NULL,1,NULL,NULL,NULL);

/*Table structure for table `dm_county` */

DROP TABLE IF EXISTS `dm_county`;

CREATE TABLE `dm_county` (
  `county_id` tinyint(3) unsigned NOT NULL auto_increment,
  `county` varchar(20) NOT NULL,
  `city_id` tinyint(3) unsigned default NULL,
  `county_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`county_id`),
  KEY `dm_county_fk_city_id` (`city_id`),
  CONSTRAINT `dm_county_fk_city_id` FOREIGN KEY (`city_id`) REFERENCES `dm_city` (`city_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_county` */

insert  into `dm_county`(`county_id`,`county`,`city_id`,`county_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'武昌区',1,NULL,1,NULL,NULL,NULL),(2,'汉口区',1,NULL,1,NULL,NULL,NULL),(3,'福田区',2,NULL,1,NULL,NULL,NULL);

/*Table structure for table `dm_cpu` */

DROP TABLE IF EXISTS `dm_cpu`;

CREATE TABLE `dm_cpu` (
  `cpu_id` tinyint(3) unsigned NOT NULL auto_increment,
  `cpu` varchar(30) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`cpu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_cpu` */

insert  into `dm_cpu`(`cpu_id`,`cpu`,`prepared1`,`prepared2`,`prepared3`) values (1,'64位苹果A8处理器',NULL,NULL,NULL),(2,'32位苹果A7处理器',NULL,NULL,NULL),(3,'三星 Exynos 7420',NULL,NULL,NULL),(4,'海思麒麟 935',NULL,NULL,NULL),(5,'海思 Kirin 925',NULL,NULL,NULL),(6,'高通 骁龙410',NULL,NULL,NULL),(7,'苹果 A8+M8协处理器',NULL,NULL,NULL),(8,'苹果 A7/M7协处理器',NULL,NULL,NULL),(9,'iOS 9',NULL,NULL,NULL),(11,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_deal_state` */

DROP TABLE IF EXISTS `dm_deal_state`;

CREATE TABLE `dm_deal_state` (
  `deal_state_id` tinyint(3) unsigned NOT NULL auto_increment,
  `deal_state` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`deal_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_deal_state` */

insert  into `dm_deal_state`(`deal_state_id`,`deal_state`,`prepared1`,`prepared2`,`prepared3`) values (1,'未交易',NULL,NULL,NULL),(2,'交易失败',NULL,NULL,NULL),(3,'交易成功',NULL,NULL,NULL),(4,'等待商家发货',NULL,NULL,NULL);

/*Table structure for table `dm_goods_state` */

DROP TABLE IF EXISTS `dm_goods_state`;

CREATE TABLE `dm_goods_state` (
  `goods_state_id` tinyint(3) unsigned NOT NULL auto_increment,
  `goods_state` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`goods_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_goods_state` */

insert  into `dm_goods_state`(`goods_state_id`,`goods_state`,`prepared1`,`prepared2`,`prepared3`) values (1,'可出售状态',NULL,NULL,NULL),(2,'不可出售状态',NULL,NULL,NULL);

/*Table structure for table `dm_operatingsystem` */

DROP TABLE IF EXISTS `dm_operatingsystem`;

CREATE TABLE `dm_operatingsystem` (
  `os_id` tinyint(3) unsigned NOT NULL auto_increment,
  `operatingsystem` varchar(30) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`os_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_operatingsystem` */

insert  into `dm_operatingsystem`(`os_id`,`operatingsystem`,`prepared1`,`prepared2`,`prepared3`) values (1,'IOS8',NULL,NULL,NULL),(2,'Android5.0',NULL,NULL,NULL),(3,'IOS7.0',NULL,NULL,NULL),(4,'IOS8.0',NULL,NULL,NULL),(5,'IOS9.0',NULL,NULL,NULL),(6,'IOS10',NULL,NULL,NULL),(7,'Android OS 4',NULL,NULL,NULL),(8,'EMUI 3.0',NULL,NULL,NULL),(9,'IOS8.0',NULL,NULL,NULL),(10,'Android OS 5',NULL,NULL,NULL),(11,'Android OS 4.3',NULL,NULL,NULL),(12,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_phonebrand` */

DROP TABLE IF EXISTS `dm_phonebrand`;

CREATE TABLE `dm_phonebrand` (
  `phonebrand_id` tinyint(3) unsigned NOT NULL auto_increment,
  `phonebrand` varchar(20) NOT NULL,
  `phonebrand_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`phonebrand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_phonebrand` */

insert  into `dm_phonebrand`(`phonebrand_id`,`phonebrand`,`phonebrand_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'Iphone',NULL,1,NULL,NULL,NULL),(2,'三星',NULL,1,NULL,NULL,NULL),(3,'HTC',NULL,1,NULL,NULL,NULL),(4,'华为',NULL,1,NULL,NULL,NULL),(5,'诺基亚',NULL,1,NULL,NULL,NULL),(6,'小米',NULL,1,NULL,NULL,NULL),(7,'酷派',NULL,1,NULL,NULL,NULL),(8,'中兴',NULL,1,NULL,NULL,NULL),(9,'OPPO',NULL,1,NULL,NULL,NULL),(10,'vivo',NULL,1,NULL,NULL,NULL),(11,'大神',NULL,1,NULL,NULL,NULL),(12,'其他',NULL,1,NULL,NULL,NULL),(30,'宁敦泉',NULL,1,NULL,NULL,NULL);

/*Table structure for table `dm_phonecolor` */

DROP TABLE IF EXISTS `dm_phonecolor`;

CREATE TABLE `dm_phonecolor` (
  `phonecolor_id` tinyint(3) unsigned NOT NULL auto_increment,
  `phonecolor` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`phonecolor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_phonecolor` */

insert  into `dm_phonecolor`(`phonecolor_id`,`phonecolor`,`prepared1`,`prepared2`,`prepared3`) values (1,'黑色',NULL,NULL,NULL),(2,'金色',NULL,NULL,NULL),(5,'白色',NULL,NULL,NULL),(6,'粉色',NULL,NULL,NULL),(7,'银灰色',NULL,NULL,NULL),(8,'咖啡色',NULL,NULL,NULL),(9,'红色',NULL,NULL,NULL),(10,'蓝色',NULL,NULL,NULL),(11,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_phonesize` */

DROP TABLE IF EXISTS `dm_phonesize`;

CREATE TABLE `dm_phonesize` (
  `phonesize_id` tinyint(3) unsigned NOT NULL auto_increment,
  `phonesize` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`phonesize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_phonesize` */

insert  into `dm_phonesize`(`phonesize_id`,`phonesize`,`prepared1`,`prepared2`,`prepared3`) values (1,'4.7寸',NULL,NULL,NULL),(2,'4.3寸',NULL,NULL,NULL),(3,'6.5寸',NULL,NULL,NULL),(4,'6.4寸',NULL,NULL,NULL),(5,'6.0寸',NULL,NULL,NULL),(6,'5.8寸',NULL,NULL,NULL),(7,'5.4寸',NULL,NULL,NULL),(8,'5.0寸',NULL,NULL,NULL),(9,'4.0寸',NULL,NULL,NULL),(10,'3.5寸',NULL,NULL,NULL),(11,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_pre_camera` */

DROP TABLE IF EXISTS `dm_pre_camera`;

CREATE TABLE `dm_pre_camera` (
  `pre_camera_id` tinyint(3) unsigned NOT NULL auto_increment,
  `pre_camera` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`pre_camera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_pre_camera` */

insert  into `dm_pre_camera`(`pre_camera_id`,`pre_camera`,`prepared1`,`prepared2`,`prepared3`) values (1,'300万像素',NULL,NULL,NULL),(2,'120万像素',NULL,NULL,NULL),(3,'100万像素',NULL,NULL,NULL),(4,'80万像素',NULL,NULL,NULL),(5,'30万像素',NULL,NULL,NULL),(6,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_province` */

DROP TABLE IF EXISTS `dm_province`;

CREATE TABLE `dm_province` (
  `province_id` tinyint(3) unsigned NOT NULL auto_increment,
  `province` varchar(20) NOT NULL,
  `province_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_province` */

insert  into `dm_province`(`province_id`,`province`,`province_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'湖北省',NULL,1,NULL,NULL,NULL),(2,'广东省',NULL,1,NULL,NULL,NULL);

/*Table structure for table `dm_ram` */

DROP TABLE IF EXISTS `dm_ram`;

CREATE TABLE `dm_ram` (
  `ram_id` tinyint(3) unsigned NOT NULL auto_increment,
  `ram` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`ram_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_ram` */

insert  into `dm_ram`(`ram_id`,`ram`,`prepared1`,`prepared2`,`prepared3`) values (1,'1G',NULL,NULL,NULL),(2,'1.6G',NULL,NULL,NULL),(4,'1.4G',NULL,NULL,NULL),(5,'1.6G',NULL,NULL,NULL),(6,'1.8G',NULL,NULL,NULL),(7,'2.0G',NULL,NULL,NULL),(8,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_resolution` */

DROP TABLE IF EXISTS `dm_resolution`;

CREATE TABLE `dm_resolution` (
  `resolution_id` tinyint(3) unsigned NOT NULL auto_increment,
  `resolution` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`resolution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_resolution` */

insert  into `dm_resolution`(`resolution_id`,`resolution`,`prepared1`,`prepared2`,`prepared3`) values (1,'1334x750像素',NULL,NULL,NULL),(2,'1288x720像素',NULL,NULL,NULL),(3,'2560x1440像素',NULL,NULL,NULL),(6,'800x480像素',NULL,NULL,NULL),(7,'1920x1080像素',NULL,NULL,NULL),(8,'1280x768像素',NULL,NULL,NULL),(9,'1136x640像素',NULL,NULL,NULL),(10,'960x640像素',NULL,NULL,NULL),(11,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_rom` */

DROP TABLE IF EXISTS `dm_rom`;

CREATE TABLE `dm_rom` (
  `rom_id` tinyint(3) unsigned NOT NULL auto_increment,
  `rom` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`rom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_rom` */

insert  into `dm_rom`(`rom_id`,`rom`,`prepared1`,`prepared2`,`prepared3`) values (1,'16G',NULL,NULL,NULL),(2,'32G',NULL,NULL,NULL),(3,'8G',NULL,NULL,NULL),(5,'18G',NULL,NULL,NULL),(7,'64G',NULL,NULL,NULL),(8,'128G',NULL,NULL,NULL),(9,'256G',NULL,NULL,NULL),(10,'其他',NULL,NULL,NULL);

/*Table structure for table `dm_shop_audit_state` */

DROP TABLE IF EXISTS `dm_shop_audit_state`;

CREATE TABLE `dm_shop_audit_state` (
  `shop_audit_state_id` tinyint(3) unsigned NOT NULL auto_increment,
  `shop_audit_state` varchar(50) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`shop_audit_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_shop_audit_state` */

insert  into `dm_shop_audit_state`(`shop_audit_state_id`,`shop_audit_state`,`prepared1`,`prepared2`,`prepared3`) values (1,'审核通过',NULL,NULL,NULL),(2,'审核不通过',NULL,NULL,NULL);

/*Table structure for table `dm_shop_state` */

DROP TABLE IF EXISTS `dm_shop_state`;

CREATE TABLE `dm_shop_state` (
  `shop_state_id` tinyint(3) unsigned NOT NULL auto_increment,
  `shop_state` varchar(20) NOT NULL,
  `shop_sort` varchar(30) default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`shop_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `dm_shop_state` */

insert  into `dm_shop_state`(`shop_state_id`,`shop_state`,`shop_sort`,`is_used`,`prepared1`,`prepared2`,`prepared3`) values (1,'开张状态',NULL,1,NULL,NULL,NULL),(2,'整改状态',NULL,1,NULL,NULL,NULL),(3,'待审核状态',NULL,1,NULL,NULL,NULL),(4,'关闭状态',NULL,1,NULL,NULL,NULL);

/*Table structure for table `t_bao_users` */

DROP TABLE IF EXISTS `t_bao_users`;

CREATE TABLE `t_bao_users` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `user_name` varchar(12) NOT NULL,
  `user_password` tinyblob NOT NULL,
  `user_email` varchar(30) default NULL,
  `user_phone_number` varchar(20) default NULL,
  `uname` varchar(12) default NULL,
  `user_picture` varchar(50) default NULL,
  `user_sex` varchar(4) default NULL,
  `balance` double(12,4) default '0.0000',
  `is_seller` tinyint(1) default '0',
  `pay_password` tinyblob,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;

/*Data for the table `t_bao_users` */

insert  into `t_bao_users`(`user_id`,`user_name`,`user_password`,`user_email`,`user_phone_number`,`uname`,`user_picture`,`user_sex`,`balance`,`is_seller`,`pay_password`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,'ndq','e10adc3949ba59abbe56e057f20f883e','295502430@qq.com','18202754104','宁敦泉','images/user/1/psb.jpg','男',32518.6000,1,'56e7b5c1170b7d777f41dd23a611d7e0',NULL,NULL,NULL,NULL,NULL),(2,'ning','e10adc3949ba59abbe56e057f20f883e','ningznz@163.com','18202755619','lbw',NULL,'男',23184.5000,1,'56e7b5c1170b7d777f41dd23a611d7e0',NULL,NULL,NULL,NULL,NULL),(3,'lian','e10adc3949ba59abbe56e057f20f883e','540978308@qq.com','18202754106','Lily',NULL,'女',14297.5000,1,'56e7b5c1170b7d777f41dd23a611d7e0',NULL,NULL,NULL,NULL,NULL),(4,'zf8292170','e10adc3949ba59abbe56e057f20f883e','65456456465w','185223456',NULL,NULL,NULL,0.0000,0,NULL,NULL,NULL,NULL,NULL,NULL),(5,'tea','e10adc3949ba59abbe56e057f20f883e','icetea@ndq.com',NULL,NULL,NULL,NULL,0.0000,1,'1a45636e80cd289d5972767d655e25d7',NULL,NULL,NULL,NULL,NULL),(7,'phone','e10adc3949ba59abbe56e057f20f883e','ndq@163.com',NULL,NULL,NULL,NULL,0.0000,1,'96e79218965eb72c92a549dd5a330112',NULL,NULL,NULL,NULL,NULL),(8,'mysql','e10adc3949ba59abbe56e057f20f883e','21321@163.com','18202755619','小宁',NULL,'男',0.0000,1,'202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,NULL),(9,'小明','e10adc3949ba59abbe56e057f20f883e','213213@163.com',NULL,NULL,NULL,NULL,0.0000,1,'e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL),(10,'zhangfan','d3e0966ac539ac9da9792d5172a28550','474627339@qq.com','18202755621',NULL,NULL,NULL,0.0000,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_bao_worker` */

DROP TABLE IF EXISTS `t_bao_worker`;

CREATE TABLE `t_bao_worker` (
  `bao_worker_id` int(10) unsigned NOT NULL auto_increment,
  `bao_worker_no` varchar(20) NOT NULL,
  `bao_worker_password` tinyblob,
  `bao_worker_id_card` varchar(20) default NULL,
  `bao_worker_name` varchar(20) default NULL,
  `bao_worker_phone_number` varchar(20) NOT NULL,
  `province_id` tinyint(3) unsigned default NULL,
  `is_used` tinyint(1) default '1',
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`bao_worker_id`),
  UNIQUE KEY `bao_worker_no` (`bao_worker_no`),
  UNIQUE KEY `bao_worker_id_card` (`bao_worker_id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `t_bao_worker` */

insert  into `t_bao_worker`(`bao_worker_id`,`bao_worker_no`,`bao_worker_password`,`bao_worker_id_card`,`bao_worker_name`,`bao_worker_phone_number`,`province_id`,`is_used`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,'20120046','e10adc3949ba59abbe56e057f20f883e','420922199311113974','宁敦泉','18202754104',NULL,1,NULL,NULL,NULL,NULL,NULL),(2,'ndqndq','e10adc3949ba59abbe56e057f20f883e','420922199311112222','宁宁','18202754104',NULL,1,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_compain_goods` */

DROP TABLE IF EXISTS `t_compain_goods`;

CREATE TABLE `t_compain_goods` (
  `compain_goods_id` int(10) unsigned NOT NULL auto_increment,
  `compainer` int(10) unsigned default NULL,
  `ordergoods_id` int(10) unsigned default NULL,
  `comlain_time` date default NULL,
  `complain_reason_id` tinyint(3) unsigned default NULL,
  `complain_content` varchar(100) default NULL,
  `complain_result_id` tinyint(3) unsigned default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`compain_goods_id`),
  KEY `t_compain_goods_fk_ordergoods_id` (`ordergoods_id`),
  KEY `t_compain_goods_fk_user_id` (`compainer`),
  KEY `t_compain_goods_fk_complain_reason_id` (`complain_reason_id`),
  KEY `t_compain_goods_fk_complain_result_id` (`complain_result_id`),
  CONSTRAINT `t_compain_goods_fk_complain_reason_id` FOREIGN KEY (`complain_reason_id`) REFERENCES `dm_complain_reason` (`complain_reason_id`) ON DELETE CASCADE,
  CONSTRAINT `t_compain_goods_fk_complain_result_id` FOREIGN KEY (`complain_result_id`) REFERENCES `dm_complain_result` (`complain_result_id`) ON DELETE CASCADE,
  CONSTRAINT `t_compain_goods_fk_ordergoods_id` FOREIGN KEY (`ordergoods_id`) REFERENCES `t_ordergoods` (`ordergoods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_compain_goods_fk_user_id` FOREIGN KEY (`compainer`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_compain_goods` */

/*Table structure for table `t_goods` */

DROP TABLE IF EXISTS `t_goods`;

CREATE TABLE `t_goods` (
  `goods_id` int(10) unsigned NOT NULL auto_increment,
  `goods_name` varchar(30) default NULL,
  `goods_no` varchar(30) default NULL,
  `shop_id` int(10) unsigned default NULL,
  `goods_price` double(12,4) default '0.0000',
  `goods_num` smallint(6) default '1',
  `goods_introduce` varchar(50) default NULL,
  `goods_sell_num` smallint(6) default '0',
  `goods_click_rate` int(11) default '0',
  `goods_content` varchar(500) default NULL,
  `goods_appeared_time` varchar(20) default NULL,
  `goods_main_picture` varchar(100) default NULL,
  `goods_pic1` varchar(100) default NULL,
  `goods_pic2` varchar(100) default NULL,
  `goods_pic3` varchar(100) default NULL,
  `goods_pic4` varchar(100) default NULL,
  `goods_pic5` varchar(100) default NULL,
  `phonebrand_id` tinyint(3) unsigned default NULL,
  `cpu_id` tinyint(3) unsigned default NULL,
  `phonesize_id` tinyint(3) unsigned default NULL,
  `os_id` tinyint(3) unsigned default NULL,
  `ram_id` tinyint(3) unsigned default NULL,
  `rom_id` tinyint(3) unsigned default NULL,
  `resolution_id` tinyint(3) unsigned default NULL,
  `pre_camera_id` tinyint(3) unsigned default NULL,
  `camera_id` tinyint(3) unsigned default NULL,
  `phonecolor_id` tinyint(3) unsigned default NULL,
  `communication_id` tinyint(3) unsigned default NULL,
  `goods_state_id` tinyint(3) unsigned default '1',
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(20) default NULL,
  `prepared6` varchar(50) default NULL,
  `prepared7` varchar(100) default NULL,
  PRIMARY KEY  (`goods_id`),
  KEY `t_goods_fk_phonebrand_id` (`phonebrand_id`),
  KEY `t_goods_fk_cpu_id` (`cpu_id`),
  KEY `t_goods_fk_phonesize_id` (`phonesize_id`),
  KEY `t_goods_fk_os_id` (`os_id`),
  KEY `t_goods_fk_ram_id` (`ram_id`),
  KEY `t_goods_fk_rom_id` (`rom_id`),
  KEY `t_goods_fk_resolution_id` (`resolution_id`),
  KEY `t_goods_fk_pre_camera_id` (`pre_camera_id`),
  KEY `t_goods_fk_camera_id` (`camera_id`),
  KEY `t_goods_fk_phonecolor_id` (`phonecolor_id`),
  KEY `t_goods_fk_communication_id` (`communication_id`),
  KEY `t_goods_fk_goods_state_id` (`goods_state_id`),
  CONSTRAINT `t_goods_fk_camera_id` FOREIGN KEY (`camera_id`) REFERENCES `dm_camera` (`camera_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_communication_id` FOREIGN KEY (`communication_id`) REFERENCES `dm_communication` (`communication_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_cpu_id` FOREIGN KEY (`cpu_id`) REFERENCES `dm_cpu` (`cpu_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_goods_state_id` FOREIGN KEY (`goods_state_id`) REFERENCES `dm_goods_state` (`goods_state_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_os_id` FOREIGN KEY (`os_id`) REFERENCES `dm_operatingsystem` (`os_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_phonebrand_id` FOREIGN KEY (`phonebrand_id`) REFERENCES `dm_phonebrand` (`phonebrand_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_phonecolor_id` FOREIGN KEY (`phonecolor_id`) REFERENCES `dm_phonecolor` (`phonecolor_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_phonesize_id` FOREIGN KEY (`phonesize_id`) REFERENCES `dm_phonesize` (`phonesize_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_pre_camera_id` FOREIGN KEY (`pre_camera_id`) REFERENCES `dm_pre_camera` (`pre_camera_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_ram_id` FOREIGN KEY (`ram_id`) REFERENCES `dm_ram` (`ram_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_resolution_id` FOREIGN KEY (`resolution_id`) REFERENCES `dm_resolution` (`resolution_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_fk_rom_id` FOREIGN KEY (`rom_id`) REFERENCES `dm_rom` (`rom_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods` */

insert  into `t_goods`(`goods_id`,`goods_name`,`goods_no`,`shop_id`,`goods_price`,`goods_num`,`goods_introduce`,`goods_sell_num`,`goods_click_rate`,`goods_content`,`goods_appeared_time`,`goods_main_picture`,`goods_pic1`,`goods_pic2`,`goods_pic3`,`goods_pic4`,`goods_pic5`,`phonebrand_id`,`cpu_id`,`phonesize_id`,`os_id`,`ram_id`,`rom_id`,`resolution_id`,`pre_camera_id`,`camera_id`,`phonecolor_id`,`communication_id`,`goods_state_id`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`,`prepared6`,`prepared7`) values (1,'iphone6','no10000',1,5288.0000,5,'iPhone 6是苹果在2014年9月9日推出的一款手机',1,12,'iPhone 6采用4.7英寸屏幕，分辨率为1334*750像素，内置64位构架的苹果A8处理器，性能提升非常明显；同时还搭配全新的M8协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2014年9月9日','images/iphone6.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'iphone5S','no10001',1,4688.0000,2,'iPhone 5s是苹果在2013年9月9日推出的一款手机',0,12,'iPhone 5S采用4.0英寸屏幕，分辨率为1288*720像素，内置32位构架的苹果A7处理器，性能提升非常明显；同时还搭配全新的M7协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2013年9月12日','images/iphone5s.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'三星s6','no10003',1,5488.0000,1,'2015年3月2日，三星在巴萨罗那发布三星galaxy s6',0,12,'2015年3月2日（巴萨罗那时间2015年3月1日），三星在巴萨罗那发布三星galaxy s6，亮相MWC2015产品发布会。GALAXY S6类似iPhone4前后均由玻璃覆盖','2015年3月2日','images/s6.jpg',NULL,NULL,NULL,NULL,NULL,2,2,2,2,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'iphone6','no10000',1,5288.0000,2,'iPhone 6是苹果在2014年9月9日推出的一款手机',1,4,'iPhone 6采用4.7英寸屏幕，分辨率为1334*750像素，内置64位构架的苹果A8处理器，性能提升非常明显；同时还搭配全新的M8协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2014年9月9日','images/iphone6.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'iphone5S','no10001',1,4688.0000,6,'iPhone 5s是苹果在2013年9月9日推出的一款手机',3,11,'iPhone 5S采用4.0英寸屏幕，分辨率为1288*720像素，内置32位构架的苹果A7处理器，性能提升非常明显；同时还搭配全新的M7协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2013年9月12日','images/iphone5s.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'三星s6','no10003',1,5488.0000,3,'2015年3月2日，三星在巴萨罗那发布三星galaxy s6',0,4,'2015年3月2日（巴萨罗那时间2015年3月1日），三星在巴萨罗那发布三星galaxy s6，亮相MWC2015产品发布会。GALAXY S6类似iPhone4前后均由玻璃覆盖','2015年3月2日','images/s6.jpg',NULL,NULL,NULL,NULL,NULL,2,2,2,2,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'iphone6','no10000',1,5288.0000,2,'iPhone 6是苹果在2014年9月9日推出的一款手机',0,4,'iPhone 6采用4.7英寸屏幕，分辨率为1334*750像素，内置64位构架的苹果A8处理器，性能提升非常明显；同时还搭配全新的M8协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2014年9月9日','images/iphone6.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'iphone5S','no10001',1,4688.0000,4,'iPhone 5s是苹果在2013年9月9日推出的一款手机',0,2,'iPhone 5S采用4.0英寸屏幕，分辨率为1288*720像素，内置32位构架的苹果A7处理器，性能提升非常明显；同时还搭配全新的M7协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2013年9月12日','images/iphone5s.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'三星s6','no10003',1,5488.0000,5,'2015年3月2日，三星在巴萨罗那发布三星galaxy s6',0,1,'2015年3月2日（巴萨罗那时间2015年3月1日），三星在巴萨罗那发布三星galaxy s6，亮相MWC2015产品发布会。GALAXY S6类似iPhone4前后均由玻璃覆盖','2015年3月2日','images/s6.jpg',NULL,NULL,NULL,NULL,NULL,2,2,2,2,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'小米手机',NULL,1,1999.9000,20,'小米手机，你值得拥有',0,1,'这是小米手机，惊喜等你','2015-06-02','upload/shop/3/桌面1.jpg','upload/shop/3/aa.jpg','upload/shop/3/bb.jpg','upload/shop/3/131403020913933.jpg','upload/shop/3/131403021410513.jpg','upload/shop/3/13140302564234.jpg',6,2,2,2,1,1,1,1,1,2,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'魅族手机，最好的手机',NULL,1,2566.0000,13,'魅族手机，用来追妹子的手机',0,0,'对比MX4，MX4 Pro有以下五大特色提升：\r\nMX4 Pro 的屏幕分辨率更是全面升级到 2560 x 1536，拥有全球领先的 546PPI，对比度高达 1500 : 1。全新的 Nega 负液晶和 LED 背光模组在 2K 屏幕上的首次使用，大幅降低了功耗；这块显示屏还配备了 PSR 自刷新技术，降低静态页面功耗，最终屏幕实际功耗几乎与 MX4 相当。','2015-06-02','images/goods/3/12/960a304e251f95ca739bb11bc9177f3e67095234.jpg',NULL,NULL,NULL,NULL,NULL,6,2,2,2,2,2,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'魅族手机，你的选择',NULL,1,1999.9000,14,'这是魅族手机，精品推荐',4,1,'MX4 Pro 配备了 Exynos 5430 ，采用 20nm 制程，同等面积可以容纳更多的晶体管。此外，更先进的制程意味着漏电率和发热量的显著降低。Exynos 5430 还独有 Mobile Image Compressor 模块，能够对 2K 分辨率数据进行无损压缩再交由 GPU 运算，可大幅降低功耗和运算时间，提升综合体验。无论是开启 4K 视频和游戏，还是同时处理多个复杂任务，在 MX4 Pro 上，你都能感受到畅快无阻，毫无卡顿。','2015-06-09','images/goods/3/12/960a304e251f95ca739bb11bc9177f3e67095234.jpg',NULL,NULL,NULL,NULL,NULL,4,2,1,2,2,1,1,1,1,2,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'三星s2322',NULL,1,1357.0000,34,'三星手机，你值得拥有',0,0,'三星6[3] 在英国伦敦举行发布会，正式发布Galaxy及ATIV系列多达9款新产品，其中包含3款手机产品、1款相机产品和5款笔记本、平版产品。\r\nGalaxy S4系列智能手机：Galaxy S4 Active、Galaxy S4 mini、Galaxy S4 zoom。\r\n三星首款Android相机：Galaxy NX\r\n五款笔记本、平板电脑：ATIV Book 9 Plus、ATIV Book 9 Lite(轻薄笔记本)、ATIV Q、ATIV Tab 3、ATIVOne 5。','2015-06-09','images/goods/3/13/6d81800a19d8bc3e0174c9e5818ba61ea8d345a5.jpg','images/goods/3/13/203fb80e7bec54e7c242a4b2b8389b504ec26af4.jpg','images/goods/3/13/c2cec3fdfc039245e22a40408294a4c27c1e25e5.jpg','images/goods/3/13/faedab64034f78f00e7655dc7a310a55b3191ca4.jpg',NULL,NULL,2,2,1,2,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'阿萨姆手机，最好的手机',NULL,1,3212.0000,14,'啊飒飒的',0,2,'撒旦撒旦撒大士大夫第三方第三方的手','2015-06-02','images/goods/3/14/c2cec3fdfc039245e22a40408294a4c27c1e25e5.jpg','images/goods/3/14/6d81800a19d8bc3e0174c9e5818ba61ea8d345a5.jpg','images/goods/3/14/c2cec3fdfc039245e22a40408294a4c27c1e25e5.jpg','images/goods/3/14/203fb80e7bec54e7c242a4b2b8389b504ec26af4.jpg',NULL,NULL,6,2,2,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'三星GALAXY S6',NULL,5,5288.0000,8,'新鲜到货，拒绝暴利，edge全网通4680，比国行港行便宜2000哦',0,0,'Galaxy S6｜S6 edge双曲面屏缔造优雅与卓越，玻璃与金属巧妙融汇。感受三星Galaxy S6的更多精彩内容，尽在官网。 ','2015-05-12','images/goods/5/15/cesaxaXVzGFeA.jpg','images/goods/5/15/ce9xdYNrpWLxY.jpg','images/goods/5/15/ceKYVBf9YckIY.jpg','images/goods/5/15/cesO9CLOL6rk2.jpg','images/goods/5/15/cetFvStKWLdHE.jpg',NULL,2,3,1,7,5,2,1,1,3,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'三星GALAXY S6 Edge',NULL,5,6088.0000,7,'新鲜到货，拒绝暴利，edge全网通4680，比国行港行便宜2000哦',0,0,'Galaxy S6 Edge配备的5.1英寸曲面屏幕，八核Exynos 7420处理器，并搭配3GB运行内存，拥有1600万像素主镜头。','2015-05-30','images/goods/5/16/cegPlq2eWAULE.jpg','images/goods/5/16/cesaxaXVzGFeA.jpg','images/goods/5/16/ceoF8CxIGPRIg.jpg','images/goods/5/16/ceKYVBf9YckIY.jpg','images/goods/5/16/cetFvStKWLdHE.jpg',NULL,2,1,1,10,6,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'三星W2015',NULL,5,11000.0000,5,'高端商务机好选择 三星W2015',0,0,'三星W2015性能与美观，为你而生，正品行货，全国联保。','2015-05-30','images/goods/5/17/cegi289dHzrhc.jpg','images/goods/5/17/ceDUspCBLx4Jo.jpg','images/goods/5/17/ceuBpS2XPNcoo.jpg','images/goods/5/17/ceVtXDDNDTxs.jpg','images/goods/5/17/cetpocjoekp2.jpg',NULL,2,6,1,1,7,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'三星GALAXYA7',NULL,5,2350.0000,8,'真八核电信机 三星GALAXY A7商家报低价',0,0,'三星GALAXYA7正面采用一块5.5英寸SuperAMOLED魔焕炫屏，分辨率为1920X1080像素的FHD级别，显示效果不错。','2015-05-31','images/goods/5/18/ce3Pm041zJLOs.jpg','images/goods/5/18/ce9iWsSSMBQI.jpg','images/goods/5/18/ceOsrNBsZ4bFs.jpg','images/goods/5/18/cesMqDHXArlIY.jpg','images/goods/5/18/ceJCtqXRwx3Y.jpg',NULL,2,3,1,10,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'华为P8',NULL,5,3588.0000,11,'华为P8很不错',0,0,'这是一个万物生长的时代，曾经一无所有，却誓言改变一切。似水流年，志存高远。华为P8，以行践言！','2015-06-01','images/goods/5/19/ce28SdDmd0GKY.jpg','images/goods/5/19/ced9CZmQiXloM.jpg','images/goods/5/19/ceC5OTjs5ONE.jpg','images/goods/5/19/ceVoUjRDkRxew.jpg','images/goods/5/19/cevWnVlkaeWak.jpg',NULL,4,4,1,10,7,1,1,1,5,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'华为Mate7',NULL,5,2999.0000,10,'华为Mate7是华为旗下的一款手机。北京时间2014年9月4日晚9点，华为Mate7发布会在柏林举行',0,0,'华为本次发布的新旗舰华为Mate7分为高配版，标配版，尊爵版，同时这三种版本分别有4个渠道版本。','2015-06-01','images/goods/5/18/ce3Pm041zJLOs.jpg',NULL,NULL,NULL,NULL,NULL,4,1,1,10,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'华为Mate7',NULL,5,2999.0000,10,'华为Mate7是华为旗下的一款手机。北京时间2014年9月4日晚9点，华为Mate7发布会在柏林举行',0,1,'华为本次发布的新旗舰华为Mate7分为高配版，标配版，尊爵版，同时这三种版本分别有4个渠道版本。','2015-06-01','images/goods/5/19/ce28SdDmd0GKY.jpg',NULL,NULL,NULL,NULL,NULL,4,1,1,10,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'华为Mate7',NULL,5,2999.0000,10,'华为Mate7是华为旗下的一款手机。北京时间2014年9月4日晚9点，华为Mate7发布会在柏林举行',0,0,'华为本次发布的新旗舰华为Mate7分为高配版，标配版，尊爵版，同时这三种版本分别有4个渠道版本。','2015-06-01','images/goods/5/24/ceFFplBBRcLI (1).jpg',NULL,NULL,NULL,NULL,NULL,4,4,1,10,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'华为Mate7',NULL,5,2999.0000,7,'华为Mate7是华为旗下的一款手机。北京时间2014年9月4日晚9点，华为Mate7发布会在柏林举行',3,2,'华为本次发布的新旗舰华为Mate7分为高配版，标配版，尊爵版，同时这三种版本分别有4个渠道版本。','2015-06-01','images/goods/5/24/ceFFplBBRcLI (1).jpg',NULL,NULL,NULL,NULL,NULL,4,4,1,10,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'华为荣耀6',NULL,5,1499.0000,9,'华为荣耀6给人一种高大上的感觉',0,0,'华为荣耀6是华为着力打造一款4G智能机，于2014年6月24日在国家体育中心正式发布。荣耀6搭载了华为海思自主研发的麒麟920芯片。该芯片基于28nm工艺制造，采用8核big.little GTS架构','','images/goods/5/24/ceFFplBBRcLI (1).jpg','images/goods/5/24/ceFFplBBRcLI.jpg','images/goods/5/24/cejVuXZCMecq2.jpg','images/goods/5/24/ce2HVBFAl3mxw.jpg','images/goods/5/24/cesjWnKw1IcEw.jpg',NULL,4,1,1,10,6,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'华为Mate7',NULL,5,2999.0000,10,'华为Mate7是华为旗下的一款手机。北京时间2014年9月4日晚9点，华为Mate7发布会在柏林举行',0,0,'华为本次发布的新旗舰华为Mate7分为高配版，标配版，尊爵版，同时这三种版本分别有4个渠道版本。','2015-06-01','images/goods/5/25/ceS7lEl5TVLuk (1).jpg','images/goods/5/25/ceS7lEl5TVLuk.jpg','images/goods/5/25/ceGXYL3Lx0UCQ.jpg','images/goods/5/25/ce2TsoAfC45EI.jpg','images/goods/5/25/ceU0tEXdtbLw.jpg',NULL,4,4,1,10,6,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'荣耀畅玩4x',NULL,5,1299.0000,10,'荣耀畅玩4x直降百元!超全品牌手机,电话0利润超低价.货到付款!亚马逊Z.cn放心购物',0,0,'2014年10月28日，业内首款64位多核芯全网通智能手机荣耀畅玩4X全网发布支持中国移动、联通、电信三大运营商4G、3G、2G网络','2015-06-01','images/goods/5/26/cehois2LpQfxw.jpg','images/goods/5/26/cehois2LpQfxw.jpg','images/goods/5/26/ceDWJjJqNiwK.jpg','images/goods/5/26/ce0CLQNaogbAI.jpg','images/goods/5/26/ceXnoBdmqnKBU.jpg',NULL,4,4,1,10,1,1,1,3,3,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'三星GALAXY Note III',NULL,5,5288.0000,8,'5.7英寸大屏，携带不便，3200毫安的大容量电池',0,0,'三星GALAXY Note III是三星Note系列的第三代产品，配备5.7英寸全高清炫丽屏(Super AMOLED)，分辨率为1080P','2015-05-31','images/goods/5/27/cemSaoTz6ZoIg.jpg','images/goods/5/27/ceAly49mfBP.jpg','images/goods/5/27/ceQmRZpzVV3Y2.jpg','images/goods/5/27/cejOrsTdxJEeQ.jpg','images/goods/5/27/ce0RC9Tck8A.jpg',NULL,2,3,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'华为荣耀3C',NULL,5,650.0000,7,'华为荣耀3C作为华为品牌独立运作推出多款终端之一的一款千元机型',0,0,'华为荣耀3C深圳报价620元 送钢化膜 华为荣耀3C畅玩版搭载四核1.3GHz高速处理器,配备5英寸1280*720分辨率IPS高清显示屏','2015-06-01','images/goods/5/28/ceDKXlofQJdc.jpg','images/goods/5/28/ce9XOTt4lpkIM.jpg','images/goods/5/28/ceyR9lmIN5s8.jpg','images/goods/5/28/ceP3QDAzBxax2.jpg','images/goods/5/28/ceW7GDEDV9ClA.jpg',NULL,4,4,1,10,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'OPPO R7',NULL,5,2499.0000,8,'OPPO R7是OPPO在2015年新推出的一款智能手机',0,0,'2015年5月13日 - OPPO将于5月20日在北京发布R7系列手机,该机的电信版和移动版也拿到了工信部入网许可证。','2015-06-01','images/goods/5/29/cef2EHtjfD4c6.jpg','images/goods/5/29/cef2EHtjfD4c6.jpg','images/goods/5/29/ce1ATBfUQPPZ6.jpg','images/goods/5/29/cesxTxA1OhgVw.jpg','images/goods/5/29/ceYzwjRD3lhAc.jpg',NULL,9,11,1,12,5,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'OPPO Find 7',NULL,5,2999.0000,6,'2014年3月19日OPPO正式发布了新旗舰 OPPO Find 7',0,0,'2014年3月19日，OPPO在北京798艺术中心发布了年度旗舰手机OPPO Find 7，其分为轻装版和标准版，均采用了5.5英寸屏幕，1080P（1920×1080）/2K（2560×1440）分辨率，搭载高通骁龙801处理器','2015-06-01','images/goods/5/30/ce0wxHl817A6k.jpg','images/goods/5/30/cetMZDX4WDLHk.jpg','images/goods/5/30/ceidnvn4SXq4I.jpg','images/goods/5/30/ceK8nYSXawx.jpg','images/goods/5/30/ceJTXBwWjuGTI.jpg',NULL,9,6,1,9,6,1,1,1,5,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'OPPO R5',NULL,5,2999.0000,7,'主屏尺寸5.2英寸 主屏分辨率1920x1080像素',0,0,'OPPO R5是OPPO向Finder致敬经典的新一代力作，以4.85mm的超薄机身成为截止至2014年10月为止的全球最薄智能手机。.','2015-06-01','images/goods/5/31/cex2o2PrPTIM2.jpg','images/goods/5/31/cex2o2PrPTIM2.jpg','images/goods/5/31/ce4AzApiNjUk.jpg','images/goods/5/31/ce3vC4ZCwe1I.jpg','images/goods/5/31/ceJoWrMZWt1q6.jpg',NULL,9,4,1,10,1,1,1,1,4,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'OPPO N3',NULL,5,3999.0000,11,'2014年10月29日，OPPO在北京发布',0,0,'2014旗舰手机N3，N3在设计上延续了N1的线体设计理念，而把手动旋转摄像头升级到更加智能的电动旋转摄像头，兼备优雅的镂空设计和星环呼吸灯，产品尺寸也缩小到更为舒适的5.5寸。而1600万像素施耐德认证镜头、PI原画引擎2.0+让N3堪称2014年度最强安卓拍照手机之一。','2015-06-01','images/goods/5/32/ceex0DVqeNBwE.jpg','images/goods/5/32/cemuKvD1KaKcs.jpg','images/goods/5/32/ceABqrxO60dbI.jpg','images/goods/5/32/ce8wKcaRWWlc.jpg','images/goods/5/32/ceqmzwVsrwMqE.jpg',NULL,9,5,1,10,6,1,1,1,3,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'OPPO A31',NULL,5,999.0000,5,'OPPO A31是OPPO于2015年4月中旬发布',0,0,'OPPO A31是OPPO于2015年4月中旬发布的一款双卡4G智能手机产品，该机型外部采用了经典的流光镜面设计，内部采用更加牢固的双层金属骨架支撑设计，并在拍照体验上沿袭了OPPO的优良基因，将于2015年4月底全面上市，有冰晶白与宝石蓝两色可选。','','images/goods/5/33/ceZ54MuTqzS6.jpg','images/goods/5/33/ceFr1emPxRC2.jpg','images/goods/5/33/cexWZHfiYxPc.jpg','images/goods/5/33/cedHQAIYZueJw.jpg','images/goods/5/33/ceZGbIpo0cXtc.jpg',NULL,9,4,1,12,1,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'vivo X5Pro',NULL,5,2598.0000,7,'vivo X5Pro是vivo智能手机',0,0,'vivo X5Pro是vivo智能手机于2015年5月推出的一款智能手机，该机于5月13日在北京、广州双城同时发布。\r\n4月13日下午，vivo官方微博公布vivo即将发布新款手机vivo X5pro。根据新闻曝光的汇总可以看出，vivo X5pro是采用双面玻璃材质，正面更可能采用2.5D屏。','2015-06-01','images/goods/5/34/ceysuHFg31FT.jpg','images/goods/5/34/ceysuHFg31FT.jpg','images/goods/5/34/ce7VEcYnjmgXg.jpg','images/goods/5/34/ceqTf34iZy972.jpg','images/goods/5/34/cerJBr8tnDFEs.jpg',NULL,10,11,7,1,5,3,1,1,5,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'vivo X5',NULL,5,0.0000,3,'vivo X5的屏幕是达到了主流水准，但和顶级水准还是有些差距的',0,5,'vivo X5配硬件方面一点也没有缩水，达到了现在主流八核手机的水平。vivo X5这次搭载的是MT6592八核1.7GHz处理器，Mali-450的GPU，并且配备了2GB运行内存和16GB的机身内存。','','images/goods/5/35/ceAnLuiuvEsqo.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'vivo X5',NULL,5,0.0000,4,'',0,0,'','','images/goods/5/35/ceAnLuiuvEsqo.jpg',NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'vivo X5L',NULL,5,2298.0000,7,'vivo X5的屏幕是达到了主流水准，但和顶级水准还是有些差距的',0,0,'但vivo X5配硬件方面一点也没有缩水，达到了现在主流八核手机的水平。vivo X5这次搭载的是MT6592八核1.7GHz处理器，Mali-450的GPU，并且配备了2GB运行内存和16GB的机身内存。','2015-06-01','images/goods/5/37/ceAnLuiuvEsqo.jpg','images/goods/5/37/ceAnLuiuvEsqo.jpg','images/goods/5/37/ceon0Q71NKu5Y.jpg','images/goods/5/37/cex4OIXw0sFbk.jpg','images/goods/5/37/ce4Dyh8pCAJC6.jpg',NULL,10,11,1,7,5,7,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,'vivo X5Max',NULL,5,2998.0000,8,'vivo X5Max是vivo于2014年底发布的一款旗舰产品。',0,0,'全球首发。vivo发布vivo X5Max 2014年11月11日，vivo在官方微博上发布vivo X5Max的产品曝光图。\r\nvivo X5Max V(电信4G) 手机类型 4G手机,3G手机,智能手机,拍照手机,音乐手机 触摸屏类型 电容屏,多点触控 主屏尺寸 5.5英寸 主屏材质 OLED','2015-06-01','images/goods/5/38/ce2TjKvuQnSqo.jpg','images/goods/5/38/ce2TjKvuQnSqo.jpg','images/goods/5/38/cefz3ovJZEkjs.jpg','images/goods/5/38/ce2XZV4vtNPi.jpg','images/goods/5/38/ceSPZijTb3a8Q.jpg',NULL,10,11,4,10,1,7,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,'vivo x5sl',NULL,7,2298.0000,10,'x5sL基本参数操作系统Funtouch OS 2.0',0,0,'x5sL基本参数操作系统Funtouch OS 2.0(基于Android 4.4)处理器高速64位八核处理器内存2G RAM+16G ROM\r\n vivo X5SL(16GB/移动4G)采用直板式设计，配备5英寸多点触控屏，运行Android 4.4操作系统，搭载八核处理器，内置双摄像头，支持WiFi，GPS导航功能','','images/goods/7/39/ceidYnW0FbGJ6.jpg','images/goods/7/39/ceidYnW0FbGJ6.jpg','images/goods/7/39/ceon0Q71NKu5Y.jpg','images/goods/7/39/cewz7Yuo1mveE.jpg','images/goods/7/39/cex4OIXw0sFbk.jpg',NULL,10,11,1,12,1,1,1,1,4,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,'vivo X3L',NULL,7,1998.0000,10,'vivo X3L是vivo于2014年5月份推出的vivo X3的4G移动版升级版本',0,0,'vivo智能手机vivo X3L,1.2GHz四核处理器,800W主摄像头,2G RAM,16G ROM,支持T卡扩展(最大支持128GB),支持4G TDD-LTE制式,上网快人一步,vivo,HiFi极致影音.','2015-06-01','images/goods/7/40/ce8RvnL4NSMO.jpg','images/goods/7/40/ce8RvnL4NSMO.jpg','images/goods/7/40/ce87cDvcLF3b.jpg','images/goods/7/40/cem3W6l2FpD02.jpg','images/goods/7/40/ceawP11jJx8kA.jpg',NULL,10,11,1,12,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'iPhone 6',NULL,7,5288.0000,12,'iPhone 6是苹果公司（Apple）在2014年9月9日推出的一款手机',0,4,'iPhone 6采用4.7英寸屏幕，分辨率为1334*750像素，内置64位构架的苹果A8处理器，性能提升非常明显；同时还搭配全新的M8协处理器，专为健康应用所设计；采用后置800万像素镜头，前置120万像素 鞠昀摄影FaceTime HD 高清摄像头；并且加入Touch ID支持指纹识别，首次新增NFC功能；也是一款三网通手机，4G LTE连接速度可达150Mbps，支持多达20个LTE频段。','2015-06-01','images/goods/7/41/ce9mOCY1IJbz6.jpg','images/goods/7/41/ce9mOCY1IJbz6.jpg','images/goods/7/41/cedoQQeurXeU.jpg','images/goods/7/41/ceveIRprKgv4c.jpg','images/goods/7/41/ceoIMlVy9xV2.jpg',NULL,1,1,1,1,7,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'iPhone 5s',NULL,7,3420.0000,7,'iPhone5s是美国苹果公司在2013年9月推出的一款手机。',0,0,'iPhone5s是美国苹果公司在2013年9月推出的一款手机。在9月20日于12个国家以及地区首发iPhone 5s，首次包括中国大陆，首周销量突破900万部。2013年底，美国知名科技媒体《商业内幕》整理出了“本年度最具创新力的十大设备”，iPhone 5s因指纹识别功能而被入选其中。\r\n苹果iPhone 5s延续了上一代iPhone5的经典设计。','2015-06-01','images/goods/7/42/ce6ak9yGLOI.jpg','images/goods/7/42/ce6ak9yGLOI.jpg','images/goods/7/42/ceaoaYnw9Ti7o.jpg','images/goods/7/42/ceMLvgbcaTsv.jpg','images/goods/7/42/ceE99jST4h0.jpg',NULL,1,1,1,1,6,1,1,1,1,5,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,'iPhone 4S',NULL,7,1800.0000,7,'iPhone 4S是苹果公司推出的一款触摸屏智能手机',0,3,'iPhone 4s在硬件和软件方面都有了较大的提升，其最大的亮点在于全新siri智能语音助手和iCloud云端服务。硬件方面，搭载苹果A5双核处理器，正面配有3.5英寸IPS玻璃硬屏，分辨率为960×640像素，背照式镜头像素提升至800万。','2015-06-01','images/goods/7/43/ce2cGKfIWyRuc.jpg','images/goods/7/43/cetr3hPFaqVlg.jpg','images/goods/7/43/ce5koPgOVxLTw.jpg','images/goods/7/43/ceGgimFyc8I8I.jpg','images/goods/7/43/ceADfiOUGiXoM.jpg',NULL,1,1,1,1,1,1,1,1,1,1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'iPhone 5c',NULL,7,3288.0000,10,'iPhone 5c是2013年9月推出的一款智能手机',0,0,'iPhone 5c是苹果公司（Apple）在2013年9月推出的一款智能手机。北京时间2013年9月11日凌晨1点，苹果在公司总部加利福尼亚州库比蒂诺举行发布会，在发布会上推出苹果产品线上首款塑料多彩的产品iPhone5C。iPhone 5c采用4英寸视网膜Retina屏幕（分辨率为1136x640），机身采用硬质涂层的聚碳酸酯材料，A6处理器，1GRAM,搭出厂默认搭载IOS7系统，1510mAh电池，略大于iPhone 5电池容量，配备800万像素iSight摄像头，720p HD FaceTime前置摄像头。iPhone5c的“C”是“COLOURFUL”彩色的意思[1] ，也就是说iPhone5c其实就是多彩版的iPhone5，拥有绿，黄，红， 白，蓝五色可选。16GB版售价4488元，32GB版5288元。2014年3月20日，在中国内地新增8GB版售价4088元，型号为 A1507，系列号为 MG902.','','images/goods/7/44/cedKGTCPSvRLI.jpg','images/goods/7/44/cedKGTCPSvRLI.jpg','images/goods/7/44/cemCwtjqwa2M.jpg','images/goods/7/44/ceEuemAnhoFE.jpg','images/goods/7/44/cezY6pHiaVtmE.jpg',NULL,1,1,1,1,1,8,1,1,1,10,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'TCL ono P620M',NULL,7,799.0000,10,'TCL ono P620M必定会成为599元机型的一员猛将',0,0,'TCL ono P620M配置了A53架构的MT6735四核64位处理器，频率1.3GHz；配备2GB RAM和16GB ROM，并支持扩展；采用5.0英寸IPS屏幕，分辨率1280*720，摄像头为500万+1300万，标配2650mAh电池。对，这个配置仅售599元，支持4G并适配了Android 5.0系统。','2015-06-02','images/goods/7/45/ceCwPpby1bKC6.jpg','images/goods/7/45/ceTScEoaUQxtE.jpg','images/goods/7/45/ceA5RffA9lNGA.jpg','images/goods/7/45/ceA2SJVR2h9k.jpg','images/goods/7/45/ceUQmHWqAa3fA.jpg',NULL,12,1,1,10,1,1,1,1,1,5,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'TCL 么么哒3N',NULL,7,899.0000,9,'TCL 么么哒3N是一款时尚出色的真八核超值手机',0,1,'TCL 么么哒3N采用5.5英寸屏幕，分辨率为1280x1080像素，搭载了1.7GHz主频联发科MT6752八核处理器，配合2GB RAM+16GB ROM内存组合，以及1300万像素后置+800万像素前置摄像头组合','2015-06-02','images/goods/7/48/ceUlPsHgqYX6.jpg',NULL,NULL,NULL,NULL,NULL,12,1,1,10,1,1,1,1,1,1,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'TCL 么么哒3N',NULL,7,899.0000,9,'TCL 么么哒3N是一款时尚出色的真八核超值手机',0,0,'TCL 么么哒3N采用5.5英寸屏幕，分辨率为1280x1080像素，搭载了1.7GHz主频联发科MT6752八核处理器，配合2GB RAM+16GB ROM内存组合，以及1300万像素后置+800万像素前置摄像头组合','2015-06-02','images/goods/7/47/ceLBpaK02nJHA.jpg','images/goods/7/47/cebaDcJXxuxWM.jpg','images/goods/7/47/ceEEQZJQJoaOg.jpg','images/goods/7/47/cefmZeZGUau9Q.jpg','images/goods/7/47/ce7oozOoSNksE.jpg',NULL,12,1,1,10,1,1,1,1,1,7,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'TCL乐玩',NULL,7,599.0000,8,'TCL在北京798艺术区正式发布一款入门4G手机??TCL乐玩',0,0,'TCL乐玩手机[1] 是中国电信联合TCL推出的一款手机，型号为P588L。该款手机以“乐在玩中”为主旨，既满足了年轻人爱玩爱乐的需求又为老年人开发了贴心的专属功能。以炫酷贴心的设计、超畅快的体验、超强大的自拍功能、超高的性价比和超实惠的价位赢得了市场的关注。','2015-06-01','images/goods/7/48/ceUlPsHgqYX6.jpg','images/goods/7/48/ceUlPsHgqYX6.jpg','images/goods/7/48/ceaVxGugyYdk.jpg','images/goods/7/48/cey42Ztnccd1A.jpg','images/goods/7/48/cesn5gVtJz3I.jpg',NULL,12,1,1,1,1,1,1,2,4,11,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'TCL P331M',NULL,7,399.0000,6,'TCL P331M 正面配置有一块4.5英寸屏',0,0,'TCL P331M 正面配置有一块4.5英寸屏幕多点触控式电容屏，分辨率为854×480像素的FWVGA级别。CPU方面配置一颗主频1.3GHz联发科 MT6582M四核芯处理器，以及512MB RAM+4GB ROM的内存组合，可流畅运行Android 4.4系统。TCL P331M还搭载一枚500万像素的后置摄像头，能够满足日常拍照需求。\r\nTCL P331M采用直板式设计，配备4.5英寸触摸屏，搭载联发科四核处理器，运行Android 4.4操作系统，内置摄像头，支持WiFi','','images/goods/7/49/ce2wFCpT7dfNs.jpg','images/goods/7/49/ce2wFCpT7dfNs.jpg','images/goods/7/49/ceL7TutKTiA.jpg','images/goods/7/49/ceQiGv9GFnmsE.jpg','images/goods/7/49/cexMpSTrNmfow.jpg',NULL,12,11,1,10,1,1,1,1,1,5,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'酷派锋尚Pro',NULL,7,1090.0000,6,'酷派锋尚Pro 是酷派锋尚系列的最新旗舰机型',0,1,'酷派锋尚Pro 是酷派锋尚系列的最新旗舰机型,其被消费者称为“最具性价比指纹识别手机”。酷派锋尚Pro于5月23日正式发售,移动4G版先行,电信4G版紧随其后。\r\n酷派锋尚Pro搭载了目前比较流行的指纹识别功能,这一模块设计在它的手机背部,支持5组指纹,为按压式。并且不仅仅只是用来解锁,还可以通过指纹快速进入应用。','2015-06-01','images/goods/7/50/cebP1PabKLQQE.jpg','images/goods/7/50/cebP1PabKLQQE.jpg','images/goods/7/50/cecYyCQJb9mw.jpg','images/goods/7/50/ceorQ2T9NJy2w.jpg','images/goods/7/50/cecYyCQJb9mw.jpg',NULL,7,6,1,7,5,1,1,2,2,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'酷派 大观',NULL,7,1299.0000,7,'据悉酷派铂顿就是以酷派大观5为蓝本打造',0,0,'酷派 大观铂顿采用的CPU为高通骁龙801 MSM8974AC,主频达2.5GHz;3GB的RAM搭配32GB的ROM,可支持最高使用32GB的内存卡进行扩展。铂顿配备了两颗1300万像素的后置摄像','2015-06-02','images/goods/7/51/ceOC6CpJI8FCo.jpg','images/goods/7/51/ceOC6CpJI8FCo.jpg','images/goods/7/51/ceTZwSykLM3m6.jpg','images/goods/7/51/cen8ZwKK0yh4I.jpg','images/goods/7/51/ceIGWWMiBdQzo.jpg',NULL,7,6,1,7,1,1,1,3,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'酷派8720L',NULL,7,550.0000,8,'首款千元4G四核手机 酷派8720L全国首测',0,0,'酷派8720L是国内首款4G手机，5英寸触屏手机、搭载1228MHz四核处理器，运行Android OS 4.3系统，支持安卓各类应用程序，前后摄像头（前200万、后500万），1280x720分辨率让你尽显清晰效果。','','images/goods/7/52/ceNFx8N7coCaw.jpg','images/goods/7/52/ceNFx8N7coCaw.jpg','images/goods/7/52/ce3a5Qvswrkn2.jpg','images/goods/7/52/cekkFz920ZE1.jpg','images/goods/7/52/cepMkdmwB10eY.jpg',NULL,7,1,1,10,5,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'酷派S6',NULL,7,900.0000,9,'全球首款4G双卡双通手机??酷派S6正式发布',0,0,'酷派S6的发布，又有着不同寻常的意义：全球首款4G双卡手机诞生，将改变行业布局，引领4G趋势。2014年3月，4G终端产品数量在市场上呈迅速递增趋势，4G双卡手机却是全球首款，对于消费者来说，升级4G将面临换卡带来的不便，而双卡可以有效降低消费者换卡成本，更好地延展和升级4G体验。可以说，酷派S6的诞生，将引领国内。4G快速普及，酷派更有望正式向4G产品实现国内销量第一目标迈进。','2015-06-01','images/goods/7/53/ceM7QbqTl4g7g.jpg','images/goods/7/53/ceM7QbqTl4g7g.jpg','images/goods/7/53/ce4HbBQ6sE57g.jpg','images/goods/7/53/ceu1zHCFG76.jpg','images/goods/7/53/ceOTpL8nQRE4.jpg',NULL,7,1,1,10,1,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'大神X7',NULL,7,1699.0000,8,'大神手机不愧是大神',0,1,'2015年1月8日下午2点30分，大神手机在北京国家会议中心举行年度旗舰新品发布会，正式发布其产品大神X7。机身采用了纯彩航空铝一体成形中框+双镜面玻璃面板，在手机制作工艺方面得到业内一致的好评。\r\n酷派大神（dazen)以神自不凡的理念,为用户打造尖叫的产品体验!做4G时代年轻人的互联网旗舰品质手机.为梦想执着,你就是大神','2015-06-01','images/goods/7/54/ceej9bZIPQgJs.jpg','images/goods/7/54/ceej9bZIPQgJs.jpg','images/goods/7/54/ceGh8SnGkT26.jpg','images/goods/7/54/ceU5gpfk5H37s.jpg','images/goods/7/54/ceO5tyj0aiU2.jpg',NULL,11,11,1,7,4,1,1,1,1,5,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'sadsad',NULL,1,3432.0000,3,'这是你的手机，精品推荐',0,0,'sadsadsadsa','2015-06-02','images/goods/3/56/cc.jpg','images/goods/3/56/960a304e251f95ca739bb11bc9177f3e67095234.jpg','images/goods/3/56/a5c27d1ed21b0ef4fc054735dec451da80cb3ee8.jpg','images/goods/3/56/b58f8c5494eef01f91c6cbfbe4fe9925bc317d3a.jpg',NULL,NULL,5,4,4,5,4,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_goods_ad` */

DROP TABLE IF EXISTS `t_goods_ad`;

CREATE TABLE `t_goods_ad` (
  `goods_ad_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `goods_ad_picture` varchar(50) NOT NULL,
  `goods_ad_content` varchar(100) default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`goods_ad_id`),
  KEY `t_goods_ad_fk_goods_id` (`goods_id`),
  CONSTRAINT `t_goods_ad_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods_ad` */

insert  into `t_goods_ad`(`goods_ad_id`,`goods_id`,`goods_ad_picture`,`goods_ad_content`,`prepared1`,`prepared2`,`prepared3`) values (1,10,'images/ad1.png',NULL,NULL,NULL,NULL),(2,21,'images/ad2.png',NULL,NULL,NULL,NULL),(3,12,'images/ad3.png',NULL,NULL,NULL,NULL);

/*Table structure for table `t_goods_collect` */

DROP TABLE IF EXISTS `t_goods_collect`;

CREATE TABLE `t_goods_collect` (
  `goods_collect_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `user_id` int(10) unsigned default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`goods_collect_id`),
  KEY `t_goods_collect_fk_goods_id` (`goods_id`),
  KEY `t_goods_collect_fk_user_id` (`user_id`),
  CONSTRAINT `t_goods_collect_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_collect_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods_collect` */

insert  into `t_goods_collect`(`goods_collect_id`,`goods_id`,`user_id`,`prepared1`,`prepared2`,`prepared3`) values (1,5,1,NULL,NULL,NULL),(2,1,3,NULL,NULL,NULL),(7,3,1,NULL,NULL,NULL),(11,18,1,NULL,NULL,NULL);

/*Table structure for table `t_goods_judged` */

DROP TABLE IF EXISTS `t_goods_judged`;

CREATE TABLE `t_goods_judged` (
  `goods_judged_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned default NULL,
  `goods_id` int(10) unsigned default NULL,
  `judged_content` varchar(100) default NULL,
  `judged_rate` tinyint(4) default '5',
  `judged_time` date default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`goods_judged_id`),
  KEY `t_goods_judged_fk_goods_id` (`goods_id`),
  KEY `t_goods_judged_fk_user_id` (`user_id`),
  CONSTRAINT `t_goods_judged_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_judged_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods_judged` */

insert  into `t_goods_judged`(`goods_judged_id`,`user_id`,`goods_id`,`judged_content`,`judged_rate`,`judged_time`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,1,19,'这是一款好手机，很便宜，用着非常好',5,'2015-05-13',NULL,NULL,NULL,NULL,NULL),(2,1,5,'精致，手感一流，操作流畅~~~感觉之前丢了的S4要好用~~',5,'2015-02-18',NULL,NULL,NULL,NULL,NULL),(3,1,18,'合适我们的意乱啊手机打开垃圾啊撒双击打开啊就是离开',5,'2015-06-09',NULL,NULL,NULL,NULL,NULL),(4,1,18,'测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试',5,'2015-06-09',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_goods_picture` */

DROP TABLE IF EXISTS `t_goods_picture`;

CREATE TABLE `t_goods_picture` (
  `goods_picture_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `goods_pic` varchar(150) default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`goods_picture_id`),
  KEY `t_goods_picture_fk_goods_id` (`goods_id`),
  CONSTRAINT `t_goods_picture_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods_picture` */

insert  into `t_goods_picture`(`goods_picture_id`,`goods_id`,`goods_pic`,`prepared1`,`prepared2`,`prepared3`) values (1,10,'upload/shop/3/cc.jpg',NULL,NULL,NULL),(2,10,'upload/shop/3/psb.jpg',NULL,NULL,NULL),(3,11,'images/goods/3/11/screen.jpg',NULL,NULL,NULL),(4,11,'images/goods/3/11/net.jpg',NULL,NULL,NULL),(5,11,'images/goods/3/11/battery.jpg',NULL,NULL,NULL),(6,12,'images/goods/3/12/battery.jpg',NULL,NULL,NULL),(7,12,'images/goods/3/12/net.jpg',NULL,NULL,NULL),(8,12,'images/goods/3/12/screen.jpg',NULL,NULL,NULL),(9,14,'images/goods/3/14/u=4205123531,2307039702&fm=21&gp=0.jpg',NULL,NULL,NULL),(10,14,'images/goods/3/14/u=3831834945,1112444855&fm=21&gp=0.jpg',NULL,NULL,NULL),(11,14,'images/goods/3/14/u=994262050,879586730&fm=21&gp=0.jpg',NULL,NULL,NULL),(12,15,'images/goods/5/15/Cg-4WVVVjPGIdoffAAA1Ogcln2wAADl_ALQ6wkAADVS644.jpg',NULL,NULL,NULL),(13,16,'images/goods/5/16/Cg-4WVVVjPGIdoffAAA1Ogcln2wAADl_ALQ6wkAADVS644.jpg',NULL,NULL,NULL),(14,17,'images/goods/5/17/cenFE1uc0tG92.jpg',NULL,NULL,NULL),(15,17,'images/goods/5/17/ceNZbGHx4rak.jpg',NULL,NULL,NULL),(16,17,'images/goods/5/17/ceRc3sQHA2QU2.jpg',NULL,NULL,NULL),(17,18,'images/goods/5/18/ceuKa4l1yptM.jpg',NULL,NULL,NULL),(18,18,'images/goods/5/18/ceWVdwkGmJCw6.jpg',NULL,NULL,NULL),(19,18,'images/goods/5/18/ce40fTL2xwaE.jpg',NULL,NULL,NULL),(20,19,'images/goods/5/19/cejKvY6xHHmU.jpg',NULL,NULL,NULL),(21,19,'images/goods/5/19/ceqs2v8KSaN.jpg',NULL,NULL,NULL),(22,19,'images/goods/5/19/ceX0HERcpIt92.jpg',NULL,NULL,NULL),(23,24,'images/goods/5/24/Cg-4jlSZMPWIPVe2AABE0SyjmVYAAPPKwPU_REAAETp536.jpg',NULL,NULL,NULL),(24,25,'images/goods/5/25/cexwplzKT6Sbg.jpg',NULL,NULL,NULL),(25,25,'images/goods/5/25/ceXrZ0wPyNTGQ.jpg',NULL,NULL,NULL),(26,25,'images/goods/5/25/cewHhPUhnfm.jpg',NULL,NULL,NULL),(27,26,'images/goods/5/26/Cg-4jVSZMV2IHV5LAABCsaztT0YAAPPLQDjcJkAAELJ481.jpg',NULL,NULL,NULL),(28,26,'images/goods/5/26/Cg-4jVSZMV2ILo7XAACieqW03LwAAPPLQDhw20AAKKS832.jpg',NULL,NULL,NULL),(29,26,'images/goods/5/26/ce6al4oMpFSw.jpg',NULL,NULL,NULL),(30,26,'images/goods/5/26/cedp6A4X9PtzQ.jpg',NULL,NULL,NULL),(31,26,'images/goods/5/26/celyf1GeGR8TA.jpg',NULL,NULL,NULL),(32,27,'images/goods/5/27/ce8lJKOqzLsk.jpg',NULL,NULL,NULL),(33,27,'images/goods/5/27/ces3Jemw67f1M.jpg',NULL,NULL,NULL),(34,27,'images/goods/5/27/cespaEm8wACQI.jpg',NULL,NULL,NULL),(35,28,'images/goods/5/28/cegL9HubnuZW2.jpg',NULL,NULL,NULL),(36,28,'images/goods/5/28/Cg-4jlSZMIaIYvHCAABCGEyTbrYAAPPKgKBaggAAEIw097.jpg',NULL,NULL,NULL),(37,28,'images/goods/5/28/cejmIsqZsZTs.jpg',NULL,NULL,NULL),(38,28,'images/goods/5/28/ceiAuf1Ky0UuY.jpg',NULL,NULL,NULL),(39,29,'images/goods/5/29/cen6TFxrKeq1E.jpg',NULL,NULL,NULL),(40,29,'images/goods/5/29/cegFclyidxjS2.jpg',NULL,NULL,NULL),(41,29,'images/goods/5/29/ceSDlDxloPMM.jpg',NULL,NULL,NULL),(42,29,'images/goods/5/29/Cg-4WVVv_1iIHMV6AAA0e7xTXvgAAEowgMwHqMAADST818.jpg',NULL,NULL,NULL),(43,29,'images/goods/5/29/Cg-4WlVv_12IXZoJAAC_10sfQnAAAEowgON8C4AAL_v142.jpg',NULL,NULL,NULL),(44,29,'images/goods/5/29/ceZ0lXLE612Yc.jpg',NULL,NULL,NULL),(45,30,'images/goods/5/30/ce77z6RUjyBAs.jpg',NULL,NULL,NULL),(46,30,'images/goods/5/30/cevXDaNHUnoeE.jpg',NULL,NULL,NULL),(47,30,'images/goods/5/30/cenNiCOl2pLMA.jpg',NULL,NULL,NULL),(48,30,'images/goods/5/30/ceMj3VFA50lFw.jpg',NULL,NULL,NULL),(49,30,'images/goods/5/30/ceB7lPaoBhG8s.jpg',NULL,NULL,NULL),(50,31,'images/goods/5/31/cemyv50ZhUXaY.jpg',NULL,NULL,NULL),(51,31,'images/goods/5/31/ceRKEASAQ8mTQ.jpg',NULL,NULL,NULL),(52,31,'images/goods/5/31/Cg-4jVSZCKGITN9ZAABBCZDbvkcAAPOtgEw1rcAAEEh450.jpg',NULL,NULL,NULL),(53,31,'images/goods/5/31/ceUUAYfsQsmg.jpg',NULL,NULL,NULL),(54,31,'images/goods/5/31/ceSFpFtRmcoEw.jpg',NULL,NULL,NULL),(55,32,'images/goods/5/32/Cg-4jlSZCCyIbwc8AABG-ts3MU0AAPOtAOvY3kAAEcS185.jpg',NULL,NULL,NULL),(56,32,'images/goods/5/32/ceXWycQICK97A.jpg',NULL,NULL,NULL),(57,32,'images/goods/5/32/cevYdaj14nQDQ.jpg',NULL,NULL,NULL),(58,32,'images/goods/5/32/ceVovzqKXIDGk.jpg',NULL,NULL,NULL),(59,33,'images/goods/5/33/ceYlu4P4Lkglo.jpg',NULL,NULL,NULL),(60,33,'images/goods/5/33/cexWve9NeG7Q2.jpg',NULL,NULL,NULL),(61,33,'images/goods/5/33/ceA8vJwfip8nc.jpg',NULL,NULL,NULL),(62,34,'images/goods/5/34/ceQzsGECSBZqo.jpg',NULL,NULL,NULL),(63,34,'images/goods/5/34/cehtSLPYe9MM.jpg',NULL,NULL,NULL),(64,34,'images/goods/5/34/ceDUBCAVskVMo.jpg',NULL,NULL,NULL),(65,34,'images/goods/5/34/ce0QEjKLXXfEc.jpg',NULL,NULL,NULL),(66,34,'images/goods/5/34/Cg-4WlVvxfuIWMEuAAAw4WJ0IFoAAEoLQEoGjcAADD5356.jpg',NULL,NULL,NULL),(67,34,'images/goods/5/34/Cg-4WVVvxguIIQhaAADHMzZnQIwAAEoLQIvu9IAAMdL162.jpg',NULL,NULL,NULL),(68,37,'images/goods/5/37/Cg-4jlSZCSOINdWTAABEFYVDAWAAAPOtwE_SMcAAEQt760.jpg',NULL,NULL,NULL),(69,37,'images/goods/5/37/cezQSUarWtvAc.jpg',NULL,NULL,NULL),(70,37,'images/goods/5/37/ceyrQT2we2AwY.jpg',NULL,NULL,NULL),(71,37,'images/goods/5/37/ceNtIv9hdyHk.jpg',NULL,NULL,NULL),(72,37,'images/goods/5/37/celcMsxHSTIEU.jpg',NULL,NULL,NULL),(73,38,'images/goods/5/38/Cg-4jlSbvBqIdL20AABXsJMnwkEAAPVjwGPoxkAAFfI117.jpg',NULL,NULL,NULL),(74,38,'images/goods/5/38/ceyMtjBY65oks.jpg',NULL,NULL,NULL),(75,38,'images/goods/5/38/cevBuXb3Nw1kM.jpg',NULL,NULL,NULL),(76,38,'images/goods/5/38/ceoXgWlxXTbBg.jpg',NULL,NULL,NULL),(77,38,'images/goods/5/38/ceeCSyCf4k3Pk.jpg',NULL,NULL,NULL),(78,39,'images/goods/7/39/Cg-4jlSZCSOINdWTAABEFYVDAWAAAPOtwE_SMcAAEQt760.jpg',NULL,NULL,NULL),(79,39,'images/goods/7/39/cezQSUarWtvAc.jpg',NULL,NULL,NULL),(80,39,'images/goods/7/39/ceNtIv9hdyHk.jpg',NULL,NULL,NULL),(81,39,'images/goods/7/39/celcMsxHSTIEU.jpg',NULL,NULL,NULL),(82,39,'images/goods/7/39/cesaFrNgnFhkM.jpg',NULL,NULL,NULL),(83,40,'images/goods/7/40/cefdKzUiqA20E.jpg',NULL,NULL,NULL),(84,40,'images/goods/7/40/cePyxqEKY5FgE.jpg',NULL,NULL,NULL),(85,40,'images/goods/7/40/ceo6QqZQ81dDM.jpg',NULL,NULL,NULL),(86,40,'images/goods/7/40/cePYpTXw9XQI.jpg',NULL,NULL,NULL),(87,40,'images/goods/7/40/ceqh2Qs2XkhM.jpg',NULL,NULL,NULL),(88,41,'images/goods/7/41/ceMaz8VwyMbjM.jpg',NULL,NULL,NULL),(89,41,'images/goods/7/41/ceblWKUnVn9A.jpg',NULL,NULL,NULL),(90,41,'images/goods/7/41/ceArcjdNOz8dw.jpg',NULL,NULL,NULL),(91,41,'images/goods/7/41/cezp8qoCMW4AY.jpg',NULL,NULL,NULL),(92,41,'images/goods/7/41/Cg-4jlSZL_qIbuSfAABDphSuOh0AAPPKANl5hcAAEO-406.jpg',NULL,NULL,NULL),(93,42,'images/goods/7/42/cen10fvOQon6.jpg',NULL,NULL,NULL),(94,42,'images/goods/7/42/ceqgVI3LHlPGA.jpg',NULL,NULL,NULL),(95,42,'images/goods/7/42/cetM94KvbdpI.jpg',NULL,NULL,NULL),(96,42,'images/goods/7/42/cexnT0oOVejio.jpg',NULL,NULL,NULL),(97,42,'images/goods/7/42/ceNGHHZ3lqyls.jpg',NULL,NULL,NULL),(98,42,'images/goods/7/42/cedo9eRMpGg1U.jpg',NULL,NULL,NULL),(99,43,'images/goods/7/43/Cg-4jVSjwbGIGKaGAAAwPEQw-ucAAPrPACQMlsAADBU253.jpg',NULL,NULL,NULL),(100,43,'images/goods/7/43/ceZRUxGdbUius.jpg',NULL,NULL,NULL),(101,43,'images/goods/7/43/ceUjvy8tMFDjk.jpg',NULL,NULL,NULL),(102,43,'images/goods/7/43/ceaTWnHzIx0IY.jpg',NULL,NULL,NULL),(103,43,'images/goods/7/43/ceJ9doqbPBgdc.jpg',NULL,NULL,NULL),(104,43,'images/goods/7/43/ceroXaQczNBw.jpg',NULL,NULL,NULL),(105,44,'images/goods/7/44/ceb0SGvlNAs.jpg',NULL,NULL,NULL),(106,44,'images/goods/7/44/ceoY3Cl3Pr1zY.jpg',NULL,NULL,NULL),(107,44,'images/goods/7/44/ceZzpxn8mkqM.jpg',NULL,NULL,NULL),(108,44,'images/goods/7/44/cePcEJrMefPNQ.jpg',NULL,NULL,NULL),(109,45,'images/goods/7/45/ceAUmiJpIQo2.jpg',NULL,NULL,NULL),(110,45,'images/goods/7/45/ceMf4jAVj4y6Y.jpg',NULL,NULL,NULL),(111,45,'images/goods/7/45/cekVFH4YdFFtg.jpg',NULL,NULL,NULL),(112,45,'images/goods/7/45/cecflVNUySH7U.jpg',NULL,NULL,NULL),(113,47,'images/goods/7/47/Cg-4zFTKAiWIZ6uTAAAy0f4CkgUAATRFABGP3wAADLp646.jpg',NULL,NULL,NULL),(114,47,'images/goods/7/47/ceymrksNRmqKc.jpg',NULL,NULL,NULL),(115,47,'images/goods/7/47/ceTXNQTkrM6dM.jpg',NULL,NULL,NULL),(116,47,'images/goods/7/47/cevuxK9KC9zxU.jpg',NULL,NULL,NULL),(117,47,'images/goods/7/47/ceSn9P0Fhw7g.jpg',NULL,NULL,NULL),(118,48,'images/goods/7/48/ceCxzWpKZ12oY.jpg',NULL,NULL,NULL),(119,48,'images/goods/7/48/cehtpSIQTmDYs.jpg',NULL,NULL,NULL),(120,48,'images/goods/7/48/ceG6AVl4bmvQ.jpg',NULL,NULL,NULL),(121,48,'images/goods/7/48/ceKhfuoHKRtk6.jpg',NULL,NULL,NULL),(122,49,'images/goods/7/49/ce51GJOkJFSA.jpg',NULL,NULL,NULL),(123,49,'images/goods/7/49/cesg3ybGy0bjA.jpg',NULL,NULL,NULL),(124,49,'images/goods/7/49/ceSDSf1Aq9Q9Y.jpg',NULL,NULL,NULL),(125,49,'images/goods/7/49/ceM9YL4AcoTBU.jpg',NULL,NULL,NULL),(126,50,'images/goods/7/50/ceVYcocL8Muio.jpg',NULL,NULL,NULL),(127,50,'images/goods/7/50/cexnNIiIZ4Dc.jpg',NULL,NULL,NULL),(128,50,'images/goods/7/50/ceRd4cyVDaQhk.jpg',NULL,NULL,NULL),(129,50,'images/goods/7/50/ce6LE5Udns1O2.jpg',NULL,NULL,NULL),(130,51,'images/goods/7/51/ceBbe8uwVwzuA.jpg',NULL,NULL,NULL),(131,51,'images/goods/7/51/ceh6QBL3llCM.jpg',NULL,NULL,NULL),(132,51,'images/goods/7/51/ceLkTPZ3VAog.jpg',NULL,NULL,NULL),(133,51,'images/goods/7/51/ceArBEYTg8IAw.jpg',NULL,NULL,NULL),(134,51,'images/goods/7/51/cerHHuj9qQ2Ns.jpg',NULL,NULL,NULL),(135,52,'images/goods/7/52/ce78gz33Dzw.jpg',NULL,NULL,NULL),(136,52,'images/goods/7/52/ceF5e56kcOnuY.jpg',NULL,NULL,NULL),(137,52,'images/goods/7/52/ceyMmZHvmavY.jpg',NULL,NULL,NULL),(138,52,'images/goods/7/52/cesTLfRdR9B2.jpg',NULL,NULL,NULL),(139,52,'images/goods/7/52/ceyFhv5vitCk6.jpg',NULL,NULL,NULL),(140,53,'images/goods/7/53/ce3zsRG0ZIexE.jpg',NULL,NULL,NULL),(141,53,'images/goods/7/53/ce5BLYfR7gfJE.jpg',NULL,NULL,NULL),(142,53,'images/goods/7/53/cefXehJ9cN6.jpg',NULL,NULL,NULL),(143,53,'images/goods/7/53/ceHwgfSRypcQ.jpg',NULL,NULL,NULL),(144,54,'images/goods/7/54/ce5NvMTJvT66.jpg',NULL,NULL,NULL),(145,54,'images/goods/7/54/ce5YRLHPR2Qyo.jpg',NULL,NULL,NULL),(146,54,'images/goods/7/54/ceb5no6uXSWDg.jpg',NULL,NULL,NULL),(147,54,'images/goods/7/54/cehFC5HhIGJVY.jpg',NULL,NULL,NULL),(148,54,'images/goods/7/54/cezwJuYrMGNBo.jpg',NULL,NULL,NULL),(149,54,'images/goods/7/54/Cg-4V1TIejaIJIRPAAAwmYhy1QYAAUhaAINK3wAADCx185.jpg',NULL,NULL,NULL),(153,56,'images/goods/3/56/net.jpg',NULL,NULL,NULL),(154,56,'images/goods/3/56/screen.jpg',NULL,NULL,NULL);

/*Table structure for table `t_goods_recommend` */

DROP TABLE IF EXISTS `t_goods_recommend`;

CREATE TABLE `t_goods_recommend` (
  `goods_recommend_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `user_id` int(10) unsigned default NULL,
  `judge_content` varchar(200) default NULL,
  `recommend_time` date default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`goods_recommend_id`),
  KEY `t_goods_recommend_fk_goods_id` (`goods_id`),
  KEY `t_goods_recommend_fk_user_id` (`user_id`),
  CONSTRAINT `t_goods_recommend_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_goods_recommend_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_goods_recommend` */

/*Table structure for table `t_hot_words` */

DROP TABLE IF EXISTS `t_hot_words`;

CREATE TABLE `t_hot_words` (
  `hot_words_id` smallint(5) unsigned NOT NULL auto_increment,
  `hot_words` varchar(5) NOT NULL,
  `hot_words_rate` int(10) unsigned default '1',
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`hot_words_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_hot_words` */

/*Table structure for table `t_keyword` */

DROP TABLE IF EXISTS `t_keyword`;

CREATE TABLE `t_keyword` (
  `keyword_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `keyword` varchar(10) default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`keyword_id`),
  KEY `t_keyword_fk_goods_id` (`goods_id`),
  CONSTRAINT `t_keyword_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=gb2312;

/*Data for the table `t_keyword` */

insert  into `t_keyword`(`keyword_id`,`goods_id`,`keyword`,`prepared1`,`prepared2`,`prepared3`) values (1,1,'iphone',NULL,NULL,NULL),(2,1,'iphone6',NULL,NULL,NULL),(3,1,'apple',NULL,NULL,NULL),(4,1,'苹果',NULL,NULL,NULL),(5,2,'iphone',NULL,NULL,NULL),(6,2,'iphone5s',NULL,NULL,NULL),(7,2,'5s',NULL,NULL,NULL),(8,3,'三星',NULL,NULL,NULL),(9,3,'samsung',NULL,NULL,NULL),(10,4,'s6',NULL,NULL,NULL),(11,4,'samsungs6',NULL,NULL,NULL),(12,5,'samsungs6',NULL,NULL,NULL),(13,6,'samsungs6',NULL,NULL,NULL),(14,1,'手机',NULL,NULL,NULL),(15,2,'手机',NULL,NULL,NULL),(16,3,'手机',NULL,NULL,NULL),(17,4,'手机',NULL,NULL,NULL),(18,5,'手机',NULL,NULL,NULL),(19,6,'手机',NULL,NULL,NULL),(20,7,'手机',NULL,NULL,NULL),(21,8,'手机',NULL,NULL,NULL),(22,9,'手机',NULL,NULL,NULL),(23,10,'小米',NULL,NULL,NULL),(24,10,'xiaomi',NULL,NULL,NULL),(25,10,'mi',NULL,NULL,NULL),(26,10,'手机',NULL,NULL,NULL),(27,10,'miui',NULL,NULL,NULL),(28,11,'魅族',NULL,NULL,NULL),(29,11,'魅蓝',NULL,NULL,NULL),(30,11,'meizu',NULL,NULL,NULL),(31,11,'手机',NULL,NULL,NULL),(32,11,'phone',NULL,NULL,NULL),(33,12,'魅族',NULL,NULL,NULL),(34,12,'mei',NULL,NULL,NULL),(35,12,'妹子',NULL,NULL,NULL),(36,12,'手机',NULL,NULL,NULL),(37,12,'meizu',NULL,NULL,NULL),(38,13,'三星',NULL,NULL,NULL),(39,13,'GALAXY S6',NULL,NULL,NULL),(40,13,'手机',NULL,NULL,NULL),(41,13,'phone',NULL,NULL,NULL),(42,13,'GALAXY',NULL,NULL,NULL),(43,14,'手机',NULL,NULL,NULL),(44,14,'阿萨姆',NULL,NULL,NULL),(45,14,'phone',NULL,NULL,NULL),(46,14,'三星',NULL,NULL,NULL),(47,14,'苏打水',NULL,NULL,NULL),(48,15,'三星',NULL,NULL,NULL),(49,15,'S6',NULL,NULL,NULL),(50,15,'三星S6',NULL,NULL,NULL),(51,15,'GALAXY',NULL,NULL,NULL),(52,16,'Edge',NULL,NULL,NULL),(53,16,'三星',NULL,NULL,NULL),(54,16,'GALAXY',NULL,NULL,NULL),(55,16,'S6',NULL,NULL,NULL),(56,17,'三星W2015',NULL,NULL,NULL),(57,17,'三星',NULL,NULL,NULL),(58,17,'W2015',NULL,NULL,NULL),(59,17,'三星W',NULL,NULL,NULL),(60,17,'三星2015',NULL,NULL,NULL),(61,18,'GALAXYA7',NULL,NULL,NULL),(62,18,'三星GALAXYA7',NULL,NULL,NULL),(63,18,'三星A7',NULL,NULL,NULL),(64,18,'A7',NULL,NULL,NULL),(65,18,'三星',NULL,NULL,NULL),(66,19,'华为P8',NULL,NULL,NULL),(67,19,'p8',NULL,NULL,NULL),(68,19,'华为',NULL,NULL,NULL),(69,19,'华为手机',NULL,NULL,NULL),(70,19,'最新华为',NULL,NULL,NULL),(71,20,'华为',NULL,NULL,NULL),(72,20,'华为Mate',NULL,NULL,NULL),(73,20,'华为Mate7',NULL,NULL,NULL),(74,20,'华为7',NULL,NULL,NULL),(75,20,'华为手机',NULL,NULL,NULL),(76,21,'华为',NULL,NULL,NULL),(77,21,'华为Mate',NULL,NULL,NULL),(78,21,'华为Mate7',NULL,NULL,NULL),(79,21,'华为7',NULL,NULL,NULL),(80,21,'华为手机',NULL,NULL,NULL),(81,22,'华为',NULL,NULL,NULL),(82,22,'华为Mate',NULL,NULL,NULL),(83,22,'华为Mate7',NULL,NULL,NULL),(84,22,'华为7',NULL,NULL,NULL),(85,22,'华为手机',NULL,NULL,NULL),(86,23,'华为',NULL,NULL,NULL),(87,23,'华为Mate',NULL,NULL,NULL),(88,23,'华为Mate7',NULL,NULL,NULL),(89,23,'华为7',NULL,NULL,NULL),(90,23,'华为手机',NULL,NULL,NULL),(91,24,'华为荣耀6',NULL,NULL,NULL),(92,24,'华为',NULL,NULL,NULL),(93,24,'华为手机',NULL,NULL,NULL),(94,24,'荣耀6',NULL,NULL,NULL),(95,24,'华为荣耀',NULL,NULL,NULL),(96,25,'华为',NULL,NULL,NULL),(97,25,'华为手机',NULL,NULL,NULL),(98,25,'华为mate',NULL,NULL,NULL),(99,25,'华为mate7',NULL,NULL,NULL),(100,25,'mate7',NULL,NULL,NULL),(101,26,'荣耀畅玩4x',NULL,NULL,NULL),(102,26,'华为手机',NULL,NULL,NULL),(103,26,'华为畅玩',NULL,NULL,NULL),(104,26,'华为4x',NULL,NULL,NULL),(105,26,'畅玩4x',NULL,NULL,NULL),(106,27,'三星',NULL,NULL,NULL),(107,28,'华为荣耀3C',NULL,NULL,NULL),(108,28,'华为',NULL,NULL,NULL),(109,28,'华为手机',NULL,NULL,NULL),(110,28,'华为荣耀',NULL,NULL,NULL),(111,28,'华为3c',NULL,NULL,NULL),(112,29,'OPPO R7',NULL,NULL,NULL),(113,29,'OPPO',NULL,NULL,NULL),(114,29,'R7',NULL,NULL,NULL),(115,29,'OPPO手机',NULL,NULL,NULL),(116,29,'OP R7',NULL,NULL,NULL),(117,30,'OPPO',NULL,NULL,NULL),(118,30,'OPPO手机',NULL,NULL,NULL),(119,30,'OPPO Find ',NULL,NULL,NULL),(120,30,'OPPO 7',NULL,NULL,NULL),(121,31,'OPPO R5',NULL,NULL,NULL),(122,31,'OPPO',NULL,NULL,NULL),(123,31,'OPPO手机',NULL,NULL,NULL),(124,31,'OPPO R',NULL,NULL,NULL),(125,31,'OP 5',NULL,NULL,NULL),(126,32,'OPPO N3',NULL,NULL,NULL),(127,32,'OPPO手机',NULL,NULL,NULL),(128,32,'OPPO',NULL,NULL,NULL),(129,32,'OP N3',NULL,NULL,NULL),(130,32,'OPPO 3',NULL,NULL,NULL),(131,33,'OPPO A31',NULL,NULL,NULL),(132,33,'OPPO',NULL,NULL,NULL),(133,33,'OPPO手机',NULL,NULL,NULL),(134,33,'OPPO A',NULL,NULL,NULL),(135,33,'OPPO 31',NULL,NULL,NULL),(136,34,'vivo X5Pro',NULL,NULL,NULL),(137,34,'vivo',NULL,NULL,NULL),(138,34,'vivo手机',NULL,NULL,NULL),(139,34,'vivo X5',NULL,NULL,NULL),(140,34,'vivo X',NULL,NULL,NULL),(141,35,'',NULL,NULL,NULL),(142,35,'',NULL,NULL,NULL),(143,35,'',NULL,NULL,NULL),(144,35,'',NULL,NULL,NULL),(145,35,'',NULL,NULL,NULL),(146,36,'',NULL,NULL,NULL),(147,36,'',NULL,NULL,NULL),(148,36,'',NULL,NULL,NULL),(149,36,'',NULL,NULL,NULL),(150,36,'',NULL,NULL,NULL),(151,37,'vivo X5L',NULL,NULL,NULL),(152,37,'vivo X5L手机',NULL,NULL,NULL),(153,37,'vivo X5',NULL,NULL,NULL),(154,37,'vivo',NULL,NULL,NULL),(155,37,'vivo手机',NULL,NULL,NULL),(156,38,'vivo X5Max',NULL,NULL,NULL),(157,38,'vivo X5',NULL,NULL,NULL),(158,38,'vivo 手机',NULL,NULL,NULL),(159,38,'vivo',NULL,NULL,NULL),(160,39,'vivo x5sl',NULL,NULL,NULL),(161,39,'vivo手机',NULL,NULL,NULL),(162,39,'vivo ',NULL,NULL,NULL),(163,39,'x5sl',NULL,NULL,NULL),(164,40,'vivo X3L',NULL,NULL,NULL),(165,40,'vivo X3L手机',NULL,NULL,NULL),(166,40,'vivo',NULL,NULL,NULL),(167,40,'vivo手机',NULL,NULL,NULL),(168,40,'X3L',NULL,NULL,NULL),(169,41,'iPhone 6',NULL,NULL,NULL),(170,41,'iPhone ',NULL,NULL,NULL),(171,41,'苹果',NULL,NULL,NULL),(172,41,'苹果手机',NULL,NULL,NULL),(173,41,'苹果6',NULL,NULL,NULL),(174,42,'iPhone 5s',NULL,NULL,NULL),(175,42,'5s',NULL,NULL,NULL),(176,42,'苹果',NULL,NULL,NULL),(177,42,'iPhone',NULL,NULL,NULL),(178,42,'苹果5s',NULL,NULL,NULL),(179,43,'iPhone 4s',NULL,NULL,NULL),(180,43,'iPhone',NULL,NULL,NULL),(181,43,'苹果4s',NULL,NULL,NULL),(182,43,'苹果',NULL,NULL,NULL),(183,44,'iPhone 5c',NULL,NULL,NULL),(184,44,'iPhone',NULL,NULL,NULL),(185,44,'苹果5c',NULL,NULL,NULL),(186,44,'苹果',NULL,NULL,NULL),(187,45,'TCL手机',NULL,NULL,NULL),(188,45,'TCL',NULL,NULL,NULL),(189,45,'TCL ono',NULL,NULL,NULL),(190,46,'',NULL,NULL,NULL),(191,46,'',NULL,NULL,NULL),(192,46,'',NULL,NULL,NULL),(193,46,'',NULL,NULL,NULL),(194,46,'',NULL,NULL,NULL),(195,47,'TCL 么么哒3N',NULL,NULL,NULL),(196,47,'TCL 么么哒',NULL,NULL,NULL),(197,47,'TCL',NULL,NULL,NULL),(198,47,'TCL手机',NULL,NULL,NULL),(199,48,'TCL乐玩',NULL,NULL,NULL),(200,48,'TCL乐玩手机',NULL,NULL,NULL),(201,48,'TCL',NULL,NULL,NULL),(202,48,'TCL手机',NULL,NULL,NULL),(203,48,'乐玩手机',NULL,NULL,NULL),(204,49,'TCL P331M',NULL,NULL,NULL),(205,49,'TCL ',NULL,NULL,NULL),(206,49,'TCL 手机',NULL,NULL,NULL),(207,49,'P331M',NULL,NULL,NULL),(208,50,'酷派锋尚Pro',NULL,NULL,NULL),(209,50,'酷派锋尚Pro手机',NULL,NULL,NULL),(210,50,'酷派',NULL,NULL,NULL),(211,50,'酷派手机',NULL,NULL,NULL),(212,50,'酷派锋尚',NULL,NULL,NULL),(213,51,'酷派 大观铂顿',NULL,NULL,NULL),(214,51,'酷派 大观',NULL,NULL,NULL),(215,51,'酷派',NULL,NULL,NULL),(216,51,'酷派手机',NULL,NULL,NULL),(217,51,'酷派 大观手机',NULL,NULL,NULL),(218,52,'酷派8720L',NULL,NULL,NULL),(219,52,'酷派8720L手机',NULL,NULL,NULL),(220,52,'酷派手机',NULL,NULL,NULL),(221,52,'酷派',NULL,NULL,NULL),(222,52,'酷派8720',NULL,NULL,NULL),(223,53,'酷派S6',NULL,NULL,NULL),(224,53,'酷派S6手机',NULL,NULL,NULL),(225,53,'酷派',NULL,NULL,NULL),(226,53,'酷派手机',NULL,NULL,NULL),(227,53,'酷派6',NULL,NULL,NULL),(228,54,'大神',NULL,NULL,NULL),(229,54,'大神手机',NULL,NULL,NULL),(230,54,'大神X7',NULL,NULL,NULL),(231,54,'大神X7手机',NULL,NULL,NULL),(232,54,'大神7',NULL,NULL,NULL),(238,56,'手机',NULL,NULL,NULL),(239,56,'魅族',NULL,NULL,NULL),(240,56,'三星',NULL,NULL,NULL),(241,56,'phone',NULL,NULL,NULL),(242,56,'meizu',NULL,NULL,NULL);

/*Table structure for table `t_manager` */

DROP TABLE IF EXISTS `t_manager`;

CREATE TABLE `t_manager` (
  `manager_id` smallint(5) unsigned NOT NULL auto_increment,
  `manager_name` varchar(20) default NULL,
  `manager_password` tinyblob,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`manager_id`),
  UNIQUE KEY `manager_name` (`manager_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_manager` */

/*Table structure for table `t_ordergoods` */

DROP TABLE IF EXISTS `t_ordergoods`;

CREATE TABLE `t_ordergoods` (
  `ordergoods_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `user_id` int(10) unsigned default NULL,
  `ordergoods_num` smallint(6) default '1',
  `og_sum_price` double(12,4) default NULL,
  `address_id` int(10) unsigned default NULL,
  `is_judged` tinyint(1) default '1',
  `deal_state_id` tinyint(3) unsigned default NULL,
  `order_time` datetime default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`ordergoods_id`),
  KEY `t_ordergoods_fk_goods_id` (`goods_id`),
  KEY `t_ordergoods_fk_user_id` (`user_id`),
  KEY `t_ordergoods_fk_deal_state_id` (`deal_state_id`),
  CONSTRAINT `t_ordergoods_fk_deal_state_id` FOREIGN KEY (`deal_state_id`) REFERENCES `dm_deal_state` (`deal_state_id`) ON DELETE CASCADE,
  CONSTRAINT `t_ordergoods_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_ordergoods_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=gb2312;

/*Data for the table `t_ordergoods` */

insert  into `t_ordergoods`(`ordergoods_id`,`goods_id`,`user_id`,`ordergoods_num`,`og_sum_price`,`address_id`,`is_judged`,`deal_state_id`,`order_time`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (55,4,2,3,15864.0000,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(56,3,2,1,5488.0000,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(59,5,1,1,4688.0000,3,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(60,4,1,1,5288.0000,3,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(63,5,1,1,4688.0000,2,1,3,NULL,NULL,NULL,NULL,NULL,NULL),(64,4,1,1,5288.0000,2,1,3,NULL,NULL,NULL,NULL,NULL,NULL),(65,4,2,2,10576.0000,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(66,5,1,1,4688.0000,3,1,3,'2015-06-07 00:00:00',NULL,NULL,NULL,NULL,NULL),(67,3,1,1,5488.0000,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(68,1,1,1,5288.0000,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(69,1,1,1,5288.0000,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(70,14,10,1,3212.0000,14,1,1,'2015-06-08 00:00:00',NULL,NULL,NULL,NULL,NULL),(71,12,1,1,1999.9000,2,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(72,23,1,1,2999.0000,13,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(73,18,1,1,2350.0000,4,1,1,'2015-06-09 00:00:00',NULL,NULL,NULL,NULL,NULL),(74,23,1,1,2999.0000,2,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(75,12,1,3,5999.7000,2,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(76,18,1,3,7050.0000,4,1,1,'2015-06-09 00:00:00',NULL,NULL,NULL,NULL,NULL),(77,6,1,2,10976.0000,4,1,1,'2015-06-09 00:00:00',NULL,NULL,NULL,NULL,NULL),(78,1,1,1,5288.0000,5,1,4,NULL,NULL,NULL,NULL,NULL,NULL),(79,23,1,1,2999.0000,5,1,4,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_pay_deal` */

DROP TABLE IF EXISTS `t_pay_deal`;

CREATE TABLE `t_pay_deal` (
  `pay_deal_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned default NULL,
  `amount` double(12,4) default NULL,
  `ordergoods_id` int(10) unsigned default NULL,
  `pay_deal_time` datetime default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`pay_deal_id`),
  KEY `t_pay_deal_fk_user_id` (`user_id`),
  KEY `t_pay_deal_fk_ordergoods_id` (`ordergoods_id`),
  CONSTRAINT `t_pay_deal_fk_ordergoods_id` FOREIGN KEY (`ordergoods_id`) REFERENCES `t_ordergoods` (`ordergoods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_pay_deal_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gb2312;

/*Data for the table `t_pay_deal` */

insert  into `t_pay_deal`(`pay_deal_id`,`user_id`,`amount`,`ordergoods_id`,`pay_deal_time`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,2,223.0000,55,'2015-06-09 13:11:43',NULL,NULL,NULL,NULL,NULL),(2,2,234.0000,56,'2015-05-28 00:00:00',NULL,NULL,NULL,NULL,NULL),(9,1,4688.0000,63,'2015-05-29 01:42:43',NULL,NULL,NULL,NULL,NULL),(10,1,4688.0000,64,'2015-05-29 01:42:43',NULL,NULL,NULL,NULL,NULL),(11,1,4688.0000,66,'2015-06-07 20:37:24',NULL,NULL,NULL,NULL,NULL),(12,1,4688.0000,59,'2015-06-07 21:25:57',NULL,NULL,NULL,NULL,NULL),(13,1,NULL,60,NULL,NULL,NULL,NULL,NULL,NULL),(14,1,1999.9000,71,'2015-06-09 10:21:01',NULL,NULL,NULL,NULL,NULL),(15,1,2999.0000,72,'2015-06-09 10:22:57',NULL,NULL,NULL,NULL,NULL),(16,1,2999.0000,74,'2015-06-09 10:50:42',NULL,NULL,NULL,NULL,NULL),(17,1,2999.0000,75,'2015-06-09 10:50:42',NULL,NULL,NULL,NULL,NULL),(18,1,5288.0000,78,'2015-06-09 13:24:37',NULL,NULL,NULL,NULL,NULL),(19,1,5288.0000,79,'2015-06-09 13:24:37',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_shop` */

DROP TABLE IF EXISTS `t_shop`;

CREATE TABLE `t_shop` (
  `shop_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned default NULL,
  `shop_name` varchar(30) NOT NULL,
  `shop_rate` int(11) default '0',
  `real_name` varchar(20) default NULL,
  `shop_id_card` varchar(20) NOT NULL,
  `shop_phone` varchar(20) default NULL,
  `host_picture` varchar(50) default NULL,
  `shop_picture` varchar(50) default NULL,
  `shop_content` varchar(100) default NULL,
  `shop_state_id` tinyint(3) unsigned default '3',
  `county_id` tinyint(3) unsigned default NULL,
  `street` varchar(50) default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`shop_id`),
  UNIQUE KEY `shop_name` (`shop_name`),
  KEY `t_shop_fk_user_id` (`user_id`),
  KEY `t_shop_fk_shop_state_id` (`shop_state_id`),
  KEY `t_shop_fk_county_id` (`county_id`),
  CONSTRAINT `t_shop_fk_county_id` FOREIGN KEY (`county_id`) REFERENCES `dm_county` (`county_id`) ON DELETE CASCADE,
  CONSTRAINT `t_shop_fk_shop_state_id` FOREIGN KEY (`shop_state_id`) REFERENCES `dm_shop_state` (`shop_state_id`) ON DELETE CASCADE,
  CONSTRAINT `t_shop_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;

/*Data for the table `t_shop` */

insert  into `t_shop`(`shop_id`,`user_id`,`shop_name`,`shop_rate`,`real_name`,`shop_id_card`,`shop_phone`,`host_picture`,`shop_picture`,`shop_content`,`shop_state_id`,`county_id`,`street`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,3,'iPhone手机专卖店',0,NULL,'420922199313433453',NULL,'lily.jpg','lilyshop.jpg','这是Lily的店铺，请多多关照',1,2,'友谊大道',NULL,NULL,NULL,NULL,NULL),(5,5,'三星手机专卖店',0,'宁敦泉','420922199311113974','18202754104','images/seller/5/ndq.jpg',NULL,'这是我的淘宝店铺',1,2,'友谊大道',NULL,NULL,NULL,NULL,NULL),(7,7,'宁敦泉的店铺',0,'宁敦泉','420922199311113973','18202754104','images/seller/7/ndq.jpg',NULL,'宁敦泉的店铺',1,3,'某某公司十八楼',NULL,NULL,NULL,NULL,NULL),(8,8,'mysql',0,'刘博文','42092219931343343','18202755623','images/seller/8/ndq.jpg',NULL,'这是我的店铺，欢迎光临',3,3,'中山大道',NULL,NULL,NULL,NULL,NULL),(9,9,'小名的小店',0,'小明','12321321','1212213321','images/seller/9/net.jpg',NULL,'这是我的店铺，欢迎光临',3,3,'三大三大四',NULL,NULL,NULL,NULL,NULL),(10,1,'宁敦泉的店铺s',0,'宁敦泉','420922199311113932','18202754104','images/seller/1/ndq.jpg',NULL,'这是我的淘宝店铺',3,2,'wqsad',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_shop_audit` */

DROP TABLE IF EXISTS `t_shop_audit`;

CREATE TABLE `t_shop_audit` (
  `shop_audit_id` int(10) unsigned NOT NULL auto_increment,
  `shop_id` int(10) unsigned default NULL,
  `shop_audit_state_id` tinyint(3) unsigned default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`shop_audit_id`),
  KEY `t_shop_audit_fk_shop_id` (`shop_id`),
  KEY `t_shop_audit_fk_shop_audit_state_id` (`shop_audit_state_id`),
  CONSTRAINT `t_shop_audit_fk_shop_audit_state_id` FOREIGN KEY (`shop_audit_state_id`) REFERENCES `dm_shop_audit_state` (`shop_audit_state_id`) ON DELETE CASCADE,
  CONSTRAINT `t_shop_audit_fk_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_shop_audit` */

/*Table structure for table `t_shop_collect` */

DROP TABLE IF EXISTS `t_shop_collect`;

CREATE TABLE `t_shop_collect` (
  `shop_collect_id` int(10) unsigned NOT NULL auto_increment,
  `shop_id` int(10) unsigned default NULL,
  `user_id` int(10) unsigned default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` varchar(20) default NULL,
  `prepared3` varchar(50) default NULL,
  PRIMARY KEY  (`shop_collect_id`),
  KEY `t_shop_collect_fk_shop_id` (`shop_id`),
  KEY `t_shop_collect_fk_user_id` (`user_id`),
  CONSTRAINT `t_shop_collect_fk_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`) ON DELETE CASCADE,
  CONSTRAINT `t_shop_collect_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_shop_collect` */

/*Table structure for table `t_shopping_cart` */

DROP TABLE IF EXISTS `t_shopping_cart`;

CREATE TABLE `t_shopping_cart` (
  `shopping_cart_id` int(10) unsigned NOT NULL auto_increment,
  `goods_id` int(10) unsigned default NULL,
  `user_id` int(10) unsigned default NULL,
  `shopping_cart_num` smallint(6) default '1',
  `sum_price` double(12,4) default NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`shopping_cart_id`),
  KEY `t_shopping_cart_fk_goods_id` (`goods_id`),
  KEY `t_shopping_cart_fk_user_id` (`user_id`),
  CONSTRAINT `t_shopping_cart_fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE CASCADE,
  CONSTRAINT `t_shopping_cart_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_bao_users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

/*Data for the table `t_shopping_cart` */

insert  into `t_shopping_cart`(`shopping_cart_id`,`goods_id`,`user_id`,`shopping_cart_num`,`sum_price`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (3,4,2,2,10576.0000,NULL,NULL,NULL,NULL,NULL),(4,3,2,1,5488.0000,NULL,NULL,NULL,NULL,NULL),(5,3,1,1,5488.0000,NULL,NULL,NULL,NULL,NULL),(6,1,1,1,5288.0000,NULL,NULL,NULL,NULL,NULL),(7,5,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(10,23,1,1,2999.0000,NULL,NULL,NULL,NULL,NULL),(11,43,3,1,1800.0000,NULL,NULL,NULL,NULL,NULL),(12,12,1,3,5999.7000,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_user_address` */

DROP TABLE IF EXISTS `t_user_address`;

CREATE TABLE `t_user_address` (
  `address_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned default NULL,
  `county_id` tinyint(3) unsigned default NULL,
  `street` varchar(50) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `prepared1` int(11) default NULL,
  `prepared2` int(11) default NULL,
  `prepared3` varchar(20) default NULL,
  `prepared4` varchar(50) default NULL,
  `prepared5` varchar(100) default NULL,
  PRIMARY KEY  (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gb2312;

/*Data for the table `t_user_address` */

insert  into `t_user_address`(`address_id`,`user_id`,`county_id`,`street`,`receiver`,`phone_number`,`prepared1`,`prepared2`,`prepared3`,`prepared4`,`prepared5`) values (1,2,1,'和平大道余家头','阿泉','18202754104',NULL,NULL,NULL,NULL,NULL),(2,1,1,'友谊大道湖北大学','宁敦泉','18202754104',NULL,NULL,NULL,NULL,NULL),(3,1,3,'腾讯公司大楼','宁敦泉','18202754104',NULL,NULL,NULL,NULL,NULL),(4,1,2,'江汉路江汉一路428号','Chris','18233444345',NULL,NULL,NULL,NULL,NULL),(5,1,1,'友谊大道湖北大学三期公寓34号','小当家','13871246753',NULL,NULL,NULL,NULL,NULL),(7,2,2,'12','12','12',NULL,NULL,NULL,NULL,NULL),(9,3,2,'友谊大道','Tom','18202754104',NULL,NULL,NULL,NULL,NULL),(11,3,1,'sada','sada','324324324',NULL,NULL,NULL,NULL,NULL),(12,1,1,'sadsa','sadsad','324324',NULL,NULL,NULL,NULL,NULL),(13,1,2,'sadsa','sadsadsd','324324',NULL,NULL,NULL,NULL,NULL),(14,10,1,'sjakdjlajsld','sdkjas','198193',NULL,NULL,NULL,NULL,NULL),(15,1,2,'jlaksjdlkaksjd','akjsdjk','23123',NULL,NULL,NULL,NULL,NULL);

/* Procedure structure for procedure `pro_addtogoodscollect` */

/*!50003 DROP PROCEDURE IF EXISTS  `pro_addtogoodscollect` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_addtogoodscollect`(IN goodsid INT,IN userid INT,OUT json VARCHAR(200))
BEGIN
	DECLARE num INT;
	SELECT COUNT(goods_collect_id) INTO num FROM t_goods_collect WHERE goods_id=goodsid AND user_id=userid;
	IF num = 0 THEN
	INSERT INTO t_goods_collect(goods_id,user_id) VALUES(goodsid,userid);
	SELECT
		CONCAT(
		   CONCAT('{"id":"',goods_id,'",'),
		   CONCAT('"picture":"',goods_main_picture,'",'),
		   CONCAT('"name":"',goods_name,'",'),
		   CONCAT('"price":"',goods_price),'"}')
	INTO json FROM vw_simple_goods WHERE goods_id=goodsid LIMIT 1; 
	ELSE
	SELECT CONCAT(num,"") INTO json;
	END IF;
END */$$
DELIMITER ;

/* Procedure structure for procedure `pro_addtoshoppingcart` */

/*!50003 DROP PROCEDURE IF EXISTS  `pro_addtoshoppingcart` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_addtoshoppingcart`(IN goodsid INT,IN userid INT,IN goodsnum INT,OUT json VARCHAR(200))
BEGIN
	DECLARE num INT;
	DECLARE id INT;
	DECLARE sumprice FLOAT;
	
	SELECT COUNT(shopping_cart_id) INTO num 
	FROM t_shopping_cart 
	WHERE goods_id=goodsid AND user_id=userid;
	
	SELECT goods_price * goodsnum INTO sumprice 
	FROM vw_simple_goods
	WHERE goods_id=goodsid LIMIT 1;
	
	IF num = 0 THEN
	INSERT INTO t_shopping_cart(goods_id,user_id,shopping_cart_num,sum_price) 
	VALUES(goodsid,userid,goodsnum,sumprice);
	ELSE
	SELECT shopping_cart_id INTO id 
	FROM t_shopping_cart 
	WHERE goods_id=goodsid AND user_id=userid;
	
	UPDATE t_shopping_cart 
	SET shopping_cart_num = shopping_cart_num + goodsnum,sum_price = sum_price + sumprice 
	WHERE shopping_cart_id = id;
	
	SELECT sum_price INTO sumprice
	FROM t_shopping_cart
	WHERE shopping_cart_id = id;
	
	END IF;
	
	SELECT
		CONCAT(
		   CONCAT('{"id":"',goods_id,'",'),
		   CONCAT('"picture":"',goods_main_picture,'",'),
		   CONCAT('"name":"',goods_name,'",'),
		   CONCAT('"shopname":"',shop_name,'",'),
		   CONCAT('"sumprice":"',sumprice),'"}')
	INTO json FROM vw_simple_goods WHERE goods_id=goodsid LIMIT 1; 
	
END */$$
DELIMITER ;

/*Table structure for table `goods_view` */

DROP TABLE IF EXISTS `goods_view`;

/*!50001 DROP VIEW IF EXISTS `goods_view` */;
/*!50001 DROP TABLE IF EXISTS `goods_view` */;

/*!50001 CREATE TABLE  `goods_view`(
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `goods_no` varchar(30) ,
 `shop_id` int(10) unsigned ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `goods_sell_num` smallint(6) ,
 `goods_click_rate` int(11) ,
 `goods_content` varchar(500) ,
 `goods_appeared_time` varchar(20) ,
 `goods_main_picture` varchar(100) ,
 `goods_pic1` varchar(100) ,
 `goods_pic2` varchar(100) ,
 `goods_pic3` varchar(100) ,
 `goods_pic4` varchar(100) ,
 `goods_pic5` varchar(100) ,
 `phonebrand` varchar(20) ,
 `operatingsystem` varchar(30) ,
 `cpu` varchar(30) ,
 `phonesize` varchar(20) ,
 `ram` varchar(20) ,
 `rom` varchar(20) ,
 `resolution` varchar(20) ,
 `pre_camera` varchar(20) ,
 `camera` varchar(20) ,
 `phonecolor` varchar(20) ,
 `communication` varchar(30) ,
 `goods_state` varchar(20) 
)*/;

/*Table structure for table `v_comments` */

DROP TABLE IF EXISTS `v_comments`;

/*!50001 DROP VIEW IF EXISTS `v_comments` */;
/*!50001 DROP TABLE IF EXISTS `v_comments` */;

/*!50001 CREATE TABLE  `v_comments`(
 `goods_recommend_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `user_name` varchar(12) ,
 `recommend_time` date ,
 `judge_content` varchar(100) 
)*/;

/*Table structure for table `vw_bao_worker` */

DROP TABLE IF EXISTS `vw_bao_worker`;

/*!50001 DROP VIEW IF EXISTS `vw_bao_worker` */;
/*!50001 DROP TABLE IF EXISTS `vw_bao_worker` */;

/*!50001 CREATE TABLE  `vw_bao_worker`(
 `baoWorkerId` int(10) unsigned ,
 `baoWorkerNo` varchar(20) ,
 `baoWorkerPassword` tinyblob ,
 `baoWorkerIdCard` varchar(20) ,
 `baoWorkerName` varchar(20) ,
 `baoWorkerPhoneNumber` varchar(20) 
)*/;

/*Table structure for table `vw_goods` */

DROP TABLE IF EXISTS `vw_goods`;

/*!50001 DROP VIEW IF EXISTS `vw_goods` */;
/*!50001 DROP TABLE IF EXISTS `vw_goods` */;

/*!50001 CREATE TABLE  `vw_goods`(
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `shop_name` varchar(30) ,
 `user_name` varchar(12) ,
 `goods_no` varchar(30) ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `goods_sell_num` smallint(6) ,
 `goods_click_rate` int(11) ,
 `goods_content` varchar(500) ,
 `goods_appeared_time` varchar(20) ,
 `goods_main_picture` varchar(100) ,
 `goods_pic1` varchar(100) ,
 `goods_pic2` varchar(100) ,
 `goods_pic3` varchar(100) ,
 `goods_pic4` varchar(100) ,
 `goods_pic5` varchar(100) ,
 `phonebrand` varchar(20) ,
 `cpu` varchar(30) ,
 `phonesize` varchar(20) ,
 `operatingsystem` varchar(30) ,
 `ram` varchar(20) ,
 `rom` varchar(20) ,
 `resolution` varchar(20) ,
 `pre_camera` varchar(20) ,
 `camera` varchar(20) ,
 `phonecolor` varchar(20) ,
 `communication` varchar(30) ,
 `goods_state` varchar(20) ,
 `prepared1` int(11) ,
 `prepared2` int(11) ,
 `prepared3` varchar(20) ,
 `prepared4` varchar(50) ,
 `prepared5` varchar(20) ,
 `prepared6` varchar(50) ,
 `prepared7` varchar(100) 
)*/;

/*Table structure for table `vw_goods_collection` */

DROP TABLE IF EXISTS `vw_goods_collection`;

/*!50001 DROP VIEW IF EXISTS `vw_goods_collection` */;
/*!50001 DROP TABLE IF EXISTS `vw_goods_collection` */;

/*!50001 CREATE TABLE  `vw_goods_collection`(
 `goods_collect_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `goods_sell_num` smallint(6) ,
 `goods_main_picture` varchar(100) 
)*/;

/*Table structure for table `vw_goods_judged` */

DROP TABLE IF EXISTS `vw_goods_judged`;

/*!50001 DROP VIEW IF EXISTS `vw_goods_judged` */;
/*!50001 DROP TABLE IF EXISTS `vw_goods_judged` */;

/*!50001 CREATE TABLE  `vw_goods_judged`(
 `goodsJudgedId` int(10) unsigned ,
 `goodsId` int(10) unsigned ,
 `userId` int(10) unsigned ,
 `goodsName` varchar(30) ,
 `goodsPrice` double(12,4) ,
 `goodsNum` smallint(6) ,
 `goodsIntroduce` varchar(50) ,
 `goodsMainPicture` varchar(100) ,
 `judgedContent` varchar(100) ,
 `judgedtime` date 
)*/;

/*Table structure for table `vw_goods_pic` */

DROP TABLE IF EXISTS `vw_goods_pic`;

/*!50001 DROP VIEW IF EXISTS `vw_goods_pic` */;
/*!50001 DROP TABLE IF EXISTS `vw_goods_pic` */;

/*!50001 CREATE TABLE  `vw_goods_pic`(
 `goodsPictureId` int(10) unsigned ,
 `goodsId` int(10) unsigned ,
 `goodsPic` varchar(150) 
)*/;

/*Table structure for table `vw_old_ordergoods` */

DROP TABLE IF EXISTS `vw_old_ordergoods`;

/*!50001 DROP VIEW IF EXISTS `vw_old_ordergoods` */;
/*!50001 DROP TABLE IF EXISTS `vw_old_ordergoods` */;

/*!50001 CREATE TABLE  `vw_old_ordergoods`(
 `ordergoods_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `shop_id` int(10) unsigned ,
 `shop_name` varchar(30) ,
 `address_id` int(10) unsigned ,
 `goods_price` double(12,4) ,
 `ordergoods_num` smallint(6) ,
 `og_sum_price` double(12,4) ,
 `goods_main_picture` varchar(100) ,
 `deal_state_id` tinyint(3) unsigned ,
 `is_judged` tinyint(1) 
)*/;

/*Table structure for table `vw_ordergoods` */

DROP TABLE IF EXISTS `vw_ordergoods`;

/*!50001 DROP VIEW IF EXISTS `vw_ordergoods` */;
/*!50001 DROP TABLE IF EXISTS `vw_ordergoods` */;

/*!50001 CREATE TABLE  `vw_ordergoods`(
 `ordergoods_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `shop_id` int(10) unsigned ,
 `shop_name` varchar(30) ,
 `address_id` int(10) unsigned ,
 `goods_price` double(12,4) ,
 `ordergoods_num` smallint(6) ,
 `og_sum_price` double(12,4) ,
 `goods_main_picture` varchar(100) ,
 `deal_state_id` tinyint(3) unsigned ,
 `is_judged` tinyint(1) ,
 `receiver` varchar(20) ,
 `province` varchar(20) ,
 `city` varchar(20) ,
 `county` varchar(20) ,
 `street` varchar(50) ,
 `phone_number` varchar(20) 
)*/;

/*Table structure for table `vw_pay_deal` */

DROP TABLE IF EXISTS `vw_pay_deal`;

/*!50001 DROP VIEW IF EXISTS `vw_pay_deal` */;
/*!50001 DROP TABLE IF EXISTS `vw_pay_deal` */;

/*!50001 CREATE TABLE  `vw_pay_deal`(
 `pay_deal_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `amount` double(12,4) ,
 `ordergoods_id` int(10) unsigned ,
 `ordergoods_num` smallint(6) ,
 `pay_deal_time` datetime 
)*/;

/*Table structure for table `vw_pay_ordergoods` */

DROP TABLE IF EXISTS `vw_pay_ordergoods`;

/*!50001 DROP VIEW IF EXISTS `vw_pay_ordergoods` */;
/*!50001 DROP TABLE IF EXISTS `vw_pay_ordergoods` */;

/*!50001 CREATE TABLE  `vw_pay_ordergoods`(
 `ordergoods_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `buyer` int(10) unsigned ,
 `ordergoods_num` smallint(6) ,
 `goods_num` smallint(6) ,
 `goods_state_id` tinyint(3) unsigned ,
 `shop_id` int(10) unsigned ,
 `seller` int(10) unsigned 
)*/;

/*Table structure for table `vw_shop` */

DROP TABLE IF EXISTS `vw_shop`;

/*!50001 DROP VIEW IF EXISTS `vw_shop` */;
/*!50001 DROP TABLE IF EXISTS `vw_shop` */;

/*!50001 CREATE TABLE  `vw_shop`(
 `shopId` int(10) unsigned ,
 `userId` int(10) unsigned ,
 `shopName` varchar(30) ,
 `realName` varchar(20) ,
 `shopIdCard` varchar(20) ,
 `shopPhone` varchar(20) ,
 `hostPicture` varchar(50) ,
 `shopPicture` varchar(50) ,
 `shopContent` varchar(100) ,
 `shopStateId` tinyint(3) unsigned ,
 `countyId` tinyint(3) unsigned ,
 `street` varchar(50) ,
 `county` varchar(20) ,
 `city` varchar(20) ,
 `province` varchar(20) 
)*/;

/*Table structure for table `vw_shop_goods` */

DROP TABLE IF EXISTS `vw_shop_goods`;

/*!50001 DROP VIEW IF EXISTS `vw_shop_goods` */;
/*!50001 DROP TABLE IF EXISTS `vw_shop_goods` */;

/*!50001 CREATE TABLE  `vw_shop_goods`(
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `user_id` int(10) unsigned ,
 `shop_id` int(10) unsigned ,
 `goods_click_rate` int(11) ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `goods_sell_num` smallint(6) ,
 `goods_main_picture` varchar(100) 
)*/;

/*Table structure for table `vw_shopping_cart` */

DROP TABLE IF EXISTS `vw_shopping_cart`;

/*!50001 DROP VIEW IF EXISTS `vw_shopping_cart` */;
/*!50001 DROP TABLE IF EXISTS `vw_shopping_cart` */;

/*!50001 CREATE TABLE  `vw_shopping_cart`(
 `shopping_cart_id` int(10) unsigned ,
 `goods_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `shop_id` int(10) unsigned ,
 `shop_name` varchar(30) ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `shopping_cart_num` smallint(6) ,
 `sum_price` double(12,4) ,
 `goods_main_picture` varchar(100) 
)*/;

/*Table structure for table `vw_simple_goods` */

DROP TABLE IF EXISTS `vw_simple_goods`;

/*!50001 DROP VIEW IF EXISTS `vw_simple_goods` */;
/*!50001 DROP TABLE IF EXISTS `vw_simple_goods` */;

/*!50001 CREATE TABLE  `vw_simple_goods`(
 `goods_id` int(10) unsigned ,
 `goods_name` varchar(30) ,
 `shop_name` varchar(30) ,
 `goods_price` double(12,4) ,
 `goods_num` smallint(6) ,
 `goods_introduce` varchar(50) ,
 `goods_sell_num` smallint(6) ,
 `goods_main_picture` varchar(100) ,
 `keyword` varchar(10) 
)*/;

/*Table structure for table `vw_user_address` */

DROP TABLE IF EXISTS `vw_user_address`;

/*!50001 DROP VIEW IF EXISTS `vw_user_address` */;
/*!50001 DROP TABLE IF EXISTS `vw_user_address` */;

/*!50001 CREATE TABLE  `vw_user_address`(
 `address_id` int(10) unsigned ,
 `user_id` int(10) unsigned ,
 `receiver` varchar(20) ,
 `province` varchar(20) ,
 `city` varchar(20) ,
 `county` varchar(20) ,
 `county_id` tinyint(3) unsigned ,
 `city_id` tinyint(3) unsigned ,
 `province_id` tinyint(3) unsigned ,
 `street` varchar(50) ,
 `phone_number` varchar(20) ,
 `prepared1` int(11) ,
 `prepared2` int(11) ,
 `prepared3` varchar(20) ,
 `prepared4` varchar(50) ,
 `prepared5` varchar(100) 
)*/;

/*View structure for view goods_view */

/*!50001 DROP TABLE IF EXISTS `goods_view` */;
/*!50001 DROP VIEW IF EXISTS `goods_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `goods_view` AS select `t_goods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_goods`.`goods_no` AS `goods_no`,`t_goods`.`shop_id` AS `shop_id`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_goods`.`goods_sell_num` AS `goods_sell_num`,`t_goods`.`goods_click_rate` AS `goods_click_rate`,`t_goods`.`goods_content` AS `goods_content`,`t_goods`.`goods_appeared_time` AS `goods_appeared_time`,`t_goods`.`goods_main_picture` AS `goods_main_picture`,`t_goods`.`goods_pic1` AS `goods_pic1`,`t_goods`.`goods_pic2` AS `goods_pic2`,`t_goods`.`goods_pic3` AS `goods_pic3`,`t_goods`.`goods_pic4` AS `goods_pic4`,`t_goods`.`goods_pic5` AS `goods_pic5`,`dm_phonebrand`.`phonebrand` AS `phonebrand`,`dm_operatingsystem`.`operatingsystem` AS `operatingsystem`,`dm_cpu`.`cpu` AS `cpu`,`dm_phonesize`.`phonesize` AS `phonesize`,`dm_ram`.`ram` AS `ram`,`dm_rom`.`rom` AS `rom`,`dm_resolution`.`resolution` AS `resolution`,`dm_pre_camera`.`pre_camera` AS `pre_camera`,`dm_camera`.`camera` AS `camera`,`dm_phonecolor`.`phonecolor` AS `phonecolor`,`dm_communication`.`communication` AS `communication`,`dm_goods_state`.`goods_state` AS `goods_state` from ((((((((((((`t_goods` join `dm_cpu`) join `dm_phonesize`) join `dm_operatingsystem`) join `dm_ram`) join `dm_rom`) join `dm_resolution`) join `dm_pre_camera`) join `dm_camera`) join `dm_phonecolor`) join `dm_communication`) join `dm_goods_state`) join `dm_phonebrand`) where ((`t_goods`.`cpu_id` = `dm_cpu`.`cpu_id`) and (`t_goods`.`phonesize_id` = `dm_phonesize`.`phonesize_id`) and (`t_goods`.`os_id` = `dm_operatingsystem`.`os_id`) and (`t_goods`.`ram_id` = `dm_ram`.`ram_id`) and (`t_goods`.`rom_id` = `dm_rom`.`rom_id`) and (`t_goods`.`resolution_id` = `dm_resolution`.`resolution_id`) and (`t_goods`.`pre_camera_id` = `dm_pre_camera`.`pre_camera_id`) and (`t_goods`.`camera_id` = `dm_camera`.`camera_id`) and (`t_goods`.`phonecolor_id` = `dm_phonecolor`.`phonecolor_id`) and (`t_goods`.`communication_id` = `dm_communication`.`communication_id`) and (`t_goods`.`goods_state_id` = `dm_goods_state`.`goods_state_id`) and (`t_goods`.`phonebrand_id` = `dm_phonebrand`.`phonebrand_id`)) */;

/*View structure for view v_comments */

/*!50001 DROP TABLE IF EXISTS `v_comments` */;
/*!50001 DROP VIEW IF EXISTS `v_comments` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_comments` AS select `t_goods_judged`.`goods_judged_id` AS `goods_recommend_id`,`t_goods_judged`.`goods_id` AS `goods_id`,`t_bao_users`.`user_name` AS `user_name`,`t_goods_judged`.`judged_time` AS `recommend_time`,`t_goods_judged`.`judged_content` AS `judge_content` from (`t_bao_users` join `t_goods_judged`) where (`t_bao_users`.`user_id` = `t_goods_judged`.`user_id`) order by `t_goods_judged`.`judged_time` desc */;

/*View structure for view vw_bao_worker */

/*!50001 DROP TABLE IF EXISTS `vw_bao_worker` */;
/*!50001 DROP VIEW IF EXISTS `vw_bao_worker` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_bao_worker` AS select `t_bao_worker`.`bao_worker_id` AS `baoWorkerId`,`t_bao_worker`.`bao_worker_no` AS `baoWorkerNo`,`t_bao_worker`.`bao_worker_password` AS `baoWorkerPassword`,`t_bao_worker`.`bao_worker_id_card` AS `baoWorkerIdCard`,`t_bao_worker`.`bao_worker_name` AS `baoWorkerName`,`t_bao_worker`.`bao_worker_phone_number` AS `baoWorkerPhoneNumber` from `t_bao_worker` */;

/*View structure for view vw_goods */

/*!50001 DROP TABLE IF EXISTS `vw_goods` */;
/*!50001 DROP VIEW IF EXISTS `vw_goods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_goods` AS select `t_goods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`shop_name` AS `shop_name`,`t_bao_users`.`user_name` AS `user_name`,`t_goods`.`goods_no` AS `goods_no`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_goods`.`goods_sell_num` AS `goods_sell_num`,`t_goods`.`goods_click_rate` AS `goods_click_rate`,`t_goods`.`goods_content` AS `goods_content`,`t_goods`.`goods_appeared_time` AS `goods_appeared_time`,`t_goods`.`goods_main_picture` AS `goods_main_picture`,`t_goods`.`goods_pic1` AS `goods_pic1`,`t_goods`.`goods_pic2` AS `goods_pic2`,`t_goods`.`goods_pic3` AS `goods_pic3`,`t_goods`.`goods_pic4` AS `goods_pic4`,`t_goods`.`goods_pic5` AS `goods_pic5`,`dm_phonebrand`.`phonebrand` AS `phonebrand`,`dm_cpu`.`cpu` AS `cpu`,`dm_phonesize`.`phonesize` AS `phonesize`,`dm_operatingsystem`.`operatingsystem` AS `operatingsystem`,`dm_ram`.`ram` AS `ram`,`dm_rom`.`rom` AS `rom`,`dm_resolution`.`resolution` AS `resolution`,`dm_pre_camera`.`pre_camera` AS `pre_camera`,`dm_camera`.`camera` AS `camera`,`dm_phonecolor`.`phonecolor` AS `phonecolor`,`dm_communication`.`communication` AS `communication`,`dm_goods_state`.`goods_state` AS `goods_state`,`t_goods`.`prepared1` AS `prepared1`,`t_goods`.`prepared2` AS `prepared2`,`t_goods`.`prepared3` AS `prepared3`,`t_goods`.`prepared4` AS `prepared4`,`t_goods`.`prepared5` AS `prepared5`,`t_goods`.`prepared6` AS `prepared6`,`t_goods`.`prepared7` AS `prepared7` from ((((((((((((((`t_goods` join `t_bao_users`) join `t_shop`) join `dm_phonebrand`) join `dm_cpu`) join `dm_phonesize`) join `dm_operatingsystem`) join `dm_ram`) join `dm_rom`) join `dm_resolution`) join `dm_pre_camera`) join `dm_camera`) join `dm_phonecolor`) join `dm_communication`) join `dm_goods_state`) where ((`t_goods`.`phonebrand_id` = `dm_phonebrand`.`phonebrand_id`) and (`t_goods`.`cpu_id` = `dm_cpu`.`cpu_id`) and (`t_goods`.`phonesize_id` = `dm_phonesize`.`phonesize_id`) and (`t_goods`.`os_id` = `dm_operatingsystem`.`os_id`) and (`t_goods`.`ram_id` = `dm_ram`.`ram_id`) and (`t_goods`.`rom_id` = `dm_rom`.`rom_id`) and (`t_goods`.`resolution_id` = `dm_resolution`.`resolution_id`) and (`t_goods`.`pre_camera_id` = `dm_pre_camera`.`pre_camera_id`) and (`t_goods`.`camera_id` = `dm_camera`.`camera_id`) and (`t_goods`.`phonecolor_id` = `dm_phonecolor`.`phonecolor_id`) and (`t_goods`.`communication_id` = `dm_communication`.`communication_id`) and (`t_goods`.`goods_state_id` = `dm_goods_state`.`goods_state_id`) and (`t_goods`.`shop_id` = `t_shop`.`shop_id`) and (`t_shop`.`user_id` = `t_bao_users`.`user_id`)) */;

/*View structure for view vw_goods_collection */

/*!50001 DROP TABLE IF EXISTS `vw_goods_collection` */;
/*!50001 DROP VIEW IF EXISTS `vw_goods_collection` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_goods_collection` AS select `t_goods_collect`.`goods_collect_id` AS `goods_collect_id`,`t_goods`.`goods_id` AS `goods_id`,`t_goods_collect`.`user_id` AS `user_id`,`t_goods`.`goods_name` AS `goods_name`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_goods`.`goods_sell_num` AS `goods_sell_num`,`t_goods`.`goods_main_picture` AS `goods_main_picture` from (`t_goods` join `t_goods_collect`) where (`t_goods`.`goods_id` = `t_goods_collect`.`goods_id`) */;

/*View structure for view vw_goods_judged */

/*!50001 DROP TABLE IF EXISTS `vw_goods_judged` */;
/*!50001 DROP VIEW IF EXISTS `vw_goods_judged` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_goods_judged` AS select `t_goods_judged`.`goods_judged_id` AS `goodsJudgedId`,`t_goods`.`goods_id` AS `goodsId`,`t_goods_judged`.`user_id` AS `userId`,`t_goods`.`goods_name` AS `goodsName`,`t_goods`.`goods_price` AS `goodsPrice`,`t_goods`.`goods_num` AS `goodsNum`,`t_goods`.`goods_introduce` AS `goodsIntroduce`,`t_goods`.`goods_main_picture` AS `goodsMainPicture`,`t_goods_judged`.`judged_content` AS `judgedContent`,`t_goods_judged`.`judged_time` AS `judgedtime` from (`t_goods` join `t_goods_judged`) where (`t_goods`.`goods_id` = `t_goods_judged`.`goods_id`) */;

/*View structure for view vw_goods_pic */

/*!50001 DROP TABLE IF EXISTS `vw_goods_pic` */;
/*!50001 DROP VIEW IF EXISTS `vw_goods_pic` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_goods_pic` AS select `t_goods_picture`.`goods_picture_id` AS `goodsPictureId`,`t_goods_picture`.`goods_id` AS `goodsId`,`t_goods_picture`.`goods_pic` AS `goodsPic` from `t_goods_picture` */;

/*View structure for view vw_old_ordergoods */

/*!50001 DROP TABLE IF EXISTS `vw_old_ordergoods` */;
/*!50001 DROP VIEW IF EXISTS `vw_old_ordergoods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_old_ordergoods` AS select `t_ordergoods`.`ordergoods_id` AS `ordergoods_id`,`t_ordergoods`.`user_id` AS `user_id`,`t_ordergoods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`shop_id` AS `shop_id`,`t_shop`.`shop_name` AS `shop_name`,`t_ordergoods`.`address_id` AS `address_id`,`t_goods`.`goods_price` AS `goods_price`,`t_ordergoods`.`ordergoods_num` AS `ordergoods_num`,`t_ordergoods`.`og_sum_price` AS `og_sum_price`,`t_goods`.`goods_main_picture` AS `goods_main_picture`,`t_ordergoods`.`deal_state_id` AS `deal_state_id`,`t_ordergoods`.`is_judged` AS `is_judged` from ((`t_ordergoods` join `t_goods`) join `t_shop`) where ((`t_goods`.`goods_id` = `t_ordergoods`.`goods_id`) and (`t_goods`.`shop_id` = `t_shop`.`shop_id`)) */;

/*View structure for view vw_ordergoods */

/*!50001 DROP TABLE IF EXISTS `vw_ordergoods` */;
/*!50001 DROP VIEW IF EXISTS `vw_ordergoods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_ordergoods` AS select `t_ordergoods`.`ordergoods_id` AS `ordergoods_id`,`t_ordergoods`.`user_id` AS `user_id`,`t_ordergoods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`shop_id` AS `shop_id`,`t_shop`.`shop_name` AS `shop_name`,`t_ordergoods`.`address_id` AS `address_id`,`t_goods`.`goods_price` AS `goods_price`,`t_ordergoods`.`ordergoods_num` AS `ordergoods_num`,`t_ordergoods`.`og_sum_price` AS `og_sum_price`,`t_goods`.`goods_main_picture` AS `goods_main_picture`,`t_ordergoods`.`deal_state_id` AS `deal_state_id`,`t_ordergoods`.`is_judged` AS `is_judged`,`t_user_address`.`receiver` AS `receiver`,`dm_province`.`province` AS `province`,`dm_city`.`city` AS `city`,`dm_county`.`county` AS `county`,`t_user_address`.`street` AS `street`,`t_user_address`.`phone_number` AS `phone_number` from ((((((`t_ordergoods` join `t_goods`) join `t_shop`) join `dm_province`) join `dm_city`) join `dm_county`) join `t_user_address`) where ((`t_goods`.`goods_id` = `t_ordergoods`.`goods_id`) and (`t_goods`.`shop_id` = `t_shop`.`shop_id`) and (`t_ordergoods`.`address_id` = `t_user_address`.`address_id`) and (`t_user_address`.`county_id` = `dm_county`.`county_id`) and (`dm_county`.`city_id` = `dm_city`.`city_id`) and (`dm_province`.`province_id` = `dm_city`.`province_id`)) */;

/*View structure for view vw_pay_deal */

/*!50001 DROP TABLE IF EXISTS `vw_pay_deal` */;
/*!50001 DROP VIEW IF EXISTS `vw_pay_deal` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_pay_deal` AS select `t_pay_deal`.`pay_deal_id` AS `pay_deal_id`,`t_pay_deal`.`user_id` AS `user_id`,`t_pay_deal`.`amount` AS `amount`,`t_pay_deal`.`ordergoods_id` AS `ordergoods_id`,`t_ordergoods`.`ordergoods_num` AS `ordergoods_num`,`t_pay_deal`.`pay_deal_time` AS `pay_deal_time` from (`t_pay_deal` join `t_ordergoods`) where (`t_pay_deal`.`ordergoods_id` = `t_ordergoods`.`ordergoods_id`) */;

/*View structure for view vw_pay_ordergoods */

/*!50001 DROP TABLE IF EXISTS `vw_pay_ordergoods` */;
/*!50001 DROP VIEW IF EXISTS `vw_pay_ordergoods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_pay_ordergoods` AS select `t_ordergoods`.`ordergoods_id` AS `ordergoods_id`,`t_ordergoods`.`goods_id` AS `goods_id`,`t_ordergoods`.`user_id` AS `buyer`,`t_ordergoods`.`ordergoods_num` AS `ordergoods_num`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_state_id` AS `goods_state_id`,`t_shop`.`shop_id` AS `shop_id`,`t_shop`.`user_id` AS `seller` from ((`t_ordergoods` join `t_goods`) join `t_shop`) where ((`t_ordergoods`.`goods_id` = `t_goods`.`goods_id`) and (`t_goods`.`shop_id` = `t_shop`.`shop_id`)) */;

/*View structure for view vw_shop */

/*!50001 DROP TABLE IF EXISTS `vw_shop` */;
/*!50001 DROP VIEW IF EXISTS `vw_shop` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_shop` AS select `t_shop`.`shop_id` AS `shopId`,`t_shop`.`user_id` AS `userId`,`t_shop`.`shop_name` AS `shopName`,`t_shop`.`real_name` AS `realName`,`t_shop`.`shop_id_card` AS `shopIdCard`,`t_shop`.`shop_phone` AS `shopPhone`,`t_shop`.`host_picture` AS `hostPicture`,`t_shop`.`shop_picture` AS `shopPicture`,`t_shop`.`shop_content` AS `shopContent`,`t_shop`.`shop_state_id` AS `shopStateId`,`dm_county`.`county_id` AS `countyId`,`t_shop`.`street` AS `street`,`dm_county`.`county` AS `county`,`dm_city`.`city` AS `city`,`dm_province`.`province` AS `province` from (((`t_shop` join `dm_county`) join `dm_city`) join `dm_province`) where ((`t_shop`.`county_id` = `dm_county`.`county_id`) and (`dm_county`.`city_id` = `dm_city`.`city_id`) and (`dm_city`.`province_id` = `dm_province`.`province_id`)) */;

/*View structure for view vw_shop_goods */

/*!50001 DROP TABLE IF EXISTS `vw_shop_goods` */;
/*!50001 DROP VIEW IF EXISTS `vw_shop_goods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_shop_goods` AS select `t_goods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`user_id` AS `user_id`,`t_goods`.`shop_id` AS `shop_id`,`t_goods`.`goods_click_rate` AS `goods_click_rate`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_goods`.`goods_sell_num` AS `goods_sell_num`,`t_goods`.`goods_main_picture` AS `goods_main_picture` from (`t_goods` join `t_shop`) where (`t_goods`.`shop_id` = `t_shop`.`shop_id`) */;

/*View structure for view vw_shopping_cart */

/*!50001 DROP TABLE IF EXISTS `vw_shopping_cart` */;
/*!50001 DROP VIEW IF EXISTS `vw_shopping_cart` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_shopping_cart` AS select `t_shopping_cart`.`shopping_cart_id` AS `shopping_cart_id`,`t_goods`.`goods_id` AS `goods_id`,`t_shopping_cart`.`user_id` AS `user_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`shop_id` AS `shop_id`,`t_shop`.`shop_name` AS `shop_name`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_shopping_cart`.`shopping_cart_num` AS `shopping_cart_num`,`t_shopping_cart`.`sum_price` AS `sum_price`,`t_goods`.`goods_main_picture` AS `goods_main_picture` from ((`t_goods` join `t_shop`) join `t_shopping_cart`) where ((`t_goods`.`goods_id` = `t_shopping_cart`.`goods_id`) and (`t_goods`.`shop_id` = `t_shop`.`shop_id`)) */;

/*View structure for view vw_simple_goods */

/*!50001 DROP TABLE IF EXISTS `vw_simple_goods` */;
/*!50001 DROP VIEW IF EXISTS `vw_simple_goods` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_simple_goods` AS select `t_goods`.`goods_id` AS `goods_id`,`t_goods`.`goods_name` AS `goods_name`,`t_shop`.`shop_name` AS `shop_name`,`t_goods`.`goods_price` AS `goods_price`,`t_goods`.`goods_num` AS `goods_num`,`t_goods`.`goods_introduce` AS `goods_introduce`,`t_goods`.`goods_sell_num` AS `goods_sell_num`,`t_goods`.`goods_main_picture` AS `goods_main_picture`,`t_keyword`.`keyword` AS `keyword` from ((`t_goods` join `t_shop`) join `t_keyword`) where ((`t_goods`.`shop_id` = `t_shop`.`shop_id`) and (`t_goods`.`goods_id` = `t_keyword`.`goods_id`)) */;

/*View structure for view vw_user_address */

/*!50001 DROP TABLE IF EXISTS `vw_user_address` */;
/*!50001 DROP VIEW IF EXISTS `vw_user_address` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_user_address` AS select `t_user_address`.`address_id` AS `address_id`,`t_user_address`.`user_id` AS `user_id`,`t_user_address`.`receiver` AS `receiver`,`dm_province`.`province` AS `province`,`dm_city`.`city` AS `city`,`dm_county`.`county` AS `county`,`dm_county`.`county_id` AS `county_id`,`dm_city`.`city_id` AS `city_id`,`dm_province`.`province_id` AS `province_id`,`t_user_address`.`street` AS `street`,`t_user_address`.`phone_number` AS `phone_number`,`t_user_address`.`prepared1` AS `prepared1`,`t_user_address`.`prepared2` AS `prepared2`,`t_user_address`.`prepared3` AS `prepared3`,`t_user_address`.`prepared4` AS `prepared4`,`t_user_address`.`prepared5` AS `prepared5` from (((`t_user_address` join `dm_province`) join `dm_city`) join `dm_county`) where ((`t_user_address`.`county_id` = `dm_county`.`county_id`) and (`dm_county`.`city_id` = `dm_city`.`city_id`) and (`dm_province`.`province_id` = `dm_city`.`province_id`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
