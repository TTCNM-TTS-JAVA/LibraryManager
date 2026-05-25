package org.library.manager.service;

import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.UpdateLoanRequest;
import org.library.manager.model.response.LoanResponse;
import org.springframework.data.domain.Page;

public interface LoanService {

    Page<LoanResponse> getAll(int page, int size);

    Page<LoanResponse> filter(int page, int size, LoanFilterRequest request);

    LoanResponse getById(Long loanId);

    LoanResponse create(CreateLoanRequest request);

    LoanResponse update(Long loanId, UpdateLoanRequest request);

    void cancel(Long loanId, CancelLoanRequest request);

    void delete(Long loanId);
}
