package org.library.manager.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.enums.Status;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookResponse {
    Long id;
    String bookCode;
    String bookTitle;
    Set<Long> categoryIds;
    Set<Long> authorIds;
    Long publisherId;
    Long publishedYear;
    Long totalQuantity;
    String shelfLocation;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
