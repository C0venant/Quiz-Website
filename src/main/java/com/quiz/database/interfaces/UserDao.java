package com.quiz.database.interfaces;

import com.quiz.model.user.User;

import java.security.NoSuchAlgorithmException;

public interface UserDao {
    public boolean registerUser(User user) throws NoSuchAlgorithmException;
    public User getUser(String loginName);
    public boolean loginUser(String loginName, String password) throws NoSuchAlgorithmException;
    public boolean deleteUser(String loginName);
}
