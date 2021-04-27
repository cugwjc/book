package com.cug.dao;

import com.cug.bean.Order;

import java.util.List;

/**
 * @author warogychenger
 */
public interface OrderDAO {

    void saveOrder(Order order);

    List<Order> getOrders();

    void changeOrderStatus(String orderId, Integer status);

    List<Order> getOrderByUserId(Integer userId);

    Order getOrderByOrderId(String orderId);
}
