CREATE SCHEMA `wilmar_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE wilmar_test;
DROP DATABASE IF EXISTS user_management;
CREATE DATABASE user_management;

DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
	birtday VARCHAR(10),
	email VARCHAR(50),
	phone VARCHAR(12),
	gender BOOLEAN
);