package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    @Mapping(source = "restaurantModel", target = "restaurantEntity")
    @Mapping(source = "categoryModel", target = "categoryEntity")
    DishEntity toDishEntity(DishModel dishModel);

    @Mapping(source = "restaurantEntity", target = "restaurantModel")
    @Mapping(source = "categoryEntity", target = "categoryModel")
    DishModel toDishModel(DishEntity dishEntity);
}
