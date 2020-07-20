package com.quiz.model.quiz.question;

import com.quiz.model.quiz.question.utils.QuestionType;

import java.util.List;

public class QuestionTest extends QuestionBasic {
    private final List<String> answers;


    public QuestionTest(String body, int maxGrade, String imageFile, String correctAnswer, int id, List<String> answers){
        super(body, maxGrade, imageFile, correctAnswer, id);
        this.answers = answers;
        super.setType(QuestionType.TEST);
    }

    public QuestionTest(String body, List<String> answers, String correctAnswer){
        super(body);
        this.answers = answers;
        setCorrectAnswer(correctAnswer);
        super.setType(QuestionType.TEST);
    }

    public boolean checkAnswer(String userAnswer){
        return userAnswer.equals(getCorrectAnswer());
    }

    public List<String> getAnswers() { return answers;}


    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder(super.getBody()+"\n");
        for(int i = 0 ; i < answers.size(); i++){
            ret.append(i).append(") ").append(answers.get(i)).append("\n");
        }
        return ret.toString();
    }

}
