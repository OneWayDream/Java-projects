package ru.itis.javalab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Top3NpsInfo {

    protected String minionName;
    protected Double profit;
    protected Integer place;

}
