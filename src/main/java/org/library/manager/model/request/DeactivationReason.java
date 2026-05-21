package org.library.manager.model.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeactivationReason {
    @Size(max = 500)
    public String deactivation_Reason;
}
