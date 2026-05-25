package org.library.manager.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.library.manager.enums.LoanStatus;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateLoanRequest {
    @NotNull(message = "memberId.not.null")
    private Long memberId;

    @NotNull(message = "loan.date.not.null")
    private LocalDate loanDate;

    @NotNull(message = "due.date.not.null")
    private LocalDate dueDate;

    @NotNull(message = "loan.item.empty")
    @Valid
    private List<LoanItemRequest> loanItems;

    @Size(max = 500, message = "max.500.character")
    private String note;

    private LoanStatus status = LoanStatus.BORROWING;

}
