package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserDisabledException extends AuthenticationException {
    public UserDisabledException(String msg, Throwable t) {
        super(msg, t);
    }
    public UserDisabledException(String msg) {
        super(msg);
    }
}
