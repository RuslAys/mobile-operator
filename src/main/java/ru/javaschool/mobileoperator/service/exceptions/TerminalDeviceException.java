package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TerminalDeviceException extends RuntimeException {
    public TerminalDeviceException() {
    }

    public TerminalDeviceException(String message) {
        super(message);
    }

    public TerminalDeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalDeviceException(Throwable cause) {
        super(cause);
    }

    public TerminalDeviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
