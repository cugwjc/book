package com.cug.dao;

import com.cug.bean.User;

/**
 * @author warogychenger
 */
public interface UserDAO {
    int saveUser(User user);

    User getUserByUsername(String username);

    User getUserByUnAndPw(String username, String password);



}
