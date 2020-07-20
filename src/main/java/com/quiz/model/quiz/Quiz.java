package com.quiz.model.quiz;

import com.quiz.model.quiz.question.QuestionBasic;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final String quizName;
    private final String quizAuthor;
    private final List<QuestionBasic> questions;

    public Quiz(String quizName, String quizAuthor, List<QuestionBasic> questions){
        this.quizName = quizName;
        this.quizAuthor = quizAuthor;
        this.questions = questions;
    }

    public Quiz(String quizName, String quizAuthor){
        this.quizName = quizName;
        this.quizAuthor = quizAuthor;
        questions = new ArrayList<>();
    }

    public String getQuizName() {
        return quizName;
    }

    public String getQuizAuthor() {
        return quizAuthor;
    }

    public List<QuestionBasic> getQuestions(){
        return questions;
    }

    public void addQuestion(QuestionBasic question){
        questions.add(question);
    }

    public int getOverallGrade(){
        int sum = 0;
        for(QuestionBasic q : questions){
            sum+=q.getMaxGrade();
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != getClass()) return false;
        Quiz other = (Quiz)obj;
        return other.quizName.equals(getQuizName());
    }
}
