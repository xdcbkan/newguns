/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql5.5
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : 127.0.0.1:3306
 Source Schema         : article_manage

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 09/10/2019 13:10:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_publish_address
-- ----------------------------
DROP TABLE IF EXISTS `article_publish_address`;
CREATE TABLE `article_publish_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id(运营人员id)',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `address1` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '百度',
  `address2` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '今日头条',
  `address3` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '腾讯快报',
  `address4` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '腾讯新闻',
  `address5` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信公众号',
  `address6` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阿里UC',
  `address7` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新浪微博',
  `address8` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '搜狐',
  `address9` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一点资讯',
  `address10` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '趣头条',
  `address11` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '搜狗',
  `address12` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QIHOO360',
  `address13` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网易',
  `address14` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '京东',
  `address15` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '淘宝',
  `address16` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝',
  `address17` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知乎',
  `address18` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简书',
  `address19` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人民日报',
  `address20` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部官网',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章发布地址表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_receive
-- ----------------------------
DROP TABLE IF EXISTS `article_receive`;
CREATE TABLE `article_receive`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `results_enforcement` int(1) NOT NULL DEFAULT 1 COMMENT '1.审核中（默认）、2.审核通过、3.打回、4.作废、5.配图完成、6.发布',
  `operate_time` date NULL DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 498 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '领取文章记录表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_record
-- ----------------------------
DROP TABLE IF EXISTS `article_record`;
CREATE TABLE `article_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_num` bigint(11) NULL DEFAULT NULL COMMENT '文章编号',
  `main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主标题',
  `subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `operation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作类型',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人',
  `operation_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章记录管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_task
-- ----------------------------
DROP TABLE IF EXISTS `article_task`;
CREATE TABLE `article_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主标题',
  `subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `timeliness_category` int(1) NULL DEFAULT NULL COMMENT '时效性类别(1 实时 2 知识)',
  `task_status` int(1) NULL DEFAULT 1 COMMENT '任务状态(1、待领去  2、已领取)',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `type` int(11) NULL DEFAULT NULL COMMENT '文章分类',
  `recipients_id` int(11) NULL DEFAULT NULL COMMENT '领取人id',
  `recipients` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领取人',
  `pick_up_time` datetime NULL DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 350 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章任务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for code_dbinfo
-- ----------------------------
DROP TABLE IF EXISTS `code_dbinfo`;
CREATE TABLE `code_dbinfo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `db_driver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库驱动',
  `db_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库地址',
  `db_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库账户',
  `db_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接密码',
  `db_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库链接信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for default_unit_price
-- ----------------------------
DROP TABLE IF EXISTS `default_unit_price`;
CREATE TABLE `default_unit_price`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `purchase_price` int(11) NULL DEFAULT NULL COMMENT '用户购买文章单价',
  `settlement_price` int(11) NULL DEFAULT NULL COMMENT '文编文章结算单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '默认单价表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for editor_statistics_day
-- ----------------------------
DROP TABLE IF EXISTS `editor_statistics_day`;
CREATE TABLE `editor_statistics_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `submit` int(11) NULL DEFAULT NULL COMMENT '提交数',
  `examine1_return` int(11) NULL DEFAULT NULL COMMENT '审1退回数',
  `examine2_return` int(11) NULL DEFAULT NULL COMMENT '审2退回数',
  `examine1_return_edit1` int(11) NULL DEFAULT NULL COMMENT '审1修1',
  `examine1_return_edit2` int(11) NULL DEFAULT NULL COMMENT '审1修1',
  `examine2_return_edit1` int(11) NULL DEFAULT NULL COMMENT '审2修1',
  `examine2_return_edit2` int(11) NULL DEFAULT NULL COMMENT '审2修2',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '通过数(审2通过的数量-作废数)',
  `settlement_amount` int(11) NULL DEFAULT NULL COMMENT '结算金额',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 132 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文编每日统计结算表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for editor_statistics_month
-- ----------------------------
DROP TABLE IF EXISTS `editor_statistics_month`;
CREATE TABLE `editor_statistics_month`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `submit` int(11) NULL DEFAULT NULL COMMENT '提交数',
  `examine1_return` int(11) NULL DEFAULT NULL COMMENT '审1退回数',
  `examine2_return` int(11) NULL DEFAULT NULL COMMENT '审2退回数',
  `examine1_return_edit1` int(11) NULL DEFAULT NULL COMMENT '审1修1',
  `examine1_return_edit2` int(11) NULL DEFAULT NULL COMMENT '审1修1',
  `examine2_return_edit1` int(11) NULL DEFAULT NULL COMMENT '审2修1',
  `examine2_return_edit2` int(11) NULL DEFAULT NULL COMMENT '审2修2',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '审2通过的数量-作废数',
  `settlement_amount` int(11) NULL DEFAULT NULL COMMENT '结算金额',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文编每月统计结算表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for examine1_statistics_day
-- ----------------------------
DROP TABLE IF EXISTS `examine1_statistics_day`;
CREATE TABLE `examine1_statistics_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '通过数',
  `unpass` int(11) NULL DEFAULT NULL COMMENT '未通过数',
  `examining_num` int(11) NULL DEFAULT NULL COMMENT '审核中的数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审核1每日工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for examine1_statistics_month
-- ----------------------------
DROP TABLE IF EXISTS `examine1_statistics_month`;
CREATE TABLE `examine1_statistics_month`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '通过数',
  `unpass` int(11) NULL DEFAULT NULL COMMENT '未通过数',
  `examining_num` int(11) NULL DEFAULT NULL COMMENT '审核中的数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审核1每月工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for examine2_statistics_day
-- ----------------------------
DROP TABLE IF EXISTS `examine2_statistics_day`;
CREATE TABLE `examine2_statistics_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '通过数',
  `unpass` int(11) NULL DEFAULT NULL COMMENT '未通过数',
  `examining_num` int(11) NULL DEFAULT NULL COMMENT '审核中的数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审2每日工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for examine2_statistics_month
-- ----------------------------
DROP TABLE IF EXISTS `examine2_statistics_month`;
CREATE TABLE `examine2_statistics_month`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `pass` int(11) NULL DEFAULT NULL COMMENT '通过数',
  `unpass` int(11) NULL DEFAULT NULL COMMENT '未通过数',
  `examining_num` int(11) NULL DEFAULT NULL COMMENT '审核中的数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审2每月工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for historical_article
-- ----------------------------
DROP TABLE IF EXISTS `historical_article`;
CREATE TABLE `historical_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_num` bigint(11) NULL DEFAULT NULL COMMENT '文章编号',
  `main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主标题',
  `subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `prefix` int(1) NULL DEFAULT NULL COMMENT '前缀0-6  提交 审1修1--- 审2修2',
  `article_status` int(2) NULL DEFAULT NULL COMMENT '文章状态草稿  待审1 待审2  审1审核中  审2审核中  审1拒 审2拒 作废  待配图  配图中  文章完成   运营领取  已被购买',
  `picture_num` int(11) NULL DEFAULT NULL COMMENT '配图数',
  `article_type_id` int(11) NULL DEFAULT NULL COMMENT '文章分类id',
  `examine1_id` int(11) NULL DEFAULT NULL COMMENT '审1领取人id',
  `examine2_id` int(11) NULL DEFAULT NULL COMMENT '审2领取人id',
  `layout_id` int(11) NULL DEFAULT NULL COMMENT '配图人id',
  `operate_id` int(11) NULL DEFAULT NULL COMMENT '运营领取人id',
  `purchase_id` int(11) NULL DEFAULT NULL COMMENT '购买人id',
  `editor_new_flag` int(1) NULL DEFAULT NULL COMMENT '0非 1 是最新',
  `return_message` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退回原因',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `new_article_id` int(11) NULL DEFAULT NULL COMMENT '新表id',
  `prescription` int(1) NULL DEFAULT NULL COMMENT '时效性（1.实时型新闻  2.知识型新闻）',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `project_type_id` int(11) NULL DEFAULT NULL COMMENT '项目类别',
  `illustration_type` int(1) NULL DEFAULT NULL COMMENT '插图类别（1.实景图  2.手绘图）',
  `article_task_id` int(11) NULL DEFAULT NULL COMMENT '文章任务id',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `new_main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新主标题',
  `new_subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新副标题',
  `release_type` int(1) NULL DEFAULT 1 COMMENT '发布类型 1-分配、2-自拟',
  `word_count` int(11) NULL DEFAULT NULL COMMENT '字数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 384 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '记录文章历史版本' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for history_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `history_userinfo`;
CREATE TABLE `history_userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `balance` int(11) NULL DEFAULT 0 COMMENT '积分余额',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `alipay` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝账号',
  `residuereceivenum` int(11) NULL DEFAULT NULL COMMENT '剩余领取次数',
  `receivenum` int(11) NULL DEFAULT NULL COMMENT '每日领取上限',
  `price` int(11) NULL DEFAULT NULL COMMENT '文章单价',
  `userid` int(11) NULL DEFAULT NULL COMMENT '介绍人id',
  `usernumber` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `articletypeid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类id',
  `passnum` int(11) NULL DEFAULT NULL COMMENT '文章总通过数',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除  4：待激活）',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段',
  `office_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公地点',
  `entry_time` date NULL DEFAULT NULL COMMENT '入职时间',
  `wechatid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信账户',
  `qqid` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ账户',
  `sinaid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博ID',
  `employee_type` int(1) NULL DEFAULT NULL COMMENT '员工类型(合作/兼职)',
  `employee_status` int(1) NULL DEFAULT NULL COMMENT '员工状态 1.合作中 2.暂停 3.废除/中止',
  `post_rank` decimal(3, 1) NULL DEFAULT NULL COMMENT '岗位职级',
  `nation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `id_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证地址',
  `certificate_validity_period` date NULL DEFAULT NULL COMMENT '证件有效期',
  `first_time_worke` date NULL DEFAULT NULL COMMENT '首次参加工作时间',
  `marital_status` int(1) NULL DEFAULT NULL COMMENT '婚姻状况',
  `registration_type` int(1) NULL DEFAULT NULL COMMENT '户籍类型(1.城镇 2.农村)',
  `current_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现在住址',
  `political_outlook` int(1) NULL DEFAULT NULL COMMENT '政治面貌(1.团员 2.党员)',
  `social_security_account` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人社保账号',
  `provident_fund_account` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人公积金账号',
  `education` int(1) NULL DEFAULT NULL COMMENT '学历',
  `university` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `graduation_time` date NULL DEFAULT NULL COMMENT '毕业时间',
  `major` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业',
  `contract_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同公司',
  `contract_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同类型',
  `first_contract_origin` date NULL DEFAULT NULL COMMENT '首次合同起始日',
  `first_contract_expire` date NULL DEFAULT NULL COMMENT '首次合同到期日',
  `current_contract_origin` date NULL DEFAULT NULL COMMENT '现合同起始日',
  `current_contract_expire` date NULL DEFAULT NULL COMMENT '现合同到期日',
  `contract_period` int(11) NULL DEFAULT NULL COMMENT '合同期限',
  `renewal_times` int(11) NULL DEFAULT NULL COMMENT '续签次数',
  `emergency_contact_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_relationship` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人关系',
  `emergency_contact_sex` int(1) NULL DEFAULT NULL COMMENT '紧急联系人性别',
  `emergency_contact_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `without_children` int(1) NULL DEFAULT NULL COMMENT '有无子女',
  `children_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子女姓名',
  `children_sex` int(1) NULL DEFAULT NULL COMMENT '子女性别',
  `children_birthday` date NULL DEFAULT NULL COMMENT '子女出生日期',
  `id_card_positive` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证（人面像）',
  `id_card_back` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证（国徽面）',
  `academic_certificate` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历证书',
  `diploma` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学位证书',
  `leaving_certificate` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前公司离职证明',
  `photo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工照片',
  `project_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目分类id',
  `sys_user_id` int(11) NULL DEFAULT NULL COMMENT '用户表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for invitation_code
-- ----------------------------
DROP TABLE IF EXISTS `invitation_code`;
CREATE TABLE `invitation_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `invitation_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `amount` int(11) NULL DEFAULT NULL COMMENT '注册次数',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户注册邀请码表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for layout_statistics_day
-- ----------------------------
DROP TABLE IF EXISTS `layout_statistics_day`;
CREATE TABLE `layout_statistics_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `article_num` int(11) NULL DEFAULT NULL COMMENT '配图文章数',
  `picture_num` int(11) NULL DEFAULT NULL COMMENT '配图图片数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配图每日统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for layout_statistics_month
-- ----------------------------
DROP TABLE IF EXISTS `layout_statistics_month`;
CREATE TABLE `layout_statistics_month`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `article_num` int(11) NULL DEFAULT NULL COMMENT '配图文章数',
  `picture_num` int(11) NULL DEFAULT NULL COMMENT '配图图片数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配图每月统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for new_article
-- ----------------------------
DROP TABLE IF EXISTS `new_article`;
CREATE TABLE `new_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_num` bigint(11) NULL DEFAULT NULL COMMENT '文章编号',
  `main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主标题',
  `subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `prefix` int(1) NULL DEFAULT NULL COMMENT '前缀1-5 已提交   审1修1--- 审2修2',
  `article_status` int(2) NULL DEFAULT NULL COMMENT '1草稿  2.待审1 3.待审2  4.审1审核中  5.审2审核中  6.审1拒 7.审2拒 8.审核作废 9. 待配图  10.配图中  11.文章完成  12. 运营领取  13.已被购买 14.运营作废',
  `picture_num` int(11) NULL DEFAULT NULL COMMENT '配图数',
  `article_type_id` int(11) NULL DEFAULT NULL COMMENT '文章分类id',
  `examine1_id` int(11) NULL DEFAULT NULL COMMENT '审1领取人id',
  `examine2_id` int(11) NULL DEFAULT NULL COMMENT '审2领取人id',
  `layout_id` int(11) NULL DEFAULT NULL COMMENT '配图人id',
  `operate_id` int(11) NULL DEFAULT NULL COMMENT '运营领取人id',
  `purchase_id` int(11) NULL DEFAULT NULL COMMENT '购买人id',
  `editor_new_flag` int(1) NULL DEFAULT NULL COMMENT '0非 1 是最新',
  `return_message` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退回/作废原因',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `article_price` int(11) NULL DEFAULT NULL COMMENT '文章单价',
  `prescription` int(1) NULL DEFAULT NULL COMMENT '时效性（1.实时型新闻  2.知识型新闻）',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `project_type_id` int(11) NULL DEFAULT NULL COMMENT '项目类别',
  `illustration_type` int(1) NULL DEFAULT NULL COMMENT '插图类别（1.实景图  2.手绘图）',
  `article_task_id` int(11) NULL DEFAULT NULL COMMENT '文章任务id',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `new_main_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新主标题',
  `new_subheading` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新副标题',
  `release_type` int(1) NULL DEFAULT 1 COMMENT '发布类型 1-分配、2-自拟',
  `word_count` int(11) NULL DEFAULT NULL COMMENT '字数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 321 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '记录最新版本的文章' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for operate_statistics_day
-- ----------------------------
DROP TABLE IF EXISTS `operate_statistics_day`;
CREATE TABLE `operate_statistics_day`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营每天工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for operate_statistics_month
-- ----------------------------
DROP TABLE IF EXISTS `operate_statistics_month`;
CREATE TABLE `operate_statistics_month`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive` int(11) NULL DEFAULT NULL COMMENT '领取数',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `abolish` int(11) NULL DEFAULT NULL COMMENT '作废数',
  `statistics_time` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营每月工作统计表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project_type
-- ----------------------------
DROP TABLE IF EXISTS `project_type`;
CREATE TABLE `project_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类型名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20, 2) NULL DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1486 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `num` int(65) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) NULL DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) NULL DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(65) NULL DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) NULL DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1131404053486120982 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) NULL DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法名称',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 382 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) NULL DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12019 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `receivenum` int(11) NULL DEFAULT NULL COMMENT '每日领取上限',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `balance` int(11) NULL DEFAULT 0 COMMENT '积分余额',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `alipay` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝账号',
  `residuereceivenum` int(11) NULL DEFAULT NULL COMMENT '剩余领取次数',
  `receivenum` int(11) NULL DEFAULT NULL COMMENT '每日领取上限',
  `price` int(11) NULL DEFAULT NULL COMMENT '文章单价',
  `userid` int(11) NULL DEFAULT NULL COMMENT '介绍人id',
  `usernumber` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `articletypeid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类id',
  `passnum` int(11) NULL DEFAULT NULL COMMENT '文章总通过数',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除  4：待激活）',
  `createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段',
  `office_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公地点',
  `entry_time` date NULL DEFAULT NULL COMMENT '入职时间',
  `wechatid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信账户',
  `qqid` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ账户',
  `sinaid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博ID',
  `employee_type` int(1) NULL DEFAULT NULL COMMENT '员工类型(合作/兼职)',
  `employee_status` int(1) NULL DEFAULT NULL COMMENT '员工状态 1.合作中 2.暂停 3.废除/中止',
  `post_rank` decimal(3, 1) NULL DEFAULT NULL COMMENT '岗位职级',
  `nation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `id_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证地址',
  `certificate_validity_period` date NULL DEFAULT NULL COMMENT '证件有效期',
  `first_time_worke` date NULL DEFAULT NULL COMMENT '首次参加工作时间',
  `marital_status` int(1) NULL DEFAULT NULL COMMENT '婚姻状况',
  `registration_type` int(1) NULL DEFAULT NULL COMMENT '户籍类型(1.城镇 2.农村)',
  `current_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现在住址',
  `political_outlook` int(1) NULL DEFAULT NULL COMMENT '政治面貌(1.团员 2.党员)',
  `social_security_account` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人社保账号',
  `provident_fund_account` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人公积金账号',
  `education` int(1) NULL DEFAULT NULL COMMENT '学历',
  `university` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `graduation_time` date NULL DEFAULT NULL COMMENT '毕业时间',
  `major` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业',
  `contract_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同公司',
  `contract_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同类型',
  `first_contract_origin` date NULL DEFAULT NULL COMMENT '首次合同起始日',
  `first_contract_expire` date NULL DEFAULT NULL COMMENT '首次合同到期日',
  `current_contract_origin` date NULL DEFAULT NULL COMMENT '现合同起始日',
  `current_contract_expire` date NULL DEFAULT NULL COMMENT '现合同到期日',
  `contract_period` int(11) NULL DEFAULT NULL COMMENT '合同期限',
  `renewal_times` int(11) NULL DEFAULT NULL COMMENT '续签次数',
  `emergency_contact_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_relationship` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人关系',
  `emergency_contact_sex` int(1) NULL DEFAULT NULL COMMENT '紧急联系人性别',
  `emergency_contact_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `without_children` int(1) NULL DEFAULT NULL COMMENT '有无子女',
  `children_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子女姓名',
  `children_sex` int(1) NULL DEFAULT NULL COMMENT '子女性别',
  `children_birthday` date NULL DEFAULT NULL COMMENT '子女出生日期',
  `id_card_positive` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证（人面像）',
  `id_card_back` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证（国徽面）',
  `academic_certificate` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历证书',
  `diploma` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学位证书',
  `leaving_certificate` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前公司离职证明',
  `photo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工照片',
  `project_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 502 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `aaa` int(11) NOT NULL AUTO_INCREMENT,
  `bbb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aaa`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trans_water
-- ----------------------------
DROP TABLE IF EXISTS `trans_water`;
CREATE TABLE `trans_water`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NULL DEFAULT NULL COMMENT '数量',
  `trans_status` int(1) NULL DEFAULT NULL COMMENT '状态(消费、充值、文编结算、文编提现、提现失败退款)',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '操作人',
  `trans_type` int(1) NULL DEFAULT NULL COMMENT '交易类型(现金、积分)',
  `points_balance` int(11) NULL DEFAULT NULL COMMENT '积分余额',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '交易流水记录的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '交易时间',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易流水表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for withdraw_application
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_application`;
CREATE TABLE `withdraw_application`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ali_pay` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝账号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `amount` int(11) NULL DEFAULT NULL COMMENT '提现金额',
  `withdraw_status` int(11) NULL DEFAULT 1 COMMENT '提现状态   1为提现中、2为提现完成、3为失败',
  `error_message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提现反馈（失败原因）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提现申请表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
