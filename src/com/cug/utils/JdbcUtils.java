package com.cug.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author warogychenger
 */
public class JdbcUtils {
    private static DataSource source;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static{
        try {
            Properties pros = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null){
            try {
                conn = source.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

//    public static void close(Connection conn){
//        try {
//            if (conn != null){
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void commit(){
        Connection conn = conns.get();
        if (conn != null){
            try {
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollback(){
        Connection conn = conns.get();
        if (conn != null){
            try {
                conn.rollback();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
}
