package com.library.webservice.dao;

import com.library.webservice.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDao extends JpaRepository<Loan, Integer> {

    List<Loan> findLoansByUserId(Integer userId);

    List<Loan> findAllByArchivedFalse();
}
