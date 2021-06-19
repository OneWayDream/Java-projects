package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.FuelDto;
import ru.itis.javalab.data.models.Fuel;
import ru.itis.javalab.data.services.FuelService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@RestController
@RequestMapping("/fuel")
@Log
@CrossOrigin
public class FuelsController {

    @Autowired
    protected FuelService fuelService;

    @ApiOperation(value = "Getting all fuels.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = FuelDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FuelDto>> getAllFuels() {
        return ResponseEntity.ok(fuelService.findAll());
    }

    @ApiOperation(value = "Add new fuel.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = FuelDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<FuelDto> addNewFuel(FuelDto fuelDto){
        return ResponseEntity.ok(fuelService.add(fuelDto));
    }

    @ApiOperation(value = "Delete fuel by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{fuel-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteFuel(@PathVariable("fuel-name") String fuelName){
        fuelService.delete(FuelDto.builder().name(fuelName).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update fuel by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<FuelDto> updateFuel(FuelDto fuelDto){
        return ResponseEntity.ok(fuelService.update(fuelDto));
    }

    @ApiOperation(value = "Get fuel by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = FuelDto.class)})
    @GetMapping(
            value = "/{fuel-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FuelDto> getFuelByName(@PathVariable("fuel-name") String fuelName){
        return ResponseEntity.ok(fuelService.findFuelByName(fuelName));
    }

}
