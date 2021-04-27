package com.cug.dao.impl;

import com.cug.bean.Order;
import com.cug.dao.BaseDAO;
import com.cug.dao.OrderDAO;

import java.util.List;

/**
 * @create 2020-12-23-19:16
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> getOrders() {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id UserId from t_order";
        return getTList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set status = ? where order_id =?";
        update(sql, status, orderId);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id UserId from t_order where user_id =?";
        return getTList(Order.class, sql, userId);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id UserId from t_order where order_id =?";
        return getT(Order.class, sql, orderId);
    }
}
