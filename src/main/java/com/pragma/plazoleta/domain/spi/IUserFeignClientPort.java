package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.UserModel;

import java.util.Optional;

public interface IUserFeignClientPort {

    Optional<UserModel> getUserById(Long userId);

}
