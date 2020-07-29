package com.quiz.database.interfaces;

import com.quiz.model.request.Request;

import java.util.List;

public interface RequestDao {

    void addRequest(Request request);

    boolean deleteRequest(int requestId);

    Request getRequest(int requestId);

    List<Request> getSentRequests(String userFrom);

    List<Request> getReceivedRequests(String userTo);

    List<Request> getFriendRequests(String userTo);

    List<Request> getMessagesFromConcreteUser(String toUser, String fromUser);

    List<Request> getAllUnreadMessages(String toUser);

    List<Request> getUnreadMessagesFromConcreteUser(String toUser, String fromUser);

    void markAsReadMessagesFromConcreteUser(String toUser, String fromUser);

    void markAllMessagesAsRead(String toUser);
}
