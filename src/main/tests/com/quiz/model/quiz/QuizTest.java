package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuizTest {

    Quiz quizOne;
    Quiz quizTwo;

    @Before
    public void setup(){
        QuestionBasic q1 = new QuestionBasic("question1");
        QuestionTest q2 = new QuestionTest("question2", Arrays.asList("a","b","c"), "b");
        QuestionFillBlank q3 = new QuestionFillBlank("question3 # is this", "answer");
        q1.setMaxGrade(10);
        q2.setMaxGrade(20);
        q3.setMaxGrade(30);
        quizOne = new Quiz("exampleQuizOne", "exampleAuthorOne", Arrays.asList(q1,q2,q3));
        quizTwo = new Quiz("exampleQuizTwo", "exampleAuthorTwo");
    }

    @Test
    public void test(){
        assertEquals(3, quizOne.getQuestions().size());
        for(QuestionBasic q : quizOne.getQuestions()){
            if(q.getType().equals(QuestionType.TEST)){
                QuestionTest qt = (QuestionTest)q;
                assertEquals(20, qt.getMaxGrade());
            }else if(q.getType().equals(QuestionType.BLANK)){
                QuestionFillBlank qb = (QuestionFillBlank)q;
                assertEquals(2, qb.splitOnDelimiter().size());
            }else if(q.getType().equals(QuestionType.BASIC)){
                assertEquals("question1", q.getBody());
            }
        }
    }
}