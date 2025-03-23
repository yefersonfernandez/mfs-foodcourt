package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    private static final long DEFAULT_CATEGORY_ID = 1L;
    private static final String DEFAULT_CATEGORY_NAME = "Fast Food";
    private static final String DEFAULT_CATEGORY_DESCRIPTION = "Comida rápida y deliciosa";

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private CategoryModel categoryModel;

    @BeforeEach
    void setUp() {
        categoryModel = new CategoryModel();
        categoryModel.setId(DEFAULT_CATEGORY_ID);
        categoryModel.setName(DEFAULT_CATEGORY_NAME);
        categoryModel.setDescription(DEFAULT_CATEGORY_DESCRIPTION);
    }

    @Test
    void saveCategory_SuccessfullySavesCategory() {
        doNothing().when(categoryPersistencePort).saveCategory(categoryModel);

        assertDoesNotThrow(() -> categoryUseCase.saveCategory(categoryModel));
        verify(categoryPersistencePort, times(1)).saveCategory(categoryModel);
    }

    @Test
    void getCategoryById_ExistingCategory_ReturnsCategory() {
        when(categoryPersistencePort.getCategoryById(DEFAULT_CATEGORY_ID)).thenReturn(categoryModel);

        CategoryModel foundCategory = categoryUseCase.getCategoryById(DEFAULT_CATEGORY_ID);

        assertNotNull(foundCategory);
        assertEquals(DEFAULT_CATEGORY_ID, foundCategory.getId());
        assertEquals(DEFAULT_CATEGORY_NAME, foundCategory.getName());
        verify(categoryPersistencePort, times(1)).getCategoryById(DEFAULT_CATEGORY_ID);
    }

    @Test
    void getCategoryById_NonExistingCategory_ReturnsNull() {
        when(categoryPersistencePort.getCategoryById(DEFAULT_CATEGORY_ID)).thenReturn(null);

        CategoryModel foundCategory = categoryUseCase.getCategoryById(DEFAULT_CATEGORY_ID);

        assertNull(foundCategory);
        verify(categoryPersistencePort, times(1)).getCategoryById(DEFAULT_CATEGORY_ID);
    }
}
