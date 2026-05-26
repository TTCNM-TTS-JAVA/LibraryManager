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
import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.ExtendLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.LoanItemRequest;
import org.library.manager.model.request.ReturnLoanRequest;
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
    @Transactional(readOnly = true)
    public Page<LoanResponse> getAll(int page, int size, LoanFilterRequest filter) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Loan> loanPage = loanRepo.searchWithMember(
                filter.getMemberKeyword(),
                filter.getStatus(),
                filter.getFromDate(),
                filter.getToDate(),
                pageable
        );
        List<Long> ids = loanPage.getContent().stream().map(Loan::getId).toList();
        if (!ids.isEmpty()) {
            loanRepo.findAllByIdsWithLoanItems(ids);
        }
        return loanPage.map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse getById(Long loanId) {
        Loan loan = findWithDetailsOrThrow(loanId);
        return toResponse(loan);
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

        Map<Long, Book> bookMap = loadBookMap(request.getLoanItems());
        validateStock(request.getLoanItems(), bookMap);

        Loan loan = Loan.builder()
                .code(generateLoanCode())
                .member(member)
                .loanDate(request.getLoanDate())
                .dueDate(request.getDueDate())
                .note(request.getNote())
                .status(LoanStatus.BORROWING)
                .build();

        for (LoanItemRequest itemReq : request.getLoanItems()) {
            Book book = bookMap.get(itemReq.getBookId());
            LoanItem loanItem = LoanItem.builder()
                    .loan(loan)
                    .book(book)
                    .quantity(itemReq.getQuantity())
                    .build();
            book.setTotalQuantity(book.getTotalQuantity() - itemReq.getQuantity());
            loan.getLoanItems().add(loanItem);
        }

        loanRepo.save(loan);
        return toResponse(findWithDetailsOrThrow(loan.getId()));
    }

    @Override
    @Transactional
    public LoanResponse returnLoan(Long loanId, ReturnLoanRequest request) {
        Loan loan = findWithDetailsOrThrow(loanId);

        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (LoanStatus.RETURNED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_ALREADY_RETURNED);
        }

        restoreStock(loan);
        loan.setStatus(LoanStatus.RETURNED);
        loan.setActualReturnDate(request.getActualReturnDate());
        if (request.getProcessingNote() != null) loan.setNote(request.getProcessingNote());

        return toResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse extendLoan(Long loanId, ExtendLoanRequest request) {
        Loan loan = findWithDetailsOrThrow(loanId);

        if (!LoanStatus.BORROWING.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_EXTEND);
        }
        if (!request.getNewDueDate().isAfter(loan.getDueDate())) {
            throw new CustomException(ErrorCode.LOAN_INVALID_DUE_DATE);
        }

        loan.setDueDate(request.getNewDueDate());
        return toResponse(loan);
    }

    @Override
    @Transactional
    public void cancel(Long loanId, CancelLoanRequest request) {
        Loan loan = findWithDetailsOrThrow(loanId);

        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (LoanStatus.RETURNED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_DELETE);
        }

        restoreStock(loan);
        loan.setStatus(LoanStatus.CANCELLED);
        loan.setCancellationReason(request.getCancellationReason());
    }

    @Override
    @Transactional
    public void delete(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));

        if (!LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_DELETE);
        }

        loanRepo.delete(loan);
    }

    private Loan findWithDetailsOrThrow(Long loanId) {
        return loanRepo.findByIdWithDetails(loanId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOAN_NOT_FOUND));
    }

    private Map<Long, Book> loadBookMap(List<LoanItemRequest> items) {
        List<Long> bookIds = items.stream()
                .map(LoanItemRequest::getBookId)
                .distinct()
                .toList();
        return bookRepo.findAllById(bookIds).stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
    }

    private void validateStock(List<LoanItemRequest> items, Map<Long, Book> bookMap) {
        for (LoanItemRequest itemReq : items) {
            Book book = bookMap.get(itemReq.getBookId());
            if (book == null || !Status.ACTIVE.equals(book.getStatus())) {
                throw new CustomException(ErrorCode.BOOK_NOT_EXISTED);
            }
            if (book.getTotalQuantity() < itemReq.getQuantity()) {
                throw new CustomException(ErrorCode.TOTAL_QUANTITY_LESS_THAN_BORROWED);
            }
        }
    }

    private void restoreStock(Loan loan) {
        for (LoanItem item : loan.getLoanItems()) {
            item.getBook().setTotalQuantity(item.getBook().getTotalQuantity() + item.getQuantity());
        }
    }

    private String generateLoanCode() {
        String prefix = "DQC";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Random random = new Random();
        String code;
        do {
            code = prefix + timestamp + String.format("%03d", random.nextInt(1000));
        } while (loanRepo.existsByCode(code));
        return code;
    }

    private LoanResponse toResponse(Loan loan) {
        List<LoanItemResponse> items = loan.getLoanItems().stream()
                .map(item -> LoanItemResponse.builder()
                        .id(item.getId())
                        .bookId(item.getBookId())
                        .quantity(item.getQuantity())
                        .build())
                .toList();

        return LoanResponse.builder()
                .id(loan.getId())
                .code(loan.getCode())
                .memberName(loan.getMember().getFullName())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .actualReturnDate(loan.getActualReturnDate())
                .note(loan.getNote())
                .cancellationReason(loan.getCancellationReason())
                .status(loan.getStatus())
                .loanItems(items)
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();
    }
}
