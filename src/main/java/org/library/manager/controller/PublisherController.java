package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.model.request.PublisherDeactivationRequest;
import org.library.manager.model.request.PublisherFilterRequest;
import org.library.manager.model.request.PublisherRequest;
import org.library.manager.model.response.PublisherResponse;
import org.library.manager.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;
    @PostMapping("/filter")
    public ResponseEntity<List<PublisherResponse>> filter(@RequestParam int size,
                                                          @RequestParam int page,
                                                          @RequestBody PublisherFilterRequest request){
        return ResponseEntity.ok(service.filter(size, page, request));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PublisherResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping
    public ResponseEntity<PublisherResponse> create(@Valid @RequestBody PublisherRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody PublisherRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }
    @PatchMapping("/{id}/deactive")
    public ResponseEntity<PublisherResponse> deactivation(@PathVariable Long id,
                                                             @Valid @RequestBody PublisherDeactivationRequest reason){
        return ResponseEntity.ok(service.deactivationReason(id, reason));


    }
}
