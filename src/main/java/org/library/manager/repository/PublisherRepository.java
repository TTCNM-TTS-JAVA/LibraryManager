package org.library.manager.repository;


import org.library.manager.entity.Publisher;
import org.library.manager.enums.PublisherStatus;
import org.library.manager.model.response.PublisherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    boolean existsByNameIgnoreCaseAndStatus(String name, PublisherStatus status);

    boolean existsByNameIgnoreCaseAndStatusAndIdNot(String name, PublisherStatus status, Long id);

    @Query("SELECT p from Publisher p "
            + "WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%' )))"
            + "AND (:status IS NULL OR p.status = :status)"
            + "ORDER BY p.updatedAt DESC")
    Page<Publisher> search(@Param("keyword") String keyword, @Param("status") PublisherStatus status, Pageable pageable);
}
