package com.abletocode.missioncontrol.controller;


import com.abletocode.missioncontrol.dto.SatelliteRequestDto;
import com.abletocode.missioncontrol.dto.SatelliteResponseDto;
import com.abletocode.missioncontrol.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/satellites")
@RequiredArgsConstructor
public class SatelliteController {

    private final SatelliteService satelliteService;

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteResponseDto> updateSatellite(
            @PathVariable Long id,
            @Valid @RequestBody SatelliteRequestDto dto) {
        SatelliteResponseDto updated = satelliteService.updateSatellite(id, dto);
        return ResponseEntity.ok(updated);
    }
}
