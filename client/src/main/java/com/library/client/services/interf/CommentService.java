package com.library.client.services.interf;

import com.library.client.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentFromBookRef(String book_ref);

    Comment addComment(int author_id, String content, int notation, String book_reference);

    int getAverageNotation(List<Comment> comments);
}
