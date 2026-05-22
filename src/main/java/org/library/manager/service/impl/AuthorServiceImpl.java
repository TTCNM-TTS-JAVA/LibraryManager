package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Author;
import org.library.manager.enums.Status;
import org.library.manager.exception.BadRequestException;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.dto.AuthorDto;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.UpdateAuthorRequest;
import org.library.manager.model.response.AuthorResponse;
import org.library.manager.repository.AuthorRepository;
import org.library.manager.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorResponse> filter(int size, int page, AuthorDto authorDto) {
        Pageable pageFormat = PageRequest.of(page -1,size);
        Page<Author> authors = authorRepository
                .findByFilter(authorDto.getSearch(),
                        authorDto.getStatus(),
                        pageFormat);
        return authors.stream()
                .map(x -> AuthorResponse.builder()
                        .id(x.getId())
                        .fullName(x.getFullName())
                        .penName(x.getPenName())
                        .country(x.getCountry())
                        .shortDescription(x.getShortDescription())
                        .status(x.getStatus())
                        .createAt(x.getCreateAt())
                        .updateAt(x.getUpdateAt())
                        .build())
                .toList();
    }

    @Override
    public AuthorResponse getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));

        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .penName(author.getPenName())
                .country(author.getCountry())
                .shortDescription(author.getShortDescription())
                .status(author.getStatus())
                .createAt(author.getCreateAt())
                .updateAt(author.getUpdateAt())
                .build();
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {

        if(authorRepository.existsByFullName(request.getFullName())){
            throw new BadRequestException("author.already.exists");
        }

               Author author = Author.builder()
                .fullName(request.getFullName())
                .penName(request.getPenName())
                .country(request.getCountry())
                .shortDescription(request.getShortDescription())
                .status(request.getStatus())
                .build();
        authorRepository.save(author);
        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .penName(author.getPenName())
                .country(author.getCountry())
                .shortDescription(author.getShortDescription())
                .status(author.getStatus())
                .createAt(author.getCreateAt())
                .updateAt(author.getUpdateAt())
                .build();
    }

    @Override
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));

        if(authorRepository.existsByFullName(request.getFullName())
                && !request.getFullName().equals(author.getFullName())){
            throw new BadRequestException("author.already.exists");
        }

        author.setFullName(request.getFullName());
        author.setPenName(request.getPenName());
        author.setCountry(request.getCountry());
        author.setCountry(request.getCountry());
        author.setShortDescription(request.getShortDescription());
        author.setStatus(request.getStatus());

        authorRepository.save(author);

        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .penName(author.getPenName())
                .country(author.getCountry())
                .shortDescription(author.getShortDescription())
                .status(author.getStatus())
                .createAt(author.getCreateAt())
                .updateAt(author.getUpdateAt())
                .build();
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        author.setStatus(Status.INACTIVE);

        authorRepository.save(author);
    }
}
