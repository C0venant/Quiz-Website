package com.quiz.database.interfaces;

public interface RemoveDao {
    void removeQuiz(String quizName);
    void removeQuestion(int questionId);
    void removeUser(String userName);
}
