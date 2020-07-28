package com.quiz.database.interfaces;

import com.quiz.model.user.User;

import java.security.NoSuchAlgorithmException;

public interface UserDao {
    boolean registerUser(User user) throws NoSuchAlgorithmException;

    User getUser(String loginName);

    boolean loginUser(String loginName, String password) throws NoSuchAlgorithmException;

    boolean deleteUser(String loginName);

    boolean addFriend(User user, User friend);

    boolean removeFriend(User user, User friend);

    boolean addAdmin(String loginName);

    boolean removeAdmin(String loginName);

    Boolean isAdmin(String loginName);

    boolean areFriends(User user, User friend);
}
