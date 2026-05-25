package com.zair.newsletter.subscribers.internal;

import com.zair.newsletter.publications.PublicationPublished;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class SubscriberListener {

    private final SubscriberRepository repository;

    @ApplicationModuleListener
    void on(PublicationPublished publication) {
        log.debug("Received PublicationPublished event: {}", publication);

        // Simulate notification failure
//        throw new RuntimeException("Notification service unavailable");

        repository.findAll().forEach(subscriber ->
                log.info("Notify {} about publication '{}'", subscriber.getEmail(), publication.title())
        );
    }

}
