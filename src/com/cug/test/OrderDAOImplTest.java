package com.cug.test;

import com.cug.bean.Order;
import com.cug.dao.OrderDAO;
import com.cug.dao.impl.OrderDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author warogychenger
 */
public class OrderDAOImplTest {
    private OrderDAO orderDAO = new OrderDAOImpl();
    @Test
    public void saveOrder() {
        Order order = new Order(System.currentTimeMillis() + "", new Date(), new BigDecimal(200), 0, 3);
        orderDAO.saveOrder(order);
    }

    @Test
    public void getOrders() {
        List<Order> orders = orderDAO.getOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void changeOrderStatus() {
        orderDAO.changeOrderStatus("1608723677191",1);
    }

    @Test
    public void getOrderByUserId() {
        List<Order> orderByUserId = orderDAO.getOrderByUserId(3);
        orderByUserId.forEach(System.out::println);
    }
}