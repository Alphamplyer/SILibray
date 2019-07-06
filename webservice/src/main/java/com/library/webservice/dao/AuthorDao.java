package com.library.webservice.dao;

import com.library.webservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

    /**
     * Recherche des auteurs qui contient la chaine de caractère thingToSearch.
     * @param thingToSearch la chaine de caractère.
     * @return les auteurs résultant de la recherche.
     */
    List<Author> findAllByNameIgnoreCaseContaining(String thingToSearch);
}
