package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.enums.Status;
import org.library.manager.model.AuthorWithBook;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.FindByFullName;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.AuthorResponse;
import org.library.manager.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/allAuthor")
    public ResponseEntity<List<AuthorResponse>> getAllAuthor(){
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long authorId){
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @GetMapping("/fullName")
    public ResponseEntity<AuthorResponse> getByFullName(@RequestBody FindByFullName fullName){
        return ResponseEntity.ok(authorService.getAuthorByFullName(fullName));
    }

    @GetMapping("/penName")
    public ResponseEntity<AuthorResponse> getAuthorByPenName(@RequestBody FindByFullName penName){
        return ResponseEntity.ok(authorService.getAuthorByPenName(penName));
    }

    @GetMapping("/status")
    public ResponseEntity<List<AuthorResponse>> filterStatus(@RequestBody FindByFullName status){
        return ResponseEntity.ok(authorService.filterStatus(status));
    }

    @GetMapping("/authorWithBook")
    public ResponseEntity<List<AuthorWithBook>> authorWithBook(){
        return ResponseEntity.ok(authorService.authorWithBook());
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest request){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(authorService.createAuthor(request));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> UpdateAuthor(@PathVariable Long authorId, @RequestBody UpdateAuthorRequest request){
        return ResponseEntity.ok(authorService.updateAuthor(authorId,request));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }
}
