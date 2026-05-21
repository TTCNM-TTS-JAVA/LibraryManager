package org.library.manager.repository;

import org.library.manager.entity.Member;
import org.library.manager.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByMemberCode(String memberCode);

    @Query("SELECT a FROM Member a WHERE (:search IS NULL OR (a.memberCode LIKE CONCAT('%',:search,'%')) " +
            "OR (a.fullName LIKE CONCAT('%',:search,'%')) OR (a.email LIKE CONCAT('%',:search,'%')))" +
            "AND (:status IS NULL OR a.status = :status)")
    Page<Member> findByFilterMember(String search, Status status, Pageable pageable);
}
