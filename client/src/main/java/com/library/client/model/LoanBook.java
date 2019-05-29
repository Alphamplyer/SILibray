package com.library.client.model;

public class LoanBook {

    private Book book;
    private Loan loan;

    public LoanBook(Book book, Loan loan) {
        this.book = book;
        this.loan = loan;
    }

    public Book getBook() {
        return book;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "LoanBook{" +
            "book=" + book +
            ", loan=" + loan +
            '}';
    }
}
