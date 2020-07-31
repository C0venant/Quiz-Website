package com.quiz.controller;

import com.quiz.controller.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/listQuizAndUsers")
    public ModelAndView listQuizAndUsers(@RequestParam String username){
        return adminService.listQuizAndUsers(username);
    }

    @RequestMapping("/loadUserView")
    public ModelAndView loadUserView(@RequestParam String username, @RequestParam String globalUser){
        return adminService.loadUser(username, globalUser);
    }

    @RequestMapping("/loadQuizView")
    public ModelAndView loadQuizView(@RequestParam String username, @RequestParam String quizName){
        return adminService.loadQuiz(username, quizName);
    }

    @RequestMapping("/deleteQuizFromSite")
    public ModelAndView deleteQuizFromSite(@RequestParam String username, @RequestParam String quizName){
        return adminService.deleteQuiz(username, quizName);
    }

    @RequestMapping("/deleteUserFromSite")
    public ModelAndView deleteUserFromSite(@RequestParam String username, @RequestParam String globalUser){
        return adminService.deleteUser(username, globalUser);
    }

    @RequestMapping("/grantAdmin")
    public ModelAndView grantAdmin(@RequestParam String username, @RequestParam String globalUser){
        return adminService.AdminUser(username, globalUser);
    }

    @RequestMapping("/returnHome")
    public ModelAndView returnHome(@RequestParam String username){
        return adminService.backToHome(username);
    }
}
