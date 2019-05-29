package com.library.webservice.dao;

import com.library.webservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorId(int authorId);

    List<Book> findBooksByBookReference(String bookReference);

    List<Book> findBooksByNameContaining(String thingToSearch);

    List<Book> findAllByNameIgnoreCaseContaining(String thingToSearch);
}
