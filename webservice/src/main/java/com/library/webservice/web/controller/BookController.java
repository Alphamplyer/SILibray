package com.library.webservice.web.controller;

import com.library.webservice.dao.BookDao;
import com.library.webservice.model.Book;
import com.library.webservice.web.exceptions.NotFoundException;
import com.library.webservice.web.exceptions.UnableToAddException;
import com.library.webservice.web.exceptions.UnableToDeleteException;
import com.library.webservice.web.exceptions.UnableToModifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping(value = "/Books")
    public List<Book> getBooks() {
        List<Book> books = bookDao.findAll();

        if (books.isEmpty()) throw new NotFoundException("There are no books currently !");

        return books;
    }

    @GetMapping(value = "/Books/{id}")
    public Optional<Book> getBook(@PathVariable int id) {
        Optional<Book> book = bookDao.findById(id);

        if (!book.isPresent()) throw new NotFoundException("Book with the ID : '" + id + "' doesn't exits");

        return book;
    }

    @GetMapping(value = "/Books/ref/{bookReference}")
    public List<Book> getBook(@PathVariable String bookReference) {
        List<Book> books = bookDao.findBooksByBookReference(bookReference);

        if (books.isEmpty()) throw new NotFoundException("Author with the Referebce : '" + bookReference + "' doesn't exits");

        return books;
    }

    @GetMapping(value = "/Books/Author/{id}")
    public List<Book> getAuthorBooks(@PathVariable int id) {
        List<Book> books = bookDao.findBooksByAuthorId(id);

        if (books.isEmpty()) throw new NotFoundException("Author with the ID : '" + id + "' doesn't exits");

        return books;
    }

    @PostMapping(value = "/Books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookAdded = null;

        try {
            bookAdded = bookDao.save(book);
        }
        catch (Exception e) {
            throw new UnableToAddException("An error as occurred when trying to add a book");
        }

        if (bookAdded == null) throw new UnableToAddException("An error as occurred when trying to add a book");

        return new ResponseEntity<>(bookAdded, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Books")
    public ResponseEntity<Book> modifyBook(@RequestBody Book book) {
        Optional<Book> bookToUpdate = bookDao.findById(book.getId());

        if (!bookToUpdate.isPresent()) throw new NotFoundException("Book with the ID : '" + book.getId() + "' doesn't exits");

        Book bookModified = bookDao.save(book);

        if (bookModified == null) throw new UnableToModifyException("Book has been not modified!");

        return new ResponseEntity<>(bookModified, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Books/{id}")
    public void deleteBook(@PathVariable int id) {
        try {
            bookDao.deleteById(id);
        }
        catch (Exception e) {
            throw new UnableToDeleteException("Book has been not deleted du to internal error!");
        }
    }
}
