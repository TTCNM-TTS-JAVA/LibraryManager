package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.UpdateLoanRequest;
import org.library.manager.model.response.LoanResponse;
import org.library.manager.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService service;

    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(service.getAllLoan(page, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<LoanResponse>> filter(
            @RequestParam int page,
            @RequestParam int size,
            @RequestBody LoanFilterRequest request) {
        return ResponseEntity.ok(service.filter(page, size, request));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long loanId) {
        return ResponseEntity.ok(service.getLoanById(loanId));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody @Valid CreateLoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<LoanResponse> update(
            @PathVariable Long loanId,
            @RequestBody @Valid UpdateLoanRequest request) {
        return ResponseEntity.ok(service.update(loanId, request));
    }

    @PatchMapping("/{loanId}/cancel")
    public ResponseEntity<Void> cancelLoan(
            @PathVariable Long loanId,
            @RequestBody @Valid CancelLoanRequest request) {
        service.cancelLoan(loanId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long loanId) {
        service.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }
}
