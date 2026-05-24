package org.library.manager.repository;

import org.library.manager.entity.BookCategory;
import org.library.manager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    boolean existsByNameIgnoreCaseAndStatus(String name, Status status);

    @Query("SELECT bc.id FROM BookCategory bc")
    Set<Long> findAllId();

    @Query("""
    SELECT bc FROM BookCategory bc
            WHERE (:keyword IS NULL OR LOWER(bc.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
            AND (:status IS NULL OR bc.status = :status)
         """)
    Page<BookCategory> search(@Param("keyword") String keyword, @Param("status") Status status, Pageable pageable);

}
