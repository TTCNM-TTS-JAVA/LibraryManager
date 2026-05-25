package org.library.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

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

    // @BatchSize: thay vì load books từng loanItem một,
    // Hibernate gom thành 1 query dạng WHERE loan_item_id IN (1, 2, 3, ...)
    @BatchSize(size = 30)
    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "loan_item_books",
            joinColumns = @JoinColumn(name = "loan_item_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
