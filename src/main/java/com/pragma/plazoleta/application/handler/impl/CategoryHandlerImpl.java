package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.CategoryRequestDto;
import com.pragma.plazoleta.application.dto.response.CategoryResponseDto;
import com.pragma.plazoleta.application.handler.ICategoryHandler;
import com.pragma.plazoleta.application.mapper.request.ICategoryRequestMapper;
import com.pragma.plazoleta.application.mapper.response.ICategoryResponseMapper;
import com.pragma.plazoleta.domain.api.ICategoryServicePort;
import com.pragma.plazoleta.domain.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandlerImpl implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        CategoryModel categoryModel = categoryRequestMapper.toCategoryModel(categoryRequestDto);
        categoryServicePort.saveCategory(categoryModel);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        CategoryModel categoryModel = categoryServicePort.getCategoryById(categoryId);
        return categoryResponseMapper.toCategoryResponseDto(categoryModel);
    }
}
