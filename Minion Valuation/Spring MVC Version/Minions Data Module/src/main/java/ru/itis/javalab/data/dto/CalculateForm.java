package ru.itis.javalab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateForm {

    protected String minionName;
    protected Integer minionTier;
    protected String fuelName;
    protected String firstUpgradeName;
    protected String secondUpgradeName;

}
