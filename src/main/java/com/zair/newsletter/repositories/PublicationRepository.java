package com.zair.newsletter.repositories;

import com.zair.newsletter.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
