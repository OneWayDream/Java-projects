package ru.itis.javalab.data.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.data.dto.Top3BazaarInfo;
import ru.itis.javalab.data.dto.Top3NpsInfo;
import ru.itis.javalab.data.services.StatisticalService;

import java.util.List;

@RestController
@CrossOrigin
public class StatisticalController {

    @Autowired
    protected StatisticalService statisticalService;

    @ApiOperation(value = "Getting top 3 bazaar minions.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = Top3BazaarInfo.class)})
    @GetMapping(
            value = "/top-3-bazaar",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Top3BazaarInfo>> getBazaarTop3() {
        return ResponseEntity.ok(statisticalService.getBazaarTop3());
    }

    @ApiOperation(value = "Getting top 3 nps minions.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = Top3NpsInfo.class)})
    @GetMapping(
            value = "/top-3-nps",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Top3NpsInfo>> getNpsTop3() {
        return ResponseEntity.ok(statisticalService.getNpsTop3());
    }

}
