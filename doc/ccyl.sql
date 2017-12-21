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
  `portrait` varchar(1024) comment '头像',
  `is_reward` BOOLEAN comment '是否领取过奖励',
  `org_name` VARCHAR(256) COMMENT '学校/单位名',
  `age` INT(4) COMMENT '年龄',
  `name` VARCHAR(128) COMMENT '姓名',
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
  `question_no` INT(11) COMMENT '题号',
  `file_id` INT(11) COMMENT '题库源文件',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
	`create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
	`is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
	PRIMARY KEY (`id`),
	KEY `t_question_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='题库表';
DROP TABLE IF EXISTS `t_score_record`;
CREATE TABLE `t_score_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `score` INT(4) NOT NULL COMMENT '积分',
  `src_type` TINYINT(2) COMMENT '得分来源',
  `src_question_id` INT(11) COMMENT '来源题目id',
  `self_answer` VARCHAR(4) COMMENT '所选题目选项',
  `src_account_id` INT(11) COMMENT '来源账户id',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_score_account_id_key` (`account_id`,`src_question_id`),
  KEY `t_score_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='积分表';
DROP TABLE IF EXISTS `t_access_token`;
CREATE TABLE `t_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `access_token` VARCHAR(256) NOT NULL COMMENT 'token',
  `token_expires_in` int(11) NOT NULL COMMENT '过期时间',
  `ticket_expires_in` int(11) DEFAULT NULL,
  `token_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `ticket_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `jsapi_ticket` VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_access_token_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='token表';
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` VARCHAR(256) NOT NULL COMMENT '物理地址',
  `type` TINYINT(2) COMMENT '文件类型',
  `busi_type` TINYINT(2) COMMENT '文件业务类型',
  `name` VARCHAR(128) COMMENT '文件名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_file_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='token表';
DROP TABLE IF EXISTS `t_day_question`;
CREATE TABLE `t_day_question` (
  `id` int(11) NOT NULL COMMENT '主键，同question表主键保持一致，不自动生成',
  `day_num` INT(2) COMMENT '竞赛第几天',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `order_num` INT(4) COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `t_day_question_is_del_key` (`is_del`),
  KEY `t_day_question_order_num_key` (`order_num`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8 COMMENT='每日问题表';
DROP PROCEDURE IF EXISTS proce_init_day_questions;
DELIMITER $
CREATE PROCEDURE proce_init_day_questions()
  BEGIN
    DECLARE flag INTEGER;
    DECLARE day_question_id INTEGER;
    DECLARE cur_day_num INTEGER;
    DECLARE cur_order_num INTEGER;
    DECLARE cursor_day_question CURSOR FOR SELECT id FROM t_day_question WHERE date_format(create_time,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d');
    DECLARE cursor_top3_question CURSOR FOR SELECT id FROM t_question WHERE is_del=FALSE AND id > (SELECT ifnull(max(id),0) FROM t_day_question) ORDER BY id LIMIT 3;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET flag=1;
    SET flag=0;
    OPEN cursor_day_question;
    FETCH cursor_day_question INTO day_question_id;
    IF (flag=1) THEN
      SET cur_order_num = 1;
      SET cur_day_num = datediff('2017-12-20',date_format(now(),'%Y-%m-%d'))+1;
      SET flag=0;
      OPEN cursor_top3_question;
      FETCH cursor_top3_question INTO day_question_id;
      WHILE flag <> 1 DO
        INSERT INTO t_day_question(id,day_num,update_time,create_time,is_del,order_num) VALUES (day_question_id,cur_day_num,now(),now(),0,cur_order_num);
        SET cur_order_num = cur_day_num+1;
        FETCH cursor_top3_question INTO day_question_id;
      END WHILE;
      CLOSE cursor_top3_question;
    END IF;
    CLOSE cursor_day_question;
  END $
DELIMITER ;
CREATE EVENT IF NOT EXISTS event_init_day_question
  ON SCHEDULE EVERY 1 DAY STARTS '2017-12-21 00:00:00'
DO CALL proce_init_day_questions();