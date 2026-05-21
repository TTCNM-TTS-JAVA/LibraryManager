package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.library.manager.enums.Status;

@Data
public class BookCategoryRequest {
    @NotBlank(message = "Tên loại sách là bắt buộc")
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    private Status status = Status.ACTIVE;
}
