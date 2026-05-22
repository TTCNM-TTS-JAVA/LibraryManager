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
public class AuthorResponse {

    Long id;
    String fullName;
    String penName;
    String country;
    String shortDescription;
    Status status;
    LocalDate createAt;
    LocalDate updateAt;
}
