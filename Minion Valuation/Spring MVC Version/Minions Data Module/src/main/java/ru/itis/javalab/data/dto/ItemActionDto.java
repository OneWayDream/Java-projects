package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.ItemAction;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemActionDto {

    protected Long id;
    protected String itemName;
    protected String resultName;
    protected Integer inAmount;
    protected Integer outAmount;
    protected UpgradeDto upgradeDto;

    public static ItemActionDto from(ItemAction itemAction){
        return ItemActionDto.builder()
                .id(itemAction.getId())
                .itemName(itemAction.getItemName())
                .resultName(itemAction.getResultName())
                .inAmount(itemAction.getInAmount())
                .outAmount(itemAction.getOutAmount())
                .upgradeDto(UpgradeDto.from(itemAction.getUpgrade()))
                .build();
    }

    public static List<ItemActionDto> from(List<ItemAction> itemActions){
        return itemActions.stream().map(ItemActionDto::from).collect(Collectors.toList());
    }

}
