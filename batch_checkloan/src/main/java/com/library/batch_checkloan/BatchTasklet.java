package com.library.batch_checkloan;

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

import java.util.Date;
import java.util.List;

@Component
public class BatchTasklet implements Tasklet {

    private EmailServiceImpl emailService;
    private UserService userService;
    private BookService bookService;
    private LoanService loanService;

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

            String message = "Bonjour, " + user.getLastName() + " " + user.getFirstName();
            message += "\nJe vous informe que vous devez retourner le livre suivant le plus vite possible sous peine de devoir payer ce dernier.\n\n";
            message += "- " + book.getName() + " (Ref : " + book.getBookReference() + ")\n\n";
            message += "Ne pas répondre à ce message. Ce message a été généré automatiquement, perssone ne vous repondra.\n" +
                "Pour toutes demandes, veuillez vous adresser au service client de la Bibliothèque où vous avez emprunter ce livre !";

            emailService.sendSimpleMessage(user.getEmail(), "[RAPPEL] Retourner le livre rapidement !", message);
        }

        return RepeatStatus.FINISHED;
    }
}
