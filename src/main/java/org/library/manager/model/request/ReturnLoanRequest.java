package org.library.manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnLoanRequest {

    @NotNull(message = "actual.return.date.not.null")
    private LocalDate actualReturnDate;

    @Size(max = 500, message = "max.500.character")
    private String processingNote;
}
