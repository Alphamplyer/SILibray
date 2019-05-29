package com.library.webservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "book_pk")
    private int id;
    @Column(name = "book_reference", nullable = false)
    private String bookReference;
    @Column(name = "author_id", nullable = false)
    private int authorId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "summary", nullable = false)
    private String summary;
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;
    @Column(name = "available", nullable = false)
    private boolean available;

    public Book() {}

    public Book(int id, String bookReference, int authorId, String name, String summary, Date releaseDate, boolean available) {
        this.id = id;
        this.bookReference = bookReference;
        this.authorId = authorId;
        this.name = name;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookReference() {
        return bookReference;
    }

    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", bookReference='" + bookReference + '\'' +
            ", authorId=" + authorId +
            ", name='" + name + '\'' +
            ", summary='" + summary + '\'' +
            ", releaseDate=" + releaseDate +
            ", available=" + available +
            '}';
    }
}
