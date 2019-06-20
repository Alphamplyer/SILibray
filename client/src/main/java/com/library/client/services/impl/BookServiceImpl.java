package com.library.client.services.impl;

import com.library.client.model.Book;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.BookService;
import com.library.client.utils.ExtendedList;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class BookServiceImpl extends AbstractService implements BookService {

    @Override
    public Book getBook(int book_id) {
        return restTemplate.getForObject(properties.getServiceURL() + "Books/" + book_id, Book.class);
    }

    @Override
    public List<Book> getBooks() {
        Book[] books = restTemplate.getForObject(properties.getServiceURL() + "Books", Book[].class);

        return getBookAssociatedByReferences(books);
    }

    @Override
    public List<Book> getBooksByRef(String book_reference) {
        Book[] books = restTemplate.getForObject(properties.getServiceURL() + "Books/ref/" + book_reference, Book[].class);

        if (books != null)
            return Arrays.asList(books);

        return null;
    }

    @Override
    public List<Book> getAuthorBooks(int author_id) {
        Book[] books = restTemplate.getForObject(properties.getServiceURL() + "Books/Author/" + author_id, Book[].class);

        return getBookAssociatedByReferences(books);
    }

    @Override
    public boolean unavailable(Book book) {
        book.setAvailable(false);
        restTemplate.put(properties.getServiceURL() + "Books", book);
        return true;
    }

    private List<Book> getBookAssociatedByReferences(Book[] books) {

        ExtendedList<String, Book> ref_books = new ExtendedList<>();

        if (books != null) {
            for (int i = 0; i < books.length; i++) {
                if (!ref_books.getKeys().contains(books[i].getBookReference())) {
                    ref_books.add(books[i].getBookReference(), books[i]);
                }
                else if (books[i].isAvailable()) {
                    Book available_book = ref_books.getValue(books[i].getBookReference());
                    available_book.setAvailable(true);
                    available_book.setId(books[i].getId());
                }
            }

            return ref_books.getValues();
        } else {
            return null;
        }
    }
}
