package com.library.client.controller;

import com.library.client.model.Book;
import com.library.client.model.Loan;
import com.library.client.services.interf.BookService;
import com.library.client.services.interf.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends AbstractController {

    @RequestMapping({"/", "/index"})
    public String home (Model model) {

        List<Book> new_books = null;

        try {
            new_books = bookService.getBooks().subList(0, 3);
        }
        catch (HttpClientErrorException e) {
            System.out.println("Il n'y a pas de livre dans cette bibliothèque en ce moment");
        }

        List<Loan> loans = null;

        try {
            loans = loanService.getLoans().subList(0,3);
        }
        catch (HttpClientErrorException e) {
            System.out.println("Il n'y a pas d'emprunt dans cette bibliothèque en ce moment");
        }


        if (loans != null) {
            List<Book> recent_loaned_books = new ArrayList<>();

            for (Loan loan : loans) {
                try {
                    recent_loaned_books.add(bookService.getBook(loan.getBookId()));
                } catch (HttpClientErrorException e) {
                    System.out.println("Il n'y a pas le livre démandé dans cette bibliothèque en ce moment");
                }
            }

            model.addAttribute("recentLoanedBooks", recent_loaned_books);
        }

        if (new_books != null) {
            model.addAttribute("newBooks", new_books);
        }

        return "index";
    }
}
