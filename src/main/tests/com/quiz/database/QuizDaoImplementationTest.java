package com.quiz.database;

import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static org.junit.Assert.*;

public class QuizDaoImplementationTest {
    private QuizDaoImplementation quizDao;
    private UserDaoImplementation userDao;
    private QuestionDaoImplementation questionDao;
    private Quiz quizOne;
    private Quiz quizTwo;
    private User userOne;
    private User userTwo;

    /** replace with yours*/
    private static final String DATABASE_NAME = "quiz_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        quizDao = new QuizDaoImplementation(dataSource);
        userDao = new UserDaoImplementation(dataSource);
        questionDao = new QuestionDaoImplementation(dataSource);
        userOne = new User("giogela", "rame", "gio", "gelashvili");
        userTwo = new User("ikakandelaki", "rame", "ika", "kandelaki");
        QuestionBasic q1 = new QuestionBasic("question1");
        QuestionBasic q2 = new QuestionBasic("question2");
        quizOne = new Quiz("exQuizOne", userOne.getLoginName());
        quizTwo = new Quiz("exQuizTwo", userTwo.getLoginName());

        userDao.registerUser(userOne);
        userDao.registerUser(userTwo);
        questionDao.addQuestion(userOne.getLoginName(), q1);
        questionDao.addQuestion(userTwo.getLoginName(), q1);
        questionDao.addQuestion(userOne.getLoginName(), q2);
        questionDao.addQuestion(userTwo.getLoginName(), q2);
    }

    @Test
    public void testConstructor(){
        assertNotNull(quizDao);
    }

    @Test
    public void testAddQuiz(){
        assertTrue(quizDao.addQuiz(quizOne));
        assertFalse(quizDao.addQuiz(quizOne));
        assertTrue(quizDao.addQuiz(quizTwo));
        assertFalse(quizDao.addQuiz(quizTwo));
    }

    @Test
    public void testGetQuizByName(){
        quizDao.addQuiz(quizOne);
        assertEquals(quizOne, quizDao.getQuizByName(quizOne.getQuizName()));
        assertNull(quizDao.getQuizByName(quizTwo.getQuizName()));
    }

    @Test
    public void testGetQuizByAuthor(){
        quizDao.addQuiz(quizOne);
        quizDao.addQuiz(quizTwo);
        assertEquals(Collections.singletonList(quizOne), quizDao.getQuizzesByAuthor(quizOne.getQuizAuthor()));
    }

    @Test
    public void testAddQuestionToQuiz(){
        quizDao.addQuiz(quizOne);
        assertTrue(quizDao.addQuestionToQuiz(quizOne.getQuizName(), 1));
        quizDao.deleteQuestionFromQuiz(quizOne.getQuizName(), 1);
        assertFalse(quizDao.deleteQuestionFromQuiz("sd", 1));
        assertFalse(quizDao.addQuestionToQuiz("sd", 1));
    }

    @Test
    public void testGetAllQuestionIdsFromQuiz(){
        quizDao.addQuiz(quizOne);
        quizDao.addQuestionToQuiz(quizOne.getQuizName(), 1);
        assertEquals(1, quizDao.getAllQuestionIdsFromQuiz(quizOne.getQuizName()).size());
        assertNull(quizDao.getAllQuestionIdsFromQuiz("sd"));
        quizDao.deleteQuestionFromQuiz(quizOne.getQuizName(), 1);
    }

    @Test
    public void testQuizAnswers(){
        testAnswerQuestionAndGetQuestionAnswer();
        testGradeAnsweredQuestion();
        testUnAnswerQuestion();
    }

    private void testAnswerQuestionAndGetQuestionAnswer() {
        assertNull(quizDao.getQuestionAnswer(quizOne.getQuizName(), userOne.getLoginName(), 1));
        quizDao.answerQuestion(quizOne.getQuizName(), userOne.getLoginName(), 1, "answer1");
        assertNotNull(quizDao.getQuestionAnswer(quizOne.getQuizName(), userOne.getLoginName(), 1));
       quizDao.answerQuestion(quizOne.getQuizName(), userOne.getLoginName(), 1, "answer2");
    }

    private void testGradeAnsweredQuestion() {
        assertFalse(quizDao.gradeAnsweredQuestion(quizTwo.getQuizName(), userTwo.getLoginName(), 1, 10));
        assertTrue(quizDao.gradeAnsweredQuestion(quizOne.getQuizName(), userOne.getLoginName(), 1, 10));
    }

    private void testUnAnswerQuestion() {
        assertTrue(quizDao.unAnswerQuestion(quizOne.getQuizName(), userOne.getLoginName(), 1));
        assertFalse(quizDao.unAnswerQuestion(quizOne.getQuizName(), userOne.getLoginName(), 1));
    }


    @After
    public void finish(){
        quizDao.deleteQuiz(quizOne.getQuizName());
        quizDao.deleteQuiz(quizTwo.getQuizName());
        for(QuestionBasic question : questionDao.getAuthorQuestions(userOne.getLoginName())){
            questionDao.deleteQuestion(question.getId());
        }
        for(QuestionBasic question : questionDao.getAuthorQuestions(userTwo.getLoginName())){
            questionDao.deleteQuestion(question.getId());
        }
        userDao.deleteUser(userOne.getLoginName());
        userDao.deleteUser(userTwo.getLoginName());
    }
}