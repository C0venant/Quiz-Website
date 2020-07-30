package com.quiz.database;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.user.UserCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class QuizDaoImplementation implements QuizDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    QuestionDao questionDao;

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
            for(QuestionBasic q : quiz.getQuestions()){
                addQuestionToQuiz(quizName, q.getId());
            }
            return true;
        }
    }


    @Override
    public Quiz getQuizByName(String quizName) {
        String getStr = "SELECT * FROM quiz WHERE quizName = " + "'" + quizName + "'";
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                String author = resultSet.getString(2);
                return new Quiz(quizName, author, getAllQuestionFromQuiz(getAllQuestionIdsFromQuiz(quizName)));
            }
            return null;
        });
    }

    @Override
    public Quiz isPresent(String quizName) {
        String getStr = "SELECT * FROM quiz WHERE quizName = " + "'" + quizName + "'";
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                String author = resultSet.getString(2);
                return new Quiz(quizName, author);
            }
            return null;
        });
    }

    @Override
    public List<Quiz> getQuizzesByAuthor(final String author) {
        String getStr = "SELECT * FROM quiz WHERE author = " + "'" + author + "'";
        return jdbcTemplate.query(getStr, (rs, rowNum) -> {
            String quizName = rs.getString("quizName");
            return new Quiz(quizName, author, getAllQuestionFromQuiz(getAllQuestionIdsFromQuiz(quizName)));
        });
    }

    @Override
    public boolean addQuestionToQuiz(String quizName, int questionId) {
        if(isPresent(quizName) == null){
            return false;
        } else {
            String addStr = "INSERT INTO quizQuestions VALUES(?, ?)";
            jdbcTemplate.update(addStr, quizName, questionId);
            return true;
        }
    }

    @Override
    public boolean deleteQuestionFromQuiz(String quizName, int questionId) {
        if(isPresent(quizName) == null){
            return false;
        } else {
            String delStr = "DELETE FROM quizQuestions WHERE quizName =? AND questionId =?";
            jdbcTemplate.update(delStr, quizName, questionId);
            return true;
        }
    }

    @Override
    public boolean deleteQuiz(String quizName) {
        if(isPresent(quizName) == null){
            return false;
        } else {

            List<QuestionBasic> list = getAllQuestionFromQuiz(getAllQuestionIdsFromQuiz(quizName));
            for(QuestionBasic q : list){
                deleteQuestionFromQuiz(quizName, q.getId());
            }
            String delStr = "DELETE FROM quiz WHERE quizName =?";
            jdbcTemplate.update(delStr, quizName);
            return true;
        }
    }

    @Override
    public List<Integer> getAllQuestionIdsFromQuiz(String quizName) {
        if(isPresent(quizName) == null){
            return null;
        } else {
            String getStr = "SELECT * FROM quizQuestions WHERE quizName = " + "'" + quizName + "'";
            return jdbcTemplate.query(getStr, (rs, rowNum) -> rs.getInt("questionId"));
        }
    }

    @Override
    public List<QuestionBasic> getAllQuestionFromQuiz(List<Integer> questionId) {
        List<QuestionBasic> questionList = new ArrayList<>();
        for(int id : questionId){
            questionList.add(questionDao.getQuestion(id));
        }
        return questionList;
    }

    @Override
    public Integer getQuizScore(String quizName, String userName) {
        String getStr = "SELECT sum(grade) FROM quizAnswers WHERE quizName = " + "'" + quizName + "'" + "And userName ="+ "'" +userName+ "'";
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        });
    }

    @Override
    public void answerQuestion(String quizName, String userName, int questionId, String answer) {
        if(getQuestionAnswer(quizName, userName, questionId) != null){
            unAnswerQuestion(quizName, userName, questionId);
        }
        String ansStr = "INSERT INTO quizAnswers(quizName, userName, questionId, answer)"
                    + " VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(ansStr, quizName, userName, questionId, answer);
    }

    @Override
    public boolean unAnswerQuestion(String quizName, String userName, int questionId){
        if(getQuestionAnswer(quizName, userName, questionId) == null){
            return false;
        } else {
            String unAnsStr = "DELETE FROM quizAnswers WHERE quizName =? AND userName =? AND questionId =?";
            jdbcTemplate.update(unAnsStr, quizName, userName, questionId);
            return true;
        }
    }

    @Override
    public boolean gradeAnsweredQuestion(String quizName, String userName, int questionId, int grade){
        if(getQuestionAnswer(quizName, userName, questionId) == null){
            return false;
        } else {
            String grdStr = "UPDATE quizAnswers SET grade =?"
                    + " WHERE quizName =? AND userName =? AND questionId =?";
            jdbcTemplate.update(grdStr, grade, quizName, userName, questionId);
            return true;
        }
    }

    @Override
    public String getQuestionAnswer(String quizName, String userName, int questionId) {
        String getStr = "SELECT answer FROM quizAnswers WHERE"
                + " quizName =" + "'" + quizName + "'"
                + " AND userName =" + "'" + userName + "'"
                + " AND questionId =" + questionId;
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                return resultSet.getString("answer");
            }
            return null;
        });
    }


    @Override
    public void addQuizForCheck(String quizName, String userName, String author) {
        String regStr = "INSERT INTO quizCheck (quizName, userName, author)"
                + " VALUES (?, ?, ?)";
        jdbcTemplate.update(regStr, quizName, userName, author);
    }

    @Override
    public void deleteQuizForCheck(String quizName, String userName) {
        String delete = "DELETE FROM quizCheck WHERE quizName ="+"'"+quizName+"'"+"AND userName="+"'"+userName+"'";
        jdbcTemplate.update(delete);
    }

    @Override
    public List<UserCheck> needsCheck(String author) {
        String query = "SELECT * FROM quizCheck WHERE isChecked=FALSE AND author="+"'"+author+"'";
        return jdbcTemplate.query(
                query, (rs, rowNum) ->
                        new UserCheck(
                                rs.getString("userName"),
                                rs.getString("quizName")
                        )
        );
    }

    @Override
    public List<String> checkedQuizUser(String username) {
        String query = "SELECT quizName FROM quizCheck WHERE isChecked=TRUE AND userName="+"'"+username+"'";
        return jdbcTemplate.query(
                query, (rs, rowNum) ->
                        rs.getString("quizName")
        );
    }

    @Override
    public void checkQuiz(String quizName, String username) {
        String update = "UPDATE quizCheck SET isChecked = " +
                "TRUE WHERE quizName =" +"'"+quizName+"'"+"AND username ="+"'"+username+"'";
        jdbcTemplate.update(update);
    }

    @Override
    public void uncheckQuiz(String quizName, String username) {
        String update = "UPDATE quizCheck SET isChecked = " +
                "FALSE WHERE quizName =" +"'"+quizName+"'"+"AND username ="+"'"+username+"'";
        jdbcTemplate.update(update);
    }

    @Override
    public List<String> getGlobalQuizzes(String username) {
        String query = "SELECT quizName FROM quiz WHERE author!="+"'"+username+"'";
        return jdbcTemplate.query(
                query, (rs, rowNum) ->
                        rs.getString("quizName")
        );
    }
}
