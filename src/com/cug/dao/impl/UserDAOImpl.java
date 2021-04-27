package com.cug.dao.impl;

import com.cug.bean.User;
import com.cug.dao.BaseDAO;
import com.cug.dao.UserDAO;

/**
 * @create 2020-12-18-9:23
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int saveUser = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return saveUser;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        User t = getT(User.class, sql, username);
        return t;
    }

    @Override
    public User getUserByUnAndPw(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        User t = getT(User.class, sql, username, password);
        return t;
    }
}
