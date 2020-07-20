package com.quiz.database.interfaces;

import com.quiz.model.quiz.Quiz;

import java.util.List;

public interface QuizDao {
    public boolean addQuiz(Quiz quiz);
    public Quiz getQuizByName(final String quizName);
    public List<Quiz> getQuizzesByAuthor(final String author);
}
