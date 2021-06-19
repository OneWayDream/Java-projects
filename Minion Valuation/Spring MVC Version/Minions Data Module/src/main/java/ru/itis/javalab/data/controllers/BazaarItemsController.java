package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.annotations.Log;
import ru.itis.javalab.data.dto.BazaarItemDto;
import ru.itis.javalab.data.services.BazaarItemsService;

import java.util.List;

@RestController
@RequestMapping("/bazaar-item")
@Log
@CrossOrigin
public class BazaarItemsController {

    @Autowired
    protected BazaarItemsService bazaarItemsService;

    @ApiOperation(value = "Getting all bazaar items.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = BazaarItemDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BazaarItemDto>> getAllItems() {
        return ResponseEntity.ok(bazaarItemsService.findAll());
    }

    @ApiOperation(value = "Add new item.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = BazaarItemDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<BazaarItemDto> addNewItem(BazaarItemDto itemDto){
        return ResponseEntity.ok(bazaarItemsService.add(itemDto));
    }

    @ApiOperation(value = "Delete item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{item-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteItem(@PathVariable("item-name") String itemName){
        bazaarItemsService.delete(BazaarItemDto.builder().name(itemName).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<BazaarItemDto> updateItem(BazaarItemDto itemDto){
        return ResponseEntity.ok(bazaarItemsService.update(itemDto));
    }

    @ApiOperation(value = "Get item by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = BazaarItemDto.class)})
    @GetMapping(
            value = "/{item-name}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BazaarItemDto> getItemByName(@PathVariable("item-name") String itemName){
        return ResponseEntity.ok(bazaarItemsService.findItemByName(itemName));
    }

}
