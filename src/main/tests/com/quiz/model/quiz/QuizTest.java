package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class QuizTest {
    private Quiz quizOne;
    private Quiz quizTwo;
    private QuestionBasic q1;
    private QuestionTest q2;
    private QuestionFillBlank q3;

    @Before
    public void setup(){
        q1 = new QuestionBasic("question1");
        q2 = new QuestionTest("question2", Arrays.asList("a","b","c"), "b");
        q3 = new QuestionFillBlank("question3 # is this", "answer");
        q1.setMaxGrade(10);
        q2.setMaxGrade(20);
        q3.setMaxGrade(30);
        quizOne = new Quiz("exampleQuizOne", "exampleAuthorOne", Arrays.asList(q1,q2,q3));
        quizTwo = new Quiz("exampleQuizTwo", "exampleAuthorTwo");
    }

    @Test
    public void testFirstConstructor(){
        assertNotNull(quizOne);
    }

    @Test
    public void testSecondConstructor(){
        assertNotNull(quizTwo);
    }

    @Test
    public void testGetQuizName(){
        assertEquals("exampleQuizOne", quizOne.getQuizName());
        assertEquals("exampleQuizTwo", quizTwo.getQuizName());
    }

    @Test
    public void testGetQuizAuthor(){
        assertEquals("exampleAuthorOne", quizOne.getQuizAuthor());
        assertEquals("exampleAuthorTwo", quizTwo.getQuizAuthor());
    }

    @Test
    public void testGetOverallGrade(){
        assertEquals(60, quizOne.getOverallGrade());
        assertEquals(0, quizTwo.getOverallGrade());
    }

    @Test
    public void testGetQuestions(){
        assertEquals(quizOne.getQuestions(), Arrays.asList(q1, q2, q3));
        assertTrue(quizTwo.getQuestions().isEmpty());
    }

    @Test
    public void testAddQuestions(){
        quizTwo.addQuestion(q2);
        assertEquals(quizTwo.getQuestions(), Collections.singletonList(q2));
    }

    @Test
    public void testEquals(){
        Quiz q1 = new Quiz("sds", "df");
        Quiz q2 = new Quiz("sds", "sdsd");
        String q3 = "";
        assertEquals(q1, q2);
        assertNotEquals(q1, q3);
    }
}