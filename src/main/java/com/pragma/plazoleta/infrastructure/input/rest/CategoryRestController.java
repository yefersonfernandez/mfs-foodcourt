package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.application.handler.ICategoryHandler;
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
@RequestMapping(ApiPaths.BASE_CATEGORY)
@RequiredArgsConstructor
@Tag(name = "category", description = ApiDescriptions.CATEGORY_TAG)
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = ApiDescriptions.CREATE_CATEGORY_SUMMARY,
            description = ApiDescriptions.CREATE_CATEGORY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiDescriptions.CREATE_CATEGORY_SUCCESS),
            @ApiResponse(responseCode = "409", description = ApiDescriptions.CREATE_CATEGORY_CONFLICT)
    })
    @PostMapping(ApiPaths.CREATE_CATEGORY)
    public ResponseEntity<Void> createCategory(
            @Parameter(description = ApiDescriptions.CREATE_CATEGORY_PARAM, required = true)
            @RequestBody CategoryRequestDto categoryRequestDto
    ) {
        categoryHandler.saveCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ApiDescriptions.GET_CATEGORY_BY_ID_SUMMARY,
            description = ApiDescriptions.GET_CATEGORY_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiDescriptions.GET_CATEGORY_BY_ID_SUCCESS),
            @ApiResponse(responseCode = "404", description = ApiDescriptions.GET_CATEGORY_BY_ID_NOT_FOUND)
    })
    @GetMapping(ApiPaths.GET_CATEGORY_BY_ID)
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @Parameter(description = ApiDescriptions.GET_CATEGORY_BY_ID_PARAM, required = true)
            @PathVariable(name = "id") Long categoryId
    ) {
        CategoryResponseDto response = categoryHandler.getCategoryById(categoryId);
        return ResponseEntity.ok(response);
    }
}