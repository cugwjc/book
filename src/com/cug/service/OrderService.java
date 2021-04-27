package com.cug.service;

import com.cug.bean.Cart;
import com.cug.bean.Order;

import java.util.List;

/**
 * @author warogychenger
 */
public interface OrderService {

    String createOrder(Cart cart, Integer userId);

    List<Order> getallOrders();

    List<Order> getUserOrders(Integer userId);

    void sendOrder(String orderId);

    Order getOrderByOrderId(String orderId);

    void signOrder(String orderId);
}
