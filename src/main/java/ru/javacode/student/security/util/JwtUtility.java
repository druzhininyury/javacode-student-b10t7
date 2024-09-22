package ru.javacode.student.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    public static final long ACCESS_TOKEN_DURATION = 60 * 1000; // milliseconds
    public static final long REFRESH_TOKEN_DURATION = 24 * 60 * 60 * 1000; // milliseconds
    private static final String SECRET = "rudotjavacodedotstudentdotsecret";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String createToken(String username, long duration) {
        long currentEpochTime = System.currentTimeMillis();

        return Jwts.builder()
                   .subject(username)
                   .issuedAt(new Date(currentEpochTime))
                   .expiration(new Date(currentEpochTime + duration))
                   .signWith(SECRET_KEY)
                   .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return  (Claims) Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parse(token)
                .getPayload();
    }

}
