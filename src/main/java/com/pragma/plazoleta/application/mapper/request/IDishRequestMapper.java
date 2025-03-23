package com.pragma.plazoleta.application.mapper.request;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(source = "restaurantId", target = "restaurantModel.id")
    @Mapping(source = "categoryId", target = "categoryModel.id")
    DishModel toDishModel(DishRequestDto dishRequestDto);

}
