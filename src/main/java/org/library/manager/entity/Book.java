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

    @Column(name = "book_code", nullable = false,length = 20, unique = true)
    String bookCode;

    @Column(name = "book_title", nullable = false, length = 200)
    String bookTitle;

    @Column(name = "category_id",nullable = false)
    Long categoryId;

    @Column(name = "author_id",nullable = false)
    Long authorId;

    @Column(name = "publisher_id",nullable = false)
    Long publisherId;

    @Column(name = "published_year",length = 4)
    Long publishedYear;

    @Column(name = "total_quantity")
    Long totalQuantity;

    @Column(name = "shelf_location",length = 50)
    String shelfLocation;

    @Column(name = "status",length = 10)
    Status status;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false,insertable = false)
    LocalDate updatedAt;

    @ManyToMany(mappedBy = "danhSachBook")
    private Set<Author> danhSachAuthor = new HashSet<>();

    public void addAuthor(Author author) {
        this.danhSachAuthor.add(author);
        author.getDanhSachBook().add(this);
    }

    public void removeAuthor(Author author) {
        this.danhSachAuthor.remove(author);
        author.getDanhSachBook().remove(this);
    }

    @ManyToMany(mappedBy = "danhSachBook")
    private Set<BookCategory> danhSachCategory = new HashSet<>();

    public void addCategory(BookCategory category) {
        this.danhSachCategory.add(category);
        category.getDanhSachBooks().add(this);
    }

    public void deleteCategory(BookCategory category) {
        this.danhSachCategory.remove(category);
        category.getDanhSachBooks().remove(this);
    }
}
