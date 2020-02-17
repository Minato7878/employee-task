-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: employeeDB
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(33) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'A3-A2-A3'),(2,'A3-A2-A2'),(3,'A3-G3'),(4,'A2-B1'),(5,'A2-B3'),(6,'A2-C1'),(7,'A7-B8');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(33) DEFAULT NULL,
  `emp_active` varchar(5) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Micki Bitting','true',1),(2,'Virgie Repp','true',2),(3,'Myung Brunke','false',1),(4,'Roscoe Yerger','true',3),(5,'Lanelle Dyal','false',1),(6,'Zita Shatley','false',6),(7,'Thaddeus Shumake','true',1),(8,'Rosita Bury','true',1),(9,'Robyn Mcfaul','false',3),(10,'Ed Hobbs','false',1),(11,'Marianela Comstock','true',1),(12,'Gracie Stalker','true',2),(13,'Maximina Baskette','true',1),(14,'Clinton Triana','false',2),(15,'Cheryll Caddell','true',1),(16,'Lorean Sheffield','false',1),(17,'Lesia Adamski','true',4),(18,'Raleigh Jessen','false',5),(19,'Yuk Narciso','false',1),(20,'Otilia Abundis','true',1),(21,'Rufina Tew','false',6),(22,'Zenaida Marsland','false',2),(23,'Salvatore Maxwell','true',6),(24,'Margherita Kan','true',2),(25,'Sherwood Hazel','false',5),(26,'Yoshie Perrino','true',7),(27,'Maricruz Garris','false',1),(28,'Karry Turrentine','true',6),(29,'Vincent Dutcher','true',4),(30,'Tess Michell','true',4),(31,'Cherish Stahlman','false',4),(32,'Collene Forester','true',2),(33,'Theron Mcfetridge','true',5),(34,'Lissette Seefeldt','false',4),(35,'Jann Hedgepeth','false',4),(36,'Briana Moroney','true',7),(37,'Elena Mixon','true',3),(38,'Vernia Spece','false',7),(39,'Gayla Lanz','false',2),(40,'Yajaira Allard','false',3),(41,'Loris Stilts','true',7);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(15) DEFAULT NULL,
  `pass` varchar(256) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `user_role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 20:44:34
