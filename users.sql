create database if not exists homework_db;

USE homework_db;

DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
	loginName CHAR(50) PRIMARY KEY,
    hashedPassword CHAR(50) NOT NULL,
    firstName CHAR(50),
    lastName CHAR(50)
);