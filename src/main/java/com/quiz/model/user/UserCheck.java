package com.quiz.model.user;

public class UserCheck {
    private final String username;
    private final String quizName;

    public UserCheck(String username, String quizName) {
        this.username = username;
        this.quizName = quizName;
    }

    public String getUsername() {
        return username;
    }

    public String getQuizName() {
        return quizName;
    }




}
