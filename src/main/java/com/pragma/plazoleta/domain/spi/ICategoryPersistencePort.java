package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.CategoryModel;

import java.util.Optional;

public interface ICategoryPersistencePort {

    void saveCategory(CategoryModel categoryModel);

    Optional<CategoryModel> getCategoryById(Long categoryId);

}
