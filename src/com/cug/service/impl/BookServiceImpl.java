package com.cug.service.impl;

import com.cug.bean.Book;
import com.cug.bean.Page;
import com.cug.dao.BookDAO;
import com.cug.dao.impl.BookDAOImpl;
import com.cug.service.BookService;

import java.util.List;

/**
 * @author warogychenger

 */
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    public Page page(int pageNo, int pageSize) {
        Page page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDAO.getCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        Integer begin = (pageNo - 1) * pageSize;
        List<Book> list = bookDAO.getBooksByPage(begin, pageSize);
        page.setItems(list);

        return page;
    }

    @Override
    public Page pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDAO.getCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        Integer begin = (pageNo - 1) * pageSize;
        List<Book> list = bookDAO.getBooksByPrice(begin, pageSize, min, max);
        page.setItems(list);

        return page;
    }

    @Override
    public Page pageByName(int pageNo, int pageSize, String bname) {
        Page page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDAO.getCountByName(bname);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if ((pageTotalCount % pageSize) > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        Integer begin = (pageNo - 1) * pageSize;
        List<Book> list = bookDAO.getBooksByName(begin, pageSize, bname);
        page.setItems(list);
        return page;
    }


}
