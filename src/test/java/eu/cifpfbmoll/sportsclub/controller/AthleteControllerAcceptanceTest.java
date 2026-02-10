package eu.cifpfbmoll.sportsclub.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
class AthleteControllerAcceptanceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetAllAthletes() throws Exception {
        mockMvc.perform(get("/api/athletes"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAthleteById() throws Exception {
        mockMvc.perform(get("/api/athletes/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testCreateAthlete() throws Exception {
        String json = "{\"name\":\"Test\",\"surname\":\"Acceptance\",\"email\":\"test.acceptance@example.com\",\"birthDate\":\"2000-01-01\"}";

        mockMvc.perform(post("/api/athletes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }
}