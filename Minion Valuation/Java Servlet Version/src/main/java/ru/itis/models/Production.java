package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Production {

    String itemName;
    String minionName;
    Integer chance;
    Double amount;
}
