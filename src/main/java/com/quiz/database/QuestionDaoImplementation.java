package com.quiz.database;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class QuestionDaoImplementation implements QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public QuestionDaoImplementation(DataSource dataSource){jdbcTemplate = new JdbcTemplate(dataSource);}

    private int generateId(){
        String getStr = "SELECT max(questionId) FROM questions" ;
        Integer id = jdbcTemplate.query(getStr, resultSet -> {
            resultSet.next();
            return resultSet.getInt(1);
        });
        return id +1;
    }

    private void addProbableAnswers(QuestionTest question){
        int questionId = question.getId();
        for(String probAnswer : question.getAnswers()){
            String regStr = "INSERT INTO probableAnswers (questionId, probAnswer)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(regStr, questionId, probAnswer);
        }
    }

    private void removeProbableAnswers(QuestionTest question){
        int questionId = question.getId();
        String delete = "DELETE FROM probableAnswers WHERE questionId =?";
        jdbcTemplate.update(delete, questionId);
    }

    private List<String> getProbableAnswers(int questionId){
        String getStr = "SELECT * FROM probableAnswers WHERE questionId = " + questionId;
        return jdbcTemplate.query(getStr, (resultSet, rowNum) -> resultSet.getString(2));
    }

    @Override
    public boolean addQuestion(String author, QuestionBasic question) {
        int questionId = generateId();
        String questionType = question.getType();
        String body = question.getBody();
        int maxGrade = question.getMaxGrade();
        String imageFile = question.getImageFile();
        String correctAnswer = question.getCorrectAnswer();
        if(body.equals("")&&imageFile == null){
            return false;
        } else {
            String regStr = "INSERT INTO questions (questionId, questionType, body, maxGrade, imageFile, correctAnswer, author)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(regStr, questionId, questionType, body, maxGrade, imageFile, correctAnswer, author);
            if(question.getType().equals(QuestionType.TEST)){
                question.setId(questionId);
                addProbableAnswers((QuestionTest)question);
            }
            return true;
        }
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        QuestionBasic question = getQuestion(questionId);
        if(question == null){
            return false;
        } else {
            if(question.getType().equals(QuestionType.TEST)){
                removeProbableAnswers((QuestionTest) question);
            }
            String delete = "DELETE FROM questions WHERE questionId =?";
            jdbcTemplate.update(delete, questionId);
            return  true;
        }
    }

    @Override
    public QuestionBasic getQuestion(int questionId) {
        String getStr = "SELECT * FROM questions WHERE questionId = " + questionId;
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                int questionId1 = resultSet.getInt(1);
                String questionType = resultSet.getString(2);
                String body = resultSet.getString(3);
                int maxGrade = resultSet.getInt(4);
                String imageFile = resultSet.getString(5);
                String correctAnswer = resultSet.getString(6);

                if(questionType.equals(QuestionType.TEST)){
                    return new QuestionTest(body, maxGrade, imageFile, correctAnswer, questionId1, getProbableAnswers(questionId1));
                }else if(questionType.equals(QuestionType.BLANK)){
                    return new QuestionFillBlank(body, maxGrade, imageFile, correctAnswer, questionId1);
                }else{
                    return new QuestionBasic(body, maxGrade, imageFile, correctAnswer, questionId1);
                }
            }
            return null;
        });
    }

    @Override
    public List<QuestionBasic> getAuthorQuestions(String author) {
        String getStr = "SELECT * FROM questions WHERE author = " + "'" + author + "'";
        return jdbcTemplate.query(getStr, (resultSet, rowNum) -> getQuestion(resultSet.getInt(1)));
    }
}
