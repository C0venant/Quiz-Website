package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.UserService;
import com.quiz.database.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplementation implements UserService {
    @Autowired
    UserDao userDao;

}
