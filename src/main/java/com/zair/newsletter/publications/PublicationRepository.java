package com.zair.newsletter.publications;

import org.springframework.data.jpa.repository.JpaRepository;

interface PublicationRepository extends JpaRepository<Publication, Long> {
}
