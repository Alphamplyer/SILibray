package com.library.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private int id;
    private String bookReference;
    private int authorId;
    private String name;
    private String summary;
    private Date releaseDate;
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

    /**
     * Récupère l'ID du livre.
     * @return L'ID du livre.
     */
    public int getId() {
        return id;
    }

    /**
     * Définie l'ID du livre
     * @param id L'ID du livre
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Récupère la référence du livre.
     * @return La référence du livre.
     */
    public String getBookReference() {
        return bookReference;
    }

    /**
     * Définie la référence du livre.
     * @param bookReference La référence du livre.
     */
    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }

    /**
     * Récupère l'ID de l'auteur du livre.
     * @return  L'ID de l'auteur du livre.
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Définie l'ID de l'auteur du livre.
     * @param authorId L'ID de l'auteur du livre.
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Récupère le nom du livre.
     * @return Le nom du livre
     */
    public String getName() {
        return name;
    }

    /**
     * Définie le nom du livre.
     * @param name Le nom du livre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère le résumé du livre.
     * @return Le résumé du livre.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Définie le résumé du livre.
     * @param summary Le résumé du livre.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Récupère la date de parution du livre.
     * @return La date de parution du livre.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Définie la date de parution du livre.
     * @param releaseDate La date de parution du livre.
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Récupère si oui ou non le livre est disponible.
     * @return Le livre est-il disponible ?
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Définie si oui ou non le livre est disponible
     * @param available Le livre est-il disponible ?
     */
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
