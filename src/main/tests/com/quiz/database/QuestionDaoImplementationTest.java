package com.quiz.database;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionDaoImplementationTest {

    private QuestionDaoImplementation questionDao;
    private UserDaoImplementation userDao;
    private User user;

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
        questionDao = new QuestionDaoImplementation(dataSource);
        userDao = new UserDaoImplementation(dataSource);
        user = new User("Giorgi", "gela", "gio", "gelashvili");
        userDao.registerUser(user);
    }

    @Test
    public void testInit(){
        assertNotNull(questionDao);
    }

    @Test
    public void testInsertQuestion1() {
        QuestionBasic q = new QuestionBasic("this is question");
        q.setMaxGrade(10);
        q.setImageFile("image.jpg");
        assertTrue(questionDao.addQuestion(user.getLoginName(), q));
        assertTrue(questionDao.deleteQuestion(1));
    }

    @Test
    public void testInsertQuestion2() {
        QuestionBasic q1 = new QuestionBasic("this is question");
        QuestionBasic q2 = new QuestionBasic("this is question2");
        QuestionBasic q3 = new QuestionBasic("this is question3");
        assertTrue(questionDao.addQuestion(user.getLoginName(), q1));
        assertTrue(questionDao.addQuestion(user.getLoginName(), q2));
        assertTrue(questionDao.addQuestion(user.getLoginName(), q3));
        List<QuestionBasic> list =  questionDao.getAuthorQuestions(user.getLoginName());
        assertEquals(3,list.size());
        for(QuestionBasic q : list){
            assertTrue(questionDao.deleteQuestion(q.getId()));
        }
    }

    @Test
    public void testInsertQuestion3(){
        QuestionFillBlank q1 = new QuestionFillBlank("this # question2", "is");
        QuestionTest q2 = new QuestionTest("this is test", Arrays.asList("option1","option2","option3"),"option2");
        assertTrue(questionDao.addQuestion(user.getLoginName(), q1));
        assertTrue(questionDao.addQuestion(user.getLoginName(), q2));
        List<QuestionBasic> list =  questionDao.getAuthorQuestions(user.getLoginName());
        assertEquals(2,list.size());
        for(QuestionBasic q : list){
            assertTrue(questionDao.deleteQuestion(q.getId()));
        }
    }

    @Test
    public void testInsertQuestion4(){
        QuestionBasic q1 = new QuestionBasic ("");
        assertFalse(questionDao.addQuestion(user.getLoginName(), q1));
    }

    @Test
    public void testInsertQuestion5(){
        QuestionBasic q1 = new QuestionBasic ("");
        q1.setImageFile("image");
        assertTrue(questionDao.addQuestion(user.getLoginName(), q1));
        questionDao.deleteQuestion(questionDao.getAuthorQuestions(user.getLoginName()).get(0).getId());
    }

    @Test
    public void testBadCase(){
        assertFalse(questionDao.deleteQuestion(-1));
        assertNull(questionDao.getQuestion(-1));

    }

    @Test
    public void testGetQuestionBasic(){
        QuestionBasic original = new QuestionBasic("body", 5, "image", "answer", -1);
        questionDao.addQuestion(user.getLoginName(), original);
        QuestionBasic returned = questionDao.getAuthorQuestions(user.getLoginName()).get(0);
        assertEquals(original.getBody(), returned.getBody());
        assertEquals(original.getMaxGrade(), returned.getMaxGrade());
        assertEquals(original.getImageFile(), returned.getImageFile());
        assertEquals(original.getCorrectAnswer(), returned.getCorrectAnswer());
        assertEquals(original.getType(), returned.getType());
        questionDao.deleteQuestion(returned.getId());
    }

    @Test
    public void testGetQuestionFillBlank(){
        QuestionFillBlank original = new QuestionFillBlank("body # blank", 5, "image", "is", -1);
        questionDao.addQuestion(user.getLoginName(), original);
        QuestionFillBlank returned = (QuestionFillBlank)questionDao.getAuthorQuestions(user.getLoginName()).get(0);
        assertEquals(original.getBody(), returned.getBody());
        assertEquals(original.getMaxGrade(), returned.getMaxGrade());
        assertEquals(original.getImageFile(), returned.getImageFile());
        assertEquals(original.getCorrectAnswer(), returned.getCorrectAnswer());
        questionDao.deleteQuestion(returned.getId());
    }

    @Test
    public void testGetQuestionTest(){
        QuestionTest original = new QuestionTest("body # blank", 5, "image", "option2", -1,
                Arrays.asList("option1","option2","option3"));
        questionDao.addQuestion(user.getLoginName(), original);
        QuestionTest returned = (QuestionTest) questionDao.getAuthorQuestions(user.getLoginName()).get(0);
        assertEquals(original.getBody(), returned.getBody());
        assertEquals(original.getMaxGrade(), returned.getMaxGrade());
        assertEquals(original.getImageFile(), returned.getImageFile());
        assertEquals(original.getCorrectAnswer(), returned.getCorrectAnswer());
        assertEquals(original.getAnswers(), returned.getAnswers());
        questionDao.deleteQuestion(returned.getId());
    }

    @After
    public void finish(){
        userDao.deleteUser(user.getLoginName());
    }
}