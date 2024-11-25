package org.itiszakk.cashflow.security;

import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.itiszakk.cashflow.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenProvider {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    private static final long TOKEN_EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);

    public String generate(@NonNull User user) {

        String login = user.getLogin();

        if (login == null) {
            return null;
        }

        Instant now = Instant.now();
        Instant validity = now.plus(TOKEN_EXPIRATION_TIME, ChronoUnit.MILLIS);

        return Jwts.builder()
                .subject(login)
                .issuedAt(Date.from(now))
                .expiration(Date.from(validity))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String parse(String token) {

        if (token == null) {
            return null;
        }

        JwtParser parser = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build();

        return parser.parse(token)
                .accept(Jws.CLAIMS)
                .getPayload()
                .getSubject();
    }
}
