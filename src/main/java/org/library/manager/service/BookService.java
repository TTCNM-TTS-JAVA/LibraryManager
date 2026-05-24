package org.library.manager.service;

import org.library.manager.model.dto.BookDto;
import org.library.manager.model.request.BookRequest;
import org.library.manager.model.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> filterBook(int size, int page, BookDto book);
    BookResponse getBookById(Long bookId);
    BookResponse createBook(BookRequest request);
    BookResponse updateBook(Long bookId,BookRequest request);
    void deleteBook(Long bookId);
}
