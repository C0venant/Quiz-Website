create database if not exists homework_db;

use homework_db;

DROP TABLE IF EXISTS probableAnswers;

CREATE TABLE probableAnswers (
	questionId INT NOT NULL,
    probAnswer VARCHAR(500) NOT NULL,
    UNIQUE(questionId, probAnswer)
);