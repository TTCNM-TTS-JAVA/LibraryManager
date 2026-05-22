package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.model.dto.AuthorDto;
import org.library.manager.model.request.BookRequest;
import org.library.manager.model.response.BookResponse;
import org.library.manager.repository.BookRepository;
import org.library.manager.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookResponse> filterBook(int size, int page, AuthorDto authorDto) {
        return List.of();
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        return null;
    }

    @Override
    public BookResponse createBook(BookRequest request) {
        return null;
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest request) {
        return null;
    }

    @Override
    public BookResponse deleteBook(Long bookId) {
        return null;
    }
}
