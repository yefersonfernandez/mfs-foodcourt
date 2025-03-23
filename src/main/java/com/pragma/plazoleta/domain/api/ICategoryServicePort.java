package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.CategoryModel;

public interface ICategoryServicePort {

    void saveCategory(CategoryModel categoryModel);

    CategoryModel getCategoryById(Long categoryId);

}
