package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Loan;
import org.library.manager.entity.Member;
import org.library.manager.enums.LoanStatus;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.response.LoanItemResponse;
import org.library.manager.model.response.LoanResponse;
import org.library.manager.repository.LoanRepository;
import org.library.manager.repository.MemberRepository;
import org.library.manager.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepo;
    private final MemberRepository memberRepo;

    @Override
    public Page<LoanResponse> filter(int page, int size,  LoanFilterRequest request) {
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Loan> loans = loanRepo.search(
                request.getMemberKeyword(),
                request.getStatus(),
                request.getFromDate(),
                request.getToDate(),
                pageable
        );
        return loans.map(this::toResponse);

    }
    @Override
    @Transactional
    public Page<LoanResponse> getAllLoan(int page, int size){
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Loan> loans = loanRepo.findAll(pageable);
        return loans.map(this::toResponse);
    }

    @Override
    public LoanResponse create(CreateLoanRequest request) {
//        Member member = memberRepo.findById(request.getMemberId())
//                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));
//        if(!Status.ACTIVE.equals(member.getStatus())){
//            throw new CustomException(ErrorCode.MEMBER_NOT_EXISTED);
//        }
//        if(request.getDueDate().isBefore(request.getLoanDate())){
//            throw new CustomException(ErrorCode.LOAN_INVALID_DUE_DATE);
//        }





        return null;
    }

    @Override
    public LoanResponse update(Long loanId, CreateLoanRequest request) {
//        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
//        if(LoanStatus.CANCELLED.equals(request.getStatus())){
//            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
//        }


        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long loanId) {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new CustomException(ErrorCode. LOAN_NOT_FOUND));
        return toResponse(loan);
    }

    @Override
    public void cancelLoan(Long loanId, CancelLoanRequest reason) {

    }
    @Override
    public void deleteLoan(Long loanId){
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
        if(LoanStatus.CANCELLED.equals(loan.getStatus())){
            throw new CustomException(ErrorCode.LOAN_CANNOT_DELETE);
        }
        loanRepo.delete(loan);


    }

    private LoanResponse toResponse(Loan loan){
        List<LoanItemResponse> list = loan.getLoanItems().stream()
                .map(item -> LoanItemResponse.builder()
                        .id(item.getId())
                        .bookId(item.getBookId())
                        .quantity(item.getQuantity())
                        .build()
                ).toList();
        return LoanResponse.builder()
                .id(loan.getId())
                .code(loan.getCode())
                .memberName(loan.getMember().getFullName())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate()).
                itemResponseList(list)
                .status(loan.getStatus())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();


    }

}
