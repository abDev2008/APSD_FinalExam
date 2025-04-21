package com.abletocode.missioncontrol.controller;


import com.abletocode.missioncontrol.dto.AstronautRequestDto;
import com.abletocode.missioncontrol.dto.AstronautResponseDto;
import com.abletocode.missioncontrol.service.AstronautService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/astronauts")
@RequiredArgsConstructor
public class AstronautController {

    private final AstronautService astronautService;

    @PostMapping
    public ResponseEntity<AstronautResponseDto> createAstronaut(
            @Valid @RequestBody AstronautRequestDto dto) {
        AstronautResponseDto created = astronautService.createAstronaut(dto);
        return ResponseEntity.status(201).body(created);
    }


    @GetMapping
    public ResponseEntity<List<AstronautResponseDto>> getAllAstronauts(
            @RequestParam(defaultValue = "experienceYears") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(astronautService.getAll(sort, order));
    }
}


