package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.manager.enums.LoanItemStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "loan_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_loan_items_loan_book",
                        columnNames = {"loan_id", "book_id"}
                )
        },
        indexes = {
                @Index(name = "idx_loan_items_book", columnList = "book_id"),
                @Index(name = "idx_loan_items_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    Loan loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @Column(nullable = false)
    Integer quantity;

    @Column(name = "returned_quantity", nullable = false)
    @Builder.Default
    Integer returnedQuantity = 0;

    @Column(name = "return_date")
    LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    LoanItemStatus status = LoanItemStatus.BORROWING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}