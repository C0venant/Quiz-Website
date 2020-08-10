package com.quiz.database.interfaces;

import com.quiz.model.quiz.question.QuestionBasic;

import java.util.List;

public interface QuestionDao {
    boolean addQuestion(String author, QuestionBasic question);
    boolean deleteQuestion(int id);
    QuestionBasic getQuestion(int questionId);
    List<QuestionBasic> getAuthorQuestions(String author);
}
