package com.devdive.backend.security.core.resolver;

import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.UserDetails;
import com.devdive.backend.security.authentication.token.AnonymousAuthenticationToken;
import com.devdive.backend.security.core.securitycontext.SecurityContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SecurityContextResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new UserDetails() {
                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public Long getId() {
                    return null;
                }
            };
        }

        return authentication.getPrincipal();
    }
}
