package com.cug.web;

import com.cug.bean.Page;
import com.cug.service.BookService;
import com.cug.service.impl.BookServiceImpl;
import com.cug.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author warogychenger
 */
public class ClientBookServlet extends BaseServlet{
    private BookServiceImpl bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strPageNo = req.getParameter("pageNo");
        String strPageSize = req.getParameter("pageSize");
        int pageNo = WebUtils.parseInt(strPageNo, 1);
        int pageSize = WebUtils.parseInt(strPageSize, Page.PAGE_SIZE);
        Page page = bookService.page(pageNo, pageSize);
        page.setUrl("client/clientBookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strMax = req.getParameter("max");
        String strMin = req.getParameter("min");
        int max = WebUtils.parseInt(strMax,Integer.MAX_VALUE);
        int min = WebUtils.parseInt(strMin,0);
        String strPageNo = req.getParameter("pageNo");
        String strPageSize = req.getParameter("pageSize");
        int pageNo = WebUtils.parseInt(strPageNo, 1);
        int pageSize = WebUtils.parseInt(strPageSize, Page.PAGE_SIZE);
        Page page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder url = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if (strMin != null ){
            url.append("&min=").append(strMin);
        }
        if (strMax != null ){
            url.append("&max=").append(strMax);
        }
        page.setUrl(url.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bname = req.getParameter("bname");
        String strPageNo = req.getParameter("pageNo");
        String strPageSize = req.getParameter("pageSize");
        int pageNo = WebUtils.parseInt(strPageNo, 1);
        int pageSize = WebUtils.parseInt(strPageSize, Page.PAGE_SIZE);
        Page page = bookService.pageByName(pageNo, pageSize, bname);
        StringBuilder url = new StringBuilder("client/clientBookServlet?action=pageByName");
        if (bname != null ) {
            url.append("&bname=").append(bname);
        }
        page.setUrl(url.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
