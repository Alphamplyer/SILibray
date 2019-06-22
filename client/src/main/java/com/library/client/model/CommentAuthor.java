package com.library.client.model;

public class CommentAuthor {

    private Comment comment;
    private User author;

    public CommentAuthor(Comment comment, User author) {
        this.comment = comment;
        this.author = author;
    }

    public Comment getComment() {
        return comment;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CommentAuthor{" +
            "comment=" + comment +
            ", author=" + author +
            '}';
    }
}
