package com.abletocode.missioncontrol.config;


import com.abletocode.missioncontrol.model.Astronaut;
import com.abletocode.missioncontrol.model.Satellite;
import com.abletocode.missioncontrol.repository.AstronautRepository;
import com.abletocode.missioncontrol.repository.SatelliteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final AstronautRepository astronautRepo;
    private final SatelliteRepository satelliteRepo;

    @PostConstruct
    public void seed() {
        astronautRepo.deleteAll();
        satelliteRepo.deleteAll();
        Satellite hubble = satelliteRepo.save(Satellite.builder()
                .name("Hubble")
                .launchDate(LocalDate.of(1990, 4, 24))
                .orbitType("LEO")
                .decommissioned(false)
                .build());

        Satellite starlink = satelliteRepo.save(Satellite.builder()
                .name("Starlink-17")
                .launchDate(LocalDate.of(2023, 8, 14))
                .orbitType("MEO")
                .decommissioned(false)
                .build());

        Satellite sentinel = satelliteRepo.save(Satellite.builder()
                .name("Sentinel-6")
                .launchDate(LocalDate.of(2020, 11, 21))
                .orbitType("LEO")
                .decommissioned(true)
                .build());

        astronautRepo.save(Astronaut.builder()
                .firstName("Neil")
                .lastName("Armstrong")
                .experienceYears(12)
                .satellites(Set.of(hubble))
                .build());

        astronautRepo.save(Astronaut.builder()
                .firstName("Sally")
                .lastName("Ride")
                .experienceYears(8)
                .satellites(Set.of(starlink))
                .build());

        astronautRepo.save(Astronaut.builder()
                .firstName("Chris")
                .lastName("Hadfield")
                .experienceYears(15)
                .satellites(Set.of(hubble, sentinel))
                .build());
    }
}
