package com.library.client.services.interf;

import com.library.client.model.Comment;

import java.util.List;

public interface CommentService {

    /**
     * Obtien la liste des commentaire à partir de la référence d'un livre
     * @param book_ref la référence d'un livre
     * @return la liste des commentaire
     */
    List<Comment> getCommentFromBookRef(String book_ref);

    /**
     * Ajoute un commentaire à la BD
     * @param author_id auteur du commentaire
     * @param content contenu du commentaire
     * @param notation notation du commentaire
     * @param book_reference référence du livre comenté
     * @return le commentaire ajouté à la BD
     */
    Comment addComment(int author_id, String content, int notation, String book_reference);

    /**
     * Obtient la notation moyenne à partir la liste des commentaires fournit
     * @param comments les commentaires
     * @return la notation
     */
    int getAverageNotation(List<Comment> comments);
}
