-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 10.171.247.19    Database: shared_bis_update
-- ------------------------------------------------------
-- Server version   5.7.32-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- Table structure for table `bis_app`
--

DROP TABLE IF EXISTS `bis_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_app` (
  `id` bigint(20) NOT NULL,
  `app_id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `app_id_UNIQUE` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_app`
--

LOCK TABLES `bis_app` WRITE;
/*!40000 ALTER TABLE `bis_app` DISABLE KEYS */;
INSERT INTO `bis_app` VALUES (1,'appA','appA','appA','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(2,'appB','appB','appB','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(3,'pubA','pubA','pubA','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(4,'subB','subB','subB','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(5,'pubB','pubB','pubB','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bis_plugin`
--

DROP TABLE IF EXISTS `bis_plugin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_plugin` (
  `id` bigint(20) NOT NULL,
  `plugin_id` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `break_chain` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_plugin`
--

LOCK TABLES `bis_plugin` WRITE;
/*!40000 ALTER TABLE `bis_plugin` DISABLE KEYS */;
INSERT INTO `bis_plugin` VALUES (1,'pluginA','pluginA','pluginA','INBOUND',1,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(2,'pluginB','pluginB','pluginB','ENDPOINT',2,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(3,'pluginC','pluginC','pluginC','OUTBOUND',3,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(4,'pluginA','pluginA','pluginA','INBOUND',1,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(5,'pluginD','pluginD','pluginD','ENDPOINT',2,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(6,'pluginC','pluginC','pluginC','OUTBOUND',3,1,0,'liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_plugin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bis_plugin_chain`
--

DROP TABLE IF EXISTS `bis_plugin_chain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_plugin_chain` (
  `id` bigint(20) NOT NULL,
  `plugin_chain_id` varchar(45) DEFAULT NULL,
  `plugin_id` varchar(45) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_plugin_chain`
--

LOCK TABLES `bis_plugin_chain` WRITE;
/*!40000 ALTER TABLE `bis_plugin_chain` DISABLE KEYS */;
INSERT INTO `bis_plugin_chain` VALUES (1,'chianA','pluginA','chianA','chianA','liuliu','2020-12-06 22:30:40','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL),(2,'chianA','pluginB','chianA','chianA','liuliu','2020-12-06 22:30:40','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL),(3,'chianA','pluginC','chianA','chianA','liuliu','2020-12-07 09:20:59','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL),(4,'chianB','pluginA','chianB','chianB','liuliu','2020-12-07 09:20:59','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL),(5,'chianB','pluginD','chianB','chianB','liuliu','2020-12-07 09:20:59','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL),(6,'chianB','pluginC','chianB','chianB','liuliu','2020-12-07 09:20:59','liuliu','2020-12-07 09:20:59',0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_plugin_chain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bis_pub_api`
--

DROP TABLE IF EXISTS `bis_pub_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_pub_api` (
  `id` bigint(20) NOT NULL,
  `pub_app_id` varchar(45) DEFAULT NULL,
  `api_id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `plugin_chain_id` varchar(32) DEFAULT NULL,
  `env` varchar(45) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `target` varchar(2048) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_pub_api`
--

LOCK TABLES `bis_pub_api` WRITE;
/*!40000 ALTER TABLE `bis_pub_api` DISABLE KEYS */;
INSERT INTO `bis_pub_api` VALUES (1,'pubA','API_A_ITF','API_A_ITF','API_A_ITF','chianA','ITF','active','http://localhost:8080/hello/mono','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 23:54:41',0,0,NULL,NULL,NULL),(2,'pubA','API_A_PROD','API_A_PROD','API_A_PROD','chianA','PROD','active','http://localhost:8080/hello/mono','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 23:54:41',0,0,NULL,NULL,NULL),(3,'pubA','API_B_ITF','API_B_ITF','API_B_ITF','chianB','ITF','active','http://localhost:8080/hello/mono02','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 23:54:41',0,0,NULL,NULL,NULL),(4,'pubA','API_B_PROD','API_B_PROD','API_B_PROD','chianB','PROD','close','http://localhost:8080/hello/mono02','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 23:54:41',0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_pub_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bis_sub_api`
--

DROP TABLE IF EXISTS `bis_sub_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_sub_api` (
  `id` bigint(20) NOT NULL,
  `sub_app_id` varchar(45) DEFAULT NULL,
  `api_id` varchar(45) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_sub_api`
--

LOCK TABLES `bis_sub_api` WRITE;
/*!40000 ALTER TABLE `bis_sub_api` DISABLE KEYS */;
INSERT INTO `bis_sub_api` VALUES (1,'subB','API_A_ITF','active','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL),(2,'subB','API_B_PROD','active','liuliu','2020-12-06 22:30:40','liuliu','2020-12-06 22:30:40',0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_sub_api` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07 10:21:46