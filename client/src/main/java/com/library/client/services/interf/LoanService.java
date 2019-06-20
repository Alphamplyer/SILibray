package com.library.client.services.interf;

import com.library.client.model.Loan;

import java.util.List;

public interface LoanService {

    Loan getLoan(int loan_id);

    List<Loan> getLoans();

    List<Loan> getUserLoans(int user_id);

    Loan registerLoan(int book_id, int user_id);

    Integer extendLoan(int loan_id, int user_id);
}
