-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: TinyLibrary
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `book_name` varchar(100) NOT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `year_of_publication` int(11) DEFAULT NULL,
  `call_number` int(11) NOT NULL,
  `collection_amount` int(11) NOT NULL,
  PRIMARY KEY (`book_name`),
  KEY `Book_book_name_index` (`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Reader`
--

DROP TABLE IF EXISTS `Reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reader` (
  `reader_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `contact_info` varchar(100) NOT NULL,
  PRIMARY KEY (`reader_id`),
  KEY `Reader_reader_id_index` (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Reader_pass`
--

DROP TABLE IF EXISTS `Reader_pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reader_pass` (
  `reader_id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`reader_id`),
  KEY `Reader_pass_reader_id_index` (`reader_id`),
  CONSTRAINT `Reader_pass_Reader_reader_id_fk` FOREIGN KEY (`reader_id`) REFERENCES `Reader` (`reader_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `borrow_book`
--

DROP TABLE IF EXISTS `borrow_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow_book` (
  `reader_id` int(11) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `borrow_time` datetime NOT NULL,
  PRIMARY KEY (`reader_id`,`book_name`,`borrow_time`),
  KEY `borrow_book_borrow_time_index` (`borrow_time`),
  KEY `borrow_book_Book_book_name_fk` (`book_name`),
  CONSTRAINT `borrow_book_Book_book_name_fk` FOREIGN KEY (`book_name`) REFERENCES `Book` (`book_name`) ON DELETE CASCADE,
  CONSTRAINT `borrow_book_Reader_reader_id_fk` FOREIGN KEY (`reader_id`) REFERENCES `Reader` (`reader_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER borrow_from AFTER INSERT ON borrow_book
  FOR EACH ROW
  UPDATE Book
  SET collection_amount = collection_amount - 1
  WHERE book_name = NEW.book_name */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER return_to AFTER DELETE ON borrow_book
  FOR EACH ROW 
  BEGIN 
    UPDATE Book
      SET collection_amount = collection_amount + 1
    WHERE book_name = OLD.book_name;
    INSERT INTO return_book
      VALUES (OLD.reader_id, OLD.book_name, NOW(), OLD.borrow_time);
  end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `return_book`
--

DROP TABLE IF EXISTS `return_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_book` (
  `reader_id` int(11) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `return_time` datetime NOT NULL,
  `borrow_time` datetime DEFAULT NULL,
  PRIMARY KEY (`reader_id`,`book_name`,`return_time`),
  KEY `return_book_return_time_index` (`return_time`),
  KEY `return_book_Book_book_name_fk` (`book_name`),
  CONSTRAINT `return_book_Book_book_name_fk` FOREIGN KEY (`book_name`) REFERENCES `Book` (`book_name`) ON DELETE CASCADE,
  CONSTRAINT `return_book_Reader_reader_id_fk` FOREIGN KEY (`reader_id`) REFERENCES `Reader` (`reader_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-28 21:34:42
