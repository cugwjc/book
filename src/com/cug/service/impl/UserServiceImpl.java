package com.cug.service.impl;

import com.cug.bean.User;
import com.cug.dao.impl.UserDAOImpl;
import com.cug.service.UserService;

/**
 * @author warogychenger
 */
public class UserServiceImpl implements UserService{
    private UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public void registUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        User userByUnAndPw = userDAO.getUserByUnAndPw(user.getUsername(), user.getPassword());
        return userByUnAndPw;
    }

    @Override
    public boolean existUsername(String username) {
        User userByUsername = userDAO.getUserByUsername(username);
        if (userByUsername == null){
            return false;
        }else{
            return true;
        }
    }
}
