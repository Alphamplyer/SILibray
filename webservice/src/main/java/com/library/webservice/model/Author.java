package com.library.webservice.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "authors_pk")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "biography", nullable = false)
    private String biography;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "death_date")
    private Date deathDate;

    public Author () {}

    public Author(int id, String name, String biography, Date birthDate, @Nullable Date deathDate) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    /**
     * Retourne l'ID de l'auteur.
     * @return Id de l'auteur.
     */
    public int getId() {
        return id;
    }

    /**
     * Défini l'ID de l'auteur.
     * @param id l'ID de l'auteur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'auteur.
     * @return le nom de l'auteur.
     */
    public String getName() {
        return name;
    }

    /**
     * Défini le nom de l'auteur.
     * @param name le nom de l'auteur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la biographie de l'auteur.
     * @return la biographie de l'auteur.
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Défini la biographie de l'auteur.
     * @param biography la biographie de l'auteur.
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Retourne la date de naissance de l'auteur.
     * @return la date de naissance de l'auteur.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Défini la date de naissance de l'auteur.
     * @param birthDate la date de naissance de l'auteur.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retourne la date de mort de l'auteur.
     * @return la date de mort de l'auteur.
     */
    public Date getDeathDate() {
        return deathDate;
    }

    /**
     * Défini la date de mort de l'auteur.
     * @param deathDate la date de mort de l'auteur.
     */
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", biography='" + biography + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            '}';
    }
}
