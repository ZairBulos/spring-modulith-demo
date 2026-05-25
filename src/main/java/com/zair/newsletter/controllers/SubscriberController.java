package com.zair.newsletter.controllers;

import com.zair.newsletter.entities.Subscriber;
import com.zair.newsletter.services.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/subscribers")
@RequiredArgsConstructor
class SubscriberController {

    private final SubscriberService service;

    record SubscriberRequest(String email) {}

    @PostMapping
    ResponseEntity<Subscriber> create(@RequestBody SubscriberRequest request) {
        log.info("Creating subscriber {}", request);

        var subscriber = service.create(request.email());

        return ResponseEntity
                .created(URI.create("/api/subscribers/" + subscriber.getId()))
                .body(subscriber);
    }

}
