package eu.cifpfbmoll.sportsclub.controller;

import eu.cifpfbmoll.sportsclub.model.Activity;
import eu.cifpfbmoll.sportsclub.model.ActivityType;
import eu.cifpfbmoll.sportsclub.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        return activityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public List<Activity> getActivitiesByType(@PathVariable ActivityType type) {
        return activityService.findByType(type);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
        Activity saved = activityService.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id,
                                                   @Valid @RequestBody Activity activity) {
        if (!activityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        activity.setId(id);
        return ResponseEntity.ok(activityService.save(activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        if (!activityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        activityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
