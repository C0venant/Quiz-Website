package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.QuestionService;
import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import com.quiz.utilities.TextFieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    RequestDao requestDao;

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
    public ModelAndView editOrDeleteQuestion(String author, int questionId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", author);
        mv.addObject("id", questionId);
        mv.addObject("question", questionDao.getQuestion(questionId));
        mv.setViewName("question/editOrDeleteQuestion");
        return mv;
    }

    @Override
    public ModelAndView deleteQuestion(String author, int questionId) {
        questionDao.deleteQuestion(questionId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        mv.addObject("friendRequests", requestDao.getFriendRequests(author));
        mv.addObject("questions", questionDao.getAuthorQuestions(author));
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(author));
        return mv;
    }

    @Override
    public ModelAndView registerQuestion(String author, String body, String typeFull, int maxGrade, String imageFile, String correctAnswer, String answers) {
        QuestionBasic question;
        String[] t = typeFull.split("\\?");
        String type = t[0];
        if(type.equals(QuestionType.BLANK)){
            question = new QuestionFillBlank(body, maxGrade, imageFile, correctAnswer, -1);
        }else if(type.equals(QuestionType.TEST)){
            question = new QuestionTest(body, maxGrade, imageFile, correctAnswer, -1, TextFieldUtils.parseProbableAnswers(answers));
        }else{
            question = new QuestionBasic(body, maxGrade, imageFile, correctAnswer, -1);
        }
        questionDao.addQuestion(author, question);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        mv.addObject("friendRequests", requestDao.getFriendRequests(author));
        mv.addObject("questions", questionDao.getAuthorQuestions(author));
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(author));
        return mv;
    }

}
