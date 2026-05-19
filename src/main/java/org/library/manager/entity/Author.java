package org.library.manager.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.manager.enums.Status;

import java.time.LocalDate;

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
}
