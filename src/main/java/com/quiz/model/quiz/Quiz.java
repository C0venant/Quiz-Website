package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    List<QuestionBasic> questions;

    public Quiz(List<QuestionBasic> questions){
        this.questions = questions;
    }

    public Quiz(){
        questions = new ArrayList<>();
    }

    public int getOverallGrade(){
        int sum = 0;
        for(QuestionBasic q : questions){
            sum+=q.getMaxGrade();
        }
        return sum;
    }

    public void addQuestion(QuestionBasic question){
        questions.add(question);
    }

    public List<QuestionBasic> getQuestions(){
        return questions;
    }

}
