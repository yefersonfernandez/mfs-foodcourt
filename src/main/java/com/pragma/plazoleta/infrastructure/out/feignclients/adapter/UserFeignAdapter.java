package com.pragma.plazoleta.infrastructure.out.feignclients.adapter;

import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.IUserFeignClientPort;
import com.pragma.plazoleta.infrastructure.out.feignclients.IUserFeignClients;
import com.pragma.plazoleta.infrastructure.out.feignclients.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserFeignClientPort {

    private final IUserFeignClients userFeignClients;
    private final IUserDtoMapper userDtoMapper;

    @Override
    public Optional<UserModel> getUserById(Long userId) {
        return Optional.ofNullable(userFeignClients.getUserById(userId))
                .map(userDtoMapper::toUserModel);
    }

}
