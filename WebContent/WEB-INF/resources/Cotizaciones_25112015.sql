CREATE DATABASE  IF NOT EXISTS `cotizaciones` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cotizaciones`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: cotizaciones
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` (`idAuto`, `codigoAuto`, `idTipoAuto`, `descripcionTipo`, `idMarca`, `marca`, `idModelo`, `modelo`, `anio`, `numpuertas`, `precio`, `urlFoto`, `flagEstado`) VALUES (1,'TY1001',1,'Auto',1,'Auto',1,'Yaris',2015,4,60000,NULL,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`idCliente`, `codigoCliente`, `razonSocial`, `nombres`, `apellidos`, `idTipoCliente`, `descripTipoCliente`, `idTipoDocumento`, `descripTipoDoc`, `numeroDocumento`, `flagEstado`) VALUES (1,'CL001',NULL,'Juan','Perez',1,'Persona Natural',1,'DNI','44251122','');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configvariable`
--

LOCK TABLES `configvariable` WRITE;
/*!40000 ALTER TABLE `configvariable` DISABLE KEYS */;
INSERT INTO `configvariable` (`idConfigVariable`, `idPadre`, `codigo`, `descripcion`, `flagEstado`) VALUES (1,NULL,NULL,'Marca',''),(2,1,NULL,'Toyota',''),(3,NULL,NULL,'TipoAuto',''),(4,3,NULL,'Auto',''),(5,NULL,'','Modelo',''),(6,5,NULL,'2015',''),(7,NULL,NULL,'Moneda',''),(8,7,NULL,'DOLARES','');
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
  `idAuto` int(11) NOT NULL,
  `idTipoMoneda` int(11) NOT NULL,
  `descripTipoMoneda` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  `importe` decimal(10,0) DEFAULT NULL,
  `flagAprobado` bit(1) DEFAULT b'1',
  `flagEstado` bit(1) DEFAULT b'1',
  PRIMARY KEY (`idCotizacion`),
  KEY `idVendedor` (`idVendedor`),
  KEY `idCliente` (`idCliente`),
  KEY `idAuto` (`idAuto`),
  KEY `idTipoMoneda` (`idTipoMoneda`),
  CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`idVendedor`) REFERENCES `vendedor` (`idVendedor`),
  CONSTRAINT `cotizacion_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `cotizacion_ibfk_3` FOREIGN KEY (`idAuto`) REFERENCES `auto` (`idAuto`),
  CONSTRAINT `cotizacion_ibfk_4` FOREIGN KEY (`idTipoMoneda`) REFERENCES `configvariable` (`idConfigVariable`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
INSERT INTO `cotizacion` (`idCotizacion`, `codigoCotizacion`, `idVendedor`, `idCliente`, `idAuto`, `idTipoMoneda`, `descripTipoMoneda`, `cantidad`, `precio`, `importe`, `flagAprobado`, `flagEstado`) VALUES (1,'COT001',1,1,1,1,'DOLARES',1,20000,20000,'',''),(2,'COT002',1,1,1,1,'DOLARES',1,50000,50000,'\0','');
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
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
INSERT INTO `sucursal` (`idSucursal`, `codigoUbigeo`, `nombre`, `flagEstado`) VALUES (1,1,'LIMA',''),(2,2,'ICA','');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`IdUsuario`, `idVendedor`, `correo`, `clave`, `flagHabilitado`, `flagEstado`) VALUES (1,NULL,'luis@gmail.com','12345','','');
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
INSERT INTO `vendedor` (`idVendedor`, `idSupervisor`, `codigoVendedor`, `docIdentidad`, `nombres`, `apellidoPaterno`, `apellidoMaterno`, `telefono`, `correo`, `idSucursal`, `nombreSucursal`, `fechaNacimiento`, `fechaRegistro`, `flagHabilitado`, `flagEstado`) VALUES (1,NULL,'V001','12345678','Pepe','Ramirez','Vasquez','123456789','pp@gmail.com',1,'LIMA','1980-01-01 00:00:00','2015-11-25 00:00:00','','');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cotizaciones'
--

--
-- Dumping routines for database 'cotizaciones'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-25 23:50:29
