package org.library.manager.model;

import lombok.Data;

/**
 * Ví dụ về một Request DTO (Data Transfer Object).
 * Package `model` chứa các class dùng để trao đổi dữ liệu giữa client và server:
 *   - Request model: nhận dữ liệu từ client gửi lên (POST/PUT body).
 *   - Response model: trả dữ liệu về cho client.
 * Tại sao không dùng Entity trực tiếp:
 *   - Kiểm soát được chính xác trường nào client được phép gửi.
 *   - Tránh lộ cấu trúc nội bộ của database ra ngoài API.
 */
@Data
public class ItemRequest {

    /** Tên item */
    private String name;

    /** Mô tả */
    private String description;
}
