-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` binary(16) NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `order_id` binary(16) NOT NULL,
  `product_id` binary(16) NOT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (_binary ']l<\È&Aú”¡‡€¬j‹\Ú',1,NULL,_binary '›\æ‘A§mEE¸7¹\ìT\ì',_binary '¾X\î\óM4¾\Û*\å˜O',2300.99),(_binary 'O3JÐ²=@»$\ó\ÒÚ‚P',2,NULL,_binary 'qƒcZvNÏ—\è)\÷\íB)C',_binary 'f\ð\×\ás@¨išû\ïBZ',12999.98),(_binary 's\Ü\ÍÓœFHšd\öOS\Äy',3,NULL,_binary '›\æ‘A§mEE¸7¹\ìT\ì',_binary 'f\ð\×\ás@¨išû\ïBZ',19499.97),(_binary 'ŒÀ35*G™}»\÷¹5\0h',1,NULL,_binary '¹ÿ0hLÒ™\égb\ê`',_binary 'f\ð\×\ás@¨išû\ïBZ',6499.99),(_binary '­(U\âifM\ëŸ\æ?«\Ø\ëI',5,NULL,_binary '¹ÿ0hLÒ™\égb\ê`',_binary '¾X\î\óM4¾\Û*\å˜O',11504.95),(_binary '\ö\Ãûq\"GµW­!oûX',1,NULL,_binary 'qƒcZvNÏ—\è)\÷\íB)C',_binary '¾X\î\óM4¾\Û*\å˜O',2300.99);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` enum('CANCELADO','PAGO','PENDENTE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (_binary 'qƒcZvNÏ—\è)\÷\íB)C','2025-07-17 19:05:48.348081','PAGO',15300.97,'2025-07-17 20:01:46.623825',_binary '\Z¼§©¥L\"ŒW\Þ\ÑÅœQ\à'),(_binary '›\æ‘A§mEE¸7¹\ìT\ì','2025-07-17 20:03:24.977854','PENDENTE',21800.96,'2025-07-17 20:03:24.977854',_binary '\Z¼§©¥L\"ŒW\Þ\ÑÅœQ\à'),(_binary '¹ÿ0hLÒ™\égb\ê`','2025-07-17 20:06:07.222980','PENDENTE',18004.94,'2025-07-17 20:06:07.222980',_binary '\Z¼§©¥L\"ŒW\Þ\ÑÅœQ\à');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` binary(16) NOT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `stock_quantity` int NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (_binary 'f\ð\×\ás@¨išû\ïBZ','EletrÃ´nicos','2025-07-17 17:12:30.439714','Agora com SSD 1TB','Notebook Dell Atualizado',6499.99,6,'2025-07-17 20:01:46.624824'),(_binary 'x\ÍÀ^f\ÙGA°-\Ú\Ü\0Â¿\ç','Eletronicos','2025-07-17 22:08:24.704946','Intel i3, 8GB RAM','Notebook Lenovo',2300.99,15,NULL),(_binary '¾X\î\óM4¾\Û*\å˜O','Eletronicos','2025-07-17 19:04:03.438927','Intel i3, 86GB RAM','Notebook Lenovo',2300.99,14,'2025-07-17 20:01:46.624824');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'ADMIN'),(4,'USER'),(5,'ADMIN'),(6,'USER'),(7,'ADMIN'),(8,'USER'),(9,'ADMIN'),(10,'USER'),(11,'ADMIN'),(12,'USER'),(13,'ADMIN'),(14,'USER'),(15,'ADMIN'),(16,'USER'),(17,'ADMIN'),(18,'USER'),(19,'ADMIN'),(20,'USER'),(21,'ADMIN'),(22,'USER'),(23,'ADMIN'),(24,'USER'),(25,'ADMIN'),(26,'USER'),(27,'ADMIN'),(28,'USER'),(29,'ADMIN'),(30,'USER'),(31,'ADMIN'),(32,'USER'),(33,'ADMIN'),(34,'USER'),(35,'ADMIN'),(36,'USER'),(37,'ADMIN'),(38,'USER'),(39,'ADMIN'),(40,'USER'),(41,'ADMIN'),(42,'USER'),(43,'ADMIN'),(44,'USER'),(45,'ADMIN'),(46,'USER'),(47,'ADMIN'),(48,'USER'),(49,'ADMIN'),(50,'USER'),(51,'ADMIN'),(52,'USER'),(53,'ADMIN'),(54,'USER'),(55,'ADMIN'),(56,'USER'),(57,'ADMIN'),(58,'USER'),(59,'ADMIN'),(60,'USER'),(61,'ADMIN'),(62,'USER'),(63,'ADMIN'),(64,'USER'),(65,'ADMIN'),(66,'USER'),(67,'ADMIN'),(68,'USER'),(69,'ADMIN'),(70,'USER'),(71,'ADMIN'),(72,'USER'),(73,'ADMIN'),(74,'USER'),(75,'ADMIN'),(76,'USER'),(77,'ADMIN'),(78,'USER'),(79,'ADMIN'),(80,'USER'),(81,'ADMIN'),(82,'USER'),(83,'ADMIN'),(84,'USER'),(85,'ADMIN'),(86,'USER'),(87,'ADMIN'),(88,'USER'),(89,'ADMIN'),(90,'USER'),(91,'ADMIN'),(92,'USER'),(93,'ADMIN'),(94,'USER'),(95,'ADMIN'),(96,'USER'),(97,'ADMIN'),(98,'USER'),(99,'ADMIN'),(100,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` binary(16) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (_binary '†w¦¨”Oƒ¨\Ö\nsœ¯¢',9),(_binary '\Z¼§©¥L\"ŒW\Þ\ÑÅœQ\à',10),(_binary '†w¦¨”Oƒ¨\Ö\nsœ¯¢',10);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '\Z¼§©¥L\"ŒW\Þ\ÑÅœQ\à','$2a$10$BnN5GDr0xPoQIlFLQpPmJO9D.cdsP8vD6EkQScSEXZ2AHFe8AKdnK','user'),(_binary '†w¦¨”Oƒ¨\Ö\nsœ¯¢','$2a$10$pvX3t1FXqVdTA.wLXcjVPO/GmXvSbGa6ZLAGGhblI1YucmHvhsWxW','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ecommerce_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-18 11:40:03
