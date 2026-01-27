package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.fixtures.TestFixtures;
import eu.cifpfbmoll.sportsclub.model.Athlete;
import eu.cifpfbmoll.sportsclub.repository.AthleteRepository;
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
class AthleteServiceTest {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    void testCreateAthlete() {
        Athlete athlete = TestFixtures.createAthlete();
        Athlete saved = athleteService.save(athlete);

        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
        assertEquals("Athlete", saved.getSurname());
        assertEquals("test.athlete@test.com", saved.getEmail());
    }

    @Test
    void testFindAthleteById() {
        Athlete athlete = athleteService.save(TestFixtures.createAthlete());

        Optional<Athlete> found = athleteService.findById(athlete.getId());

        assertTrue(found.isPresent());
        assertEquals(athlete.getId(), found.get().getId());
    }

    @Test
    void testFindAllAthletes() {
        athleteRepository.deleteAll();
        List<Athlete> athletes = TestFixtures.createAthletes();
        athletes.forEach(athleteService::save);

        List<Athlete> all = athleteService.findAll();

        assertEquals(3, all.size());
    }

    @Test
    void testUpdateAthlete() {
        Athlete athlete = athleteService.save(TestFixtures.createAthlete());

        athlete.setName("Updated");
        Athlete updated = athleteService.save(athlete);

        assertEquals("Updated", updated.getName());
    }

    @Test
    void testDeleteAthlete() {
        Athlete athlete = athleteService.save(TestFixtures.createAthlete());
        Long id = athlete.getId();

        athleteService.deleteById(id);

        assertFalse(athleteService.findById(id).isPresent());
    }

    @Test
    void testFindByEmail() {
        Athlete athlete = TestFixtures.createAthlete();
        athleteService.save(athlete);

        Optional<Athlete> found = athleteService.findByEmail("test.athlete@test.com");

        assertTrue(found.isPresent());
        assertEquals("Test", found.get().getName());
    }

    @Test
    void testFindByEmailNotFound() {
        Optional<Athlete> found = athleteService.findByEmail("nonexistent@test.com");
        assertFalse(found.isPresent());
    }

    @Test
    void testAthleteEmailIsUnique() {
        Athlete athlete1 = TestFixtures.createAthlete();
        athleteService.save(athlete1);

        Athlete athlete2 = TestFixtures.createAthlete();

        assertThrows(Exception.class, () -> athleteService.save(athlete2));
    }
}
