package org.library.manager.service.impl;

import org.library.manager.entity.Authors;
import org.library.manager.enums.ErrorCode;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.AuthorResponse;
import org.library.manager.repository.AuthorRepository;
import org.library.manager.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        if(authorRepository.existsByfullName(request.getFullName())){
            throw new CustomException(ErrorCode.AUTHOR_EXISTED);
        }
        Authors authors = Authors.builder()
                .fullName(request.getFullName())
                .penName(request.getPenName())
                .country(request.getCountry())
                .description(request.getDescription())
                .status(request.getStatus())
                .inactiveReason(request.getInactiveReason())
                .build();

        authorRepository.save(authors);

        return AuthorResponse.builder()
                .id(authors.getId())
                .fullName(authors.getFullName())
                .penName(authors.getPenName())
                .country(authors.getCountry())
                .description(authors.getDescription())
                .status(authors.getStatus())
                .inactiveReason(authors.getInactiveReason())
                .createdAt(authors.getCreatedAt())
                .updatedAt(authors.getUpdatedAt())
                .build();
    }

    @Override
    public List<AuthorResponse> getAuthors() {
        List<Authors> authors = authorRepository.findAll();

        return authors.stream()
                .map(author -> AuthorResponse.builder()
                        .id(author.getId())
                        .fullName(author.getFullName())
                        .penName(author.getPenName())
                        .country(author.getCountry())
                        .description(author.getDescription())
                        .status(author.getStatus())
                        .inactiveReason(author.getInactiveReason())
                        .createdAt(author.getCreatedAt())
                        .updatedAt(author.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public AuthorResponse getAuthorById(Long authorId) {
        Authors authors = authorRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_EXISTED));

        return AuthorResponse.builder()
                .id(authors.getId())
                .fullName(authors.getFullName())
                .penName(authors.getPenName())
                .country(authors.getCountry())
                .description(authors.getDescription())
                .status(authors.getStatus())
                .inactiveReason(authors.getInactiveReason())
                .createdAt(authors.getCreatedAt())
                .updatedAt(authors.getUpdatedAt())
                .build();
    }

    @Override
    public AuthorResponse UpdateAuthor(Long authorId, UpdateAuthorRequest request) {

        Authors authors = authorRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_EXISTED));

        authors.setFullName(request.getFullName());
        authors.setPenName(request.getPenName());
        authors.setCountry(request.getCountry());
        authors.setDescription(request.getDescription());
        authors.setStatus(request.getStatus());
        authors.setInactiveReason(request.getInactiveReason());

        authorRepository.save(authors);

        return AuthorResponse.builder()
                .fullName(request.getFullName())
                .penName(request.getPenName())
                .country(request.getCountry())
                .description(request.getDescription())
                .status(request.getStatus())
                .inactiveReason(request.getInactiveReason())
                .build();
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Authors authors = authorRepository.findById(authorId).orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_EXISTED));
         authors.setStatus(Status.INACTIVE);
         authorRepository.save(authors);
    }
}
