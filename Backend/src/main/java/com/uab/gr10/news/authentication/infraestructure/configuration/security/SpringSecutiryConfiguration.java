package com.uab.gr10.news.authentication.infraestructure.configuration.security;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Component
public class SpringSecutiryConfiguration
{
    @Autowired
    private Environment env;
    private final CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
    private final GR10AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SpringSecutiryConfiguration(final CustomJwtAuthenticationFilter customJwtAuthenticationFilter,
        final GR10AuthenticationEntryPoint jwtAuthenticationEntryPoint)
    {
        this.customJwtAuthenticationFilter = customJwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    /**
     * This method is used to create an encryption method usually used in passwords.
     *
     * @return encryption method (bcrypt algorithm).
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * This function authorizes api calls with disable security into the application.
     *
     * @param http - allows configuring web based security for specific http requests
     * @return a register with according the security filter chain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.cors();
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers(HttpMethod.GET, "/logout").permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
            .logoutSuccessUrl("/logout")
            .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(
                customJwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * This function create a bean for AuthenticationManager.
     *
     * @param authenticationConfiguration - authentication Configuration to get authentication
     *                                    manager
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * This function is for cors configuration.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of(env.getProperty("react.origin.url")));
        configuration.setAllowedMethods(ImmutableList.of(
            "HEAD",
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of(
            "Authorization",
            "Cache-Control",
            "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Ignores the security in many urls related to backend.
     *
     * @return a filter in the Proxy with urls ignore in the authorization.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return (web) ->
            web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui/**");
    }
}
