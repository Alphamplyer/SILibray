package com.library.batch_checkloan.service.impl;

import com.library.batch_checkloan.model.Book;
import com.library.batch_checkloan.service.interf.BookService;
import org.springframework.web.client.RestTemplate;

public class BookServiceImpl extends AbstractService implements BookService {

    @Override
    public Book getBook(int book_id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(properties.getServiceURL() + "Books/" + book_id, Book.class);
    }
}
