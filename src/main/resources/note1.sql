create database note;

use note;

DROP TABLE IF EXISTS `user`;
create  table user (
`user_id` bigint  NOT NULL AUTO_INCREMENT comment '用户id',
`user_name` varchar(64) NOT NULL comment '用户姓名',
`nick_name` varchar(64) NOT NULL comment '用户昵称',
`user_password` varchar(64) NOT NULL comment '用户密码',
`user_address` varchar(64)   comment '用户地址',
`user_email` varchar (64)   comment '用户邮箱',
`user_phone` varchar(64)    comment '用户电话',
`create_time` timestamp NOT NULL default current_timestamp comment '注册时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
unique key `uk_user_name`(`user_name`),
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';



DROP TABLE IF EXISTS `note_category`;
CREATE TABLE `note_category` (
`category_id` bigint NOT NULL AUTO_INCREMENT,
`user_id` bigint NOT NULL,
`category_name` varchar(64) Not NULL comment '分类名称',
`category_description` varchar(64)   comment '类目描述',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`category_id`),
key `category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'笔记类目表';



DROP TABLE IF EXISTS `note_info`;
CREATE TABLE `note_info` (
`note_id` bigint NOT NULL AUTO_INCREMENT,
`note_name` varchar(64) Not NULL comment '笔记标题',
`note_description` varchar(64)   comment '笔记描述',
`note_context` text   comment '笔记内容',
`note_status`tinyint(3) Not NULL default  '0' comment '笔记状态,默认0未分享，1已分享',
`category_id` bigint Not NULL comment '类目编号',
`user_id` bigint NOT NULL,
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
 key `idx_note_name` (`note_name`),
PRIMARY KEY (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'笔记表';


DROP TABLE IF EXISTS `note_tag`;
CREATE TABLE `note_tag` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `note_id` bigint Not NULL comment '笔记Id',
  `note_label` varchar(64)   comment '笔记标签',
  `create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
  `update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
  key `idx_note_id` (`note_id`),
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'笔记标签';





DROP TABLE IF EXISTS `note_share`;
CREATE TABLE `note_share` (
`share_id` bigint NOT NULL AUTO_INCREMENT,
`user_id` bigint NOT NULL,
`note_id` bigint NOT NULL ,
`note_name` varchar(64) Not NULL comment '笔记标题',
`note_label` varchar(64) Not NULL comment '笔记标签',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`share_id`),
constraint `note_share_fk_1` foreign key (`user_id`) references `user`(`user_id`),
constraint `note_share_fk_2` foreign key (`note_id`) references `note_info`(`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'笔记分享表';