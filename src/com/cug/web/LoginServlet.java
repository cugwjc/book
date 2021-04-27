package com.cug.web;

import com.cug.bean.User;
import com.cug.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author warogychenger
 */
public class LoginServlet extends HttpServlet {
//    UserServiceImpl userService = new UserServiceImpl();
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        User loginUser = userService.loginUser(new User(0, username, password, null));
//        if (loginUser == null){
//            req.setAttribute("msg","用户名或密码错误，登录失败！");
//            req.setAttribute("username",username);
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
//        }else{
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
//        }
//    }
}
