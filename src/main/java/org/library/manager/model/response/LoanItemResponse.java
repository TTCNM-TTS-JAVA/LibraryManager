package org.library.manager.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanItemResponse {
    private Long id;

    private Long bookId;

    private Integer quantity;
}
