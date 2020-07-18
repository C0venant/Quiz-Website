package com.quiz.database;

import com.quiz.models.User;

import java.security.NoSuchAlgorithmException;

public interface UserDao {
    public boolean registerUser(User user) throws NoSuchAlgorithmException;
    public User getUser(String loginName);
    public boolean loginUser(String loginName, String password) throws NoSuchAlgorithmException;
}
