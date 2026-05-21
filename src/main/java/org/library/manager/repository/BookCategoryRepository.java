package org.library.manager.repository;

import org.library.manager.entity.BookCategory;
import org.library.manager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    boolean existsByNameContainingIgnoreCaseAndStatus(String name, Status status);

    boolean existsByNameContainingIgnoreCaseAndStatusAndIdNot(String name, Status status, Long id);

    List<BookCategory> findByNameContainingIgnoreCaseAndStatusOrderByUpdatedAtDesc(String keyword, Status status);

    List<BookCategory> findByNameContainingIgnoreCaseOrderByUpdatedAtDesc(String name);

    List<BookCategory> findByStatusOrderByUpdatedAtDesc(Status status);

    List<BookCategory> findAllByOrderByUpdatedAtDesc();


}
