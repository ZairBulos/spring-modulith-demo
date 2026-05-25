package com.zair.newsletter.subscribers.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class SubscriberService {

    private final SubscriberRepository repository;

    @Transactional
    Subscriber create(String email) {
        return repository.save(new Subscriber(email));
    }

}
