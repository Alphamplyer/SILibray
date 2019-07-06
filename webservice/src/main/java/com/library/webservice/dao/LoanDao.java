package com.library.webservice.dao;

import com.library.webservice.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDao extends JpaRepository<Loan, Integer> {

    /**
     * Obtient de la base de données, la liste des emprunt de l'utilisateur avec son ID.
     * @param userId ID de l'utilisateur.
     * @return la liste des emprunt de l'utilisateur
     */
    List<Loan> findLoansByUserId(Integer userId);

    /**
     * Obtient de la base de données, tous les emprunt actif.
     * @return la liste des emprunt actif.
     */
    List<Loan> findAllByArchivedFalse();
}
