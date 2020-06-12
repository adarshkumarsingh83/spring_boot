package com.espark.adarsh.repository;

import com.espark.adarsh.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserBean getUserInfo(String username) {
        String sql = "SELECT u.username name, u.password pass, a.authority role FROM " +
                "users u INNER JOIN authorities a on u.username=a.username WHERE " +
                "u.enabled =1 and u.username = ?";

        UserBean userInfo = (UserBean) jdbcTemplate.queryForObject(sql, new Object[]{username},
                new RowMapper<UserBean>() {
                    public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserBean user = new UserBean();
                        user.setUsername(rs.getString("name"));
                        user.setPassword(rs.getString("pass"));
                        user.setRole(rs.getString("role"));
                        return user;
                    }
                });
        return userInfo;
    }
}