package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "book_id", nullable = false)
    Long bookId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
