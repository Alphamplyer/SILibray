package com.library.client.services.interf;

import com.library.client.model.Loan;

import java.util.List;

public interface LoanService {

    /**
     * obtient le prêt à partir de l'ID
     * @param loan_id l'ID du prêt
     * @return le prêt.
     */
    Loan getLoan(int loan_id);

    /**
     * Obtenir la liste de tous les prêts
     * @return liste de tous les prêts
     */
    List<Loan> getLoans();

    /**
     * Obtient les prêts d'un utilisateur.
     * @param user_id l'ID de l'utilisateur
     * @return les prêts
     */
    List<Loan> getUserLoans(int user_id);

    /**
     * Ajoute un prêt à un utilisateur
     * @param book_id ID du livre emprunté
     * @param user_id ID du lecteur
     * @return le prêt ajouté
     */
    Loan registerLoan(int book_id, int user_id);

    /**
     * Etend un prêt
     * @param loan_id ID du prêt
     * @param user_id Id du lecteur
     * @return retourne ID du prêt modifié
     */
    Integer extendLoan(int loan_id, int user_id);
}
