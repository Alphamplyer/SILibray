package com.library.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {

    private int id;
    private int userId;
    private int bookId;
    private Date beginDate;
    private Date endDate;
    private boolean extended;
    private boolean archived;

    public Loan () {}

    public Loan(int id, int userId, int bookId, Date beginDate, Date endDate, boolean extended, boolean archived) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.extended = extended;
        this.archived = archived;
    }


    // --- < GETTERS >-------------------------------------------------------------------------------------------------- //

    /**
     * Récupère l'ID du prêt.
     * @return L'ID du prêt.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère l'ID de l'emprunteur.
     * @return L'ID de l'emprunteur.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Récupère l'ID du livre emprunté.
     * @return L'ID du livre emprunté.
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Récupère la date de début du prêt.
     * @return La date de début du prêt.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Récupère la date de fin du prêt.
     * @return La date de fun dy prêt.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Récupère si oui ou non le prêt est prolongé.
     * @return le prêt est-il prolongé ?
     */
    public boolean isExtended() {
        return extended;
    }

    /**
     * Récupère si oui ou non le prêt est archivé, s'il a fini d'être traité.
     * @return Le prêt est-il archivé ?
     */
    public boolean isArchived() {
        return archived;
    }


    // --- < SETTERS >-------------------------------------------------------------------------------------------------- //

    /**
     * Définie l'ID du prêt.
     * @param id l'ID du prêt.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définie l'ID de l'emprunteur.
     * @param userId l'ID de l'emprunteur.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Définie l'ID du livre emprunté.
     * @param bookId l'ID du livre emprunté.
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Définie la date de début du prêt.
     * @param beginDate la date de début du prêt.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Définie la date de fin du prêt.
     * @param endDate la date de fin du prêt.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Définie si oui ou non le prêt est prolongé.
     * @param extended le prêt est-il prolongé ?
     */
    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    /**
     * Définie si oui ou non le prêt est archivé, s'il a fini d'être traité.
     * @param archived Le prêt est-il archivé ?
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Loan{" +
            "id=" + id +
            ", userId=" + userId +
            ", bookId=" + bookId +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", extended=" + extended +
            ", archived=" + archived +
            '}';
    }
}