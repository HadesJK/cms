CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`slideNum`  int(11) NULL DEFAULT NULL COMMENT '��Ƭ����' ,
`buildTime`  datetime NULL DEFAULT NULL COMMENT '�ϴ�ʱ��' ,
`baseDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�洢�Ļ���·��' ,
`zipDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`jpgDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`zipName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`patientName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����' ,
`doctors`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����ҽ��' ,
`descr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��Ͻ��' ,
`hospital`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '���ҽԺ' ,
`item`  varchar(255) CHARACTER SET utf8 COLLATE utf8_latvian_ci NULL DEFAULT NULL COMMENT '��Ŀ:�ν��/�ν��/����CT' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=186
ROW_FORMAT=DYNAMIC
;