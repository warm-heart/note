
create database healthCare;

use healthCare;



DROP TABLE IF EXISTS `household `;
create  table household  (
`household_id` bigint  NOT NULL AUTO_INCREMENT comment '住户id',

`household_sex`tinyint(3) NOT NULL default  '0' comment '用户性别,默认0 男，1女',

`create_time` timestamp NOT NULL default current_timestamp comment '入院时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',

PRIMARY KEY (`household_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '住户表';


DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed` (
`bed_id` bigint NOT NULL AUTO_INCREMENT comment '床位id',
`bed_num` bigint NOT NULL  comment'床位号',
`floor_num` bigint NOT NULL comment  '所属楼层号',
`room_num` bigint NOT NULL  comment '所属房间号',
key `idx_room_num` (`room_num`),
PRIMARY KEY (`bed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'床位表';


DROP TABLE IF EXISTS `nurse`;
CREATE TABLE `nurse` (
`nurse_id` bigint NOT NULL AUTO_INCREMENT comment '护工id',
`room_num` bigint NOT NULL comment '房间号',

PRIMARY KEY (`nurse_id`),
constraint `fk_room_num` foreign key (`room_num`) references `bed`(`room_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'护工表';




DROP TABLE IF EXISTS `household_healthy`;
CREATE TABLE `household_healthy` (
`healthy_id` bigint NOT NULL AUTO_INCREMENT,
`household_id` bigint NOT NULL comment '住户id',
`blood_sugar` float(8,2) NOT NULL  comment '血糖',
`blood_pressure` float(8,2) NOT NULL  comment '血压',
`blood_fat` float(8,2) NOT NULL  comment '体脂',

`create_time` timestamp NOT NULL default current_timestamp comment '测量时间',

PRIMARY KEY (`healthy_id`),
constraint `household_healthy_fk_1` foreign key (`household_id`) references `household`(`household_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'住户健康表';