package org.library.manager.repository;

import org.library.manager.entity.BookCategory;
import org.library.manager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    boolean existsByNameContainingIgnoreCaseAndStatus(String name, Status status);

    boolean existsByNameIgnoreCaseAndStatusAndIdNot(String name, Status status, Long id);

    @Query("SELECT bc FROM BookCategory bc "
            + "WHERE (:keyword IS NULL OR LOWER(bc.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (:status IS NULL OR bc.status = :status) "
            + "ORDER BY bc.updatedAt DESC")
    List<BookCategory> search(@Param("keyword") String keyword, @Param("status") Status status);

}
