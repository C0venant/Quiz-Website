package com.quiz.database;

import com.quiz.database.interfaces.RemoveDao;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SuppressWarnings("SqlDialectInspection")
public class RemoveDaoImplementation implements RemoveDao {

    private final JdbcTemplate jdbcTemplate;

    public RemoveDaoImplementation(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void removeQuiz(String quizName){
        String clearQuizAnswers = "DELETE FROM quizAnswers WHERE quizName =?";
        jdbcTemplate.update(clearQuizAnswers, quizName);
        String clearQuizQuestions = "DELETE FROM quizQuestions WHERE quizName =?";
        jdbcTemplate.update(clearQuizQuestions, quizName);
        String clearQuizCheck = "DELETE FROM quizCheck WHERE quizName =?";
        jdbcTemplate.update(clearQuizCheck, quizName);
        String clearQuiz = "DELETE FROM quiz WHERE quizName =?";
        jdbcTemplate.update(clearQuiz, quizName);
    }

    @Override
    public void removeQuestion(int questionId){
        String clearQuizAnswers = "DELETE FROM quizAnswers WHERE questionId =?";
        jdbcTemplate.update(clearQuizAnswers, questionId);
        String clearQuizQuestions = "DELETE FROM quizQuestions WHERE questionId =?";
        jdbcTemplate.update(clearQuizQuestions, questionId);
        String clearProbableAnswers = "DELETE FROM probableAnswers WHERE questionId =?";
        jdbcTemplate.update(clearProbableAnswers, questionId);
        String clearQuestions = "DELETE FROM questions WHERE questionId =?";
        jdbcTemplate.update(clearQuestions, questionId);
    }

    @Override
    public void removeUser(String userName){
        String clearQuizCheck = "DELETE FROM quizCheck WHERE author =?";
        jdbcTemplate.update(clearQuizCheck, userName);
        String clearRequests = "DELETE FROM requests WHERE fromUser = ? OR toUser = ?";
        jdbcTemplate.update(clearRequests, userName, userName);
        String clearFriends = "DELETE FROM friends WHERE user1 = ? OR user2 = ?";
        jdbcTemplate.update(clearFriends, userName, userName);
        String clearQuizAnswers = "DELETE FROM quizAnswers WHERE userName =?";
        jdbcTemplate.update(clearQuizAnswers, userName);
        String clearQuestions = "DELETE FROM questions WHERE author =?";
        jdbcTemplate.update(clearQuestions, userName);
        String clearQuiz = "DELETE FROM quiz WHERE author =?";
        jdbcTemplate.update(clearQuiz, userName);
        String clearUsers = "DELETE from users WHERE loginName =?";
        jdbcTemplate.update(clearUsers, userName);
    }

}
