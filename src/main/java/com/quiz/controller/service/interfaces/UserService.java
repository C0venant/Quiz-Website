package com.quiz.controller.service.interfaces;

import org.springframework.web.servlet.ModelAndView;

public interface UserService {

    ModelAndView addFriendService(String fromUser, String toUser);

    ModelAndView homepageService(String userName);

    ModelAndView acceptOrRejectService(String fromUser, String toUser, Integer id);

    ModelAndView proceedAcceptOrRejectService(String accept, String fromUser, String userName, Integer id);

    ModelAndView friendRequestsService(String userName);

    ModelAndView messengerService(String userName);

    ModelAndView markAllAsReadService(String userName);

    ModelAndView messageToUserService(String userName, String fromUser);

    ModelAndView sendMessageService(String fromUser, String toUser, String messageText);
}
