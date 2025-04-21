package com.abletocode.missioncontrol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Satellite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Past
    @NotNull
    private LocalDate launchDate;

    @NotBlank
    @Pattern(regexp = "LEO|MEO|GEO", message = "Orbit type must be LEO, MEO, or GEO")
    private String orbitType;

    private boolean decommissioned;

    @ManyToMany(mappedBy = "satellites")
    private Set<Astronaut> astronauts = new HashSet<>();
}







