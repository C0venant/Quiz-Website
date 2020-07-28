package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface QuizService {

    ModelAndView displayQuestions(String author);

    ModelAndView assembleQuestions(String author, String quizName, HttpServletRequest req);

    ModelAndView viewOrDeleteQuiz(String author, String quizName);

    ModelAndView deleteQuiz(String author, String quizName);

    ModelAndView enrollQuiz(String username, String quizName);

    ModelAndView startQuiz(String username, String quizName);

    ModelAndView fetchNextQuestion(String username, String quizName, String nextQuestion);
}
