package com.library.client.services.impl;

import com.library.client.model.Comment;
import com.library.client.model.Loan;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.CommentService;

import java.util.Arrays;
import java.util.List;

public class CommentServiceImpl extends AbstractService implements CommentService {

    @Override
    public List<Comment> getCommentFromBookRef(String book_ref) {
        Comment[] comments = restTemplate.getForObject(properties.getServiceURL() + "Comments/" + book_ref, Comment[].class);
        return Arrays.asList(comments);
    }

    @Override
    public Comment addComment(int author_id, String content, int notation, String book_reference) {

        Comment comment = new Comment(author_id, content, notation, book_reference);

        return restTemplate.postForObject(properties.getServiceURL() + "Comments", comment, Comment.class);
    }

    @Override
    public int getAverageNotation(List<Comment> comments) {

        if (comments.size() == 0)
            return 0;

        int average_notation = 0;

        for (Comment comment : comments) {
            average_notation += comment.getNotation();
        }

        average_notation /= comments.size();

        return average_notation;
    }
}
