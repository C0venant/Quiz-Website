package com.quiz.controller.service;

import com.quiz.controller.service.interfaces.UserService;
import com.quiz.database.interfaces.QuestionDao;
import com.quiz.database.interfaces.QuizDao;
import com.quiz.database.interfaces.RequestDao;
import com.quiz.database.interfaces.UserDao;
import com.quiz.model.request.Request;
import com.quiz.model.request.RequestType;
import com.quiz.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class UserServiceImplementation implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    QuizDao quizDao;

    @Override
    public ModelAndView sendRequest(String fromUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("request/sendFriendRequest");
        return mv;
    }

    @Override
    public ModelAndView addFriendService(String fromUser, String toUser) {
        ModelAndView mv = new ModelAndView();
        User user1 = new User(fromUser, "r", "", "");
        User user2 = new User(toUser, "r", "", "");
        boolean areFriends = userDao.areFriends(user1, user2);
        boolean userExists = userDao.getUser(toUser) != null;
        if(userExists && !areFriends && !toUser.equals(fromUser)){
            List<Request> sentReqs = requestDao.getSentRequests(fromUser);
            boolean alreadySent = false;
            for(Request req : sentReqs){
                if (req.getToUser().equals(toUser) && req.getType().equals(RequestType.FRIEND)) {
                    alreadySent = true;
                    break;
                }
            }
            if(!alreadySent){
                Request newReq = new Request(fromUser, toUser, RequestType.FRIEND, "");
                requestDao.addRequest(newReq);
            }
            mv.addObject("quizzes", quizDao.getQuizzesByAuthor(fromUser));
            mv.addObject("questions", questionDao.getAuthorQuestions(fromUser));
            mv.addObject("friendRequests", requestDao.getFriendRequests(fromUser));
            mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        } else {
            if(areFriends){
                mv.setViewName("request/alreadyFriends");
            } else {
                mv.setViewName("request/incorrectUserName");
            }
        }
        return mv;
    }

    @Override
    public ModelAndView homepageService(String userName){
        ModelAndView mv = new ModelAndView();
        mv.addObject("questions", questionDao.getAuthorQuestions(userName));
        mv.addObject("quizzes", quizDao.getQuizzesByAuthor(userName));
        mv.addObject("friendRequests", requestDao.getFriendRequests(userName));
        mv.setViewName("loginAndRegister/correctLoginOrRegistration");
        return mv;
    }

    @Override
    public ModelAndView acceptOrRejectService(String fromUser, String toUser, Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("request/acceptOrRejectFriendReq");
        mv.addObject("fromUser", fromUser);
        mv.addObject("toUser", toUser);
        mv.addObject("id", id);
        return mv;
    }

    @Override
    public ModelAndView proceedAcceptOrRejectService(String accept, String fromUser, String userName, Integer id) {
        ModelAndView mv;
        if(accept.equals("yes")){
            userDao.addFriend(new User(fromUser, "", "", ""), new User(userName, "", "", ""));
        }
        requestDao.deleteRequest(id);
        mv = homepageService(userName);
        return mv;
    }

    @Override
    public ModelAndView friendRequestsService(String userName){
        ModelAndView mv = new ModelAndView();
        mv.addObject("friendRequests", requestDao.getFriendRequests(userName));
        mv.addObject("userDao", userDao);
        mv.addObject("username", userName);
        mv.setViewName("request/friendRequests");
        return mv;
    }

}
