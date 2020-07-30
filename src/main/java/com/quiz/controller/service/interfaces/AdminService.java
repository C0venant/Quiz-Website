package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface AdminService {
    public ModelAndView listQuizAndUsers(String username);
}
