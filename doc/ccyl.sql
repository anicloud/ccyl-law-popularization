DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` VARCHAR(32) COMMENT '微信id',
  `account_name` varchar(32) NOT NULL COMMENT '账户名，默认是微信id',
  `account_pwd` varchar(32) not null comment '账户密码',
  `phone` char(15) comment '手机号',
  `email` varchar(128) comment '邮箱',
  `address` varchar(256) comment '地址',
  `province` tinyint(2) comment '省份',
  `nick_name` varchar(64) comment '昵称',
  `portrait` varchar(128) comment '头像',
  `is_reward` BOOLEAN comment '是否领取过奖励',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_account_open_id_key` (`open_id`),
  UNIQUE KEY `t_account_name` (`account_name`),
  KEY `t_account_province_key` (`province`),
  KEY `t_account_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='账户表';
drop table if exists `t_question`;
create table `t_question` (
	`id` int(11) not null auto_increment comment '主键',
	`title` varchar(128) comment '题目',
	`category` TINYINT(2) comment '类型',
	`content` varchar(1024) not null comment '题干',
	`option_one` varchar(1024) comment '选项一',
	`option_two` varchar(1024) comment '选项二',
	`option_three` varchar(1024) comment '选项三',
	`option_four` varchar(1024) comment '选项四',
	`answer` char(4) comment '答案',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
	`create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
	`is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
	PRIMARY KEY (`id`),
	KEY `t_question_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='题库表';
drop table if exists `t_achievement`;
create table `t_achievement` (
	`id` int(11) not null AUTO_INCREMENT comment '主键',
  `account_id` int(11) not null COMMENT '账户id',
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `is_correct` BOOLEAN NOT NULL DEFAULT FALSE,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_achievement_account_id_key` (`account_id`),
  KEY `t_achievement_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='答题记录表';
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `score` INT(4) NOT NULL COMMENT '积分',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_score_account_id_key` (`account_id`),
  KEY `t_score_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='积分表';
DROP TABLE IF EXISTS `t_sign_in`;
CREATE TABLE `t_sign_in` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` INT(11) NOT NULL COMMENT '账户id',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_sign_in_account_id_key` (`account_id`),
  KEY `t_sign_in_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='签到表';