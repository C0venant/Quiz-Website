package com.quiz.model.quiz.question;

import com.quiz.model.quiz.question.utils.QuestionType;

public class QuestionBasic {
    private String body;
    private int maxGrade;
    private String imageFile;
    private String type = QuestionType.BASIC;
    private String correctAnswer;

    private int id;

    public QuestionBasic(String body, int maxGrade, String imageFile, String correctAnswer, int id) {
        this.body = body;
        this.maxGrade = maxGrade;
        this.imageFile = imageFile;
        this.correctAnswer = correctAnswer;
        this.id = id;
        type = QuestionType.BASIC;
    }

    public QuestionBasic(String body){
        this.body = body;
        maxGrade = 0;
        id = -1;
    }

    public String getCorrectAnswer() {return correctAnswer;}

    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}

    public int getId() {return id;}

    public void setId(int id) { this.id = id;}

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public void setType(String type) { this.type = type; }

    public String getType(){
        return type;
    }

    @Override
    public String toString(){
        return body;
    }
}
