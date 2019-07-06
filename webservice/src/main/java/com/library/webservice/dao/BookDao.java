package com.library.webservice.dao;

import com.library.webservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    /**
     * Obtient de la base de données, une liste de livres ordonné par leur date de parution.
     * @return la liste de livres ordonné par leur date de parution.
     */
    @Query("select b from Book b order by b.releaseDate desc")
    List<Book> selectAll();

    /**
     * Obtient de la base de données, la liste des livres écris par un auteur, trouvé par son ID.
     * @param authorId ID de l'auteur.
     * @return les livres de l'auteur.
     */
    List<Book> findBooksByAuthorId(int authorId);

    /**
     * Obtient de la base de données, la liste des livres de même référence.
     * @param bookReference la référence des livres.
     * @return la liste des livres de même référence.
     */
    @Query("select b from Book b where b.bookReference = :bookReference")
    List<Book> findBooksByBookReference(@Param("bookReference") String bookReference);

    /**
     * Recherche et obtient de la base de données, tous les livre dont leur nom contient la chaine de caratère précisé.
     * @param thingToSearch la chaine de caratère
     * @return tous les livre dont leur nom contient la chaine de caratère précisé.
     */
    List<Book> findAllByNameIgnoreCaseContaining(String thingToSearch);
}
