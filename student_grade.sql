/*
 Navicat Premium Data Transfer

 Source Server         : grade
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : student_grade

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/12/2021 23:46:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dic_data
-- ----------------------------
DROP TABLE IF EXISTS `dic_data`;
CREATE TABLE `dic_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '等级字典表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic_data
-- ----------------------------
INSERT INTO `dic_data` VALUES (1, 'agileSprintOne', '1001', 'Agile Sprint 1', '2021-11-13 21:19:42', '2021-11-13 21:19:42');
INSERT INTO `dic_data` VALUES (2, 'agileSprintTwo', '1002', 'Agile Sprint 2', '2021-11-13 21:19:56', '2021-11-13 21:19:56');
INSERT INTO `dic_data` VALUES (3, 'agileEssay', '1003', 'Agile Essay', '2021-11-13 21:20:01', '2021-11-13 21:20:01');
INSERT INTO `dic_data` VALUES (4, 'groceryStoresDatabase', '1004', 'Grocery Stores Database', '2021-11-13 21:20:14', '2021-11-13 21:20:14');
INSERT INTO `dic_data` VALUES (5, 'databaseResearchEssay', '1005', 'Database Research Essay ', '2021-11-13 21:20:26', '2021-11-13 21:20:26');
INSERT INTO `dic_data` VALUES (6, 'operationsManagementReport', '1006', 'Operations Management Report', '2021-11-13 21:20:37', '2021-11-13 21:20:37');
INSERT INTO `dic_data` VALUES (7, 'operationsManagementCaseStudy', '1007', 'Operations Management CaseStudy', '2021-11-13 21:20:45', '2021-11-13 21:20:45');
INSERT INTO `dic_data` VALUES (8, 'dataAnalysisReport', '1008', 'Data Analysis Report', '2021-11-13 21:20:50', '2021-11-13 21:20:50');
INSERT INTO `dic_data` VALUES (9, 'changeManagementReport', '1009', 'Change Management Report', '2021-11-13 21:20:58', '2021-11-13 21:20:58');
INSERT INTO `dic_data` VALUES (10, 'crossSiteScriptingEssay', '1010', 'Cross Site Scripting Essay', '2021-11-13 21:21:06', '2021-11-13 21:21:06');
INSERT INTO `dic_data` VALUES (11, 'riskAssessmentReport', '1011', 'Risk Assessment Report', '2021-11-13 21:21:12', '2021-11-13 21:21:12');
INSERT INTO `dic_data` VALUES (12, 'wblReport', '1012', 'WBL Report', '2021-11-13 21:21:25', '2021-11-13 21:21:25');
INSERT INTO `dic_data` VALUES (13, 'wblJournal', '1013', 'WBL Journal', '2021-11-13 21:21:33', '2021-11-13 21:21:33');
INSERT INTO `dic_data` VALUES (14, 'A1', '2001', 'A1', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (15, 'A2', '2002', 'A2', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (16, 'A3', '2003', 'A3', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (17, 'A4', '2004', 'A4', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (18, 'A5', '2005', 'A5', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (19, 'B1', '2006', 'B1', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (20, 'B2', '2007', 'B2', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (21, 'B3', '2008', 'B3', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (22, 'C1', '2009', 'C1', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (23, 'C2', '2010', 'C2', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (24, 'C3', '2011', 'C3', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (28, 'M1', '2015', 'M1', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (29, 'M2', '2016', 'M2', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (30, 'M3', '2017', 'M3', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (31, 'CF', '2018', 'CF', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (32, 'BF', '2019', 'BF', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (33, 'AB', '2020', 'AB', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (34, 'CA', '2021', 'CA', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (35, 'DC', '2022', 'DC', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (36, 'DS', '2023', 'DS', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (37, 'MC', '2024', 'MC', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (38, 'ME', '2025', 'ME', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (39, 'NM', '2026', 'NM', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (40, 'QF', '2027', 'QF', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (41, 'ST', '2028', 'ST', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (42, 'WD', '2029', 'WD', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (43, 'P', '2030', 'P', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (44, 'F', '2031', 'F', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (45, 'D1', '2012', 'D1', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (46, 'D2', '2013', 'D2', '2021-11-13 21:19:31', '2021-11-13 21:19:31');
INSERT INTO `dic_data` VALUES (47, 'D3', '2014', 'D3', '2021-11-13 21:19:31', '2021-11-13 21:19:31');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `student_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联学生信息表',
  `subject_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联字典表id',
  `source` decimal(8, 2) NULL DEFAULT NULL COMMENT '分数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1211 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '成绩表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for source_template
-- ----------------------------
DROP TABLE IF EXISTS `source_template`;
CREATE TABLE `source_template`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rule` json NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of source_template
-- ----------------------------
INSERT INTO `source_template` VALUES (1, '[{\"rate\": 23, \"grade\": \"A1\", \"openScore\": 95, \"closeScore\": 100}, {\"rate\": 22, \"grade\": \"A2\", \"openScore\": 89, \"closeScore\": 94}, {\"rate\": 21, \"grade\": \"A3\", \"openScore\": 83, \"closeScore\": 88}, {\"rate\": 20, \"grade\": \"A4\", \"openScore\": 76, \"closeScore\": 82}, {\"rate\": 19, \"grade\": \"A5\", \"openScore\": 70, \"closeScore\": 75}, {\"rate\": 18, \"grade\": \"B1\", \"openScore\": 67, \"closeScore\": 69}, {\"rate\": 17, \"grade\": \"B2\", \"openScore\": 64, \"closeScore\": 66}, {\"rate\": 16, \"grade\": \"B3\", \"openScore\": 60, \"closeScore\": 63}, {\"rate\": 15, \"grade\": \"C1\", \"openScore\": 57, \"closeScore\": 59}, {\"rate\": 14, \"grade\": \"C2\", \"openScore\": 54, \"closeScore\": 56}, {\"rate\": 13, \"grade\": \"C3\", \"openScore\": 50, \"closeScore\": 53}, {\"rate\": 12, \"grade\": \"D1\", \"openScore\": 47, \"closeScore\": 49}, {\"rate\": 11, \"grade\": \"D2\", \"openScore\": 44, \"closeScore\": 46}, {\"rate\": 10, \"grade\": \"D3\", \"openScore\": 40, \"closeScore\": 43}, {\"rate\": 9, \"grade\": \"M1\", \"openScore\": 37, \"closeScore\": 39}, {\"rate\": 8, \"grade\": \"M2\", \"openScore\": 34, \"closeScore\": 36}, {\"rate\": 7, \"grade\": \"M3\", \"openScore\": 30, \"closeScore\": 33}, {\"rate\": 5, \"grade\": \"CF\", \"openScore\": 20, \"closeScore\": 29}, {\"rate\": 2, \"grade\": \"BF\", \"openScore\": 0, \"closeScore\": 19}]');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `first_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属文件名',
  `overall_grade` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = ' ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_info
-- ----------------------------

-- ----------------------------
-- Table structure for student_subject_level
-- ----------------------------
DROP TABLE IF EXISTS `student_subject_level`;
CREATE TABLE `student_subject_level`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `grade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联成绩表id',
  `dic_data_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联字典表id',
  `credit` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1211 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生科目登记表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_subject_level
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_info_id` bigint NULL DEFAULT NULL,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pass_word` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
