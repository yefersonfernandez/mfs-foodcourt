package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.ICategoryServicePort;
import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(CategoryModel categoryModel) {
        categoryPersistencePort.saveCategory(categoryModel);
    }

    @Override
    public CategoryModel getCategoryById(Long categoryId) {
        return categoryPersistencePort.getCategoryById(categoryId);
    }

}
