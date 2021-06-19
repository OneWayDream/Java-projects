package ru.itis.javalab.server.dto.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuelCalculateData {

    protected String name;
    protected String optionValue;
    protected String imageName;

}
