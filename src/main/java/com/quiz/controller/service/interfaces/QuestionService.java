package com.quiz.controller.service.interfaces;

import com.quiz.model.quiz.question.QuestionBasic;
import org.springframework.web.servlet.ModelAndView;

public interface QuestionService {
    public ModelAndView createBasicQuestion(String author);
    public ModelAndView createTestQuestion(String author);
    public ModelAndView createFillInBlankQuestion(String author);
    public ModelAndView registerQuestion(String author, String body, String type, int maxGrade, String imageFile, String correctAnswer, String answers);
}
