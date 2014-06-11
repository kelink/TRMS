/*
MySQL Data Transfer
Source Host: localhost
Source Database: trms1
Target Host: localhost
Target Database: trms1
Date: 2014/6/11 15:32:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
CREATE TABLE `blacklist` (
  `bl_ID` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `team_ID` int(11) NOT NULL,
  PRIMARY KEY (`bl_ID`),
  KEY `Fk_Bl_team_ID` (`team_ID`),
  CONSTRAINT `Fk_Bl_team_ID` FOREIGN KEY (`team_ID`) REFERENCES `team` (`team_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for department
-- ----------------------------
CREATE TABLE `department` (
  `department_ID` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) NOT NULL,
  PRIMARY KEY (`department_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `blacklist` VALUES ('12', 'out of date', '2');
INSERT INTO `dbuser` VALUES ('1', '18846521541', '1', '1030041097@qq.com', '0', '1234', '1');
INSERT INTO `dbuser` VALUES ('2', '18846565465', '2', 'aaronlin88@126.com', '0', '1234', '2');
INSERT INTO `dbuser` VALUES ('3', '18845261414', '1', '510025953@qq.com', '1', '1234', '3');
INSERT INTO `dbuser` VALUES ('4', '18845662658', '2', '2804438948@qq.com', '0', '1234', '4');
INSERT INTO `department` VALUES ('1', 'GBM');
INSERT INTO `department` VALUES ('2', 'Global CMB & TSC');
INSERT INTO `department` VALUES ('3', 'Global Finance IT');
INSERT INTO `department` VALUES ('4', 'Global HR IT');
INSERT INTO `department` VALUES ('7', 'Training Team');
INSERT INTO `reservation` VALUES ('53', '7f1d789c-a1fd-457a-bb03-854e1429e243', '2014-06-18', '2014-06-18', '1030041097@qq.com', '18856245648', '2014-06-09', 'test', '7', '-1', '2', '4', '1');
INSERT INTO `reservation` VALUES ('56', '6f1d9856c-a1fd-457a-bb03-854e1429e243', '2014-06-18', '2014-06-18', '2804438948@qq.com', '18856245648', '2014-06-09', 'test', '7', '-1', '2', '4', '1');
INSERT INTO `reservation` VALUES ('61', '919dfd57-6e50-413e-ba20-8b5b3f5e8da2', '2014-06-17', '2014-06-17', '1030041097@qq.com', '18856245648', '2014-06-10', 'test', '6', '-1', '2', '4', '4');
INSERT INTO `reservation` VALUES ('62', 'd1d3a32b-2049-4634-aa39-dc9b2d56037c', '2014-06-11', '2014-06-11', '2804438948@qq.com', '18856245648', '2014-06-10', 'test', '6', '1', '2', '4', '4');
INSERT INTO `reservation` VALUES ('63', '71bc8560-0fa1-47ca-a8d6-45eca326c552', '2014-06-17', '2014-06-17', '2804438948@qq.com', '18856245648', '2014-06-10', 'test', '1', '1', '8', '4', '4');
INSERT INTO `reservation` VALUES ('65', '78cdd016-6420-4f23-9573-dbc3a6fe7a6d', '2014-06-13', '2014-06-13', '1030041097@qq.com', '18846521456', '2014-06-11', 'test', '20', '1', '18', '4', '4');
INSERT INTO `reservation` VALUES ('66', '7861e292-fb1e-430d-a2e3-780d172ecc51', '2014-06-12', '2014-06-15', '1030041097@qq.com', '123', '2014-06-11', '123', '1', '-1', '3', '4', '1');
INSERT INTO `reservation` VALUES ('67', '9ee72c00-a8e0-4052-bf86-d6deecbcff28', '2014-06-20', '2014-06-20', 'dsad', 'dsad', '2014-06-11', 'dsad', '1', '-1', '3', '0', '1');
INSERT INTO `reservation` VALUES ('68', '2a1fee85-4b78-413f-a4d8-a199c3ab146f', '2014-06-18', '2014-06-18', 'dasd', 'dsad', '2014-06-11', 'dsad', '1', '-1', '4', '0', '1');
INSERT INTO `reservation` VALUES ('69', '25b1cece-4f6e-4f7c-afbf-081592a1173c', '2014-06-18', '2014-06-19', 'dasd', 'dasd', '2014-06-11', 'dasdas', '1', '-1', '3', '0', '1');
INSERT INTO `reservation` VALUES ('70', '39773300-2060-4381-a3a5-076c29f85db7', '2014-06-19', '2014-06-19', 'sadasd', 'dsad', '2014-06-11', 'dsad', '1', '-1', '3', '0', '1');
INSERT INTO `reservation` VALUES ('71', '1c7d6dd9-16c7-45b5-9a07-b88348f06f94', '2014-06-12', '2014-06-13', 'dsa', 'dsa', '2014-06-11', 'dasd', '1', '1', '3', '4', '4');
INSERT INTO `reservation` VALUES ('72', 'f4396ade-8b8d-4656-b8c6-5ccc5dab1414', '2014-06-18', '2014-06-18', 'dsadd', 'sad', '2014-06-11', 'sad', '1', '1', '6', '4', '4');
INSERT INTO `role` VALUES ('1', 'ROLE_LC');
INSERT INTO `role` VALUES ('2', 'ROLE_TA');
INSERT INTO `room` VALUES ('1', 'TKH OT2 22.5', '2014-05-14', '0', '1');
INSERT INTO `room` VALUES ('2', 'TKH OT2 23.5', '2014-05-06', '0', '1');
INSERT INTO `room` VALUES ('3', 'TKH OT2 24.5', '2014-05-06', '0', '1');
INSERT INTO `room` VALUES ('4', 'TKH OT2 25.5', '2014-05-07', '0', '1');
INSERT INTO `room` VALUES ('5', 'TKH OT2 26.5', '2014-05-06', '1', '2');
INSERT INTO `room` VALUES ('6', 'TKH OT2 27.5', '2014-05-05', '0', '2');
INSERT INTO `room` VALUES ('7', 'TKH OT2 28.5', '2014-05-14', '0', '2');
INSERT INTO `room` VALUES ('8', 'TKH OT2 29.5', '2014-05-15', '0', '2');
INSERT INTO `room` VALUES ('9', 'TKH OT1 22', '2014-06-10', '0', '3');
INSERT INTO `room` VALUES ('10', 'TKH OT1 23', '2014-06-10', '0', '3');
INSERT INTO `room` VALUES ('11', 'TKH OT1 24', '2014-06-03', '0', '3');
INSERT INTO `room` VALUES ('12', 'TKH OT1 25', '2014-06-10', '1', '3');
INSERT INTO `room` VALUES ('13', 'TKH OT1 26', '2014-06-10', '0', '4');
INSERT INTO `room` VALUES ('14', 'TKH OT1 27', '2014-06-03', '0', '4');
INSERT INTO `room` VALUES ('15', 'TKH OT1 28', '2014-06-07', '0', '4');
INSERT INTO `room` VALUES ('16', 'TKH OT1 29', '2014-06-06', '1', '4');
INSERT INTO `room` VALUES ('20', 'TKH OT1 14.5', '2014-06-11', '0', '7');
INSERT INTO `team` VALUES ('2', '1', 'GLT_GLB_GBM_FO_FIXED_INCOME', '1');
INSERT INTO `team` VALUES ('3', '1', 'GLT_GLB_GBM_FO_CROSS_ASSET_IT', '1');
INSERT INTO `team` VALUES ('4', '1', 'GLT_GLB_GBM_FO_ETD&OTC', '1');
INSERT INTO `team` VALUES ('6', '1', 'GLT_GLB_GBM_FO_FXMM', '1');
INSERT INTO `team` VALUES ('8', '1', 'GLT_GLB_GBM_FO_GFIX', '1');
INSERT INTO `team` VALUES ('9', '1', 'GLT_GLB_GBM_FO_SALES', '1');
INSERT INTO `team` VALUES ('10', '1', 'GLT_GLB_GBM_GLOBAL_PRD_SUP', '1');
INSERT INTO `team` VALUES ('12', '2', 'CMB Regional - eDist&ClService', '2');
INSERT INTO `team` VALUES ('13', '2', 'GLT_Global CMB&TSC_GTRF', '2');
INSERT INTO `team` VALUES ('14', '2', 'GLT_Global CMB&TSC_GTRF APAC', '2');
INSERT INTO `team` VALUES ('15', '3', 'GLT_GLOBAL FUNCTION_INT SYS', '3');
INSERT INTO `team` VALUES ('16', '3', 'GFIT-Asia Pacific Finance IT', '3');
INSERT INTO `team` VALUES ('17', '3', 'GFIT-Finance Accounting', '3');
INSERT INTO `team` VALUES ('18', '4', 'Global HR IT - SWD', '4');
