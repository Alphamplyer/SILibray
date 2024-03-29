package com.library.client.controller;

import com.library.client.model.*;
import com.library.client.services.interf.BookService;
import com.library.client.services.interf.LoanService;
import com.library.client.services.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère les requêtes liées aux utilisateurs.
 */
@Controller
public class UserController extends AbstractController {


    /**
     * Affiche la page profil avec l'utilisateur fourni.
     * @param user Utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping("/user")
    public ModelAndView displayUserAfterRegistrationOrLogin(@ModelAttribute("user") User user) {
        return new ModelAndView("user", "user", user);
    }

    // MAPPING /////////////////////////////////////////////////////////////

    /**
     * Obtien et Affiche l'utilisateur avec son ID
     * @param id ID de l'utilisateur
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping("/user/{id}")
    public String displayUser(Model model, @PathVariable int id, HttpSession session) {

        if (session.getAttribute("user") == null){
            model.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "error";
        }

        User session_user = (User) session.getAttribute("user");

        User user;

        try {
            user = userService.getUser(id);
        }
        catch (HttpClientErrorException e) {
            user = null;
        }

        if (user == null) {
            return "error";
        }

        if (user.getId() != session_user.getId()) {
            model.addAttribute("error_message", "Vous n'êtes pas authorisé à voir cette page");
            return "error";
        }

        model.addAttribute("user", user);

        List<Loan> user_loans = loanService.getUserLoans(id);

        if (user_loans == null)
            return "user";

        List<LoanBook> loans = new ArrayList<>();

        for (Loan loan: user_loans) {
            Book book = bookService.getBook(loan.getBookId());
            if (book != null)
                loans.add(new LoanBook(book, loan));
        }

        model.addAttribute("loans", loans.size() > 0 ? loans : null);

        return "user";
    }

    /**
     * Affiche la page d'inscription.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model) {
        UserRegistration userRegistration = new UserRegistration();
        model.addAttribute("user", userRegistration);
        return "signup";
    }

    /**
     * Inscrit un nouveau menbre
     * @param userRegistration informations entrées par l'utilisateur.
     * @param result les résultats de la validation de userRegistration.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") @Valid UserRegistration userRegistration, BindingResult result, HttpSession session) {

        if (!result.hasErrors()) {

            try {
                User user = userService.register(userRegistration.toUser());

                if (user == null) {
                    return new ModelAndView("redirect:/error");
                }

                if (session.getAttribute("user") != null)
                    session.removeAttribute("user");

                session.setAttribute("user", user);

                return new ModelAndView("redirect:/user/" + user.getId());
            }
            catch (HttpClientErrorException e) {

                HttpStatus status = e.getStatusCode();

                if (status == HttpStatus.CONFLICT) {
                    result.rejectValue("nickname", "nickname", "Le serveur à rencontré un problème lors de votre inscription. Il se peut que ce pseudonyme soit déjà utilisé !");
                    result.rejectValue("email", "email", "Le serveur à rencontré un problème lors de votre inscription. Il se peut que cet email soit déjà utilisé !");
                    return new ModelAndView("signup", "user", userRegistration);
                }

                return new ModelAndView("redirect:/error");
            }
        }
        else {
            return new ModelAndView("signup", "user", userRegistration);
        }
    }

    /**
     * Affiche la page de connexion.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String identificationPage(Model model) {
        UserLogin userLogin = new UserLogin();
        model.addAttribute("user", userLogin);
        return "signin";
    }

    /**
     * Connecte un utilisateur.
     * @param userLogin informations entrées par l'utilisateur.
     * @param result les résultats de la validation de userLogin.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ModelAndView identification(@ModelAttribute("user") @Valid UserLogin userLogin, BindingResult result, HttpSession session) {

        if (!result.hasErrors()) {
            try {
                User user = userService.identify(userLogin);

                if (user == null)
                    return new ModelAndView("redirect:/error");

                if (session.getAttribute("user") != null)
                    session.removeAttribute("user");

                session.setAttribute("user", user);

                return new ModelAndView("redirect:/user/" + user.getId());
            }
            catch (HttpClientErrorException e) {
                HttpStatus status = e.getStatusCode();

                if (status == HttpStatus.UNAUTHORIZED){
                    result.rejectValue("nickname", "nickname", "Vous avez entré un mauvais identifiant ou un mauvais mot de passe");
                    return new ModelAndView("signin", "user", userLogin);
                }

                return new ModelAndView("redirect:/error");
            }
        } else {
            return new ModelAndView("signin", "user", userLogin);
        }
    }

    /**
     * Déconnecte un utilisateur.
     * @param httpSession Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/signout")
    public String LoginProcess(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            httpSession.removeAttribute("user");
        }

        return "redirect:/index";
    }
}
