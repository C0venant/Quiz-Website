package com.quiz.model.quiz.question;

public class BasicQuestion {
    private String body;
    private int maxGrade;
    private String imageFile;

    private String type = "basic";

    public BasicQuestion(String body){
        this.body = body;
        maxGrade = 0;
    }

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
