package com.quiz.controller;

import com.quiz.controller.service.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        System.out.println(quizName);
        return quizService.deleteQuiz(username, quizName);
    }

    @RequestMapping("/enrollQuiz")
    public ModelAndView enrollQuiz(@RequestParam String username, @RequestParam String quizName){
        return quizService.enrollQuiz(username, quizName);
    }

    @RequestMapping("/startQuiz")
    public ModelAndView startQuiz(@RequestParam String username, @RequestParam String quizName){
        return quizService.startQuiz(username, quizName);
    }

    @RequestMapping("/fetchNextQuestion")
    public ModelAndView fetchNextQuestion(@RequestParam String username,
                                          @RequestParam String quizName,
                                          @RequestParam String nextQuestion,
                                          @RequestParam int index,
                                          @RequestParam(value = "userAnswer",required=false) String userAnswer){
        return quizService.fetchNextQuestion(username, quizName, nextQuestion, index, userAnswer);
    }

    @RequestMapping("/gradeQuiz")
    public ModelAndView gradeQuiz(@RequestParam String username, @RequestParam String quizName, @RequestParam String checkReq){
        return quizService.prepareQuizForCheck(username, quizName, checkReq);
    }

    @RequestMapping("/returnMarked")
    public ModelAndView returnMarked(@RequestParam String username,
                                     @RequestParam String quizName,
                                     @RequestParam String checkReq,
                                     HttpServletRequest req){
        return quizService.submitQuizScore(username, quizName, checkReq, req);
    }

}
