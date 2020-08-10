package com.quiz.utilities;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import com.quiz.database.interfaces.UserDao;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class HomePageUtils {
    public static ModelAndView setHomeParameters(String userName, String somethingChanged, QuestionDao questionDao, QuizDao quizDao, RequestDao requestDao, UserDao userdao){
        ModelAndView mv = new ModelAndView();
        mv.addObject("questions", questionDao.getAuthorQuestions(userName));
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(userName));
        mv.addObject("friendRequests", requestDao.getFriendRequests(userName));
        mv.addObject("allUnreadMessages", requestDao.getAllUnreadMessages(userName));
        mv.addObject("needsChecking", quizDao.needsCheck(userName));
        mv.addObject("globalQuizzes", quizDao.getGlobalQuizzes(userName));
        mv.addObject("isAdmin", userdao.isAdmin(userName));
        Map<String, Integer> gradeMap = new HashMap<>();
        for(String quiz : quizDao.checkedQuizUser(userName)){
            gradeMap.put(quiz, quizDao.getQuizScore(quiz, userName));
        }
        mv.addObject("isChecked", gradeMap);
        mv.addObject("somethingChanged", somethingChanged);
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        return mv;
    }
}
