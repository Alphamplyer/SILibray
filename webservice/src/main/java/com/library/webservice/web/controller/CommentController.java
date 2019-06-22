package com.library.webservice.web.controller;

import com.library.webservice.dao.CommentDao;
import com.library.webservice.model.Comment;
import com.library.webservice.web.exceptions.NotFoundException;
import com.library.webservice.web.exceptions.UnableToAddException;
import com.library.webservice.web.exceptions.UnableToDeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    private CommentDao commentDao;

    @Autowired
    public CommentController (CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @GetMapping("/Comments/{book_ref}")
    public ResponseEntity<List<Comment>> getCommentFromBoofRef(@PathVariable(name = "book_ref") String book_ref) {

        List<Comment> comments = null;

        try {
            comments = commentDao.selectAllFromBookReference(book_ref);
        } catch (Exception e) {
            throw new NotFoundException("There are no comment from this book !");
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/Comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {

        Comment created_comment;

        comment.setCreationTime(new Timestamp(new Date().getTime()));

        try {
            created_comment = commentDao.save(comment);
        } catch (Exception e) {
            throw new UnableToAddException("An error as occurred when trying to add a comment");
        }

        return new ResponseEntity<>(created_comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/Comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "comment_id") int id) {
        try {
            commentDao.deleteById(id);
        } catch (Exception e) {
            throw new UnableToDeleteException("An error as occurred when trying to delete a comment");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
