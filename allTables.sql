create database if not exists homework_db;

USE homework_db;

DROP TABLE IF EXISTS quizQuestions;
DROP TABLE IF EXISTS probableAnswers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS quiz;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	loginName CHAR(50) PRIMARY KEY,
    hashedPassword CHAR(50) NOT NULL,
    firstName CHAR(50),
    lastName CHAR(50)
);

create table quiz(
	quizId int auto_increment primary key,
    author char(50) not null,
    foreign key(author) references users(loginName)
);

CREATE TABLE questions (
	questionId INT AUTO_INCREMENT PRIMARY KEY,
    questionType VARCHAR(500) NOT NULL,
	body VARCHAR(500) NOT NULL,
    maxGrade INT DEFAULT(0),
    imageFile VARCHAR(100),
    correctAnswer VARCHAR(500),
    author CHAR(50) not null,
    foreign key(author) references users(loginName)
);

CREATE TABLE quizQuestions(
	quizId INT NOT NULL,
    questionId INT NOT NULL,
    UNIQUE(quizId, questionId),
    foreign key(quizId) references quiz(quizId),
    foreign key(questionId) references questions(questionId)
);

CREATE TABLE probableAnswers (
	questionId INT NOT NULL,
    probAnswer VARCHAR(500) NOT NULL,
    UNIQUE(questionId, probAnswer),
    foreign key(questionId) references questions(questionId)
);