package org.library.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorWithBook {
    String fullname;
    String penName;
    List<ListBook> books;
}
