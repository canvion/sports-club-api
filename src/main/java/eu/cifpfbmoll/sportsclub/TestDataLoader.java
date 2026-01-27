package eu.cifpfbmoll.sportsclub;

import eu.cifpfbmoll.sportsclub.model.*;
import eu.cifpfbmoll.sportsclub.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Profile("!test")

public class TestDataLoader {

    @Bean
    CommandLineRunner initDatabase(CoachRepository coachRepository,
                                   AthleteRepository athleteRepository,
                                   ActivityRepository activityRepository) {
        return args -> {
            // Crear Coaches
            Coach coach1 = coachRepository.save(new Coach(
                    "John", "Smith", "john.smith@club.com",
                    LocalDate.of(1980, 5, 15), "UEFA Pro License"
            ));

            Coach coach2 = coachRepository.save(new Coach(
                    "Mary", "Johnson", "mary.johnson@club.com",
                    LocalDate.of(1985, 8, 20), "Level 3 Certified"
            ));

            Coach coach3 = coachRepository.save(new Coach(
                    "Robert", "Williams", "robert.williams@club.com",
                    LocalDate.of(1978, 3, 10), "Olympic Coach"
            ));

            // Crear Athletes
            Athlete athlete1 = new Athlete("Alice", "Brown", "alice.brown@athlete.com", LocalDate.of(2005, 3, 10));
            athlete1.setCoach(coach1);
            athleteRepository.save(athlete1);

            Athlete athlete2 = new Athlete("Bob", "Wilson", "bob.wilson@athlete.com", LocalDate.of(2006, 7, 22));
            athlete2.setCoach(coach1);
            athleteRepository.save(athlete2);

            Athlete athlete3 = new Athlete("Charlie", "Davis", "charlie.davis@athlete.com", LocalDate.of(2004, 11, 5));
            athlete3.setCoach(coach2);
            athleteRepository.save(athlete3);

            Athlete athlete4 = new Athlete("Diana", "Miller", "diana.miller@athlete.com", LocalDate.of(2005, 9, 18));
            athlete4.setCoach(coach2);
            athleteRepository.save(athlete4);

            Athlete athlete5 = new Athlete("Edward", "Moore", "edward.moore@athlete.com", LocalDate.of(2007, 1, 30));
            athlete5.setCoach(coach2);
            athleteRepository.save(athlete5);

            Athlete athlete6 = new Athlete("Fiona", "Taylor", "fiona.taylor@athlete.com", LocalDate.of(2006, 4, 12));
            athlete6.setCoach(coach3);
            athleteRepository.save(athlete6);

            Athlete athlete7 = new Athlete("George", "Anderson", "george.anderson@athlete.com", LocalDate.of(2005, 8, 25));
            athlete7.setCoach(coach3);
            athleteRepository.save(athlete7);

            Athlete athlete8 = new Athlete("Hannah", "Thomas", "hannah.thomas@athlete.com", LocalDate.of(2004, 12, 3));
            athleteRepository.save(athlete8);

            Athlete athlete9 = new Athlete("Ian", "Jackson", "ian.jackson@athlete.com", LocalDate.of(2006, 6, 14));
            athleteRepository.save(athlete9);

            Athlete athlete10 = new Athlete("Julia", "White", "julia.white@athlete.com", LocalDate.of(2007, 2, 28));
            athleteRepository.save(athlete10);

            // Crear Activities
            activityRepository.save(new Activity(
                    "Morning Training Session",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 1, 9, 0),
                    120,
                    "Main Stadium Track"
            ));

            activityRepository.save(new Activity(
                    "Speed Training",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 3, 16, 0),
                    90,
                    "Training Field A"
            ));

            activityRepository.save(new Activity(
                    "Regional Championship",
                    ActivityType.COMPETITION,
                    LocalDateTime.of(2026, 2, 15, 10, 0),
                    240,
                    "City Stadium"
            ));

            activityRepository.save(new Activity(
                    "Endurance Training",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 5, 8, 0),
                    150,
                    "Mountain Trail"
            ));

            activityRepository.save(new Activity(
                    "Team Practice",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 7, 17, 0),
                    120,
                    "Indoor Facility"
            ));

            activityRepository.save(new Activity(
                    "National Finals",
                    ActivityType.COMPETITION,
                    LocalDateTime.of(2026, 3, 1, 11, 0),
                    300,
                    "National Arena"
            ));

            activityRepository.save(new Activity(
                    "Technical Skills Workshop",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 10, 14, 0),
                    180,
                    "Training Center B"
            ));

            activityRepository.save(new Activity(
                    "Youth Tournament",
                    ActivityType.COMPETITION,
                    LocalDateTime.of(2026, 2, 20, 9, 0),
                    360,
                    "Sports Complex"
            ));

            activityRepository.save(new Activity(
                    "Recovery Session",
                    ActivityType.TRAINING,
                    LocalDateTime.of(2026, 2, 12, 10, 0),
                    60,
                    "Pool Area"
            ));

            activityRepository.save(new Activity(
                    "Inter-Club Challenge",
                    ActivityType.COMPETITION,
                    LocalDateTime.of(2026, 2, 25, 15, 0),
                    180,
                    "Regional Stadium"
            ));

            System.out.println("✅ Test data loaded successfully!");
        };
    }
}
