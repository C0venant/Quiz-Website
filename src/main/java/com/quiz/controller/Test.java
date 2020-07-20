package com.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {
    @RequestMapping("/test")
    public ModelAndView testControl(@RequestParam("username") String user,
                                    @RequestParam("password")String password) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("user", user);
        mv.addObject("password", password);

        return mv;
    }

}
