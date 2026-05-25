package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Book;
import org.library.manager.entity.Loan;
import org.library.manager.entity.LoanItem;
import org.library.manager.entity.Member;
import org.library.manager.enums.LoanStatus;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.request.*;
import org.library.manager.model.response.LoanItemResponse;
import org.library.manager.model.response.LoanResponse;
import org.library.manager.repository.BookRepository;
import org.library.manager.repository.LoanRepository;
import org.library.manager.repository.MemberRepository;
import org.library.manager.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepo;
    private final MemberRepository memberRepo;
    private final BookRepository bookRepo;

    @Override
    public Page<LoanResponse> filter(int page, int size, LoanFilterRequest request) {
        Pageable pageable = PageRequest.of(page - 1, size);
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
    public Page<LoanResponse> getAllLoan(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Loan> loans = loanRepo.findAll(pageable);
        return loans.map(this::toResponse);
    }

    @Override
    @Transactional
    public LoanResponse create(CreateLoanRequest request) {
        Member member = memberRepo.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXISTED));
        if (!Status.ACTIVE.equals(member.getStatus())) {
            throw new CustomException(ErrorCode.MEMBER_NOT_EXISTED);
        }

        if (request.getDueDate().isBefore(request.getLoanDate())) {
            throw new CustomException(ErrorCode.LOAN_INVALID_DUE_DATE);
        }

        List<Long> bookIds = request.getLoanItems().stream()
                .map(LoanItemRequest::getBookId)
                .toList();
        Map<Long, Book> bookMap = bookRepo.findAllById(bookIds).stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));

        for (LoanItemRequest itemReq : request.getLoanItems()) {
            Book book = bookMap.get(itemReq.getBookId());
            if (book == null) {
                throw new CustomException(ErrorCode.BOOK_NOT_EXISTED);
            }
            if (!Status.ACTIVE.equals(book.getStatus())) {
                throw new CustomException(ErrorCode.BOOK_NOT_EXISTED);
            }
            if (book.getTotalQuantity() < itemReq.getQuantity()) {
                throw new CustomException(ErrorCode.TOTAL_QUANTITY_LESS_THAN_BORROWED);
            }
        }

        String loanCode = generateLoanCode();
        Loan loan = Loan.builder()
                .code(loanCode)
                .member(member)
                .loanDate(request.getLoanDate())
                .dueDate(request.getDueDate())
                .note(request.getNote())
                .status(LoanStatus.BORROWING)
                .loanItems(new ArrayList<>())
                .build();

        for (LoanItemRequest itemReq : request.getLoanItems()) {
            Book book = bookMap.get(itemReq.getBookId());

            LoanItem loanItem = LoanItem.builder()
                    .loan(loan)
                    .book(book)
                    .quantity(itemReq.getQuantity())
                    .build();
            loan.getLoanItems().add(loanItem);

            book.setTotalQuantity(book.getTotalQuantity() - itemReq.getQuantity());
        }
        loanRepo.save(loan);

        return toResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse update(Long loanId, UpdateLoanRequest request) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (LoanStatus.CANCELLED.equals(request.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (request.getNewDueDate() != null && request.getNewDueDate().isBefore(loan.getDueDate())) {
            throw new CustomException(ErrorCode.LOAN_INVALID_DUE_DATE);
        }

        boolean isTransitionToReturned = LoanStatus.RETURNED.equals(request.getStatus())
                && !LoanStatus.RETURNED.equals(loan.getStatus());
        if (isTransitionToReturned) {
            for (LoanItem item : loan.getLoanItems()) {
                Book book = item.getBook();
                book.setTotalQuantity(book.getTotalQuantity() + item.getQuantity());
            }
        }
        if (request.getNewDueDate() != null) {
            loan.setDueDate(request.getNewDueDate());
        }
        if (request.getActualReturnDate() != null) {
            loan.setActualReturnDate(request.getActualReturnDate());
        }
        if (request.getProcessingNote() != null) {
            loan.setNote(request.getProcessingNote());
        }
        loan.setStatus(request.getStatus());

        loanRepo.save(loan);
        return toResponse(loan);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
        return toResponse(loan);
    }

    @Override
    @Transactional
    public void cancelLoan(Long loanId, CancelLoanRequest request) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));

        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }

        if (LoanStatus.RETURNED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_DELETE);
        }

        for (LoanItem item : loan.getLoanItems()) {
            Book book = item.getBook();
            book.setTotalQuantity(book.getTotalQuantity() + item.getQuantity());
        }

        loan.setStatus(LoanStatus.CANCELLED);
        loan.setCancellationReason(request.getCancellationReason());
        loanRepo.save(loan);
    }

    @Override
    public void deleteLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_DELETE);
        }
        loanRepo.delete(loan);
    }

    private String generateLoanCode() {
        String prefix = "DQC";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Random random = new Random();
        String code;
        do {
            String suffix = String.format("%03d", random.nextInt(1000));
            code = prefix + timestamp + suffix;
        } while (loanRepo.existsByCode(code));
        return code;
    }

    private LoanResponse toResponse(Loan loan) {
        List<LoanItemResponse> list = loan.getLoanItems().stream()
                .map(item -> LoanItemResponse.builder()
                        .id(item.getId())
                        .bookId(item.getBook().getId())
                        .quantity(item.getQuantity())
                        .build()
                ).toList();
        return LoanResponse.builder()
                .id(loan.getId())
                .code(loan.getCode())
                .memberName(loan.getMember().getFullName())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .actualReturnDate(loan.getActualReturnDate())
                .cancellationReason(loan.getCancellationReason())
                .itemResponseList(list)
                .status(loan.getStatus())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();
    }
}
