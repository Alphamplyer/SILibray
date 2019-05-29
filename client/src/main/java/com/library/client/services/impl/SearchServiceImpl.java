package com.library.client.services.impl;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.SearchService;

import java.util.*;

public class SearchServiceImpl extends AbstractService implements SearchService {

    @Override
    public List<Book> searchBook(String title) {
        Book[] books = restTemplate.postForObject(properties.getServiceURL() + "Search/Book", title, Book[].class);

        return getBookAssociatedByReferences(books);
    }

    @Override
    public List<Author> searchAuthor(String name) {
        Author[] authors = restTemplate.postForObject(properties.getServiceURL() + "Search/Author", name, Author[].class);

        if (authors == null)
            return null;
        else
            return Arrays.asList(authors);
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
