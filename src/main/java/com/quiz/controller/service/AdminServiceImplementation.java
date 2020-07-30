package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.AdminService;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.UserDao;
import com.quiz.model.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class AdminServiceImplementation implements AdminService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserDao userDao;

    @Override
    public ModelAndView listQuizAndUsers(String username) {
        System.out.println(username);
        ModelAndView mv = new ModelAndView();
        List <String> quizList = quizDao.getGlobalQuizzes("learnedFromMaster");
        List <String> userList = userDao.getGlobalUsers(username);
        mv.addObject("quizList", quizList);
        mv.addObject("userList", userList);
        mv.setViewName("admin/quizAndUsers");
        return mv;
    }
}
