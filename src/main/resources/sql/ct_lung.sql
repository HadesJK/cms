CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`ctId`  int(11) NULL DEFAULT NULL ,
`serialNum`  int(11) NULL DEFAULT NULL COMMENT '��Ƭ���' ,
`slideName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��Ƭ�ļ���' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1719
ROW_FORMAT=DYNAMIC
;