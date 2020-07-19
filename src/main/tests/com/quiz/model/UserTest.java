package com.quiz.model;

import com.quiz.model.user.User;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("baro", "brat", "ras", "shvebi");
    }

    @Test
    public void testConstructor(){
        assertNotNull(user);
    }

    @Test
    public void testGetLoginName(){
        assertEquals("baro", user.getLoginName());
    }

    @Test
    public void testGetHashedPassword() throws NoSuchAlgorithmException {
        assertEquals("d3e01b4928209153ce8716b5677a57004764561d", user.hashPasswordAndGet());
    }

    @Test
    public void testGetPassword(){
        assertEquals("brat", user.getPassword());
    }

    @Test
    public void testGetFirstName(){
        assertEquals("ras", user.getFirstName());
    }

    @Test
    public void testGetLastName(){
        assertEquals("shvebi", user.getLastName());
    }
}