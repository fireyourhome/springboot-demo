package com.springboot.demo.dao;

import com.springboot.demo.entity.Book;

public interface  BookRepository {

    Book getByIsbn(String isbn);
}
