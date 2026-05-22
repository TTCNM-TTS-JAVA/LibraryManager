package org.library.manager.model.response;

import lombok.Builder;
import lombok.Data;
import org.library.manager.enums.PublisherStatus;
import java.time.LocalDateTime;

@Data
@Builder
public class PublisherResponse {
    private String name;

    private String email;

    private String phoneNum;

    private String address;

    private Long countBookByPublisher;

    private PublisherStatus status;

    private LocalDateTime updatedAt;

    private String deactivationReason;

}
