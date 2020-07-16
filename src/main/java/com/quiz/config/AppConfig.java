package com.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import sun.jvm.hotspot.debugger.DataSource;


@Configuration
@ComponentScan({"com.quiz"})
public class AppConfig {

    // this one is responsible for searching view name
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/view/");
        vr.setSuffix(".jsp");
        return vr;
    }


    //TODO: here must go database configuration(this is just sample might work might not)
    /*
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
     */

}
