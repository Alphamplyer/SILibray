package com.library.client;

import com.library.client.services.impl.*;
import com.library.client.services.interf.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@PropertySource(value = "file:${APP_HOME}/client.properties") // On charge le fichier de configuration de l'application dans le dossier externalisé.
@SpringBootApplication
public class ClientApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean(value = "authorService")
	public AuthorService getAuthorService() {
		return new AuthorServiceImpl();
	}

	@Bean(value = "bookService")
	public BookService getBookService() {
		return new BookServiceImpl();
	}

	@Bean(value = "loanService")
	public LoanService getLoanService() {
		return new LoanServiceImpl();
	}

	@Bean(value = "userService")
	public UserService getUserService() {
		return new UserServiceImpl();
	}

	@Bean(value = "searchService")
	public SearchService getSearchService() {
		return new SearchServiceImpl();
	}

	@Bean(value = "restTemplate")
	public RestTemplate getRestTemplate() { return new RestTemplate(); }

	@Bean(value = "commentService")
	public CommentService getCommentService() { return new CommentServiceImpl(); }
}