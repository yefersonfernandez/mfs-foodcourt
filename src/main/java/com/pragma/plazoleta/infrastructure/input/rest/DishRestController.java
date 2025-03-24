package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.infrastructure.utils.ApiDescriptions;
import com.pragma.plazoleta.infrastructure.utils.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(ApiPaths.BASE_DISH)
@RequiredArgsConstructor
@Tag(name = "dish", description = ApiDescriptions.DISH_TAG)
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = ApiDescriptions.CREATE_DISH_SUMMARY,
            description = ApiDescriptions.CREATE_DISH_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiDescriptions.CREATE_DISH_SUCCESS),
            @ApiResponse(responseCode = "400", description = ApiDescriptions.CREATE_DISH_BAD_REQUEST)
    })
    @PostMapping(ApiPaths.CREATE_DISH)
    public ResponseEntity<Void> createDish(
            @Parameter(description = ApiDescriptions.CREATE_DISH_PARAM, required = true)
            @RequestBody DishRequestDto dishRequestDto
    ) {
        dishHandler.saveDish(dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ApiDescriptions.GET_DISH_BY_ID_SUMMARY,
            description = ApiDescriptions.GET_DISH_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiDescriptions.GET_DISH_BY_ID_SUCCESS),
            @ApiResponse(responseCode = "404", description = ApiDescriptions.GET_DISH_BY_ID_NOT_FOUND)
    })
    @GetMapping(ApiPaths.GET_DISH_BY_ID)
    public ResponseEntity<DishResponseDto> getDishById(
            @Parameter(description = ApiDescriptions.GET_DISH_BY_ID_PARAM, required = true)
            @PathVariable(name = "id") Long dishId
    ) {
        DishResponseDto response = dishHandler.getDishById(dishId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = ApiDescriptions.UPDATE_DISH_SUMMARY,
            description = ApiDescriptions.UPDATE_DISH_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ApiDescriptions.UPDATE_DISH_SUCCESS),
            @ApiResponse(responseCode = "400", description = ApiDescriptions.UPDATE_DISH_BAD_REQUEST),
            @ApiResponse(responseCode = "404", description = ApiDescriptions.UPDATE_DISH_NOT_FOUND)
    })
    @PatchMapping(ApiPaths.UPDATE_DISH)
    public ResponseEntity<Void> updateDish(
            @Parameter(description = ApiDescriptions.UPDATE_DISH_PARAM, required = true)
            @PathVariable(name = "id") Long dishId,
            @RequestBody DishRequestDto dishRequestDto
    ) {
        dishHandler.updateDish(dishId, dishRequestDto);
        return ResponseEntity.noContent().build();
    }

}