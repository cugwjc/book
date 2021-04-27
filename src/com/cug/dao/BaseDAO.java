package com.cug.dao;

import com.cug.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.naming.event.ObjectChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author warogychenger
 */
public abstract class BaseDAO {
    private QueryRunner runner = new QueryRunner();

    public int update(String sql, Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            int update = runner.update(conn, sql, args);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T getT(Class<T> type, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            T query = runner.query(conn, sql, new BeanHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getTList(Class<T> type, String sql, Object ... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            List<T> query = runner.query(conn, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object getValue(String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return runner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
