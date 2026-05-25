package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CancelLoanRequest {

    @NotBlank(message = "cancel.reason.not.blank")
    @Size(max = 500, message = "max.500.character")
    private String cancellationReason;
}
