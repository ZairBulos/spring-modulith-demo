package com.zair.newsletter.services;

import com.zair.newsletter.entities.Publication;
import com.zair.newsletter.repositories.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository repository;
    private final SubscriberService subscriberService;

    @Transactional(readOnly = true)
    public Publication findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Publication not found"));
    }

    @Transactional
    public Publication publish(String title, String content) {
        var publication = repository.save(new Publication(title, content));
        subscriberService.notifySubscribers(publication);
        return publication;
    }

}
