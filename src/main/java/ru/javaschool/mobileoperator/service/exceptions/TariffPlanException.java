package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TariffPlanException extends RuntimeException {
    public TariffPlanException() {
    }

    public TariffPlanException(String message) {
        super(message);
    }

    public TariffPlanException(String message, Throwable cause) {
        super(message, cause);
    }

    public TariffPlanException(Throwable cause) {
        super(cause);
    }

    public TariffPlanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
