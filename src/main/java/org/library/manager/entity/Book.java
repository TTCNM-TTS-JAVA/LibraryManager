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
@Table(name = "books")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "code", nullable = false, length = 20, unique = true)
    String bookCode;

    @Column(name = "name", nullable = false, length = 200)
    String bookTitle;

    @Column(name = "publish_year", length = 4)
    Long publishedYear;

    @Column(name = "total_quantity")
    Long totalQuantity;

    @Column(name = "available_quantity", nullable = false)
    Long availableQuantity;

    @Column(name = "shelf_location", length = 50)
    String shelfLocation;

    @Enumerated(EnumType.STRING)
    Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "book_categories_mapping",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<BookCategory> categories = new HashSet<>();
}