package com.pragma.plazoleta.domain.spi;

public interface ITokenPort {

    String getBearerToken();

    Long getAuthenticatedUserId(String token);

    String getAuthenticatedUserRole(String token);

    String getAuthenticatedUsername(String token);
}

