package com.abletocode.missioncontrol.dto;

import java.util.List;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AstronautResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int experienceYears;
    private List<SatelliteResponseDto> satellites;
}
