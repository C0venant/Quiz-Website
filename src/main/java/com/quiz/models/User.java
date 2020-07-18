package com.quiz.models;

import com.quiz.utilities.HashUtils;

import java.security.NoSuchAlgorithmException;

public class User {
    private final String loginName;
    private final String password;
    private final String firstName;
    private final String lastName;

    public User(String loginName, String password, String firstName, String lastName) {
        this.loginName = loginName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String hashAndGetPassword() throws NoSuchAlgorithmException {
        byte[] hex = HashUtils.generateHash(password);
        return HashUtils.hexToString(hex);
    }

    public String getHashedPassword(){
        return password;
    }
}
