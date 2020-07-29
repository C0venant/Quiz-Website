package com.quiz.database.interfaces;

import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.user.UserCheck;

import java.util.List;
import java.util.Map;

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

    void addQuizForCheck(String quizName, String userName, String author);

    void deleteQuizForCheck(String quizName, String userName);

    List<UserCheck> needsCheck(String author);

    List<String> checkedQuizUser(String username);

    void checkQuiz(String quizName, String username);

    void uncheckQuiz(String quizName, String username);
}
