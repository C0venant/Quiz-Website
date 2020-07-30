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
}
