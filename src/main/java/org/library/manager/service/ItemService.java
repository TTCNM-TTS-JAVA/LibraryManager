package org.library.manager.service;

import org.library.manager.model.ItemRequest;
import org.library.manager.model.ItemResponse;

import java.util.List;

/**
 * Ví dụ về một Service interface.
 * Package `service` chứa các interface định nghĩa nghiệp vụ của từng module.
 * Controller chỉ phụ thuộc vào interface này, không biết đến class cài đặt cụ thể.
 * Lợi ích:
 *   - Dễ mock khi viết unit test (@MockBean / Mockito).
 *   - Tuân theo nguyên tắc Dependency Inversion (SOLID).
 *   - Có thể đổi implementation mà không ảnh hưởng đến controller.
 */
public interface ItemService {

    /** Lấy toàn bộ danh sách item đang active */
    List<ItemResponse> getAll();

    /** Lấy thông tin một item theo ID */
    ItemResponse getById(Long id);

    /** Tạo mới một item */
    ItemResponse create(ItemRequest request);

    /** Cập nhật thông tin item theo ID */
    ItemResponse update(Long id, ItemRequest request);

    /** Xóa item */
    void delete(Long id);
}
