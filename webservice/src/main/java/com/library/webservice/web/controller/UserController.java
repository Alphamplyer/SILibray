package com.library.webservice.web.controller;

import com.library.webservice.dao.UserDao;
import com.library.webservice.model.User;
import com.library.webservice.utils.Password;
import com.library.webservice.web.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller qui à partir d'un requete renvoit des informations, effectu des actions relative aux utilisisateur.
 */
@RestController
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Retourne la liste de tous les utilisateurs
     * @return la liste de tous les utilisateurs
     */
    @GetMapping(value = "Users")
    public List<User> getUsers() {
        List<User> users = userDao.findAll();

        if (users.isEmpty()) throw new NotFoundException("There are no users currently !");

        return users;
    }

    /**
     * Retourne un utilisateur à partir de son ID
     * @param id id de l'utilisateur
     * @return l'utilisateur
     */
    @GetMapping(value = "Users/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        Optional<User> user = userDao.findById(id);

        if (!user.isPresent()) throw new NotFoundException("User with the ID : '" + id + "' doesn't exits");

        return user;
    }

    /**
     * Identifie un utilisateur, et retroune ses informations si identifier
     * @param userToIdentify utilisateur à identifier
     * @return informations de l'utlisateur si identifier
     */
    @PostMapping(value = "Users/Identification")
    public ResponseEntity<User> identifyUser(@RequestBody User userToIdentify) {

        User user = userDao.findUserByNickname(userToIdentify.getNickname());

        if (user == null || !Password.verifyUserPassword(userToIdentify.getPassword(), user.getPassword(), user.getSalt())) throw new IdentificationException("Bad login information");

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Créer un utilisateur en base à partir de ses informations
     * @param user info utilisateur
     * @return l'utilisateur créer
     */
    @PostMapping(value = "Users")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user.setSalt(Password.getSalt(255));
        user.setPassword(Password.generateSecurePassword(user.getPassword(), user.getSalt()));
        User userAdded = null;

        try {
            userAdded = userDao.save(user);
        }
        catch (Exception e) {
            throw new SQLConflitcException("Duplicate Nickname or Email");
        }

        if (userAdded == null) throw new UnableToAddException("An error as occurred when trying to add an user");

        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    /**
     * Modifie un utilisateur en base à partir de ses informations
     * @param user info utilisateur
     * @return l'utilisateur modifier
     */
    @PutMapping(value = "Users")
    public ResponseEntity<User> modifyUser(@RequestBody User user) {

        Optional<User> userToUpdate = userDao.findById(user.getId());

        if (!userToUpdate.isPresent()) throw new NotFoundException("User with the ID : '" + user.getId() + "' doesn't exits");

        user.setSalt(userToUpdate.get().getSalt());
        user.setPassword(userToUpdate.get().getPassword());

        User userModified = userDao.save(user);

        if (userModified == null) throw new UnableToModifyException("User has been not modified!");

        userModified.setSalt(null);
        userModified.setPassword(null);

        return new ResponseEntity<>(userModified, HttpStatus.OK);
    }

    /**
     * Suprime un utilisateur en base à partir de son id
     * @param id id utilisateur
     */
    @DeleteMapping(value = "Users/{id}")
    public void deleteUser(@PathVariable int id) {
        try {
            userDao.deleteById(id);
        }
        catch (Exception e) {
            throw new UnableToDeleteException("User has been not deleted du to internal error!");
        }
    }
}
