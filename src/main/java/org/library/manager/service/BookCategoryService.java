package org.library.manager.service;

import org.library.manager.model.request.BookCategoryFilterRequest;
import org.library.manager.model.request.BookCategoryRequest;
import org.library.manager.model.request.DeactivationReason;
import org.library.manager.model.response.BookCategoryResponse;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryResponse> filter(int size, int page, BookCategoryFilterRequest request);

    BookCategoryResponse create(BookCategoryRequest request);

    BookCategoryResponse update(Long id, BookCategoryRequest request);

    BookCategoryResponse getById(Long id);

    BookCategoryResponse deactivation(Long id, DeactivationReason reason);






}
