/*
MySQL Data Transfer
Source Host: localhost
Source Database: trms1
Target Host: localhost
Target Database: trms1
Date: 2014/6/9 12:29:14
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `dbuser` VALUES ('1', '18854689457', '1', '1030041097@qq.com', '1', '1234', '1');
INSERT INTO `dbuser` VALUES ('2', '18846565465', '2', 'aaronlin88@126.com', '0', '1234', '2');
INSERT INTO `dbuser` VALUES ('3', '18845261414', '1', '510025953@qq.com', '1', '1234', '3');
INSERT INTO `dbuser` VALUES ('4', '18845662658', '2', '2804438948@qq.com', '0', '1234', '4');
INSERT INTO `department` VALUES ('1', 'GBM');
INSERT INTO `department` VALUES ('2', 'Global CMB & TSC');
INSERT INTO `department` VALUES ('3', 'Global Finance IT');
INSERT INTO `department` VALUES ('4', 'Global HR IT');
INSERT INTO `role` VALUES ('1', 'ROLE_LC');
INSERT INTO `role` VALUES ('2', 'ROLE_TA');
INSERT INTO `room` VALUES ('1', 'TKH OT2 22.5', '2014-05-14', '1', '1');
INSERT INTO `room` VALUES ('2', 'TKH OT2 24.5', '2014-05-06', '1', '1');
INSERT INTO `room` VALUES ('3', 'TKH OT2 25.5', '2014-05-06', '1', '1');
INSERT INTO `room` VALUES ('4', 'TKH OT2 26.5', '2014-05-07', '1', '1');
INSERT INTO `room` VALUES ('5', 'TKH OT2 27.5', '2014-05-06', '1', '2');
INSERT INTO `room` VALUES ('6', 'TKH OT1 22.5', '2014-05-05', '0', '2');
INSERT INTO `room` VALUES ('7', 'TKH OT2 24.5', '2014-05-14', '0', '2');
INSERT INTO `room` VALUES ('8', 'TKH OT2 28', '2014-05-15', '0', '2');
INSERT INTO `room` VALUES ('9', 'TKH OT2 27.5', '2014-06-10', '0', '3');
INSERT INTO `room` VALUES ('10', 'TKH OT2 28.5', '2014-06-10', '0', '3');
INSERT INTO `room` VALUES ('11', 'TKH OT2 29', '2014-06-03', '0', '3');
INSERT INTO `room` VALUES ('12', 'TKH OT1 28.5', '2014-06-10', '1', '3');
INSERT INTO `room` VALUES ('13', 'TKH OT1 22', '2014-06-10', '0', '4');
INSERT INTO `room` VALUES ('14', 'TKH OT1 23', '2014-06-03', '0', '4');
INSERT INTO `room` VALUES ('15', 'TKH OT1 22.5', '2014-06-07', '0', '4');
INSERT INTO `room` VALUES ('16', 'TKH OT1 22', '2014-06-06', '1', '4');
INSERT INTO `team` VALUES ('1', '1', 'GLT_GLB_GBM_FO_FINANCE', '0');
INSERT INTO `team` VALUES ('2', '1', 'GLT_GLB_GBM_FO_FIXED_INCOME', '0');
INSERT INTO `team` VALUES ('3', '3', 'GLT_GLB_GBM_FO_CROSS_ASSET_IT', '0');
INSERT INTO `team` VALUES ('4', '3', 'GLT_GLB_GBM_FO_ETD&OTC', '0');
INSERT INTO `team` VALUES ('6', '2', 'GLT_GLB_GBM_FO_FXMM', '0');
INSERT INTO `team` VALUES ('8', '2', 'GLT_GLB_GBM_FO_GFIX', '0');
INSERT INTO `team` VALUES ('9', '4', 'GLT_GLB_GBM_GLOBAL_PRD_SUP', '0');
INSERT INTO `team` VALUES ('10', '4', 'GLTC Team2', '0');
INSERT INTO `team` VALUES ('12', '4', 'GLT_Global CMB&TSC_GTRF', '0');
INSERT INTO `team` VALUES ('13', '3', 'GLT_Global CMB&TSC_GTRF APAC', '0');
INSERT INTO `team` VALUES ('14', '3', 'GLT_GLOBAL FUNCTION_INT SYS', '0');
INSERT INTO `team` VALUES ('15', '4', 'GFIT-Asia Pacific Finance IT', '0');
INSERT INTO `team` VALUES ('16', '3', 'GFIT-Finance Accounting', '0');
INSERT INTO `team` VALUES ('17', '4', 'Global HR IT - SWD', '0');
