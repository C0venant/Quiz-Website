package com.quiz.database;

import com.quiz.database.interfaces.RequestDao;
import com.quiz.model.request.Request;
import com.quiz.model.request.RequestType;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImplementation implements RequestDao {

    private final JdbcTemplate jdbcTemplate;

    public RequestDaoImplementation(DataSource dataSource){jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public void addRequest(Request request) {
        String fromUser = request.getFromUser();
        String toUser = request.getToUser();
        String type = request.getType();
        String body = request.getBody();
        String regStr = "INSERT INTO requests (fromUser, toUser, requestType, body)"
                    + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(regStr, fromUser, toUser, type, body);
    }


    @Override
    public boolean deleteRequest(int requestId) {
        Request request = getRequest(requestId);
        if(request == null){
            return false;
        } else {
            String delete = "DELETE FROM requests WHERE requestId =?";
            jdbcTemplate.update(delete, requestId);
            return  true;
        }
    }

    @Override
    public Request getRequest(int requestId) {
        String getStr = "SELECT * FROM requests WHERE requestId = " + requestId;
        return jdbcTemplate.query(getStr, resultSet -> {
            if(resultSet.next()){
                int requestId1 = resultSet.getInt(1);
                String fromUser = resultSet.getString(2);
                String toUser = resultSet.getString(3);
                String requestType = resultSet.getString(4);
                String body = resultSet.getString(5);
                boolean isSeen = resultSet.getBoolean(6);
                return new Request(fromUser, toUser, requestType, body, isSeen, requestId1);
            }
            return null;
        });
    }

    @Override
    public List<Request> getSentRequests(String fromUser) {
        String getStr = "SELECT * FROM requests WHERE fromUser = " + "'" + fromUser + "'";
        return jdbcTemplate.query(getStr, (resultSet, rowNum) -> getRequest(resultSet.getInt(1)));
    }

    @Override
    public List<Request> getReceivedRequests(String toUser) {
        String getStr = "SELECT * FROM requests WHERE toUser = " + "'" + toUser+ "'";
        return jdbcTemplate.query(getStr, (resultSet, rowNum) -> getRequest(resultSet.getInt(1)));
    }

    @Override
    public List<Request> getFriendRequests(String userTo) {
        List<Request> allReqs = getReceivedRequests(userTo);
        List<Request> friendReqs = new ArrayList<>();
        for(Request req : allReqs){
            if(req.getType().equals(RequestType.FRIEND)){
                friendReqs.add(req);
            }
        }
        return friendReqs;
    }

    @Override
    public List<Request> getMessages(String toUser){
        List<Request> allReqs = getReceivedRequests(toUser);
        List<Request> messageReqs = new ArrayList<>();
        for(Request req : allReqs){
            if(req.getType().equals(RequestType.NOTE)){
                messageReqs.add(req);
            }
        }
        return messageReqs;
    }

    @Override
    public List<Request> getAllUnreadMessages(String toUser) {
        List<Request> allReqs = getReceivedRequests(toUser);
        List<Request> messageReqs = new ArrayList<>();
        for (Request req : allReqs) {
            if (req.getType().equals(RequestType.NOTE) && !req.isSeen()) {
                messageReqs.add(req);
            }
        }
        return messageReqs;
    }

    @Override
    public List<Request> getUnreadMessagesFromConcreteUser(String toUser, String fromUser) {
        String getStr = "SELECT * FROM requests WHERE requestType =" + "'" + RequestType.NOTE + "'"
                        + " AND seen = FALSE AND fromUser =" + "'" + fromUser + "'"
                        + " AND toUser =" + "'" + toUser + "'";
        return jdbcTemplate.query(getStr, (resultSet, rowNum) -> getRequest(resultSet.getInt(1)));
    }

    @Override
    public void markAllMessagesAsRead(String toUser){
        String markAsReadStr = "UPDATE requests SET seen = TRUE WHERE toUser = ? AND requestType = ?";
        jdbcTemplate.update(markAsReadStr, toUser, RequestType.NOTE);
    }

    @Override
    public void markAsReadMessagesFromConcreteUser(String toUser, String fromUser){
        String markAllAsReadStr = "UPDATE requests SET seen = TRUE WHERE requestType = ? AND fromUser=? AND toUser=?";
        jdbcTemplate.update(markAllAsReadStr, RequestType.NOTE, fromUser, toUser);
    }
}
