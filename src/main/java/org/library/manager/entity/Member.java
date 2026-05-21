package org.library.manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.manager.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "member_code",nullable = false,unique = true,length = 20)
    String memberCode;

    @Column(name = "full_name",nullable = false,length = 120)
    String fullName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number", length = 20)
    String phoneNumber;

    @Column(name = "date_of_birth")
    LocalDate dob;

    @Column(length = 500)
    String address;

    @Enumerated(EnumType.STRING)
    Status status;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",insertable = false)
    LocalDate updatedAt;
}
