package com.uab.gr10.news.authentication.infraestructure.configuration.security;

import com.uab.gr10.news.authentication.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil
{
    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.refreshExpirationDateInMs}")
    private int refreshExpirationDateInMs;

    /**
     * This method is used to generate a token for an user.
     *
     * @param user
     * is the User for which the token will be generated
     * @return string with the token generated.
     */
    public String generateToken(User user)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());
        claims.put("id", user.getId());
        logger.info("Token had been generated");
        return generateToken(claims, user.getUsername());
    }

    private String generateToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs))
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
