package org.library.manager.repository;

import org.library.manager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Ví dụ về một Repository interface.
 * Package `repository` chứa các interface kế thừa JpaRepository<Entity, IdType>.
 * Spring Data JPA tự sinh implementation — không cần viết SQL cho CRUD cơ bản.
 * Có thể mở rộng query bằng cách:
 *   1. Đặt tên method theo quy ước Spring Data (findBy..., existsBy..., deleteBy...).
 *   2. Dùng @Query để viết JPQL hoặc native SQL nếu cần query phức tạp.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Lấy danh sách item theo trạng thái.
     * Spring Data tự sinh: SELECT * FROM items WHERE status = ?
     */
    List<Item> findByStatus(String status);

    /**
     * Kiểm tra item có tồn tại theo tên không.
     * Dùng để validate dữ liệu trước khi lưu.
     */
    boolean existsByName(String name);
}
