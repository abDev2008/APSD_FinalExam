package com.abletocode.missioncontrol.service;

import com.abletocode.missioncontrol.dto.SatelliteRequestDto;
import com.abletocode.missioncontrol.dto.SatelliteResponseDto;

public interface SatelliteService {
    SatelliteResponseDto updateSatellite(Long id, SatelliteRequestDto dto);
}
