package com.cug.test;

import com.cug.bean.User;
import com.cug.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author warogychenger
 */
public class UserServiceImplTest {
    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void registUser() {
        User user = new User(0,"jcw123","jcw123","jcw123@qq.com");
        userService.registUser(user);
    }

    @Test
    public void loginUser() {
        User user = new User(0,"jcw123","jcw123","jcw123@qq.com");
        User loginUser = userService.loginUser(user);
        System.out.println(loginUser);
    }

    @Test
    public void existUsername() {
        boolean existUsername = userService.existUsername("jcw123");
        System.out.println(existUsername);
    }
}