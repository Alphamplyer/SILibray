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

/**
 * Controller qui à partir d'un requete renvoit des informations, effectu des actions relative aux livres
 */
@RestController
public class BookController {

    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * Retourne tous les livres en base
     * @return la liste de tous les livres
     */
    @GetMapping(value = "/Books")
    public List<Book> getBooks() {
        List<Book> books = bookDao.selectAll();

        if (books.isEmpty()) throw new NotFoundException("There are no books currently !");

        return books;
    }

    /**
     * Retourne un livre à partir de son id
     * @param id id du livre
     * @return le livre
     */
    @GetMapping(value = "/Books/{id}")
    public Optional<Book> getBook(@PathVariable int id) {
        Optional<Book> book = bookDao.findById(id);

        if (!book.isPresent()) throw new NotFoundException("Book with the ID : '" + id + "' doesn't exits");

        return book;
    }

    /**
     * Retoure la liste de tous les livres de même référence.
     * @param bookReference la référence du livre
     * @return la liste de tous les livres de même référence
     */
    @GetMapping(value = "/Books/ref/{bookReference}")
    public List<Book> getBook(@PathVariable String bookReference) {
        List<Book> books = bookDao.findBooksByBookReference(bookReference);

        if (books.isEmpty()) throw new NotFoundException("Author with the Referebce : '" + bookReference + "' doesn't exits");

        return books;
    }

    /**
     * Retourne tous les livres d'un auteurs
     * @param id ID de l'auteur
     * @return la liste des livres de l'auteur
     */
    @GetMapping(value = "/Books/Author/{id}")
    public List<Book> getAuthorBooks(@PathVariable int id) {
        List<Book> books = bookDao.findBooksByAuthorId(id);

        if (books.isEmpty()) throw new NotFoundException("Author with the ID : '" + id + "' doesn't exits");

        return books;
    }

    /**
     * Ajoute une livre à la base
     * @param book le livre à ajouter
     * @return le livre ajouté
     */
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

    /**
     * Modifi un livre dans la base
     * @param book le livre à modifier et ses informations
     * @return le livre modifié
     */
    @PutMapping(value = "/Books")
    public ResponseEntity<Book> modifyBook(@RequestBody Book book) {
        Optional<Book> bookToUpdate = bookDao.findById(book.getId());

        if (!bookToUpdate.isPresent()) throw new NotFoundException("Book with the ID : '" + book.getId() + "' doesn't exits");

        Book bookModified = bookDao.save(book);

        if (bookModified == null) throw new UnableToModifyException("Book has been not modified!");

        return new ResponseEntity<>(bookModified, HttpStatus.OK);
    }

    /**
     * Supprime un livre de la base
     * @param id ID du livre à supprimer
     */
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
