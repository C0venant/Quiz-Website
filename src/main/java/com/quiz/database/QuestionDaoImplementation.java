package com.quiz.database;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionBasicFillBlank;
import com.quiz.model.quiz.question.QuestionBasicTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class QuestionDaoImplementation implements QuestionDao {

    JdbcTemplate jdbcTemplate;

    public QuestionDaoImplementation(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    private int generateId(){
        return 0;
    }

    @Override
    public boolean addQuestion(String author, QuestionBasic question) {
        int questionId = generateId();
        String questionType = "basic";
        String body = question.getBody();
        int maxGrade = question.getMaxGrade();
        String imageFile = question.getImageFile();

        if(getQuestion(questionId) != null){
            return false;
        } else {
            String regStr = "INSERT INTO questions (questionId, questionType, body, maxGrade, imageFile, correctAnswer, author)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(regStr, questionId, questionType, body, maxGrade, imageFile, null, author);
            return true;
        }
    }

    @Override
    public boolean addQuestion(String author, QuestionBasicFillBlank question) {
        return false;
    }

    @Override
    public boolean addQuestion(String author, QuestionBasicTest question) {
        return false;
    }

    @Override
    public QuestionBasic getQuestion(int questionId) {
        return null;
    }

    @Override
    public List<QuestionBasic> getAuthorQuestions(String author) {
        return null;
    }
}
