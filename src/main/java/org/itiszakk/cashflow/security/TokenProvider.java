package org.itiszakk.cashflow.security;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.itiszakk.cashflow.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.token.secret-key}")
    private String JWT_TOKEN_SECRET_KEY;

    @Value("${jwt.token.expiration-time:3600}")
    private long JWT_TOKEN_EXPIRATION_TIME;

    public String generate(@NonNull User user) {

        String login = user.getLogin();

        if (login == null) {
            return null;
        }

        Instant now = Instant.now();
        Instant validity = now.plus(Duration.ofSeconds(JWT_TOKEN_EXPIRATION_TIME).toMillis(), ChronoUnit.MILLIS);

        return Jwts.builder()
                .subject(login)
                .issuedAt(Date.from(now))
                .expiration(Date.from(validity))
                .signWith(getSecretKey())
                .compact();
    }

    public String parse(String token) {

        if (token == null) {
            return null;
        }

        JwtParser parser = Jwts.parser()
                .verifyWith(getSecretKey())
                .build();

        return parser.parse(token)
                .accept(Jws.CLAIMS)
                .getPayload()
                .getSubject();
    }

    private SecretKey getSecretKey() {
        return StringUtils.isBlank(JWT_TOKEN_SECRET_KEY)
                ? Jwts.SIG.HS256.key().build()
                : Keys.hmacShaKeyFor(JWT_TOKEN_SECRET_KEY.getBytes());
    }
}
