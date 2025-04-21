package com.abletocode.missioncontrol.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SatelliteResponseDto {

    private Long id;
    private String name;
    private LocalDate launchDate;
    private String orbitType;
    private boolean decommissioned;


    public SatelliteResponseDto(Long id, String name, LocalDate launchDate, String orbitType, boolean decommissioned) {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
        this.orbitType = orbitType;
        this.decommissioned = decommissioned;
    }

}

