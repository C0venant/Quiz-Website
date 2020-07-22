package com.quiz.database.interfaces;

import com.quiz.model.user.User;

import java.security.NoSuchAlgorithmException;

public interface UserDao {
    public boolean registerUser(User user) throws NoSuchAlgorithmException;
    public User getUser(String loginName);
    public boolean loginUser(String loginName, String password) throws NoSuchAlgorithmException;
    public boolean deleteUser(String loginName);
    public boolean addFriend(User user, User friend);
    public boolean removeFriend(User user, User friend);
    public boolean addAdmin(String loginName);
    public boolean removeAdmin(String loginName);
    public Boolean isAdmin(String loginName);
}
