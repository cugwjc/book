package com.cug.service;

import com.cug.bean.Book;
import com.cug.bean.Page;

import java.util.List;

/**
 * @author warogychenger
 */
public interface BookService {

    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book getBookById(Integer id);

    List<Book> getBooks();

    Page page(int pageNo, int pageSize);

    Page pageByPrice(int pageNo, int pageSize, int min, int max);

    Page pageByName(int pageNo, int pageSize, String bname);
}
