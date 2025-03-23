package com.pragma.plazoleta.application.mapper.response;

import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

    CategoryResponseDto toCategoryResponseDto(CategoryModel categoryModel);

}
