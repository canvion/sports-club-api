package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.model.Coach;
import eu.cifpfbmoll.sportsclub.repository.CoachRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteById(Long id) {
        coachRepository.deleteById(id);
    }

    public Optional<Coach> findByEmail(String email) {
        return coachRepository.findByEmail(email);
    }
}
