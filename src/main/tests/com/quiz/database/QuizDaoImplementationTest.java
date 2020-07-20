package com.quiz.database;

import org.junit.Before;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.*;

public class QuizDaoImplementationTest {
    private QuizDaoImplementation quizDao;

    /** replace with yours*/
    private static final String DATABASE_NAME = "quiz_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    @Before
    public void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        quizDao = new QuizDaoImplementation(dataSource);
    }



}