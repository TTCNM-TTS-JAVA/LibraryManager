package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.enums.Status;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookRequest {

    @NotBlank(message = "")
    @Size(max = 20)
    String bookCode;

    @NotBlank(message = "")
    @Size(max = 200)
    String bookTitle;


    Long categoryId;


    Long authorId;


    Long publisherId;


    Long publishedYear;


    Long totalQuantity;


    String shelfLocation;


    Status status;
}
