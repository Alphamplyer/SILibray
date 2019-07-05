package com.library.client.controller;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.utils.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Gère tous les requêtes liés au autheur.
 */
@Controller
public class AuthorController extends AbstractController {

    // MAPPING /////////////////////////////////////////////////////////////

    /**
     * Optenir la liste d'auteur suivant la page
     * @param page (optionel) page à afficher. Valeur par défaut page = 1
     * @return le nom de la page html associé.
     */
    @RequestMapping("/authors")
    public String displayAuthors(Model model, @RequestParam(required = false) Integer page) {

        List<Author> authors = authorService.getAuthors();

        if (authors == null)
            return "error";

        if (page == null)
            page = 1;

        Pagination<Author> authorPagination = new Pagination<>(properties.getNumberElementByPage(), authors);

        model.addAttribute("hasPrevPage", authorPagination.hasPrevPage(page));
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNextPage", authorPagination.hasNextPage(page));
        model.addAttribute("maxPage", authorPagination.getMaxPage());
        model.addAttribute("authors", authorPagination.getPageItem(page));

        return "authors";
    }

    /**
     * Optenir les information d'un auteur par son ID.
     * @param id ID de l'auteur.
     * @return le nom de la page html associé.
     */
    @RequestMapping("/authors/{id}")
    public String displayAuthor (Model model, @PathVariable int id) {

        Author author = authorService.getAuthor(id);

        if (author == null)
            return "error";

        List<Book> books = bookService.getAuthorBooks(id);

        model.addAttribute("author", author);
        model.addAttribute("books", books);

        return "author";
    }
}
