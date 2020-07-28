package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface UserService {
    ModelAndView sendRequest(String fromUser);

    ModelAndView addFriendService(String fromUser, String toUser);

    ModelAndView homepageService(String userName);

    ModelAndView acceptOrRejectService(String fromUser, String toUser, Integer id);

    ModelAndView proceedAcceptOrRejectService(String accept, String fromUser, String userName, Integer id);

    ModelAndView friendRequestsService(String userName);
}
