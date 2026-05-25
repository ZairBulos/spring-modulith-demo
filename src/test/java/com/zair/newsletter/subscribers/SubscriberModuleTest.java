package com.zair.newsletter.subscribers;

import com.zair.newsletter.publications.PublicationPublished;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ActiveProfiles("test")
class SubscriberModuleTest {

    @Test
    void notifiesSubscribersOnPublicationPublished(Scenario scenario) {
        scenario
                .publish(new PublicationPublished(1L, "04. Integration Testing Application Modules"))
                .andWaitForEventOfType(PublicationPublished.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.id()).isEqualTo(1L);
                    assertThat(event.title()).isEqualTo("04. Integration Testing Application Modules");
                });
    }

}
