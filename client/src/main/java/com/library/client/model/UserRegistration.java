package com.library.client.model;

import com.library.client.utils.validator.customAnnotation.PasswordMatches;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Classe pour valider les infos entré par l'utilisateur lors de l'inscription
 */
@PasswordMatches
public class UserRegistration {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String nickname;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 28)
    private String password;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 28)
    private String passwordComfimation;
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 254)
    private String email;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date birthDate;

    public UserRegistration() {
    }

    public UserRegistration(String firstName, String lastName, String nickname, String password, String passwordComfimation, String email, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.passwordComfimation = passwordComfimation;
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

    public String getPasswordComfimation() {
        return passwordComfimation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordComfimation(String passwordComfimation) {
        this.passwordComfimation = passwordComfimation;
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

    @Override
    public String toString() {
        return "UserRegistration{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", birthDate=" + birthDate +
            '}';
    }

    public User toUser() {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirthDate(birthDate);

        return user;
    }
}
