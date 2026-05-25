package org.library.manager.model.request;

import lombok.Data;
import org.library.manager.enums.LoanStatus;

import java.time.LocalDate;

@Data
public class LoanFilterRequest {
    private String memberKeyword;

    private LoanStatus status;

    private LocalDate fromDate;

    private LocalDate toDate;


}
