package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.Minion;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinionDto {

    protected String name;
    protected List<Double> timeBetweenActions;
    protected List<UpgradeDto> supportedUpgrades;

    public static MinionDto from(Minion minion){
        return MinionDto.builder()
                .name(minion.getName())
                .timeBetweenActions(minion.getTimeBetweenActions())
                .supportedUpgrades(UpgradeDto.from(minion.getSupportedUpgrades()))
                .build();
    }

    public static List<MinionDto> from(List<Minion> minions){
        return minions.stream().map(MinionDto::from).collect(Collectors.toList());
    }

}
