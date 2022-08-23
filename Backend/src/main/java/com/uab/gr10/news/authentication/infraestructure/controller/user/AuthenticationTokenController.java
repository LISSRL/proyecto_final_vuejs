package com.uab.gr10.news.authentication.infraestructure.controller.user;

import com.uab.gr10.news.authentication.domain.user.Account;
import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.infraestructure.configuration.security.JwtUtil;
import com.uab.gr10.news.authentication.infraestructure.dto.UserCredentialsDTO;
import com.uab.gr10.news.authentication.infraestructure.exceptions.AuthTokenException;
import com.uab.gr10.news.authentication.infraestructure.usecase.user.fetch.UserFetchUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin(origins = "${vue.origin.url}")
public class AuthenticationTokenController
{

    private static final Logger logger = LogManager.getLogger(AuthenticationTokenController.class);
    private final AuthenticationManager authenticationManager;
    private final UserFetchUseCase userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationTokenController(
        final AuthenticationManager authenticationManager,
        final UserFetchUseCase userDetailsService,
        final JwtUtil jwtUtil)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Account> createAuthenticationToken(
        HttpServletRequest request,
        @RequestBody UserCredentialsDTO userCredentialsDTO) throws Exception
    {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userCredentialsDTO.getUsernameOrEmail(),
                userCredentialsDTO.getPassword());
        try
        {
            authenticationManager.authenticate(authentication);
        }
        catch (BadCredentialsException | InternalAuthenticationServiceException e)
        {
            logger.warn("The user failed trying to login, with message: " + e.getMessage());
            throw new Exception(e);
        }
        try
        {
            User user =
                userDetailsService.loadUserByUsername(userCredentialsDTO.getUsernameOrEmail());
            String token = jwtUtil.generateToken(user);
            Account account = new Account(user.getUsername(), "", "ok");
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
            account.setToken(token);
            logger.error("Login user success: " + user.getUsername());
            return ResponseEntity.ok(account);
        }
        catch (Exception e)
        {
            logger.error("Problem with the token generation, with message: " + e.getMessage());
            throw new AuthTokenException("Problem with the token generation", e.getMessage());
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if (session != null)
        {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.getAuthentication().setAuthenticated(false);
            SecurityContextHolder.clearContext();
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, null);
            session.invalidate();
        }
    }
}
