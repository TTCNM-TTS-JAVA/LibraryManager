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
public class UpdateAuthorRequest {
    @NotBlank(message = "full.name.not.null")
    @Size(max = 120,message = "max.120.character")
    String fullName;

    @Size(max = 80,message = "max.80.character")
    String penName;

    @Size(max = 100,message = "max.100.character")
    String country;

    @Size(max = 500,message = "max.500.character")
    String shortDescription;

    Status status;
}
