package eu.cifpfbmoll.sportsclub.fixtures;

import eu.cifpfbmoll.sportsclub.model.Activity;
import eu.cifpfbmoll.sportsclub.model.ActivityType;
import eu.cifpfbmoll.sportsclub.model.Athlete;
import eu.cifpfbmoll.sportsclub.model.Coach;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestFixtures {

    // atleta

    public static Athlete createAthlete() {
        return new Athlete(
                "Test",
                "Athlete",
                "test.athlete@test.com",
                LocalDate.of(2000, 1, 1)
        );
    }

    public static List<Athlete> createAthletes() {
        List<Athlete> athletes = new ArrayList<>();

        athletes.add(new Athlete(
                "John",
                "Doe",
                "john.doe@test.com",
                LocalDate.of(2005, 5, 15)
        ));

        athletes.add(new Athlete(
                "Jane",
                "Smith",
                "jane.smith@test.com",
                LocalDate.of(2006, 8, 20)
        ));

        athletes.add(new Athlete(
                "Bob",
                "Wilson",
                "bob.wilson@test.com",
                LocalDate.of(2004, 3, 10)
        ));

        return athletes;
    }

    //coach

    public static Coach createCoach() {
        return new Coach(
                "Test",
                "Coach",
                "test.coach@test.com",
                LocalDate.of(1980, 5, 15),
                "Test Certification"
        );
    }

    public static List<Coach> createCoaches() {
        List<Coach> coaches = new ArrayList<>();

        coaches.add(new Coach(
                "Michael",
                "Johnson",
                "michael.johnson@test.com",
                LocalDate.of(1975, 3, 10),
                "Level 1 Coach"
        ));

        coaches.add(new Coach(
                "Sarah",
                "Brown",
                "sarah.brown@test.com",
                LocalDate.of(1982, 7, 25),
                "Level 2 Coach"
        ));

        return coaches;
    }

    //actividad

    public static Activity createActivity() {
        //fecha futura
        return new Activity(
                "Test Activity",
                ActivityType.TRAINING,
                LocalDateTime.of(2027, 6, 15, 10, 0),
                90,
                "Test Location"
        );
    }

    public static List<Activity> createActivities() {
        List<Activity> activities = new ArrayList<>();

        //las fechas deben ser FUTURAS
        activities.add(new Activity(
                "Morning Training",
                ActivityType.TRAINING,
                LocalDateTime.of(2027, 7, 1, 9, 0),
                120,
                "Stadium A"
        ));

        activities.add(new Activity(
                "Afternoon Training",
                ActivityType.TRAINING,
                LocalDateTime.of(2027, 7, 5, 16, 0),
                90,
                "Training Field"
        ));

        activities.add(new Activity(
                "Championship",
                ActivityType.COMPETITION,
                LocalDateTime.of(2027, 8, 10, 14, 0),
                180,
                "Main Stadium"
        ));

        return activities;
    }
}