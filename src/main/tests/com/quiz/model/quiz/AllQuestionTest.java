package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionBasicFillBlank;
import com.quiz.model.quiz.question.QuestionBasicTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AllQuestionTest {

    @Test
    public void testBasic(){
        QuestionBasic q = new QuestionBasic("what?");
        assertEquals("what?", q.getBody());
        assertEquals(QuestionType.BASIC, q.getType());
        assertEquals(0, q.getMaxGrade());
        assertNull(q.getImageFile());
        q.setMaxGrade(10);
        q.setImageFile("image.jpg");
        assertEquals("image.jpg", q.getImageFile());
        assertEquals(10, q.getMaxGrade());
    }

    @Test
    public void testBlank(){
        QuestionBasicFillBlank q = new QuestionBasicFillBlank("capital city of georgia is #, the end","tbilisi");
        assertTrue(q.checkAnswer("TbiLisi"));
        assertEquals(2, q.splitOnDelimiter().size());
        assertEquals(QuestionType.BLANK, q.getType());
        System.out.println(q.toString());
    }

    @Test
    public void testTest(){
        List<String> list = new ArrayList<>();
        list.add("option1");
        list.add("option2");
        list.add("option3");
        QuestionBasicTest q = new QuestionBasicTest("choose from this answers: ", list, "option1");
        assertEquals(QuestionType.TEST, q.getType());
        System.out.println(q.toString());
    }
}