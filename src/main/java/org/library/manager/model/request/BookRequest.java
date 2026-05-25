package org.library.manager.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.library.manager.entity.Book;
import org.library.manager.enums.Status;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookRequest {

    @NotBlank(message = "{max.20.character}")
    @Size(max = 20)
    String bookCode;

    @NotBlank(message = "{max.200.character}")
    @Size(max = 200)
    String bookTitle;

    @NotEmpty(message = "{category.id.not.null}")
    Set<Long> categoryIds;

    @NotEmpty(message = "{author.id.not.null}")
    Set<Long> authorIds;

    @NotNull(message = "{publisher.id.not.null}")
    Long publisherId;

    @Min(value = 1900,message = "{min.1900}")
    Long publishedYear;

    @NotNull(message = "{total.quantity.not.null}")
    @Min(value = 1,message = "{min.1}")
    Long totalQuantity;

    @Size(max = 50,message = "{max.50.character}")
    String shelfLocation;

    Status status;
}