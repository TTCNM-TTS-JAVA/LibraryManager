package org.library.manager.repository;

import org.library.manager.entity.Author;
import org.library.manager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByFullName(String fullName);

    @Query("SELECT a.id FROM Author a")
    Set<Long> findAllId();

    @Query("SELECT a FROM Author a WHERE (:search IS NULL OR (a.fullName LIKE %:search%) " +
            "OR (a.penName LIKE %:search%))" +
            "AND (:status IS NULL OR a.status = :status)")
    Page<Author> findByFilter(String search, Status status, Pageable pageable);
}
