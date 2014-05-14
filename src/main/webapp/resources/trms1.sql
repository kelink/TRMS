-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 05 月 14 日 14:45
-- 服务器版本: 5.6.12-log
-- PHP 版本: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `trms1`
--
CREATE DATABASE IF NOT EXISTS `trms1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `trms1`;

-- --------------------------------------------------------

--
-- 表的结构 `blacklist`
--

CREATE TABLE IF NOT EXISTS `blacklist` (
  `bl_ID` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `team_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`bl_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `dbuser`
--

CREATE TABLE IF NOT EXISTS `dbuser` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LN` varchar(50) DEFAULT NULL,
  `Tele` varchar(50) DEFAULT NULL,
  `access` int(11) DEFAULT NULL COMMENT '标记角色',
  `account` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `dbuser`
--

INSERT INTO `dbuser` (`user_ID`, `LN`, `Tele`, `access`, `account`, `gender`, `password`) VALUES
(1, '1030041097@qq.com', '18864648656', 1, 'luo', 1, '123'),
(2, '266469874@qq.com', '56566354', 2, 'luo2', 0, '1234'),
(3, 'dashen@qq.com', '15645824', 2, 'luo3', 1, '1234');

-- --------------------------------------------------------

--
-- 表的结构 `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Applied_End_Date` datetime DEFAULT NULL,
  `Applied_Start_Date` datetime DEFAULT NULL,
  `order_Time` datetime DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `room_ID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `team_ID` int(11) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `Role_ID` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(11) DEFAULT NULL COMMENT '验证角色',
  PRIMARY KEY (`Role_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`Role_ID`, `authority`) VALUES
(1, 'ROLE_LC'),
(2, 'ROLE_TA');

-- --------------------------------------------------------

--
-- 表的结构 `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `room_ID` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(50) DEFAULT NULL,
  `last_Used_Date` date DEFAULT NULL,
  `ownBy` varchar(50) DEFAULT NULL,
  `room_Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `team`
--

CREATE TABLE IF NOT EXISTS `team` (
  `team_ID` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(50) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`team_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `userrole`
--

CREATE TABLE IF NOT EXISTS `userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
