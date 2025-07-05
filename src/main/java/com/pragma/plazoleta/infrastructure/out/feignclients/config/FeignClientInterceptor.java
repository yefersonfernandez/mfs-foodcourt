package com.pragma.plazoleta.infrastructure.out.feignclients.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.pragma.plazoleta.infrastructure.security.util.SecurityConstants.HEADER_AUTHORIZATION;

@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = getBearerTokenHeader();
        if (token != null && !token.isEmpty()) {
            template.header(HEADER_AUTHORIZATION, token);
        }
    }

    private String getBearerTokenHeader() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        return attributes.getRequest().getHeader(HEADER_AUTHORIZATION);
    }
}
