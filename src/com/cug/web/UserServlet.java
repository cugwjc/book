package com.cug.web;

import com.cug.bean.User;
import com.cug.service.UserService;
import com.cug.service.impl.UserServiceImpl;
import com.cug.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author warogychenger
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = WebUtils.copyParamToBean(parameterMap, new User());

        User loginUser = userService.loginUser(user);
        if (loginUser == null){
            req.setAttribute("msg","用户名或密码错误，登录失败！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else{
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object token = req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if (token != null && token.equals(code)){
            if (userService.existUsername(username)){
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else{
                Map<String, String[]> parameterMap = req.getParameterMap();
                User user = WebUtils.copyParamToBean(parameterMap, new User());
                userService.registUser(user);
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        }else{
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Gson gson = new Gson();
        Map<String,Boolean> map = new HashMap<>();
        map.put("existUsername",existUsername);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}
