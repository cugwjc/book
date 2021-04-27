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
public class RegistServlet extends HttpServlet{
//    private UserServiceImpl userService = new UserServiceImpl();
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
//        if ("abcde".equals(code)){
//            if (userService.existUsername(username)){
//                req.setAttribute("msg", "用户名已存在！");
//                req.setAttribute("username", username);
//                req.setAttribute("email", email);
//                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//            }else{
//                userService.registUser(new User(0, username, password, email));
//                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
//            }
//
//        }else{
//            req.setAttribute("msg", "验证码错误！");
//            req.setAttribute("username", username);
//            req.setAttribute("email", email);
//            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//        }
//    }
}
