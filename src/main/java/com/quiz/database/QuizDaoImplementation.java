package com.quiz.database;

import com.quiz.database.interfaces.QuizDao;
import com.quiz.model.quiz.Quiz;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuizDaoImplementation implements QuizDao {
    private final JdbcTemplate jdbcTemplate;

    public QuizDaoImplementation(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        String quizName = quiz.getQuizName();
        String author = quiz.getQuizAuthor();
        if(getQuizByName(quizName) != null){
            return false;
        } else {
            String addStr = "INSERT INTO quiz (quizName, author)"
                    + " VALUES(?, ?)";
            jdbcTemplate.update(addStr, quizName, author);
            return true;
        }
    }

    @Override
    public Quiz getQuizByName(final String quizName) {
        String getStr = "SELECT * FROM quiz WHERE quizName = " + "'" + quizName + "'";
        return jdbcTemplate.query(getStr, new ResultSetExtractor<Quiz>() {

            @Override
            public Quiz extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String author = resultSet.getString(2);
                    return new Quiz(quizName, author);
                }
                return null;
            }
        });
    }

    @Override
    public List<Quiz> getQuizzesByAuthor(final String author) {
        String getStr = "SELECT * FROM quiz WHERE author = " + "'" + author + "'";
        return jdbcTemplate.query(getStr, new RowMapper<Quiz>(){

            @Override
            public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
                String quizName = rs.getString("quizName");
                return new Quiz(quizName, author);
            }
        });
    }

    @Override
    public boolean addQuestionToQuiz(String quizName, int questionId) {
        if(getQuizByName(quizName) == null){
            return false;
        } else {
            String addStr = "INSERT INTO quizQuestions VALUES(?, ?)";
            jdbcTemplate.update(addStr, quizName, questionId);
            return true;
        }
    }

    @Override
    public boolean deleteQuestionFromQuiz(String quizName, int questionId) {
        if(getQuizByName(quizName) == null){
            return false;
        } else {
            String delStr = "DELETE FROM quizQuestions WHERE quizName =? AND questionId =?";
            jdbcTemplate.update(delStr, quizName, questionId);
            return true;
        }
    }

    @Override
    public boolean deleteQuiz(String quizName) {
        if(getQuizByName(quizName) == null){
            return false;
        } else {
            String delStr = "DELETE FROM quiz WHERE quizName =?";
            jdbcTemplate.update(delStr, quizName);
            return true;
        }
    }

    @Override
    public List<Integer> getAllQuestionIdsFromQuiz(String quizName) {
        if(getQuizByName(quizName) == null){
            return null;
        } else {
            String getStr = "SELECT * FROM quizQuestions WHERE quizName = " + "'" + quizName + "'";
            return jdbcTemplate.query(getStr, new RowMapper<Integer>(){

                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("questionId");
                }
            });
        }
    }
}
