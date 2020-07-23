package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.UserService;
import com.quiz.database.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class UserServiceImplementation implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ModelAndView addFriendService() {
        return null;
    }
}
