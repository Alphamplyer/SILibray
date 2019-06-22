package com.library.webservice.dao;

import com.library.webservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    @Query("select b from Book b order by b.releaseDate desc")
    List<Book> selectAll();

    List<Book> findBooksByAuthorId(int authorId);

    @Query("select b from Book b where b.bookReference = :bookReference")
    List<Book> findBooksByBookReference(@Param("bookReference") String bookReference);

    List<Book> findBooksByNameContaining(String thingToSearch);

    List<Book> findAllByNameIgnoreCaseContaining(String thingToSearch);
}
