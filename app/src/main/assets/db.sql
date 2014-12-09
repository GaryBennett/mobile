BEGIN TRANSACTION;
CREATE TABLE "lecture" (
	`_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT,
	`code`	TEXT,
	`lecturer`	TEXT,
	`room`	TEXT,
	`time`	INTEGER,
	`lat`	INTEGER,
	`lon`	INTEGER
);
INSERT INTO `lecture` VALUES ('1','Software Project Management','COC281','Dr Christian Dawson','RT037','36000000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('2','Agent-Based Systems','COC101','Dr Syeda Fatima','CC014','39600000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('3','Cryptography and Network Security','COC140','Dr Ana Salagean','HE010','54000000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('4','Software Project Management','COC281','Dr Christian Dawson','SMB014','61200000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('5','Agent-Based Systems','COC101','Dr Syeda Fatima','CC014','122400000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('6','Algorithm Analysis','COC104','Dr Paul Bell','A001','133200000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('7','Robotics','COC001','Dr Qinggang Meng','J203','136800000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('8','Mobile Application Development','COC155','Dr Huanjia Yang','HE010','144000000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('9','Robotics','COC001','Dr Qinggang Meng','N001','205200000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('10','Careers & Employability','COQ005',NULL,'JB021','216000000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('11','Placements Event','COQ003','Dr Iain Phillips',NULL,'219600000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('12','Mobile Application Development','COC155','Dr Huanjia Yang','N001, N002','291600000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('13','Algorithm Analysis','COC104','Dr Paul Bell','GGB01','302400000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('14','Robotics','COC001','Dr Qinggang Meng','N002','306000000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('15','Cryptography and Network Security','COC140','Dr Ana Salagean','A001','309600000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('16','Software Project Management','COC281','Dr Christian Dawson','J104','313200000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('17','Algorithm Analysis','COC104','Dr Paul Bell','JB021','378000000','52765397','-1226117');
COMMIT;
