package com.library.client.controller;

import com.library.client.configurations.ApplicationProperties;
import com.library.client.services.interf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractController {

    // PRIVATE VARIABLES ///////////////////////////////////////////////////

    protected AuthorService authorService;
    protected BookService bookService;
    protected CommentService commentService;
    protected LoanService loanService;
    protected ApplicationProperties properties;
    protected SearchService searchService;
    protected UserService userService;

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

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
