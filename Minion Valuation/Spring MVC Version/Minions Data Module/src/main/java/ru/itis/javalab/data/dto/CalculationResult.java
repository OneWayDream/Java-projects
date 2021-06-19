package ru.itis.javalab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculationResult {

    protected Boolean isSuccess;
    protected String errorMessage;
    protected String warningMessage;
    protected Double result;

}
