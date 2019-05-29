package com.library.batch_checkloan.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isArchived() {
        return archived;
    }

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
