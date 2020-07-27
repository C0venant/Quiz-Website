package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface QuizService {

    public ModelAndView displayQuestions(String author);
    public ModelAndView assembleQuestions(String author, String quizName, HttpServletRequest req);
    public ModelAndView viewOrDeleteQuiz(String author, String quizName);
}
