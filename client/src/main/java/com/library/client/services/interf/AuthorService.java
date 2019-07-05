package com.library.client.services.interf;

import com.library.client.model.Author;

import java.util.List;

public interface AuthorService {

    /**
     * Obtient la liste des auteurs.
     * @return la liste des auteurs.
     */
    List<Author> getAuthors();

    /**
     * Obtient un auteur.
     * @param author_id l'ID de l'auteur.
     * @return l'auteur.
     */
    Author getAuthor(int author_id);
}
