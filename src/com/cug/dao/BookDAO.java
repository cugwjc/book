package com.cug.dao;

import com.cug.bean.Book;

import java.util.List;

/**
 * @author warogychenger
 */
public interface BookDAO {

    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book getBookById(Integer id);

    List<Book> getBooks();

    Integer getCount();

    List<Book> getBooksByPage(int begin, int pageSize);

    Integer getCountByPrice(int min, int max);

    List<Book> getBooksByPrice(Integer begin, int pageSize, int min, int max);

    Integer getCountByName(String name);

    List<Book> getBooksByName(Integer begin, int pageSize, String name);
}
