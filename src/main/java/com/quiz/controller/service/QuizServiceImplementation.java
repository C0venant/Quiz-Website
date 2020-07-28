package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.QuizService;
import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class QuizServiceImplementation implements QuizService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    RequestDao requestDao;

    @Override
    public ModelAndView displayQuestions(String author) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username" ,author);
        mv.addObject("questions" ,questionDao.getAuthorQuestions(author));
        mv.setViewName("quiz/chooseQuestions");
        return mv;
    }

    private void addQuiz(List<QuestionBasic> list, String quizName, String author){
        if(!list.isEmpty()){
            Quiz quiz = new Quiz(quizName, author, list);
            quizDao.addQuiz(quiz);
        }
    }

    @Override
    public ModelAndView assembleQuestions(String author, String quizName, HttpServletRequest req) {
        List<QuestionBasic> list = new ArrayList<>();
        for(int i = 0; i< questionDao.getAuthorQuestions(author).size(); i++){
            if(req.getParameter("question"+i) != null){
                int id = Integer.parseInt(req.getParameter("question"+i));
                QuestionBasic q = questionDao.getQuestion(id);
                list.add(q);
            }
        }
        addQuiz(list, quizName, author);
        return getDefaultPageModelAndView(author);
    }

    private ModelAndView getDefaultPageModelAndView(String author) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(author));
        mv.addObject("questions", questionDao.getAuthorQuestions(author));
        mv.addObject("friendRequests", requestDao.getFriendRequests(author));
        mv.addObject("allUnreadMessages", requestDao.getAllUnreadMessages(author));
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        return mv;
    }

    @Override
    public ModelAndView viewOrDeleteQuiz(String author, String quizName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("quiz/viewQuiz");
        mv.addObject("username", author);
        mv.addObject("quiz", quizDao.getQuizByName(quizName));
        return mv;
    }

    @Override
    public ModelAndView deleteQuiz(String author, String quizName) {
        quizDao.deleteQuiz(quizName);
        return getDefaultPageModelAndView(author);
    }

    @Override
    public ModelAndView enrollQuiz(String username, String quizName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", username);
        mv.addObject("quiz", quizDao.getQuizByName(quizName));
        mv.setViewName("quiz/enrollQuiz");
        return mv;
    }

    @Override
    public ModelAndView startQuiz(String username, String quizName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", username);
        mv.addObject("quiz", quizDao.getQuizByName(quizName));
        mv.addObject("questionIndex", 0);
        mv.setViewName("quiz/quizQuestion");
        return mv;
    }

    @Override
    public ModelAndView fetchNextQuestion(String username, String quizName, String nextQuestion) {
        System.out.println(username);
        System.out.println(quizName);
        System.out.println(nextQuestion);
        return null;
    }
}
