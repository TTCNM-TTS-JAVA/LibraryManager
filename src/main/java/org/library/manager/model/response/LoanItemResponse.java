package org.library.manager.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoanItemResponse {

    private Long id;

    private Long bookId;

    private int quantity;
}
