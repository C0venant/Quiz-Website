package com.quiz.controller.service.interfaces;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

public interface LoginRegisterService {
    public ModelAndView loginService(String userName, String password) throws NoSuchAlgorithmException;
    public ModelAndView createAccountService();
    public ModelAndView proceedAccountCreationControl(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException;
    public ModelAndView logoutService();
}
