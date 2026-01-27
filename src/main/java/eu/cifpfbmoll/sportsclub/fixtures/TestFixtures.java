package eu.cifpfbmoll.sportsclub.fixtures;

import eu.cifpfbmoll.sportsclub.model.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestFixtures {

    public static Coach createCoach() {
        return new Coach(
                "Test", "Coach", "test.coach@test.com",
                LocalDate.of(1985, 5, 15), "Test Certification"
        );
    }

    public static Athlete createAthlete() {
        return new Athlete(
                "Test", "Athlete", "test.athlete@test.com",
                LocalDate.of(2005, 6, 10)
        );
    }

    public static Activity createActivity() {
        return new Activity(
                "Test Activity",
                ActivityType.TRAINING,
                LocalDateTime.of(2026, 3, 1, 10, 0),
                120,
                "Test Location"
        );
    }

    public static List<Coach> createCoaches() {
        return List.of(
                new Coach("John", "Smith", "john@test.com", LocalDate.of(1980, 5, 15), "Level 3"),
                new Coach("Mary", "Johnson", "mary@test.com", LocalDate.of(1985, 8, 20), "Level 2")
        );
    }

    public static List<Athlete> createAthletes() {
        return List.of(
                new Athlete("Alice", "Brown", "alice@test.com", LocalDate.of(2005, 3, 10)),
                new Athlete("Bob", "Wilson", "bob@test.com", LocalDate.of(2006, 7, 22)),
                new Athlete("Charlie", "Davis", "charlie@test.com", LocalDate.of(2004, 11, 5))
        );
    }

    public static List<Activity> createActivities() {
        return List.of(
                new Activity("Training 1", ActivityType.TRAINING, LocalDateTime.of(2026, 2, 1, 9, 0), 120, "Field A"),
                new Activity("Competition 1", ActivityType.COMPETITION, LocalDateTime.of(2026, 2, 15, 10, 0), 180, "Stadium"),
                new Activity("Training 2", ActivityType.TRAINING, LocalDateTime.of(2026, 2, 5, 16, 0), 90, "Field B")
        );
    }
}
