package org.library.manager.model.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PublisherDeactivationRequest {
    @Size(max = 500, message = "{max.500.character}")
    private String deactivationReason;
}
