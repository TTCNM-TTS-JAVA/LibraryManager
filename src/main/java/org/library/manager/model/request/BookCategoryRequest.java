package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.library.manager.enums.Status;

@Data
public class BookCategoryRequest {
    @NotBlank(message = "{name.not.null}")
    @Size(max = 100, message = "{max.100.character}")
    private String name;

    @Size(max = 500, message = "{max.500.character}")
    private String description;

    private Status status = Status.ACTIVE;
}
