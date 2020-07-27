package com.quiz.database.interfaces;

import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;

import java.util.List;

public interface QuizDao {
    public boolean addQuiz(Quiz quiz);
    public Quiz isPresent(String quizName);
    public Quiz getQuizByName(final String quizName);
    public List<Quiz> getQuizzesByAuthor(final String author);
    public boolean addQuestionToQuiz(String quizName, int questionId);
    public boolean deleteQuestionFromQuiz(String quizName, int questionId);
    public boolean deleteQuiz(String quizName);
    public List<Integer> getAllQuestionIdsFromQuiz(String quizName);
    public boolean answerQuestion(String quizName, String userName, int questionId, String answer);
    public boolean unAnswerQuestion(String quizName, String userName, int questionId);
    public boolean gradeAnsweredQuestion(String quizName, String userName, int questionId, int grade);
    public String getQuestionAnswer(String quizName, String userName, int questionId);
    public List<QuestionBasic> getAllQuestionFromQuiz(List<Integer> questionId);
}
