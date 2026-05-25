package org.library.manager.controller;


import lombok.RequiredArgsConstructor;
import org.library.manager.model.response.LoanResponse;
import org.library.manager.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService service;
    @GetMapping("/{loanId}")
    private ResponseEntity<LoanResponse> getById(@PathVariable Long loanId){
        return ResponseEntity.ok(service.getLoanById(loanId));

    }
    @DeleteMapping("/{loanId}")
    private ResponseEntity<Void> deleteLoan(@PathVariable Long loanId){
        service.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    private ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(service.getAllLoan(page, size));

    }






}
