package com.abletocode.missioncontrol.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SatelliteRequestDto {

    @NotBlank
    private String name;

    @Past
    @NotNull
    private LocalDate launchDate;

    @Pattern(regexp = "LEO|MEO|GEO", message = "Orbit type must be LEO, MEO, or GEO")
    private String orbitType;

    private boolean decommissioned;

}

