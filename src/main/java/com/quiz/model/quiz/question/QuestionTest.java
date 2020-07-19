package com.quiz.model.quiz.question;

import com.quiz.model.quiz.question.utils.QuestionType;

import java.util.List;

public class QuestionTest extends QuestionBasic {
    private final List<String> answers;
    private String correctAnswer;

    public QuestionTest(String body, List<String> answers, String correctAnswer){
        super(body);
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        super.setType(QuestionType.TEST);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String userAnswer){
        return userAnswer.equals(correctAnswer);
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
