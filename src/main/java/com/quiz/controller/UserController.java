package com.quiz.controller;

import com.quiz.controller.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("proceedSendFriendRequest")
    public ModelAndView proceedSendFriendRequestControl(@RequestParam("username") String fromUser,
                                                        @RequestParam("touser") String toUser){
        return userService.addFriendService(fromUser, toUser);
    }

    @RequestMapping("homepage")
    public ModelAndView homepageControl(@RequestParam("username") String userName){
        return userService.homepageService(userName);
    }

    @RequestMapping("acceptOrReject")
    public ModelAndView acceptOrRejectControl(@RequestParam String fromUser, @RequestParam String toUser, @RequestParam Integer id){
        return  userService.acceptOrRejectService(fromUser, toUser, id);
    }

    @RequestMapping("proceedAcceptOrReject")
    public ModelAndView proceedAcceptOrRejectControl(@RequestParam("accept") String accept,
                                                     @RequestParam("fromuser") String fromUser,
                                                     @RequestParam("username") String userName,
                                                     @RequestParam("id") Integer id){
        return userService.proceedAcceptOrRejectService(accept, fromUser, userName, id);
    }

    @RequestMapping("friendRequests")
    public ModelAndView friendRequestsControl(@RequestParam("username") String userName){
        return userService.friendRequestsService(userName);
    }
}
