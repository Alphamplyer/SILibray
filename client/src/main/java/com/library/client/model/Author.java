package com.library.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

    private int id;
    private String name;
    private String biography;
    private Date birthDate;
    private Date deathDate;

    public Author () {}

    public Author(int id, String name, String biography, Date birthDate, Date deathDate) {
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
