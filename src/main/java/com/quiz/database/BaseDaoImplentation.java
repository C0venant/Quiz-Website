package com.quiz.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImplentation implements BaseDao {

    private JdbcTemplate jdbcTemplate;

    public BaseDaoImplentation(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> getAllInfo() {
        String select = "select * from metropolises";
        List<String> ls = jdbcTemplate.query(select, new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("metropolis");
            }
        });
        return ls;
    }
}
