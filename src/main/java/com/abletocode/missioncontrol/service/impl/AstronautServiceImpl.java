package com.abletocode.missioncontrol.service.impl;


import com.abletocode.missioncontrol.dto.*;
import com.abletocode.missioncontrol.exception.SatelliteNotFoundException;
import com.abletocode.missioncontrol.model.Astronaut;
import com.abletocode.missioncontrol.model.Satellite;
import com.abletocode.missioncontrol.repository.AstronautRepository;
import com.abletocode.missioncontrol.repository.SatelliteRepository;
import com.abletocode.missioncontrol.service.AstronautService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AstronautServiceImpl implements AstronautService {

    private final AstronautRepository astronautRepo;
    private final SatelliteRepository satelliteRepo;

    @Override
    public AstronautResponseDto createAstronaut(AstronautRequestDto dto) {
        Set<Satellite> satellites = new HashSet<>();

        for (Long id : dto.getSatelliteIds()) {
            Satellite sat = satelliteRepo.findById(id)
                    .orElseThrow(() -> new SatelliteNotFoundException("Satellite with ID " + id + " not found"));
            satellites.add(sat);
        }

        Astronaut astronaut = Astronaut.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .experienceYears(dto.getExperienceYears())
                .satellites(satellites)
                .build();

        Astronaut saved = astronautRepo.save(astronaut);
        return mapToResponse(saved);
    }

    @Override
    public List<AstronautResponseDto> getAll(String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return astronautRepo.findAll(sort).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AstronautResponseDto mapToResponse(Astronaut entity) {
        List<SatelliteResponseDto> satellites = entity.getSatellites().stream().map(sat ->
                new SatelliteResponseDto(
                        sat.getId(),
                        sat.getName(),
                        sat.getLaunchDate(),
                        sat.getOrbitType(),
                        sat.isDecommissioned()
                )).collect(Collectors.toList());

        AstronautResponseDto dto = new AstronautResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setExperienceYears(entity.getExperienceYears());
        dto.setSatellites(satellites);
        return dto;
    }
}

