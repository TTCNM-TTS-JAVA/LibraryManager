package org.library.manager.service;

import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(CreateAuthorRequest request);
    List<AuthorResponse>  getAuthors();
    AuthorResponse getAuthorById(Long authorId);
    AuthorResponse UpdateAuthor(Long authorId, UpdateAuthorRequest request);
    void deleteAuthor(Long authorId);
}
