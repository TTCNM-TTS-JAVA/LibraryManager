package org.library.manager.repository;

import org.library.manager.entity.Loan;
import org.library.manager.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByCode(String code);

    @Query("""
            SELECT l FROM Loan l JOIN FETCH l.member m
            WHERE (:memberKeyword IS NULL
                    OR m.memberCode LIKE CONCAT('%', :memberKeyword, '%')
                    OR m.fullName   LIKE CONCAT('%', :memberKeyword, '%'))
              AND (:status   IS NULL OR l.status   = :status)
              AND (:fromDate IS NULL OR l.loanDate >= :fromDate)
              AND (:toDate   IS NULL OR l.loanDate <= :toDate)
            ORDER BY l.createdAt DESC
            """)
    Page<Loan> searchWithMember(@Param("memberKeyword") String memberKeyword,
                                @Param("status") LoanStatus status,
                                @Param("fromDate") LocalDate fromDate,
                                @Param("toDate") LocalDate toDate,
                                Pageable pageable);


    @Query("""
            SELECT DISTINCT l FROM Loan l
            JOIN FETCH l.member
            LEFT JOIN FETCH l.loanItems li
            LEFT JOIN FETCH li.book
            WHERE l.id = :id
            """)
    Optional<Loan> findByIdWithDetails(@Param("id") Long id);

    @Query("""
            SELECT DISTINCT l FROM Loan l
            LEFT JOIN FETCH l.loanItems li
            LEFT JOIN FETCH li.book
            WHERE l.id IN :ids
            """)
    List<Loan> findAllByIdsWithLoanItems(@Param("ids") List<Long> ids);
}
