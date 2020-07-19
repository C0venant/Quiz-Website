package com.quiz.model.quiz.question;

import com.quiz.model.quiz.question.BasicQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QuestionFillBlank extends BasicQuestion {

    private String blankDelimiter;
    private String correctAnswer;


    public QuestionFillBlank(String body, String correctAnswer){
        super(body);
        this.correctAnswer=correctAnswer;
        super.setType("blank");
        blankDelimiter = "#";
    }

    public QuestionFillBlank(String body, String correctAnswer, String blankDelimiter){
        super(body);
        this.blankDelimiter = blankDelimiter;
        this.correctAnswer= correctAnswer;
        super.setType("blank");
    }

    public String getBlankDelimiter() {
        return blankDelimiter;
    }

    public void setBlankDelimiter(String blankDelimiter) {
        this.blankDelimiter = blankDelimiter;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer =  correctAnswer.toLowerCase();
    }

    public List<String> splitOnDelimiter(){
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(this.getBody(), blankDelimiter);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

    public boolean checkAnswer(String userAnswer){
        return userAnswer.toLowerCase().equals(correctAnswer);
    }

    @Override
    public String getType(){
        return super.getType();
    }

    @Override
    public String toString(){
        List<String> list = splitOnDelimiter();
        String ret = "";
        return list.get(0)+"____"+list.get(1);
    }

}