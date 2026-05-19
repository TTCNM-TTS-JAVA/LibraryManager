package org.library.manager.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExampleRequest {
    @NotBlank(message = "{not.blank}")
    private String name;
}
