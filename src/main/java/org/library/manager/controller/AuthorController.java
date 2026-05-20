package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.AuthorDto;
import org.library.manager.model.request.CreateAuthorRequest;
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

    @GetMapping("/filter")
    public ResponseEntity<List<AuthorResponse>> filter(@RequestParam int size , @RequestParam int page, @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.filter(size,page, authorDto));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long authorId){
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest request){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(authorService.createAuthor(request));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> UpdateAuthor(@PathVariable Long authorId, @RequestBody @Valid UpdateAuthorRequest request){
        return ResponseEntity.ok(authorService.updateAuthor(authorId,request));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }
}
