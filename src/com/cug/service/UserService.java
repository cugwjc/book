package com.cug.service;

import com.cug.bean.User;

/**
 * @author warogychenger
 */
public interface UserService {

    void registUser(User user);

    User loginUser(User user);

    boolean existUsername(String username);
}
