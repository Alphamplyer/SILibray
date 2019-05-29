package com.library.client.services.impl;

import com.library.client.model.Book;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.BookService;
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

        Map<String, Book> bookMap = new HashMap<>();

        if (books != null) {
            for (Book book:books) {
                if (!bookMap.containsKey(book.getBookReference())) {
                    bookMap.put(book.getBookReference(), book);
                }
                else if (book.isAvailable()) {
                    Book available_book = bookMap.get(book.getBookReference());
                    available_book.setAvailable(true);
                    available_book.setId(book.getId());
                }
            }

            return new ArrayList<>(bookMap.values());
        } else {
            return null;
        }
    }
}
