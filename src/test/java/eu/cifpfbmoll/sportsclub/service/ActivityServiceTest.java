package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.fixtures.TestFixtures;
import eu.cifpfbmoll.sportsclub.model.Activity;
import eu.cifpfbmoll.sportsclub.model.ActivityType;
import eu.cifpfbmoll.sportsclub.repository.ActivityRepository;
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
class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    void testCreateActivity() {
        Activity activity = TestFixtures.createActivity();
        Activity saved = activityService.save(activity);

        assertNotNull(saved.getId());
        assertEquals("Test Activity", saved.getName());
    }

    @Test
    void testFindActivityById() {
        Activity activity = activityService.save(TestFixtures.createActivity());

        Optional<Activity> found = activityService.findById(activity.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void testFindAllActivities() {
        activityRepository.deleteAll();
        List<Activity> activities = TestFixtures.createActivities();
        activities.forEach(activityService::save);

        List<Activity> all = activityService.findAll();

        assertEquals(3, all.size());
    }

    @Test
    void testFindByType() {
        activityRepository.deleteAll();
        TestFixtures.createActivities().forEach(activityService::save);

        List<Activity> trainings = activityService.findByType(ActivityType.TRAINING);
        List<Activity> competitions = activityService.findByType(ActivityType.COMPETITION);

        assertEquals(2, trainings.size());
        assertEquals(1, competitions.size());
    }

    @Test
    void testUpdateActivity() {
        Activity activity = activityService.save(TestFixtures.createActivity());

        activity.setLocation("New Location");
        Activity updated = activityService.save(activity);

        assertEquals("New Location", updated.getLocation());
    }

    @Test
    void testDeleteActivity() {
        Activity activity = activityService.save(TestFixtures.createActivity());
        Long id = activity.getId();

        activityService.deleteById(id);

        assertFalse(activityService.findById(id).isPresent());
    }
}
