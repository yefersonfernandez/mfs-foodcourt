package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.exception.*;
import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.utils.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    private static final String DEFAULT_DISH_NAME = "Hamburguesa";
    private static final int DEFAULT_PRICE = 15000;
    private static final String DEFAULT_DESCRIPTION = "Deliciosa hamburguesa con queso";
    private static final String DEFAULT_IMAGE_URL = "https://example.com/hamburguesa.jpg";
    private static final long DEFAULT_RESTAURANT_ID = 1L;
    private static final long DEFAULT_CATEGORY_ID = 1L;
    private static final long DEFAULT_DISH_ID = 10L;

    private static final int INVALID_PRICE = -500;
    private static final String EMPTY_STRING = "";

    private static final int UPDATED_PRICE = 20000;
    private static final String UPDATED_DESCRIPTION = "Updated description";

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    private DishModel dishModel;
    private RestaurantModel restaurantModel;
    private CategoryModel categoryModel;

    @BeforeEach
    void setUp() {
        restaurantModel = new RestaurantModel();
        restaurantModel.setId(DEFAULT_RESTAURANT_ID);

        categoryModel = new CategoryModel();
        categoryModel.setId(DEFAULT_CATEGORY_ID);

        dishModel = new DishModel();
        dishModel.setId(DEFAULT_DISH_ID);
        dishModel.setName(DEFAULT_DISH_NAME);
        dishModel.setPrice(DEFAULT_PRICE);
        dishModel.setDescription(DEFAULT_DESCRIPTION);
        dishModel.setImageUrl(DEFAULT_IMAGE_URL);
        dishModel.setRestaurantModel(restaurantModel);
        dishModel.setCategoryModel(categoryModel);
    }

    @Test
    void saveDish_SuccessfullyCreatesDish() {
        when(restaurantPersistencePort.getRestaurantById(anyLong())).thenReturn(restaurantModel);
        when(categoryPersistencePort.getCategoryById(anyLong())).thenReturn(categoryModel);
        doNothing().when(dishPersistencePort).saveDish(any(DishModel.class));

        assertDoesNotThrow(() -> dishUseCase.saveDish(dishModel));
        verify(dishPersistencePort, times(1)).saveDish(dishModel);
    }

    @Test
    void saveDish_EmptyName_ThrowsException() {
        dishModel.setName(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_NAME_REQUIRED, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_EmptyDescription_ThrowsException() {
        dishModel.setDescription(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_DESCRIPTION_REQUIRED, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_EmptyImageUrl_ThrowsException() {
        dishModel.setImageUrl(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_IMAGE_URL_REQUIRED, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_NullRestaurantId_ThrowsException() {
        dishModel.getRestaurantModel().setId(null);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_RESTAURANT_REQUIRED, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_NullCategoryId_ThrowsException() {
        dishModel.getCategoryModel().setId(null);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_CATEGORY_REQUIRED, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_NegativePrice_ThrowsException() {
        dishModel.setPrice(INVALID_PRICE);
        InvalidPriceException exception = assertThrows(InvalidPriceException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.DISH_PRICE_MUST_BE_POSITIVE, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void saveDish_RestaurantNotFound_ThrowsException() {
        when(restaurantPersistencePort.getRestaurantById(anyLong())).thenReturn(null);
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.RESTAURANT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void saveDish_CategoryNotFound_ThrowsException() {
        when(restaurantPersistencePort.getRestaurantById(anyLong())).thenReturn(restaurantModel);
        when(categoryPersistencePort.getCategoryById(anyLong())).thenReturn(null);
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> dishUseCase.saveDish(dishModel));
        assertEquals(ErrorMessages.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getDishById_ExistingDish_ReturnsDish() {
        when(dishPersistencePort.getDishById(DEFAULT_DISH_ID)).thenReturn(dishModel);

        DishModel foundDish = dishUseCase.getDishById(DEFAULT_DISH_ID);

        assertNotNull(foundDish);
        assertEquals(DEFAULT_DISH_ID, foundDish.getId());
        assertEquals(DEFAULT_DISH_NAME, foundDish.getName());
        verify(dishPersistencePort, times(1)).getDishById(DEFAULT_DISH_ID);
    }

    @Test
    void getDishById_NonExistingDish_ReturnsNull() {
        when(dishPersistencePort.getDishById(DEFAULT_DISH_ID)).thenReturn(null);

        DishModel foundDish = dishUseCase.getDishById(DEFAULT_DISH_ID);

        assertNull(foundDish);
        verify(dishPersistencePort, times(1)).getDishById(DEFAULT_DISH_ID);
    }

    @Test
    void updateDish_SuccessfullyUpdatesDish(){
        when(dishPersistencePort.getDishById(DEFAULT_DISH_ID)).thenReturn(dishModel);
        doNothing().when(dishPersistencePort).saveDish(any(DishModel.class));

        DishModel updatedDish = new DishModel();
        updatedDish.setPrice(UPDATED_PRICE);
        updatedDish.setDescription(UPDATED_DESCRIPTION);

        assertDoesNotThrow(() -> dishUseCase.updateDish(DEFAULT_DISH_ID, updatedDish));

        verify(dishPersistencePort, times(1)).getDishById(DEFAULT_DISH_ID);
        verify(dishPersistencePort, times(1)).saveDish(any(DishModel.class));

        assertEquals(UPDATED_PRICE, dishModel.getPrice());
        assertEquals(UPDATED_DESCRIPTION, dishModel.getDescription());

        assertEquals(DEFAULT_DISH_NAME, dishModel.getName());
        assertEquals(DEFAULT_IMAGE_URL, dishModel.getImageUrl());
        assertEquals(restaurantModel, dishModel.getRestaurantModel());
        assertEquals(categoryModel, dishModel.getCategoryModel());
    }

    @Test
    void updateDish_DishNotFound_ThrowsException(){
        when(dishPersistencePort.getDishById(DEFAULT_DISH_ID)).thenReturn(null);

        DishModel updatedDish = new DishModel();
        updatedDish.setPrice(UPDATED_PRICE);
        updatedDish.setDescription(UPDATED_DESCRIPTION);

        Executable action = () -> dishUseCase.updateDish(DEFAULT_DISH_ID, updatedDish);
        DishNotFoundException exception = assertThrows(DishNotFoundException.class, action);
        assertEquals(ErrorMessages.DISH_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateDish_NegativePrice_ThrowsException() {
        when(dishPersistencePort.getDishById(DEFAULT_DISH_ID)).thenReturn(dishModel);

        DishModel updatedDish = new DishModel();
        updatedDish.setPrice(INVALID_PRICE);
        updatedDish.setDescription(UPDATED_DESCRIPTION);

        Executable action = () -> dishUseCase.updateDish(DEFAULT_DISH_ID, updatedDish);
        InvalidPriceException exception = assertThrows(InvalidPriceException.class, action);

        assertEquals(ErrorMessages.DISH_PRICE_MUST_BE_POSITIVE, exception.getMessage());
        verify(dishPersistencePort, never()).saveDish(any());
    }

}
