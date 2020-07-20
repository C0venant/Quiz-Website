create database if not exists quiz_db;

USE quiz_db;

DROP TABLE IF EXISTS friends;
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
	quizName char(200) primary key,
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
	quizName char(200) NOT NULL,
    questionId INT NOT NULL,
    UNIQUE(quizName, questionId),
    foreign key(quizName) references quiz(quizName),
    foreign key(questionId) references questions(questionId)
);

CREATE TABLE probableAnswers (
	questionId INT NOT NULL,
    probAnswer VARCHAR(500) NOT NULL,
    UNIQUE(questionId, probAnswer),
    foreign key(questionId) references questions(questionId)
);

CREATE TABLE friends (
	user1 CHAR(50),
    user2 CHAR(50),
    foreign key(user1) references users(loginName),
    foreign key(user2) references users(loginName)
);