package com.pragma.plazoleta.infrastructure.security.util;

public final class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    public static final String[] SWAGGER_PATHS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    public static final String INVALID_JWT_MESSAGE = "El token JWT no es válido!";

    public static final String BODY_KEY_MESSAGE = "message";
    public static final String BODY_KEY_ERROR = "error";

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }
}
