package com.library.batch_checkloan.service.interf;

import com.library.batch_checkloan.model.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> getActiveLoans();
}
