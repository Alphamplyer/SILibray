package com.library.client.controller;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.services.interf.AuthorService;
import com.library.client.services.interf.BookService;
import com.library.client.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
public class BookController extends AbstractController {

    // PRIVATE VARIABLES ///////////////////////////////////////////////////

    private AuthorService authorService;
    private BookService bookService;

    // SETTERS /////////////////////////////////////////////////////////////

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    // MAPPING /////////////////////////////////////////////////////////////

    @RequestMapping("/books")
    public String displayBooks(Model model, @RequestParam(required = false) Integer page) {

        List<Book> books = null;

        try {
            books = bookService.getBooks();
        }
        catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                model.addAttribute("error_message", "Il n'y a pas de livre dans cette bibliothèque en ce moment.");
                return "error";
            }
        }

        if (books == null)
            return "error";

        if (page == null)
            page = 1;

        Pagination<Book> bookPagination = new Pagination<>(properties.getNumberElementByPage(), books);

        model.addAttribute("hasPrevPage", bookPagination.hasPrevPage(page));
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNextPage", bookPagination.hasNextPage(page));
        model.addAttribute("maxPage", bookPagination.getMaxPage());
        model.addAttribute("books", bookPagination.getPageItem(page));
        return "books";
    }

    @RequestMapping("/books/{id}")
    public String displayBook(Model model, @PathVariable int id) {

        Book book = null;
        List<Book> books = null;
        Author author = null;
        int numberOfAvailableBook = 0;

        try {
            book = bookService.getBook(id);
            books = bookService.getBooksByRef(book.getBookReference());
            author = authorService.getAuthor(book.getAuthorId());

            for (Book b: books) {
                if (b.isAvailable())
                    numberOfAvailableBook++;
            }
        }
        catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                model.addAttribute("error_message", "Le livre n'a pas était trouvé ! Il ce peux que nos service est un problème !");
                return "error";
            }
        }
        catch (NullPointerException e) {
            model.addAttribute("error_message", "Le livre n'a pas était trouvé ! Il ce peut, que nos service est un problème !");
            return "error";
        }

        model.addAttribute("maxBook", books == null ? 0 : books.size());
        model.addAttribute("numberOfAvailableBook", numberOfAvailableBook);
        model.addAttribute("book", book);
        model.addAttribute("author", author);

        return "book";
    }
}
