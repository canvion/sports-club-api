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
class ActivityControllerAcceptanceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetAllActivities() throws Exception {
        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetActivityById() throws Exception {
        mockMvc.perform(get("/api/activities/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testCreateActivity() throws Exception {
        String json = "{\"name\":\"Test Activity\",\"type\":\"TRAINING\",\"dateTime\":\"2027-03-10T18:00:00\",\"durationMinutes\":90,\"location\":\"Test Location\"}";

        mockMvc.perform(post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }
}