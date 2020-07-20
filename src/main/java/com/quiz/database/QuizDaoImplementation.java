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
        return false;
    }

    @Override
    public Quiz getQuizByName(String quizName) {
        return null;
    }

    @Override
    public List<Quiz> getQuizzesByAuthor(String author) {
        return null;
    }


    /*private int generateId(){
        String getStr = "SELECT count(*) FROM quiz";
        Integer id = jdbcTemplate.query(getStr, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
                return 1;
            }
        });
        if(id == null){
            return 1;
        }
        return id +1;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        int quizId = generateId();
        String author = quiz.getQuizAuthor();
        if(getQuizById(quizId) != null) {
            return false;
        } else {
            String addStr = "INSERT INTO quiz (quizId, author)"
                    + " VALUES(?, ?)";
            jdbcTemplate.update(addStr, quizId, author);
            return true;
        }
    }

    @Override
    public Quiz getQuizById(final int quizId){
        String getStr = "SELECT * FROM quiz WHERE quizId = " + "'" + quizId + "'";
        return jdbcTemplate.query(getStr, new ResultSetExtractor<Quiz>() {

            @Override
            public Quiz extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String author = resultSet.getString(2);
                    return new Quiz(quizId, author);
                }
                return null;
            }
        });
    }

    @Override
    public List<Quiz> getQuizzesByAuthor(final String author){
        String getStr = "SELECT * FROM quiz WHERE author = " + "'" + author + "'";
        return jdbcTemplate.query(getStr, new RowMapper<Quiz>(){

            @Override
            public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
                int quizId = rs.getInt("quizId");
                return new Quiz(quizId, author);
            }
        });
    }*/

}
