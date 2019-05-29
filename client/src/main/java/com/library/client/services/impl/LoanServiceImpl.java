package com.library.client.services.impl;

import com.library.client.model.Loan;
import com.library.client.services.AbstractService;
import com.library.client.services.interf.LoanService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanServiceImpl extends AbstractService implements LoanService {

    @Override
    public Loan getLoan(int loan_id) {
        return restTemplate.getForObject(properties.getServiceURL() + "Loans/" + loan_id, Loan.class);
    }

    @Override
    public List<Loan> getUserLoans(int user_id) {
        Loan[] loans = restTemplate.getForObject(properties.getServiceURL() + "Loans/user/" + user_id, Loan[].class);
        return loans == null ? null : Arrays.asList(loans);
    }

    @Override
    public Loan registerLoan(int book_id, int user_id) {
        Loan loan = new Loan();
        loan.setBookId(book_id);
        loan.setUserId(user_id);

        Date date = new Date();

        loan.setBeginDate(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.WEEK_OF_YEAR, properties.getDefaultReservationWeeks());

        loan.setEndDate(calendar.getTime());

        return restTemplate.postForObject(properties.getServiceURL() + "Loans", loan, Loan.class);
    }

    @Override
    public Integer extendLoan(int loan_id, int user_id) {

        Loan loan;

        try {
            loan = restTemplate.getForObject(properties.getServiceURL() + "Loans/" + loan_id, Loan.class);
        }
        catch (HttpClientErrorException e) {
            loan = null;
        }

        if (loan == null)
            return -1;

        if (loan.getUserId() != user_id)
            return -2;

        Date date = loan.getEndDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, properties.getExtendReservationWeeks());

        loan.setEndDate(calendar.getTime());
        loan.setExtended(true);

        restTemplate.put(properties.getServiceURL() + "Loans", loan, Loan.class);

        return loan_id;
    }
}
