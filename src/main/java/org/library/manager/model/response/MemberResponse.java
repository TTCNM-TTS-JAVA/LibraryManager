package org.library.manager.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.enums.Status;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MemberResponse {
    Long id;
    String memberCode;
    String fullName;
    String email;
    String phoneNumber;
    LocalDate dob;
    String address;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
