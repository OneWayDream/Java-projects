package ru.itis.javalab.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.Production;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionDto {

    protected Long id;
    protected String itemName;
    protected String minionName;
    protected Integer chance;
    protected Double amount;

    public static ProductionDto from(Production production){
        return ProductionDto.builder()
                .id(production.getId())
                .itemName(production.getItemName())
                .minionName(production.getMinion().getName())
                .chance(production.getChance())
                .amount(production.getAmount())
                .build();
    }

    public static List<ProductionDto> from(List<Production> productions){
        return productions.stream().map(ProductionDto::from).collect(Collectors.toList());
    }


}
