package org.itiszakk.cashflow.util;

import org.itiszakk.cashflow.security.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static TokenAuthentication getCurrentAuthentication() {
        return (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUser() {
        return getCurrentAuthentication().getLogin();
    }
}
