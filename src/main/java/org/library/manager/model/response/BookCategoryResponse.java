package org.library.manager.model.response;

import lombok.Builder;
import lombok.Data;
import org.library.manager.enums.Status;

import java.time.LocalDateTime;

@Data
@Builder
public class BookCategoryResponse {
    private String name;

    private String description;

    private Long BookCountByBookCate;

    private Status status;

    private LocalDateTime updatedAt;

    private String deactivationReason;

}
