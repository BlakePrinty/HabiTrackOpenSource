package org.habitrack.HabiTrack.Repository;

import org.habitrack.HabiTrack.Model.Habit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitRepository extends CrudRepository<Habit, Long> {
    Optional<Habit> findByName(String name);
}
