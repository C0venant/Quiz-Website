package com.quiz.controller;

import com.quiz.controller.service.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class QuizController {

    @Autowired
    QuizService quizService;

    @RequestMapping("/chooseQuestions")
    public ModelAndView chooseQuestion(@RequestParam String username){
        return quizService.displayQuestions(username);
    }

    @RequestMapping("/assembleQuestions")
    public ModelAndView assembleQuestions(@RequestParam String username, @RequestParam String quizName, HttpServletRequest req){
        return quizService.assembleQuestions(username, quizName, req);
    }

    @RequestMapping("/viewQuiz")
    public ModelAndView assembleQuestions(@RequestParam String username, @RequestParam String quizName){
        return quizService.viewOrDeleteQuiz(username, quizName);
    }

    @RequestMapping("/deleteQuiz")
    public ModelAndView deleteQuiz(@RequestParam String username, @RequestParam String quizName){
        return quizService.deleteQuiz(username, quizName);
    }

}
