package ru.itis.javalab.data.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.MinionDto;
import ru.itis.javalab.data.services.MinionsService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@Controller
@RequestMapping("/minion")
@Log
@CrossOrigin
public class MinionsController {

    @Autowired
    protected MinionsService minionsService;

    @ApiOperation(value = "Getting all minions.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = MinionDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MinionDto>> getAllMinions() {
        return ResponseEntity.ok(minionsService.findAll());
    }

    @ApiOperation(value = "Add new minion.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = MinionDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<MinionDto> addNewMinion(MinionDto minionDto){
        return ResponseEntity.ok(minionsService.add(minionDto));
    }

    @ApiOperation(value = "Delete minion by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{minion-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteMinion(@PathVariable("minion-name") String minionName){
        minionsService.delete(MinionDto.builder().name(minionName).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update minion by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<MinionDto> updateMinion(MinionDto minionDto){
        return ResponseEntity.ok(minionsService.update(minionDto));
    }

    @ApiOperation(value = "Get minion by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = MinionDto.class)})
    @GetMapping(
            value = "/{minion-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MinionDto> getMinionByName(@PathVariable("minion-name") String minionName){
        return ResponseEntity.ok(minionsService.findMinionByName(minionName));
    }

}
