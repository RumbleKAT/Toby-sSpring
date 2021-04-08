package com.company;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String [] args) throws ClassNotFoundException, SQLException{
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao",UserDao.class);

//        UserDao dao3 = context.getBean("userDao",UserDao.class);
//        UserDao dao4 = context.getBean("userD®ao",UserDao.class);
//
//        System.out.println(dao3);
//        System.out.println(dao4);
        User user = new User();
        user.setId("5");
        user.setName("song");
        user.setPassword("0000");

        dao.add(user);

//        CountingConnectionMaker countingConnectionMaker = context.getBean("connectionMaker",CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + countingConnectionMaker.getCounter());


        //DB 생성 방법이나 전략에 대해서 커플링이 낮아짐
        //인터페이스를 도입하고 클라이언트의 도움을 얻는 방법은 상속을 사용하는 경우보다 유연하다.

        /*
        * 전략 패턴은 자신의 기능 맥락에서 필요에 따라,변경이 필요한 알고리즘을 일터페이스를 통해 통째로 외부로 분리
        * 이를 구현한 구체적인 알고맂므 클래스를 필요에 따라 바꿔서 사용할 수 있게 하는 디자인 패턴
        * UserDao는 전략 패턴의 컨텍스트에 해당
        * */

    }
}
