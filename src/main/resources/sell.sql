



DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
`product_id` varchar (32) NOT NULL ,
`product_name` varchar(64) Not NULL comment '商品名称',
`product_price` decimal (8,2) Not NULL comment '商品价格',
`product_stock` int Not NULL comment '库存',
`product_description` varchar(64) Not NULL comment '商品描述',
`product_status` tinyint(3) default '0' comment '商品状态',

`product_icon` varchar(512) Not NULL comment '商品图片',
`category_type` int Not NULL comment '类目编号',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'商品表';




DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
`category_id` int NOT NULL AUTO_INCREMENT,
`category_name` varchar(64) Not NULL comment '类目名称',
`category_type` int Not NULL comment '类目编号',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
PRIMARY KEY (`category_id`),
unique key `uqe_category_type` (`category_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'类目表';



DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
`order_id` varchar (32) NOT NULL  ,
`buyer_name` varchar(32) Not NULL comment '买家名字',
`buyer_phone` varchar(32) Not NULL comment '买家电话',
`buyer_address` varchar(128) Not NULL comment '买家地址',
`buyer_openid` varchar(64) Not NULL comment '买家微信openid',
`order_amount` decimal (8,2) Not NULL comment '订单总金额',
`order_status`  tinyint(3) Not NULL default  '0' comment '订单状态,默认0新下单',
`pay_status`  tinyint(3) Not NULL default  '0' comment '支付状态,默认0未支付',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',

PRIMARY KEY (`order_id`),
key `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment'订单主表';



DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
`detail_id` varchar (32) NOT NULL  ,
`order_id` varchar(32) NOT NULL,
`product_id` varchar(32) NOT NULL,
`product_name` varchar(64) NOT NULL comment '商品名称',
`product_price` decimal (8,2) Not NULL comment '商品价格',
`product_quantity` int  Not NULL comment '商品数量',
`product_icon` varchar(512) Not NULL comment '商品图片',
`create_time` timestamp NOT NULL default current_timestamp comment '创建时间',
`update_time` timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',

PRIMARY KEY (`detail_id`),
key `idx_order_id` (`order_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '订单详情表';



DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
`category_id` int NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '卖家表' ;


