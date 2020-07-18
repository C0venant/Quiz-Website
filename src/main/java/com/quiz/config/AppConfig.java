package com.quiz.config;

import com.quiz.database.UserDaoImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.quiz"})
@EnableWebMvc
public class AppConfig {
    /** replace with yours*/
    private static final String DATABASE_NAME = "homework_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    // this one is responsible for searching view name
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/view/");
        vr.setSuffix(".jsp");
        return vr;
    }


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/" + DATABASE_NAME + "?useSSL=false");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public UserDaoImplementation getUserDao() {
        return new UserDaoImplementation(getDataSource());
    }
}
