package com.uab.gr10.news.authentication.infraestructure.configuration.security;

import com.uab.gr10.news.authentication.infraestructure.exceptions.AuthTokenException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class GR10AuthenticationEntryPoint implements AuthenticationEntryPoint
{
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException authException)
    {
        if (authException != null)
        {
            resolver.resolveException(request, response, null, new AuthTokenException(
                "Token",
                authException.getLocalizedMessage()));
        }
    }
}
