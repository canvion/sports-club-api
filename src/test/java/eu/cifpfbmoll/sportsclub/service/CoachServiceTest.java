package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.fixtures.TestFixtures;
import eu.cifpfbmoll.sportsclub.model.Coach;
import eu.cifpfbmoll.sportsclub.repository.AthleteRepository;
import eu.cifpfbmoll.sportsclub.repository.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CoachServiceTest {

    @Autowired
    private CoachService coachService;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    void testCreateCoach() {
        Coach coach = TestFixtures.createCoach();
        Coach saved = coachService.save(coach);

        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
    }

    @Test
    void testFindCoachById() {
        Coach coach = coachService.save(TestFixtures.createCoach());

        Optional<Coach> found = coachService.findById(coach.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void testFindAllCoaches() {
        athleteRepository.deleteAll();
        coachRepository.deleteAll();

        List<Coach> coaches = TestFixtures.createCoaches();
        coaches.forEach(coachService::save);

        List<Coach> all = coachService.findAll();

        assertEquals(2, all.size());
    }

    @Test
    void testUpdateCoach() {
        Coach coach = coachService.save(TestFixtures.createCoach());

        coach.setCertification("Advanced");
        Coach updated = coachService.save(coach);

        assertEquals("Advanced", updated.getCertification());
    }

    @Test
    void testDeleteCoach() {
        // Limpiar athletes que puedan referenciar coaches
        athleteRepository.deleteAll();

        Coach coach = coachService.save(TestFixtures.createCoach());
        Long id = coach.getId();

        coachService.deleteById(id);

        assertFalse(coachService.findById(id).isPresent());
    }

    @Test
    void testFindByEmail() {
        Coach coach = TestFixtures.createCoach();
        coachService.save(coach);

        Optional<Coach> found = coachService.findByEmail("test.coach@test.com");

        assertTrue(found.isPresent());
    }
}
