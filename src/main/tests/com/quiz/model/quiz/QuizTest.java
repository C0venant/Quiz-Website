package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionBasicFillBlank;
import com.quiz.model.quiz.question.QuestionBasicTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuizTest {

    Quiz quiz;

    @Before
    public void setup(){
        QuestionBasic q1 = new QuestionBasic("question1");
        QuestionBasicTest q2 = new QuestionBasicTest("question2", Arrays.asList("a","b","c"), "b");
        QuestionBasicFillBlank q3 = new QuestionBasicFillBlank("question3 # is this", "answer");
        q1.setMaxGrade(10);
        q2.setMaxGrade(20);
        q3.setMaxGrade(30);
        quiz = new Quiz(Arrays.asList(q1,q2,q3));
    }

    @Test
    public void test(){
        assertEquals(3, quiz.getQuestions().size());
        for(QuestionBasic q : quiz.getQuestions()){
            if(q.getType() == QuestionType.TEST){
                QuestionBasicTest qt = (QuestionBasicTest)q;
                assertEquals(20, qt.getMaxGrade());
            }else if(q.getType() == QuestionType.BLANK){
                QuestionBasicFillBlank qb = (QuestionBasicFillBlank)q;
                assertEquals(2, qb.splitOnDelimiter().size());
            }else if(q.getType() == QuestionType.BASIC){
                assertEquals("question1", q.getBody());
            }
        }
    }
}