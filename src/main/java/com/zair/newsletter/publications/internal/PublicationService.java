package com.zair.newsletter.publications.internal;

import com.zair.newsletter.publications.PublicationPublished;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class PublicationService {

    private final PublicationRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional(readOnly = true)
    Publication findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Publication not found"));
    }

    @Transactional
    Publication publish(String title, String content) {
        var publication = repository.save(new Publication(title, content));
        publisher.publishEvent(new PublicationPublished(publication.getId(), publication.getTitle()));
        return publication;
    }

}
