package com.quiz.model.quiz;

import com.quiz.model.quiz.question.BasicQuestion;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    List<BasicQuestion> questions;

    public Quiz(List<BasicQuestion> questions){
        this.questions = questions;
    }

    public Quiz(){
        questions = new ArrayList<>();
    }

    public int getOverallGrade(){
        int sum = 0;
        for(BasicQuestion q : questions){
            sum+=q.getMaxGrade();
        }
        return sum;
    }

    public void addQuestion(BasicQuestion question){
        questions.add(question);
    }

    public List<BasicQuestion> getQuestions(){
        return questions;
    }

}
