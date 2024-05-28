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
  `id` int NOT NULL AUTO_INCREMENT,
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
  `id` int NOT NULL AUTO_INCREMENT,
  `ho` varchar(60) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ten` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sdt` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_taiKhoan_UNIQUE` (`id_taiKhoan`),
  KEY `fk_chutro_taikhoan_idx` (`id_taiKhoan`),
  CONSTRAINT `fk_chutro_taikhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chu_tro`
--

LOCK TABLES `chu_tro` WRITE;
/*!40000 ALTER TABLE `chu_tro` DISABLE KEYS */;
INSERT INTO `chu_tro` VALUES (1,'Tran Thi','D','19 Tran Phu','012548423','2024-04-02 01:25:44',3),(2,'Vo van','H','199/22 Phan the hien','0932466','2024-05-02 01:25:44',4),(4,'demoChuTro','ChuTro','666, nguyen huu tho','098754221','2043-04-03 01:25:44',14);
/*!40000 ALTER TABLE `chu_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `noiDung` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `thoiGian` datetime DEFAULT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_taiKhoan_idx` (`id_taiKhoan`),
  CONSTRAINT `fk_comment_taiKhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Noi dung super good','2023-04-02 01:25:44',1),(2,'Noi dung normal','2022-04-24 01:25:44',12),(3,'Noi dung bad','2023-04-24 01:25:44',5),(4,'Noi dung OKe','2024-07-11 16:22:44',7);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_nguoiThue` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_follow_id_idchuTro_idnguoiThue` (`id`,`id_chuTro`,`id_nguoiThue`),
  UNIQUE KEY `unique_index` (`id_nguoiThue`,`id_chuTro`),
  KEY `fk_follow_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_follow_chuTro_idx` (`id_chuTro`),
  CONSTRAINT `fk_follow_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_follow_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (5,1,2),(4,2,2),(1,4,1),(12,4,2);
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
  `id` int NOT NULL AUTO_INCREMENT,
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
  `id` int NOT NULL AUTO_INCREMENT,
  `ho` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ten` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sdt` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `id_taiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_taiKhoan_UNIQUE` (`id_taiKhoan`),
  CONSTRAINT `fk_nguoithue_taikhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_thue`
--

LOCK TABLES `nguoi_thue` WRITE;
/*!40000 ALTER TABLE `nguoi_thue` DISABLE KEYS */;
INSERT INTO `nguoi_thue` VALUES (1,'Nguyen Van','A','012345644','29 hoang minh giam','2023-02-02 01:25:44',1),(2,'Tran thi','B','0221144','124/2 Duong 15','2023-12-02 01:25:44',2),(4,'Nguyen Hoang ','Uyen ','0188488','255-ngo tat to','2023-04-02 01:25:44',5);
/*!40000 ALTER TABLE `nguoi_thue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_tro`
--

DROP TABLE IF EXISTS `phong_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_tro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diaChiPhong` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `gia` double DEFAULT NULL,
  `soNguoi` int DEFAULT NULL,
  `conTrong` tinyint DEFAULT NULL,
  `ngayDang` datetime DEFAULT NULL,
  `ngayCapNhat` datetime DEFAULT NULL,
  `id_quan` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phongTro_chuTro_idx` (`id_chuTro`),
  KEY `fk_phongTro_quan_idx` (`id_quan`),
  CONSTRAINT `fk_phongTro_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_phongTro_quan` FOREIGN KEY (`id_quan`) REFERENCES `quan_huyen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_tro`
--

LOCK TABLES `phong_tro` WRITE;
/*!40000 ALTER TABLE `phong_tro` DISABLE KEYS */;
INSERT INTO `phong_tro` VALUES (1,'18-nguyen huu tho',1800000,3,0,'2023-04-02 01:25:44',NULL,NULL,1),(2,'255-Ham nghi',3000000,4,1,'2024-03-02 01:25:44',NULL,NULL,2);
/*!40000 ALTER TABLE `phong_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quan_huyen`
--

DROP TABLE IF EXISTS `quan_huyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quan_huyen` (
  `id` int NOT NULL AUTO_INCREMENT,
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
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  `vaiTro` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (1,'nguoithue1','123',' ','ROLE_NGUOITHUE'),(2,'nguoithue2','123',' ','ROLE_NGUOITHUE'),(3,'chutro1','123',' ','ROLE_CHUTRO'),(4,'chutro2','123',' ','ROLE_CHUTRO'),(5,'nguoithue3','$2a$10$zWrquoRJXnfOEF3qiaomne4V9pjDJOFFlquDQRPfE1QyA223BkjGy','https://res.cloudinary.com/dlfzoufof/image/upload/v1716041507/lprr9eagn61oc1vwwojx.jpg','ROLE_NGUOITHUE'),(7,'admin','$2a$10$YjNrM2dXMCGkdOTdINg/gu0w9CffW5SeREAh4/HI2lhmw4THtNKTK','https://res.cloudinary.com/dlfzoufof/image/upload/v1716078993/pa3ucytoz6ji2uhhoux6.png','ROLE_ADMIN'),(8,'test','$2a$10$QR1TRZCxqcCM4qBZtHrlL.sLFOpKW5S/viRT/YJ0eBh6tKn4K4qCO','https://res.cloudinary.com/dlfzoufof/image/upload/v1716079139/pi4pdwhzv7fcehgefxdb.png','ROLE_ADMIN'),(9,'test1','$2a$10$SZV2.YhV.fbJkvuhmlpNBeekNHVIgrhQNfjQAcww/m28rnT3nH8P.','https://res.cloudinary.com/dlfzoufof/image/upload/v1716079531/xabnhmdrb0vhikhwpcyg.png','ROLE_ADMIN'),(10,'demo1','$2a$10$C4qS1EE2Xik40FzLLt.xIORHw3YtnhrT9VggtfCHB.7EPyldPH2my','https://res.cloudinary.com/dlfzoufof/image/upload/v1716079705/wan1kcxm204qx1yz0b63.jpg','ROLE_CHUTRO'),(11,'abc','$2a$10$Bk.4YzHSF/zEQ4Gk8Sto3uQQQESNbmOrCxElYY4pBFyyxLGNusihm','https://res.cloudinary.com/dlfzoufof/image/upload/v1716079786/u9rtvdvdlsvt8s6xff7p.png','ROLE_ADMIN'),(12,'chutro3','$2a$10$DmTKnIx/mySVmDvniiowXejTyzZyW5ysNo63dPdolOTH1M/6zOaf6','https://res.cloudinary.com/dlfzoufof/image/upload/v1716728080/ispmvg8hdmuzkj1bdhzo.jpg','ROLE_CHUTRO'),(13,'abc','$2a$10$3BWZbJ.m7Z03sUAcGMUebuGHCnQx.JNfop8fjFjECPXuHfHYhzYR2','https://res.cloudinary.com/dlfzoufof/image/upload/v1716729107/sd8qzbowi4myjfhtcgmx.jpg','ROLE_ADMIN'),(14,'demochutro1','$2a$10$MQGGsO83GInMorTopYyaKegSKl4q759.f6AJ2qHDzbYvNFTNvscxa','https://res.cloudinary.com/dlfzoufof/image/upload/v1716882992/qsquvd3imq6vrgn6bve3.jpg','ROLE_CHUTRO'),(15,'demonguoithue1','$2a$10$1hO7A0L3Mdx03f7Rn6GhAe2oIXFlOnIeGQa8Y3QM85skHlCi/wAJu','https://res.cloudinary.com/dlfzoufof/image/upload/v1716883007/jr5ukspkjxm6p9fziwdl.jpg','ROLE_NGUOITHUE');
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanh_pho`
--

DROP TABLE IF EXISTS `thanh_pho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanh_pho` (
  `id` int NOT NULL AUTO_INCREMENT,
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
  `id` int NOT NULL AUTO_INCREMENT,
  `noiDung` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `thoiGian` datetime DEFAULT NULL,
  `loaiTin` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `id_comment` int DEFAULT NULL,
  `id_nguoiThue` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  `id_phongTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tin_comment_idx` (`id_comment`),
  KEY `fk_tin_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_tin_chuTro_idx` (`id_chuTro`),
  KEY `fk_tin_phongTro_idx` (`id_phongTro`),
  CONSTRAINT `fk_tin_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_tin_comment` FOREIGN KEY (`id_comment`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_tin_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`),
  CONSTRAINT `fk_tin_phongTro` FOREIGN KEY (`id_phongTro`) REFERENCES `phong_tro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tin`
--

LOCK TABLES `tin` WRITE;
/*!40000 ALTER TABLE `tin` DISABLE KEYS */;
INSERT INTO `tin` VALUES (1,'Phong tro cho thue gia re','2024-07-03 18:24:44','O ghep',2,NULL,1,1),(2,'Phong tro cho thue gia cao','2024-07-03 18:24:44','Thue moi',1,NULL,1,2);
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

-- Dump completed on 2024-05-28 17:01:02
