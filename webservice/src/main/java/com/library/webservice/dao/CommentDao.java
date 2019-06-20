package com.library.webservice.dao;

import com.library.webservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    @Query(value = "select c from comments c where c.book_reference = :book_reference", nativeQuery = true)
    List<Comment> selectAllFromBookReference(@Param("book_reference") String book_reference);
}
