package com.library.client.services.interf;

import com.library.client.model.Book;

import java.util.List;

public interface BookService {

    /**
     * Obtient du livre.
     * @param author_id l'ID du livre.
     * @return le livre.
     */
    Book getBook(int book_id);

    /**
     * Obtient la liste des livres.
     * @return la liste des livres.
     */
    List<Book> getBooks();

    /**
     * Obtient la liste des livres d'un auteur.
     * @param author_id l'ID de l'auteur.
     * @return les livres de l'auteur.
     */
    List<Book> getAuthorBooks(int author_id);

    /**
     * Obtient la liste des livres d'une même référence.
     * @param book_reference la référence.
     * @return la liste des livres d'une même référence.
     */
    List<Book> getBooksByRef(String book_reference);

    /**
     * Défini un livre comme indisponible.
     * @param book le livre.
     * @return true si pas d'erreur.
     */
    boolean unavailable(Book book);
}
