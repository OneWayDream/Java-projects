package ru.itis.javalab.data.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.CalculateForm;
import ru.itis.javalab.data.dto.CalculationResult;
import ru.itis.javalab.data.services.CalculationService;

@RestController
public class CalculationController {

    @Autowired
    protected CalculationService calculationService;

    @ApiOperation(value = "Calculate profit for minion configuration")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = CalculationResult.class)})
    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<CalculationResult> handleCalculateForm(@RequestBody CalculateForm calculateForm){
        return ResponseEntity.ok(calculationService.handleCalculationForm(calculateForm));
    }

}
