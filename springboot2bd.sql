-- MariaDB dump 10.19  Distrib 10.4.22-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cursomc
-- ------------------------------------------------------
-- Server version	10.4.22-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_categorias`
--

DROP TABLE IF EXISTS `tb_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categorias`
--

LOCK TABLES `tb_categorias` WRITE;
/*!40000 ALTER TABLE `tb_categorias` DISABLE KEYS */;
INSERT INTO `tb_categorias` VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama, mesa e banho'),(4,'Eletrônicos'),(5,'Jardinagem'),(6,'Decoração'),(7,'Perfumaria');
/*!40000 ALTER TABLE `tb_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_categorias_produtos`
--

DROP TABLE IF EXISTS `tb_categorias_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_categorias_produtos` (
  `categoria_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  PRIMARY KEY (`categoria_id`,`produto_id`),
  KEY `FKdxe8cytqvvmontixjwkwc6jew` (`produto_id`),
  CONSTRAINT `FKdxe8cytqvvmontixjwkwc6jew` FOREIGN KEY (`produto_id`) REFERENCES `tb_produtos` (`id`),
  CONSTRAINT `FKnb8v9cijxpp2odwjs59k034kl` FOREIGN KEY (`categoria_id`) REFERENCES `tb_categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categorias_produtos`
--

LOCK TABLES `tb_categorias_produtos` WRITE;
/*!40000 ALTER TABLE `tb_categorias_produtos` DISABLE KEYS */;
INSERT INTO `tb_categorias_produtos` VALUES (1,1),(1,2),(1,3),(2,2),(2,4),(3,5),(3,6),(4,1),(4,2),(4,3),(4,7),(5,8),(6,9),(6,10),(7,11);
/*!40000 ALTER TABLE `tb_categorias_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cidades`
--

DROP TABLE IF EXISTS `tb_cidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqoy3fxe4vvhaaf5qsw3dtbag7` (`estado_id`),
  CONSTRAINT `FKqoy3fxe4vvhaaf5qsw3dtbag7` FOREIGN KEY (`estado_id`) REFERENCES `tb_estados` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cidades`
--

LOCK TABLES `tb_cidades` WRITE;
/*!40000 ALTER TABLE `tb_cidades` DISABLE KEYS */;
INSERT INTO `tb_cidades` VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
/*!40000 ALTER TABLE `tb_cidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_clientes`
--

DROP TABLE IF EXISTS `tb_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf_ou_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipo_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9o6dwdqix5udt21xn0tsq4m7m` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_clientes`
--

LOCK TABLES `tb_clientes` WRITE;
/*!40000 ALTER TABLE `tb_clientes` DISABLE KEYS */;
INSERT INTO `tb_clientes` VALUES (1,'36378912377','josehenriquebrito55@gmail.com','Maria Silva','12345',1),(2,'51903479070','josehenriquebrito56@gmail.com','Ana Silva','12345',1);
/*!40000 ALTER TABLE `tb_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_enderecos`
--

DROP TABLE IF EXISTS `tb_enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_enderecos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `cidade_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd1w4po9uu0x5jyjii5hhgvq01` (`cidade_id`),
  KEY `FKtjgooj5176dkbo5n6qv493jxh` (`cliente_id`),
  CONSTRAINT `FKd1w4po9uu0x5jyjii5hhgvq01` FOREIGN KEY (`cidade_id`) REFERENCES `tb_cidades` (`id`),
  CONSTRAINT `FKtjgooj5176dkbo5n6qv493jxh` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_enderecos`
--

LOCK TABLES `tb_enderecos` WRITE;
/*!40000 ALTER TABLE `tb_enderecos` DISABLE KEYS */;
INSERT INTO `tb_enderecos` VALUES (1,'Jardim','38220834','Apto 203','Rua Flores','300',1,1),(2,'Centro','38777012','Sala 800','Avenidas Matos','105',2,1),(3,'Centro','34357012',NULL,'Avenidas Silva','125',2,2);
/*!40000 ALTER TABLE `tb_enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estados`
--

DROP TABLE IF EXISTS `tb_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_estados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estados`
--

LOCK TABLES `tb_estados` WRITE;
/*!40000 ALTER TABLE `tb_estados` DISABLE KEYS */;
INSERT INTO `tb_estados` VALUES (1,'Minas Gerais'),(2,'São Paulo');
/*!40000 ALTER TABLE `tb_estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item_pedido`
--

DROP TABLE IF EXISTS `tb_item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_item_pedido` (
  `desconto` double DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `produto_id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  PRIMARY KEY (`pedido_id`,`produto_id`),
  KEY `FKnpevrq6vpcfcnj4gubf1809ve` (`produto_id`),
  CONSTRAINT `FK84972dyukk617aqp62nvxpj5s` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pedidos` (`id`),
  CONSTRAINT `FKnpevrq6vpcfcnj4gubf1809ve` FOREIGN KEY (`produto_id`) REFERENCES `tb_produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item_pedido`
--

LOCK TABLES `tb_item_pedido` WRITE;
/*!40000 ALTER TABLE `tb_item_pedido` DISABLE KEYS */;
INSERT INTO `tb_item_pedido` VALUES (0,2000,1,1,1),(0,80,2,3,1),(100,800,1,2,2);
/*!40000 ALTER TABLE `tb_item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamentos`
--

DROP TABLE IF EXISTS `tb_pagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamentos` (
  `pedido_id` int(11) NOT NULL,
  `estado_pagamento` int(11) DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKcjlb736jmhj6o7goj41e19796` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamentos`
--

LOCK TABLES `tb_pagamentos` WRITE;
/*!40000 ALTER TABLE `tb_pagamentos` DISABLE KEYS */;
INSERT INTO `tb_pagamentos` VALUES (1,3),(2,1);
/*!40000 ALTER TABLE `tb_pagamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamentos_cartao`
--

DROP TABLE IF EXISTS `tb_pagamentos_cartao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamentos_cartao` (
  `numero_de_parcelas` int(11) DEFAULT NULL,
  `pedido_id` int(11) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKl83s5vqu453qv13ke0s2slldt` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pagamentos` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamentos_cartao`
--

LOCK TABLES `tb_pagamentos_cartao` WRITE;
/*!40000 ALTER TABLE `tb_pagamentos_cartao` DISABLE KEYS */;
INSERT INTO `tb_pagamentos_cartao` VALUES (6,1);
/*!40000 ALTER TABLE `tb_pagamentos_cartao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamentos_com_boleto`
--

DROP TABLE IF EXISTS `tb_pagamentos_com_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamentos_com_boleto` (
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `pedido_id` int(11) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKj9qly1s43ll46yl3e946serxn` FOREIGN KEY (`pedido_id`) REFERENCES `tb_pagamentos` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamentos_com_boleto`
--

LOCK TABLES `tb_pagamentos_com_boleto` WRITE;
/*!40000 ALTER TABLE `tb_pagamentos_com_boleto` DISABLE KEYS */;
INSERT INTO `tb_pagamentos_com_boleto` VALUES ('2017-10-20 00:00:00',NULL,2);
/*!40000 ALTER TABLE `tb_pagamentos_com_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pedidos`
--

DROP TABLE IF EXISTS `tb_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instante` datetime DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjc16u7dot8y3iengmehtvhq46` (`cliente_id`),
  KEY `FKpehiftpmax0ndkfti2g9xfvba` (`endereco_id`),
  CONSTRAINT `FKjc16u7dot8y3iengmehtvhq46` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`),
  CONSTRAINT `FKpehiftpmax0ndkfti2g9xfvba` FOREIGN KEY (`endereco_id`) REFERENCES `tb_enderecos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pedidos`
--

LOCK TABLES `tb_pedidos` WRITE;
/*!40000 ALTER TABLE `tb_pedidos` DISABLE KEYS */;
INSERT INTO `tb_pedidos` VALUES (1,'2017-09-30 10:32:00',1,1),(2,'2017-10-10 19:35:00',1,2);
/*!40000 ALTER TABLE `tb_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_produtos`
--

DROP TABLE IF EXISTS `tb_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_produtos`
--

LOCK TABLES `tb_produtos` WRITE;
/*!40000 ALTER TABLE `tb_produtos` DISABLE KEYS */;
INSERT INTO `tb_produtos` VALUES (1,'Computador',2000),(2,'Impressora',800),(3,'Mouse',80),(4,'Mesa de escritório',300),(5,'Toalha',50),(6,'Colcha',200),(7,'TV True Color',1200),(8,'Roçadeira',80),(9,'Abajour',100),(10,'Pendente',180),(11,'Shampoo',90);
/*!40000 ALTER TABLE `tb_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_telefones`
--

DROP TABLE IF EXISTS `tb_telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_telefones` (
  `cliente_id` int(11) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FK7bbjm8bg83814bse304d1w4du` (`cliente_id`),
  CONSTRAINT `FK7bbjm8bg83814bse304d1w4du` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_telefones`
--

LOCK TABLES `tb_telefones` WRITE;
/*!40000 ALTER TABLE `tb_telefones` DISABLE KEYS */;
INSERT INTO `tb_telefones` VALUES (1,'27363323'),(1,'93838393'),(2,'27389323'),(2,'936473393');
/*!40000 ALTER TABLE `tb_telefones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19  0:00:02
