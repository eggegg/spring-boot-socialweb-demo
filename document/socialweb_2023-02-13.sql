# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.34)
# Database: socialweb
# Generation Time: 2023-02-13 15:23:05 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table attribute_name
# ------------------------------------------------------------

DROP TABLE IF EXISTS `attribute_name`;

CREATE TABLE `attribute_name` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `attribute_name` WRITE;
/*!40000 ALTER TABLE `attribute_name` DISABLE KEYS */;

INSERT INTO `attribute_name` (`id`, `name`, `create_time`, `update_time`, `category_id`)
VALUES
	(1,'颜色','2023-02-10 15:21:40','2023-02-10 15:21:40',3),
	(2,'尺寸','2023-02-10 15:21:47','2023-02-10 15:21:47',3);

/*!40000 ALTER TABLE `attribute_name` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table attribute_value
# ------------------------------------------------------------

DROP TABLE IF EXISTS `attribute_value`;

CREATE TABLE `attribute_value` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL DEFAULT '',
  `attribute_name_id` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `attribute_value` WRITE;
/*!40000 ALTER TABLE `attribute_value` DISABLE KEYS */;

INSERT INTO `attribute_value` (`id`, `value`, `attribute_name_id`, `create_time`, `update_time`)
VALUES
	(1,'红色',1,'2023-02-10 15:22:29','2023-02-10 15:22:29'),
	(2,'蓝色',1,'2023-02-10 15:22:37','2023-02-10 15:22:37'),
	(3,'黑色',1,'2023-02-10 15:22:40','2023-02-10 15:22:40'),
	(4,'4寸',2,'2023-02-10 15:22:55','2023-02-10 15:22:55'),
	(5,'5寸',2,'2023-02-10 15:22:59','2023-02-10 15:22:59');

/*!40000 ALTER TABLE `attribute_value` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;

INSERT INTO `brand` (`id`, `name`, `create_time`, `update_time`)
VALUES
	(1,'苹果','2023-02-10 15:05:35','2023-02-10 15:05:35');

/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `parent_id_index` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `level`, `parent_id`)
VALUES
	(1,'数码产品','2023-02-10 15:06:48','2023-02-10 15:06:48',1,0),
	(2,'3C','2023-02-10 15:07:05','2023-02-10 15:07:05',2,1),
	(3,'手机','2023-02-10 15:07:24','2023-02-10 15:07:24',3,2);

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `category_id` int(11) NOT NULL DEFAULT '0',
  `seller_id` int(11) NOT NULL DEFAULT '0',
  `brand_id` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`),
  KEY `seller_id_index` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`id`, `name`, `category_id`, `seller_id`, `brand_id`, `create_time`, `update_time`)
VALUES
	(1,'iphone xr',3,1,1,'2023-02-10 15:14:10','2023-02-10 15:14:10'),
	(2,'iphone mini',3,1,1,'2023-02-10 15:14:18','2023-02-10 15:14:18');

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seller
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `seller_name` varchar(255) NOT NULL DEFAULT '',
  `seller_password` varchar(255) NOT NULL DEFAULT '',
  `seller_tel` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;

INSERT INTO `seller` (`id`, `seller_name`, `seller_password`, `seller_tel`, `create_time`, `update_time`, `version`)
VALUES
	(1,'apple','123456','13866666666','2023-02-10 14:41:03','2023-02-10 14:41:03',0);

/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `address` varchar(255) NOT NULL DEFAULT '',
  `business_hour` varchar(255) NOT NULL DEFAULT '',
  `seller_id` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `seller_id_index` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;

INSERT INTO `shop` (`id`, `name`, `address`, `business_hour`, `seller_id`, `create_time`, `update_time`, `version`)
VALUES
	(1,'天环广场苹果门店','天河区天环广场','每天早上8:00 至 晚上10:00',1,'2023-02-10 14:52:50','2023-02-10 14:52:50',0);

/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sku
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sku`;

CREATE TABLE `sku` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL DEFAULT '0',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id_index` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sku` WRITE;
/*!40000 ALTER TABLE `sku` DISABLE KEYS */;

INSERT INTO `sku` (`id`, `item_id`, `price`, `create_time`, `update_time`)
VALUES
	(1,1,8000.00,'2023-02-10 16:07:46','2023-02-10 16:07:46'),
	(2,1,9000.00,'2023-02-10 16:08:51','2023-02-10 16:08:51');

/*!40000 ALTER TABLE `sku` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sku_attribute_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sku_attribute_info`;

CREATE TABLE `sku_attribute_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL DEFAULT '0',
  `attribute_name_id` int(11) NOT NULL DEFAULT '0',
  `attribute_value_id` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sku_id_index` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sku_attribute_info` WRITE;
/*!40000 ALTER TABLE `sku_attribute_info` DISABLE KEYS */;

INSERT INTO `sku_attribute_info` (`id`, `sku_id`, `attribute_name_id`, `attribute_value_id`, `create_time`, `update_time`)
VALUES
	(1,1,1,1,'2023-02-10 16:07:46','2023-02-10 16:07:46'),
	(2,1,2,4,'2023-02-10 16:07:46','2023-02-10 16:07:46'),
	(3,2,1,2,'2023-02-10 16:08:51','2023-02-10 16:08:51'),
	(4,2,2,5,'2023-02-10 16:08:51','2023-02-10 16:08:51');

/*!40000 ALTER TABLE `sku_attribute_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table stock
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL DEFAULT '0',
  `shop_id` int(11) NOT NULL DEFAULT '0',
  `stock_count` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_shop_id_index` (`sku_id`,`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;

INSERT INTO `stock` (`id`, `sku_id`, `shop_id`, `stock_count`, `create_time`, `update_time`)
VALUES
	(1,1,1,398,'2023-02-10 16:19:02','2023-02-10 16:37:22'),
	(3,2,1,400,'2023-02-10 16:19:38','2023-02-10 16:36:40');

/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table test
# ------------------------------------------------------------

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '''''',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;

INSERT INTO `test` (`id`, `name`, `create_time`, `update_time`)
VALUES
	(1,'测试记录','2023-02-11 00:00:00','2023-02-13 00:00:00'),
	(2,'测试记录2','2023-02-11 00:00:00','2023-02-11 00:00:00');

/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table trade
# ------------------------------------------------------------

DROP TABLE IF EXISTS `trade`;

CREATE TABLE `trade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL DEFAULT '0',
  `shop_id` int(11) NOT NULL DEFAULT '0',
  `stock_count` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `single_price` decimal(10,0) DEFAULT NULL,
  `total_price` decimal(10,0) DEFAULT NULL,
  `wechat_trade_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wechat_trade_id` (`wechat_trade_id`),
  KEY `user_id_status_index` (`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;

INSERT INTO `trade` (`id`, `sku_id`, `shop_id`, `stock_count`, `user_id`, `status`, `create_time`, `update_time`, `single_price`, `total_price`, `wechat_trade_id`)
VALUES
	(1,1,1,1,1,2,'2023-02-13 20:43:03','2023-02-13 23:21:50',8000,8000,'111'),
	(3,1,1,1,1,3,'2023-02-13 20:50:36','2023-02-13 23:12:40',8000,8000,'222'),
	(4,1,1,1,1,2,'2023-02-13 21:04:59','2023-02-13 21:13:21',8000,8000,'548387a8-27dd-421c-ba82-c1a9d47fc022');

/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) NOT NULL DEFAULT '',
  `user_password` varchar(60) NOT NULL DEFAULT '',
  `user_tel` varchar(60) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `user_name`, `user_password`, `user_tel`, `create_time`, `update_time`, `version`)
VALUES
	(1,'imooc','123456','13812345678','2023-02-01 00:00:00','2023-02-01 00:00:00',0),
	(2,'eggdev02','888888','13899999999',NULL,NULL,0),
	(3,'egg03','888888','13899999999',NULL,NULL,0),
	(4,'egg04','888888','13899999999',NULL,NULL,0),
	(5,'eggdev05','888888','13899999999',NULL,'2023-02-09 19:09:33',0),
	(6,'egg06','888888','13899999999','2023-02-09 19:08:24','2023-02-09 19:08:24',0);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
