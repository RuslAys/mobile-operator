package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OptionException extends RuntimeException {
    public OptionException() {
    }

    public OptionException(String message) {
        super(message);
    }

    public OptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionException(Throwable cause) {
        super(cause);
    }

    public OptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
