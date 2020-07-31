package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.AdminService;
import com.quiz.database.interfaces.*;
import com.quiz.model.quiz.Quiz;
import com.quiz.model.request.Request;
import com.quiz.model.user.User;
import com.quiz.utilities.HomePageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    RemoveDao removeDao;


    private ModelAndView adminHome(String username){
        ModelAndView mv = new ModelAndView();
        List <String> quizList = quizDao.getGlobalQuizzes("learnedFromMaster");
        List <String> userList = userDao.getGlobalUsers(username);
        mv.addObject("quizList", quizList);
        mv.addObject("userList", userList);
        mv.addObject("username", username);
        mv.setViewName("admin/quizAndUsers");
        return mv;
    }

    @Override
    public ModelAndView listQuizAndUsers(String username) {
        return adminHome(username);
    }

    @Override
    public ModelAndView loadUser(String username, String globalUser) {
        User user = userDao.getUser(globalUser);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.addObject("username", username);
        mv.setViewName("admin/userPage");
        return mv;
    }

    @Override
    public ModelAndView loadQuiz(String username, String quizName) {
        Quiz quiz = quizDao.getQuizByName(quizName);
        ModelAndView mv = new ModelAndView();
        mv.addObject("quiz", quiz);
        mv.addObject("username", username);
        mv.setViewName("admin/quizPage");
        return mv;
    }

    @Override
    public ModelAndView deleteQuiz(String username, String quizName) {
        removeDao.removeQuiz(quizName);
        return adminHome(username);
    }

    @Override
    public ModelAndView deleteUser(String username, String globalUser) {
        removeDao.removeUser(globalUser);
        return adminHome(username);
    }

    @Override
    public ModelAndView AdminUser(String username, String globalUser) {
        userDao.addAdmin(globalUser);
        return adminHome(username);
    }

    @Override
    public ModelAndView backToHome(String username) {
        return HomePageUtils.setHomeParameters(username, questionDao, quizDao, requestDao, userDao);
    }
}
