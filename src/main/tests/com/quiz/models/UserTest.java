package com.quiz.models;

import org.junit.Before;
import org.junit.Test;

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
    public void testGetHashedPassword(){
        assertEquals("brat", user.getHashedPassword());
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