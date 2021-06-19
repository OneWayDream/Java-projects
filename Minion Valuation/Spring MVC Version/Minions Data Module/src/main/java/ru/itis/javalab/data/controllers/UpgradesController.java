package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.UpgradeDto;
import ru.itis.javalab.data.services.UpgradeService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@RestController
@RequestMapping("/upgrade")
@Log
@CrossOrigin
public class UpgradesController {

    @Autowired
    protected UpgradeService upgradeService;

    @ApiOperation(value = "Getting all upgrades.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = UpgradeDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UpgradeDto>> getAllUpgrades() {
        return ResponseEntity.ok(upgradeService.findAll());
    }

    @ApiOperation(value = "Add new upgrade.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = UpgradeDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<UpgradeDto> addNewUpgrade(UpgradeDto upgradeDto){
        return ResponseEntity.ok(upgradeService.add(upgradeDto));
    }

    @ApiOperation(value = "Delete upgrade by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{upgrade-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteUpgrade(@PathVariable("upgrade-name") String upgradeName){
        upgradeService.delete(UpgradeDto.builder().name(upgradeName).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update upgrade by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<UpgradeDto> updateUpgrade(UpgradeDto upgradeDto){
        return ResponseEntity.ok(upgradeService.update(upgradeDto));
    }

    @ApiOperation(value = "Get upgrade by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = UpgradeDto.class)})
    @GetMapping(
            value = "/{upgrade-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UpgradeDto> getUpgradeByName(@PathVariable("upgrade-name") String upgradeName){
        return ResponseEntity.ok(upgradeService.findUpgradeByName(upgradeName));
    }

}
