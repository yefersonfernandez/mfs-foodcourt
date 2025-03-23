package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.exception.*;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RoleModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.IUserFeignClientPort;
import com.pragma.plazoleta.domain.utils.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    private static final long ROLE_ADMIN_ID = 1L;
    private static final String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    private static final String ROLE_ADMIN_DESCRIPTION = "Admin";

    private static final long ROLE_OWNER_ID = 2L;
    private static final String ROLE_OWNER_NAME = "ROLE_OWNER";
    private static final String ROLE_OWNER_DESCRIPTION = "Owner";

    private static final String DEFAULT_FIRST_NAME = "Juan";
    private static final String DEFAULT_LAST_NAME = "Perez";
    private static final String DEFAULT_DOCUMENT_NUMBER = "123456789";
    private static final String DEFAULT_PHONE_NUMBER = "+573005698325";
    private static final String DEFAULT_EMAIL = "juan.perez@example.com";
    private static final String DEFAULT_PASSWORD = "password123";
    private static final LocalDate DEFAULT_BIRTHDATE = LocalDate.of(2000, 1, 1);

    private static final long DEFAULT_RESTAURANT_ID = 1L;
    private static final String DEFAULT_RESTAURANT_NAME = "Dogor Food";
    private static final String DEFAULT_ADDRESS = "Calle #14-2";
    private static final long DEFAULT_OWNER_ID = 1L;
    private static final String DEFAULT_PHONE = "3133534222";
    private static final String DEFAULT_URL_LOGO = "https://i.pinimg.com/736x/71/26/0f/71260f1016fe47ad476fdada466d90db.jpg";
    private static final String DEFAULT_NIT = "1097345976";

    private static final String INVALID_NIT = "1094187650a";
    private static final String INVALID_PHONE = "313313988q";
    private static final String INVALID_NAME = "1234";
    private static final String EMPTY_STRING = "";

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserFeignClientPort userFeignClientPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private RestaurantModel restaurantModel;
    private UserModel userModel;

    @BeforeEach
    void setUp() {
        userModel = new UserModel();
        userModel.setFirstName(DEFAULT_FIRST_NAME);
        userModel.setLastName(DEFAULT_LAST_NAME);
        userModel.setDocumentNumber(DEFAULT_DOCUMENT_NUMBER);
        userModel.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        userModel.setBirthdate(DEFAULT_BIRTHDATE);
        userModel.setEmail(DEFAULT_EMAIL);
        userModel.setPassword(DEFAULT_PASSWORD);
        userModel.setRoleModel(new RoleModel(ROLE_ADMIN_ID, ROLE_ADMIN_NAME, ROLE_ADMIN_DESCRIPTION));

        restaurantModel = new RestaurantModel();
        restaurantModel.setId(DEFAULT_RESTAURANT_ID);
        restaurantModel.setName(DEFAULT_RESTAURANT_NAME);
        restaurantModel.setAddress(DEFAULT_ADDRESS);
        restaurantModel.setOwnerId(DEFAULT_OWNER_ID);
        restaurantModel.setPhone(DEFAULT_PHONE);
        restaurantModel.setUrlLogo(DEFAULT_URL_LOGO);
        restaurantModel.setNit(DEFAULT_NIT);
    }

    @Test
    void saveRestaurant_SuccessfullyCreatesRestaurant() {
        when(userFeignClientPort.getUserById(anyLong())).thenReturn(userModel);
        doNothing().when(restaurantPersistencePort).saveRestaurant(any(RestaurantModel.class));

        assertDoesNotThrow(() -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurantModel);
    }

    @Test
    void saveRestaurant_InvalidNit_ThrowsException() {
        restaurantModel.setNit(INVALID_NIT);
        InvalidNitException exception = assertThrows(InvalidNitException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_NIT, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_InvalidPhoneNumber_ThrowsException() {
        restaurantModel.setPhone(INVALID_PHONE);
        InvalidPhoneNumberException exception = assertThrows(InvalidPhoneNumberException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_PHONE, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_InvalidName_ThrowsException() {
        restaurantModel.setName(INVALID_NAME);
        InvalidRestaurantNameException exception = assertThrows(InvalidRestaurantNameException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_RESTAURANT_NAME, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyName_ThrowsException() {
        restaurantModel.setName(EMPTY_STRING);
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyNit_ThrowsException() {
        restaurantModel.setNit(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.NIT_REQUIRED, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyPhone_ThrowsException() {
        restaurantModel.setPhone(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.PHONE_REQUIRED, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyAddress_ThrowsException() {
        restaurantModel.setAddress(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.ADDRESS_REQUIRED, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyUrlLogo_ThrowsException() {
        restaurantModel.setUrlLogo(EMPTY_STRING);
        MissingRequiredFieldsException exception = assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.LOGO_URL_REQUIRED, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_UserNotFound_ThrowsException() {
        when(userFeignClientPort.getUserById(anyLong())).thenReturn(null);
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void saveRestaurant_InvalidUserRole_ThrowsException() {
        userModel.setRoleModel(new RoleModel(ROLE_OWNER_ID, ROLE_OWNER_NAME, ROLE_OWNER_DESCRIPTION));
        when(userFeignClientPort.getUserById(anyLong())).thenReturn(userModel);
        InvalidUserRoleException exception = assertThrows(InvalidUserRoleException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_USER_ROLE, exception.getMessage());
    }

    @Test
    void getRestaurantById_ExistingDish_ReturnsRestaurant() {
        when(restaurantPersistencePort.getRestaurantById(DEFAULT_RESTAURANT_ID)).thenReturn(restaurantModel);

        RestaurantModel foundRestaurant = restaurantUseCase.getRestaurantById(DEFAULT_RESTAURANT_ID);

        assertNotNull(foundRestaurant);
        assertEquals(DEFAULT_RESTAURANT_ID, foundRestaurant.getId());
        assertEquals(DEFAULT_RESTAURANT_NAME, foundRestaurant.getName());
        verify(restaurantPersistencePort, times(1)).getRestaurantById(DEFAULT_RESTAURANT_ID);
    }

    @Test
    void getDishById_NonExistingDish_ReturnsNull() {
        when(restaurantPersistencePort.getRestaurantById(DEFAULT_RESTAURANT_ID)).thenReturn(null);

        RestaurantModel foundRestaurant = restaurantUseCase.getRestaurantById(DEFAULT_RESTAURANT_ID);

        assertNull(foundRestaurant);
        verify(restaurantPersistencePort, times(1)).getRestaurantById(DEFAULT_RESTAURANT_ID);
    }
}
