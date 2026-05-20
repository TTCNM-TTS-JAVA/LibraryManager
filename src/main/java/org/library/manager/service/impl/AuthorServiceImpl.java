package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Author;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.AuthorWithBook;
import org.library.manager.model.ListBook;
import org.library.manager.model.request.CreateAuthorRequest;
import org.library.manager.model.request.FindByFullName;
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
    public List<AuthorResponse> getAllAuthor() {
        Pageable pageFormat = PageRequest.of(1,5);
        Page<Author> authors = authorRepository.findAll(pageFormat);
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
    public AuthorResponse getAuthorByFullName(FindByFullName fullName) {
        Author author = authorRepository.findByFullName(fullName.getFullName())
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));

        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .penName(author.getPenName())
                .country(author.getPenName())
                .shortDescription(author.getShortDescription())
                .status(author.getStatus())
                .createAt(author.getCreateAt())
                .updateAt(author.getUpdateAt())
                .build();
    }

    @Override
    public AuthorResponse getAuthorByPenName(FindByFullName penName) {
        Author author = authorRepository.findByPenName(penName.getPenName())
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));

        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .penName(author.getPenName())
                .country(author.getPenName())
                .shortDescription(author.getShortDescription())
                .status(author.getStatus())
                .createAt(author.getCreateAt())
                .updateAt(author.getUpdateAt())
                .build();
    }

    @Override
    public List<AuthorResponse> filterStatus(FindByFullName status) {
        List<Author> authors = authorRepository.findByStatus(status.getStatus());

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
    public List<AuthorWithBook> authorWithBook() {
        List<AuthorWithBook> authors = authorRepository.findAuthorWithBook();
        return authors.stream()
                .map(x -> new AuthorWithBook(
                        x.getFullname(),
                        x.getPenName(),
                        x.getBooks()
                                .stream()
                                .map(y-> new ListBook(
                                        y.getNameBook()
                                ))
                                .toList()
                ))
                .toList();
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {

        if(authorRepository.existsByFullName(request.getFullName())
                || authorRepository.existsByPenName(request.getPenName())){
            throw new RuntimeException("Author is existsed");
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

        if(authorRepository.existsByFullName(request.getFullName())
                || authorRepository.existsByPenName(request.getPenName())){
            throw new RuntimeException("Author is existsed");
        }

        Author author = authorRepository.findById(authorId)
                .orElseThrow(()-> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));

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
