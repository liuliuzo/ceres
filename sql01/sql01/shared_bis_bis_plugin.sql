-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 10.171.247.19    Database: shared_bis
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
-- Table structure for table `bis_plugin`
--

DROP TABLE IF EXISTS `bis_plugin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bis_plugin` (
  `plugin_id` bigint(10) NOT NULL,
  `plugin_chain_id` bigint(10) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `order` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `continued` tinyint(1) NOT NULL,
  `status` varchar(16) NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `create_time` timestamp(4) NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_time` timestamp(4) NOT NULL,
  `delete_flag` tinyint(1) NOT NULL,
  `version` int(11) NOT NULL,
  `reserve1` varchar(64) DEFAULT NULL,
  `reserve2` varchar(64) DEFAULT NULL,
  `reserve3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`plugin_id`,`plugin_chain_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bis_plugin`
--

LOCK TABLES `bis_plugin` WRITE;
/*!40000 ALTER TABLE `bis_plugin` DISABLE KEYS */;
/*!40000 ALTER TABLE `bis_plugin` ENABLE KEYS */;
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

-- Dump completed on 2020-12-06 17:26:31
