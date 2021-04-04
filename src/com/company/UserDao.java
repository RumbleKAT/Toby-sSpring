package com.company;

import java.sql.*;

public class UserDao {

    String server = "localhost"; // MySQL 서버 주소
    String database = "toby"; // MySQL DATABASE 이름
    String user_name = "root"; //  MySQL 서버 아이디
    String password = ""; // MySQL 서버 비밀번호

    public void add(User user) throws ClassNotFoundException, SQLException{
        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        Connection c = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true", user_name, password);
        PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values (?,?,?)");

        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        Connection c = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true", user_name, password);
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
