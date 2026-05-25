package org.library.manager.repository;

import org.library.manager.entity.Loan;
import org.library.manager.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByCode(String code);
    @Query("SELECT l FROM Loan l JOIN l.member m WHERE " +
            "(:memberKeyword IS NULL OR m.memberCode LIKE CONCAT('%',:memberKeyword,'%') " +
            "   OR m.fullName LIKE CONCAT('%',:memberKeyword,'%')) " +
            "AND (:loanStatus IS NULL OR l.status = :loanStatus) " +
            "AND (:fromDate IS NULL OR l.loanDate >= :fromDate) " +
            "AND (:toDate IS NULL OR l.loanDate <= :toDate)")
    Page<Loan> search(String memberKeyword, LoanStatus loanStatus,
                            LocalDate fromDate, LocalDate toDate, Pageable pageable);









}
