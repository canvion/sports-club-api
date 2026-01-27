package eu.cifpfbmoll.sportsclub.service;

import eu.cifpfbmoll.sportsclub.model.Athlete;
import eu.cifpfbmoll.sportsclub.repository.AthleteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }

    public Optional<Athlete> findById(Long id) {
        return athleteRepository.findById(id);
    }

    public Athlete save(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public void deleteById(Long id) {
        athleteRepository.deleteById(id);
    }

    public Optional<Athlete> findByEmail(String email) {
        return athleteRepository.findByEmail(email);
    }
}
