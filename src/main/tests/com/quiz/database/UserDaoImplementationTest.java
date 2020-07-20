package com.quiz.database;

import com.quiz.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UserDaoImplementationTest {
    private UserDaoImplementation userDao;

    /** replace with yours*/
    private static final String DATABASE_NAME = "quiz_db";
    private static final String USER = "root";
    private static final String PASSWORD = "hOLDEM1!";

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
        assertFalse(userDao.loginUser("giogelao", "rss"));
        assertFalse(userDao.loginUser("giogela", "rsss"));
        userDao.deleteUser("giogela");
    }

    @Test
    public void testFriend() throws NoSuchAlgorithmException {
        userDao.registerUser(new User("baro", "brat", "ras", "shvebi"));
        userDao.registerUser(new User("bar", "brat", "ras", "shvebi"));
        assertTrue(userDao.addFriend(new User("baro", "brat", "ras", "shvebi"),
                new User("bar", "brat", "ras", "shvebi")));
        assertTrue(userDao.removeFriend(new User("baro", "brat", "ras", "shvebi"),
                new User("bar", "brat", "ras", "shvebi")));
    }

}