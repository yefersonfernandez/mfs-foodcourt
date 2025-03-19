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
        userModel.setFirstName("Juan");
        userModel.setLastName("Perez");
        userModel.setDocumentNumber("123456789");
        userModel.setPhoneNumber("+573005698325");
        userModel.setBirthdate(LocalDate.of(2000, 1, 1));
        userModel.setEmail("juan.perez@example.com");
        userModel.setPassword("password123");
        userModel.setRoleModel(new RoleModel(1L, "ROLE_ADMIN", "Admin"));

        restaurantModel = new RestaurantModel();
        restaurantModel.setName("Dogor Food");
        restaurantModel.setAddress("Calle #14-2");
        restaurantModel.setOwnerId(1L);
        restaurantModel.setPhone("3133534222");
        restaurantModel.setUrlLogo("https://i.pinimg.com/736x/71/26/0f/71260f1016fe47ad476fdada466d90db.jpg");
        restaurantModel.setNit("1097345976");
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
        restaurantModel.setNit("1094187650a");
        InvalidNitException exception = assertThrows(InvalidNitException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_NIT, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_InvalidPhoneNumber_ThrowsException() {
        restaurantModel.setPhone("313313988q");
        InvalidPhoneNumberException exception = assertThrows(InvalidPhoneNumberException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_PHONE, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_InvalidName_ThrowsException() {
        restaurantModel.setName("1234");
        InvalidRestaurantNameException exception = assertThrows(InvalidRestaurantNameException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_RESTAURANT_NAME, exception.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyName_ThrowsException() {
        restaurantModel.setName("");
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_NullPhone_ThrowsException() {
        restaurantModel.setPhone(null);
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyAddress_ThrowsException() {
        restaurantModel.setAddress("");
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_NullOwnerId_ThrowsException() {
        restaurantModel.setOwnerId(null);
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        verify(restaurantPersistencePort, never()).saveRestaurant(any());
    }

    @Test
    void saveRestaurant_EmptyUrlLogo_ThrowsException() {
        restaurantModel.setUrlLogo("");
        assertThrows(MissingRequiredFieldsException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
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
        userModel.setRoleModel(new RoleModel(2L, "ROLE_OWNER", "Owner"));
        when(userFeignClientPort.getUserById(anyLong())).thenReturn(userModel);
        InvalidUserRoleException exception = assertThrows(InvalidUserRoleException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
        assertEquals(ErrorMessages.INVALID_USER_ROLE, exception.getMessage());
    }
}