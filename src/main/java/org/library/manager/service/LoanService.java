package org.library.manager.service;

import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.UpdateLoanRequest;
import org.library.manager.model.response.LoanResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanService {
    Page<LoanResponse> filter(int page, int size, LoanFilterRequest request);

    LoanResponse create(CreateLoanRequest request);

    LoanResponse update(Long loanId, UpdateLoanRequest request);

    LoanResponse getLoanById(Long loanId);

    void cancelLoan(Long loanId, CancelLoanRequest reason);
    void deleteLoan(Long loanId);
    Page<LoanResponse> getAllLoan(int page, int size);

}
