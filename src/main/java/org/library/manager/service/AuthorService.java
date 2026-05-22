package org.library.manager.service;

import org.library.manager.model.dto.AuthorDto;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    List<AuthorResponse> filter(int size, int page, AuthorDto authorDto);
    AuthorResponse getAuthorById(Long authorId);
    AuthorResponse createAuthor(CreateAuthorRequest request);
    AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request);
    void deleteAuthor(Long authorId);
}
