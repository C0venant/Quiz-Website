package com.quiz.controller.service.interfaces;

import com.quiz.model.quiz.Quiz;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface QuizService {

    public ModelAndView displayQuestions(String author);
    public ModelAndView assembleQuestions(String author, String quizName, HttpServletRequest req);
    public ModelAndView viewOrDeleteQuiz(String author, String quizName);
    public ModelAndView deleteQuiz(String author, String quizName);
    public ModelAndView enrollQuiz(String username, String quizName);
    public ModelAndView startQuiz(String username, String quizName);
}
