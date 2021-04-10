package com.company.templateMethod;

import com.company.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//템플릿 메소드 패턴
//변하지 않는 부분은 슈퍼클래스 변하는 부분은 추상메소드로 정의해서 서브클래스에서 오버라이드
public class UserDaoDeleteAll extends UserDao {
    protected PreparedStatement makeStatement(Connection c) throws SQLException{
        PreparedStatement ps = c.prepareStatement("delete from users");
        return ps;
    }
}
