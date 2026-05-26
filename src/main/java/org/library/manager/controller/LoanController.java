package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.request.CancelLoanRequest;
import org.library.manager.model.request.CreateLoanRequest;
import org.library.manager.model.request.ExtendLoanRequest;
import org.library.manager.model.request.LoanFilterRequest;
import org.library.manager.model.request.ReturnLoanRequest;
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

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @ModelAttribute LoanFilterRequest filter) {
        return ResponseEntity.ok(loanService.getAll(page, size, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody @Valid CreateLoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(request));
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnLoan(
            @PathVariable Long id,
            @RequestBody @Valid ReturnLoanRequest request) {
        return ResponseEntity.ok(loanService.returnLoan(id, request));
    }

    @PatchMapping("/{id}/extend")
    public ResponseEntity<LoanResponse> extendLoan(
            @PathVariable Long id,
            @RequestBody @Valid ExtendLoanRequest request) {
        return ResponseEntity.ok(loanService.extendLoan(id, request));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<LoanResponse> cancel(
            @PathVariable Long id,
            @RequestBody @Valid CancelLoanRequest request) {
        return ResponseEntity.ok(loanService.cancel(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
