package com.quiz.database.interfaces;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionBasicFillBlank;
import com.quiz.model.quiz.question.QuestionBasicTest;

import java.util.List;

public interface QuestionDao {
    public boolean addQuestion(String author, QuestionBasic question);
    public boolean addQuestion(String author, QuestionBasicFillBlank question);
    public boolean addQuestion(String author, QuestionBasicTest question);

    public QuestionBasic getQuestion(int questionId);
    public List<QuestionBasic> getAuthorQuestions(String author);
}
