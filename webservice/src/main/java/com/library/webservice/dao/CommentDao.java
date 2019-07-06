package com.library.webservice.dao;

import com.library.webservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    /**
     * Obtient de la base de données tous les commentaires d'un livre grâce à sa référence.
     * @param book_reference la référence du livre.
     * @return la liste de commentaire.
     */
    @Query(value = "select * from comments where book_reference = :book_reference", nativeQuery = true)
    List<Comment> selectAllFromBookReference(@Param("book_reference") String book_reference);
}
