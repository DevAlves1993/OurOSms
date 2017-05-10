package org.akanza.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Component
public class TokenUtils
{
    private final Logger LOG = LoggerFactory.getLogger(TokenUtils.class);

    private final String SUB = "sub";
    private final String AUTHORITIES = "role";
    private final String PASSWORD = "password";
    private final String CREATED = "created";
    private final String EXPIRED = "expired";
    private final String TOKEN_SECRET_KEY = "secretKey";
    private final String TOKEN_ID = "secretId";

    @Value("${akanza.jwt.expiration}")
    public long expiration;

    @Value("${akanza.jwt.token}")
    public String secret;

    public String createToken(String login,String password,String authorities)
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put(SUB,login);
        claims.put(PASSWORD,password); // Save Original Password. No Hash Password
        claims.put(AUTHORITIES,authorities);
        LocalDateTime now = LocalDateTime.now();
        claims.put(CREATED,now);
        claims.put(EXPIRED,now.plusHours(1));
        return encoded(claims);
    }

    public String refreshToken(String token)
    {
        Claims claims = getClaims(token);
        LocalDateTime now = LocalDateTime.now();
        claims.put(CREATED,now);
        claims.put(EXPIRED,now.plusHours(1));
        return encoded(claims);
    }


    public boolean exist(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims != null;
    }

    public boolean isExpiate(String token)
    {
        Claims claims = getClaims(token);
        LocalDateTime expiateDate = (LocalDateTime) claims.get(EXPIRED);
        return LocalDateTime.now().isAfter(expiateDate);
    }

    public List<GrantedAuthority> getAuthorityInToken(String token)
    {
        Claims claims = getClaims(token);
        String authorities = (String) claims.get(AUTHORITIES);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    public String getLoginInToken(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(SUB);
    }

    public String getPasswordInToken(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(PASSWORD);
    }

    public String getSecretKey(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(TOKEN_SECRET_KEY);
    }

    public String getSecretId(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(TOKEN_ID);
    }

    private String encoded(Map<String, Object> claims)
    {
        String token =  Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.ES256,secret)
                .compact();
        LOG.info("New token is create");
        return token;
    }

    private Claims getClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }
}
