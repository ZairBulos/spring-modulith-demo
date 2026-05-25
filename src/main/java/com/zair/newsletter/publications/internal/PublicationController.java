package com.zair.newsletter.publications.internal;

import com.zair.newsletter.publications.Publication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
class PublicationController {

    private final PublicationService service;

    record PublicationRequest(String title, String content) {}

    @GetMapping("/{id}")
    ResponseEntity<Publication> getById(@PathVariable Long id) {
        log.info("Getting publication by id {}", id);

        return ResponseEntity
                .ok(service.findById(id));
    }

    @PostMapping
    ResponseEntity<Publication> create(@RequestBody PublicationRequest request) {
        log.info("Creating publication {}", request);

        var publication = service.publish(request.title(), request.content());

        return ResponseEntity
                .created(URI.create("/api/publications/" + publication.getId()))
                .body(publication);
    }

}
