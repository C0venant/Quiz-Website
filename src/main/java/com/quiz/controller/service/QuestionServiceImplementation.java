package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.QuestionService;
import com.quiz.database.interfaces.QuestionDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    QuestionDao questionDao;

    @Override
    public ModelAndView createBasicQuestion(String author) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", author);
        mv.setViewName("question/createQuestionBasic");
        return mv;
    }

    @Override
    public ModelAndView createTestQuestion(String author) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", author);
        mv.setViewName("question/createQuestionTest");
        return mv;
    }

    @Override
    public ModelAndView createFillInBlankQuestion(String author) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", author);
        mv.setViewName("question/createQuestionFillBlank");
        return mv;
    }

    @Override
    public ModelAndView registerQuestion(String author, String body, String type, int maxGrade, String imageFile, String correctAnswer, String answers) {
        QuestionBasic question;
        if(type.equals(QuestionType.BLANK)){
            question = new QuestionFillBlank(body, maxGrade, imageFile, correctAnswer, -1);
        }else if(type.equals(QuestionType.TEST)){
            question = new QuestionTest(body, maxGrade, imageFile, correctAnswer, -1, null);
        }else{
            question = new QuestionBasic(body, maxGrade, imageFile, correctAnswer, -1);
        }
        questionDao.addQuestion(author, question);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        return mv;
    }

}
