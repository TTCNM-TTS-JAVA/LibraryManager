package org.library.manager.service.impl;


import lombok.RequiredArgsConstructor;
import org.library.manager.entity.BookCategory;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.request.BookCategoryRequest;
import org.library.manager.model.request.DeactivationReason;
import org.library.manager.model.response.BookCategoryResponse;
import org.library.manager.repository.BookCategoryRepository;
import org.library.manager.service.BookCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepo;

    @Override
    @Transactional(readOnly = true)
    public BookCategoryResponse getById(Long id){
        return toResponse(findOrThrow(id));
    }
    @Override
    @Transactional(readOnly = true)
    public List<BookCategoryResponse> search(String keyword, Status status){
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasStatus = status != null;
        List<BookCategory> data;
        if(hasKeyword && hasStatus){
            data = bookCategoryRepo.findByNameContainingIgnoreCaseAndStatusOrderByUpdatedAtDesc(keyword.trim(), status);
        }
        else if(hasKeyword){
            data = bookCategoryRepo.findByNameContainingIgnoreCaseOrderByUpdatedAtDesc(keyword.trim());
        } else if (hasStatus) {
            data = bookCategoryRepo.findByStatusOrderByUpdatedAtDesc(status);
        }
        else {
            data = bookCategoryRepo.findAllByOrderByUpdatedAtDesc();
        }
        return data.stream().map(this::toResponse).toList();


    }
    @Override
    @Transactional
    public BookCategoryResponse create(BookCategoryRequest request){
        String name = request.getName().trim();
        if(bookCategoryRepo.existsByNameContainingIgnoreCaseAndStatus(name, Status.ACTIVE)){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        BookCategory entity = BookCategory.builder()
                .name(name)
                .description(request.getDescription())
                .status(Status.ACTIVE)
                .build();

        return toResponse(bookCategoryRepo.save(entity));

    }
    @Override
    @Transactional
    public BookCategoryResponse update(Long id, BookCategoryRequest request){
        BookCategory entity = findOrThrow(id);
        String name = request.getName().trim();
        if(bookCategoryRepo.existsByNameContainingIgnoreCaseAndStatusAndIdNot(name, Status.ACTIVE, id)){
            throw new CustomException((ErrorCode.INTERNAL_SERVER_ERROR));
        }
        entity.setName(name);
        entity.setDescription(request.getDescription());

        return toResponse(bookCategoryRepo.save(entity));


    }
    @Override
    public BookCategoryResponse deactivation(Long id, DeactivationReason reason){
        BookCategory entity = findOrThrow(id);

        if (entity.getStatus() == Status.INACTIVE) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        // Khi có Book table, chặn ngừng dùng nếu còn sách đang ACTIVE thuộc loại này


        entity.setStatus(Status.INACTIVE);
        if (reason != null) {
            entity.setDeactivationReason(reason.getDeactivationReason());
        }
        return toResponse(bookCategoryRepo.save(entity));
    }
    private BookCategory findOrThrow(Long id){
        return bookCategoryRepo.findById(id).orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));

    }
    private BookCategoryResponse toResponse(BookCategory entity){
        return BookCategoryResponse.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .updatedAt(entity.getUpdatedAt())
                .BookCountByBookCate(0L)
                .deactivationReason(entity.getDeactivationReason())
                .build();

    }

}
