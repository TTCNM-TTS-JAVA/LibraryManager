package org.library.manager.model.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeactivationReason {
    @Size(max = 500, message = "{max.500.character}")
    public String deactivationReason;
}
