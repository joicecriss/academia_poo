CREATE DATABASE  IF NOT EXISTS `academia_poo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `academia_poo`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: academia_poo
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `academia`
--

DROP TABLE IF EXISTS `academia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academia` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `cnpj` varchar(25) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academia`
--

LOCK TABLES `academia` WRITE;
/*!40000 ALTER TABLE `academia` DISABLE KEYS */;
INSERT INTO `academia` VALUES (1,'Biotech Prime','Rua Ceara, nº 1571, bairro Santa Maria','31.810.569/0001-46','2024-06-23 14:55:53','2024-06-23 15:00:53');
/*!40000 ALTER TABLE `academia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacao_fisica`
--

DROP TABLE IF EXISTS `avaliacao_fisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao_fisica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ultimoTreino` date NOT NULL,
  `peso` double NOT NULL,
  `altura` double NOT NULL,
  `imc` double NOT NULL,
  `satisfacao` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  `pessoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `pessoa_id_idx` (`pessoa_id`),
  CONSTRAINT `pessoa_id` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao_fisica`
--

LOCK TABLES `avaliacao_fisica` WRITE;
/*!40000 ALTER TABLE `avaliacao_fisica` DISABLE KEYS */;
INSERT INTO `avaliacao_fisica` VALUES (1,'2024-06-22',70.9,1.8,21.882716049382715,10,'2024-06-24 01:57:01','2024-06-24 01:57:01',3),(2,'2024-06-30',72.91,1.8,22.503086419753085,9,'2024-06-24 02:00:47','2024-06-25 23:33:56',3);
/*!40000 ALTER TABLE `avaliacao_fisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisao_treino`
--

DROP TABLE IF EXISTS `divisao_treino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `divisao_treino` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(10) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisao_treino`
--

LOCK TABLES `divisao_treino` WRITE;
/*!40000 ALTER TABLE `divisao_treino` DISABLE KEYS */;
INSERT INTO `divisao_treino` VALUES (1,'ABC','ABC 2x e descansa 1x','2024-06-23 19:02:41','2024-06-23 19:02:41'),(2,'ABCD','ABCD 2x e descansa 1x','2024-06-23 19:03:21','2024-06-23 19:03:21'),(3,'ABC','ABC 3x e descansa 1x','2024-06-23 19:03:36','2024-06-23 19:03:36'),(4,'ABCD','ABC 3x e descansa 1x','2024-06-23 19:04:20','2024-06-23 19:04:20');
/*!40000 ALTER TABLE `divisao_treino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisao_treino_musculacao`
--

DROP TABLE IF EXISTS `divisao_treino_musculacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `divisao_treino_musculacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` text NOT NULL,
  `posicao` varchar(2) NOT NULL,
  `divisao_treino_id` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `idMusculacao` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `divisao_treino_id_idx` (`divisao_treino_id`),
  CONSTRAINT `divisao_treino_id` FOREIGN KEY (`divisao_treino_id`) REFERENCES `divisao_treino` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisao_treino_musculacao`
--

LOCK TABLES `divisao_treino_musculacao` WRITE;
/*!40000 ALTER TABLE `divisao_treino_musculacao` DISABLE KEYS */;
INSERT INTO `divisao_treino_musculacao` VALUES (4,'PEITO, OMBRO, TRICEPS','A',1,'2024-06-23 19:58:09','2024-06-23 21:59:31',0),(5,'COSTAS, BICEPS','B',1,'2024-06-23 19:58:09','2024-06-23 22:00:03',0),(6,'PERNA','C',1,'2024-06-23 19:58:09','2024-06-23 19:58:09',0),(7,'Posterior, Quadriceps, Gluteo, Cardio','A',1,'2024-06-23 23:55:03','2024-06-23 23:55:03',657),(8,'Costa, Ombro, Biceps, Cardio, Abdominal','B',1,'2024-06-23 23:55:03','2024-06-23 23:55:03',657),(9,'Peito, Triceps, Cardio','C',1,'2024-06-23 23:55:03','2024-06-24 00:05:16',657);
/*!40000 ALTER TABLE `divisao_treino_musculacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_aluno`
--

DROP TABLE IF EXISTS `entrada_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada_aluno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrada` datetime NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `pessoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `pessoa_id_idx` (`pessoa_id`),
  CONSTRAINT `entrada_pessoa_id` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_aluno`
--

LOCK TABLES `entrada_aluno` WRITE;
/*!40000 ALTER TABLE `entrada_aluno` DISABLE KEYS */;
INSERT INTO `entrada_aluno` VALUES (1,'2024-06-24 21:20:00','2024-06-27 01:27:22','2024-06-27 01:27:22',3),(2,'2024-06-28 23:30:02','2024-06-29 21:03:41','2024-06-29 21:03:41',3);
/*!40000 ALTER TABLE `entrada_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercicio`
--

DROP TABLE IF EXISTS `exercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercicio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercicio`
--

LOCK TABLES `exercicio` WRITE;
/*!40000 ALTER TABLE `exercicio` DISABLE KEYS */;
INSERT INTO `exercicio` VALUES (1,'Supino reto','Barra com pesos tipo anilha','2024-06-23 15:28:55','2024-06-23 15:28:55');
/*!40000 ALTER TABLE `exercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercicio_aplicacao`
--

DROP TABLE IF EXISTS `exercicio_aplicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercicio_aplicacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(250) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercicio_aplicacao`
--

LOCK TABLES `exercicio_aplicacao` WRITE;
/*!40000 ALTER TABLE `exercicio_aplicacao` DISABLE KEYS */;
INSERT INTO `exercicio_aplicacao` VALUES (1,'4 series com 12 repeticoes','2024-06-23 17:36:39','2024-06-23 17:36:39'),(2,'5 series com 8 repeticoes','2024-06-23 17:37:55','2024-06-23 17:37:55');
/*!40000 ALTER TABLE `exercicio_aplicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensalidade_vigente`
--

DROP TABLE IF EXISTS `mensalidade_vigente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensalidade_vigente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `inicio` date NOT NULL,
  `termino` date NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensalidade_vigente`
--

LOCK TABLES `mensalidade_vigente` WRITE;
/*!40000 ALTER TABLE `mensalidade_vigente` DISABLE KEYS */;
INSERT INTO `mensalidade_vigente` VALUES (1,102,'2024-08-10','2025-07-13','2024-06-25 23:50:07','2024-06-25 23:54:57'),(3,99.99,'2024-06-01','2024-06-01','2024-06-30 18:20:22','2024-06-30 18:20:22');
/*!40000 ALTER TABLE `mensalidade_vigente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao_financeira`
--

DROP TABLE IF EXISTS `movimentacao_financeira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimentacao_financeira` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `tipo` enum('1','2') NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao_financeira`
--

LOCK TABLES `movimentacao_financeira` WRITE;
/*!40000 ALTER TABLE `movimentacao_financeira` DISABLE KEYS */;
INSERT INTO `movimentacao_financeira` VALUES (1,250,'2','Conta de Agua - mes 05 de 2024','2024-06-29 23:16:59','2024-06-29 23:24:58'),(2,262,'2','Conta de Agua - mes 04 de 2024','2024-06-29 23:21:28','2024-06-29 23:25:18'),(3,20,'1','Pagamento Mensalidade Aluno: Douglas da Silva','2024-06-30 02:55:31','2024-06-30 02:55:31'),(4,20,'1','Pagamento Mensalidade Aluno: Douglas da Silva','2024-06-30 03:02:51','2024-06-30 03:02:51'),(5,20,'1','Pagamento Mensalidade Aluno: Douglas da Silva','2024-06-30 03:13:54','2024-06-30 03:13:54'),(6,20,'1','Pagamento Mensalidade Aluno: Douglas da Silva','2024-06-30 03:19:54','2024-06-30 03:19:54');
/*!40000 ALTER TABLE `movimentacao_financeira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_mensalidade`
--

DROP TABLE IF EXISTS `pagamento_mensalidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento_mensalidade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataVencimento` date NOT NULL,
  `dataPagamento` date DEFAULT NULL,
  `valorPago` double DEFAULT NULL,
  `data` date DEFAULT NULL,
  `modalidade` enum('1','2','3','4') DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `mensalidade_id` int NOT NULL,
  `pessoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `mensalidade_vingente_id_idx` (`mensalidade_id`),
  KEY `pessoa_id_pagamento_idx` (`pessoa_id`),
  CONSTRAINT `mensalidade_vingente_id` FOREIGN KEY (`mensalidade_id`) REFERENCES `mensalidade_vigente` (`id`),
  CONSTRAINT `pagamento_pessoa_id` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_mensalidade`
--

LOCK TABLES `pagamento_mensalidade` WRITE;
/*!40000 ALTER TABLE `pagamento_mensalidade` DISABLE KEYS */;
INSERT INTO `pagamento_mensalidade` VALUES (1,'2024-07-01','2024-07-01',102,'2024-07-01','1','2024-06-30 03:19:54','2024-06-30 03:19:54',1,3),(2,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:33:58','2024-06-30 21:33:58',1,3),(3,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:34:04','2024-06-30 21:34:04',1,3),(4,'2024-08-04',NULL,NULL,'2024-08-04','4','2024-06-30 21:34:06','2024-06-30 21:34:06',1,3),(5,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:42:50','2024-06-30 21:42:50',1,3),(6,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:42:57','2024-06-30 21:42:57',1,3),(7,'2024-08-04',NULL,NULL,'2024-08-04','4','2024-06-30 21:43:02','2024-06-30 21:43:02',1,3),(8,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:43:40','2024-06-30 21:43:40',1,3),(9,'2024-07-04',NULL,NULL,'2024-07-04','4','2024-06-30 21:43:42','2024-06-30 21:43:42',1,3);
/*!40000 ALTER TABLE `pagamento_mensalidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_recorrente`
--

DROP TABLE IF EXISTS `pagamento_recorrente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento_recorrente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `cartaoCredito` varchar(45) NOT NULL,
  `valor` double NOT NULL,
  `dataInicio` date NOT NULL,
  `numeroMeses` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  `pessoa_id` int NOT NULL,
  `pagamento_mensalidade_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `pg_recorrente_pessoa_id_idx` (`pessoa_id`),
  KEY `pg_mensalidade_id_idx` (`pagamento_mensalidade_id`),
  CONSTRAINT `pg_mensalidade_id` FOREIGN KEY (`pagamento_mensalidade_id`) REFERENCES `pagamento_mensalidade` (`id`),
  CONSTRAINT `pg_recorrente_pessoa_id` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_recorrente`
--

LOCK TABLES `pagamento_recorrente` WRITE;
/*!40000 ALTER TABLE `pagamento_recorrente` DISABLE KEYS */;
INSERT INTO `pagamento_recorrente` VALUES (1,'2024-07-05','1111.1111.1111',99.99,'2024-07-05',1,'2024-06-30 05:46:11','2024-06-30 05:46:11',3,NULL),(2,'2024-07-05','1111.1111.1111.1111',99.99,'2024-07-05',1,'2024-06-30 06:15:23','2024-06-30 06:15:23',3,NULL),(3,'2024-08-05','1111.1111.1111.1111',99.99,'2024-07-05',2,'2024-06-30 06:15:23','2024-06-30 06:15:23',3,NULL),(4,'2024-09-05','1111.1111.1111.1111',99.99,'2024-07-05',3,'2024-06-30 06:15:23','2024-06-30 06:15:23',3,NULL),(5,'2024-10-05','1111.1111.1111.1111',99.99,'2024-07-05',4,'2024-06-30 06:15:23','2024-06-30 06:15:23',3,NULL),(6,'2024-11-05','1111.1111.1111.1111',99.99,'2024-07-05',5,'2024-06-30 06:15:23','2024-06-30 06:15:23',3,NULL);
/*!40000 ALTER TABLE `pagamento_recorrente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  `login` varchar(200) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `tipoUsuario` enum('1','2','3') NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Joice Cristina','Feminino','1998-09-15','joice@gmail.com','joice123','3','702.524.266-73','2024-06-20 03:36:14','2024-06-23 04:28:07'),(3,'Douglas da Silva','Masculino','2001-09-02','douglas@gmail.com','douglas123','1','870.517.920-32','2024-06-24 01:33:04','2024-06-24 01:33:04');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treino`
--

DROP TABLE IF EXISTS `treino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treino` (
  `id` int NOT NULL AUTO_INCREMENT,
  `objetivo` varchar(200) NOT NULL,
  `dataInicio` date NOT NULL,
  `dataTermino` date NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treino`
--

LOCK TABLES `treino` WRITE;
/*!40000 ALTER TABLE `treino` DISABLE KEYS */;
INSERT INTO `treino` VALUES (1,'Ganhar massa muscular','2024-05-18','2024-06-29','2024-06-24 00:59:35','2024-06-24 01:11:44');
/*!40000 ALTER TABLE `treino` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-30 19:03:54
