package com.company.dao;

import com.company.strategy.StatementStrategy;
import com.company.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);

        this.dataSource = dataSource;
    }

    private JdbcContext jdbcContext;

    public void deleteAll() throws SQLException {
      this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
          @Override
          public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
              return c.prepareStatement("delete from users");
          }
      });
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    private PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement ps;
        ps = c.prepareStatement("delete from users");
        return ps;
    }

    public void add(final User user) throws ClassNotFoundException, SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
                                             public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                                                 PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
                                                 ps.setString(1, user.getId());
                                                 ps.setString(2, user.getName());
                                                 ps.setString(3, user.getPassword());
                                                 return ps;
                                             }
                                         }
        );
    }

    public User get(String id) throws SQLException {
        Connection c = this.dataSource.getConnection();
        PreparedStatement ps = c
                .prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

}
