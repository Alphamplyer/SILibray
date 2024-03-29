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


    public Comment() { super(); }
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


    /**
     * Optien l'ID du commentaire
     * @return l'ID du commentaire
     */
    public int getId() {
        return id;
    }

    /**
     * Optien l'ID de l'auteur
     * @return l'ID de l'auteur
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Optien la date et l'heure de la création du commentaire
     * @return date et heure de la création du commentaire
     */
    public Timestamp getCreationTime() {
        return creationTime;
    }

    /**
     * Optien le contenu du livre
     * @return le contenu du livre
     */
    public String getContent() {
        return content;
    }

    /**
     * Optien la notation du livre
     * @return la notation du livre
     */
    public int getNotation() {
        return notation;
    }

    /**
     * Optien la référence du livre
     * @return la référence du livre
     */
    public String getBookReference() {
        return bookReference;
    }


    /**
     * Défini l'Id du commentaire
     * @param id l'ID du commentaire
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Défini l'ID de l'auteur
     * @param authorId l'ID de l'auteur.
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Défini la date et l'heure de la création du commentaire
     * @param creationTime date et heure de la création du commentaire
     */
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Défini le contenu du commentaire
     * @param content le contenu du commentaire
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Défini la notation du livre
     * @param notation la notation du livre
     */
    public void setNotation(int notation) {
        this.notation = notation;
    }

    /**
     * Défini la référence du livre
     * @param bookReference la référence du livre
     */
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