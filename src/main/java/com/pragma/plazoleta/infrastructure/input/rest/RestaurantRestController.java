package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.infrastructure.utils.ApiPaths;
import com.pragma.plazoleta.infrastructure.utils.ApiDescriptions;
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
@RequestMapping(ApiPaths.BASE_RESTAURANT)
@RequiredArgsConstructor
@Tag(name = "restaurant", description = ApiDescriptions.RESTAURANT_TAG)
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = ApiDescriptions.CREATE_RESTAURANT_SUMMARY,
            description = ApiDescriptions.CREATE_RESTAURANT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiDescriptions.CREATE_RESTAURANT_SUCCESS),
            @ApiResponse(responseCode = "409", description = ApiDescriptions.CREATE_RESTAURANT_CONFLICT)
    })
    @PostMapping(ApiPaths.CREATE_RESTAURANT)
    public ResponseEntity<Void> createRestaurant(
            @Parameter(description = ApiDescriptions.CREATE_RESTAURANT_PARAM, required = true)
            @RequestBody RestaurantRequestDto restaurantRequestDto
    ) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ApiDescriptions.GET_RESTAURANT_BY_ID_SUMMARY,
            description = ApiDescriptions.GET_RESTAURANT_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiDescriptions.GET_RESTAURANT_BY_ID_SUCCESS),
            @ApiResponse(responseCode = "404", description = ApiDescriptions.GET_RESTAURANT_BY_ID_NOT_FOUND)
    })
    @GetMapping(ApiPaths.GET_RESTAURANT_BY_ID)
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(
            @Parameter(description = ApiDescriptions.GET_RESTAURANT_BY_ID_PARAM, required = true)
            @PathVariable(name = "id") Long restaurantId
    ) {
        RestaurantResponseDto response = restaurantHandler.getRestaurantById(restaurantId);
        return ResponseEntity.ok(response);
    }
}
