create database if not exists homework_db;

use homework_db;

DROP TABLE IF EXISTS questions;

CREATE TABLE questions (
	id INT AUTO_INCREMENT PRIMARY KEY,
    questionType VARCHAR(500) NOT NULL,
	body VARCHAR(500) NOT NULL,
    maxGrade INT DEFAULT(0),
    imageFile VARCHAR(100),
    correctAnswer VARCHAR(500)
);