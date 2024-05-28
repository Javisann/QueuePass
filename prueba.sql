-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vips1db
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'prueba@gmail.com',NULL,'Getafe','VIPS','678645127');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pnvtwliis6p05pn6i3ndjrqt2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (4,'CREATE'),(2,'DELETE'),(1,'READ'),(3,'UPDATE');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plates`
--

DROP TABLE IF EXISTS `plates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rg60vcb7tw38jhw9cgfxpt90e` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plates`
--

LOCK TABLES `plates` WRITE;
/*!40000 ALTER TABLE `plates` DISABLE KEYS */;
INSERT INTO `plates` VALUES (11,'Carne de vacuno con huevo frito, salsa holandesa, bacon crujiente, queso cheddar, tomate, lechuga y salsa mayo-sriracha en pan brioche tostado con dos sésamos. Acompañada de guarnición a elegir y salsa baconesa.','http://localhost:8080/files/hb-benedict.jpg','Benedict',13.95,'Hamburguesa'),(12,'Carne de vacuno con cremosa burrata, paleta ibérica crujiente, chimichurri de pistacho, pomodoro seco, tomate, rúcula y mayonesa de chimichurri en pan brioche tostado con dos sésamos. Acompañada de guarnición a elegir y salsa mayonesa de chimichurri.','http://localhost:8080/files/hb-burratisima.jpg','Burratisima',13.95,'Hamburguesa'),(15,'Carne de vacuno, queso provolone y lonchas de queso fundidos, bacon, cebolla caramelizada, salsa mayonesa de chimichurri y rúcula en pan de mollete. Acompañada de guarnición a elegir y mayonesa de chimichurri.','http://localhost:8080/files/hb-pampera.jpg','Pampera',13.95,'Hamburguesa'),(16,'Bizcocho de cacao con mousse de Oreo, galletas Oreo, sirope de chocolate y azúcar.','http://localhost:8080/files/brownie_oreo.jpg','Brownie con Oreo',5.95,'Postre'),(17,'Tortitas recién hechas con Nocilla, plátano, fresas y crujiente de barquillo.','http://localhost:8080/files/tortitas_nocilla.jpg','Tortitas con Nocilla',4.65,'Postre'),(18,'Bizcocho de cacao con mousse de chocolate, helado de chocolate belga, trozos de chocolate y migas de galleta Oreo.','http://localhost:8080/files/chocolate_experience.jpg','Chocolate Experience',5.95,'Postre'),(19,'Chicken Fingers, con salsa BBQ Chipotle, quesadilla de Jamón y Queso, crema agria y guacamole, Alitas Chili BBQ y Nachos Tex-Mex.','http://localhost:8080/files/combo.jpg','Combo de entrantes VIPS',12.95,'Entrante'),(20,'Alitas de pollo marinadas en salsa BBQ Chipotle y salsa sweet chili sobre patatas fritas, acompañadas de salsa ranch','http://localhost:8080/files/alitas_sweet_chili_bbq.jpg','Alitas Chili BBQ',9.95,'Entrante'),(21,'Nachos de maíz, mezcla de quesos, cebolla roja, chorizo picado, chiles jalapeños, cilantro y crema agria.','http://localhost:8080/files/nachos_tex-mex.jpg','Nachos Tex Mex',7.9,'Entrante'),(22,'Cachopo relleno de carne de vacuno, jamón serrano, queso y pimiento del piquillo, huevo frito, guindilla verde, lechuga y mayonesa ligeramente picante, en crujiente pan cristalino. Acompañado de guarnición a elegir y mayonesa ligeramente picante.','http://localhost:8080/files/cachopower.jpg','Sándwich CachoPower',13.9,'Sandwich'),(23,'Pulled pork, queso emmental y queso fundido, jamón cocido, mostaza, mayonesa y pepinillo, en pan de semillas de girasol. Servido con salsa mostaza y miel. Guarnición a elegir, pídelo con patatas gajo.','http://localhost:8080/files/cubano.jpg','Sándwich Cubano',11.5,'Sandwich'),(24,'Pan de maíz con semillas de calabaza y girasol, jamón cocido extra, pechuga de pavo, huevos revueltos y queso cheddar fundido.','http://localhost:8080/files/fundy.jpg','Fundy O\'Clock',9.95,'Sandwich'),(25,'Dados de pollo glaseados en salsa de whiksy y melaza, bacon ahumado, pimiento rojo y cebolla frita, aliñada con salsa ranch sobre una base de mezcla de lechugas con brotes.','http://localhost:8080/files/ensalada_louisiana.jpg','Ensalada Louisiana',8.95,'Ensalada'),(26,'Pechuga de pollo crujiente, queso rallado, crujientes de queso, aliñada con salsa César sobre una base de lechuga romana y otras mezclas, acompañada de barritas de pan de cristal.','http://localhost:8080/files/ensalada_cesar.jpg','Ensalada César',8.95,'Ensalada'),(27,'Aguacate, mango, fresas, queso de cabra, queso feta, tomate cherry, cebolla roja y nueces, aliñada con vinagreta de lima y cilantro sobre una base de quinoa y mezcla de lechugas con brotes.','http://localhost:8080/files/ensalada_los_angeles.jpg','Ensalada Los Angeles',8.95,'Ensalada'),(28,'Refill','http://localhost:8080/files/vaso_pepsi.png','Refrescos',3.75,'Bebida'),(29,'Copa Mahou\nCopa Mahou Sin\nJarra Mahou\nCervezas Especiales','http://localhost:8080/files/cerveza.jpg','Cerveza',3.75,'Bebida'),(31,'Agua Mineral\nGaseosa\nZumos\nNéctar de piña\nZumo de naranja natural','http://localhost:8080/files/otros(1).jpg','Otras bebidas',2.5,'Bebida');
/*!40000 ALTER TABLE `plates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queue`
--

DROP TABLE IF EXISTS `queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `queue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `num_personas` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_49dl7cqd7vvwi982lengkvq23` (`user_id`),
  CONSTRAINT `FKwa29rxwm83sj1xgsegxdyxbt` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queue`
--

LOCK TABLES `queue` WRITE;
/*!40000 ALTER TABLE `queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FKegdk29eiy7mdtefy5c7eirr6e` (`permission_id`),
  CONSTRAINT `FKegdk29eiy7mdtefy5c7eirr6e` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `FKn5fotdgk8d1xvo8nav9uv3muc` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,4);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','USER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2),(3,2),(9,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_no_expired` bit(1) DEFAULT NULL,
  `account_no_locked` bit(1) DEFAULT NULL,
  `credential_no_expired` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '',_binary '',_binary '',_binary '','$2a$10$YqI4tAh7x6AJK6k2acxYYeVpo/rB.Cs.4cXFxJPfMIBe8OzVUivrm','Javier@gmail.com','Javier Sanchez'),(2,_binary '',_binary '',_binary '',_binary '','$2a$10$pQIliJ.JJN8X4yUhncxRzuMU3K8FosJia/thCTHpYviqYLsBhkHGe','Alvaro@gmail.com','Alvaro Perez'),(3,_binary '',_binary '',_binary '',_binary '','$2a$10$gTKHdqgembbnsxYoMCdZr.j3FR1ifc/1GrGkZKrWiIdNJOgHCJ7hK','David@gmail.com','David Garcia'),(9,_binary '',_binary '',_binary '',_binary '','$2a$10$yNmS.hP2c0QaZgrbxZ7gneiYigy7EPUJhLsjE12ijd7cNkfUtVQFu','sergio@gmail.com','Sergio Crespo');
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

-- Dump completed on 2024-05-28 13:48:06
