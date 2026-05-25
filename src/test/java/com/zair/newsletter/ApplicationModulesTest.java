package com.zair.newsletter;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModulesTest {

    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(NewsletterApplication.class).verify();
    }

}
