package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.MinionDto;
import ru.itis.javalab.data.dto.ProductionDto;

import java.util.List;

public interface ProductionsService extends CrudService<ProductionDto, Long> {
    List<ProductionDto> findAllByMinion(MinionDto minionDto);
}
