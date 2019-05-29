package com.library.batch_checkloan.service.impl;

import com.library.batch_checkloan.model.Loan;
import com.library.batch_checkloan.service.interf.LoanService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class LoanServiceImpl extends AbstractService implements LoanService {

    @Override
    public List<Loan> getActiveLoans() {
        RestTemplate restTemplate = new RestTemplate();
        Loan[] loans = restTemplate.getForObject(properties.getServiceURL() + "Loans/active", Loan[].class);
        return loans == null ? null : Arrays.asList(loans);
    }
}
