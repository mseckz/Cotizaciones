CREATE DATABASE  IF NOT EXISTS `cotizaciones` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cotizaciones`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: cotizaciones
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria` (
  `idAuditoria` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `idRegistroTabla` int(11) NOT NULL,
  `nombreTabla` varchar(100) NOT NULL,
  `fechaRegistro` datetime NOT NULL,
  `operacion` varchar(100) NOT NULL,
  PRIMARY KEY (`idAuditoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto` (
  `idAuto` int(11) NOT NULL AUTO_INCREMENT,
  `codigoAuto` char(8) NOT NULL,
  `idTipoAuto` int(11) NOT NULL,
  `descripcionTipo` varchar(50) DEFAULT NULL,
  `idMarca` int(11) NOT NULL,
  `marca` varchar(100) NOT NULL,
  `idModelo` int(11) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `anio` int(11) NOT NULL,
  `numpuertas` int(11) DEFAULT NULL,
  `precio` decimal(10,0) NOT NULL,
  `urlFoto` varchar(100) DEFAULT NULL,
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idAuto`),
  KEY `idTipoAuto` (`idTipoAuto`),
  KEY `idMarca` (`idMarca`),
  KEY `idModelo` (`idModelo`),
  CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`idTipoAuto`) REFERENCES `configvariable` (`idConfigVariable`),
  CONSTRAINT `auto_ibfk_2` FOREIGN KEY (`idMarca`) REFERENCES `configvariable` (`idConfigVariable`),
  CONSTRAINT `auto_ibfk_3` FOREIGN KEY (`idModelo`) REFERENCES `configvariable` (`idConfigVariable`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` VALUES (1,'TY1001',1,'Auto',1,'Auto',1,'Yaris',2015,4,60000,NULL,''),(2,'TY1002',1,'Auto',1,'Auto',1,'Corolla',2010,4,30000,NULL,'');
/*!40000 ALTER TABLE `auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCliente` char(8) NOT NULL,
  `razonSocial` varchar(100) DEFAULT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `idTipoCliente` int(11) NOT NULL,
  `descripTipoCliente` varchar(50) DEFAULT NULL,
  `idTipoDocumento` int(11) NOT NULL,
  `descripTipoDoc` varchar(50) DEFAULT NULL,
  `numeroDocumento` varchar(13) DEFAULT NULL,
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idCliente`),
  KEY `idTipoCliente` (`idTipoCliente`),
  KEY `idTipoDocumento` (`idTipoDocumento`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idTipoCliente`) REFERENCES `configvariable` (`idConfigVariable`),
  CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`idTipoDocumento`) REFERENCES `configvariable` (`idConfigVariable`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'CL001',NULL,'Juan','Perez',1,'Persona Natural',1,'DNI','44251122',''),(2,'12333','asdasd','asdas','dasdas',1,NULL,12,NULL,'123123123',''),(3,'12312321','asdasd','asdasd','asdasd',1,NULL,12,NULL,'123123123',''),(6,'123123','asdasd','sadasd','asdasd',1,NULL,12,'','123123123',''),(7,'12345','asd','asd','asd',1,NULL,13,'Pasaporte','12345258',''),(8,'432423','dsadsa','dsadsa','dsadsa',15,'Persona Natural',12,'DNI','|12345678',''),(9,'987987','sddsa','dsadsa','dsadsa',15,'Persona Natural',13,'Pasaporte','123456',''),(10,'cli002','asd','sda','dsa',15,'Persona Natural',12,'DNI','12345678',''),(11,'CL0005','JO SAC','JO','YEP',15,'Persona Natural',12,'DNI','4886551',''),(12,'CL0006','carlos sac','carlos','cornejo',15,'Persona Natural',13,'Pasaporte','06662222',''),(13,'12345','dsa','dsa','dsa',17,'Persona Juridica',12,'DNI','12345678','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configvariable`
--

DROP TABLE IF EXISTS `configvariable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configvariable` (
  `idConfigVariable` int(11) NOT NULL AUTO_INCREMENT,
  `idPadre` int(11) DEFAULT NULL,
  `codigo` varchar(10) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idConfigVariable`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configvariable`
--

LOCK TABLES `configvariable` WRITE;
/*!40000 ALTER TABLE `configvariable` DISABLE KEYS */;
INSERT INTO `configvariable` VALUES (1,NULL,NULL,'Marca',''),(2,1,NULL,'Toyota',''),(3,NULL,NULL,'TipoAuto',''),(4,3,NULL,'Mecanico',''),(5,NULL,'','Modelo',''),(6,5,NULL,'Sedan',''),(7,NULL,NULL,'Moneda',''),(8,7,NULL,'DOLARES',''),(9,1,NULL,'Kia',''),(10,1,NULL,'Kia',''),(11,NULL,NULL,'TipoDocumento',''),(12,11,NULL,'DNI',''),(13,11,NULL,'Pasaporte',''),(14,NULL,NULL,'TipoCliente',''),(15,14,NULL,'Persona Natural',''),(17,14,NULL,'Persona Juridica',''),(18,NULL,NULL,'TipoAuto',''),(19,3,NULL,'dsa','');
/*!40000 ALTER TABLE `configvariable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion` (
  `idCotizacion` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCotizacion` char(8) NOT NULL,
  `idVendedor` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idTipoMoneda` int(11) NOT NULL,
  `descripTipoMoneda` varchar(50) DEFAULT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  `importe` decimal(10,0) DEFAULT NULL,
  `flagAprobado` bit(1) DEFAULT b'0',
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idCotizacion`),
  KEY `idVendedor` (`idVendedor`),
  KEY `idCliente` (`idCliente`),
  KEY `idTipoMoneda` (`idTipoMoneda`),
  CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`idVendedor`) REFERENCES `vendedor` (`idVendedor`),
  CONSTRAINT `cotizacion_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `cotizacion_ibfk_4` FOREIGN KEY (`idTipoMoneda`) REFERENCES `configvariable` (`idConfigVariable`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
INSERT INTO `cotizacion` VALUES (1,'COT001',1,1,1,'DOLARES',20000,20000,'',''),(2,'COT002',1,1,1,'DOLARES',50000,50000,'\0',''),(3,'COT003',1,1,8,NULL,NULL,60000,'\0',''),(4,'COT004',1,2,8,NULL,NULL,90000,'\0',''),(5,'COT005',1,1,8,'DOLARES',NULL,30000,'\0',''),(6,'COT006',1,1,8,'DOLARES',NULL,60000,'\0',''),(7,'COT007',1,1,8,'DOLARES',NULL,120000,'\0','');
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecotizacion`
--

DROP TABLE IF EXISTS `detallecotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallecotizacion` (
  `idDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idCotizacion` int(11) DEFAULT NULL,
  `idAuto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `idCotizacion` (`idCotizacion`),
  KEY `idAuto` (`idAuto`),
  CONSTRAINT `detallecotizacion_ibfk_1` FOREIGN KEY (`idCotizacion`) REFERENCES `cotizacion` (`idCotizacion`),
  CONSTRAINT `detallecotizacion_ibfk_2` FOREIGN KEY (`idAuto`) REFERENCES `auto` (`idAuto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecotizacion`
--

LOCK TABLES `detallecotizacion` WRITE;
/*!40000 ALTER TABLE `detallecotizacion` DISABLE KEYS */;
INSERT INTO `detallecotizacion` VALUES (1,3,1,1,60000.00,60000.00),(2,4,2,1,30000.00,30000.00),(3,4,1,1,60000.00,60000.00),(4,5,2,1,30000.00,30000.00),(5,6,2,2,30000.00,60000.00),(6,7,2,2,30000.00,60000.00),(7,7,1,1,60000.00,60000.00);
/*!40000 ALTER TABLE `detallecotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `idSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `codigoUbigeo` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idSucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,1,'LIMA',''),(2,2,'ICA','');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idVendedor` int(11) DEFAULT NULL,
  `correo` varchar(20) DEFAULT NULL,
  `clave` varchar(300) DEFAULT NULL,
  `flagHabilitado` bit(1) DEFAULT b'1',
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`IdUsuario`),
  KEY `idVendedor` (`idVendedor`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idVendedor`) REFERENCES `vendedor` (`idVendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,NULL,'luis@gmail.com','12345','',''),(2,1,'pp@gmail.com','12345','','');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `idVendedor` int(11) NOT NULL AUTO_INCREMENT,
  `idSupervisor` int(11) DEFAULT NULL,
  `codigoVendedor` char(8) NOT NULL,
  `docIdentidad` char(8) NOT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `apellidoPaterno` varchar(80) DEFAULT NULL,
  `apellidoMaterno` varchar(80) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `idSucursal` int(11) NOT NULL,
  `nombreSucursal` varchar(100) DEFAULT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT CURRENT_TIMESTAMP,
  `flagHabilitado` bit(1) DEFAULT b'1',
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idVendedor`),
  KEY `idSucursal` (`idSucursal`),
  KEY `idSupervisor` (`idSupervisor`),
  CONSTRAINT `vendedor_ibfk_1` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`),
  CONSTRAINT `vendedor_ibfk_2` FOREIGN KEY (`idSupervisor`) REFERENCES `vendedor` (`idVendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,NULL,'V001','12345678','Pepe','Ramirez','Vasquez','123456789','pp@gmail.com',1,'LIMA','1980-01-01 00:00:00','2015-11-25 00:00:00','','');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-26 22:59:53
