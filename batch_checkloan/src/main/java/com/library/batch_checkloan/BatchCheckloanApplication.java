package com.library.batch_checkloan;

import com.library.batch_checkloan.service.impl.BookServiceImpl;
import com.library.batch_checkloan.service.impl.LoanServiceImpl;
import com.library.batch_checkloan.service.impl.UserServiceImpl;
import com.library.batch_checkloan.service.interf.BookService;
import com.library.batch_checkloan.service.interf.LoanService;
import com.library.batch_checkloan.service.interf.UserService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:${APP_HOME}/batch.properties")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableBatchProcessing
public class BatchCheckloanApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchCheckloanApplication.class, args);
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
}
