package com.library.webservice.web.controller;

import com.library.webservice.dao.AuthorDao;
import com.library.webservice.dao.BookDao;
import com.library.webservice.model.Author;
import com.library.webservice.model.Book;
import com.library.webservice.web.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private BookDao bookDao;
    private AuthorDao authorDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @PostMapping(value= "/Search/Book")
    public ResponseEntity<List<Book>> searchBook (@RequestBody String thingToSearch) {

        thingToSearch = thingToSearch.toLowerCase();

        List<Book> books = bookDao.findAllByNameIgnoreCaseContaining(thingToSearch);

        if (books.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(value= "/Search/Author")
    public ResponseEntity<List<Author>> searchAuthor (@RequestBody String thingToSearch) {

        thingToSearch = thingToSearch.toLowerCase();

        List<Author> authors = authorDao.findAllByNameIgnoreCaseContaining(thingToSearch);

        if (authors.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

}
