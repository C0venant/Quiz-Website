package com.quiz.database;

import com.quiz.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplementation implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImplementation(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
     * Given a byte[] array, produces a hex String, such as "234a6f". with 2 chars
     * for each byte in the array. (provided code)
     */
    public static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff; // remove higher bits, sign
            if (val < 16)
                buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    @Override
    public boolean registerUser(String loginName, String password, String firstName, String lastName) throws NoSuchAlgorithmException {
        if(getUser(loginName) != null){
            return false;
        } else {
            byte[] hex = generateHash(password);
            String hashedPassword = hexToString(hex);
            String regStr = "INSERT INTO users (loginName, hashedPassword, firstName, lastName)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(regStr, loginName, hashedPassword, firstName, lastName);
            return true;
        }
    }

    @Override
    public User getUser(final String loginName) {
        String getStr = "SELECT * FROM users WHERE loginName = " + "'" + loginName + "'";
        return jdbcTemplate.query(getStr, new ResultSetExtractor<User>() {

            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String hashedPassword = resultSet.getString(2);
                    String firstName = resultSet.getString(3);
                    String lastName = resultSet.getString(4);
                    return new User(loginName, hashedPassword, firstName, lastName);
                }
                return null;
            }
        });
    }

    @Override
    public boolean loginUser(String loginName, String password) throws NoSuchAlgorithmException {
        User currUser = getUser(loginName);
        if(currUser == null){
            return false;
        } else {
            String realHashedPassword = currUser.getHashedPassword();
            byte[] hex = generateHash(password);
            String tryingHashedPassword = hexToString(hex);
            return tryingHashedPassword.equals(realHashedPassword);
        }
    }

    protected byte[] generateHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        return md.digest();
    }
}
