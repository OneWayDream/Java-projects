package ru.itis.javalab.server.dto.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Top3BazaarInfo {

    protected String minionName;
    protected Double profit;
    protected Integer place;
    protected String imageName;

}
