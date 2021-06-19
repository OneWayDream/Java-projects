package ru.itis.javalab.data.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.FuelDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.Fuel;
import ru.itis.javalab.data.repositories.MinionFuelsRepository;

import java.util.List;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    protected MinionFuelsRepository fuelsRepository;

    @Override
    public List<FuelDto> findAll() {
        return FuelDto.from(fuelsRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public FuelDto add(FuelDto fuelDto) {
        Fuel newFuel = Fuel.builder()
                .name(fuelDto.getName())
                .speedBoost(fuelDto.getSpeedBoost())
                .type(fuelDto.getType())
                .build();
        fuelsRepository.save(newFuel);
        return FuelDto.from(newFuel);
    }

    @Override
    public FuelDto findById(Long id) {
        return FuelDto.from(fuelsRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void delete(FuelDto fuelDto) {
        Fuel fuelForDelete = fuelsRepository.findByName(fuelDto.getName())
                .filter(fuel -> fuel.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no fuel with name "
                        + fuelDto.getName()));
        fuelForDelete.setIsDeleted(true);
        fuelsRepository.save(fuelForDelete);
    }

    @Override
    public FuelDto update(FuelDto fuelDto) {
        Fuel fuelForUpdate = fuelsRepository.findByName(fuelDto.getName())
                .filter(fuel -> fuel.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no fuel with name "
                        + fuelDto.getName()));
        fuelsRepository.save(FuelDto.to(fuelDto, fuelForUpdate));
        return FuelDto.from(fuelForUpdate);
    }

    @Override
    public FuelDto findFuelByName(String fuelName) {
        return FuelDto.from(fuelsRepository.findByName(fuelName)
                .filter(fuel -> fuel.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }
}
