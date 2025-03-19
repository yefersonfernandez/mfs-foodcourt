package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.UserModel;

public interface IUserFeignClientPort {

    UserModel getUserById(Long userId);

}
