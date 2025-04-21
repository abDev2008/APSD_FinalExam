package com.abletocode.missioncontrol.repository;


import com.abletocode.missioncontrol.model.Astronaut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AstronautRepositoryTest {

    @Autowired
    private AstronautRepository astronautRepo;

    @Test
    void saveAstronaut_shouldWork() {
        Astronaut astro = Astronaut.builder()
                .firstName("Neil")
                .lastName("Armstrong")
                .experienceYears(12)
                .build();

        Astronaut saved = astronautRepo.save(astro);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo("Neil");
    }
}
