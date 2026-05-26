package org.library.manager.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExtendLoanRequest {

    @NotNull(message = "new.due.date.not.null")
    private LocalDate newDueDate;
}
