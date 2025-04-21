package com.abletocode.missioncontrol.service.impl;


import com.abletocode.missioncontrol.dto.SatelliteRequestDto;
import com.abletocode.missioncontrol.dto.SatelliteResponseDto;
import com.abletocode.missioncontrol.exception.SatelliteNotFoundException;
import com.abletocode.missioncontrol.model.Satellite;
import com.abletocode.missioncontrol.repository.SatelliteRepository;
import com.abletocode.missioncontrol.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository satelliteRepo;

    @Override
    public SatelliteResponseDto updateSatellite(Long id, SatelliteRequestDto dto) {
        Satellite satellite = satelliteRepo.findById(id)
                .orElseThrow(() -> new SatelliteNotFoundException("Satellite with ID " + id + " not found"));

        if (satellite.isDecommissioned()) {
            throw new IllegalStateException("Cannot update a decommissioned satellite.");
        }

        satellite.setName(dto.getName());
        satellite.setLaunchDate(dto.getLaunchDate());
        satellite.setOrbitType(dto.getOrbitType());
        satellite.setDecommissioned(dto.isDecommissioned());

        Satellite updated = satelliteRepo.save(satellite);

        return new SatelliteResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getLaunchDate(),
                updated.getOrbitType(),
                updated.isDecommissioned()
        );
    }
}
