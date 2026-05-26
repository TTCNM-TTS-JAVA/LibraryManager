package org.library.manager.model.response;

import lombok.Builder;
import lombok.Getter;
import org.library.manager.enums.LoanStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class LoanResponse {

    private Long id;

    private String code;

    private String memberName;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private LocalDate actualReturnDate;

    private String note;

    private String cancellationReason;

    private LoanStatus status;

    private List<LoanItemResponse> loanItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
