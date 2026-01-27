package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.model.Activity;
import eu.cifpfbmoll.sportsclub.model.ActivityType;
import eu.cifpfbmoll.sportsclub.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    public List<Activity> findByType(ActivityType type) {
        return activityRepository.findByType(type);
    }
}
