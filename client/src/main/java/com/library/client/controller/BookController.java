package com.library.client.controller;

import com.library.client.model.*;
import com.library.client.utils.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController extends AbstractController {

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
    public String displayBook(Model model, @PathVariable int id, HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            User user = (User) httpSession.getAttribute("user");
            model.addAttribute("haveReadBook", haveReadBook(user.getId(), id));
        }

        Book book = null;
        List<Book> books = null;
        Author author = null;
        List<Comment> comments = null;
        List<Author> comments_authors = null;
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
        catch (Exception e) {
            model.addAttribute("error_message", "Le livre n'a pas était trouvé ! Il ce peux que nos service est un problème !");
            return "error";
        }

        try {
            comments = commentService.getCommentFromBookRef(book.getBookReference());
            int average_notation = commentService.getAverageNotation(comments);

            System.out.println("Average Notation = " + average_notation);

            List<CommentAuthor> commentAuthors = new ArrayList<>();

            for (int i = 0; i < comments.size(); i++) {
                User comment_author = userService.getUser(comments.get(i).getAuthorId());
                commentAuthors.add(new CommentAuthor(comments.get(i), comment_author));
            }

            model.addAttribute("average_notation", average_notation);
            model.addAttribute("comments", commentAuthors);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("average_notation", 0);
            model.addAttribute("comments", null);
            model.addAttribute("comments_authors", null);
        }

        model.addAttribute("maxBook", books.size());
        model.addAttribute("numberOfAvailableBook", numberOfAvailableBook);
        model.addAttribute("book", book);
        model.addAttribute("author", author);

        return "book";
    }

    @RequestMapping(value = "/books/{id}/comment", method = RequestMethod.POST)
    public String createComment(Model model, RedirectAttributes redirectAttributes, @PathVariable(name = "id") int id, @RequestParam("content") String content, @RequestParam("notation") String notation_str, HttpSession session) {

        int notation;

        try {
            notation = Integer.parseInt(notation_str);

        } catch (NumberFormatException e) {
            notation = -1;
        }

        if (notation < 0 || notation > 5 || content.length() == 0) {
            redirectAttributes.addAttribute("error_message", "Votre commentaire n'est pas valide");
            return "redirect:/error";
        }

        Book book;

        try {
            book = bookService.getBook(id);
        } catch (HttpClientErrorException e) {
            book = null;
        }

        if (book == null) {
            redirectAttributes.addAttribute("error_message", "Une erreur c'est produite ! Veuillez réessayer !");
            return "redirect:/error";
        }

        User user;

        if (session.getAttribute("user") != null)
            user = (User)session.getAttribute("user");
        else {
            redirectAttributes.addAttribute("error_message", "Vous n'êtes pas connecté ! Connectez-vous et réessayé !");
            return "redirect:/error";
        }

        try {
            commentService.addComment(user.getId(), content, notation, book.getBookReference());
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status == HttpStatus.NOT_FOUND) {
                redirectAttributes.addAttribute("error_message", "Une erreur c'est produite ! Veuillez réessayer !");
                return "redirect:/error";
            }
        }

        return "redirect:/books/" + id;
    }

    // PRIVATE METHODS /////////////////////////////////////////////////////

    private boolean haveReadBook(int user_id, int book_id) {
        boolean haveReadThisBook = false;
        List<Loan> loans;

        try {
            loans = loanService.getUserLoans(user_id);
        } catch (Exception e) {
            return false;
        }

        for (Loan loan : loans) {
            if (loan.getBookId() == book_id) {
                haveReadThisBook = true;
                break;
            }
        }

        return haveReadThisBook;
    }
}
