package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loan_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Scalar FK — đọc trực tiếp từ cột book_id, không trigger proxy load
    @Column(name = "book_id", insertable = false, updatable = false)
    private Long bookId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
