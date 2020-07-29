package com.quiz.database;

import com.quiz.model.request.Request;
import com.quiz.model.request.RequestType;
import com.quiz.model.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.*;

public class RequestDaoImplementationTest {

    private RequestDaoImplementation requestDao;
    private UserDaoImplementation userDao;
    private User user1;
    private User user2;

    /** replace with yours*/
    private static final String DATABASE_NAME = "quiz_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    @Before
    public void setup() throws NoSuchAlgorithmException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        requestDao = new RequestDaoImplementation(dataSource);
        userDao = new UserDaoImplementation(dataSource);
        user1 = new User("Giorgi", "gelusa", "gio", "gelashvili");
        user2 = new User("Irakli", "kandusa", "ika", "kandelaki");
        userDao.registerUser(user1);
        userDao.registerUser(user2);
    }

    @Test
    public void testInit(){
        assertNotNull(requestDao);
    }

    @Test
    public void testAddRequest1(){
        Request original = new Request(user1.getLoginName(), user2.getLoginName(), RequestType.NOTE, "hello", false);
        requestDao.addRequest(original);
        List<Request> list = requestDao.getSentRequests(user1.getLoginName());
        assertEquals(1, list.size());
        Request returned = list.get(0);
        assertEquals(original.getBody(), returned.getBody());
        assertEquals(original.getFromUser(), returned.getFromUser());
        assertEquals(original.getToUser(), returned.getToUser());
        assertEquals(original.getType(), returned.getType());
        assertNotEquals(original.getId(), returned.getId());
        assertTrue(requestDao.deleteRequest(returned.getId()));
    }

    @Test
    public void testAddRequest2(){
        Request original = new Request(user1.getLoginName(), user2.getLoginName(), RequestType.NOTE, "hello", false);
        requestDao.addRequest(original);
        List<Request> list = requestDao.getReceivedRequests(user2.getLoginName());
        assertEquals(1, list.size());
        Request returned = list.get(0);
        assertEquals(original.getBody(), returned.getBody());
        assertEquals(original.getFromUser(), returned.getFromUser());
        assertEquals(original.getToUser(), returned.getToUser());
        assertEquals(original.getType(), returned.getType());
        assertNotEquals(original.getId(), returned.getId());
        assertTrue(requestDao.deleteRequest(returned.getId()));
    }

    @Test
    public void testAddRequest3(){
        Request original1 = new Request(user1.getLoginName(), user2.getLoginName(), RequestType.NOTE, "hello", false);
        Request original2 = new Request(user1.getLoginName(), user2.getLoginName(), RequestType.CHALLENGE, "hello", false);
        requestDao.addRequest(original1);
        requestDao.addRequest(original2);
        List<Request> list = requestDao.getReceivedRequests(user2.getLoginName());
        assertEquals(2, list.size());
        assertTrue(requestDao.deleteRequest(list.get(0).getId()));
        assertTrue(requestDao.deleteRequest(list.get(1).getId()));
    }

    @Test
    public void testBadCase(){
        assertNull(requestDao.getRequest(-1));
        assertFalse(requestDao.deleteRequest(-1));
    }

    @After
    public void finish(){
        userDao.deleteUser(user1.getLoginName());
        userDao.deleteUser(user2.getLoginName());
    }

}