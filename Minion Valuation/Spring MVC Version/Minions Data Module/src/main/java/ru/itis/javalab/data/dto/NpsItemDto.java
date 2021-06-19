package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.NpsItem;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NpsItemDto {

    protected String name;
    protected Double sellPrice;

    public static NpsItemDto from(NpsItem npsItem){
        return NpsItemDto.builder()
                .name(npsItem.getName())
                .sellPrice(npsItem.getSellPrice())
                .build();
    }

    public static List<NpsItemDto> from(List<NpsItem> items){
        return items.stream().map(NpsItemDto::from).collect(Collectors.toList());
    }

    public static NpsItem to (NpsItemDto itemDto, NpsItem item){
        item.setSellPrice(itemDto.getSellPrice());
        return item;
    }

}
