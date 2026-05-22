package org.library.manager.model.request;

import lombok.Data;
import org.library.manager.enums.Status;

@Data
public class BookCategoryFilterRequest {
    private String keyword;
    private Status status;
}
