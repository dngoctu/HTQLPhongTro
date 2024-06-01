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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chu_tro`
--

LOCK TABLES `chu_tro` WRITE;
/*!40000 ALTER TABLE `chu_tro` DISABLE KEYS */;
INSERT INTO `chu_tro` VALUES (1,'Tran Thi','D','19 Tran Phu','012548423','2024-04-02 01:25:44',3),(2,'Vo van','H','199/22 Phan the hien','0932466','2024-05-02 01:25:44',4),(4,'demoChuTro','ChuTro','666, nguyen huu tho','098754221','2024-04-03 01:25:44',14),(8,'Ho Thi','Nguyet','225/11 duong so 7, phuong 7, go vap','0641355','2024-05-31 16:32:16',30);
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
  `id_tin` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_taiKhoan_idx` (`id_taiKhoan`),
  KEY `fk_comment_tin_idx` (`id_tin`),
  CONSTRAINT `fk_comment_taiKhoan` FOREIGN KEY (`id_taiKhoan`) REFERENCES `tai_khoan` (`id`),
  CONSTRAINT `fk_comment_tin` FOREIGN KEY (`id_tin`) REFERENCES `tin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Noi dung super good','2023-04-02 01:25:44',1,1),(3,'Noi dung bad','2023-04-24 01:25:44',5,1),(4,'Noi dung OKe','2024-01-11 16:22:44',7,1),(10,'Qua tuyet voi','2024-05-31 20:52:50',5,2),(12,'abcccc','2024-06-01 08:08:58',5,2),(16,'First comment','2024-06-01 09:24:10',31,34),(18,'ssssss','2024-06-01 11:53:49',5,40),(21,'sssss','2024-06-01 11:55:30',31,40),(22,'Hays','2024-06-01 11:56:04',31,2),(23,'MY comment','2024-06-01 12:54:09',5,34),(24,'Con phong','2024-06-01 12:54:47',5,54),(25,'hohooh','2024-06-01 15:58:24',5,55);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_thue`
--

LOCK TABLES `nguoi_thue` WRITE;
/*!40000 ALTER TABLE `nguoi_thue` DISABLE KEYS */;
INSERT INTO `nguoi_thue` VALUES (1,'Nguyen Van','A','012345644','29 hoang minh giam','2024-12-02 01:25:44',1),(2,'Tran thi','B','0221144','124/2 Duong 15','2024-12-02 01:25:44',2),(4,'Nguyen Hoang ','Uyen ','0188488','255-ngo tat to','2024-04-02 01:25:44',5),(7,'Pham Van','Hoang','01588444','241 ho hao hon','2024-05-31 16:33:45',31);
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (1,'nguoithue1','123',' https://res.cloudinary.com/dlfzoufof/image/upload/v1716041507/lprr9eagn61oc1vwwojx.jpg','ROLE_NGUOITHUE'),(2,'nguoithue2','123','https://res.cloudinary.com/dlfzoufof/image/upload/v1717066765/trolisv_xvciqf.png','ROLE_NGUOITHUE'),(3,'chutro1','123','https://res.cloudinary.com/dlfzoufof/image/upload/v1717066765/trolisv_xvciqf.png','ROLE_CHUTRO'),(4,'chutro2','123',' https://res.cloudinary.com/dlfzoufof/image/upload/v1716041507/lprr9eagn61oc1vwwojx.jpg','ROLE_CHUTRO'),(5,'nguoithue3','$2a$10$zWrquoRJXnfOEF3qiaomne4V9pjDJOFFlquDQRPfE1QyA223BkjGy','https://res.cloudinary.com/dlfzoufof/image/upload/v1716041507/lprr9eagn61oc1vwwojx.jpg','ROLE_NGUOITHUE'),(7,'admin','$2a$10$YjNrM2dXMCGkdOTdINg/gu0w9CffW5SeREAh4/HI2lhmw4THtNKTK','https://res.cloudinary.com/dlfzoufof/image/upload/v1717066757/sv_hemka6.png','ROLE_ADMIN'),(14,'demochutro1','$2a$10$MQGGsO83GInMorTopYyaKegSKl4q759.f6AJ2qHDzbYvNFTNvscxa','https://res.cloudinary.com/dlfzoufof/image/upload/v1716041507/lprr9eagn61oc1vwwojx.jpg','ROLE_CHUTRO'),(30,'chutro4','$2a$10$TtCLLkspzcR0VvcCmGuqeOp6pVPuqV.mruPHMBi4AWae2SsxuOpkC','https://res.cloudinary.com/dlfzoufof/image/upload/v1717147936/orfmmzuda8iijl6fxhwt.png','ROLE_CHUTRO'),(31,'nguoithue4','$2a$10$uF96PDiiM5Wo/l4L0HSWEubk0bDiOKtUdQFLEVNw12udgLDf.7ccO','https://res.cloudinary.com/dlfzoufof/image/upload/v1717148025/m4lewk0bz5twur0ibnro.jpg','ROLE_NGUOITHUE');
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
  `id_nguoiThue` int DEFAULT NULL,
  `id_chuTro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tin_nguoiThue_idx` (`id_nguoiThue`),
  KEY `fk_tin_chuTro_idx` (`id_chuTro`),
  CONSTRAINT `fk_tin_chuTro` FOREIGN KEY (`id_chuTro`) REFERENCES `chu_tro` (`id`),
  CONSTRAINT `fk_tin_nguoiThue` FOREIGN KEY (`id_nguoiThue`) REFERENCES `nguoi_thue` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tin`
--

LOCK TABLES `tin` WRITE;
/*!40000 ALTER TABLE `tin` DISABLE KEYS */;
INSERT INTO `tin` VALUES (1,'Cần tìm người ở ghép trong tuần','2024-07-03 18:24:44','Ở ghép',NULL,1),(2,'Cho thuê phòng giá rẻ','2024-07-03 18:24:44','Phòng mới',NULL,2),(34,'<p>HOT HOT <em><u>HOT</u></em></p>','2024-06-01 09:20:18','Phòng mới',NULL,8),(38,'<p>sssssssssasd</p>','2024-06-01 10:29:08','Phòng mới',NULL,8),(40,'<p>11111111111111111111<span class=\"ql-size-huge\">hehehhe</span></p>','2024-06-01 10:33:03','Ở ghép',7,NULL),(53,'<p>Bai cua tui</p>','2024-06-01 11:56:25','Phòng mới',7,NULL),(54,'<p>Phong tro xin xo</p>','2024-06-01 12:54:26','Phòng mới',4,NULL),(55,'<p>Phong can nguoi don vao thang nay</p>','2024-06-01 15:58:16','Phòng mới',4,NULL);
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

-- Dump completed on 2024-06-01 16:03:50
