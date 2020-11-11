-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bdlsi
-- ------------------------------------------------------
-- Server version	5.1.70-community-log

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
-- Table structure for table `cad_produto`
--

DROP TABLE IF EXISTS `cad_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cad_produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `preco_custo` double NOT NULL,
  `preco_venda` double NOT NULL,
  `categoria` int(11) DEFAULT NULL,
  `cod_fornecedor` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


create table cad_usuario(
    	id int not null auto_increment,
    nome varchar(30) not null,
    login varchar(30) not null,
    senha varchar(32) not null,
    administrador tinyint(1),
    PRIMARY KEY(id)
);


create table vendasProduto(
	id_VendasProdutos int not null auto_increment,
    pedido int not null,
    produto int not null,
    preço double not null,
    PRIMARY KEY(id_VendasProdutos),
    FOREIGN KEY (pedido) REFERENCES vendas (id_vendas)
);


create table vendas(
	id_vendas int not null auto_increment,
    data date,
    tipo varchar(20),
    PRIMARY KEY(id_vendas)
);


--
-- Dumping data for table `cad_produto`
--

LOCK TABLES `cad_produto` WRITE;
/*!40000 ALTER TABLE `cad_produto` DISABLE KEYS */;
INSERT INTO `cad_produto` VALUES (1,'1','teste',1,1,0,1,0);
/*!40000 ALTER TABLE `cad_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdlsi'
--

--
-- Dumping routines for database 'bdlsi'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-27  8:28:11
