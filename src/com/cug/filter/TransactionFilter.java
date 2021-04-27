package com.cug.filter;

import com.cug.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author warogychenger
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commit();
        } catch (Exception e) {
            JdbcUtils.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
