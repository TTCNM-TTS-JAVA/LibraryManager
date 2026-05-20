package org.library.manager.repository;

import org.library.manager.entity.Author;
import org.library.manager.enums.Status;
import org.library.manager.model.AuthorWithBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByFullName(String fullName);

    boolean existsByPenName(String penName);

    Optional<Author> findByFullName(String fullName);

    Optional<Author> findByPenName(String penName);

    List<Author> findByStatus(Status status);

    @Query("SELECT DISTINCT a.fullName, a.penname FROM Author a JOIN FETCH a.books")
    List<AuthorWithBook> findAuthorWithBook();
}
