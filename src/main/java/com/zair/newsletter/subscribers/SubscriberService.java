package com.zair.newsletter.subscribers;

import com.zair.newsletter.publications.Publication;
import com.zair.newsletter.subscribers.internal.Subscriber;
import com.zair.newsletter.subscribers.internal.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;

    @Transactional
    public Subscriber create(String email) {
        return repository.save(new Subscriber(email));
    }

    public void notifySubscribers(Publication publication) {
        // Simulate notification failure
//        throw new RuntimeException("Notification service unavailable");

        repository.findAll().forEach(subscriber ->
                log.info("Notify {} about publication '{}'", subscriber.getEmail(), publication.getTitle())
        );
    }

}
