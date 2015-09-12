-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: trainbenchmark
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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

--
-- Current Database: `trainbenchmark`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `trainbenchmark` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `trainbenchmark`;

--
-- Table structure for table `Route`
--

DROP TABLE IF EXISTS `Route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entry` int(11) DEFAULT NULL,
  `exit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Route`
--

LOCK TABLES `Route` WRITE;
/*!40000 ALTER TABLE `Route` DISABLE KEYS */;
INSERT INTO `Route` VALUES (2,NULL,1),(3,NULL,NULL);
/*!40000 ALTER TABLE `Route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Segment`
--

DROP TABLE IF EXISTS `Segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `segment_length_idx` (`length`)
) ENGINE=MEMORY AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Segment`
--

LOCK TABLES `Segment` WRITE;
/*!40000 ALTER TABLE `Segment` DISABLE KEYS */;
INSERT INTO `Segment` VALUES (6,1),(7,1);
/*!40000 ALTER TABLE `Segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Semaphore`
--

DROP TABLE IF EXISTS `Semaphore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Semaphore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `signal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semaphore`
--

LOCK TABLES `Semaphore` WRITE;
/*!40000 ALTER TABLE `Semaphore` DISABLE KEYS */;
INSERT INTO `Semaphore` VALUES (1,0);
/*!40000 ALTER TABLE `Semaphore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sensor`
--

DROP TABLE IF EXISTS `Sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sensor`
--

LOCK TABLES `Sensor` WRITE;
/*!40000 ALTER TABLE `Sensor` DISABLE KEYS */;
INSERT INTO `Sensor` VALUES (4),(5);
/*!40000 ALTER TABLE `Sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Switch`
--

DROP TABLE IF EXISTS `Switch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Switch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currentPosition` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Switch`
--

LOCK TABLES `Switch` WRITE;
/*!40000 ALTER TABLE `Switch` DISABLE KEYS */;
/*!40000 ALTER TABLE `Switch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SwitchPosition`
--

DROP TABLE IF EXISTS `SwitchPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SwitchPosition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `follows` int(11) DEFAULT NULL,
  `switch` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SwitchPosition`
--

LOCK TABLES `SwitchPosition` WRITE;
/*!40000 ALTER TABLE `SwitchPosition` DISABLE KEYS */;
/*!40000 ALTER TABLE `SwitchPosition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrackElement`
--

DROP TABLE IF EXISTS `TrackElement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrackElement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrackElement`
--

LOCK TABLES `TrackElement` WRITE;
/*!40000 ALTER TABLE `TrackElement` DISABLE KEYS */;
INSERT INTO `TrackElement` VALUES (6,4),(7,5);
/*!40000 ALTER TABLE `TrackElement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connectsTo`
--

DROP TABLE IF EXISTS `connectsTo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connectsTo` (
  `TrackElement1` int(11) NOT NULL,
  `TrackElement2` int(11) NOT NULL,
  PRIMARY KEY (`TrackElement1`,`TrackElement2`),
  KEY `connectsTo_idx1` (`TrackElement1`),
  KEY `connectsTo_idx2` (`TrackElement1`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connectsTo`
--

LOCK TABLES `connectsTo` WRITE;
/*!40000 ALTER TABLE `connectsTo` DISABLE KEYS */;
INSERT INTO `connectsTo` VALUES (6,7);
/*!40000 ALTER TABLE `connectsTo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `definedBy`
--

DROP TABLE IF EXISTS `definedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `definedBy` (
  `Route_id` int(11) NOT NULL,
  `Sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`Route_id`,`Sensor_id`),
  KEY `definedBy_idx` (`Route_id`,`Sensor_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `definedBy`
--

LOCK TABLES `definedBy` WRITE;
/*!40000 ALTER TABLE `definedBy` DISABLE KEYS */;
INSERT INTO `definedBy` VALUES (2,4),(3,5);
/*!40000 ALTER TABLE `definedBy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
