package com.library.webservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loan_pk")
    private int id;
    @Column(name = "user_id", updatable = false, nullable = false)
    private int userId;
    @Column(name = "book_id", updatable = false, nullable = false)
    private int bookId;
    @Column(name = "begin_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date beginDate;
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Column (name = "is_extended", columnDefinition = "false", nullable = false)
    private boolean extended;
    @Column (name = "is_archived", columnDefinition = "false", nullable = false)
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
