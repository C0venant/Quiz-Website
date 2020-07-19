package com.quiz.model.quiz.question;

public class BasicQuestion {
    private String body;
    private int maxGrade;
    private String imageFile;

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

    public String getType(){
        return "basic";
    }
}
