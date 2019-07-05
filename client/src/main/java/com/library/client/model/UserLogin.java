package com.library.client.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Diminution de la classe user, uniquement pour la connexion
 */
public class UserLogin {

    @NotNull
    @NotEmpty
    private String nickname;
    @NotNull
    @NotEmpty
    private String password;

    public UserLogin() {
    }

    public UserLogin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserLogin{" +
            "nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
