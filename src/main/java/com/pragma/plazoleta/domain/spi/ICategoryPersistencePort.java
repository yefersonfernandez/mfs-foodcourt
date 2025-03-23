package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.CategoryModel;

public interface ICategoryPersistencePort {

    void saveCategory(CategoryModel categoryModel);

    CategoryModel getCategoryById(Long categoryId);

}
