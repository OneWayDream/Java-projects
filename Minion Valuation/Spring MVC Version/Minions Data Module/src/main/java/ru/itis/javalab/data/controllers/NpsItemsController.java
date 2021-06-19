package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.NpsItemDto;
import ru.itis.javalab.data.services.NpsItemsService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@RestController
@RequestMapping("/nps-item")
@Log
@CrossOrigin
public class NpsItemsController {

    @Autowired
    protected NpsItemsService npsItemsService;

    @ApiOperation(value = "Getting all nps items.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = NpsItemDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NpsItemDto>> getAllItems() {
        return ResponseEntity.ok(npsItemsService.findAll());
    }

    @ApiOperation(value = "Add new item.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = NpsItemDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<NpsItemDto> addNewItem(NpsItemDto itemDto){
        return ResponseEntity.ok(npsItemsService.add(itemDto));
    }

    @ApiOperation(value = "Delete item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{item-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteItem(@PathVariable("item-name") String itemName){
        npsItemsService.delete(NpsItemDto.builder().name(itemName).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<NpsItemDto> updateItem(NpsItemDto itemDto){
        return ResponseEntity.ok(npsItemsService.update(itemDto));
    }

    @ApiOperation(value = "Get item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = NpsItemDto.class)})
    @GetMapping(
            value = "/{item-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<NpsItemDto> getItemByName(@PathVariable("item-name") String itemName){
        return ResponseEntity.ok(npsItemsService.findItemByName(itemName));
    }

}
