package com.quiz.database;

import com.quiz.database.interfaces.RequestDao;
import com.quiz.model.quiz.question.QuestionBasic;
import com.quiz.model.quiz.question.QuestionFillBlank;
import com.quiz.model.quiz.question.QuestionTest;
import com.quiz.model.quiz.question.utils.QuestionType;
import com.quiz.model.request.Request;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.query(getStr, new ResultSetExtractor<Request>() {
            @Override
            public Request extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int requestId = resultSet.getInt(1);
                    String fromUser = resultSet.getString(2);
                    String toUser = resultSet.getString(3);
                    String requestType = resultSet.getString(4);
                    String body = resultSet.getString(5);
                    return new Request(fromUser, toUser, requestType, body, requestId);
                }
                return null;
            }
        });
    }

    @Override
    public List<Request> getSentRequests(String fromUser) {
        String getStr = "SELECT * FROM requests WHERE fromUser = " + "'" + fromUser + "'";
        return jdbcTemplate.query(getStr, new RowMapper<Request>() {
            @Override
            public Request mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return  getRequest(resultSet.getInt(1));
            }
        });
    }

    @Override
    public List<Request> getReceivedRequests(String toUser) {
        String getStr = "SELECT * FROM requests WHERE toUser = " + "'" + toUser+ "'";
        return jdbcTemplate.query(getStr, new RowMapper<Request>() {
            @Override
            public Request mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return  getRequest(resultSet.getInt(1));
            }
        });
    }
}
