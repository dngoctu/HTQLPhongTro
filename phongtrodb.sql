-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: phongtrodb
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_taiKhoan_UNIQUE` (`id_taiKhoan`),
  KEY `fk_admin_taiKhoan_idx` (`id_taiKhoan`),
  CONSTRAINT `fk_admin_taiKhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chu_tro`
--

DROP TABLE IF EXISTS `chu_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chu_tro` (
  `id` int NOT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sdt` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_taiKhoan_UNIQUE` (`id_taiKhoan`),
  KEY `fk_chutro_taikhoan_idx` (`id_taiKhoan`),
  CONSTRAINT `fk_chutro_taikhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chu_tro`
--

LOCK TABLES `chu_tro` WRITE;
/*!40000 ALTER TABLE `chu_tro` DISABLE KEYS */;
/*!40000 ALTER TABLE `chu_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL,
  `noiDung` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `thoiGian` datetime DEFAULT NULL,
  `id_taiKhoan` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_taiKhoan_idx` (`id_taiKhoan`),
  CONSTRAINT `fk_comment_taiKhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL,
  `id_nguoiThue` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_follow_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_follow_chuTro_idx` (`id_chuTro`),
  CONSTRAINT `fk_follow_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_follow_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh_anh_tro`
--

DROP TABLE IF EXISTS `hinh_anh_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh_anh_tro` (
  `id` int NOT NULL,
  `hinhAnh` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `id_phongTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hinhAnhTro_phongTro_idx` (`id_phongTro`),
  CONSTRAINT `fk_hinhAnhTro_phongTro` FOREIGN KEY (`id_phongTro`) REFERENCES `phong_tro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_tro`
--

LOCK TABLES `hinh_anh_tro` WRITE;
/*!40000 ALTER TABLE `hinh_anh_tro` DISABLE KEYS */;
/*!40000 ALTER TABLE `hinh_anh_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kinhdo_vido`
--

DROP TABLE IF EXISTS `kinhdo_vido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kinhdo_vido` (
  `id` int NOT NULL,
  `kinhDo` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `viDo` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_nguoiThue` int DEFAULT NULL,
  `id_phongTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kdvd_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_kdvd_phongTro_idx` (`id_phongTro`),
  CONSTRAINT `fk_kdvd_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`),
  CONSTRAINT `fk_kdvd_phongTro` FOREIGN KEY (`id_phongTro`) REFERENCES `phong_tro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kinhdo_vido`
--

LOCK TABLES `kinhdo_vido` WRITE;
/*!40000 ALTER TABLE `kinhdo_vido` DISABLE KEYS */;
/*!40000 ALTER TABLE `kinhdo_vido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoi_thue`
--

DROP TABLE IF EXISTS `nguoi_thue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoi_thue` (
  `id` int NOT NULL,
  `ho` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ten` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sdt` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_taiKhoan_UNIQUE` (`id_taiKhoan`),
  CONSTRAINT `fk_nguoithue_taikhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_thue`
--

LOCK TABLES `nguoi_thue` WRITE;
/*!40000 ALTER TABLE `nguoi_thue` DISABLE KEYS */;
/*!40000 ALTER TABLE `nguoi_thue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_tro`
--

DROP TABLE IF EXISTS `phong_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_tro` (
  `id` int NOT NULL,
  `diaChiPhong` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `gia` double DEFAULT NULL,
  `soNguoi` int DEFAULT NULL,
  `conTrong` tinyint DEFAULT NULL,
  `id_quan` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phongTro_chuTro_idx` (`id_chuTro`),
  KEY `fk_phongTro_quan_idx` (`id_quan`),
  CONSTRAINT `fk_phongTro_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_phongTro_quan` FOREIGN KEY (`id_quan`) REFERENCES `quan_huyen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_tro`
--

LOCK TABLES `phong_tro` WRITE;
/*!40000 ALTER TABLE `phong_tro` DISABLE KEYS */;
/*!40000 ALTER TABLE `phong_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quan_huyen`
--

DROP TABLE IF EXISTS `quan_huyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quan_huyen` (
  `id` int NOT NULL,
  `ten` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_thanhPho` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_thanhPho_quanHuyen_idx` (`id_thanhPho`),
  CONSTRAINT `fk_thanhPho_quanHuyen` FOREIGN KEY (`id_thanhPho`) REFERENCES `thanh_pho` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quan_huyen`
--

LOCK TABLES `quan_huyen` WRITE;
/*!40000 ALTER TABLE `quan_huyen` DISABLE KEYS */;
/*!40000 ALTER TABLE `quan_huyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `id` int NOT NULL,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `vaiTro` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanh_pho`
--

DROP TABLE IF EXISTS `thanh_pho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanh_pho` (
  `id` int NOT NULL,
  `ten` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanh_pho`
--

LOCK TABLES `thanh_pho` WRITE;
/*!40000 ALTER TABLE `thanh_pho` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanh_pho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tin`
--

DROP TABLE IF EXISTS `tin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tin` (
  `id` int NOT NULL,
  `noiDung` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `thoiGian` datetime DEFAULT NULL,
  `loaiTin` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_comment` int DEFAULT NULL,
  `id_nguoiThue` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tin_comment_idx` (`id_comment`),
  KEY `fk_tin_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_tin_chuTro_idx` (`id_chuTro`),
  CONSTRAINT `fk_tin_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_tin_comment` FOREIGN KEY (`id_comment`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_tin_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tin`
--

LOCK TABLES `tin` WRITE;
/*!40000 ALTER TABLE `tin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 21:28:15
