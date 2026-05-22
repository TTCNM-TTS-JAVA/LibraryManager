package org.library.manager.model.request;

import lombok.Data;
import org.library.manager.enums.PublisherStatus;

@Data
public class PublisherFilterRequest {
    private String keyword;
    private PublisherStatus status;
}
