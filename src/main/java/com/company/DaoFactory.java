package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

    String server = "localhost"; // MySQL 서버 주소
    String database = "toby"; // MySQL DATABASE 이름
    String user_name = "root"; //  MySQL 서버 아이디
    String password = "Init123$"; // MySQL 서버 비밀번호

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<Driver>) Class.forName("com.mysql.cj.jdbc.Driver"));
        dataSource.setUrl("jdbc:mysql://localhost/toby?useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUsername(user_name);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }
}
