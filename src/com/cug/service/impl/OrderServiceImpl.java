package com.cug.service.impl;

import com.cug.bean.*;
import com.cug.dao.BookDAO;
import com.cug.dao.OrderDAO;
import com.cug.dao.OrderItemDAO;
import com.cug.dao.impl.BookDAOImpl;
import com.cug.dao.impl.OrderDAOImpl;
import com.cug.dao.impl.OrderItemDAOImpl;
import com.cug.service.OrderService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author warogychenger
 */
public class OrderServiceImpl implements OrderService{
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDAO.saveOrder(order);
//        int i = 12 / 0;
        Map<Integer, CartItem> items = cart.getItems();
        Collection<CartItem> values = items.values();
        for (CartItem cartItem : values) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(),orderId);
            orderItemDAO.saveOrderItem(orderItem);
            Book book = bookDAO.getBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDAO.updateBook(book);
        }

        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> getallOrders() {
        List<Order> orders = orderDAO.getOrders();
        return orders;
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        return orderDAO.getOrderByUserId(userId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId, 1);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderDAO.getOrderByOrderId(orderId);
    }

    @Override
    public void signOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId, 2);
    }
}
