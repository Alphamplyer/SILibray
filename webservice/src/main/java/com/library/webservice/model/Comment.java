package com.library.webservice.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comments_pk")
    private int id;

    @Column(name = "author_id", nullable = false)
    private int authorId;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @NotEmpty
    @Column(name = "content", nullable = false)
    private String content;

    @Min(value = 0)
    @Max(value = 5)
    @Column(name = "notation", nullable = false)
    private int notation;

    @Column(name = "book_reference", nullable = false)
    private String bookReference;


    public Comment(int authorId, String content, int notation, String bookReference) {
        this.authorId = authorId;
        this.content = content;
        this.notation = notation;
        this.bookReference = bookReference;
    }
    public Comment(int id, int authorId, Timestamp creationTime, String content, int notation, String bookReference) {
        this.id = id;
        this.authorId = authorId;
        this.creationTime = creationTime;
        this.content = content;
        this.notation = notation;
        this.bookReference = bookReference;
    }


    public int getId() {
        return id;
    }
    public int getAuthorId() {
        return authorId;
    }
    public Timestamp getCreationTime() {
        return creationTime;
    }
    public String getContent() {
        return content;
    }
    public int getNotation() {
        return notation;
    }
    public String getBookReference() {
        return bookReference;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setNotation(int notation) {
        this.notation = notation;
    }
    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id=" + id +
            ", authorId=" + authorId +
            ", creationTime=" + creationTime +
            ", content='" + content + '\'' +
            ", notation=" + notation +
            ", bookReference='" + bookReference + '\'' +
            '}';
    }
}