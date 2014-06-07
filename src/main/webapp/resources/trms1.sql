/*
MySQL Data Transfer
Source Host: localhost
Source Database: trms1
Target Host: localhost
Target Database: trms1
Date: 2014/6/7 23:01:45
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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
  `dept_ID` int(11) NOT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

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
  `handle_by` int(11) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

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
INSERT INTO `blacklist` VALUES ('2', 'll', '2');
INSERT INTO `dbuser` VALUES ('1', '18854689457', '1', 'luo', '1', '123', '1');
INSERT INTO `dbuser` VALUES ('2', '18846565465', '2', 'luo2', '0', '1234', '2');
INSERT INTO `dbuser` VALUES ('3', '18845261414', '2', '1030041097@qq.com', '0', '1234', '3');
INSERT INTO `dbuser` VALUES ('4', '18845662658', '1', '2804438948@qq.com', '0', '123', '4');
INSERT INTO `department` VALUES ('1', 'Training');
INSERT INTO `department` VALUES ('2', 'Financial');
INSERT INTO `department` VALUES ('3', 'HR');
INSERT INTO `department` VALUES ('4', 'GLTC');
INSERT INTO `reservation` VALUES ('10', '1400663561529-9f1dcd61-f547-4530-a288-6fd8eaa65eae', '2014-05-21', '2014-05-24', 'sdsdasda@qq.com', '18745468546', '2014-05-21', 'test', '1', '0', '1', '2', '2');
INSERT INTO `reservation` VALUES ('16', 'e38ee11b-836b-4b32-aad0-0204bd21bb8a', '2014-05-28', '2014-06-01', '10300410697@qq.com', '18846589565', '2014-05-26', 'test1', '1', '0', '4', '0', '2');
INSERT INTO `reservation` VALUES ('17', '68642c18-f354-4751-ad9b-ce353ffbc07a', '2014-05-28', '2014-05-28', '454sdadd5@163.com', '18754568585', '2014-05-26', 'Test', '2', '0', '2', '0', '2');
INSERT INTO `reservation` VALUES ('18', '7ac769cd-d4c9-4c60-9668-231c57050d34', '2014-05-31', '2014-05-31', '1030041097@qq.com', '18845453665', '2014-05-29', 'test', '1', '0', '2', '2', '2');
INSERT INTO `reservation` VALUES ('19', 'e518d2e8-ddb5-47aa-927b-54fdd768e680', '2014-05-31', '2014-05-31', '1030041097@qq.com', '18745454454', '2014-05-29', 'Test', '4', '0', '2', '2', '2');
INSERT INTO `reservation` VALUES ('40', 'e543c464-20ec-4bb7-b73b-b17033da7229', '2014-06-20', '2014-06-20', '???', '???', '2014-06-06', '??', '4', '0', '8', '2', '1');
INSERT INTO `reservation` VALUES ('42', '6c0cbdc7-cd34-41c2-8eae-1a48d218048c', '2014-06-20', '2014-06-20', '1030041097@qq.com', '18825162414', '2014-06-07', 'test', '4', '1', '8', '2', '1');
INSERT INTO `reservation` VALUES ('43', '8493c78f-8f32-4d67-b47a-1f434324ccf1', '2014-06-10', '2014-06-10', '1030041097@qq.com', '188254685984', '2014-06-07', 'test', '5', '1', '4', '3', '4');
INSERT INTO `role` VALUES ('1', 'ROLE_LC');
INSERT INTO `role` VALUES ('2', 'ROLE_TA');
INSERT INTO `room` VALUES ('1', 'TKH OT2 22.5', '2014-05-14', '1', '2');
INSERT INTO `room` VALUES ('2', 'TKH OT2 24.5', '2014-05-06', '1', '1');
INSERT INTO `room` VALUES ('3', 'TKH OT2 25.5', '2014-05-06', '1', '2');
INSERT INTO `room` VALUES ('4', 'TKH OT2 26.5', '2014-05-07', '1', '1');
INSERT INTO `room` VALUES ('5', 'TKH OT2 27.5', '2014-05-06', '1', '3');
INSERT INTO `room` VALUES ('6', 'TKH OT1 22.5', '2014-05-05', '0', '3');
INSERT INTO `room` VALUES ('7', 'TKH OT2 24.5', '2014-05-14', '0', '4');
INSERT INTO `room` VALUES ('8', 'TKH OT2 27.5', '2014-05-15', '0', '4');
INSERT INTO `team` VALUES ('1', '1', 'Training Team1', '1');
INSERT INTO `team` VALUES ('2', '1', 'Training Team2', '1');
INSERT INTO `team` VALUES ('3', '3', 'HR Team1', '2');
INSERT INTO `team` VALUES ('4', '3', 'HR Team2', '4');
INSERT INTO `team` VALUES ('6', '2', 'Financial Team1', '3');
INSERT INTO `team` VALUES ('8', '2', 'Financial Team2', '1');
INSERT INTO `team` VALUES ('9', '4', 'GLTC Team1', '1');
INSERT INTO `team` VALUES ('10', '4', 'GLTC Team2', '2');
