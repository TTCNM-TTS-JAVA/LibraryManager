package org.library.manager.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
public class CreateMemberRequest {
    @NotBlank(message = "{member.code.not.null}")
    @Size(max = 20, message = "{max.20.character}")
    String memberCode;

    @NotBlank(message = "{full.name.not.null}")
    @Size(max = 120, message = "{max.120.character}")
    String fullName;

    @Email(message = "{email.not.found}")
    String email;

    @Size(max = 20, message = "{max.20.character}")
    String phoneNumber;

    @Past(message = "{dob.not.found}")
    LocalDate dob;

    @Size(max = 500, message = "{max.500.character}")
    String address;

    Status status;
}
