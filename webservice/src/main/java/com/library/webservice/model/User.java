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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getSalt() {
        return salt;
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
