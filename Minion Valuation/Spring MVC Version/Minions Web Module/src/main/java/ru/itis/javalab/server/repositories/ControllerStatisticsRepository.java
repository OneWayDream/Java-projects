package ru.itis.javalab.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.server.models.ControllerStatistics;

import java.util.Optional;

public interface ControllerStatisticsRepository extends JpaRepository<ControllerStatistics, Long> {

    Optional<ControllerStatistics> findByControllerName(String name);

}
