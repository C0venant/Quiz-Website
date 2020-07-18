package com.quiz.database;

import com.quiz.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UserDaoImplementationTest {
    private UserDaoImplementation userDao;

    /** replace with yours*/
    private static final String DATABASE_NAME = "homework_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    @Before
    public void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/" + DATABASE_NAME + "?useSSL=false");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        userDao = new UserDaoImplementation(dataSource);
    }

    @Test
    public void testConstructor(){
        assertNotNull(userDao);
    }

    @Test
    public void testDeleteUser(){
        assertFalse(userDao.deleteUser("l"));
    }

    @Test
    public void testGetUser(){
        userDao.deleteUser("tornike");
        String loginName = "tornike";
        assertNull(userDao.getUser(loginName));
    }

    @Test
    public void registerUser1() throws NoSuchAlgorithmException {
        User user = new User("ikakandelaki", "rss", "irakli", "kandelaki");
        assertTrue(userDao.registerUser(user));
        userDao.deleteUser("ikakandelaki");
    }

    @Test
    public void registerUser2() throws NoSuchAlgorithmException {
        User user = new User("giogela", "rss", "gio", "gelashvili");
        assertTrue(userDao.registerUser(user));
        user = new User("giogela", "rsdfd", "sds", "sdsds");
        assertFalse(userDao.registerUser(user));
        userDao.deleteUser("giogela");
    }

    @Test
    public void testLogin1() throws NoSuchAlgorithmException {
        User user = new User("giogela", "rss", "gio", "gelashvili");
        assertTrue(userDao.registerUser(user));
        assertTrue(userDao.loginUser("giogela", "rss"));
        userDao.deleteUser("giogela");
    }
}