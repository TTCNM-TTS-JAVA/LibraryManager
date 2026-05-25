package org.library.manager.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CancelLoanRequest {
    @NotNull(message = "cancel.loan.not.null")
    @Size(max = 500)
    private String cancellationReason;

}
