package com.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Test {

    @RequestMapping("/test")
    public ModelAndView testControl(@RequestParam("username") String user,
                                    @RequestParam("password")String password){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("user", user);
        mv.addObject("password", password);
        return mv;
    }
}
