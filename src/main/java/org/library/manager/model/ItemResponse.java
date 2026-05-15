package org.library.manager.model;

import lombok.Builder;
import lombok.Data;

/**
 * Ví dụ về một Response DTO.
 * Dùng @Builder để service có thể tạo đối tượng theo từng trường
 * mà không cần setter. Phù hợp khi ánh xạ từ Entity sang DTO.
 */
@Data
@Builder
public class ItemResponse {

    /** ID của item */
    private Long id;

    /** Tên item */
    private String name;

    /** Mô tả */
    private String description;

    /** Trạng thái hiện tại */
    private String status;
}
