/*
MySQL Data Transfer
Source Host: localhost
Source Database: trms1
Target Host: localhost
Target Database: trms1
Date: 2014/5/21 23:15:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
CREATE TABLE `blacklist` (
  `bl_ID` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `team_ID` int(11) NOT NULL,
  PRIMARY KEY (`bl_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dbuser
-- ----------------------------
CREATE TABLE `dbuser` (
  `user_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Tele` varchar(50) NOT NULL,
  `access` int(11) NOT NULL,
  `account` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for department
-- ----------------------------
CREATE TABLE `department` (
  `department_ID` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) NOT NULL,
  PRIMARY KEY (`department_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
CREATE TABLE `reservation` (
  `reservation_ID` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_Num` varchar(100) DEFAULT NULL,
  `Applied_Start_Date` date DEFAULT NULL,
  `Applied_End_Date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tele` varchar(255) DEFAULT NULL,
  `order_Time` date DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `room_ID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `team_ID` int(11) DEFAULT NULL,
  `approve_by` int(11) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE `role` (
  `Role_ID` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(11) NOT NULL,
  PRIMARY KEY (`Role_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
CREATE TABLE `room` (
  `room_ID` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) NOT NULL,
  `last_Used_Date` date DEFAULT NULL,
  `room_Status` int(11) NOT NULL,
  `department_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_ID`),
  KEY `FK_io02x9xii8dr7ly1jv4ru57o2` (`department_ID`),
  CONSTRAINT `FK_io02x9xii8dr7ly1jv4ru57o2` FOREIGN KEY (`department_ID`) REFERENCES `department` (`department_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for team
-- ----------------------------
CREATE TABLE `team` (
  `team_ID` int(11) NOT NULL AUTO_INCREMENT,
  `department_ID` int(11) NOT NULL,
  `teamName` varchar(255) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`team_ID`),
  KEY `FK_igkdd6e6ghkvr2uwduwlrg5hb` (`department_ID`),
  CONSTRAINT `FK_igkdd6e6ghkvr2uwduwlrg5hb` FOREIGN KEY (`department_ID`) REFERENCES `department` (`department_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `dbuser` VALUES ('1', '18854689457', '1', 'luo', '1', '123');
INSERT INTO `dbuser` VALUES ('2', '18846565465', '2', 'luo2', '0', '1234');
INSERT INTO `department` VALUES ('1', 'Training');
INSERT INTO `department` VALUES ('2', 'Financial');
INSERT INTO `department` VALUES ('3', 'HR');
INSERT INTO `department` VALUES ('4', 'GLTC');
INSERT INTO `reservation` VALUES ('1', null, '2014-05-03', '2014-05-03', '103004109@qq.com', '18846584874', '2014-05-01', 'test', '1', '1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('2', null, '2014-05-13', '2014-05-13', '106584488@qq.com', '18846584845', '2014-05-11', 'test', '1', '1', '2', '1', '2');
INSERT INTO `reservation` VALUES ('3', null, '2014-05-16', '2014-05-19', '46565@gail.com', '19888465848', '2014-05-20', 'test', '1', '1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('5', null, '2014-05-21', '2014-05-21', '10300555@qq.com', '188546254', '2014-05-20', 'Test', '1', '-1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('7', null, '2014-05-22', '2014-05-23', 'link@gmail.com', '1845987455', '2014-05-20', 'Test', '1', '-1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('9', '1400663460973-8c3e08c2-64ad-438b-beaf-760ee00eec25', '2014-05-21', '2014-05-21', 'dsad@qq.com', '18854658498', '2014-05-21', 'test', '1', '-1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('10', '1400663561529-9f1dcd61-f547-4530-a288-6fd8eaa65eae', '2014-05-21', '2014-05-24', 'sdsdasda@qq.com', '1874546854655', '2014-05-21', 'test', '1', '1', '1', '1', '1');
INSERT INTO `reservation` VALUES ('11', '6521fc64-334c-46c8-807c-2b1ee8db0d8a', '2014-05-22', '2014-05-23', '1030041097@qq.com', '18845465845', '2014-05-21', 'Test', '8', '1', '1', '0', '1');
INSERT INTO `role` VALUES ('1', 'ROLE_LC');
INSERT INTO `role` VALUES ('2', 'ROLE_TA');
INSERT INTO `room` VALUES ('1', 'TKH OT2 22.5', '2014-05-14', '1', '2');
INSERT INTO `room` VALUES ('2', 'TKH OT2 24.5', '2014-05-06', '0', '1');
INSERT INTO `room` VALUES ('3', 'TKH OT2 25.5', '2014-05-06', '0', '2');
INSERT INTO `room` VALUES ('4', 'TKH OT2 26.5', '2014-05-07', '1', '1');
INSERT INTO `room` VALUES ('5', 'TKH OT2 27.5', '2014-05-06', '1', '3');
INSERT INTO `room` VALUES ('6', 'TKH OT1 22.5', '2014-05-05', '0', '3');
INSERT INTO `room` VALUES ('7', 'TKH OT2 24.5', '2014-05-14', '1', '4');
INSERT INTO `room` VALUES ('8', 'TKH OT2 27.5', '2014-05-15', '0', '4');
INSERT INTO `team` VALUES ('1', '1', 'Training Team1', '1');
INSERT INTO `team` VALUES ('2', '1', 'Training Team2', '2');
INSERT INTO `team` VALUES ('3', '2', 'HR Team1', '1');
INSERT INTO `team` VALUES ('4', '2', 'HR Team2', '2');
INSERT INTO `team` VALUES ('6', '3', 'Financial Team1', '1');
INSERT INTO `team` VALUES ('8', '3', 'Financial Team2', '2');
INSERT INTO `team` VALUES ('9', '4', 'GLTC Team1', '1');
INSERT INTO `team` VALUES ('10', '4', 'GLTC Team2', '2');
