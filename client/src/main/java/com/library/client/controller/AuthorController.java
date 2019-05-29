package com.library.client.controller;

import com.library.client.configurations.ApplicationProperties;
import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.services.interf.AuthorService;
import com.library.client.services.interf.BookService;
import com.library.client.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    // PRIVATE VARIABLES ///////////////////////////////////////////////////

    private ApplicationProperties properties;
    private AuthorService authorService;
    private BookService bookService;

    // SETTERS /////////////////////////////////////////////////////////////

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    // MAPPING /////////////////////////////////////////////////////////////

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
