package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.dto.BookDto;
import org.library.manager.model.request.BookRequest;
import org.library.manager.model.response.BookResponse;
import org.library.manager.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/filter")
    public ResponseEntity<List<BookResponse>> filterBook(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page,
            @RequestBody BookDto bookDto){

        return ResponseEntity.ok(
                bookService.filterBook(size,page,bookDto)
        );
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(
            @PathVariable Long bookId){

        return ResponseEntity.ok(
                bookService.getBookById(bookId)
        );
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @RequestBody @Valid BookRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(request));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long bookId,
            @RequestBody @Valid BookRequest request){

        return ResponseEntity.ok(
                bookService.updateBook(bookId,request)
        );
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long bookId){

        bookService.deleteBook(bookId);

        return ResponseEntity.noContent().build();
    }
}