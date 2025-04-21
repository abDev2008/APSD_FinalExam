package com.abletocode.missioncontrol.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AstronautRequestDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @Min(0)
    @Max(50)
    private int experienceYears;

    @NotEmpty(message = "At least one satellite ID must be provided")
    private List<@NotNull Long> satelliteIds;

    // Getters and Setters
}
