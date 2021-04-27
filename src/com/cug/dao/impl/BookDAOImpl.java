package com.cug.dao.impl;

import com.cug.bean.Book;
import com.cug.dao.BaseDAO;
import com.cug.dao.BookDAO;

import java.util.List;

/**
 * @author warogychenger
 */
public class BookDAOImpl extends BaseDAO implements BookDAO{

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book getBookById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id = ?";
        return getT(Book.class, sql, id);
    }

    @Override
    public List<Book> getBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
        return getTList(Book.class, sql);
    }

    @Override
    public Integer getCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number)getValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> getBooksByPage(int begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book limit ?,?";
        return getTList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer getCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number)getValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> getBooksByPrice(Integer begin, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where price between ? and ? order by price limit ?,?";
        return getTList(Book.class, sql, min, max, begin, pageSize);
    }

    @Override
    public Integer getCountByName(String name) {
        String sql = "select count(*) from t_book where name like ?";
        String bname = "%" + name + "%";
        Number count = (Number)getValue(sql, bname);
        return count.intValue();
    }

    @Override
    public List<Book> getBooksByName(Integer begin, int pageSize, String name) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where name like ? order by price limit ?,?";
        String bname = "%" + name + "%";
        return getTList(Book.class, sql, bname, begin, pageSize);
    }
}
