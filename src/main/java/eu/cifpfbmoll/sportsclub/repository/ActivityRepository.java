package eu.cifpfbmoll.sportsclub.repository;

import eu.cifpfbmoll.sportsclub.model.Activity;
import eu.cifpfbmoll.sportsclub.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByType(ActivityType type);
}
