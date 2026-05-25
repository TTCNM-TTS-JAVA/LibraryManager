package org.library.manager.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoanItemResponse {

    private Long id;

    private List<Long> bookIds;

    private int quantity;
}
