package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface AdminService {
    public ModelAndView listQuizAndUsers(String username);
    public ModelAndView loadUser(String username, String globalUser);
    public ModelAndView loadQuiz(String username, String quizName);
    public ModelAndView deleteQuiz(String username, String quizName);
    public ModelAndView deleteUser(String username, String globalUser);
    public ModelAndView AdminUser(String username, String globalUser);
    public ModelAndView backToHome(String username);
}
