package ru.itis.javalab.server.services.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.server.models.ControllerStatistics;
import ru.itis.javalab.server.repositories.ControllerStatisticsRepository;

@Service
public class ControllerCounterServiceImpl implements ControllerCounterService {

    @Autowired
    protected ControllerStatisticsRepository controllerStatisticsRepository;

    @Override
    public void enlargeCounterForController(Class aclass) {
        ControllerStatistics controllerStatistics = controllerStatisticsRepository
                .findByControllerName(aclass.getSimpleName().split("\\$\\$")[0])
                .orElseGet(() -> createStatisticsForClass(aclass));

        controllerStatistics.setCounter(controllerStatistics.getCounter() + 1);
        controllerStatistics.setLastVisitDate(java.util.Calendar.getInstance().getTime());

        controllerStatisticsRepository.save(controllerStatistics);
    }

    public ControllerStatistics createStatisticsForClass(Class aclass){
        return controllerStatisticsRepository.save(ControllerStatistics.builder()
        .controllerName(aclass.getSimpleName().split("\\$\\$")[0])
        .counter(0L)
        .build());
    }
}
