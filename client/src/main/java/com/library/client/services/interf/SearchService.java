package com.library.client.services.interf;

import com.library.client.model.Author;
import com.library.client.model.Book;

import java.util.List;

public interface SearchService {

    /**
     * recherche la liste des livres à partir d'une chaine de caractères
     * @param title la chaine de caractère
     * @return le résultat de la recherche
     */
    List<Book> searchBook(String title);

    /**
     * recherche la liste des auteurs à partir d'une chaine de caractères
     * @param name la chaine de caractères
     * @return le résultat de la recherche
     */
    List<Author> searchAuthor(String name);
}
