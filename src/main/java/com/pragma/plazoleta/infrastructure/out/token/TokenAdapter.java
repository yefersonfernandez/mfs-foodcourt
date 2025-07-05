package com.pragma.plazoleta.infrastructure.out.token;

import com.pragma.plazoleta.domain.spi.ITokenPort;
import com.pragma.plazoleta.infrastructure.exception.MissingTokenException;
import com.pragma.plazoleta.infrastructure.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.pragma.plazoleta.infrastructure.security.util.SecurityConstants.HEADER_AUTHORIZATION;
import static com.pragma.plazoleta.infrastructure.security.util.SecurityConstants.TOKEN_PREFIX;
import static com.pragma.plazoleta.infrastructure.utils.ErrorMessages.MISSING_TOKEN;

@RequiredArgsConstructor
public class TokenAdapter implements ITokenPort {

    private final JwtUtil jwtUtil;

    @Override
    public String getBearerToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new MissingTokenException(MISSING_TOKEN);
        }

        String header = attributes.getRequest().getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw new MissingTokenException(MISSING_TOKEN);
        }

        return header.substring(TOKEN_PREFIX.length());
    }

    @Override
    public Long getAuthenticatedUserId(String token) {
        return jwtUtil.extractUserId(token);
    }

    @Override
    public String getAuthenticatedUserRole(String token) {
        return jwtUtil.extractUserRole(token);
    }

    @Override
    public String getAuthenticatedUsername(String token) {
        return jwtUtil.extractUsername(token);
    }
}
