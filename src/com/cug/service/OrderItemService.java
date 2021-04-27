package com.cug.service;

import com.cug.bean.OrderItem;

import java.util.List;

/**
 * @author warogychenger
 */
public interface OrderItemService {

    List<OrderItem> showOrderDetail(String orderId);
}
