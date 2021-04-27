package com.cug.test;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleDropDbLinkStatement;
import com.cug.bean.OrderItem;
import com.cug.dao.OrderItemDAO;
import com.cug.dao.impl.OrderItemDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author warogychenger
 */
public class OrderItemDAOImplTest {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "藏着真话", 2, new BigDecimal(100), new BigDecimal(200), "1608723677191");
        orderItemDAO.saveOrderItem(orderItem);
    }

    @Test
    public void getOrderItemByOrderId() {
        List<OrderItem> orderItemByOrderId = orderItemDAO.getOrderItemByOrderId("1608723677191");
    }
}