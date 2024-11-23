package org.itiszakk.cashflow.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$", Pattern.CASE_INSENSITIVE);

    private static final String TOKEN_MATCHER_GROUP = "token";

    private final AuthenticationManager authenticationManager;

    public TokenFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);
        TokenAuthentication authentication = new TokenAuthentication(token);

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        authenticationManager.authenticate(authentication);

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {

        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header == null) {
            return null;
        }

        Matcher matcher = AUTHORIZATION_PATTERN.matcher(header);

        return matcher.matches()
                ? matcher.group(TOKEN_MATCHER_GROUP)
                : null;
    }
}
