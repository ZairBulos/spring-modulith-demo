package com.zair.newsletter.subscribers;

import org.springframework.data.jpa.repository.JpaRepository;

interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
