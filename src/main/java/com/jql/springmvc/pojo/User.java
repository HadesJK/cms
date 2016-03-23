package com.jql.springmvc.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * User
 *
 * @author 一饭
 * @date 15/6/29 下午7:26
 */
public class User {
    private String username;
    private String password;
    private List<Book> books;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        books = new ArrayList<Book>(8);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
