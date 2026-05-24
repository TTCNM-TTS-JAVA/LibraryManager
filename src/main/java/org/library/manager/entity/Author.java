package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.manager.enums.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name", nullable = false, length = 120)
    String fullName;

    @Column(name = "pen_name", length = 80)
    String penName;

    @Column(name = "country", length = 100)
    String country;

    @Column(name = "short_description", length = 500)
    String shortDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDate updatedAt;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}