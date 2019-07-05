package com.library.batch_checkloan;

import com.library.batch_checkloan.configuration.ApplicationProperties;
import com.library.batch_checkloan.model.Book;
import com.library.batch_checkloan.model.Loan;
import com.library.batch_checkloan.model.User;
import com.library.batch_checkloan.service.impl.EmailServiceImpl;
import com.library.batch_checkloan.service.interf.BookService;
import com.library.batch_checkloan.service.interf.LoanService;
import com.library.batch_checkloan.service.interf.UserService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BatchTasklet implements Tasklet {

    private EmailServiceImpl emailService;
    private UserService userService;
    private BookService bookService;
    private LoanService loanService;
    private ApplicationProperties properties;

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setEmailService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Récupère tous les prêts actifs (non-archivés), et vérifie si leur date de retour n'a pas été dépassé.
     * Dans le cas contraire, envoit un message de rappel à l'utilisateur par son email.
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        List<Loan> loans;

        try {
            loans = loanService.getActiveLoans();
        }
        catch (HttpClientErrorException e) {
            loans = null;
        }

        if (loans == null) {
            System.out.println("Failed to get all Active Loans");
            throw new Exception();
        }

        Date now = new Date();

        for (Loan loan : loans) {

            if (now.before(loan.getEndDate()))
                continue;

            User user;
            Book book;

            try {
                user = userService.getUser(loan.getUserId());
                book = bookService.getBook(loan.getBookId());
            }
            catch (HttpClientErrorException e) {
                user = null;
                book = null;
            }

            if (user == null || book == null) {
                System.out.println("Failed to get data of Active Loan (ID = " + loan.getId() + ")");
                continue;
            }

            String title = properties.getTitleMessage();
            String message = properties.getCustomMessage();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String begindate = dateFormat.format(loan.getBeginDate());
            String enddate = dateFormat.format(loan.getEndDate());

            message = message.replace("%firstname%", user.getFirstName());
            message = message.replace("%lastname%", user.getLastName());
            message = message.replace("%nickname%", user.getNickname());
            message = message.replace("%bookname%", book.getName());
            message = message.replace("%bookref%",book.getBookReference());
            message = message.replace("%begindate%", begindate);
            message = message.replace("%enddate%",enddate);

            emailService.sendSimpleMessage(user.getEmail(), title, message);
        }

        return RepeatStatus.FINISHED;
    }
}
