package com.cug.web;

import com.cug.bean.Cart;
import com.cug.bean.Order;
import com.cug.bean.OrderItem;
import com.cug.bean.User;
import com.cug.service.OrderItemService;
import com.cug.service.OrderService;
import com.cug.service.impl.OrderItemServiceImpl;
import com.cug.service.impl.OrderServiceImpl;
import com.cug.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author warogychenger
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        int userId = user.getId();
        String orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void showUserOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        int userId = user.getId();
        List<Order> userOrders = orderService.getUserOrders(userId);
        req.getSession().setAttribute("userOrders", userOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || !user.getUsername().equals("admin")){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        List<Order> allOrders = orderService.getallOrders();
        req.getSession().setAttribute("allOrders", allOrders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("referer"));
    }

    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderItemService.showOrderDetail(orderId);
        Order orderByOrderId = orderService.getOrderByOrderId(orderId);
        req.getSession().setAttribute("orderItem", orderItems);
        req.getSession().setAttribute("orderByOrderId", orderByOrderId);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    protected void signOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.signOrder(orderId);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
