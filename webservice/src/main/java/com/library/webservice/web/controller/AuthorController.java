package com.library.webservice.web.controller;

import com.library.webservice.dao.AuthorDao;
import com.library.webservice.model.Author;
import com.library.webservice.web.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private AuthorDao authorDao;

    @Autowired
    public AuthorController (AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping(value = "/Authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorDao.findAll();

        if (authors.isEmpty()) throw new NotFoundException("There are no authors currently !");

        return authors;
    }

    @GetMapping(value = "/Authors/{id}")
    public Optional<Author> getAuthor(@PathVariable int id) {
        Optional<Author> author = authorDao.findById(id);

        if (!author.isPresent()) throw new NotFoundException("Book with the ID : '" + id + "' doesn't exits");

        return author;
    }

    @PostMapping(value = "/Authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author authorAdded = null;

        try {
            authorAdded = authorDao.save(author);
        }
        catch (Exception e) {
            throw new UnableToAddException("An error as occurred when trying to add an author");
        }

        if (authorAdded == null) throw new UnableToAddException("An error as occurred when trying to add an author");

        return new ResponseEntity<>(authorAdded, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Authors")
    public ResponseEntity<Author> modifyAuthor(@RequestBody Author author) {
        Optional<Author> authorToUpdate = authorDao.findById(author.getId());

        if (!authorToUpdate.isPresent()) throw new NotFoundException("Author with the ID : '" + author.getId() + "' doesn't exits");

        Author authorModified = authorDao.save(author);

        if (authorModified == null) throw new UnableToModifyException("Author has been not modified!");

        return new ResponseEntity<>(authorModified, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Authors/{id}")
    public void deleteAuthor(@PathVariable int id) {
        try {
            authorDao.deleteById(id);
        }
        catch (Exception e) {
            throw new UnableToDeleteException("Author has been not deleted du to internal error!");
        }
    }
}
