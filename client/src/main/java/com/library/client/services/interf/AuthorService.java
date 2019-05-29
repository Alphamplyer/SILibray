package com.library.client.services.interf;

import com.library.client.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthors();

    Author getAuthor(int author_id);
}
