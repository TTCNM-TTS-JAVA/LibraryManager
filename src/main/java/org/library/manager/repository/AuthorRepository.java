package org.library.manager.repository;

import org.library.manager.entity.Author;
import org.library.manager.enums.Status;
import org.library.manager.model.AuthorWithBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByFullName(String fullName);

    @Query("SELECT DISTINCT a.fullName, a.penname FROM Author a JOIN FETCH a.books")
    List<AuthorWithBook> findAuthorWithBook();

    @Query("SELECT a FROM Author a WHERE (:fullName IS NULL OR (a.fullName LIKE CONCAT('%',:fullName,'%'))) " +
            "AND (:penName IS NULL OR (a.penName LIKE ('%' || :penName || '%')))" +
            "AND (:status IS NULL OR (a.status LIKE ('%' || :status || '%')))")
    Page<Author> findAll(String fullName, String penName, Status status, Pageable pageable);
}
