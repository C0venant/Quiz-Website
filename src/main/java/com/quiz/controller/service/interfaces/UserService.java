package com.quiz.controller.service.interfaces;

import com.quiz.database.interfaces.QuestionDao;
import com.quiz.model.quiz.question.QuestionBasic;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {
    public ModelAndView sendRequest(String fromUser);
    public ModelAndView addFriendService(String fromUser, String toUser);
    public ModelAndView homepageService(String userName);
    public ModelAndView acceptOrRejectService(String fromUser, String toUser, Integer id);
    public ModelAndView proceedAcceptOrRejectService(String accept, String fromUser, String userName, Integer id);
}
