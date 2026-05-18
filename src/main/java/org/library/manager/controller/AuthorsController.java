package org.library.manager.controller;

import jakarta.validation.Valid;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.ApiResponse;
import org.library.manager.model.response.AuthorResponse;
import org.library.manager.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    ApiResponse<AuthorResponse> createAuthor(@RequestBody @Valid CreateAuthorRequest request){
        return ApiResponse.<AuthorResponse>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<AuthorResponse> > getAuthors(){
        return ApiResponse.<List<AuthorResponse> >builder()
                .result(authorService.getAuthors())
                .build();
    }

    @GetMapping("/{authorId}")
    ApiResponse<AuthorResponse> getAuthorById(@PathVariable Long authorId){
        return ApiResponse.<AuthorResponse>builder()
                .result(authorService.getAuthorById(authorId))
                .build();
    }

    @PutMapping("/{authorId}")
    ApiResponse<AuthorResponse> updateAuthor(@PathVariable Long authorId,@RequestBody UpdateAuthorRequest request){
        return ApiResponse.<AuthorResponse>builder()
                .result(authorService.UpdateAuthor(authorId,request))
                .build();
    }

    @DeleteMapping("/{authorId}")
    ApiResponse<String> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ApiResponse.<String>builder()
                .result( "Author has been Inactive")
                .build();
    }
}
