package com.library.client.services.interf;

import com.library.client.model.Book;

import java.util.List;

public interface BookService {

    Book getBook(int book_id);

    List<Book> getBooks();

    List<Book> getAuthorBooks(int author_id);

    List<Book> getBooksByRef(String book_reference);

    boolean unavailable(Book book);
}
