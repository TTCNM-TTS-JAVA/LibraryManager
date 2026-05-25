package org.library.manager.model.response;

import lombok.Builder;
import lombok.Data;
import org.library.manager.enums.LoanStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LoanResponse {
    private Long id;

    private String code;

    private String memberName;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private List<LoanItemResponse> itemResponseList;

    private LoanStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
