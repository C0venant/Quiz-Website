package com.quiz;

import com.quiz.database.BaseInterface;
import com.quiz.database.testBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Test {
    @Autowired
    private BaseInterface baseInterface;

    @RequestMapping("/test")
    public ModelAndView testControl(@RequestParam("username") String user,
                                    @RequestParam("password")String password){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("user", user);
        mv.addObject("password", password);

        //baseInterface.getAllInfo().stream().forEach(a -> System.out.println(a));
        List<String> getAll = baseInterface.getAllInfo();
        for(int i=0; i<getAll.size(); i++){
            System.out.println(getAll.get(i));
        }

        return mv;
    }
}
