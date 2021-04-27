package com.cug.test;

import com.cug.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author warogychenger
 */
public class JdbcUtilsTest {
    @Test
    public void test(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
}
