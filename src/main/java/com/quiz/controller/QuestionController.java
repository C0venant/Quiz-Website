package com.quiz.controller;

import com.quiz.controller.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/createQuestion_basic")
    public ModelAndView loadBasicCreationPage(@RequestParam String username){
        return questionService.createBasicQuestion(username);
    }

    @RequestMapping("/createQuestion_blank")
    public ModelAndView loadFillBlankCreationPage(@RequestParam String username){
        return questionService.createFillInBlankQuestion(username);
    }

    @RequestMapping("/createQuestion_test")
    public ModelAndView loadTestCreationPage(@RequestParam String username){
        return questionService.createTestQuestion(username);
    }
    @RequestMapping("/registerQuestion")
    public ModelAndView addQuestionToDataBase(@RequestParam String username,
                                              @RequestParam(value = "body") String body,
                                              @RequestParam(value = "maxGrade") int maxGrade,
                                              @RequestParam(value = "type") String type,
                                              @RequestParam(value = "imageFile",required=false) String imageFile,
                                              @RequestParam(value = "correctAnswer",required=false) String correctAnswer,
                                              @RequestParam(value = "answers",required=false) String answers){
        return questionService.registerQuestion(username, body, type, maxGrade, imageFile, correctAnswer, answers);
    }



}
