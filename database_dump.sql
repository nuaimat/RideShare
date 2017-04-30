-- MySQL dump 10.13  Distrib 5.7.18, for osx10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: heroku_8817d68b4a21fb3
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Current Database: `heroku_8817d68b4a21fb3`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `heroku_8817d68b4a21fb3` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `heroku_8817d68b4a21fb3`;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `commentid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `postid` int(20) unsigned NOT NULL,
  `comment` text COLLATE utf8_bin NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (2,3,52,'hkhkjhjhjghjfjfj','2017-04-25 23:09:53','2017-04-25 23:09:53'),(3,3,52,'hkhkjhjhjghjfjfj','2017-04-25 23:09:53','2017-04-25 23:09:53'),(12,1,1,' ','2017-04-26 05:17:56','2017-04-26 05:17:56'),(22,1,1,' ','2017-04-26 05:17:56','2017-04-26 05:17:56'),(32,1,192,'test','2017-04-26 05:17:56','2017-04-26 05:17:56'),(42,1,192,'test','2017-04-26 05:17:56','2017-04-26 05:17:56'),(52,112,192,'test','2017-04-26 05:17:56','2017-04-26 05:17:56'),(62,112,192,'test','2017-04-26 05:17:56','2017-04-26 05:17:56'),(72,112,192,'br','2017-04-26 05:17:56','2017-04-26 05:17:56'),(82,112,182,'test by mo','2017-04-26 05:32:04','2017-04-26 05:32:04'),(92,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(102,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(112,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(122,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(132,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(142,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(152,112,112,'trying ','2017-04-26 17:34:56','2017-04-26 17:34:56'),(162,112,192,'coment from br as jssssssssssssssssson string','2017-04-26 17:34:56','2017-04-26 17:34:56'),(172,112,192,'coment from br as jssssssssssssssssson string','2017-04-26 17:34:56','2017-04-26 17:34:56'),(182,112,202,'test ','2017-04-26 16:31:34','2017-04-26 16:31:34'),(192,112,202,'salam nao','2017-04-26 16:37:29','2017-04-26 16:37:29'),(202,112,202,'test comment','2017-04-26 16:58:55','2017-04-26 16:58:55'),(212,112,202,'test comment','2017-04-26 17:04:36','2017-04-26 17:04:36'),(222,112,212,'test','2017-04-26 17:27:17','2017-04-26 17:27:17'),(232,112,212,'test','2017-04-26 17:28:48','2017-04-26 17:28:48'),(242,112,212,'test','2017-04-26 17:29:10','2017-04-26 17:29:10'),(252,112,212,'test yo!','2017-04-26 17:32:30','2017-04-26 17:32:30'),(262,112,212,'test yo 2!','2017-04-26 17:32:34','2017-04-26 17:32:34'),(272,112,212,'test yo 3!','2017-04-26 17:32:42','2017-04-26 17:32:42'),(282,112,212,'test yo 4!','2017-04-26 17:32:45','2017-04-26 17:32:45'),(292,112,0,'test','2017-04-26 18:26:15','2017-04-26 18:26:15'),(302,112,0,'test 33','2017-04-26 18:26:22','2017-04-26 18:26:22'),(312,112,262,'test','2017-04-26 18:29:19','2017-04-26 18:29:19'),(322,112,262,'test 2 comment','2017-04-26 18:29:27','2017-04-26 18:29:27'),(332,112,262,'yo this is bad','2017-04-26 18:31:55','2017-04-26 18:31:55'),(342,112,262,'what time are you thinking?','2017-04-26 18:36:23','2017-04-26 18:36:23'),(352,112,262,'test','2017-04-26 20:06:20','2017-04-26 20:06:20'),(362,112,212,'old comment','2017-04-26 20:09:04','2017-04-26 20:09:04'),(372,112,262,'what\'s up?','2017-04-26 20:11:56','2017-04-26 20:11:56'),(382,112,62,'i am interested','2017-04-26 20:12:11','2017-04-26 20:12:11'),(392,112,52,'when you leaving?','2017-04-26 20:12:18','2017-04-26 20:12:18'),(402,122,282,'the ','2017-04-27 15:59:31','2017-04-27 15:59:31'),(403,122,152,'test ','2017-04-27 16:10:49','2017-04-27 16:10:49'),(404,122,0,'test me not','2017-04-27 16:19:12','2017-04-27 16:19:12');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `likeid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `postid` int(20) unsigned NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`likeid`)
) ENGINE=InnoDB AUTO_INCREMENT=873 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1,1,'2017-04-26 20:23:42','2017-04-26 20:23:42'),(3,3,3,'2017-04-26 20:23:42','2017-04-26 20:23:42'),(4,2,3,'2017-04-25 06:22:36','2017-04-25 06:22:36'),(5,3,5,'2017-04-26 20:23:42','2017-04-26 20:23:42'),(6,3,6,'2017-04-26 20:23:42','2017-04-26 20:23:42'),(7,3,7,'2017-04-26 20:23:42','2017-04-26 20:23:42'),(12,1,1,'2017-04-26 21:18:40','2017-04-26 21:18:40'),(182,112,252,'2017-04-26 22:24:43','2017-04-26 22:24:43'),(192,112,252,'2017-04-26 22:24:46','2017-04-26 22:24:46'),(202,112,252,'2017-04-26 22:24:47','2017-04-26 22:24:47'),(352,112,262,'2017-04-27 00:49:13','2017-04-27 00:49:13'),(412,122,222,'2017-04-27 02:24:34','2017-04-27 02:24:34'),(492,122,252,'2017-04-27 02:52:40','2017-04-27 02:52:40'),(502,122,252,'2017-04-27 02:53:10','2017-04-27 02:53:10'),(522,122,242,'2017-04-27 02:54:54','2017-04-27 02:54:54'),(752,122,252,'2017-04-27 03:58:59','2017-04-27 03:58:59'),(762,122,242,'2017-04-27 03:59:02','2017-04-27 03:59:02'),(862,122,42,'2017-04-27 06:39:11','2017-04-27 06:39:11'),(866,122,0,'2017-04-27 16:12:34','2017-04-27 16:12:34'),(867,122,284,'2017-04-27 16:14:41','2017-04-27 16:14:41'),(871,122,283,'2017-04-27 16:17:03','2017-04-27 16:17:03'),(872,122,262,'2017-04-27 16:17:10','2017-04-27 16:17:10');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `postid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `post` text COLLATE utf8_bin NOT NULL,
  `posttype` int(1) unsigned NOT NULL,
  `src` point NOT NULL,
  `dest` point NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `srcReadable` text COLLATE utf8_bin,
  `destReadable` text COLLATE utf8_bin,
  PRIMARY KEY (`postid`),
  KEY `userid` (`userid`),
  SPATIAL KEY `src_idx` (`src`),
  SPATIAL KEY `dest_idx` (`dest`)
) ENGINE=MyISAM AUTO_INCREMENT=292 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (22,1,'testing',0,'\0\0\0\0\0\0\0\0\0\0 ≠˝V¿\0\0\0`˘ÄD@','\0\0\0\0\0\0\0\0\0\0Ä\’\"S¿\0\0\0`¶¨C@','2017-04-25 16:40:56','2017-04-25 16:40:56','Fairfield, IA, United States','xyxyxy, Lillyan Avenue, Baltimore, MD, United States'),(32,1,'need to pick some stuff',1,'\0\0\0\0\0\0\0\0\0\0 \"˝V¿\0\0 ÅD@','\0\0\0\0\0\0\0\0\0\0†!\ZW¿\0\0@\rÇD@','2017-04-25 16:40:56','2017-04-26 04:40:58','Fairfield High School, East Broadway Avenue, Fairfield, IA, United States','Ottumwa, IA, United States'),(42,1,'need to pick some stuff',0,'\0\0\0\0\0\0\0\0\0\0 \"˝V¿\0\0 ÅD@','\0\0\0\0\0\0\0\0\0\0†!\ZW¿\0\0@\rÇD@','2017-04-25 16:40:56','2017-04-25 16:40:56','Fairfield High School, East Broadway Avenue, Fairfield, IA, United States','Ottumwa, IA, United States'),(52,1,'from ottomuwa to san antonio',1,'\0\0\0\0\0\0\0\0\0\0†!\ZW¿\0\0@\rÇD@','\0\0\0\0\0\0\0\0\0\0†óüX¿ˇˇˇ?ìl=@','2017-04-25 16:42:15','2017-04-26 04:40:58','Ottumwa, IA, United States','San Antonio, TX, United States'),(62,1,'Across the states ride!',1,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:14:46','2017-04-26 04:40:58','New York, IA, United States','San Francisco, CA, United States'),(72,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:19:11','2017-04-26 01:19:11','New York, IA, United States','San Francisco, CA, United States'),(82,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:19:57','2017-04-26 01:19:57','New York, IA, United States','San Francisco, CA, United States'),(92,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:31:41','2017-04-26 01:31:41','New York, IA, United States','San Francisco, CA, United States'),(102,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:35:25','2017-04-26 01:35:25','New York, IA, United States','San Francisco, CA, United States'),(112,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:35:39','2017-04-26 01:35:39','New York, IA, United States','San Francisco, CA, United States'),(122,1,'Across the states ride!',0,'\0\0\0\0\0\0\0\0\0\0\‡¥SW¿ˇˇˇøZmD@','\0\0\0\0\0\0\0\0\0\0\0hó^¿\0\0\0@*\ B@','2017-04-26 01:39:26','2017-04-26 01:39:26','New York, IA, United States','San Francisco, CA, United States'),(132,1,'Urgent, i need a ride please',0,'\0\0\0\0\0\0\0\0\0\0¿äU¿\0\0\0[\‚C@','\0\0\0\0\0\0\0\0\0\0†N\ËU¿\0\0\0\0fD@','2017-04-26 02:49:34','2017-04-26 02:49:34','Indianapolis, IN, United States','Chicago, IL, United States'),(142,1,'Across-state trip offered',0,'\0\0\0\0\0\0\0\0\0\0@¢àU¿\0\0\0`3\"D@','\0\0\0\0\0\0\0\0\0\0`ø\⁄]¿\0\0\0\0ûcB@','2017-04-26 02:50:19','2017-04-26 02:50:19','Indiana, United States','California, United States'),(152,1,'How \'bout dat?',0,'\0\0\0\0\0\0\0\0\0\0`§˝V¿\0\0 “ÄD@','\0\0\0\0\0\0\0\0\0\0\0≠cW¿\0\0\0Ä‘≠D@','2017-04-26 02:51:19','2017-04-26 02:51:19','India Cafe, West Burlington Avenue, Fairfield, IA, United States','Indianola, IA, United States'),(162,1,'Ride inside CA',0,'\0\0\0\0\0\0\0\0\0\0†πx^¿\0\0\0`J´B@','\0\0\0\0\0\0\0\0\0\0¿ó~^¿\0\0\0†\‘\„B@','2017-04-26 02:56:09','2017-04-26 02:56:09','San Jose, CA, United States','San Ramon, CA, United States'),(172,1,'Weekend ride to Yellowstone',0,'\0\0\0\0\0\0\0\0\0\0\‡˘[¿\0\0@aaD@','\0\0\0\0\0\0\0\0\0\0@©•[¿\0\0\0Ä\«6F@','2017-04-26 03:13:33','2017-04-26 03:13:33','Salt Lake City, UT, United States','Yellowstone National Park, WY, United States'),(182,1,'What\'s up homies, anyone can take me from NY to Ohio? thanks!',0,'\0\0\0\0\0\0\0\0\0\0`aÄR¿\0\0\0Ä<[D@','\0\0\0\0\0\0\0\0\0\0@∫T¿\0\0\0†i5D@','2017-04-26 03:23:17','2017-04-26 03:23:17','NY, United States','Ohio, United States'),(192,1,'What\'s up homies, anyone can take me from here to there?',0,'\0\0\0\0\0\0\0\0\0\0ÄÙgW¿\0\0@K\ﬁD@','\0\0\0\0\0\0\0\0\0\0†–çV¿\0\0\0`]C@','2017-04-26 03:23:55','2017-04-26 03:23:55','Faith Baptist Bible College, Northwest 4th Street, Ankeny, IA, United States','Mississippi River, St. Louis, MO, United States'),(202,112,'Salam Nao',0,'\0\0\0\0\0\0\0\0\0\0†I\ÍU¿\0\0@ˇD@','\0\0\0\0\0\0\0\0\0\0†N\ËU¿\0\0\0\0fD@','2017-04-26 16:29:13','2017-04-26 16:29:13','Ethiopian Diamond, North Broadway Street, Chicago, IL, United States','Chicago, IL, United States'),(212,112,'Adsdf asdf asdf asdf',0,'\0\0\0\0\0\0\0\0\0\0\‡`MW¿\0\0 \ÏlD@','\0\0\0\0\0\0\0\0\0\0†N\ËU¿\0\0\0\0fD@','2017-04-26 16:58:24','2017-04-26 16:58:24','New York, IA, United States','Chicago, IL, United States'),(222,112,'To the airport',0,'\0\0\0\0\0\0\0\0\0\0 ≠˝V¿\0\0\0`˘ÄD@','\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','2017-04-26 17:37:24','2017-04-26 17:37:24','Fairfield, IA, United States','Des Moines, IA, United States'),(232,112,'Ride test',0,'\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','\0\0\0\0\0\0\0\0\0\0@\Ó\·V¿ˇˇˇﬂü\‘D@','2017-04-26 18:21:58','2017-04-26 18:21:58','Des Moines, IA, United States','Iowa City, IA, United States'),(242,112,'Ride test',0,'\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','\0\0\0\0\0\0\0\0\0\0@\Ó\·V¿ˇˇˇﬂü\‘D@','2017-04-26 18:22:43','2017-04-26 18:22:43','Des Moines, IA, United States','Iowa City, IA, United States'),(252,112,'Requesting a ride test',1,'\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','\0\0\0\0\0\0\0\0\0\0 ≠˝V¿\0\0\0`˘ÄD@','2017-04-26 18:25:14','2017-04-26 18:25:14','Des Moines, IA, United States','Fairfield, IA, United States'),(262,112,'To the airport please, I have one luggage',1,'\0\0\0\0\0\0\0\0\0\0 ≠˝V¿\0\0\0`˘ÄD@','\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','2017-04-26 18:25:59','2017-04-26 18:25:59','Fairfield, IA, United States','Des Moines, IA, United States'),(272,132,'A weekend trip',0,'\0\0\0\0\0\0\0\0\0\0 \\BS¿\0\0\0\‡tC@','\0\0\0\0\0\0\0\0\0\0ÄÅYV¿\0\0\0@\nQD@','2017-04-27 04:02:06','2017-04-27 04:02:06','Washington, DC, United States','Illinois, United States'),(282,132,'From des moines to Dallas',0,'\0\0\0\0\0\0\0\0\0\0†˚fW¿\0\0\0†\ﬁ\ÃD@','\0\0\0\0\0\0\0\0\0\0\‡3X¿\0\0\0¿ic@@','2017-04-27 04:10:13','2017-04-27 04:10:13','Des Moines, IA, United States','Dallas, TX, United States'),(283,122,'Going from DSM to ORD, need a ride please',1,'\0\0\0\0\0\0\0\0\0\0\0\ËKW¿\0\0\0 \ÎD@','\0\0\0\0\0\0\0\0\0\0\0\0˘U¿\0\0\0ÄíE@','2017-04-27 16:05:23','2017-04-27 16:05:23','Des Moines, IA, United States','Chicago, IL, United States'),(284,152,'Across the states',0,'\0\0\0\0\0\0\0\0\0\0`aÄR¿\0\0\0Ä<[D@','\0\0\0\0\0\0\0\0\0\0¿◊ö^¿\0\0\0\‡0\„B@','2017-04-27 16:09:12','2017-04-27 16:09:12','New York, NY, United States','San Francisco, CA, United States'),(285,122,'Asdfsad fads fsa',0,'\0\0\0\0\0\0\0\0\0\0Ä\”\ÈU¿\0\0\0†\√\ÁD@','\0\0\0\0\0\0\0\0\0\0†\—~^¿\0\0@SõB@','2017-04-27 16:10:06','2017-04-27 16:10:06','Testa Produce, Inc., South Racine Avenue, Chicago, IL, United States','Testarossa Winery, College Avenue, Los Gatos, CA, United States'),(286,152,'Across the states',0,'\0\0\0\0\0\0\0\0\0\0`aÄR¿\0\0\0Ä<[D@','\0\0\0\0\0\0\0\0\0\0¿◊ö^¿\0\0\0\‡0\„B@','2017-04-27 16:23:20','2017-04-27 16:23:20','New York, NY, United States','San Francisco, CA, United States'),(287,152,'Test me',0,'\0\0\0\0\0\0\0\0\0\0@OJ]¿\0\0@ù[@@','\0\0\0\0\0\0\0\0\0\0†óüX¿ˇˇˇ?ìl=@','2017-04-27 16:25:34','2017-04-27 16:25:34','San Diego, CA, United States','San Antonio, TX, United States'),(288,152,'Test me',0,'\0\0\0\0\0\0\0\0\0\0@OJ]¿\0\0@ù[@@','\0\0\0\0\0\0\0\0\0\0†óüX¿ˇˇˇ?ìl=@','2017-04-27 16:26:02','2017-04-27 16:26:02','San Diego, CA, United States','San Antonio, TX, United States'),(289,152,'Test me',0,'\0\0\0\0\0\0\0\0\0\0@OJ]¿\0\0@ù[@@','\0\0\0\0\0\0\0\0\0\0†óüX¿ˇˇˇ?ìl=@','2017-04-27 16:26:29','2017-04-27 16:26:29','San Diego, CA, United States','San Antonio, TX, United States'),(290,152,'Test',0,'\0\0\0\0\0\0\0\0\0\0\‡FÇ^¿ˇˇˇ\ﬂ\·\ŒG@','\0\0\0\0\0\0\0\0\0\0 \\BS¿\0\0\0\‡tC@','2017-04-27 16:33:04','2017-04-27 16:33:04','Sammamish, WA, United States','Washington, DC, United States'),(291,152,'Test',0,'\0\0\0\0\0\0\0\0\0\0\‡FÇ^¿ˇˇˇ\ﬂ\·\ŒG@','\0\0\0\0\0\0\0\0\0\0 \\BS¿\0\0\0\‡tC@','2017-04-27 16:34:45','2017-04-27 16:34:45','Sammamish, WA, United States','Washington, DC, United States');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) COLLATE utf8_bin NOT NULL,
  `gender` int(1) unsigned NOT NULL,
  `state` varchar(50) COLLATE utf8_bin NOT NULL,
  `city` varchar(50) COLLATE utf8_bin NOT NULL,
  `street` varchar(50) COLLATE utf8_bin NOT NULL,
  `zipcode` int(5) unsigned NOT NULL,
  `birthyear` int(4) unsigned NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'sa',0,'Iowa','jghgj','jgjhg',23456,1234,'ghj','','2017-04-26 17:36:32','2017-04-26 17:36:32'),(2,'sam',0,'Iowa','jhjh','jhkhj',12345,1234,'khjh','hjh','2017-04-26 17:36:32','2017-04-26 17:36:32'),(3,'sa',0,'Iowa','jghgj','jgjhg',23456,1234,'ghj','hgh','2017-04-26 17:36:32','2017-04-26 17:36:32'),(32,'sami',0,'Iowa','hjkhk','hkjhk',12345,6798,'hkjhj','mi','2017-04-26 17:36:32','2017-04-26 17:36:32'),(42,'sami',0,'Iowa','hkjh','hjkhk',12345,1234,'hjhk','hjhk','2017-04-26 17:36:32','2017-04-26 17:36:32'),(52,'sami',0,'Iowa','hkjh','hjkhk',12345,1234,'hjhk','hjhk','2017-04-26 17:36:32','2017-04-26 17:36:32'),(62,'ksjk',0,'Iowa','dkjfk','dskj',12345,1234,'skdfjk','dfkj','2017-04-26 17:36:32','2017-04-26 17:36:32'),(72,'tiema',0,'California','addis','megenaga',87654,1236,'samimulaw@GMAIL','mulaw','2017-04-26 17:36:32','2017-04-26 17:36:32'),(82,'sfhj',0,'Iowa','khjl','hfhgf',9876,1567,'hjh','jkhj','2017-04-26 17:36:32','2017-04-26 17:36:32'),(92,'beti',0,'Alaska','meke','ahsj',56784,1234,'sami','beti','2017-04-26 17:36:32','2017-04-26 17:36:32'),(102,'Nahom',0,'Colorado','mk','sajak',67252,1990,'nahu','abrha','2017-04-26 17:36:32','2017-04-26 17:36:32'),(112,'br',0,'iowa','adddddddiii','adddiiiiiii',77777,1234,'','br','2017-04-27 01:10:01','2017-04-27 01:10:01'),(122,'sa',0,'Iowa','mk','ax',12348,1992,'sa','sa','2017-04-26 22:45:46','2017-04-26 22:45:46'),(132,'b',0,'Iowa','c','c',12345,1234,'b','b','2017-04-27 04:14:09','2017-04-27 04:14:09'),(142,'Sami Kidanu',0,'Florida','AddisAbaba','Bole',52557,1992,'samimulaw@gmail.com','sami6','2017-04-27 04:32:16','2017-04-27 04:32:16'),(152,'test',0,'Florida','test','test',12345,1234,'test','test','2017-04-27 04:47:51','2017-04-27 04:47:51');
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

-- Dump completed on 2017-04-28 15:37:32
