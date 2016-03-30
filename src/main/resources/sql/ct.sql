CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`slideNum`  int(11) NULL DEFAULT NULL COMMENT '切片数量' ,
`buildTime`  datetime NULL DEFAULT NULL COMMENT '上传时间' ,
`baseDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储的基本路径' ,
`zipDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`jpgDir`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`zipName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`patientName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者' ,
`doctors`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主治医生' ,
`descr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊断结果' ,
`hospital`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查医院' ,
`item`  varchar(255) CHARACTER SET utf8 COLLATE utf8_latvian_ci NULL DEFAULT NULL COMMENT '项目:肺结节/肺结核/肝脏CT' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=186
ROW_FORMAT=DYNAMIC
;