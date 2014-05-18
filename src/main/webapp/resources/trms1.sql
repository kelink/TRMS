/*
MySQL Data Transfer
Source Host: localhost
Source Database: trms1
Target Host: localhost
Target Database: trms1
Date: 2014/5/18 10:36:28
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
  `Applied_End_Date` datetime DEFAULT NULL,
  `Applied_Start_Date` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `order_Time` datetime DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `room_ID` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `team_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  PRIMARY KEY (`reservation_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `dbuser` VALUES ('1', '18856246584', '1', 'luo', '1', '123');
INSERT INTO `dbuser` VALUES ('2', '18846597848', '2', 'luo2', '0', '1234');
INSERT INTO `department` VALUES ('1', 'Training');
INSERT INTO `department` VALUES ('2', 'Financial');
INSERT INTO `department` VALUES ('3', 'HR');
INSERT INTO `department` VALUES ('4', 'GLTC');
INSERT INTO `role` VALUES ('1', 'ROLE_LC');
INSERT INTO `role` VALUES ('2', 'ROLE_TA');
INSERT INTO `room` VALUES ('1', 'TKH OT2 22.5', '2014-05-02', '0', '1');
INSERT INTO `room` VALUES ('2', 'TKH OT2 24.5', '2014-05-06', '1', '1');
INSERT INTO `room` VALUES ('3', 'TKH OT2 25.5', '2014-05-12', '0', '1');
INSERT INTO `room` VALUES ('4', 'TKH OT2 26.5', '2014-05-01', '1', '1');
INSERT INTO `room` VALUES ('5', 'TKH OT2 27.5', '2014-04-07', '1', '1');
INSERT INTO `room` VALUES ('6', 'TKH OT1 22.5', '2014-04-24', '0', '2');
INSERT INTO `room` VALUES ('7', 'TKH OT1 23.5', '2014-04-07', '0', '2');
INSERT INTO `room` VALUES ('8', 'TKH OT1 24.5', '2014-04-07', '0', '3');
INSERT INTO `room` VALUES ('9', 'TKH OT1 27.5', '2014-04-20', '1', '3');
INSERT INTO `room` VALUES ('10', 'TKH OT1 26.5', '2014-05-26', '0', '4');
INSERT INTO `room` VALUES ('11', 'TKH OT1 28.5', '2014-04-27', '1', '4');
INSERT INTO `team` VALUES ('1', '1', 'Training Team 1', '1');
INSERT INTO `team` VALUES ('2', '1', 'Training Team 2', '2');
INSERT INTO `team` VALUES ('3', '2', 'Financial team1', '1');
INSERT INTO `team` VALUES ('4', '2', 'Financial team1', '2');
INSERT INTO `team` VALUES ('5', '3', 'HR Team1', '1');
INSERT INTO `team` VALUES ('6', '3', 'HR Team2', '2');
INSERT INTO `team` VALUES ('7', '4', 'GLTC Team1', '1');
INSERT INTO `team` VALUES ('8', '4', 'GLTC Team1', '2');
