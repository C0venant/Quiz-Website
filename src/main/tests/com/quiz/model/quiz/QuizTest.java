package com.quiz.model.quiz;

import com.quiz.model.quiz.question.BasicQuestion;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.spel.ast.QualifiedIdentifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuizTest {

    Quiz quiz;

    @Before
    public void setup(){
        BasicQuestion q1 = new BasicQuestion("question1");
        QuestionTest q2 = new QuestionTest("question2", Arrays.asList("a","b","c"), "b");
        QuestionFillBlank q3 = new QuestionFillBlank("question3 # is this", "answer");
        q1.setMaxGrade(10);
        q2.setMaxGrade(20);
        q3.setMaxGrade(30);
        quiz = new Quiz(Arrays.asList(q1,q2,q3));
    }

    @Test
    public void test(){
        assertEquals(3, quiz.getQuestions().size());
        for(BasicQuestion q : quiz.getQuestions()){
            if(q.getType().equals("test")){
                QuestionTest qt = (QuestionTest)q;
                assertEquals(20, qt.getMaxGrade());
            }else if(q.getType().equals("blank")){
                QuestionFillBlank qb = (QuestionFillBlank)q;
                assertEquals(2, qb.splitOnDelimiter().size());
            }else if(q.getType().equals("basic")){
                assertEquals("question1", q.getBody());
            }
        }
    }
}