package com.cug.service.impl;

import com.cug.bean.OrderItem;
import com.cug.dao.OrderItemDAO;
import com.cug.dao.impl.OrderItemDAOImpl;
import com.cug.service.OrderItemService;

import java.util.List;

/**
 * @author warogychenger

 */
public class OrderItemServiceImpl implements OrderItemService {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDAO.getOrderItemByOrderId(orderId);
        return orderItems;
    }
}
