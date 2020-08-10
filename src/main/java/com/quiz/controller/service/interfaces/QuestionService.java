package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface QuestionService {
    ModelAndView createBasicQuestion(String author);

    ModelAndView createTestQuestion(String author);

    ModelAndView createFillInBlankQuestion(String author);

    ModelAndView editOrDeleteQuestion(String author, int questionId);

    ModelAndView deleteQuestion(String author, int questionId);

    ModelAndView registerQuestion(String author, String body, String type, int maxGrade, String imageFile, String correctAnswer, String answers);
}
