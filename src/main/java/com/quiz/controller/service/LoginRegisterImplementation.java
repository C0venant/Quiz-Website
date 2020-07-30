package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.LoginRegisterService;
import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import com.quiz.database.interfaces.UserDao;
import com.quiz.model.user.User;
import com.quiz.utilities.HomePageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

public class LoginRegisterImplementation implements LoginRegisterService {

    @Autowired
    UserDao userDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    RequestDao requestDao;



    @Override
    public ModelAndView loginService(String userName, String password) throws NoSuchAlgorithmException {
        boolean log = userDao.loginUser(userName, password);
        ModelAndView mv;
        if(log){
            mv = HomePageUtils.setHomeParameters(userName, questionDao, quizDao, requestDao, userDao);
            mv.addObject("username", userName);
        } else {
            mv = new ModelAndView();
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
        ModelAndView mv;
        User newUser = new User(userName, password, firstName, lastName);
        boolean reg = userDao.registerUser(newUser);
        if(reg){
            mv = HomePageUtils.setHomeParameters(userName, questionDao, quizDao, requestDao, userDao);
        } else {
            mv = new ModelAndView();
            mv.setViewName("loginAndRegister/nameInUse");
        }
        mv.addObject("username", userName);
        return mv;
    }

    @Override
    public ModelAndView logoutService() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginAndRegister/homepage");
        return mv;
    }
}
