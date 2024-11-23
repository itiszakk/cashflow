package org.itiszakk.cashflow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        processAuthentication((TokenAuthentication) authentication);
        return authentication;
    }

    private void processAuthentication(TokenAuthentication authentication) {

        String login = tokenProvider.parse(authentication.getToken());

        if (login == null) {
            return;
        }

        authentication.setLogin(login);
        authentication.setAuthenticated(true);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(TokenAuthentication.class);
    }
}
