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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

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
