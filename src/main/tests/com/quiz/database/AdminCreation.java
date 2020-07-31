package com.quiz.database;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.junit.Assert.*;
public class AdminCreation {

    private UserDaoImplementation userDao;

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
        userDao = new UserDaoImplementation(dataSource);
    }

    @Test
    public void makeAdmin(){
        String userName = "ikakandelaki";
        userDao.addAdmin(userName);
        assertTrue(userDao.isAdmin(userName));
    }
}
