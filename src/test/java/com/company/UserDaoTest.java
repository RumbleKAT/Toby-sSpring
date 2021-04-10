package com.company;

import com.company.dao.UserDao;
import com.company.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/applicationContext.xml")
@SpringBootTest(classes = ApplicationContext.class)
class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void loadDao() throws SQLException, ClassNotFoundException {
        dao = context.getBean("userDao", UserDao.class);
        this.user1 = new User("gyumee","박성철","springno1");
        this.user2 = new User("leegw", "이길원", "spring02");
        this.user3 = new User("bumjin", "박범진", "spring03");

    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(this.user1);
        dao.add(this.user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(this.user1.getId());
        assertThat(userget1.getName(), is(this.user1.getName()));
        assertThat(userget1.getPassword(), is(this.user1.getPassword()));

        User userget2 = dao.get(this.user2.getId());
        assertThat(userget2.getName(), is(this.user2.getName()));
        assertThat(userget2.getPassword(), is(this.user2.getPassword()));

    }


    @Test
    public void count() throws SQLException, ClassNotFoundException {
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
