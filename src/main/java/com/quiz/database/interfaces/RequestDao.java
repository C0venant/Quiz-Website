package com.quiz.database.interfaces;

import com.quiz.model.request.Request;

import java.util.List;

public interface RequestDao {

    public void addRequest(Request request);
    public boolean deleteRequest(int requestId);
    public Request getRequest(int requestId);
    public List<Request> getSentRequests(String userFrom);
    public List<Request> getReceivedRequests(String userTo);
    public List<Request> getFriendRequests(String userTo);

}
