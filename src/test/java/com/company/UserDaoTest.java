package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;

class UserDaoTest {

    ApplicationContext context;
    UserDao dao;

    @BeforeEach
    public void loadDao() throws SQLException, ClassNotFoundException {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user1 = new User("gyumee", "박성철", "spring01");
        User user2 = new User("leegw", "이길원", "spring02");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));

    }


    @Test
    public void count() throws SQLException, ClassNotFoundException {
        User user1 = new User("gyumee", "박성철", "spring01");
        User user2 = new User("leegw", "이길원", "spring02");
        User user3 = new User("bumjin", "박범진", "spring03");
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }

    @Test
    public void getUserFailure() throws SQLException{
        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->{
            dao.get("unknown_id");
        });
    }



}
