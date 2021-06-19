package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Fuel {

    protected String name;
    protected Integer speedBoost;

}
