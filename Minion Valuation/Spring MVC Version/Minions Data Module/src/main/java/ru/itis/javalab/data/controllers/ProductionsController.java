package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.ProductionDto;
import ru.itis.javalab.data.services.ProductionsService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@RestController
@RequestMapping("/production")
@Log
@CrossOrigin
public class ProductionsController {

    @Autowired
    protected ProductionsService productionsService;

    @ApiOperation(value = "Getting all productions.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = ProductionDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductionDto>> getAllProductions() {
        return ResponseEntity.ok(productionsService.findAll());
    }

    @ApiOperation(value = "Add new production.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = ProductionDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<ProductionDto> addNewProduction(ProductionDto productionDto){
        return ResponseEntity.ok(productionsService.add(productionDto));
    }

    @ApiOperation(value = "Delete production by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{id}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteProduction(@PathVariable("id") Long id){
        productionsService.delete(ProductionDto.builder().id(id).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update production by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<ProductionDto> updateProduction(ProductionDto productionDto){
        return ResponseEntity.ok(productionsService.update(productionDto));
    }

    @ApiOperation(value = "Get production by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = ProductionDto.class)})
    @GetMapping(
            value = "/{id}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductionDto> getUpgradeByName(@PathVariable("id") Long id){
        return ResponseEntity.ok(productionsService.findById(id));
    }

}
