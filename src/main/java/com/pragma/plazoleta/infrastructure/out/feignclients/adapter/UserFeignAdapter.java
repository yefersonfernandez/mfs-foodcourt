package com.pragma.plazoleta.infrastructure.out.feignclients.adapter;

import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.IUserFeignClientPort;
import com.pragma.plazoleta.infrastructure.out.feignclients.IUserFeignClients;
import com.pragma.plazoleta.infrastructure.out.feignclients.dto.UserResponseDto;
import com.pragma.plazoleta.infrastructure.out.feignclients.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserFeignClientPort {

    private final IUserFeignClients userFeignClients;
    private final IUserDtoMapper userDtoMapper;

    @Override
    public UserModel getUserById(Long userId) {
        UserResponseDto userResponseDto = userFeignClients.getUserById(userId);
        return userDtoMapper.toUserModel(userResponseDto);
    }
}
