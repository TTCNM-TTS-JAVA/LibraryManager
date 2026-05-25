package org.library.manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.library.manager.enums.LoanStatus;

import java.time.LocalDate;

@Data
public class UpdateLoanRequest {

    private LocalDate newDueDate;

    private LocalDate actualReturnDate;

    @NotNull(message = "loan.status.not.null")
    private LoanStatus status;

    @Size(max = 500, message = "max.500.character")
    private String processingNote;
}
