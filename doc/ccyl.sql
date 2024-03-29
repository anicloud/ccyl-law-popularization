-- MySQL dump 10.13  Distrib 5.5.49-37.9, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ccyl
-- ------------------------------------------------------
-- Server version	5.5.49-37.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_access_token`
--

DROP TABLE IF EXISTS `t_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `access_token` varchar(256) NOT NULL COMMENT 'token',
  `token_expires_in` int(11) NOT NULL COMMENT '过期时间',
  `ticket_expires_in` int(11) DEFAULT NULL,
  `token_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `ticket_create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `jsapi_ticket` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_access_token_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` varchar(32) DEFAULT NULL COMMENT '微信id',
  `account_name` varchar(32) NOT NULL COMMENT '账户名，默认是微信id',
  `account_pwd` varchar(32) NOT NULL COMMENT '账户密码',
  `phone` char(15) DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `province` tinyint(2) DEFAULT NULL COMMENT '省份',
  `nick_name` varchar(512) DEFAULT NULL COMMENT '昵称',
  `portrait` varchar(1024) DEFAULT NULL COMMENT '头像',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `sex` tinyint(1) NOT NULL DEFAULT '1',
  `org_name` varchar(256) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_account_name` (`account_name`),
  UNIQUE KEY `t_account_open_id_key` (`open_id`),
  KEY `t_account_province_key` (`province`),
  KEY `t_account_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_total_daily_awards`
--

DROP TABLE IF EXISTS `t_total_daily_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_total_daily_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_total_daily_awards_is_del_key` (`is_del`),
  KEY `t_total_daily_awards_type_key` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分兑换奖品总表';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `t_daily_awards`
--

DROP TABLE IF EXISTS `t_daily_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_daily_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `account_id` int(11) DEFAULT NULL,
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  KEY `t_daily_awards_is_del_key` (`is_del`),
  KEY `t_daily_awards_type_key` (`type`),
  KEY `t_daily_awards_log_date_key` (`log_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='每日奖品表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_total_top20_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_total_top20_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_total_top20_awards_is_del_key` (`is_del`),
  KEY `t_total_top20_awards_type_key` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='前20奖品总表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_top20_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_top20_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `account_id` int(11) DEFAULT NULL,
  `is_received_award` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否领取奖品',
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_top20_awards_account_id_uk` (`account_id`),
  KEY `t_top20_awards_is_del_key` (`is_del`),
  KEY `t_top20_awards_type_key` (`type`),
  KEY `t_top20_awards_log_date_key` (`log_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='每日前20奖品表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_total_lucky20_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_total_lucky20_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t__total_lucky20_awards_is_del_key` (`is_del`),
  KEY `t__total_lucky20_awards_type_key` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='幸运奖品总表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `t_lucky20_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_lucky20_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` char(32) NOT NULL COMMENT '产品id',
  `code_secret` char(64) NOT NULL COMMENT '卡密',
  `type` tinyint(2) NOT NULL COMMENT '产品类型',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `account_id` int(11) DEFAULT NULL,
  `is_received_award` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否领取奖品',
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_lucky20_awards_account_id_uk` (`account_id`),
  KEY `t_lucky20_awards_is_del_key` (`is_del`),
  KEY `t_lucky20_awards_type_key` (`type`),
  KEY `t_lucky20_awards_log_date_key` (`log_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='每日20幸运奖品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_day_question`
--

DROP TABLE IF EXISTS `t_day_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_day_question` (
  `id` int(11) NOT NULL COMMENT '主键，同question表主键保持一致，不自动生成',
  `day_num` int(2) DEFAULT NULL COMMENT '竞赛第几天',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `order_num` int(4) DEFAULT NULL COMMENT '排序',
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  KEY `t_day_question_is_del_key` (`is_del`),
  KEY `t_day_question_order_num_key` (`order_num`),
  KEY `t_day_question_log_date_key` (`log_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日问题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_file`
--

DROP TABLE IF EXISTS `t_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(256) NOT NULL COMMENT '物理地址',
  `type` tinyint(2) DEFAULT NULL COMMENT '文件类型',
  `busi_type` tinyint(2) DEFAULT NULL COMMENT '文件业务类型',
  `name` varchar(128) DEFAULT NULL COMMENT '文件名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `t_file_is_del_key` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_question`
--

DROP TABLE IF EXISTS `t_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) DEFAULT NULL COMMENT '题目',
  `category` tinyint(2) DEFAULT NULL COMMENT '类型',
  `content` varchar(1024) NOT NULL COMMENT '题干',
  `option_one` varchar(1024) DEFAULT NULL COMMENT '选项一',
  `option_two` varchar(1024) DEFAULT NULL COMMENT '选项二',
  `option_three` varchar(1024) DEFAULT NULL COMMENT '选项三',
  `type` tinyint(2) DEFAULT NULL COMMENT '选项四',
  `answer` char(4) DEFAULT NULL COMMENT '答案',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `question_no` int(11) DEFAULT NULL,
  `file_id` int(11) DEFAULT NULL,
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  KEY `t_question_is_del_key` (`is_del`),
  KEY `t_question_log_date_key` (`log_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='题库表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_score_record`
--

DROP TABLE IF EXISTS `t_score_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_score_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `score` int(4) NOT NULL COMMENT '积分',
  `src_type` tinyint(2) DEFAULT NULL COMMENT '得分来源',
  `src_question_id` int(11) DEFAULT NULL COMMENT '来源题目id',
  `self_answer` varchar(4) DEFAULT NULL COMMENT '所选题目选项',
  `src_account_id` int(11) DEFAULT NULL COMMENT '来源账户id',
  `question_time` int(1) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `log_date` CHAR(32),
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_score_record_id_key` (`account_id`,`src_question_id`),
  KEY `t_score_record_src_type_index` (`src_type`),
  KEY `t_score_record_account_id_index` (`account_id`),
  KEY `t_score_record_src_question_id_index` (`src_question_id`),
  KEY `t_score_record_src_account_id_index` (`src_account_id`),
  KEY `t_score_record_log_date_key` (`log_date`),
  KEY `t_score_record_update_time_key` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_share_relation`
--

DROP TABLE IF EXISTS `t_share_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_share_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `share_id` int(11) NOT NULL COMMENT '分享者id',
  `shared_id` int(11) NOT NULL COMMENT '被分享者id',
  `is_part_in` tinyint(1) NOT NULL DEFAULT '0' COMMENT '被分享者是否参与',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `create_time` timestamp NOT NULL DEFAULT '2018-01-01 00:00:00' COMMENT '创建日期',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_share_relation_unique_key` (`shared_id`),
  KEY `t_share_relation_share_id_key` (`share_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='分享表';




DROP TABLE IF EXISTS `t_daily_total_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_daily_total_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `score` int(8) NOT NULL DEFAULT 0 COMMENT '每天总积分',
  `log_date` CHAR(20) NOT NULL DEFAULT 0 COMMENT '日期',
  `province` tinyint(2) DEFAULT NULL COMMENT '省份',
  `question_time` int(1) DEFAULT 0,
  `correct_count` INT(2) DEFAULT 0 COMMENT '正确题数',
  `question_id` INT(1) DEFAULT 0 COMMENT '当前题id',
  PRIMARY KEY (`id`),
  KEY `t_daily_total_score_account_id_key` (`account_id`),
  KEY `t_daily_total_score_score_key` (`score`),
  KEY `t_daily_total_score_log_date_key` (`log_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='每日总分表';


DROP TABLE IF EXISTS `t_total_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_total_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户id',
  `score` int(8) NOT NULL DEFAULT 0 COMMENT '总积分',
  `province` tinyint(2) DEFAULT NULL COMMENT '省份',
  `thumb_up_count` INT(11) DEFAULT 0 COMMENT '点赞次数',
  `sign_in_count` INT(11) DEFAULT 0 COMMENT '签到次数',
  `invite_count` INT(11) DEFAULT 0 COMMENT '邀请次数',
  `share_count` INT(11) DEFAULT 0 COMMENT '分享次数',
  PRIMARY KEY (`id`),
  KEY `t_total_score_account_id_key` (`account_id`),
  KEY `t_total_score_score` (`score`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='总分表';



/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-30 14:03:18
DROP PROCEDURE IF EXISTS proce_init_daily_info;
DELIMITER $
CREATE PROCEDURE proce_init_daily_info()
  BEGIN
    DECLARE flag INTEGER;
    DECLARE day_question_id INTEGER;
    DECLARE cur_day_num INTEGER;
    DECLARE cur_order_num INTEGER;
    DECLARE awardId INTEGER;
    DECLARE prodId VARCHAR(64);
    DECLARE codeSecret VARCHAR(64);
    DECLARE awardType INTEGER;
    DECLARE cursor_day_question CURSOR FOR SELECT id FROM t_day_question WHERE date_format(create_time,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d');

    DECLARE cursor_top_xz_question CURSOR FOR SELECT id FROM t_question WHERE is_del=FALSE and type='1' ORDER BY id LIMIT 2;
    DECLARE cursor_top_pd_question CURSOR FOR SELECT id FROM t_question WHERE is_del=FALSE and type='2' ORDER BY id LIMIT 2;
    DECLARE cursor_19max_question CURSOR FOR SELECT id FROM t_question WHERE is_del=FALSE and (type='3' or type='4') ORDER BY id LIMIT 1;

    DECLARE cursor_top20_awards CURSOR FOR SELECT id,prod_id,code_secret,type FROM ((SELECT id,prod_id,code_secret,type FROM t_total_top20_awards WHERE is_del=FALSE AND type=5 LIMIT 1)
                                                UNION
                                              (SELECT id,prod_id,code_secret,type FROM t_total_top20_awards WHERE is_del=FALSE AND type=6 LIMIT 1)
                                                UNION
                                              (SELECT id,prod_id,code_secret,type FROM t_total_top20_awards WHERE is_del=FALSE AND type=7 LIMIT 1)
                                                UNION
                                              (SELECT id,prod_id,code_secret,type FROM t_total_top20_awards WHERE is_del=FALSE AND type=8 LIMIT 17))t;
    DECLARE cursor_lucky20_awards CURSOR FOR SELECT id,prod_id,code_secret,type FROM t_total_lucky20_awards WHERE is_del=FALSE LIMIT 20;
    DECLARE cursor_daily_awards CURSOR FOR SELECT id,prod_id,code_secret,type FROM ((SELECT id,prod_id,code_secret,type FROM t_total_daily_awards WHERE is_del=FALSE AND type=1 LIMIT 10)
                                                UNION
                                              (SELECT id,prod_id,code_secret,type FROM t_total_daily_awards WHERE is_del=FALSE AND type=2 LIMIT 10))t;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET flag=1;

    SET flag=0;
    OPEN cursor_lucky20_awards;
    FETCH cursor_lucky20_awards INTO awardId,prodId,codeSecret,awardType;
    WHILE flag <> 1 DO
      INSERT INTO t_lucky20_awards(prod_id, code_secret, type,create_time) VALUES (prodId,codeSecret,awardType,now());
      UPDATE t_total_lucky20_awards SET is_del=TRUE WHERE id=awardId;
      FETCH cursor_lucky20_awards INTO awardId,prodId,codeSecret,awardType;
    END WHILE;
    CLOSE cursor_lucky20_awards;

    SET flag=0;
    OPEN cursor_top20_awards;
    FETCH cursor_top20_awards INTO awardId,prodId,codeSecret,awardType;
    WHILE flag <> 1 DO
      INSERT INTO t_top20_awards(prod_id, code_secret, type,create_time) VALUES (prodId,codeSecret,awardType,now());
      UPDATE t_total_top20_awards SET is_del=TRUE WHERE id=awardId;
      FETCH cursor_top20_awards INTO awardId,prodId,codeSecret,awardType;
    END WHILE;
    CLOSE cursor_top20_awards;

    SET flag=0;
    OPEN cursor_daily_awards;
    FETCH cursor_daily_awards INTO awardId,prodId,codeSecret,awardType;
    WHILE flag <> 1 DO
      INSERT INTO t_daily_awards(prod_id, code_secret, type, create_time) VALUES (prodId,codeSecret,awardType,now());
      UPDATE t_total_daily_awards SET is_del=TRUE WHERE id=awardId;
      FETCH cursor_daily_awards INTO awardId,prodId,codeSecret,awardType;
    END WHILE;
    CLOSE cursor_daily_awards;

    SET flag=0;
    OPEN cursor_day_question;
    FETCH cursor_day_question INTO day_question_id;
    IF (flag=1) THEN
      SET cur_order_num = 1;
      SET cur_day_num = datediff(date_format(now(),'%Y-%m-%d'),'2018-01-19')+1;
      SET flag=0;
      OPEN cursor_top_xz_question;
      FETCH cursor_top_xz_question INTO day_question_id;
      WHILE flag <> 1 DO
        INSERT INTO t_day_question(id,day_num,update_time,create_time,is_del,order_num) VALUES (day_question_id,cur_day_num,now(),now(),0,cur_order_num);
        UPDATE t_question SET is_del=TRUE where id=day_question_id;
        SET cur_order_num = cur_order_num+1;
        FETCH cursor_top_xz_question INTO day_question_id;
      END WHILE;
      CLOSE cursor_top_xz_question;
      SET flag=0;
      OPEN cursor_top_pd_question;
      FETCH cursor_top_pd_question INTO day_question_id;
      WHILE flag <> 1 DO
        INSERT INTO t_day_question(id,day_num,update_time,create_time,is_del,order_num) VALUES (day_question_id,cur_day_num,now(),now(),0,cur_order_num);
        UPDATE t_question SET is_del=TRUE where id=day_question_id;
        SET cur_order_num = cur_order_num+1;
        FETCH cursor_top_pd_question INTO day_question_id;
      END WHILE;
      CLOSE cursor_top_pd_question;
      SET flag=0;
      OPEN cursor_19max_question;
      FETCH cursor_19max_question INTO day_question_id;
      WHILE flag <> 1 DO
        INSERT INTO t_day_question(id,day_num,update_time,create_time,is_del,order_num) VALUES (day_question_id,cur_day_num,now(),now(),0,cur_order_num);
        UPDATE t_question SET is_del=TRUE where id=day_question_id;
        SET cur_order_num = cur_order_num+1;
        FETCH cursor_19max_question INTO day_question_id;
      END WHILE;
      CLOSE cursor_19max_question;
    END IF;
    CLOSE cursor_day_question;
  END $
DELIMITER ;

DROP EVENT IF EXISTS event_init_day_question;
CREATE EVENT event_init_day_question
  ON SCHEDULE EVERY 1 DAY STARTS '2018-01-19 00:05:00'
  ON COMPLETION  PRESERVE
  ENABLE
DO CALL proce_init_daily_info();
SET GLOBAL event_scheduler = 1;