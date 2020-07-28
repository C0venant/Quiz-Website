package com.quiz.model.quiz.question;

import com.quiz.model.quiz.question.utils.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QuestionFillBlank extends QuestionBasic {

    private String blankDelimiter = "#";

    public QuestionFillBlank(String body, int maxGrade, String imageFile, String correctAnswer, int id){
        super(body, maxGrade, imageFile, correctAnswer, id);
        super.setType(QuestionType.BLANK);
    }

    public QuestionFillBlank(String body, String correctAnswer){
        super(body);
        setCorrectAnswer(correctAnswer);
        super.setType(QuestionType.BLANK);
    }

    public String getBlankDelimiter() {
        return blankDelimiter;
    }

    public void setBlankDelimiter(String blankDelimiter) {
        this.blankDelimiter = blankDelimiter;
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
        return userAnswer.toLowerCase().equals(getCorrectAnswer());
    }

    @Override
    public String toString(){
        List<String> list = splitOnDelimiter();
        if(list.size() == 1){
            return list.get(0)+"____";
        }
        return list.get(0)+"____"+list.get(1);
    }

}
