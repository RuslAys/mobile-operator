package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContractException extends RuntimeException {
    public ContractException() {
    }

    public ContractException(String message) {
        super(message);
    }

    public ContractException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContractException(Throwable cause) {
        super(cause);
    }

    public ContractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
