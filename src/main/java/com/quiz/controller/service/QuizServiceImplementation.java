package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.QuizService;
import com.quiz.database.interfaces.*;
import com.quiz.model.quiz.Quiz;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.utilities.HomePageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImplementation implements QuizService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    RemoveDao removeDao;

    @Autowired
    UserDao userDao;

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

    private ModelAndView getDefaultPageModelAndView(String userName) {
       return HomePageUtils.setHomeParameters(userName, "true", questionDao, quizDao, requestDao, userDao);
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
        removeDao.removeQuiz(quizName);
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
        Quiz quiz = quizDao.getQuizByName(quizName);
        String previousAnswer = quizDao.getQuestionAnswer(quizName, username, quiz.getQuestions().get(0).getId());
        mv.addObject("username", username);
        mv.addObject("quiz", quiz);
        mv.addObject("questionIndex", 0);
        mv.addObject("previousAnswer", previousAnswer);
        mv.setViewName("quiz/quizQuestion");
        return mv;
    }

    @Override
    public ModelAndView fetchNextQuestion(String username, String quizName, String nextQuestion, int index, String userAnswer) {
        Quiz quiz = quizDao.getQuizByName(quizName);
        List<QuestionBasic> list = quiz.getQuestions();
        String previousAnswer;
        if(userAnswer != null){
            quizDao.answerQuestion(quizName, username, list.get(index).getId(), userAnswer);
        }
        ModelAndView mv = new ModelAndView();
        if(nextQuestion.equals("finish")){
            quizDao.addQuizForCheck(quizName, username, quiz.getQuizAuthor());
            return getDefaultPageModelAndView(username);
        }else if(nextQuestion.equals("next")){
            mv.addObject("questionIndex", index+1);
            previousAnswer = quizDao.getQuestionAnswer(quizName, username,list.get(index+1).getId());
        }else{
            mv.addObject("questionIndex", index-1);
            previousAnswer = quizDao.getQuestionAnswer(quizName, username,list.get(index-1).getId());
        }
        mv.addObject("previousAnswer", previousAnswer);
        mv.addObject("username", username);
        mv.addObject("quiz", quizDao.getQuizByName(quizName));
        mv.setViewName("quiz/quizQuestion");
        return mv;
    }

    @Override
    public ModelAndView prepareQuizForCheck(String username, String quizName, String checkReq) {
        List<String> userAnswers = new ArrayList<>();
        Quiz quiz = quizDao.getQuizByName(quizName);
        for(QuestionBasic q : quiz.getQuestions()){
            userAnswers.add(quizDao.getQuestionAnswer(quizName, checkReq, q.getId()));
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("quiz/gradeQuiz");
        mv.addObject("checkReq", checkReq);
        mv.addObject("quiz", quiz);
        mv.addObject("userAnswers", userAnswers);
        mv.addObject("author", username);
        return mv;
    }

    @Override
    public ModelAndView submitQuizScore(String username, String quizName, String checkReq, HttpServletRequest req) {
        Quiz quiz = quizDao.getQuizByName(quizName);
        List<QuestionBasic> qList = quiz.getQuestions();
        for(int i = 0; i < qList.size(); i++){
            int grade = Integer.parseInt(req.getParameter("grade"+i));
            quizDao.gradeAnsweredQuestion(quizName, checkReq, qList.get(i).getId(), grade);
        }
        quizDao.checkQuiz(quizName, checkReq);
        return HomePageUtils.setHomeParameters(username, "true", questionDao, quizDao, requestDao, userDao);
    }
}
