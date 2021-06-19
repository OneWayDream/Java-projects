package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Item {

    protected String itemName;
    protected Double buyBazaarPrice;
    protected Double sellBazaarPrice;
    protected Double sellNPSPrice;

}
