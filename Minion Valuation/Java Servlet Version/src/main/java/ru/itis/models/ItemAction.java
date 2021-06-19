package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ItemAction {

    String itemName;
    String resultName;
    Integer inAmount;
    Integer outAmount;

}
