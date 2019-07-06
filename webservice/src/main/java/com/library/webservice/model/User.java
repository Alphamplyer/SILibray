package com.library.webservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"salt"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_pk")
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "permission_level", columnDefinition = "0", nullable = false)
    private int permissionLevel;
    @Column(name = "salt", nullable = false)
    private String salt;

    public User() {}

    public User(int id, String firstName, String lastName, String nickname, String password, String email, Date birthDate, int permissionLevel, String salt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.permissionLevel = permissionLevel;
        this.salt = salt;
    }

    /**
     * Récupère l'ID de l'utilisateur.
     * @return L'ID de l'utilisateur.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     * @return le prénom de l'utilisateur.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Récupère le nom de l'utilisateur.
     * @return le nom de l'utilisateur.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Récupère le pseudonyme de l'utilisateur.
     * @return le pseudonyme de l'utilisateur.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     * @return le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Récupère l'email de l'utilisateur.
     * @return l'email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Récupère la date de naissance de l'utilisateur.
     * @return la date de naissance de l'utilisateur.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Récupère le level de permission de l'utilisateur.
     * @return le level de permission de l'utilisateur.
     */
    public int getPermissionLevel() {
        return permissionLevel;
    }

    public String getSalt() {
        return salt;
    }

    /**
     * Définie l'ID de l'utilisateur.
     * @param id l'ID de l'utilisateur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définie le prénom de l'utilisateur.
     * @param firstName le prénom de l'utilisateur.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Définie le nom de l'utilisateur.
     * @param lastName le nom de l'utilisateur.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Définie le pseudonyme de l'utilisateur.
     * @param nickname le pseudonyme de l'utilisateur.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Définie le mot de passe de l'utilisateur.
     * @param password le mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Définie l'email de l'utilisateur.
     * @param email l'email de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définie la date de naissance de l'utilisateur.
     * @param birthDate la date de naissance de l'utilisateur.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Définie le level de permission de l'utilisateur.
     * @param permissionLevel le level de permission de l'utilisateur.
     */
    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", birthDate=" + birthDate +
            ", permissionLevel=" + permissionLevel +
            ", salt='" + salt + '\'' +
            '}';
    }
}
