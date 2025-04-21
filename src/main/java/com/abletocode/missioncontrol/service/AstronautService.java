
package com.abletocode.missioncontrol.service;

import com.abletocode.missioncontrol.dto.AstronautRequestDto;
import com.abletocode.missioncontrol.dto.AstronautResponseDto;

import java.util.List;

public interface AstronautService {
    AstronautResponseDto createAstronaut(AstronautRequestDto dto);
    List<AstronautResponseDto> getAll(String sortBy, String order);
}

