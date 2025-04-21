package com.abletocode.missioncontrol.service;


import com.abletocode.missioncontrol.dto.SatelliteRequestDto;
import com.abletocode.missioncontrol.dto.SatelliteResponseDto;
import com.abletocode.missioncontrol.model.Satellite;
import com.abletocode.missioncontrol.repository.SatelliteRepository;
import com.abletocode.missioncontrol.service.impl.SatelliteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SatelliteServiceTest {

    @Mock
    private SatelliteRepository satelliteRepo;

    @InjectMocks
    private SatelliteServiceImpl satelliteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateSatellite_shouldSucceed_ifNotDecommissioned() {
        Satellite sat = Satellite.builder()
                .id(1L)
                .name("Starlink-17")
                .launchDate(LocalDate.of(2023, 8, 14))
                .orbitType("MEO")
                .decommissioned(false)
                .build();

        SatelliteRequestDto dto = new SatelliteRequestDto();
        dto.setName("Starlink-Updated");
        dto.setLaunchDate(LocalDate.of(2023, 8, 14));
        dto.setOrbitType("MEO");
        dto.setDecommissioned(false);

        when(satelliteRepo.findById(1L)).thenReturn(Optional.of(sat));
        when(satelliteRepo.save(any())).thenReturn(sat);

        SatelliteResponseDto response = satelliteService.updateSatellite(1L, dto);

        assertEquals("Starlink-Updated", response.getName());
    }

    @Test
    void updateSatellite_shouldThrow_ifDecommissioned() {
        Satellite sat = Satellite.builder()
                .id(3L)
                .name("Sentinel-6")
                .launchDate(LocalDate.of(2020, 11, 21))
                .orbitType("LEO")
                .decommissioned(true)
                .build();

        SatelliteRequestDto dto = new SatelliteRequestDto();
        dto.setName("Fail");
        dto.setLaunchDate(LocalDate.of(2020, 11, 21));
        dto.setOrbitType("LEO");
        dto.setDecommissioned(false);

        when(satelliteRepo.findById(3L)).thenReturn(Optional.of(sat));

        assertThrows(IllegalStateException.class, () -> satelliteService.updateSatellite(3L, dto));
    }
}
