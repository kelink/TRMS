-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 05 月 11 日 02:04
-- 服务器版本: 5.6.12-log
-- PHP 版本: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `trms`
--
CREATE DATABASE IF NOT EXISTS `trms` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `trms`;

-- --------------------------------------------------------

--
-- 表的结构 `blacklist`
--

CREATE TABLE IF NOT EXISTS `blacklist` (
  `BL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Team_ID` int(11) NOT NULL,
  `Reason` varchar(255) NOT NULL,
  PRIMARY KEY (`BL_ID`),
  KEY `BL_FK` (`Team_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `Reservation_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Team_ID` int(11) NOT NULL,
  `Room_ID` int(11) NOT NULL,
  `User_ID` int(11) NOT NULL,
  `Applied_Eend_ Date` datetime NOT NULL,
  `Applied_Start_Date` datetime NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '0',
  `Order_Time` datetime NOT NULL,
  `Usage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Reservation_ID`),
  KEY `Re_User_Fk` (`User_ID`),
  KEY `RE_Team_Fk` (`Team_ID`),
  KEY `RE_Room_FK` (`Room_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `Role_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户类型的id',
  `Authority` varchar(50) NOT NULL COMMENT '用户类型的描述',
  PRIMARY KEY (`Role_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `Room_ID` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(50) NOT NULL,
  `OwnBy` varchar(50) NOT NULL,
  `Room_Status` int(11) DEFAULT '0',
  `Last_Used_Date` datetime NOT NULL,
  PRIMARY KEY (`Room_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `team`
--

CREATE TABLE IF NOT EXISTS `team` (
  `Team_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) NOT NULL,
  `Department` varchar(50) NOT NULL,
  PRIMARY KEY (`Team_ID`),
  KEY `Team_FK` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'User 表的id',
  `Account` varchar(255) NOT NULL COMMENT 'User的登陆account',
  `Password` varchar(255) NOT NULL COMMENT 'User的登陆密码',
  `Gender` int(20) NOT NULL COMMENT 'User的性别',
  `LN` int(50) NOT NULL COMMENT 'User的LN ，用于发邮件',
  `Tele` int(50) NOT NULL COMMENT 'User的电话',
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `Account` (`Account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User 用户登陆表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `User_ID` int(11) NOT NULL,
  `Role_ID` int(11) NOT NULL,
  KEY `Role_FK` (`Role_ID`),
  KEY `User_FK` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 限制导出的表
--

--
-- 限制表 `blacklist`
--
ALTER TABLE `blacklist`
  ADD CONSTRAINT `BL_FK` FOREIGN KEY (`Team_ID`) REFERENCES `team` (`Team_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `RE_Room_FK` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`Room_ID`),
  ADD CONSTRAINT `RE_Team_Fk` FOREIGN KEY (`Team_ID`) REFERENCES `team` (`Team_ID`),
  ADD CONSTRAINT `Re_User_Fk` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`);

--
-- 限制表 `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `Team_FK` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `Role_FK` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`Role_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `User_FK` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
