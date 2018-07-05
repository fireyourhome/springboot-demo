package com.springboot.demo.imp;

import com.springboot.demo.dao.BookRepository;
import com.springboot.demo.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@Component
@EnableCaching   //在程序的入口中加入@ EnableCaching开启缓存技术：
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable(value = "books",key = "'get'+#isbn") //在需要缓存的地方加入@Cacheable注解 ,这个方法就开启了缓存策略，当缓存有这个数据的时候，会直接返回数据，不会等待去查询数据库。
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService() { //线程延迟，模仿查询数据库
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
