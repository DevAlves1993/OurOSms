package org.akanza.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final String DEFINITIVELY_EXPIRED =  "definitively_expired";
    private final String USER_ID = "userId";
    private final String ORANGE_SECRET_ID = "secretId";
    private final String ORANGE_SECRET_KEY = "secretKey";


    @Value("${akanza.jwt.token}")
    String secret;

    @Value("${akanza.jwt.tokenForOrange}")
    String secretOrange;

    public String createToken(String login,String password,String authorities,String userId)
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put(SUB,login);
        claims.put(PASSWORD,password); // Save Original Password. No Hash Password
        claims.put(USER_ID,userId);
        claims.put(AUTHORITIES,authorities);
        LocalDateTime now = LocalDateTime.now();
        claims.put(CREATED,now.toString());
        claims.put(EXPIRED,now.plusHours(1).toString());
        claims.put(DEFINITIVELY_EXPIRED,now.plusDays(1).toString());
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
        String data = (String) claims.get(EXPIRED);
        LocalDateTime expiateDate = LocalDateTime.parse(data);
        return LocalDateTime.now().isAfter(expiateDate);
    }

    public boolean isDefinitivelyExpired(String token)
    {
        Claims claims = getClaims(token);
        String data = (String) claims.get(DEFINITIVELY_EXPIRED);
        LocalDateTime expiateDate = LocalDateTime.parse(data);
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

    public String getUserId(String token)
    {
        Claims claims = getClaims(token);
        return (String) claims.get(USER_ID);
    }

    private String encoded(Map<String, Object> claims)
    {
        String token =  Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        LOG.info("New token is create");
        return token;
    }

    private Claims getClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Claims getOrangeClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(secretOrange)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getOrangeSecretKey(String token)
    {
        Claims claims = getOrangeClaims(token);
        return (String) claims.get(ORANGE_SECRET_KEY);
    }

    public String getOrangeSecretId(String token)
    {
        Claims claims = getOrangeClaims(token);
        return (String) claims.get(ORANGE_SECRET_ID);
    }
}
