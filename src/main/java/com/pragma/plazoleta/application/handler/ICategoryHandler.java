package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;

public interface ICategoryHandler {

    void saveCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategoryById(Long categoryId);

}
