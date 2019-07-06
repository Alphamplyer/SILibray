package com.library.webservice.web.controller;

import com.library.webservice.dao.LoanDao;
import com.library.webservice.model.Loan;
import com.library.webservice.web.exceptions.NotFoundException;
import com.library.webservice.web.exceptions.SQLConflitcException;
import com.library.webservice.web.exceptions.UnableToAddException;
import com.library.webservice.web.exceptions.UnableToModifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller qui à partir d'un requete renvoit des informations, effectu des actions relative aux emprunts.
 */
@RestController
public class LoanController {

    private LoanDao loanDao;

    @Autowired
    public LoanController (LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    /**
     * Retourne la liste de tous les emprunts
     * @return la liste de tous les emprunts
     */
    @GetMapping(value = "/Loans")
    public List<Loan> getLoans() {
        List<Loan> loans = loanDao.findAll(Sort.by("beginDate").descending());

        if (loans.isEmpty()) throw new NotFoundException("There are no loans currently !");

        return loans;
    }

    /**
     * Retourne la liste de tous les emprunts actif
     * @return la liste de tous les emprunts actif
     */
    @GetMapping(value = "/Loans/active")
    public List<Loan> getActiveLoans() {
        List<Loan> loans = loanDao.findAllByArchivedFalse();

        if (loans.isEmpty()) throw new NotFoundException("There are no active loans currently !");

        return loans;
    }

    /**
     * Retourne un emrpunt à partir de son ID
     * @param id id de l'emrpunt
     * @return l'emrpunt
     */
    @GetMapping(value = "/Loans/{id}")
    public Optional<Loan> getLoan(@PathVariable int id) {
        Optional<Loan> loan = loanDao.findById(id);

        if (!loan.isPresent()) throw new NotFoundException("Loan with the ID : '" + id + "' doesn't exist !");

        return loan;
    }

    /**
     * Retourne la liste des emrpunts d'un utilisateur à partir de son ID
     * @param id id de l'utilisateur
     * @return les emprunt de l'utilisateur
     */
    @GetMapping(value = "/Loans/user/{id}")
    public List<Loan> getUserLoan(@PathVariable int id) {
        return loanDao.findLoansByUserId(id);
    }

    /**
     * Insert un emprunt
     * @param loan emprunt à insérer et ses information
     * @return l'emprunt inséré
     */
    @PostMapping(value = "/Loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {

        Loan loanAdded = null;

        try {
            loanAdded = loanDao.save(loan);
        }
        catch (Exception e) {
            throw new UnableToAddException("An error as occurred when trying to add a Loan");
        }

        if (loanAdded == null) throw new UnableToAddException("An error as occurred when trying to add a Loan");

        return new ResponseEntity<>(loanAdded, HttpStatus.CREATED);
    }

    /**
     * Modifi un emprunt
     * @param loan emprunt à modifier et ses nouvelles information
     * @return l'emprunt modifié
     */
    @PutMapping(value = "/Loans")
    public ResponseEntity<Loan> modifyLoan(@RequestBody Loan loan) {

        Optional<Loan> loanToUpdate = loanDao.findById(loan.getId());

        if (!loanToUpdate.isPresent()) throw new NotFoundException("Loan with the ID : '" + loan.getId() + "' doesn't exits");

        Loan loanModified = loanDao.save(loan);

        if (loanModified == null) throw new UnableToModifyException("Loan has been not modified !");

        return new ResponseEntity<>(loanModified, HttpStatus.OK);
    }

    /**
     * Archive un emprunt
     * @param id id le l'emprunt
     */
    @PutMapping(value = "/Loans/{id}")
    public ResponseEntity<Void> archiveLoan(@PathVariable int id) {
        Optional<Loan> loanToUpdate = loanDao.findById(id);

        if (!loanToUpdate.isPresent()) throw new NotFoundException("Loan with the ID : '" + id + "' doesn't exits");

        loanToUpdate.get().setArchived(true);
        Loan loanModified = loanDao.save(loanToUpdate.get());

        if (loanModified == null) throw new UnableToModifyException("Loan has been not archived !");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
