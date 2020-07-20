package com.quiz.database.interfaces;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;

import java.util.List;

public interface QuestionDao {
    public boolean addQuestion(String author, QuestionBasic question);
    public boolean deleteQuestion(int id);
    public QuestionBasic getQuestion(int questionId);
    public List<QuestionBasic> getAuthorQuestions(String author);
}
