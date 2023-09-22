/*
 Navicat Premium Data Transfer

 Source Server         : Weblog
 Source Server Type    : MySQL
 Source Server Version : 50739 (5.7.39)
 Source Host           : localhost:3306
 Source Schema         : weblog

 Target Server Type    : MySQL
 Target Server Version : 50739 (5.7.39)
 File Encoding         : 65001

 Date: 07/07/2023 16:28:59
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章id',
    `title`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
    `title_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '文章题图',
    `description` varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章描述',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
    `read_num`    int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '被阅读次数',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_category_rel`;
CREATE TABLE `t_article_category_rel`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id`  bigint(20) UNSIGNED NOT NULL COMMENT '文章id',
    `category_id` bigint(20) UNSIGNED NOT NULL COMMENT '分类id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uni_article_id`(`article_id`) USING BTREE,
    INDEX         `idx_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章所属分类映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_article_content
-- ----------------------------
DROP TABLE IF EXISTS `t_article_content`;
CREATE TABLE `t_article_content`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
    `article_id` bigint(20) NOT NULL COMMENT '文章id',
    `content`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正文内容',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX        `idx_article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag_rel`;
CREATE TABLE `t_article_tag_rel`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章id',
    `tag_id`     bigint(20) UNSIGNED NOT NULL COMMENT '标签id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX        `idx_article_id`(`article_id`) USING BTREE,
    INDEX        `idx_tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章对应标签映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_setting`;
CREATE TABLE `t_blog_setting`
(
    `id`           bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `blog_name`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '博客名称',
    `author`       varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '作者名',
    `introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '介绍语',
    `avatar`       varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者头像',
    `github_home`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
    `csdn_home`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
    `gitee_home`   varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
    `zhihu_home`   varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '知乎主页访问地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `name`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uni_name`(`name`) USING BTREE,
    INDEX         `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_statistics_article_pv
-- ----------------------------
DROP TABLE IF EXISTS `t_statistics_article_pv`;
CREATE TABLE `t_statistics_article_pv`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `pv_date`     date     NOT NULL COMMENT '被统计的日期',
    `pv_count`    bigint(20) UNSIGNED NOT NULL COMMENT 'pv浏览量',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uni_pv_date`(`pv_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '统计表 - 文章 PV' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `name`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uni_name`(`name`) USING BTREE,
    INDEX         `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_create_time`(`create_time`) USING BTREE,
    INDEX         `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `role`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_visitor_record
-- ----------------------------
CREATE TABLE `t_visitor_record`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `visitor` VARCHAR(20) NOT NULL DEFAULT 'agent',
    `ip_address` CHAR(15) NOT NULL DEFAULT '127.0.0.1',
    `ip_region` VARCHAR(64) NOT NULL DEFAULT '未知',
    `visit_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    `is_notify` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `ip_visit_time` (`ip_address`, `visit_time`)
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '访客记录表';