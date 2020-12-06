-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 10.171.247.19    Database: shared_bis_update
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='18c5eeb0-ceba-11e9-af33-fa163e48bd73:1-1220,
4757fec7-facf-49f1-b5f1-a06715f12de2:3-1823,
55041e11-eb40-11e9-aabe-fa163e57a5ab:1-1015,
af22952c-9046-4ce1-82c3-b16c7931dcd3:1-69558238';

--
-- Table structure for table `bis_pub_api`
--

DROP TABLE IF EXISTS `bis_pub_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_pub_api` (
  `interface_id` bigint(20) NOT NULL,
  `env` varchar(45) DEFAULT NULL,
  `server_code` varchar(45) DEFAULT NULL,
  `target` varchar(2048) DEFAULT NULL,
  `mq_inbound` varchar(45) DEFAULT NULL,
  `mq_outbound` varchar(45) DEFAULT NULL,
  `protocol` varchar(45) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `method` varchar(128) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`interface_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_pub_api`
--

LOCK TABLES `bis_pub_api` WRITE;
/*!40000 ALTER TABLE `bis_pub_api` DISABLE KEYS */;
INSERT INTO `bis_pub_api` VALUES (1,'DEV','MTH','/hello/mono01','N/A','N/A','HTTP','TEST_http01','POST','active',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'DEV','MTH','/hello/mono02','N/A','N/A','HTTP','TEST_http02','GET','active',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'ITF','MTH','/mono','N/A','N/A','HTTP','TEST_http03','POST','active',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'PROD','MTH','/hello/mono04','N/A','N/A','HTTP','TEST_http04','POST','close',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'PROD','CSW','N/A','127.0.0.1:9876-TopicInbound','127.0.0.1:9876-TopicInbound','MQ','TEST_MQ1','N/A','active',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bis_pub_api` ENABLE KEYS */;
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

-- Dump completed on 2020-12-06 17:26:23
