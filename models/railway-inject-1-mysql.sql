
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
DROP TABLE IF EXISTS `Region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Region` WRITE;
/*!40000 ALTER TABLE `Region` DISABLE KEYS */;
INSERT INTO `Region` VALUES (4),(51);
/*!40000 ALTER TABLE `Region` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entry` int(11) DEFAULT NULL,
  `exit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Route` WRITE;
/*!40000 ALTER TABLE `Route` DISABLE KEYS */;
INSERT INTO `Route` VALUES (3,NULL,2),(50,2,49);
/*!40000 ALTER TABLE `Route` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Segment` WRITE;
/*!40000 ALTER TABLE `Segment` DISABLE KEYS */;
INSERT INTO `Segment` VALUES (7,335),(8,439),(9,90),(10,395),(11,134),(13,137),(14,494),(15,979),(16,815),(17,813),(19,968),(20,197),(21,324),(22,645),(23,517),(25,212),(26,994),(27,472),(28,679),(29,745),(31,542),(32,655),(33,988),(34,483),(35,636),(37,429),(38,892),(39,149),(40,209),(41,721),(43,853),(44,560),(45,190),(46,764),(47,872),(54,232),(55,901),(56,530),(57,494),(58,273),(60,975),(61,386),(62,220),(63,929),(64,152),(66,246),(67,246),(68,437),(69,375),(70,942),(72,301),(73,437),(74,511),(75,625),(76,111),(78,227),(79,145),(80,789),(81,456),(82,434),(84,-822),(85,395),(86,608),(87,829),(88,619),(90,921),(91,329),(92,703),(93,763),(94,890),(96,257),(97,488),(98,366),(99,638),(100,102),(104,386),(105,462),(106,657),(107,931),(108,119),(110,392),(111,782),(112,212),(113,907),(114,118),(116,186),(117,324),(118,881),(119,740),(120,897),(122,250),(123,579),(124,39),(125,91),(126,963),(128,402),(129,337),(130,59),(131,686),(132,212),(134,191),(135,610),(136,374),(137,792),(138,100),(140,11),(141,14),(142,232),(143,605),(144,799),(146,518),(147,90),(148,52),(149,685),(150,404),(152,610),(153,514),(154,883),(155,270),(156,684);
/*!40000 ALTER TABLE `Segment` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Semaphore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Semaphore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `segment` int(11) NOT NULL,
  `signal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Semaphore` WRITE;
/*!40000 ALTER TABLE `Semaphore` DISABLE KEYS */;
INSERT INTO `Semaphore` VALUES (1,0,2),(2,7,2),(49,54,2);
/*!40000 ALTER TABLE `Semaphore` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route` int(11) DEFAULT NULL,
  `region` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Sensor` WRITE;
/*!40000 ALTER TABLE `Sensor` DISABLE KEYS */;
INSERT INTO `Sensor` VALUES (6,3,4),(12,NULL,4),(18,3,4),(24,3,4),(30,3,4),(36,3,4),(42,3,4),(53,50,51),(59,50,51),(65,50,51),(71,50,51),(77,50,51),(83,50,51),(89,50,51),(95,50,51),(103,50,51),(109,50,51),(115,50,51),(121,50,51),(127,NULL,51),(133,50,51),(139,50,51),(145,50,51),(151,50,51);
/*!40000 ALTER TABLE `Sensor` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `Switch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Switch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currentPosition` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `Switch` WRITE;
/*!40000 ALTER TABLE `Switch` DISABLE KEYS */;
INSERT INTO `Switch` VALUES (5,2),(52,1),(102,0);
/*!40000 ALTER TABLE `Switch` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `SwitchPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SwitchPosition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route` int(11) DEFAULT NULL,
  `target` int(11) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `SwitchPosition` WRITE;
/*!40000 ALTER TABLE `SwitchPosition` DISABLE KEYS */;
INSERT INTO `SwitchPosition` VALUES (48,3,5,2),(101,50,52,1),(157,50,102,0);
/*!40000 ALTER TABLE `SwitchPosition` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `TrackElement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrackElement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MEMORY AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `TrackElement` WRITE;
/*!40000 ALTER TABLE `TrackElement` DISABLE KEYS */;
INSERT INTO `TrackElement` VALUES (5,4),(7,4),(8,4),(9,4),(10,4),(11,4),(13,4),(14,4),(15,4),(16,4),(17,4),(19,4),(20,4),(21,4),(22,4),(23,4),(25,4),(26,4),(27,4),(28,4),(29,4),(31,4),(32,4),(33,4),(34,4),(35,4),(37,4),(38,4),(39,4),(40,4),(41,4),(43,4),(44,4),(45,4),(46,4),(47,4),(52,51),(54,51),(55,51),(56,51),(57,51),(58,51),(60,51),(61,51),(62,51),(63,51),(64,51),(66,51),(67,51),(68,51),(69,51),(70,51),(72,51),(73,51),(74,51),(75,51),(76,51),(78,51),(79,51),(80,51),(81,51),(82,51),(84,51),(85,51),(86,51),(87,51),(88,51),(90,51),(91,51),(92,51),(93,51),(94,51),(96,51),(97,51),(98,51),(99,51),(100,51),(102,51),(104,51),(105,51),(106,51),(107,51),(108,51),(110,51),(111,51),(112,51),(113,51),(114,51),(116,51),(117,51),(118,51),(119,51),(120,51),(122,51),(123,51),(124,51),(125,51),(126,51),(128,51),(129,51),(130,51),(131,51),(132,51),(134,51),(135,51),(136,51),(137,51),(138,51),(140,51),(141,51),(142,51),(143,51),(144,51),(146,51),(147,51),(148,51),(149,51),(150,51),(152,51),(153,51),(154,51),(155,51),(156,51);
/*!40000 ALTER TABLE `TrackElement` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `connectsTo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connectsTo` (
  `TrackElement1_id` int(11) NOT NULL,
  `TrackElement2_id` int(11) NOT NULL,
  PRIMARY KEY (`TrackElement1_id`,`TrackElement2_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `connectsTo` WRITE;
/*!40000 ALTER TABLE `connectsTo` DISABLE KEYS */;
INSERT INTO `connectsTo` VALUES (5,7),(7,8),(8,9),(9,10),(10,11),(11,13),(13,14),(14,15),(15,16),(16,17),(17,19),(19,20),(20,21),(21,22),(22,23),(23,25),(25,26),(26,27),(27,28),(28,29),(29,31),(31,32),(32,33),(33,34),(34,35),(35,37),(37,38),(38,39),(39,40),(40,41),(41,43),(43,44),(44,45),(45,46),(46,47),(52,54),(54,55),(55,56),(56,57),(57,58),(58,60),(60,61),(61,62),(62,63),(63,64),(64,66),(66,67),(67,68),(68,69),(69,70),(70,72),(72,73),(73,74),(74,75),(75,76),(76,78),(78,79),(79,80),(80,81),(81,82),(82,84),(84,85),(85,86),(86,87),(87,88),(88,90),(90,91),(91,92),(92,93),(93,94),(94,96),(96,97),(97,98),(98,99),(99,100),(100,102),(102,104);
/*!40000 ALTER TABLE `connectsTo` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `monitoredBy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitoredBy` (
  `TrackElement_id` int(11) NOT NULL,
  `Sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`TrackElement_id`,`Sensor_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `monitoredBy` WRITE;
/*!40000 ALTER TABLE `monitoredBy` DISABLE KEYS */;
INSERT INTO `monitoredBy` VALUES (5,6),(7,6),(8,6),(9,6),(10,6),(11,6),(5,12),(13,12),(14,12),(15,12),(16,12),(17,12),(5,18),(19,18),(20,18),(21,18),(22,18),(23,18),(5,24),(25,24),(26,24),(27,24),(28,24),(29,24),(5,30),(31,30),(32,30),(33,30),(34,30),(35,30),(5,36),(37,36),(38,36),(39,36),(40,36),(41,36),(5,42),(43,42),(44,42),(45,42),(46,42),(47,42),(52,53),(54,53),(55,53),(56,53),(57,53),(58,53),(52,59),(60,59),(61,59),(62,59),(63,59),(64,59),(52,65),(66,65),(67,65),(68,65),(69,65),(70,65),(52,71),(72,71),(73,71),(74,71),(75,71),(76,71),(52,77),(78,77),(79,77),(80,77),(81,77),(82,77),(52,83),(84,83),(85,83),(86,83),(87,83),(88,83),(52,89),(90,89),(91,89),(92,89),(93,89),(94,89),(52,95),(96,95),(97,95),(98,95),(99,95),(100,95),(102,103),(104,103),(105,103),(106,103),(107,103),(108,103),(102,109),(110,109),(111,109),(112,109),(113,109),(114,109),(102,115),(116,115),(117,115),(118,115),(119,115),(120,115),(102,121),(122,121),(123,121),(124,121),(125,121),(126,121),(128,127),(129,127),(130,127),(131,127),(132,127),(102,133),(134,133),(135,133),(136,133),(137,133),(138,133),(102,139),(140,139),(141,139),(142,139),(143,139),(144,139),(102,145),(146,145),(147,145),(148,145),(149,145),(150,145),(102,151),(152,151),(153,151),(154,151),(155,151),(156,151);
/*!40000 ALTER TABLE `monitoredBy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

