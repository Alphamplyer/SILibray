package com.library.batch_checkloan.service.interf;

import com.library.batch_checkloan.model.Loan;

import java.util.List;

public interface LoanService {

    /**
     * On récupère la liste des prêts encore actif.
     * @return Liste des prêts encore actif.
     */
    List<Loan> getActiveLoans();
}
