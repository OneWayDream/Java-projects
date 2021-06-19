package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.BazaarItem;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BazaarItemDto {

    protected String name;
    protected Double sellPrice;
    protected Double buyPrice;

    public static BazaarItemDto from(BazaarItem bazaarItem){
        return BazaarItemDto.builder()
                .name(bazaarItem.getName())
                .sellPrice(bazaarItem.getSellPrice())
                .buyPrice(bazaarItem.getBuyPrice())
                .build();
    }

    public static List<BazaarItemDto> from(List<BazaarItem> items){
        return items.stream().map(BazaarItemDto::from).collect(Collectors.toList());
    }

    public static BazaarItem to (BazaarItemDto itemDto, BazaarItem item){
        item.setBuyPrice(itemDto.getBuyPrice());
        item.setSellPrice(itemDto.getSellPrice());
        return item;
    }

}
