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
public class CreateAuthorRequest {
    @NotBlank(message = "{full.name.not.null}")
    @Size(max = 120)
    String fullName;

    @Size(max = 80)
    String penName;

    @Size(max = 100)
    String country;

    @Size(max = 500)
    String shortDescription;

    Status status;
}
