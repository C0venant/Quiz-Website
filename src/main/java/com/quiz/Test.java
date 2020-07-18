package com.quiz;

import com.quiz.database.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Test {
    @Autowired
    private BaseDao baseDao;

    @RequestMapping("/test")
    public ModelAndView testControl(@RequestParam("username") String user,
                                    @RequestParam("password")String password) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        mv.addObject("user", user);
        mv.addObject("password", password);

        //baseInterface.getAllInfo().stream().forEach(a -> System.out.println(a));
        List<String> getAll = baseDao.getAllInfo();
        for (String s : getAll) {
            System.out.println(s);
        }

        return mv;
    }

}
