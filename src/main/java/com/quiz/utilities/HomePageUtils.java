package com.quiz.utilities;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import org.springframework.web.servlet.ModelAndView;

public class HomePageUtils {
    public static ModelAndView setHomeParameters(String userName, QuestionDao questionDao, QuizDao quizDao, RequestDao requestDao){
        ModelAndView mv = new ModelAndView();
        mv.addObject("questions", questionDao.getAuthorQuestions(userName));
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(userName));
        mv.addObject("friendRequests", requestDao.getFriendRequests(userName));
        mv.addObject("allUnreadMessages", requestDao.getAllUnreadMessages(userName));
        mv.addObject("needsChecking", quizDao.needsCheck(userName));
        mv.addObject("isChecked", quizDao.checkedQuizUser(userName));
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        return mv;
    }
}
