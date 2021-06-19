package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.FuelDto;

public interface FuelService extends CrudService<FuelDto, Long> {

    FuelDto findFuelByName(String fuelName);

}
