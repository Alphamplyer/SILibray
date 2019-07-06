package com.library.webservice.dao;

import com.library.webservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Obtient de la base de données, l'utilisateur à partir de son pseudonyme.
     * @param nickname pseudonyme de l'utilisateur.
     * @return l'utilisateur.
     */
    User findUserByNickname(String nickname);

}
