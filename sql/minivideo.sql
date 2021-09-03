CREATE TABLE `activities`  (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `activity_title` varchar(255) not NULL COMMENT '帖子标题',
  `content` text not NULL COMMENT '帖子内容',
  `user_id` varchar(50) not NULL COMMENT '用户id',
  `community_id` int not NULL COMMENT '社区id',
  `like_num` int default 0 COMMENT '点赞数量',
  `section_id` int DEFAULT -1 COMMENT '若不是发到专区则为-1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`activity_id`)
);

CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `true_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `addr` varchar(255) NOT NULL COMMENT '地址',
  `phone` varchar(20) NOT NULL COMMENT '联系方式',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`address_id`)
);

CREATE TABLE `administrators`  (
  `administrator_id` varchar(50) NOT NULL,
  `authority` int NULL COMMENT '管理员权限',
  `name` varchar(255) NULL COMMENT '名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`administrator_id`)
);


CREATE TABLE `bullet_screens`  (
  `bullet_screen_id` int NOT NULL AUTO_INCREMENT COMMENT '弹幕id',
  `content` varchar(255) NOT NULL COMMENT '弹幕内容',
  `user_id` varchar(255) NOT NULL COMMENT '发布人id',
  `video_id` int NOT NULL COMMENT '发弹幕的video_id',
  `video_time` varchar(255) NOT NULL COMMENT '在视频中发弹幕的时间',
  `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`bullet_screen_id`)
);

CREATE TABLE `carts`  (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `number` int NOT NULL COMMENT '数量',
  `status` int NOT NULL COMMENT '状态 1正常',
  `price` decimal(8, 2) NOT NULL COMMENT '价格',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`cart_id`)
);

CREATE TABLE `collections`  (
  `collection_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL COMMENT '对象id',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `item_type` int NOT NULL COMMENT '收藏的对象， 1视频 2帖子',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`collection_id`)
);

CREATE TABLE `comments`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `from_id` varchar(50) NOT NULL COMMENT '评论者id，用户',
  `to_id` int NOT NULL COMMENT '评论对象id',
  `comment_type` int NOT NULL COMMENT '1评论视频，2回复评论，3回复帖子',
  `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`comment_id`)
);

CREATE TABLE `communities`  (
  `community_id` int NOT NULL AUTO_INCREMENT,
  `community_name` varchar(50) not NULL,
  `introduction` varchar(255) not NULL,
  `user_id` varchar(50) not NULL,
  `member_num` int default 0,
  `activity_num` int default 0,
  `main_label_id` int not NULL,
  `self_label_ids` varchar(255) NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`community_id`)
);

CREATE TABLE `community_members`  (
  `community_member_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) not NULL,
  `community_id` int not NULL,
  `status` int not NULL COMMENT '状态 1正常 2社区封禁',
  `authority` int not NULL COMMENT '权限 1普通 2社区管理员 3超级管理员 社区创建者为超管',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`community_member_id`)
);

CREATE TABLE `dynamics`  (
  `dynamic_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NULL COMMENT '用户id',
  `content` varchar(1000) NULL COMMENT '内容 若为自动动态  则内容则填写视频id',
  `dynamic_type` int NULL COMMENT '动态类型 1普通动态 2自动动态（up主发了视频）',
  `like_num` int NULL DEFAULT 0,
  `comment_num` int NULL DEFAULT 0,
  `collect_num` int NULL DEFAULT 0,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`dynamic_id`)
);

CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '商品标题',
  `avatar` varchar(255) NOT NULL COMMENT '商品图标',
  `goods_type` integer NOT NULL COMMENT '商品类别',
  `introduction` varchar(255) NOT NULL COMMENT '简介',
  `imgs` varchar(1000) NOT NULL COMMENT '多张图片的url',
  `content` text NOT NULL COMMENT '内容，可以用markdown表示',
  `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`goods_id`)
);

CREATE TABLE `histories`  (
  `history_id` integer NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `item_id` integer NOT NULL COMMENT '阅读对象id',
  `item_type` integer NOT NULL COMMENT '对象类型 1视频，2帖子，3商品',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`history_id`)
);

CREATE TABLE `labels`  (
  `label_id` int NOT NULL AUTO_INCREMENT,
  `label_name` varchar(50) NOT NULL,
  `label_type` int NOT NULL COMMENT '标签类型 1up主标签 2视频分类标签 3系统标签 4自定义标签',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`label_id`)
);

CREATE TABLE `like_map`  (
  `like_map_id` int NOT NULL AUTO_INCREMENT,
  `from_id` varchar(50) not NULL COMMENT '点赞人id',
  `to_id` int not NULL COMMENT '点赞对象id',
  `like_type` int not NULL COMMENT '点赞对象类型 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品 5点赞评论',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`like_map_id`)
);

CREATE TABLE `login_records`  (
  `login_record_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`login_record_id`)
);

CREATE TABLE `media`  (
  `media_id` integer NOT NULL AUTO_INCREMENT,
  `media_url` varchar(255) NOT NULL,
  `media_type` int NOT NULL COMMENT '1 图片类型 2视频类型',
  `user_id` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`media_id`)
);

CREATE TABLE `messages`  (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `from_id` varchar(0) NOT NULL COMMENT '发送者id',
  `from_type` int NOT NULL COMMENT '发送者类型 1系统，2用户，3网站超级管理员',
  `to_id` varchar(0) NOT NULL COMMENT '接受者id',
  `content` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`message_id`)
);

CREATE TABLE `orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `pay_price` decimal(8, 2) NOT NULL,
  `status` int NOT NULL COMMENT '1待付款，2付款完成，3待发货，4待收货，5确认收货',
  `user_id` varchar(50) NOT NULL,
  `address_id` int NOT NULL COMMENT '地址id',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`order_id`)
);

CREATE TABLE `order_goods`  (
  `order_goods_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL COMMENT '订单号',
  `goods_id` int NOT NULL COMMENT '商品id',
  `price` decimal(8, 2) NOT NULL COMMENT '该商品总价',
  `number` int NOT NULL COMMENT '数量',
  `status` int NOT NULL COMMENT '1正常',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`order_goods_id`)
);

CREATE TABLE `relations`  (
  `relation_id` int NOT NULL AUTO_INCREMENT,
  `from_id` varchar(50) NOT NULL,
  `to_id` varchar(50) NOT NULL,
  `relation_type` int NOT NULL COMMENT '1 关注',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`relation_id`)
);

CREATE TABLE `sections`  (
  `section_id` int NOT NULL AUTO_INCREMENT,
  `section_name` varchar(20) NOT NULL,
  `community_id` int NOT NULL,
  `user_id` varchar(50) NULL,
  `created_at` timestamp NULL,
  `updated_at` timestamp NULL,
  `is_deleted` integer NULL,
  PRIMARY KEY (`section_id`)
);

CREATE TABLE `users`  (
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `avatar` varchar(255) NULL COMMENT '头像',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `introduction` varchar(255) NULL COMMENT '简介',
  `status` integer NOT NULL COMMENT '状态 1正常 2封号',
  `follow_num` integer NULL DEFAULT 0 COMMENT '关注的数量',
  `sex` integer NULL  COMMENT '性别 1男2女',
  `fans_num` integer NULL DEFAULT 0 COMMENT '粉丝的数量',
  `like_num` integer NULL DEFAULT 0 COMMENT '视频获得的点赞量总数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
);

CREATE TABLE `video_goods_recommends`  (
  `video_goods_recommend_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) not NULL COMMENT '用户id',
  `video_id` int not NULL COMMENT '视频id',
  `goods_id` int not NULL COMMENT '商品id',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`video_goods_recommend_id`)
);

CREATE TABLE `videos`  (
  `video_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '视频标题',
  `avatar` varchar(255) NOT NULL COMMENT '视频',
  `video_file` varchar(255) NOT NULL COMMENT '存储视频的路径',
  `user_id` varchar(50) NOT NULL COMMENT '发布人的id',
  `introduction` varchar(255) NOT NULL COMMENT '简介',
  `video_type` integer NOT NULL COMMENT '视频类型，1美食类',
  `file_size` integer NULL COMMENT '视频文件大小 单位为MB',
  `video_size` integer NULL COMMENT '视频长度 单位秒',
  `play_num` int NULL DEFAULT 0 COMMENT '播放数量',
  `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
  `collect_num` int NULL DEFAULT 0 COMMENT '收藏数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` integer default 0,
  PRIMARY KEY (`video_id`)
);
