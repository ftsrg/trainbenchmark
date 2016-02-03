
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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `trainbenchmark` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `trainbenchmark`;
DROP TABLE IF EXISTS `Route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entry` int(11) DEFAULT NULL,
  `exit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Route` WRITE;
/*!40000 ALTER TABLE `Route` DISABLE KEYS */;
/*!40000 ALTER TABLE `Route` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Segment` WRITE;
/*!40000 ALTER TABLE `Segment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Segment` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Semaphore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Semaphore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `signal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Semaphore` WRITE;
/*!40000 ALTER TABLE `Semaphore` DISABLE KEYS */;
/*!40000 ALTER TABLE `Semaphore` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Sensor` WRITE;
/*!40000 ALTER TABLE `Sensor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sensor` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Switch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Switch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currentPosition` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Switch` WRITE;
/*!40000 ALTER TABLE `Switch` DISABLE KEYS */;
/*!40000 ALTER TABLE `Switch` ENABLE KEYS */;
UNLOCK TABLES;
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

LOCK TABLES `SwitchPosition` WRITE;
/*!40000 ALTER TABLE `SwitchPosition` DISABLE KEYS */;
/*!40000 ALTER TABLE `SwitchPosition` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `TrackElement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrackElement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `TrackElement` WRITE;
/*!40000 ALTER TABLE `TrackElement` DISABLE KEYS */;
/*!40000 ALTER TABLE `TrackElement` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `connectsTo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connectsTo` (
  `TrackElement1` int(11) NOT NULL,
  `TrackElement2` int(11) NOT NULL,
  PRIMARY KEY (`TrackElement1`,`TrackElement2`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `connectsTo` WRITE;
/*!40000 ALTER TABLE `connectsTo` DISABLE KEYS */;
/*!40000 ALTER TABLE `connectsTo` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `definedBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `definedBy` (
  `Route_id` int(11) NOT NULL,
  `Sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`Route_id`,`Sensor_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `definedBy` WRITE;
/*!40000 ALTER TABLE `definedBy` DISABLE KEYS */;
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

