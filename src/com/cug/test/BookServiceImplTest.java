package com.cug.test;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.cug.bean.Book;
import com.cug.bean.Page;
import com.cug.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author warogychenger
 */
public class BookServiceImplTest {
    BookServiceImpl bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        Book book = new Book(null, "他只是曾经", "伤害", new BigDecimal(999), 999, 1, null);
        bookService.addBook(book);
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        Book book = new Book(21, "他只是曾经", "沉默", new BigDecimal(999), 999, 1, null);
        bookService.updateBook(book);
    }

    @Test
    public void getBookById() {
        System.out.println(bookService.getBookById(21));
    }

    @Test
    public void getBooks() {
        bookService.getBooks().forEach(System.out::println);
    }

    @Test
    public void page(){
        Page page = bookService.page(1, 4);
        System.out.println(page);
    }
    @Test
    public void pageByPrice(){
        Page page = bookService.pageByPrice(1, 4, 10, 50);
        System.out.println(page);
    }
}