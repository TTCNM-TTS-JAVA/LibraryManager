package org.library.manager.repository;

import org.library.manager.entity.Book;
import org.library.manager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

        boolean existsByBookCode(String bookCode);

        @EntityGraph(attributePaths = {
                "authors",
                "categories"
        })
        @Query("""
            SELECT DISTINCT b
            FROM Book b
            LEFT JOIN b.authors a
            LEFT JOIN b.categories c
            WHERE (
                    :search IS NULL
                    OR LOWER(b.bookCode) LIKE LOWER(CONCAT('%', :search, '%'))
                    OR LOWER(b.bookTitle) LIKE LOWER(CONCAT('%', :search, '%'))
            )
            AND (
                    :status IS NULL
                    OR b.status = :status
            )
            AND (
                    :filterPublisher IS NULL
                    OR b.publisher.id = :filterPublisher
            )
            AND (
                    :filterAuthor IS NULL
                    OR a.id IN :filterAuthor
            )
            AND (
                    :filterCategory IS NULL
                    OR c.id IN :filterCategory
            )
            """)
        Page<Book> filterBook(
                String search,
                Status status,
                Set<Long> filterAuthor,
                Set<Long> filterCategory,
                Long filterPublisher,
                Pageable pageable
        );
}