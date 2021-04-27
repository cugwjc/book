package com.cug.web;

import com.cug.bean.Book;
import com.cug.bean.Cart;
import com.cug.bean.CartItem;
import com.cug.service.BookService;
import com.cug.service.impl.BookServiceImpl;
import com.cug.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author warogychenger
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        int id = WebUtils.parseInt(strId, 0);
        Book book = bookService.getBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("bookname", cartItem.getName());
        resp.sendRedirect(req.getHeader("referer"));

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        int id = WebUtils.parseInt(strId, 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        int id = WebUtils.parseInt(strId, 0);
        String strCount = req.getParameter("count");
        int count = WebUtils.parseInt(strCount, 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateCount(id, count);
        resp.sendRedirect(req.getHeader("referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        int id = WebUtils.parseInt(strId, 0);
        Book book = bookService.getBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("bookname", cartItem.getName());
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("count",cart.getTotalCount());
        map.put("bookname",cartItem.getName());
        String json = gson.toJson(map);
        resp.getWriter().write(json);

    }
}
