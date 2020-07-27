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
    public void testAddAndRemoveFriend() throws NoSuchAlgorithmException {
        User user1 = new User("giogela", "rss", "gio", "gelashvili");
        User user2 = new User("ikakand", "ree", "ika", "kandelaki");
        assertFalse(userDao.addFriend(user1, user2));
        userDao.registerUser(user1);
        assertFalse(userDao.addFriend(user1, user2));
        userDao.registerUser(user2);
        assertTrue(userDao.addFriend(user1, user2));
        assertTrue(userDao.removeFriend(user1, user2));
        userDao.addFriend(user1, user2);
        assertFalse(userDao.addFriend(user1, user2));
        userDao.removeFriend(user1, user2);
        userDao.deleteUser(user2.getLoginName());
        assertFalse(userDao.removeFriend(user1, user2));
        userDao.deleteUser(user1.getLoginName());
        assertFalse(userDao.removeFriend(user1, user2));
    }

    @Test
    public void testAdmin() throws NoSuchAlgorithmException {
        User user = new User("adminGela", "123", "admineqsa", "admineqsa");
        assertTrue(userDao.registerUser(user));
        assertFalse(userDao.removeAdmin(user.getLoginName()));
        assertTrue(userDao.addAdmin(user.getLoginName()));
        assertFalse(userDao.addAdmin(user.getLoginName()));
        assertTrue(userDao.isAdmin(user.getLoginName()));
        assertTrue(userDao.removeAdmin(user.getLoginName()));
        assertFalse(userDao.isAdmin(user.getLoginName()));
        assertTrue(userDao.deleteUser(user.getLoginName()));
        assertFalse(userDao.isAdmin(user.getLoginName()));
        assertFalse(userDao.addAdmin(user.getLoginName()));
        assertFalse(userDao.removeAdmin(user.getLoginName()));
    }
}