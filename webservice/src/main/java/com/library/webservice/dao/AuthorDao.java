package com.library.webservice.dao;

import com.library.webservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

    List<Author> findAllByNameIgnoreCaseContaining(String thingToSearch);
}
