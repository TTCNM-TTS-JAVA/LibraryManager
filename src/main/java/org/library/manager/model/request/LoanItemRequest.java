package org.library.manager.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanItemRequest {
    @NotNull(message = "bookId.not.null")
    private Long bookId;

    @NotNull(message = "quantity.not.null")
    @Min(value = 1, message = "quantity.min.value")
    private Integer quantity;
}
