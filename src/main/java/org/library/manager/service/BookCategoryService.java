package org.library.manager.service;

import org.library.manager.enums.Status;
import org.library.manager.model.request.BookCategoryRequest;
import org.library.manager.model.request.DeactivationReason;
import org.library.manager.model.response.BookCategoryResponse;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryResponse> search(String keyword, Status status);

    BookCategoryResponse create(BookCategoryRequest request);

    BookCategoryResponse update(Long id, BookCategoryRequest request);

    BookCategoryResponse getById(Long id);

    BookCategoryResponse deactivation(Long id, DeactivationReason reason);






}
