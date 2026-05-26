package org.library.manager.service;

import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.ExtendLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.ReturnLoanRequest;
import org.library.manager.model.response.LoanResponse;
import org.springframework.data.domain.Page;

public interface LoanService {

    Page<LoanResponse> getAll(int page, int size, LoanFilterRequest filter);

    LoanResponse getById(Long loanId);

    LoanResponse create(CreateLoanRequest request);

    LoanResponse returnLoan(Long loanId, ReturnLoanRequest request);

    LoanResponse extendLoan(Long loanId, ExtendLoanRequest request);

    void cancel(Long loanId, CancelLoanRequest request);

    void delete(Long loanId);
}
