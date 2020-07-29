package com.quiz.database.interfaces;

import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;

import java.util.List;

public interface QuizDao {
    boolean addQuiz(Quiz quiz);

    Quiz isPresent(String quizName);

    Quiz getQuizByName(final String quizName);

    List<Quiz> getQuizzesByAuthor(final String author);

    boolean addQuestionToQuiz(String quizName, int questionId);

    boolean deleteQuestionFromQuiz(String quizName, int questionId);

    boolean deleteQuiz(String quizName);

    List<Integer> getAllQuestionIdsFromQuiz(String quizName);

    void answerQuestion(String quizName, String userName, int questionId, String answer);

    boolean unAnswerQuestion(String quizName, String userName, int questionId);

    boolean gradeAnsweredQuestion(String quizName, String userName, int questionId, int grade);

    String getQuestionAnswer(String quizName, String userName, int questionId);

    List<QuestionBasic> getAllQuestionFromQuiz(List<Integer> questionId);

    Integer getQuizScore(String quizName, String userName);
}
