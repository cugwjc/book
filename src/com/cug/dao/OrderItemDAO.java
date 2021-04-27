package com.cug.dao;

import com.cug.bean.Order;
import com.cug.bean.OrderItem;

import java.util.List;

/**
 * @author warogychenger
 */
public interface OrderItemDAO {

    void saveOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemByOrderId(String orderId);


}
