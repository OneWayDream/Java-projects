package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.CalculateForm;
import ru.itis.javalab.data.dto.CalculationResult;

public interface CalculationService {

    CalculationResult handleCalculationForm(CalculateForm calculateForm);

}
