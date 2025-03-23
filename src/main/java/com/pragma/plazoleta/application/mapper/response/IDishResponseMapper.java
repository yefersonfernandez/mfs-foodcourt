package com.pragma.plazoleta.application.mapper.response;

import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    @Mapping(source = "restaurantModel", target = "restaurantResponseDto")
    @Mapping(source = "categoryModel", target = "categoryResponseDto")
    DishResponseDto toDishResponseDto(DishModel dishModel);

}
