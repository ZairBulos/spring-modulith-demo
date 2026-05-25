package com.zair.newsletter.publications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "publications")
@Getter @NoArgsConstructor @AllArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "published_at", nullable = false, updatable = false)
    private Instant publishedAt;

    public Publication(String title, String content) {
        this.title = title;
        this.content = content;
        this.publishedAt = Instant.now();
    }

}
