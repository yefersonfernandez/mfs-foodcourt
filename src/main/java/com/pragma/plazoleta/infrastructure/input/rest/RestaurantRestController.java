package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.BASE_RESTAURANTS)
@RequiredArgsConstructor
@Tag(name = "restaurant", description = ApiDescriptions.RESTAURANTS_TAG)
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = ApiDescriptions.CREATE_RESTAURANT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiDescriptions.CREATE_RESTAURANT_201),
            @ApiResponse(responseCode = "409", description = ApiDescriptions.CREATE_RESTAURANT_409)
    })
    @PostMapping(ApiPaths.CREATE_RESTAURANTS)
    public ResponseEntity<Void> createRestaurant(
            @Parameter(description = ApiDescriptions.CREATE_RESTAURANT_PARAM, required = true)
            @RequestBody RestaurantRequestDto restaurantRequestDto
    ) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
