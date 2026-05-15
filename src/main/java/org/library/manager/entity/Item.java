package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ví dụ về một Entity class trong Spring Data JPA.
 * Entity là class ánh xạ trực tiếp với một bảng trong cơ sở dữ liệu.
 * Mỗi instance của class tương ứng với một dòng trong bảng đó.
 * Quy tắc:
 *   - Đặt @Entity và @Table để khai báo tên bảng.
 *   - Dùng Lombok (@Data, @Builder, ...) để tránh viết boilerplate.
 *   - Không đặt logic nghiệp vụ vào đây — entity chỉ giữ dữ liệu.
 */
@Entity
@Table(name = "items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /** Khóa chính, tự tăng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tên item, bắt buộc */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /** Mô tả chi tiết, không bắt buộc */
    @Column(name = "description", length = 500)
    private String description;

    /** Trạng thái: "active" hoặc "inactive" */
    @Column(name = "status", nullable = false, length = 20)
    private String status;
}
