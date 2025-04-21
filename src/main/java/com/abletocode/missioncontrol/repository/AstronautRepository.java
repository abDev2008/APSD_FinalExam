package com.abletocode.missioncontrol.repository;


import com.abletocode.missioncontrol.model.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronautRepository extends JpaRepository<Astronaut, Long> {

}
