package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.Fuel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuelDto {

    protected String name;
    protected Double speedBoost;
    protected Fuel.Type type;

    public static FuelDto from(Fuel fuel){
        return FuelDto.builder()
                .name(fuel.getName())
                .speedBoost(fuel.getSpeedBoost())
                .type(fuel.getType())
                .build();
    }

    public static List<FuelDto> from(List<Fuel> fuels){
        return fuels.stream().map(FuelDto::from).collect(Collectors.toList());
    }

    public static Fuel to (FuelDto fuelDto, Fuel fuel){
        fuel.setSpeedBoost(fuelDto.getSpeedBoost());
        fuel.setType(fuelDto.getType());
        return fuel;
    }

}
