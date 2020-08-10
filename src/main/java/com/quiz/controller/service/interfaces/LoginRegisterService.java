package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

public interface LoginRegisterService {
    ModelAndView loginService(String userName, String password) throws NoSuchAlgorithmException;

    ModelAndView createAccountService();

    ModelAndView proceedAccountCreationControl(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException;

    ModelAndView logoutService();
}
