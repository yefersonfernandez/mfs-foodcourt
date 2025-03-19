package com.pragma.plazoleta.infrastructure.out.feignclients.mapper;

import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.infrastructure.out.feignclients.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserDtoMapper {

    @Mapping(source = "roleResponseDto", target = "roleModel")
    UserModel toUserModel(UserResponseDto userResponseDto);
}