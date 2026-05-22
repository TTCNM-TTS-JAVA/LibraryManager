package org.library.manager.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.library.manager.enums.PublisherStatus;

@Data
public class PublisherRequest {
    @NotBlank(message = "{name.not.null}")
    @Size(max = 150, message = "{max.150.character}")
    private String name;

    @Email(message = "{email.validation}")
    private String email;

    @Size(max = 20, message = "{max.20.character}")
    private String phone;

    @Size(max = 500, message = "{max.500.character}")
    private String address;

    private PublisherStatus status = PublisherStatus.ACTIVE;

}
