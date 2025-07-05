package com.pragma.plazoleta.infrastructure.out.feignclients;

import com.pragma.plazoleta.infrastructure.out.feignclients.config.FeignConfig;
import com.pragma.plazoleta.infrastructure.out.feignclients.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${clients.users.name}", url = "${clients.users.base-url}", configuration = FeignConfig.class)
public interface IUserFeignClients {

    @GetMapping("/{id}")
    UserResponseDto getUserById(@PathVariable(value = "id") Long userId);

}