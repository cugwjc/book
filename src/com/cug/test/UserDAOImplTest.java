package com.cug.test;


import com.cug.bean.Book;
import com.cug.bean.User;
import com.cug.dao.impl.BookDAOImpl;
import com.cug.dao.impl.UserDAOImpl;
import org.junit.Test;

import java.util.List;


/**
 * @author warogychenger
 */

public class UserDAOImplTest {
    UserDAOImpl udi = new UserDAOImpl();
    @Test
    public void saveUser() {
        User user = new User(0,"wjc9527","huiyi521","962092442@qq.com");
        int i = udi.saveUser(user);
        if(i > 0){
            System.out.println("添加成功！");
        }else{
            System.out.println("添加失败！");
        }
    }

    @Test
    public void getUserByUsername() {
        User user = new User(0,"wjc95278","huiyi521","962092442@qq.com");
        User userByUsername = udi.getUserByUsername(user.getUsername());
        if (userByUsername == null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已被占用！");
        }
    }

    @Test
    public void getUserByUnAndPw() {
        User user = new User(0,"wjc9527","huiyi5211","962092442@qq.com");
        User userByUnAndPw = udi.getUserByUnAndPw(user.getUsername(),user.getPassword());
        if (userByUnAndPw == null){
            System.out.println("用户名或密码错误，登陆失败！");
        }else{
            System.out.println("登录成功！");
        }
    }
    @Test
    public void getCount() {
        BookDAOImpl bookDAO = new BookDAOImpl();
        System.out.println(bookDAO.getCount());
    }

    @Test
    public void getBooksByPage() {
        BookDAOImpl bookDAO = new BookDAOImpl();
        List<Book> booksByPage = bookDAO.getBooksByPage(0, 4);
        booksByPage.forEach(System.out::println);
    }
}
