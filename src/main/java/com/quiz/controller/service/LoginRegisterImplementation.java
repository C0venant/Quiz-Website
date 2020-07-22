package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.LoginRegisterService;
import com.quiz.database.interfaces.UserDao;
import com.quiz.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

public class LoginRegisterImplementation implements LoginRegisterService {
    @Autowired
    UserDao userDao;

    @Override
    public ModelAndView loginService(String userName, String password) throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();
        boolean log = userDao.loginUser(userName, password);
        if(log){
            mv.setViewName("loginAndRegister/correctLoginOrRegistration");
            mv.addObject("username", userName);
        } else {
            mv.setViewName("loginAndRegister/incorrectLogin");
        }
        return mv;
    }

    @Override
    public ModelAndView createAccountService() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginAndRegister/createAccount");
        return mv;
    }

    @Override
    public ModelAndView proceedAccountCreationControl(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();
        User newUser = new User(userName, password, firstName, lastName);
        boolean reg = userDao.registerUser(newUser);
        if(reg){
            mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        } else {
            mv.setViewName("loginAndRegister/nameInUse");
        }
        mv.addObject("username", userName);
        return mv;
    }
}
