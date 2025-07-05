package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(CategoryModel categoryModel) {
        CategoryEntity categoryEntity = categoryEntityMapper.toCategoryEntity(categoryModel);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public Optional<CategoryModel> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryEntityMapper::toCategoryModel);
    }
}
