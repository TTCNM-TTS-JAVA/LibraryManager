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

    @Column(name = "full_name",nullable = false,length = 120)
    String fullName;

    @Column(name = "pen_name",length = 80)
    String penName;

    @Column(length = 100)
    String country;

    @Column(name = "short_description", length = 500)
    String shortDescription;

    @Enumerated(EnumType.STRING)
    Status status;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    LocalDate createAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    LocalDate updateAt;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "book-authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )

    private Set<Book> danhSachBook = new HashSet<>();

    public void addBook(Book book){
        this.danhSachBook.add(book);
        book.getDanhSachAuthor().add(this);
    }

    public void removeBook(Book book){
        this.danhSachBook.remove(book);
        book.getDanhSachAuthor().remove(this);
    }
}