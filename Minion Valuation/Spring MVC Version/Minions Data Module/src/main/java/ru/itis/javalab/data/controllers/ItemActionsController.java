package ru.itis.javalab.data.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.data.dto.ItemActionDto;
import ru.itis.javalab.data.services.ItemActionsService;
import ru.itis.javalab.data.annotations.Log;

import java.util.List;

@RestController
@RequestMapping("/processing")
@Log
@CrossOrigin
public class ItemActionsController {

    @Autowired
    protected ItemActionsService itemActionsService;

    @ApiOperation(value = "Getting all item actions.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success getting", response = ItemActionDto.class)})
    @GetMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ItemActionDto>> getAllItemActions() {
        return ResponseEntity.ok(itemActionsService.findAll());
    }

    @ApiOperation(value = "Add new item action.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful add.", response = ItemActionDto.class)})
    @PostMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<ItemActionDto> addNewItemAction(ItemActionDto itemActionDto){
        return ResponseEntity.ok(itemActionsService.add(itemActionDto));
    }

    @ApiOperation(value = "Delete item action by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful delete.")})
    @DeleteMapping(
            value = "/{id}",
            headers = {"JWT"}

    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<?> deleteItemAction(@PathVariable("id") Long id){
        itemActionsService.delete(ItemActionDto.builder().id(id).build());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update item action by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful update.")})
    @PatchMapping(
            headers = {"JWT"}
    )
    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    public ResponseEntity<ItemActionDto> updateItemAction(ItemActionDto itemActionDto){
        return ResponseEntity.ok(itemActionsService.update(itemActionDto));
    }

    @ApiOperation(value = "Get item action by name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful find.", response = ItemActionDto.class)})
    @GetMapping(
            value = "/{id}",
            headers = {"JWT"}
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ItemActionDto> getItemByName(@PathVariable("id") Long id){
        return ResponseEntity.ok(itemActionsService.findById(id));
    }

}
