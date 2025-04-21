package com.abletocode.missioncontrol.service;

import com.abletocode.missioncontrol.dto.AstronautRequestDto;
import com.abletocode.missioncontrol.dto.AstronautResponseDto;
import com.abletocode.missioncontrol.exception.SatelliteNotFoundException;
import com.abletocode.missioncontrol.model.Astronaut;
import com.abletocode.missioncontrol.model.Satellite;
import com.abletocode.missioncontrol.repository.AstronautRepository;
import com.abletocode.missioncontrol.repository.SatelliteRepository;
import com.abletocode.missioncontrol.service.impl.AstronautServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstronautServiceTest {

    @Mock
    private AstronautRepository astronautRepo;

    @Mock
    private SatelliteRepository satelliteRepo;

    @InjectMocks
    private AstronautServiceImpl astronautService;

    public AstronautServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAstronaut_successfully_whenSatelliteExists() {
        Satellite sat = Satellite.builder()
                .id(1L)
                .name("Hubble")
                .launchDate(LocalDate.of(1990, 4, 24))
                .orbitType("LEO")
                .decommissioned(false)
                .build();

        AstronautRequestDto dto = new AstronautRequestDto();
        dto.setFirstName("Buzz");
        dto.setLastName("Aldrin");
        dto.setExperienceYears(14);
        dto.setSatelliteIds(List.of(1L));

        Astronaut mockAstronaut = Astronaut.builder()
                .id(1L)
                .firstName("Buzz")
                .lastName("Aldrin")
                .experienceYears(14)
                .satellites(Set.of(sat))
                .build();

        when(satelliteRepo.findById(1L)).thenReturn(Optional.of(sat));
        when(astronautRepo.save(any())).thenReturn(mockAstronaut);

        AstronautResponseDto response = astronautService.createAstronaut(dto);

        assertNotNull(response);
        assertEquals("Buzz", response.getFirstName());
    }


    @Test
    void createAstronaut_shouldThrow_whenSatelliteNotFound() {
        AstronautRequestDto dto = new AstronautRequestDto();
        dto.setFirstName("Buzz");
        dto.setLastName("Aldrin");
        dto.setExperienceYears(14);
        dto.setSatelliteIds(List.of(99L));

        when(satelliteRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SatelliteNotFoundException.class, () -> astronautService.createAstronaut(dto));
    }
}
