package com.quiz.controller;

import com.quiz.database.interfaces.UserDao;
import com.quiz.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class LoginRegisterController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public ModelAndView loginControl(@RequestParam("username") String userName,
                                     @RequestParam("password") String password) throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();
        boolean log = userDao.loginUser(userName, password);
        if(log){
            mv.setViewName("correctLoginOrRegistration");
            mv.addObject("username", userName);
        } else {
            mv.setViewName("incorrectLogin");
        }
        return mv;
    }

    @RequestMapping("/createAccount")
    public ModelAndView createAccountControl(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("createAccount");
        return mv;
    }

    @RequestMapping("/proceedAccountCreation")
    public ModelAndView proceedAccountCreationControl(@RequestParam("username") String userName,
                                                      @RequestParam("password") String password,
                                                      @RequestParam("firstname") String firstName,
                                                      @RequestParam("lastname") String lastName) throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();
        User newUser = new User(userName, password, firstName, lastName);
        boolean reg = userDao.registerUser(newUser);
        if(reg){
            mv.setViewName("correctLoginOrRegistration");
        } else {
            mv.setViewName("nameInUse");
        }
        mv.addObject("username", userName);
        return mv;
    }
}
