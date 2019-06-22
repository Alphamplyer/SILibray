package com.library.client.controller;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.services.interf.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SearchController extends AbstractController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam String search) {

        int book_result = 0;
        int author_result = 0;

        List<Book> books;
        List<Author> authors;

        try {
            books = searchService.searchBook(search);
        }
        catch (HttpClientErrorException e) {
            model.addAttribute("error_message","Une erreur c'est produite. Veuillez réessayer !");
            return "error";
        }

        try {
            authors = searchService.searchAuthor(search);
        }
        catch (HttpClientErrorException e) {
            model.addAttribute("error_message","Une erreur c'est produite. Veuillez réessayer !");
            return "error";
        }

        if (books == null && authors == null) {
            model.addAttribute("result_found", 0);
            return "search";
        }

        if (books != null && books.size() == 1 && authors == null) {
            return "redirect:/books/" + books.get(0).getId();
        }
        else if (authors != null && books == null && authors.size() == 1) {
            return "redirect:/authors/" + authors.get(0).getId();
        }

        if (authors != null && books != null) {
            book_result = books.size();
            author_result = authors.size();
        }
        else if (authors == null)
            book_result = books.size();
        else
            author_result = authors.size();


        model.addAttribute("search", search);
        model.addAttribute("result_found", book_result + author_result);
        model.addAttribute("book_result", book_result);
        model.addAttribute("author_result", author_result);
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        return "search";
    }
}
