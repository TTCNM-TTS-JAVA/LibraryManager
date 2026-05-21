package org.library.manager.service;


import org.library.manager.enums.PublisherStatus;
import org.library.manager.model.request.PublisherDeactivationRequest;
import org.library.manager.model.request.PublisherFilterRequest;
import org.library.manager.model.request.PublisherRequest;
import org.library.manager.model.response.PublisherResponse;

import java.util.List;

public interface PublisherService {
    PublisherResponse create(PublisherRequest request);

    List<PublisherResponse> filter(int size, int page, PublisherFilterRequest request);

    PublisherResponse update(Long id, PublisherRequest request);

    PublisherResponse getById(Long id);

    PublisherResponse deactivationReason(Long id, PublisherDeactivationRequest request);




}
