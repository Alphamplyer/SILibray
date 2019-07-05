package com.library.client.controller;

import com.library.client.model.Book;
import com.library.client.model.Loan;
import com.library.client.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Gère toutes les requêtes en lien avec les prêts de livre.
 */
@Controller
public class LoanController extends AbstractController {

    // MAPPING /////////////////////////////////////////////////////////////

    /**
     * Obtien les information pour afficher les infos d'un prêt
     * @param loan_id ID du prêt
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/loans/{loan_id}", method = RequestMethod.GET)
    private String displayLoan(Model model, @PathVariable int loan_id, HttpSession session) {

        if (session.getAttribute("user") == null){
            model.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "error";
        }

        User session_user = (User) session.getAttribute("user");

        Loan loan;

        try {
            loan = loanService.getLoan(loan_id);
        }
        catch (HttpClientErrorException e) {
            loan = null;
        }

        if (loan == null) {
            model.addAttribute("error_message", "Le serveur n'a pas trouvé les données liées à cet emprunt");
            return "error";
        }

        if (loan.getUserId() != session_user.getId()) {
            model.addAttribute("error_message", "Vous n'êtes pas authorisé à voir cette page");
            return "error";
        }

        Book book = null;
        User user = null;

        try {
            book = bookService.getBook(loan.getBookId());
            user = userService.getUser(loan.getUserId());
        }
        catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                book = null;
                user = null;
            }
        }

        if (user == null || book == null) {
            model.addAttribute("error_message", "Le serveur n'a pas trouvé les données liées à cet emprunt");
            return "error";
        }

        model.addAttribute("user", user);
        model.addAttribute("book", book);
        model.addAttribute("loan", loan);
        model.addAttribute("begindate", loan.getBeginDate());
        model.addAttribute("enddate", loan.getEndDate());
        model.addAttribute("displayLoan", true);

        return "loan";
    }


    /**
     * Obtien les information pour créer un prêt.
     * @param book_id Id du livre.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/loans/new/{book_id}", method = RequestMethod.GET)
    public String displayLoanBookForm(Model model, @PathVariable int book_id, HttpSession session) {

        // if not connected
        if (session.getAttribute("user") == null) {
            model.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "error";
        }

        User session_user = (User)session.getAttribute("user");

        User user;

        try {
            user = userService.getUser(session_user.getId());
        } catch (HttpClientErrorException e) {
            user = null;
        }

        if (user == null)
            return "error";

        if (!user.getFirstName().equals(session_user.getFirstName()) || !user.getLastName().equals(session_user.getLastName())) {
            model.addAttribute("error_message", "Votre identité n'as pas été reconnu !");
            return "error";
        }

        List<Loan> user_loans;

        try {
            user_loans = loanService.getUserLoans(user.getId());
        } catch (HttpClientErrorException e) {
            user_loans = null;
        }

        if (user_loans == null)
            return "error";

        Book book = bookService.getBook(book_id);

        if (book == null) {
            model.addAttribute("error_message", "Ce livre n'existe pas");
            return "error";
        }

        if (!book.isAvailable()) {
            model.addAttribute("error_message", "Quelqu'un a réservé ce livre avant vous !");
            return "error";
        }

        List<Book> other_same_book;

        try {
            other_same_book = bookService.getBooksByRef(book.getBookReference());
        }
        catch (HttpClientErrorException e) {
            other_same_book = null;
        }

        if (other_same_book == null) {
            return "error";
        }

        for (Loan loan : user_loans) {
            for (Book tmp_book : other_same_book) {
                if (loan.getBookId() == tmp_book.getId() && !loan.isArchived()) {
                    model.addAttribute("error_message", "Vous avez déjà un exemplaire de ce livre en ce momment !");
                    return "error";
                }
            }
        }

        model.addAttribute("book", book);

        Date date = new Date();

        model.addAttribute("begindate", date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, properties.getDefaultReservationWeeks());

        model.addAttribute("enddate", calendar.getTime());
        model.addAttribute("displayLoan", false);

        return "loan";
    }

    /**
     * Créer le prêt et redirige vers les information du prêt.
     * @param book_id Id du livre.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/loans/new/{book_id}", method = RequestMethod.POST)
    public String loanBook(Model model, @PathVariable int book_id, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("user") == null) {
            redirectAttributes.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "redirect:/error";
        }

        User user = (User) session.getAttribute("user");

        if (user == null){
            redirectAttributes.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "redirect:/error";
        }

        Book book = null;

        try {
            book = bookService.getBook(book_id);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                redirectAttributes.addAttribute("error_message", "Une erreur de communication avec nos serveurs a dû se produire. Merci de réessayer plus tard !");
                return "redirect:/error";
            }
        }

        if (book == null || !book.isAvailable()) {
            redirectAttributes.addAttribute("error_message", "Une erreur de communication avec nos serveurs a dû se produire. Merci de réessayer plus tard !");
            return "redirect:/error";
        }

        Boolean success = null;

        try {
            success = bookService.unavailable(book);
        }
        catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.BAD_REQUEST) {
                redirectAttributes.addAttribute("error_message", "Une erreur de communication avec nos serveurs a dû se produire. Merci de réessayer plus tard !");
                return "redirect:/error";
            }
        }


        Integer loan_id = null;

        try {
            Loan tmp_loan = loanService.registerLoan(book.getId(), user.getId());

            if (tmp_loan == null) {
                redirectAttributes.addAttribute("error_message", "Une erreur de communication avec nos serveurs a dû se produire. Merci de réessayer plus tard !");
                return "redirect:/error";
            }

            loan_id = tmp_loan.getId();
        }
        catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.BAD_REQUEST) {
                redirectAttributes.addAttribute("error_message", "Une erreur de communication avec nos serveurs a dû se produire. Merci de réessayer plus tard !");
                return "redirect:/error";
            }
        }

        return "redirect:/loans/" + loan_id;
    }


    /**
     * Obtien les informations pour étendre un prêt.
     * @param loan_id ID du prêt.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/loans/extend/{loan_id}", method = RequestMethod.GET)
    private String extendLoanForm(Model model, @PathVariable int loan_id, HttpSession session) {

        if (session.getAttribute("user") == null){
            model.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "error";
        }

        User session_user = (User) session.getAttribute("user");

        Loan loan;

        try {
            loan = loanService.getLoan(loan_id);
        }
        catch (HttpClientErrorException e) {
            loan = null;
        }

        if (loan == null) {
            model.addAttribute("error_message", "Le serveur n'a pas trouvé les données liées à cet emprunt");
            return "error";
        }

        if (loan.getUserId() != session_user.getId()) {
            model.addAttribute("error_message", "Vous n'êtes pas authorisé à voir cette page");
            return "error";
        }

        if (loan.isExtended() || loan.isArchived())
            return "error";

        Book book;

        try {
            book = bookService.getBook(loan.getBookId());
        }
        catch (HttpClientErrorException e) {
            book = null;
        }

        if (book == null) {
            model.addAttribute("error_message", "Le serveur n'a pas trouvé les données liées à cet emprunt");
            return "error";
        }

        Date begindate = loan.getBeginDate();
        Date enddate = loan.getEndDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(enddate);
        calendar.add(Calendar.WEEK_OF_YEAR, properties.getExtendReservationWeeks());

        model.addAttribute("loan", loan);
        model.addAttribute("book", book);
        model.addAttribute("begindate", begindate);
        model.addAttribute("enddate", enddate);
        model.addAttribute("extendeddate", calendar.getTime());
        model.addAttribute("displayLoan", false);

        return "loan";
    }


    /**
     * Etand et redirige vers les information du prêt.
     * @param loan_id Id du prêt.
     * @param session Session utilisateur.
     * @return le nom de la page html associé.
     */
    @RequestMapping(value = "/loans/extend/{loan_id}", method = RequestMethod.POST)
    private String extendLoan(Model model, @PathVariable int loan_id, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("user") == null){
            redirectAttributes.addAttribute("error_message", "Vous devez être connecté pour accéder à cette page.");
            return "redirect:/error";
        }

        User session_user = (User) session.getAttribute("user");

        Integer loan = loanService.extendLoan(loan_id, session_user.getId());

        if (loan == -1) {
            redirectAttributes.addAttribute("error_message", "Une erreur a empéché d'étendre votre emprunt !");
            return "redirect:/error";
        }

        if (loan == -2) {
            redirectAttributes.addAttribute("error_message", "Vous n'êtes pas authorisé à voir cette page");
            return "redirect:/error";
        }

        return "redirect:/loans/" + loan;
    }
}
