package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.Upgrade;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpgradeDto {

    protected String name;
    protected Upgrade.Type type;
    protected Integer order;
    protected String extraItemName;
    protected Integer extraItemChance;
    protected Integer extraItemAmount;
    protected Boolean isUniversal;
    protected Integer speedBoost;

    public static UpgradeDto from(Upgrade upgrade){
        return UpgradeDto.builder()
                .name(upgrade.getName())
                .type(upgrade.getType())
                .order(upgrade.getOrder())
                .extraItemName(upgrade.getExtraItemName())
                .extraItemChance(upgrade.getExtraItemChance())
                .extraItemAmount(upgrade.getExtraItemAmount())
                .isUniversal(upgrade.getIsUniversal())
                .speedBoost(upgrade.getSpeedBoost())
                .build();
    }

    public static List<UpgradeDto> from(List<Upgrade> upgrades){
        return upgrades.stream().map(UpgradeDto::from).collect(Collectors.toList());
    }

    public static Upgrade to (UpgradeDto upgradeDto, Upgrade upgrade){
        upgrade.setType(upgradeDto.getType());
        upgrade.setOrder(upgradeDto.getOrder());
        upgrade.setExtraItemName(upgradeDto.getExtraItemName());
        upgrade.setExtraItemChance(upgradeDto.getExtraItemChance());
        upgrade.setExtraItemAmount(upgradeDto.getExtraItemAmount());
        upgrade.setIsUniversal(upgradeDto.getIsUniversal());
        upgrade.setSpeedBoost(upgradeDto.getSpeedBoost());
        return upgrade;
    }

}
