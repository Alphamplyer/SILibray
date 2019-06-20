package com.library.client.controller;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.model.Comment;
import com.library.client.model.User;
import com.library.client.services.interf.AuthorService;
import com.library.client.services.interf.BookService;
import com.library.client.services.interf.CommentService;
import com.library.client.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController extends AbstractController {

    // PRIVATE VARIABLES ///////////////////////////////////////////////////

    private AuthorService authorService;
    private BookService bookService;
    private CommentService commentService;

    // SETTERS /////////////////////////////////////////////////////////////

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
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
        List<Comment> comments = null;
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

        if (book != null) {

            try {
                comments = commentService.getCommentFromBookRef(book.getBookReference());

                int average_notation = commentService.getAverageNotation(comments);

                model.addAttribute("average_notation", average_notation);
                model.addAttribute("comments", comments);
            } catch (HttpClientErrorException e) {
                HttpStatus status = e.getStatusCode();

                if (status == HttpStatus.NOT_FOUND) {
                    model.addAttribute("comments", null);
                }
            }
        }

        model.addAttribute("maxBook", books == null ? 0 : books.size());
        model.addAttribute("numberOfAvailableBook", numberOfAvailableBook);
        model.addAttribute("book", book);
        model.addAttribute("author", author);

        return "book";
    }

    @RequestMapping(value = "/books/{id}/comment", method = RequestMethod.POST)
    public String createComment(Model model, @PathVariable(name = "id") int id, @RequestParam("content") String content, @RequestParam("notation") String notation_str, HttpSession session) {

        int notation;

        try {
            notation = Integer.parseInt(notation_str);

        } catch (NumberFormatException e) {
            notation = -1;
        }

        if (notation < 0 || notation > 5 || content.length() == 0) {
            model.addAttribute("error_message", "Votre commentaire n'est pas valide");
            return "redirect:error";
        }

        Book book;

        try {
            book = bookService.getBook(id);
        } catch (HttpClientErrorException e) {
            book = null;
        }

        if (book == null) {
            model.addAttribute("error_message", "Une erreur c'est produite ! Veuillez réessayer !");
            return "redirect:error";
        }

        User user;

        if (session.getAttribute("user") != null)
            user = (User)session.getAttribute("user");
        else {
            model.addAttribute("error_message", "Une erreur c'est produite ! Veuillez réessayer !");
            return "redirect:error";
        }

        try {
            commentService.addComment(user.getId(), content, notation, book.getBookReference());
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                model.addAttribute("error_message", "Une erreur c'est produite ! Veuillez réessayer !");
                return "redirect:error";
            }
        }

        return "redirect:book/" + book.getId();
    }
}
