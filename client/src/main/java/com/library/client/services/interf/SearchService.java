package com.library.client.services.interf;

import com.library.client.model.Author;
import com.library.client.model.Book;

import java.util.List;

public interface SearchService {

    List<Book> searchBook(String title);

    List<Author> searchAuthor(String name);
}
