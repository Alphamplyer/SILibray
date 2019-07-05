package com.library.client.services.interf;

import com.library.client.model.User;
import com.library.client.model.UserLogin;

public interface UserService {

    /**
     * Obtient l'utilisateur par son ID
     * @param user_id ID de l'utilisateur
     * @return l'utilisateur
     */
    User getUser(int user_id);

    /**
     * Enregistre un utilisateur dans la BD
     * @param user l'utilisateur
     * @return l'utilisateur enregistré
     */
    User register(User user);

    /**
     * Identifie un utilisateur
     * @param userLogin l'utilisateur à identifier
     * @return les infos de l'utilisateur identifier, null si pas identifier
     */
    User identify(UserLogin userLogin);
}
