package com.library.batch_checkloan.service.interf;

import com.library.batch_checkloan.model.User;

public interface UserService {

    /**
     * On récupère un utilisateur à partir de son ID.
     * @param user_id L'ID de l'utilisateur.
     * @return Un utilisateur.
     */
    User getUser(int user_id);
}
