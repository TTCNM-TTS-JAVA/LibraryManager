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
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.LoanItemRequest;
import org.library.manager.model.request.UpdateLoanRequest;
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
    public Page<LoanResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return loanRepo.findAllWithMember(pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LoanResponse> filter(int page, int size, LoanFilterRequest request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return loanRepo.searchWithMember(
                request.getMemberKeyword(),
                request.getStatus(),
                request.getFromDate(),
                request.getToDate(),
                pageable
        ).map(this::toResponse);
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
            LoanItem loanItem = LoanItem.builder()
                    .loan(loan)
                    .quantity(itemReq.getQuantity())
                    .build();

            for (Long bookId : itemReq.getBookIds()) {
                Book book = bookMap.get(bookId);
                loanItem.getBooks().add(book);
                book.setTotalQuantity(book.getTotalQuantity() - itemReq.getQuantity());
            }
            loan.getLoanItems().add(loanItem);
        }

        loanRepo.save(loan);
        return toResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse update(Long loanId, UpdateLoanRequest request) {
        Loan loan = findWithDetailsOrThrow(loanId);

        if (LoanStatus.CANCELLED.equals(loan.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (LoanStatus.CANCELLED.equals(request.getStatus())) {
            throw new CustomException(ErrorCode.LOAN_CANNOT_UPDATE_CANCELLED);
        }
        if (request.getNewDueDate() != null && request.getNewDueDate().isBefore(loan.getDueDate())) {
            throw new CustomException(ErrorCode.LOAN_INVALID_DUE_DATE);
        }

        boolean becomingReturned = LoanStatus.RETURNED.equals(request.getStatus())
                && !LoanStatus.RETURNED.equals(loan.getStatus());
        if (becomingReturned) {
            restoreStock(loan);
        }

        if (request.getNewDueDate() != null)      loan.setDueDate(request.getNewDueDate());
        if (request.getActualReturnDate() != null) loan.setActualReturnDate(request.getActualReturnDate());
        if (request.getProcessingNote() != null)   loan.setNote(request.getProcessingNote());
        loan.setStatus(request.getStatus());

        loanRepo.save(loan);
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
        loanRepo.save(loan);
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
                .flatMap(item -> item.getBookIds().stream())
                .distinct()
                .toList();
        return bookRepo.findAllById(bookIds).stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
    }

    private void validateStock(List<LoanItemRequest> items, Map<Long, Book> bookMap) {
        for (LoanItemRequest itemReq : items) {
            for (Long bookId : itemReq.getBookIds()) {
                Book book = bookMap.get(bookId);
                if (book == null || !Status.ACTIVE.equals(book.getStatus())) {
                    throw new CustomException(ErrorCode.BOOK_NOT_EXISTED);
                }
                if (book.getTotalQuantity() < itemReq.getQuantity()) {
                    throw new CustomException(ErrorCode.TOTAL_QUANTITY_LESS_THAN_BORROWED);
                }
            }
        }
    }

    private void restoreStock(Loan loan) {
        for (LoanItem item : loan.getLoanItems()) {
            for (Book book : item.getBooks()) {
                book.setTotalQuantity(book.getTotalQuantity() + item.getQuantity());
            }
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
                        .bookIds(item.getBooks().stream().map(Book::getId).toList())
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
