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

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(loanService.getAll(page, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<LoanResponse>> filter(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody LoanFilterRequest request) {
        return ResponseEntity.ok(loanService.filter(page, size, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody @Valid CreateLoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateLoanRequest request) {
        return ResponseEntity.ok(loanService.update(id, request));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable Long id,
            @RequestBody @Valid CancelLoanRequest request) {
        loanService.cancel(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
