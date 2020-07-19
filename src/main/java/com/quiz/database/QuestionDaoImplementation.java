package com.quiz.database;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;

import com.quiz.model.quiz.question.utils.QuestionType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDaoImplementation implements QuestionDao {

    JdbcTemplate jdbcTemplate;

    public QuestionDaoImplementation(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    private int generateId(){
        String getStr = "SELECT count(*) FROM questions" ;
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

    private void addProbableAnswers(QuestionTest question){
        int questionId = question.getId();
        for(String probAnswer : question.getAnswers()){
            String regStr = "INSERT INTO questions (questionId, probAnswer)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(regStr, questionId, probAnswer);
        }
    }

    @Override
    public boolean addQuestion(String author, QuestionBasic question) {
        int questionId = generateId();
        String questionType = question.getType();
        String body = question.getBody();
        int maxGrade = question.getMaxGrade();
        String imageFile = question.getImageFile();

        if(getQuestion(questionId) != null){
            return false;
        } else {
            String regStr = "INSERT INTO questions (questionId, questionType, body, maxGrade, imageFile, correctAnswer, author)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(regStr, questionId, questionType, body, maxGrade, imageFile, null, author);
            if(question.getType().equals(QuestionType.TEST)){
                question.setId(questionId);
                addProbableAnswers((QuestionTest)question);
            }
            return true;
        }
    }

    @Override
    public QuestionBasic getQuestion(int questionId) {
        String getStr = "SELECT * FROM questions WHERE questionId = " + questionId;
        return jdbcTemplate.query(getStr, new ResultSetExtractor<QuestionBasic>() {
            @Override
            public QuestionBasic extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    //Todo: typeCheck
                    int questionId = resultSet.getInt(1);
                    String questionType = resultSet.getString(2);
                    String body = resultSet.getString(3);
                    int maxGrade = resultSet.getInt(4);
                    String imageFile = resultSet.getString(5);
                    String correctAnswer = resultSet.getString(6);
                }
                return null;
            }
        });
    }

    @Override
    public List<QuestionBasic> getAuthorQuestions(String author) {
        String getStr = "SELECT * FROM questions WHERE author = " + "'" + author + "'";
        List<QuestionBasic> list = jdbcTemplate.query(getStr, new RowMapper<QuestionBasic>() {
            @Override
            public QuestionBasic mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return  getQuestion(resultSet.getInt(1));
            }
        });
        return list;
    }
}
