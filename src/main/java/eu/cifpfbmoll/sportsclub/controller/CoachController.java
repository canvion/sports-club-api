package eu.cifpfbmoll.sportsclub.controller;

import eu.cifpfbmoll.sportsclub.model.Coach;
import eu.cifpfbmoll.sportsclub.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return coachService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coach> createCoach(@Valid @RequestBody Coach coach) {
        Coach saved = coachService.save(coach);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id,
                                             @Valid @RequestBody Coach coach) {
        if (!coachService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        coach.setId(id);
        return ResponseEntity.ok(coachService.save(coach));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        if (!coachService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        coachService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
