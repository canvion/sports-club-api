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
class CoachControllerAcceptanceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetAllCoaches() throws Exception {
        mockMvc.perform(get("/api/coaches"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCoachById() throws Exception {
        mockMvc.perform(get("/api/coaches/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testCreateCoach() throws Exception {
        String json = "{\"name\":\"Test\",\"surname\":\"Coach\",\"email\":\"test.coach@example.com\",\"birthDate\":\"1980-05-15\",\"certification\":\"Test Cert\"}";

        mockMvc.perform(post("/api/coaches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }
}