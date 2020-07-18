package com.quiz.models;

public class User {
    private String loginName;
    private String hashedPassword;
    private String firstName;
    private String lastName;

    public User(String loginName, String password, String firstName, String lastName) {
        this.loginName = loginName;
        this.hashedPassword = password;
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

    public String getHashedPassword() {
        return hashedPassword;
    }
}
