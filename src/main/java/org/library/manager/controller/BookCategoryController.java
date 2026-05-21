package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.request.BookCategoryFilterRequest;
import org.library.manager.model.request.BookCategoryRequest;
import org.library.manager.model.request.DeactivationReason;
import org.library.manager.model.response.BookCategoryResponse;
import org.library.manager.service.BookCategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-categories")
@RequiredArgsConstructor
public class BookCategoryController {
    private final BookCategoryService service;
    @PostMapping("/filter")
    public ResponseEntity<List<BookCategoryResponse>> filter(@RequestParam int size,
                                                             @RequestParam int page,
                                                             @RequestBody BookCategoryFilterRequest request){
        return ResponseEntity.ok(service.filter(size, page, request));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BookCategoryResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping
    public ResponseEntity<BookCategoryResponse> create(@Valid @RequestBody BookCategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCategoryResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody BookCategoryRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }
    @PatchMapping("/{id}/deactive")
    public ResponseEntity<BookCategoryResponse> deactivation(@PathVariable Long id,
                                                             @Valid @RequestBody DeactivationReason reason){
        return ResponseEntity.ok(service.deactivation(id, reason));


    }



}
