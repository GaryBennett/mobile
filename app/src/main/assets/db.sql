BEGIN TRANSACTION;
CREATE TABLE "lecture" (
	`_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT,
	`code`	TEXT,
	`lecturer`	TEXT,
	`room`	TEXT,
	`time`	INTEGER UNIQUE,
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
INSERT INTO `lecture` VALUES ('20',NULL,NULL,NULL,NULL,'32400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('23','Agent-Based Systems','COC101','Dr Syeda Fatima','CC014','43200000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('24',NULL,NULL,NULL,NULL,'46800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('25',NULL,NULL,NULL,NULL,'50400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('27',NULL,NULL,NULL,NULL,'57600000',NULL,NULL);
INSERT INTO `lecture` VALUES ('29',NULL,NULL,NULL,NULL,'118800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('31',NULL,NULL,NULL,NULL,'126000000',NULL,NULL);
INSERT INTO `lecture` VALUES ('32',NULL,NULL,NULL,NULL,'129600000',NULL,NULL);
INSERT INTO `lecture` VALUES ('35','Robotics','COC001','Dr Qinggang Meng','J203','140400000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('37','Mobile Application Development','COC155','Dr Huanjia Yang','HE010','147600000','52765397','-1226117');
INSERT INTO `lecture` VALUES ('39',NULL,NULL,NULL,NULL,'208800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('40',NULL,NULL,NULL,NULL,'212400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('43',NULL,NULL,NULL,NULL,'223200000',NULL,NULL);
INSERT INTO `lecture` VALUES ('44',NULL,NULL,NULL,NULL,'226800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('45',NULL,NULL,NULL,NULL,'230400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('46',NULL,NULL,NULL,NULL,'234000000',NULL,NULL);
INSERT INTO `lecture` VALUES ('48',NULL,NULL,NULL,NULL,'295200000',NULL,NULL);
INSERT INTO `lecture` VALUES ('49',NULL,NULL,NULL,NULL,'298800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('54',NULL,NULL,NULL,NULL,'316800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('55',NULL,NULL,NULL,NULL,'320400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('57',NULL,NULL,NULL,NULL,'381600000',NULL,NULL);
INSERT INTO `lecture` VALUES ('58',NULL,NULL,NULL,NULL,'385200000',NULL,NULL);
INSERT INTO `lecture` VALUES ('59',NULL,NULL,NULL,NULL,'388800000',NULL,NULL);
INSERT INTO `lecture` VALUES ('60',NULL,NULL,NULL,NULL,'392400000',NULL,NULL);
INSERT INTO `lecture` VALUES ('61',NULL,NULL,NULL,NULL,'396000000',NULL,NULL);
INSERT INTO `lecture` VALUES ('62',NULL,NULL,NULL,NULL,'399600000',NULL,NULL);
INSERT INTO `lecture` VALUES ('63',NULL,NULL,NULL,NULL,'403200000',NULL,NULL);
INSERT INTO `lecture` VALUES ('64',NULL,NULL,NULL,NULL,'406800000',NULL,NULL);

COMMIT;
