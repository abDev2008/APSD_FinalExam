package com.abletocode.missioncontrol.controller;


import com.abletocode.missioncontrol.dto.AstronautRequestDto;
import com.abletocode.missioncontrol.service.AstronautService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AstronautController.class)
class AstronautControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstronautService astronautService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn400_whenMissingFields() throws Exception {
        AstronautRequestDto dto = new AstronautRequestDto();
        dto.setLastName("Test");
        dto.setExperienceYears(5);
        dto.setSatelliteIds(List.of(1L));  // missing first name

        mockMvc.perform(post("/api/v1/astronauts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
