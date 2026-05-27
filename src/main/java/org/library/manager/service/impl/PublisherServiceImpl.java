package org.library.manager.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Publisher;
import org.library.manager.enums.PublisherStatus;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.request.PublisherDeactivationRequest;
import org.library.manager.model.request.PublisherFilterRequest;
import org.library.manager.model.request.PublisherRequest;
import org.library.manager.model.response.PublisherResponse;
import org.library.manager.repository.PublisherRepository;
import org.library.manager.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepo;
    @Override
    @Transactional
    public PublisherResponse create(PublisherRequest request){
        String name = request.getName().trim();
        if(publisherRepo.existsByNameIgnoreCaseAndStatus(name, PublisherStatus.ACTIVE)){
            throw new CustomException(ErrorCode.PUBLISHER_NAME_DUPLICATED);
        }
        Publisher entity = Publisher.builder()
                .name(name)
                .email(request.getEmail())
                .phoneNum(request.getPhone())
                .address(request.getAddress())
                .status(PublisherStatus.ACTIVE)
                .build();
        return toResponse(publisherRepo.save(entity));

    }

    @Override
    @Transactional
    public Page<PublisherResponse> filter(int size, int page, PublisherFilterRequest request) {
        String keyword = request.getKeyword();
        String name = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return publisherRepo.search(name, request.getStatus(), pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional
    public PublisherResponse update(Long id, PublisherRequest request) {
        Publisher entity = findOrThrow(id);
        String name = request.getName().trim();
        if(publisherRepo.existsByNameIgnoreCaseAndStatus(name, PublisherStatus.ACTIVE) && !request.getName().equals(entity.getName())){
            throw new CustomException(ErrorCode.PUBLISHER_NAME_DUPLICATED);
        }
        entity.setName(name);
        entity.setEmail(request.getEmail());
        entity.setPhoneNum(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setStatus(request.getStatus());

        return toResponse(publisherRepo.save(entity));

    }

    @Override
    public PublisherResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    public PublisherResponse deactivationReason(Long id, PublisherDeactivationRequest request) {
        Publisher entity = findOrThrow(id);
        if(entity.getStatus() == PublisherStatus.INACTIVE){
            throw new CustomException(ErrorCode.PUBLISHER_ALREADY_INACTIVE);
        }
        entity.setStatus(PublisherStatus.INACTIVE);
        if (request != null) {
            entity.setDeactivationReason(request.getDeactivationReason());
        }
        return toResponse(publisherRepo.save(entity));
    }
    @Override
    public void delete(Long publisherId){
        Publisher entity = findOrThrow(publisherId);
        if(!entity.getBooks().isEmpty()){
            throw new CustomException(ErrorCode.PUBLISHER_CANNOT_DELETE);
        }
        publisherRepo.delete(entity);
    }

    private PublisherResponse toResponse(Publisher entity){
        return PublisherResponse.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNum(entity.getPhoneNum())
                .address(entity.getAddress())
                .countBookByPublisher(0L)
                .status(entity.getStatus())
                .updatedAt(entity.getUpdatedAt())
                .deactivationReason(entity.getDeactivationReason())
                .build();

    }
    private Publisher findOrThrow(Long id){
        return publisherRepo.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PUBLISHER_NOT_FOUND));

    }

}
