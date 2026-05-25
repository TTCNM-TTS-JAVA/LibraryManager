package org.library.manager.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class LoanItemRequest {

    @NotEmpty(message = "bookIds.not.empty")
    private List<Long> bookIds;

    @Min(value = 1, message = "quantity.min.value")
    private int quantity;
}
