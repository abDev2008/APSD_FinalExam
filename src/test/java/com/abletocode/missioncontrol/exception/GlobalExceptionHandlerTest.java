package com.abletocode.missioncontrol.exception;


import com.abletocode.missioncontrol.controller.AstronautController;
import com.abletocode.missioncontrol.service.AstronautService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AstronautController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstronautService astronautService;

    @Test
    void shouldReturn404_whenSatelliteNotFound() throws Exception {
        String invalidPayload = """
        {
            "firstName": "Fail",
            "lastName": "Test",
            "experienceYears": 3,
            "satelliteIds": [99]
        }
        """;

        when(astronautService.createAstronaut(any()))
                .thenThrow(new SatelliteNotFoundException("Satellite with ID 99 not found"));

        mockMvc.perform(post("/api/v1/astronauts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Satellite with ID 99 not found"));
    }
}
