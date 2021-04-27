package com.cug.dao.impl;

import com.cug.bean.OrderItem;
import com.cug.dao.BaseDAO;
import com.cug.dao.OrderItemDAO;

import java.util.List;

/**
 * @create 2020-12-23-19:17
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price totalPrice,order_id orderId from t_order_item where order_id = ?";
        return getTList(OrderItem.class, sql, orderId);
    }
}
