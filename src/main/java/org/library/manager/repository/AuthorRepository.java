package org.library.manager.repository;

import org.library.manager.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Authors,Long> {
    boolean existsByfullName(String fullName);
}
