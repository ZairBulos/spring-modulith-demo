package com.zair.newsletter.publications.internal;

import com.zair.newsletter.publications.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

interface PublicationRepository extends JpaRepository<Publication, Long> {
}
