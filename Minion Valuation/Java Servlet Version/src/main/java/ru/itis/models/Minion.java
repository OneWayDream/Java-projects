package ru.itis.models;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Minion {

    protected String name;
    protected List<Double> timeBetweenActions;


}
