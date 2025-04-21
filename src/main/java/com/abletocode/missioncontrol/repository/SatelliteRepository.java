package com.abletocode.missioncontrol.repository;


import com.abletocode.missioncontrol.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    Optional<Satellite> findByName(String name); // optional for uniqueness check
}
